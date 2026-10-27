package com.spotify.connectivity.sessionapi;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import java.util.Map;
import p.lq2;

/* JADX INFO: loaded from: classes.dex */
public interface SessionClient {
    Observable<APPermanentErrorResult> apPermanentError();

    Single<SendEndSongsResult> sendEndSongs();

    Observable<lq2> willLogoutAndForgetCurrentUser();

    Single<lq2> writeProductStateToLegacyStorage(Map<String, String> map);
}
