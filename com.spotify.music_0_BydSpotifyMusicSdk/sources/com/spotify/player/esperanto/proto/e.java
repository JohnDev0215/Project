package com.spotify.player.esperanto.proto;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.protobuf.ByteString;
import com.google.protobuf.i;
import com.spotify.player.esperanto.proto.EsPreparePlayOptions$PreparePlayOptions;
import p.au;
import p.h01;
import p.r1;
import p.rl0;
import p.wj1;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public final class e extends h01 implements yj1 {
    @Override // p.h01, p.vj1
    public final /* bridge */ /* synthetic */ wj1 buildPartial() {
        return super.buildPartial();
    }

    @Override // p.h01
    public final /* bridge */ /* synthetic */ Object clone() {
        return super.clone();
    }

    public final void d(ImmutableSet immutableSet) {
        copyOnWrite();
        EsPreparePlayOptions$PreparePlayOptions.i((EsPreparePlayOptions$PreparePlayOptions) this.instance, immutableSet);
    }

    public final void e(ImmutableMap immutableMap) {
        copyOnWrite();
        EsPreparePlayOptions$PreparePlayOptions.o((EsPreparePlayOptions$PreparePlayOptions) this.instance).putAll(immutableMap);
    }

    public final void f(boolean z) {
        copyOnWrite();
        EsPreparePlayOptions$PreparePlayOptions.m((EsPreparePlayOptions$PreparePlayOptions) this.instance, z);
    }

    public final void g(EsPreparePlayOptions$PreparePlayOptions.AudioStream audioStream) {
        copyOnWrite();
        EsPreparePlayOptions$PreparePlayOptions.k((EsPreparePlayOptions$PreparePlayOptions) this.instance, audioStream);
    }

    @Override // p.h01, p.yj1
    public final /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
        return super.getDefaultInstanceForType();
    }

    public final void h(boolean z) {
        copyOnWrite();
        EsPreparePlayOptions$PreparePlayOptions.f((EsPreparePlayOptions$PreparePlayOptions) this.instance, z);
    }

    @Override // p.h01, p.r1
    public final /* bridge */ /* synthetic */ r1 internalMergeFrom(com.google.protobuf.a aVar) {
        return super.internalMergeFrom((i) aVar);
    }

    public final void j(String str) {
        copyOnWrite();
        EsPreparePlayOptions$PreparePlayOptions.n((EsPreparePlayOptions$PreparePlayOptions) this.instance, str);
    }

    public final void k(ByteString byteString) {
        copyOnWrite();
        EsPreparePlayOptions$PreparePlayOptions.e((EsPreparePlayOptions$PreparePlayOptions) this.instance, byteString);
    }

    public final void l(EsContextPlayerOptions$ContextPlayerOptionOverrides esContextPlayerOptions$ContextPlayerOptionOverrides) {
        copyOnWrite();
        EsPreparePlayOptions$PreparePlayOptions.h((EsPreparePlayOptions$PreparePlayOptions) this.instance, esContextPlayerOptions$ContextPlayerOptionOverrides);
    }

    public final void m(EsPreparePlayOptions$PreparePlayOptions.PrefetchLevel prefetchLevel) {
        copyOnWrite();
        EsPreparePlayOptions$PreparePlayOptions.j((EsPreparePlayOptions$PreparePlayOptions) this.instance, prefetchLevel);
    }

    @Override // p.h01, p.r1
    public final /* bridge */ /* synthetic */ r1 mergeFrom(au auVar, rl0 rl0Var) {
        return super.mergeFrom(auVar, rl0Var);
    }

    public final void n(EsOptional$OptionalInt64 esOptional$OptionalInt64) {
        copyOnWrite();
        EsPreparePlayOptions$PreparePlayOptions.q((EsPreparePlayOptions$PreparePlayOptions) this.instance, esOptional$OptionalInt64);
    }

    public final void o(String str) {
        copyOnWrite();
        EsPreparePlayOptions$PreparePlayOptions.l((EsPreparePlayOptions$PreparePlayOptions) this.instance, str);
    }

    public final void p(EsSkipToTrack$SkipToTrack esSkipToTrack$SkipToTrack) {
        copyOnWrite();
        EsPreparePlayOptions$PreparePlayOptions.p((EsPreparePlayOptions$PreparePlayOptions) this.instance, esSkipToTrack$SkipToTrack);
    }

    public final void r(boolean z) {
        copyOnWrite();
        EsPreparePlayOptions$PreparePlayOptions.g((EsPreparePlayOptions$PreparePlayOptions) this.instance, z);
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
