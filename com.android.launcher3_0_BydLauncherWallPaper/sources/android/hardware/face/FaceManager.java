package android.hardware.face;

import android.app.ActivityManager;
import android.content.Context;
import android.hardware.biometrics.BiometricAuthenticator;
import android.hardware.biometrics.BiometricFaceConstants;
import android.hardware.biometrics.CryptoObject;
import android.hardware.biometrics.IBiometricServiceLockoutResetCallback;
import android.hardware.face.FaceManager;
import android.hardware.face.IFaceServiceReceiver;
import android.os.Binder;
import android.os.CancellationSignal;
import android.os.Handler;
import android.os.IBinder;
import android.os.IRemoteCallback;
import android.os.Looper;
import android.os.Message;
import android.os.PowerManager;
import android.os.RemoteException;
import android.os.Trace;
import android.os.UserHandle;
import android.util.Log;
import android.util.Slog;
import com.android.internal.R;
import com.android.internal.os.SomeArgs;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class FaceManager implements BiometricAuthenticator, BiometricFaceConstants {
    private static final boolean DEBUG = true;
    private static final int MSG_ACQUIRED = 101;
    private static final int MSG_AUTHENTICATION_FAILED = 103;
    private static final int MSG_AUTHENTICATION_SUCCEEDED = 102;
    private static final int MSG_ENROLL_RESULT = 100;
    private static final int MSG_ERROR = 104;
    private static final int MSG_GET_FEATURE_COMPLETED = 106;
    private static final int MSG_REMOVED = 105;
    private static final int MSG_SET_FEATURE_COMPLETED = 107;
    private static final String TAG = "FaceManager";
    private AuthenticationCallback mAuthenticationCallback;
    private final Context mContext;
    private CryptoObject mCryptoObject;
    private EnrollmentCallback mEnrollmentCallback;
    private GetFeatureCallback mGetFeatureCallback;
    private Handler mHandler;
    private RemovalCallback mRemovalCallback;
    private Face mRemovalFace;
    private IFaceService mService;
    private SetFeatureCallback mSetFeatureCallback;
    private IBinder mToken = new Binder();
    private IFaceServiceReceiver mServiceReceiver = new IFaceServiceReceiver.Stub() { // from class: android.hardware.face.FaceManager.1
        @Override // android.hardware.face.IFaceServiceReceiver
        public void onEnumerated(long j, int i, int i2) {
        }

        @Override // android.hardware.face.IFaceServiceReceiver
        public void onEnrollResult(long j, int i, int i2) {
            FaceManager.this.mHandler.obtainMessage(100, i2, 0, new Face(null, i, j)).sendToTarget();
        }

        @Override // android.hardware.face.IFaceServiceReceiver
        public void onAcquired(long j, int i, int i2) {
            FaceManager.this.mHandler.obtainMessage(101, i, i2, Long.valueOf(j)).sendToTarget();
        }

        @Override // android.hardware.face.IFaceServiceReceiver
        public void onAuthenticationSucceeded(long j, Face face, int i, boolean z) {
            FaceManager.this.mHandler.obtainMessage(102, i, z ? 1 : 0, face).sendToTarget();
        }

        @Override // android.hardware.face.IFaceServiceReceiver
        public void onAuthenticationFailed(long j) {
            FaceManager.this.mHandler.obtainMessage(103).sendToTarget();
        }

        @Override // android.hardware.face.IFaceServiceReceiver
        public void onError(long j, int i, int i2) {
            FaceManager.this.mHandler.obtainMessage(104, i, i2, Long.valueOf(j)).sendToTarget();
        }

        @Override // android.hardware.face.IFaceServiceReceiver
        public void onRemoved(long j, int i, int i2) {
            FaceManager.this.mHandler.obtainMessage(105, i2, 0, new Face(null, i, j)).sendToTarget();
        }

        @Override // android.hardware.face.IFaceServiceReceiver
        public void onFeatureSet(boolean z, int i) {
            FaceManager.this.mHandler.obtainMessage(107, i, 0, Boolean.valueOf(z)).sendToTarget();
        }

        @Override // android.hardware.face.IFaceServiceReceiver
        public void onFeatureGet(boolean z, int i, boolean z2) {
            SomeArgs someArgsObtain = SomeArgs.obtain();
            someArgsObtain.arg1 = Boolean.valueOf(z);
            someArgsObtain.argi1 = i;
            someArgsObtain.arg2 = Boolean.valueOf(z2);
            FaceManager.this.mHandler.obtainMessage(106, someArgsObtain).sendToTarget();
        }
    };

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

    public static abstract class GetFeatureCallback {
        public abstract void onCompleted(boolean z, int i, boolean z2);
    }

    public static abstract class LockoutResetCallback {
        public void onLockoutReset() {
        }
    }

    public static abstract class RemovalCallback {
        public void onRemovalError(Face face, int i, CharSequence charSequence) {
        }

        public void onRemovalSucceeded(Face face, int i) {
        }
    }

    public static abstract class SetFeatureCallback {
        public abstract void onCompleted(boolean z, int i);
    }

    public static int getMappedAcquiredInfo(int i, int i2) {
        if (i == 22) {
            return i2 + 1000;
        }
        switch (i) {
        }
        return 2;
    }

    public FaceManager(Context context, IFaceService iFaceService) {
        this.mContext = context;
        this.mService = iFaceService;
        if (iFaceService == null) {
            Slog.v(TAG, "FaceAuthenticationManagerService was null");
        }
        this.mHandler = new MyHandler(context);
    }

    public void authenticate(CryptoObject cryptoObject, CancellationSignal cancellationSignal, int i, AuthenticationCallback authenticationCallback, Handler handler) {
        authenticate(cryptoObject, cancellationSignal, i, authenticationCallback, handler, this.mContext.getUserId());
    }

    private void useHandler(Handler handler) {
        if (handler != null) {
            this.mHandler = new MyHandler(handler.getLooper());
        } else if (this.mHandler.getLooper() != this.mContext.getMainLooper()) {
            this.mHandler = new MyHandler(this.mContext.getMainLooper());
        }
    }

    public void authenticate(CryptoObject cryptoObject, CancellationSignal cancellationSignal, int i, AuthenticationCallback authenticationCallback, Handler handler, int i2) {
        if (authenticationCallback == null) {
            throw new IllegalArgumentException("Must supply an authentication callback");
        }
        if (cancellationSignal != null) {
            if (cancellationSignal.isCanceled()) {
                Log.w(TAG, "authentication already canceled");
                return;
            }
            cancellationSignal.setOnCancelListener(new OnAuthenticationCancelListener(cryptoObject));
        }
        try {
            if (this.mService != null) {
                try {
                    useHandler(handler);
                    this.mAuthenticationCallback = authenticationCallback;
                    this.mCryptoObject = cryptoObject;
                    long opId = cryptoObject != null ? cryptoObject.getOpId() : 0L;
                    Trace.beginSection("FaceManager#authenticate");
                    this.mService.authenticate(this.mToken, opId, i2, this.mServiceReceiver, i, this.mContext.getOpPackageName());
                } catch (RemoteException e) {
                    Log.w(TAG, "Remote exception while authenticating: ", e);
                    if (authenticationCallback != null) {
                        authenticationCallback.onAuthenticationError(1, getErrorString(this.mContext, 1, 0));
                    }
                }
            }
        } finally {
            Trace.endSection();
        }
    }

    public void enroll(int i, byte[] bArr, CancellationSignal cancellationSignal, EnrollmentCallback enrollmentCallback, int[] iArr) {
        if (enrollmentCallback == null) {
            throw new IllegalArgumentException("Must supply an enrollment callback");
        }
        if (cancellationSignal != null) {
            if (cancellationSignal.isCanceled()) {
                Log.w(TAG, "enrollment already canceled");
                return;
            }
            cancellationSignal.setOnCancelListener(new OnEnrollCancelListener());
        }
        try {
            if (this.mService != null) {
                try {
                    this.mEnrollmentCallback = enrollmentCallback;
                    Trace.beginSection("FaceManager#enroll");
                    this.mService.enroll(i, this.mToken, bArr, this.mServiceReceiver, this.mContext.getOpPackageName(), iArr);
                } catch (RemoteException e) {
                    Log.w(TAG, "Remote exception in enroll: ", e);
                    if (enrollmentCallback != null) {
                        enrollmentCallback.onEnrollmentError(1, getErrorString(this.mContext, 1, 0));
                    }
                }
            }
        } finally {
            Trace.endSection();
        }
    }

    public long generateChallenge() {
        IFaceService iFaceService = this.mService;
        if (iFaceService == null) {
            return 0L;
        }
        try {
            return iFaceService.generateChallenge(this.mToken);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public int revokeChallenge() {
        IFaceService iFaceService = this.mService;
        if (iFaceService == null) {
            return 0;
        }
        try {
            return iFaceService.revokeChallenge(this.mToken);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void setFeature(int i, int i2, boolean z, byte[] bArr, SetFeatureCallback setFeatureCallback) {
        IFaceService iFaceService = this.mService;
        if (iFaceService != null) {
            try {
                this.mSetFeatureCallback = setFeatureCallback;
                iFaceService.setFeature(i, i2, z, bArr, this.mServiceReceiver, this.mContext.getOpPackageName());
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
    }

    public void getFeature(int i, int i2, GetFeatureCallback getFeatureCallback) {
        IFaceService iFaceService = this.mService;
        if (iFaceService != null) {
            try {
                this.mGetFeatureCallback = getFeatureCallback;
                iFaceService.getFeature(i, i2, this.mServiceReceiver, this.mContext.getOpPackageName());
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
    }

    public void userActivity() {
        IFaceService iFaceService = this.mService;
        if (iFaceService != null) {
            try {
                iFaceService.userActivity();
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
    }

    @Override // android.hardware.biometrics.BiometricAuthenticator
    public void setActiveUser(int i) {
        IFaceService iFaceService = this.mService;
        if (iFaceService != null) {
            try {
                iFaceService.setActiveUser(i);
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
    }

    public void remove(Face face, int i, RemovalCallback removalCallback) {
        IFaceService iFaceService = this.mService;
        if (iFaceService != null) {
            try {
                this.mRemovalCallback = removalCallback;
                this.mRemovalFace = face;
                iFaceService.remove(this.mToken, face.getBiometricId(), i, this.mServiceReceiver, this.mContext.getOpPackageName());
            } catch (RemoteException e) {
                Log.w(TAG, "Remote exception in remove: ", e);
                if (removalCallback != null) {
                    removalCallback.onRemovalError(face, 1, getErrorString(this.mContext, 1, 0));
                }
            }
        }
    }

    public List<Face> getEnrolledFaces(int i) {
        IFaceService iFaceService = this.mService;
        if (iFaceService == null) {
            return null;
        }
        try {
            return iFaceService.getEnrolledFaces(i, this.mContext.getOpPackageName());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public List<Face> getEnrolledFaces() {
        return getEnrolledFaces(UserHandle.myUserId());
    }

    @Override // android.hardware.biometrics.BiometricAuthenticator
    public boolean hasEnrolledTemplates() {
        IFaceService iFaceService = this.mService;
        if (iFaceService == null) {
            return false;
        }
        try {
            return iFaceService.hasEnrolledFaces(UserHandle.myUserId(), this.mContext.getOpPackageName());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @Override // android.hardware.biometrics.BiometricAuthenticator
    public boolean hasEnrolledTemplates(int i) {
        IFaceService iFaceService = this.mService;
        if (iFaceService == null) {
            return false;
        }
        try {
            return iFaceService.hasEnrolledFaces(i, this.mContext.getOpPackageName());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @Override // android.hardware.biometrics.BiometricAuthenticator
    public boolean isHardwareDetected() {
        IFaceService iFaceService = this.mService;
        if (iFaceService != null) {
            try {
                return iFaceService.isHardwareDetected(this.mContext.getOpPackageName());
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
        Log.w(TAG, "isFaceHardwareDetected(): Service not connected!");
        return false;
    }

    public void addLockoutResetCallback(LockoutResetCallback lockoutResetCallback) {
        if (this.mService != null) {
            try {
                this.mService.addLockoutResetCallback(new AnonymousClass2((PowerManager) this.mContext.getSystemService(PowerManager.class), lockoutResetCallback));
                return;
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
        Log.w(TAG, "addLockoutResetCallback(): Service not connected!");
    }

    /* JADX INFO: renamed from: android.hardware.face.FaceManager$2, reason: invalid class name */
    class AnonymousClass2 extends IBiometricServiceLockoutResetCallback.Stub {
        final /* synthetic */ LockoutResetCallback val$callback;
        final /* synthetic */ PowerManager val$powerManager;

        AnonymousClass2(PowerManager powerManager, LockoutResetCallback lockoutResetCallback) {
            this.val$powerManager = powerManager;
            this.val$callback = lockoutResetCallback;
        }

        @Override // android.hardware.biometrics.IBiometricServiceLockoutResetCallback
        public void onLockoutReset(long j, IRemoteCallback iRemoteCallback) throws RemoteException {
            try {
                final PowerManager.WakeLock wakeLockNewWakeLock = this.val$powerManager.newWakeLock(1, "faceLockoutResetCallback");
                wakeLockNewWakeLock.acquire();
                Handler handler = FaceManager.this.mHandler;
                final LockoutResetCallback lockoutResetCallback = this.val$callback;
                handler.post(new Runnable() { // from class: android.hardware.face.-$$Lambda$FaceManager$2$ZpnnD6uIIoOahRQEg03QpdIhdNs
                    @Override // java.lang.Runnable
                    public final void run() {
                        FaceManager.AnonymousClass2.lambda$onLockoutReset$0(lockoutResetCallback, wakeLockNewWakeLock);
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

    private int getCurrentUserId() {
        try {
            return ActivityManager.getService().getCurrentUser().id;
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cancelEnrollment() {
        IFaceService iFaceService = this.mService;
        if (iFaceService != null) {
            try {
                iFaceService.cancelEnrollment(this.mToken);
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cancelAuthentication(CryptoObject cryptoObject) {
        IFaceService iFaceService = this.mService;
        if (iFaceService != null) {
            try {
                iFaceService.cancelAuthentication(this.mToken, this.mContext.getOpPackageName());
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
    }

    public static String getErrorString(Context context, int i, int i2) {
        switch (i) {
            case 1:
                return context.getString(R.string.face_error_hw_not_available);
            case 2:
                return context.getString(R.string.face_error_unable_to_process);
            case 3:
                return context.getString(R.string.face_error_timeout);
            case 4:
                return context.getString(R.string.face_error_no_space);
            case 5:
                return context.getString(R.string.face_error_canceled);
            case 7:
                return context.getString(R.string.face_error_lockout);
            case 8:
                String[] stringArray = context.getResources().getStringArray(R.array.face_error_vendor);
                if (i2 < stringArray.length) {
                    return stringArray[i2];
                }
                break;
            case 9:
                return context.getString(R.string.face_error_lockout_permanent);
            case 10:
                return context.getString(R.string.face_error_user_canceled);
            case 11:
                return context.getString(R.string.face_error_not_enrolled);
            case 12:
                return context.getString(R.string.face_error_hw_not_present);
            case 15:
                return context.getString(R.string.face_error_security_update_required);
        }
        Slog.w(TAG, "Invalid error message: " + i + ", " + i2);
        return "";
    }

    public static String getAcquiredString(Context context, int i, int i2) {
        switch (i) {
            case 0:
                return null;
            case 1:
                return context.getString(R.string.face_acquired_insufficient);
            case 2:
                return context.getString(R.string.face_acquired_too_bright);
            case 3:
                return context.getString(R.string.face_acquired_too_dark);
            case 4:
                return context.getString(R.string.face_acquired_too_close);
            case 5:
                return context.getString(R.string.face_acquired_too_far);
            case 6:
                return context.getString(R.string.face_acquired_too_high);
            case 7:
                return context.getString(R.string.face_acquired_too_low);
            case 8:
                return context.getString(R.string.face_acquired_too_right);
            case 9:
                return context.getString(R.string.face_acquired_too_left);
            case 10:
                return context.getString(R.string.face_acquired_poor_gaze);
            case 11:
                return context.getString(R.string.face_acquired_not_detected);
            case 12:
                return context.getString(R.string.face_acquired_too_much_motion);
            case 13:
                return context.getString(R.string.face_acquired_recalibrate);
            case 14:
                return context.getString(R.string.face_acquired_too_different);
            case 15:
                return context.getString(R.string.face_acquired_too_similar);
            case 16:
                return context.getString(R.string.face_acquired_pan_too_extreme);
            case 17:
                return context.getString(R.string.face_acquired_tilt_too_extreme);
            case 18:
                return context.getString(R.string.face_acquired_roll_too_extreme);
            case 19:
                return context.getString(R.string.face_acquired_obscured);
            case 20:
                return null;
            case 21:
                return context.getString(R.string.face_acquired_sensor_dirty);
            case 22:
                String[] stringArray = context.getResources().getStringArray(R.array.face_acquired_vendor);
                if (i2 < stringArray.length) {
                    return stringArray[i2];
                }
                break;
        }
        Slog.w(TAG, "Invalid acquired message: " + i + ", " + i2);
        return null;
    }

    public static class AuthenticationResult {
        private CryptoObject mCryptoObject;
        private Face mFace;
        private boolean mIsStrongBiometric;
        private int mUserId;

        public AuthenticationResult(CryptoObject cryptoObject, Face face, int i, boolean z) {
            this.mCryptoObject = cryptoObject;
            this.mFace = face;
            this.mUserId = i;
            this.mIsStrongBiometric = z;
        }

        public CryptoObject getCryptoObject() {
            return this.mCryptoObject;
        }

        public Face getFace() {
            return this.mFace;
        }

        public int getUserId() {
            return this.mUserId;
        }

        public boolean isStrongBiometric() {
            return this.mIsStrongBiometric;
        }
    }

    private class OnEnrollCancelListener implements CancellationSignal.OnCancelListener {
        private OnEnrollCancelListener() {
        }

        @Override // android.os.CancellationSignal.OnCancelListener
        public void onCancel() {
            FaceManager.this.cancelEnrollment();
        }
    }

    private class OnAuthenticationCancelListener implements CancellationSignal.OnCancelListener {
        private CryptoObject mCrypto;

        OnAuthenticationCancelListener(CryptoObject cryptoObject) {
            this.mCrypto = cryptoObject;
        }

        @Override // android.os.CancellationSignal.OnCancelListener
        public void onCancel() {
            FaceManager.this.cancelAuthentication(this.mCrypto);
        }
    }

    private class MyHandler extends Handler {
        private MyHandler(Context context) {
            super(context.getMainLooper());
        }

        private MyHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            Trace.beginSection("FaceManager#handleMessage: " + Integer.toString(message.what));
            switch (message.what) {
                case 100:
                    FaceManager.this.sendEnrollResult((Face) message.obj, message.arg1);
                    break;
                case 101:
                    FaceManager.this.sendAcquiredResult(((Long) message.obj).longValue(), message.arg1, message.arg2);
                    break;
                case 102:
                    FaceManager.this.sendAuthenticatedSucceeded((Face) message.obj, message.arg1, message.arg2 == 1);
                    break;
                case 103:
                    FaceManager.this.sendAuthenticatedFailed();
                    break;
                case 104:
                    FaceManager.this.sendErrorResult(((Long) message.obj).longValue(), message.arg1, message.arg2);
                    break;
                case 105:
                    FaceManager.this.sendRemovedResult((Face) message.obj, message.arg1);
                    break;
                case 106:
                    SomeArgs someArgs = (SomeArgs) message.obj;
                    FaceManager.this.sendGetFeatureCompleted(((Boolean) someArgs.arg1).booleanValue(), someArgs.argi1, ((Boolean) someArgs.arg2).booleanValue());
                    someArgs.recycle();
                    break;
                case 107:
                    FaceManager.this.sendSetFeatureCompleted(((Boolean) message.obj).booleanValue(), message.arg1);
                    break;
                default:
                    Log.w(FaceManager.TAG, "Unknown message: " + message.what);
                    break;
            }
            Trace.endSection();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendSetFeatureCompleted(boolean z, int i) {
        SetFeatureCallback setFeatureCallback = this.mSetFeatureCallback;
        if (setFeatureCallback == null) {
            return;
        }
        setFeatureCallback.onCompleted(z, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendGetFeatureCompleted(boolean z, int i, boolean z2) {
        GetFeatureCallback getFeatureCallback = this.mGetFeatureCallback;
        if (getFeatureCallback == null) {
            return;
        }
        getFeatureCallback.onCompleted(z, i, z2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendRemovedResult(Face face, int i) {
        RemovalCallback removalCallback = this.mRemovalCallback;
        if (removalCallback == null) {
            return;
        }
        if (face == null) {
            Log.e(TAG, "Received MSG_REMOVED, but face is null");
        } else {
            removalCallback.onRemovalSucceeded(face, i);
        }
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
            removalCallback.onRemovalError(this.mRemovalFace, i3, getErrorString(this.mContext, i, i2));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendEnrollResult(Face face, int i) {
        EnrollmentCallback enrollmentCallback = this.mEnrollmentCallback;
        if (enrollmentCallback != null) {
            enrollmentCallback.onEnrollmentProgress(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendAuthenticatedSucceeded(Face face, int i, boolean z) {
        if (this.mAuthenticationCallback != null) {
            this.mAuthenticationCallback.onAuthenticationSucceeded(new AuthenticationResult(this.mCryptoObject, face, i, z));
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
        if (i == 22) {
            i = i2 + 1000;
        }
        EnrollmentCallback enrollmentCallback = this.mEnrollmentCallback;
        if (enrollmentCallback != null) {
            enrollmentCallback.onEnrollmentHelp(i, acquiredString);
            return;
        }
        AuthenticationCallback authenticationCallback2 = this.mAuthenticationCallback;
        if (authenticationCallback2 == null || acquiredString == null) {
            return;
        }
        authenticationCallback2.onAuthenticationHelp(i, acquiredString);
    }
}
