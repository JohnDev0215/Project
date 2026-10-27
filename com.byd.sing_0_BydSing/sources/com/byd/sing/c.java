package com.byd.sing;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

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
        return a(1, f, SingApplication.a().getResources().getDisplayMetrics());
    }

    public static int c(View view) {
        if (view == null) {
            return 0;
        }
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        return iArr[0];
    }

    public static int d(View view) {
        if (view == null) {
            return 0;
        }
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        return iArr[1];
    }

    public static int e() {
        Resources resources = SingApplication.a().getResources();
        int identifier = resources.getIdentifier(j(SingApplication.a()) ? "navigation_bar_height_landscape" : "navigation_bar_height", "dimen", "android");
        if (identifier > 0) {
            return resources.getDimensionPixelSize(identifier);
        }
        return 0;
    }

    public static int f() {
        try {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((WindowManager) SingApplication.a().getSystemService("window")).getDefaultDisplay().getRealMetrics(displayMetrics);
            return j(SingApplication.a()) ? Math.min(displayMetrics.widthPixels, displayMetrics.heightPixels) : Math.max(displayMetrics.widthPixels, displayMetrics.heightPixels);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static int g() {
        return f() - e();
    }

    public static int h() {
        try {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((WindowManager) SingApplication.a().getSystemService("window")).getDefaultDisplay().getRealMetrics(displayMetrics);
            return j(SingApplication.a()) ? Math.max(displayMetrics.widthPixels, displayMetrics.heightPixels) : Math.min(displayMetrics.widthPixels, displayMetrics.heightPixels);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static int i() {
        Resources resources = SingApplication.a().getResources();
        int identifier = resources.getIdentifier("status_bar_height", "dimen", "android");
        if (identifier > 0) {
            return resources.getDimensionPixelSize(identifier);
        }
        return 0;
    }

    public static boolean j(Context context) {
        return context.getResources().getConfiguration().orientation == 2;
    }

    public static float k(float f) {
        return a(2, f, SingApplication.a().getResources().getDisplayMetrics());
    }
}
