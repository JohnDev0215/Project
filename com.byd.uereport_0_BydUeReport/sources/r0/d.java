package r0;

import android.os.Build;
import android.widget.EdgeEffect;

/* JADX INFO: compiled from: EdgeEffectCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class d {
    public static void a(EdgeEffect edgeEffect, float f8, float f9) {
        if (Build.VERSION.SDK_INT >= 21) {
            edgeEffect.onPull(f8, f9);
        } else {
            edgeEffect.onPull(f8);
        }
    }
}
