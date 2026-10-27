package com.android.phone;

import android.app.AppOpsManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.ComponentInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.internal.telephony.sysprop.TelephonyProperties;
import android.net.Uri;
import android.os.AsyncResult;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.ParcelFileDescriptor;
import android.os.ParcelUuid;
import android.os.PersistableBundle;
import android.os.RemoteException;
import android.os.ResultReceiver;
import android.os.ServiceSpecificException;
import android.os.UserHandle;
import android.os.UserManager;
import android.os.WorkSource;
import android.preference.PreferenceManager;
import android.provider.DeviceConfig;
import android.provider.Settings;
import android.provider.Telephony;
import android.telecom.PhoneAccount;
import android.telecom.PhoneAccountHandle;
import android.telecom.TelecomManager;
import android.telephony.CallForwardingInfo;
import android.telephony.CarrierConfigManager;
import android.telephony.CarrierRestrictionRules;
import android.telephony.CellIdentity;
import android.telephony.CellIdentityCdma;
import android.telephony.CellIdentityGsm;
import android.telephony.CellInfo;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoWcdma;
import android.telephony.ClientRequestStats;
import android.telephony.ICellInfoCallback;
import android.telephony.IccOpenLogicalChannelResponse;
import android.telephony.LocationAccessPolicy;
import android.telephony.ModemActivityInfo;
import android.telephony.NeighboringCellInfo;
import android.telephony.NetworkScanRequest;
import android.telephony.PhoneCapability;
import android.telephony.PhoneNumberRange;
import android.telephony.RadioAccessFamily;
import android.telephony.RadioAccessSpecifier;
import android.telephony.ServiceState;
import android.telephony.SignalStrength;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyFrameworkInitializer;
import android.telephony.TelephonyHistogram;
import android.telephony.TelephonyManager;
import android.telephony.UiccCardInfo;
import android.telephony.UiccSlotInfo;
import android.telephony.VisualVoicemailSmsFilterSettings;
import android.telephony.emergency.EmergencyNumber;
import android.telephony.ims.ImsException;
import android.telephony.ims.RegistrationManager;
import android.telephony.ims.aidl.IImsCapabilityCallback;
import android.telephony.ims.aidl.IImsConfig;
import android.telephony.ims.aidl.IImsConfigCallback;
import android.telephony.ims.aidl.IImsMmTelFeature;
import android.telephony.ims.aidl.IImsRcsFeature;
import android.telephony.ims.aidl.IImsRegistration;
import android.telephony.ims.aidl.IImsRegistrationCallback;
import android.text.TextUtils;
import android.util.ArraySet;
import android.util.EventLog;
import android.util.Log;
import android.util.Pair;
import com.android.ims.ImsManager;
import com.android.ims.internal.IImsServiceFeatureCallback;
import com.android.internal.telephony.CallManager;
import com.android.internal.telephony.CarrierInfoManager;
import com.android.internal.telephony.CarrierResolver;
import com.android.internal.telephony.CellNetworkScanResult;
import com.android.internal.telephony.CommandException;
import com.android.internal.telephony.DefaultPhoneNotifier;
import com.android.internal.telephony.GsmCdmaPhone;
import com.android.internal.telephony.HalVersion;
import com.android.internal.telephony.IBooleanConsumer;
import com.android.internal.telephony.IIntegerConsumer;
import com.android.internal.telephony.INumberVerificationCallback;
import com.android.internal.telephony.ITelephony;
import com.android.internal.telephony.IccCard;
import com.android.internal.telephony.LocaleTracker;
import com.android.internal.telephony.NetworkScanRequestTracker;
import com.android.internal.telephony.OperatorInfo;
import com.android.internal.telephony.Phone;
import com.android.internal.telephony.PhoneConfigurationManager;
import com.android.internal.telephony.PhoneConstantConversions;
import com.android.internal.telephony.PhoneConstants;
import com.android.internal.telephony.PhoneFactory;
import com.android.internal.telephony.ProxyController;
import com.android.internal.telephony.RIL;
import com.android.internal.telephony.RILConstants;
import com.android.internal.telephony.ServiceStateTracker;
import com.android.internal.telephony.SmsPermissions;
import com.android.internal.telephony.SubscriptionController;
import com.android.internal.telephony.TelephonyPermissions;
import com.android.internal.telephony.dataconnection.ApnSettingUtils;
import com.android.internal.telephony.emergency.EmergencyNumberTracker;
import com.android.internal.telephony.euicc.EuiccConnector;
import com.android.internal.telephony.ims.ImsResolver;
import com.android.internal.telephony.imsphone.ImsPhone;
import com.android.internal.telephony.metrics.TelephonyMetrics;
import com.android.internal.telephony.uicc.IccCardApplicationStatus;
import com.android.internal.telephony.uicc.IccCardStatus;
import com.android.internal.telephony.uicc.IccIoResult;
import com.android.internal.telephony.uicc.IccRecords;
import com.android.internal.telephony.uicc.IccUtils;
import com.android.internal.telephony.uicc.UiccCard;
import com.android.internal.telephony.uicc.UiccController;
import com.android.internal.telephony.uicc.UiccProfile;
import com.android.internal.telephony.uicc.UiccSlot;
import com.android.internal.telephony.util.LocaleUtils;
import com.android.internal.telephony.util.VoicemailNotificationSettingsUtil;
import com.android.phone.settings.PickSmsSubscriptionActivity;
import com.android.phone.vvm.PhoneAccountHandleConverter;
import com.android.phone.vvm.RemoteVvmTaskManager;
import com.android.phone.vvm.VisualVoicemailSettingsUtil;
import com.android.phone.vvm.VisualVoicemailSmsFilterConfig;
import com.android.telephony.Rlog;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes.dex */
public class PhoneInterfaceManager extends ITelephony.Stub {
    private static PhoneInterfaceManager sInstance;
    private PhoneGlobals mApp;
    private AppOpsManager mAppOps;
    private SharedPreferences mTelephonySharedPreferences;
    private UserManager mUserManager;
    private final ModemActivityInfo mLastModemActivityInfo = new ModemActivityInfo(0, 0, 0, new int[0], 0);
    private CallManager mCM = PhoneGlobals.getInstance().mCM;
    private ImsResolver mImsResolver = PhoneGlobals.getInstance().getImsResolver();
    private MainThreadHandler mMainThreadHandler = new MainThreadHandler(this, null);
    private SubscriptionController mSubscriptionController = SubscriptionController.getInstance();
    private NetworkScanRequestTracker mNetworkScanRequestTracker = new NetworkScanRequestTracker();
    private PhoneConfigurationManager mPhoneConfigurationManager = PhoneConfigurationManager.getInstance();
    private AtomicBoolean mNotifyUserActivity = new AtomicBoolean(false);

    private static final class IccAPDUArgument {
        public int channel;
        public int cla;
        public int command;
        public String data;
        public int p1;
        public int p2;
        public int p3;

        public IccAPDUArgument(int i, int i2, int i3, int i4, int i5, int i6, String str) {
            this.channel = i;
            this.cla = i2;
            this.command = i3;
            this.p1 = i4;
            this.p2 = i5;
            this.p3 = i6;
            this.data = str;
        }
    }

    private static final class ManualNetworkSelectionArgument {
        public OperatorInfo operatorInfo;
        public boolean persistSelection;

        public ManualNetworkSelectionArgument(OperatorInfo operatorInfo, boolean z) {
            this.operatorInfo = operatorInfo;
            this.persistSelection = z;
        }
    }

    private static final class MainThreadRequest {
        public Object argument;
        public Phone phone;
        public Object result;
        public Integer subId;
        public WorkSource workSource;

        MainThreadRequest(Object obj, Phone phone, WorkSource workSource) {
            this.subId = -1;
            this.argument = obj;
            if (phone != null) {
                this.phone = phone;
            }
            this.workSource = workSource;
        }

        MainThreadRequest(Object obj, Integer num, WorkSource workSource) {
            this.subId = -1;
            this.argument = obj;
            if (num != null) {
                this.subId = num;
            }
            this.workSource = workSource;
        }
    }

    private final class MainThreadHandler extends Handler {
        private MainThreadHandler() {
        }

        /* synthetic */ MainThreadHandler(PhoneInterfaceManager phoneInterfaceManager, AnonymousClass1 anonymousClass1) {
            this();
        }

        /* JADX WARN: Removed duplicated region for block: B:73:0x01c4  */
        @Override // android.os.Handler
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void handleMessage(android.os.Message r26) {
            /*
                Method dump skipped, instruction units count: 4040
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.phone.PhoneInterfaceManager.MainThreadHandler.handleMessage(android.os.Message):void");
        }

        private void notifyRequester(MainThreadRequest mainThreadRequest) {
            synchronized (mainThreadRequest) {
                mainThreadRequest.notifyAll();
            }
        }

        private void handleNullReturnEvent(Message message, String str) {
            AsyncResult asyncResult = (AsyncResult) message.obj;
            MainThreadRequest mainThreadRequest = (MainThreadRequest) asyncResult.userObj;
            Throwable th = asyncResult.exception;
            if (th == null) {
                mainThreadRequest.result = Boolean.TRUE;
            } else {
                mainThreadRequest.result = Boolean.FALSE;
                if (th instanceof CommandException) {
                    PhoneInterfaceManager.loge(str + ": CommandException: " + asyncResult.exception);
                } else {
                    PhoneInterfaceManager.loge(str + ": Unknown exception");
                }
            }
            notifyRequester(mainThreadRequest);
        }
    }

    private Object sendRequest(int i, Object obj) {
        return sendRequest(i, obj, -1, null, null);
    }

    private Object sendRequest(int i, Object obj, WorkSource workSource) {
        return sendRequest(i, obj, -1, null, workSource);
    }

    private Object sendRequest(int i, Object obj, Integer num) {
        return sendRequest(i, obj, num, null, null);
    }

    private Object sendRequest(int i, Object obj, Phone phone, WorkSource workSource) {
        return sendRequest(i, obj, -1, phone, workSource);
    }

    private Object sendRequest(int i, Object obj, Integer num, Phone phone, WorkSource workSource) {
        MainThreadRequest mainThreadRequest;
        if (Looper.myLooper() == this.mMainThreadHandler.getLooper()) {
            throw new RuntimeException("This method will deadlock if called from the main thread.");
        }
        if (num.intValue() != -1 && phone != null) {
            throw new IllegalArgumentException("subId and phone cannot both be specified!");
        }
        if (phone != null) {
            mainThreadRequest = new MainThreadRequest(obj, phone, workSource);
        } else {
            mainThreadRequest = new MainThreadRequest(obj, num, workSource);
        }
        this.mMainThreadHandler.obtainMessage(i, mainThreadRequest).sendToTarget();
        synchronized (mainThreadRequest) {
            while (mainThreadRequest.result == null) {
                try {
                    mainThreadRequest.wait();
                } catch (InterruptedException unused) {
                }
            }
        }
        return mainThreadRequest.result;
    }

    private void sendRequestAsync(int i, Object obj, Phone phone, WorkSource workSource) {
        this.mMainThreadHandler.obtainMessage(i, new MainThreadRequest(obj, phone, workSource)).sendToTarget();
    }

    static PhoneInterfaceManager init(PhoneGlobals phoneGlobals) {
        PhoneInterfaceManager phoneInterfaceManager;
        synchronized (PhoneInterfaceManager.class) {
            if (sInstance == null) {
                sInstance = new PhoneInterfaceManager(phoneGlobals);
            } else {
                Log.wtf("PhoneInterfaceManager", "init() called multiple times!  sInstance = " + sInstance);
            }
            phoneInterfaceManager = sInstance;
        }
        return phoneInterfaceManager;
    }

    private PhoneInterfaceManager(PhoneGlobals phoneGlobals) {
        this.mApp = phoneGlobals;
        this.mUserManager = (UserManager) phoneGlobals.getSystemService("user");
        this.mAppOps = (AppOpsManager) phoneGlobals.getSystemService("appops");
        this.mTelephonySharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.mApp);
        publish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Phone getDefaultPhone() {
        Phone phone = getPhone(getDefaultSubscription());
        return phone != null ? phone : PhoneFactory.getDefaultPhone();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void publish() {
        TelephonyFrameworkInitializer.getTelephonyServiceManager().getTelephonyServiceRegisterer().register(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Phone getPhoneFromRequest(MainThreadRequest mainThreadRequest) {
        Phone phone = mainThreadRequest.phone;
        return phone != null ? phone : getPhoneFromSubId(mainThreadRequest.subId.intValue());
    }

    private Phone getPhoneFromSubId(int i) {
        return i == -1 ? getDefaultPhone() : getPhone(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public UiccCard getUiccCardFromRequest(MainThreadRequest mainThreadRequest) {
        Phone phoneFromRequest = getPhoneFromRequest(mainThreadRequest);
        if (phoneFromRequest == null) {
            return null;
        }
        return UiccController.getInstance().getUiccCard(phoneFromRequest.getPhoneId());
    }

    private Phone getPhone(int i) {
        return PhoneFactory.getPhone(this.mSubscriptionController.getPhoneId(i));
    }

    private void sendEraseModemConfig(Phone phone) {
        if (phone != null) {
            TelephonyPermissions.enforceCallingOrSelfModifyPermissionOrCarrierPrivilege(this.mApp, phone.getSubId(), "eraseModemConfig");
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        }
    }

    private boolean isImsAvailableOnDevice() {
        PackageManager packageManager = getDefaultPhone().getContext().getPackageManager();
        if (packageManager == null) {
            return true;
        }
        return packageManager.hasSystemFeature("android.hardware.telephony.ims", 0);
    }

    public void dial(String str) {
        dialForSubscriber(getPreferredVoiceSubscription(), str);
    }

    public void dialForSubscriber(int i, String str) {
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            String strCreateTelUrl = createTelUrl(str);
            if (strCreateTelUrl == null) {
                return;
            }
            PhoneConstants.State state = this.mCM.getState(i);
            if (state != PhoneConstants.State.OFFHOOK && state != PhoneConstants.State.RINGING) {
                Intent intent = new Intent("android.intent.action.DIAL", Uri.parse(strCreateTelUrl));
                intent.addFlags(268435456);
                this.mApp.startActivity(intent);
            }
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public void call(String str, String str2) {
        callForSubscriber(getPreferredVoiceSubscription(), str, str2);
    }

    public void callForSubscriber(int i, String str, String str2) {
        enforceCallPermission();
        if (this.mAppOps.noteOp("android:call_phone", Binder.getCallingUid(), str) != 0) {
            return;
        }
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            String strCreateTelUrl = createTelUrl(str2);
            if (strCreateTelUrl == null) {
                return;
            }
            boolean z = false;
            List<SubscriptionInfo> activeSubscriptionInfoListPrivileged = getActiveSubscriptionInfoListPrivileged();
            if (activeSubscriptionInfoListPrivileged != null) {
                Iterator<SubscriptionInfo> it = activeSubscriptionInfoListPrivileged.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    } else if (it.next().getSubscriptionId() == i) {
                        z = true;
                        break;
                    }
                }
            }
            if (z) {
                Intent intent = new Intent("android.intent.action.CALL", Uri.parse(strCreateTelUrl));
                intent.putExtra("subscription", i);
                intent.addFlags(268435456);
                this.mApp.startActivity(intent);
            }
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public boolean supplyPinForSubscriber(int i, String str) {
        return supplyPinReportResultForSubscriber(i, str)[0] == 0;
    }

    public boolean supplyPukForSubscriber(int i, String str, String str2) {
        return supplyPukReportResultForSubscriber(i, str, str2)[0] == 0;
    }

    public int[] supplyPinReportResultForSubscriber(int i, String str) {
        enforceModifyPermission();
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            UnlockSim unlockSim = new UnlockSim(getPhone(i).getIccCard());
            unlockSim.start();
            return unlockSim.unlockSim(null, str);
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public int[] supplyPukReportResultForSubscriber(int i, String str, String str2) {
        enforceModifyPermission();
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            UnlockSim unlockSim = new UnlockSim(getPhone(i).getIccCard());
            unlockSim.start();
            return unlockSim.unlockSim(str, str2);
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    private static class UnlockSim extends Thread {
        private Handler mHandler;
        private final IccCard mSimCard;
        private boolean mDone = false;
        private int mResult = 2;
        private int mRetryCount = -1;

        public UnlockSim(IccCard iccCard) {
            this.mSimCard = iccCard;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            Looper.prepare();
            synchronized (this) {
                this.mHandler = new Handler() { // from class: com.android.phone.PhoneInterfaceManager.UnlockSim.1
                    @Override // android.os.Handler
                    public void handleMessage(Message message) {
                        AsyncResult asyncResult = (AsyncResult) message.obj;
                        if (message.what != 100) {
                            return;
                        }
                        Log.d("PhoneInterfaceManager", "SUPPLY_PIN_COMPLETE");
                        synchronized (UnlockSim.this) {
                            UnlockSim.this.mRetryCount = message.arg1;
                            if (asyncResult.exception == null) {
                                UnlockSim.this.mResult = 0;
                            } else if (!(asyncResult.exception instanceof CommandException) || asyncResult.exception.getCommandError() != CommandException.Error.PASSWORD_INCORRECT) {
                                UnlockSim.this.mResult = 2;
                            } else {
                                UnlockSim.this.mResult = 1;
                            }
                            UnlockSim.this.mDone = true;
                            UnlockSim.this.notifyAll();
                        }
                    }
                };
                notifyAll();
            }
            Looper.loop();
        }

        synchronized int[] unlockSim(String str, String str2) {
            while (this.mHandler == null) {
                try {
                    wait();
                } catch (InterruptedException unused) {
                    Thread.currentThread().interrupt();
                }
            }
            Message messageObtain = Message.obtain(this.mHandler, 100);
            if (str == null) {
                this.mSimCard.supplyPin(str2, messageObtain);
            } else {
                this.mSimCard.supplyPuk(str, str2, messageObtain);
            }
            while (!this.mDone) {
                try {
                    Log.d("PhoneInterfaceManager", "wait for done");
                    wait();
                } catch (InterruptedException unused2) {
                    Thread.currentThread().interrupt();
                }
            }
            Log.d("PhoneInterfaceManager", "done");
            return new int[]{this.mResult, this.mRetryCount};
        }
    }

    public void updateServiceLocation() {
        updateServiceLocationForSubscriber(getDefaultSubscription());
    }

    public void updateServiceLocationForSubscriber(int i) {
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            Phone phone = getPhone(i);
            if (phone != null) {
                phone.updateServiceLocation();
            }
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    @Deprecated
    public boolean isRadioOn(String str) {
        return isRadioOnWithFeature(str, null);
    }

    public boolean isRadioOnWithFeature(String str, String str2) {
        return isRadioOnForSubscriberWithFeature(getDefaultSubscription(), str, str2);
    }

    @Deprecated
    public boolean isRadioOnForSubscriber(int i, String str) {
        return isRadioOnForSubscriberWithFeature(i, str, null);
    }

    public boolean isRadioOnForSubscriberWithFeature(int i, String str, String str2) {
        if (!TelephonyPermissions.checkCallingOrSelfReadPhoneState(this.mApp, i, str, str2, "isRadioOnForSubscriber")) {
            return false;
        }
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            return isRadioOnForSubscriber(i);
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    private boolean isRadioOnForSubscriber(int i) {
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            Phone phone = getPhone(i);
            if (phone != null) {
                return phone.getServiceState().getState() != 3;
            }
            return false;
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public void toggleRadioOnOff() {
        toggleRadioOnOffForSubscriber(getDefaultSubscription());
    }

    public void toggleRadioOnOffForSubscriber(int i) {
        enforceModifyPermission();
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            Phone phone = getPhone(i);
            if (phone != null) {
                phone.setRadioPower(!isRadioOnForSubscriber(i));
            }
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public boolean setRadio(boolean z) {
        return setRadioForSubscriber(getDefaultSubscription(), z);
    }

    public boolean setRadioForSubscriber(int i, boolean z) {
        enforceModifyPermission();
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            Phone phone = getPhone(i);
            if (phone == null) {
                return false;
            }
            if ((phone.getServiceState().getState() != 3) != z) {
                toggleRadioOnOffForSubscriber(i);
            }
            return true;
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public boolean needMobileRadioShutdown() {
        enforceReadPrivilegedPermission("needMobileRadioShutdown");
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        for (int i = 0; i < TelephonyManager.getDefault().getPhoneCount(); i++) {
            try {
                Phone phone = PhoneFactory.getPhone(i);
                if (phone != null && phone.isRadioAvailable()) {
                    Binder.restoreCallingIdentity(jClearCallingIdentity);
                    return true;
                }
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        }
        logv(TelephonyManager.getDefault().getPhoneCount() + " Phones are shutdown.");
        return false;
    }

    public void shutdownMobileRadios() {
        enforceModifyPermission();
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        for (int i = 0; i < TelephonyManager.getDefault().getPhoneCount(); i++) {
            try {
                logv("Shutting down Phone " + i);
                shutdownRadioUsingPhoneId(i);
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        }
    }

    private void shutdownRadioUsingPhoneId(int i) {
        Phone phone = PhoneFactory.getPhone(i);
        if (phone == null || !phone.isRadioAvailable()) {
            return;
        }
        phone.shutdownRadio();
    }

    public boolean setRadioPower(boolean z) {
        boolean z2;
        enforceModifyPermission();
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            Phone defaultPhone = PhoneFactory.getDefaultPhone();
            if (defaultPhone != null) {
                defaultPhone.setRadioPower(z);
                z2 = true;
            } else {
                loge("There's no default phone.");
                z2 = false;
            }
            return z2;
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public boolean enableDataConnectivity() {
        enforceModifyPermission();
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            Phone phone = getPhone(this.mSubscriptionController.getDefaultDataSubId());
            if (phone == null) {
                return false;
            }
            phone.getDataEnabledSettings().setUserDataEnabled(true);
            return true;
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public boolean disableDataConnectivity() {
        enforceModifyPermission();
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            Phone phone = getPhone(this.mSubscriptionController.getDefaultDataSubId());
            if (phone == null) {
                return false;
            }
            phone.getDataEnabledSettings().setUserDataEnabled(false);
            return true;
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public boolean isDataConnectivityPossible(int i) {
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            Phone phone = getPhone(i);
            if (phone != null) {
                return phone.isDataAllowed(17);
            }
            return false;
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public boolean handlePinMmi(String str) {
        return handlePinMmiForSubscriber(getDefaultSubscription(), str);
    }

    public void handleUssdRequest(int i, String str, ResultReceiver resultReceiver) {
        enforceCallPermission();
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            if (SubscriptionManager.isValidSubscriptionId(i)) {
                sendRequest(47, new Pair(str, resultReceiver), Integer.valueOf(i));
            }
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public boolean handlePinMmiForSubscriber(int i, String str) {
        enforceModifyPermission();
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            if (SubscriptionManager.isValidSubscriptionId(i)) {
                return ((Boolean) sendRequest(1, str, Integer.valueOf(i))).booleanValue();
            }
            return false;
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public int getCallState() {
        return getCallStateForSlot(getSlotForDefaultSubscription());
    }

    public int getCallStateForSlot(int i) {
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            Phone phone = PhoneFactory.getPhone(i);
            return phone == null ? 0 : PhoneConstantConversions.convertCallState(phone.getState());
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public int getDataState() {
        return getDataStateForSubId(this.mSubscriptionController.getDefaultDataSubId());
    }

    public int getDataStateForSubId(int i) {
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            Phone phone = getPhone(i);
            if (phone != null) {
                return PhoneConstantConversions.convertDataState(phone.getDataConnectionState());
            }
            return PhoneConstantConversions.convertDataState(PhoneConstants.DataState.DISCONNECTED);
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public int getDataActivity() {
        return getDataActivityForSubId(this.mSubscriptionController.getDefaultDataSubId());
    }

    public int getDataActivityForSubId(int i) {
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            Phone phone = getPhone(i);
            if (phone != null) {
                return DefaultPhoneNotifier.convertDataActivityState(phone.getDataActivityState());
            }
            return 0;
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public CellIdentity getCellLocation(String str, String str2) {
        ((AppOpsManager) this.mApp.getSystemService(AppOpsManager.class)).checkPackage(Binder.getCallingUid(), str);
        int i = AnonymousClass1.$SwitchMap$android$telephony$LocationAccessPolicy$LocationPermissionResult[LocationAccessPolicy.checkLocationPermission(this.mApp, new LocationAccessPolicy.LocationPermissionQuery.Builder().setCallingPackage(str).setCallingFeatureId(str2).setCallingPid(Binder.getCallingPid()).setCallingUid(Binder.getCallingUid()).setMethod("getCellLocation").setMinSdkVersionForCoarse(1).setMinSdkVersionForFine(29).build()).ordinal()];
        if (i == 1) {
            throw new SecurityException("Not allowed to access cell location");
        }
        if (i == 2) {
            return getDefaultPhone().getPhoneType() == 2 ? new CellIdentityCdma() : new CellIdentityGsm();
        }
        WorkSource workSource = getWorkSource(Binder.getCallingUid());
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            return (CellIdentity) sendRequest(62, workSource, Integer.valueOf(this.mSubscriptionController.getDefaultDataSubId()));
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public String getNetworkCountryIsoForPhone(int i) {
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        if (i == -1) {
            i = Integer.MAX_VALUE;
        }
        try {
            this.mSubscriptionController.getSubIdUsingPhoneId(i);
            Phone phone = PhoneFactory.getPhone(i);
            String emergencyCountryIso = "";
            if (phone == null) {
                return "";
            }
            ServiceStateTracker serviceStateTracker = phone.getServiceStateTracker();
            if (serviceStateTracker == null) {
                return "";
            }
            LocaleTracker localeTracker = serviceStateTracker.getLocaleTracker();
            if (localeTracker == null) {
                return "";
            }
            if (!TextUtils.isEmpty(localeTracker.getCurrentCountry())) {
                return localeTracker.getCurrentCountry();
            }
            EmergencyNumberTracker emergencyNumberTracker = phone.getEmergencyNumberTracker();
            if (emergencyNumberTracker != null) {
                emergencyCountryIso = emergencyNumberTracker.getEmergencyCountryIso();
            }
            return emergencyCountryIso;
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public void enableLocationUpdates() {
        enableLocationUpdatesForSubscriber(getDefaultSubscription());
    }

    public void enableLocationUpdatesForSubscriber(int i) {
        this.mApp.enforceCallingOrSelfPermission("android.permission.CONTROL_LOCATION_UPDATES", null);
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            Phone phone = getPhone(i);
            if (phone != null) {
                phone.enableLocationUpdates();
            }
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public void disableLocationUpdates() {
        disableLocationUpdatesForSubscriber(getDefaultSubscription());
    }

    public void disableLocationUpdatesForSubscriber(int i) {
        this.mApp.enforceCallingOrSelfPermission("android.permission.CONTROL_LOCATION_UPDATES", null);
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            Phone phone = getPhone(i);
            if (phone != null) {
                phone.disableLocationUpdates();
            }
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    private int getTargetSdk(String str) {
        try {
            ApplicationInfo applicationInfoAsUser = this.mApp.getPackageManager().getApplicationInfoAsUser(str, 0, UserHandle.getUserHandleForUid(Binder.getCallingUid()));
            if (applicationInfoAsUser != null) {
                return applicationInfoAsUser.targetSdkVersion;
            }
            return Integer.MAX_VALUE;
        } catch (PackageManager.NameNotFoundException unused) {
            loge("Failed to get package info for pkg=" + str + ", uid=" + Binder.getCallingUid());
            return Integer.MAX_VALUE;
        }
    }

    public List<NeighboringCellInfo> getNeighboringCellInfo(String str, String str2) {
        List<CellInfo> allCellInfo;
        if (getTargetSdk(str) >= 29) {
            throw new SecurityException("getNeighboringCellInfo() is unavailable to callers targeting Q+ SDK levels.");
        }
        if (this.mAppOps.noteOp("android:neighboring_cells", Binder.getCallingUid(), str) != 0 || (allCellInfo = getAllCellInfo(str, str2)) == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (CellInfo cellInfo : allCellInfo) {
            if (cellInfo instanceof CellInfoGsm) {
                arrayList.add(new NeighboringCellInfo((CellInfoGsm) cellInfo));
            } else if (cellInfo instanceof CellInfoWcdma) {
                arrayList.add(new NeighboringCellInfo((CellInfoWcdma) cellInfo));
            }
        }
        if (arrayList.size() > 0) {
            return arrayList;
        }
        return null;
    }

    private List<CellInfo> getCachedCellInfo() {
        ArrayList arrayList = new ArrayList();
        for (Phone phone : PhoneFactory.getPhones()) {
            List allCellInfo = phone.getAllCellInfo();
            if (allCellInfo != null) {
                arrayList.addAll(allCellInfo);
            }
        }
        return arrayList;
    }

    public List<CellInfo> getAllCellInfo(String str, String str2) {
        ((AppOpsManager) this.mApp.getSystemService(AppOpsManager.class)).checkPackage(Binder.getCallingUid(), str);
        int i = AnonymousClass1.$SwitchMap$android$telephony$LocationAccessPolicy$LocationPermissionResult[LocationAccessPolicy.checkLocationPermission(this.mApp, new LocationAccessPolicy.LocationPermissionQuery.Builder().setCallingPackage(str).setCallingFeatureId(str2).setCallingPid(Binder.getCallingPid()).setCallingUid(Binder.getCallingUid()).setMethod("getAllCellInfo").setMinSdkVersionForCoarse(1).setMinSdkVersionForFine(29).build()).ordinal()];
        if (i == 1) {
            throw new SecurityException("Not allowed to access cell info");
        }
        if (i == 2) {
            return new ArrayList();
        }
        if (getTargetSdk(str) >= 29) {
            return getCachedCellInfo();
        }
        WorkSource workSource = getWorkSource(Binder.getCallingUid());
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            ArrayList arrayList = new ArrayList();
            for (Phone phone : PhoneFactory.getPhones()) {
                List list = (List) sendRequest(60, null, phone, workSource);
                if (list != null) {
                    arrayList.addAll(list);
                }
            }
            return arrayList;
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public void requestCellInfoUpdate(int i, ICellInfoCallback iCellInfoCallback, String str, String str2) {
        requestCellInfoUpdateInternal(i, iCellInfoCallback, str, str2, getWorkSource(Binder.getCallingUid()));
    }

    public void requestCellInfoUpdateWithWorkSource(int i, ICellInfoCallback iCellInfoCallback, String str, String str2, WorkSource workSource) {
        enforceModifyPermission();
        requestCellInfoUpdateInternal(i, iCellInfoCallback, str, str2, workSource);
    }

    private void requestCellInfoUpdateInternal(int i, ICellInfoCallback iCellInfoCallback, String str, String str2, WorkSource workSource) {
        ((AppOpsManager) this.mApp.getSystemService(AppOpsManager.class)).checkPackage(Binder.getCallingUid(), str);
        int i2 = AnonymousClass1.$SwitchMap$android$telephony$LocationAccessPolicy$LocationPermissionResult[LocationAccessPolicy.checkLocationPermission(this.mApp, new LocationAccessPolicy.LocationPermissionQuery.Builder().setCallingPackage(str).setCallingFeatureId(str2).setCallingPid(Binder.getCallingPid()).setCallingUid(Binder.getCallingUid()).setMethod("requestCellInfoUpdate").setMinSdkVersionForCoarse(1).setMinSdkVersionForFine(1).build()).ordinal()];
        if (i2 == 1) {
            if (getTargetSdk(str) < 29) {
                EventLog.writeEvent(1397638484, "154934934", Integer.valueOf(Binder.getCallingUid()));
            }
            throw new SecurityException("Not allowed to access cell info");
        }
        if (i2 == 2) {
            if (getTargetSdk(str) < 29) {
                EventLog.writeEvent(1397638484, "154934934", Integer.valueOf(Binder.getCallingUid()));
            }
            try {
                iCellInfoCallback.onCellInfo(new ArrayList());
                return;
            } catch (RemoteException unused) {
                return;
            }
        }
        Phone phoneFromSubId = getPhoneFromSubId(i);
        if (phoneFromSubId == null) {
            throw new IllegalArgumentException("Invalid Subscription Id: " + i);
        }
        sendRequestAsync(66, iCellInfoCallback, phoneFromSubId, workSource);
    }

    public void setCellInfoListRate(int i) {
        enforceModifyPermission();
        WorkSource workSource = getWorkSource(Binder.getCallingUid());
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            getDefaultPhone().setCellInfoListRate(i, workSource);
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public String getImeiForSlot(int i, String str, String str2) {
        Phone phone = PhoneFactory.getPhone(i);
        if (phone == null) {
            return null;
        }
        if (!TelephonyPermissions.checkCallingOrSelfReadDeviceIdentifiers(this.mApp, phone.getSubId(), str, str2, "getImeiForSlot")) {
            return null;
        }
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            return phone.getImei();
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public String getTypeAllocationCodeForSlot(int i) {
        String imei;
        Phone phone = PhoneFactory.getPhone(i);
        if (phone == null || (imei = phone.getImei()) == null) {
            return null;
        }
        return imei.substring(0, 8);
    }

    public String getMeidForSlot(int i, String str, String str2) {
        Phone phone = PhoneFactory.getPhone(i);
        if (phone == null) {
            return null;
        }
        if (!TelephonyPermissions.checkCallingOrSelfReadDeviceIdentifiers(this.mApp, phone.getSubId(), str, str2, "getMeidForSlot")) {
            return null;
        }
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            return phone.getMeid();
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public String getManufacturerCodeForSlot(int i) {
        String meid;
        Phone phone = PhoneFactory.getPhone(i);
        if (phone == null || (meid = phone.getMeid()) == null) {
            return null;
        }
        return meid.substring(0, 8);
    }

    public String getDeviceSoftwareVersionForSlot(int i, String str, String str2) {
        Phone phone = PhoneFactory.getPhone(i);
        if (phone == null) {
            return null;
        }
        if (!TelephonyPermissions.checkCallingOrSelfReadPhoneState(this.mApp, phone.getSubId(), str, str2, "getDeviceSoftwareVersionForSlot")) {
            return null;
        }
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            return phone.getDeviceSvn();
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public int getSubscriptionCarrierId(int i) {
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            Phone phone = getPhone(i);
            return phone == null ? -1 : phone.getCarrierId();
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public String getSubscriptionCarrierName(int i) {
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            Phone phone = getPhone(i);
            return phone == null ? null : phone.getCarrierName();
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public int getSubscriptionSpecificCarrierId(int i) {
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            Phone phone = getPhone(i);
            return phone == null ? -1 : phone.getSpecificCarrierId();
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public String getSubscriptionSpecificCarrierName(int i) {
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            Phone phone = getPhone(i);
            return phone == null ? null : phone.getSpecificCarrierName();
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public int getCarrierIdFromMccMnc(int i, String str, boolean z) {
        if (!z) {
            enforceReadPrivilegedPermission("getCarrierIdFromMccMnc");
        }
        Phone phone = PhoneFactory.getPhone(i);
        if (phone == null) {
            return -1;
        }
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            return CarrierResolver.getCarrierIdFromMccMnc(phone.getContext(), str);
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    private void enforceModifyPermission() {
        this.mApp.enforceCallingOrSelfPermission("android.permission.MODIFY_PHONE_STATE", null);
    }

    private void enforceSystemCaller() {
        if (Binder.getCallingUid() != 1000) {
            throw new SecurityException("Caller must be system");
        }
    }

    private void enforceActiveEmergencySessionPermission() {
        this.mApp.enforceCallingOrSelfPermission("android.permission.READ_ACTIVE_EMERGENCY_SESSION", null);
    }

    private void enforceCallPermission() {
        this.mApp.enforceCallingOrSelfPermission("android.permission.CALL_PHONE", null);
    }

    private void enforceSettingsPermission() {
        this.mApp.enforceCallingOrSelfPermission("android.permission.NETWORK_SETTINGS", null);
    }

    private String createTelUrl(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return "tel:" + str;
    }

    private static void log(String str) {
        Log.d("PhoneInterfaceManager", "[PhoneIntfMgr] " + str);
    }

    private static void logv(String str) {
        Log.v("PhoneInterfaceManager", "[PhoneIntfMgr] " + str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void loge(String str) {
        Log.e("PhoneInterfaceManager", "[PhoneIntfMgr] " + str);
    }

    public int getActivePhoneType() {
        return getActivePhoneTypeForSlot(getSlotForDefaultSubscription());
    }

    public int getActivePhoneTypeForSlot(int i) {
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            Phone phone = PhoneFactory.getPhone(i);
            if (phone != null) {
                return phone.getPhoneType();
            }
            return 0;
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public int getCdmaEriIconIndex(String str, String str2) {
        return getCdmaEriIconIndexForSubscriber(getDefaultSubscription(), str, str2);
    }

    public int getCdmaEriIconIndexForSubscriber(int i, String str, String str2) {
        if (!TelephonyPermissions.checkCallingOrSelfReadPhoneState(this.mApp, i, str, str2, "getCdmaEriIconIndexForSubscriber")) {
            return -1;
        }
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            Phone phone = getPhone(i);
            if (phone != null) {
                return phone.getCdmaEriIconIndex();
            }
            return -1;
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public int getCdmaEriIconMode(String str, String str2) {
        return getCdmaEriIconModeForSubscriber(getDefaultSubscription(), str, str2);
    }

    public int getCdmaEriIconModeForSubscriber(int i, String str, String str2) {
        if (!TelephonyPermissions.checkCallingOrSelfReadPhoneState(this.mApp, i, str, str2, "getCdmaEriIconModeForSubscriber")) {
            return -1;
        }
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            Phone phone = getPhone(i);
            if (phone != null) {
                return phone.getCdmaEriIconMode();
            }
            return -1;
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public String getCdmaEriText(String str, String str2) {
        return getCdmaEriTextForSubscriber(getDefaultSubscription(), str, str2);
    }

    public String getCdmaEriTextForSubscriber(int i, String str, String str2) {
        if (!TelephonyPermissions.checkCallingOrSelfReadPhoneState(this.mApp, i, str, str2, "getCdmaEriIconTextForSubscriber")) {
            return null;
        }
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            Phone phone = getPhone(i);
            if (phone != null) {
                return phone.getCdmaEriText();
            }
            return null;
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public String getCdmaMdn(int i) {
        TelephonyPermissions.enforceCallingOrSelfModifyPermissionOrCarrierPrivilege(this.mApp, i, "getCdmaMdn");
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            Phone phone = getPhone(i);
            if (phone != null && phone.getPhoneType() == 2) {
                return phone.getLine1Number();
            }
            loge("getCdmaMdn: no phone found. Invalid subId: " + i);
            return null;
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public String getCdmaMin(int i) {
        TelephonyPermissions.enforceCallingOrSelfModifyPermissionOrCarrierPrivilege(this.mApp, i, "getCdmaMin");
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            Phone phone = getPhone(i);
            if (phone == null || phone.getPhoneType() != 2) {
                return null;
            }
            return phone.getCdmaMin();
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public void requestNumberVerification(PhoneNumberRange phoneNumberRange, long j, INumberVerificationCallback iNumberVerificationCallback, String str) {
        if (this.mApp.checkCallingOrSelfPermission("android.permission.MODIFY_PHONE_STATE") != 0) {
            throw new SecurityException("Caller must hold the MODIFY_PHONE_STATE permission");
        }
        this.mAppOps.checkPackage(Binder.getCallingUid(), str);
        if (!TextUtils.equals(str, NumberVerificationManager.getAuthorizedPackage(this.mApp))) {
            throw new SecurityException("Calling package must be configured in the device config");
        }
        if (phoneNumberRange == null) {
            throw new NullPointerException("Range must be non-null");
        }
        NumberVerificationManager.getInstance().requestVerification(phoneNumberRange, iNumberVerificationCallback, Math.min(j, TelephonyManager.getMaxNumberVerificationTimeoutMillis()));
    }

    public boolean needsOtaServiceProvisioning() {
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            return getDefaultPhone().needsOtaServiceProvisioning();
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public boolean setVoiceMailNumber(int i, String str, String str2) {
        TelephonyPermissions.enforceCallingOrSelfCarrierPrivilege(this.mApp, i, "setVoiceMailNumber");
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            return ((Boolean) sendRequest(33, new Pair(str, str2), new Integer(i))).booleanValue();
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public Bundle getVisualVoicemailSettings(String str, int i) {
        this.mAppOps.checkPackage(Binder.getCallingUid(), str);
        if (!TextUtils.equals(str, ((TelecomManager) this.mApp.getSystemService(TelecomManager.class)).getSystemDialerPackage())) {
            throw new SecurityException("caller must be system dialer");
        }
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            PhoneAccountHandle phoneAccountHandleFromSubId = PhoneAccountHandleConverter.fromSubId(i);
            if (phoneAccountHandleFromSubId != null) {
                return VisualVoicemailSettingsUtil.dump(this.mApp, phoneAccountHandleFromSubId);
            }
            return null;
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public String getVisualVoicemailPackageName(String str, String str2, int i) {
        this.mAppOps.checkPackage(Binder.getCallingUid(), str);
        if (!TelephonyPermissions.checkCallingOrSelfReadPhoneState(this.mApp, i, str, str2, "getVisualVoicemailPackageName")) {
            return null;
        }
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            return RemoteVvmTaskManager.getRemotePackage(this.mApp, i).getPackageName();
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public void enableVisualVoicemailSmsFilter(String str, int i, VisualVoicemailSmsFilterSettings visualVoicemailSmsFilterSettings) {
        this.mAppOps.checkPackage(Binder.getCallingUid(), str);
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            VisualVoicemailSmsFilterConfig.enableVisualVoicemailSmsFilter(this.mApp, str, i, visualVoicemailSmsFilterSettings);
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public void disableVisualVoicemailSmsFilter(String str, int i) {
        this.mAppOps.checkPackage(Binder.getCallingUid(), str);
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            VisualVoicemailSmsFilterConfig.disableVisualVoicemailSmsFilter(this.mApp, str, i);
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public VisualVoicemailSmsFilterSettings getVisualVoicemailSmsFilterSettings(String str, int i) {
        this.mAppOps.checkPackage(Binder.getCallingUid(), str);
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            return VisualVoicemailSmsFilterConfig.getVisualVoicemailSmsFilterSettings(this.mApp, str, i);
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public VisualVoicemailSmsFilterSettings getActiveVisualVoicemailSmsFilterSettings(int i) {
        enforceReadPrivilegedPermission("getActiveVisualVoicemailSmsFilterSettings");
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            return VisualVoicemailSmsFilterConfig.getActiveVisualVoicemailSmsFilterSettings(this.mApp, i);
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public void sendVisualVoicemailSmsForSubscriber(String str, String str2, int i, String str3, int i2, String str4, PendingIntent pendingIntent) {
        this.mAppOps.checkPackage(Binder.getCallingUid(), str);
        enforceVisualVoicemailPackage(str, i);
        enforceSendSmsPermission();
        PhoneFactory.getSmsController().sendVisualVoicemailSmsForSubscriber(str, str2, i, str3, i2, str4, pendingIntent);
    }

    public void setVoiceActivationState(int i, int i2) {
        TelephonyPermissions.enforceCallingOrSelfModifyPermissionOrCarrierPrivilege(this.mApp, i, "setVoiceActivationState");
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            Phone phone = getPhone(i);
            if (phone != null) {
                phone.setVoiceActivationState(i2);
            } else {
                loge("setVoiceActivationState fails with invalid subId: " + i);
            }
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public void setDataActivationState(int i, int i2) {
        TelephonyPermissions.enforceCallingOrSelfModifyPermissionOrCarrierPrivilege(this.mApp, i, "setDataActivationState");
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            Phone phone = getPhone(i);
            if (phone != null) {
                phone.setDataActivationState(i2);
            } else {
                loge("setDataActivationState fails with invalid subId: " + i);
            }
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public int getVoiceActivationState(int i, String str) {
        enforceReadPrivilegedPermission("getVoiceActivationState");
        Phone phone = getPhone(i);
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        if (phone != null) {
            try {
                return phone.getVoiceActivationState();
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        }
        return 0;
    }

    public int getDataActivationState(int i, String str) {
        enforceReadPrivilegedPermission("getDataActivationState");
        Phone phone = getPhone(i);
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        if (phone != null) {
            try {
                return phone.getDataActivationState();
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        }
        return 0;
    }

    public int getVoiceMessageCountForSubscriber(int i, String str, String str2) {
        if (!TelephonyPermissions.checkCallingOrSelfReadPhoneState(this.mApp, i, str, str2, "getVoiceMessageCountForSubscriber")) {
            return 0;
        }
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            Phone phone = getPhone(i);
            if (phone != null) {
                return phone.getVoiceMessageCount();
            }
            return 0;
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public boolean isConcurrentVoiceAndDataAllowed(int i) {
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            Phone phone = getPhone(i);
            return phone == null ? false : phone.isConcurrentVoiceAndDataAllowed();
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public void sendDialerSpecialCode(String str, String str2) {
        Phone defaultPhone = getDefaultPhone();
        this.mAppOps.checkPackage(Binder.getCallingUid(), str);
        if (!TextUtils.equals(str, ((TelecomManager) defaultPhone.getContext().getSystemService(TelecomManager.class)).getDefaultDialerPackage())) {
            TelephonyPermissions.enforceCallingOrSelfCarrierPrivilege(this.mApp, getDefaultSubscription(), "sendDialerSpecialCode");
        }
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            defaultPhone.sendDialerSpecialCode(str2);
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public int getNetworkSelectionMode(int i) {
        TelephonyPermissions.enforeceCallingOrSelfReadPrecisePhoneStatePermissionOrCarrierPrivilege(this.mApp, i, "getNetworkSelectionMode");
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            if (isActiveSubscription(i)) {
                return ((Integer) sendRequest(52, (Object) null, Integer.valueOf(i))).intValue();
            }
            return 0;
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public boolean isInEmergencySmsMode() {
        enforceReadPrivilegedPermission("isInEmergencySmsMode");
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            for (Phone phone : PhoneFactory.getPhones()) {
                if (phone.isInEmergencySmsMode()) {
                    Binder.restoreCallingIdentity(jClearCallingIdentity);
                    return true;
                }
            }
            return false;
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: android.os.ServiceSpecificException */
    public void registerImsRegistrationCallback(int i, IImsRegistrationCallback iImsRegistrationCallback) throws RemoteException, ServiceSpecificException {
        TelephonyPermissions.enforeceCallingOrSelfReadPrecisePhoneStatePermissionOrCarrierPrivilege(this.mApp, i, "registerImsRegistrationCallback");
        if (!ImsManager.isImsSupportedOnDevice(this.mApp)) {
            throw new ServiceSpecificException(2, "IMS not available on device.");
        }
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            try {
                ImsManager.getInstance(this.mApp, getSlotIndexOrException(i)).addRegistrationCallbackForSubscription(iImsRegistrationCallback, i);
            } catch (ImsException e) {
                throw new ServiceSpecificException(e.getCode());
            }
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public void unregisterImsRegistrationCallback(int i, IImsRegistrationCallback iImsRegistrationCallback) {
        TelephonyPermissions.enforeceCallingOrSelfReadPrecisePhoneStatePermissionOrCarrierPrivilege(this.mApp, i, "unregisterImsRegistrationCallback");
        if (!SubscriptionManager.isValidSubscriptionId(i)) {
            throw new IllegalArgumentException("Invalid Subscription ID: " + i);
        }
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            try {
                ImsManager.getInstance(this.mApp, getSlotIndexOrException(i)).removeRegistrationCallbackForSubscription(iImsRegistrationCallback, i);
            } catch (ImsException unused) {
                Log.i("PhoneInterfaceManager", "unregisterImsRegistrationCallback: " + i + "is inactive, ignoring unregister.");
            }
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: android.os.ServiceSpecificException */
    public void getImsMmTelRegistrationState(int i, final IIntegerConsumer iIntegerConsumer) throws ServiceSpecificException {
        enforceReadPrivilegedPermission("getImsMmTelRegistrationState");
        if (!ImsManager.isImsSupportedOnDevice(this.mApp)) {
            throw new ServiceSpecificException(2, "IMS not available on device.");
        }
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            Phone phone = getPhone(i);
            if (phone == null) {
                Log.w("PhoneInterfaceManager", "getImsMmTelRegistrationState: called with an invalid subscription '" + i + "'");
                throw new ServiceSpecificException(3);
            }
            phone.getImsRegistrationState(new Consumer() { // from class: com.android.phone.-$$Lambda$PhoneInterfaceManager$Cr0U9EjkB_laz0e_7mF3u8QVLgA
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    PhoneInterfaceManager.lambda$getImsMmTelRegistrationState$0(iIntegerConsumer, (Integer) obj);
                }
            });
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    static /* synthetic */ void lambda$getImsMmTelRegistrationState$0(IIntegerConsumer iIntegerConsumer, Integer num) {
        int iIntValue;
        if (num == null) {
            iIntValue = 0;
        } else {
            try {
                iIntValue = num.intValue();
            } catch (RemoteException unused) {
                Log.w("PhoneInterfaceManager", "getImsMmTelRegistrationState: callback not available.");
                return;
            }
        }
        iIntegerConsumer.accept(iIntValue);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: android.os.ServiceSpecificException */
    public void getImsMmTelRegistrationTransportType(int i, final IIntegerConsumer iIntegerConsumer) throws ServiceSpecificException {
        TelephonyPermissions.enforeceCallingOrSelfReadPrecisePhoneStatePermissionOrCarrierPrivilege(this.mApp, i, "getImsMmTelRegistrationTransportType");
        if (!ImsManager.isImsSupportedOnDevice(this.mApp)) {
            throw new ServiceSpecificException(2, "IMS not available on device.");
        }
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            Phone phone = getPhone(i);
            if (phone == null) {
                Log.w("PhoneInterfaceManager", "getImsMmTelRegistrationState: called with an invalid subscription '" + i + "'");
                throw new ServiceSpecificException(3);
            }
            phone.getImsRegistrationTech(new Consumer() { // from class: com.android.phone.-$$Lambda$PhoneInterfaceManager$zLDE5DguQH9eWdPjiLXwfef-who
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    PhoneInterfaceManager.lambda$getImsMmTelRegistrationTransportType$1(iIntegerConsumer, (Integer) obj);
                }
            });
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    static /* synthetic */ void lambda$getImsMmTelRegistrationTransportType$1(IIntegerConsumer iIntegerConsumer, Integer num) {
        try {
            iIntegerConsumer.accept(((Integer) RegistrationManager.IMS_REG_TO_ACCESS_TYPE_MAP.get(Integer.valueOf(num == null ? -1 : num.intValue()))).intValue());
        } catch (RemoteException unused) {
            Log.w("PhoneInterfaceManager", "getImsMmTelRegistrationState: callback not available.");
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: android.os.ServiceSpecificException */
    public void registerMmTelCapabilityCallback(int i, IImsCapabilityCallback iImsCapabilityCallback) throws RemoteException, ServiceSpecificException {
        TelephonyPermissions.enforeceCallingOrSelfReadPrecisePhoneStatePermissionOrCarrierPrivilege(this.mApp, i, "registerMmTelCapabilityCallback");
        if (!ImsManager.isImsSupportedOnDevice(this.mApp)) {
            throw new ServiceSpecificException(2, "IMS not available on device.");
        }
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            try {
                ImsManager.getInstance(this.mApp, getSlotIndexOrException(i)).addCapabilitiesCallbackForSubscription(iImsCapabilityCallback, i);
            } catch (ImsException e) {
                throw new ServiceSpecificException(e.getCode());
            }
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public void unregisterMmTelCapabilityCallback(int i, IImsCapabilityCallback iImsCapabilityCallback) {
        TelephonyPermissions.enforeceCallingOrSelfReadPrecisePhoneStatePermissionOrCarrierPrivilege(this.mApp, i, "unregisterMmTelCapabilityCallback");
        if (!SubscriptionManager.isValidSubscriptionId(i)) {
            throw new IllegalArgumentException("Invalid Subscription ID: " + i);
        }
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            try {
                ImsManager.getInstance(this.mApp, getSlotIndexOrException(i)).removeCapabilitiesCallbackForSubscription(iImsCapabilityCallback, i);
            } catch (ImsException unused) {
                Log.i("PhoneInterfaceManager", "unregisterMmTelCapabilityCallback: " + i + "is inactive, ignoring unregister.");
            }
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public boolean isCapable(int i, int i2, int i3) {
        enforceReadPrivilegedPermission("isCapable");
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            return ImsManager.getInstance(this.mApp, getSlotIndexOrException(i)).queryMmTelCapability(i2, i3);
        } catch (com.android.ims.ImsException e) {
            Log.w("PhoneInterfaceManager", "IMS isCapable - service unavailable: " + e.getMessage());
            return false;
        } catch (ImsException unused) {
            Log.i("PhoneInterfaceManager", "isCapable: " + i + " is inactive, returning false.");
            return false;
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public boolean isAvailable(int i, int i2, int i3) {
        enforceReadPrivilegedPermission("isAvailable");
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            Phone phone = getPhone(i);
            if (phone == null) {
                return false;
            }
            return phone.isImsCapabilityAvailable(i2, i3);
        } catch (com.android.ims.ImsException e) {
            Log.w("PhoneInterfaceManager", "IMS isAvailable - service unavailable: " + e.getMessage());
            return false;
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
        Binder.restoreCallingIdentity(jClearCallingIdentity);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: android.os.ServiceSpecificException */
    public void isMmTelCapabilitySupported(int i, final IIntegerConsumer iIntegerConsumer, int i2, int i3) throws ServiceSpecificException {
        enforceReadPrivilegedPermission("isMmTelCapabilitySupported");
        if (!ImsManager.isImsSupportedOnDevice(this.mApp)) {
            throw new ServiceSpecificException(2, "IMS not available on device.");
        }
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            int slotIndex = getSlotIndex(i);
            if (slotIndex <= -1) {
                Log.w("PhoneInterfaceManager", "isMmTelCapabilitySupported: called with an inactive subscription '" + i + "'");
                throw new ServiceSpecificException(3);
            }
            ImsManager.getInstance(this.mApp, slotIndex).isSupported(i2, i3, new Consumer() { // from class: com.android.phone.-$$Lambda$PhoneInterfaceManager$EhpP6EPBGyQRSoagRl430VpyDck
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    PhoneInterfaceManager.lambda$isMmTelCapabilitySupported$2(iIntegerConsumer, (Boolean) obj);
                }
            });
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    static /* synthetic */ void lambda$isMmTelCapabilitySupported$2(IIntegerConsumer iIntegerConsumer, Boolean bool) {
        int i = 0;
        if (bool != null) {
            try {
                if (bool.booleanValue()) {
                    i = 1;
                }
            } catch (RemoteException unused) {
                Log.w("PhoneInterfaceManager", "isMmTelCapabilitySupported: remote caller is not running. Ignore");
                return;
            }
        }
        iIntegerConsumer.accept(i);
    }

    public boolean isAdvancedCallingSettingEnabled(int i) {
        TelephonyPermissions.enforeceCallingOrSelfReadPrecisePhoneStatePermissionOrCarrierPrivilege(this.mApp, i, "isAdvancedCallingSettingEnabled");
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            try {
                return ImsManager.getInstance(this.mApp, getSlotIndexOrException(i)).isEnhanced4gLteModeSettingEnabledByUser();
            } catch (ImsException e) {
                throw new ServiceSpecificException(e.getCode());
            }
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public void setAdvancedCallingSettingEnabled(int i, boolean z) {
        TelephonyPermissions.enforceCallingOrSelfModifyPermissionOrCarrierPrivilege(this.mApp, i, "setAdvancedCallingSettingEnabled");
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            try {
                ImsManager.getInstance(this.mApp, getSlotIndexOrException(i)).setEnhanced4gLteModeSetting(z);
            } catch (ImsException e) {
                throw new ServiceSpecificException(e.getCode());
            }
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public boolean isVtSettingEnabled(int i) {
        TelephonyPermissions.enforeceCallingOrSelfReadPrecisePhoneStatePermissionOrCarrierPrivilege(this.mApp, i, "isVtSettingEnabled");
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            try {
                return ImsManager.getInstance(this.mApp, getSlotIndexOrException(i)).isVtEnabledByUser();
            } catch (ImsException e) {
                throw new ServiceSpecificException(e.getCode());
            }
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public void setVtSettingEnabled(int i, boolean z) {
        TelephonyPermissions.enforceCallingOrSelfModifyPermissionOrCarrierPrivilege(this.mApp, i, "setVtSettingEnabled");
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            try {
                ImsManager.getInstance(this.mApp, getSlotIndexOrException(i)).setVtSetting(z);
            } catch (ImsException e) {
                throw new ServiceSpecificException(e.getCode());
            }
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public boolean isVoWiFiSettingEnabled(int i) {
        TelephonyPermissions.enforeceCallingOrSelfReadPrecisePhoneStatePermissionOrCarrierPrivilege(this.mApp, i, "isVoWiFiSettingEnabled");
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            try {
                return ImsManager.getInstance(this.mApp, getSlotIndexOrException(i)).isWfcEnabledByUser();
            } catch (ImsException e) {
                throw new ServiceSpecificException(e.getCode());
            }
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public void setVoWiFiSettingEnabled(int i, boolean z) {
        TelephonyPermissions.enforceCallingOrSelfModifyPermissionOrCarrierPrivilege(this.mApp, i, "setVoWiFiSettingEnabled");
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            try {
                ImsManager.getInstance(this.mApp, getSlotIndexOrException(i)).setWfcSetting(z);
            } catch (ImsException e) {
                throw new ServiceSpecificException(e.getCode());
            }
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public boolean isVoWiFiRoamingSettingEnabled(int i) {
        TelephonyPermissions.enforeceCallingOrSelfReadPrecisePhoneStatePermissionOrCarrierPrivilege(this.mApp, i, "isVoWiFiRoamingSettingEnabled");
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            try {
                return ImsManager.getInstance(this.mApp, getSlotIndexOrException(i)).isWfcRoamingEnabledByUser();
            } catch (ImsException e) {
                throw new ServiceSpecificException(e.getCode());
            }
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public void setVoWiFiRoamingSettingEnabled(int i, boolean z) {
        TelephonyPermissions.enforceCallingOrSelfModifyPermissionOrCarrierPrivilege(this.mApp, i, "setVoWiFiRoamingSettingEnabled");
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            try {
                ImsManager.getInstance(this.mApp, getSlotIndexOrException(i)).setWfcRoamingSetting(z);
            } catch (ImsException e) {
                throw new ServiceSpecificException(e.getCode());
            }
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public void setVoWiFiNonPersistent(int i, boolean z, int i2) {
        TelephonyPermissions.enforceCallingOrSelfModifyPermissionOrCarrierPrivilege(this.mApp, i, "setVoWiFiNonPersistent");
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            try {
                ImsManager.getInstance(this.mApp, getSlotIndexOrException(i)).setWfcNonPersistent(z, i2);
            } catch (ImsException e) {
                throw new ServiceSpecificException(e.getCode());
            }
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public int getVoWiFiModeSetting(int i) {
        TelephonyPermissions.enforeceCallingOrSelfReadPrecisePhoneStatePermissionOrCarrierPrivilege(this.mApp, i, "getVoWiFiModeSetting");
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            try {
                return ImsManager.getInstance(this.mApp, getSlotIndexOrException(i)).getWfcMode(false);
            } catch (ImsException e) {
                throw new ServiceSpecificException(e.getCode());
            }
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public void setVoWiFiModeSetting(int i, int i2) {
        TelephonyPermissions.enforceCallingOrSelfModifyPermissionOrCarrierPrivilege(this.mApp, i, "setVoWiFiModeSetting");
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            try {
                ImsManager.getInstance(this.mApp, getSlotIndexOrException(i)).setWfcMode(i2, false);
            } catch (ImsException e) {
                throw new ServiceSpecificException(e.getCode());
            }
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public int getVoWiFiRoamingModeSetting(int i) {
        enforceReadPrivilegedPermission("getVoWiFiRoamingModeSetting");
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            try {
                return ImsManager.getInstance(this.mApp, getSlotIndexOrException(i)).getWfcMode(true);
            } catch (ImsException e) {
                throw new ServiceSpecificException(e.getCode());
            }
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public void setVoWiFiRoamingModeSetting(int i, int i2) {
        TelephonyPermissions.enforceCallingOrSelfModifyPermissionOrCarrierPrivilege(this.mApp, i, "setVoWiFiRoamingModeSetting");
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            try {
                ImsManager.getInstance(this.mApp, getSlotIndexOrException(i)).setWfcMode(i2, true);
            } catch (ImsException e) {
                throw new ServiceSpecificException(e.getCode());
            }
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public void setRttCapabilitySetting(int i, boolean z) {
        TelephonyPermissions.enforceCallingOrSelfModifyPermissionOrCarrierPrivilege(this.mApp, i, "setRttCapabilityEnabled");
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            try {
                ImsManager.getInstance(this.mApp, getSlotIndexOrException(i)).setRttEnabled(z);
            } catch (ImsException e) {
                throw new ServiceSpecificException(e.getCode());
            }
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public boolean isTtyOverVolteEnabled(int i) {
        TelephonyPermissions.enforeceCallingOrSelfReadPrecisePhoneStatePermissionOrCarrierPrivilege(this.mApp, i, "isTtyOverVolteEnabled");
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            try {
                return ImsManager.getInstance(this.mApp, getSlotIndexOrException(i)).isTtyOnVoLteCapable();
            } catch (ImsException e) {
                throw new ServiceSpecificException(e.getCode());
            }
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public void registerImsProvisioningChangedCallback(int i, IImsConfigCallback iImsConfigCallback) {
        enforceReadPrivilegedPermission("registerImsProvisioningChangedCallback");
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            try {
                if (!isImsAvailableOnDevice()) {
                    throw new ServiceSpecificException(2, "IMS not available on device.");
                }
                ImsManager.getInstance(this.mApp, getSlotIndexOrException(i)).addProvisioningCallbackForSubscription(iImsConfigCallback, i);
            } catch (ImsException e) {
                throw new ServiceSpecificException(e.getCode());
            }
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public void unregisterImsProvisioningChangedCallback(int i, IImsConfigCallback iImsConfigCallback) {
        enforceReadPrivilegedPermission("unregisterImsProvisioningChangedCallback");
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            if (!SubscriptionManager.isValidSubscriptionId(i)) {
                throw new IllegalArgumentException("Invalid Subscription ID: " + i);
            }
            try {
                ImsManager.getInstance(this.mApp, getSlotIndexOrException(i)).removeProvisioningCallbackForSubscription(iImsConfigCallback, i);
            } catch (ImsException unused) {
                Log.i("PhoneInterfaceManager", "unregisterImsProvisioningChangedCallback: " + i + "is inactive, ignoring unregister.");
            }
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    private void checkModifyPhoneStatePermission(int i, String str) {
        TelephonyPermissions.enforceCallingOrSelfModifyPermissionOrCarrierPrivilege(this.mApp, i, str);
    }

    private boolean isImsProvisioningRequired(int i, int i2, boolean z) {
        Phone phone = getPhone(i);
        if (phone != null) {
            return z ? doesImsCapabilityRequireProvisioning(phone.getContext(), i, i2) : doesRcsCapabilityRequireProvisioning(phone.getContext(), i, i2);
        }
        loge("phone instance null for subid " + i);
        return false;
    }

    public void setRcsProvisioningStatusForCapability(int i, int i2, boolean z) {
        checkModifyPhoneStatePermission(i, "setRcsProvisioningStatusForCapability");
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            if (isImsProvisioningRequired(i, i2, false)) {
                ImsManager imsManager = ImsManager.getInstance(this.mApp, getSlotIndex(i));
                if (i2 != 1 && i2 != 2) {
                    throw new IllegalArgumentException("Tried to set provisioning for rcs capability '" + i2 + "', which does not require provisioning.");
                }
                imsManager.setEabProvisioned(z);
            }
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public boolean getRcsProvisioningStatusForCapability(int i, int i2) {
        enforceReadPrivilegedPermission("getRcsProvisioningStatusForCapability");
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            if (!isImsProvisioningRequired(i, i2, false)) {
                return true;
            }
            ImsManager imsManager = ImsManager.getInstance(this.mApp, getSlotIndex(i));
            if (i2 != 1 && i2 != 2) {
                throw new IllegalArgumentException("Tried to get rcs provisioning for capability '" + i2 + "', which does not require provisioning.");
            }
            return imsManager.isEabProvisionedOnDevice();
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public void setImsProvisioningStatusForCapability(int i, int i2, int i3, boolean z) {
        if (i3 != 1 && i3 != 0) {
            throw new IllegalArgumentException("Registration technology '" + i3 + "' is invalid");
        }
        checkModifyPhoneStatePermission(i, "setImsProvisioningStatusForCapability");
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            if (isImsProvisioningRequired(i, i2, true)) {
                ImsManager imsManager = ImsManager.getInstance(this.mApp, getSlotIndex(i));
                if (i2 != 1) {
                    if (i2 == 2) {
                        imsManager.setVtProvisioned(z);
                    } else if (i2 == 4) {
                        if (isMmTelCapabilityProvisionedInCache(i, i2, i3) == z) {
                            return;
                        }
                        cacheMmTelCapabilityProvisioning(i, i2, i3, z);
                        try {
                            imsManager.changeMmTelCapability(i2, i3, z);
                        } catch (com.android.ims.ImsException e) {
                            loge("setImsProvisioningStatusForCapability: couldn't change UT capability, Exception" + e.getMessage());
                        }
                    } else {
                        throw new IllegalArgumentException("Tried to set provisioning for MmTel capability '" + i2 + "', which does not require provisioning. ");
                    }
                } else if (i3 == 0) {
                    imsManager.setVolteProvisioned(z);
                } else if (i3 == 1) {
                    imsManager.setWfcProvisioned(z);
                }
            }
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public boolean getImsProvisioningStatusForCapability(int i, int i2, int i3) {
        if (i3 != 1 && i3 != 0) {
            throw new IllegalArgumentException("Registration technology '" + i3 + "' is invalid");
        }
        enforceReadPrivilegedPermission("getProvisioningStatusForCapability");
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            if (!isImsProvisioningRequired(i, i2, true)) {
                return true;
            }
            ImsManager imsManager = ImsManager.getInstance(this.mApp, getSlotIndex(i));
            if (i2 == 1) {
                if (i3 == 0) {
                    return imsManager.isVolteProvisionedOnDevice();
                }
                if (i3 == 1) {
                    return imsManager.isWfcProvisionedOnDevice();
                }
                throw new IllegalArgumentException("Invalid radio technology for voice capability.");
            }
            if (i2 == 2) {
                return imsManager.isVtProvisionedOnDevice();
            }
            if (i2 == 4) {
                return isMmTelCapabilityProvisionedInCache(i, i2, i3);
            }
            throw new IllegalArgumentException("Tried to get provisioning for MmTel capability '" + i2 + "', which does not require provisioning.");
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public boolean isMmTelCapabilityProvisionedInCache(int i, int i2, int i3) {
        if (i3 != 1 && i3 != 0) {
            throw new IllegalArgumentException("Registration technology '" + i3 + "' is invalid");
        }
        enforceReadPrivilegedPermission("isMmTelCapabilityProvisionedInCache");
        return (getMmTelCapabilityProvisioningBitfield(i, i3) & i2) > 0;
    }

    public void cacheMmTelCapabilityProvisioning(int i, int i2, int i3, boolean z) {
        if (i3 != 1 && i3 != 0) {
            throw new IllegalArgumentException("Registration technology '" + i3 + "' is invalid");
        }
        TelephonyPermissions.enforceCallingOrSelfModifyPermissionOrCarrierPrivilege(this.mApp, i, "setProvisioningStatusForCapability");
        int mmTelCapabilityProvisioningBitfield = getMmTelCapabilityProvisioningBitfield(i, i3);
        if (((mmTelCapabilityProvisioningBitfield & i2) > 0) == z) {
            return;
        }
        if (z) {
            setMmTelCapabilityProvisioningBitfield(i, i3, i2 | mmTelCapabilityProvisioningBitfield);
        } else {
            setMmTelCapabilityProvisioningBitfield(i, i3, (~i2) & mmTelCapabilityProvisioningBitfield);
        }
    }

    private int getMmTelCapabilityProvisioningBitfield(int i, int i2) {
        return this.mTelephonySharedPreferences.getInt(getMmTelProvisioningKey(i, i2), 0);
    }

    private void setMmTelCapabilityProvisioningBitfield(int i, int i2, int i3) {
        SharedPreferences.Editor editorEdit = this.mTelephonySharedPreferences.edit();
        editorEdit.putInt(getMmTelProvisioningKey(i, i2), i3);
        editorEdit.commit();
    }

    private static String getMmTelProvisioningKey(int i, int i2) {
        return "provision_ims_mmtel_" + i + "_" + i2;
    }

    private boolean doesImsCapabilityRequireProvisioning(Context context, int i, int i2) {
        PersistableBundle configForSubId = new CarrierConfigManager(context).getConfigForSubId(i);
        boolean z = configForSubId.getBoolean("carrier_supports_ss_over_ut_bool", false) && configForSubId.getBoolean("carrier_ut_provisioning_required_bool", false);
        boolean z2 = configForSubId.getBoolean("carrier_volte_provisioning_required_bool", false);
        if (i2 == 1 || i2 == 2) {
            if (z2) {
                return true;
            }
        } else if (i2 == 4 && z) {
            return true;
        }
        return false;
    }

    private boolean doesRcsCapabilityRequireProvisioning(Context context, int i, int i2) {
        return (i2 == 1 || i2 == 2) && new CarrierConfigManager(context).getConfigForSubId(i).getBoolean("carrier_rcs_provisioning_required_bool", false);
    }

    public int getImsProvisioningInt(int i, int i2) {
        if (!SubscriptionManager.isValidSubscriptionId(i)) {
            throw new IllegalArgumentException("Invalid Subscription id '" + i + "'");
        }
        enforceReadPrivilegedPermission("getImsProvisioningInt");
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            int slotIndex = getSlotIndex(i);
            if (slotIndex <= -1) {
                Log.w("PhoneInterfaceManager", "getImsProvisioningInt: called with an inactive subscription '" + i + "' for key:" + i2);
                return -1;
            }
            return ImsManager.getInstance(this.mApp, slotIndex).getConfigInterface().getConfigInt(i2);
        } catch (com.android.ims.ImsException unused) {
            Log.w("PhoneInterfaceManager", "getImsProvisioningInt: ImsService is not available for subscription '" + i + "' for key:" + i2);
            return -1;
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public String getImsProvisioningString(int i, int i2) {
        if (!SubscriptionManager.isValidSubscriptionId(i)) {
            throw new IllegalArgumentException("Invalid Subscription id '" + i + "'");
        }
        enforceReadPrivilegedPermission("getImsProvisioningString");
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            int slotIndex = getSlotIndex(i);
            if (slotIndex <= -1) {
                Log.w("PhoneInterfaceManager", "getImsProvisioningString: called for an inactive subscription id '" + i + "' for key:" + i2);
                return "STRING_QUERY_RESULT_ERROR_GENERIC";
            }
            return ImsManager.getInstance(this.mApp, slotIndex).getConfigInterface().getConfigString(i2);
        } catch (com.android.ims.ImsException unused) {
            Log.w("PhoneInterfaceManager", "getImsProvisioningString: ImsService is not available for sub '" + i + "' for key:" + i2);
            return "STRING_QUERY_RESULT_ERROR_NOT_READY";
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public int setImsProvisioningInt(int i, int i2, int i3) {
        if (!SubscriptionManager.isValidSubscriptionId(i)) {
            throw new IllegalArgumentException("Invalid Subscription id '" + i + "'");
        }
        TelephonyPermissions.enforceCallingOrSelfModifyPermissionOrCarrierPrivilege(this.mApp, i, "setImsProvisioningInt");
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            int slotIndex = getSlotIndex(i);
            if (slotIndex <= -1) {
                Log.w("PhoneInterfaceManager", "setImsProvisioningInt: called with an inactive subscription id '" + i + "' for key:" + i2);
                return 1;
            }
            return ImsManager.getInstance(this.mApp, slotIndex).getConfigInterface().setConfig(i2, i3);
        } catch (com.android.ims.ImsException unused) {
            Log.w("PhoneInterfaceManager", "setImsProvisioningInt: ImsService unavailable for sub '" + i + "' for key:" + i2);
            return 1;
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public int setImsProvisioningString(int i, int i2, String str) {
        if (!SubscriptionManager.isValidSubscriptionId(i)) {
            throw new IllegalArgumentException("Invalid Subscription id '" + i + "'");
        }
        TelephonyPermissions.enforceCallingOrSelfModifyPermissionOrCarrierPrivilege(this.mApp, i, "setImsProvisioningString");
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            int slotIndex = getSlotIndex(i);
            if (slotIndex <= -1) {
                Log.w("PhoneInterfaceManager", "setImsProvisioningString: called with an inactive subscription id '" + i + "' for key:" + i2);
                return 1;
            }
            return ImsManager.getInstance(this.mApp, slotIndex).getConfigInterface().setConfig(i2, str);
        } catch (com.android.ims.ImsException unused) {
            Log.w("PhoneInterfaceManager", "setImsProvisioningString: ImsService unavailable for sub '" + i + "' for key:" + i2);
            return 1;
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    private int getSlotIndexOrException(int i) throws ImsException {
        int slotIndex = SubscriptionManager.getSlotIndex(i);
        if (SubscriptionManager.isValidSlotIndex(slotIndex)) {
            return slotIndex;
        }
        throw new ImsException("Invalid Subscription Id, subId=" + i, 3);
    }

    private int getSlotIndex(int i) {
        int slotIndex = SubscriptionManager.getSlotIndex(i);
        if (SubscriptionManager.isValidSlotIndex(slotIndex)) {
            return slotIndex;
        }
        return -1;
    }

    public int getNetworkTypeForSubscriber(int i, String str, String str2) {
        int targetSdk = getTargetSdk(str);
        if (targetSdk > 29) {
            return getDataNetworkTypeForSubscriber(i, str, str2);
        }
        if (targetSdk == 29 && !TelephonyPermissions.checkCallingOrSelfReadPhoneStateNoThrow(this.mApp, i, str, str2, "getNetworkTypeForSubscriber")) {
            return 0;
        }
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            Phone phone = getPhone(i);
            if (phone != null) {
                return phone.getServiceState().getDataNetworkType();
            }
            return 0;
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public int getDataNetworkType(String str, String str2) {
        return getDataNetworkTypeForSubscriber(getDefaultSubscription(), str, str2);
    }

    public int getDataNetworkTypeForSubscriber(int i, String str, String str2) {
        if (!TelephonyPermissions.checkCallingOrSelfReadPhoneState(this.mApp, i, str, str2, "getDataNetworkTypeForSubscriber")) {
            return 0;
        }
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            Phone phone = getPhone(i);
            if (phone != null) {
                return phone.getServiceState().getDataNetworkType();
            }
            return 0;
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public int getVoiceNetworkTypeForSubscriber(int i, String str, String str2) {
        if (!TelephonyPermissions.checkCallingOrSelfReadPhoneState(this.mApp, i, str, str2, "getDataNetworkTypeForSubscriber")) {
            return 0;
        }
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            Phone phone = getPhone(i);
            if (phone != null) {
                return phone.getServiceState().getVoiceNetworkType();
            }
            return 0;
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public boolean hasIccCard() {
        return hasIccCardUsingSlotIndex(this.mSubscriptionController.getSlotIndex(getDefaultSubscription()));
    }

    public boolean hasIccCardUsingSlotIndex(int i) {
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            Phone phone = PhoneFactory.getPhone(i);
            if (phone != null) {
                return phone.getIccCard().hasIccCard();
            }
            return false;
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public int getLteOnCdmaMode(String str, String str2) {
        return getLteOnCdmaModeForSubscriber(getDefaultSubscription(), str, str2);
    }

    public int getLteOnCdmaModeForSubscriber(int i, String str, String str2) {
        try {
            enforceReadPrivilegedPermission("getLteOnCdmaModeForSubscriber");
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                Phone phone = getPhone(i);
                if (phone == null) {
                    return -1;
                }
                return phone.getLteOnCdmaMode();
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        } catch (SecurityException unused) {
            return -1;
        }
    }

    private int getDefaultSubscription() {
        return this.mSubscriptionController.getDefaultSubId();
    }

    private int getSlotForDefaultSubscription() {
        return this.mSubscriptionController.getPhoneId(getDefaultSubscription());
    }

    private int getPreferredVoiceSubscription() {
        return this.mSubscriptionController.getDefaultVoiceSubId();
    }

    private boolean isActiveSubscription(int i) {
        return this.mSubscriptionController.isActiveSubId(i);
    }

    private Phone getPhoneFromSlotIdOrThrowException(int i) {
        int phoneIdFromSlotId = UiccController.getInstance().getPhoneIdFromSlotId(i);
        if (phoneIdFromSlotId == -1) {
            throw new IllegalArgumentException("Given slot index: " + i + " does not correspond to an active phone");
        }
        return PhoneFactory.getPhone(phoneIdFromSlotId);
    }

    public IccOpenLogicalChannelResponse iccOpenLogicalChannel(int i, String str, String str2, int i2) {
        TelephonyPermissions.enforceCallingOrSelfModifyPermissionOrCarrierPrivilege(this.mApp, i, "iccOpenLogicalChannel");
        this.mAppOps.checkPackage(Binder.getCallingUid(), str);
        return iccOpenLogicalChannelWithPermission(getPhoneFromSubId(i), str, str2, i2);
    }

    public IccOpenLogicalChannelResponse iccOpenLogicalChannelBySlot(int i, String str, String str2, int i2) {
        enforceModifyPermission();
        this.mAppOps.checkPackage(Binder.getCallingUid(), str);
        return iccOpenLogicalChannelWithPermission(getPhoneFromSlotIdOrThrowException(i), str, str2, i2);
    }

    private IccOpenLogicalChannelResponse iccOpenLogicalChannelWithPermission(Phone phone, String str, String str2, int i) {
        ComponentInfo componentInfoFindBestComponent;
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            if (TextUtils.equals("A0000005591010FFFFFFFF8900000100", str2) && ((componentInfoFindBestComponent = EuiccConnector.findBestComponent(getDefaultPhone().getContext().getPackageManager())) == null || !TextUtils.equals(str, componentInfoFindBestComponent.packageName))) {
                loge("The calling package is not allowed to access ISD-R.");
                throw new SecurityException("The calling package is not allowed to access ISD-R.");
            }
            return (IccOpenLogicalChannelResponse) sendRequest(9, new Pair(str2, Integer.valueOf(i)), phone, null);
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public boolean iccCloseLogicalChannel(int i, int i2) {
        TelephonyPermissions.enforceCallingOrSelfModifyPermissionOrCarrierPrivilege(this.mApp, i, "iccCloseLogicalChannel");
        return iccCloseLogicalChannelWithPermission(getPhoneFromSubId(i), i2);
    }

    public boolean iccCloseLogicalChannelBySlot(int i, int i2) {
        enforceModifyPermission();
        return iccCloseLogicalChannelWithPermission(getPhoneFromSlotIdOrThrowException(i), i2);
    }

    private boolean iccCloseLogicalChannelWithPermission(Phone phone, int i) {
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        if (i >= 0) {
            try {
                return ((Boolean) sendRequest(11, Integer.valueOf(i), phone, null)).booleanValue();
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        }
        return false;
    }

    public String iccTransmitApduLogicalChannel(int i, int i2, int i3, int i4, int i5, int i6, int i7, String str) {
        TelephonyPermissions.enforceCallingOrSelfModifyPermissionOrCarrierPrivilege(this.mApp, i, "iccTransmitApduLogicalChannel");
        return iccTransmitApduLogicalChannelWithPermission(getPhoneFromSubId(i), i2, i3, i4, i5, i6, i7, str);
    }

    public String iccTransmitApduLogicalChannelBySlot(int i, int i2, int i3, int i4, int i5, int i6, int i7, String str) {
        enforceModifyPermission();
        return iccTransmitApduLogicalChannelWithPermission(getPhoneFromSlotIdOrThrowException(i), i2, i3, i4, i5, i6, i7, str);
    }

    private String iccTransmitApduLogicalChannelWithPermission(Phone phone, int i, int i2, int i3, int i4, int i5, int i6, String str) {
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        if (i <= 0) {
            return "";
        }
        try {
            IccIoResult iccIoResult = (IccIoResult) sendRequest(7, new IccAPDUArgument(i, i2, i3, i4, i5, i6, str), phone, null);
            String strSubstring = Integer.toHexString((iccIoResult.sw1 << 8) + iccIoResult.sw2 + 65536).substring(1);
            if (iccIoResult.payload != null) {
                strSubstring = IccUtils.bytesToHexString(iccIoResult.payload) + strSubstring;
            }
            return strSubstring;
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public String iccTransmitApduBasicChannel(int i, String str, int i2, int i3, int i4, int i5, int i6, String str2) {
        TelephonyPermissions.enforceCallingOrSelfModifyPermissionOrCarrierPrivilege(this.mApp, i, "iccTransmitApduBasicChannel");
        this.mAppOps.checkPackage(Binder.getCallingUid(), str);
        return iccTransmitApduBasicChannelWithPermission(getPhoneFromSubId(i), str, i2, i3, i4, i5, i6, str2);
    }

    public String iccTransmitApduBasicChannelBySlot(int i, String str, int i2, int i3, int i4, int i5, int i6, String str2) {
        enforceModifyPermission();
        this.mAppOps.checkPackage(Binder.getCallingUid(), str);
        return iccTransmitApduBasicChannelWithPermission(getPhoneFromSlotIdOrThrowException(i), str, i2, i3, i4, i5, i6, str2);
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0082 A[Catch: all -> 0x009b, TRY_LEAVE, TryCatch #0 {all -> 0x009b, blocks: (B:9:0x0019, B:11:0x0023, B:13:0x0035, B:16:0x003f, B:17:0x0047, B:21:0x004e, B:23:0x0082), top: B:29:0x0019 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.String iccTransmitApduBasicChannelWithPermission(com.android.internal.telephony.Phone r13, java.lang.String r14, int r15, int r16, int r17, int r18, int r19, java.lang.String r20) {
        /*
            r12 = this;
            java.lang.String r0 = "The calling package is not allowed to select ISD-R."
            long r1 = android.os.Binder.clearCallingIdentity()
            r3 = 164(0xa4, float:2.3E-43)
            r7 = r16
            if (r7 != r3) goto L48
            r3 = 4
            r8 = r17
            if (r8 != r3) goto L4a
            if (r18 != 0) goto L4a
            r3 = 16
            r10 = r19
            if (r10 != r3) goto L4c
            java.lang.String r3 = "A0000005591010FFFFFFFF8900000100"
            r11 = r20
            boolean r3 = android.text.TextUtils.equals(r3, r11)     // Catch: java.lang.Throwable -> L9b
            if (r3 == 0) goto L4e
            com.android.internal.telephony.Phone r3 = r12.getDefaultPhone()     // Catch: java.lang.Throwable -> L9b
            android.content.Context r3 = r3.getContext()     // Catch: java.lang.Throwable -> L9b
            android.content.pm.PackageManager r3 = r3.getPackageManager()     // Catch: java.lang.Throwable -> L9b
            android.content.pm.ComponentInfo r3 = com.android.internal.telephony.euicc.EuiccConnector.findBestComponent(r3)     // Catch: java.lang.Throwable -> L9b
            if (r3 == 0) goto L3f
            java.lang.String r3 = r3.packageName     // Catch: java.lang.Throwable -> L9b
            r4 = r14
            boolean r3 = android.text.TextUtils.equals(r14, r3)     // Catch: java.lang.Throwable -> L9b
            if (r3 == 0) goto L3f
            goto L4e
        L3f:
            loge(r0)     // Catch: java.lang.Throwable -> L9b
            java.lang.SecurityException r3 = new java.lang.SecurityException     // Catch: java.lang.Throwable -> L9b
            r3.<init>(r0)     // Catch: java.lang.Throwable -> L9b
            throw r3     // Catch: java.lang.Throwable -> L9b
        L48:
            r8 = r17
        L4a:
            r10 = r19
        L4c:
            r11 = r20
        L4e:
            r0 = 29
            com.android.phone.PhoneInterfaceManager$IccAPDUArgument r3 = new com.android.phone.PhoneInterfaceManager$IccAPDUArgument     // Catch: java.lang.Throwable -> L9b
            r5 = 0
            r4 = r3
            r6 = r15
            r7 = r16
            r8 = r17
            r9 = r18
            r10 = r19
            r11 = r20
            r4.<init>(r5, r6, r7, r8, r9, r10, r11)     // Catch: java.lang.Throwable -> L9b
            r4 = 0
            r5 = r12
            r6 = r13
            java.lang.Object r0 = r12.sendRequest(r0, r3, r13, r4)     // Catch: java.lang.Throwable -> L9b
            com.android.internal.telephony.uicc.IccIoResult r0 = (com.android.internal.telephony.uicc.IccIoResult) r0     // Catch: java.lang.Throwable -> L9b
            int r3 = r0.sw1     // Catch: java.lang.Throwable -> L9b
            int r3 = r3 << 8
            int r4 = r0.sw2     // Catch: java.lang.Throwable -> L9b
            int r3 = r3 + r4
            r4 = 65536(0x10000, float:9.1835E-41)
            int r3 = r3 + r4
            java.lang.String r3 = java.lang.Integer.toHexString(r3)     // Catch: java.lang.Throwable -> L9b
            r4 = 1
            java.lang.String r3 = r3.substring(r4)     // Catch: java.lang.Throwable -> L9b
            byte[] r4 = r0.payload     // Catch: java.lang.Throwable -> L9b
            if (r4 == 0) goto L97
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L9b
            r4.<init>()     // Catch: java.lang.Throwable -> L9b
            byte[] r0 = r0.payload     // Catch: java.lang.Throwable -> L9b
            java.lang.String r0 = com.android.internal.telephony.uicc.IccUtils.bytesToHexString(r0)     // Catch: java.lang.Throwable -> L9b
            r4.append(r0)     // Catch: java.lang.Throwable -> L9b
            r4.append(r3)     // Catch: java.lang.Throwable -> L9b
            java.lang.String r3 = r4.toString()     // Catch: java.lang.Throwable -> L9b
        L97:
            android.os.Binder.restoreCallingIdentity(r1)
            return r3
        L9b:
            r0 = move-exception
            android.os.Binder.restoreCallingIdentity(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.phone.PhoneInterfaceManager.iccTransmitApduBasicChannelWithPermission(com.android.internal.telephony.Phone, java.lang.String, int, int, int, int, int, java.lang.String):java.lang.String");
    }

    public byte[] iccExchangeSimIO(int i, int i2, int i3, int i4, int i5, int i6, String str) {
        byte[] bArr;
        int length;
        TelephonyPermissions.enforceCallingOrSelfModifyPermissionOrCarrierPrivilege(this.mApp, i, "iccExchangeSimIO");
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            IccIoResult iccIoResult = (IccIoResult) sendRequest(31, new IccAPDUArgument(-1, i2, i3, i4, i5, i6, str), Integer.valueOf(i));
            if (iccIoResult.payload != null) {
                length = iccIoResult.payload.length + 2;
                bArr = new byte[length];
                System.arraycopy(iccIoResult.payload, 0, bArr, 0, iccIoResult.payload.length);
            } else {
                bArr = new byte[2];
                length = 2;
            }
            bArr[length - 1] = (byte) iccIoResult.sw2;
            bArr[length - 2] = (byte) iccIoResult.sw1;
            return bArr;
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public String[] getForbiddenPlmns(int i, int i2, String str, String str2) {
        if (!TelephonyPermissions.checkCallingOrSelfReadPhoneState(this.mApp, i, str, str2, "getForbiddenPlmns")) {
            return null;
        }
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            if (i2 != 2 && i2 != 1) {
                loge("getForbiddenPlmnList(): App Type must be USIM or SIM");
                return null;
            }
            Object objSendRequest = sendRequest(48, new Integer(i2), Integer.valueOf(i));
            if (objSendRequest instanceof String[]) {
                return (String[]) objSendRequest;
            }
            return null;
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public int setForbiddenPlmns(int i, int i2, List<String> list, String str, String str2) {
        if (!TelephonyPermissions.checkCallingOrSelfReadPhoneState(this.mApp, i, str, str2, "setForbiddenPlmns")) {
            throw new IllegalStateException("No Permissions for setForbiddenPlmns");
        }
        if (i2 != 2 && i2 != 1) {
            loge("setForbiddenPlmnList(): App Type must be USIM or SIM");
            throw new IllegalArgumentException("Invalid appType: App Type must be USIM or SIM");
        }
        if (list == null) {
            throw new IllegalArgumentException("Fplmn List provided is null");
        }
        for (String str3 : list) {
            if (!CellIdentity.isValidPlmn(str3)) {
                throw new IllegalArgumentException("Invalid fplmn provided: " + str3);
            }
        }
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            return ((Integer) sendRequest(72, new Pair(new Integer(i2), list), Integer.valueOf(i))).intValue();
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public String sendEnvelopeWithStatus(int i, String str) {
        TelephonyPermissions.enforceCallingOrSelfModifyPermissionOrCarrierPrivilege(this.mApp, i, "sendEnvelopeWithStatus");
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            IccIoResult iccIoResult = (IccIoResult) sendRequest(25, str, Integer.valueOf(i));
            if (iccIoResult.payload == null) {
                return "";
            }
            return IccUtils.bytesToHexString(iccIoResult.payload) + Integer.toHexString((iccIoResult.sw1 << 8) + iccIoResult.sw2 + 65536).substring(1);
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public String nvReadItem(int i) {
        WorkSource workSource = getWorkSource(Binder.getCallingUid());
        TelephonyPermissions.enforceCallingOrSelfModifyPermissionOrCarrierPrivilege(this.mApp, getDefaultSubscription(), "nvReadItem");
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            return (String) sendRequest(13, Integer.valueOf(i), workSource);
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public boolean nvWriteItem(int i, String str) {
        WorkSource workSource = getWorkSource(Binder.getCallingUid());
        TelephonyPermissions.enforceCallingOrSelfModifyPermissionOrCarrierPrivilege(this.mApp, getDefaultSubscription(), "nvWriteItem");
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            return ((Boolean) sendRequest(15, new Pair(Integer.valueOf(i), str), workSource)).booleanValue();
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public boolean nvWriteCdmaPrl(byte[] bArr) {
        TelephonyPermissions.enforceCallingOrSelfModifyPermissionOrCarrierPrivilege(this.mApp, getDefaultSubscription(), "nvWriteCdmaPrl");
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            return ((Boolean) sendRequest(17, bArr)).booleanValue();
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public boolean resetModemConfig(int i) {
        Phone phone = PhoneFactory.getPhone(i);
        if (phone == null) {
            return false;
        }
        TelephonyPermissions.enforceCallingOrSelfModifyPermissionOrCarrierPrivilege(this.mApp, phone.getSubId(), "resetModemConfig");
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            return ((Boolean) sendRequest(19, null)).booleanValue();
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public boolean rebootModem(int i) {
        Phone phone = PhoneFactory.getPhone(i);
        if (phone == null) {
            return false;
        }
        TelephonyPermissions.enforceCallingOrSelfModifyPermissionOrCarrierPrivilege(this.mApp, phone.getSubId(), "rebootModem");
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            return ((Boolean) sendRequest(64, null)).booleanValue();
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public String[] getPcscfAddress(String str, String str2, String str3) {
        Phone defaultPhone = getDefaultPhone();
        if (!TelephonyPermissions.checkCallingOrSelfReadPhoneState(this.mApp, defaultPhone.getSubId(), str2, str3, "getPcscfAddress")) {
            return new String[0];
        }
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            return defaultPhone.getPcscfAddress(str);
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public void resetIms(int i) {
        enforceModifyPermission();
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            if (this.mImsResolver == null) {
                return;
            }
            this.mImsResolver.disableIms(i);
            this.mImsResolver.enableIms(i);
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public void enableIms(int i) {
        enforceModifyPermission();
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            if (this.mImsResolver == null) {
                return;
            }
            this.mImsResolver.enableIms(i);
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public void disableIms(int i) {
        enforceModifyPermission();
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            if (this.mImsResolver == null) {
                return;
            }
            this.mImsResolver.disableIms(i);
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public IImsMmTelFeature getMmTelFeatureAndListen(int i, IImsServiceFeatureCallback iImsServiceFeatureCallback) {
        enforceModifyPermission();
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            if (this.mImsResolver != null) {
                return this.mImsResolver.getMmTelFeatureAndListen(i, iImsServiceFeatureCallback);
            }
            return null;
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public IImsRcsFeature getRcsFeatureAndListen(int i, IImsServiceFeatureCallback iImsServiceFeatureCallback) {
        enforceModifyPermission();
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            if (this.mImsResolver != null) {
                return this.mImsResolver.getRcsFeatureAndListen(i, iImsServiceFeatureCallback);
            }
            return null;
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public void unregisterImsFeatureCallback(int i, int i2, IImsServiceFeatureCallback iImsServiceFeatureCallback) {
        enforceModifyPermission();
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            if (this.mImsResolver == null) {
                return;
            }
            this.mImsResolver.unregisterImsFeatureCallback(i, i2, iImsServiceFeatureCallback);
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public IImsRegistration getImsRegistration(int i, int i2) throws RemoteException {
        enforceModifyPermission();
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            if (this.mImsResolver != null) {
                return this.mImsResolver.getImsRegistration(i, i2);
            }
            return null;
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public IImsConfig getImsConfig(int i, int i2) throws RemoteException {
        enforceModifyPermission();
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            if (this.mImsResolver != null) {
                return this.mImsResolver.getImsConfig(i, i2);
            }
            return null;
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public boolean setBoundImsServiceOverride(int i, boolean z, int[] iArr, String str) {
        int[] subId = SubscriptionManager.getSubId(i);
        TelephonyPermissions.enforceShellOnly(Binder.getCallingUid(), "setBoundImsServiceOverride");
        TelephonyPermissions.enforceCallingOrSelfModifyPermissionOrCarrierPrivilege(this.mApp, subId != null ? subId[0] : -1, "setBoundImsServiceOverride");
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            if (this.mImsResolver == null) {
                return false;
            }
            HashMap map = new HashMap();
            for (int i2 : iArr) {
                map.put(Integer.valueOf(i2), str);
            }
            return this.mImsResolver.overrideImsServiceConfiguration(i, z, map);
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public String getBoundImsServicePackage(int i, boolean z, int i2) {
        int[] subId = SubscriptionManager.getSubId(i);
        TelephonyPermissions.enforeceCallingOrSelfReadPrivilegedPhoneStatePermissionOrCarrierPrivilege(this.mApp, subId != null ? subId[0] : -1, "getBoundImsServicePackage");
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            return this.mImsResolver == null ? "" : this.mImsResolver.getImsServiceConfiguration(i, z, i2);
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: android.os.ServiceSpecificException */
    public void getImsMmTelFeatureState(int i, final IIntegerConsumer iIntegerConsumer) throws ServiceSpecificException {
        enforceReadPrivilegedPermission("getImsMmTelFeatureState");
        if (!ImsManager.isImsSupportedOnDevice(this.mApp)) {
            throw new ServiceSpecificException(2, "IMS not available on device.");
        }
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            int slotIndex = getSlotIndex(i);
            if (slotIndex <= -1) {
                Log.w("PhoneInterfaceManager", "getImsMmTelFeatureState: called with an inactive subscription '" + i + "'");
                throw new ServiceSpecificException(3);
            }
            ImsManager.getInstance(this.mApp, slotIndex).getImsServiceState(new Consumer() { // from class: com.android.phone.-$$Lambda$PhoneInterfaceManager$jZjLFE775VMWjy_JJx6Ge__itFs
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    PhoneInterfaceManager.lambda$getImsMmTelFeatureState$3(iIntegerConsumer, (Integer) obj);
                }
            });
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    static /* synthetic */ void lambda$getImsMmTelFeatureState$3(IIntegerConsumer iIntegerConsumer, Integer num) {
        int iIntValue;
        if (num == null) {
            iIntValue = 0;
        } else {
            try {
                iIntValue = num.intValue();
            } catch (RemoteException unused) {
                Log.w("PhoneInterfaceManager", "getImsMmTelFeatureState: remote caller is no longer running. Ignore");
                return;
            }
        }
        iIntegerConsumer.accept(iIntValue);
    }

    public void setImsRegistrationState(boolean z) {
        enforceModifyPermission();
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            getDefaultPhone().setImsRegistrationState(z);
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public void setNetworkSelectionModeAutomatic(int i) {
        TelephonyPermissions.enforceCallingOrSelfModifyPermissionOrCarrierPrivilege(this.mApp, i, "setNetworkSelectionModeAutomatic");
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            if (isActiveSubscription(i)) {
                sendRequest(35, (Object) null, Integer.valueOf(i));
            }
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public boolean setNetworkSelectionModeManual(int i, OperatorInfo operatorInfo, boolean z) {
        TelephonyPermissions.enforceCallingOrSelfModifyPermissionOrCarrierPrivilege(this.mApp, i, "setNetworkSelectionModeManual");
        if (!isActiveSubscription(i)) {
            return false;
        }
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            return ((Boolean) sendRequest(41, new ManualNetworkSelectionArgument(operatorInfo, z), Integer.valueOf(i))).booleanValue();
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public String getManualNetworkSelectionPlmn(int i) {
        TelephonyPermissions.enforeceCallingOrSelfReadPrecisePhoneStatePermissionOrCarrierPrivilege(this.mApp, i, "getManualNetworkSelectionPlmn");
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            if (!isActiveSubscription(i)) {
                return "";
            }
            Phone phone = getPhone(i);
            if (phone == null) {
                return "";
            }
            OperatorInfo savedNetworkSelection = phone.getSavedNetworkSelection();
            return TextUtils.isEmpty(savedNetworkSelection.getOperatorNumeric()) ? phone.getManualNetworkSelectionPlmn() : savedNetworkSelection.getOperatorNumeric();
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public CellNetworkScanResult getCellNetworkScanResults(int i, String str, String str2) {
        TelephonyPermissions.enforceCallingOrSelfModifyPermissionOrCarrierPrivilege(this.mApp, i, "getCellNetworkScanResults");
        int i2 = AnonymousClass1.$SwitchMap$android$telephony$LocationAccessPolicy$LocationPermissionResult[LocationAccessPolicy.checkLocationPermission(this.mApp, new LocationAccessPolicy.LocationPermissionQuery.Builder().setCallingPackage(str).setCallingFeatureId(str2).setCallingPid(Binder.getCallingPid()).setCallingUid(Binder.getCallingUid()).setMethod("getCellNetworkScanResults").setMinSdkVersionForFine(29).build()).ordinal()];
        if (i2 == 1) {
            throw new SecurityException("Not allowed to access scan results -- location");
        }
        if (i2 == 2) {
            return null;
        }
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            return (CellNetworkScanResult) sendRequest(39, (Object) null, Integer.valueOf(i));
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public CallForwardingInfo getCallForwarding(int i, int i2) {
        enforceReadPrivilegedPermission("getCallForwarding");
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            return (CallForwardingInfo) sendRequest(83, Integer.valueOf(i2), Integer.valueOf(i));
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public boolean setCallForwarding(int i, CallForwardingInfo callForwardingInfo) {
        enforceModifyPermission();
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            return ((Boolean) sendRequest(85, callForwardingInfo, Integer.valueOf(i))).booleanValue();
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public int getCallWaitingStatus(int i) {
        enforceReadPrivilegedPermission("getCallForwarding");
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            return ((Integer) sendRequest(87, (Object) null, Integer.valueOf(i))).intValue();
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public boolean setCallWaitingStatus(int i, boolean z) {
        enforceModifyPermission();
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            return ((Boolean) sendRequest(89, Boolean.valueOf(z), Integer.valueOf(i))).booleanValue();
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public int requestNetworkScan(int i, NetworkScanRequest networkScanRequest, Messenger messenger, IBinder iBinder, String str, String str2) {
        SecurityException securityExceptionCheckNetworkRequestForSanitizedLocationAccess;
        TelephonyPermissions.enforceCallingOrSelfModifyPermissionOrCarrierPrivilege(this.mApp, i, "requestNetworkScan");
        LocationAccessPolicy.LocationPermissionResult locationPermissionResultCheckLocationPermission = LocationAccessPolicy.checkLocationPermission(this.mApp, new LocationAccessPolicy.LocationPermissionQuery.Builder().setCallingPackage(str).setCallingFeatureId(str2).setCallingPid(Binder.getCallingPid()).setCallingUid(Binder.getCallingUid()).setMethod("requestNetworkScan").setMinSdkVersionForFine(29).build());
        if (locationPermissionResultCheckLocationPermission != LocationAccessPolicy.LocationPermissionResult.ALLOWED && (securityExceptionCheckNetworkRequestForSanitizedLocationAccess = checkNetworkRequestForSanitizedLocationAccess(networkScanRequest, i, str)) != null) {
            if (locationPermissionResultCheckLocationPermission == LocationAccessPolicy.LocationPermissionResult.DENIED_HARD) {
                throw securityExceptionCheckNetworkRequestForSanitizedLocationAccess;
            }
            loge(securityExceptionCheckNetworkRequestForSanitizedLocationAccess.getMessage());
            return -1;
        }
        int callingUid = Binder.getCallingUid();
        int callingPid = Binder.getCallingPid();
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            return this.mNetworkScanRequestTracker.startNetworkScan(networkScanRequest, messenger, iBinder, getPhone(i), callingUid, callingPid, str);
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    private SecurityException checkNetworkRequestForSanitizedLocationAccess(NetworkScanRequest networkScanRequest, int i, String str) {
        int iCheckCarrierPrivilegesForPackage = checkCarrierPrivilegesForPackage(i, str);
        boolean z = iCheckCarrierPrivilegesForPackage == 1;
        boolean z2 = this.mApp.checkCallingOrSelfPermission("android.permission.NETWORK_SCAN") == 0;
        if (!z && !z2) {
            return new SecurityException("permission.NETWORK_SCAN or carrier privileges is needed for network scans without location access.");
        }
        if (networkScanRequest.getSpecifiers() == null || networkScanRequest.getSpecifiers().length <= 0) {
            return null;
        }
        for (RadioAccessSpecifier radioAccessSpecifier : networkScanRequest.getSpecifiers()) {
            if (radioAccessSpecifier.getChannels() != null && radioAccessSpecifier.getChannels().length > 0) {
                return new SecurityException("Specific channels must not be scanned without location access.");
            }
        }
        return null;
    }

    public void stopNetworkScan(int i, int i2) {
        TelephonyPermissions.enforceCallingOrSelfModifyPermissionOrCarrierPrivilege(this.mApp, i, "stopNetworkScan");
        int callingUid = Binder.getCallingUid();
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            this.mNetworkScanRequestTracker.stopNetworkScan(i2, callingUid);
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public int getCalculatedPreferredNetworkType(String str, String str2) {
        Phone defaultPhone = getDefaultPhone();
        if (!TelephonyPermissions.checkCallingOrSelfReadPhoneState(this.mApp, defaultPhone.getSubId(), str, str2, "getCalculatedPreferredNetworkType")) {
            return RILConstants.PREFERRED_NETWORK_MODE;
        }
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            return PhoneFactory.calculatePreferredNetworkType(defaultPhone.getContext(), 0);
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public int getPreferredNetworkType(int i) {
        TelephonyPermissions.enforeceCallingOrSelfReadPrivilegedPhoneStatePermissionOrCarrierPrivilege(this.mApp, i, "getPreferredNetworkType");
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            int[] iArr = (int[]) sendRequest(21, (Object) null, Integer.valueOf(i));
            return iArr != null ? iArr[0] : -1;
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public boolean setPreferredNetworkType(int i, int i2) {
        TelephonyPermissions.enforceCallingOrSelfModifyPermissionOrCarrierPrivilege(this.mApp, i, "setPreferredNetworkType");
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            Boolean bool = (Boolean) sendRequest(23, Integer.valueOf(i2), Integer.valueOf(i));
            if (bool.booleanValue()) {
                Settings.Global.putInt(this.mApp.getContentResolver(), "preferred_network_mode" + i, i2);
            }
            return bool.booleanValue();
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public long getAllowedNetworkTypes(int i) {
        TelephonyPermissions.enforeceCallingOrSelfReadPrivilegedPhoneStatePermissionOrCarrierPrivilege(this.mApp, i, "getAllowedNetworkTypes");
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            return SubscriptionManager.getLongSubscriptionProperty(i, "allowed_network_types", -1L, this.mApp);
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public boolean setAllowedNetworkTypes(int i, long j) {
        TelephonyPermissions.enforceCallingOrSelfModifyPermissionOrCarrierPrivilege(this.mApp, i, "setAllowedNetworkTypes");
        SubscriptionManager.setSubscriptionProperty(i, "allowed_network_types", String.valueOf(j));
        return setPreferredNetworkType(i, Settings.Global.getInt(this.mApp.getContentResolver(), "preferred_network_mode" + i, RILConstants.PREFERRED_NETWORK_MODE));
    }

    public long getAllowedNetworkTypesForReason(int i, int i2) {
        TelephonyPermissions.enforeceCallingOrSelfReadPrivilegedPhoneStatePermissionOrCarrierPrivilege(this.mApp, i, "getAllowedNetworkTypesForReason");
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            return getPhoneFromSubId(i).getAllowedNetworkTypes(i2);
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public long getEffectiveAllowedNetworkTypes(int i) {
        TelephonyPermissions.enforeceCallingOrSelfReadPrivilegedPhoneStatePermissionOrCarrierPrivilege(this.mApp, i, "getEffectiveAllowedNetworkTypes");
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            return getPhoneFromSubId(i).getEffectiveAllowedNetworkTypes();
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public boolean setAllowedNetworkTypesForReason(int i, int i2, long j) {
        TelephonyPermissions.enforceCallingOrSelfModifyPermissionOrCarrierPrivilege(this.mApp, i, "setAllowedNetworkTypesForReason");
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            getPhoneFromSubId(i).setAllowedNetworkTypes(i2, j);
            return setPreferredNetworkType(i, Settings.Global.getInt(this.mApp.getContentResolver(), "preferred_network_mode" + i, RILConstants.PREFERRED_NETWORK_MODE));
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public boolean isTetheringApnRequiredForSubscriber(int i) {
        enforceModifyPermission();
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        Phone phone = getPhone(i);
        if (phone != null) {
            try {
                return phone.hasMatchedTetherApnSetting();
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        }
        return false;
    }

    public void setUserDataEnabled(int i, boolean z) {
        TelephonyPermissions.enforceCallingOrSelfModifyPermissionOrCarrierPrivilege(this.mApp, i, "setUserDataEnabled");
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            Phone phone = PhoneFactory.getPhone(this.mSubscriptionController.getPhoneId(i));
            if (phone != null) {
                phone.getDataEnabledSettings().setUserDataEnabled(z);
            } else {
                loge("setUserDataEnabled: no phone found. Invalid subId=" + i);
            }
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public void setAlwaysReportSignalStrength(int i, boolean z) {
        enforceModifyPermission();
        enforceSystemCaller();
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        Phone phone = getPhone(i);
        try {
            if (phone != null) {
                phone.setAlwaysReportSignalStrength(z);
            } else {
                loge("setAlwaysReportSignalStrength: no phone found for subId=" + i);
            }
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public boolean getDataEnabled(int i) {
        return isUserDataEnabled(i);
    }

    public boolean isUserDataEnabled(int i) {
        try {
            this.mApp.enforceCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE", null);
        } catch (Exception unused) {
            TelephonyPermissions.enforceCallingOrSelfModifyPermissionOrCarrierPrivilege(this.mApp, i, "isUserDataEnabled");
        }
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            Phone phone = PhoneFactory.getPhone(this.mSubscriptionController.getPhoneId(i));
            if (phone != null) {
                return phone.isUserDataEnabled();
            }
            return false;
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public boolean isDataEnabled(int i) {
        enforceReadPrivilegedPermission("isDataEnabled");
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            Phone phone = PhoneFactory.getPhone(this.mSubscriptionController.getPhoneId(i));
            if (phone != null) {
                return phone.getDataEnabledSettings().isDataEnabled();
            }
            return false;
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    private int getCarrierPrivilegeStatusFromCarrierConfigRules(int i, int i2, Phone phone) {
        if (i2 == 1001) {
            return 1;
        }
        SubscriptionController subscriptionController = SubscriptionController.getInstance();
        if (i == 1 || subscriptionController == null) {
            return i;
        }
        String[] packagesForUid = phone.getContext().getPackageManager().getPackagesForUid(i2);
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            SubscriptionInfo subscriptionInfo = subscriptionController.getSubscriptionInfo(phone.getSubId());
            SubscriptionManager subscriptionManager = (SubscriptionManager) phone.getContext().getSystemService("telephony_subscription_service");
            for (String str : packagesForUid) {
                if (subscriptionManager.canManageSubscription(subscriptionInfo, str)) {
                    return 1;
                }
            }
            return i;
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    private int getCarrierPrivilegeStatusFromCarrierConfigRules(int i, Phone phone, String str) {
        SubscriptionController subscriptionController = SubscriptionController.getInstance();
        if (i == 1 || subscriptionController == null) {
            return i;
        }
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            if (((SubscriptionManager) phone.getContext().getSystemService("telephony_subscription_service")).canManageSubscription(subscriptionController.getSubscriptionInfo(phone.getSubId()), str)) {
                i = 1;
            }
            return i;
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public int getCarrierPrivilegeStatus(int i) {
        Phone phone = getPhone(i);
        if (phone == null) {
            loge("getCarrierPrivilegeStatus: Invalid subId");
            return 0;
        }
        UiccCard uiccCard = UiccController.getInstance().getUiccCard(phone.getPhoneId());
        if (uiccCard == null) {
            loge("getCarrierPrivilegeStatus: No UICC");
            return -1;
        }
        return getCarrierPrivilegeStatusFromCarrierConfigRules(uiccCard.getCarrierPrivilegeStatusForCurrentTransaction(phone.getContext().getPackageManager()), Binder.getCallingUid(), phone);
    }

    public int getCarrierPrivilegeStatusForUid(int i, int i2) {
        enforceReadPrivilegedPermission("getCarrierPrivilegeStatusForUid");
        Phone phone = getPhone(i);
        if (phone == null) {
            loge("getCarrierPrivilegeStatusForUid: Invalid subId");
            return 0;
        }
        UiccProfile uiccProfileForPhone = UiccController.getInstance().getUiccProfileForPhone(phone.getPhoneId());
        if (uiccProfileForPhone == null) {
            loge("getCarrierPrivilegeStatusForUid: No UICC");
            return -1;
        }
        return getCarrierPrivilegeStatusFromCarrierConfigRules(uiccProfileForPhone.getCarrierPrivilegeStatusForUid(phone.getContext().getPackageManager(), i2), i2, phone);
    }

    public int checkCarrierPrivilegesForPackage(int i, String str) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        int phoneId = SubscriptionManager.getPhoneId(i);
        UiccCard uiccCard = UiccController.getInstance().getUiccCard(phoneId);
        if (uiccCard == null) {
            loge("checkCarrierPrivilegesForPackage: No UICC on subId " + i);
            return -1;
        }
        return getCarrierPrivilegeStatusFromCarrierConfigRules(uiccCard.getCarrierPrivilegeStatus(this.mApp.getPackageManager(), str), getPhone(phoneId), str);
    }

    public int checkCarrierPrivilegesForPackageAnyPhone(String str) {
        UiccCard uiccCard;
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        int carrierPrivilegeStatusFromCarrierConfigRules = -1;
        for (int i = 0; i < TelephonyManager.getDefault().getPhoneCount() && ((uiccCard = UiccController.getInstance().getUiccCard(i)) == null || (carrierPrivilegeStatusFromCarrierConfigRules = getCarrierPrivilegeStatusFromCarrierConfigRules(uiccCard.getCarrierPrivilegeStatus(this.mApp.getPackageManager(), str), getPhone(i), str)) != 1); i++) {
        }
        return carrierPrivilegeStatusFromCarrierConfigRules;
    }

    public List<String> getCarrierPackageNamesForIntentAndPhone(Intent intent, int i) {
        if (!SubscriptionManager.isValidPhoneId(i)) {
            loge("phoneId " + i + " is not valid.");
            return null;
        }
        UiccCard uiccCard = UiccController.getInstance().getUiccCard(i);
        if (uiccCard == null) {
            loge("getCarrierPackageNamesForIntentAndPhone: No UICC");
            return null;
        }
        return uiccCard.getCarrierPackageNamesForIntent(this.mApp.getPackageManager(), intent);
    }

    public List<String> getPackagesWithCarrierPrivileges(int i) {
        PackageManager packageManager = this.mApp.getPackageManager();
        ArrayList arrayList = new ArrayList();
        UiccCard uiccCard = UiccController.getInstance().getUiccCard(i);
        if (uiccCard != null && uiccCard.hasCarrierPrivilegeRules()) {
            List installedPackagesAsUser = packageManager.getInstalledPackagesAsUser(134251008, UserHandle.SYSTEM.getIdentifier());
            for (int size = installedPackagesAsUser.size() - 1; size >= 0; size--) {
                PackageInfo packageInfo = (PackageInfo) installedPackagesAsUser.get(size);
                if (packageInfo != null && packageInfo.packageName != null && uiccCard.getCarrierPrivilegeStatus(packageInfo) == 1) {
                    arrayList.add(packageInfo.packageName);
                }
            }
        }
        return arrayList;
    }

    public List<String> getPackagesWithCarrierPrivilegesForAllPhones() {
        enforceReadPrivilegedPermission("getPackagesWithCarrierPrivilegesForAllPhones");
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < TelephonyManager.getDefault().getPhoneCount(); i++) {
            try {
                arrayList.addAll(getPackagesWithCarrierPrivileges(i));
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        }
        return arrayList;
    }

    private String getIccId(int i) {
        Phone phone = getPhone(i);
        UiccCard uiccCard = phone == null ? null : phone.getUiccCard();
        if (uiccCard == null) {
            loge("getIccId: No UICC");
            return null;
        }
        String iccId = uiccCard.getIccId();
        if (!TextUtils.isEmpty(iccId)) {
            return iccId;
        }
        loge("getIccId: ICC ID is null or empty.");
        return null;
    }

    public boolean setLine1NumberForDisplayForSubscriber(int i, String str, String str2) {
        TelephonyPermissions.enforceCallingOrSelfCarrierPrivilege(this.mApp, i, "setLine1NumberForDisplayForSubscriber");
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            String iccId = getIccId(i);
            Phone phone = getPhone(i);
            if (phone == null) {
                return false;
            }
            String subscriberId = phone.getSubscriberId();
            if (TextUtils.isEmpty(iccId)) {
                return false;
            }
            SharedPreferences.Editor editorEdit = this.mTelephonySharedPreferences.edit();
            String str3 = "carrier_alphtag_" + iccId;
            if (str == null) {
                editorEdit.remove(str3);
            } else {
                editorEdit.putString(str3, str);
            }
            String str4 = "carrier_number_" + iccId;
            String str5 = "carrier_subscriber_" + iccId;
            if (str2 == null) {
                editorEdit.remove(str4);
                editorEdit.remove(str5);
            } else {
                editorEdit.putString(str4, str2);
                editorEdit.putString(str5, subscriberId);
            }
            editorEdit.commit();
            return true;
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public String getLine1NumberForDisplay(int i, String str, String str2) {
        if (!TelephonyPermissions.checkCallingOrSelfReadPhoneNumber(this.mApp, i, str, str2, "getLine1NumberForDisplay")) {
            return null;
        }
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            String iccId = getIccId(i);
            if (iccId == null) {
                return null;
            }
            return this.mTelephonySharedPreferences.getString("carrier_number_" + iccId, null);
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public String getLine1AlphaTagForDisplay(int i, String str, String str2) {
        if (!TelephonyPermissions.checkCallingOrSelfReadPhoneState(this.mApp, i, str, str2, "getLine1AlphaTagForDisplay")) {
            return null;
        }
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            String iccId = getIccId(i);
            if (iccId == null) {
                return null;
            }
            return this.mTelephonySharedPreferences.getString("carrier_alphtag_" + iccId, null);
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public String[] getMergedSubscriberIds(int i, String str, String str2) {
        if (!TelephonyPermissions.checkCallingOrSelfReadPhoneState(this.mApp, -1, str, str2, "getMergedSubscriberIds")) {
            return null;
        }
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            PhoneGlobals phoneGlobals = this.mApp;
            TelephonyManager telephonyManagerFrom = TelephonyManager.from(phoneGlobals);
            SubscriptionManager.from(phoneGlobals);
            ArraySet arraySet = new ArraySet();
            if (isActiveSubscription(i)) {
                arraySet.add(telephonyManagerFrom.getSubscriberId(i));
            }
            Map<String, ?> all = this.mTelephonySharedPreferences.getAll();
            String str3 = null;
            for (String str4 : all.keySet()) {
                if (str4.startsWith("carrier_subscriber_") && arraySet.contains((String) all.get(str4))) {
                    str3 = (String) all.get("carrier_number_" + str4.substring(19));
                    if (!TextUtils.isEmpty(str3)) {
                        break;
                    }
                }
            }
            if (TextUtils.isEmpty(str3)) {
                return null;
            }
            ArraySet arraySet2 = new ArraySet();
            for (String str5 : all.keySet()) {
                if (str5.startsWith("carrier_number_") && str3.equals((String) all.get(str5))) {
                    String str6 = (String) all.get("carrier_subscriber_" + str5.substring(15));
                    if (!TextUtils.isEmpty(str6)) {
                        arraySet2.add(str6);
                    }
                }
            }
            String[] strArr = (String[]) arraySet2.toArray(new String[arraySet2.size()]);
            Arrays.sort(strArr);
            return strArr;
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public String[] getMergedImsisFromGroup(int i, String str) {
        enforceReadPrivilegedPermission("getMergedImsisFromGroup");
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            TelephonyManager telephonyManager = (TelephonyManager) this.mApp.getSystemService(TelephonyManager.class);
            String subscriberId = telephonyManager.getSubscriberId(i);
            if (subscriberId != null) {
                ParcelUuid groupUuid = SubscriptionController.getInstance().getSubscriptionInfo(i).getGroupUuid();
                if (groupUuid == null) {
                    return new String[]{subscriberId};
                }
                ArrayList arrayList = new ArrayList();
                Iterator it = SubscriptionController.getInstance().getSubscriptionsInGroup(groupUuid, this.mApp.getOpPackageName(), this.mApp.getAttributionTag()).iterator();
                while (it.hasNext()) {
                    String subscriberId2 = telephonyManager.getSubscriberId(((SubscriptionInfo) it.next()).getSubscriptionId());
                    if (subscriberId2 != null) {
                        arrayList.add(subscriberId2);
                    }
                }
                return (String[]) arrayList.toArray(new String[arrayList.size()]);
            }
            return null;
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public boolean setOperatorBrandOverride(int i, String str) {
        TelephonyPermissions.enforceCallingOrSelfCarrierPrivilege(this.mApp, i, "setOperatorBrandOverride");
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            Phone phone = getPhone(i);
            return phone == null ? false : phone.setOperatorBrandOverride(str);
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public boolean setRoamingOverride(int i, List<String> list, List<String> list2, List<String> list3, List<String> list4) {
        TelephonyPermissions.enforceCallingOrSelfCarrierPrivilege(this.mApp, i, "setRoamingOverride");
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            Phone phone = getPhone(i);
            if (phone != null) {
                return phone.setRoamingOverride(list, list2, list3, list4);
            }
            return false;
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    @Deprecated
    public int invokeOemRilRequestRaw(byte[] bArr, byte[] bArr2) {
        int iOrdinal;
        AsyncResult asyncResult;
        enforceModifyPermission();
        try {
            asyncResult = (AsyncResult) sendRequest(27, bArr);
        } catch (RuntimeException unused) {
            Log.w("PhoneInterfaceManager", "sendOemRilRequestRaw: Runtime Exception");
            iOrdinal = CommandException.Error.GENERIC_FAILURE.ordinal();
            if (iOrdinal <= 0) {
                return iOrdinal;
            }
        }
        if (asyncResult.exception == null) {
            if (asyncResult.result == null) {
                return 0;
            }
            byte[] bArr3 = (byte[]) asyncResult.result;
            if (bArr3.length > bArr2.length) {
                Log.w("PhoneInterfaceManager", "Buffer to copy response too small: Response length is " + bArr3.length + "bytes. Buffer Size is " + bArr2.length + "bytes.");
            }
            System.arraycopy(bArr3, 0, bArr2, 0, bArr3.length);
            return bArr3.length;
        }
        iOrdinal = asyncResult.exception.getCommandError().ordinal();
        if (iOrdinal <= 0) {
            return iOrdinal;
        }
        return iOrdinal * (-1);
    }

    public void setRadioCapability(RadioAccessFamily[] radioAccessFamilyArr) {
        try {
            ProxyController.getInstance().setRadioCapability(radioAccessFamilyArr);
        } catch (RuntimeException unused) {
            Log.w("PhoneInterfaceManager", "setRadioCapability: Runtime Exception");
        }
    }

    public int getRadioAccessFamily(int i, String str) {
        Phone phone = PhoneFactory.getPhone(i);
        try {
            TelephonyPermissions.enforeceCallingOrSelfReadPrivilegedPhoneStatePermissionOrCarrierPrivilege(this.mApp, phone.getSubId(), "getRadioAccessFamily");
            if (phone == null) {
                return 0;
            }
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                TelephonyPermissions.enforeceCallingOrSelfReadPrivilegedPhoneStatePermissionOrCarrierPrivilege(this.mApp, phone.getSubId(), "getRadioAccessFamily");
                return ProxyController.getInstance().getRadioAccessFamily(i);
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        } catch (SecurityException e) {
            EventLog.writeEvent(1397638484, "150857259", -1, "Missing Permission");
            throw e;
        }
    }

    public void enableVideoCalling(boolean z) {
        Phone defaultPhone = getDefaultPhone();
        enforceModifyPermission();
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            ImsManager.getInstance(defaultPhone.getContext(), defaultPhone.getPhoneId()).setVtSetting(z);
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public boolean isVideoCallingEnabled(String str, String str2) {
        Phone defaultPhone = getDefaultPhone();
        boolean zCheckCallingOrSelfReadPhoneState = TelephonyPermissions.checkCallingOrSelfReadPhoneState(this.mApp, defaultPhone.getSubId(), str, str2, "isVideoCallingEnabled");
        boolean z = false;
        if (!zCheckCallingOrSelfReadPhoneState) {
            return false;
        }
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            ImsManager imsManager = ImsManager.getInstance(defaultPhone.getContext(), defaultPhone.getPhoneId());
            if (imsManager.isVtEnabledByPlatform() && imsManager.isEnhanced4gLteModeSettingEnabledByUser()) {
                if (imsManager.isVtEnabledByUser()) {
                    z = true;
                }
            }
            return z;
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public boolean canChangeDtmfToneLength(int i, String str, String str2) {
        if (!TelephonyPermissions.checkCallingOrSelfReadPhoneState(this.mApp, i, str, str2, "isVideoCallingEnabled")) {
            return false;
        }
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            return ((CarrierConfigManager) this.mApp.getSystemService("carrier_config")).getConfigForSubId(i).getBoolean("dtmf_type_enabled_bool");
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public boolean isWorldPhone(int i, String str, String str2) {
        if (!TelephonyPermissions.checkCallingOrSelfReadPhoneState(this.mApp, i, str, str2, "isVideoCallingEnabled")) {
            return false;
        }
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            return ((CarrierConfigManager) this.mApp.getSystemService("carrier_config")).getConfigForSubId(i).getBoolean("world_phone_bool");
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public boolean isTtyModeSupported() {
        return ((TelecomManager) this.mApp.getSystemService(TelecomManager.class)).isTtySupported();
    }

    public boolean isHearingAidCompatibilitySupported() {
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            return this.mApp.getResources().getBoolean(R.bool.hac_enabled);
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public boolean isRttSupported(int i) {
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        Phone phone = getPhone(i);
        boolean z = false;
        if (phone == null) {
            loge("isRttSupported: no Phone found. Invalid subId:" + i);
            return false;
        }
        try {
            boolean z2 = this.mApp.getCarrierConfigForSubId(i).getBoolean("rtt_supported_bool");
            boolean z3 = phone.getContext().getResources().getBoolean(R.bool.config_support_rtt);
            if (z2 && z3) {
                z = true;
            }
            return z;
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public boolean isRttEnabled(int i) {
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            boolean zIsRttSupported = isRttSupported(i);
            boolean z = false;
            boolean z2 = Settings.Secure.getInt(this.mApp.getContentResolver(), "rtt_calling_mode", 0) != 0;
            boolean z3 = this.mApp.getCarrierConfigForSubId(i).getBoolean("ignore_rtt_mode_setting_bool");
            if (zIsRttSupported && (z2 || z3)) {
                z = true;
            }
            return z;
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    @Deprecated
    public String getDeviceId(String str) {
        return getDeviceIdWithFeature(str, null);
    }

    public String getDeviceIdWithFeature(String str, String str2) {
        Phone phone = PhoneFactory.getPhone(0);
        if (phone == null) {
            return null;
        }
        if (!TelephonyPermissions.checkCallingOrSelfReadDeviceIdentifiers(this.mApp, phone.getSubId(), str, str2, "getDeviceId")) {
            return null;
        }
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            return phone.getDeviceId();
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public boolean isImsRegistered(int i) {
        Phone phone = getPhone(i);
        if (phone != null) {
            return phone.isImsRegistered();
        }
        return false;
    }

    public int getSubIdForPhoneAccount(PhoneAccount phoneAccount) {
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            return PhoneUtils.getSubIdForPhoneAccount(phoneAccount);
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public int getSubIdForPhoneAccountHandle(PhoneAccountHandle phoneAccountHandle, String str, String str2) {
        if (!TelephonyPermissions.checkCallingOrSelfReadPhoneState(this.mApp, getDefaultSubscription(), str, str2, "getSubIdForPhoneAccountHandle")) {
            throw new SecurityException("Requires READ_PHONE_STATE permission.");
        }
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            return PhoneUtils.getSubIdForPhoneAccountHandle(phoneAccountHandle);
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public PhoneAccountHandle getPhoneAccountHandleForSubscriptionId(int i) {
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            Phone phone = getPhone(i);
            if (phone != null) {
                return PhoneUtils.makePstnPhoneAccountHandle(phone);
            }
            return null;
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public boolean isWifiCallingAvailable(int i) {
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            Phone phone = getPhone(i);
            if (phone != null) {
                return phone.isWifiCallingEnabled();
            }
            return false;
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public boolean isVideoTelephonyAvailable(int i) {
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            Phone phone = getPhone(i);
            if (phone != null) {
                return phone.isVideoEnabled();
            }
            return false;
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public int getImsRegTechnologyForMmTel(int i) {
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            Phone phone = getPhone(i);
            if (phone != null) {
                return phone.getImsRegistrationTech();
            }
            return -1;
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public void factoryReset(int i) {
        enforceSettingsPermission();
        if (this.mUserManager.hasUserRestriction("no_network_reset")) {
            return;
        }
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            if (SubscriptionManager.isUsableSubIdValue(i) && !this.mUserManager.hasUserRestriction("no_config_mobile_networks")) {
                setUserDataEnabled(i, getDefaultDataEnabled());
                setNetworkSelectionModeAutomatic(i);
                setPreferredNetworkType(i, getDefaultNetworkType(i));
                setDataRoamingEnabled(i, getDefaultDataRoamingEnabled(i));
                CarrierInfoManager.deleteAllCarrierKeysForImsiEncryption(this.mApp);
            }
            cleanUpSmsRawTable(getDefaultPhone().getContext());
            int slotIndex = getSlotIndex(i);
            if (slotIndex > -1) {
                ImsManager.getInstance(this.mApp, slotIndex).factoryReset();
            }
            String property = DeviceConfig.getProperty("telephony", "reset_network_erase_modem_config_enabled");
            if (property != null && Boolean.parseBoolean(property)) {
                sendEraseModemConfig(getDefaultPhone());
            }
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    private void cleanUpSmsRawTable(Context context) {
        context.getContentResolver().delete(Uri.withAppendedPath(Telephony.Sms.CONTENT_URI, "raw/permanentDelete"), null, null);
    }

    public String getSimLocaleForSubscriber(int i) {
        String language;
        enforceReadPrivilegedPermission("getSimLocaleForSubscriber, subId: " + i);
        Phone phone = getPhone(i);
        if (phone == null) {
            log("getSimLocaleForSubscriber, invalid subId");
            return null;
        }
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            SubscriptionInfo activeSubscriptionInfo = this.mSubscriptionController.getActiveSubscriptionInfo(i, phone.getContext().getOpPackageName(), phone.getContext().getAttributionTag());
            if (activeSubscriptionInfo == null) {
                log("getSimLocaleForSubscriber, inactive subId: " + i);
                return null;
            }
            int mcc = activeSubscriptionInfo.getMcc();
            Locale localeFromSimAndCarrierPrefs = phone.getLocaleFromSimAndCarrierPrefs();
            if (localeFromSimAndCarrierPrefs == null) {
                language = null;
            } else {
                if (!localeFromSimAndCarrierPrefs.getCountry().isEmpty()) {
                    return localeFromSimAndCarrierPrefs.toLanguageTag();
                }
                language = localeFromSimAndCarrierPrefs.getLanguage();
            }
            Locale localeFromMcc = LocaleUtils.getLocaleFromMcc(this.mApp, mcc, language);
            if (localeFromMcc != null) {
                return localeFromMcc.toLanguageTag();
            }
            return null;
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    private List<SubscriptionInfo> getActiveSubscriptionInfoListPrivileged() {
        return this.mSubscriptionController.getActiveSubscriptionInfoList(this.mApp.getOpPackageName(), this.mApp.getAttributionTag());
    }

    public void requestModemActivityInfo(ResultReceiver resultReceiver) {
        enforceModifyPermission();
        WorkSource workSource = getWorkSource(Binder.getCallingUid());
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            sendRequestAsync(37, resultReceiver, null, workSource);
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isModemActivityInfoValid(ModemActivityInfo modemActivityInfo) {
        if (modemActivityInfo == null) {
            return false;
        }
        int timestamp = (int) (modemActivityInfo.getTimestamp() - this.mLastModemActivityInfo.getTimestamp());
        int[] transmitTimeMillis = modemActivityInfo.getTransmitTimeMillis();
        int i = 0;
        for (int i2 = 0; i2 < modemActivityInfo.getTransmitPowerInfo().size(); i2++) {
            i += transmitTimeMillis[i2];
        }
        return modemActivityInfo.isValid() && modemActivityInfo.getSleepTimeMillis() <= timestamp && modemActivityInfo.getIdleTimeMillis() <= timestamp && modemActivityInfo.getReceiveTimeMillis() <= timestamp && i <= timestamp;
    }

    public ServiceState getServiceStateForSubscriber(int i, String str, String str2) {
        if (!TelephonyPermissions.checkCallingOrSelfReadPhoneState(this.mApp, i, str, str2, "getServiceStateForSubscriber")) {
            return null;
        }
        LocationAccessPolicy.LocationPermissionResult locationPermissionResultCheckLocationPermission = LocationAccessPolicy.checkLocationPermission(this.mApp, new LocationAccessPolicy.LocationPermissionQuery.Builder().setCallingPackage(str).setCallingFeatureId(str2).setCallingPid(Binder.getCallingPid()).setCallingUid(Binder.getCallingUid()).setMethod("getServiceStateForSubscriber").setLogAsInfo(true).setMinSdkVersionForFine(29).build());
        LocationAccessPolicy.LocationPermissionResult locationPermissionResultCheckLocationPermission2 = LocationAccessPolicy.checkLocationPermission(this.mApp, new LocationAccessPolicy.LocationPermissionQuery.Builder().setCallingPackage(str).setCallingFeatureId(str2).setCallingPid(Binder.getCallingPid()).setCallingUid(Binder.getCallingUid()).setMethod("getServiceStateForSubscriber").setLogAsInfo(true).setMinSdkVersionForCoarse(29).build());
        boolean z = locationPermissionResultCheckLocationPermission == LocationAccessPolicy.LocationPermissionResult.ALLOWED;
        boolean z2 = locationPermissionResultCheckLocationPermission2 == LocationAccessPolicy.LocationPermissionResult.ALLOWED;
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            if (this.mSubscriptionController.isActiveSubId(i, str, str2)) {
                Phone phone = getPhone(i);
                if (phone == null) {
                    return null;
                }
                ServiceState serviceState = phone.getServiceState();
                return z ? serviceState : z2 ? serviceState.createLocationInfoSanitizedCopy(false) : serviceState.createLocationInfoSanitizedCopy(true);
            }
            Rlog.d("PhoneInterfaceManager", "getServiceStateForSubscriber returning null for inactive subId=" + i);
            return null;
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public Uri getVoicemailRingtoneUri(PhoneAccountHandle phoneAccountHandle) {
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            Phone phoneForPhoneAccountHandle = PhoneUtils.getPhoneForPhoneAccountHandle(phoneAccountHandle);
            if (phoneForPhoneAccountHandle == null) {
                phoneForPhoneAccountHandle = getDefaultPhone();
            }
            return VoicemailNotificationSettingsUtil.getRingtoneUri(phoneForPhoneAccountHandle.getContext());
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public void setVoicemailRingtoneUri(String str, PhoneAccountHandle phoneAccountHandle, Uri uri) {
        Phone defaultPhone = getDefaultPhone();
        this.mAppOps.checkPackage(Binder.getCallingUid(), str);
        if (!TextUtils.equals(str, ((TelecomManager) defaultPhone.getContext().getSystemService(TelecomManager.class)).getDefaultDialerPackage())) {
            TelephonyPermissions.enforceCallingOrSelfModifyPermissionOrCarrierPrivilege(this.mApp, PhoneUtils.getSubIdForPhoneAccountHandle(phoneAccountHandle), "setVoicemailRingtoneUri");
        }
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            Phone phoneForPhoneAccountHandle = PhoneUtils.getPhoneForPhoneAccountHandle(phoneAccountHandle);
            if (phoneForPhoneAccountHandle != null) {
                defaultPhone = phoneForPhoneAccountHandle;
            }
            VoicemailNotificationSettingsUtil.setRingtoneUri(defaultPhone.getContext(), uri);
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public boolean isVoicemailVibrationEnabled(PhoneAccountHandle phoneAccountHandle) {
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            Phone phoneForPhoneAccountHandle = PhoneUtils.getPhoneForPhoneAccountHandle(phoneAccountHandle);
            if (phoneForPhoneAccountHandle == null) {
                phoneForPhoneAccountHandle = getDefaultPhone();
            }
            return VoicemailNotificationSettingsUtil.isVibrationEnabled(phoneForPhoneAccountHandle.getContext());
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public void setVoicemailVibrationEnabled(String str, PhoneAccountHandle phoneAccountHandle, boolean z) {
        Phone defaultPhone = getDefaultPhone();
        this.mAppOps.checkPackage(Binder.getCallingUid(), str);
        if (!TextUtils.equals(str, ((TelecomManager) defaultPhone.getContext().getSystemService(TelecomManager.class)).getDefaultDialerPackage())) {
            TelephonyPermissions.enforceCallingOrSelfModifyPermissionOrCarrierPrivilege(this.mApp, PhoneUtils.getSubIdForPhoneAccountHandle(phoneAccountHandle), "setVoicemailVibrationEnabled");
        }
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            Phone phoneForPhoneAccountHandle = PhoneUtils.getPhoneForPhoneAccountHandle(phoneAccountHandle);
            if (phoneForPhoneAccountHandle != null) {
                defaultPhone = phoneForPhoneAccountHandle;
            }
            VoicemailNotificationSettingsUtil.setVibrationEnabled(defaultPhone.getContext(), z);
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    private void enforceReadPrivilegedPermission(String str) {
        this.mApp.enforceCallingOrSelfPermission("android.permission.READ_PRIVILEGED_PHONE_STATE", str);
    }

    private void enforceSendSmsPermission() {
        this.mApp.enforceCallingOrSelfPermission("android.permission.SEND_SMS", null);
    }

    private void enforceVisualVoicemailPackage(String str, int i) {
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            ComponentName remotePackage = RemoteVvmTaskManager.getRemotePackage(this.mApp, i);
            if (remotePackage == null) {
                throw new SecurityException("Caller not current active visual voicemail package[null]");
            }
            String packageName = remotePackage.getPackageName();
            if (str.equals(packageName)) {
                return;
            }
            throw new SecurityException("Caller not current active visual voicemail package[" + packageName + "]");
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public String getAidForAppType(int i, int i2) {
        enforceReadPrivilegedPermission("getAidForAppType");
        Phone phone = getPhone(i);
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        String aid = null;
        if (phone == null) {
            return null;
        }
        try {
            try {
                aid = UiccController.getInstance().getUiccCard(phone.getPhoneId()).getApplicationByType(i2).getAid();
            } catch (Exception e) {
                Log.e("PhoneInterfaceManager", "Not getting aid. Exception ex=" + e);
            }
            return aid;
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public String getEsn(int i) {
        enforceReadPrivilegedPermission("getEsn");
        Phone phone = getPhone(i);
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        String esn = null;
        if (phone == null) {
            return null;
        }
        try {
            try {
                esn = phone.getEsn();
            } catch (Exception e) {
                Log.e("PhoneInterfaceManager", "Not getting ESN. Exception ex=" + e);
            }
            return esn;
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public String getCdmaPrlVersion(int i) {
        enforceReadPrivilegedPermission("getCdmaPrlVersion");
        Phone phone = getPhone(i);
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        String cdmaPrlVersion = null;
        if (phone == null) {
            return null;
        }
        try {
            try {
                cdmaPrlVersion = phone.getCdmaPrlVersion();
            } catch (Exception e) {
                Log.e("PhoneInterfaceManager", "Not getting PRLVersion", e);
            }
            return cdmaPrlVersion;
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public List<TelephonyHistogram> getTelephonyHistograms() {
        TelephonyPermissions.enforceCallingOrSelfModifyPermissionOrCarrierPrivilege(this.mApp, getDefaultSubscription(), "getTelephonyHistograms");
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            return RIL.getTelephonyRILTimingHistograms();
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public int setAllowedCarriers(CarrierRestrictionRules carrierRestrictionRules) {
        enforceModifyPermission();
        WorkSource workSource = getWorkSource(Binder.getCallingUid());
        if (carrierRestrictionRules == null) {
            throw new NullPointerException("carrier restriction cannot be null");
        }
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            return ((Integer) sendRequest(43, carrierRestrictionRules, workSource)).intValue();
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public CarrierRestrictionRules getAllowedCarriers() {
        enforceReadPrivilegedPermission("getAllowedCarriers");
        WorkSource workSource = getWorkSource(Binder.getCallingUid());
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            Object objSendRequest = sendRequest(45, (Object) null, workSource);
            if (objSendRequest instanceof CarrierRestrictionRules) {
                return (CarrierRestrictionRules) objSendRequest;
            }
            return null;
        } catch (Exception e) {
            Log.e("PhoneInterfaceManager", "getAllowedCarriers. Exception ex=" + e);
            return null;
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public void carrierActionSetMeteredApnsEnabled(int i, boolean z) {
        enforceModifyPermission();
        Phone phone = getPhone(i);
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        if (phone == null) {
            loge("carrierAction: SetMeteredApnsEnabled fails with invalid subId: " + i);
            return;
        }
        try {
            try {
                phone.carrierActionSetMeteredApnsEnabled(z);
            } catch (Exception e) {
                Log.e("PhoneInterfaceManager", "carrierAction: SetMeteredApnsEnabled fails. Exception ex=" + e);
            }
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public void carrierActionSetRadioEnabled(int i, boolean z) {
        enforceModifyPermission();
        Phone phone = getPhone(i);
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        if (phone == null) {
            loge("carrierAction: SetRadioEnabled fails with invalid sibId: " + i);
            return;
        }
        try {
            try {
                phone.carrierActionSetRadioEnabled(z);
            } catch (Exception e) {
                Log.e("PhoneInterfaceManager", "carrierAction: SetRadioEnabled fails. Exception ex=" + e);
            }
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public void carrierActionReportDefaultNetworkStatus(int i, boolean z) {
        enforceModifyPermission();
        Phone phone = getPhone(i);
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        if (phone == null) {
            loge("carrierAction: ReportDefaultNetworkStatus fails with invalid sibId: " + i);
            return;
        }
        try {
            try {
                phone.carrierActionReportDefaultNetworkStatus(z);
            } catch (Exception e) {
                Log.e("PhoneInterfaceManager", "carrierAction: ReportDefaultNetworkStatus fails. Exception ex=" + e);
            }
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public void carrierActionResetAll(int i) {
        enforceModifyPermission();
        Phone phone = getPhone(i);
        if (phone == null) {
            loge("carrierAction: ResetAll fails with invalid sibId: " + i);
            return;
        }
        try {
            phone.carrierActionResetAll();
        } catch (Exception e) {
            Log.e("PhoneInterfaceManager", "carrierAction: ResetAll fails. Exception ex=" + e);
        }
    }

    protected void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        if (this.mApp.checkCallingOrSelfPermission("android.permission.DUMP") != 0) {
            printWriter.println("Permission Denial: can't dump Phone from pid=" + Binder.getCallingPid() + ", uid=" + Binder.getCallingUid() + "without permission android.permission.DUMP");
            return;
        }
        DumpsysHandler.dump(this.mApp, fileDescriptor, printWriter, strArr);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int handleShellCommand(ParcelFileDescriptor parcelFileDescriptor, ParcelFileDescriptor parcelFileDescriptor2, ParcelFileDescriptor parcelFileDescriptor3, String[] strArr) {
        return new TelephonyShellCommand(this, getDefaultPhone().getContext()).exec(this, parcelFileDescriptor.getFileDescriptor(), parcelFileDescriptor2.getFileDescriptor(), parcelFileDescriptor3.getFileDescriptor(), strArr);
    }

    public void setPolicyDataEnabled(boolean z, int i) {
        enforceModifyPermission();
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            Phone phone = getPhone(i);
            if (phone != null) {
                phone.getDataEnabledSettings().setPolicyDataEnabled(z);
            }
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public List<ClientRequestStats> getClientRequestStats(String str, String str2, int i) {
        if (!TelephonyPermissions.checkCallingOrSelfReadPhoneState(this.mApp, i, str, str2, "getClientRequestStats")) {
            return null;
        }
        Phone phone = getPhone(i);
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        if (phone == null) {
            return null;
        }
        try {
            return phone.getClientRequestStats();
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    private WorkSource getWorkSource(int i) {
        return new WorkSource(i, this.mApp.getPackageManager().getNameForUid(i));
    }

    public void setSimPowerStateForSlot(int i, int i2) {
        enforceModifyPermission();
        Phone phone = PhoneFactory.getPhone(i);
        WorkSource workSource = getWorkSource(Binder.getCallingUid());
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        if (phone != null) {
            try {
                phone.setSimPowerState(i2, workSource);
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isUssdApiAllowed(int i) {
        PersistableBundle configForSubId;
        CarrierConfigManager carrierConfigManager = (CarrierConfigManager) this.mApp.getSystemService("carrier_config");
        if (carrierConfigManager == null || (configForSubId = carrierConfigManager.getConfigForSubId(i)) == null) {
            return false;
        }
        return configForSubId.getBoolean("allow_ussd_requests_via_telephony_manager_bool");
    }

    public boolean getEmergencyCallbackMode(int i) {
        enforceReadPrivilegedPermission("getEmergencyCallbackMode");
        Phone phone = getPhone(i);
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        if (phone != null) {
            try {
                return phone.isInEcm();
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        }
        return false;
    }

    public SignalStrength getSignalStrength(int i) {
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            Phone phone = getPhone(i);
            if (phone != null) {
                return phone.getSignalStrength();
            }
            return null;
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public int getRadioPowerState(int i, String str, String str2) {
        Phone phone = PhoneFactory.getPhone(i);
        if (phone == null || !TelephonyPermissions.checkCallingOrSelfReadPhoneState(this.mApp, phone.getSubId(), str, str2, "getRadioPowerState")) {
            return 2;
        }
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            return phone.getRadioPowerState();
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public boolean isDataRoamingEnabled(int i) {
        this.mApp.enforceCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE", null);
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        boolean dataRoamingEnabled = false;
        try {
            try {
                Phone phone = getPhone(i);
                if (phone != null) {
                    dataRoamingEnabled = phone.getDataRoamingEnabled();
                }
            } catch (Exception unused) {
                TelephonyPermissions.enforeceCallingOrSelfReadPhoneStatePermissionOrCarrierPrivilege(this.mApp, i, "isDataRoamingEnabled");
            }
            return dataRoamingEnabled;
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public void setDataRoamingEnabled(int i, boolean z) {
        TelephonyPermissions.enforceCallingOrSelfModifyPermissionOrCarrierPrivilege(this.mApp, i, "setDataRoamingEnabled");
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            Phone phone = getPhone(i);
            if (phone != null) {
                phone.setDataRoamingEnabled(z);
            }
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public boolean isManualNetworkSelectionAllowed(int i) {
        TelephonyPermissions.enforeceCallingOrSelfReadPrivilegedPhoneStatePermissionOrCarrierPrivilege(this.mApp, i, "isManualNetworkSelectionAllowed");
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            Phone phone = getPhone(i);
            return phone != null ? phone.isCspPlmnEnabled() : true;
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public List<UiccCardInfo> getUiccCardsInfo(String str) {
        boolean z;
        ((AppOpsManager) this.mApp.getSystemService(AppOpsManager.class)).checkPackage(Binder.getCallingUid(), str);
        try {
            enforceReadPrivilegedPermission("getUiccCardsInfo");
            z = true;
        } catch (SecurityException unused) {
            if (checkCarrierPrivilegesForPackageAnyPhone(str) != 1) {
                throw new SecurityException("Caller does not have permission.");
            }
            z = false;
        }
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            UiccController uiccController = UiccController.getInstance();
            ArrayList<UiccCardInfo> allUiccCardInfos = uiccController.getAllUiccCardInfos();
            if (z) {
                return allUiccCardInfos;
            }
            ArrayList arrayList = new ArrayList();
            for (UiccCardInfo uiccCardInfo : allUiccCardInfos) {
                UiccCard uiccCardForSlot = uiccController.getUiccCardForSlot(uiccCardInfo.getSlotIndex());
                if (uiccCardForSlot == null || uiccCardForSlot.getUiccProfile() == null) {
                    arrayList.add(uiccCardInfo.getUnprivileged());
                } else if (uiccCardForSlot.getUiccProfile().getCarrierPrivilegeStatus(this.mApp.getPackageManager(), str) == 1) {
                    arrayList.add(uiccCardInfo);
                } else {
                    arrayList.add(uiccCardInfo.getUnprivileged());
                }
            }
            return arrayList;
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public UiccSlotInfo[] getUiccSlotsInfo() {
        String eid;
        enforceReadPrivilegedPermission("getUiccSlotsInfo");
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            UiccSlot[] uiccSlots = UiccController.getInstance().getUiccSlots();
            if (uiccSlots == null) {
                Rlog.i("PhoneInterfaceManager", "slots is null.");
                return null;
            }
            UiccSlotInfo[] uiccSlotInfoArr = new UiccSlotInfo[uiccSlots.length];
            for (int i = 0; i < uiccSlots.length; i++) {
                UiccSlot uiccSlot = uiccSlots[i];
                if (uiccSlot != null) {
                    UiccCard uiccCard = uiccSlot.getUiccCard();
                    if (uiccCard != null) {
                        eid = uiccCard.getCardId();
                    } else {
                        eid = uiccSlot.getEid();
                        if (TextUtils.isEmpty(eid)) {
                            eid = uiccSlot.getIccId();
                        }
                    }
                    if (eid != null) {
                        eid = IccUtils.stripTrailingFs(eid);
                    }
                    String str = eid;
                    int i2 = AnonymousClass1.$SwitchMap$com$android$internal$telephony$uicc$IccCardStatus$CardState[uiccSlot.getCardState().ordinal()];
                    int i3 = 3;
                    if (i2 == 1) {
                        i3 = 1;
                    } else if (i2 == 2) {
                        i3 = 2;
                    } else if (i2 != 3) {
                        i3 = i2 != 4 ? 0 : 4;
                    }
                    uiccSlotInfoArr[i] = new UiccSlotInfo(uiccSlot.isActive(), uiccSlot.isEuicc(), str, i3, uiccSlot.getPhoneId(), uiccSlot.isExtendedApduSupported(), uiccSlot.isRemovable());
                }
            }
            return uiccSlotInfoArr;
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    /* JADX INFO: renamed from: com.android.phone.PhoneInterfaceManager$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$android$telephony$LocationAccessPolicy$LocationPermissionResult;
        static final /* synthetic */ int[] $SwitchMap$com$android$internal$telephony$uicc$IccCardStatus$CardState;

        static {
            int[] iArr = new int[IccCardStatus.CardState.values().length];
            $SwitchMap$com$android$internal$telephony$uicc$IccCardStatus$CardState = iArr;
            try {
                iArr[IccCardStatus.CardState.CARDSTATE_ABSENT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$android$internal$telephony$uicc$IccCardStatus$CardState[IccCardStatus.CardState.CARDSTATE_PRESENT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$android$internal$telephony$uicc$IccCardStatus$CardState[IccCardStatus.CardState.CARDSTATE_ERROR.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$android$internal$telephony$uicc$IccCardStatus$CardState[IccCardStatus.CardState.CARDSTATE_RESTRICTED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            int[] iArr2 = new int[LocationAccessPolicy.LocationPermissionResult.values().length];
            $SwitchMap$android$telephony$LocationAccessPolicy$LocationPermissionResult = iArr2;
            try {
                iArr2[LocationAccessPolicy.LocationPermissionResult.DENIED_HARD.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$android$telephony$LocationAccessPolicy$LocationPermissionResult[LocationAccessPolicy.LocationPermissionResult.DENIED_SOFT.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    public boolean switchSlots(int[] iArr) {
        enforceModifyPermission();
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            return ((Boolean) sendRequest(50, iArr)).booleanValue();
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public int getCardIdForDefaultEuicc(int i, String str) {
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            return UiccController.getInstance().getCardIdForDefaultEuicc();
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public void refreshUiccProfile(int i) {
        enforceModifyPermission();
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            Phone phone = getPhone(i);
            if (phone == null) {
                return;
            }
            UiccCard uiccCard = phone.getUiccCard();
            if (uiccCard == null) {
                return;
            }
            UiccProfile uiccProfile = uiccCard.getUiccProfile();
            if (uiccProfile == null) {
                return;
            }
            uiccProfile.refresh();
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    private boolean getDefaultDataEnabled() {
        return TelephonyProperties.mobile_data().orElse(Boolean.TRUE).booleanValue();
    }

    private boolean getDefaultDataRoamingEnabled(int i) {
        CarrierConfigManager carrierConfigManager = (CarrierConfigManager) this.mApp.getSystemService("carrier_config");
        return carrierConfigManager.getConfigForSubId(i).getBoolean("carrier_default_data_roaming_enabled_bool") | TelephonyProperties.data_roaming().orElse(Boolean.FALSE).booleanValue();
    }

    private int getDefaultNetworkType(int i) {
        List<Integer> listDefault_network = TelephonyProperties.default_network();
        int phoneId = this.mSubscriptionController.getPhoneId(i);
        if (phoneId >= 0 && phoneId < listDefault_network.size() && listDefault_network.get(phoneId) != null) {
            return listDefault_network.get(phoneId).intValue();
        }
        return Phone.PREFERRED_NT_MODE;
    }

    public void setCarrierTestOverride(int i, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9) {
        enforceModifyPermission();
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            Phone phone = getPhone(i);
            if (phone == null) {
                loge("setCarrierTestOverride fails with invalid subId: " + i);
                return;
            }
            phone.setCarrierTestOverride(str, str2, str3, str4, str5, str6, str7, str8, str9);
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public int getCarrierIdListVersion(int i) {
        enforceReadPrivilegedPermission("getCarrierIdListVersion");
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            Phone phone = getPhone(i);
            if (phone == null) {
                loge("getCarrierIdListVersion fails with invalid subId: " + i);
                return -1;
            }
            return phone.getCarrierIdListVersion();
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public int getNumberOfModemsWithSimultaneousDataConnections(int i, String str, String str2) {
        if (!TelephonyPermissions.checkCallingOrSelfReadPhoneState(this.mApp, i, str, str2, "getNumberOfModemsWithSimultaneousDataConnections")) {
            return -1;
        }
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            return this.mPhoneConfigurationManager.getNumberOfModemsWithSimultaneousDataConnections();
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public int getCdmaRoamingMode(int i) {
        TelephonyPermissions.enforeceCallingOrSelfReadPrivilegedPhoneStatePermissionOrCarrierPrivilege(this.mApp, i, "getCdmaRoamingMode");
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            return ((Integer) sendRequest(54, (Object) null, Integer.valueOf(i))).intValue();
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public boolean setCdmaRoamingMode(int i, int i2) {
        TelephonyPermissions.enforceCallingOrSelfModifyPermissionOrCarrierPrivilege(this.mApp, i, "setCdmaRoamingMode");
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            return ((Boolean) sendRequest(56, Integer.valueOf(i2), Integer.valueOf(i))).booleanValue();
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public boolean setCdmaSubscriptionMode(int i, int i2) {
        TelephonyPermissions.enforceCallingOrSelfModifyPermissionOrCarrierPrivilege(this.mApp, i, "setCdmaSubscriptionMode");
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            return ((Boolean) sendRequest(58, Integer.valueOf(i2), Integer.valueOf(i))).booleanValue();
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public Map<Integer, List<EmergencyNumber>> getEmergencyNumberList(String str, String str2) {
        if (!TelephonyPermissions.checkCallingOrSelfReadPhoneState(this.mApp, getDefaultSubscription(), str, str2, "getEmergencyNumberList")) {
            throw new SecurityException("Requires READ_PHONE_STATE permission.");
        }
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            HashMap map = new HashMap();
            for (Phone phone : PhoneFactory.getPhones()) {
                if (phone.getEmergencyNumberTracker() != null && phone.getEmergencyNumberTracker().getEmergencyNumberList() != null) {
                    map.put(Integer.valueOf(phone.getSubId()), phone.getEmergencyNumberTracker().getEmergencyNumberList());
                }
            }
            return map;
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public boolean isEmergencyNumber(String str, boolean z) {
        Phone defaultPhone = getDefaultPhone();
        if (!z) {
            TelephonyPermissions.enforeceCallingOrSelfReadPrivilegedPhoneStatePermissionOrCarrierPrivilege(this.mApp, defaultPhone.getSubId(), "isEmergencyNumber(Potential)");
        }
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            for (Phone phone : PhoneFactory.getPhones()) {
                if (phone.getEmergencyNumberTracker() != null && phone.getEmergencyNumberTracker() != null && phone.getEmergencyNumberTracker().isEmergencyNumber(str, z)) {
                    Binder.restoreCallingIdentity(jClearCallingIdentity);
                    return true;
                }
            }
            return false;
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public void startEmergencyCallbackMode() {
        TelephonyPermissions.enforceShellOnly(Binder.getCallingUid(), "startEmergencyCallbackMode");
        enforceModifyPermission();
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            for (GsmCdmaPhone gsmCdmaPhone : PhoneFactory.getPhones()) {
                Rlog.d("PhoneInterfaceManager", "startEmergencyCallbackMode phone type: " + gsmCdmaPhone.getPhoneType());
                if (gsmCdmaPhone != null && (gsmCdmaPhone.getPhoneType() == 1 || gsmCdmaPhone.getPhoneType() == 2)) {
                    gsmCdmaPhone.obtainMessage(25).sendToTarget();
                    Rlog.d("PhoneInterfaceManager", "startEmergencyCallbackMode: triggered");
                }
            }
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public void updateEmergencyNumberListTestMode(int i, EmergencyNumber emergencyNumber) {
        TelephonyPermissions.enforceShellOnly(Binder.getCallingUid(), "updateEmergencyNumberListTestMode");
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            for (Phone phone : PhoneFactory.getPhones()) {
                EmergencyNumberTracker emergencyNumberTracker = phone.getEmergencyNumberTracker();
                if (emergencyNumberTracker != null) {
                    emergencyNumberTracker.executeEmergencyNumberTestModeCommand(i, emergencyNumber);
                }
            }
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public List<String> getEmergencyNumberListTestMode() {
        TelephonyPermissions.enforceShellOnly(Binder.getCallingUid(), "getEmergencyNumberListTestMode");
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            HashSet hashSet = new HashSet();
            for (Phone phone : PhoneFactory.getPhones()) {
                EmergencyNumberTracker emergencyNumberTracker = phone.getEmergencyNumberTracker();
                if (emergencyNumberTracker != null) {
                    Iterator it = emergencyNumberTracker.getEmergencyNumberList().iterator();
                    while (it.hasNext()) {
                        hashSet.add(((EmergencyNumber) it.next()).getNumber());
                    }
                }
            }
            return new ArrayList(hashSet);
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public int getEmergencyNumberDbVersion(int i) {
        enforceReadPrivilegedPermission("getEmergencyNumberDbVersion");
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            Phone phone = getPhone(i);
            if (phone == null) {
                loge("getEmergencyNumberDbVersion fails with invalid subId: " + i);
                return -1;
            }
            return phone.getEmergencyNumberDbVersion();
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public void notifyOtaEmergencyNumberDbInstalled() {
        enforceModifyPermission();
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            for (Phone phone : PhoneFactory.getPhones()) {
                EmergencyNumberTracker emergencyNumberTracker = phone.getEmergencyNumberTracker();
                if (emergencyNumberTracker != null) {
                    emergencyNumberTracker.updateOtaEmergencyNumberDatabase();
                }
            }
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public void updateOtaEmergencyNumberDbFilePath(ParcelFileDescriptor parcelFileDescriptor) {
        enforceActiveEmergencySessionPermission();
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            for (Phone phone : PhoneFactory.getPhones()) {
                EmergencyNumberTracker emergencyNumberTracker = phone.getEmergencyNumberTracker();
                if (emergencyNumberTracker != null) {
                    emergencyNumberTracker.updateOtaEmergencyNumberDbFilePath(parcelFileDescriptor);
                }
            }
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public void resetOtaEmergencyNumberDbFilePath() {
        enforceActiveEmergencySessionPermission();
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            for (Phone phone : PhoneFactory.getPhones()) {
                EmergencyNumberTracker emergencyNumberTracker = phone.getEmergencyNumberTracker();
                if (emergencyNumberTracker != null) {
                    emergencyNumberTracker.resetOtaEmergencyNumberDbFilePath();
                }
            }
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public List<String> getCertsFromCarrierPrivilegeAccessRules(int i) {
        enforceReadPrivilegedPermission("getCertsFromCarrierPrivilegeAccessRules");
        Phone phone = getPhone(i);
        if (phone == null) {
            return null;
        }
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            UiccProfile uiccProfileForPhone = UiccController.getInstance().getUiccProfileForPhone(phone.getPhoneId());
            if (uiccProfileForPhone != null) {
                return uiccProfileForPhone.getCertsFromCarrierPrivilegeAccessRules();
            }
            return null;
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public boolean enableModemForSlot(int i, boolean z) {
        enforceModifyPermission();
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            Phone phone = PhoneFactory.getPhone(i);
            if (phone != null) {
                return ((Boolean) sendRequest(68, Boolean.valueOf(z), phone, null)).booleanValue();
            }
            return false;
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public boolean isModemEnabledForSlot(int i, String str, String str2) {
        Phone phone = PhoneFactory.getPhone(i);
        if (phone == null) {
            return false;
        }
        if (!TelephonyPermissions.checkCallingOrSelfReadPhoneState(this.mApp, phone.getSubId(), str, str2, "isModemEnabledForSlot")) {
            throw new SecurityException("Requires READ_PHONE_STATE permission.");
        }
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            return this.mPhoneConfigurationManager.getPhoneStatusFromCache(phone.getPhoneId());
        } catch (NoSuchElementException unused) {
            return ((Boolean) this.sendRequest(70, null, phone, null)).booleanValue();
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public void setMultiSimCarrierRestriction(boolean z) {
        enforceModifyPermission();
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            this.mTelephonySharedPreferences.edit().putBoolean("multisim_restricted", z).commit();
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public int isMultiSimSupported(String str, String str2) {
        if (!TelephonyPermissions.checkCallingOrSelfReadPhoneState(this.mApp, getDefaultPhone().getSubId(), str, str2, "isMultiSimSupported")) {
            return 1;
        }
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            return isMultiSimSupportedInternal();
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    private int isMultiSimSupportedInternal() {
        if (UiccController.getInstance().getUiccSlots().length < 2) {
            loge("isMultiSimSupportedInternal: requires at least 2 cards");
            return 1;
        }
        PhoneCapability staticPhoneCapability = this.mPhoneConfigurationManager.getStaticPhoneCapability();
        if (staticPhoneCapability == null) {
            loge("isMultiSimSupportedInternal: no static configuration available");
            return 1;
        }
        if (staticPhoneCapability.logicalModemList.size() >= 2) {
            return this.mTelephonySharedPreferences.getBoolean("multisim_restricted", false) ? 2 : 0;
        }
        loge("isMultiSimSupportedInternal: maximum number of modem is < 2");
        return 1;
    }

    public void switchMultiSimConfig(int i) {
        if (i == 1) {
            enforceModifyPermission();
        } else {
            TelephonyPermissions.enforceCallingOrSelfModifyPermissionOrCarrierPrivilege(this.mApp, Integer.MAX_VALUE, "switchMultiSimConfig");
        }
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            if (isMultiSimSupportedInternal() != 0) {
                loge("switchMultiSimConfig not possible. It is restricted or not supported.");
            } else {
                this.mPhoneConfigurationManager.switchMultiSimConfig(i);
            }
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public boolean isApplicationOnUicc(int i, int i2) {
        enforceReadPrivilegedPermission("isApplicationOnUicc");
        Phone phone = getPhone(i);
        if (phone == null) {
            return false;
        }
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            UiccCard uiccCard = phone.getUiccCard();
            if (uiccCard == null) {
                return false;
            }
            UiccProfile uiccProfile = uiccCard.getUiccProfile();
            if (uiccProfile == null) {
                return false;
            }
            if (1 > i2 || i2 > 5) {
                return false;
            }
            return uiccProfile.isApplicationOnIcc(IccCardApplicationStatus.AppType.values()[i2]);
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public boolean doesSwitchMultiSimConfigTriggerReboot(int i, String str, String str2) {
        if (!TelephonyPermissions.checkCallingOrSelfReadPhoneState(this.mApp, i, str, str2, "doesSwitchMultiSimConfigTriggerReboot")) {
            return false;
        }
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            return this.mPhoneConfigurationManager.isRebootRequiredForModemConfigChange();
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateModemStateMetrics() {
        TelephonyMetrics.getInstance().updateEnabledModemBitmap((1 << TelephonyManager.from(this.mApp).getPhoneCount()) - 1);
    }

    public int[] getSlotsMapping() {
        enforceReadPrivilegedPermission("getSlotsMapping");
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            int[] iArr = new int[TelephonyManager.getDefault().getPhoneCount()];
            UiccSlotInfo[] uiccSlotsInfo = getUiccSlotsInfo();
            for (int i = 0; i < uiccSlotsInfo.length; i++) {
                if (SubscriptionManager.isValidPhoneId(uiccSlotsInfo[i].getLogicalSlotIdx())) {
                    iArr[uiccSlotsInfo[i].getLogicalSlotIdx()] = i;
                }
            }
            return iArr;
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public int getRadioHalVersion() {
        Phone defaultPhone = getDefaultPhone();
        if (defaultPhone == null) {
            return -1;
        }
        HalVersion halVersion = defaultPhone.getHalVersion();
        if (halVersion.equals(HalVersion.UNKNOWN)) {
            return -1;
        }
        return (halVersion.major * 100) + halVersion.minor;
    }

    public String getCurrentPackageName() {
        return this.mApp.getPackageManager().getPackagesForUid(Binder.getCallingUid())[0];
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean isDataEnabledForApn(int r3, int r4, java.lang.String r5) {
        /*
            r2 = this;
            java.lang.String r5 = "Needs READ_PRIVILEGED_PHONE_STATE for isDataEnabledForApn"
            r2.enforceReadPrivilegedPermission(r5)
            long r0 = android.os.Binder.clearCallingIdentity()
            com.android.internal.telephony.Phone r2 = r2.getPhone(r4)     // Catch: java.lang.Throwable -> L29
            r4 = 0
            if (r2 != 0) goto L14
            android.os.Binder.restoreCallingIdentity(r0)
            return r4
        L14:
            boolean r5 = com.android.internal.telephony.dataconnection.ApnSettingUtils.isMeteredApnType(r3, r2)     // Catch: java.lang.Throwable -> L29
            if (r5 == 0) goto L24
            com.android.internal.telephony.dataconnection.DataEnabledSettings r2 = r2.getDataEnabledSettings()     // Catch: java.lang.Throwable -> L29
            boolean r2 = r2.isDataEnabled(r3)     // Catch: java.lang.Throwable -> L29
            if (r2 == 0) goto L25
        L24:
            r4 = 1
        L25:
            android.os.Binder.restoreCallingIdentity(r0)
            return r4
        L29:
            r2 = move-exception
            android.os.Binder.restoreCallingIdentity(r0)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.phone.PhoneInterfaceManager.isDataEnabledForApn(int, int, java.lang.String):boolean");
    }

    public boolean isApnMetered(int i, int i2) {
        enforceReadPrivilegedPermission("isApnMetered");
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            Phone phone = getPhone(i2);
            if (phone != null) {
                return ApnSettingUtils.isMeteredApnType(i, phone);
            }
            return true;
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public void setSystemSelectionChannels(List<RadioAccessSpecifier> list, int i, final IBooleanConsumer iBooleanConsumer) {
        enforceModifyPermission();
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            Phone phone = getPhone(i);
            if (phone != null) {
                sendRequestAsync(80, Pair.create(list, new Consumer() { // from class: com.android.phone.-$$Lambda$PhoneInterfaceManager$E2wt822b_wcCgBAc0ZDb7nRC1Ms
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        PhoneInterfaceManager.lambda$setSystemSelectionChannels$4(iBooleanConsumer, (Boolean) obj);
                    }
                }), phone, null);
            } else {
                if (iBooleanConsumer != null) {
                    try {
                        iBooleanConsumer.accept(false);
                    } catch (RemoteException unused) {
                    }
                }
            }
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    static /* synthetic */ void lambda$setSystemSelectionChannels$4(IBooleanConsumer iBooleanConsumer, Boolean bool) {
        if (iBooleanConsumer != null) {
            try {
                iBooleanConsumer.accept(bool.booleanValue());
            } catch (RemoteException unused) {
            }
        }
    }

    public boolean isMvnoMatched(int i, int i2, String str) {
        enforceReadPrivilegedPermission("isMvnoMatched");
        IccRecords iccRecords = UiccController.getInstance().getIccRecords(SubscriptionManager.getPhoneId(i), 1);
        if (iccRecords == null) {
            Log.d("PhoneInterfaceManager", "isMvnoMatched# IccRecords is null");
            return false;
        }
        return ApnSettingUtils.mvnoMatches(iccRecords, i2, str);
    }

    public void enqueueSmsPickResult(String str, String str2, IIntegerConsumer iIntegerConsumer) {
        if (str == null) {
            str = getCurrentPackageName();
        }
        Phone defaultPhone = getDefaultPhone();
        PhoneGlobals phoneGlobals = this.mApp;
        if (!new SmsPermissions(defaultPhone, phoneGlobals, (AppOpsManager) phoneGlobals.getSystemService("appops")).checkCallingCanSendSms(str, str2, "Sending message")) {
            throw new SecurityException("Requires SEND_SMS permission to perform this operation");
        }
        PickSmsSubscriptionActivity.addPendingResult(iIntegerConsumer);
        Intent intent = new Intent();
        intent.setClass(this.mApp, PickSmsSubscriptionActivity.class);
        intent.addFlags(268435456);
        intent.putExtra("dialog_type", 4);
        this.mApp.startActivity(intent);
    }

    public String getMmsUAProfUrl(int i) {
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            return SubscriptionManager.getResourcesForSubId(getDefaultPhone().getContext(), i).getString(android.R.string.config_defaultAutofillService);
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public String getMmsUserAgent(int i) {
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            return SubscriptionManager.getResourcesForSubId(getDefaultPhone().getContext(), i).getString(android.R.string.config_defaultAugmentedAutofillService);
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public boolean setDataAllowedDuringVoiceCall(int i, boolean z) {
        enforceModifyPermission();
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            Phone phone = getPhone(i);
            if (phone != null) {
                return phone.getDataEnabledSettings().setAllowDataDuringVoiceCall(z);
            }
            return false;
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public boolean isDataAllowedInVoiceCall(int i) {
        enforceReadPrivilegedPermission("isDataAllowedInVoiceCall");
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            Phone phone = getPhone(i);
            if (phone != null) {
                return phone.getDataEnabledSettings().isDataAllowedInVoiceCall();
            }
            return false;
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public boolean setAlwaysAllowMmsData(int i, boolean z) {
        enforceModifyPermission();
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            Phone phone = getPhone(i);
            if (phone != null) {
                return phone.getDataEnabledSettings().setAlwaysAllowMmsData(z);
            }
            return false;
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public void setCepEnabled(boolean z) {
        TelephonyPermissions.enforceShellOnly(Binder.getCallingUid(), "setCepEnabled");
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            Rlog.i("PhoneInterfaceManager", "setCepEnabled isCepEnabled=" + z);
            for (Phone phone : PhoneFactory.getPhones()) {
                ImsPhone imsPhone = phone.getImsPhone();
                if (imsPhone != null && imsPhone.getPhoneType() == 5) {
                    ImsPhone imsPhone2 = imsPhone;
                    imsPhone2.getCallTracker().setConferenceEventPackageEnabled(z);
                    Rlog.i("PhoneInterfaceManager", "setCepEnabled isCepEnabled=" + z + ", for imsPhone " + imsPhone2.getMsisdn());
                }
            }
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public void notifyRcsAutoConfigurationReceived(int i, byte[] bArr, boolean z) {
        TelephonyPermissions.enforceCallingOrSelfModifyPermissionOrCarrierPrivilege(this.mApp, i, "notifyRcsAutoConfigurationReceived");
        try {
            IImsConfig imsConfig = getImsConfig(getSlotIndex(i), 2);
            if (imsConfig == null) {
                Rlog.e("PhoneInterfaceManager", "null result for getImsConfig");
            } else {
                imsConfig.notifyRcsAutoConfigurationReceived(bArr, z);
            }
        } catch (RemoteException e) {
            Rlog.e("PhoneInterfaceManager", "fail to getImsConfig " + e.getMessage());
        }
    }

    public boolean isIccLockEnabled(int i) {
        enforceReadPrivilegedPermission("isIccLockEnabled");
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            Phone phone = getPhone(i);
            if (phone == null || phone.getIccCard() == null) {
                return false;
            }
            return phone.getIccCard().getIccLockEnabled();
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public int setIccLockEnabled(int i, boolean z, String str) {
        enforceModifyPermission();
        Phone phone = getPhone(i);
        if (phone == null) {
            return 0;
        }
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            return ((Integer) sendRequest(78, new Pair(Boolean.valueOf(z), str), phone, null)).intValue();
        } catch (Exception e) {
            Log.e("PhoneInterfaceManager", "setIccLockEnabled. Exception e =" + e);
            return 0;
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public int changeIccLockPassword(int i, String str, String str2) {
        enforceModifyPermission();
        Phone phone = getPhone(i);
        if (phone == null) {
            return 0;
        }
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            return ((Integer) sendRequest(76, new Pair(str, str2), phone, null)).intValue();
        } catch (Exception e) {
            Log.e("PhoneInterfaceManager", "changeIccLockPassword. Exception e =" + e);
            return 0;
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public void requestUserActivityNotification() {
        if (this.mNotifyUserActivity.get() || this.mMainThreadHandler.hasMessages(82)) {
            return;
        }
        this.mNotifyUserActivity.set(true);
    }

    public void userActivity() {
        if (Binder.getCallingUid() != 1000) {
            throw new SecurityException("Only the OS may call notifyUserActivity()");
        }
        if (this.mNotifyUserActivity.getAndSet(false)) {
            this.mMainThreadHandler.sendEmptyMessageDelayed(82, 200L);
        }
    }

    public boolean canConnectTo5GInDsdsMode() {
        return this.mApp.getResources().getBoolean(R.bool.config_5g_connection_in_dsds_mode);
    }
}
