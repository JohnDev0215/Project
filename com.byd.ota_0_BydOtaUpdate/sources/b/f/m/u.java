package b.f.m;

import android.os.Build;
import android.view.ViewGroup;

/* JADX INFO: compiled from: ViewGroupCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class u {
    public static boolean a(ViewGroup viewGroup) {
        if (Build.VERSION.SDK_INT >= 21) {
            return viewGroup.isTransitionGroup();
        }
        Boolean bool = (Boolean) viewGroup.getTag(b.f.c.M);
        return ((bool == null || !bool.booleanValue()) && viewGroup.getBackground() == null && s.A(viewGroup) == null) ? false : true;
    }
}
