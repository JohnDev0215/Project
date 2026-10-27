package com.android.server.telecom.callfiltering;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

/* JADX INFO: loaded from: classes.dex */
public class CallFilter {
    private int mIndegree;
    public CallFilteringResult result;
    private List<CallFilter> mDependencies = new ArrayList();
    private List<CallFilter> mFollowings = new ArrayList();
    public CallFilteringResult mPriorStageResult = null;

    public CompletionStage<CallFilteringResult> startFilterLookup(CallFilteringResult callFilteringResult) {
        return CompletableFuture.completedFuture(callFilteringResult);
    }

    List<CallFilter> getDependencies() {
        return this.mDependencies;
    }

    void addDependency(CallFilter callFilter) {
        synchronized (this) {
            this.mDependencies.add(callFilter);
            this.mIndegree = this.mDependencies.size();
        }
    }

    List<CallFilter> getFollowings() {
        return this.mFollowings;
    }

    void addFollowings(CallFilter callFilter) {
        this.mFollowings.add(callFilter);
    }

    int decrementAndGetIndegree() {
        int i;
        synchronized (this) {
            i = this.mIndegree - 1;
            this.mIndegree = i;
        }
        return i;
    }

    public CallFilteringResult getResult() {
        CallFilteringResult callFilteringResult = this.result;
        if (callFilteringResult != null) {
            return callFilteringResult;
        }
        throw new NullPointerException("Result of this filter is null. This filter hasn't finished performing");
    }
}
