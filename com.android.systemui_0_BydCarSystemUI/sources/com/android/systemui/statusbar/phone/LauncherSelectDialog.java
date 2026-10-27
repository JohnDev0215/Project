package com.android.systemui.statusbar.phone;

import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.hardware.input.InputManager;
import android.os.Handler;
import android.os.LauncherManager;
import android.os.SystemClock;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.systemui.Dependency;
import com.android.systemui.R;
import com.android.systemui.base.BaseDialog2;
import com.android.systemui.stackdivider.DividerUtils;
import com.byd.card.utils.AppUtils;
import com.byd.card.widget.BlurView;
import com.bydux.api.common.tools.log.BydLogHelper;
import com.bydux.api.common.views.window.ToastUtils;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class LauncherSelectDialog extends BaseDialog2 implements View.OnClickListener, DialogInterface.OnShowListener {
    private static final int INDEX_MAP_LAUNCHER = 0;
    private static final int INDEX_WALLPAPER = 1;
    private static final String LAUNCHER_CHANGE_ACTION = "com.byd.action.launcher.change";
    public static final String PKG_MAP_LAUNCHER = "com.neusoft.na.navigation";
    private static final String PKG_WALLPAPER = "com.android.launcher3";
    private static final String TAG = "LauncherSelectDialog";
    public String HOME_ACTION;
    public String HOME_CATEGORY;
    private Bitmap mBlurBitmap;
    private BlurView mBlurView;
    private Button mCancel;
    private ImageView mClose;
    private int mDefaultMode;
    private LauncherManager mLauncherManager;
    private ImageView mMap;
    private ImageView mMapSelect;
    private TextView mMapText;
    private Button mOk;
    private int mTargetMode;
    private ImageView mWallpaper;
    private ImageView mWallpaperSelect;
    private TextView mWallpaperText;
    private Runnable toHome;

    @Override // com.android.systemui.base.BaseDialog2
    protected void initSize() {
    }

    public void setBlurBitmap(Bitmap mBlurBitmap) {
        this.mBlurBitmap = mBlurBitmap;
    }

    public void setBlurBitmap() {
        Bitmap bitmap = this.mBlurBitmap;
        if (bitmap != null) {
            this.mBlurView.setBlurView(bitmap);
        }
    }

    public LauncherSelectDialog(Context context) {
        this(context, 0);
    }

    public LauncherSelectDialog(Context context, int themeResId) {
        super(context, themeResId);
        this.HOME_ACTION = "android.intent.action.MAIN";
        this.HOME_CATEGORY = "android.intent.category.HOME_ONLY";
        this.toHome = new Runnable() { // from class: com.android.systemui.statusbar.phone.LauncherSelectDialog.2
            @Override // java.lang.Runnable
            public void run() {
                long jUptimeMillis = SystemClock.uptimeMillis();
                LauncherSelectDialog.this.sendEvent(3, 0, 0, jUptimeMillis, jUptimeMillis);
                LauncherSelectDialog.this.sendEvent(3, 1, 0, jUptimeMillis, jUptimeMillis);
            }
        };
    }

    @Override // com.android.systemui.base.BaseDialog2
    protected int getLayoutId() {
        return AppUtils.isRightRudder() ? R.layout.dialog_launcher_select_right_rudder : R.layout.dialog_launcher_select;
    }

    private void initView() {
        getWindow().setBackgroundDrawable(this.mContext.getDrawable(R.drawable.blur_shadow_bg));
        getWindow().setElevation(16.0f);
        this.mBlurView = (BlurView) findViewById(R.id.root_blur);
        this.mClose = (ImageView) findViewById(R.id.close);
        this.mMap = (ImageView) findViewById(R.id.map);
        this.mMapSelect = (ImageView) findViewById(R.id.map_select);
        this.mMapText = (TextView) findViewById(R.id.map_launcher);
        this.mWallpaper = (ImageView) findViewById(R.id.wallpaper);
        this.mWallpaperSelect = (ImageView) findViewById(R.id.wallpaper_select);
        this.mWallpaperText = (TextView) findViewById(R.id.wallpaper_launcher);
        this.mOk = (Button) findViewById(R.id.ok);
        this.mCancel = (Button) findViewById(R.id.cancel);
        this.mClose.setOnClickListener(this);
        this.mMap.setOnClickListener(this);
        this.mWallpaper.setOnClickListener(this);
        this.mOk.setOnClickListener(this);
        this.mCancel.setOnClickListener(this);
        setOnShowListener(this);
        this.mBlurView.setBlurViewRadius(this.mContext.getResources().getDimensionPixelSize(R.dimen.dp_16));
    }

    @Override // com.android.systemui.base.BaseDialog2
    protected void initData() {
        this.mLauncherManager = (LauncherManager) this.mContext.getSystemService("launcher");
        initView();
        initSelectOption();
    }

    private void setSelectView(boolean isWallpaper) {
        this.mWallpaperSelect.setVisibility(isWallpaper ? 0 : 8);
        this.mMapSelect.setVisibility(isWallpaper ? 8 : 0);
        this.mWallpaperText.setSelected(isWallpaper);
        this.mMapText.setSelected(!isWallpaper);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cancel /* 2131296392 */:
            case R.id.close /* 2131296422 */:
                dismiss();
                break;
            case R.id.map /* 2131296687 */:
                this.mTargetMode = 0;
                setSelectView(false);
                break;
            case R.id.ok /* 2131296751 */:
                DividerUtils.getInstance().setLauncherSelecting(true);
                new Handler().postDelayed(new Runnable() { // from class: com.android.systemui.statusbar.phone.LauncherSelectDialog.1
                    @Override // java.lang.Runnable
                    public void run() {
                        DividerUtils.getInstance().setLauncherSelecting(false);
                    }
                }, 500L);
                handlerSure();
                dismiss();
                break;
            case R.id.wallpaper /* 2131297103 */:
                this.mTargetMode = 1;
                setSelectView(true);
                break;
        }
    }

    public void startHome(Context context) {
        ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).postDelayed(this.toHome, 30L);
    }

    private void handlerSure() {
        if (this.mTargetMode != this.mDefaultMode) {
            setLauncherMode(this.mContext, this.mTargetMode);
            sendBroadcastFromLauncherSelect(null, LAUNCHER_CHANGE_ACTION);
        }
        showToast();
    }

    /* JADX WARN: Removed duplicated region for block: B:6:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void setLauncherMode(android.content.Context r4, int r5) {
        /*
            r3 = this;
            java.lang.String r4 = com.android.systemui.statusbar.phone.LauncherSelectDialog.TAG
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "--setLauncherMode---index = "
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.StringBuilder r0 = r0.append(r5)
            java.lang.String r0 = r0.toString()
            com.bydux.api.common.tools.log.BydLogHelper.logD(r4, r0)
            java.lang.String r0 = "com.huawei.maps.auto.app"
            java.lang.String r1 = "com.android.launcher3"
            java.lang.String r2 = "com.neusoft.na.navigation"
            if (r5 == 0) goto L27
            r0 = 1
            if (r5 == r0) goto L25
        L23:
            r0 = r2
            goto L34
        L25:
            r0 = r1
            goto L34
        L27:
            boolean r5 = com.byd.card.utils.AppUtils.isAppInstall(r2)
            if (r5 == 0) goto L2e
            goto L23
        L2e:
            boolean r5 = com.byd.card.utils.AppUtils.isAppInstall(r0)
            if (r5 == 0) goto L25
        L34:
            boolean r5 = android.text.TextUtils.isEmpty(r0)
            if (r5 != 0) goto L60
            android.os.LauncherManager r5 = r3.mLauncherManager
            if (r5 == 0) goto L60
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r1 = "setDefaultLauncher pkg = "
            java.lang.StringBuilder r5 = r5.append(r1)
            java.lang.StringBuilder r5 = r5.append(r0)
            java.lang.String r5 = r5.toString()
            com.bydux.api.common.tools.log.BydLogHelper.logD(r4, r5)
            android.os.LauncherManager r4 = r3.mLauncherManager
            r4.setDefaultLauncher(r0)
            android.content.Context r4 = r3.mContext
            r3.startHome(r4)
            goto L83
        L60:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r1 = "pkg:"
            java.lang.StringBuilder r5 = r5.append(r1)
            java.lang.StringBuilder r5 = r5.append(r0)
            java.lang.String r0 = " , mLauncherManager:"
            java.lang.StringBuilder r5 = r5.append(r0)
            android.os.LauncherManager r0 = r3.mLauncherManager
            java.lang.StringBuilder r5 = r5.append(r0)
            java.lang.String r5 = r5.toString()
            android.util.Log.i(r4, r5)
        L83:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.phone.LauncherSelectDialog.setLauncherMode(android.content.Context, int):void");
    }

    public static void set(String key, String value) {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            cls.getMethod("set", String.class, String.class).invoke(cls, key, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setDefaultLauncher(Context ctx, String pkg) {
        PackageManager packageManager = ctx.getPackageManager();
        Intent intent = new Intent();
        intent.setAction("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        List<ResolveInfo> listQueryIntentActivities = packageManager.queryIntentActivities(intent, 0);
        ComponentName[] componentNameArr = new ComponentName[listQueryIntentActivities.size()];
        ComponentName componentName = null;
        for (int i = 0; i < listQueryIntentActivities.size(); i++) {
            ActivityInfo activityInfo = listQueryIntentActivities.get(i).activityInfo;
            ComponentName componentName2 = new ComponentName(activityInfo.packageName, activityInfo.name);
            componentNameArr[i] = componentName2;
            if (activityInfo.packageName.equals(pkg)) {
                componentName = componentName2;
            }
        }
        if (componentName == null) {
            return;
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.MAIN");
        intentFilter.addCategory("android.intent.category.HOME");
        intentFilter.addCategory("android.intent.category.DEFAULT");
        try {
            packageManager.getClass().getMethod("replacePreferredActivity", IntentFilter.class, Integer.TYPE, ComponentName[].class, ComponentName.class).invoke(packageManager, intentFilter, 1048576, componentNameArr, componentName);
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static List getLauncherList(Context context) {
        BydLogHelper.logE();
        ArrayList arrayList = new ArrayList();
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        List<ResolveInfo> listQueryIntentActivities = context.getPackageManager().queryIntentActivities(intent, 65536);
        for (int i = 0; i < listQueryIntentActivities.size(); i++) {
            ResolveInfo resolveInfo = listQueryIntentActivities.get(i);
            "com.android.launcher".equals(resolveInfo.activityInfo.packageName);
            arrayList.add(resolveInfo.activityInfo.packageName);
            BydLogHelper.logE(TAG, "packageName =" + resolveInfo.activityInfo.packageName);
        }
        if (arrayList.size() == 0) {
            return null;
        }
        return arrayList;
    }

    public int getLauncherMode(Context ctx) {
        PackageManager packageManager = ctx.getPackageManager();
        Intent intent = new Intent();
        intent.setAction("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        ResolveInfo resolveInfoResolveActivity = packageManager.resolveActivity(intent, 0);
        if (resolveInfoResolveActivity == null) {
            BydLogHelper.logE(TAG, "rif = null");
            return 1;
        }
        String str = resolveInfoResolveActivity.activityInfo.packageName;
        Log.i(TAG, "pkg = " + str);
        return (str == null || !"com.android.launcher3".equals(str)) ? 0 : 1;
    }

    @Override // android.content.DialogInterface.OnShowListener
    public void onShow(DialogInterface dialog) {
        initSelectOption();
    }

    @Override // android.app.Dialog
    public void show() {
        this.mBlurView.setBlurView(this.mBlurBitmap);
        super.show();
    }

    private void initSelectOption() {
        int launcherMode = getLauncherMode(this.mContext);
        this.mDefaultMode = launcherMode;
        this.mTargetMode = launcherMode;
        BydLogHelper.logD(TAG, "mDefaultMode = " + this.mDefaultMode);
        setSelectView(this.mDefaultMode == 1);
    }

    private void showToast() {
        if (this.mTargetMode == 1) {
            ToastUtils.show(this.mContext, R.string.set_wallpaper_launcher);
        } else {
            ToastUtils.show(this.mContext, R.string.set_map_launcher);
        }
    }

    public boolean isLand(Context context) {
        return context.getResources().getConfiguration().orientation != 1;
    }

    public void sendEvent(int keyCode, int action, int flags, long down, long when) {
        InputManager.getInstance().injectInputEvent(new KeyEvent(down, when, action, keyCode, (flags & 128) != 0 ? 1 : 0, 0, -1, 0, flags | 8 | 64, 257), 0);
    }

    private synchronized void sendBroadcastFromLauncherSelect(String pkg, String action) {
        Log.i(TAG, "broadcast:" + action);
        Intent intent = new Intent();
        intent.setAction(action);
        this.mContext.sendBroadcast(intent);
    }
}
