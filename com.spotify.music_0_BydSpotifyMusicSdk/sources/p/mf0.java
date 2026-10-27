package p;

import com.google.protobuf.ByteString;
import com.spotify.player.esperanto.proto.EsCommandOptions$CommandOptions;

/* JADX INFO: loaded from: classes.dex */
public final class mf0 extends h01 implements yj1 {
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
        EsCommandOptions$CommandOptions.f((EsCommandOptions$CommandOptions) this.instance, z);
    }

    public final void e(ByteString byteString) {
        copyOnWrite();
        EsCommandOptions$CommandOptions.h((EsCommandOptions$CommandOptions) this.instance, byteString);
    }

    public final void f(boolean z) {
        copyOnWrite();
        EsCommandOptions$CommandOptions.e((EsCommandOptions$CommandOptions) this.instance, z);
    }

    public final void g(boolean z) {
        copyOnWrite();
        EsCommandOptions$CommandOptions.g((EsCommandOptions$CommandOptions) this.instance, z);
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
