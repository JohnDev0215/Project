package c.b.a.a.n;

import android.content.Context;
import android.graphics.Color;
import c.b.a.a.s.b;
import com.google.android.material.R$attr;

/* JADX INFO: compiled from: ElevationOverlayProvider.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f1737a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f1738b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f1739c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final float f1740d;

    public a(Context context) {
        this.f1737a = b.b(context, R$attr.elevationOverlayEnabled, false);
        this.f1738b = c.b.a.a.k.a.a(context, R$attr.elevationOverlayColor, 0);
        this.f1739c = c.b.a.a.k.a.a(context, R$attr.colorSurface, 0);
        this.f1740d = context.getResources().getDisplayMetrics().density;
    }

    public float a(float f) {
        if (this.f1740d <= 0.0f || f <= 0.0f) {
            return 0.0f;
        }
        return Math.min(((((float) Math.log1p(f / r0)) * 4.5f) + 2.0f) / 100.0f, 1.0f);
    }

    public int b(int i, float f) {
        float fA = a(f);
        return b.g.c.a.d(c.b.a.a.k.a.f(b.g.c.a.d(i, 255), this.f1738b, fA), Color.alpha(i));
    }

    public int c(int i, float f) {
        return (this.f1737a && e(i)) ? b(i, f) : i;
    }

    public boolean d() {
        return this.f1737a;
    }

    public final boolean e(int i) {
        return b.g.c.a.d(i, 255) == this.f1739c;
    }
}
