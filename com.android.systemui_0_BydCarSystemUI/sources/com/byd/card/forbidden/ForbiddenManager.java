package com.byd.card.forbidden;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.hardware.bydauto.gearbox.AbsBYDAutoGearboxListener;
import android.hardware.bydauto.gearbox.BYDAutoGearboxDevice;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import com.android.systemui.Dependency;
import com.android.systemui.R;
import com.android.systemui.SysUiServiceProvider;
import com.android.systemui.SystemUIApplication;
import com.android.systemui.shared.system.ActivityManagerWrapper;
import com.android.systemui.stackdivider.Divider;
import com.android.systemui.stackdivider.DividerUtils;
import com.android.systemui.stackdivider.view.SplitScreenPrimaryActivity;
import com.android.systemui.stackdivider.view.SplitScreenSecondaryActivity;
import com.byd.card.bean.AppInfo;
import com.byd.card.bean.AppOrShortcutItem;
import com.byd.card.bean.Constant;
import com.byd.card.bean.MessageEvent;
import com.byd.card.bean.ShortcutInfo;
import com.byd.card.db.CardDatabase;
import com.byd.card.floatcard.Executors;
import com.byd.card.forbidden.BaseForbiddenActivity;
import com.byd.card.utils.AppUtils;
import com.byd.card.view.activity.ActivityCollector;
import com.byd.card.view.activity.MoreAppActivity;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: loaded from: classes.dex */
public class ForbiddenManager {
    public static final float FORBIDDEN_ICON_ALPHA = 0.4f;
    private static final String TAG = "ForbiddenManager";
    private static List<String> sForbiddenAppOnNonP;
    private static volatile ForbiddenManager sForbiddenManager;
    private static boolean sIsPGear;
    private Context mContext = SystemUIApplication.getInstance().getApplicationContext();
    private TopActivityHandler mHandler;

    public static ForbiddenManager getInstance() {
        if (sForbiddenManager == null) {
            synchronized (ForbiddenManager.class) {
                if (sForbiddenManager == null) {
                    sForbiddenManager = new ForbiddenManager();
                }
            }
        }
        return sForbiddenManager;
    }

    private ForbiddenManager() {
    }

    public static boolean isPGear() {
        return sIsPGear;
    }

    public void init() {
        this.mHandler = new TopActivityHandler();
        registerGearboxListener();
        sIsPGear = AppUtils.isP(this.mContext);
    }

    public void onTopActivityChanged() {
        if (getForbiddenAppListOnNonP().size() <= 0) {
            Log.d(TAG, "onTopActivityChanged, no forbidden app installed!");
        } else {
            this.mHandler.removeMessages(8);
            this.mHandler.sendEmptyMessageDelayed(8, 20L);
        }
    }

    public void checkSplitWhenStackChange(ComponentName currentTop, AppInfo currentTopExcludeForbidden, boolean isPrimary) {
        if (sIsPGear || getForbiddenAppListOnNonP().size() <= 0) {
            Log.d(TAG, "checkSplitWhenStackChange not handle! sIsPGear = " + sIsPGear + " forbidden size = " + getForbiddenAppListOnNonP().size());
            return;
        }
        if (currentTopExcludeForbidden == null || !getForbiddenAppListOnNonP().contains(currentTopExcludeForbidden.getPackageName())) {
            return;
        }
        if (currentTop != null) {
            if (TextUtils.equals(currentTop.getClassName(), (isPrimary ? ForbiddenSplitPrimaryActivity.class : ForbiddenSplitSecondaryActivity.class).getName())) {
                return;
            }
        }
        Intent intent = new Intent();
        if (isPrimary) {
            intent.setClass(this.mContext, ForbiddenSplitPrimaryActivity.class);
        } else {
            intent.setClass(this.mContext, ForbiddenSplitSecondaryActivity.class);
        }
        Bundle bundle = DividerUtils.getInstance().getActivityOptions(isPrimary).toBundle();
        intent.setFlags(268435456);
        SystemUIApplication.getInstance().startActivity(intent, bundle);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkUpdateForbidden(final int gear) {
        ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).post(new Runnable() { // from class: com.byd.card.forbidden.-$$Lambda$ForbiddenManager$NW5sZb5GvoPSoJZITEz6sgzZAyQ
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$checkUpdateForbidden$0$ForbiddenManager(gear);
            }
        });
    }

    public /* synthetic */ void lambda$checkUpdateForbidden$0$ForbiddenManager(int i) {
        boolean z = i == 1;
        if (sIsPGear != z) {
            sIsPGear = z;
            if (getForbiddenAppListOnNonP().size() <= 0) {
                Log.d(TAG, "checkUpdateForbidden no forbidden app installed!");
                return;
            }
            EventBus.getDefault().post(new MessageEvent(Constant.EventBusMessage.CHECK_CARD_FORBIDDEN));
            checkWhenGearChange(z);
            MoreAppActivity moreAppActivity = (MoreAppActivity) ActivityCollector.getActivity(MoreAppActivity.class);
            if (moreAppActivity != null && !moreAppActivity.isFinishing()) {
                moreAppActivity.updateForbidden(z);
            }
            SplitScreenPrimaryActivity splitScreenPrimaryActivity = (SplitScreenPrimaryActivity) ActivityCollector.getActivity(SplitScreenPrimaryActivity.class);
            if (splitScreenPrimaryActivity != null && !splitScreenPrimaryActivity.isFinishing()) {
                splitScreenPrimaryActivity.updateForbidden(z);
            }
            SplitScreenSecondaryActivity splitScreenSecondaryActivity = (SplitScreenSecondaryActivity) ActivityCollector.getActivity(SplitScreenSecondaryActivity.class);
            if (splitScreenSecondaryActivity == null || splitScreenSecondaryActivity.isFinishing()) {
                return;
            }
            splitScreenSecondaryActivity.updateForbidden(z);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkWhenTopChange(boolean isPGear) {
        if (isPGear || ((Divider) SysUiServiceProvider.getComponent(this.mContext, Divider.class)).isSplitActive()) {
            return;
        }
        checkFullWhenNonP();
    }

    private void checkWhenGearChange(boolean isPGear) {
        if (isPGear) {
            EventBus.getDefault().post(new BaseForbiddenActivity.ForbiddenEvent(0));
        } else if (((Divider) SysUiServiceProvider.getComponent(this.mContext, Divider.class)).isSplitActive()) {
            checkSplitWhenNonP(DividerUtils.getInstance().getPrimaryAppInfo(), true);
            checkSplitWhenNonP(DividerUtils.getInstance().getSecondaryAppInfo(), false);
        } else {
            checkFullWhenNonP();
        }
    }

    private void checkSplitWhenNonP(AppInfo currentTop, boolean isPrimary) {
        if (currentTop == null || !getForbiddenAppListOnNonP().contains(currentTop.getPackageName())) {
            return;
        }
        Intent intent = new Intent();
        if (isPrimary) {
            intent.setClass(this.mContext, ForbiddenSplitPrimaryActivity.class);
        } else {
            intent.setClass(this.mContext, ForbiddenSplitSecondaryActivity.class);
        }
        Bundle bundle = DividerUtils.getInstance().getActivityOptions(isPrimary).toBundle();
        intent.setFlags(268435456);
        SystemUIApplication.getInstance().startActivity(intent, bundle);
    }

    private void checkFullWhenNonP() {
        boolean[] zArr = new boolean[1];
        ActivityManager.RunningTaskInfo runningTaskExceptForbidden = ActivityManagerWrapper.getInstance().getRunningTaskExceptForbidden(zArr);
        if (runningTaskExceptForbidden.topActivity == null) {
            return;
        }
        if (getForbiddenAppListOnNonP().contains(runningTaskExceptForbidden.topActivity.getPackageName())) {
            if (zArr[0]) {
                return;
            }
            Intent intent = new Intent();
            intent.setClass(this.mContext, runningTaskExceptForbidden.supportsSplitScreenMultiWindow ? ForbiddenSplitPrimaryActivity.class : ForbiddenFullActivity.class);
            intent.setFlags(268435456);
            SystemUIApplication.getInstance().startActivity(intent);
            return;
        }
        ForbiddenFullActivity forbiddenFullActivity = (ForbiddenFullActivity) ActivityCollector.getActivity(ForbiddenFullActivity.class);
        if (forbiddenFullActivity != null) {
            forbiddenFullActivity.finish();
        }
    }

    private void registerGearboxListener() {
        BYDAutoGearboxDevice.getInstance(this.mContext).registerListener(new AbsBYDAutoGearboxListener() { // from class: com.byd.card.forbidden.ForbiddenManager.1
            @Override // android.hardware.bydauto.gearbox.AbsBYDAutoGearboxListener
            public void onGearChanged(int value) {
                boolean zIsCarEV = AppUtils.isCarEV();
                Log.d("AbsBYDAutoGearboxListener", "onGearChanged value = " + value + ", isCarEv = " + zIsCarEV + ", " + (zIsCarEV ? "handle!" : "skip!"));
                if (zIsCarEV) {
                    ForbiddenManager.this.checkUpdateForbidden(value);
                }
            }

            @Override // android.hardware.bydauto.gearbox.AbsBYDAutoGearboxListener
            public void onGearboxAutoModeLevelChanged(int level) {
                boolean zIsCarEV = AppUtils.isCarEV();
                Log.d("AbsBYDAutoGearboxListener", "onGearboxAutoModeLevelChanged level = " + level + ", isCarEv = " + zIsCarEV + ", " + (zIsCarEV ? "skip!" : "handle!"));
                if (zIsCarEV) {
                    return;
                }
                ForbiddenManager.this.checkUpdateForbidden(level);
            }

            @Override // android.hardware.bydauto.gearbox.AbsBYDAutoGearboxListener
            public void onGearboxAutoModeTypeChanged(int level) {
                super.onGearboxAutoModeTypeChanged(level);
            }
        });
    }

    public static void updateForbidden(AppOrShortcutItem item, boolean isPGear) {
        if (item == null) {
            return;
        }
        List<String> forbiddenAppListOnNonP = getForbiddenAppListOnNonP();
        if (forbiddenAppListOnNonP.size() <= 0) {
            Log.d(TAG, "updateForbidden, no forbidden app installed!");
        } else if (isPGear) {
            item.setForbidden(false);
        } else if (forbiddenAppListOnNonP.contains(item.getItemIdentify())) {
            item.setForbidden(true);
        }
    }

    public static void updateForbidden(List<AppOrShortcutItem> infoList, boolean isPGear) {
        if (infoList == null || infoList.size() == 0) {
            return;
        }
        List<String> forbiddenAppListOnNonP = getForbiddenAppListOnNonP();
        if (forbiddenAppListOnNonP.size() <= 0) {
            Log.d(TAG, "updateForbidden, no forbidden app installed!");
            return;
        }
        if (isPGear) {
            for (AppOrShortcutItem appOrShortcutItem : infoList) {
                if (appOrShortcutItem == null) {
                    Log.e(TAG, "updateForbiddenApp, info is null!");
                } else {
                    appOrShortcutItem.setForbidden(false);
                }
            }
            return;
        }
        for (AppOrShortcutItem appOrShortcutItem2 : infoList) {
            if (forbiddenAppListOnNonP.contains(appOrShortcutItem2.getItemIdentify())) {
                appOrShortcutItem2.setForbidden(true);
            }
        }
    }

    public static List<String> getForbiddenAppListOnNonP() {
        List<String> list;
        synchronized (ForbiddenManager.class) {
            if (sForbiddenAppOnNonP == null) {
                sForbiddenAppOnNonP = new ArrayList();
                Context applicationContext = SystemUIApplication.getInstance().getApplicationContext();
                for (String str : applicationContext.getResources().getStringArray(R.array.forbidden_package_on_nonGearP)) {
                    if (AppUtils.isInstalled(applicationContext, str)) {
                        sForbiddenAppOnNonP.add(str);
                    }
                }
                Executors.MODEL_EXECUTOR.execute(new Runnable() { // from class: com.byd.card.forbidden.ForbiddenManager.2
                    @Override // java.lang.Runnable
                    public void run() {
                        List<ShortcutInfo> shortcutByPGearFlag = CardDatabase.getInstance().getShortcutInfoDao().getShortcutByPGearFlag(1);
                        if (shortcutByPGearFlag == null || shortcutByPGearFlag.size() <= 0) {
                            return;
                        }
                        synchronized (ForbiddenManager.class) {
                            Iterator<ShortcutInfo> it = shortcutByPGearFlag.iterator();
                            while (it.hasNext()) {
                                ForbiddenManager.sForbiddenAppOnNonP.add(it.next().getItemIdentify());
                            }
                            Log.d(ForbiddenManager.TAG, "getForbiddenAppListOnNonP CHECK_CARD_FORBIDDEN sForbiddenAppOnNonP = " + ForbiddenManager.sForbiddenAppOnNonP);
                            EventBus.getDefault().post(new MessageEvent(Constant.EventBusMessage.CHECK_CARD_FORBIDDEN));
                        }
                    }
                });
                Log.d(TAG, "getForbiddenAppListOnNonP sForbiddenAppOnNonP = " + sForbiddenAppOnNonP);
            }
            list = sForbiddenAppOnNonP;
        }
        return list;
    }

    public static void addForbiddenAppListOnNonP(String addPackage, int type) {
        synchronized (ForbiddenManager.class) {
            Log.d(TAG, "addForbiddenAppListOnNonP addPackage = " + addPackage);
            List<String> list = sForbiddenAppOnNonP;
            if (list == null) {
                return;
            }
            if (list.contains(addPackage)) {
                return;
            }
            if (type == 3) {
                sForbiddenAppOnNonP.add(addPackage);
            } else if (type == 1) {
                for (String str : SystemUIApplication.getInstance().getApplicationContext().getResources().getStringArray(R.array.forbidden_package_on_nonGearP)) {
                    if (TextUtils.equals(addPackage, str)) {
                        Log.d(TAG, "addForbiddenAppListOnNonP add success!");
                        sForbiddenAppOnNonP.add(addPackage);
                        return;
                    }
                }
            }
        }
    }

    public static void removeForbiddenAppListOnNonP(String removePackage) {
        synchronized (ForbiddenManager.class) {
            Log.d(TAG, "removeForbiddenAppListOnNonP removePackage = " + removePackage);
            List<String> list = sForbiddenAppOnNonP;
            if (list == null) {
                return;
            }
            list.remove(removePackage);
        }
    }

    private static class TopActivityHandler extends Handler {
        private TopActivityHandler() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            if (msg.what == 8) {
                ForbiddenManager.getInstance().checkWhenTopChange(ForbiddenManager.sIsPGear);
            }
        }
    }
}
