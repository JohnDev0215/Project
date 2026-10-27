package com.spotify.esperanto.esperantocosmos;

import com.spotify.cosmos.cosmos.Request;
import com.spotify.cosmos.cosmos.Response;
import io.reactivex.rxjava3.core.Observable;

/* JADX INFO: loaded from: classes.dex */
public interface EsperantoRxRouter {
    Observable<Response> resolve(Request request);
}
