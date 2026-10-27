package android.hardware.biometrics;

import android.content.Context;
import android.content.DialogInterface;
import android.hardware.biometrics.BiometricAuthenticator;
import android.hardware.biometrics.IAuthService;
import android.hardware.biometrics.IBiometricServiceReceiver;
import android.hardware.face.FaceManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Binder;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.security.identity.IdentityCredential;
import android.text.TextUtils;
import android.util.Log;
import com.android.internal.R;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.security.Signature;
import java.util.concurrent.Executor;
import javax.crypto.Cipher;
import javax.crypto.Mac;

/* JADX INFO: loaded from: classes.dex */
public class BiometricPrompt implements BiometricAuthenticator, BiometricConstants {
    public static final int AUTHENTICATION_RESULT_TYPE_BIOMETRIC = 2;
    public static final int AUTHENTICATION_RESULT_TYPE_DEVICE_CREDENTIAL = 1;
    public static final int DISMISSED_REASON_BIOMETRIC_CONFIRMED = 1;
    public static final int DISMISSED_REASON_BIOMETRIC_CONFIRM_NOT_REQUIRED = 4;
    public static final int DISMISSED_REASON_CREDENTIAL_CONFIRMED = 7;
    public static final int DISMISSED_REASON_ERROR = 5;
    public static final int DISMISSED_REASON_NEGATIVE = 2;
    public static final int DISMISSED_REASON_SERVER_REQUESTED = 6;
    public static final int DISMISSED_REASON_USER_CANCEL = 3;
    public static final String EXTRA_DISALLOW_BIOMETRICS_IF_POLICY_EXISTS = "check_dpm";
    public static final int HIDE_DIALOG_DELAY = 2000;
    public static final String KEY_ALLOW_DEVICE_CREDENTIAL = "allow_device_credential";
    public static final String KEY_AUTHENTICATORS_ALLOWED = "authenticators_allowed";
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_DEVICE_CREDENTIAL_DESCRIPTION = "device_credential_description";
    public static final String KEY_DEVICE_CREDENTIAL_SUBTITLE = "device_credential_subtitle";
    public static final String KEY_DEVICE_CREDENTIAL_TITLE = "device_credential_title";
    public static final String KEY_NEGATIVE_TEXT = "negative_text";
    public static final String KEY_RECEIVE_SYSTEM_EVENTS = "receive_system_events";
    public static final String KEY_REQUIRE_CONFIRMATION = "require_confirmation";
    public static final String KEY_SUBTITLE = "subtitle";
    public static final String KEY_TITLE = "title";
    public static final String KEY_USE_DEFAULT_TITLE = "use_default_title";
    private static final String TAG = "BiometricPrompt";
    private AuthenticationCallback mAuthenticationCallback;
    private final IBiometricServiceReceiver mBiometricServiceReceiver;
    private final Bundle mBundle;
    private final Context mContext;
    private CryptoObject mCryptoObject;
    private Executor mExecutor;
    private final ButtonInfo mNegativeButtonInfo;
    private final ButtonInfo mPositiveButtonInfo;
    private final IAuthService mService;
    private final IBinder mToken;

    public static abstract class AuthenticationCallback extends BiometricAuthenticator.AuthenticationCallback {
        @Override // android.hardware.biometrics.BiometricAuthenticator.AuthenticationCallback
        public void onAuthenticationAcquired(int i) {
        }

        @Override // android.hardware.biometrics.BiometricAuthenticator.AuthenticationCallback
        public void onAuthenticationError(int i, CharSequence charSequence) {
        }

        @Override // android.hardware.biometrics.BiometricAuthenticator.AuthenticationCallback
        public void onAuthenticationFailed() {
        }

        @Override // android.hardware.biometrics.BiometricAuthenticator.AuthenticationCallback
        public void onAuthenticationHelp(int i, CharSequence charSequence) {
        }

        public void onAuthenticationSucceeded(AuthenticationResult authenticationResult) {
        }

        public void onSystemEvent(int i) {
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface AuthenticationResultType {
    }

    /* synthetic */ BiometricPrompt(Context context, Bundle bundle, ButtonInfo buttonInfo, ButtonInfo buttonInfo2, AnonymousClass1 anonymousClass1) {
        this(context, bundle, buttonInfo, buttonInfo2);
    }

    private static class ButtonInfo {
        Executor executor;
        DialogInterface.OnClickListener listener;

        ButtonInfo(Executor executor, DialogInterface.OnClickListener onClickListener) {
            this.executor = executor;
            this.listener = onClickListener;
        }
    }

    public static class Builder {
        private final Bundle mBundle = new Bundle();
        private Context mContext;
        private ButtonInfo mNegativeButtonInfo;
        private ButtonInfo mPositiveButtonInfo;

        public Builder(Context context) {
            this.mContext = context;
        }

        public Builder setTitle(CharSequence charSequence) {
            this.mBundle.putCharSequence("title", charSequence);
            return this;
        }

        public Builder setUseDefaultTitle() {
            this.mBundle.putBoolean(BiometricPrompt.KEY_USE_DEFAULT_TITLE, true);
            return this;
        }

        public Builder setSubtitle(CharSequence charSequence) {
            this.mBundle.putCharSequence(BiometricPrompt.KEY_SUBTITLE, charSequence);
            return this;
        }

        public Builder setDescription(CharSequence charSequence) {
            this.mBundle.putCharSequence("description", charSequence);
            return this;
        }

        public Builder setTextForDeviceCredential(CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3) {
            if (charSequence != null) {
                this.mBundle.putCharSequence(BiometricPrompt.KEY_DEVICE_CREDENTIAL_TITLE, charSequence);
            }
            if (charSequence2 != null) {
                this.mBundle.putCharSequence(BiometricPrompt.KEY_DEVICE_CREDENTIAL_SUBTITLE, charSequence2);
            }
            if (charSequence3 != null) {
                this.mBundle.putCharSequence(BiometricPrompt.KEY_DEVICE_CREDENTIAL_DESCRIPTION, charSequence3);
            }
            return this;
        }

        public Builder setNegativeButton(CharSequence charSequence, Executor executor, DialogInterface.OnClickListener onClickListener) {
            if (TextUtils.isEmpty(charSequence)) {
                throw new IllegalArgumentException("Text must be set and non-empty");
            }
            if (executor == null) {
                throw new IllegalArgumentException("Executor must not be null");
            }
            if (onClickListener == null) {
                throw new IllegalArgumentException("Listener must not be null");
            }
            this.mBundle.putCharSequence(BiometricPrompt.KEY_NEGATIVE_TEXT, charSequence);
            this.mNegativeButtonInfo = new ButtonInfo(executor, onClickListener);
            return this;
        }

        public Builder setConfirmationRequired(boolean z) {
            this.mBundle.putBoolean(BiometricPrompt.KEY_REQUIRE_CONFIRMATION, z);
            return this;
        }

        @Deprecated
        public Builder setDeviceCredentialAllowed(boolean z) {
            this.mBundle.putBoolean(BiometricPrompt.KEY_ALLOW_DEVICE_CREDENTIAL, z);
            return this;
        }

        public Builder setAllowedAuthenticators(int i) {
            this.mBundle.putInt(BiometricPrompt.KEY_AUTHENTICATORS_ALLOWED, i);
            return this;
        }

        public Builder setDisallowBiometricsIfPolicyExists(boolean z) {
            this.mBundle.putBoolean("check_dpm", z);
            return this;
        }

        public Builder setReceiveSystemEvents(boolean z) {
            this.mBundle.putBoolean(BiometricPrompt.KEY_RECEIVE_SYSTEM_EVENTS, z);
            return this;
        }

        public BiometricPrompt build() {
            CharSequence charSequence = this.mBundle.getCharSequence("title");
            CharSequence charSequence2 = this.mBundle.getCharSequence(BiometricPrompt.KEY_NEGATIVE_TEXT);
            boolean z = this.mBundle.getBoolean(BiometricPrompt.KEY_USE_DEFAULT_TITLE, false);
            boolean z2 = this.mBundle.getBoolean(BiometricPrompt.KEY_ALLOW_DEVICE_CREDENTIAL) || (32768 & this.mBundle.getInt(BiometricPrompt.KEY_AUTHENTICATORS_ALLOWED, 0)) != 0;
            if (TextUtils.isEmpty(charSequence) && !z) {
                throw new IllegalArgumentException("Title must be set and non-empty");
            }
            if (TextUtils.isEmpty(charSequence2) && !z2) {
                throw new IllegalArgumentException("Negative text must be set and non-empty");
            }
            if (!TextUtils.isEmpty(charSequence2) && z2) {
                throw new IllegalArgumentException("Can't have both negative button behavior and device credential enabled");
            }
            return new BiometricPrompt(this.mContext, this.mBundle, this.mPositiveButtonInfo, this.mNegativeButtonInfo, null);
        }
    }

    private class OnAuthenticationCancelListener implements CancellationSignal.OnCancelListener {
        private OnAuthenticationCancelListener() {
        }

        /* synthetic */ OnAuthenticationCancelListener(BiometricPrompt biometricPrompt, AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // android.os.CancellationSignal.OnCancelListener
        public void onCancel() {
            BiometricPrompt.this.cancelAuthentication();
        }
    }

    /* JADX INFO: renamed from: android.hardware.biometrics.BiometricPrompt$1, reason: invalid class name */
    class AnonymousClass1 extends IBiometricServiceReceiver.Stub {
        AnonymousClass1() {
        }

        @Override // android.hardware.biometrics.IBiometricServiceReceiver
        public void onAuthenticationSucceeded(final int i) throws RemoteException {
            BiometricPrompt.this.mExecutor.execute(new Runnable() { // from class: android.hardware.biometrics.-$$Lambda$BiometricPrompt$1$gIivejImUko3E_hn_CTfTgtadUc
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onAuthenticationSucceeded$0$BiometricPrompt$1(i);
                }
            });
        }

        public /* synthetic */ void lambda$onAuthenticationSucceeded$0$BiometricPrompt$1(int i) {
            BiometricPrompt.this.mAuthenticationCallback.onAuthenticationSucceeded(new AuthenticationResult(BiometricPrompt.this.mCryptoObject, i));
        }

        @Override // android.hardware.biometrics.IBiometricServiceReceiver
        public void onAuthenticationFailed() throws RemoteException {
            BiometricPrompt.this.mExecutor.execute(new Runnable() { // from class: android.hardware.biometrics.-$$Lambda$BiometricPrompt$1$kadcUdVEh6-Pi4fAGi8aOZdzebY
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onAuthenticationFailed$1$BiometricPrompt$1();
                }
            });
        }

        public /* synthetic */ void lambda$onAuthenticationFailed$1$BiometricPrompt$1() {
            BiometricPrompt.this.mAuthenticationCallback.onAuthenticationFailed();
        }

        @Override // android.hardware.biometrics.IBiometricServiceReceiver
        public void onError(final int i, final int i2, final int i3) throws RemoteException {
            BiometricPrompt.this.mExecutor.execute(new Runnable() { // from class: android.hardware.biometrics.-$$Lambda$BiometricPrompt$1$6ALUIGCaNBo6xJ0yw9OEFnUegfc
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onError$2$BiometricPrompt$1(i, i2, i3);
                }
            });
        }

        public /* synthetic */ void lambda$onError$2$BiometricPrompt$1(int i, int i2, int i3) {
            BiometricPrompt.this.mAuthenticationCallback.onAuthenticationError(i2, i != 2 ? i != 8 ? "" : FaceManager.getErrorString(BiometricPrompt.this.mContext, i2, i3) : FingerprintManager.getErrorString(BiometricPrompt.this.mContext, i2, i3));
        }

        @Override // android.hardware.biometrics.IBiometricServiceReceiver
        public void onAcquired(final int i, final String str) throws RemoteException {
            BiometricPrompt.this.mExecutor.execute(new Runnable() { // from class: android.hardware.biometrics.-$$Lambda$BiometricPrompt$1$txidP0_DcmimndZLc7PFTTOLwE8
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onAcquired$3$BiometricPrompt$1(i, str);
                }
            });
        }

        public /* synthetic */ void lambda$onAcquired$3$BiometricPrompt$1(int i, String str) {
            BiometricPrompt.this.mAuthenticationCallback.onAuthenticationHelp(i, str);
        }

        @Override // android.hardware.biometrics.IBiometricServiceReceiver
        public void onDialogDismissed(int i) throws RemoteException {
            if (i == 1) {
                BiometricPrompt.this.mPositiveButtonInfo.executor.execute(new Runnable() { // from class: android.hardware.biometrics.-$$Lambda$BiometricPrompt$1$NZtxxH8bEcWwX6VXvlg6wsw98G4
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$onDialogDismissed$4$BiometricPrompt$1();
                    }
                });
            } else if (i == 2) {
                BiometricPrompt.this.mNegativeButtonInfo.executor.execute(new Runnable() { // from class: android.hardware.biometrics.-$$Lambda$BiometricPrompt$1$rmD6lJwaAnjiJgJbC5DVCqTxtZQ
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$onDialogDismissed$5$BiometricPrompt$1();
                    }
                });
            }
        }

        public /* synthetic */ void lambda$onDialogDismissed$4$BiometricPrompt$1() {
            BiometricPrompt.this.mPositiveButtonInfo.listener.onClick(null, -1);
        }

        public /* synthetic */ void lambda$onDialogDismissed$5$BiometricPrompt$1() {
            BiometricPrompt.this.mNegativeButtonInfo.listener.onClick(null, -2);
        }

        @Override // android.hardware.biometrics.IBiometricServiceReceiver
        public void onSystemEvent(final int i) throws RemoteException {
            BiometricPrompt.this.mExecutor.execute(new Runnable() { // from class: android.hardware.biometrics.-$$Lambda$BiometricPrompt$1$TcdO8RRcXhep3sN0j3_S2sWnBFA
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onSystemEvent$6$BiometricPrompt$1(i);
                }
            });
        }

        public /* synthetic */ void lambda$onSystemEvent$6$BiometricPrompt$1(int i) {
            BiometricPrompt.this.mAuthenticationCallback.onSystemEvent(i);
        }
    }

    private BiometricPrompt(Context context, Bundle bundle, ButtonInfo buttonInfo, ButtonInfo buttonInfo2) {
        this.mToken = new Binder();
        this.mBiometricServiceReceiver = new AnonymousClass1();
        this.mContext = context;
        this.mBundle = bundle;
        this.mPositiveButtonInfo = buttonInfo;
        this.mNegativeButtonInfo = buttonInfo2;
        this.mService = IAuthService.Stub.asInterface(ServiceManager.getService(Context.AUTH_SERVICE));
    }

    public CharSequence getTitle() {
        return this.mBundle.getCharSequence("title", "");
    }

    public boolean shouldUseDefaultTitle() {
        return this.mBundle.getBoolean(KEY_USE_DEFAULT_TITLE, false);
    }

    public CharSequence getSubtitle() {
        return this.mBundle.getCharSequence(KEY_SUBTITLE);
    }

    public CharSequence getDescription() {
        return this.mBundle.getCharSequence("description");
    }

    public CharSequence getNegativeButtonText() {
        return this.mBundle.getCharSequence(KEY_NEGATIVE_TEXT);
    }

    public boolean isConfirmationRequired() {
        return this.mBundle.getBoolean(KEY_REQUIRE_CONFIRMATION, true);
    }

    public int getAllowedAuthenticators() {
        return this.mBundle.getInt(KEY_AUTHENTICATORS_ALLOWED, 0);
    }

    public static final class CryptoObject extends android.hardware.biometrics.CryptoObject {
        public CryptoObject(Signature signature) {
            super(signature);
        }

        public CryptoObject(Cipher cipher) {
            super(cipher);
        }

        public CryptoObject(Mac mac) {
            super(mac);
        }

        public CryptoObject(IdentityCredential identityCredential) {
            super(identityCredential);
        }

        @Override // android.hardware.biometrics.CryptoObject
        public Signature getSignature() {
            return super.getSignature();
        }

        @Override // android.hardware.biometrics.CryptoObject
        public Cipher getCipher() {
            return super.getCipher();
        }

        @Override // android.hardware.biometrics.CryptoObject
        public Mac getMac() {
            return super.getMac();
        }

        @Override // android.hardware.biometrics.CryptoObject
        public IdentityCredential getIdentityCredential() {
            return super.getIdentityCredential();
        }
    }

    public static class AuthenticationResult extends BiometricAuthenticator.AuthenticationResult {
        public AuthenticationResult(CryptoObject cryptoObject, int i) {
            super(cryptoObject, i, null, 0);
        }

        @Override // android.hardware.biometrics.BiometricAuthenticator.AuthenticationResult
        public CryptoObject getCryptoObject() {
            return (CryptoObject) super.getCryptoObject();
        }

        @Override // android.hardware.biometrics.BiometricAuthenticator.AuthenticationResult
        public int getAuthenticationType() {
            return super.getAuthenticationType();
        }
    }

    public void authenticateUser(CancellationSignal cancellationSignal, Executor executor, AuthenticationCallback authenticationCallback, int i) {
        if (cancellationSignal == null) {
            throw new IllegalArgumentException("Must supply a cancellation signal");
        }
        if (executor == null) {
            throw new IllegalArgumentException("Must supply an executor");
        }
        if (authenticationCallback == null) {
            throw new IllegalArgumentException("Must supply a callback");
        }
        authenticateInternal(null, cancellationSignal, executor, authenticationCallback, i);
    }

    public void authenticate(CryptoObject cryptoObject, CancellationSignal cancellationSignal, Executor executor, AuthenticationCallback authenticationCallback) {
        if (cryptoObject == null) {
            throw new IllegalArgumentException("Must supply a crypto object");
        }
        if (cancellationSignal == null) {
            throw new IllegalArgumentException("Must supply a cancellation signal");
        }
        if (executor == null) {
            throw new IllegalArgumentException("Must supply an executor");
        }
        if (authenticationCallback == null) {
            throw new IllegalArgumentException("Must supply a callback");
        }
        if ((this.mBundle.getInt(KEY_AUTHENTICATORS_ALLOWED, 15) & 255 & (-16)) != 0) {
            throw new IllegalArgumentException("Only Strong biometrics supported with crypto");
        }
        authenticateInternal(cryptoObject, cancellationSignal, executor, authenticationCallback, this.mContext.getUserId());
    }

    public void authenticate(CancellationSignal cancellationSignal, Executor executor, AuthenticationCallback authenticationCallback) {
        if (cancellationSignal == null) {
            throw new IllegalArgumentException("Must supply a cancellation signal");
        }
        if (executor == null) {
            throw new IllegalArgumentException("Must supply an executor");
        }
        if (authenticationCallback == null) {
            throw new IllegalArgumentException("Must supply a callback");
        }
        authenticateInternal(null, cancellationSignal, executor, authenticationCallback, this.mContext.getUserId());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cancelAuthentication() {
        IAuthService iAuthService = this.mService;
        if (iAuthService != null) {
            try {
                iAuthService.cancelAuthentication(this.mToken, this.mContext.getOpPackageName());
            } catch (RemoteException e) {
                Log.e(TAG, "Unable to cancel authentication", e);
            }
        }
    }

    private void authenticateInternal(CryptoObject cryptoObject, CancellationSignal cancellationSignal, Executor executor, final AuthenticationCallback authenticationCallback, int i) {
        Bundle bundle;
        try {
            if (cancellationSignal.isCanceled()) {
                Log.w(TAG, "Authentication already canceled");
                return;
            }
            cancellationSignal.setOnCancelListener(new OnAuthenticationCancelListener(this, null));
            this.mCryptoObject = cryptoObject;
            this.mExecutor = executor;
            this.mAuthenticationCallback = authenticationCallback;
            long opId = cryptoObject != null ? cryptoObject.getOpId() : 0L;
            if (cryptoObject != null) {
                bundle = new Bundle(this.mBundle);
                bundle.putInt(KEY_AUTHENTICATORS_ALLOWED, this.mBundle.getInt(KEY_AUTHENTICATORS_ALLOWED, 15));
            } else {
                bundle = this.mBundle;
            }
            this.mService.authenticate(this.mToken, opId, i, this.mBiometricServiceReceiver, this.mContext.getOpPackageName(), bundle);
        } catch (RemoteException e) {
            Log.e(TAG, "Remote exception while authenticating", e);
            this.mExecutor.execute(new Runnable() { // from class: android.hardware.biometrics.-$$Lambda$BiometricPrompt$b7ud_riEDbZLscO6CnUOgaGmC8w
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$authenticateInternal$0$BiometricPrompt(authenticationCallback);
                }
            });
        }
    }

    public /* synthetic */ void lambda$authenticateInternal$0$BiometricPrompt(AuthenticationCallback authenticationCallback) {
        authenticationCallback.onAuthenticationError(1, this.mContext.getString(R.string.biometric_error_hw_unavailable));
    }
}
