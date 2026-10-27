package x2;

import android.content.Context;
import android.graphics.Color;
import b3.b;

/* JADX INFO: compiled from: ElevationOverlayProvider.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final boolean f9823a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final int f9824b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final int f9825c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final float f9826d;

    public a(Context context) {
        this.f9823a = b.b(context, o2.b.elevationOverlayEnabled, false);
        this.f9824b = u2.a.a(context, o2.b.elevationOverlayColor, 0);
        this.f9825c = u2.a.a(context, o2.b.colorSurface, 0);
        this.f9826d = context.getResources().getDisplayMetrics().density;
    }

    private boolean e(int i7) {
        return z.a.d(i7, 255) == this.f9825c;
    }

    public float a(float f7) {
        if (this.f9826d <= 0.0f || f7 <= 0.0f) {
            return 0.0f;
        }
        return Math.min(((((float) Math.log1p(f7 / r0)) * 4.5f) + 2.0f) / 100.0f, 1.0f);
    }

    public int b(int i7, float f7) {
        float fA = a(f7);
        return z.a.d(u2.a.f(z.a.d(i7, 255), this.f9824b, fA), Color.alpha(i7));
    }

    public int c(int i7, float f7) {
        return (this.f9823a && e(i7)) ? b(i7, f7) : i7;
    }

    public boolean d() {
        return this.f9823a;
    }
}
