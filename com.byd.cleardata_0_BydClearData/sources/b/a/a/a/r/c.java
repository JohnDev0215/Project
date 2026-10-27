package b.a.a.a.r;

import android.os.Build;

/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int f1762a;

    static {
        int i = Build.VERSION.SDK_INT;
        f1762a = i >= 21 ? 2 : i >= 18 ? 1 : 0;
    }
}
