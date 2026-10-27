package io.branch.referral;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.text.TextUtils;
import com.amazonaws.mobile.client.internal.oauth2.OAuth2Client;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;

/* JADX INFO: loaded from: classes7.dex */
class BranchStrongMatchHelper {
    private static int StrongMatchUrlHitDelay = 750;
    private static BranchStrongMatchHelper branchStrongMatchHelper_;
    private Class<?> CustomTabsCallbackClass;
    private Class<?> CustomTabsClientClass;
    private Class<?> CustomTabsSessionClass;
    private Class<?> ICustomTabsServiceClass;
    private boolean isCustomTabsAvailable_;
    private final Handler timeOutHandler_;
    private Object mClient_ = null;
    private boolean isStrongMatchUrlLaunched = false;

    interface StrongMatchCheckEvents {
        void onStrongMatchCheckFinished();
    }

    private BranchStrongMatchHelper() {
        this.isCustomTabsAvailable_ = true;
        try {
            this.CustomTabsClientClass = Class.forName("android.support.customtabs.CustomTabsClient");
            this.CustomTabsCallbackClass = Class.forName("android.support.customtabs.CustomTabsCallback");
            this.CustomTabsSessionClass = Class.forName("android.support.customtabs.CustomTabsSession");
            this.ICustomTabsServiceClass = Class.forName("android.support.customtabs.ICustomTabsService");
        } catch (Throwable unused) {
            this.isCustomTabsAvailable_ = false;
        }
        this.timeOutHandler_ = new Handler();
    }

    public static BranchStrongMatchHelper getInstance() {
        if (branchStrongMatchHelper_ == null) {
            branchStrongMatchHelper_ = new BranchStrongMatchHelper();
        }
        return branchStrongMatchHelper_;
    }

    void setStrongMatchUrlHitDelay(int i) {
        StrongMatchUrlHitDelay = i;
    }

    void checkForStrongMatch(Context context, String str, DeviceInfo deviceInfo, final PrefHelper prefHelper, final StrongMatchCheckEvents strongMatchCheckEvents) {
        this.isStrongMatchUrlLaunched = false;
        if (System.currentTimeMillis() - prefHelper.getLastStrongMatchTime() < 2592000000L) {
            updateStrongMatchCheckFinished(strongMatchCheckEvents, this.isStrongMatchUrlLaunched);
            return;
        }
        if (!this.isCustomTabsAvailable_) {
            updateStrongMatchCheckFinished(strongMatchCheckEvents, this.isStrongMatchUrlLaunched);
            return;
        }
        try {
            if (deviceInfo.getHardwareID() != null) {
                final Uri uriBuildStrongMatchUrl = buildStrongMatchUrl(str, deviceInfo, prefHelper, context);
                if (uriBuildStrongMatchUrl != null) {
                    this.timeOutHandler_.postDelayed(new Runnable() { // from class: io.branch.referral.BranchStrongMatchHelper.1
                        @Override // java.lang.Runnable
                        public void run() {
                            BranchStrongMatchHelper branchStrongMatchHelper = BranchStrongMatchHelper.this;
                            branchStrongMatchHelper.updateStrongMatchCheckFinished(strongMatchCheckEvents, branchStrongMatchHelper.isStrongMatchUrlLaunched);
                        }
                    }, 500L);
                    final Method method = this.CustomTabsClientClass.getMethod("warmup", Long.TYPE);
                    final Method method2 = this.CustomTabsClientClass.getMethod("newSession", this.CustomTabsCallbackClass);
                    final Method method3 = this.CustomTabsSessionClass.getMethod("mayLaunchUrl", Uri.class, Bundle.class, List.class);
                    Intent intent = new Intent("android.support.customtabs.action.CustomTabsService");
                    intent.setPackage(OAuth2Client.CUSTOM_TABS_PACKAGE_NAME);
                    context.bindService(intent, new MockCustomTabServiceConnection() { // from class: io.branch.referral.BranchStrongMatchHelper.2
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super();
                        }

                        @Override // io.branch.referral.BranchStrongMatchHelper.MockCustomTabServiceConnection
                        public void onCustomTabsServiceConnected(ComponentName componentName, Object obj) {
                            BranchStrongMatchHelper branchStrongMatchHelper = BranchStrongMatchHelper.this;
                            branchStrongMatchHelper.mClient_ = branchStrongMatchHelper.CustomTabsClientClass.cast(obj);
                            if (BranchStrongMatchHelper.this.mClient_ != null) {
                                try {
                                    method.invoke(BranchStrongMatchHelper.this.mClient_, 0);
                                    Object objInvoke = method2.invoke(BranchStrongMatchHelper.this.mClient_, null);
                                    if (objInvoke != null) {
                                        PrefHelper.Debug("Strong match request " + uriBuildStrongMatchUrl);
                                        method3.invoke(objInvoke, uriBuildStrongMatchUrl, null, null);
                                        prefHelper.saveLastStrongMatchTime(System.currentTimeMillis());
                                        BranchStrongMatchHelper.this.isStrongMatchUrlLaunched = true;
                                    }
                                } catch (Throwable unused) {
                                    BranchStrongMatchHelper.this.mClient_ = null;
                                    BranchStrongMatchHelper branchStrongMatchHelper2 = BranchStrongMatchHelper.this;
                                    branchStrongMatchHelper2.updateStrongMatchCheckFinished(strongMatchCheckEvents, branchStrongMatchHelper2.isStrongMatchUrlLaunched);
                                }
                            }
                        }

                        @Override // android.content.ServiceConnection
                        public void onServiceDisconnected(ComponentName componentName) {
                            BranchStrongMatchHelper.this.mClient_ = null;
                            BranchStrongMatchHelper branchStrongMatchHelper = BranchStrongMatchHelper.this;
                            branchStrongMatchHelper.updateStrongMatchCheckFinished(strongMatchCheckEvents, branchStrongMatchHelper.isStrongMatchUrlLaunched);
                        }
                    }, 33);
                } else {
                    updateStrongMatchCheckFinished(strongMatchCheckEvents, this.isStrongMatchUrlLaunched);
                }
            } else {
                updateStrongMatchCheckFinished(strongMatchCheckEvents, this.isStrongMatchUrlLaunched);
                PrefHelper.Debug("Cannot use cookie-based matching since device id is not available");
            }
        } catch (Throwable unused) {
            updateStrongMatchCheckFinished(strongMatchCheckEvents, this.isStrongMatchUrlLaunched);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateStrongMatchCheckFinished(final StrongMatchCheckEvents strongMatchCheckEvents, boolean z) {
        if (strongMatchCheckEvents != null) {
            if (z) {
                new Handler().postDelayed(new Runnable() { // from class: io.branch.referral.BranchStrongMatchHelper.3
                    @Override // java.lang.Runnable
                    public void run() {
                        strongMatchCheckEvents.onStrongMatchCheckFinished();
                    }
                }, StrongMatchUrlHitDelay);
            } else {
                strongMatchCheckEvents.onStrongMatchCheckFinished();
            }
        }
    }

    private Uri buildStrongMatchUrl(String str, DeviceInfo deviceInfo, PrefHelper prefHelper, Context context) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String str2 = ("https://" + str + "/_strong_match?os=" + deviceInfo.getOsName()) + "&" + Defines$Jsonkey.HardwareID.getKey() + "=" + deviceInfo.getHardwareID();
        String str3 = str2 + "&" + Defines$Jsonkey.HardwareIDType.getKey() + "=" + (deviceInfo.getHardwareID().isReal() ? Defines$Jsonkey.HardwareIDTypeVendor : Defines$Jsonkey.HardwareIDTypeRandom).getKey();
        String aid = deviceInfo.getSystemObserver().getAID();
        if (aid != null && !BranchUtil.checkTestMode(context)) {
            str3 = str3 + "&" + Defines$Jsonkey.GoogleAdvertisingID.getKey() + "=" + aid;
        }
        if (!prefHelper.getDeviceFingerPrintID().equals("bnc_no_value")) {
            str3 = str3 + "&" + Defines$Jsonkey.DeviceFingerprintID.getKey() + "=" + prefHelper.getDeviceFingerPrintID();
        }
        if (!deviceInfo.getAppVersion().equals("bnc_no_value")) {
            str3 = str3 + "&" + Defines$Jsonkey.AppVersion.getKey() + "=" + deviceInfo.getAppVersion();
        }
        if (!prefHelper.getBranchKey().equals("bnc_no_value")) {
            str3 = str3 + "&" + Defines$Jsonkey.BranchKey.getKey() + "=" + prefHelper.getBranchKey();
        }
        return Uri.parse(str3 + "&sdk=android4.1.1");
    }

    private abstract class MockCustomTabServiceConnection implements ServiceConnection {
        public abstract void onCustomTabsServiceConnected(ComponentName componentName, Object obj);

        MockCustomTabServiceConnection() {
        }

        @Override // android.content.ServiceConnection
        public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                Constructor declaredConstructor = BranchStrongMatchHelper.this.CustomTabsClientClass.getDeclaredConstructor(BranchStrongMatchHelper.this.ICustomTabsServiceClass, ComponentName.class);
                declaredConstructor.setAccessible(true);
                onCustomTabsServiceConnected(componentName, declaredConstructor.newInstance(Class.forName("android.support.customtabs.ICustomTabsService$Stub").getMethod("asInterface", IBinder.class).invoke(null, iBinder), componentName));
            } catch (Throwable unused) {
                onCustomTabsServiceConnected(null, null);
            }
        }
    }
}
