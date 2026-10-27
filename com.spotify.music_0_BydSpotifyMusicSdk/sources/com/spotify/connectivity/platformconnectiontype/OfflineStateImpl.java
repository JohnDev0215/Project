package com.spotify.connectivity.platformconnectiontype;

import com.spotify.connectivity.connectiontype.OfflineState;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class OfflineStateImpl implements OfflineState {
    private final OfflineState.State offlineState;

    public OfflineStateImpl(OfflineState.State state) {
        v03.j(state, "offlineState");
        this.offlineState = state;
    }

    @Override // com.spotify.connectivity.connectiontype.OfflineState
    public boolean offline() {
        OfflineState.State state = this.offlineState;
        return state == OfflineState.State.OFFLINE || state == OfflineState.State.FORCED_OFFLINE;
    }

    @Override // com.spotify.connectivity.connectiontype.OfflineState
    public OfflineState.State offlineState() {
        return this.offlineState;
    }
}
