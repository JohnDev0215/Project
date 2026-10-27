package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class ListThingRegistrationTaskReportsRequest extends AmazonWebServiceRequest implements Serializable {
    private Integer maxResults;
    private String nextToken;
    private String reportType;
    private String taskId;

    public String getTaskId() {
        return this.taskId;
    }

    public void setTaskId(String str) {
        this.taskId = str;
    }

    public ListThingRegistrationTaskReportsRequest withTaskId(String str) {
        this.taskId = str;
        return this;
    }

    public String getReportType() {
        return this.reportType;
    }

    public void setReportType(String str) {
        this.reportType = str;
    }

    public ListThingRegistrationTaskReportsRequest withReportType(String str) {
        this.reportType = str;
        return this;
    }

    public void setReportType(ReportType reportType) {
        this.reportType = reportType.toString();
    }

    public ListThingRegistrationTaskReportsRequest withReportType(ReportType reportType) {
        this.reportType = reportType.toString();
        return this;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public ListThingRegistrationTaskReportsRequest withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public Integer getMaxResults() {
        return this.maxResults;
    }

    public void setMaxResults(Integer num) {
        this.maxResults = num;
    }

    public ListThingRegistrationTaskReportsRequest withMaxResults(Integer num) {
        this.maxResults = num;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getTaskId() != null) {
            sb.append("taskId: " + getTaskId() + ",");
        }
        if (getReportType() != null) {
            sb.append("reportType: " + getReportType() + ",");
        }
        if (getNextToken() != null) {
            sb.append("nextToken: " + getNextToken() + ",");
        }
        if (getMaxResults() != null) {
            sb.append("maxResults: " + getMaxResults());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((getTaskId() == null ? 0 : getTaskId().hashCode()) + 31) * 31) + (getReportType() == null ? 0 : getReportType().hashCode())) * 31) + (getNextToken() == null ? 0 : getNextToken().hashCode())) * 31) + (getMaxResults() != null ? getMaxResults().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListThingRegistrationTaskReportsRequest)) {
            return false;
        }
        ListThingRegistrationTaskReportsRequest listThingRegistrationTaskReportsRequest = (ListThingRegistrationTaskReportsRequest) obj;
        if ((listThingRegistrationTaskReportsRequest.getTaskId() == null) ^ (getTaskId() == null)) {
            return false;
        }
        if (listThingRegistrationTaskReportsRequest.getTaskId() != null && !listThingRegistrationTaskReportsRequest.getTaskId().equals(getTaskId())) {
            return false;
        }
        if ((listThingRegistrationTaskReportsRequest.getReportType() == null) ^ (getReportType() == null)) {
            return false;
        }
        if (listThingRegistrationTaskReportsRequest.getReportType() != null && !listThingRegistrationTaskReportsRequest.getReportType().equals(getReportType())) {
            return false;
        }
        if ((listThingRegistrationTaskReportsRequest.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        if (listThingRegistrationTaskReportsRequest.getNextToken() != null && !listThingRegistrationTaskReportsRequest.getNextToken().equals(getNextToken())) {
            return false;
        }
        if ((listThingRegistrationTaskReportsRequest.getMaxResults() == null) ^ (getMaxResults() == null)) {
            return false;
        }
        return listThingRegistrationTaskReportsRequest.getMaxResults() == null || listThingRegistrationTaskReportsRequest.getMaxResults().equals(getMaxResults());
    }
}
