package defpackage;

/* JADX INFO: compiled from: VelocityMatrix.java */
/* JADX INFO: loaded from: classes.dex */
public final class fl3 {
    public float a;
    public float b;
    public float c;
    public float d;
    public float e;
    public float f;

    public final void a(float f, float f2, int i, int i2, float[] fArr) {
        float f3 = fArr[0];
        float f4 = fArr[1];
        float f5 = (f - 0.5f) * 2.0f;
        float f6 = (f2 - 0.5f) * 2.0f;
        float f7 = f3 + this.c;
        float f8 = f4 + this.d;
        float f9 = (this.a * f5) + f7;
        float f10 = (this.b * f6) + f8;
        float radians = (float) Math.toRadians(this.f);
        float radians2 = (float) Math.toRadians(this.e);
        double d = radians;
        double d2 = i2 * f6;
        float fSin = (((float) ((Math.sin(d) * ((double) ((-i) * f5))) - (Math.cos(d) * d2))) * radians2) + f9;
        float fCos = (radians2 * ((float) ((Math.cos(d) * ((double) (i * f5))) - (Math.sin(d) * d2)))) + f10;
        fArr[0] = fSin;
        fArr[1] = fCos;
    }
}
