package com.android.phone;

import android.net.Uri;
import android.os.Binder;
import android.os.RemoteException;
import android.os.ServiceSpecificException;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyFrameworkInitializer;
import android.telephony.ims.ImsException;
import android.telephony.ims.RegistrationManager;
import android.telephony.ims.aidl.IImsCapabilityCallback;
import android.telephony.ims.aidl.IImsRcsController;
import android.telephony.ims.aidl.IImsRegistrationCallback;
import android.telephony.ims.aidl.IRcsUceControllerCallback;
import android.util.Log;
import com.android.ims.ImsManager;
import com.android.internal.telephony.IIntegerConsumer;
import com.android.internal.telephony.Phone;
import com.android.internal.telephony.TelephonyPermissions;
import com.android.services.telephony.rcs.RcsFeatureController;
import com.android.services.telephony.rcs.TelephonyRcsService;
import com.android.services.telephony.rcs.UserCapabilityExchangeImpl;
import java.util.List;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes.dex */
public class ImsRcsController extends IImsRcsController.Stub {
    private static ImsRcsController sInstance;
    private PhoneGlobals mApp;
    private TelephonyRcsService mRcsService;

    static ImsRcsController init(PhoneGlobals phoneGlobals) {
        ImsRcsController imsRcsController;
        synchronized (ImsRcsController.class) {
            if (sInstance == null) {
                sInstance = new ImsRcsController(phoneGlobals);
            } else {
                Log.wtf("ImsRcsController", "init() called multiple times!  sInstance = " + sInstance);
            }
            imsRcsController = sInstance;
        }
        return imsRcsController;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private ImsRcsController(PhoneGlobals phoneGlobals) {
        Log.i("ImsRcsController", "ImsRcsController");
        this.mApp = phoneGlobals;
        TelephonyFrameworkInitializer.getTelephonyServiceManager().getTelephonyImsServiceRegisterer().register(this);
    }

    public void registerImsRegistrationCallback(int i, IImsRegistrationCallback iImsRegistrationCallback) {
        enforceReadPrivilegedPermission("registerImsRegistrationCallback");
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            try {
                getRcsFeatureController(i).registerImsRegistrationCallback(i, iImsRegistrationCallback);
            } catch (ImsException e) {
                Log.e("ImsRcsController", "registerImsRegistrationCallback: sudId=" + i + ", " + e.getMessage());
                throw new ServiceSpecificException(e.getCode());
            }
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public void unregisterImsRegistrationCallback(int i, IImsRegistrationCallback iImsRegistrationCallback) {
        enforceReadPrivilegedPermission("unregisterImsRegistrationCallback");
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            try {
                getRcsFeatureController(i).unregisterImsRegistrationCallback(i, iImsRegistrationCallback);
            } catch (ServiceSpecificException e) {
                Log.e("ImsRcsController", "unregisterImsRegistrationCallback: error=" + e.errorCode);
            }
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public void getImsRcsRegistrationState(int i, final IIntegerConsumer iIntegerConsumer) {
        enforceReadPrivilegedPermission("getImsRcsRegistrationState");
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            getRcsFeatureController(i).getRegistrationState(new Consumer() { // from class: com.android.phone.-$$Lambda$ImsRcsController$i6RTajMN7CRsLwp6lcFUlQdhQK8
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ImsRcsController.lambda$getImsRcsRegistrationState$0(iIntegerConsumer, (Integer) obj);
                }
            });
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    static /* synthetic */ void lambda$getImsRcsRegistrationState$0(IIntegerConsumer iIntegerConsumer, Integer num) {
        int iIntValue;
        if (num == null) {
            iIntValue = 0;
        } else {
            try {
                iIntValue = num.intValue();
            } catch (RemoteException unused) {
                Log.w("ImsRcsController", "getImsRcsRegistrationState: callback is not available.");
                return;
            }
        }
        iIntegerConsumer.accept(iIntValue);
    }

    public void getImsRcsRegistrationTransportType(int i, final IIntegerConsumer iIntegerConsumer) {
        enforceReadPrivilegedPermission("getImsRcsRegistrationTransportType");
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            getRcsFeatureController(i).getRegistrationTech(new Consumer() { // from class: com.android.phone.-$$Lambda$ImsRcsController$4f8bSCzUWp_fNkK_1VBQzTRBq-Y
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ImsRcsController.lambda$getImsRcsRegistrationTransportType$1(iIntegerConsumer, (Integer) obj);
                }
            });
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    static /* synthetic */ void lambda$getImsRcsRegistrationTransportType$1(IIntegerConsumer iIntegerConsumer, Integer num) {
        try {
            iIntegerConsumer.accept(((Integer) RegistrationManager.IMS_REG_TO_ACCESS_TYPE_MAP.get(Integer.valueOf(num == null ? -1 : num.intValue()))).intValue());
        } catch (RemoteException unused) {
            Log.w("ImsRcsController", "getImsRcsRegistrationTransportType: callback is not available.");
        }
    }

    public void registerRcsAvailabilityCallback(int i, IImsCapabilityCallback iImsCapabilityCallback) {
        enforceReadPrivilegedPermission("registerRcsAvailabilityCallback");
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            try {
                getRcsFeatureController(i).registerRcsAvailabilityCallback(i, iImsCapabilityCallback);
            } catch (ImsException e) {
                Log.e("ImsRcsController", "registerRcsAvailabilityCallback: sudId=" + i + ", " + e.getMessage());
                throw new ServiceSpecificException(e.getCode());
            }
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public void unregisterRcsAvailabilityCallback(int i, IImsCapabilityCallback iImsCapabilityCallback) {
        enforceReadPrivilegedPermission("unregisterRcsAvailabilityCallback");
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            getRcsFeatureController(i).unregisterRcsAvailabilityCallback(i, iImsCapabilityCallback);
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public boolean isCapable(int i, int i2, int i3) {
        enforceReadPrivilegedPermission("isCapable");
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            return getRcsFeatureController(i).isCapable(i2, i3);
        } catch (ImsException e) {
            Log.e("ImsRcsController", "isCapable: sudId=" + i + ", capability=" + i2 + ", " + e.getMessage());
            return false;
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public boolean isAvailable(int i, int i2) {
        enforceReadPrivilegedPermission("isAvailable");
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            return getRcsFeatureController(i).isAvailable(i2);
        } catch (ImsException e) {
            Log.e("ImsRcsController", "isAvailable: sudId=" + i + ", capability=" + i2 + ", " + e.getMessage());
            return false;
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: android.os.ServiceSpecificException */
    public void requestCapabilities(int i, String str, String str2, List<Uri> list, IRcsUceControllerCallback iRcsUceControllerCallback) throws ServiceSpecificException {
        enforceReadPrivilegedPermission("requestCapabilities");
        if (!isUceSettingEnabled(i, str, str2)) {
            throw new ServiceSpecificException(2, "The user has not enabled UCE for this subscription.");
        }
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            UserCapabilityExchangeImpl userCapabilityExchangeImpl = (UserCapabilityExchangeImpl) getRcsFeatureController(i).getFeature(UserCapabilityExchangeImpl.class);
            if (userCapabilityExchangeImpl == null) {
                throw new ServiceSpecificException(2, "This subscription does not support UCE.");
            }
            userCapabilityExchangeImpl.requestCapabilities(list, iRcsUceControllerCallback);
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public int getUcePublishState(int i) {
        enforceReadPrivilegedPermission("getUcePublishState");
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            UserCapabilityExchangeImpl userCapabilityExchangeImpl = (UserCapabilityExchangeImpl) getRcsFeatureController(i).getFeature(UserCapabilityExchangeImpl.class);
            if (userCapabilityExchangeImpl == null) {
                throw new ServiceSpecificException(2, "This subscription does not support UCE.");
            }
            return userCapabilityExchangeImpl.getUcePublishState();
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public boolean isUceSettingEnabled(int i, String str, String str2) {
        if (!TelephonyPermissions.checkCallingOrSelfReadPhoneState(this.mApp, i, str, str2, "isUceSettingEnabled")) {
            Log.w("ImsRcsController", "isUceSettingEnabled: READ_PHONE_STATE app op disabled when accessing isUceSettingEnabled");
            return false;
        }
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            return SubscriptionManager.getBooleanSubscriptionProperty(i, "ims_rcs_uce_enabled", false, this.mApp);
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public void setUceSettingEnabled(int i, boolean z) {
        enforceModifyPermission();
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            SubscriptionManager.setSubscriptionProperty(i, "ims_rcs_uce_enabled", z ? "1" : "0");
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    private void enforceReadPrivilegedPermission(String str) {
        this.mApp.enforceCallingOrSelfPermission("android.permission.READ_PRIVILEGED_PHONE_STATE", str);
    }

    private void enforceModifyPermission() {
        this.mApp.enforceCallingOrSelfPermission("android.permission.MODIFY_PHONE_STATE", null);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: android.os.ServiceSpecificException */
    private RcsFeatureController getRcsFeatureController(int i) throws ServiceSpecificException {
        if (!ImsManager.isImsSupportedOnDevice(this.mApp)) {
            throw new ServiceSpecificException(2, "IMS is not available on device.");
        }
        if (this.mRcsService == null) {
            throw new ServiceSpecificException(2, "IMS is not available on device.");
        }
        Phone phone = PhoneGlobals.getPhone(i);
        if (phone == null) {
            throw new ServiceSpecificException(3, "Invalid subscription Id: " + i);
        }
        RcsFeatureController featureController = this.mRcsService.getFeatureController(phone.getPhoneId());
        if (featureController != null) {
            return featureController;
        }
        throw new ServiceSpecificException(2, "The requested operation is not supported for subId " + i);
    }

    void setRcsService(TelephonyRcsService telephonyRcsService) {
        this.mRcsService = telephonyRcsService;
    }
}
