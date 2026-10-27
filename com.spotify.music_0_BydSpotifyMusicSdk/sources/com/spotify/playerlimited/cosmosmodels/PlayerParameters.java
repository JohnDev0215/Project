package com.spotify.playerlimited.cosmosmodels;

import com.spotify.player.legacyplayer.LoggingParameters;
import com.spotify.player.model.PlayOrigin;
import p.c71;
import p.pt1;
import p.v03;
import p.wt1;
import p.z61;

/* JADX INFO: loaded from: classes.dex */
@c71(generateAdapter = true)
public final class PlayerParameters {
    public final wt1 a;
    public final pt1 b;
    public final PlayOrigin c;
    public final LoggingParameters d;

    public PlayerParameters(@z61(name = "context") wt1 wt1Var, @z61(name = "options") pt1 pt1Var, @z61(name = "play_origin") PlayOrigin playOrigin, @z61(name = "logging_params") LoggingParameters loggingParameters) {
        v03.j(loggingParameters, "loggingParams");
        this.a = wt1Var;
        this.b = pt1Var;
        this.c = playOrigin;
        this.d = loggingParameters;
    }
}
