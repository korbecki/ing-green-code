package org.example.atmservice.dto;

public record Task(int region, RequestTypeEnum requestType, int atmId) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        if (region != task.region) return false;
        if (atmId != task.atmId) return false;
        return requestType == task.requestType;
    }

    @Override
    public int hashCode() {
        int result = region;
        result = 31 * result + requestType.hashCode();
        result = 31 * result + atmId;
        return result;
    }

    @Override
    public String toString() {
        return "Task{" +
                "region=" + region +
                ", requestType=" + requestType +
                ", atmId=" + atmId +
                '}';
    }
}
