package com.spotify.ucs.v0.proto;

import com.google.protobuf.i;
import com.spotify.rcs.resolver.grpc.v0.proto.Resolve$ResolveRequest;
import com.spotify.ucs.v0.proto.Ucs$UcsRequest;
import p.au;
import p.h01;
import p.r1;
import p.rl0;
import p.wj1;
import p.x32;
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
        Ucs$UcsRequest.g((Ucs$UcsRequest) this.instance, (Ucs$UcsRequest.AccountAttributesRequest) aVar.build());
    }

    public final void e(c cVar) {
        copyOnWrite();
        Ucs$UcsRequest.e((Ucs$UcsRequest) this.instance, (Ucs$UcsRequest.CallerInfo) cVar.build());
    }

    public final void f(x32 x32Var) {
        copyOnWrite();
        Ucs$UcsRequest.f((Ucs$UcsRequest) this.instance, (Resolve$ResolveRequest) x32Var.build());
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
