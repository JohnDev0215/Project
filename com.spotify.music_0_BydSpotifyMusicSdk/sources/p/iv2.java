package p;

import android.os.Build;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public class iv2 extends hv2 {
    public static boolean A = true;

    @Override // p.u11
    public void K(View view, int i) {
        if (Build.VERSION.SDK_INT == 28) {
            super.K(view, i);
        } else if (A) {
            try {
                view.setTransitionVisibility(i);
            } catch (NoSuchMethodError unused) {
                A = false;
            }
        }
    }
}
