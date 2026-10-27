package cerence.ark.assistant.dm;

import cerence.ark.assistant.dm.data.DmConfiguration;
import cerence.ark.assistant.dm.data.DmInitParams;
import cerence.ark.assistant.dm.data.DmResultCode;
import cerence.ark.assistant.dm.data.FatalErrorCode;
import cerence.ark.assistant.dm.data.PrompterVoice;
import cerence.ark.assistant.dm.data.SpeechAudioType;

/* JADX INFO: loaded from: classes.dex */
public class NativeDialogManager {

    public interface DMCallback {
        void onAbortPrompt(String str, int i);

        boolean onAbortRequest(String str);

        void onActivateDeviceFailed(String str);

        void onActivateDeviceSuccess(String str);

        void onEndOfStream(String str, int i);

        void onEnhancedSiriAudio(byte[] bArr, long j);

        void onFatalError(FatalErrorCode fatalErrorCode, String str);

        void onNotification(String str);

        void onPromptAudio(String str, byte[] bArr, int i);

        void onPromptError(String str, String str2, int i);

        void onPromptEvent(String str, String str2, int i);

        void onRequest(String str);

        void onSpeechAudio(SpeechAudioType speechAudioType, byte[] bArr);

        void onStartRecording();

        void onStopRecording(boolean z);
    }

    public static native String[] getAvailableAsrLanguages(String str);

    public static native String[] getAvailablePromptLanguages(String str);

    public static native PrompterVoice[] getAvailablePromptVoices(String str, String str2);

    public static native String getVersion(String str);

    public static native DmResultCode setCloudServer(int i);

    public native DmResultCode abortPrompt(String str);

    public native DmResultCode activateDevice(String str, int[] iArr);

    public native DmResultCode deactivateDevice(String str, int[] iArr);

    public native DmResultCode destroy();

    public native void feedAudio(byte[] bArr, int i);

    public native String[] getAvailablePrompterNames();

    public native String getDialogPrompterName();

    public native String getWuwInfo();

    public native DmResultCode initialize(DmInitParams dmInitParams, DMCallback dMCallback);

    public native DmResultCode overrideConfiguration(String str, String str2);

    public native DmResultCode pausePrompt(String str);

    public native DmResultCode playPrompt(String str, String str2, String str3, String str4, boolean z, int[] iArr);

    public native DmResultCode queryDeviceStatus(String str, int[] iArr);

    public native DmResultCode requestTrimMemory();

    public native DmResultCode resumePrompt(String str);

    public native DmResultCode sendAppEvent(String str);

    public native DmResultCode sendResponse(String str);

    public native DmResultCode setDataRetentionEnabled(boolean z);

    public native DmResultCode setTtsVoiceId(String str);

    public native DmResultCode setWuw(String str);

    public native DmResultCode start(DmConfiguration dmConfiguration);

    public native DmResultCode stop();

    public native DmResultCode updateDynamicContent(String str, String[] strArr);

    public native DmResultCode updateShortcut(String[] strArr);

    public native DmResultCode updateSystemContext(int i, String str);

    static {
        System.loadLibrary("dialog_jni");
    }
}
