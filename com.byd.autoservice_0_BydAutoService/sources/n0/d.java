package n0;

import android.os.Build;
import android.widget.EdgeEffect;

/* JADX INFO: compiled from: EdgeEffectCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class d {
    public static void a(EdgeEffect edgeEffect, float f3, float f4) {
        if (Build.VERSION.SDK_INT >= 21) {
            edgeEffect.onPull(f3, f4);
        } else {
            edgeEffect.onPull(f3);
        }
    }
}
