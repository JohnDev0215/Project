package x;

/* JADX INFO: renamed from: x.E, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public interface InterfaceC2616E extends InterfaceC2647m {
    @Override // x.InterfaceC2647m
    default F0 a(D0 d02) {
        return new B8.t(this);
    }

    float b(long j3, float f10, float f11, float f12);

    float c(long j3, float f10, float f11, float f12);

    long d(float f10, float f11, float f12);

    default float f(float f10, float f11, float f12) {
        return c(d(f10, f11, f12), f10, f11, f12);
    }
}
