package com.amazon.identity.auth.device.metrics;

import android.content.Context;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.ce;
import com.amazon.identity.auth.device.e;
import com.amazon.identity.auth.device.yd;
import com.amazonaws.cognito.clientcontext.datacollection.DataRecordKey;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public final class SSOMetrics {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f439a = "com.amazon.identity.auth.device.metrics.SSOMetrics";
    public static Context b;
    public static ce c;

    /* JADX INFO: compiled from: DCP */
    public enum AccountManagerFailure {
        BAD_ARGUMENTS(7, "BadArguments"),
        BAD_REQUEST(8, "BadRequest"),
        CANCELED(4, "Cancelled"),
        INVALID_RESPONSE(5, "InvalidResponse"),
        NETWORK_ERROR(3, "NetworkError"),
        REMOTE_EXCEPTION(1, "RemoteException"),
        UNSUPPORTED_OPERATION(6, "UnsupportedOperation"),
        UNRECOGNIZED(9, "UnrecognizedExternalError");

        private final int mErrorCode;
        private final String mName;

        AccountManagerFailure(int i, String str) {
            this.mErrorCode = i;
            this.mName = str;
        }

        public static AccountManagerFailure getFromAccountManagerErrorCode(int i) {
            AccountManagerFailure[] accountManagerFailureArrValues = values();
            for (int i2 = 0; i2 < 8; i2++) {
                AccountManagerFailure accountManagerFailure = accountManagerFailureArrValues[i2];
                if (accountManagerFailure.getAccountManagerErrorCode() == i) {
                    return accountManagerFailure;
                }
            }
            return UNRECOGNIZED;
        }

        public int getAccountManagerErrorCode() {
            return this.mErrorCode;
        }

        public String getName() {
            return this.mName;
        }
    }

    /* JADX INFO: compiled from: DCP */
    public enum AccountProperties {
        DeviceName(DataRecordKey.MODEL),
        DeviceEmail("DeviceEmail"),
        General("General");

        private final String mName;

        AccountProperties(String str) {
            this.mName = str;
        }

        public String getName() {
            return this.mName;
        }
    }

    /* JADX INFO: compiled from: DCP */
    public enum CredentialActionFailureReason {
        RequestTimeOut("RequestTimeOut"),
        DidnotReturnAuthToken("DidnotReturnAuthToken"),
        UnrecognizedFailure("UnrecognizedFailure");

        private final String mName;

        CredentialActionFailureReason(String str) {
            this.mName = str;
        }

        public String getName() {
            return this.mName;
        }
    }

    /* JADX INFO: compiled from: DCP */
    public enum SetAccountPropertiesFailureReason {
        NoAmazonAccount("NoAmazonAccount"),
        InvalidRequest("InvalidRequest"),
        UnrecognizedFailure("UnrecognizedFailure");

        private final String mName;

        SetAccountPropertiesFailureReason(String str) {
            this.mName = str;
        }

        public String getName() {
            return this.mName;
        }
    }

    public static synchronized void a() {
        Context context = b;
        if (context != null && c == null) {
            c = yd.a(context);
        }
    }

    public static void a(MAPAccountManager.RegistrationError registrationError) {
        String name;
        a();
        String[] strArr = new String[1];
        if (registrationError != null) {
            name = registrationError.getName();
        } else {
            name = MAPAccountManager.RegistrationError.UNRECOGNIZED.getName();
        }
        strArr[0] = name;
        a("DeregistrationFailure", strArr);
    }

    public static void a(String str, int i) {
        a();
        a("WebViewLoadFailure", "SSLError:" + e.c(str), "SSLError:" + i);
    }

    public static void a(String str, String... strArr) {
        ce ceVar = c;
        if (ceVar == null) {
            return;
        }
        ceVar.a("GenericMetrics_SSO", str, strArr);
    }
}
