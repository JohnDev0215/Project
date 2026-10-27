package a.g.k;

import android.os.Build;
import android.view.ViewGroup;

/* JADX INFO: loaded from: classes.dex */
public final class v {
    public static boolean a(ViewGroup viewGroup) {
        if (Build.VERSION.SDK_INT >= 21) {
            return viewGroup.isTransitionGroup();
        }
        Boolean bool = (Boolean) viewGroup.getTag(a.g.b.M);
        return ((bool == null || !bool.booleanValue()) && viewGroup.getBackground() == null && t.F(viewGroup) == null) ? false : true;
    }
}
