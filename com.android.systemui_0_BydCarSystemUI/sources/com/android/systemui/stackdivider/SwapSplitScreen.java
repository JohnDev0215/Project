package com.android.systemui.stackdivider;

import android.app.ActivityManager;
import android.app.ActivityTaskManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.RemoteException;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.SurfaceControl;
import android.view.WindowManager;
import androidx.appcompat.widget.ActivityChooserView;
import com.android.systemui.R;
import com.android.systemui.SysUiServiceProvider;
import com.android.systemui.statusbar.wm.Util;
import com.byd.card.utils.AppUtils;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: loaded from: classes.dex */
public class SwapSplitScreen {
    private static SwapSplitScreen instance;
    private final DividerUtils mDividerUtils = DividerUtils.getInstance();

    public static synchronized SwapSplitScreen getInstance() {
        if (instance == null) {
            instance = new SwapSplitScreen();
        }
        return instance;
    }

    private SwapSplitScreen() {
    }

    public void executeSwapView(final Context context, final Divider divider) {
        ActivityManager.StackInfo stackInfo = this.mDividerUtils.getStackInfo(true);
        ActivityManager.StackInfo stackInfo2 = this.mDividerUtils.getStackInfo(false);
        BydLogHelper.logD("primaryStackInfo : " + (stackInfo != null) + " , secondStackInfo : " + (stackInfo2 == null));
        if (stackInfo == null || stackInfo2 == null || stackInfo.topActivity == null || stackInfo2.topActivity == null) {
            BydLogHelper.logD("primaryStackInfo or secondStackInfo is null");
            return;
        }
        String packageName = stackInfo.topActivity.getPackageName();
        String packageName2 = stackInfo2.topActivity.getPackageName();
        BydLogHelper.logD("primaryPkg = " + packageName + " secondPkg = " + packageName2);
        final Bitmap appIcon = getAppIcon(context, packageName);
        final Bitmap appIcon2 = getAppIcon(context, packageName2);
        new Handler().postDelayed(new Runnable() { // from class: com.android.systemui.stackdivider.SwapSplitScreen.1
            @Override // java.lang.Runnable
            public void run() {
                BydLogHelper.logD("executeSwapView isPowerMode = " + SwapSplitScreen.this.mDividerUtils.isPowerMode());
                if (SwapSplitScreen.this.mDividerUtils.isPowerMode()) {
                    return;
                }
                Bitmap splitScreenshot = SwapSplitScreen.this.getSplitScreenshot(context, true);
                Bitmap splitScreenshot2 = SwapSplitScreen.this.getSplitScreenshot(context, false);
                boolean zIsRtl = AppUtils.isRtl(context);
                boolean zIsHorizontalDivision = SwapSplitScreen.this.mDividerUtils.isHorizontalDivision();
                BydLogHelper.logD("executeSwapView isRtl = " + zIsRtl + " , isHorizontalDivision : " + zIsHorizontalDivision);
                if (!zIsHorizontalDivision) {
                    splitScreenshot2 = SwapSplitScreen.this.getSplitScreenshot(context, false);
                    splitScreenshot = SwapSplitScreen.this.getSplitScreenshot(context, true);
                } else if (zIsRtl) {
                    splitScreenshot2 = SwapSplitScreen.this.getSplitScreenshot(context, true);
                    splitScreenshot = SwapSplitScreen.this.getSplitScreenshot(context, false);
                }
                BydLogHelper.logD("primaryBitmap = " + splitScreenshot + ", secondBitmap = " + splitScreenshot2);
                BydLogHelper.logD("primaryLayerTwoBitmap = " + SwapSplitScreen.this.layerTwoBitmap(appIcon, splitScreenshot) + ", secondLayerTwoBitmap = " + SwapSplitScreen.this.layerTwoBitmap(appIcon2, splitScreenshot2));
                if (splitScreenshot == null || splitScreenshot2 == null) {
                    BydLogHelper.logD("continueCreateSwapView bitmap is null");
                    SwapSplitScreen.this.swapSplitTask(context);
                } else {
                    divider.createSwapView(splitScreenshot, splitScreenshot2);
                }
            }
        }, 100L);
    }

    private Bitmap getAppIcon(Context context, String str) {
        PackageManager packageManager = context.getPackageManager();
        try {
            Drawable applicationIcon = packageManager.getApplicationIcon(packageManager.getApplicationInfo(str, 128));
            int intrinsicWidth = applicationIcon.getIntrinsicWidth();
            int intrinsicHeight = applicationIcon.getIntrinsicHeight();
            BydLogHelper.logD("getAppIcon intrinsicWidth =" + intrinsicWidth + ", intrinsicHeight =" + intrinsicHeight);
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmapCreateBitmap);
            applicationIcon.setBounds(0, 0, 100, 100);
            applicationIcon.draw(canvas);
            return bitmapCreateBitmap;
        } catch (Exception e) {
            BydLogHelper.logD("getAppIcon Exception = " + e);
            e.printStackTrace();
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Bitmap layerTwoBitmap(Bitmap icon, Bitmap bitmap) {
        if (icon == null || bitmap == null) {
            return null;
        }
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        Bitmap bitmapCopy = bitmap.copy(Bitmap.Config.ARGB_4444, true);
        Canvas canvas = new Canvas(bitmapCopy);
        BydLogHelper.logD("bitmapBgWidth = " + bitmapCopy.getWidth() + ", bitmapBgHeight = " + bitmapCopy.getHeight());
        canvas.drawBitmap(icon, (r2 - icon.getWidth()) / 2.0f, (r3 - icon.getHeight()) / 2.0f, paint);
        canvas.save();
        canvas.restore();
        icon.recycle();
        return bitmapCopy;
    }

    public void swapSplitTask(final Context context) {
        final ActivityManager.StackInfo stackInfo = this.mDividerUtils.getStackInfo(true);
        final ActivityManager.StackInfo stackInfo2 = this.mDividerUtils.getStackInfo(false);
        BydLogHelper.logD("py_dock swapSplitTask primaryStackInfo = " + stackInfo);
        BydLogHelper.logD("py_dock swapSplitTask secondStackInfo = " + stackInfo2);
        if (stackInfo == null || stackInfo2 == null) {
            return;
        }
        final int iExcludeForbiddenActivity = DividerUtils.excludeForbiddenActivity(stackInfo, null);
        final int iExcludeForbiddenActivity2 = DividerUtils.excludeForbiddenActivity(stackInfo2, null);
        BydLogHelper.logD("py_dock swapSplitTask primaryTaskId = " + iExcludeForbiddenActivity + " secondaryTaskId = " + iExcludeForbiddenActivity2 + " secondStackInfo.stackId = " + stackInfo2.stackId);
        if (stackInfo.topActivity == null || stackInfo2.topActivity == null || iExcludeForbiddenActivity < 0 || iExcludeForbiddenActivity2 < 0) {
            BydLogHelper.logD("py_dock swapSplitTask return 1");
            return;
        }
        BydLogHelper.logD("py_dock swapSplitTask primaryStackInfo getPackageName = " + stackInfo.topActivity.getPackageName());
        BydLogHelper.logD("py_dock swapSplitTask secondStackInfo getPackageName = " + stackInfo2.topActivity.getPackageName());
        if (stackInfo.topActivity.getPackageName().equals("com.android.launcher3") || stackInfo2.topActivity.getPackageName().equals("com.android.launcher3")) {
            BydLogHelper.logD("py_dock swapSplitTask return 2");
        } else {
            new Thread(new Runnable() { // from class: com.android.systemui.stackdivider.-$$Lambda$SwapSplitScreen$nPq8XwAMMQFLRYML4B6jtQUMAoQ
                @Override // java.lang.Runnable
                public final void run() {
                    SwapSplitScreen.lambda$swapSplitTask$0(context, iExcludeForbiddenActivity2, stackInfo, iExcludeForbiddenActivity, stackInfo2);
                }
            }).start();
        }
    }

    static /* synthetic */ void lambda$swapSplitTask$0(Context context, int i, ActivityManager.StackInfo stackInfo, int i2, ActivityManager.StackInfo stackInfo2) {
        try {
            Divider divider = (Divider) SysUiServiceProvider.getComponent(context, Divider.class);
            if (divider != null) {
                BydLogHelper.logD("py_dock swapSplitTask primary to second divider.isSplitActive() = " + divider.isSplitActive());
                if (divider.isSplitActive()) {
                    ActivityTaskManager.getService().positionTaskInStack(i, stackInfo.stackId, ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED);
                }
                Thread.sleep(200L);
                BydLogHelper.logD("py_dock swapSplitTask second to primary divider.isSplitActive() = " + divider.isSplitActive());
                if (divider.isSplitActive()) {
                    ActivityTaskManager.getService().positionTaskInStack(i2, stackInfo2.stackId, ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED);
                }
            }
        } catch (RemoteException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Bitmap getSplitScreenshot(Context context, boolean isPrimary) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int navigationBarHeight = Util.getNavigationBarHeight(context);
        int statusBarHeight = Util.getStatusBarHeight(context);
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(R.dimen.docked_divider_handle_height);
        BydLogHelper.logD("getScreenshot navigationBarHeight = " + navigationBarHeight + ", statusBarHeight = " + statusBarHeight + ", dockedDividerHeight = " + dimensionPixelSize);
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        defaultDisplay.getRealMetrics(displayMetrics);
        int i8 = displayMetrics.widthPixels;
        int i9 = displayMetrics.heightPixels;
        int rotation = defaultDisplay.getRotation();
        BydLogHelper.logD("getScreenshot rot = " + rotation + ", mScreenWidth = " + i8 + ", mScreenHeight = " + i9 + " dockedDividerHeight =" + dimensionPixelSize);
        int i10 = 0;
        if (rotation != 0) {
            if (rotation == 1) {
                if (isPrimary) {
                    i = i9 - navigationBarHeight;
                    i2 = i / 2;
                    i3 = dimensionPixelSize / 2;
                    statusBarHeight = i2 + i3 + 9;
                } else {
                    i4 = (i9 - navigationBarHeight) / 2;
                    i5 = dimensionPixelSize / 2;
                    i = (i4 - i5) - 9;
                    statusBarHeight = 0;
                }
            } else if (rotation == 2) {
                if (isPrimary) {
                    i7 = (dimensionPixelSize + i8) / 2;
                    i10 = i7 + 14;
                } else {
                    i6 = (i8 - dimensionPixelSize) / 2;
                    i8 = i6 - 14;
                }
            } else if (rotation != 3) {
                statusBarHeight = 0;
                i = 0;
            } else if (isPrimary) {
                i4 = (i9 - navigationBarHeight) / 2;
                i5 = dimensionPixelSize / 2;
                i = (i4 - i5) - 9;
                statusBarHeight = 0;
            } else {
                i = i9 - navigationBarHeight;
                i2 = i / 2;
                i3 = dimensionPixelSize / 2;
                statusBarHeight = i2 + i3 + 9;
            }
            BydLogHelper.logD("getScreenshot left = " + i10 + ", top = " + statusBarHeight + ", right = " + i8 + ", bottom = " + i);
            return getBlurImage(context, SurfaceControl.screenshot(new Rect(i10, statusBarHeight, i8, i), i8 - i10, i - statusBarHeight, rotation), 0.9f, 20.0f);
        }
        if (isPrimary) {
            i6 = (i8 - dimensionPixelSize) / 2;
            i8 = i6 - 14;
        } else {
            i7 = (dimensionPixelSize + i8) / 2;
            i10 = i7 + 14;
        }
        i = i9 - navigationBarHeight;
        BydLogHelper.logD("getScreenshot left = " + i10 + ", top = " + statusBarHeight + ", right = " + i8 + ", bottom = " + i);
        return getBlurImage(context, SurfaceControl.screenshot(new Rect(i10, statusBarHeight, i8, i), i8 - i10, i - statusBarHeight, rotation), 0.9f, 20.0f);
    }

    public Bitmap getBlurImage(Context context, Bitmap bitmap, float ambiguity, float radius) {
        BydLogHelper.logD("getBlurImage: start");
        if (bitmap == null) {
            return null;
        }
        Bitmap bitmapCopy = bitmap.copy(Bitmap.Config.ARGB_8888, false);
        Bitmap bitmapCreateScaledBitmap = Bitmap.createScaledBitmap(bitmapCopy, Math.round(bitmapCopy.getWidth() * ambiguity), Math.round(bitmapCopy.getHeight() * ambiguity), false);
        bitmapCopy.recycle();
        RenderScript renderScriptCreate = RenderScript.create(context);
        Allocation allocationCreateFromBitmap = Allocation.createFromBitmap(renderScriptCreate, bitmapCreateScaledBitmap);
        Allocation allocationCreateTyped = Allocation.createTyped(renderScriptCreate, allocationCreateFromBitmap.getType());
        ScriptIntrinsicBlur scriptIntrinsicBlurCreate = ScriptIntrinsicBlur.create(renderScriptCreate, Element.U8_4(renderScriptCreate));
        scriptIntrinsicBlurCreate.setInput(allocationCreateFromBitmap);
        scriptIntrinsicBlurCreate.setRadius(radius);
        scriptIntrinsicBlurCreate.forEach(allocationCreateTyped);
        allocationCreateTyped.copyTo(bitmapCreateScaledBitmap);
        renderScriptCreate.destroy();
        BydLogHelper.logD("getBlurImage: end");
        return bitmapCreateScaledBitmap;
    }
}
