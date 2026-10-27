package com.spotify.remoteconfig.fetcherimpl.network;

import com.spotify.rcs.resolver.grpc.v0.ResolveResponse;
import p.u42;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class ResolveRequestFailedException extends Exception {
    private final u42<ResolveResponse> response;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ResolveRequestFailedException(String str, u42 u42Var) {
        super(str);
        v03.j(str, "msg");
        v03.j(u42Var, "response");
        this.response = u42Var;
    }

    public final u42 a() {
        return this.response;
    }
}
