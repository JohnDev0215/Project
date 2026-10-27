package p;

import com.spotify.messages.TimeMeasurementErrorNonAuth;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class yk2 extends h01 implements yj1 {
    @Override // p.h01, p.vj1
    public final /* bridge */ /* synthetic */ wj1 buildPartial() {
        return super.buildPartial();
    }

    @Override // p.h01
    public final /* bridge */ /* synthetic */ Object clone() {
        return super.clone();
    }

    public final void d(Map map) {
        copyOnWrite();
        TimeMeasurementErrorNonAuth.r((TimeMeasurementErrorNonAuth) this.instance).putAll(map);
    }

    public final void e(Map map) {
        copyOnWrite();
        TimeMeasurementErrorNonAuth.n((TimeMeasurementErrorNonAuth) this.instance).putAll(map);
    }

    public final void f(Map map) {
        copyOnWrite();
        TimeMeasurementErrorNonAuth.m((TimeMeasurementErrorNonAuth) this.instance).putAll(map);
    }

    public final void g(Map map) {
        copyOnWrite();
        TimeMeasurementErrorNonAuth.p((TimeMeasurementErrorNonAuth) this.instance).putAll(map);
    }

    @Override // p.h01, p.yj1
    public final /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
        return super.getDefaultInstanceForType();
    }

    public final void h(Map map) {
        copyOnWrite();
        TimeMeasurementErrorNonAuth.o((TimeMeasurementErrorNonAuth) this.instance).putAll(map);
    }

    @Override // p.h01, p.r1
    public final /* bridge */ /* synthetic */ r1 internalMergeFrom(com.google.protobuf.a aVar) {
        return super.internalMergeFrom((com.google.protobuf.i) aVar);
    }

    public final void j(Map map) {
        copyOnWrite();
        TimeMeasurementErrorNonAuth.g((TimeMeasurementErrorNonAuth) this.instance).putAll(map);
    }

    public final void k(Map map) {
        copyOnWrite();
        TimeMeasurementErrorNonAuth.h((TimeMeasurementErrorNonAuth) this.instance).putAll(map);
    }

    public final void l(Map map) {
        copyOnWrite();
        TimeMeasurementErrorNonAuth.f((TimeMeasurementErrorNonAuth) this.instance).putAll(map);
    }

    public final void m(String str) {
        copyOnWrite();
        TimeMeasurementErrorNonAuth.q((TimeMeasurementErrorNonAuth) this.instance, str);
    }

    @Override // p.h01, p.r1
    public final /* bridge */ /* synthetic */ r1 mergeFrom(au auVar, rl0 rl0Var) {
        return super.mergeFrom(auVar, rl0Var);
    }

    public final void n(int i) {
        copyOnWrite();
        TimeMeasurementErrorNonAuth.e((TimeMeasurementErrorNonAuth) this.instance, i);
    }

    public final void o(String str) {
        copyOnWrite();
        TimeMeasurementErrorNonAuth.i((TimeMeasurementErrorNonAuth) this.instance, str);
    }

    public final void p(String str) {
        copyOnWrite();
        TimeMeasurementErrorNonAuth.j((TimeMeasurementErrorNonAuth) this.instance, str);
    }

    public final void r(long j) {
        copyOnWrite();
        TimeMeasurementErrorNonAuth.l((TimeMeasurementErrorNonAuth) this.instance, j);
    }

    public final void s(String str) {
        copyOnWrite();
        TimeMeasurementErrorNonAuth.k((TimeMeasurementErrorNonAuth) this.instance, str);
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
