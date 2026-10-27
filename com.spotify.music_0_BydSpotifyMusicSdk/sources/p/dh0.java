package p;

import com.spotify.player.esperanto.proto.EsOptional$OptionalInt64;
import com.spotify.player.esperanto.proto.EsSkipToTrack$SkipToTrack;

/* JADX INFO: loaded from: classes.dex */
public final class dh0 extends h01 implements yj1 {
    @Override // p.h01, p.vj1
    public final /* bridge */ /* synthetic */ wj1 buildPartial() {
        return super.buildPartial();
    }

    @Override // p.h01
    public final /* bridge */ /* synthetic */ Object clone() {
        return super.clone();
    }

    public final void d(EsOptional$OptionalInt64 esOptional$OptionalInt64) {
        copyOnWrite();
        EsSkipToTrack$SkipToTrack.h((EsSkipToTrack$SkipToTrack) this.instance, esOptional$OptionalInt64);
    }

    public final void e(String str) {
        copyOnWrite();
        EsSkipToTrack$SkipToTrack.e((EsSkipToTrack$SkipToTrack) this.instance, str);
    }

    public final void f(EsOptional$OptionalInt64 esOptional$OptionalInt64) {
        copyOnWrite();
        EsSkipToTrack$SkipToTrack.g((EsSkipToTrack$SkipToTrack) this.instance, esOptional$OptionalInt64);
    }

    public final void g(String str) {
        copyOnWrite();
        EsSkipToTrack$SkipToTrack.i((EsSkipToTrack$SkipToTrack) this.instance, str);
    }

    @Override // p.h01, p.yj1
    public final /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
        return super.getDefaultInstanceForType();
    }

    public final void h(String str) {
        copyOnWrite();
        EsSkipToTrack$SkipToTrack.f((EsSkipToTrack$SkipToTrack) this.instance, str);
    }

    @Override // p.h01, p.r1
    public final /* bridge */ /* synthetic */ r1 internalMergeFrom(com.google.protobuf.a aVar) {
        return super.internalMergeFrom((com.google.protobuf.i) aVar);
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
