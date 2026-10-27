package p;

import com.spotify.messages.AudioManagerProxyEvent;

/* JADX INFO: loaded from: classes.dex */
public final class hd extends h01 implements yj1 {
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
        AudioManagerProxyEvent.j((AudioManagerProxyEvent) this.instance, z);
    }

    public final void e(String str) {
        copyOnWrite();
        AudioManagerProxyEvent.i((AudioManagerProxyEvent) this.instance, str);
    }

    public final void f(String str) {
        copyOnWrite();
        AudioManagerProxyEvent.e((AudioManagerProxyEvent) this.instance, str);
    }

    public final void g(String str) {
        copyOnWrite();
        AudioManagerProxyEvent.h((AudioManagerProxyEvent) this.instance, str);
    }

    @Override // p.h01, p.yj1
    public final /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
        return super.getDefaultInstanceForType();
    }

    public final void h(String str) {
        copyOnWrite();
        AudioManagerProxyEvent.g((AudioManagerProxyEvent) this.instance, str);
    }

    @Override // p.h01, p.r1
    public final /* bridge */ /* synthetic */ r1 internalMergeFrom(com.google.protobuf.a aVar) {
        return super.internalMergeFrom((com.google.protobuf.i) aVar);
    }

    public final void j(String str) {
        copyOnWrite();
        AudioManagerProxyEvent.f((AudioManagerProxyEvent) this.instance, str);
    }

    public final void k(String str) {
        copyOnWrite();
        AudioManagerProxyEvent.l((AudioManagerProxyEvent) this.instance, str);
    }

    public final void l(String str) {
        copyOnWrite();
        AudioManagerProxyEvent.k((AudioManagerProxyEvent) this.instance, str);
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
