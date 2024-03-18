import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class Main {
    public static List<Machine> parseMachines(String filePath) throws IOException {
        List<Machine> machines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean readingMachines = false;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Available machines:")) {
                    readingMachines = true;
                    continue;
                }

                if (line.isEmpty() || line.startsWith("#") || !readingMachines)
                    continue;

                String[] parts = line.split("\\. ", 2);
                if (parts.length < 2) {
                    continue;
                }
                int id = Integer.parseInt(parts[0]);
                String[] machineInfo = parts[1].split(" - ");
                if (machineInfo.length < 3) {
                    continue;
                }
                String name = machineInfo[0].trim();
                String[] capacityInfo = machineInfo[1].split(": ");
                String capacity = capacityInfo.length >= 2 ? capacityInfo[1].trim() : "Unknown";
                String[] cooldownInfo = machineInfo[2].split(": ");
                String cooldownTime = cooldownInfo.length >= 2 ? cooldownInfo[1].trim() : "Unknown";

                machines.add(new Machine(id, name, capacity, cooldownTime));
            }
        }
        return machines;
    }
    public static List<Part> parseParts(String filePath) throws IOException {
        List<Part> parts = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean readingParts = false;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Part list:")) {
                    readingParts = true;
                    continue;
                }

                if (line.isEmpty() || line.startsWith("#") || !readingParts)
                    continue;

                String[] partsInfo = line.split("\\. ", 2);
                if (partsInfo.length < 2) {
                    continue;
                }
                int id = Integer.parseInt(partsInfo[0]);
                String[] partInfo = partsInfo[1].split(" - ");
                if (partInfo.length < 2) {
                    continue;
                }
                String name = partInfo[0].trim();
                String[] quantityInfo = partInfo[1].split(" ");
                int quantity = Integer.parseInt(quantityInfo.length > 0 ? quantityInfo[0] : "0");

                parts.add(new Part(id, name, quantity));
            }
        }
        return parts;
    }
    public static List<List<Operation>> parseOperations(String filePath, int numParts) throws IOException {
        List<List<Operation>> operationsList = new ArrayList<>();
        for (int i = 0; i < numParts; i++) {
            operationsList.add(new ArrayList<>());
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean readingOperations = false;
            int currentPart = 0;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Part operations:")) {
                    readingOperations = true;
                    continue;
                }

                if (line.isEmpty() || line.startsWith("#") || !readingOperations)
                    continue;

                if (line.startsWith(String.valueOf(currentPart + 1) + ":")) {
                    String[] operationInfo = line.split(" - ");
                    if (operationInfo.length < 2) {
                        continue;
                    }
                    for (int i = 1; i < operationInfo.length; i++) {
                        String[] parts = operationInfo[i].split(": ");
                        if (parts.length < 2) {
                            continue;
                        }
                        String machineName = parts[0].trim();
                        int processingTime = Integer.parseInt(parts[1].split(" ")[0]);
                        operationsList.get(currentPart).add(new Operation(currentPart + 1, machineName, processingTime));
                    }
                    currentPart++;
                }
            }
        }
        return operationsList;
    }

    public static void displayData(List<Machine> machines, List<Part> parts, List<List<Operation>> operationsList) {
        System.out.println("Available Machines:");
        for (Machine machine : machines) {
            System.out.println(machine);
        }
        System.out.println();

        System.out.println("Parts List:");
        for (Part part : parts) {
            System.out.println(part);
        }
        System.out.println();

        System.out.println("Part Operations:");
        for (int i = 0; i < operationsList.size(); i++) {
            System.out.println("Part " + (i + 1) + ":");
            for (Operation operation : operationsList.get(i)) {
                System.out.println(operation);
            }
            System.out.println();
        }
    }

    public static List<ScheduledOperation> scheduleOperations(List<Machine> machines, List<Part> parts, List<List<Operation>> operationsList) {
        PriorityQueue<ScheduledOperation> schedule = new PriorityQueue<>(Comparator.comparingInt(ScheduledOperation::getStartTime));
        Map<String, Integer> machineEndTimes = new HashMap<>();

        for (Machine machine : machines) {
            machineEndTimes.put(machine.getName(), 0);
        }

        for (int i = 0; i < parts.size(); i++) {
            Part part = parts.get(i);
            List<Operation> operations = operationsList.get(i);

            for (Operation operation : operations) {
                int partId = part.getId();
                String machineName = operation.getMachineName();
                int processingTime = operation.getProcessingTime();

                int startTime = machineEndTimes.getOrDefault(machineName, 0);

                int endTime = startTime + processingTime;
                machineEndTimes.put(machineName, endTime);

                schedule.offer(new ScheduledOperation(partId, operations.indexOf(operation), startTime, endTime));
            }
        }

        List<ScheduledOperation> result = new ArrayList<>();
        while (!schedule.isEmpty()) {
            result.add(schedule.poll());
        }

        return result;
    }

    public static List<ScheduledOperation> flexibleScheduleOperations(List<Machine> machines, List<Part> parts, List<List<Operation>> operationsList) {
        PriorityQueue<ScheduledOperation> schedule = new PriorityQueue<>(Comparator.comparingInt(ScheduledOperation::getStartTime));
        Map<String, Integer> machineEndTimes = new HashMap<>();

        for (Machine machine : machines) {
            machineEndTimes.put(machine.getName(), 0);
        }

        for (int i = 0; i < parts.size(); i++) {
            Part part = parts.get(i);
            List<Operation> operations = operationsList.get(i);

            for (Operation operation : operations) {
                int partId = part.getId();
                String machineName = operation.getMachineName();
                int processingTime = operation.getProcessingTime();

                int startTime = Integer.MAX_VALUE;
                for (Machine machine : machines) {
                    if (machine.getName().equals(machineName)) {
                        startTime = Math.max(startTime, machineEndTimes.get(machineName));
                        break;
                    }
                }

                int endTime = startTime + processingTime;
                machineEndTimes.put(machineName, endTime);

                schedule.offer(new ScheduledOperation(partId, operations.indexOf(operation), startTime, endTime));
            }
        }

        List<ScheduledOperation> result = new ArrayList<>(schedule);
        return result;
    }

    // The display method displays the operations for Stage 4
    public static void displaySchedule(List<ScheduledOperation> schedule) {
        JFrame frame = new JFrame("Scheduled Operations");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(schedule.size(), 1));

        Font font = new Font("Segoe UI", Font.PLAIN, 16);

        for (ScheduledOperation scheduledOperation : schedule) {
            int partId = scheduledOperation.getPartId();
            int operationIndex = scheduledOperation.getOperationIndex();
            int startTime = scheduledOperation.getStartTime();
            int endTime = scheduledOperation.getEndTime();
            String text = "Part " + partId + ", Operation " + (operationIndex + 1) + ": Start Time - " + formatTime(endTime) + ", End Time - " + formatTime(startTime);
            JLabel label = new JLabel(text);
            label.setFont(font);
            label.setForeground(Color.WHITE);
            label.setBackground(Color.BLACK);
            label.setOpaque(true);
            panel.add(label);
        }

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        frame.add(scrollPane);
        frame.setSize(600, 400);
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setVisible(true);
    }

    // Formatting time in hours:minutes:seconds format
    /// TODO: Correct hours for Machine 4
    public static String formatTime(int seconds) {
        int hours = Math.abs(seconds) % 1;
        int remainingSeconds = Math.abs(seconds) % 3600;
        int minutes = remainingSeconds / 60;
        int secs = remainingSeconds % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, secs);
    }


    public static void main(String[] args) {
        try {
            // Stage One - Parsing input files
            String inputFilePath = "Input_One.txt";
            List<Machine> machines = parseMachines(inputFilePath);
            List<Part> parts = parseParts(inputFilePath);
            List<List<Operation>> operationsList = parseOperations(inputFilePath, parts.size());

            // Display parsed data
            displayData(machines, parts, operationsList);

            // Stage Two - Simple Scheduling
            List<ScheduledOperation> schedule = scheduleOperations(machines, parts, operationsList);
            displaySchedule(schedule);

            // Stage Three - Flexible Scheduling
            String inputFilePath2 = "Input_Two.txt";
            List<Machine> machines2 = parseMachines(inputFilePath2);
            List<Part> parts2 = parseParts(inputFilePath2);
            List<List<Operation>> operationsList2 = parseOperations(inputFilePath2, parts2.size());

            List<ScheduledOperation> flexibleSchedule = flexibleScheduleOperations(machines2, parts2, operationsList2);
            displaySchedule(flexibleSchedule);
            System.out.println("\nTo be fixed for Part 4");

        } catch (IOException e) {
            System.err.println("Error reading input file: " + e.getMessage());
        }
    }
}
