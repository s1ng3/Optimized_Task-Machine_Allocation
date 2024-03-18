<h2 style="color:blue; font-size: 24px">🎯 Optimized Task-Machine Allocation</h2>


## <span style="color:orange">🚀 Accessing the Solution</span>

To access the solution, follow these steps:
   
   <span style="color:green">**Run the Application**</span>: Navigate to the `src` folder and execute `Main.java` to launch the application's graphical user interface (GUI).

## <span style="color:orange">🔍 Overview</span>

The scheduling application comprises several classes that work together to parse input files, schedule operations, and display schedules:

- <span style="color:green">**Main**</span>: Contains the main method and implementation of parsing input files, scheduling operations, and displaying schedules.
- <span style="color:green">**Machine**</span>: Represents a machine with attributes such as ID, name, capacity, and cooldown time.
- <span style="color:green">**Part**</span>: Represents a part with attributes like ID, name, and quantity.
- <span style="color:green">**Operation**</span>: Represents an operation with attributes including part ID, machine name, and processing time.
- <span style="color:green">**ScheduledOperation**</span>: Represents a scheduled operation with attributes such as part ID, operation index, start time, and end time.

## <span style="color:orange">⚙️ Functionality</span>

The application performs the following tasks:

1. <span style="color:green">**Parsing Input Files**</span>:
   - Reads input files containing information about machines, parts, and operations.
   - Populates lists of machines, parts, and operations by parsing the input files.

2. <span style="color:green">**Displaying Parsed Data**</span>:
   - Displays the list of available machines, parts, and operations parsed from the input files.

3. <span style="color:green">**Simple Scheduling**</span>:
   - Schedules operations for each part using a simple scheduling algorithm.
   - Calculates start and end times for each operation based on the availability of machines.

4. <span style="color:green">**Flexible Scheduling**</span>:
   - Schedules operations for each part using a flexible scheduling algorithm.
   - Considers the availability of each machine and schedules operations accordingly.

5. <span style="color:green">**Displaying Schedules**</span>:
   - Displays the scheduled operations in a graphical user interface (GUI).
   - Shows the start and end times for each operation in the schedule.

## <span style="color:orange">🎨 Customization</span>

The application provides customization options for the graphical user interface (GUI):

- <span style="color:green">**GUI Design**</span>: The GUI is designed using Swing components for a cross-platform user interface.
- <span style="color:green">**Background Color**</span>: The background color of the GUI is set to black for a sleek appearance.
- <span style="color:green">**Font and Text**</span>: Custom fonts and text colors are used to enhance readability and aesthetics of the GUI.

## <span style="color:orange">👩‍💻 Usage</span>

To use the scheduling application, follow these steps:

1. <span style="color:green">**Input Files**</span>: Provide input files containing machine, part, and operation information.
2. <span style="color:green">**Run Application**</span>: Execute the application to parse the input files and schedule operations.
3. <span style="color:green">**View Schedules**</span>: View the parsed data and scheduled operations in the graphical user interface (GUI).

## <span style="color:orange">🎉 Conclusion</span>

The scheduling application offers a convenient and efficient way to parse input files, schedule operations, and visualize the schedules. It provides both simple and flexible scheduling algorithms to accommodate various scheduling requirements and offers customization options for the graphical user interface.
