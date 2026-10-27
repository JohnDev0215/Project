package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class GetBehaviorModelTrainingSummariesResult implements Serializable {
    private String nextToken;
    private List<BehaviorModelTrainingSummary> summaries;

    public List<BehaviorModelTrainingSummary> getSummaries() {
        return this.summaries;
    }

    public void setSummaries(Collection<BehaviorModelTrainingSummary> collection) {
        if (collection == null) {
            this.summaries = null;
        } else {
            this.summaries = new ArrayList(collection);
        }
    }

    public GetBehaviorModelTrainingSummariesResult withSummaries(BehaviorModelTrainingSummary... behaviorModelTrainingSummaryArr) {
        if (getSummaries() == null) {
            this.summaries = new ArrayList(behaviorModelTrainingSummaryArr.length);
        }
        for (BehaviorModelTrainingSummary behaviorModelTrainingSummary : behaviorModelTrainingSummaryArr) {
            this.summaries.add(behaviorModelTrainingSummary);
        }
        return this;
    }

    public GetBehaviorModelTrainingSummariesResult withSummaries(Collection<BehaviorModelTrainingSummary> collection) {
        setSummaries(collection);
        return this;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public GetBehaviorModelTrainingSummariesResult withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getSummaries() != null) {
            sb.append("summaries: " + getSummaries() + ",");
        }
        if (getNextToken() != null) {
            sb.append("nextToken: " + getNextToken());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getSummaries() == null ? 0 : getSummaries().hashCode()) + 31) * 31) + (getNextToken() != null ? getNextToken().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof GetBehaviorModelTrainingSummariesResult)) {
            return false;
        }
        GetBehaviorModelTrainingSummariesResult getBehaviorModelTrainingSummariesResult = (GetBehaviorModelTrainingSummariesResult) obj;
        if ((getBehaviorModelTrainingSummariesResult.getSummaries() == null) ^ (getSummaries() == null)) {
            return false;
        }
        if (getBehaviorModelTrainingSummariesResult.getSummaries() != null && !getBehaviorModelTrainingSummariesResult.getSummaries().equals(getSummaries())) {
            return false;
        }
        if ((getBehaviorModelTrainingSummariesResult.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        return getBehaviorModelTrainingSummariesResult.getNextToken() == null || getBehaviorModelTrainingSummariesResult.getNextToken().equals(getNextToken());
    }
}
