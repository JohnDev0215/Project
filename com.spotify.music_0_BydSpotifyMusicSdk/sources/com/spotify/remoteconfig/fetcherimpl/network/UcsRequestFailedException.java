package com.spotify.remoteconfig.fetcherimpl.network;

import com.spotify.ucs.proto.v0.UcsResponseWrapper;
import p.u42;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class UcsRequestFailedException extends Exception {
    private final u42<UcsResponseWrapper> response;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UcsRequestFailedException(u42 u42Var) {
        super("UCS request failed!");
        v03.j(u42Var, "response");
        this.response = u42Var;
    }

    public final u42 a() {
        return this.response;
    }
}
