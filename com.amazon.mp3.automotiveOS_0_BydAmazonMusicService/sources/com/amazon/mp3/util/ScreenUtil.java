package com.amazon.mp3.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.view.OrientationEventListener;
import android.view.WindowManager;
import androidx.core.os.EnvironmentCompat;
import com.amazon.mp3.AmazonApplication;
import com.amazon.music.events.types.Orientation;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ViewType;
import com.fasterxml.jackson.dataformat.cbor.CBORConstants;
import com.visualon.OSMPUtils.voOSType;
import java.util.Locale;

/* JADX INFO: loaded from: classes4.dex */
public class ScreenUtil {
    private static final int[] LARGE_OR_EXTRA_LARGE = {3, 4};
    private static final int[] NORMAL_OR_SMALL = {2, 1};
    private static int sDeviceHeight;
    private static int sDeviceWidth;

    public static boolean isLargeOrExtraLargeScreen() {
        return matchScreenSize(LARGE_OR_EXTRA_LARGE);
    }

    public static boolean isNormalOrSmallScreen() {
        return matchScreenSize(NORMAL_OR_SMALL);
    }

    public static boolean isNormalXXHdpiDisplay() {
        return matchScreenSize(new int[]{2}) && matchScreenDensity(new int[]{480});
    }

    public static boolean isPortrait() {
        return getScreenOrientation(AmazonApplication.getContext()) == Orientation.PORTRAIT;
    }

    public static boolean isLandscape() {
        return getScreenOrientation(AmazonApplication.getContext()) == Orientation.LANDSCAPE;
    }

    public static Orientation getScreenOrientation(final Context context) {
        if (context == null) {
            return null;
        }
        int i = context.getResources().getConfiguration().orientation;
        if (i == 0) {
            return Orientation.UNDEFINED;
        }
        if (i == 1) {
            return Orientation.PORTRAIT;
        }
        if (i == 2) {
            return Orientation.LANDSCAPE;
        }
        if (i != 3) {
            return null;
        }
        return Orientation.SQUARE;
    }

    public static ViewType getScreenViewType(final Context context) {
        if (context == null) {
            return null;
        }
        int i = context.getResources().getConfiguration().orientation;
        if (i == 0) {
            return ViewType.UNDEFINED;
        }
        if (i == 1) {
            return ViewType.PORTRAIT;
        }
        if (i == 2) {
            return ViewType.LANDSCAPE;
        }
        if (i != 3) {
            return null;
        }
        return ViewType.SQUARE;
    }

    @Deprecated
    public static int getDeviceWidth() {
        if (sDeviceWidth == 0) {
            Point point = new Point();
            ((WindowManager) AmazonApplication.getContext().getSystemService("window")).getDefaultDisplay().getSize(point);
            sDeviceWidth = point.x;
        }
        return sDeviceWidth;
    }

    public static int getDeviceHeight() {
        if (sDeviceHeight == 0) {
            Point point = new Point();
            ((WindowManager) AmazonApplication.getContext().getSystemService("window")).getDefaultDisplay().getSize(point);
            sDeviceHeight = point.y;
        }
        return sDeviceHeight;
    }

    public static int convertDpToPx(final int dp) {
        return Math.round((dp * AmazonApplication.getContext().getResources().getDisplayMetrics().densityDpi) / CBORConstants.PREFIX_TYPE_OBJECT);
    }

    public static Point getActivityWindowSize(final Activity activity) {
        Point point = new Point();
        activity.getWindowManager().getDefaultDisplay().getSize(point);
        return point;
    }

    private static boolean matchScreenSize(int[] sizes) {
        int i = AmazonApplication.getContext().getResources().getConfiguration().screenLayout & 15;
        for (int i2 : sizes) {
            if (i == i2) {
                return true;
            }
        }
        return false;
    }

    private static boolean matchScreenDensity(int[] densities) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) AmazonApplication.getContext().getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        for (int i : densities) {
            if (displayMetrics.densityDpi == i) {
                return true;
            }
        }
        return false;
    }

    public static String describeScreen() {
        return String.format(Locale.US, "%s-%s-%s", screenLayoutSize(), screenLayoutLong(), screenDpi());
    }

    private static String screenLayoutLong() {
        int i = AmazonApplication.getContext().getResources().getConfiguration().screenLayout & 48;
        if (i == 0) {
            return "undef(" + i + ")";
        }
        if (i == 16) {
            return "notLong";
        }
        if (i == 32) {
            return "long";
        }
        return "unknown(" + i + ")";
    }

    private static String screenLayoutSize() {
        int i = AmazonApplication.getContext().getResources().getConfiguration().screenLayout & 15;
        if (i == 0) {
            return "undef(" + i + ")";
        }
        if (i == 1) {
            return "small";
        }
        if (i == 2) {
            return "normal";
        }
        if (i == 3) {
            return "large";
        }
        if (i == 4) {
            return "xlarge";
        }
        return "unknown(" + i + ")";
    }

    private static String screenDpi() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) AmazonApplication.getContext().getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        int i = displayMetrics.densityDpi;
        return String.format(Locale.US, "%s (%s)", i != 120 ? i != 160 ? i != 240 ? i != 320 ? i != 480 ? 160 == i ? "default" : EnvironmentCompat.MEDIA_UNKNOWN : "xxhdpi" : "xhdpi" : "hdpi" : "mdpi" : "ldpi", Integer.valueOf(i));
    }

    public static OrientationEventListener getOrientationEventListener(final Activity activity) {
        return new OrientationEventListener(activity) { // from class: com.amazon.mp3.util.ScreenUtil.1
            @Override // android.view.OrientationEventListener
            public void onOrientationChanged(int orientation) {
                if (shouldRotate(orientation, 90, 5) || shouldRotate(orientation, voOSType.VOOSMP_PID_DOLBY_CERTIFICATION_DIALOGUE_ENHANCEMENT, 5)) {
                    activity.setRequestedOrientation(4);
                }
            }

            private boolean shouldRotate(int deviceAngle, int orientationAngle, int threshold) {
                return (Settings.System.getInt(activity.getContentResolver(), "accelerometer_rotation", 0) == 1) && deviceAngle > orientationAngle - threshold && deviceAngle < orientationAngle + threshold;
            }
        };
    }

    public static void removeOrientationEventListener(OrientationEventListener listener) {
        if (listener != null) {
            listener.disable();
        }
    }
}
