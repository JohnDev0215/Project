package android.telephony.ims.stub;

import android.net.Uri;
import android.os.RemoteException;
import android.telephony.ims.ImsException;
import android.telephony.ims.RcsContactUceCapability;
import android.util.Log;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes3.dex */
public class RcsSipOptionsImplBase extends RcsCapabilityExchange {
    private static final String LOG_TAG = "RcsSipOptionsImplBase";
    public static final int RESPONSE_BAD_REQUEST = 5;
    public static final int RESPONSE_DOES_NOT_EXIST_ANYWHERE = 4;
    public static final int RESPONSE_GENERIC_FAILURE = -1;
    public static final int RESPONSE_NOT_FOUND = 3;
    public static final int RESPONSE_REQUEST_TIMEOUT = 2;
    public static final int RESPONSE_SUCCESS = 0;
    public static final int RESPONSE_TEMPORARILY_UNAVAILABLE = 1;

    @Retention(RetentionPolicy.SOURCE)
    public @interface SipResponseCode {
    }

    public final void onCapabilityRequestResponse(int i, String str, RcsContactUceCapability rcsContactUceCapability, int i2) throws ImsException {
        try {
            getListener().onCapabilityRequestResponseOptions(i, str, rcsContactUceCapability, i2);
        } catch (RemoteException e) {
            throw new ImsException(e.getMessage(), 1);
        }
    }

    public final void onRemoteCapabilityRequest(Uri uri, RcsContactUceCapability rcsContactUceCapability, int i) throws ImsException {
        try {
            getListener().onRemoteCapabilityRequest(uri, rcsContactUceCapability, i);
        } catch (RemoteException e) {
            throw new ImsException(e.getMessage(), 1);
        }
    }

    public void sendCapabilityRequest(Uri uri, RcsContactUceCapability rcsContactUceCapability, int i) {
        Log.w(LOG_TAG, "sendCapabilityRequest called with no implementation.");
        try {
            getListener().onCommandUpdate(8, i);
        } catch (RemoteException | ImsException unused) {
        }
    }

    public void respondToCapabilityRequest(String str, RcsContactUceCapability rcsContactUceCapability, int i) {
        Log.w(LOG_TAG, "respondToCapabilityRequest called with no implementation.");
        try {
            getListener().onCommandUpdate(8, i);
        } catch (RemoteException | ImsException unused) {
        }
    }

    public void respondToCapabilityRequestWithError(Uri uri, int i, String str, int i2) {
        Log.w(LOG_TAG, "respondToCapabiltyRequestWithError called with no implementation.");
        try {
            getListener().onCommandUpdate(8, i2);
        } catch (RemoteException | ImsException unused) {
        }
    }
}
