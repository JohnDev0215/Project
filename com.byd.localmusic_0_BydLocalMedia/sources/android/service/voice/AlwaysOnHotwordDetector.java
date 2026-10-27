package android.service.voice;

import android.content.Intent;
import android.hardware.soundtrigger.IRecognitionStatusCallback;
import android.hardware.soundtrigger.KeyphraseEnrollmentInfo;
import android.hardware.soundtrigger.KeyphraseMetadata;
import android.hardware.soundtrigger.SoundTrigger;
import android.media.AudioFormat;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.util.Slog;
import com.android.internal.app.IVoiceInteractionManagerService;
import java.io.PrintWriter;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Locale;

/* JADX INFO: loaded from: classes3.dex */
public class AlwaysOnHotwordDetector {
    public static final int AUDIO_CAPABILITY_ECHO_CANCELLATION = 1;
    public static final int AUDIO_CAPABILITY_NOISE_SUPPRESSION = 2;
    static final boolean DBG = false;
    public static final int MODEL_PARAM_THRESHOLD_FACTOR = 0;
    private static final int MSG_AVAILABILITY_CHANGED = 1;
    private static final int MSG_DETECTION_ERROR = 3;
    private static final int MSG_DETECTION_PAUSE = 4;
    private static final int MSG_DETECTION_RESUME = 5;
    private static final int MSG_HOTWORD_DETECTED = 2;
    public static final int RECOGNITION_FLAG_ALLOW_MULTIPLE_TRIGGERS = 2;
    public static final int RECOGNITION_FLAG_CAPTURE_TRIGGER_AUDIO = 1;
    public static final int RECOGNITION_FLAG_ENABLE_AUDIO_ECHO_CANCELLATION = 4;
    public static final int RECOGNITION_FLAG_ENABLE_AUDIO_NOISE_SUPPRESSION = 8;
    public static final int RECOGNITION_FLAG_NONE = 0;
    public static final int RECOGNITION_MODE_USER_IDENTIFICATION = 2;
    public static final int RECOGNITION_MODE_VOICE_TRIGGER = 1;
    public static final int STATE_HARDWARE_UNAVAILABLE = -2;
    private static final int STATE_INVALID = -3;
    public static final int STATE_KEYPHRASE_ENROLLED = 2;
    public static final int STATE_KEYPHRASE_UNENROLLED = 1;

    @Deprecated
    public static final int STATE_KEYPHRASE_UNSUPPORTED = -1;
    private static final int STATE_NOT_READY = 0;
    private static final int STATUS_ERROR = Integer.MIN_VALUE;
    private static final int STATUS_OK = 0;
    static final String TAG = "AlwaysOnHotwordDetector";
    private final Callback mExternalCallback;
    private final KeyphraseEnrollmentInfo mKeyphraseEnrollmentInfo;
    private KeyphraseMetadata mKeyphraseMetadata;
    private final Locale mLocale;
    private final IVoiceInteractionManagerService mModelManagementService;
    private final String mText;
    private final Object mLock = new Object();
    private int mAvailability = 0;
    private final Handler mHandler = new MyHandler();
    private final SoundTriggerListener mInternalCallback = new SoundTriggerListener(this.mHandler);

    @Retention(RetentionPolicy.SOURCE)
    public @interface AudioCapabilities {
    }

    public static abstract class Callback {
        public abstract void onAvailabilityChanged(int i);

        public abstract void onDetected(EventPayload eventPayload);

        public abstract void onError();

        public abstract void onRecognitionPaused();

        public abstract void onRecognitionResumed();
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ModelParams {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface RecognitionFlags {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface RecognitionModes {
    }

    public static final class ModelParamRange {
        private final SoundTrigger.ModelParamRange mModelParamRange;

        ModelParamRange(SoundTrigger.ModelParamRange modelParamRange) {
            this.mModelParamRange = modelParamRange;
        }

        public int getStart() {
            return this.mModelParamRange.getStart();
        }

        public int getEnd() {
            return this.mModelParamRange.getEnd();
        }

        public String toString() {
            return this.mModelParamRange.toString();
        }

        public boolean equals(Object obj) {
            return this.mModelParamRange.equals(obj);
        }

        public int hashCode() {
            return this.mModelParamRange.hashCode();
        }
    }

    public static class EventPayload {
        private final AudioFormat mAudioFormat;
        private final boolean mCaptureAvailable;
        private final int mCaptureSession;
        private final byte[] mData;
        private final boolean mTriggerAvailable;

        private EventPayload(boolean z, boolean z2, AudioFormat audioFormat, int i, byte[] bArr) {
            this.mTriggerAvailable = z;
            this.mCaptureAvailable = z2;
            this.mCaptureSession = i;
            this.mAudioFormat = audioFormat;
            this.mData = bArr;
        }

        public AudioFormat getCaptureAudioFormat() {
            return this.mAudioFormat;
        }

        public byte[] getTriggerAudio() {
            if (this.mTriggerAvailable) {
                return this.mData;
            }
            return null;
        }

        public Integer getCaptureSession() {
            if (this.mCaptureAvailable) {
                return Integer.valueOf(this.mCaptureSession);
            }
            return null;
        }
    }

    public AlwaysOnHotwordDetector(String str, Locale locale, Callback callback, KeyphraseEnrollmentInfo keyphraseEnrollmentInfo, IVoiceInteractionManagerService iVoiceInteractionManagerService) {
        this.mText = str;
        this.mLocale = locale;
        this.mKeyphraseEnrollmentInfo = keyphraseEnrollmentInfo;
        this.mExternalCallback = callback;
        this.mModelManagementService = iVoiceInteractionManagerService;
        new RefreshAvailabiltyTask().execute(new Void[0]);
    }

    public int getSupportedRecognitionModes() {
        int supportedRecognitionModesLocked;
        synchronized (this.mLock) {
            supportedRecognitionModesLocked = getSupportedRecognitionModesLocked();
        }
        return supportedRecognitionModesLocked;
    }

    private int getSupportedRecognitionModesLocked() {
        KeyphraseMetadata keyphraseMetadata;
        int i = this.mAvailability;
        if (i == -3) {
            throw new IllegalStateException("getSupportedRecognitionModes called on an invalid detector");
        }
        if (i != 2 || (keyphraseMetadata = this.mKeyphraseMetadata) == null) {
            throw new UnsupportedOperationException("Getting supported recognition modes for the keyphrase is not supported");
        }
        return keyphraseMetadata.getRecognitionModeFlags();
    }

    public int getSupportedAudioCapabilities() {
        int supportedAudioCapabilitiesLocked;
        synchronized (this.mLock) {
            supportedAudioCapabilitiesLocked = getSupportedAudioCapabilitiesLocked();
        }
        return supportedAudioCapabilitiesLocked;
    }

    private int getSupportedAudioCapabilitiesLocked() {
        try {
            SoundTrigger.ModuleProperties dspModuleProperties = this.mModelManagementService.getDspModuleProperties();
            if (dspModuleProperties != null) {
                return dspModuleProperties.getAudioCapabilities();
            }
            return 0;
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public boolean startRecognition(int i) {
        boolean z;
        synchronized (this.mLock) {
            if (this.mAvailability == -3) {
                throw new IllegalStateException("startRecognition called on an invalid detector");
            }
            if (this.mAvailability != 2) {
                throw new UnsupportedOperationException("Recognition for the given keyphrase is not supported");
            }
            z = startRecognitionLocked(i) == 0;
        }
        return z;
    }

    public boolean stopRecognition() {
        boolean z;
        synchronized (this.mLock) {
            if (this.mAvailability == -3) {
                throw new IllegalStateException("stopRecognition called on an invalid detector");
            }
            if (this.mAvailability != 2) {
                throw new UnsupportedOperationException("Recognition for the given keyphrase is not supported");
            }
            z = stopRecognitionLocked() == 0;
        }
        return z;
    }

    public int setParameter(int i, int i2) {
        int parameterLocked;
        synchronized (this.mLock) {
            if (this.mAvailability == -3) {
                throw new IllegalStateException("setParameter called on an invalid detector");
            }
            parameterLocked = setParameterLocked(i, i2);
        }
        return parameterLocked;
    }

    public int getParameter(int i) {
        int parameterLocked;
        synchronized (this.mLock) {
            if (this.mAvailability == -3) {
                throw new IllegalStateException("getParameter called on an invalid detector");
            }
            parameterLocked = getParameterLocked(i);
        }
        return parameterLocked;
    }

    public ModelParamRange queryParameter(int i) {
        ModelParamRange modelParamRangeQueryParameterLocked;
        synchronized (this.mLock) {
            if (this.mAvailability == -3) {
                throw new IllegalStateException("queryParameter called on an invalid detector");
            }
            modelParamRangeQueryParameterLocked = queryParameterLocked(i);
        }
        return modelParamRangeQueryParameterLocked;
    }

    public Intent createEnrollIntent() {
        Intent manageIntentLocked;
        synchronized (this.mLock) {
            manageIntentLocked = getManageIntentLocked(0);
        }
        return manageIntentLocked;
    }

    public Intent createUnEnrollIntent() {
        Intent manageIntentLocked;
        synchronized (this.mLock) {
            manageIntentLocked = getManageIntentLocked(2);
        }
        return manageIntentLocked;
    }

    public Intent createReEnrollIntent() {
        Intent manageIntentLocked;
        synchronized (this.mLock) {
            manageIntentLocked = getManageIntentLocked(1);
        }
        return manageIntentLocked;
    }

    private Intent getManageIntentLocked(int i) {
        int i2 = this.mAvailability;
        if (i2 == -3) {
            throw new IllegalStateException("getManageIntent called on an invalid detector");
        }
        if (i2 != 2 && i2 != 1) {
            throw new UnsupportedOperationException("Managing the given keyphrase is not supported");
        }
        return this.mKeyphraseEnrollmentInfo.getManageKeyphraseIntent(i, this.mText, this.mLocale);
    }

    void invalidate() {
        synchronized (this.mLock) {
            this.mAvailability = -3;
            notifyStateChangedLocked();
        }
    }

    void onSoundModelsChanged() {
        synchronized (this.mLock) {
            if (this.mAvailability != -3 && this.mAvailability != -2) {
                if (this.mAvailability == 2) {
                    stopRecognitionLocked();
                }
                new RefreshAvailabiltyTask().execute(new Void[0]);
                return;
            }
            Slog.w(TAG, "Received onSoundModelsChanged for an unsupported keyphrase/config");
        }
    }

    private int startRecognitionLocked(int i) {
        SoundTrigger.KeyphraseRecognitionExtra[] keyphraseRecognitionExtraArr = {new SoundTrigger.KeyphraseRecognitionExtra(this.mKeyphraseMetadata.getId(), this.mKeyphraseMetadata.getRecognitionModeFlags(), 0, new SoundTrigger.ConfidenceLevel[0])};
        boolean z = (i & 1) != 0;
        boolean z2 = (i & 2) != 0;
        int i2 = (i & 4) == 0 ? 0 : 1;
        try {
            int iStartRecognition = this.mModelManagementService.startRecognition(this.mKeyphraseMetadata.getId(), this.mLocale.toLanguageTag(), this.mInternalCallback, new SoundTrigger.RecognitionConfig(z, z2, keyphraseRecognitionExtraArr, null, (i & 8) != 0 ? i2 | 2 : i2));
            if (iStartRecognition != 0) {
                Slog.w(TAG, "startRecognition() failed with error code " + iStartRecognition);
            }
            return iStartRecognition;
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    private int stopRecognitionLocked() {
        try {
            int iStopRecognition = this.mModelManagementService.stopRecognition(this.mKeyphraseMetadata.getId(), this.mInternalCallback);
            if (iStopRecognition != 0) {
                Slog.w(TAG, "stopRecognition() failed with error code " + iStopRecognition);
            }
            return iStopRecognition;
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    private int setParameterLocked(int i, int i2) {
        try {
            int parameter = this.mModelManagementService.setParameter(this.mKeyphraseMetadata.getId(), i, i2);
            if (parameter != 0) {
                Slog.w(TAG, "setParameter failed with error code " + parameter);
            }
            return parameter;
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    private int getParameterLocked(int i) {
        try {
            return this.mModelManagementService.getParameter(this.mKeyphraseMetadata.getId(), i);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    private ModelParamRange queryParameterLocked(int i) {
        try {
            SoundTrigger.ModelParamRange modelParamRangeQueryParameter = this.mModelManagementService.queryParameter(this.mKeyphraseMetadata.getId(), i);
            if (modelParamRangeQueryParameter == null) {
                return null;
            }
            return new ModelParamRange(modelParamRangeQueryParameter);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyStateChangedLocked() {
        Message messageObtain = Message.obtain(this.mHandler, 1);
        messageObtain.arg1 = this.mAvailability;
        messageObtain.sendToTarget();
    }

    static final class SoundTriggerListener extends IRecognitionStatusCallback.Stub {
        private final Handler mHandler;

        public SoundTriggerListener(Handler handler) {
            this.mHandler = handler;
        }

        @Override // android.hardware.soundtrigger.IRecognitionStatusCallback
        public void onKeyphraseDetected(SoundTrigger.KeyphraseRecognitionEvent keyphraseRecognitionEvent) {
            Slog.i(AlwaysOnHotwordDetector.TAG, "onDetected");
            Message.obtain(this.mHandler, 2, new EventPayload(keyphraseRecognitionEvent.triggerInData, keyphraseRecognitionEvent.captureAvailable, keyphraseRecognitionEvent.captureFormat, keyphraseRecognitionEvent.captureSession, keyphraseRecognitionEvent.data)).sendToTarget();
        }

        @Override // android.hardware.soundtrigger.IRecognitionStatusCallback
        public void onGenericSoundTriggerDetected(SoundTrigger.GenericRecognitionEvent genericRecognitionEvent) {
            Slog.w(AlwaysOnHotwordDetector.TAG, "Generic sound trigger event detected at AOHD: " + genericRecognitionEvent);
        }

        @Override // android.hardware.soundtrigger.IRecognitionStatusCallback
        public void onError(int i) {
            Slog.i(AlwaysOnHotwordDetector.TAG, "onError: " + i);
            this.mHandler.sendEmptyMessage(3);
        }

        @Override // android.hardware.soundtrigger.IRecognitionStatusCallback
        public void onRecognitionPaused() {
            Slog.i(AlwaysOnHotwordDetector.TAG, "onRecognitionPaused");
            this.mHandler.sendEmptyMessage(4);
        }

        @Override // android.hardware.soundtrigger.IRecognitionStatusCallback
        public void onRecognitionResumed() {
            Slog.i(AlwaysOnHotwordDetector.TAG, "onRecognitionResumed");
            this.mHandler.sendEmptyMessage(5);
        }
    }

    class MyHandler extends Handler {
        MyHandler() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            synchronized (AlwaysOnHotwordDetector.this.mLock) {
                if (AlwaysOnHotwordDetector.this.mAvailability == -3) {
                    Slog.w(AlwaysOnHotwordDetector.TAG, "Received message: " + message.what + " for an invalid detector");
                    return;
                }
                int i = message.what;
                if (i == 1) {
                    AlwaysOnHotwordDetector.this.mExternalCallback.onAvailabilityChanged(message.arg1);
                    return;
                }
                if (i == 2) {
                    AlwaysOnHotwordDetector.this.mExternalCallback.onDetected((EventPayload) message.obj);
                    return;
                }
                if (i == 3) {
                    AlwaysOnHotwordDetector.this.mExternalCallback.onError();
                    return;
                }
                if (i == 4) {
                    AlwaysOnHotwordDetector.this.mExternalCallback.onRecognitionPaused();
                } else if (i == 5) {
                    AlwaysOnHotwordDetector.this.mExternalCallback.onRecognitionResumed();
                } else {
                    super.handleMessage(message);
                }
            }
        }
    }

    class RefreshAvailabiltyTask extends AsyncTask<Void, Void, Void> {
        RefreshAvailabiltyTask() {
        }

        @Override // android.os.AsyncTask
        public Void doInBackground(Void... voidArr) {
            int iInternalGetInitialAvailability = internalGetInitialAvailability();
            synchronized (AlwaysOnHotwordDetector.this.mLock) {
                if (iInternalGetInitialAvailability != 0) {
                    AlwaysOnHotwordDetector.this.mAvailability = iInternalGetInitialAvailability;
                    AlwaysOnHotwordDetector.this.notifyStateChangedLocked();
                } else {
                    internalUpdateEnrolledKeyphraseMetadata();
                    iInternalGetInitialAvailability = AlwaysOnHotwordDetector.this.mKeyphraseMetadata != null ? 2 : 1;
                    AlwaysOnHotwordDetector.this.mAvailability = iInternalGetInitialAvailability;
                    AlwaysOnHotwordDetector.this.notifyStateChangedLocked();
                }
            }
            return null;
        }

        private int internalGetInitialAvailability() {
            synchronized (AlwaysOnHotwordDetector.this.mLock) {
                if (AlwaysOnHotwordDetector.this.mAvailability == -3) {
                    return -3;
                }
                try {
                    return AlwaysOnHotwordDetector.this.mModelManagementService.getDspModuleProperties() == null ? -2 : 0;
                } catch (RemoteException e) {
                    throw e.rethrowFromSystemServer();
                }
            }
        }

        private void internalUpdateEnrolledKeyphraseMetadata() {
            try {
                AlwaysOnHotwordDetector.this.mKeyphraseMetadata = AlwaysOnHotwordDetector.this.mModelManagementService.getEnrolledKeyphraseMetadata(AlwaysOnHotwordDetector.this.mText, AlwaysOnHotwordDetector.this.mLocale.toLanguageTag());
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
    }

    public void dump(String str, PrintWriter printWriter) {
        synchronized (this.mLock) {
            printWriter.print(str);
            printWriter.print("Text=");
            printWriter.println(this.mText);
            printWriter.print(str);
            printWriter.print("Locale=");
            printWriter.println(this.mLocale);
            printWriter.print(str);
            printWriter.print("Availability=");
            printWriter.println(this.mAvailability);
            printWriter.print(str);
            printWriter.print("KeyphraseMetadata=");
            printWriter.println(this.mKeyphraseMetadata);
            printWriter.print(str);
            printWriter.print("EnrollmentInfo=");
            printWriter.println(this.mKeyphraseEnrollmentInfo);
        }
    }
}
