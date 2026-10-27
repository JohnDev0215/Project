package android.telephony.ims;

import android.content.Context;
import android.telephony.SubscriptionManager;

/* JADX INFO: loaded from: classes3.dex */
public class ImsManager {
    public static final String ACTION_FORBIDDEN_NO_SERVICE_AUTHORIZATION = "com.android.internal.intent.action.ACTION_FORBIDDEN_NO_SERVICE_AUTHORIZATION";
    public static final String ACTION_WFC_IMS_REGISTRATION_ERROR = "android.telephony.ims.action.WFC_IMS_REGISTRATION_ERROR";
    public static final String EXTRA_WFC_REGISTRATION_FAILURE_MESSAGE = "android.telephony.ims.extra.WFC_REGISTRATION_FAILURE_MESSAGE";
    public static final String EXTRA_WFC_REGISTRATION_FAILURE_TITLE = "android.telephony.ims.extra.WFC_REGISTRATION_FAILURE_TITLE";
    private Context mContext;

    public ImsManager(Context context) {
        this.mContext = context;
    }

    public ImsRcsManager getImsRcsManager(int i) {
        if (!SubscriptionManager.isValidSubscriptionId(i)) {
            throw new IllegalArgumentException("Invalid subscription ID: " + i);
        }
        return new ImsRcsManager(this.mContext, i);
    }

    public ImsMmTelManager getImsMmTelManager(int i) {
        if (!SubscriptionManager.isValidSubscriptionId(i)) {
            throw new IllegalArgumentException("Invalid subscription ID: " + i);
        }
        return new ImsMmTelManager(i);
    }
}
