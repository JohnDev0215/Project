package com.spotify.cosmos.rxrouter;

import com.spotify.cosmos.cosmos.Request;
import com.spotify.cosmos.cosmos.Response;
import io.reactivex.rxjava3.core.Observable;

/* JADX INFO: loaded from: classes.dex */
public interface RxRouter {
    Observable<Response> resolve(Request request);
}
