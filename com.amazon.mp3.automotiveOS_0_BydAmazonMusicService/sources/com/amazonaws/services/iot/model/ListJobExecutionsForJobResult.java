package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class ListJobExecutionsForJobResult implements Serializable {
    private List<JobExecutionSummaryForJob> executionSummaries;
    private String nextToken;

    public List<JobExecutionSummaryForJob> getExecutionSummaries() {
        return this.executionSummaries;
    }

    public void setExecutionSummaries(Collection<JobExecutionSummaryForJob> collection) {
        if (collection == null) {
            this.executionSummaries = null;
        } else {
            this.executionSummaries = new ArrayList(collection);
        }
    }

    public ListJobExecutionsForJobResult withExecutionSummaries(JobExecutionSummaryForJob... jobExecutionSummaryForJobArr) {
        if (getExecutionSummaries() == null) {
            this.executionSummaries = new ArrayList(jobExecutionSummaryForJobArr.length);
        }
        for (JobExecutionSummaryForJob jobExecutionSummaryForJob : jobExecutionSummaryForJobArr) {
            this.executionSummaries.add(jobExecutionSummaryForJob);
        }
        return this;
    }

    public ListJobExecutionsForJobResult withExecutionSummaries(Collection<JobExecutionSummaryForJob> collection) {
        setExecutionSummaries(collection);
        return this;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public ListJobExecutionsForJobResult withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getExecutionSummaries() != null) {
            sb.append("executionSummaries: " + getExecutionSummaries() + ",");
        }
        if (getNextToken() != null) {
            sb.append("nextToken: " + getNextToken());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getExecutionSummaries() == null ? 0 : getExecutionSummaries().hashCode()) + 31) * 31) + (getNextToken() != null ? getNextToken().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListJobExecutionsForJobResult)) {
            return false;
        }
        ListJobExecutionsForJobResult listJobExecutionsForJobResult = (ListJobExecutionsForJobResult) obj;
        if ((listJobExecutionsForJobResult.getExecutionSummaries() == null) ^ (getExecutionSummaries() == null)) {
            return false;
        }
        if (listJobExecutionsForJobResult.getExecutionSummaries() != null && !listJobExecutionsForJobResult.getExecutionSummaries().equals(getExecutionSummaries())) {
            return false;
        }
        if ((listJobExecutionsForJobResult.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        return listJobExecutionsForJobResult.getNextToken() == null || listJobExecutionsForJobResult.getNextToken().equals(getNextToken());
    }
}
