package android.hardware.biometrics;

import android.annotation.SystemApi;
import android.content.Context;
import android.os.RemoteException;
import android.util.Slog;

/* JADX INFO: loaded from: classes.dex */
public class BiometricManager {
    public static final int BIOMETRIC_ERROR_HW_UNAVAILABLE = 1;
    public static final int BIOMETRIC_ERROR_NONE_ENROLLED = 11;
    public static final int BIOMETRIC_ERROR_NO_HARDWARE = 12;
    public static final int BIOMETRIC_ERROR_SECURITY_UPDATE_REQUIRED = 15;
    public static final int BIOMETRIC_SUCCESS = 0;
    private static final String TAG = "BiometricManager";
    private final Context mContext;
    private final IAuthService mService;

    public interface Authenticators {

        @SystemApi
        public static final int BIOMETRIC_CONVENIENCE = 4095;
        public static final int BIOMETRIC_MAX_STRENGTH = 1;
        public static final int BIOMETRIC_MIN_STRENGTH = 32767;
        public static final int BIOMETRIC_STRONG = 15;
        public static final int BIOMETRIC_WEAK = 255;
        public static final int DEVICE_CREDENTIAL = 32768;

        @SystemApi
        public static final int EMPTY_SET = 0;

        public @interface Types {
        }
    }

    @interface BiometricError {
    }

    public BiometricManager(Context context, IAuthService iAuthService) {
        this.mContext = context;
        this.mService = iAuthService;
    }

    @Deprecated
    public int canAuthenticate() {
        return canAuthenticate(255);
    }

    public int canAuthenticate(int i) {
        return canAuthenticate(this.mContext.getUserId(), i);
    }

    public int canAuthenticate(int i, int i2) {
        if (this.mService != null) {
            try {
                return this.mService.canAuthenticate(this.mContext.getOpPackageName(), i, i2);
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
        Slog.w(TAG, "hasEnrolledBiometrics(): Service not connected");
        return 1;
    }

    public boolean hasEnrolledBiometrics(int i) {
        IAuthService iAuthService = this.mService;
        if (iAuthService != null) {
            try {
                return iAuthService.hasEnrolledBiometrics(i, this.mContext.getOpPackageName());
            } catch (RemoteException e) {
                Slog.w(TAG, "Remote exception in hasEnrolledBiometrics(): " + e);
            }
        }
        return false;
    }

    public void registerEnabledOnKeyguardCallback(IBiometricEnabledOnKeyguardCallback iBiometricEnabledOnKeyguardCallback) {
        IAuthService iAuthService = this.mService;
        if (iAuthService != null) {
            try {
                iAuthService.registerEnabledOnKeyguardCallback(iBiometricEnabledOnKeyguardCallback);
                return;
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
        Slog.w(TAG, "registerEnabledOnKeyguardCallback(): Service not connected");
    }

    public void setActiveUser(int i) {
        IAuthService iAuthService = this.mService;
        if (iAuthService != null) {
            try {
                iAuthService.setActiveUser(i);
                return;
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
        Slog.w(TAG, "setActiveUser(): Service not connected");
    }

    public void resetLockout(byte[] bArr) {
        IAuthService iAuthService = this.mService;
        if (iAuthService != null) {
            try {
                iAuthService.resetLockout(bArr);
                return;
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
        Slog.w(TAG, "resetLockout(): Service not connected");
    }

    public long[] getAuthenticatorIds() {
        IAuthService iAuthService = this.mService;
        if (iAuthService != null) {
            try {
                return iAuthService.getAuthenticatorIds();
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
        Slog.w(TAG, "getAuthenticatorIds(): Service not connected");
        return new long[0];
    }
}
