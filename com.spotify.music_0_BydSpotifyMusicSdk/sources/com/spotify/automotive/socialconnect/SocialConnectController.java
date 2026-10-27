package com.spotify.automotive.socialconnect;

import com.spotify.automotive.socialconnect.endpoint.SocialConnectSession;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

/* JADX INFO: loaded from: classes.dex */
public interface SocialConnectController {
    Single<SocialConnectSession> getCurrentSession();

    Single<SocialConnectSession> getOrCreateSession();

    String getScannableUrl(SocialConnectSession socialConnectSession);

    Completable leaveSession();
}
