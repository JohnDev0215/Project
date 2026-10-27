package com.amazon.identity.auth.device;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.SparseIntArray;
import com.amazon.identity.auth.device.api.Callback;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.api.MAPError;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public final class y {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final SparseIntArray f665a;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        f665a = sparseIntArray;
        MAPAccountManager.RegistrationError registrationError = MAPAccountManager.RegistrationError.BAD_REQUEST;
        sparseIntArray.append(7, registrationError.value());
        sparseIntArray.append(8, registrationError.value());
        MAPAccountManager.RegistrationError registrationError2 = MAPAccountManager.RegistrationError.REGISTER_FAILED;
        sparseIntArray.append(4, registrationError2.value());
        sparseIntArray.append(5, MAPAccountManager.RegistrationError.PARSE_ERROR.value());
        sparseIntArray.append(3, MAPAccountManager.RegistrationError.NETWORK_FAILURE.value());
        sparseIntArray.append(1, registrationError2.value());
        sparseIntArray.append(6, MAPAccountManager.RegistrationError.UNRECOGNIZED.value());
    }

    @Deprecated
    public static void a(Callback callback, MAPError mAPError, String str, int i, String str2, Bundle bundle) {
        if (callback == null) {
            return;
        }
        Bundle bundleB = b(mAPError, str, i, str2);
        if (bundle != null) {
            bundleB.putAll(bundle);
        }
        callback.onError(bundleB);
    }

    @Deprecated
    public static Bundle b(MAPError mAPError, String str, int i, String str2) {
        Bundle bundle = new Bundle();
        bundle.putInt("com.amazon.dcp.sso.ErrorCode", i);
        bundle.putString("com.amazon.dcp.sso.ErrorMessage", str2);
        bundle.putInt(MAPError.KEY_ERROR_CODE, mAPError.getErrorCode());
        bundle.putString(MAPError.KEY_ERROR_MESSAGE, str);
        bundle.putString(MAPError.KEY_ERROR_TYPE, mAPError.getErrorType());
        return bundle;
    }

    @Deprecated
    public static void a(Callback callback, MAPError mAPError, String str, int i, String str2) {
        if (callback == null) {
            return;
        }
        callback.onError(a(mAPError, str, i, str2));
    }

    @Deprecated
    public static void a(Callback callback, Bundle bundle) {
        if (callback == null) {
            return;
        }
        if (a(bundle)) {
            callback.onError(bundle);
        } else {
            callback.onSuccess(bundle);
        }
    }

    @Deprecated
    public static boolean a(Bundle bundle) {
        if (bundle == null) {
            return false;
        }
        return bundle.containsKey("com.amazon.dcp.sso.ErrorCode") || bundle.containsKey("errorCode") || bundle.containsKey(MAPError.KEY_ERROR_CODE);
    }

    @Deprecated
    public static Bundle a(MAPError mAPError, String str, int i, String str2) {
        int i2 = f665a.get(i, Integer.MIN_VALUE);
        Integer numValueOf = i2 == Integer.MIN_VALUE ? null : Integer.valueOf(i2);
        Bundle bundle = new Bundle();
        if (numValueOf != null) {
            bundle = b(mAPError, str, numValueOf.intValue(), str2);
        }
        bundle.putInt("errorCode", i);
        bundle.putString("errorMessage", str2);
        return bundle;
    }

    @Deprecated
    public static void a(Callback callback, String str) {
        if (callback == null) {
            return;
        }
        callback.onError(a(str));
    }

    @Deprecated
    public static Bundle a(String str) {
        Bundle bundleB = b(MAPError.AccountError.ACCOUNT_ALREADY_REGISTERED, "Account has already been registered on this device", MAPAccountManager.RegistrationError.ACCOUNT_ALREADY_EXISTS.value(), "Account has already been registered on this device");
        if (!TextUtils.isEmpty(str)) {
            bundleB.putString("com.amazon.dcp.sso.property.account.acctId", str);
        }
        return bundleB;
    }
}
