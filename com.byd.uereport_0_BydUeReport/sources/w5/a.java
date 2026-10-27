package w5;

import android.content.Context;
import android.graphics.Color;
import b6.b;
import com.google.android.material.R$attr;

/* JADX INFO: compiled from: ElevationOverlayProvider.java */
/* JADX INFO: loaded from: classes2.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f8750a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f8751b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f8752c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final float f8753d;

    public a(Context context) {
        this.f8750a = b.b(context, R$attr.elevationOverlayEnabled, false);
        this.f8751b = t5.a.a(context, R$attr.elevationOverlayColor, 0);
        this.f8752c = t5.a.a(context, R$attr.colorSurface, 0);
        this.f8753d = context.getResources().getDisplayMetrics().density;
    }

    public float a(float f8) {
        if (this.f8753d <= 0.0f || f8 <= 0.0f) {
            return 0.0f;
        }
        return Math.min(((((float) Math.log1p(f8 / r0)) * 4.5f) + 2.0f) / 100.0f, 1.0f);
    }

    public int b(int i8, float f8) {
        float fA = a(f8);
        return g0.a.d(t5.a.f(g0.a.d(i8, 255), this.f8751b, fA), Color.alpha(i8));
    }

    public int c(int i8, float f8) {
        return (this.f8750a && e(i8)) ? b(i8, f8) : i8;
    }

    public boolean d() {
        return this.f8750a;
    }

    public final boolean e(int i8) {
        return g0.a.d(i8, 255) == this.f8752c;
    }
}
