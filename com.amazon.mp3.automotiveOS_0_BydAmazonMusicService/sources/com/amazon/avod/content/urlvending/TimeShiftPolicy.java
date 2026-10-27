package com.amazon.avod.content.urlvending;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import javax.annotation.concurrent.Immutable;

/* JADX INFO: loaded from: classes2.dex */
@JsonIgnoreProperties(ignoreUnknown = true)
@Immutable
public class TimeShiftPolicy {
    private final boolean mPlayPauseControlEnabled;
    private final boolean mPlayerControlEnabled;
    private final boolean mSeekBackwardEnabled;
    private final boolean mSeekForwardEnabled;

    @JsonCreator
    public TimeShiftPolicy(@JsonProperty("playerControlEnabled") boolean z, @JsonProperty("playPauseControlEnabled") boolean z2, @JsonProperty("seekBackwardEnabled") boolean z3, @JsonProperty("seekForwardEnabled") boolean z4) {
        this.mPlayerControlEnabled = z;
        this.mPlayPauseControlEnabled = z2;
        this.mSeekBackwardEnabled = z3;
        this.mSeekForwardEnabled = z4;
    }

    public boolean isSeekBackwardEnabled() {
        return this.mSeekBackwardEnabled;
    }

    public boolean isSeekForwardEnabled() {
        return this.mSeekForwardEnabled;
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("playerControlEnabled", this.mPlayerControlEnabled).add("playPauseControlEnabled", this.mPlayPauseControlEnabled).add("seekBackwardEnabled", this.mSeekBackwardEnabled).add("seekForwardEnabled", this.mSeekForwardEnabled).toString();
    }
}
