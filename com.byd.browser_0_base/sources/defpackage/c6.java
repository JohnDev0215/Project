package defpackage;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.hardware.display.DisplayManager;
import android.os.StrictMode;
import android.os.UserManager;
import android.view.Display;
import android.widget.TextView;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/* JADX INFO: compiled from: ApiCompatibilityUtils.java */
/* JADX INFO: loaded from: classes.dex */
public final class c6 {
    public static int a(int i, int i2, Context context, String str) {
        try {
            return context.checkPermission(str, i, i2);
        } catch (RuntimeException unused) {
            return -1;
        }
    }

    public static byte[] b(String str) {
        return str.getBytes(StandardCharsets.UTF_8);
    }

    public static Drawable c(Resources resources, int i, int i2) {
        StrictMode.ThreadPolicy threadPolicyAllowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            return i2 == 0 ? resources.getDrawable(i, null) : resources.getDrawableForDensity(i, i2, null);
        } finally {
            StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskReads);
        }
    }

    public static ArrayList d(Activity activity) {
        DisplayManager displayManager;
        ArrayList arrayList = new ArrayList();
        if (activity != null && (displayManager = (DisplayManager) activity.getSystemService("display")) != null) {
            Display[] displays = displayManager.getDisplays();
            ActivityManager activityManager = (ActivityManager) activity.getSystemService("activity");
            for (Display display : displays) {
                if (display.getState() == 2 && activityManager.isActivityStartAllowedOnDisplay(activity, display.getDisplayId(), new Intent(activity, activity.getClass()))) {
                    arrayList.add(Integer.valueOf(display.getDisplayId()));
                }
            }
        }
        return arrayList;
    }

    public static boolean e() {
        return ((UserManager) xz.a.getSystemService("user")).isDemoUser();
    }

    public static void f(TextView textView, int i) {
        textView.setTextAppearance(textView.getContext(), i);
    }
}
