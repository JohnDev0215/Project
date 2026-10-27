package a4;

import android.graphics.Color;
import android.graphics.Matrix;

/* JADX INFO: renamed from: a4.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0920a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public float f11090a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public float f11091b;
    public float c;
    public int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public float[] f11092e = null;

    public C0920a(C0920a c0920a) {
        this.f11090a = 0.0f;
        this.f11091b = 0.0f;
        this.c = 0.0f;
        this.d = 0;
        this.f11090a = c0920a.f11090a;
        this.f11091b = c0920a.f11091b;
        this.c = c0920a.c;
        this.d = c0920a.d;
    }

    public final void a(int i7, P3.a aVar) {
        int iAlpha = Color.alpha(this.d);
        int iC = AbstractC0925f.c(i7);
        Matrix matrix = AbstractC0927h.f11132a;
        int i10 = (int) ((((iAlpha / 255.0f) * iC) / 255.0f) * 255.0f);
        if (i10 <= 0) {
            aVar.clearShadowLayer();
        } else {
            aVar.setShadowLayer(Math.max(this.f11090a, Float.MIN_VALUE), this.f11091b, this.c, Color.argb(i10, Color.red(this.d), Color.green(this.d), Color.blue(this.d)));
        }
    }

    public final void b(int i7) {
        this.d = Color.argb(Math.round((AbstractC0925f.c(i7) * Color.alpha(this.d)) / 255.0f), Color.red(this.d), Color.green(this.d), Color.blue(this.d));
    }

    public final void c(Matrix matrix) {
        if (this.f11092e == null) {
            this.f11092e = new float[2];
        }
        float[] fArr = this.f11092e;
        fArr[0] = this.f11091b;
        fArr[1] = this.c;
        matrix.mapVectors(fArr);
        float[] fArr2 = this.f11092e;
        this.f11091b = fArr2[0];
        this.c = fArr2[1];
        this.f11090a = matrix.mapRadius(this.f11090a);
    }
}
