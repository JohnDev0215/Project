package p;

import com.spotify.rcs.resolver.grpc.v0.proto.Resolve$Context;
import com.spotify.rcs.resolver.grpc.v0.proto.Resolve$Fetch;
import com.spotify.rcs.resolver.grpc.v0.proto.Resolve$ResolveRequest;

/* JADX INFO: loaded from: classes.dex */
public final class x32 extends h01 implements yj1 {
    @Override // p.h01, p.vj1
    public final /* bridge */ /* synthetic */ wj1 buildPartial() {
        return super.buildPartial();
    }

    @Override // p.h01
    public final /* bridge */ /* synthetic */ Object clone() {
        return super.clone();
    }

    public final void d(com.spotify.rcs.resolver.grpc.v0.proto.g gVar) {
        copyOnWrite();
        Resolve$ResolveRequest.g((Resolve$ResolveRequest) this.instance, (Resolve$Context) gVar.build());
    }

    public final void e(com.spotify.rcs.resolver.grpc.v0.proto.i iVar) {
        copyOnWrite();
        Resolve$ResolveRequest.f((Resolve$ResolveRequest) this.instance, (Resolve$Fetch) iVar.build());
    }

    public final void f(String str) {
        copyOnWrite();
        Resolve$ResolveRequest.e((Resolve$ResolveRequest) this.instance, str);
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
