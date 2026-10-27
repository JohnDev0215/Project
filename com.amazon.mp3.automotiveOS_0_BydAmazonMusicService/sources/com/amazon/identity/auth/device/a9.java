package com.amazon.identity.auth.device;

import android.os.Bundle;
import android.util.Log;
import android.util.SparseIntArray;
import com.amazon.identity.auth.device.api.Callback;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.api.MAPError;
import com.amazon.identity.auth.device.api.TokenManagement;
import com.amazon.identity.auth.device.d7;
import com.amazon.identity.auth.device.token.OAuthTokenManager;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public final class a9 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f175a = "com.amazon.identity.auth.device.a9";
    public static final SparseIntArray b;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        b = sparseIntArray;
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
    public static void a(Callback callback, String str, boolean z) {
        if (callback == null) {
            Log.e(ga.a(f175a), "Cannot callback success because no callback was given");
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString("value_key", str);
        if (z) {
            Log.i(ga.a(f175a), "This valid token is from cache while service returns error.");
            bundle.putBoolean(TokenManagement.KEY_ACCESS_TOKEN_FROM_CACHE, true);
        }
        callback.onSuccess(bundle);
    }

    @Deprecated
    public static void b(Callback callback, MAPError mAPError, String str, int i, String str2) {
        if (callback == null) {
            return;
        }
        int i2 = b.get(i, Integer.MIN_VALUE);
        Integer numValueOf = i2 == Integer.MIN_VALUE ? null : Integer.valueOf(i2);
        Bundle bundle = new Bundle();
        if (numValueOf != null) {
            bundle = a(mAPError, str, numValueOf.intValue(), str2);
        }
        bundle.putInt("errorCode", i);
        bundle.putString("errorMessage", str2);
        callback.onError(bundle);
    }

    @Deprecated
    public static void a(Callback callback, MAPError mAPError, String str, int i, String str2) {
        if (callback == null) {
            Log.e(ga.a(f175a), "Cannot callback error because no callback was given");
        } else {
            callback.onError(a(mAPError, str, i, str2));
        }
    }

    @Deprecated
    public static void a(Callback callback, MAPError mAPError, String str, d7.a aVar) {
        if (callback == null) {
            Log.e(ga.a(f175a), "Cannot callback error because no callback was given");
        } else {
            callback.onError(a(mAPError, str, aVar));
        }
    }

    @Deprecated
    public static Bundle a(MAPError mAPError, String str, d7.a aVar) {
        Bundle bundleA = a(mAPError, str, aVar.c(), aVar.b());
        d7 d7VarA = aVar.a();
        if (d7VarA != null) {
            bundleA.putAll(d7VarA.c());
        }
        if (aVar instanceof OAuthTokenManager.OAuthTokenManagerException) {
            bundleA.putBoolean(MAPError.KEY_SHOULD_CLEAR_AUTH_COOKIES, ((OAuthTokenManager.OAuthTokenManagerException) aVar).g());
        }
        return bundleA;
    }

    @Deprecated
    public static Bundle a(MAPError mAPError, String str, int i, String str2) {
        Bundle bundle = new Bundle();
        bundle.putInt("com.amazon.dcp.sso.ErrorCode", i);
        bundle.putString("com.amazon.dcp.sso.ErrorMessage", str2);
        bundle.putInt(MAPError.KEY_ERROR_CODE, mAPError.getErrorCode());
        bundle.putString(MAPError.KEY_ERROR_MESSAGE, str);
        bundle.putString(MAPError.KEY_ERROR_TYPE, mAPError.getErrorType());
        return bundle;
    }
}
