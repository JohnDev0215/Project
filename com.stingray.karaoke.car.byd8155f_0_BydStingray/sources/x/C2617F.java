package x;

/* JADX INFO: renamed from: x.F, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2617F implements InterfaceC2616E {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f23403a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final C2638h0 f23404b;

    public C2617F(float f10, float f11, float f12) {
        this.f23403a = f12;
        C2638h0 c2638h0 = new C2638h0();
        c2638h0.f23539a = 1.0f;
        double dSqrt = Math.sqrt(50.0d);
        c2638h0.f23540b = dSqrt;
        c2638h0.f23543g = 1.0f;
        if (f10 < 0.0f) {
            throw new IllegalArgumentException("Damping ratio must be non-negative");
        }
        c2638h0.f23543g = f10;
        c2638h0.c = false;
        if (((float) (dSqrt * dSqrt)) <= 0.0f) {
            throw new IllegalArgumentException("Spring stiffness constant must be positive.");
        }
        c2638h0.f23540b = Math.sqrt(f11);
        c2638h0.c = false;
        this.f23404b = c2638h0;
    }

    @Override // x.InterfaceC2616E
    public final float b(long j3, float f10, float f11, float f12) {
        C2638h0 c2638h0 = this.f23404b;
        c2638h0.f23539a = f11;
        return Float.intBitsToFloat((int) (c2638h0.a(f10, f12, j3 / 1000000) >> 32));
    }

    @Override // x.InterfaceC2616E
    public final float c(long j3, float f10, float f11, float f12) {
        C2638h0 c2638h0 = this.f23404b;
        c2638h0.f23539a = f11;
        return Float.intBitsToFloat((int) (c2638h0.a(f10, f12, j3 / 1000000) & 4294967295L));
    }

    /* JADX WARN: Removed duplicated region for block: B:47:0x0138  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0174  */
    @Override // x.InterfaceC2616E
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final long d(float r32, float r33, float r34) {
        /*
            Method dump skipped, instruction units count: 711
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: x.C2617F.d(float, float, float):long");
    }

    @Override // x.InterfaceC2616E
    public final float f(float f10, float f11, float f12) {
        return 0.0f;
    }
}
