package org.example.atmservice.comparator;

import org.example.Constants;
import org.example.atmservice.dto.Task;

import java.util.Comparator;

public class TaskComparator implements Comparator<Task> {
    @Override
    public int compare(Task o1, Task o2) {
        int result;
        result = Integer.compare(o1.region(), o2.region());
        if (result == Constants.INT_ZERO)
            result = o1.requestType().compareTo(o2.requestType());
        return result;
    }
}
