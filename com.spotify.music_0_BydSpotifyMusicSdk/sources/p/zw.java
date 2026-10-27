package p;

import com.spotify.messages.ConfigurationFetched;
import java.util.TreeMap;

/* JADX INFO: loaded from: classes.dex */
public final class zw extends h01 implements yj1 {
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
        ConfigurationFetched.x((ConfigurationFetched) this.instance).putAll(treeMap);
    }

    public final void e(String str) {
        copyOnWrite();
        ConfigurationFetched.w((ConfigurationFetched) this.instance, str);
    }

    public final void f(int i) {
        copyOnWrite();
        ConfigurationFetched.t((ConfigurationFetched) this.instance, i);
    }

    public final void g(int i) {
        copyOnWrite();
        ConfigurationFetched.u((ConfigurationFetched) this.instance, i);
    }

    @Override // p.h01, p.yj1
    public final /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
        return super.getDefaultInstanceForType();
    }

    public final void h(String str) {
        copyOnWrite();
        ConfigurationFetched.o((ConfigurationFetched) this.instance, str);
    }

    @Override // p.h01, p.r1
    public final /* bridge */ /* synthetic */ r1 internalMergeFrom(com.google.protobuf.a aVar) {
        return super.internalMergeFrom((com.google.protobuf.i) aVar);
    }

    public final void j(String str) {
        copyOnWrite();
        ConfigurationFetched.s((ConfigurationFetched) this.instance, str);
    }

    public final void k(String str) {
        copyOnWrite();
        ConfigurationFetched.q((ConfigurationFetched) this.instance, str);
    }

    public final void l(String str) {
        copyOnWrite();
        ConfigurationFetched.n((ConfigurationFetched) this.instance, str);
    }

    public final void m(String str) {
        copyOnWrite();
        ConfigurationFetched.r((ConfigurationFetched) this.instance, str);
    }

    @Override // p.h01, p.r1
    public final /* bridge */ /* synthetic */ r1 mergeFrom(au auVar, rl0 rl0Var) {
        return super.mergeFrom(auVar, rl0Var);
    }

    public final void n(String str) {
        copyOnWrite();
        ConfigurationFetched.p((ConfigurationFetched) this.instance, str);
    }

    public final void o(String str) {
        copyOnWrite();
        ConfigurationFetched.j((ConfigurationFetched) this.instance, str);
    }

    public final void p(long j) {
        copyOnWrite();
        ConfigurationFetched.e((ConfigurationFetched) this.instance, j);
    }

    public final void r(long j) {
        copyOnWrite();
        ConfigurationFetched.k((ConfigurationFetched) this.instance, j);
    }

    public final void s(long j) {
        copyOnWrite();
        ConfigurationFetched.l((ConfigurationFetched) this.instance, j);
    }

    public final void t() {
        copyOnWrite();
        ConfigurationFetched.i((ConfigurationFetched) this.instance);
    }

    public final void u(int i) {
        copyOnWrite();
        ConfigurationFetched.v((ConfigurationFetched) this.instance, i);
    }

    public final void v() {
        copyOnWrite();
        ConfigurationFetched.y((ConfigurationFetched) this.instance);
    }

    public final void w() {
        copyOnWrite();
        ConfigurationFetched.f((ConfigurationFetched) this.instance);
    }

    public final void x() {
        copyOnWrite();
        ConfigurationFetched.g((ConfigurationFetched) this.instance);
    }

    public final void y(String str) {
        copyOnWrite();
        ConfigurationFetched.h((ConfigurationFetched) this.instance, str);
    }

    public final void z(int i) {
        copyOnWrite();
        ConfigurationFetched.m((ConfigurationFetched) this.instance, i);
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
