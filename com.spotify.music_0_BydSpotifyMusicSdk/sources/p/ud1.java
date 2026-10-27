package p;

import com.spotify.libs.connect.events.proto.LogoutNonAuth;

/* JADX INFO: loaded from: classes.dex */
public final class ud1 extends h01 implements yj1 {
    @Override // p.h01, p.vj1
    public final /* bridge */ /* synthetic */ wj1 buildPartial() {
        return super.buildPartial();
    }

    @Override // p.h01
    public final /* bridge */ /* synthetic */ Object clone() {
        return super.clone();
    }

    public final void d(String str) {
        copyOnWrite();
        LogoutNonAuth.f((LogoutNonAuth) this.instance, str);
    }

    public final void e(boolean z) {
        copyOnWrite();
        LogoutNonAuth.h((LogoutNonAuth) this.instance, z);
    }

    public final void f(String str) {
        copyOnWrite();
        LogoutNonAuth.e((LogoutNonAuth) this.instance, str);
    }

    public final void g(boolean z) {
        copyOnWrite();
        LogoutNonAuth.g((LogoutNonAuth) this.instance, z);
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
