package com.android.phone;

import android.app.AppOpsManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.PersistableBundle;
import android.os.RemoteException;
import android.os.ResultReceiver;
import android.os.UserHandle;
import android.preference.PreferenceManager;
import android.service.carrier.CarrierIdentifier;
import android.service.carrier.ICarrierService;
import android.telephony.CarrierConfigManager;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyFrameworkInitializer;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.ArraySet;
import android.util.Log;
import com.android.internal.telephony.ICarrierConfigLoader;
import com.android.internal.telephony.IndentingPrintWriter;
import com.android.internal.telephony.LocalLog;
import com.android.internal.telephony.Phone;
import com.android.internal.telephony.PhoneFactory;
import com.android.internal.telephony.SubscriptionInfoUpdater;
import com.android.internal.telephony.TelephonyPermissions;
import com.android.internal.telephony.util.ArrayUtils;
import com.android.services.telephony.RadioOnStateListener;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class CarrierConfigLoader extends ICarrierConfigLoader.Stub {
    private static CarrierConfigLoader sInstance;
    private final BroadcastReceiver mBootReceiver;
    private final LocalLog mCarrierConfigLoadingLog = new LocalLog(100);
    private PersistableBundle[] mConfigFromCarrierApp;
    private PersistableBundle[] mConfigFromDefaultApp;
    private Context mContext;
    private boolean[] mFromSystemUnlocked;
    private final Handler mHandler;
    private boolean[] mHasSentConfigChange;
    private PersistableBundle mNoSimConfig;
    private PersistableBundle[] mOverrideConfigs;
    private final BroadcastReceiver mPackageReceiver;
    private PersistableBundle[] mPersistentOverrideConfigs;
    private final String mPlatformCarrierConfigPackage;
    private boolean[] mServiceBound;
    private boolean[] mServiceBoundForNoSimConfig;
    private CarrierServiceConnection[] mServiceConnection;
    private CarrierServiceConnection[] mServiceConnectionForNoSimConfig;
    private final SubscriptionInfoUpdater mSubscriptionInfoUpdater;

    private class ConfigHandler extends Handler {
        private ConfigHandler() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            final int i = message.arg1;
            CarrierConfigLoader.this.logdWithLocalLog("mHandler: " + message.what + " phoneId: " + i);
            if (SubscriptionManager.isValidPhoneId(i) || message.what == 17) {
                int i2 = message.what;
                if (i2 == 0) {
                    CarrierConfigLoader.this.clearConfigForPhone(i, true);
                    return;
                }
                int i3 = 0;
                switch (i2) {
                    case 3:
                        removeMessages(10);
                        final CarrierServiceConnection carrierServiceConnection = (CarrierServiceConnection) message.obj;
                        if (CarrierConfigLoader.this.mServiceConnection[i] == carrierServiceConnection && carrierServiceConnection.service != null) {
                            final CarrierIdentifier carrierIdentifierForPhoneId = CarrierConfigLoader.this.getCarrierIdentifierForPhoneId(i);
                            try {
                                ICarrierService.Stub.asInterface(carrierServiceConnection.service).getCarrierConfig(carrierIdentifierForPhoneId, new ResultReceiver(this) { // from class: com.android.phone.CarrierConfigLoader.ConfigHandler.1
                                    @Override // android.os.ResultReceiver
                                    public void onReceiveResult(int i4, Bundle bundle) {
                                        CarrierConfigLoader carrierConfigLoader = CarrierConfigLoader.this;
                                        carrierConfigLoader.unbindIfBound(carrierConfigLoader.mContext, carrierServiceConnection, i);
                                        if (CarrierConfigLoader.this.mServiceConnection[i] != carrierServiceConnection) {
                                            CarrierConfigLoader.this.loge("Received response for stale request.");
                                            return;
                                        }
                                        ConfigHandler.this.removeMessages(14);
                                        if (i4 == 1 || bundle == null) {
                                            CarrierConfigLoader.this.loge("Failed to get carrier config");
                                            CarrierConfigLoader.this.notifySubscriptionInfoUpdater(i);
                                            return;
                                        }
                                        PersistableBundle persistableBundle = (PersistableBundle) bundle.getParcelable("config_bundle");
                                        CarrierConfigLoader carrierConfigLoader2 = CarrierConfigLoader.this;
                                        carrierConfigLoader2.saveConfigToXml(carrierConfigLoader2.mPlatformCarrierConfigPackage, "", i, carrierIdentifierForPhoneId, persistableBundle);
                                        PersistableBundle[] persistableBundleArr = CarrierConfigLoader.this.mConfigFromDefaultApp;
                                        int i5 = i;
                                        persistableBundleArr[i5] = persistableBundle;
                                        ConfigHandler configHandler = ConfigHandler.this;
                                        configHandler.sendMessage(configHandler.obtainMessage(5, i5, -1));
                                    }
                                });
                                CarrierConfigLoader.this.logdWithLocalLog("Fetch config for default app: " + CarrierConfigLoader.this.mPlatformCarrierConfigPackage + " carrierid: " + carrierIdentifierForPhoneId.toString());
                                sendMessageDelayed(obtainMessage(14, i, -1), 30000L);
                                return;
                            } catch (RemoteException e) {
                                CarrierConfigLoader.this.loge("Failed to get carrier config from default app: " + CarrierConfigLoader.this.mPlatformCarrierConfigPackage + " err: " + e.toString());
                                CarrierConfigLoader carrierConfigLoader = CarrierConfigLoader.this;
                                carrierConfigLoader.unbindIfBound(carrierConfigLoader.mContext, carrierServiceConnection, i);
                                return;
                            }
                        }
                        CarrierConfigLoader carrierConfigLoader2 = CarrierConfigLoader.this;
                        carrierConfigLoader2.unbindIfBound(carrierConfigLoader2.mContext, carrierServiceConnection, i);
                        return;
                    case RadioOnStateListener.MSG_RADIO_ON /* 4 */:
                        removeMessages(11);
                        final CarrierServiceConnection carrierServiceConnection2 = (CarrierServiceConnection) message.obj;
                        if (CarrierConfigLoader.this.mServiceConnection[i] == carrierServiceConnection2 && carrierServiceConnection2.service != null) {
                            final CarrierIdentifier carrierIdentifierForPhoneId2 = CarrierConfigLoader.this.getCarrierIdentifierForPhoneId(i);
                            try {
                                ICarrierService.Stub.asInterface(carrierServiceConnection2.service).getCarrierConfig(carrierIdentifierForPhoneId2, new ResultReceiver(this) { // from class: com.android.phone.CarrierConfigLoader.ConfigHandler.2
                                    @Override // android.os.ResultReceiver
                                    public void onReceiveResult(int i4, Bundle bundle) {
                                        CarrierConfigLoader carrierConfigLoader3 = CarrierConfigLoader.this;
                                        carrierConfigLoader3.unbindIfBound(carrierConfigLoader3.mContext, carrierServiceConnection2, i);
                                        if (CarrierConfigLoader.this.mServiceConnection[i] != carrierServiceConnection2) {
                                            CarrierConfigLoader.this.loge("Received response for stale request.");
                                            return;
                                        }
                                        ConfigHandler.this.removeMessages(15);
                                        if (i4 == 1 || bundle == null) {
                                            CarrierConfigLoader.this.loge("Failed to get carrier config from carrier app: " + CarrierConfigLoader.this.getCarrierPackageForPhoneId(i));
                                            CarrierConfigLoader.this.broadcastConfigChangedIntent(i);
                                            CarrierConfigLoader.this.notifySubscriptionInfoUpdater(i);
                                            return;
                                        }
                                        PersistableBundle persistableBundle = (PersistableBundle) bundle.getParcelable("config_bundle");
                                        CarrierConfigLoader carrierConfigLoader4 = CarrierConfigLoader.this;
                                        carrierConfigLoader4.saveConfigToXml(carrierConfigLoader4.getCarrierPackageForPhoneId(i), "", i, carrierIdentifierForPhoneId2, persistableBundle);
                                        PersistableBundle[] persistableBundleArr = CarrierConfigLoader.this.mConfigFromCarrierApp;
                                        int i5 = i;
                                        persistableBundleArr[i5] = persistableBundle;
                                        ConfigHandler configHandler = ConfigHandler.this;
                                        configHandler.sendMessage(configHandler.obtainMessage(6, i5, -1));
                                    }
                                });
                                CarrierConfigLoader.this.logdWithLocalLog("Fetch config for carrier app: " + CarrierConfigLoader.this.getCarrierPackageForPhoneId(i) + " carrierid: " + carrierIdentifierForPhoneId2.toString());
                                sendMessageDelayed(obtainMessage(15, i, -1), 30000L);
                                return;
                            } catch (RemoteException e2) {
                                CarrierConfigLoader.this.loge("Failed to get carrier config: " + e2.toString());
                                CarrierConfigLoader carrierConfigLoader3 = CarrierConfigLoader.this;
                                carrierConfigLoader3.unbindIfBound(carrierConfigLoader3.mContext, carrierServiceConnection2, i);
                                return;
                            }
                        }
                        CarrierConfigLoader carrierConfigLoader4 = CarrierConfigLoader.this;
                        carrierConfigLoader4.unbindIfBound(carrierConfigLoader4.mContext, carrierServiceConnection2, i);
                        return;
                    case 5:
                        if (message.getData().getBoolean("loaded_from_xml", false) || CarrierConfigLoader.this.mServiceConnection[i] != null) {
                            String carrierPackageForPhoneId = CarrierConfigLoader.this.getCarrierPackageForPhoneId(i);
                            if (carrierPackageForPhoneId == null) {
                                CarrierConfigLoader.this.notifySubscriptionInfoUpdater(i);
                                return;
                            }
                            CarrierConfigLoader.this.logd("Found carrier config app: " + carrierPackageForPhoneId);
                            sendMessage(obtainMessage(8, i, -1));
                            return;
                        }
                        return;
                    case 6:
                        if (message.getData().getBoolean("loaded_from_xml", false) || CarrierConfigLoader.this.mServiceConnection[i] != null) {
                            CarrierConfigLoader.this.notifySubscriptionInfoUpdater(i);
                            return;
                        }
                        return;
                    case 7:
                        CarrierConfigLoader carrierConfigLoader5 = CarrierConfigLoader.this;
                        PersistableBundle persistableBundleRestoreConfigFromXml = carrierConfigLoader5.restoreConfigFromXml(carrierConfigLoader5.mPlatformCarrierConfigPackage, "-override", i);
                        if (persistableBundleRestoreConfigFromXml != null) {
                            CarrierConfigLoader.this.logd("Loaded persistent override config from XML. package=" + CarrierConfigLoader.this.mPlatformCarrierConfigPackage + " phoneId=" + i);
                            CarrierConfigLoader.this.mPersistentOverrideConfigs[i] = persistableBundleRestoreConfigFromXml;
                        }
                        CarrierConfigLoader carrierConfigLoader6 = CarrierConfigLoader.this;
                        PersistableBundle persistableBundleRestoreConfigFromXml2 = carrierConfigLoader6.restoreConfigFromXml(carrierConfigLoader6.mPlatformCarrierConfigPackage, "", i);
                        if (persistableBundleRestoreConfigFromXml2 != null) {
                            CarrierConfigLoader.this.logd("Loaded config from XML. package=" + CarrierConfigLoader.this.mPlatformCarrierConfigPackage + " phoneId=" + i);
                            CarrierConfigLoader.this.mConfigFromDefaultApp[i] = persistableBundleRestoreConfigFromXml2;
                            Message messageObtainMessage = obtainMessage(5, i, -1);
                            messageObtainMessage.getData().putBoolean("loaded_from_xml", true);
                            CarrierConfigLoader.this.mHandler.sendMessage(messageObtainMessage);
                            return;
                        }
                        CarrierConfigLoader carrierConfigLoader7 = CarrierConfigLoader.this;
                        if (!carrierConfigLoader7.bindToConfigPackage(carrierConfigLoader7.mPlatformCarrierConfigPackage, i, 3)) {
                            CarrierConfigLoader.this.notifySubscriptionInfoUpdater(i);
                            CarrierConfigLoader.this.loge("binding to default app: " + CarrierConfigLoader.this.mPlatformCarrierConfigPackage + " fails");
                            return;
                        }
                        sendMessageDelayed(obtainMessage(10, i, -1), 30000L);
                        return;
                    case 8:
                        String carrierPackageForPhoneId2 = CarrierConfigLoader.this.getCarrierPackageForPhoneId(i);
                        PersistableBundle persistableBundleRestoreConfigFromXml3 = CarrierConfigLoader.this.restoreConfigFromXml(carrierPackageForPhoneId2, "", i);
                        if (persistableBundleRestoreConfigFromXml3 == null) {
                            if (carrierPackageForPhoneId2 == null || !CarrierConfigLoader.this.bindToConfigPackage(carrierPackageForPhoneId2, i, 4)) {
                                CarrierConfigLoader.this.broadcastConfigChangedIntent(i);
                                CarrierConfigLoader.this.loge("Bind to carrier app: " + carrierPackageForPhoneId2 + " fails");
                                CarrierConfigLoader.this.notifySubscriptionInfoUpdater(i);
                                return;
                            }
                            sendMessageDelayed(obtainMessage(11, i, -1), 30000L);
                            return;
                        }
                        CarrierConfigLoader.this.logd("Loaded config from XML. package=" + carrierPackageForPhoneId2 + " phoneId=" + i);
                        CarrierConfigLoader.this.mConfigFromCarrierApp[i] = persistableBundleRestoreConfigFromXml3;
                        Message messageObtainMessage2 = obtainMessage(6, i, -1);
                        messageObtainMessage2.getData().putBoolean("loaded_from_xml", true);
                        sendMessage(messageObtainMessage2);
                        return;
                    case 9:
                        String str = (String) message.obj;
                        if (CarrierConfigLoader.this.clearCachedConfigForPackage(str)) {
                            int activeModemCount = TelephonyManager.from(CarrierConfigLoader.this.mContext).getActiveModemCount();
                            while (i3 < activeModemCount) {
                                CarrierConfigLoader.this.logdWithLocalLog("Package changed: " + str + ", phone=" + i3);
                                CarrierConfigLoader.this.updateConfigForPhoneId(i3);
                                i3++;
                            }
                            return;
                        }
                        return;
                    case 10:
                    case 14:
                        CarrierConfigLoader.this.loge("Bind/fetch time out from " + CarrierConfigLoader.this.mPlatformCarrierConfigPackage);
                        removeMessages(14);
                        if (CarrierConfigLoader.this.mServiceConnection[i] != null) {
                            CarrierConfigLoader carrierConfigLoader8 = CarrierConfigLoader.this;
                            carrierConfigLoader8.unbindIfBound(carrierConfigLoader8.mContext, CarrierConfigLoader.this.mServiceConnection[i], i);
                            CarrierConfigLoader.this.broadcastConfigChangedIntent(i);
                        }
                        CarrierConfigLoader.this.notifySubscriptionInfoUpdater(i);
                        return;
                    case 11:
                    case 15:
                        CarrierConfigLoader.this.loge("Bind/fetch from carrier app timeout");
                        removeMessages(15);
                        if (CarrierConfigLoader.this.mServiceConnection[i] != null) {
                            CarrierConfigLoader carrierConfigLoader9 = CarrierConfigLoader.this;
                            carrierConfigLoader9.unbindIfBound(carrierConfigLoader9.mContext, CarrierConfigLoader.this.mServiceConnection[i], i);
                            CarrierConfigLoader.this.broadcastConfigChangedIntent(i);
                        }
                        CarrierConfigLoader.this.notifySubscriptionInfoUpdater(i);
                        return;
                    case 12:
                        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(CarrierConfigLoader.this.mContext);
                        String string = defaultSharedPreferences.getString("build_fingerprint", null);
                        if (Build.FINGERPRINT.equals(string)) {
                            return;
                        }
                        CarrierConfigLoader.this.logd("Build fingerprint changed. old: " + string + " new: " + Build.FINGERPRINT);
                        CarrierConfigLoader.this.clearCachedConfigForPackage(null);
                        defaultSharedPreferences.edit().putString("build_fingerprint", Build.FINGERPRINT).apply();
                        return;
                    case 13:
                        break;
                    case 16:
                        CarrierConfigLoader.this.broadcastConfigChangedIntent(i);
                        return;
                    case 17:
                        CarrierConfigLoader.this.onMultiSimConfigChanged();
                        return;
                    case 18:
                        CarrierConfigLoader carrierConfigLoader10 = CarrierConfigLoader.this;
                        PersistableBundle persistableBundleRestoreNoSimConfigFromXml = carrierConfigLoader10.restoreNoSimConfigFromXml(carrierConfigLoader10.mPlatformCarrierConfigPackage);
                        if (persistableBundleRestoreNoSimConfigFromXml != null) {
                            CarrierConfigLoader.this.logd("Loaded no SIM config from XML. package=" + CarrierConfigLoader.this.mPlatformCarrierConfigPackage);
                            CarrierConfigLoader.this.mNoSimConfig = persistableBundleRestoreNoSimConfigFromXml;
                            sendMessage(obtainMessage(19, i, -1));
                            return;
                        }
                        CarrierConfigLoader carrierConfigLoader11 = CarrierConfigLoader.this;
                        if (!carrierConfigLoader11.bindToConfigPackage(carrierConfigLoader11.mPlatformCarrierConfigPackage, i, 20)) {
                            CarrierConfigLoader.this.broadcastConfigChangedIntent(i, false);
                            CarrierConfigLoader.this.loge("binding to default app to fetch no SIM config: " + CarrierConfigLoader.this.mPlatformCarrierConfigPackage + " fails");
                            return;
                        }
                        sendMessageDelayed(obtainMessage(21, i, -1), 30000L);
                        return;
                    case 19:
                        CarrierConfigLoader.this.broadcastConfigChangedIntent(i, false);
                        return;
                    case 20:
                        removeMessages(21);
                        final CarrierServiceConnection carrierServiceConnection3 = (CarrierServiceConnection) message.obj;
                        if (CarrierConfigLoader.this.mServiceConnectionForNoSimConfig[i] != carrierServiceConnection3 || carrierServiceConnection3.service == null) {
                            CarrierConfigLoader carrierConfigLoader12 = CarrierConfigLoader.this;
                            carrierConfigLoader12.unbindIfBoundForNoSimConfig(carrierConfigLoader12.mContext, carrierServiceConnection3, i);
                            return;
                        }
                        try {
                            ICarrierService.Stub.asInterface(carrierServiceConnection3.service).getCarrierConfig((CarrierIdentifier) null, new ResultReceiver(this) { // from class: com.android.phone.CarrierConfigLoader.ConfigHandler.3
                                @Override // android.os.ResultReceiver
                                public void onReceiveResult(int i4, Bundle bundle) {
                                    CarrierConfigLoader carrierConfigLoader13 = CarrierConfigLoader.this;
                                    carrierConfigLoader13.unbindIfBoundForNoSimConfig(carrierConfigLoader13.mContext, carrierServiceConnection3, i);
                                    if (CarrierConfigLoader.this.mServiceConnectionForNoSimConfig[i] != carrierServiceConnection3) {
                                        CarrierConfigLoader.this.loge("Received response for stale request.");
                                        return;
                                    }
                                    ConfigHandler.this.removeMessages(22);
                                    if (i4 == 1 || bundle == null) {
                                        CarrierConfigLoader.this.loge("Failed to get no SIM carrier config");
                                        return;
                                    }
                                    PersistableBundle persistableBundle = (PersistableBundle) bundle.getParcelable("config_bundle");
                                    CarrierConfigLoader carrierConfigLoader14 = CarrierConfigLoader.this;
                                    carrierConfigLoader14.saveNoSimConfigToXml(carrierConfigLoader14.mPlatformCarrierConfigPackage, persistableBundle);
                                    CarrierConfigLoader.this.mNoSimConfig = persistableBundle;
                                    ConfigHandler configHandler = ConfigHandler.this;
                                    configHandler.sendMessage(configHandler.obtainMessage(19, i, -1));
                                }
                            });
                            CarrierConfigLoader.this.logdWithLocalLog("Fetch no sim config from default app: " + CarrierConfigLoader.this.mPlatformCarrierConfigPackage);
                            sendMessageDelayed(obtainMessage(22, i, -1), 30000L);
                            return;
                        } catch (RemoteException e3) {
                            CarrierConfigLoader.this.loge("Failed to get no sim carrier config from default app: " + CarrierConfigLoader.this.mPlatformCarrierConfigPackage + " err: " + e3.toString());
                            CarrierConfigLoader carrierConfigLoader13 = CarrierConfigLoader.this;
                            carrierConfigLoader13.unbindIfBoundForNoSimConfig(carrierConfigLoader13.mContext, carrierServiceConnection3, i);
                            return;
                        }
                    case 21:
                    case 22:
                        CarrierConfigLoader.this.loge("Bind/fetch time out for no SIM config from " + CarrierConfigLoader.this.mPlatformCarrierConfigPackage);
                        removeMessages(22);
                        if (CarrierConfigLoader.this.mServiceConnectionForNoSimConfig[i] != null) {
                            CarrierConfigLoader carrierConfigLoader14 = CarrierConfigLoader.this;
                            carrierConfigLoader14.unbindIfBoundForNoSimConfig(carrierConfigLoader14.mContext, CarrierConfigLoader.this.mServiceConnectionForNoSimConfig[i], i);
                        }
                        CarrierConfigLoader.this.broadcastConfigChangedIntent(i, false);
                        return;
                    default:
                        return;
                }
                while (i3 < TelephonyManager.from(CarrierConfigLoader.this.mContext).getActiveModemCount()) {
                    if (CarrierConfigLoader.this.mHasSentConfigChange[i3]) {
                        CarrierConfigLoader.this.logdWithLocalLog("System unlocked");
                        CarrierConfigLoader.this.mFromSystemUnlocked[i3] = true;
                        CarrierConfigLoader.this.updateConfigForPhoneId(i3);
                    }
                    i3++;
                }
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private CarrierConfigLoader(Context context) {
        this.mBootReceiver = new ConfigLoaderBroadcastReceiver();
        this.mPackageReceiver = new ConfigLoaderBroadcastReceiver();
        this.mContext = context;
        this.mPlatformCarrierConfigPackage = context.getString(R.string.platform_carrier_config_package);
        this.mHandler = new ConfigHandler();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.BOOT_COMPLETED");
        context.registerReceiver(this.mBootReceiver, intentFilter);
        IntentFilter intentFilter2 = new IntentFilter();
        intentFilter2.addAction("android.intent.action.PACKAGE_ADDED");
        intentFilter2.addAction("android.intent.action.PACKAGE_REMOVED");
        intentFilter2.addAction("android.intent.action.PACKAGE_REPLACED");
        intentFilter2.addDataScheme("package");
        context.registerReceiver(this.mPackageReceiver, intentFilter2);
        int supportedModemCount = TelephonyManager.from(context).getSupportedModemCount();
        this.mConfigFromDefaultApp = new PersistableBundle[supportedModemCount];
        this.mConfigFromCarrierApp = new PersistableBundle[supportedModemCount];
        this.mPersistentOverrideConfigs = new PersistableBundle[supportedModemCount];
        this.mOverrideConfigs = new PersistableBundle[supportedModemCount];
        this.mNoSimConfig = new PersistableBundle();
        this.mServiceConnection = new CarrierServiceConnection[supportedModemCount];
        this.mServiceBound = new boolean[supportedModemCount];
        this.mHasSentConfigChange = new boolean[supportedModemCount];
        this.mFromSystemUnlocked = new boolean[supportedModemCount];
        this.mServiceConnectionForNoSimConfig = new CarrierServiceConnection[supportedModemCount];
        this.mServiceBoundForNoSimConfig = new boolean[supportedModemCount];
        TelephonyFrameworkInitializer.getTelephonyServiceManager().getCarrierConfigServiceRegisterer().register(this);
        logd("CarrierConfigLoader has started");
        this.mSubscriptionInfoUpdater = PhoneFactory.getSubscriptionInfoUpdater();
        this.mHandler.sendEmptyMessage(12);
    }

    static CarrierConfigLoader init(Context context) {
        CarrierConfigLoader carrierConfigLoader;
        synchronized (CarrierConfigLoader.class) {
            if (sInstance == null) {
                sInstance = new CarrierConfigLoader(context);
            } else {
                Log.wtf("CarrierConfigLoader", "init() called multiple times!  sInstance = " + sInstance);
            }
            carrierConfigLoader = sInstance;
        }
        return carrierConfigLoader;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearConfigForPhone(int i, boolean z) {
        Phone phone = PhoneFactory.getPhone(i);
        if (phone == null || !phone.isShuttingDown()) {
            this.mConfigFromDefaultApp[i] = null;
            this.mConfigFromCarrierApp[i] = null;
            this.mServiceConnection[i] = null;
            this.mHasSentConfigChange[i] = false;
            if (z) {
                Handler handler = this.mHandler;
                handler.sendMessage(handler.obtainMessage(18, i, -1));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifySubscriptionInfoUpdater(int i) {
        String carrierPackageForPhoneId;
        PersistableBundle persistableBundle;
        getSpecificCarrierIdForPhoneId(i);
        if (this.mConfigFromCarrierApp[i] != null) {
            carrierPackageForPhoneId = getCarrierPackageForPhoneId(i);
            persistableBundle = this.mConfigFromCarrierApp[i];
        } else {
            carrierPackageForPhoneId = this.mPlatformCarrierConfigPackage;
            persistableBundle = this.mConfigFromDefaultApp[i];
        }
        if (persistableBundle == null) {
            persistableBundle = new PersistableBundle();
        }
        PersistableBundle persistableBundle2 = this.mOverrideConfigs[i];
        if (persistableBundle2 != null) {
            PersistableBundle persistableBundle3 = new PersistableBundle(persistableBundle);
            persistableBundle3.putAll(persistableBundle2);
            persistableBundle = persistableBundle3;
        }
        this.mSubscriptionInfoUpdater.updateSubscriptionByCarrierConfigAndNotifyComplete(i, carrierPackageForPhoneId, persistableBundle, this.mHandler.obtainMessage(16, i, -1));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void broadcastConfigChangedIntent(int i) {
        broadcastConfigChangedIntent(i, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void broadcastConfigChangedIntent(int i, boolean z) {
        Intent intent = new Intent("android.telephony.action.CARRIER_CONFIG_CHANGED");
        intent.addFlags(335544320);
        if (z) {
            int[] subId = SubscriptionManager.getSubId(i);
            int simApplicationState = !ArrayUtils.isEmpty(subId) ? TelephonyManager.from(this.mContext).createForSubscriptionId(subId[0]).getSimApplicationState() : 0;
            logd("Broadcast CARRIER_CONFIG_CHANGED for phone " + i + " simApplicationState " + simApplicationState);
            if (simApplicationState != 0 && simApplicationState != 6) {
                SubscriptionManager.putPhoneIdAndSubIdExtra(intent, i);
                intent.putExtra("android.telephony.extra.SPECIFIC_CARRIER_ID", getSpecificCarrierIdForPhoneId(i));
                intent.putExtra("android.telephony.extra.CARRIER_ID", getCarrierIdForPhoneId(i));
            }
        }
        intent.putExtra("android.telephony.extra.SLOT_INDEX", i);
        intent.putExtra("android.telephony.extra.REBROADCAST_ON_UNLOCK", this.mFromSystemUnlocked[i]);
        this.mContext.sendBroadcastAsUser(intent, UserHandle.ALL);
        int[] subId2 = SubscriptionManager.getSubId(i);
        if (subId2 != null && subId2.length > 0) {
            logd("Broadcast CARRIER_CONFIG_CHANGED for phone " + i + ", subId=" + subId2[0]);
        } else {
            logd("Broadcast CARRIER_CONFIG_CHANGED for phone " + i);
        }
        this.mHasSentConfigChange[i] = true;
        this.mFromSystemUnlocked[i] = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean bindToConfigPackage(String str, int i, int i2) {
        logdWithLocalLog("Binding to " + str + " for phone " + i);
        Intent intent = new Intent("android.service.carrier.CarrierService");
        intent.setPackage(str);
        CarrierServiceConnection carrierServiceConnection = new CarrierServiceConnection(i, str, i2);
        if (i2 == 20) {
            this.mServiceConnectionForNoSimConfig[i] = carrierServiceConnection;
        } else {
            this.mServiceConnection[i] = carrierServiceConnection;
        }
        try {
            if (this.mContext.bindService(intent, carrierServiceConnection, 1)) {
                if (i2 == 20) {
                    this.mServiceBoundForNoSimConfig[i] = true;
                } else {
                    this.mServiceBound[i] = true;
                }
                return true;
            }
        } catch (SecurityException unused) {
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public CarrierIdentifier getCarrierIdentifierForPhoneId(int i) {
        String str;
        String strSubstring;
        int i2;
        int specificCarrierId;
        String str2;
        String str3;
        String str4;
        String simOperatorNameForPhone = TelephonyManager.from(this.mContext).getSimOperatorNameForPhone(i);
        String simOperatorNumericForPhone = TelephonyManager.from(this.mContext).getSimOperatorNumericForPhone(i);
        if (simOperatorNumericForPhone == null || simOperatorNumericForPhone.length() < 3) {
            str = "";
            strSubstring = str;
        } else {
            String strSubstring2 = simOperatorNumericForPhone.substring(0, 3);
            strSubstring = simOperatorNumericForPhone.substring(3);
            str = strSubstring2;
        }
        Phone phone = PhoneFactory.getPhone(i);
        if (phone != null) {
            String subscriberId = phone.getSubscriberId();
            String groupIdLevel1 = phone.getGroupIdLevel1();
            String groupIdLevel2 = phone.getGroupIdLevel2();
            int carrierId = phone.getCarrierId();
            specificCarrierId = phone.getSpecificCarrierId();
            str3 = groupIdLevel1;
            str4 = groupIdLevel2;
            i2 = carrierId;
            str2 = subscriberId;
        } else {
            i2 = -1;
            specificCarrierId = -1;
            str2 = "";
            str3 = str2;
            str4 = str3;
        }
        return new CarrierIdentifier(str, strSubstring, simOperatorNameForPhone, str2, str3, str4, i2, specificCarrierId);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getCarrierPackageForPhoneId(int i) {
        List carrierPackageNamesForIntentAndPhone = TelephonyManager.from(this.mContext).getCarrierPackageNamesForIntentAndPhone(new Intent("android.service.carrier.CarrierService"), i);
        if (carrierPackageNamesForIntentAndPhone == null || carrierPackageNamesForIntentAndPhone.size() <= 0) {
            return null;
        }
        return (String) carrierPackageNamesForIntentAndPhone.get(0);
    }

    private String getIccIdForPhoneId(int i) {
        Phone phone;
        if (SubscriptionManager.isValidPhoneId(i) && (phone = PhoneFactory.getPhone(i)) != null) {
            return phone.getIccSerialNumber();
        }
        return null;
    }

    private int getSpecificCarrierIdForPhoneId(int i) {
        Phone phone;
        if (SubscriptionManager.isValidPhoneId(i) && (phone = PhoneFactory.getPhone(i)) != null) {
            return phone.getSpecificCarrierId();
        }
        return -1;
    }

    private int getCarrierIdForPhoneId(int i) {
        Phone phone;
        if (SubscriptionManager.isValidPhoneId(i) && (phone = PhoneFactory.getPhone(i)) != null) {
            return phone.getCarrierId();
        }
        return -1;
    }

    private void saveConfigToXml(String str, String str2, int i, CarrierIdentifier carrierIdentifier, PersistableBundle persistableBundle, boolean z) {
        String filenameForConfig;
        if (str == null) {
            loge("Cannot save config with null packageName");
            return;
        }
        if (z) {
            filenameForConfig = getFilenameForNoSimConfig(str);
        } else {
            if (SubscriptionManager.getSimStateForSlotIndex(i) != 10) {
                loge("Skip save config because SIM records are not loaded.");
                return;
            }
            String iccIdForPhoneId = getIccIdForPhoneId(i);
            int specificCarrierId = carrierIdentifier.getSpecificCarrierId();
            if (iccIdForPhoneId == null) {
                loge("Cannot save config with null iccid.");
                return;
            }
            filenameForConfig = getFilenameForConfig(str, str2, iccIdForPhoneId, specificCarrierId);
        }
        if (persistableBundle == null || persistableBundle.isEmpty()) {
            return;
        }
        String packageVersion = getPackageVersion(str);
        if (packageVersion == null) {
            loge("Failed to get package version for: " + str);
            return;
        }
        logdWithLocalLog("Save config to xml, packagename: " + str + " phoneId: " + i);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(this.mContext.getFilesDir(), filenameForConfig));
            persistableBundle.putString("__carrier_config_package_version__", packageVersion);
            persistableBundle.writeToStream(fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (IOException e) {
            loge(e.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveConfigToXml(String str, String str2, int i, CarrierIdentifier carrierIdentifier, PersistableBundle persistableBundle) {
        saveConfigToXml(str, str2, i, carrierIdentifier, persistableBundle, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveNoSimConfigToXml(String str, PersistableBundle persistableBundle) {
        saveConfigToXml(str, "", -1, null, persistableBundle, true);
    }

    private PersistableBundle restoreConfigFromXml(String str, String str2, int i, boolean z) {
        String filenameForConfig;
        PersistableBundle fromStream;
        if (str == null) {
            loge("Cannot restore config with null packageName");
        }
        String packageVersion = getPackageVersion(str);
        PersistableBundle persistableBundle = null;
        persistableBundle = null;
        persistableBundle = null;
        File file = null;
        if (packageVersion == null) {
            loge("Failed to get package version for: " + str);
            return null;
        }
        if (z) {
            filenameForConfig = getFilenameForNoSimConfig(str);
        } else {
            if (SubscriptionManager.getSimStateForSlotIndex(i) != 10) {
                loge("Skip restore config because SIM records are not loaded.");
                return null;
            }
            String iccIdForPhoneId = getIccIdForPhoneId(i);
            int specificCarrierIdForPhoneId = getSpecificCarrierIdForPhoneId(i);
            if (iccIdForPhoneId == null) {
                loge("Cannot restore config with null iccid.");
                return null;
            }
            filenameForConfig = getFilenameForConfig(str, str2, iccIdForPhoneId, specificCarrierIdForPhoneId);
        }
        try {
            try {
                File file2 = new File(this.mContext.getFilesDir(), filenameForConfig);
                try {
                    FileInputStream fileInputStream = new FileInputStream(file2);
                    fromStream = PersistableBundle.readFromStream(fileInputStream);
                    try {
                        String string = fromStream.getString("__carrier_config_package_version__");
                        fromStream.remove("__carrier_config_package_version__");
                        if (packageVersion.equals(string)) {
                            persistableBundle = fromStream;
                        } else {
                            loge("Saved version mismatch: " + packageVersion + " vs " + string);
                        }
                        fileInputStream.close();
                        return persistableBundle;
                    } catch (FileNotFoundException unused) {
                        file = file2;
                        if (file != null) {
                            logd("File not found: " + file.getPath());
                        }
                        return fromStream;
                    } catch (IOException e) {
                        e = e;
                        persistableBundle = fromStream;
                        loge(e.toString());
                        return persistableBundle;
                    }
                } catch (FileNotFoundException unused2) {
                    fromStream = persistableBundle;
                }
            } catch (IOException e2) {
                e = e2;
            }
        } catch (FileNotFoundException unused3) {
            fromStream = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PersistableBundle restoreConfigFromXml(String str, String str2, int i) {
        return restoreConfigFromXml(str, str2, i, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PersistableBundle restoreNoSimConfigFromXml(String str) {
        return restoreConfigFromXml(str, "", -1, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean clearCachedConfigForPackage(final String str) {
        File[] fileArrListFiles = this.mContext.getFilesDir().listFiles(new FilenameFilter(this) { // from class: com.android.phone.CarrierConfigLoader.1
            @Override // java.io.FilenameFilter
            public boolean accept(File file, String str2) {
                if (str != null) {
                    return str2.startsWith("carrierconfig-" + str + "-");
                }
                return str2.startsWith("carrierconfig-");
            }
        });
        if (fileArrListFiles == null || fileArrListFiles.length < 1) {
            return false;
        }
        for (File file : fileArrListFiles) {
            logd("Deleting " + file.getName());
            file.delete();
        }
        return true;
    }

    private static String getFilenameForConfig(String str, String str2, String str3, int i) {
        return "carrierconfig-" + str + str2 + "-" + str3 + "-" + i + ".xml";
    }

    private String getFilenameForNoSimConfig(String str) {
        return "carrierconfig-" + str + "-nosim.xml";
    }

    private String getPackageVersion(String str) {
        try {
            return Long.toString(this.mContext.getPackageManager().getPackageInfo(str, 0).getLongVersionCode());
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateConfigForPhoneId(int i) {
        if (this.mConfigFromCarrierApp[i] != null && getCarrierPackageForPhoneId(i) == null) {
            this.mConfigFromCarrierApp[i] = null;
        }
        Handler handler = this.mHandler;
        handler.sendMessage(handler.obtainMessage(7, i, -1));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onMultiSimConfigChanged() {
        for (int activeModemCount = TelephonyManager.from(this.mContext).getActiveModemCount(); activeModemCount < this.mConfigFromDefaultApp.length; activeModemCount++) {
            clearConfigForPhone(activeModemCount, false);
        }
    }

    public PersistableBundle getConfigForSubId(int i, String str) {
        return getConfigForSubIdWithFeature(i, str, null);
    }

    public PersistableBundle getConfigForSubIdWithFeature(int i, String str, String str2) {
        if (!TelephonyPermissions.checkCallingOrSelfReadPhoneState(this.mContext, i, str, str2, "getCarrierConfig")) {
            return new PersistableBundle();
        }
        int phoneId = SubscriptionManager.getPhoneId(i);
        PersistableBundle defaultConfig = CarrierConfigManager.getDefaultConfig();
        if (SubscriptionManager.isValidPhoneId(phoneId)) {
            PersistableBundle persistableBundle = this.mConfigFromDefaultApp[phoneId];
            if (persistableBundle != null) {
                defaultConfig.putAll(persistableBundle);
                if (getCarrierPackageForPhoneId(phoneId) == null) {
                    defaultConfig.putBoolean("carrier_config_applied_bool", true);
                }
            }
            PersistableBundle persistableBundle2 = this.mConfigFromCarrierApp[phoneId];
            if (persistableBundle2 != null) {
                defaultConfig.putAll(persistableBundle2);
                defaultConfig.putBoolean("carrier_config_applied_bool", true);
            }
            PersistableBundle persistableBundle3 = this.mPersistentOverrideConfigs[phoneId];
            if (persistableBundle3 != null) {
                defaultConfig.putAll(persistableBundle3);
                defaultConfig.putBoolean("carrier_config_applied_bool", true);
            }
            PersistableBundle persistableBundle4 = this.mOverrideConfigs[phoneId];
            if (persistableBundle4 != null) {
                defaultConfig.putAll(persistableBundle4);
            }
        } else {
            PersistableBundle persistableBundle5 = this.mNoSimConfig;
            if (persistableBundle5 != null) {
                defaultConfig.putAll(persistableBundle5);
            }
        }
        return defaultConfig;
    }

    public void overrideConfig(int i, PersistableBundle persistableBundle, boolean z) {
        this.mContext.enforceCallingOrSelfPermission("android.permission.MODIFY_PHONE_STATE", null);
        int phoneId = SubscriptionManager.getPhoneId(i);
        if (!SubscriptionManager.isValidPhoneId(phoneId)) {
            logd("Ignore invalid phoneId: " + phoneId + " for subId: " + i);
            return;
        }
        overrideConfig(this.mOverrideConfigs, phoneId, persistableBundle);
        if (z) {
            overrideConfig(this.mPersistentOverrideConfigs, phoneId, persistableBundle);
            if (persistableBundle != null) {
                saveConfigToXml(this.mPlatformCarrierConfigPackage, "-override", phoneId, getCarrierIdentifierForPhoneId(phoneId), this.mPersistentOverrideConfigs[phoneId]);
            } else {
                new File(this.mContext.getFilesDir(), getFilenameForConfig(this.mPlatformCarrierConfigPackage, "-override", getIccIdForPhoneId(phoneId), getSpecificCarrierIdForPhoneId(phoneId))).delete();
            }
        }
        notifySubscriptionInfoUpdater(phoneId);
    }

    private void overrideConfig(PersistableBundle[] persistableBundleArr, int i, PersistableBundle persistableBundle) {
        if (persistableBundle == null) {
            persistableBundleArr[i] = new PersistableBundle();
        } else if (persistableBundleArr[i] == null) {
            persistableBundleArr[i] = persistableBundle;
        } else {
            persistableBundleArr[i].putAll(persistableBundle);
        }
    }

    public void notifyConfigChangedForSubId(int i) {
        int phoneId = SubscriptionManager.getPhoneId(i);
        if (!SubscriptionManager.isValidPhoneId(phoneId)) {
            logd("Ignore invalid phoneId: " + phoneId + " for subId: " + i);
            return;
        }
        TelephonyPermissions.enforceCallingOrSelfModifyPermissionOrCarrierPrivilege(this.mContext, i, "Require carrier privileges or MODIFY_PHONE_STATE permission.");
        clearCachedConfigForPackage(this.mContext.getPackageManager().getNameForUid(Binder.getCallingUid()));
        updateConfigForPhoneId(phoneId);
    }

    public void updateConfigForPhoneId(int i, String str) {
        this.mContext.enforceCallingOrSelfPermission("android.permission.MODIFY_PHONE_STATE", null);
        logdWithLocalLog("Update config for phoneId: " + i + " simState: " + str);
        if (SubscriptionManager.isValidPhoneId(i)) {
            switch (str) {
                case "ABSENT":
                case "CARD_IO_ERROR":
                case "CARD_RESTRICTED":
                case "UNKNOWN":
                case "NOT_READY":
                    Handler handler = this.mHandler;
                    handler.sendMessage(handler.obtainMessage(0, i, -1));
                    break;
                case "LOADED":
                case "LOCKED":
                    updateConfigForPhoneId(i);
                    break;
            }
        }
    }

    public String getDefaultCarrierServicePackageName() {
        this.mContext.enforceCallingOrSelfPermission("android.permission.READ_PRIVILEGED_PHONE_STATE", "getDefaultCarrierServicePackageName");
        return this.mPlatformCarrierConfigPackage;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void unbindIfBound(Context context, CarrierServiceConnection carrierServiceConnection, int i) {
        boolean[] zArr = this.mServiceBound;
        if (zArr[i]) {
            zArr[i] = false;
            context.unbindService(carrierServiceConnection);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void unbindIfBoundForNoSimConfig(Context context, CarrierServiceConnection carrierServiceConnection, int i) {
        boolean[] zArr = this.mServiceBoundForNoSimConfig;
        if (zArr[i]) {
            zArr[i] = false;
            context.unbindService(carrierServiceConnection);
        }
    }

    public void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        IndentingPrintWriter indentingPrintWriter = new IndentingPrintWriter(printWriter, "    ");
        if (this.mContext.checkCallingOrSelfPermission("android.permission.DUMP") != 0) {
            indentingPrintWriter.println("Permission Denial: can't dump carrierconfig from from pid=" + Binder.getCallingPid() + ", uid=" + Binder.getCallingUid());
            return;
        }
        String str = null;
        int iIndexOf = ArrayUtils.indexOf(strArr, "--requesting-package");
        if (iIndexOf >= 0 && iIndexOf < strArr.length - 1) {
            int i = iIndexOf + 1;
            if (!TextUtils.isEmpty(strArr[i])) {
                str = strArr[i];
                enforceCallerIsSystemOrRequestingPackage(str);
            }
        }
        indentingPrintWriter.println("CarrierConfigLoader: " + this);
        for (int i2 = 0; i2 < TelephonyManager.from(this.mContext).getActiveModemCount(); i2++) {
            indentingPrintWriter.println("Phone Id = " + i2);
            printConfig(CarrierConfigManager.getDefaultConfig(), indentingPrintWriter, "Default Values from CarrierConfigManager");
            printConfig(this.mConfigFromDefaultApp[i2], indentingPrintWriter, "mConfigFromDefaultApp");
            printConfig(this.mConfigFromCarrierApp[i2], indentingPrintWriter, "mConfigFromCarrierApp");
            printConfig(this.mPersistentOverrideConfigs[i2], indentingPrintWriter, "mPersistentOverrideConfigs");
            printConfig(this.mOverrideConfigs[i2], indentingPrintWriter, "mOverrideConfigs");
        }
        printConfig(this.mNoSimConfig, indentingPrintWriter, "mNoSimConfig");
        indentingPrintWriter.println("CarrierConfigLoadingLog=");
        this.mCarrierConfigLoadingLog.dump(fileDescriptor, indentingPrintWriter, strArr);
        if (str != null) {
            logd("Including default and requesting package " + str + " carrier services in dump");
            indentingPrintWriter.println("");
            indentingPrintWriter.println("Connected services");
            dumpCarrierServiceIfBound(fileDescriptor, indentingPrintWriter, "Default config package", this.mPlatformCarrierConfigPackage, false);
            dumpCarrierServiceIfBound(fileDescriptor, indentingPrintWriter, "Requesting package", str, true);
        }
    }

    private void printConfig(PersistableBundle persistableBundle, IndentingPrintWriter indentingPrintWriter, String str) {
        indentingPrintWriter.increaseIndent();
        if (persistableBundle == null) {
            indentingPrintWriter.println(str + " : null ");
            indentingPrintWriter.decreaseIndent();
            indentingPrintWriter.println("");
            return;
        }
        indentingPrintWriter.println(str + " : ");
        ArrayList<String> arrayList = new ArrayList(persistableBundle.keySet());
        Collections.sort(arrayList);
        indentingPrintWriter.increaseIndent();
        indentingPrintWriter.increaseIndent();
        for (String str2 : arrayList) {
            if (persistableBundle.get(str2) != null && (persistableBundle.get(str2) instanceof Object[])) {
                indentingPrintWriter.println(str2 + " = " + Arrays.toString((Object[]) persistableBundle.get(str2)));
            } else if (persistableBundle.get(str2) != null && (persistableBundle.get(str2) instanceof int[])) {
                indentingPrintWriter.println(str2 + " = " + Arrays.toString((int[]) persistableBundle.get(str2)));
            } else {
                indentingPrintWriter.println(str2 + " = " + persistableBundle.get(str2));
            }
        }
        indentingPrintWriter.decreaseIndent();
        indentingPrintWriter.decreaseIndent();
        indentingPrintWriter.decreaseIndent();
        indentingPrintWriter.println("");
    }

    private void enforceCallerIsSystemOrRequestingPackage(String str) throws SecurityException {
        int callingUid = Binder.getCallingUid();
        if (callingUid == 0 || callingUid == 1000 || callingUid == 2000 || callingUid == 1001) {
            return;
        }
        AppOpsManager appOpsManager = (AppOpsManager) this.mContext.getSystemService(AppOpsManager.class);
        if (appOpsManager == null) {
            throw new SecurityException("No AppOps");
        }
        appOpsManager.checkPackage(callingUid, str);
    }

    private void dumpCarrierServiceIfBound(FileDescriptor fileDescriptor, IndentingPrintWriter indentingPrintWriter, String str, String str2, boolean z) {
        IBinder iBinder;
        indentingPrintWriter.increaseIndent();
        indentingPrintWriter.println(str + " : " + str2);
        ArraySet arraySet = new ArraySet(this.mServiceConnection.length);
        CarrierServiceConnection[] carrierServiceConnectionArr = this.mServiceConnection;
        int length = carrierServiceConnectionArr.length;
        for (int i = 0; i < length; i++) {
            CarrierServiceConnection carrierServiceConnection = carrierServiceConnectionArr[i];
            if (carrierServiceConnection != null && SubscriptionManager.isValidPhoneId(carrierServiceConnection.phoneId) && !TextUtils.isEmpty(carrierServiceConnection.pkgName)) {
                String str3 = carrierServiceConnection.pkgName;
                boolean zEquals = TextUtils.equals(str2, str3);
                boolean z2 = z && hasCarrierPrivileges(str2, carrierServiceConnection.phoneId);
                if ((zEquals || z2) && (iBinder = carrierServiceConnection.service) != null && iBinder.isBinderAlive() && iBinder.pingBinder() && arraySet.add(str3)) {
                    if (!zEquals) {
                        logd(str2 + " has carrier privileges on phoneId " + carrierServiceConnection.phoneId + ", service provided by " + str3);
                        indentingPrintWriter.increaseIndent();
                        StringBuilder sb = new StringBuilder();
                        sb.append("Proxy : ");
                        sb.append(str3);
                        indentingPrintWriter.println(sb.toString());
                        indentingPrintWriter.decreaseIndent();
                    }
                    indentingPrintWriter.flush();
                    try {
                        logd("Dumping " + str3);
                        carrierServiceConnection.service.dump(fileDescriptor, null);
                        logd("Done with " + str3);
                    } catch (RemoteException e) {
                        logd("RemoteException from " + str3, e);
                        indentingPrintWriter.increaseIndent();
                        indentingPrintWriter.println("RemoteException");
                        indentingPrintWriter.increaseIndent();
                        e.printStackTrace(indentingPrintWriter);
                        indentingPrintWriter.decreaseIndent();
                        indentingPrintWriter.decreaseIndent();
                    }
                    indentingPrintWriter.println("");
                }
            }
        }
        if (arraySet.isEmpty()) {
            indentingPrintWriter.increaseIndent();
            indentingPrintWriter.println("Not bound");
            indentingPrintWriter.decreaseIndent();
            indentingPrintWriter.println("");
        }
        indentingPrintWriter.decreaseIndent();
    }

    private boolean hasCarrierPrivileges(String str, int i) {
        int[] subId = SubscriptionManager.getSubId(i);
        return !ArrayUtils.isEmpty(subId) && TelephonyManager.from(this.mContext).createForSubscriptionId(subId[0]).checkCarrierPrivilegesForPackage(str) == 1;
    }

    private class CarrierServiceConnection implements ServiceConnection {
        final int eventId;
        final int phoneId;
        final String pkgName;
        IBinder service;

        CarrierServiceConnection(int i, String str, int i2) {
            this.phoneId = i;
            this.pkgName = str;
            this.eventId = i2;
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            CarrierConfigLoader.this.logd("Connected to config app: " + componentName.flattenToShortString());
            this.service = iBinder;
            CarrierConfigLoader.this.mHandler.sendMessage(CarrierConfigLoader.this.mHandler.obtainMessage(this.eventId, this.phoneId, -1, this));
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            CarrierConfigLoader.this.logd("Disconnected from config app: " + componentName.flattenToShortString());
            this.service = null;
        }

        @Override // android.content.ServiceConnection
        public void onBindingDied(ComponentName componentName) {
            CarrierConfigLoader.this.logd("Binding died from config app: " + componentName.flattenToShortString());
            this.service = null;
        }

        @Override // android.content.ServiceConnection
        public void onNullBinding(ComponentName componentName) {
            CarrierConfigLoader.this.logd("Null binding from config app: " + componentName.flattenToShortString());
            this.service = null;
        }
    }

    private class ConfigLoaderBroadcastReceiver extends BroadcastReceiver {
        private ConfigLoaderBroadcastReceiver() {
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            byte b = 0;
            if (!intent.getBooleanExtra("android.intent.extra.REPLACING", false) || "android.intent.action.PACKAGE_REPLACED".equals(action)) {
                switch (action.hashCode()) {
                    case -810471698:
                        b = !action.equals("android.intent.action.PACKAGE_REPLACED") ? (byte) -1 : (byte) 3;
                        break;
                    case 525384130:
                        b = !action.equals("android.intent.action.PACKAGE_REMOVED") ? (byte) -1 : (byte) 2;
                        break;
                    case 798292259:
                        if (!action.equals("android.intent.action.BOOT_COMPLETED")) {
                            b = -1;
                        }
                        break;
                    case 1544582882:
                        b = !action.equals("android.intent.action.PACKAGE_ADDED") ? (byte) -1 : (byte) 1;
                        break;
                    default:
                        b = -1;
                        break;
                }
                if (b == 0) {
                    CarrierConfigLoader.this.mHandler.sendMessage(CarrierConfigLoader.this.mHandler.obtainMessage(13, null));
                    return;
                }
                if (b == 1 || b == 2 || b == 3) {
                    String nameForUid = CarrierConfigLoader.this.mContext.getPackageManager().getNameForUid(intent.getIntExtra("android.intent.extra.UID", -1));
                    if (nameForUid != null) {
                        CarrierConfigLoader.this.mHandler.sendMessage(CarrierConfigLoader.this.mHandler.obtainMessage(9, nameForUid));
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void logd(String str) {
        Log.d("CarrierConfigLoader", str);
    }

    private void logd(String str, Throwable th) {
        Log.d("CarrierConfigLoader", str, th);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void logdWithLocalLog(String str) {
        Log.d("CarrierConfigLoader", str);
        this.mCarrierConfigLoadingLog.log(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loge(String str) {
        Log.e("CarrierConfigLoader", str);
        this.mCarrierConfigLoadingLog.log(str);
    }
}
