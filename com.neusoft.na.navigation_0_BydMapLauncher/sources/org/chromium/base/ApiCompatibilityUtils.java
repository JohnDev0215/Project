package org.chromium.base;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.PendingIntent;
import android.car.Car;
import android.content.ContentResolver;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.PowerManager;
import android.os.Process;
import android.os.StatFs;
import android.os.UserManager;
import android.provider.Settings;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

/* JADX INFO: loaded from: classes3.dex */
public class ApiCompatibilityUtils {
    static final /* synthetic */ boolean $assertionsDisabled = false;

    public static int getActivityNewDocumentFlag() {
        return 524288;
    }

    public static boolean isElevationSupported() {
        return true;
    }

    public static boolean isPrintingSupported() {
        return true;
    }

    private ApiCompatibilityUtils() {
    }

    public static boolean isLayoutRtl(View view) {
        return view.getLayoutDirection() == 1;
    }

    public static int getLayoutDirection(Configuration configuration) {
        return configuration.getLayoutDirection();
    }

    public static void setLayoutDirection(View view, int i) {
        view.setLayoutDirection(i);
    }

    public static void setTextAlignment(View view, int i) {
        view.setTextAlignment(i);
    }

    public static void setTextDirection(View view, int i) {
        view.setTextDirection(i);
    }

    public static void setLabelFor(View view, int i) {
        view.setLabelFor(i);
    }

    public static void setMarginEnd(ViewGroup.MarginLayoutParams marginLayoutParams, int i) {
        marginLayoutParams.setMarginEnd(i);
    }

    public static int getMarginEnd(ViewGroup.MarginLayoutParams marginLayoutParams) {
        return marginLayoutParams.getMarginEnd();
    }

    public static void setMarginStart(ViewGroup.MarginLayoutParams marginLayoutParams, int i) {
        marginLayoutParams.setMarginStart(i);
    }

    public static int getMarginStart(ViewGroup.MarginLayoutParams marginLayoutParams) {
        return marginLayoutParams.getMarginStart();
    }

    public static void setPaddingRelative(View view, int i, int i2, int i3, int i4) {
        view.setPaddingRelative(i, i2, i3, i4);
    }

    public static int getPaddingStart(View view) {
        return view.getPaddingStart();
    }

    public static int getPaddingEnd(View view) {
        return view.getPaddingEnd();
    }

    public static void setCompoundDrawablesRelative(TextView textView, Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        textView.setCompoundDrawablesRelative(drawable, drawable2, drawable3, drawable4);
    }

    public static void setCompoundDrawablesRelativeWithIntrinsicBounds(TextView textView, Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        textView.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
    }

    public static void setCompoundDrawablesRelativeWithIntrinsicBounds(TextView textView, int i, int i2, int i3, int i4) {
        textView.setCompoundDrawablesRelativeWithIntrinsicBounds(i, i2, i3, i4);
    }

    public static String getCreatorPackage(PendingIntent pendingIntent) {
        return pendingIntent.getCreatorPackage();
    }

    public static boolean isDeviceProvisioned(Context context) {
        return context == null || context.getContentResolver() == null || Settings.Global.getInt(context.getContentResolver(), "device_provisioned", 0) != 0;
    }

    public static void finishAndRemoveTask(Activity activity) {
        activity.finishAndRemoveTask();
    }

    public static boolean setElevation(View view, float f) {
        if (!isElevationSupported()) {
            return false;
        }
        view.setElevation(f);
        return true;
    }

    private static class FinishAndRemoveTaskWithRetry implements Runnable {
        private static final long MAX_TRY_COUNT = 3;
        private static final long RETRY_DELAY_MS = 500;
        private final Activity mActivity;
        private int mTryCount;

        FinishAndRemoveTaskWithRetry(Activity activity) {
            this.mActivity = activity;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.mActivity.finishAndRemoveTask();
            this.mTryCount++;
            if (this.mActivity.isFinishing()) {
                return;
            }
            if (this.mTryCount < 3) {
                ThreadUtils.postOnUiThreadDelayed(this, RETRY_DELAY_MS);
            } else {
                this.mActivity.finish();
            }
        }
    }

    public static boolean isInteractive(Context context) {
        return ((PowerManager) context.getSystemService(Car.POWER_SERVICE)).isInteractive();
    }

    public static boolean shouldSkipFirstUseHints(ContentResolver contentResolver) {
        return Settings.Secure.getInt(contentResolver, "skip_first_use_hints", 0) != 0;
    }

    public static void setTaskDescription(Activity activity, String str, Bitmap bitmap, int i) {
        activity.setTaskDescription(new ActivityManager.TaskDescription(str, bitmap, i));
    }

    public static void setStatusBarColor(Window window, int i) {
        if (i == -16777216 && window.getNavigationBarColor() == -16777216) {
            window.clearFlags(Integer.MIN_VALUE);
        } else {
            window.addFlags(Integer.MIN_VALUE);
        }
        window.setStatusBarColor(i);
    }

    public static Drawable getDrawable(Resources resources, int i) throws Resources.NotFoundException {
        return resources.getDrawable(i, null);
    }

    public static Drawable getDrawableForDensity(Resources resources, int i, int i2) {
        return resources.getDrawableForDensity(i, i2, null);
    }

    public static void finishAfterTransition(Activity activity) {
        activity.finishAfterTransition();
    }

    public static Drawable getUserBadgedIcon(Context context, int i) {
        return context.getPackageManager().getUserBadgedIcon(getDrawable(context.getResources(), i), Process.myUserHandle());
    }

    public static Drawable getUserBadgedDrawableForDensity(Context context, Drawable drawable, Rect rect, int i) {
        return context.getPackageManager().getUserBadgedDrawableForDensity(drawable, Process.myUserHandle(), rect, i);
    }

    public static int getColor(Resources resources, int i) throws Resources.NotFoundException {
        return resources.getColor(i, null);
    }

    public static ColorFilter getColorFilter(Drawable drawable) {
        return drawable.getColorFilter();
    }

    public static ColorStateList getColorStateList(Resources resources, int i) throws Resources.NotFoundException {
        return resources.getColorStateList(i, null);
    }

    public static void setTextAppearance(TextView textView, int i) {
        textView.setTextAppearance(i);
    }

    public static long getBlockCount(StatFs statFs) {
        return statFs.getBlockCountLong();
    }

    public static long getBlockSize(StatFs statFs) {
        return statFs.getBlockSizeLong();
    }

    public static boolean isDemoUser(Context context) {
        if (!BuildInfo.isGreaterThanN()) {
            return false;
        }
        try {
            return ((Boolean) UserManager.class.getMethod("isDemoUser", new Class[0]).invoke((UserManager) context.getSystemService("user"), new Object[0])).booleanValue();
        } catch (RuntimeException | Exception unused) {
            return false;
        }
    }

    public static int checkPermission(Context context, String str, int i, int i2) {
        try {
            return context.checkPermission(str, i, i2);
        } catch (RuntimeException unused) {
            return -1;
        }
    }
}
