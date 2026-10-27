package com.spotify.player.esperanto.proto;

import com.google.protobuf.i;
import com.spotify.player.esperanto.proto.EsSeekTo$SeekToRequest;
import p.au;
import p.h01;
import p.r1;
import p.rl0;
import p.wj1;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public final class g extends h01 implements yj1 {
    @Override // p.h01, p.vj1
    public final /* bridge */ /* synthetic */ wj1 buildPartial() {
        return super.buildPartial();
    }

    @Override // p.h01
    public final /* bridge */ /* synthetic */ Object clone() {
        return super.clone();
    }

    public final void d(EsLoggingParams$LoggingParams esLoggingParams$LoggingParams) {
        copyOnWrite();
        EsSeekTo$SeekToRequest.g((EsSeekTo$SeekToRequest) this.instance, esLoggingParams$LoggingParams);
    }

    public final void e(EsCommandOptions$CommandOptions esCommandOptions$CommandOptions) {
        copyOnWrite();
        EsSeekTo$SeekToRequest.e((EsSeekTo$SeekToRequest) this.instance, esCommandOptions$CommandOptions);
    }

    public final void f(long j) {
        copyOnWrite();
        EsSeekTo$SeekToRequest.h((EsSeekTo$SeekToRequest) this.instance, j);
    }

    public final void g(EsSeekTo$SeekToRequest.Relative relative) {
        copyOnWrite();
        EsSeekTo$SeekToRequest.f((EsSeekTo$SeekToRequest) this.instance, relative);
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
