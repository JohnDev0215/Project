package b0;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Process;
import android.util.TypedValue;
import java.io.File;

/* JADX INFO: compiled from: ContextCompat.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Object f1756a = new Object();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static TypedValue f1757b;

    public static int a(Context context, String str) {
        if (str != null) {
            return context.checkPermission(str, Process.myPid(), Process.myUid());
        }
        throw new IllegalArgumentException("permission is null");
    }

    public static int b(Context context, int i3) {
        return Build.VERSION.SDK_INT >= 23 ? context.getColor(i3) : context.getResources().getColor(i3);
    }

    public static ColorStateList c(Context context, int i3) {
        return Build.VERSION.SDK_INT >= 23 ? context.getColorStateList(i3) : context.getResources().getColorStateList(i3);
    }

    public static Drawable d(Context context, int i3) {
        int i4;
        int i5 = Build.VERSION.SDK_INT;
        if (i5 >= 21) {
            return context.getDrawable(i3);
        }
        if (i5 >= 16) {
            return context.getResources().getDrawable(i3);
        }
        synchronized (f1756a) {
            if (f1757b == null) {
                f1757b = new TypedValue();
            }
            context.getResources().getValue(i3, f1757b, true);
            i4 = f1757b.resourceId;
        }
        return context.getResources().getDrawable(i4);
    }

    public static File[] e(Context context) {
        return Build.VERSION.SDK_INT >= 19 ? context.getExternalCacheDirs() : new File[]{context.getExternalCacheDir()};
    }

    public static File[] f(Context context, String str) {
        return Build.VERSION.SDK_INT >= 19 ? context.getExternalFilesDirs(str) : new File[]{context.getExternalFilesDir(str)};
    }

    public static boolean g(Context context, Intent[] intentArr, Bundle bundle) {
        if (Build.VERSION.SDK_INT >= 16) {
            context.startActivities(intentArr, bundle);
            return true;
        }
        context.startActivities(intentArr);
        return true;
    }
}
