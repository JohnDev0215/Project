package com.amplifyframework.auth;

import androidx.annotation.NonNull;
import com.amplifyframework.AmplifyException;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public class AuthException extends AmplifyException {
    private static final long serialVersionUID = 1;

    public enum GuestAccess {
        GUEST_ACCESS_ENABLED,
        GUEST_ACCESS_POSSIBLE,
        GUEST_ACCESS_DISABLED
    }

    public AuthException(@NonNull String str, @NonNull Throwable th, @NonNull String str2) {
        super(str, th, str2);
    }

    public AuthException(@NonNull String str, @NonNull String str2) {
        super(str, str2);
    }

    public static class SignedOutException extends AuthException {
        private static final String MESSAGE = "You are currently signed out.";
        private static final Map<GuestAccess, String> RECOVERY_SUGGESTIONS;
        private static final long serialVersionUID = 1;

        static {
            HashMap map = new HashMap();
            RECOVERY_SUGGESTIONS = map;
            map.put(GuestAccess.GUEST_ACCESS_DISABLED, "Please sign in and reattempt the operation.");
            map.put(GuestAccess.GUEST_ACCESS_POSSIBLE, "If you have guest access enabled, please check that your device is online and try again. Otherwise if guest access is not enabled, you'll need to sign in and try again.");
            map.put(GuestAccess.GUEST_ACCESS_ENABLED, "For guest access, please check that your device is online and try again. For normal user access, please sign in.");
        }

        public SignedOutException() {
            super(MESSAGE, RECOVERY_SUGGESTIONS.get(GuestAccess.GUEST_ACCESS_DISABLED));
        }

        public SignedOutException(GuestAccess guestAccess) {
            super(MESSAGE, RECOVERY_SUGGESTIONS.get(guestAccess));
        }

        public SignedOutException(Throwable th) {
            super(MESSAGE, th, RECOVERY_SUGGESTIONS.get(GuestAccess.GUEST_ACCESS_DISABLED));
        }
    }

    public static class SessionUnavailableOfflineException extends AuthException {
        private static final String MESSAGE = "Unable to fetch/refresh credentials because the server is unreachable.";
        private static final String RECOVERY_SUGGESTION = "Check online connectivity and retry operation.";
        private static final long serialVersionUID = 1;

        public SessionUnavailableOfflineException() {
            super(MESSAGE, RECOVERY_SUGGESTION);
        }

        public SessionUnavailableOfflineException(Throwable th) {
            super(MESSAGE, th, RECOVERY_SUGGESTION);
        }
    }

    public static class SessionUnavailableServiceException extends AuthException {
        private static final String MESSAGE = "Unable to fetch/refresh credentials because of a service error.";
        private static final String RECOVERY_SUGGESTION = "Retry with exponential backoff.";
        private static final long serialVersionUID = 1;

        public SessionUnavailableServiceException() {
            super(MESSAGE, RECOVERY_SUGGESTION);
        }

        public SessionUnavailableServiceException(Throwable th) {
            super(MESSAGE, th, RECOVERY_SUGGESTION);
        }
    }

    public static class InvalidAccountTypeException extends AuthException {
        private static final String MESSAGE = "The account type you have configured doesn't support this operation.";
        private static final String RECOVERY_SUGGESTION = "Update your Auth configuration to an account type which supports this operation.";
        private static final long serialVersionUID = 1;

        public InvalidAccountTypeException() {
            super(MESSAGE, RECOVERY_SUGGESTION);
        }

        public InvalidAccountTypeException(Throwable th) {
            super(MESSAGE, th, RECOVERY_SUGGESTION);
        }
    }

    public static class SessionExpiredException extends AuthException {
        private static final String MESSAGE = "Your session has expired.";
        private static final String RECOVERY_SUGGESTION = "Please sign in and reattempt the operation.";
        private static final long serialVersionUID = 1;

        public SessionExpiredException() {
            super(MESSAGE, RECOVERY_SUGGESTION);
        }

        public SessionExpiredException(Throwable th) {
            super(MESSAGE, th, RECOVERY_SUGGESTION);
        }
    }

    public static class UnknownException extends AuthException {
        private static final long serialVersionUID = 1;

        public UnknownException(Throwable th) {
            super("An unclassified error prevented this operation.", th, "See the attached exception for more details");
        }
    }

    public static class UserNotFoundException extends AuthException {
        private static final String MESSAGE = "User not found in the system.";
        private static final String RECOVERY_SUGGESTION = "Please enter correct username.";
        private static final long serialVersionUID = 1;

        public UserNotFoundException(Throwable th) {
            super(MESSAGE, th, RECOVERY_SUGGESTION);
        }
    }

    public static class UserNotConfirmedException extends AuthException {
        private static final String MESSAGE = "User not confirmed in the system.";
        private static final String RECOVERY_SUGGESTION = "Please confirm user first and then retry operation";
        private static final long serialVersionUID = 1;

        public UserNotConfirmedException(Throwable th) {
            super(MESSAGE, th, RECOVERY_SUGGESTION);
        }
    }

    public static class UsernameExistsException extends AuthException {
        private static final String MESSAGE = "Username already exists in the system.";
        private static final String RECOVERY_SUGGESTION = "Retry operation and enter another username.";
        private static final long serialVersionUID = 1;

        public UsernameExistsException(Throwable th) {
            super(MESSAGE, th, RECOVERY_SUGGESTION);
        }
    }

    public static class AliasExistsException extends AuthException {
        private static final String MESSAGE = "Alias (an account with this email or phone) already exists in the system.";
        private static final String RECOVERY_SUGGESTION = "Retry operation and use another alias.";
        private static final long serialVersionUID = 1;

        public AliasExistsException(Throwable th) {
            super(MESSAGE, th, RECOVERY_SUGGESTION);
        }
    }

    public static class CodeDeliveryFailureException extends AuthException {
        private static final String MESSAGE = "Error in delivering the confirmation code.";
        private static final String RECOVERY_SUGGESTION = "Retry operation and send another confirmation code.";
        private static final long serialVersionUID = 1;

        public CodeDeliveryFailureException(Throwable th) {
            super(MESSAGE, th, RECOVERY_SUGGESTION);
        }
    }

    public static class CodeMismatchException extends AuthException {
        private static final String MESSAGE = "Confirmation code entered is not correct.";
        private static final String RECOVERY_SUGGESTION = "Enter correct confirmation code.";
        private static final long serialVersionUID = 1;

        public CodeMismatchException(Throwable th) {
            super(MESSAGE, th, RECOVERY_SUGGESTION);
        }
    }

    public static class CodeExpiredException extends AuthException {
        private static final String MESSAGE = "Confirmation code has expired.";
        private static final String RECOVERY_SUGGESTION = "Resend a new confirmation code and then retry operation with it.";
        private static final long serialVersionUID = 1;

        public CodeExpiredException(Throwable th) {
            super(MESSAGE, th, RECOVERY_SUGGESTION);
        }
    }

    public static class InvalidParameterException extends AuthException {
        private static final String MESSAGE = "One or more parameters are incorrect.";
        private static final String RECOVERY_SUGGESTION = "Enter correct parameters.";
        private static final long serialVersionUID = 1;

        public InvalidParameterException(Throwable th) {
            super(MESSAGE, th, RECOVERY_SUGGESTION);
        }
    }

    public static class InvalidPasswordException extends AuthException {
        private static final String MESSAGE = "The password given is invalid.";
        private static final String RECOVERY_SUGGESTION = "Enter correct password.";
        private static final long serialVersionUID = 1;

        public InvalidPasswordException(Throwable th) {
            super(MESSAGE, th, RECOVERY_SUGGESTION);
        }
    }

    public static class InvalidUserPoolConfigurationException extends AuthException {
        private static final String MESSAGE = "The user pool configuration is missing or invalid.";
        private static final String RECOVERY_SUGGESTION = "Please check your user pool configuration.";
        private static final long serialVersionUID = 1;

        public InvalidUserPoolConfigurationException(Throwable th) {
            super(MESSAGE, th, RECOVERY_SUGGESTION);
        }
    }

    public static class LimitExceededException extends AuthException {
        private static final String MESSAGE = "Number of allowed operation has exceeded.";
        private static final String RECOVERY_SUGGESTION = "Please wait a while before re-attempting or increase the service limit.";
        private static final long serialVersionUID = 1;

        public LimitExceededException(Throwable th) {
            super(MESSAGE, th, RECOVERY_SUGGESTION);
        }
    }

    public static class MFAMethodNotFoundException extends AuthException {
        private static final String MESSAGE = "Could not find multi-factor authentication (MFA) method.";
        private static final String RECOVERY_SUGGESTION = "Configure multi-factor authentication using Amplify CLI or AWS Cognito console.";
        private static final long serialVersionUID = 1;

        public MFAMethodNotFoundException(Throwable th) {
            super(MESSAGE, th, RECOVERY_SUGGESTION);
        }
    }

    public static class NotAuthorizedException extends AuthException {
        private static final String MESSAGE = "Failed since user is not authorized.";
        private static final String RECOVERY_SUGGESTION = "Check whether the given values are correct and the user is authorized to perform the operation.";
        private static final long serialVersionUID = 1;

        public NotAuthorizedException(Throwable th) {
            super(MESSAGE, th, RECOVERY_SUGGESTION);
        }
    }

    public static class PasswordResetRequiredException extends AuthException {
        private static final String MESSAGE = "Required to reset the password of the user.";
        private static final String RECOVERY_SUGGESTION = "Reset the password of the user.";
        private static final long serialVersionUID = 1;

        public PasswordResetRequiredException(Throwable th) {
            super(MESSAGE, th, RECOVERY_SUGGESTION);
        }
    }

    public static class ResourceNotFoundException extends AuthException {
        private static final String MESSAGE = "Could not find the requested online resource.";
        private static final String RECOVERY_SUGGESTION = "Retry with exponential back-off or check your config file to be sure the endpoint is valid.";
        private static final long serialVersionUID = 1;

        public ResourceNotFoundException(Throwable th) {
            super(MESSAGE, th, RECOVERY_SUGGESTION);
        }
    }

    public static class SoftwareTokenMFANotFoundException extends AuthException {
        private static final String MESSAGE = "Could not find software token MFA.";
        private static final String RECOVERY_SUGGESTION = "Enable the software token MFA for the user.";
        private static final long serialVersionUID = 1;

        public SoftwareTokenMFANotFoundException(Throwable th) {
            super(MESSAGE, th, RECOVERY_SUGGESTION);
        }
    }

    public static class FailedAttemptsLimitExceededException extends AuthException {
        private static final String MESSAGE = "User has made too many failed attempts for a given action.";
        private static final String RECOVERY_SUGGESTION = "Please check out the service configuration to see the condition of locking.";
        private static final long serialVersionUID = 1;

        public FailedAttemptsLimitExceededException(Throwable th) {
            super(MESSAGE, th, RECOVERY_SUGGESTION);
        }
    }

    public static class TooManyRequestsException extends AuthException {
        private static final String MESSAGE = "Failed since the user made too many requests.";
        private static final String RECOVERY_SUGGESTION = "Make sure the requests send are controlled and the errors are properly handled.";
        private static final long serialVersionUID = 1;

        public TooManyRequestsException(Throwable th) {
            super(MESSAGE, th, RECOVERY_SUGGESTION);
        }
    }

    public static class UserCancelledException extends AuthException {
        private static final long serialVersionUID = 1;

        public UserCancelledException(String str, Throwable th, String str2) {
            super(str, th, str2);
        }
    }
}
