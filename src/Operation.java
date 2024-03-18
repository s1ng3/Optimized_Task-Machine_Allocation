import java.util.*;
public class Operation {
    private int partId;
    private String machineName;
    private int processingTime;

    public Operation(int partId, String machineName, int processingTime) {
        this.partId = partId;
        this.machineName = machineName;
        this.processingTime = processingTime;
    }

    @Override
    public String toString() {
        return "- " + machineName + ": " + processingTime + " seconds";
    }

    public String getMachineName() {
        return machineName;
    }

    public int getProcessingTime() {
        return processingTime;
    }

    public int getPartId() {
        return partId;
    }
}