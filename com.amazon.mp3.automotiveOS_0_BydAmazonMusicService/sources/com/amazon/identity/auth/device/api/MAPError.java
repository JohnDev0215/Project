package com.amazon.identity.auth.device.api;

import com.amazon.fireos.sdk.annotations.FireOsSdk;
import com.amazon.identity.mobi.common.javascript.JavaScriptBridgeCommon;
import com.amazonaws.mobileconnectors.remoteconfiguration.Attributes;
import com.google.android.gms.cast.MediaError;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class MAPError {
    public static final String ERROR_CODE_PREFIX = "com.amazon.map.error.";

    @FireOsSdk
    public static final String KEY_ERROR_CODE = "com.amazon.map.error.errorCode";

    @FireOsSdk
    public static final String KEY_ERROR_MESSAGE = "com.amazon.map.error.errorMessage";

    @FireOsSdk
    public static final String KEY_ERROR_TYPE = "com.amazon.map.error.errorType";

    @FireOsSdk
    public static final String KEY_SHOULD_CLEAR_AUTH_COOKIES = "com.amazon.map.error.shouldClearAuthCookies";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f221a;
    public String b;
    public String c;

    /* JADX INFO: compiled from: DCP */
    public static final class AccountError extends MAPError {

        @FireOsSdk
        public static final AccountError CUSTOMER_NOT_FOUND = new AccountError(AccountErrorEnum.CUSTOMER_NOT_FOUND);

        @FireOsSdk
        public static final AccountError DELEGATEE_ACCOUNT_ALREADY_DEREGISTERED = new AccountError(AccountErrorEnum.DELEGATEE_ACCOUNT_ALREADY_DEREGISTERED);

        @FireOsSdk
        public static final AccountError ACCOUNT_ALREADY_REGISTERED = new AccountError(AccountErrorEnum.ACCOUNT_ALREADY_REGISTERED);

        @FireOsSdk
        public static final AccountError REGISTER_FAILED = new AccountError(AccountErrorEnum.REGISTER_FAILED);

        @FireOsSdk
        public static final AccountError ACCOUNT_ALREADY_DEREGISTERED = new AccountError(AccountErrorEnum.ACCOUNT_ALREADY_DEREGISTERED);

        @FireOsSdk
        public static final AccountError DEREGISTER_FAILED = new AccountError(AccountErrorEnum.DEREGISTER_FAILED);

        @FireOsSdk
        public static final AccountError AUTHENTICATION_FAILED = new AccountError(AccountErrorEnum.AUTHENTICATION_FAILED);

        @FireOsSdk
        public static final AccountError DEVICE_ALREADY_DEREGISTERED = new AccountError(AccountErrorEnum.DEVICE_ALREADY_DEREGISTERED);

        @FireOsSdk
        public static final AccountError DUPLICATE_DEVICE_NAME = new AccountError(AccountErrorEnum.DUPLICATE_DEVICE_NAME);

        @FireOsSdk
        public static final AccountError AUTHENTICATION_CHALLENGED = new AccountError(AccountErrorEnum.AUTHENTICATION_CHALLENGED);

        @FireOsSdk
        public static final AccountError MISSING_DEVICE_SECRET = new AccountError(AccountErrorEnum.MISSING_DEVICE_SECRET);

        @FireOsSdk
        public static final AccountError MISSING_PACKAGE = new AccountError(AccountErrorEnum.MISSING_PACKAGE);

        @FireOsSdk
        public static final AccountError DEVICE_TYPE_UPGRADE_FAILED = new AccountError(AccountErrorEnum.DEVICE_TYPE_UPGRADE_FAILED);

        @FireOsSdk
        public static final AccountError DEVICE_ALREADY_REGISTERED = new AccountError(AccountErrorEnum.DEVICE_ALREADY_REGISTERED);

        @FireOsSdk
        public static final AccountError MISSING_CHALLENGE_EXCEPTION = new AccountError(AccountErrorEnum.MISSING_CHALLENGE_EXCEPTION);

        @FireOsSdk
        public static final AccountError UNSUPPORTED_PROTOCOL = new AccountError(AccountErrorEnum.UNSUPPORTED_PROTOCOL);

        @FireOsSdk
        public static final AccountError INVALID_HTTP_METHOD = new AccountError(AccountErrorEnum.INVALID_HTTP_METHOD);

        @FireOsSdk
        public static final AccountError SETTING_CREDENTIALS_FAILED = new AccountError(AccountErrorEnum.SETTING_CREDENTIALS_FAILED);

        @FireOsSdk
        public static final AccountError CORRUPTED_ACCOUNT = new AccountError(AccountErrorEnum.CORRUPTED_ACCOUNT);

        @FireOsSdk
        public static final AccountError REPLACE_ACCOUNTS_FAILED = new AccountError(AccountErrorEnum.REPLACE_ACCOUNTS_FAILED);

        @FireOsSdk
        public static final AccountError REQUIRES_3P_AUTHENTICATION = new AccountError(AccountErrorEnum.REQUIRES_3P_AUTHENTICATION);

        @FireOsSdk
        public static final AccountError ACCOUNT_ENCRYPTION_KEY_NOT_FOUND = new AccountError(AccountErrorEnum.ACCOUNT_ENCRYPTION_KEY_NOT_FOUND);

        /* JADX INFO: compiled from: DCP */
        public enum AccountErrorEnum implements MAPErrorMethods {
            CUSTOMER_NOT_FOUND(100, "CustomerNotFound", "Customer account does not exist"),
            REQUIRES_3P_AUTHENTICATION(101, "Requires3PAuthentication", "Authentication via a 3P identity provider is required"),
            DELEGATEE_ACCOUNT_ALREADY_DEREGISTERED(102, "DelegateeAccountAlreadyDeregistered", "The delegatee account is already deregistered on this device"),
            ACCOUNT_ALREADY_REGISTERED(103, "AccountAlreadyRegistered", "Account has already been registered on this device"),
            REGISTER_FAILED(104, "RegisterFailed", "An error occurred during registration"),
            ACCOUNT_ALREADY_DEREGISTERED(105, "AccountAlreadyDeregistered", "Given account has already been deregistered"),
            DEREGISTER_FAILED(106, "DeregisterFailed", "An error occurred during deregistration"),
            REPLACE_ACCOUNTS_FAILED(107, "ReplaceAccounts", "Failed to replace accounts on device"),
            AUTHENTICATION_FAILED(108, "AuthenticationFailed", "An error occurred during authentication"),
            DEVICE_ALREADY_DEREGISTERED(109, "DeviceAlreadyDeregistered", "Device already deregistered"),
            DUPLICATE_DEVICE_NAME(110, "DuplicateDeviceName", "Cannot duplicate the device name"),
            AUTHENTICATION_CHALLENGED(111, "AuthenticationChallenged", "Authentication was challenged"),
            MISSING_DEVICE_SECRET(112, "MissingDeviceSecret", "Missing the device secret"),
            CORRUPTED_ACCOUNT(113, "CorruptedAccount", "This account has been corrupted"),
            MISSING_PACKAGE(114, "MissingPackage", "Could not find a package to register the device type"),
            DEVICE_TYPE_UPGRADE_FAILED(115, "DeviceTypeUpgradeFailed", "Cannot upgrade a legacy child device type to a different device type. Deregistering the device to clean up the bad state."),
            DEVICE_ALREADY_REGISTERED(116, "DeviceAlreadyRegistered", "Device already registered"),
            MISSING_CHALLENGE_EXCEPTION(117, "MissingChallengeException", "Challenge exception is missing"),
            UNSUPPORTED_PROTOCOL(118, "UnsupportedProtocol", "Attempting to use an unsupported protocol"),
            INVALID_HTTP_METHOD(119, "InvalidHTTPMethod", "Attempting to use an invalid HTTP method"),
            SETTING_CREDENTIALS_FAILED(120, "SettingCredentialsFailed", "An error occurred while setting the credentials"),
            ACCOUNT_ENCRYPTION_KEY_NOT_FOUND(121, "AccountEncryptionKeyNotFound", "Null/Invalid encryption key or key identifier received.");

            private int mErrorCode;
            private String mErrorMessage;
            private String mErrorType;

            AccountErrorEnum(int i, String str, String str2) {
                this.mErrorCode = i;
                this.mErrorType = str;
                this.mErrorMessage = str2;
            }

            @Override // com.amazon.identity.auth.device.api.MAPError.MAPErrorMethods
            public int getErrorCode() {
                return this.mErrorCode;
            }

            @Override // com.amazon.identity.auth.device.api.MAPError.MAPErrorMethods
            public String getErrorMessage() {
                return this.mErrorMessage;
            }

            @Override // com.amazon.identity.auth.device.api.MAPError.MAPErrorMethods
            public String getErrorType() {
                return this.mErrorType;
            }
        }

        public AccountError(AccountErrorEnum accountErrorEnum) {
            super(accountErrorEnum.getErrorCode(), accountErrorEnum.getErrorMessage(), accountErrorEnum.getErrorType());
        }
    }

    /* JADX INFO: compiled from: DCP */
    public static final class ActorError extends MAPError {

        @FireOsSdk
        public static final ActorError ACTOR_NOT_ASSOCIATED = new ActorError(ActorErrorEnum.ACTOR_NOT_ASSOCIATED);

        @FireOsSdk
        public static final ActorError ACTOR_SIGNUP_FAILED = new ActorError(ActorErrorEnum.ACTOR_SIGNUP_FAILED);

        @FireOsSdk
        public static final ActorError ACTOR_ENROLLMENT_FAILED = new ActorError(ActorErrorEnum.ACTOR_ENROLLMENT_FAILED);

        /* JADX INFO: compiled from: DCP */
        public enum ActorErrorEnum implements MAPErrorMethods {
            ACTOR_NOT_ASSOCIATED(500, "ActorNotAssociated", "The actor is no longer associated with the account"),
            ACTOR_SIGNUP_FAILED(501, "ActorSignupFailed", "Failed to sign up a actor for the account."),
            ACTOR_ENROLLMENT_FAILED(502, "ActorEnrollmentFailed", "Failed to enroll the actor for your program.");

            private int mErrorCode;
            private String mErrorMessage;
            private String mErrorType;

            ActorErrorEnum(int i, String str, String str2) {
                this.mErrorCode = i;
                this.mErrorType = str;
                this.mErrorMessage = str2;
            }

            @Override // com.amazon.identity.auth.device.api.MAPError.MAPErrorMethods
            public int getErrorCode() {
                return this.mErrorCode;
            }

            @Override // com.amazon.identity.auth.device.api.MAPError.MAPErrorMethods
            public String getErrorMessage() {
                return this.mErrorMessage;
            }

            @Override // com.amazon.identity.auth.device.api.MAPError.MAPErrorMethods
            public String getErrorType() {
                return this.mErrorType;
            }
        }

        public ActorError(ActorErrorEnum actorErrorEnum) {
            super(actorErrorEnum.getErrorCode(), actorErrorEnum.getErrorMessage(), actorErrorEnum.getErrorType());
        }
    }

    /* JADX INFO: compiled from: DCP */
    public static final class AttributeError extends MAPError {

        @FireOsSdk
        public static final AttributeError SET_ATTRIBUTE_FAILED = new AttributeError(AttributeErrorEnum.SET_ATTRIBUTE_FAILED);

        @FireOsSdk
        public static final AttributeError GET_ATTRIBUTE_FAILED = new AttributeError(AttributeErrorEnum.GET_ATTRIBUTE_FAILED);

        @FireOsSdk
        public static final AttributeError KEY_NOT_FOUND = new AttributeError(AttributeErrorEnum.KEY_NOT_FOUND);

        @FireOsSdk
        public static final AttributeError CANNOT_DETERMINE_OVERRIDE_DEVICE_TYPE = new AttributeError(AttributeErrorEnum.CANNOT_DETERMINE_OVERRIDE_DEVICE_TYPE);

        @FireOsSdk
        public static final AttributeError NO_OVERRIDDEN_CHILD_DEVICE_TYPE = new AttributeError(AttributeErrorEnum.NO_OVERRIDDEN_CHILD_DEVICE_TYPE);

        @FireOsSdk
        public static final AttributeError PACKAGE_RUNNING_ON_FIRST_PARTY_DEVICE = new AttributeError(AttributeErrorEnum.PACKAGE_RUNNING_ON_FIRST_PARTY_DEVICE);

        @FireOsSdk
        public static final AttributeError NOT_A_CHILD_DEVICE_TYPE = new AttributeError(AttributeErrorEnum.NOT_A_CHILD_DEVICE_TYPE);

        @FireOsSdk
        public static final AttributeError UNRECOGNIZED_PACKAGE_NAME = new AttributeError(AttributeErrorEnum.UNRECOGNIZED_PACKAGE_NAME);

        /* JADX INFO: compiled from: DCP */
        public enum AttributeErrorEnum implements MAPErrorMethods {
            SET_ATTRIBUTE_FAILED(300, "SetAttributeFailed", "An error occurred while setting the attribute"),
            GET_ATTRIBUTE_FAILED(MediaError.DetailedErrorCode.SEGMENT_NETWORK, "GetAttributeFailed", "An error occurred while getting the attribute"),
            KEY_NOT_FOUND(302, "KeyNotFound", "Could not find key"),
            CANNOT_DETERMINE_OVERRIDE_DEVICE_TYPE(303, "CannotDetermineOverrideDeviceType", "Cannot determine the override device type"),
            NO_OVERRIDDEN_CHILD_DEVICE_TYPE(304, "NoOverriddenChildDeviceType", "The package doesn't have an overridden child device type to rename child device."),
            PACKAGE_RUNNING_ON_FIRST_PARTY_DEVICE(305, "PackageRunningOnFirstPartyDevice", "The package is running on a first party device, so the child device type cannot be overridden"),
            NOT_A_CHILD_DEVICE_TYPE(306, "NotAChildDeviceType", "This is not a child device type"),
            UNRECOGNIZED_PACKAGE_NAME(307, "UnrecognizedPackageName", "Package cannot be found or trusted to rename child device");

            private int mErrorCode;
            private String mErrorMessage;
            private String mErrorType;

            AttributeErrorEnum(int i, String str, String str2) {
                this.mErrorCode = i;
                this.mErrorType = str;
                this.mErrorMessage = str2;
            }

            @Override // com.amazon.identity.auth.device.api.MAPError.MAPErrorMethods
            public int getErrorCode() {
                return this.mErrorCode;
            }

            @Override // com.amazon.identity.auth.device.api.MAPError.MAPErrorMethods
            public String getErrorMessage() {
                return this.mErrorMessage;
            }

            @Override // com.amazon.identity.auth.device.api.MAPError.MAPErrorMethods
            public String getErrorType() {
                return this.mErrorType;
            }
        }

        public AttributeError(AttributeErrorEnum attributeErrorEnum) {
            super(attributeErrorEnum.getErrorCode(), attributeErrorEnum.getErrorMessage(), attributeErrorEnum.getErrorType());
        }
    }

    /* JADX INFO: compiled from: DCP */
    public static final class CommonError extends MAPError {

        @FireOsSdk
        public static final CommonError NETWORK_ERROR = new CommonError(CommonErrorEnum.NETWORK_ERROR);

        @FireOsSdk
        public static final CommonError UI_NOT_FOUND = new CommonError(CommonErrorEnum.UI_NOT_FOUND);

        @FireOsSdk
        public static final CommonError INTERNAL_ERROR = new CommonError(CommonErrorEnum.INTERNAL_ERROR);

        @FireOsSdk
        public static final CommonError OPERATION_CANCELLED = new CommonError(CommonErrorEnum.OPERATION_CANCELLED);

        @FireOsSdk
        public static final CommonError BAD_REQUEST = new CommonError(CommonErrorEnum.BAD_REQUEST);

        @FireOsSdk
        public static final CommonError PARSE_ERROR = new CommonError(CommonErrorEnum.PARSE_ERROR);

        @FireOsSdk
        public static final CommonError INVALID_RESPONSE = new CommonError(CommonErrorEnum.INVALID_RESPONSE);

        @FireOsSdk
        public static final CommonError BACKWARDS_INCOMPATIBILITY = new CommonError(CommonErrorEnum.BACKWARDS_INCOMPATIBILITY);

        @FireOsSdk
        public static final CommonError CORRUPTED_DATABASE = new CommonError(CommonErrorEnum.CORRUPTED_DATABASE);

        @FireOsSdk
        public static final CommonError UNSUPPORTED_OPERATION = new CommonError(CommonErrorEnum.UNSUPPORTED_OPERATION);

        @FireOsSdk
        public static final CommonError SERVER_ERROR = new CommonError(CommonErrorEnum.SERVER_ERROR);

        @FireOsSdk
        public static final CommonError SERVICE_UNAVAILABLE = new CommonError(CommonErrorEnum.SERVICE_UNAVAILABLE);

        @FireOsSdk
        public static final CommonError FEATURE_NOT_IMPLEMENTED = new CommonError(CommonErrorEnum.FEATURE_NOT_IMPLEMENTED);

        @FireOsSdk
        public static final CommonError UNKNOWN_IPC_ERROR = new CommonError(CommonErrorEnum.UNKNOWN_IPC_ERROR);

        /* JADX INFO: compiled from: DCP */
        public enum CommonErrorEnum implements MAPErrorMethods {
            FEATURE_NOT_IMPLEMENTED(400, "FeatureNotImplemented", "The feature has not been implemented yet"),
            NETWORK_ERROR(401, "NetworkError", "A network error occurred"),
            UI_NOT_FOUND(402, "UINotFound", "The UI could not be found"),
            SERVICE_UNAVAILABLE(403, "ServiceUnavailable", "The service is temporarily unavailable"),
            INTERNAL_ERROR(404, "InternalError", "An internal error occurred"),
            OPERATION_CANCELLED(405, "OperationCancelled", "The operation was cancelled before completing"),
            BAD_REQUEST(406, "BadRequest", "The request was invalid"),
            PARSE_ERROR(407, "ParseError", "An error occurred while parsing the server response"),
            INVALID_RESPONSE(408, "InvalidResponse", "An invalid response was received from the server"),
            BACKWARDS_INCOMPATIBILITY(409, "BackwardsIncompatibility", "An error occurred due to a backwards incompatibility issue"),
            CORRUPTED_DATABASE(410, "CorruptedDatabase", "The MAP database is corrupted"),
            UNSUPPORTED_OPERATION(MediaError.DetailedErrorCode.HLS_MANIFEST_MASTER, "UnsupportedOperation", "Attempted to perform an operation that is not supported"),
            SERVER_ERROR(MediaError.DetailedErrorCode.HLS_MANIFEST_PLAYLIST, "ServerError", "An error occurred on the server side"),
            UNKNOWN_IPC_ERROR(413, "UnknownIPCError", "Something went wrong communicating with imp apk. Usually due to IPC command not being recognized");

            private int mErrorCode;
            private String mErrorMessage;
            private String mErrorType;

            CommonErrorEnum(int i, String str, String str2) {
                this.mErrorCode = i;
                this.mErrorType = str;
                this.mErrorMessage = str2;
            }

            @Override // com.amazon.identity.auth.device.api.MAPError.MAPErrorMethods
            public int getErrorCode() {
                return this.mErrorCode;
            }

            @Override // com.amazon.identity.auth.device.api.MAPError.MAPErrorMethods
            public String getErrorMessage() {
                return this.mErrorMessage;
            }

            @Override // com.amazon.identity.auth.device.api.MAPError.MAPErrorMethods
            public String getErrorType() {
                return this.mErrorType;
            }
        }

        public CommonError(CommonErrorEnum commonErrorEnum) {
            super(commonErrorEnum.getErrorCode(), commonErrorEnum.getErrorMessage(), commonErrorEnum.getErrorType());
        }
    }

    /* JADX INFO: compiled from: DCP */
    public static final class FidoAuthenticatorError extends MAPError {
        public static final Map<Integer, MAPError> AUTHENTICATOR_ERRORS_TO_MAP_ERRORS;

        @FireOsSdk
        public static final FidoAuthenticatorError CANNOT_BE_AUTHENTICATED;

        @FireOsSdk
        public static final FidoAuthenticatorError CANNOT_VALIDATE_REQUEST;

        @FireOsSdk
        public static final FidoAuthenticatorError CREDENTIAL_CREATION_FAILED;

        @FireOsSdk
        public static final FidoAuthenticatorError LOCK_SCREEN_NOT_SECURE;

        @FireOsSdk
        public static final FidoAuthenticatorError REQUEST_TIMED_OUT;

        /* JADX INFO: compiled from: DCP */
        public enum FidoAuthenticatorErrorEnum implements MAPErrorMethods {
            CANNOT_VALIDATE_REQUEST(MediaError.DetailedErrorCode.TEXT_UNKNOWN, "CannotValidateRequest", "The request cannot be validated. Check that the relying party domain is correct and that it has the associated assetlink."),
            CANNOT_BE_AUTHENTICATED(601, "CannotBeAuthenticated", "The user cancelled the authenticator screen, or a credential id was used that does not exist on the device."),
            REQUEST_TIMED_OUT(602, "RequestTimedOut", "The user did not complete the PIN/Biometric prompt before the timeout time elapsed."),
            LOCK_SCREE_NOT_SECURE(603, "LockScreenNotSecure", "The user does not have a PIN or Biometric set for their lock screen."),
            CREDENTIAL_CREATION_FAILED(604, "CredentialCreationFailed", "An error occurred while creating the authenticator credential. This could be due to calling the API multiple times before waiting for the user to complete the first prompt to create a credential.");

            private int mErrorCode;
            private String mErrorMessage;
            private String mErrorType;

            FidoAuthenticatorErrorEnum(int i, String str, String str2) {
                this.mErrorCode = i;
                this.mErrorType = str;
                this.mErrorMessage = str2;
            }

            @Override // com.amazon.identity.auth.device.api.MAPError.MAPErrorMethods
            public int getErrorCode() {
                return this.mErrorCode;
            }

            @Override // com.amazon.identity.auth.device.api.MAPError.MAPErrorMethods
            public String getErrorMessage() {
                return this.mErrorMessage;
            }

            @Override // com.amazon.identity.auth.device.api.MAPError.MAPErrorMethods
            public String getErrorType() {
                return this.mErrorType;
            }
        }

        static {
            FidoAuthenticatorError fidoAuthenticatorError = new FidoAuthenticatorError(FidoAuthenticatorErrorEnum.CANNOT_VALIDATE_REQUEST);
            CANNOT_VALIDATE_REQUEST = fidoAuthenticatorError;
            FidoAuthenticatorError fidoAuthenticatorError2 = new FidoAuthenticatorError(FidoAuthenticatorErrorEnum.CANNOT_BE_AUTHENTICATED);
            CANNOT_BE_AUTHENTICATED = fidoAuthenticatorError2;
            FidoAuthenticatorError fidoAuthenticatorError3 = new FidoAuthenticatorError(FidoAuthenticatorErrorEnum.REQUEST_TIMED_OUT);
            REQUEST_TIMED_OUT = fidoAuthenticatorError3;
            FidoAuthenticatorError fidoAuthenticatorError4 = new FidoAuthenticatorError(FidoAuthenticatorErrorEnum.LOCK_SCREE_NOT_SECURE);
            LOCK_SCREEN_NOT_SECURE = fidoAuthenticatorError4;
            FidoAuthenticatorError fidoAuthenticatorError5 = new FidoAuthenticatorError(FidoAuthenticatorErrorEnum.CREDENTIAL_CREATION_FAILED);
            CREDENTIAL_CREATION_FAILED = fidoAuthenticatorError5;
            HashMap map = new HashMap();
            map.put(18, fidoAuthenticatorError);
            map.put(23, fidoAuthenticatorError3);
            map.put(28, fidoAuthenticatorError5);
            map.put(29, fidoAuthenticatorError4);
            map.put(35, fidoAuthenticatorError2);
            AUTHENTICATOR_ERRORS_TO_MAP_ERRORS = Collections.unmodifiableMap(map);
        }

        public FidoAuthenticatorError(FidoAuthenticatorErrorEnum fidoAuthenticatorErrorEnum) {
            super(fidoAuthenticatorErrorEnum.getErrorCode(), fidoAuthenticatorErrorEnum.getErrorMessage(), fidoAuthenticatorErrorEnum.getErrorType());
        }
    }

    /* JADX INFO: compiled from: DCP */
    public interface MAPErrorMethods {
        @FireOsSdk
        int getErrorCode();

        @FireOsSdk
        String getErrorMessage();

        @FireOsSdk
        String getErrorType();
    }

    /* JADX INFO: compiled from: DCP */
    public static final class TokenError extends MAPError {

        @FireOsSdk
        public static final TokenError MISSING_TOKEN = new TokenError(TokenErrorEnum.MISSING_TOKEN);

        @FireOsSdk
        public static final TokenError COOKIE_REFRESH_FAILED = new TokenError(TokenErrorEnum.COOKIE_REFRESH_FAILED);

        @FireOsSdk
        public static final TokenError FETCH_TOKEN_FAILED = new TokenError(TokenErrorEnum.FETCH_TOKEN_FAILED);

        /* JADX INFO: compiled from: DCP */
        public enum TokenErrorEnum implements MAPErrorMethods {
            MISSING_TOKEN(200, "MissingToken", "The expected token could not be found"),
            COOKIE_REFRESH_FAILED(201, "CookieRefreshFailed", "Cookie refresh failed"),
            FETCH_TOKEN_FAILED(202, "FetchTokenFailed", "An error occurred while fetching the token");

            private int mErrorCode;
            private String mErrorMessage;
            private String mErrorType;

            TokenErrorEnum(int i, String str, String str2) {
                this.mErrorCode = i;
                this.mErrorType = str;
                this.mErrorMessage = str2;
            }

            @Override // com.amazon.identity.auth.device.api.MAPError.MAPErrorMethods
            public int getErrorCode() {
                return this.mErrorCode;
            }

            @Override // com.amazon.identity.auth.device.api.MAPError.MAPErrorMethods
            public String getErrorMessage() {
                return this.mErrorMessage;
            }

            @Override // com.amazon.identity.auth.device.api.MAPError.MAPErrorMethods
            public String getErrorType() {
                return this.mErrorType;
            }
        }

        public TokenError(TokenErrorEnum tokenErrorEnum) {
            super(tokenErrorEnum.getErrorCode(), tokenErrorEnum.getErrorMessage(), tokenErrorEnum.getErrorType());
        }
    }

    public MAPError(int i, String str, String str2) {
        this.f221a = i;
        this.b = str;
        this.c = str2;
    }

    public static MAPError a(int i) {
        int i2 = 0;
        if (i >= 100 && i < 200) {
            AccountError.AccountErrorEnum[] accountErrorEnumArrValues = AccountError.AccountErrorEnum.values();
            while (i2 < 22) {
                AccountError.AccountErrorEnum accountErrorEnum = accountErrorEnumArrValues[i2];
                if (accountErrorEnum.getErrorCode() == i) {
                    return new AccountError(accountErrorEnum);
                }
                i2++;
            }
            return null;
        }
        if (i >= 200 && i < 300) {
            TokenError.TokenErrorEnum[] tokenErrorEnumArrValues = TokenError.TokenErrorEnum.values();
            while (i2 < 3) {
                TokenError.TokenErrorEnum tokenErrorEnum = tokenErrorEnumArrValues[i2];
                if (tokenErrorEnum.getErrorCode() == i) {
                    return new TokenError(tokenErrorEnum);
                }
                i2++;
            }
            return null;
        }
        if (i >= 300 && i < 400) {
            AttributeError.AttributeErrorEnum[] attributeErrorEnumArrValues = AttributeError.AttributeErrorEnum.values();
            while (i2 < 8) {
                AttributeError.AttributeErrorEnum attributeErrorEnum = attributeErrorEnumArrValues[i2];
                if (attributeErrorEnum.getErrorCode() == i) {
                    return new AttributeError(attributeErrorEnum);
                }
                i2++;
            }
            return null;
        }
        if (i >= 400 && i < 500) {
            CommonError.CommonErrorEnum[] commonErrorEnumArrValues = CommonError.CommonErrorEnum.values();
            while (i2 < 14) {
                CommonError.CommonErrorEnum commonErrorEnum = commonErrorEnumArrValues[i2];
                if (commonErrorEnum.getErrorCode() == i) {
                    return new CommonError(commonErrorEnum);
                }
                i2++;
            }
            return null;
        }
        if (i >= 500 && i < 600) {
            ActorError.ActorErrorEnum[] actorErrorEnumArrValues = ActorError.ActorErrorEnum.values();
            while (i2 < 3) {
                ActorError.ActorErrorEnum actorErrorEnum = actorErrorEnumArrValues[i2];
                if (actorErrorEnum.getErrorCode() == i) {
                    return new ActorError(actorErrorEnum);
                }
                i2++;
            }
            return null;
        }
        if (i < 600 || i >= 700) {
            return null;
        }
        FidoAuthenticatorError.FidoAuthenticatorErrorEnum[] fidoAuthenticatorErrorEnumArrValues = FidoAuthenticatorError.FidoAuthenticatorErrorEnum.values();
        while (i2 < 5) {
            FidoAuthenticatorError.FidoAuthenticatorErrorEnum fidoAuthenticatorErrorEnum = fidoAuthenticatorErrorEnumArrValues[i2];
            if (fidoAuthenticatorErrorEnum.getErrorCode() == i) {
                return new FidoAuthenticatorError(fidoAuthenticatorErrorEnum);
            }
            i2++;
        }
        return null;
    }

    @FireOsSdk
    public static MAPError getErrorFromValue(int i) {
        return a(i);
    }

    public static String getErrorTypeWithUnderscores(String str) {
        JSONObject jSONObject = new JSONObject();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (Character.isUpperCase(str.charAt(i)) && i != str.length() - 1 && i != 0) {
                sb.append(Attributes.PREDEFINED_ATTRIBUTE_PREFIX);
            }
            sb.append(str.charAt(i));
        }
        try {
            jSONObject.put("error", sb.toString());
            return jSONObject.has("error") ? jSONObject.toString() : JavaScriptBridgeCommon.GENERAL_ERROR;
        } catch (Exception unused) {
            return JavaScriptBridgeCommon.GENERAL_ERROR;
        }
    }

    @FireOsSdk
    public int getErrorCode() {
        return this.f221a;
    }

    @FireOsSdk
    public String getErrorMessage() {
        return this.b;
    }

    @FireOsSdk
    public String getErrorType() {
        return this.c;
    }

    @FireOsSdk
    public String toString() {
        return String.format("MAPError: %s , ErrorCode: %d , ErrorMessage: %s", this.c, Integer.valueOf(this.f221a), this.b);
    }

    @FireOsSdk
    public static MAPError getErrorFromValue(int i, MAPError mAPError) {
        MAPError mAPErrorA = a(i);
        return mAPErrorA != null ? mAPErrorA : mAPError;
    }
}
