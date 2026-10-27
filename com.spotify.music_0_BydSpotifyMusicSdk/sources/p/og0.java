package p;

import com.spotify.player.esperanto.proto.EsContextTrack$ContextTrack;
import com.spotify.player.esperanto.proto.EsProvidedTrack$ProvidedTrack;

/* JADX INFO: loaded from: classes.dex */
public final class og0 extends h01 implements yj1 {
    @Override // p.h01, p.vj1
    public final /* bridge */ /* synthetic */ wj1 buildPartial() {
        return super.buildPartial();
    }

    @Override // p.h01
    public final /* bridge */ /* synthetic */ Object clone() {
        return super.clone();
    }

    public final void d(EsContextTrack$ContextTrack esContextTrack$ContextTrack) {
        copyOnWrite();
        EsProvidedTrack$ProvidedTrack.e((EsProvidedTrack$ProvidedTrack) this.instance, esContextTrack$ContextTrack);
    }

    public final void e(String str) {
        copyOnWrite();
        EsProvidedTrack$ProvidedTrack.f((EsProvidedTrack$ProvidedTrack) this.instance, str);
    }

    @Override // p.h01, p.yj1
    public final /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
        return super.getDefaultInstanceForType();
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
