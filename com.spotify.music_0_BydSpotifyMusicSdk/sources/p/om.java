package p;

import com.spotify.bootstrap.v1.proto.Bootstrap$BootstrapRequest;
import com.spotify.bootstrap.v1.proto.Bootstrap$TrialsFacadeRequestV1;
import com.spotify.ucs.v0.proto.Ucs$UcsRequest;

/* JADX INFO: loaded from: classes.dex */
public final class om extends h01 implements yj1 {
    @Override // p.h01, p.vj1
    public final /* bridge */ /* synthetic */ wj1 buildPartial() {
        return super.buildPartial();
    }

    @Override // p.h01
    public final /* bridge */ /* synthetic */ Object clone() {
        return super.clone();
    }

    public final void d(Bootstrap$TrialsFacadeRequestV1 bootstrap$TrialsFacadeRequestV1) {
        copyOnWrite();
        Bootstrap$BootstrapRequest.f((Bootstrap$BootstrapRequest) this.instance, bootstrap$TrialsFacadeRequestV1);
    }

    public final void e(com.spotify.ucs.v0.proto.b bVar) {
        copyOnWrite();
        Bootstrap$BootstrapRequest.e((Bootstrap$BootstrapRequest) this.instance, (Ucs$UcsRequest) bVar.build());
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
