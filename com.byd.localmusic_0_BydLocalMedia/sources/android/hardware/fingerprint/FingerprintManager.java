package android.hardware.fingerprint;

import android.app.ActivityManager;
import android.content.Context;
import android.hardware.biometrics.BiometricAuthenticator;
import android.hardware.biometrics.BiometricFingerprintConstants;
import android.hardware.biometrics.IBiometricServiceLockoutResetCallback;
import android.hardware.fingerprint.FingerprintManager;
import android.hardware.fingerprint.IFingerprintServiceReceiver;
import android.os.Binder;
import android.os.CancellationSignal;
import android.os.Handler;
import android.os.IBinder;
import android.os.IRemoteCallback;
import android.os.Looper;
import android.os.Message;
import android.os.PowerManager;
import android.os.RemoteException;
import android.security.identity.IdentityCredential;
import android.util.Slog;
import com.android.internal.R;
import java.security.Signature;
import java.util.List;
import javax.crypto.Cipher;
import javax.crypto.Mac;

/* JADX INFO: loaded from: classes2.dex */
@Deprecated
public class FingerprintManager implements BiometricAuthenticator, BiometricFingerprintConstants {
    private static final boolean DEBUG = true;
    private static final int MSG_ACQUIRED = 101;
    private static final int MSG_AUTHENTICATION_FAILED = 103;
    private static final int MSG_AUTHENTICATION_SUCCEEDED = 102;
    private static final int MSG_ENROLL_RESULT = 100;
    private static final int MSG_ENUMERATED = 106;
    private static final int MSG_ERROR = 104;
    private static final int MSG_FINGERPRINT_DETECTED = 107;
    private static final int MSG_REMOVED = 105;
    private static final String TAG = "FingerprintManager";
    private AuthenticationCallback mAuthenticationCallback;
    private Context mContext;
    private CryptoObject mCryptoObject;
    private EnrollmentCallback mEnrollmentCallback;
    private EnumerateCallback mEnumerateCallback;
    private FingerprintDetectionCallback mFingerprintDetectionCallback;
    private Handler mHandler;
    private RemovalCallback mRemovalCallback;
    private Fingerprint mRemovalFingerprint;
    private IFingerprintService mService;
    private IBinder mToken = new Binder();
    private IFingerprintServiceReceiver mServiceReceiver = new IFingerprintServiceReceiver.Stub() { // from class: android.hardware.fingerprint.FingerprintManager.2
        @Override // android.hardware.fingerprint.IFingerprintServiceReceiver
        public void onEnrollResult(long j, int i, int i2, int i3) {
            FingerprintManager.this.mHandler.obtainMessage(100, i3, 0, new Fingerprint(null, i2, i, j)).sendToTarget();
        }

        @Override // android.hardware.fingerprint.IFingerprintServiceReceiver
        public void onAcquired(long j, int i, int i2) {
            FingerprintManager.this.mHandler.obtainMessage(101, i, i2, Long.valueOf(j)).sendToTarget();
        }

        @Override // android.hardware.fingerprint.IFingerprintServiceReceiver
        public void onAuthenticationSucceeded(long j, Fingerprint fingerprint, int i, boolean z) {
            FingerprintManager.this.mHandler.obtainMessage(102, i, z ? 1 : 0, fingerprint).sendToTarget();
        }

        @Override // android.hardware.fingerprint.IFingerprintServiceReceiver
        public void onFingerprintDetected(long j, int i, boolean z) {
            FingerprintManager.this.mHandler.obtainMessage(107, i, 0, Boolean.valueOf(z)).sendToTarget();
        }

        @Override // android.hardware.fingerprint.IFingerprintServiceReceiver
        public void onAuthenticationFailed(long j) {
            FingerprintManager.this.mHandler.obtainMessage(103).sendToTarget();
        }

        @Override // android.hardware.fingerprint.IFingerprintServiceReceiver
        public void onError(long j, int i, int i2) {
            FingerprintManager.this.mHandler.obtainMessage(104, i, i2, Long.valueOf(j)).sendToTarget();
        }

        @Override // android.hardware.fingerprint.IFingerprintServiceReceiver
        public void onRemoved(long j, int i, int i2, int i3) {
            FingerprintManager.this.mHandler.obtainMessage(105, i3, 0, new Fingerprint(null, i2, i, j)).sendToTarget();
        }

        @Override // android.hardware.fingerprint.IFingerprintServiceReceiver
        public void onEnumerated(long j, int i, int i2, int i3) {
            FingerprintManager.this.mHandler.obtainMessage(106, i, i2, Long.valueOf(j)).sendToTarget();
        }
    };

    @Deprecated
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
    }

    public static abstract class EnrollmentCallback {
        public void onEnrollmentError(int i, CharSequence charSequence) {
        }

        public void onEnrollmentHelp(int i, CharSequence charSequence) {
        }

        public void onEnrollmentProgress(int i) {
        }
    }

    public static abstract class EnumerateCallback {
        public void onEnumerate(Fingerprint fingerprint) {
        }

        public void onEnumerateError(int i, CharSequence charSequence) {
        }
    }

    public interface FingerprintDetectionCallback {
        void onFingerprintDetected(int i, boolean z);
    }

    public static abstract class LockoutResetCallback {
        public void onLockoutReset() {
        }
    }

    public static abstract class RemovalCallback {
        public void onRemovalError(Fingerprint fingerprint, int i, CharSequence charSequence) {
        }

        public void onRemovalSucceeded(Fingerprint fingerprint, int i) {
        }
    }

    private class OnEnrollCancelListener implements CancellationSignal.OnCancelListener {
        private OnEnrollCancelListener() {
        }

        /* synthetic */ OnEnrollCancelListener(FingerprintManager fingerprintManager, AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // android.os.CancellationSignal.OnCancelListener
        public void onCancel() {
            FingerprintManager.this.cancelEnrollment();
        }
    }

    private class OnAuthenticationCancelListener implements CancellationSignal.OnCancelListener {
        private android.hardware.biometrics.CryptoObject mCrypto;

        public OnAuthenticationCancelListener(android.hardware.biometrics.CryptoObject cryptoObject) {
            this.mCrypto = cryptoObject;
        }

        @Override // android.os.CancellationSignal.OnCancelListener
        public void onCancel() {
            FingerprintManager.this.cancelAuthentication(this.mCrypto);
        }
    }

    private class OnFingerprintDetectionCancelListener implements CancellationSignal.OnCancelListener {
        private OnFingerprintDetectionCancelListener() {
        }

        /* synthetic */ OnFingerprintDetectionCancelListener(FingerprintManager fingerprintManager, AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // android.os.CancellationSignal.OnCancelListener
        public void onCancel() {
            FingerprintManager.this.cancelFingerprintDetect();
        }
    }

    @Deprecated
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

    @Deprecated
    public static class AuthenticationResult {
        private CryptoObject mCryptoObject;
        private Fingerprint mFingerprint;
        private boolean mIsStrongBiometric;
        private int mUserId;

        public AuthenticationResult(CryptoObject cryptoObject, Fingerprint fingerprint, int i, boolean z) {
            this.mCryptoObject = cryptoObject;
            this.mFingerprint = fingerprint;
            this.mUserId = i;
            this.mIsStrongBiometric = z;
        }

        public CryptoObject getCryptoObject() {
            return this.mCryptoObject;
        }

        public Fingerprint getFingerprint() {
            return this.mFingerprint;
        }

        public int getUserId() {
            return this.mUserId;
        }

        public boolean isStrongBiometric() {
            return this.mIsStrongBiometric;
        }
    }

    @Deprecated
    public void authenticate(CryptoObject cryptoObject, CancellationSignal cancellationSignal, int i, AuthenticationCallback authenticationCallback, Handler handler) {
        authenticate(cryptoObject, cancellationSignal, i, authenticationCallback, handler, this.mContext.getUserId());
    }

    private void useHandler(Handler handler) {
        AnonymousClass1 anonymousClass1 = null;
        if (handler != null) {
            this.mHandler = new MyHandler(this, handler.getLooper(), anonymousClass1);
        } else if (this.mHandler.getLooper() != this.mContext.getMainLooper()) {
            this.mHandler = new MyHandler(this, this.mContext.getMainLooper(), anonymousClass1);
        }
    }

    public void authenticate(CryptoObject cryptoObject, CancellationSignal cancellationSignal, int i, AuthenticationCallback authenticationCallback, Handler handler, int i2) {
        if (authenticationCallback == null) {
            throw new IllegalArgumentException("Must supply an authentication callback");
        }
        if (cancellationSignal != null) {
            if (cancellationSignal.isCanceled()) {
                Slog.w(TAG, "authentication already canceled");
                return;
            }
            cancellationSignal.setOnCancelListener(new OnAuthenticationCancelListener(cryptoObject));
        }
        if (this.mService != null) {
            try {
                useHandler(handler);
                this.mAuthenticationCallback = authenticationCallback;
                this.mCryptoObject = cryptoObject;
                this.mService.authenticate(this.mToken, cryptoObject != null ? cryptoObject.getOpId() : 0L, i2, this.mServiceReceiver, i, this.mContext.getOpPackageName());
            } catch (RemoteException e) {
                Slog.w(TAG, "Remote exception while authenticating: ", e);
                if (authenticationCallback != null) {
                    authenticationCallback.onAuthenticationError(1, getErrorString(this.mContext, 1, 0));
                }
            }
        }
    }

    public void detectFingerprint(CancellationSignal cancellationSignal, FingerprintDetectionCallback fingerprintDetectionCallback, int i) {
        if (this.mService == null) {
            return;
        }
        if (cancellationSignal.isCanceled()) {
            Slog.w(TAG, "Detection already cancelled");
            return;
        }
        cancellationSignal.setOnCancelListener(new OnFingerprintDetectionCancelListener(this, null));
        this.mFingerprintDetectionCallback = fingerprintDetectionCallback;
        try {
            this.mService.detectFingerprint(this.mToken, i, this.mServiceReceiver, this.mContext.getOpPackageName());
        } catch (RemoteException e) {
            Slog.w(TAG, "Remote exception when requesting finger detect", e);
        }
    }

    public void enroll(byte[] bArr, CancellationSignal cancellationSignal, int i, int i2, EnrollmentCallback enrollmentCallback) {
        if (i2 == -2) {
            i2 = getCurrentUserId();
        }
        int i3 = i2;
        if (enrollmentCallback == null) {
            throw new IllegalArgumentException("Must supply an enrollment callback");
        }
        if (cancellationSignal != null) {
            if (cancellationSignal.isCanceled()) {
                Slog.w(TAG, "enrollment already canceled");
                return;
            }
            cancellationSignal.setOnCancelListener(new OnEnrollCancelListener(this, null));
        }
        IFingerprintService iFingerprintService = this.mService;
        if (iFingerprintService != null) {
            try {
                this.mEnrollmentCallback = enrollmentCallback;
                iFingerprintService.enroll(this.mToken, bArr, i3, this.mServiceReceiver, i, this.mContext.getOpPackageName());
            } catch (RemoteException e) {
                Slog.w(TAG, "Remote exception in enroll: ", e);
                if (enrollmentCallback != null) {
                    enrollmentCallback.onEnrollmentError(1, getErrorString(this.mContext, 1, 0));
                }
            }
        }
    }

    public long preEnroll() {
        IFingerprintService iFingerprintService = this.mService;
        if (iFingerprintService == null) {
            return 0L;
        }
        try {
            return iFingerprintService.preEnroll(this.mToken);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public int postEnroll() {
        IFingerprintService iFingerprintService = this.mService;
        if (iFingerprintService == null) {
            return 0;
        }
        try {
            return iFingerprintService.postEnroll(this.mToken);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @Override // android.hardware.biometrics.BiometricAuthenticator
    public void setActiveUser(int i) {
        IFingerprintService iFingerprintService = this.mService;
        if (iFingerprintService != null) {
            try {
                iFingerprintService.setActiveUser(i);
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
    }

    public void remove(Fingerprint fingerprint, int i, RemovalCallback removalCallback) {
        IFingerprintService iFingerprintService = this.mService;
        if (iFingerprintService != null) {
            try {
                this.mRemovalCallback = removalCallback;
                this.mRemovalFingerprint = fingerprint;
                iFingerprintService.remove(this.mToken, fingerprint.getBiometricId(), fingerprint.getGroupId(), i, this.mServiceReceiver);
            } catch (RemoteException e) {
                Slog.w(TAG, "Remote exception in remove: ", e);
                if (removalCallback != null) {
                    removalCallback.onRemovalError(fingerprint, 1, getErrorString(this.mContext, 1, 0));
                }
            }
        }
    }

    public void enumerate(int i, EnumerateCallback enumerateCallback) {
        IFingerprintService iFingerprintService = this.mService;
        if (iFingerprintService != null) {
            try {
                this.mEnumerateCallback = enumerateCallback;
                iFingerprintService.enumerate(this.mToken, i, this.mServiceReceiver);
            } catch (RemoteException e) {
                Slog.w(TAG, "Remote exception in enumerate: ", e);
                if (enumerateCallback != null) {
                    enumerateCallback.onEnumerateError(1, getErrorString(this.mContext, 1, 0));
                }
            }
        }
    }

    public void rename(int i, int i2, String str) {
        IFingerprintService iFingerprintService = this.mService;
        if (iFingerprintService != null) {
            try {
                iFingerprintService.rename(i, i2, str);
                return;
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
        Slog.w(TAG, "rename(): Service not connected!");
    }

    public List<Fingerprint> getEnrolledFingerprints(int i) {
        IFingerprintService iFingerprintService = this.mService;
        if (iFingerprintService == null) {
            return null;
        }
        try {
            return iFingerprintService.getEnrolledFingerprints(i, this.mContext.getOpPackageName());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public List<Fingerprint> getEnrolledFingerprints() {
        return getEnrolledFingerprints(this.mContext.getUserId());
    }

    @Override // android.hardware.biometrics.BiometricAuthenticator
    public boolean hasEnrolledTemplates() {
        return hasEnrolledFingerprints();
    }

    @Override // android.hardware.biometrics.BiometricAuthenticator
    public boolean hasEnrolledTemplates(int i) {
        return hasEnrolledFingerprints(i);
    }

    @Deprecated
    public boolean hasEnrolledFingerprints() {
        IFingerprintService iFingerprintService = this.mService;
        if (iFingerprintService == null) {
            return false;
        }
        try {
            return iFingerprintService.hasEnrolledFingerprints(this.mContext.getUserId(), this.mContext.getOpPackageName());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public boolean hasEnrolledFingerprints(int i) {
        IFingerprintService iFingerprintService = this.mService;
        if (iFingerprintService == null) {
            return false;
        }
        try {
            return iFingerprintService.hasEnrolledFingerprints(i, this.mContext.getOpPackageName());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @Override // android.hardware.biometrics.BiometricAuthenticator
    @Deprecated
    public boolean isHardwareDetected() {
        IFingerprintService iFingerprintService = this.mService;
        if (iFingerprintService != null) {
            try {
                return iFingerprintService.isHardwareDetected(this.mContext.getOpPackageName());
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
        Slog.w(TAG, "isFingerprintHardwareDetected(): Service not connected!");
        return false;
    }

    public void addLockoutResetCallback(LockoutResetCallback lockoutResetCallback) {
        if (this.mService != null) {
            try {
                this.mService.addLockoutResetCallback(new AnonymousClass1((PowerManager) this.mContext.getSystemService(PowerManager.class), lockoutResetCallback));
                return;
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
        Slog.w(TAG, "addLockoutResetCallback(): Service not connected!");
    }

    /* JADX INFO: renamed from: android.hardware.fingerprint.FingerprintManager$1, reason: invalid class name */
    class AnonymousClass1 extends IBiometricServiceLockoutResetCallback.Stub {
        final /* synthetic */ LockoutResetCallback val$callback;
        final /* synthetic */ PowerManager val$powerManager;

        AnonymousClass1(PowerManager powerManager, LockoutResetCallback lockoutResetCallback) {
            this.val$powerManager = powerManager;
            this.val$callback = lockoutResetCallback;
        }

        @Override // android.hardware.biometrics.IBiometricServiceLockoutResetCallback
        public void onLockoutReset(long j, IRemoteCallback iRemoteCallback) throws RemoteException {
            try {
                final PowerManager.WakeLock wakeLockNewWakeLock = this.val$powerManager.newWakeLock(1, "lockoutResetCallback");
                wakeLockNewWakeLock.acquire();
                Handler handler = FingerprintManager.this.mHandler;
                final LockoutResetCallback lockoutResetCallback = this.val$callback;
                handler.post(new Runnable() { // from class: android.hardware.fingerprint.-$$Lambda$FingerprintManager$1$4i3tUU8mafgvA9HaB2UPD31L6UY
                    @Override // java.lang.Runnable
                    public final void run() {
                        FingerprintManager.AnonymousClass1.lambda$onLockoutReset$0(lockoutResetCallback, wakeLockNewWakeLock);
                    }
                });
            } finally {
                iRemoteCallback.sendResult(null);
            }
        }

        static /* synthetic */ void lambda$onLockoutReset$0(LockoutResetCallback lockoutResetCallback, PowerManager.WakeLock wakeLock) {
            try {
                lockoutResetCallback.onLockoutReset();
            } finally {
                wakeLock.release();
            }
        }
    }

    private class MyHandler extends Handler {
        /* synthetic */ MyHandler(FingerprintManager fingerprintManager, Context context, AnonymousClass1 anonymousClass1) {
            this(context);
        }

        /* synthetic */ MyHandler(FingerprintManager fingerprintManager, Looper looper, AnonymousClass1 anonymousClass1) {
            this(looper);
        }

        private MyHandler(Context context) {
            super(context.getMainLooper());
        }

        private MyHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            switch (message.what) {
                case 100:
                    FingerprintManager.this.sendEnrollResult((Fingerprint) message.obj, message.arg1);
                    break;
                case 101:
                    FingerprintManager.this.sendAcquiredResult(((Long) message.obj).longValue(), message.arg1, message.arg2);
                    break;
                case 102:
                    FingerprintManager.this.sendAuthenticatedSucceeded((Fingerprint) message.obj, message.arg1, message.arg2 == 1);
                    break;
                case 103:
                    FingerprintManager.this.sendAuthenticatedFailed();
                    break;
                case 104:
                    FingerprintManager.this.sendErrorResult(((Long) message.obj).longValue(), message.arg1, message.arg2);
                    break;
                case 105:
                    FingerprintManager.this.sendRemovedResult((Fingerprint) message.obj, message.arg1);
                    break;
                case 106:
                    FingerprintManager.this.sendEnumeratedResult(((Long) message.obj).longValue(), message.arg1, message.arg2);
                    break;
                case 107:
                    FingerprintManager.this.sendFingerprintDetected(message.arg1, ((Boolean) message.obj).booleanValue());
                    break;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendRemovedResult(Fingerprint fingerprint, int i) {
        if (this.mRemovalCallback == null) {
            return;
        }
        if (fingerprint == null) {
            Slog.e(TAG, "Received MSG_REMOVED, but fingerprint is null");
            return;
        }
        int biometricId = fingerprint.getBiometricId();
        int biometricId2 = this.mRemovalFingerprint.getBiometricId();
        if (biometricId2 != 0 && biometricId != 0 && biometricId != biometricId2) {
            Slog.w(TAG, "Finger id didn't match: " + biometricId + " != " + biometricId2);
            return;
        }
        int groupId = fingerprint.getGroupId();
        int groupId2 = this.mRemovalFingerprint.getGroupId();
        if (groupId != groupId2) {
            Slog.w(TAG, "Group id didn't match: " + groupId + " != " + groupId2);
            return;
        }
        this.mRemovalCallback.onRemovalSucceeded(fingerprint, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendEnumeratedResult(long j, int i, int i2) {
        EnumerateCallback enumerateCallback = this.mEnumerateCallback;
        if (enumerateCallback != null) {
            enumerateCallback.onEnumerate(new Fingerprint(null, i2, i, j));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendEnrollResult(Fingerprint fingerprint, int i) {
        EnrollmentCallback enrollmentCallback = this.mEnrollmentCallback;
        if (enrollmentCallback != null) {
            enrollmentCallback.onEnrollmentProgress(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendAuthenticatedSucceeded(Fingerprint fingerprint, int i, boolean z) {
        if (this.mAuthenticationCallback != null) {
            this.mAuthenticationCallback.onAuthenticationSucceeded(new AuthenticationResult(this.mCryptoObject, fingerprint, i, z));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendAuthenticatedFailed() {
        AuthenticationCallback authenticationCallback = this.mAuthenticationCallback;
        if (authenticationCallback != null) {
            authenticationCallback.onAuthenticationFailed();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendAcquiredResult(long j, int i, int i2) {
        AuthenticationCallback authenticationCallback = this.mAuthenticationCallback;
        if (authenticationCallback != null) {
            authenticationCallback.onAuthenticationAcquired(i);
        }
        String acquiredString = getAcquiredString(this.mContext, i, i2);
        if (acquiredString == null) {
            return;
        }
        int i3 = i == 6 ? i2 + 1000 : i;
        EnrollmentCallback enrollmentCallback = this.mEnrollmentCallback;
        if (enrollmentCallback != null) {
            enrollmentCallback.onEnrollmentHelp(i3, acquiredString);
            return;
        }
        AuthenticationCallback authenticationCallback2 = this.mAuthenticationCallback;
        if (authenticationCallback2 == null || i == 7) {
            return;
        }
        authenticationCallback2.onAuthenticationHelp(i3, acquiredString);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendErrorResult(long j, int i, int i2) {
        int i3 = i == 8 ? i2 + 1000 : i;
        EnrollmentCallback enrollmentCallback = this.mEnrollmentCallback;
        if (enrollmentCallback != null) {
            enrollmentCallback.onEnrollmentError(i3, getErrorString(this.mContext, i, i2));
            return;
        }
        AuthenticationCallback authenticationCallback = this.mAuthenticationCallback;
        if (authenticationCallback != null) {
            authenticationCallback.onAuthenticationError(i3, getErrorString(this.mContext, i, i2));
            return;
        }
        RemovalCallback removalCallback = this.mRemovalCallback;
        if (removalCallback != null) {
            removalCallback.onRemovalError(this.mRemovalFingerprint, i3, getErrorString(this.mContext, i, i2));
            return;
        }
        EnumerateCallback enumerateCallback = this.mEnumerateCallback;
        if (enumerateCallback != null) {
            enumerateCallback.onEnumerateError(i3, getErrorString(this.mContext, i, i2));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendFingerprintDetected(int i, boolean z) {
        FingerprintDetectionCallback fingerprintDetectionCallback = this.mFingerprintDetectionCallback;
        if (fingerprintDetectionCallback == null) {
            Slog.e(TAG, "sendFingerprintDetected, callback null");
        } else {
            fingerprintDetectionCallback.onFingerprintDetected(i, z);
        }
    }

    public FingerprintManager(Context context, IFingerprintService iFingerprintService) {
        this.mContext = context;
        this.mService = iFingerprintService;
        if (iFingerprintService == null) {
            Slog.v(TAG, "FingerprintManagerService was null");
        }
        this.mHandler = new MyHandler(this, context, (AnonymousClass1) null);
    }

    private int getCurrentUserId() {
        try {
            return ActivityManager.getService().getCurrentUser().id;
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cancelEnrollment() {
        IFingerprintService iFingerprintService = this.mService;
        if (iFingerprintService != null) {
            try {
                iFingerprintService.cancelEnrollment(this.mToken);
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cancelAuthentication(android.hardware.biometrics.CryptoObject cryptoObject) {
        IFingerprintService iFingerprintService = this.mService;
        if (iFingerprintService != null) {
            try {
                iFingerprintService.cancelAuthentication(this.mToken, this.mContext.getOpPackageName());
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cancelFingerprintDetect() {
        IFingerprintService iFingerprintService = this.mService;
        if (iFingerprintService == null) {
            return;
        }
        try {
            iFingerprintService.cancelFingerprintDetect(this.mToken, this.mContext.getOpPackageName());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public static String getErrorString(Context context, int i, int i2) {
        switch (i) {
            case 1:
                return context.getString(R.string.fingerprint_error_hw_not_available);
            case 2:
                return context.getString(R.string.fingerprint_error_unable_to_process);
            case 3:
                return context.getString(R.string.fingerprint_error_timeout);
            case 4:
                return context.getString(R.string.fingerprint_error_no_space);
            case 5:
                return context.getString(R.string.fingerprint_error_canceled);
            case 7:
                return context.getString(R.string.fingerprint_error_lockout);
            case 8:
                String[] stringArray = context.getResources().getStringArray(R.array.fingerprint_error_vendor);
                if (i2 < stringArray.length) {
                    return stringArray[i2];
                }
                break;
            case 9:
                return context.getString(R.string.fingerprint_error_lockout_permanent);
            case 10:
                return context.getString(R.string.fingerprint_error_user_canceled);
            case 11:
                return context.getString(R.string.fingerprint_error_no_fingerprints);
            case 12:
                return context.getString(R.string.fingerprint_error_hw_not_present);
            case 15:
                return context.getString(R.string.fingerprint_error_security_update_required);
        }
        Slog.w(TAG, "Invalid error message: " + i + ", " + i2);
        return null;
    }

    public static String getAcquiredString(Context context, int i, int i2) {
        switch (i) {
            case 0:
                return null;
            case 1:
                return context.getString(R.string.fingerprint_acquired_partial);
            case 2:
                return context.getString(R.string.fingerprint_acquired_insufficient);
            case 3:
                return context.getString(R.string.fingerprint_acquired_imager_dirty);
            case 4:
                return context.getString(R.string.fingerprint_acquired_too_slow);
            case 5:
                return context.getString(R.string.fingerprint_acquired_too_fast);
            case 6:
                String[] stringArray = context.getResources().getStringArray(R.array.fingerprint_acquired_vendor);
                if (i2 < stringArray.length) {
                    return stringArray[i2];
                }
                break;
            case 7:
                return null;
        }
        Slog.w(TAG, "Invalid acquired message: " + i + ", " + i2);
        return null;
    }
}
