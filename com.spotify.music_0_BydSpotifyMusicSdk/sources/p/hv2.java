package p;

import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public abstract class hv2 extends gv2 {
    public static boolean z = true;

    public void P(View view, int i, int i2, int i3, int i4) {
        if (z) {
            try {
                view.setLeftTopRightBottom(i, i2, i3, i4);
            } catch (NoSuchMethodError unused) {
                z = false;
            }
        }
    }
}
