package com.spotify.cosmos.fireandforgetresolver;

import com.spotify.cosmos.cosmos.Request;
import com.spotify.cosmos.cosmos.Response;
import com.spotify.cosmos.routercallback.ResolverCallbackReceiver;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

/* JADX INFO: loaded from: classes.dex */
public interface FireAndForgetResolver {
    void detached(Observable<? extends Response> observable);

    void detached(Observable<? extends Response> observable, ResolverCallbackReceiver<? extends Response> resolverCallbackReceiver);

    void detached(Single<? extends Response> single);

    void detached(Single<? extends Response> single, ResolverCallbackReceiver<? extends Response> resolverCallbackReceiver);

    void dispose();

    void reset();

    void resolve(Request request);

    void resolve(Request request, ResolverCallbackReceiver<? extends Response> resolverCallbackReceiver);
}
