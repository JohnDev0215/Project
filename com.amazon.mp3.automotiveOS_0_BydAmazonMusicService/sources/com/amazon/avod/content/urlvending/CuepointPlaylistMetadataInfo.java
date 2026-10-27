package com.amazon.avod.content.urlvending;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;

/* JADX INFO: loaded from: classes2.dex */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CuepointPlaylistMetadataInfo {
    private final boolean mShowCountdownTimer;

    @JsonCreator
    public CuepointPlaylistMetadataInfo(@JsonProperty("showCountdownTimer") boolean z) {
        this.mShowCountdownTimer = z;
    }

    public boolean getShowCountdownTimer() {
        return this.mShowCountdownTimer;
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("showCountdownTimer", getShowCountdownTimer()).toString();
    }
}
