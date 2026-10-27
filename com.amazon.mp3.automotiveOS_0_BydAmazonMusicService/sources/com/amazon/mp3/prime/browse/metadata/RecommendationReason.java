package com.amazon.mp3.prime.browse.metadata;

import java.io.Serializable;

/* JADX INFO: loaded from: classes4.dex */
public class RecommendationReason implements Serializable {
    private String mReason;

    public RecommendationReason(String reason) {
        this.mReason = reason;
    }

    public String getReason() {
        return this.mReason;
    }
}
