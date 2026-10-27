package com.spotify.ucs.proto.v0;

import com.google.protobuf.i;
import com.spotify.rcs.resolver.grpc.v0.ResolveRequest;
import com.spotify.ucs.proto.v0.UcsRequest;
import p.au;
import p.h01;
import p.r1;
import p.rl0;
import p.wj1;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public final class b extends h01 implements yj1 {
    @Override // p.h01, p.vj1
    public final /* bridge */ /* synthetic */ wj1 buildPartial() {
        return super.buildPartial();
    }

    @Override // p.h01
    public final /* bridge */ /* synthetic */ Object clone() {
        return super.clone();
    }

    public final void d(a aVar) {
        copyOnWrite();
        UcsRequest.g((UcsRequest) this.instance, (UcsRequest.AccountAttributesRequest) aVar.build());
    }

    public final void e(c cVar) {
        copyOnWrite();
        UcsRequest.e((UcsRequest) this.instance, (UcsRequest.CallerInfo) cVar.build());
    }

    public final void f(ResolveRequest resolveRequest) {
        copyOnWrite();
        UcsRequest.f((UcsRequest) this.instance, resolveRequest);
    }

    @Override // p.h01, p.yj1
    public final /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
        return super.getDefaultInstanceForType();
    }

    @Override // p.h01, p.r1
    public final /* bridge */ /* synthetic */ r1 internalMergeFrom(com.google.protobuf.a aVar) {
        return super.internalMergeFrom((i) aVar);
    }

    @Override // p.h01, p.r1
    public final /* bridge */ /* synthetic */ r1 mergeFrom(au auVar, rl0 rl0Var) {
        return super.mergeFrom(auVar, rl0Var);
    }

    @Override // p.h01, p.r1
    public final /* bridge */ /* synthetic */ r1 mergeFrom(byte[] bArr, int i, int i2) {
        return super.mergeFrom(bArr, 0, i2);
    }

    @Override // p.h01, p.r1
    public final /* bridge */ /* synthetic */ r1 mergeFrom(byte[] bArr, int i, int i2, rl0 rl0Var) {
        return super.mergeFrom(bArr, 0, i2, rl0Var);
    }
}
