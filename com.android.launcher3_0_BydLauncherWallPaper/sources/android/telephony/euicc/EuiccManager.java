package android.telephony.euicc;

import android.annotation.SystemApi;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.os.RemoteException;
import android.telephony.TelephonyFrameworkInitializer;
import android.telephony.TelephonyManager;
import com.android.internal.telephony.euicc.IEuiccController;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/* JADX INFO: loaded from: classes3.dex */
public class EuiccManager {

    @SystemApi
    public static final String ACTION_DELETE_SUBSCRIPTION_PRIVILEGED = "android.telephony.euicc.action.DELETE_SUBSCRIPTION_PRIVILEGED";
    public static final String ACTION_MANAGE_EMBEDDED_SUBSCRIPTIONS = "android.telephony.euicc.action.MANAGE_EMBEDDED_SUBSCRIPTIONS";
    public static final String ACTION_NOTIFY_CARRIER_SETUP_INCOMPLETE = "android.telephony.euicc.action.NOTIFY_CARRIER_SETUP_INCOMPLETE";

    @SystemApi
    public static final String ACTION_OTA_STATUS_CHANGED = "android.telephony.euicc.action.OTA_STATUS_CHANGED";

    @SystemApi
    public static final String ACTION_PROVISION_EMBEDDED_SUBSCRIPTION = "android.telephony.euicc.action.PROVISION_EMBEDDED_SUBSCRIPTION";

    @SystemApi
    public static final String ACTION_RENAME_SUBSCRIPTION_PRIVILEGED = "android.telephony.euicc.action.RENAME_SUBSCRIPTION_PRIVILEGED";
    public static final String ACTION_RESOLVE_ERROR = "android.telephony.euicc.action.RESOLVE_ERROR";
    public static final String ACTION_START_EUICC_ACTIVATION = "android.telephony.euicc.action.START_EUICC_ACTIVATION";

    @SystemApi
    public static final String ACTION_TOGGLE_SUBSCRIPTION_PRIVILEGED = "android.telephony.euicc.action.TOGGLE_SUBSCRIPTION_PRIVILEGED";
    public static final int EMBEDDED_SUBSCRIPTION_RESULT_ERROR = 2;
    public static final int EMBEDDED_SUBSCRIPTION_RESULT_OK = 0;
    public static final int EMBEDDED_SUBSCRIPTION_RESULT_RESOLVABLE_ERROR = 1;
    public static final int ERROR_ADDRESS_MISSING = 10011;
    public static final int ERROR_CARRIER_LOCKED = 10000;
    public static final int ERROR_CERTIFICATE_ERROR = 10012;
    public static final int ERROR_CONNECTION_ERROR = 10014;
    public static final int ERROR_DISALLOWED_BY_PPR = 10010;
    public static final int ERROR_EUICC_INSUFFICIENT_MEMORY = 10004;
    public static final int ERROR_EUICC_MISSING = 10006;
    public static final int ERROR_INCOMPATIBLE_CARRIER = 10003;
    public static final int ERROR_INSTALL_PROFILE = 10009;
    public static final int ERROR_INVALID_ACTIVATION_CODE = 10001;
    public static final int ERROR_INVALID_CONFIRMATION_CODE = 10002;
    public static final int ERROR_INVALID_RESPONSE = 10015;
    public static final int ERROR_NO_PROFILES_AVAILABLE = 10013;
    public static final int ERROR_OPERATION_BUSY = 10016;
    public static final int ERROR_SIM_MISSING = 10008;
    public static final int ERROR_TIME_OUT = 10005;
    public static final int ERROR_UNSUPPORTED_VERSION = 10007;

    @SystemApi
    public static final int EUICC_ACTIVATION_TYPE_ACCOUNT_REQUIRED = 4;

    @SystemApi
    public static final int EUICC_ACTIVATION_TYPE_BACKUP = 2;

    @SystemApi
    public static final int EUICC_ACTIVATION_TYPE_DEFAULT = 1;

    @SystemApi
    public static final int EUICC_ACTIVATION_TYPE_TRANSFER = 3;

    @SystemApi
    public static final int EUICC_OTA_FAILED = 2;

    @SystemApi
    public static final int EUICC_OTA_IN_PROGRESS = 1;

    @SystemApi
    public static final int EUICC_OTA_NOT_NEEDED = 4;

    @SystemApi
    public static final int EUICC_OTA_STATUS_UNAVAILABLE = 5;

    @SystemApi
    public static final int EUICC_OTA_SUCCEEDED = 3;

    @SystemApi
    public static final String EXTRA_ACTIVATION_TYPE = "android.telephony.euicc.extra.ACTIVATION_TYPE";
    public static final String EXTRA_EMBEDDED_SUBSCRIPTION_DETAILED_CODE = "android.telephony.euicc.extra.EMBEDDED_SUBSCRIPTION_DETAILED_CODE";
    public static final String EXTRA_EMBEDDED_SUBSCRIPTION_DOWNLOADABLE_SUBSCRIPTION = "android.telephony.euicc.extra.EMBEDDED_SUBSCRIPTION_DOWNLOADABLE_SUBSCRIPTION";

    @SystemApi
    public static final String EXTRA_EMBEDDED_SUBSCRIPTION_DOWNLOADABLE_SUBSCRIPTIONS = "android.telephony.euicc.extra.EMBEDDED_SUBSCRIPTION_DOWNLOADABLE_SUBSCRIPTIONS";
    public static final String EXTRA_EMBEDDED_SUBSCRIPTION_ERROR_CODE = "android.telephony.euicc.extra.EMBEDDED_SUBSCRIPTION_ERROR_CODE";
    public static final String EXTRA_EMBEDDED_SUBSCRIPTION_OPERATION_CODE = "android.telephony.euicc.extra.EMBEDDED_SUBSCRIPTION_OPERATION_CODE";
    public static final String EXTRA_EMBEDDED_SUBSCRIPTION_RESOLUTION_ACTION = "android.telephony.euicc.extra.EMBEDDED_SUBSCRIPTION_RESOLUTION_ACTION";
    public static final String EXTRA_EMBEDDED_SUBSCRIPTION_RESOLUTION_CALLBACK_INTENT = "android.telephony.euicc.extra.EMBEDDED_SUBSCRIPTION_RESOLUTION_CALLBACK_INTENT";
    public static final String EXTRA_EMBEDDED_SUBSCRIPTION_RESOLUTION_INTENT = "android.telephony.euicc.extra.EMBEDDED_SUBSCRIPTION_RESOLUTION_INTENT";
    public static final String EXTRA_EMBEDDED_SUBSCRIPTION_SMDX_REASON_CODE = "android.telephony.euicc.extra.EMBEDDED_SUBSCRIPTION_SMDX_REASON_CODE";
    public static final String EXTRA_EMBEDDED_SUBSCRIPTION_SMDX_SUBJECT_CODE = "android.telephony.euicc.extra.EMBEDDED_SUBSCRIPTION_SMDX_SUBJECT_CODE";

    @SystemApi
    public static final String EXTRA_ENABLE_SUBSCRIPTION = "android.telephony.euicc.extra.ENABLE_SUBSCRIPTION";

    @SystemApi
    public static final String EXTRA_FORCE_PROVISION = "android.telephony.euicc.extra.FORCE_PROVISION";

    @SystemApi
    public static final String EXTRA_FROM_SUBSCRIPTION_ID = "android.telephony.euicc.extra.FROM_SUBSCRIPTION_ID";

    @SystemApi
    public static final String EXTRA_PHYSICAL_SLOT_ID = "android.telephony.euicc.extra.PHYSICAL_SLOT_ID";

    @SystemApi
    public static final String EXTRA_SUBSCRIPTION_ID = "android.telephony.euicc.extra.SUBSCRIPTION_ID";

    @SystemApi
    public static final String EXTRA_SUBSCRIPTION_NICKNAME = "android.telephony.euicc.extra.SUBSCRIPTION_NICKNAME";
    public static final String EXTRA_USE_QR_SCANNER = "android.telephony.euicc.extra.USE_QR_SCANNER";
    public static final String META_DATA_CARRIER_ICON = "android.telephony.euicc.carriericon";
    public static final int OPERATION_APDU = 8;
    public static final int OPERATION_DOWNLOAD = 5;
    public static final int OPERATION_EUICC_CARD = 3;
    public static final int OPERATION_EUICC_GSMA = 7;
    public static final int OPERATION_HTTP = 11;
    public static final int OPERATION_METADATA = 6;
    public static final int OPERATION_SIM_SLOT = 2;
    public static final int OPERATION_SMDX = 9;
    public static final int OPERATION_SMDX_SUBJECT_REASON_CODE = 10;
    public static final int OPERATION_SWITCH = 4;
    public static final int OPERATION_SYSTEM = 1;
    private int mCardId;
    private final Context mContext;

    @Retention(RetentionPolicy.SOURCE)
    public @interface ErrorCode {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface EuiccActivationType {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface OperationCode {
    }

    @SystemApi
    @Retention(RetentionPolicy.SOURCE)
    public @interface OtaStatus {
    }

    public EuiccManager(Context context) {
        this.mContext = context;
        this.mCardId = ((TelephonyManager) context.getSystemService("phone")).getCardIdForDefaultEuicc();
    }

    private EuiccManager(Context context, int i) {
        this.mContext = context;
        this.mCardId = i;
    }

    public EuiccManager createForCardId(int i) {
        return new EuiccManager(this.mContext, i);
    }

    public boolean isEnabled() {
        return getIEuiccController() != null && refreshCardIdIfUninitialized();
    }

    public String getEid() {
        if (!isEnabled()) {
            return null;
        }
        try {
            return getIEuiccController().getEid(this.mCardId, this.mContext.getOpPackageName());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public int getOtaStatus() {
        if (!isEnabled()) {
            return 5;
        }
        try {
            return getIEuiccController().getOtaStatus(this.mCardId);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void downloadSubscription(DownloadableSubscription downloadableSubscription, boolean z, PendingIntent pendingIntent) {
        if (!isEnabled()) {
            sendUnavailableError(pendingIntent);
            return;
        }
        try {
            getIEuiccController().downloadSubscription(this.mCardId, downloadableSubscription, z, this.mContext.getOpPackageName(), null, pendingIntent);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void startResolutionActivity(Activity activity, int i, Intent intent, PendingIntent pendingIntent) throws IntentSender.SendIntentException {
        PendingIntent pendingIntent2 = (PendingIntent) intent.getParcelableExtra(EXTRA_EMBEDDED_SUBSCRIPTION_RESOLUTION_INTENT);
        if (pendingIntent2 == null) {
            throw new IllegalArgumentException("Invalid result intent");
        }
        Intent intent2 = new Intent();
        intent2.putExtra(EXTRA_EMBEDDED_SUBSCRIPTION_RESOLUTION_CALLBACK_INTENT, pendingIntent);
        activity.startIntentSenderForResult(pendingIntent2.getIntentSender(), i, intent2, 0, 0, 0);
    }

    @SystemApi
    public void continueOperation(Intent intent, Bundle bundle) {
        if (!isEnabled()) {
            PendingIntent pendingIntent = (PendingIntent) intent.getParcelableExtra(EXTRA_EMBEDDED_SUBSCRIPTION_RESOLUTION_CALLBACK_INTENT);
            if (pendingIntent != null) {
                sendUnavailableError(pendingIntent);
                return;
            }
            return;
        }
        try {
            getIEuiccController().continueOperation(this.mCardId, intent, bundle);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public void getDownloadableSubscriptionMetadata(DownloadableSubscription downloadableSubscription, PendingIntent pendingIntent) {
        if (!isEnabled()) {
            sendUnavailableError(pendingIntent);
            return;
        }
        try {
            getIEuiccController().getDownloadableSubscriptionMetadata(this.mCardId, downloadableSubscription, this.mContext.getOpPackageName(), pendingIntent);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public void getDefaultDownloadableSubscriptionList(PendingIntent pendingIntent) {
        if (!isEnabled()) {
            sendUnavailableError(pendingIntent);
            return;
        }
        try {
            getIEuiccController().getDefaultDownloadableSubscriptionList(this.mCardId, this.mContext.getOpPackageName(), pendingIntent);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public EuiccInfo getEuiccInfo() {
        if (!isEnabled()) {
            return null;
        }
        try {
            return getIEuiccController().getEuiccInfo(this.mCardId);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void deleteSubscription(int i, PendingIntent pendingIntent) {
        if (!isEnabled()) {
            sendUnavailableError(pendingIntent);
            return;
        }
        try {
            getIEuiccController().deleteSubscription(this.mCardId, i, this.mContext.getOpPackageName(), pendingIntent);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void switchToSubscription(int i, PendingIntent pendingIntent) {
        if (!isEnabled()) {
            sendUnavailableError(pendingIntent);
            return;
        }
        try {
            getIEuiccController().switchToSubscription(this.mCardId, i, this.mContext.getOpPackageName(), pendingIntent);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void updateSubscriptionNickname(int i, String str, PendingIntent pendingIntent) {
        if (!isEnabled()) {
            sendUnavailableError(pendingIntent);
            return;
        }
        try {
            getIEuiccController().updateSubscriptionNickname(this.mCardId, i, str, this.mContext.getOpPackageName(), pendingIntent);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    @Deprecated
    public void eraseSubscriptions(PendingIntent pendingIntent) {
        if (!isEnabled()) {
            sendUnavailableError(pendingIntent);
            return;
        }
        try {
            getIEuiccController().eraseSubscriptions(this.mCardId, pendingIntent);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public void eraseSubscriptions(int i, PendingIntent pendingIntent) {
        if (!isEnabled()) {
            sendUnavailableError(pendingIntent);
            return;
        }
        try {
            getIEuiccController().eraseSubscriptionsWithOptions(this.mCardId, i, pendingIntent);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void retainSubscriptionsForFactoryReset(PendingIntent pendingIntent) {
        if (!isEnabled()) {
            sendUnavailableError(pendingIntent);
            return;
        }
        try {
            getIEuiccController().retainSubscriptionsForFactoryReset(this.mCardId, pendingIntent);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public void setSupportedCountries(List<String> list) {
        if (isEnabled()) {
            try {
                getIEuiccController().setSupportedCountries(true, (List) list.stream().map($$Lambda$EuiccManager$pEEA7J_dnVvEktC5QAmFH5QOwek.INSTANCE).collect(Collectors.toList()));
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
    }

    @SystemApi
    public void setUnsupportedCountries(List<String> list) {
        if (isEnabled()) {
            try {
                getIEuiccController().setSupportedCountries(false, (List) list.stream().map($$Lambda$EuiccManager$pEEA7J_dnVvEktC5QAmFH5QOwek.INSTANCE).collect(Collectors.toList()));
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
    }

    @SystemApi
    public List<String> getSupportedCountries() {
        if (!isEnabled()) {
            return Collections.emptyList();
        }
        try {
            return getIEuiccController().getSupportedCountries(true);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public List<String> getUnsupportedCountries() {
        if (!isEnabled()) {
            return Collections.emptyList();
        }
        try {
            return getIEuiccController().getSupportedCountries(false);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public boolean isSupportedCountry(String str) {
        if (!isEnabled()) {
            return false;
        }
        try {
            return getIEuiccController().isSupportedCountry(str.toUpperCase());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    private boolean refreshCardIdIfUninitialized() {
        if (this.mCardId == -2) {
            this.mCardId = ((TelephonyManager) this.mContext.getSystemService("phone")).getCardIdForDefaultEuicc();
        }
        return this.mCardId != -2;
    }

    private static void sendUnavailableError(PendingIntent pendingIntent) {
        try {
            pendingIntent.send(2);
        } catch (PendingIntent.CanceledException unused) {
        }
    }

    private static IEuiccController getIEuiccController() {
        return IEuiccController.Stub.asInterface(TelephonyFrameworkInitializer.getTelephonyServiceManager().getEuiccControllerService().get());
    }
}
