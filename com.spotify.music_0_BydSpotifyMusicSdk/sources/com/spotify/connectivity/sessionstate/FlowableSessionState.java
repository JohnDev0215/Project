package com.spotify.connectivity.sessionstate;

import io.reactivex.rxjava3.core.Flowable;
import p.dv0;

/* JADX INFO: loaded from: classes.dex */
public interface FlowableSessionState {
    Flowable<SessionState> sessionState();

    dv0 sessionStateFlow();
}
