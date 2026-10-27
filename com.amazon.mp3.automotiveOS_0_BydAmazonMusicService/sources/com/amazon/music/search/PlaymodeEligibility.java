package com.amazon.music.search;

import java.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
public final class PlaymodeEligibility {
    private final com.amazon.music.platform.model.PlaymodeEligibility playmodeEligibility;

    PlaymodeEligibility(com.amazon.music.platform.model.PlaymodeEligibility playmodeEligibility) {
        Objects.requireNonNull(playmodeEligibility, "playmodeEligibility cannot be null");
        this.playmodeEligibility = playmodeEligibility;
    }

    public Boolean isOnDemandPlayable() {
        return this.playmodeEligibility.isOnDemand();
    }

    public Boolean isStationFromAnythingPlayable() {
        return this.playmodeEligibility.isSfa();
    }

    public String getShuffleType() {
        return this.playmodeEligibility.getShuffleType();
    }
}
