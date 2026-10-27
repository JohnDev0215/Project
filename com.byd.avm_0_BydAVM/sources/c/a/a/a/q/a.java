package c.a.a.a.q;

import android.content.Context;
import android.graphics.Color;
import c.a.a.a.t.b;

/* JADX INFO: compiled from: ElevationOverlayProvider.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final boolean f2031a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final int f2032b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final int f2033c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final float f2034d;

    public a(Context context) {
        this.f2031a = b.b(context, c.a.a.a.b.s, false);
        this.f2032b = c.a.a.a.o.a.a(context, c.a.a.a.b.r, 0);
        this.f2033c = c.a.a.a.o.a.a(context, c.a.a.a.b.p, 0);
        this.f2034d = context.getResources().getDisplayMetrics().density;
    }

    private boolean e(int i) {
        return b.g.d.a.d(i, 255) == this.f2033c;
    }

    public float a(float f) {
        if (this.f2034d <= 0.0f || f <= 0.0f) {
            return 0.0f;
        }
        return Math.min(((((float) Math.log1p(f / r2)) * 4.5f) + 2.0f) / 100.0f, 1.0f);
    }

    public int b(int i, float f) {
        float fA = a(f);
        return b.g.d.a.d(c.a.a.a.o.a.f(b.g.d.a.d(i, 255), this.f2032b, fA), Color.alpha(i));
    }

    public int c(int i, float f) {
        return (this.f2031a && e(i)) ? b(i, f) : i;
    }

    public boolean d() {
        return this.f2031a;
    }
}
