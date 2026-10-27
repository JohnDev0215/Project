package com.spotify.connectivity.platformconnectiontype;

import com.spotify.connectivity.connectiontype.OfflineState;

/* JADX INFO: loaded from: classes.dex */
final class AutoValue_OfflineStateCosmos extends OfflineStateCosmos {
    private final OfflineState.State offlineState;

    public AutoValue_OfflineStateCosmos(OfflineState.State state) {
        if (state == null) {
            throw new NullPointerException("Null offlineState");
        }
        this.offlineState = state;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof OfflineStateCosmos) {
            return this.offlineState.equals(((OfflineStateCosmos) obj).offlineState());
        }
        return false;
    }

    public int hashCode() {
        return this.offlineState.hashCode() ^ 1000003;
    }

    @Override // com.spotify.connectivity.platformconnectiontype.OfflineStateCosmos, com.spotify.connectivity.connectiontype.OfflineState
    public OfflineState.State offlineState() {
        return this.offlineState;
    }

    public String toString() {
        return "OfflineStateCosmos{offlineState=" + this.offlineState + "}";
    }
}
