package android.telephony.ims.stub;

import android.os.RemoteException;
import android.telephony.ims.ImsException;
import android.telephony.ims.aidl.IRcsFeatureListener;
import android.telephony.ims.feature.RcsFeature;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes3.dex */
public class RcsCapabilityExchange {
    public static final int COMMAND_CODE_FETCH_ERROR = 4;
    public static final int COMMAND_CODE_GENERIC_FAILURE = 2;
    public static final int COMMAND_CODE_INSUFFICIENT_MEMORY = 6;
    public static final int COMMAND_CODE_INVALID_PARAM = 3;
    public static final int COMMAND_CODE_LOST_NETWORK_CONNECTION = 7;
    public static final int COMMAND_CODE_NOT_FOUND = 9;
    public static final int COMMAND_CODE_NOT_SUPPORTED = 8;
    public static final int COMMAND_CODE_NO_CHANGE_IN_CAP = 11;
    public static final int COMMAND_CODE_REQUEST_TIMEOUT = 5;
    public static final int COMMAND_CODE_SERVICE_UNAVAILABLE = 10;
    public static final int COMMAND_CODE_SERVICE_UNKNOWN = 0;
    public static final int COMMAND_CODE_SUCCESS = 1;
    private RcsFeature mFeature;

    @Retention(RetentionPolicy.SOURCE)
    public @interface CommandCode {
    }

    public final void initialize(RcsFeature rcsFeature) {
        this.mFeature = rcsFeature;
    }

    protected final IRcsFeatureListener getListener() throws ImsException {
        if (this.mFeature.getListener() == null) {
            throw new ImsException("Connection to Framework has not been established, wait for onFeatureReady().", 1);
        }
        return this.mFeature.getListener();
    }

    public final void onCommandUpdate(int i, int i2) throws ImsException {
        try {
            getListener().onCommandUpdate(i, i2);
        } catch (RemoteException e) {
            throw new ImsException(e.getMessage(), 1);
        }
    }
}
