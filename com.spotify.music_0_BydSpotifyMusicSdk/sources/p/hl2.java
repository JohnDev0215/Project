package p;

import com.spotify.messages.TimeMeasurementNonAuth;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class hl2 extends h01 implements yj1 {
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
        TimeMeasurementNonAuth.h((TimeMeasurementNonAuth) this.instance).putAll(map);
    }

    public final void e(Map map) {
        copyOnWrite();
        TimeMeasurementNonAuth.g((TimeMeasurementNonAuth) this.instance).putAll(map);
    }

    public final void f(Map map) {
        copyOnWrite();
        TimeMeasurementNonAuth.j((TimeMeasurementNonAuth) this.instance).putAll(map);
    }

    public final void g(Map map) {
        copyOnWrite();
        TimeMeasurementNonAuth.k((TimeMeasurementNonAuth) this.instance).putAll(map);
    }

    @Override // p.h01, p.yj1
    public final /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
        return super.getDefaultInstanceForType();
    }

    public final void h(Map map) {
        copyOnWrite();
        TimeMeasurementNonAuth.i((TimeMeasurementNonAuth) this.instance).putAll(map);
    }

    @Override // p.h01, p.r1
    public final /* bridge */ /* synthetic */ r1 internalMergeFrom(com.google.protobuf.a aVar) {
        return super.internalMergeFrom((com.google.protobuf.i) aVar);
    }

    public final void j(String str) {
        copyOnWrite();
        TimeMeasurementNonAuth.e((TimeMeasurementNonAuth) this.instance, str);
    }

    public final void k(String str) {
        copyOnWrite();
        TimeMeasurementNonAuth.l((TimeMeasurementNonAuth) this.instance, str);
    }

    public final void l(String str) {
        copyOnWrite();
        TimeMeasurementNonAuth.f((TimeMeasurementNonAuth) this.instance, str);
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
