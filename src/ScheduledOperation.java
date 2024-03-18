import java.util.*;
public class ScheduledOperation {
    private int partId;
    private int operationIndex;
    private int startTime;
    private int endTime;

    public ScheduledOperation(int partId, int operationIndex, int startTime, int endTime) {
        this.partId = partId;
        this.operationIndex = operationIndex;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getPartId() {
        return partId;
    }

    public int getOperationIndex() {
        return operationIndex;
    }

    public int getStartTime() {
        return startTime;
    }

    public int getEndTime() {
        return endTime;
    }
}
