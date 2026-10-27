package p;

import com.spotify.player.esperanto.proto.EsCommandOptions$CommandOptions;
import com.spotify.player.esperanto.proto.EsContextTrack$ContextTrack;
import com.spotify.player.esperanto.proto.EsLoggingParams$LoggingParams;
import com.spotify.player.esperanto.proto.EsSkipPrev$SkipPrevRequest;

/* JADX INFO: loaded from: classes.dex */
public final class ch0 extends h01 implements yj1 {
    @Override // p.h01, p.vj1
    public final /* bridge */ /* synthetic */ wj1 buildPartial() {
        return super.buildPartial();
    }

    @Override // p.h01
    public final /* bridge */ /* synthetic */ Object clone() {
        return super.clone();
    }

    public final void d(boolean z) {
        copyOnWrite();
        EsSkipPrev$SkipPrevRequest.f((EsSkipPrev$SkipPrevRequest) this.instance, z);
    }

    public final void e(EsLoggingParams$LoggingParams esLoggingParams$LoggingParams) {
        copyOnWrite();
        EsSkipPrev$SkipPrevRequest.g((EsSkipPrev$SkipPrevRequest) this.instance, esLoggingParams$LoggingParams);
    }

    public final void f(EsCommandOptions$CommandOptions esCommandOptions$CommandOptions) {
        copyOnWrite();
        EsSkipPrev$SkipPrevRequest.e((EsSkipPrev$SkipPrevRequest) this.instance, esCommandOptions$CommandOptions);
    }

    public final void g(EsContextTrack$ContextTrack esContextTrack$ContextTrack) {
        copyOnWrite();
        EsSkipPrev$SkipPrevRequest.h((EsSkipPrev$SkipPrevRequest) this.instance, esContextTrack$ContextTrack);
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
