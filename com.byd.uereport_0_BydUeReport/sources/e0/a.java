package e0;

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
    public static final Object f4513a = new Object();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static TypedValue f4514b;

    public static int a(Context context, String str) {
        if (str != null) {
            return context.checkPermission(str, Process.myPid(), Process.myUid());
        }
        throw new IllegalArgumentException("permission is null");
    }

    public static int b(Context context, int i8) {
        return Build.VERSION.SDK_INT >= 23 ? context.getColor(i8) : context.getResources().getColor(i8);
    }

    public static ColorStateList c(Context context, int i8) {
        return Build.VERSION.SDK_INT >= 23 ? context.getColorStateList(i8) : context.getResources().getColorStateList(i8);
    }

    public static Drawable d(Context context, int i8) {
        int i9;
        int i10 = Build.VERSION.SDK_INT;
        if (i10 >= 21) {
            return context.getDrawable(i8);
        }
        if (i10 >= 16) {
            return context.getResources().getDrawable(i8);
        }
        synchronized (f4513a) {
            if (f4514b == null) {
                f4514b = new TypedValue();
            }
            context.getResources().getValue(i8, f4514b, true);
            i9 = f4514b.resourceId;
        }
        return context.getResources().getDrawable(i9);
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
