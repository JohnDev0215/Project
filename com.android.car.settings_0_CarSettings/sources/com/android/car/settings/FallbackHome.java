package com.android.car.settings;

import android.app.Activity;
import android.car.userlib.UserHelper;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.LauncherManager;
import android.os.Message;
import android.os.PowerManager;
import android.os.SystemClock;
import android.os.SystemProperties;
import android.os.UserHandle;
import android.os.UserManager;
import android.provider.Settings;
import com.android.car.settings.common.Logger;
import java.util.Objects;

/* JADX INFO: loaded from: classes5.dex */
public class FallbackHome extends Activity {
    private static final String DEFAULT_LAUNCHER_NAME = "persist.sys.default_launcher_name";
    private static final String HUAWEI_NAVI_PKG_NAME = "com.huawei.maps.auto.app";
    private static final String LAUNCHER_PKG_NAME = "com.android.launcher3";
    private static final Logger LOG = new Logger((Class<?>) FallbackHome.class);
    private static final int MSG_TIME_100 = 100;
    private static final String NAVI_PKG_NAME = "com.neusoft.na.navigation";
    private boolean mFinished;
    private LauncherManager mLauncherManager;
    private BroadcastReceiver mReceiver = new BroadcastReceiver() { // from class: com.android.car.settings.FallbackHome.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            FallbackHome.this.maybeFinish();
        }
    };
    private Handler mHandler = new Handler() { // from class: com.android.car.settings.FallbackHome.2
        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            FallbackHome.this.maybeFinish();
        }
    };

    @Override // android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(android.R.style.Theme.Translucent.NoTitleBar.Fullscreen);
        requestWindowFeature(1);
        getWindow().getDecorView().setSystemUiVisibility(2050);
        this.mLauncherManager = (LauncherManager) getApplicationContext().getSystemService("launcher");
        registerReceiver(this.mReceiver, new IntentFilter("android.intent.action.USER_UNLOCKED"));
        maybeFinish();
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        LOG.e("Fallback Home onResume ");
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(this.mReceiver);
        if (!this.mFinished) {
            LOG.e("User " + getUserId() + " FallbackHome is finished");
            finishFallbackHome();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void maybeFinish() {
        if (((UserManager) getSystemService(UserManager.class)).isUserUnlocked()) {
            Intent homeIntent = new Intent("android.intent.action.MAIN").addCategory("android.intent.category.HOME");
            ResolveInfo homeInfo = getPackageManager().resolveActivity(homeIntent, 0);
            if (Objects.equals(getPackageName(), homeInfo.activityInfo.packageName)) {
                if (UserManager.isSplitSystemUser() && UserHandle.myUserId() == 0) {
                    return;
                }
                String defaultLauncherPackage = SystemProperties.get(DEFAULT_LAUNCHER_NAME);
                if (!isUserSetup() || defaultLauncherPackage == null) {
                    this.mLauncherManager.setDefaultLauncher(LAUNCHER_PKG_NAME);
                } else if (defaultLauncherPackage != null && defaultLauncherPackage.equals(LAUNCHER_PKG_NAME)) {
                    this.mLauncherManager.setDefaultLauncher(LAUNCHER_PKG_NAME);
                } else if (defaultLauncherPackage != null && defaultLauncherPackage.equals(NAVI_PKG_NAME)) {
                    this.mLauncherManager.setDefaultLauncher(NAVI_PKG_NAME);
                } else if (defaultLauncherPackage != null && defaultLauncherPackage.equals(HUAWEI_NAVI_PKG_NAME)) {
                    this.mLauncherManager.setDefaultLauncher(HUAWEI_NAVI_PKG_NAME);
                } else {
                    this.mLauncherManager.setDefaultLauncher(LAUNCHER_PKG_NAME);
                }
                LOG.d("User " + getUserId() + " unlocked but no home; let's hope someone enables one soon?");
                this.mHandler.sendEmptyMessageDelayed(0, 100L);
                return;
            }
            String homePackageName = homeInfo.activityInfo.packageName;
            if (UserHelper.isHeadlessSystemUser(getUserId())) {
                LOG.d("User 0 unlocked, but will not launch real home: " + homePackageName);
                return;
            }
            LOG.d("User " + getUserId() + " unlocked and real home (" + homePackageName + ") found; let's go!");
            finishFallbackHome();
        }
    }

    private boolean isUserSetup() {
        ContentResolver cr = getApplicationContext().getContentResolver();
        return (Settings.Global.getInt(cr, "device_provisioned", 0) == 0 || Settings.Secure.getInt(cr, "user_setup_complete", 0) == 0) ? false : true;
    }

    private void finishFallbackHome() {
        ((PowerManager) getSystemService(PowerManager.class)).userActivity(SystemClock.uptimeMillis(), false);
        finishAndRemoveTask();
        this.mFinished = true;
    }

    @Override // android.app.Activity
    public void finish() {
        super.finish();
        overridePendingTransition(0, 0);
    }
}
