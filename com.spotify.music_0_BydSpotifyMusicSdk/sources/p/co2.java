package p;

import android.os.Build;

/* JADX INFO: loaded from: classes.dex */
public abstract class co2 {
    public static final boolean a;
    public static final boolean b;
    public static final boolean c;

    static {
        int i = Build.VERSION.SDK_INT;
        a = true;
        b = true;
        c = i >= 28;
    }
}
