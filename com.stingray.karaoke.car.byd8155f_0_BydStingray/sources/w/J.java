package w;

/* JADX INFO: loaded from: classes.dex */
public final class J {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f23074a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f23075b;

    public J(float f10, a1.b bVar) {
        this.f23074a = f10;
        float density = bVar.getDensity();
        float f11 = K.f23076a;
        this.f23075b = density * 386.0878f * 160.0f * 0.84f;
    }

    public final I a(float f10) {
        double dB = b(f10);
        double d = K.f23076a;
        double d10 = d - 1.0d;
        return new I(f10, (float) (Math.exp((d / d10) * dB) * ((double) (this.f23074a * this.f23075b))), (long) (Math.exp(dB / d10) * 1000.0d));
    }

    public final double b(float f10) {
        float[] fArr = AbstractC2571b.f23088a;
        return Math.log(((double) (Math.abs(f10) * 0.35f)) / ((double) (this.f23074a * this.f23075b)));
    }
}
