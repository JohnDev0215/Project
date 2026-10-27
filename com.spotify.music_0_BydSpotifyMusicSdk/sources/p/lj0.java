package p;

import com.spotify.messages.EventSenderInternalError2NonAuth;

/* JADX INFO: loaded from: classes.dex */
public final class lj0 extends h01 implements yj1 {
    @Override // p.h01, p.vj1
    public final /* bridge */ /* synthetic */ wj1 buildPartial() {
        return super.buildPartial();
    }

    @Override // p.h01
    public final /* bridge */ /* synthetic */ Object clone() {
        return super.clone();
    }

    public final void d(int i) {
        copyOnWrite();
        EventSenderInternalError2NonAuth.g((EventSenderInternalError2NonAuth) this.instance, i);
    }

    public final void e(String str) {
        copyOnWrite();
        EventSenderInternalError2NonAuth.f((EventSenderInternalError2NonAuth) this.instance, str);
    }

    public final void f(int i) {
        copyOnWrite();
        EventSenderInternalError2NonAuth.e((EventSenderInternalError2NonAuth) this.instance, i);
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
