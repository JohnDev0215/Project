package com.android.launcher3;

import android.animation.ValueAnimator;
import android.app.ActivityManager;
import android.app.WallpaperManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.LauncherActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ShortcutInfo;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.Message;
import android.os.PowerManager;
import android.os.TransactionTooLargeException;
import android.provider.Settings;
import android.telephony.SmsManager;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.TtsSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Pair;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.Interpolator;
import com.android.launcher3.compat.LauncherAppsCompat;
import com.android.launcher3.compat.ShortcutConfigActivityInfo;
import com.android.launcher3.dragndrop.FolderAdaptiveIcon;
import com.android.launcher3.graphics.RotationMode;
import com.android.launcher3.graphics.TintedDrawableSpan;
import com.android.launcher3.icons.LauncherIcons;
import com.android.launcher3.shortcuts.DeepShortcutManager;
import com.android.launcher3.shortcuts.ShortcutKey;
import com.android.launcher3.util.IntArray;
import com.android.launcher3.util.PackageManagerHelper;
import com.android.launcher3.views.Transposable;
import com.android.launcher3.widget.PendingAddShortcutInfo;
import java.io.Closeable;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes4.dex */
public final class Utilities {
    public static final boolean ATLEAST_OREO;
    public static final boolean ATLEAST_OREO_MR1;
    public static final boolean ATLEAST_P;
    public static final boolean ATLEAST_Q;
    private static final int CORE_POOL_SIZE;
    private static final int CPU_COUNT;
    public static final int EDGE_NAV_BAR = 256;
    public static final String EXTRA_WALLPAPER_FLAVOR = "com.android.launcher3.WALLPAPER_FLAVOR";
    public static final String EXTRA_WALLPAPER_OFFSET = "com.android.launcher3.WALLPAPER_OFFSET";
    public static final int FLAG_NO_GESTURES = 512;
    public static final boolean IS_DEBUG_DEVICE;
    public static boolean IS_RUNNING_IN_TEST_HARNESS = false;
    private static final int KEEP_ALIVE = 1;
    private static final int MAXIMUM_POOL_SIZE;
    public static final int SINGLE_FRAME_MS = 16;
    private static final String TAG = "Launcher.Utilities";
    public static final Executor THREAD_POOL_EXECUTOR;
    private static final Pattern sTrimPattern = Pattern.compile("^[\\s|\\p{javaSpaceChar}]*(.*)[\\s|\\p{javaSpaceChar}]*$");
    private static final int[] sLoc0 = new int[2];
    private static final int[] sLoc1 = new int[2];
    private static final Matrix sMatrix = new Matrix();
    private static final Matrix sInverseMatrix = new Matrix();

    public static float mapRange(float value, float min, float max) {
        return min + (value * (max - min));
    }

    public static float squaredHypot(float x, float y) {
        return (x * x) + (y * y);
    }

    static {
        ATLEAST_Q = Build.VERSION.SDK_INT >= 29;
        ATLEAST_P = Build.VERSION.SDK_INT >= 28;
        ATLEAST_OREO_MR1 = Build.VERSION.SDK_INT >= 27;
        ATLEAST_OREO = Build.VERSION.SDK_INT >= 26;
        IS_DEBUG_DEVICE = Build.TYPE.toLowerCase(Locale.ROOT).contains("debug") || Build.TYPE.toLowerCase(Locale.ROOT).equals("eng");
        int iAvailableProcessors = Runtime.getRuntime().availableProcessors();
        CPU_COUNT = iAvailableProcessors;
        int i = iAvailableProcessors + 1;
        CORE_POOL_SIZE = i;
        int i2 = (iAvailableProcessors * 2) + 1;
        MAXIMUM_POOL_SIZE = i2;
        THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(i, i2, 1L, TimeUnit.SECONDS, new LinkedBlockingQueue());
        IS_RUNNING_IN_TEST_HARNESS = ActivityManager.isRunningInTestHarness();
    }

    public static boolean shouldDisableGestures(MotionEvent ev) {
        return (ev.getEdgeFlags() & 512) == 512;
    }

    public static boolean isDevelopersOptionsEnabled(Context context) {
        return Settings.Global.getInt(context.getApplicationContext().getContentResolver(), "development_settings_enabled", 0) != 0;
    }

    public static void enableRunningInTestHarnessForTests() {
        IS_RUNNING_IN_TEST_HARNESS = true;
    }

    public static boolean isPropertyEnabled(String propertyName) {
        return Log.isLoggable(propertyName, 2);
    }

    public static boolean existsStyleWallpapers(Context context) {
        return context.getPackageManager().resolveActivity(PackageManagerHelper.getStyleWallpapersIntent(context), 0) != null;
    }

    public static float getDescendantCoordRelativeToAncestor(View descendant, View ancestor, float[] coord, boolean includeRootScroll) {
        return getDescendantCoordRelativeToAncestor(descendant, ancestor, coord, includeRootScroll, false, null);
    }

    public static float getDescendantCoordRelativeToAncestor(View descendant, View ancestor, float[] coord, boolean includeRootScroll, boolean ignoreTransform, float[] outRotation) {
        float scaleX = 1.0f;
        for (View view = descendant; view != ancestor && view != null; view = (View) view.getParent()) {
            if (view != descendant || includeRootScroll) {
                offsetPoints(coord, -view.getScrollX(), -view.getScrollY());
            }
            if (ignoreTransform) {
                if (view instanceof Transposable) {
                    RotationMode rotationMode = ((Transposable) view).getRotationMode();
                    if (rotationMode.isTransposed) {
                        Matrix matrix = sMatrix;
                        matrix.setRotate(rotationMode.surfaceRotation, view.getPivotX(), view.getPivotY());
                        matrix.mapPoints(coord);
                        if (outRotation != null) {
                            outRotation[0] = outRotation[0] + rotationMode.surfaceRotation;
                        }
                    }
                }
            } else {
                view.getMatrix().mapPoints(coord);
            }
            offsetPoints(coord, view.getLeft(), view.getTop());
            scaleX *= view.getScaleX();
        }
        return scaleX;
    }

    public static void mapCoordInSelfToDescendant(View descendant, View root, float[] coord) {
        sMatrix.reset();
        while (descendant != root) {
            Matrix matrix = sMatrix;
            matrix.postTranslate(-descendant.getScrollX(), -descendant.getScrollY());
            matrix.postConcat(descendant.getMatrix());
            matrix.postTranslate(descendant.getLeft(), descendant.getTop());
            descendant = (View) descendant.getParent();
        }
        Matrix matrix2 = sMatrix;
        matrix2.postTranslate(-descendant.getScrollX(), -descendant.getScrollY());
        Matrix matrix3 = sInverseMatrix;
        matrix2.invert(matrix3);
        matrix3.mapPoints(coord);
    }

    public static void roundArray(float[] in, int[] out) {
        for (int i = 0; i < in.length; i++) {
            out[i] = Math.round(in[i]);
        }
    }

    public static void offsetPoints(float[] points, float offsetX, float offsetY) {
        for (int i = 0; i < points.length; i += 2) {
            points[i] = points[i] + offsetX;
            int i2 = i + 1;
            points[i2] = points[i2] + offsetY;
        }
    }

    public static boolean pointInView(View v, float localX, float localY, float slop) {
        float f = -slop;
        return localX >= f && localY >= f && localX < ((float) v.getWidth()) + slop && localY < ((float) v.getHeight()) + slop;
    }

    public static int[] getCenterDeltaInScreenSpace(View v0, View v1) {
        int[] iArr = sLoc0;
        v0.getLocationInWindow(iArr);
        int[] iArr2 = sLoc1;
        v1.getLocationInWindow(iArr2);
        iArr[0] = (int) (iArr[0] + ((v0.getMeasuredWidth() * v0.getScaleX()) / 2.0f));
        iArr[1] = (int) (iArr[1] + ((v0.getMeasuredHeight() * v0.getScaleY()) / 2.0f));
        iArr2[0] = (int) (iArr2[0] + ((v1.getMeasuredWidth() * v1.getScaleX()) / 2.0f));
        iArr2[1] = (int) (iArr2[1] + ((v1.getMeasuredHeight() * v1.getScaleY()) / 2.0f));
        return new int[]{iArr2[0] - iArr[0], iArr2[1] - iArr[1]};
    }

    public static void scaleRectFAboutCenter(RectF r, float scale) {
        if (scale != 1.0f) {
            float fCenterX = r.centerX();
            float fCenterY = r.centerY();
            r.offset(-fCenterX, -fCenterY);
            r.left *= scale;
            r.top *= scale;
            r.right *= scale;
            r.bottom *= scale;
            r.offset(fCenterX, fCenterY);
        }
    }

    public static void scaleRectAboutCenter(Rect r, float scale) {
        if (scale != 1.0f) {
            int iCenterX = r.centerX();
            int iCenterY = r.centerY();
            r.offset(-iCenterX, -iCenterY);
            scaleRect(r, scale);
            r.offset(iCenterX, iCenterY);
        }
    }

    public static void scaleRect(Rect r, float scale) {
        if (scale != 1.0f) {
            r.left = (int) ((r.left * scale) + 0.5f);
            r.top = (int) ((r.top * scale) + 0.5f);
            r.right = (int) ((r.right * scale) + 0.5f);
            r.bottom = (int) ((r.bottom * scale) + 0.5f);
        }
    }

    public static void insetRect(Rect r, Rect insets) {
        r.left = Math.min(r.right, r.left + insets.left);
        r.top = Math.min(r.bottom, r.top + insets.top);
        r.right = Math.max(r.left, r.right - insets.right);
        r.bottom = Math.max(r.top, r.bottom - insets.bottom);
    }

    public static float shrinkRect(Rect r, float scaleX, float scaleY) {
        float fMin = Math.min(Math.min(scaleX, scaleY), 1.0f);
        if (fMin < 1.0f) {
            int iWidth = (int) (r.width() * (scaleX - fMin) * 0.5f);
            r.left += iWidth;
            r.right -= iWidth;
            int iHeight = (int) (r.height() * (scaleY - fMin) * 0.5f);
            r.top += iHeight;
            r.bottom -= iHeight;
        }
        return fMin;
    }

    public static float mapToRange(float t, float fromMin, float fromMax, float toMin, float toMax, Interpolator interpolator) {
        if (fromMin == fromMax || toMin == toMax) {
            Log.e(TAG, "mapToRange: range has 0 length");
            return toMin;
        }
        return mapRange(interpolator.getInterpolation(getProgress(t, fromMin, fromMax)), toMin, toMax);
    }

    public static float getProgress(float current, float min, float max) {
        return Math.abs(current - min) / Math.abs(max - min);
    }

    public static boolean isSystemApp(Context context, Intent intent) {
        String packageName;
        PackageManager packageManager = context.getPackageManager();
        ComponentName component = intent.getComponent();
        if (component == null) {
            ResolveInfo resolveInfoResolveActivity = packageManager.resolveActivity(intent, 65536);
            packageName = (resolveInfoResolveActivity == null || resolveInfoResolveActivity.activityInfo == null) ? null : resolveInfoResolveActivity.activityInfo.packageName;
        } else {
            packageName = component.getPackageName();
        }
        if (packageName == null) {
            return false;
        }
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(packageName, 0);
            if (packageInfo == null || packageInfo.applicationInfo == null) {
                return false;
            }
            return (packageInfo.applicationInfo.flags & 1) != 0;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    static Pair<String, Resources> findSystemApk(String action, PackageManager pm) {
        Iterator<ResolveInfo> it = pm.queryBroadcastReceivers(new Intent(action), 0).iterator();
        while (it.hasNext()) {
            ResolveInfo next = it.next();
            if (next.activityInfo != null && (next.activityInfo.applicationInfo.flags & 1) != 0) {
                String str = next.activityInfo.packageName;
                try {
                    return Pair.create(str, pm.getResourcesForApplication(str));
                } catch (PackageManager.NameNotFoundException unused) {
                    Log.w(TAG, "Failed to find resources for " + str);
                }
            }
        }
        return null;
    }

    public static String trim(CharSequence s) {
        if (s == null) {
            return null;
        }
        return sTrimPattern.matcher(s).replaceAll("$1");
    }

    public static int calculateTextHeight(float textSizePx) {
        Paint paint = new Paint();
        paint.setTextSize(textSizePx);
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        return (int) Math.ceil(fontMetrics.bottom - fontMetrics.top);
    }

    public static void println(String key, Object... args) {
        StringBuilder sb = new StringBuilder();
        sb.append(key);
        sb.append(": ");
        boolean z = true;
        for (Object obj : args) {
            if (z) {
                z = false;
            } else {
                sb.append(", ");
            }
            sb.append(obj);
        }
        System.out.println(sb.toString());
    }

    public static boolean isRtl(Resources res) {
        return res.getConfiguration().getLayoutDirection() == 1;
    }

    public static boolean isLauncherAppTarget(Intent launchIntent) {
        if (launchIntent == null || !Intent.ACTION_MAIN.equals(launchIntent.getAction()) || launchIntent.getComponent() == null || launchIntent.getCategories() == null || launchIntent.getCategories().size() != 1 || !launchIntent.hasCategory(Intent.CATEGORY_LAUNCHER) || !TextUtils.isEmpty(launchIntent.getDataString())) {
            return false;
        }
        Bundle extras = launchIntent.getExtras();
        return extras == null || extras.keySet().isEmpty();
    }

    public static float dpiFromPx(int size, DisplayMetrics metrics) {
        return size / (metrics.densityDpi / 160.0f);
    }

    public static int pxFromSp(float size, DisplayMetrics metrics) {
        return Math.round(TypedValue.applyDimension(2, size, metrics));
    }

    public static String createDbSelectionQuery(String columnName, IntArray values) {
        return String.format(Locale.ENGLISH, "%s IN (%s)", columnName, values.toConcatString());
    }

    public static boolean isBootCompleted() {
        return "1".equals(getSystemProperty("sys.boot_completed", "1"));
    }

    public static String getSystemProperty(String property, String defaultValue) {
        String str;
        try {
            str = (String) Class.forName("android.os.SystemProperties").getDeclaredMethod("get", String.class).invoke(null, property);
        } catch (Exception unused) {
            Log.d(TAG, "Unable to read system properties");
        }
        return !TextUtils.isEmpty(str) ? str : defaultValue;
    }

    public static int boundToRange(int value, int lowerBound, int upperBound) {
        return Math.max(lowerBound, Math.min(value, upperBound));
    }

    public static float boundToRange(float value, float lowerBound, float upperBound) {
        return Math.max(lowerBound, Math.min(value, upperBound));
    }

    public static long boundToRange(long value, long lowerBound, long upperBound) {
        return Math.max(lowerBound, Math.min(value, upperBound));
    }

    public static CharSequence wrapForTts(CharSequence msg, String ttsMsg) {
        SpannableString spannableString = new SpannableString(msg);
        spannableString.setSpan(new TtsSpan.TextBuilder(ttsMsg).build(), 0, spannableString.length(), 18);
        return spannableString;
    }

    public static CharSequence prefixTextWithIcon(Context context, int iconRes, CharSequence msg) {
        SpannableString spannableString = new SpannableString("  " + ((Object) msg));
        spannableString.setSpan(new TintedDrawableSpan(context, iconRes), 0, 1, 34);
        return spannableString;
    }

    public static SharedPreferences getPrefs(Context context) {
        return context.getSharedPreferences(LauncherFiles.SHARED_PREFERENCES_KEY, 0);
    }

    public static SharedPreferences getDevicePrefs(Context context) {
        return context.getSharedPreferences(LauncherFiles.DEVICE_PREFERENCES_KEY, 0);
    }

    public static boolean areAnimationsEnabled(Context context) {
        if (ATLEAST_OREO) {
            return ValueAnimator.areAnimatorsEnabled();
        }
        return !((PowerManager) context.getSystemService(PowerManager.class)).isPowerSaveMode();
    }

    public static boolean isWallpaperAllowed(Context context) {
        return ((WallpaperManager) context.getSystemService(WallpaperManager.class)).isSetWallpaperAllowed();
    }

    public static void closeSilently(Closeable c) {
        if (c != null) {
            try {
                c.close();
            } catch (IOException unused) {
            }
        }
    }

    public static boolean isBinderSizeError(Exception e) {
        return (e.getCause() instanceof TransactionTooLargeException) || (e.getCause() instanceof DeadObjectException);
    }

    public static void postAsyncCallback(Handler handler, Runnable callback) {
        Message messageObtain = Message.obtain(handler, callback);
        messageObtain.setAsynchronous(true);
        handler.sendMessage(messageObtain);
    }

    public static Point parsePoint(String point) {
        String[] strArrSplit = point.split(SmsManager.REGEX_PREFIX_DELIMITER);
        return new Point(Integer.parseInt(strArrSplit[0]), Integer.parseInt(strArrSplit[1]));
    }

    public static String getPointString(int x, int y) {
        return String.format(Locale.ENGLISH, "%d,%d", Integer.valueOf(x), Integer.valueOf(y));
    }

    public static void unregisterReceiverSafely(Context context, BroadcastReceiver receiver) {
        try {
            context.unregisterReceiver(receiver);
        } catch (IllegalArgumentException unused) {
        }
    }

    public static Drawable getFullDrawable(Launcher launcher, ItemInfo info, int width, int height, boolean flattenDrawable, Object[] outObj) {
        FolderAdaptiveIcon folderAdaptiveIconCreateFolderAdaptiveIcon;
        LauncherAppState launcherAppState = LauncherAppState.getInstance(launcher);
        if (info.itemType == 0) {
            LauncherActivityInfo launcherActivityInfoResolveActivity = LauncherAppsCompat.getInstance(launcher).resolveActivity(info.getIntent(), info.user);
            outObj[0] = launcherActivityInfoResolveActivity;
            if (launcherActivityInfoResolveActivity != null) {
                return launcherAppState.getIconCache().getFullResIcon(launcherActivityInfoResolveActivity, flattenDrawable);
            }
            return null;
        }
        if (info.itemType == 6) {
            if (info instanceof PendingAddShortcutInfo) {
                ShortcutConfigActivityInfo shortcutConfigActivityInfo = ((PendingAddShortcutInfo) info).activityInfo;
                outObj[0] = shortcutConfigActivityInfo;
                return shortcutConfigActivityInfo.getFullResIcon(launcherAppState.getIconCache());
            }
            ShortcutKey shortcutKeyFromItemInfo = ShortcutKey.fromItemInfo(info);
            DeepShortcutManager deepShortcutManager = DeepShortcutManager.getInstance(launcher);
            List<ShortcutInfo> listQueryForFullDetails = deepShortcutManager.queryForFullDetails(shortcutKeyFromItemInfo.componentName.getPackageName(), Arrays.asList(shortcutKeyFromItemInfo.getId()), shortcutKeyFromItemInfo.user);
            if (listQueryForFullDetails.isEmpty()) {
                return null;
            }
            outObj[0] = listQueryForFullDetails.get(0);
            return deepShortcutManager.getShortcutIconDrawable(listQueryForFullDetails.get(0), launcherAppState.getInvariantDeviceProfile().fillResIconDpi);
        }
        if (info.itemType != 2 || (folderAdaptiveIconCreateFolderAdaptiveIcon = FolderAdaptiveIcon.createFolderAdaptiveIcon(launcher, info.id, new Point(width, height))) == null) {
            return null;
        }
        outObj[0] = folderAdaptiveIconCreateFolderAdaptiveIcon;
        return folderAdaptiveIconCreateFolderAdaptiveIcon;
    }

    public static Drawable getBadge(Launcher launcher, ItemInfo info, Object obj) {
        LauncherAppState launcherAppState = LauncherAppState.getInstance(launcher);
        int i = launcherAppState.getInvariantDeviceProfile().iconBitmapSize;
        if (info.itemType == 6) {
            boolean z = (info instanceof ItemInfoWithIcon) && (((ItemInfoWithIcon) info).runtimeStatusFlags & 512) > 0;
            if ((info.id == -1 && !z) || !(obj instanceof ShortcutInfo)) {
                return new FixedSizeEmptyDrawable(i);
            }
            LauncherIcons launcherIconsObtain = LauncherIcons.obtain(launcherAppState.getContext());
            Bitmap bitmap = launcherIconsObtain.getShortcutInfoBadge((ShortcutInfo) obj, launcherAppState.getIconCache()).iconBitmap;
            launcherIconsObtain.recycle();
            float f = i;
            float dimension = (f - launcher.getResources().getDimension(R.dimen.profile_badge_size)) / f;
            return new InsetDrawable(new FastBitmapDrawable(bitmap), dimension, dimension, 0.0f, 0.0f);
        }
        if (info.itemType == 2) {
            return ((FolderAdaptiveIcon) obj).getBadge();
        }
        return launcher.getPackageManager().getUserBadgedIcon(new FixedSizeEmptyDrawable(i), info.user);
    }

    public static int[] getIntArrayFromString(String tokenized) {
        StringTokenizer stringTokenizer = new StringTokenizer(tokenized, SmsManager.REGEX_PREFIX_DELIMITER);
        int[] iArr = new int[stringTokenizer.countTokens()];
        int i = 0;
        while (stringTokenizer.hasMoreTokens()) {
            iArr[i] = Integer.parseInt(stringTokenizer.nextToken());
            i++;
        }
        return iArr;
    }

    public static String getStringFromIntArray(int[] array) {
        StringBuilder sb = new StringBuilder();
        for (int i : array) {
            sb.append(i).append(SmsManager.REGEX_PREFIX_DELIMITER);
        }
        return sb.toString();
    }

    public static float squaredTouchSlop(Context context) {
        float scaledTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        return scaledTouchSlop * scaledTouchSlop;
    }

    private static class FixedSizeEmptyDrawable extends ColorDrawable {
        private final int mSize;

        public FixedSizeEmptyDrawable(int size) {
            super(0);
            this.mSize = size;
        }

        @Override // android.graphics.drawable.Drawable
        public int getIntrinsicHeight() {
            return this.mSize;
        }

        @Override // android.graphics.drawable.Drawable
        public int getIntrinsicWidth() {
            return this.mSize;
        }
    }
}
