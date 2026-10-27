package com.spotify.player.legacyplayer;

import p.c71;
import p.v03;
import p.z61;

/* JADX INFO: loaded from: classes.dex */
@c71(generateAdapter = true)
public final class ActionParameters<T> {
    public final Object a;
    public final LoggingParameters b;

    public ActionParameters(@z61(name = "value") T t, @z61(name = "logging_params") LoggingParameters loggingParameters) {
        v03.j(loggingParameters, "loggingParams");
        this.a = t;
        this.b = loggingParameters;
    }
}
