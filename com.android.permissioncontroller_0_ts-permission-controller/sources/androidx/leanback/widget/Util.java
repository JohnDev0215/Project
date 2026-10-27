package androidx.leanback.widget;

import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: loaded from: classes3.dex */
public class Util {
    public static boolean isDescendant(ViewGroup parent, View child) {
        while (child != null) {
            if (child == parent) {
                return true;
            }
            Object parent2 = child.getParent();
            if (!(parent2 instanceof View)) {
                return false;
            }
            child = (View) parent2;
        }
        return false;
    }

    private Util() {
    }
}
