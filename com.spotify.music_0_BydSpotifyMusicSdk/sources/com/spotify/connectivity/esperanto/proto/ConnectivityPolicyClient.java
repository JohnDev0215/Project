package com.spotify.connectivity.esperanto.proto;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

/* JADX INFO: loaded from: classes.dex */
public interface ConnectivityPolicyClient {
    Single<ForceOfflineResponse> setForceOffline(ForceOfflineRequest forceOfflineRequest);

    Single<PutRulesResponse> setRules(PutRulesRequest putRulesRequest);

    Observable<GetStateResponse> subState(SubStateRequest subStateRequest);
}
