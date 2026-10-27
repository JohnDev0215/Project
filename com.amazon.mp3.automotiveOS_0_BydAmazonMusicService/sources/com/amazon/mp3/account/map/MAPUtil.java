package com.amazon.mp3.account.map;

import android.annotation.SuppressLint;
import android.os.Build;
import androidx.annotation.Nullable;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.PlatformUtil;
import com.amazon.mp3.util.ResourceUtil;

/* JADX INFO: loaded from: classes3.dex */
public class MAPUtil {
    private static final String TAG = "MAPUtil";

    /* JADX INFO: renamed from: com.amazon.mp3.account.map.MAPUtil$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$identity$auth$device$api$MAPAccountManager$RegistrationError;

        static {
            int[] iArr = new int[MAPAccountManager.RegistrationError.values().length];
            $SwitchMap$com$amazon$identity$auth$device$api$MAPAccountManager$RegistrationError = iArr;
            try {
                iArr[MAPAccountManager.RegistrationError.DEVICE_ALREADY_REGISTERED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$identity$auth$device$api$MAPAccountManager$RegistrationError[MAPAccountManager.RegistrationError.ACCOUNT_ALREADY_EXISTS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$identity$auth$device$api$MAPAccountManager$RegistrationError[MAPAccountManager.RegistrationError.AUTHENTICATION_FAILED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$identity$auth$device$api$MAPAccountManager$RegistrationError[MAPAccountManager.RegistrationError.BAD_REQUEST.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$identity$auth$device$api$MAPAccountManager$RegistrationError[MAPAccountManager.RegistrationError.CUSTOMER_NOT_FOUND.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$identity$auth$device$api$MAPAccountManager$RegistrationError[MAPAccountManager.RegistrationError.DEREGISTER_FAILED.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$identity$auth$device$api$MAPAccountManager$RegistrationError[MAPAccountManager.RegistrationError.DUPLICATE_DEVICE_NAME.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$identity$auth$device$api$MAPAccountManager$RegistrationError[MAPAccountManager.RegistrationError.NETWORK_FAILURE.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$amazon$identity$auth$device$api$MAPAccountManager$RegistrationError[MAPAccountManager.RegistrationError.PARSE_ERROR.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$amazon$identity$auth$device$api$MAPAccountManager$RegistrationError[MAPAccountManager.RegistrationError.REGISTER_FAILED.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$amazon$identity$auth$device$api$MAPAccountManager$RegistrationError[MAPAccountManager.RegistrationError.UNRECOGNIZED.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
        }
    }

    public static int convertMAPErrorCodeToMessageId(int errorCode) {
        if (errorCode == -1) {
            return R.string.dmusic_sso_auth_error_111;
        }
        try {
            switch (AnonymousClass1.$SwitchMap$com$amazon$identity$auth$device$api$MAPAccountManager$RegistrationError[MAPAccountManager.RegistrationError.fromValue(errorCode).ordinal()]) {
                case 1:
                case 2:
                    return R.string.dmusic_auth_devicemaster_error_device_already_registered;
                case 3:
                    return R.string.dmusic_sso_auth_error_112;
                case 4:
                    return R.string.dmusic_sso_auth_error_113;
                case 5:
                    return R.string.dmusic_auth_devicemaster_error_customer_not_found;
                case 6:
                    return R.string.dmusic_sso_auth_error_114;
                case 7:
                    return R.string.dmusic_auth_devicemaster_error_duplicate_account_name;
                case 8:
                    return ResourceUtil.getNoConnectionStringId();
                case 9:
                    return R.string.dmusic_sso_auth_error_115;
                case 10:
                    return R.string.dmusic_sso_auth_error_116;
                case 11:
                    return R.string.dmusic_sso_auth_error_117;
                default:
                    return R.string.dmusic_sso_auth_error_118;
            }
        } catch (IndexOutOfBoundsException unused) {
            return errorCode;
        }
        return errorCode;
    }

    @Nullable
    @SuppressLint({"MissingPermission"})
    public static String getDeviceSerialNumber() {
        if (PlatformUtil.isVersionOrGreater(26)) {
            try {
                return Build.getSerial();
            } catch (SecurityException e) {
                Log.error(TAG, "Cannot get Build.getSerial(). No READ_PHONE_STATE or READ_PRIVILEGED_PHONE_STATE permission granted: " + e.getMessage());
                return null;
            } catch (Exception e2) {
                Log.error(TAG, "Cannot get Build.getSerial()", e2);
                return null;
            }
        }
        return Build.SERIAL;
    }
}
