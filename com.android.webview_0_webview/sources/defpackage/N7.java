package defpackage;

import android.graphics.Paint;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class N7 extends Q7 {
    public int[] d;
    public C0638d2 e;
    public float f;
    public C0638d2 g;
    public float h;
    public int i;
    public float j;
    public float k;
    public float l;
    public float m;
    public Paint.Cap n;
    public Paint.Join o;
    public float p;

    public N7() {
        this.f = 0.0f;
        this.h = 1.0f;
        this.i = 0;
        this.j = 1.0f;
        this.k = 0.0f;
        this.l = 1.0f;
        this.m = 0.0f;
        this.n = Paint.Cap.BUTT;
        this.o = Paint.Join.MITER;
        this.p = 4.0f;
    }

    @Override // defpackage.P7
    public boolean a() {
        return this.g.c() || this.e.c();
    }

    @Override // defpackage.P7
    public boolean b(int[] iArr) {
        return this.e.d(iArr) | this.g.d(iArr);
    }

    public float getFillAlpha() {
        return this.j;
    }

    public int getFillColor() {
        return this.g.c;
    }

    public float getStrokeAlpha() {
        return this.h;
    }

    public int getStrokeColor() {
        return this.e.c;
    }

    public float getStrokeWidth() {
        return this.f;
    }

    public float getTrimPathEnd() {
        return this.l;
    }

    public float getTrimPathOffset() {
        return this.m;
    }

    public float getTrimPathStart() {
        return this.k;
    }

    public void setFillAlpha(float f) {
        this.j = f;
    }

    public void setFillColor(int i) {
        this.g.c = i;
    }

    public void setStrokeAlpha(float f) {
        this.h = f;
    }

    public void setStrokeColor(int i) {
        this.e.c = i;
    }

    public void setStrokeWidth(float f) {
        this.f = f;
    }

    public void setTrimPathEnd(float f) {
        this.l = f;
    }

    public void setTrimPathOffset(float f) {
        this.m = f;
    }

    public void setTrimPathStart(float f) {
        this.k = f;
    }

    public N7(N7 n7) {
        super(n7);
        this.f = 0.0f;
        this.h = 1.0f;
        this.i = 0;
        this.j = 1.0f;
        this.k = 0.0f;
        this.l = 1.0f;
        this.m = 0.0f;
        this.n = Paint.Cap.BUTT;
        this.o = Paint.Join.MITER;
        this.p = 4.0f;
        this.d = n7.d;
        this.e = n7.e;
        this.f = n7.f;
        this.h = n7.h;
        this.g = n7.g;
        this.i = n7.i;
        this.j = n7.j;
        this.k = n7.k;
        this.l = n7.l;
        this.m = n7.m;
        this.n = n7.n;
        this.o = n7.o;
        this.p = n7.p;
    }
}
