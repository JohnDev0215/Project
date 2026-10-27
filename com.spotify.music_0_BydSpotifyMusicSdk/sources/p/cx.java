package p;

import com.spotify.messages.ConfigurationFetchedNonAuth;
import java.util.TreeMap;

/* JADX INFO: loaded from: classes.dex */
public final class cx extends h01 implements yj1 {
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
        ConfigurationFetchedNonAuth.v((ConfigurationFetchedNonAuth) this.instance).putAll(treeMap);
    }

    public final void e(String str) {
        copyOnWrite();
        ConfigurationFetchedNonAuth.w((ConfigurationFetchedNonAuth) this.instance, str);
    }

    public final void f(int i) {
        copyOnWrite();
        ConfigurationFetchedNonAuth.t((ConfigurationFetchedNonAuth) this.instance, i);
    }

    public final void g(int i) {
        copyOnWrite();
        ConfigurationFetchedNonAuth.u((ConfigurationFetchedNonAuth) this.instance, i);
    }

    @Override // p.h01, p.yj1
    public final /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
        return super.getDefaultInstanceForType();
    }

    public final void h(String str) {
        copyOnWrite();
        ConfigurationFetchedNonAuth.o((ConfigurationFetchedNonAuth) this.instance, str);
    }

    @Override // p.h01, p.r1
    public final /* bridge */ /* synthetic */ r1 internalMergeFrom(com.google.protobuf.a aVar) {
        return super.internalMergeFrom((com.google.protobuf.i) aVar);
    }

    public final void j(String str) {
        copyOnWrite();
        ConfigurationFetchedNonAuth.s((ConfigurationFetchedNonAuth) this.instance, str);
    }

    public final void k(String str) {
        copyOnWrite();
        ConfigurationFetchedNonAuth.q((ConfigurationFetchedNonAuth) this.instance, str);
    }

    public final void l(String str) {
        copyOnWrite();
        ConfigurationFetchedNonAuth.n((ConfigurationFetchedNonAuth) this.instance, str);
    }

    public final void m(String str) {
        copyOnWrite();
        ConfigurationFetchedNonAuth.r((ConfigurationFetchedNonAuth) this.instance, str);
    }

    @Override // p.h01, p.r1
    public final /* bridge */ /* synthetic */ r1 mergeFrom(au auVar, rl0 rl0Var) {
        return super.mergeFrom(auVar, rl0Var);
    }

    public final void n(String str) {
        copyOnWrite();
        ConfigurationFetchedNonAuth.p((ConfigurationFetchedNonAuth) this.instance, str);
    }

    public final void o(String str) {
        copyOnWrite();
        ConfigurationFetchedNonAuth.j((ConfigurationFetchedNonAuth) this.instance, str);
    }

    public final void p(long j) {
        copyOnWrite();
        ConfigurationFetchedNonAuth.e((ConfigurationFetchedNonAuth) this.instance, j);
    }

    public final void r(long j) {
        copyOnWrite();
        ConfigurationFetchedNonAuth.k((ConfigurationFetchedNonAuth) this.instance, j);
    }

    public final void s(long j) {
        copyOnWrite();
        ConfigurationFetchedNonAuth.l((ConfigurationFetchedNonAuth) this.instance, j);
    }

    public final void t() {
        copyOnWrite();
        ConfigurationFetchedNonAuth.i((ConfigurationFetchedNonAuth) this.instance);
    }

    public final void u() {
        copyOnWrite();
        ConfigurationFetchedNonAuth.x((ConfigurationFetchedNonAuth) this.instance);
    }

    public final void v() {
        copyOnWrite();
        ConfigurationFetchedNonAuth.f((ConfigurationFetchedNonAuth) this.instance);
    }

    public final void w() {
        copyOnWrite();
        ConfigurationFetchedNonAuth.g((ConfigurationFetchedNonAuth) this.instance);
    }

    public final void x(String str) {
        copyOnWrite();
        ConfigurationFetchedNonAuth.h((ConfigurationFetchedNonAuth) this.instance, str);
    }

    public final void y(int i) {
        copyOnWrite();
        ConfigurationFetchedNonAuth.m((ConfigurationFetchedNonAuth) this.instance, i);
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
