package k1;

import android.annotation.SuppressLint;
import android.os.Build;
import android.view.ViewGroup;

/* JADX INFO: compiled from: ViewGroupUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class w {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static boolean f5830a = true;

    public static v a(ViewGroup viewGroup) {
        return Build.VERSION.SDK_INT >= 18 ? new u(viewGroup) : t.g(viewGroup);
    }

    @SuppressLint({"NewApi"})
    public static void b(ViewGroup viewGroup, boolean z7) {
        if (f5830a) {
            try {
                viewGroup.suppressLayout(z7);
            } catch (NoSuchMethodError unused) {
                f5830a = false;
            }
        }
    }

    public static void c(ViewGroup viewGroup, boolean z7) {
        int i8 = Build.VERSION.SDK_INT;
        if (i8 >= 29) {
            viewGroup.suppressLayout(z7);
        } else if (i8 >= 18) {
            b(viewGroup, z7);
        } else {
            x.b(viewGroup, z7);
        }
    }
}
