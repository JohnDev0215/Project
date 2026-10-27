package f2;

import android.content.Context;
import android.graphics.Color;
import com.google.android.material.R$attr;
import k2.b;

/* JADX INFO: compiled from: ElevationOverlayProvider.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f2913a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f2914b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f2915c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final float f2916d;

    public a(Context context) {
        this.f2913a = b.b(context, R$attr.elevationOverlayEnabled, false);
        this.f2914b = c2.a.a(context, R$attr.elevationOverlayColor, 0);
        this.f2915c = c2.a.a(context, R$attr.colorSurface, 0);
        this.f2916d = context.getResources().getDisplayMetrics().density;
    }

    public float a(float f3) {
        if (this.f2916d <= 0.0f || f3 <= 0.0f) {
            return 0.0f;
        }
        return Math.min(((((float) Math.log1p(f3 / r0)) * 4.5f) + 2.0f) / 100.0f, 1.0f);
    }

    public int b(int i3, float f3) {
        float fA = a(f3);
        return d0.a.d(c2.a.f(d0.a.d(i3, 255), this.f2914b, fA), Color.alpha(i3));
    }

    public int c(int i3, float f3) {
        return (this.f2913a && e(i3)) ? b(i3, f3) : i3;
    }

    public boolean d() {
        return this.f2913a;
    }

    public final boolean e(int i3) {
        return d0.a.d(i3, 255) == this.f2915c;
    }
}
