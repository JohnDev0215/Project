package p;

import com.spotify.messages.BootstrapRequestNonAuth;
import java.util.TreeMap;

/* JADX INFO: loaded from: classes.dex */
public final class gn extends h01 implements yj1 {
    @Override // p.h01, p.vj1
    public final /* bridge */ /* synthetic */ wj1 buildPartial() {
        return super.buildPartial();
    }

    @Override // p.h01
    public final /* bridge */ /* synthetic */ Object clone() {
        return super.clone();
    }

    public final void d(TreeMap treeMap) {
        copyOnWrite();
        BootstrapRequestNonAuth.i((BootstrapRequestNonAuth) this.instance).putAll(treeMap);
    }

    public final void e(String str) {
        copyOnWrite();
        BootstrapRequestNonAuth.f((BootstrapRequestNonAuth) this.instance, str);
    }

    public final void f(String str) {
        copyOnWrite();
        BootstrapRequestNonAuth.l((BootstrapRequestNonAuth) this.instance, str);
    }

    public final void g(String str) {
        copyOnWrite();
        BootstrapRequestNonAuth.h((BootstrapRequestNonAuth) this.instance, str);
    }

    @Override // p.h01, p.yj1
    public final /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
        return super.getDefaultInstanceForType();
    }

    public final void h(long j) {
        copyOnWrite();
        BootstrapRequestNonAuth.e((BootstrapRequestNonAuth) this.instance, j);
    }

    @Override // p.h01, p.r1
    public final /* bridge */ /* synthetic */ r1 internalMergeFrom(com.google.protobuf.a aVar) {
        return super.internalMergeFrom((com.google.protobuf.i) aVar);
    }

    public final void j(long j) {
        copyOnWrite();
        BootstrapRequestNonAuth.j((BootstrapRequestNonAuth) this.instance, j);
    }

    public final void k(String str) {
        copyOnWrite();
        BootstrapRequestNonAuth.g((BootstrapRequestNonAuth) this.instance, str);
    }

    public final void l(int i) {
        copyOnWrite();
        BootstrapRequestNonAuth.k((BootstrapRequestNonAuth) this.instance, i);
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
