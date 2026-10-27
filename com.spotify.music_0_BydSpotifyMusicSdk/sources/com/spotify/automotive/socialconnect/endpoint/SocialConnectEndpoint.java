package com.spotify.automotive.socialconnect.endpoint;

import com.spotify.connectivity.productstate.RxProductState;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import p.ez1;
import p.f50;
import p.gr1;
import p.ns1;
import p.sz0;
import p.u42;

/* JADX INFO: loaded from: classes.dex */
public interface SocialConnectEndpoint {
    @f50("social-connect/v3/sessions/{sessionId}")
    Completable deleteSession(@ns1("sessionId") String str, @ez1("local_device_id") String str2);

    @sz0("social-connect/v2/sessions/current_or_new")
    Single<u42<SocialConnectSession>> getCurrentOrNewSession(@ez1("local_device_id") String str, @ez1(RxProductState.Keys.KEY_TYPE) String str2);

    @sz0("social-connect/v2/sessions/current")
    Single<u42<SocialConnectSession>> getCurrentSession(@ez1("local_device_id") String str);

    @gr1("social-connect/v3/sessions/{sessionId}/leave")
    Completable leaveSession(@ns1("sessionId") String str, @ez1("local_device_id") String str2);
}
