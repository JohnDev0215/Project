package g5;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

/* JADX INFO: compiled from: DimensionUtil.java */
/* JADX INFO: loaded from: classes2.dex */
public class e {
    public static int a(View view) {
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        return iArr[0];
    }

    public static int b(View view) {
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        return iArr[1];
    }

    public static int c(Context context) {
        Resources resources = context.getResources();
        int identifier = resources.getIdentifier(g(context) ? "navigation_bar_height_landscape" : "navigation_bar_height", "dimen", "android");
        if (identifier > 0) {
            return resources.getDimensionPixelSize(identifier);
        }
        return 0;
    }

    public static int d(Context context) {
        try {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getRealMetrics(displayMetrics);
            return g(context) ? Math.min(displayMetrics.widthPixels, displayMetrics.heightPixels) : Math.max(displayMetrics.widthPixels, displayMetrics.heightPixels);
        } catch (Exception e8) {
            e8.printStackTrace();
            return 0;
        }
    }

    public static int e(Context context) {
        try {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getRealMetrics(displayMetrics);
            return g(context) ? Math.max(displayMetrics.widthPixels, displayMetrics.heightPixels) : Math.min(displayMetrics.widthPixels, displayMetrics.heightPixels);
        } catch (Exception e8) {
            e8.printStackTrace();
            return 0;
        }
    }

    public static int f(Context context) {
        Resources resources = context.getResources();
        int identifier = resources.getIdentifier(g(context) ? "status_bar_height_landscape" : "status_bar_height", "dimen", "android");
        if (identifier > 0) {
            return resources.getDimensionPixelSize(identifier);
        }
        return 0;
    }

    public static boolean g(Context context) {
        return context.getResources().getConfiguration().orientation == 2;
    }
}
