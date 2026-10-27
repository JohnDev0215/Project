package c.b.a.a.a0;

import android.content.Context;
import android.graphics.Color;
import c.b.a.a.f0.b;

/* JADX INFO: compiled from: ElevationOverlayProvider.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f3693a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f3694b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f3695c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final float f3696d;

    public a(Context context) {
        this.f3693a = b.b(context, c.b.a.a.b.elevationOverlayEnabled, false);
        this.f3694b = c.b.a.a.w.a.a(context, c.b.a.a.b.elevationOverlayColor, 0);
        this.f3695c = c.b.a.a.w.a.a(context, c.b.a.a.b.colorSurface, 0);
        this.f3696d = context.getResources().getDisplayMetrics().density;
    }

    public float a(float f2) {
        if (this.f3696d <= 0.0f || f2 <= 0.0f) {
            return 0.0f;
        }
        return Math.min(((((float) Math.log1p(f2 / r0)) * 4.5f) + 2.0f) / 100.0f, 1.0f);
    }

    public int b(int i2, float f2) {
        float fA = a(f2);
        return b.h.f.a.d(c.b.a.a.w.a.f(b.h.f.a.d(i2, 255), this.f3694b, fA), Color.alpha(i2));
    }

    public int c(int i2, float f2) {
        return (this.f3693a && e(i2)) ? b(i2, f2) : i2;
    }

    public boolean d() {
        return this.f3693a;
    }

    public final boolean e(int i2) {
        return b.h.f.a.d(i2, 255) == this.f3695c;
    }
}
