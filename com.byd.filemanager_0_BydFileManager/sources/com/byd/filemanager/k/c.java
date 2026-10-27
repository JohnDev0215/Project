package com.byd.filemanager.k;

import android.content.Context;
import android.util.DisplayMetrics;
import com.byd.filemanager.FileApplication;

/* JADX INFO: compiled from: DimensionUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class c {
    public static float a(int i, float f, DisplayMetrics displayMetrics) {
        float f2;
        if (i == 0) {
            return f;
        }
        if (i == 1) {
            f2 = displayMetrics.density;
        } else if (i == 2) {
            f2 = displayMetrics.scaledDensity;
        } else if (i == 3) {
            f *= displayMetrics.xdpi;
            f2 = 0.013888889f;
        } else if (i == 4) {
            f2 = displayMetrics.xdpi;
        } else {
            if (i != 5) {
                return 0.0f;
            }
            f *= displayMetrics.xdpi;
            f2 = 0.03937008f;
        }
        return f * f2;
    }

    public static float b(float f) {
        return a(1, f, FileApplication.a().getResources().getDisplayMetrics());
    }

    public static int c(Context context) {
        return context.getResources().getConfiguration().orientation;
    }

    public static boolean d(Context context) {
        return context.getResources().getConfiguration().orientation == 2;
    }

    public static boolean e(Context context) {
        return context.getResources().getConfiguration().orientation == 1;
    }

    public static boolean f() {
        return FileApplication.a().getResources().getConfiguration().getLayoutDirection() == 1;
    }

    public static boolean g(Context context) {
        return context.getResources().getConfiguration().locale.getLanguage().endsWith("zh");
    }
}
