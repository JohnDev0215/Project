package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class ListJobTemplatesResult implements Serializable {
    private List<JobTemplateSummary> jobTemplates;
    private String nextToken;

    public List<JobTemplateSummary> getJobTemplates() {
        return this.jobTemplates;
    }

    public void setJobTemplates(Collection<JobTemplateSummary> collection) {
        if (collection == null) {
            this.jobTemplates = null;
        } else {
            this.jobTemplates = new ArrayList(collection);
        }
    }

    public ListJobTemplatesResult withJobTemplates(JobTemplateSummary... jobTemplateSummaryArr) {
        if (getJobTemplates() == null) {
            this.jobTemplates = new ArrayList(jobTemplateSummaryArr.length);
        }
        for (JobTemplateSummary jobTemplateSummary : jobTemplateSummaryArr) {
            this.jobTemplates.add(jobTemplateSummary);
        }
        return this;
    }

    public ListJobTemplatesResult withJobTemplates(Collection<JobTemplateSummary> collection) {
        setJobTemplates(collection);
        return this;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public ListJobTemplatesResult withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getJobTemplates() != null) {
            sb.append("jobTemplates: " + getJobTemplates() + ",");
        }
        if (getNextToken() != null) {
            sb.append("nextToken: " + getNextToken());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getJobTemplates() == null ? 0 : getJobTemplates().hashCode()) + 31) * 31) + (getNextToken() != null ? getNextToken().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListJobTemplatesResult)) {
            return false;
        }
        ListJobTemplatesResult listJobTemplatesResult = (ListJobTemplatesResult) obj;
        if ((listJobTemplatesResult.getJobTemplates() == null) ^ (getJobTemplates() == null)) {
            return false;
        }
        if (listJobTemplatesResult.getJobTemplates() != null && !listJobTemplatesResult.getJobTemplates().equals(getJobTemplates())) {
            return false;
        }
        if ((listJobTemplatesResult.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        return listJobTemplatesResult.getNextToken() == null || listJobTemplatesResult.getNextToken().equals(getNextToken());
    }
}
