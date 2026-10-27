package cerence.ark.assistant.dm;

import cerence.ark.assistant.dm.data.CloudParamType;
import cerence.ark.assistant.dm.data.DmConfiguration;
import cerence.ark.assistant.dm.data.DmInitParams;
import cerence.ark.assistant.dm.data.DmPromptDataType;
import cerence.ark.assistant.dm.data.DmPrompterInstance;
import cerence.ark.assistant.dm.data.DmRecoveryModeParams;
import cerence.ark.assistant.dm.data.DmResultCode;
import cerence.ark.assistant.dm.data.FatalErrorCode;
import cerence.ark.assistant.dm.data.LocationType;
import cerence.ark.assistant.dm.data.NativeUpdateInfo;
import cerence.ark.assistant.dm.data.NativeUpdateResultCode;
import cerence.ark.assistant.dm.data.PrompterVoice;
import cerence.ark.assistant.dm.data.RecognitionErrorCode;
import cerence.ark.assistant.dm.data.SpeechAudioType;

/* JADX INFO: loaded from: classes2.dex */
public class NativeDialogManager {

    public interface DMCallback {
        void onAbortPrompt(String str, int i);

        boolean onAbortRequest(String str);

        void onEndOfStream(String str, int i);

        void onEvent(String str);

        void onFatalError(FatalErrorCode fatalErrorCode, String str);

        void onNotification(String str);

        void onPromptAudio(String str, byte[] bArr, int i);

        void onPromptError(String str, String str2, int i);

        void onPromptEvent(String str, String str2, int i);

        void onRequest(String str, String str2);

        void onSpeechAudio(SpeechAudioType speechAudioType, byte[] bArr);

        void onStartRecording();

        void onStopRecording(boolean z);

        void onVpaAudio(byte[] bArr, long j);
    }

    public interface RecognitionCallback {
        void onBeginningOfSpeech();

        void onEndOfSpeech();

        void onError(RecognitionErrorCode recognitionErrorCode);

        void onReadyForSpeech();

        void onRecognizedSpeech(String str, boolean z);

        void onRmsChanged(int i);
    }

    public interface UpdaterCallback {
        void onCheckUpdateFinished(boolean z, NativeUpdateInfo[] nativeUpdateInfoArr, NativeUpdateResultCode nativeUpdateResultCode);

        void onCheckUpdateStarted();

        void onConfirmed(String str, NativeUpdateResultCode nativeUpdateResultCode);

        void onDataDeleted(String str, NativeUpdateResultCode nativeUpdateResultCode);

        void onDownloadFinished(String str, NativeUpdateResultCode nativeUpdateResultCode);

        void onDownloadProgress(String str, long j, long j2, boolean z);

        void onDownloadStarted(String str);

        void onRecoveryFinished(String str, NativeUpdateResultCode nativeUpdateResultCode);

        void onRecoveryStarted();

        void onUpdateAborted(String str, NativeUpdateResultCode nativeUpdateResultCode);

        void onUpdateFinished(String str, NativeUpdateResultCode nativeUpdateResultCode);

        void onUpdateInstallable(NativeUpdateInfo nativeUpdateInfo, boolean z);
    }

    public static native DmResultCode abortRecovery();

    public static native DmResultCode enterRecoveryMode(DmRecoveryModeParams dmRecoveryModeParams, DmTokenProvider dmTokenProvider, UpdaterCallback updaterCallback);

    public static native DmResultCode exitRecoveryMode();

    public static native DmResultCode resumeRecovery();

    public static native DmResultCode stopRecovery();

    public native DmResultCode abortPrompt(String str);

    public native DmResultCode abortUpdate(String str);

    public native DmResultCode acceptDownload(String str);

    public native DmResultCode acceptInstall(String str);

    public native DmResultCode cancelListening();

    public native DmResultCode checkUpdate(String[] strArr, boolean z, boolean z2);

    public native DmResultCode clearUserData();

    public native DmResultCode configureLogConsumer(String str, String str2, String str3, String str4, String str5);

    public native DmResultCode deleteUpdate(String str, String[] strArr);

    public native DmResultCode destroy();

    public native DmResultCode destroyUpdater();

    public native void feedAudio(byte[] bArr, int i);

    public native String[] getAvailableAsrLanguages();

    public native DmPrompterInstance[] getAvailablePrompterInstances();

    public native String[] getAvailablePrompterLanguages();

    public native PrompterVoice[] getAvailablePrompterVoices(String str);

    public native String getCurrentVersion(String str);

    public native DmPrompterInstance[] getDialogPrompterInstances();

    public native NativeUpdateInfo[] getDownloadStatus(String[] strArr);

    public native String[] getLogConsumerNames();

    public native String getVersion();

    public native DmResultCode initUpdater(UpdaterCallback updaterCallback);

    public native DmResultCode initialize(DmInitParams dmInitParams, DmTokenProvider dmTokenProvider, DMCallback dMCallback);

    public native DmResultCode overrideConfiguration(String str, String str2);

    public native DmResultCode pausePrompt(String str);

    public native DmResultCode playPrompt(String str, DmPromptDataType dmPromptDataType, String str2, String str3, String str4, DmInitParams.AudioPlaybackMode audioPlaybackMode, boolean z, int[] iArr);

    public native DmResultCode prepareVoice(String str, String str2, String str3);

    public native DmResultCode rejectDownload(String str);

    public native DmResultCode rejectInstall(String str);

    public native DmResultCode requestTrimMemory();

    public native DmResultCode resumePrompt(String str);

    public native DmResultCode resumeUpdate(String str);

    public native DmResultCode sendAppEvent(String str);

    public native DmResultCode sendResponse(String str);

    public native DmResultCode setDynamicWords(String[] strArr);

    public native DmResultCode setPrompterParameter(String str, int i, int i2);

    public native DmResultCode setTtsVoice(String str);

    public native DmResultCode setWuw(String str);

    public native DmResultCode start(DmConfiguration dmConfiguration);

    public native DmResultCode startListening(RecognitionCallback recognitionCallback, String str);

    public native DmResultCode stop();

    public native DmResultCode stopListening();

    public native DmResultCode stopUpdate();

    public native DmResultCode updateDynamicContent(String str, String[] strArr);

    public native DmResultCode updateLocation(String str, String str2, String str3);

    public native DmResultCode updateSystemContext(CloudParamType cloudParamType, String str);

    public native DmResultCode updateUdeDataConfig(String str, String str2, String str3);

    public native DmResultCode updateUdeRppConfig(LocationType locationType, String str, String str2, String str3);

    static {
        System.loadLibrary("dialog_jni");
    }
}
