package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class ListJobsResult implements Serializable {
    private List<JobSummary> jobs;
    private String nextToken;

    public List<JobSummary> getJobs() {
        return this.jobs;
    }

    public void setJobs(Collection<JobSummary> collection) {
        if (collection == null) {
            this.jobs = null;
        } else {
            this.jobs = new ArrayList(collection);
        }
    }

    public ListJobsResult withJobs(JobSummary... jobSummaryArr) {
        if (getJobs() == null) {
            this.jobs = new ArrayList(jobSummaryArr.length);
        }
        for (JobSummary jobSummary : jobSummaryArr) {
            this.jobs.add(jobSummary);
        }
        return this;
    }

    public ListJobsResult withJobs(Collection<JobSummary> collection) {
        setJobs(collection);
        return this;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public ListJobsResult withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getJobs() != null) {
            sb.append("jobs: " + getJobs() + ",");
        }
        if (getNextToken() != null) {
            sb.append("nextToken: " + getNextToken());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getJobs() == null ? 0 : getJobs().hashCode()) + 31) * 31) + (getNextToken() != null ? getNextToken().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListJobsResult)) {
            return false;
        }
        ListJobsResult listJobsResult = (ListJobsResult) obj;
        if ((listJobsResult.getJobs() == null) ^ (getJobs() == null)) {
            return false;
        }
        if (listJobsResult.getJobs() != null && !listJobsResult.getJobs().equals(getJobs())) {
            return false;
        }
        if ((listJobsResult.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        return listJobsResult.getNextToken() == null || listJobsResult.getNextToken().equals(getNextToken());
    }
}
