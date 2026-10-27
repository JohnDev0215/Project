package d.b.a.a.q;

import android.content.Context;
import android.graphics.Color;
import d.b.a.a.t.b;

/* JADX INFO: compiled from: ElevationOverlayProvider.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final boolean f3965a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final int f3966b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final int f3967c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final float f3968d;

    public a(Context context) {
        this.f3965a = b.b(context, d.b.a.a.b.s, false);
        this.f3966b = d.b.a.a.o.a.a(context, d.b.a.a.b.r, 0);
        this.f3967c = d.b.a.a.o.a.a(context, d.b.a.a.b.p, 0);
        this.f3968d = context.getResources().getDisplayMetrics().density;
    }

    private boolean e(int i) {
        return c.g.d.a.d(i, 255) == this.f3967c;
    }

    public float a(float f) {
        if (this.f3968d <= 0.0f || f <= 0.0f) {
            return 0.0f;
        }
        return Math.min(((((float) Math.log1p(f / r0)) * 4.5f) + 2.0f) / 100.0f, 1.0f);
    }

    public int b(int i, float f) {
        float fA = a(f);
        return c.g.d.a.d(d.b.a.a.o.a.f(c.g.d.a.d(i, 255), this.f3966b, fA), Color.alpha(i));
    }

    public int c(int i, float f) {
        return (this.f3965a && e(i)) ? b(i, f) : i;
    }

    public boolean d() {
        return this.f3965a;
    }
}
