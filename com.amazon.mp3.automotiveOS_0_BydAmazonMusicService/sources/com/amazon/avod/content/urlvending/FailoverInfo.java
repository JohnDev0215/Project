package com.amazon.avod.content.urlvending;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class FailoverInfo {
    private final FailoverMode mFailoverMode;
    private final String mUrlSetId;

    @JsonCreator
    public FailoverInfo(@JsonProperty("urlSetId") @Nonnull String str, @JsonProperty("failoverMode") @Nonnull FailoverMode failoverMode) {
        this.mUrlSetId = (String) Preconditions.checkNotNull(str, "urlSetId");
        this.mFailoverMode = (FailoverMode) Preconditions.checkNotNull(failoverMode, "mode");
    }

    @Nonnull
    public String getUrlSetId() {
        return this.mUrlSetId;
    }

    @Nonnull
    public FailoverMode getFailoverMode() {
        return this.mFailoverMode;
    }
}
