package com.amazon.identity.auth.device.framework;

import android.text.TextUtils;
import android.util.Log;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.api.MAPError;
import com.amazon.identity.auth.device.da;
import com.amazon.identity.auth.device.ga;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class AuthEndpointErrorParser {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a f319a = new a(AuthErrorType.GenericError, "Generic Error", "Generic Error", "No Error Index", "No Request Id");
    public static final String b = AuthEndpointErrorParser.class.getName();

    /* JADX WARN: Enum visitor error
    jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'MissingValue' uses external variables
    	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:451)
    	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByRegister(EnumVisitor.java:395)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:324)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:262)
    	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:151)
    	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
     */
    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX INFO: compiled from: DCP */
    public static final class AuthErrorType {
        private static final /* synthetic */ AuthErrorType[] $VALUES;
        public static final AuthErrorType ActorNotAssociated;
        public static final AuthErrorType AuthenticationChallenged;
        public static final AuthErrorType BuildRequestFailure;
        public static final AuthErrorType CredentialError;
        public static final AuthErrorType DeviceAlreadyRegistered;
        public static final AuthErrorType DuplicateDeviceName;
        public static final AuthErrorType Forbidden;
        public static final AuthErrorType GenericError;
        public static final AuthErrorType InvalidActorToken;
        public static final AuthErrorType InvalidDirectedId;
        public static final AuthErrorType InvalidRequest;
        public static final AuthErrorType InvalidToken;
        public static final AuthErrorType InvalidValue;
        public static final AuthErrorType MethodNotAllowed;
        public static final AuthErrorType MissingValue;
        public static final AuthErrorType NetworkFailure;
        public static final AuthErrorType NotImplemented;
        public static final AuthErrorType ParseError;
        public static final AuthErrorType ProtocolError;
        public static final AuthErrorType ServerError;
        public static final AuthErrorType ServiceUnavailable;
        private final String mCode;
        private final MAPError mError;
        private final String mErrorMessage;
        private final MAPAccountManager.RegistrationError mLegacyError;
        private final boolean mRetry;

        static {
            MAPAccountManager.RegistrationError registrationError = MAPAccountManager.RegistrationError.BAD_REQUEST;
            MAPError.CommonError commonError = MAPError.CommonError.BAD_REQUEST;
            AuthErrorType authErrorType = new AuthErrorType("MissingValue", 0, "MissingValue", false, registrationError, commonError, "Missing value");
            MissingValue = authErrorType;
            AuthErrorType authErrorType2 = new AuthErrorType("InvalidValue", 1, "InvalidValue", false, registrationError, commonError, "Invalid value");
            InvalidValue = authErrorType2;
            AuthErrorType authErrorType3 = new AuthErrorType("InvalidToken", 2, "InvalidToken", false, registrationError, commonError, "Invalid token");
            InvalidToken = authErrorType3;
            AuthErrorType authErrorType4 = new AuthErrorType("ProtocolError", 3, "ProtocolError", false, registrationError, MAPError.AccountError.UNSUPPORTED_PROTOCOL, "Protocol error");
            ProtocolError = authErrorType4;
            AuthErrorType authErrorType5 = new AuthErrorType("CredentialError", 4, "CredentialError", false, MAPAccountManager.RegistrationError.CUSTOMER_NOT_FOUND, MAPError.AccountError.CUSTOMER_NOT_FOUND, "Credential error");
            CredentialError = authErrorType5;
            AuthErrorType authErrorType6 = new AuthErrorType("Forbidden", 5, "Forbidden", false, registrationError, commonError, "Forbidden");
            Forbidden = authErrorType6;
            AuthErrorType authErrorType7 = new AuthErrorType("MethodNotAllowed", 6, "MethodNotAllowed", false, registrationError, MAPError.CommonError.UNSUPPORTED_OPERATION, "Method not allowed");
            MethodNotAllowed = authErrorType7;
            MAPAccountManager.RegistrationError registrationError2 = MAPAccountManager.RegistrationError.UNRECOGNIZED;
            AuthErrorType authErrorType8 = new AuthErrorType("ServerError", 7, "ServerError", true, registrationError2, MAPError.CommonError.SERVER_ERROR, "Server error");
            ServerError = authErrorType8;
            MAPError.CommonError commonError2 = MAPError.CommonError.SERVICE_UNAVAILABLE;
            AuthErrorType authErrorType9 = new AuthErrorType("ServiceUnavailable", 8, "ServiceUnavailable", true, registrationError2, commonError2, commonError2.getErrorMessage());
            ServiceUnavailable = authErrorType9;
            MAPError.CommonError commonError3 = MAPError.CommonError.FEATURE_NOT_IMPLEMENTED;
            AuthErrorType authErrorType10 = new AuthErrorType("NotImplemented", 9, "NotImplemented", false, registrationError, commonError3, commonError3.getErrorMessage());
            NotImplemented = authErrorType10;
            AuthErrorType authErrorType11 = new AuthErrorType("InvalidDirectedId", 10, "InvalidDirectedId", false, registrationError, commonError, "Invalid directedId");
            InvalidDirectedId = authErrorType11;
            MAPAccountManager.RegistrationError registrationError3 = MAPAccountManager.RegistrationError.DEVICE_ALREADY_REGISTERED;
            MAPError.AccountError accountError = MAPError.AccountError.DEVICE_ALREADY_REGISTERED;
            AuthErrorType authErrorType12 = new AuthErrorType("DeviceAlreadyRegistered", 11, "DeviceAlreadyRegistered", false, registrationError3, accountError, accountError.getErrorMessage());
            DeviceAlreadyRegistered = authErrorType12;
            MAPAccountManager.RegistrationError registrationError4 = MAPAccountManager.RegistrationError.DUPLICATE_DEVICE_NAME;
            MAPError.AccountError accountError2 = MAPError.AccountError.DUPLICATE_DEVICE_NAME;
            AuthErrorType authErrorType13 = new AuthErrorType("DuplicateDeviceName", 12, "DuplicateDeviceName", false, registrationError4, accountError2, accountError2.getErrorMessage());
            DuplicateDeviceName = authErrorType13;
            AuthErrorType authErrorType14 = new AuthErrorType("GenericError", 13);
            GenericError = authErrorType14;
            MAPAccountManager.RegistrationError registrationError5 = MAPAccountManager.RegistrationError.AUTHENTICATION_CHALLENGED;
            MAPError.AccountError accountError3 = MAPError.AccountError.AUTHENTICATION_CHALLENGED;
            AuthErrorType authErrorType15 = new AuthErrorType("AuthenticationChallenged", 14, "AuthenticationChallenged", false, registrationError5, accountError3, accountError3.getErrorMessage());
            AuthenticationChallenged = authErrorType15;
            MAPAccountManager.RegistrationError registrationError6 = MAPAccountManager.RegistrationError.PARSE_ERROR;
            MAPError.CommonError commonError4 = MAPError.CommonError.PARSE_ERROR;
            AuthErrorType authErrorType16 = new AuthErrorType("ParseError", 15, "ParseError", true, registrationError6, commonError4, commonError4.getErrorMessage());
            ParseError = authErrorType16;
            AuthErrorType authErrorType17 = new AuthErrorType("BuildRequestFailure", 16, "BuildRequestFailure", false, registrationError, commonError, "Build request failure");
            BuildRequestFailure = authErrorType17;
            MAPAccountManager.RegistrationError registrationError7 = MAPAccountManager.RegistrationError.NETWORK_FAILURE;
            MAPError.CommonError commonError5 = MAPError.CommonError.NETWORK_ERROR;
            AuthErrorType authErrorType18 = new AuthErrorType("NetworkFailure", 17, "NetworkFailure", true, registrationError7, commonError5, commonError5.getErrorMessage());
            NetworkFailure = authErrorType18;
            AuthErrorType authErrorType19 = new AuthErrorType("ActorNotAssociated", 18, "ActorNotAssociated", false, MAPAccountManager.RegistrationError.ACTOR_NOT_ASSOCIATED, commonError, "Actor is not associated with account");
            ActorNotAssociated = authErrorType19;
            AuthErrorType authErrorType20 = new AuthErrorType("InvalidActorToken", 19, "InvalidActorToken", true, MAPAccountManager.RegistrationError.INTERNAL_ERROR, MAPError.CommonError.INTERNAL_ERROR, "Actor token is invalid");
            InvalidActorToken = authErrorType20;
            AuthErrorType authErrorType21 = new AuthErrorType("InvalidRequest", 20, "InvalidRequest", false, registrationError, commonError, "The input is invalid");
            InvalidRequest = authErrorType21;
            $VALUES = new AuthErrorType[]{authErrorType, authErrorType2, authErrorType3, authErrorType4, authErrorType5, authErrorType6, authErrorType7, authErrorType8, authErrorType9, authErrorType10, authErrorType11, authErrorType12, authErrorType13, authErrorType14, authErrorType15, authErrorType16, authErrorType17, authErrorType18, authErrorType19, authErrorType20, authErrorType21};
        }

        /* JADX WARN: Illegal instructions before constructor call */
        private AuthErrorType(String str, int i) {
            MAPAccountManager.RegistrationError registrationError = MAPAccountManager.RegistrationError.UNRECOGNIZED;
            MAPError.CommonError commonError = MAPError.CommonError.INTERNAL_ERROR;
            this(str, i, "GenericError", false, registrationError, commonError, commonError.getErrorMessage());
        }

        public static AuthErrorType getAuthErrorTypeFromCode(String str) {
            AuthErrorType[] authErrorTypeArrValues = values();
            for (int i = 0; i < 21; i++) {
                AuthErrorType authErrorType = authErrorTypeArrValues[i];
                if (authErrorType.getCode().equals(str)) {
                    return authErrorType;
                }
            }
            return GenericError;
        }

        public static AuthErrorType valueOf(String str) {
            return (AuthErrorType) Enum.valueOf(AuthErrorType.class, str);
        }

        public static AuthErrorType[] values() {
            return (AuthErrorType[]) $VALUES.clone();
        }

        public String getCode() {
            return this.mCode;
        }

        public MAPError getError() {
            return this.mError;
        }

        public String getErrorMessage() {
            return this.mErrorMessage;
        }

        public MAPAccountManager.RegistrationError getRegistrationError() {
            return this.mLegacyError;
        }

        public boolean getRetry() {
            return this.mRetry;
        }

        private AuthErrorType(String str, int i, String str2, boolean z, MAPAccountManager.RegistrationError registrationError, MAPError mAPError, String str3) {
            this.mCode = str2;
            this.mRetry = z;
            this.mLegacyError = registrationError;
            this.mError = mAPError;
            this.mErrorMessage = str3;
        }
    }

    /* JADX INFO: compiled from: DCP */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final AuthErrorType f320a;
        public final String b;
        public final String c;
        public final String d;

        public a(AuthErrorType authErrorType, String str, String str2, String str3, String str4) {
            this.f320a = authErrorType;
            this.b = str;
            this.c = str2;
            this.d = str3;
        }
    }

    public static boolean a(Integer num) {
        return num == null || num.intValue() < 200 || num.intValue() >= 300;
    }

    public a b(JSONObject jSONObject) {
        try {
            return a(jSONObject);
        } catch (JSONException e) {
            String str = "Given JSON is not in Auth Error format. Error: " + e.getMessage();
            ga.a(b);
            return new a(AuthErrorType.ParseError, str, null, null, null);
        }
    }

    public final a a(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        JSONObject jSONObject2 = jSONObject.getJSONObject("response");
        String strA = da.a(jSONObject2, "request_id", (String) null);
        JSONObject jSONObject3 = jSONObject2.getJSONObject("error");
        AuthErrorType authErrorTypeFromCode = AuthErrorType.getAuthErrorTypeFromCode(jSONObject3.getString("code"));
        String strA2 = da.a(jSONObject3, "message", (String) null);
        String strA3 = da.a(jSONObject3, "detail", (String) null);
        String strA4 = da.a(jSONObject3, "index", (String) null);
        if (!TextUtils.isEmpty(strA4)) {
            String str = b;
            Log.e(ga.a(str), "Error Index was received: " + strA4);
            ga.a(str);
        }
        return new a(authErrorTypeFromCode, strA2, strA3, strA4, strA);
    }
}
