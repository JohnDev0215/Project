package d.b.a.a.q;

import android.content.Context;
import android.graphics.Color;
import d.b.a.a.t.b;

/* JADX INFO: compiled from: ElevationOverlayProvider.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final boolean f4783a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final int f4784b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final int f4785c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final float f4786d;

    public a(Context context) {
        this.f4783a = b.b(context, d.b.a.a.b.s, false);
        this.f4784b = d.b.a.a.o.a.a(context, d.b.a.a.b.r, 0);
        this.f4785c = d.b.a.a.o.a.a(context, d.b.a.a.b.p, 0);
        this.f4786d = context.getResources().getDisplayMetrics().density;
    }

    private boolean e(int i) {
        return b.g.d.a.d(i, 255) == this.f4785c;
    }

    public float a(float f2) {
        if (this.f4786d <= 0.0f || f2 <= 0.0f) {
            return 0.0f;
        }
        return Math.min(((((float) Math.log1p(f2 / r0)) * 4.5f) + 2.0f) / 100.0f, 1.0f);
    }

    public int b(int i, float f2) {
        float fA = a(f2);
        return b.g.d.a.d(d.b.a.a.o.a.f(b.g.d.a.d(i, 255), this.f4784b, fA), Color.alpha(i));
    }

    public int c(int i, float f2) {
        return (this.f4783a && e(i)) ? b(i, f2) : i;
    }

    public boolean d() {
        return this.f4783a;
    }
}
