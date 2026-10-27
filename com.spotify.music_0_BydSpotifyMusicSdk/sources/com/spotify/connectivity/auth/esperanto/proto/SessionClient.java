package com.spotify.connectivity.auth.esperanto.proto;

import com.google.protobuf.Empty;
import com.spotify.connectivity.auth.esperanto.proto.EsSession;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

/* JADX INFO: loaded from: classes.dex */
public interface SessionClient {
    Observable<EsSession.APPermanentErrorResult> apPermanentError(Empty empty);

    Single<EsSession.SendEndSongsResult> sendEndSongs(Empty empty);

    Observable<Empty> willLogoutAndForgetCurrentUser(Empty empty);

    Single<Empty> writeProductStateToLegacyStorage(EsSession.ProductStateMap productStateMap);
}
