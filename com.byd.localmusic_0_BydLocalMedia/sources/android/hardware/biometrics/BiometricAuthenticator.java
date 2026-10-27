package android.hardware.biometrics;

import android.os.CancellationSignal;
import android.os.Parcelable;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public interface BiometricAuthenticator {
    public static final int TYPE_CREDENTIAL = 1;
    public static final int TYPE_FACE = 8;
    public static final int TYPE_FINGERPRINT = 2;
    public static final int TYPE_IRIS = 4;
    public static final int TYPE_NONE = 0;

    public static abstract class AuthenticationCallback {
        public void onAuthenticationAcquired(int i) {
        }

        public void onAuthenticationError(int i, CharSequence charSequence) {
        }

        public void onAuthenticationFailed() {
        }

        public void onAuthenticationHelp(int i, CharSequence charSequence) {
        }
    }

    public static abstract class Identifier implements Parcelable {
        private int mBiometricId;
        private long mDeviceId;
        private CharSequence mName;

        public Identifier() {
        }

        public Identifier(CharSequence charSequence, int i, long j) {
            this.mName = charSequence;
            this.mBiometricId = i;
            this.mDeviceId = j;
        }

        public CharSequence getName() {
            return this.mName;
        }

        public int getBiometricId() {
            return this.mBiometricId;
        }

        public long getDeviceId() {
            return this.mDeviceId;
        }

        public void setName(CharSequence charSequence) {
            this.mName = charSequence;
        }

        public void setDeviceId(long j) {
            this.mDeviceId = j;
        }
    }

    public static class AuthenticationResult {
        private int mAuthenticationType;
        private CryptoObject mCryptoObject;
        private Identifier mIdentifier;
        private int mUserId;

        public AuthenticationResult() {
        }

        public AuthenticationResult(CryptoObject cryptoObject, int i, Identifier identifier, int i2) {
            this.mCryptoObject = cryptoObject;
            this.mAuthenticationType = i;
            this.mIdentifier = identifier;
            this.mUserId = i2;
        }

        public CryptoObject getCryptoObject() {
            return this.mCryptoObject;
        }

        public int getAuthenticationType() {
            return this.mAuthenticationType;
        }

        public Identifier getId() {
            return this.mIdentifier;
        }

        public int getUserId() {
            return this.mUserId;
        }
    }

    default boolean isHardwareDetected() {
        throw new UnsupportedOperationException("Stub!");
    }

    default boolean hasEnrolledTemplates() {
        throw new UnsupportedOperationException("Stub!");
    }

    default boolean hasEnrolledTemplates(int i) {
        throw new UnsupportedOperationException("Stub!");
    }

    default void setActiveUser(int i) {
        throw new UnsupportedOperationException("Stub!");
    }

    default void authenticate(CryptoObject cryptoObject, CancellationSignal cancellationSignal, Executor executor, AuthenticationCallback authenticationCallback) {
        throw new UnsupportedOperationException("Stub!");
    }

    default void authenticate(CancellationSignal cancellationSignal, Executor executor, AuthenticationCallback authenticationCallback) {
        throw new UnsupportedOperationException("Stub!");
    }
}
