package com.spotify.cosmos.servicebasedrouter;

import com.spotify.cosmos.cosmos.Response;

/* JADX INFO: loaded from: classes.dex */
public final class ScopedRouterKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isSuccessful(Response response) {
        int status = response.getStatus();
        return 200 <= status && status < 300;
    }
}
