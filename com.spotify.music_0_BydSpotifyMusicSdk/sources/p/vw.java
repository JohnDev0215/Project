package p;

import com.spotify.messages.ConfigurationAppliedNonAuth;
import java.util.Set;
import java.util.TreeMap;

/* JADX INFO: loaded from: classes.dex */
public final class vw extends h01 implements yj1 {
    @Override // p.h01, p.vj1
    public final /* bridge */ /* synthetic */ wj1 buildPartial() {
        return super.buildPartial();
    }

    @Override // p.h01
    public final /* bridge */ /* synthetic */ Object clone() {
        return super.clone();
    }

    public final void d(Set set) {
        copyOnWrite();
        ConfigurationAppliedNonAuth.k((ConfigurationAppliedNonAuth) this.instance, set);
    }

    public final void e(TreeMap treeMap) {
        copyOnWrite();
        ConfigurationAppliedNonAuth.j((ConfigurationAppliedNonAuth) this.instance).putAll(treeMap);
    }

    public final void f(String str) {
        copyOnWrite();
        ConfigurationAppliedNonAuth.f((ConfigurationAppliedNonAuth) this.instance, str);
    }

    public final void g(long j) {
        copyOnWrite();
        ConfigurationAppliedNonAuth.e((ConfigurationAppliedNonAuth) this.instance, j);
    }

    @Override // p.h01, p.yj1
    public final /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
        return super.getDefaultInstanceForType();
    }

    public final void h() {
        copyOnWrite();
        ConfigurationAppliedNonAuth.i((ConfigurationAppliedNonAuth) this.instance);
    }

    @Override // p.h01, p.r1
    public final /* bridge */ /* synthetic */ r1 internalMergeFrom(com.google.protobuf.a aVar) {
        return super.internalMergeFrom((com.google.protobuf.i) aVar);
    }

    public final void j() {
        copyOnWrite();
        ConfigurationAppliedNonAuth.g((ConfigurationAppliedNonAuth) this.instance);
    }

    public final void k() {
        copyOnWrite();
        ConfigurationAppliedNonAuth.h((ConfigurationAppliedNonAuth) this.instance);
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
