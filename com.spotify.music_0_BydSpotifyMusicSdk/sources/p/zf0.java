package p;

import com.spotify.player.esperanto.proto.EsGetStateRequest$GetStateRequest;
import com.spotify.player.esperanto.proto.EsOptional$OptionalInt64;

/* JADX INFO: loaded from: classes.dex */
public final class zf0 extends h01 implements yj1 {
    @Override // p.h01, p.vj1
    public final /* bridge */ /* synthetic */ wj1 buildPartial() {
        return super.buildPartial();
    }

    @Override // p.h01
    public final /* bridge */ /* synthetic */ Object clone() {
        return super.clone();
    }

    public final void d(cg0 cg0Var) {
        copyOnWrite();
        EsGetStateRequest$GetStateRequest.f((EsGetStateRequest$GetStateRequest) this.instance, (EsOptional$OptionalInt64) cg0Var.build());
    }

    public final void e(cg0 cg0Var) {
        copyOnWrite();
        EsGetStateRequest$GetStateRequest.e((EsGetStateRequest$GetStateRequest) this.instance, (EsOptional$OptionalInt64) cg0Var.build());
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
