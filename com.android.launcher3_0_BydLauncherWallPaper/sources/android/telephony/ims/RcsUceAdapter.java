package android.telephony.ims;

import android.annotation.SystemApi;
import android.content.Context;
import android.net.Uri;
import android.os.Binder;
import android.os.RemoteException;
import android.telephony.TelephonyFrameworkInitializer;
import android.telephony.ims.aidl.IImsRcsController;
import android.telephony.ims.aidl.IRcsUceControllerCallback;
import android.util.Log;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes3.dex */
public class RcsUceAdapter {
    public static final int ERROR_ALREADY_IN_QUEUE = 13;
    public static final int ERROR_FORBIDDEN = 6;
    public static final int ERROR_GENERIC_FAILURE = 1;
    public static final int ERROR_INSUFFICIENT_MEMORY = 11;
    public static final int ERROR_LOST_NETWORK = 12;
    public static final int ERROR_NOT_AUTHORIZED = 5;
    public static final int ERROR_NOT_AVAILABLE = 3;
    public static final int ERROR_NOT_ENABLED = 2;
    public static final int ERROR_NOT_FOUND = 7;
    public static final int ERROR_NOT_REGISTERED = 4;
    public static final int ERROR_REQUEST_TIMEOUT = 10;
    public static final int ERROR_REQUEST_TOO_LARGE = 8;
    public static final int PUBLISH_STATE_NOT_PUBLISHED = 2;
    public static final int PUBLISH_STATE_OK = 1;
    public static final int PUBLISH_STATE_OTHER_ERROR = 6;
    public static final int PUBLISH_STATE_RCS_PROVISION_ERROR = 4;
    public static final int PUBLISH_STATE_REQUEST_TIMEOUT = 5;
    public static final int PUBLISH_STATE_VOLTE_PROVISION_ERROR = 3;
    private static final String TAG = "RcsUceAdapter";
    private final Context mContext;
    private final int mSubId;

    public static class CapabilitiesCallback {
        public void onCapabilitiesReceived(List<RcsContactUceCapability> list) {
        }

        public void onError(int i) {
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ErrorCode {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface PublishState {
    }

    RcsUceAdapter(Context context, int i) {
        this.mContext = context;
        this.mSubId = i;
    }

    public void requestCapabilities(Executor executor, List<Uri> list, CapabilitiesCallback capabilitiesCallback) throws ImsException {
        if (capabilitiesCallback == null) {
            throw new IllegalArgumentException("Must include a non-null AvailabilityCallback.");
        }
        if (executor == null) {
            throw new IllegalArgumentException("Must include a non-null Executor.");
        }
        if (list == null) {
            throw new IllegalArgumentException("Must include non-null contact number list.");
        }
        IImsRcsController iImsRcsController = getIImsRcsController();
        if (iImsRcsController == null) {
            Log.e(TAG, "requestCapabilities: IImsRcsController is null");
            throw new ImsException("Can not find remote IMS service", 1);
        }
        try {
            iImsRcsController.requestCapabilities(this.mSubId, this.mContext.getOpPackageName(), this.mContext.getAttributionTag(), list, new AnonymousClass1(executor, capabilitiesCallback));
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling IImsRcsController#requestCapabilities", e);
            throw new ImsException("Remote IMS Service is not available", 1);
        }
    }

    /* JADX INFO: renamed from: android.telephony.ims.RcsUceAdapter$1, reason: invalid class name */
    class AnonymousClass1 extends IRcsUceControllerCallback.Stub {
        final /* synthetic */ CapabilitiesCallback val$c;
        final /* synthetic */ Executor val$executor;

        AnonymousClass1(Executor executor, CapabilitiesCallback capabilitiesCallback) {
            this.val$executor = executor;
            this.val$c = capabilitiesCallback;
        }

        @Override // android.telephony.ims.aidl.IRcsUceControllerCallback
        public void onCapabilitiesReceived(final List<RcsContactUceCapability> list) {
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                Executor executor = this.val$executor;
                final CapabilitiesCallback capabilitiesCallback = this.val$c;
                executor.execute(new Runnable() { // from class: android.telephony.ims.-$$Lambda$RcsUceAdapter$1$b0-i2iMKdfOsGYTgGHVABh7yYFc
                    @Override // java.lang.Runnable
                    public final void run() {
                        capabilitiesCallback.onCapabilitiesReceived(list);
                    }
                });
            } finally {
                restoreCallingIdentity(jClearCallingIdentity);
            }
        }

        @Override // android.telephony.ims.aidl.IRcsUceControllerCallback
        public void onError(final int i) {
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                Executor executor = this.val$executor;
                final CapabilitiesCallback capabilitiesCallback = this.val$c;
                executor.execute(new Runnable() { // from class: android.telephony.ims.-$$Lambda$RcsUceAdapter$1$TeCE9Z_B0132uz57quWn7DujTOI
                    @Override // java.lang.Runnable
                    public final void run() {
                        capabilitiesCallback.onError(i);
                    }
                });
            } finally {
                restoreCallingIdentity(jClearCallingIdentity);
            }
        }
    }

    public int getUcePublishState() throws ImsException {
        IImsRcsController iImsRcsController = getIImsRcsController();
        if (iImsRcsController == null) {
            Log.e(TAG, "getUcePublishState: IImsRcsController is null");
            throw new ImsException("Can not find remote IMS service", 1);
        }
        try {
            return iImsRcsController.getUcePublishState(this.mSubId);
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling IImsRcsController#getUcePublishState", e);
            throw new ImsException("Remote IMS Service is not available", 1);
        }
    }

    public boolean isUceSettingEnabled() throws ImsException {
        IImsRcsController iImsRcsController = getIImsRcsController();
        if (iImsRcsController == null) {
            Log.e(TAG, "isUceSettingEnabled: IImsRcsController is null");
            throw new ImsException("Can not find remote IMS service", 1);
        }
        try {
            return iImsRcsController.isUceSettingEnabled(this.mSubId, this.mContext.getOpPackageName(), this.mContext.getAttributionTag());
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling IImsRcsController#isUceSettingEnabled", e);
            throw new ImsException("Remote IMS Service is not available", 1);
        }
    }

    @SystemApi
    public void setUceSettingEnabled(boolean z) throws ImsException {
        IImsRcsController iImsRcsController = getIImsRcsController();
        if (iImsRcsController == null) {
            Log.e(TAG, "setUceSettingEnabled: IImsRcsController is null");
            throw new ImsException("Can not find remote IMS service", 1);
        }
        try {
            iImsRcsController.setUceSettingEnabled(this.mSubId, z);
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling IImsRcsController#setUceSettingEnabled", e);
            throw new ImsException("Remote IMS Service is not available", 1);
        }
    }

    private IImsRcsController getIImsRcsController() {
        return IImsRcsController.Stub.asInterface(TelephonyFrameworkInitializer.getTelephonyServiceManager().getTelephonyImsServiceRegisterer().get());
    }
}
