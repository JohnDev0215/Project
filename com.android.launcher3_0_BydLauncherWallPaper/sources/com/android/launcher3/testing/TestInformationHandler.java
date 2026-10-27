package com.android.launcher3.testing;

import android.content.Context;
import android.os.Bundle;
import com.android.launcher3.DeviceProfile;
import com.android.launcher3.InvariantDeviceProfile;
import com.android.launcher3.Launcher;
import com.android.launcher3.LauncherAppState;
import com.android.launcher3.LauncherState;
import com.android.launcher3.MainThreadExecutor;
import com.android.launcher3.R;
import com.android.launcher3.util.ResourceBasedOverride;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

/* JADX INFO: loaded from: classes4.dex */
public class TestInformationHandler implements ResourceBasedOverride {
    protected Context mContext;
    protected DeviceProfile mDeviceProfile;
    protected Launcher mLauncher;
    protected LauncherAppState mLauncherAppState;

    public static TestInformationHandler newInstance(Context context) {
        return (TestInformationHandler) ResourceBasedOverride.Overrides.getObject(TestInformationHandler.class, context, R.string.test_information_handler_class);
    }

    public void init(Context context) {
        this.mContext = context;
        this.mDeviceProfile = InvariantDeviceProfile.INSTANCE.lambda$get$0$MainThreadInitializedObject(context).getDeviceProfile(context);
        LauncherAppState instanceNoCreate = LauncherAppState.getInstanceNoCreate();
        this.mLauncherAppState = instanceNoCreate;
        this.mLauncher = instanceNoCreate != null ? (Launcher) instanceNoCreate.getModel().getCallback() : null;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public Bundle call(String method) {
        Bundle bundle;
        bundle = new Bundle();
        method.hashCode();
        switch (method) {
            case "disable-debug-tracing":
                TestProtocol.sDebugTracing = false;
                return bundle;
            case "app-list-freeze-flags":
                try {
                    bundle.putInt("response", ((Integer) new MainThreadExecutor().submit(new Callable() { // from class: com.android.launcher3.testing.-$$Lambda$TestInformationHandler$j_rxUk-9rbeR2BLps6of6FeahrI
                        @Override // java.util.concurrent.Callable
                        public final Object call() {
                            return this.f$0.lambda$call$2$TestInformationHandler();
                        }
                    }).get()).intValue());
                    return bundle;
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (ExecutionException e2) {
                    throw new RuntimeException(e2);
                }
            case "all-apps-to-overview-swipe-height":
                if (this.mLauncher == null) {
                    return null;
                }
                bundle.putInt("response", (int) (this.mLauncher.getAllAppsController().getShiftRange() * (LauncherState.OVERVIEW.getVerticalProgress(this.mLauncher) - LauncherState.ALL_APPS.getVerticalProgress(this.mLauncher))));
                return bundle;
            case "enable-debug-tracing":
                TestProtocol.sDebugTracing = true;
                return bundle;
            case "unfreeze-app-list":
                new MainThreadExecutor().execute(new Runnable() { // from class: com.android.launcher3.testing.-$$Lambda$TestInformationHandler$mtlPTuPGN7ok7ESjBT5m6-nI4mA
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$call$1$TestInformationHandler();
                    }
                });
                return bundle;
            case "home-to-all-apps-swipe-height":
                if (this.mLauncher == null) {
                    return null;
                }
                bundle.putInt("response", (int) (this.mLauncher.getAllAppsController().getShiftRange() * (LauncherState.NORMAL.getVerticalProgress(this.mLauncher) - LauncherState.ALL_APPS.getVerticalProgress(this.mLauncher))));
                return bundle;
            case "freeze-app-list":
                new MainThreadExecutor().execute(new Runnable() { // from class: com.android.launcher3.testing.-$$Lambda$TestInformationHandler$lp9_jo5M_q37_VzTBpBhVNh-Fck
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$call$0$TestInformationHandler();
                    }
                });
                return bundle;
            default:
                return bundle;
        }
    }

    public /* synthetic */ void lambda$call$0$TestInformationHandler() {
        this.mLauncher.getAppsView().getAppsStore().enableDeferUpdates(4);
    }

    public /* synthetic */ void lambda$call$1$TestInformationHandler() {
        this.mLauncher.getAppsView().getAppsStore().disableDeferUpdates(4);
    }

    public /* synthetic */ Integer lambda$call$2$TestInformationHandler() throws Exception {
        return Integer.valueOf(this.mLauncher.getAppsView().getAppsStore().getDeferUpdatesFlags());
    }
}
