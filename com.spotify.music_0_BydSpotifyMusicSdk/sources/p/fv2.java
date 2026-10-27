package p;

import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public abstract class fv2 extends u11 {
    public static boolean w = true;

    public float L(View view) {
        if (w) {
            try {
                return view.getTransitionAlpha();
            } catch (NoSuchMethodError unused) {
                w = false;
            }
        }
        return view.getAlpha();
    }

    public void M(View view, float f) {
        if (w) {
            try {
                view.setTransitionAlpha(f);
                return;
            } catch (NoSuchMethodError unused) {
                w = false;
            }
        }
        view.setAlpha(f);
    }
}
