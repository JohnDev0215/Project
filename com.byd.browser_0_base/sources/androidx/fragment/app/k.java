package androidx.fragment.app;

import android.view.View;
import defpackage.gq2;

/* JADX INFO: compiled from: SpecialEffectsController.kt */
/* JADX INFO: loaded from: classes.dex */
public final class k {
    public static int a(View view) {
        if ((view.getAlpha() == 0.0f) && view.getVisibility() == 0) {
            return 4;
        }
        int visibility = view.getVisibility();
        if (visibility == 0) {
            return 2;
        }
        if (visibility == 4) {
            return 4;
        }
        if (visibility == 8) {
            return 3;
        }
        throw new IllegalArgumentException(gq2.e("Unknown visibility ", visibility));
    }
}
