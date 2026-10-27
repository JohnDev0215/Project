package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class ListJobExecutionsForThingResult implements Serializable {
    private List<JobExecutionSummaryForThing> executionSummaries;
    private String nextToken;

    public List<JobExecutionSummaryForThing> getExecutionSummaries() {
        return this.executionSummaries;
    }

    public void setExecutionSummaries(Collection<JobExecutionSummaryForThing> collection) {
        if (collection == null) {
            this.executionSummaries = null;
        } else {
            this.executionSummaries = new ArrayList(collection);
        }
    }

    public ListJobExecutionsForThingResult withExecutionSummaries(JobExecutionSummaryForThing... jobExecutionSummaryForThingArr) {
        if (getExecutionSummaries() == null) {
            this.executionSummaries = new ArrayList(jobExecutionSummaryForThingArr.length);
        }
        for (JobExecutionSummaryForThing jobExecutionSummaryForThing : jobExecutionSummaryForThingArr) {
            this.executionSummaries.add(jobExecutionSummaryForThing);
        }
        return this;
    }

    public ListJobExecutionsForThingResult withExecutionSummaries(Collection<JobExecutionSummaryForThing> collection) {
        setExecutionSummaries(collection);
        return this;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public ListJobExecutionsForThingResult withNextToken(String str) {
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
        if (obj == null || !(obj instanceof ListJobExecutionsForThingResult)) {
            return false;
        }
        ListJobExecutionsForThingResult listJobExecutionsForThingResult = (ListJobExecutionsForThingResult) obj;
        if ((listJobExecutionsForThingResult.getExecutionSummaries() == null) ^ (getExecutionSummaries() == null)) {
            return false;
        }
        if (listJobExecutionsForThingResult.getExecutionSummaries() != null && !listJobExecutionsForThingResult.getExecutionSummaries().equals(getExecutionSummaries())) {
            return false;
        }
        if ((listJobExecutionsForThingResult.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        return listJobExecutionsForThingResult.getNextToken() == null || listJobExecutionsForThingResult.getNextToken().equals(getNextToken());
    }
}
