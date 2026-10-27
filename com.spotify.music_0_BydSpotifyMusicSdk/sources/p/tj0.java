package p;

import com.google.protobuf.ByteString;
import com.spotify.messages.EventSenderStats2NonAuth;
import java.util.TreeSet;

/* JADX INFO: loaded from: classes.dex */
public final class tj0 extends h01 implements yj1 {
    @Override // p.h01, p.vj1
    public final /* bridge */ /* synthetic */ wj1 buildPartial() {
        return super.buildPartial();
    }

    @Override // p.h01
    public final /* bridge */ /* synthetic */ Object clone() {
        return super.clone();
    }

    public final void d(TreeSet treeSet) {
        copyOnWrite();
        EventSenderStats2NonAuth.q((EventSenderStats2NonAuth) this.instance, treeSet);
    }

    public final void e(int i) {
        copyOnWrite();
        EventSenderStats2NonAuth.f((EventSenderStats2NonAuth) this.instance, i);
    }

    public final void f(int i) {
        copyOnWrite();
        EventSenderStats2NonAuth.e((EventSenderStats2NonAuth) this.instance, i);
    }

    public final void g(long j) {
        copyOnWrite();
        EventSenderStats2NonAuth.i((EventSenderStats2NonAuth) this.instance, j);
    }

    @Override // p.h01, p.yj1
    public final /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
        return super.getDefaultInstanceForType();
    }

    public final void h(long j) {
        copyOnWrite();
        EventSenderStats2NonAuth.j((EventSenderStats2NonAuth) this.instance, j);
    }

    @Override // p.h01, p.r1
    public final /* bridge */ /* synthetic */ r1 internalMergeFrom(com.google.protobuf.a aVar) {
        return super.internalMergeFrom((com.google.protobuf.i) aVar);
    }

    public final void j(long j) {
        copyOnWrite();
        EventSenderStats2NonAuth.g((EventSenderStats2NonAuth) this.instance, j);
    }

    public final void k(long j) {
        copyOnWrite();
        EventSenderStats2NonAuth.l((EventSenderStats2NonAuth) this.instance, j);
    }

    public final void l(int i) {
        copyOnWrite();
        EventSenderStats2NonAuth.k((EventSenderStats2NonAuth) this.instance, i);
    }

    public final void m(ByteString byteString) {
        copyOnWrite();
        EventSenderStats2NonAuth.h((EventSenderStats2NonAuth) this.instance, byteString);
    }

    @Override // p.h01, p.r1
    public final /* bridge */ /* synthetic */ r1 mergeFrom(au auVar, rl0 rl0Var) {
        return super.mergeFrom(auVar, rl0Var);
    }

    public final void n(long j) {
        copyOnWrite();
        EventSenderStats2NonAuth.u((EventSenderStats2NonAuth) this.instance, j);
    }

    public final void o(long j) {
        copyOnWrite();
        EventSenderStats2NonAuth.t((EventSenderStats2NonAuth) this.instance, j);
    }

    public final void p(long j) {
        copyOnWrite();
        EventSenderStats2NonAuth.p((EventSenderStats2NonAuth) this.instance, j);
    }

    public final void r(long j) {
        copyOnWrite();
        EventSenderStats2NonAuth.n((EventSenderStats2NonAuth) this.instance, j);
    }

    public final void s(int i) {
        copyOnWrite();
        EventSenderStats2NonAuth.m((EventSenderStats2NonAuth) this.instance, i);
    }

    public final void t(long j) {
        copyOnWrite();
        EventSenderStats2NonAuth.r((EventSenderStats2NonAuth) this.instance, j);
    }

    public final void u(long j) {
        copyOnWrite();
        EventSenderStats2NonAuth.s((EventSenderStats2NonAuth) this.instance, j);
    }

    public final void v(long j) {
        copyOnWrite();
        EventSenderStats2NonAuth.o((EventSenderStats2NonAuth) this.instance, j);
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
