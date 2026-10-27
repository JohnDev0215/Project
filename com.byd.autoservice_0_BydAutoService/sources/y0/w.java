package y0;

import android.annotation.SuppressLint;
import android.os.Build;
import android.view.ViewGroup;

/* JADX INFO: compiled from: ViewGroupUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class w {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static boolean f5988a = true;

    public static v a(ViewGroup viewGroup) {
        return Build.VERSION.SDK_INT >= 18 ? new u(viewGroup) : t.g(viewGroup);
    }

    @SuppressLint({"NewApi"})
    public static void b(ViewGroup viewGroup, boolean z3) {
        if (f5988a) {
            try {
                viewGroup.suppressLayout(z3);
            } catch (NoSuchMethodError unused) {
                f5988a = false;
            }
        }
    }

    public static void c(ViewGroup viewGroup, boolean z3) {
        int i3 = Build.VERSION.SDK_INT;
        if (i3 >= 29) {
            viewGroup.suppressLayout(z3);
        } else if (i3 >= 18) {
            b(viewGroup, z3);
        } else {
            x.b(viewGroup, z3);
        }
    }
}
