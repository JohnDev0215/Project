package android.telephony.ims.stub;

import android.net.Uri;
import android.os.RemoteException;
import android.telephony.ims.ImsException;
import android.telephony.ims.RcsContactUceCapability;
import android.util.Log;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class RcsPresenceExchangeImplBase extends RcsCapabilityExchange {
    public static final int CAPABILITY_UPDATE_TRIGGER_ETAG_EXPIRED = 0;
    public static final int CAPABILITY_UPDATE_TRIGGER_MOVE_TO_2G = 6;
    public static final int CAPABILITY_UPDATE_TRIGGER_MOVE_TO_3G = 5;
    public static final int CAPABILITY_UPDATE_TRIGGER_MOVE_TO_EHRPD = 3;
    public static final int CAPABILITY_UPDATE_TRIGGER_MOVE_TO_HSPAPLUS = 4;
    public static final int CAPABILITY_UPDATE_TRIGGER_MOVE_TO_IWLAN = 8;
    public static final int CAPABILITY_UPDATE_TRIGGER_MOVE_TO_LTE_VOPS_DISABLED = 1;
    public static final int CAPABILITY_UPDATE_TRIGGER_MOVE_TO_LTE_VOPS_ENABLED = 2;
    public static final int CAPABILITY_UPDATE_TRIGGER_MOVE_TO_NR5G_VOPS_DISABLED = 10;
    public static final int CAPABILITY_UPDATE_TRIGGER_MOVE_TO_NR5G_VOPS_ENABLED = 11;
    public static final int CAPABILITY_UPDATE_TRIGGER_MOVE_TO_WLAN = 7;
    public static final int CAPABILITY_UPDATE_TRIGGER_UNKNOWN = 9;
    private static final String LOG_TAG = "RcsPresenceExchangeIB";
    public static final int RESPONSE_FORBIDDEN = 3;
    public static final int RESPONSE_NOT_AUTHORIZED_FOR_PRESENCE = 2;
    public static final int RESPONSE_NOT_FOUND = 4;
    public static final int RESPONSE_NOT_REGISTERED = 1;
    public static final int RESPONSE_SIP_INTERVAL_TOO_SHORT = 7;
    public static final int RESPONSE_SIP_REQUEST_TIMEOUT = 5;
    public static final int RESPONSE_SIP_SERVICE_UNAVAILABLE = 8;
    public static final int RESPONSE_SUBSCRIBE_GENERIC_FAILURE = -1;
    public static final int RESPONSE_SUBSCRIBE_TOO_LARGE = 6;
    public static final int RESPONSE_SUCCESS = 0;

    @Retention(RetentionPolicy.SOURCE)
    public @interface PresenceResponseCode {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface StackPublishTriggerType {
    }

    public final void onNetworkResponse(int i, String str, int i2) throws ImsException {
        try {
            getListener().onNetworkResponse(i, str, i2);
        } catch (RemoteException e) {
            throw new ImsException(e.getMessage(), 1);
        }
    }

    public final void onCapabilityRequestResponse(List<RcsContactUceCapability> list, int i) throws ImsException {
        try {
            getListener().onCapabilityRequestResponsePresence(list, i);
        } catch (RemoteException e) {
            throw new ImsException(e.getMessage(), 1);
        }
    }

    public final void onNotifyUpdateCapabilites(int i) throws ImsException {
        try {
            getListener().onNotifyUpdateCapabilities(i);
        } catch (RemoteException e) {
            throw new ImsException(e.getMessage(), 1);
        }
    }

    public final void onUnpublish() throws ImsException {
        try {
            getListener().onUnpublish();
        } catch (RemoteException e) {
            throw new ImsException(e.getMessage(), 1);
        }
    }

    public void requestCapabilities(List<Uri> list, int i) {
        Log.w(LOG_TAG, "requestCapabilities called with no implementation.");
        try {
            getListener().onCommandUpdate(8, i);
        } catch (RemoteException | ImsException unused) {
        }
    }

    public void updateCapabilities(RcsContactUceCapability rcsContactUceCapability, int i) {
        Log.w(LOG_TAG, "updateCapabilities called with no implementation.");
        try {
            getListener().onCommandUpdate(8, i);
        } catch (RemoteException | ImsException unused) {
        }
    }
}
