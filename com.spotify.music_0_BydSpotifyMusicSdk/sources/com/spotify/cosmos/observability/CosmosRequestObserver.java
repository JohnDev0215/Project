package com.spotify.cosmos.observability;

import com.spotify.cosmos.cosmos.Request;
import com.spotify.cosmos.cosmos.Response;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public interface CosmosRequestObserver {
    public static final Companion Companion = Companion.$$INSTANCE;

    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        private static final CosmosRequestObserver NO_OP = new CosmosRequestObserver() { // from class: com.spotify.cosmos.observability.CosmosRequestObserver$Companion$NO_OP$1
            @Override // com.spotify.cosmos.observability.CosmosRequestObserver
            public void onError(String str, Throwable th) {
                v03.j(th, "throwable");
            }

            @Override // com.spotify.cosmos.observability.CosmosRequestObserver
            public void onRequest(String str, Request request) {
                v03.j(request, "request");
            }

            @Override // com.spotify.cosmos.observability.CosmosRequestObserver
            public void onResponse(String str, Response response) {
                v03.j(response, "response");
            }
        };

        private Companion() {
        }

        public static /* synthetic */ void getNO_OP$annotations() {
        }

        public final CosmosRequestObserver getNO_OP() {
            return NO_OP;
        }
    }

    static CosmosRequestObserver getNO_OP() {
        return Companion.getNO_OP();
    }

    void onError(String str, Throwable th);

    void onRequest(String str, Request request);

    void onResponse(String str, Response response);
}
