package b.a.a.a.u;

import android.content.Context;
import android.graphics.Color;
import b.a.a.a.y.b;

/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final boolean f1770a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final int f1771b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final int f1772c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final float f1773d;

    public a(Context context) {
        this.f1770a = b.b(context, b.a.a.a.b.o, false);
        this.f1771b = b.a.a.a.s.a.a(context, b.a.a.a.b.n, 0);
        this.f1772c = b.a.a.a.s.a.a(context, b.a.a.a.b.l, 0);
        this.f1773d = context.getResources().getDisplayMetrics().density;
    }

    private boolean e(int i) {
        return a.g.d.a.d(i, 255) == this.f1772c;
    }

    public float a(float f) {
        if (this.f1773d <= 0.0f || f <= 0.0f) {
            return 0.0f;
        }
        return Math.min(((((float) Math.log1p(f / r0)) * 4.5f) + 2.0f) / 100.0f, 1.0f);
    }

    public int b(int i, float f) {
        float fA = a(f);
        return a.g.d.a.d(b.a.a.a.s.a.f(a.g.d.a.d(i, 255), this.f1771b, fA), Color.alpha(i));
    }

    public int c(int i, float f) {
        return (this.f1770a && e(i)) ? b(i, f) : i;
    }

    public boolean d() {
        return this.f1770a;
    }
}
