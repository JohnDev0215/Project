package com.amazon.mp3.net.cirrus;

import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.net.cirrus.BlueMoonExceptionsUtil;
import com.amazon.mp3.net.service.ServiceException;
import com.amazon.mp3.util.Log;
import java.util.HashMap;

/* JADX INFO: loaded from: classes3.dex */
public abstract class CirrusExceptions {
    private static final HashMap<String, Class<? extends CirrusException>> EXCEPTION_TYPE_MAP;

    public static class AccountLockedException extends InvalidAccountException {
    }

    public static class AccountNotFoundException extends InvalidAccountException {
    }

    public static class AuthenticationException extends CirrusException {
    }

    public static class CirrusGeneralException extends CirrusException {
    }

    public static class ConcurrentStreamingDeviceException extends CirrusException {
    }

    public static class DownloadLimitationBreachException extends CirrusException {
    }

    public static class FileBinaryDataNotFoundException extends CirrusException {
    }

    public static class InvalidCustomerException extends CirrusException {
    }

    public static class InvalidParameterException extends CirrusException {
    }

    public static class InvalidTermsOfServiceException extends CirrusException {
    }

    public static class MalformedInputException extends CirrusException {
    }

    public static class MessageNotFoundException extends CirrusException {
    }

    public static class MetadataNotFoundException extends CirrusException {
    }

    public static class OrderNotFoundException extends CirrusException {
    }

    public static class PurchaseException extends CirrusException {
    }

    public static class StorageException extends CirrusException {
    }

    public static class StorageLimitException extends CirrusException {
    }

    public static class StreamingDeviceNotAuthorizedException extends CirrusException {
    }

    public static CirrusException getMappedException(String key, String message) {
        return getMappedException(key, message, BlueMoonExceptionsUtil.DetectType.Detect);
    }

    private static CirrusException getMappedException(String key, String message, BlueMoonExceptionsUtil.DetectType detectType) {
        CirrusException cirrusException;
        Class<? extends CirrusException> cls = EXCEPTION_TYPE_MAP.get(key);
        if (cls != null) {
            try {
                cirrusException = cls.newInstance();
                cirrusException.setAwsErrorMessage(message);
            } catch (Exception e) {
                Log.error("CirrusExceptions", "Unable to instantiate a child CirrusException", e);
                cirrusException = new CirrusException(key, message);
            }
            if (detectType != BlueMoonExceptionsUtil.DetectType.Dont_Detect) {
                BlueMoonExceptionsUtil.detectBlueMoonException(cirrusException, detectType);
            }
            Log.verbose("CirrusExceptions", "Throwing type specific, subclassed CirrusException");
            return cirrusException;
        }
        return new CirrusException(key, message);
    }

    public static class CirrusException extends ServiceException {
        private String mAwsErrorMessage;

        public void setAwsErrorMessage(String message) {
            this.mAwsErrorMessage = message;
        }

        public String getAwsErrorMessage() {
            return this.mAwsErrorMessage;
        }

        CirrusException(String what, String awsErrorMessage) {
            super(what);
            this.mAwsErrorMessage = awsErrorMessage;
        }

        CirrusException(String what) {
            this(what, null);
        }

        CirrusException() {
            this.mAwsErrorMessage = null;
        }
    }

    public static class DeviceNotAuthorizedException extends CirrusException {
        public static boolean hasBeenDetected() {
            return SettingsUtil.getBooleanPref(R.string.setting_key_device_authorized_exception);
        }

        public static void setDetected(boolean detected) {
            SettingsUtil.setBooleanPref(R.string.setting_key_device_authorized_exception, detected);
        }
    }

    public static class AccountNotVerifiedException extends CirrusException {
        public static boolean hasBeenDetected() {
            return SettingsUtil.getBooleanPref(R.string.setting_key_account_not_verified_exception);
        }

        public static void setDetected(boolean detected) {
            SettingsUtil.setBooleanPref(R.string.setting_key_account_not_verified_exception, detected);
        }
    }

    public static class DeviceException extends CirrusException {
        public static boolean hasBeenDetected() {
            return SettingsUtil.getBooleanPref(R.string.setting_key_device_exception);
        }

        public static void setDetected(boolean detected) {
            SettingsUtil.setBooleanPref(R.string.setting_key_device_exception, detected);
        }
    }

    public static class DependencyFailureException extends CirrusException {
        public static boolean hasBeenDetected() {
            return SettingsUtil.getBooleanPref(R.string.setting_key_dependency_failure_exception);
        }

        public static void setDetected(boolean detected) {
            SettingsUtil.setBooleanPref(R.string.setting_key_dependency_failure_exception, detected);
        }
    }

    public static class DependencyTimeoutException extends CirrusException {
        public static boolean hasBeenDetected() {
            return SettingsUtil.getBooleanPref(R.string.setting_key_dependency_timeout_exception);
        }

        public static void setDetected(boolean detected) {
            SettingsUtil.setBooleanPref(R.string.setting_key_dependency_timeout_exception, detected);
        }
    }

    public static abstract class InvalidAccountException extends CirrusException {
        InvalidAccountException() {
        }
    }

    public static class InvalidPaymentMethodException extends InvalidAccountException {
        public static boolean hasBeenDetected() {
            return SettingsUtil.getBooleanPref(R.string.setting_key_invalid_payment_method_detected);
        }

        public static void setDetected(boolean detected) {
            SettingsUtil.setBooleanPref(R.string.setting_key_invalid_payment_method_detected, detected);
        }
    }

    public static class CloudPlayerHomeChangedException extends InvalidAccountException {
        public static boolean hasBeenDetected() {
            return SettingsUtil.getBooleanPref(R.string.setting_key_cloud_player_home_changed);
        }

        public static void setDetected(boolean detected) {
            SettingsUtil.setBooleanPref(R.string.setting_key_cloud_player_home_changed, detected);
        }
    }

    public static class DeviceRegistrationException extends InvalidAccountException {
        public static boolean hasBeenDetected() {
            return SettingsUtil.getBooleanPref(R.string.setting_key_device_exception_detected);
        }

        public static void setDetected(boolean detected) {
            SettingsUtil.setBooleanPref(R.string.setting_key_device_exception_detected, detected);
        }
    }

    static {
        HashMap<String, Class<? extends CirrusException>> map = new HashMap<>();
        EXCEPTION_TYPE_MAP = map;
        map.put("com.amazon.cirrus.libraryservice#AuthenticationException", AuthenticationException.class);
        map.put("AuthenticationException", AuthenticationException.class);
        map.put("com.amazon.cirrus.libraryservice#InvalidParameterException", InvalidParameterException.class);
        map.put("InvalidParameterException", InvalidParameterException.class);
        map.put("MalformedInput", MalformedInputException.class);
        map.put("com.amazon.cirrus.libraryservice#StorageException", StorageException.class);
        map.put("StorageException", StorageException.class);
        map.put("com.amazon.cirrus.libraryservice#CirrusGeneralException", CirrusGeneralException.class);
        map.put("CirrusGeneralException", CirrusGeneralException.class);
        map.put("com.amazon.cirrus.libraryservice#InvalidPaymentMethodException", InvalidPaymentMethodException.class);
        map.put("InvalidPaymentMethodException", InvalidPaymentMethodException.class);
        map.put("com.amazon.cirrus.libraryservice#DeviceException", DeviceRegistrationException.class);
        map.put("DeviceException", DeviceRegistrationException.class);
        map.put("com.amazon.cirrus.libraryservice#InvalidTermsOfServiceException", InvalidTermsOfServiceException.class);
        map.put("InvalidTermsOfServiceException", InvalidTermsOfServiceException.class);
        map.put("com.amazon.cirrus.libraryservice#AccountNotFoundException", AccountNotFoundException.class);
        map.put("AccountNotFoundException", AccountNotFoundException.class);
        map.put("com.amazon.cirrus.libraryservice#AccountLockedException", AccountLockedException.class);
        map.put("AccountLockedException", AccountLockedException.class);
        map.put("com.amazon.cirrus.libraryservice#InvalidCustomerException", InvalidCustomerException.class);
        map.put("InvalidCustomerException", InvalidCustomerException.class);
        map.put("com.amazon.cirrus.libraryservice#FileBinaryDataNotFoundException", FileBinaryDataNotFoundException.class);
        map.put("FileBinaryDataNotFoundException", FileBinaryDataNotFoundException.class);
        map.put("com.amazon.cirrus.libraryservice#MetadataNotFoundException", MetadataNotFoundException.class);
        map.put("MetadataNotFoundException", MetadataNotFoundException.class);
        map.put("com.amazon.cirrus.libraryservice#StorageLimitException", StorageLimitException.class);
        map.put("StorageLimitException", StorageLimitException.class);
        map.put("com.amazon.cirrus.libraryservice#DownloadLimitationBreachException", DownloadLimitationBreachException.class);
        map.put("DownloadLimitationBreachException", DownloadLimitationBreachException.class);
        map.put("com.amazon.cirrus.libraryservice#PurchaseException", PurchaseException.class);
        map.put("PurchaseException", PurchaseException.class);
        map.put("com.amazon.cirrus.libraryservice#OrderNotFoundException", OrderNotFoundException.class);
        map.put("OrderNotFoundException", OrderNotFoundException.class);
        map.put("com.amazon.cirrus.libraryservice#ConcurrentStreamingDeviceException", ConcurrentStreamingDeviceException.class);
        map.put("ConcurrentStreamingDeviceException", ConcurrentStreamingDeviceException.class);
        map.put("com.amazon.cirrus.libraryservice#MessageNotFoundException", MessageNotFoundException.class);
        map.put("MessageNotFoundException", MessageNotFoundException.class);
        map.put("com.amazon.cirrus.libraryservice#StreamingDeviceNotAuthorizedException", StreamingDeviceNotAuthorizedException.class);
        map.put("StreamingDeviceNotAuthorizedException", StreamingDeviceNotAuthorizedException.class);
        map.put("com.amazon.cirrus.libraryservice#DeviceNotAuthorizedException", DeviceNotAuthorizedException.class);
        map.put("DeviceNotAuthorizedException", DeviceNotAuthorizedException.class);
        map.put("com.amazon.cirrus.libraryservice#AccountNotVerifiedException", AccountNotVerifiedException.class);
        map.put("AccountNotVerifiedException", AccountNotVerifiedException.class);
        map.put("com.amazon.cirrus.libraryservice#DeviceException", DeviceException.class);
        map.put("DeviceException", DeviceException.class);
        map.put("com.amazon.cirrus.libraryservice#DependencyFailureException", DependencyFailureException.class);
        map.put("DependencyFailureException", DependencyFailureException.class);
        map.put("com.amazon.cirrus.libraryservice#DependencyTimeoutException", DependencyTimeoutException.class);
        map.put("DependencyTimeoutException", DependencyTimeoutException.class);
    }
}
