package com.amazon.mp3.prime;

import android.content.Context;
import android.content.SharedPreferences;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.AccountRequestUtil;
import com.amazon.mp3.account.credentials.AccountCredentialStorage;
import com.amazon.mp3.account.credentials.AccountCredentialUtil;
import com.amazon.mp3.net.stratus.StratusRequest;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.Log;
import com.amazon.music.account.AccountManagerSingleton;
import com.amazon.music.account.DataNotReadyException;
import com.amazon.music.account.Device;
import com.amazon.music.inappmessaging.internal.model.Splash;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
public class DeviceAuthorizationManager {
    private static final String TAG = "DeviceAuthorizationManager";
    private static DeviceAuthorizationManager sInstance;
    private Context mContext = AmazonApplication.getContext();
    private boolean mHasAttemptedAutoAuthorization = false;

    public enum CurrentAuthorizationState {
        AUTHORIZED("AUTHORIZED"),
        DEAUTHORIZED("DEAUTHORIZED"),
        NEVER_AUTHORIZED("NEVER_AUTHORIZED");

        private final String mAuthState;

        CurrentAuthorizationState(String state) {
            this.mAuthState = state;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.mAuthState;
        }
    }

    public enum AuthorizationAvailability {
        AUTHORIZABLE("AUTHORIZABLE"),
        DEAUTHORIZABLE("DEAUTHORIZABLE"),
        TRANSFERRABLE("TRANSFERRABLE"),
        NONTRANSFERRABLE("NONTRANSFERRABLE"),
        UNKNOWN("UNKNOWN");

        private final String mAuthState;

        AuthorizationAvailability(String state) {
            this.mAuthState = state;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.mAuthState;
        }
    }

    private DeviceAuthorizationManager() {
    }

    public static DeviceAuthorizationManager getInstance() {
        if (sInstance == null) {
            sInstance = new DeviceAuthorizationManager();
        }
        return sInstance;
    }

    private SharedPreferences getSharedPreference() {
        return this.mContext.getSharedPreferences("com.amazon.mp3_DeviceAuthorizationManager", 4);
    }

    public void refresh() {
        if (AccountCredentialUtil.get().isSignedIn() && ConnectivityUtil.hasAnyInternetConnection() && AmazonApplication.getCapabilities().shouldCloudBeSupported()) {
            saveAuthorizedResponse(execute(StratusRequest.RetrieveDevice));
            AccountManagerSingleton.get().updateDevices();
            updatePrimeAuthorizationTimestamp();
            return;
        }
        Log.info(TAG, "Device Authorization not refreshed");
    }

    public boolean isAuthorized() {
        return getCurrentAuthorizationState() == CurrentAuthorizationState.AUTHORIZED;
    }

    private CurrentAuthorizationState getCurrentAuthorizationState() {
        return CurrentAuthorizationState.valueOf(getSharedPreference().getString("currentState", CurrentAuthorizationState.DEAUTHORIZED.toString()));
    }

    private AuthorizationAvailability getAvailableAuthorizationState() {
        return AuthorizationAvailability.valueOf(getSharedPreference().getString("availableState", AuthorizationAvailability.UNKNOWN.toString()));
    }

    public boolean authorizeDevice() {
        CurrentAuthorizationState currentAuthorizationStateSaveAuthorizedResponse = saveAuthorizedResponse(execute(StratusRequest.AuthorizeDevice));
        AccountManagerSingleton.get().updateDevices();
        return currentAuthorizationStateSaveAuthorizedResponse == CurrentAuthorizationState.AUTHORIZED;
    }

    private CurrentAuthorizationState saveAuthorizedResponse(final JSONObject response) {
        CurrentAuthorizationState currentAuthorizationStateValueOf = null;
        if (response == null) {
            return null;
        }
        try {
            String string = response.getJSONObject("device").getJSONObject("currentState").getString("deviceAuthState");
            String string2 = response.getJSONObject("device").getJSONObject("availableState").getString("nextState");
            currentAuthorizationStateValueOf = CurrentAuthorizationState.valueOf(string);
            SharedPreferences.Editor editorEdit = getSharedPreference().edit();
            editorEdit.putString("currentState", string);
            editorEdit.putString("availableState", string2);
            editorEdit.apply();
            return currentAuthorizationStateValueOf;
        } catch (JSONException e) {
            logException("Error parsing response to check if authorized", e);
            return currentAuthorizationStateValueOf;
        }
    }

    public boolean isPrimeAuthorized() {
        try {
            return AccountManagerSingleton.get().getDevice().getCapabilities().contains(Device.Capability.RETRIEVE_ROBIN_CONTENT);
        } catch (DataNotReadyException unused) {
            return false;
        }
    }

    public boolean shouldRefreshPrimeAuthorization() {
        return (System.currentTimeMillis() / 1000) - getSharedPreference().getLong("isPrimeAuthorizedTimestampSecs", 0L) > 1800;
    }

    public boolean primeAuthorizeDevice() {
        updatePrimeAuthorizationTimestamp();
        return AccountManagerSingleton.get().catalogAuthorizeDevice();
    }

    private void updatePrimeAuthorizationTimestamp() {
        SharedPreferences.Editor editorEdit = getSharedPreference().edit();
        editorEdit.putLong("isPrimeAuthorizedTimestampSecs", System.currentTimeMillis() / 1000);
        editorEdit.apply();
    }

    public int getPrimeDeviceLimit() {
        try {
            return AccountManagerSingleton.get().getDeviceCapabilities().getMaxCatalogDeviceAuthorizations();
        } catch (DataNotReadyException unused) {
            return 4;
        }
    }

    public int getRemainingDeviceAuthorizations() {
        try {
            return AccountManagerSingleton.get().getDeviceAuthorizations().getRemainingLibraryDeviceAuthorizations();
        } catch (DataNotReadyException unused) {
            return 0;
        }
    }

    private JSONObject execute(StratusRequest stratusRequest) {
        return execute(stratusRequest, null);
    }

    private JSONObject execute(StratusRequest stratusRequest, JSONObject requestParams) {
        if (requestParams == null) {
            requestParams = new JSONObject();
        }
        try {
            AccountCredentialStorage accountCredentialStorage = AccountCredentialStorage.get();
            requestParams.put(Splash.PARAMS_DEVICE_TYPE, accountCredentialStorage.getDeviceType());
            requestParams.put("targetDeviceType", accountCredentialStorage.getDeviceType());
            requestParams.put(Splash.PARAMS_DEVICE_ID, accountCredentialStorage.getDeviceId());
            requestParams.put("targetDeviceId", accountCredentialStorage.getDeviceId());
            if (stratusRequest == StratusRequest.AuthorizeDevice) {
                requestParams.put("capabilitiesToGrant", new JSONArray().put("RETRIEVE_ROBIN_CONTENT"));
            }
            try {
                return stratusRequest.execute(requestParams);
            } catch (Exception e) {
                logException("Error obtaining StratusRequest response", e);
                return null;
            }
        } catch (JSONException e2) {
            logException("Error inserting request parameters", e2);
            return null;
        }
    }

    private void logException(final String message, final Exception e) {
        Log.error(TAG, message, e);
    }

    public boolean autoAuthorize(Context context) {
        boolean z = AccountManagerSingleton.get().getDevice().getAuthorizationState() == Device.AuthorizationState.AUTHORIZED;
        if (isAuthorized() != z || shouldRefreshPrimeAuthorization()) {
            refresh();
        }
        if (isAuthorized() && !isPrimeAuthorized() && !primeAuthorizeDevice()) {
            Log.error(TAG, "Failed to prime authorize device");
        }
        if (isAuthorized() && z) {
            Log.debug(TAG, "Device is already authorized");
            return true;
        }
        if (this.mHasAttemptedAutoAuthorization) {
            return false;
        }
        if (getAvailableAuthorizationState() == AuthorizationAvailability.UNKNOWN) {
            refresh();
        }
        if (!canAutoAuthorizeDevice()) {
            Log.debug(TAG, "Device in invalid availableState for autoAuthorize");
            this.mHasAttemptedAutoAuthorization = true;
            return false;
        }
        try {
            if (AccountRequestUtil.getAccountDetails(context).getAccountVerified() || AccountManagerSingleton.get().getUser().isAccountVerified()) {
                if (authorizeDevice()) {
                    Log.info(TAG, "AutoAuthorize Success");
                    this.mHasAttemptedAutoAuthorization = false;
                    AccountRequestUtil.refreshAndStoreAccountDetailsForCurrentUser(this.mContext);
                    AccountManagerSingleton.get().updateDevices();
                    return true;
                }
                this.mHasAttemptedAutoAuthorization = true;
            }
        } catch (Exception e) {
            Log.error(TAG, "Exception trying to autoauth", e);
        }
        Log.info(TAG, "AutoAuthorize Fail");
        return false;
    }

    public boolean canAutoAuthorizeDevice() {
        return getAvailableAuthorizationState() == AuthorizationAvailability.AUTHORIZABLE || getAvailableAuthorizationState() == AuthorizationAvailability.TRANSFERRABLE;
    }

    public boolean cannotTransferDevice() {
        return getAvailableAuthorizationState() == AuthorizationAvailability.NONTRANSFERRABLE;
    }

    public boolean deviceTransferLimitReached() {
        return getRemainingDeviceAuthorizations() <= 0;
    }

    public void setDeviceNotAuthorized() {
        SharedPreferences.Editor editorEdit = getSharedPreference().edit();
        editorEdit.putString("currentState", CurrentAuthorizationState.DEAUTHORIZED.toString());
        editorEdit.apply();
    }

    public void removeAll() {
        SharedPreferences.Editor editorEdit = getSharedPreference().edit();
        editorEdit.clear();
        editorEdit.commit();
    }
}
