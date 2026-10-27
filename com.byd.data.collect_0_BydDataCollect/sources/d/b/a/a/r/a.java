package d.b.a.a.r;

import android.content.Context;
import android.graphics.Color;
import d.b.a.a.u.b;

/* JADX INFO: compiled from: ElevationOverlayProvider.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final boolean f4701a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final int f4702b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final int f4703c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final float f4704d;

    public a(Context context) {
        this.f4701a = b.b(context, d.b.a.a.b.elevationOverlayEnabled, false);
        this.f4702b = d.b.a.a.o.a.a(context, d.b.a.a.b.elevationOverlayColor, 0);
        this.f4703c = d.b.a.a.o.a.a(context, d.b.a.a.b.colorSurface, 0);
        this.f4704d = context.getResources().getDisplayMetrics().density;
    }

    private boolean e(int i2) {
        return b.g.d.a.d(i2, 255) == this.f4703c;
    }

    public float a(float f2) {
        if (this.f4704d <= 0.0f || f2 <= 0.0f) {
            return 0.0f;
        }
        return Math.min(((((float) Math.log1p(f2 / r0)) * 4.5f) + 2.0f) / 100.0f, 1.0f);
    }

    public int b(int i2, float f2) {
        float fA = a(f2);
        return b.g.d.a.d(d.b.a.a.o.a.f(b.g.d.a.d(i2, 255), this.f4702b, fA), Color.alpha(i2));
    }

    public int c(int i2, float f2) {
        return (this.f4701a && e(i2)) ? b(i2, f2) : i2;
    }

    public boolean d() {
        return this.f4701a;
    }
}
