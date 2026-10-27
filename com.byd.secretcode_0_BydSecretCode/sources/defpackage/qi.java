package defpackage;

import android.content.Context;
import android.graphics.Color;
import com.google.android.material.R$attr;

/* JADX INFO: compiled from: ElevationOverlayProvider.java */
/* JADX INFO: loaded from: classes.dex */
public class qi {
    public final boolean a;
    public final int b;
    public final int c;
    public final float d;

    public qi(Context context) {
        this.a = rj.b(context, R$attr.elevationOverlayEnabled, false);
        this.b = bi.a(context, R$attr.elevationOverlayColor, 0);
        this.c = bi.a(context, R$attr.colorSurface, 0);
        this.d = context.getResources().getDisplayMetrics().density;
    }

    public float a(float f) {
        if (this.d <= 0.0f || f <= 0.0f) {
            return 0.0f;
        }
        return Math.min(((((float) Math.log1p(f / r0)) * 4.5f) + 2.0f) / 100.0f, 1.0f);
    }

    public int b(int i, float f) {
        float fA = a(f);
        return n7.d(bi.f(n7.d(i, 255), this.b, fA), Color.alpha(i));
    }

    public int c(int i, float f) {
        return (this.a && e(i)) ? b(i, f) : i;
    }

    public boolean d() {
        return this.a;
    }

    public final boolean e(int i) {
        return n7.d(i, 255) == this.c;
    }
}
