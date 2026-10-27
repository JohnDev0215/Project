package cerence.ark.assistant.framework.impl;

import android.media.AudioAttributes;
import android.text.TextUtils;
import cerence.ark.assistant.common.Logger;
import cerence.ark.assistant.common.callback.IResultCallback;
import cerence.ark.assistant.common.callback.Result;
import cerence.ark.assistant.dm.NativeDialogManager;
import cerence.ark.assistant.dm.data.DmConfiguration;
import cerence.ark.assistant.dm.data.DmInitParams;
import cerence.ark.assistant.dm.data.DmResultCode;
import cerence.ark.assistant.dm.data.FatalErrorCode;
import cerence.ark.assistant.dm.data.PrompterVoice;
import cerence.ark.assistant.dm.data.SpeechAudioType;
import cerence.ark.assistant.framework.AssistantState;
import cerence.ark.assistant.framework.ErrorListener;
import cerence.ark.assistant.framework.Framework;
import cerence.ark.assistant.framework.FrameworkMessageListener;
import cerence.ark.assistant.framework.audio.AudioChannel;
import cerence.ark.assistant.framework.audio.AudioFormat;
import cerence.ark.assistant.framework.audio.SampleRate;
import cerence.ark.assistant.framework.audio.player.AudioPlayerProvider;
import cerence.ark.assistant.framework.audio.player.IAudioPlayer;
import cerence.ark.assistant.framework.audio.recorder.AudioRecorderProvider;
import cerence.ark.assistant.framework.audio.recorder.IAudioRecorder;
import cerence.ark.assistant.framework.data.AssistantConfiguration;
import cerence.ark.assistant.framework.data.AssistantContextType;
import cerence.ark.assistant.framework.data.AudioPlaybackMode;
import cerence.ark.assistant.framework.data.AudioRecordingMode;
import cerence.ark.assistant.framework.data.Config;
import cerence.ark.assistant.framework.data.ErrorInfo;
import cerence.ark.assistant.framework.data.wuw.WuwConfig;
import cerence.ark.assistant.framework.data.wuw.WuwInfo;
import cerence.ark.assistant.framework.impl.AudioLogConfig;
import cerence.ark.assistant.framework.impl.player.DefaultAudioPlayerProvider;
import cerence.ark.assistant.framework.impl.prompter.PrompterImpl;
import cerence.ark.assistant.framework.impl.recorder.AudioRecorderManager;
import cerence.ark.assistant.framework.impl.recorder.DefaultAudioRecorderProvider;
import cerence.ark.assistant.framework.impl.recorder.IAudioListener;
import cerence.ark.assistant.framework.prompter.IPrompter;
import cerence.ark.assistant.framework.prompter.PrompterError;
import cerence.ark.assistant.framework.siri.EnhancedSiriAudioListener;
import cerence.ark.assistant.framework.siri.EnhancedSiriAudioListenerProvider;
import cerence.ark.assistant.framework.speech.SpeechAudioListener;
import cerence.ark.assistant.framework.speech.SpeechAudioListenerProvider;
import cerence.ark.assistant.library.ErrorMessage;
import cerence.ark.assistant.library.JsonKey;
import cerence.ark.assistant.library.Utils;
import cerence.ark.assistant.library.exception.ArkAssistantException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class ArkFramework implements Framework, NativeDialogManager.DMCallback {
    private static final String ENHANCED_SIRI_AUDIO_FILE_NAME = "enhanced_siri";
    private static final int PROMPTER_AUDIO_CHUNK_SIZE = 1024;
    private static final String PROMPTER_ERROR = "prompt_error";
    private static final String PROMPT_EVENT = "prompt_event";
    private static final String TAG = "ArkFramework";
    private AssistantConfiguration mAssistantConfiguration;
    private IAudioPlayer mAudioPlayer;
    private AudioRecorderManager mAudioRecorderManager;
    private String mDefaultVoiceId;
    private String mDialogPromptAudioFolderName;
    private String mDialogPrompterName;
    private EnhancedSiriAudioListener mEnhancedSiriAudioListener;
    private ErrorListener mErrorListener;
    private Config mInitConfig;
    private FrameworkMessageListener mMessageListener;
    private String mMicAudioFolderName;
    private PrompterVoice[] mPrompterVoices;
    private SpeechAudioListener mSpeechAudioListener;
    private final AudioLogger mAudioLogger = new AudioLogger();
    private final Object mRecorderLock = new Object();
    private final Object mPlayerLock = new Object();
    private final Map<String, PrompterImpl> mPrompterMap = new ConcurrentHashMap(16);
    private volatile boolean mIsAudioRecordingStarted = false;
    private final ConcurrentHashMap<Integer, IResultCallback<String>> mCallbackMap = new ConcurrentHashMap<>();
    private AssistantState mCurrentState = AssistantState.UNINITIALIZED;
    private final NativeDialogManager mNativeDialogManager = new NativeDialogManager();

    @Override // cerence.ark.assistant.framework.Framework
    public void initialize(Config config, FrameworkMessageListener frameworkMessageListener, IResultCallback<Void> iResultCallback) {
        Objects.requireNonNull(config, "Config must not be null");
        String str = TAG;
        Logger.info(str, "initialize: " + config);
        PrompterVoice[] availablePromptVoices = NativeDialogManager.getAvailablePromptVoices(config.getDataPath(), null);
        if (availablePromptVoices == null || availablePromptVoices.length == 0) {
            Utils.notifyFailure(str, "initialize", "get available prompter voices is empty", iResultCallback);
            return;
        }
        DmInitParams.Builder builder = new DmInitParams.Builder();
        builder.setDataPath(config.getDataPath()).setWritablePath(config.getWritablePath()).setLanguage(config.getAsrLanguage()).setDeviceId(config.getDeviceId()).setUserId(config.getUserId()).setAudioRecordingMode(FrameworkUtils.getDmAudioRecordingMode(config.getAudioRecordingMode())).setAudioPlaybackMode(FrameworkUtils.getDmAudioPlaybackMode(config.getAudioPlaybackMode())).setDeviceModel(config.getDeviceModel());
        DmResultCode dmResultCodeInitialize = this.mNativeDialogManager.initialize(builder.build(), this);
        if (!isSuccess(dmResultCodeInitialize)) {
            Utils.notifyFailure(str, "initialize", dmResultCodeInitialize.name(), iResultCallback);
            return;
        }
        this.mInitConfig = config;
        this.mPrompterVoices = availablePromptVoices;
        this.mMessageListener = frameworkMessageListener;
        String asrLanguage = config.getAsrLanguage();
        if (!TextUtils.isEmpty(asrLanguage) && asrLanguage.contains("@")) {
            asrLanguage = asrLanguage.split("@")[0].trim();
        }
        this.mDefaultVoiceId = FrameworkUtils.getDefaultVoiceId(asrLanguage, availablePromptVoices);
        this.mAudioLogger.initialize(new AudioLogConfig.Builder().setAudioDir(Utils.getAudioLogDir(config.getWritablePath())).setSaveInputAudio(config.isSaveInputAudio()).setSaveOutputAudio(config.isSaveOutputAudio()).setMaximumSize(config.getMaxAudioLogSize()).build(), 2560, 1024);
        updateAssistantState(AssistantState.INITIALIZED);
        Utils.notifySuccess(iResultCallback);
    }

    @Override // cerence.ark.assistant.framework.Framework
    public void startVoiceAssistant(AssistantConfiguration assistantConfiguration, IResultCallback<Void> iResultCallback) {
        if (assistantConfiguration == null) {
            Utils.notifyFailure(TAG, "startVoiceAssistant", "assistant configuration is null", iResultCallback);
            return;
        }
        Config config = this.mInitConfig;
        if (config == null) {
            Utils.notifyFailure(TAG, "startVoiceAssistant", "init config is null", iResultCallback);
            return;
        }
        String str = TAG;
        Logger.info(str, "startVoiceAssistant: " + assistantConfiguration);
        String ttsVoiceId = assistantConfiguration.getTtsVoiceId();
        if (TextUtils.isEmpty(ttsVoiceId)) {
            ttsVoiceId = this.mDefaultVoiceId;
            if (TextUtils.isEmpty(ttsVoiceId)) {
                Utils.notifyFailure(str, "startVoiceAssistant", "tts voice id not found according to asr language.", iResultCallback);
                return;
            }
        } else if (!FrameworkUtils.checkTtsVoiceIdValid(ttsVoiceId, this.mPrompterVoices)) {
            Utils.notifyFailure(str, "startVoiceAssistant", "tts voice id is invalid", iResultCallback);
            return;
        }
        if (!FrameworkUtils.isPlaybackModePal(config.getAudioPlaybackMode())) {
            String dialogPrompterName = this.mNativeDialogManager.getDialogPrompterName();
            this.mDialogPrompterName = dialogPrompterName;
            Logger.debug(str, String.format("getDialogPromptInstance: %s", dialogPrompterName));
            if (TextUtils.isEmpty(this.mDialogPrompterName)) {
                Utils.notifyFailure(str, "startVoiceAssistant", "dialog prompter name is empty", iResultCallback);
                return;
            } else if (!initializeAudioPlayer(config, assistantConfiguration)) {
                Utils.notifyFailure(str, "startVoiceAssistant", "audio player initialize fail", iResultCallback);
                return;
            }
        }
        if (config.getAudioRecordingMode() == AudioRecordingMode.SDK && !Utils.checkSelfPermission("android.permission.RECORD_AUDIO")) {
            Utils.notifyFailure(str, "startVoiceAssistant", "the android.permission.RECORD_AUDIO was denied.", iResultCallback);
            return;
        }
        this.mAssistantConfiguration = assistantConfiguration;
        createSpeechAudioListener();
        createEnhancedSiriAudioListener();
        DmConfiguration.Builder builder = new DmConfiguration.Builder();
        builder.enableBargeIn(assistantConfiguration.isEnableBargeIn()).snoozingDuration(assistantConfiguration.getSnoozingDuration()).listeningTimeout(assistantConfiguration.getListeningTimeout()).interactionMode(FrameworkUtils.getDmInteractionMode(assistantConfiguration.getInteractionMode())).setTtsVoiceId(ttsVoiceId).extra(assistantConfiguration.getExtra()).isReceiveSpeechAudio(this.mSpeechAudioListener != null).setWakeupWords(FrameworkUtils.getCustomWakeupWords(assistantConfiguration.getWakeupWords())).setWuw(assistantConfiguration.getWuwConfig() == null ? null : assistantConfiguration.getWuwConfig().toJsonString()).enableDataRetention(assistantConfiguration.isUserDataRetentionEnabled()).enableSwys(assistantConfiguration.isEnableSwys());
        DmResultCode dmResultCodeStart = this.mNativeDialogManager.start(builder.build());
        Logger.debug(str, String.format("startVoiceAssistant: result = %s, ttsVoiceId = %s", dmResultCodeStart.name(), ttsVoiceId));
        if (isSuccess(dmResultCodeStart)) {
            updateAssistantState(AssistantState.RUNNING);
            Utils.notifySuccess(iResultCallback);
        } else {
            destroyAudioPlayer();
            Utils.notifyFailure(str, "startVoiceAssistant", dmResultCodeStart.name(), iResultCallback);
        }
    }

    @Override // cerence.ark.assistant.framework.Framework
    public void stopVoiceAssistant(IResultCallback<Void> iResultCallback) {
        String str = TAG;
        Logger.debug(str, "stopVoiceAssistant");
        destroyAudioPlayer();
        DmResultCode dmResultCodeStop = this.mNativeDialogManager.stop();
        Logger.debug(str, "stopVoiceAssistant: " + dmResultCodeStop.name());
        this.mSpeechAudioListener = null;
        this.mEnhancedSiriAudioListener = null;
        destroyAudioRecorder();
        this.mAudioLogger.closeMicFileStream();
        this.mAudioLogger.closeSpeechFileStream();
        this.mAudioLogger.closeSiriFileStream();
        if (isSuccess(dmResultCodeStop)) {
            updateAssistantState(AssistantState.INITIALIZED);
            Utils.notifySuccess(iResultCallback);
        } else {
            Utils.notifyFailure(str, "stopVoiceAssistant", dmResultCodeStop.name(), iResultCallback);
        }
    }

    @Override // cerence.ark.assistant.framework.Framework
    public void destroy(IResultCallback<Void> iResultCallback) {
        PrompterImpl prompterImpl;
        Logger.debug(TAG, "destroy: begin");
        for (String str : this.mPrompterMap.keySet()) {
            if (!TextUtils.isEmpty(str) && (prompterImpl = this.mPrompterMap.get(str)) != null) {
                prompterImpl.destroy();
            }
        }
        DmResultCode dmResultCodeDestroy = this.mNativeDialogManager.destroy();
        String str2 = TAG;
        Logger.debug(str2, String.format("destroy end: %s", dmResultCodeDestroy.name()));
        this.mCallbackMap.clear();
        this.mPrompterMap.clear();
        destroyAudioPlayer();
        destroyAudioRecorder();
        this.mMessageListener = null;
        this.mErrorListener = null;
        this.mSpeechAudioListener = null;
        this.mEnhancedSiriAudioListener = null;
        this.mInitConfig = null;
        this.mDefaultVoiceId = null;
        this.mAudioLogger.destroy();
        Logger.debug(str2, "destroy finish");
        updateAssistantState(AssistantState.UNINITIALIZED);
        Utils.notifySuccess(iResultCallback);
    }

    @Override // cerence.ark.assistant.framework.Framework
    public void sendResponse(String str, IResultCallback<Void> iResultCallback) {
        if (TextUtils.isEmpty(str)) {
            Utils.notifyFailure(TAG, "sendResponse", "response jsonData is null", iResultCallback);
            return;
        }
        DmResultCode dmResultCodeSendResponse = this.mNativeDialogManager.sendResponse(str);
        String str2 = TAG;
        Logger.debug(str2, String.format("%s: %s", "sendResponse", dmResultCodeSendResponse.name()));
        if (isSuccess(dmResultCodeSendResponse)) {
            Utils.notifySuccess(iResultCallback);
        } else {
            Utils.notifyFailure(str2, "sendResponse", dmResultCodeSendResponse.name(), iResultCallback);
        }
    }

    @Override // cerence.ark.assistant.framework.Framework
    public void sendAppEvent(String str, IResultCallback<Void> iResultCallback) {
        DmResultCode dmResultCodeSendAppEvent = this.mNativeDialogManager.sendAppEvent(str);
        String str2 = TAG;
        Logger.debug(str2, String.format("%s: %s", "sendAppEvent", dmResultCodeSendAppEvent.name()));
        if (isSuccess(dmResultCodeSendAppEvent)) {
            Utils.notifySuccess(iResultCallback);
        } else {
            Utils.notifyFailure(str2, "sendAppEvent", dmResultCodeSendAppEvent.name(), iResultCallback);
        }
    }

    @Override // cerence.ark.assistant.framework.Framework
    public boolean setTtsVoiceId(String str) {
        if (!FrameworkUtils.checkTtsVoiceIdValid(str, this.mPrompterVoices)) {
            Logger.error(TAG, "setTtsVoiceId: ttsVoiceId is invalid, ttsVoiceId = " + str);
            return false;
        }
        DmResultCode ttsVoiceId = this.mNativeDialogManager.setTtsVoiceId(str);
        Logger.debug(TAG, String.format("setTtsVoiceId: %s, ttsVoiceId = %s", ttsVoiceId.name(), str));
        return isSuccess(ttsVoiceId);
    }

    @Override // cerence.ark.assistant.framework.Framework
    public List<String> getAvailablePrompterNames() {
        ArrayList arrayList = new ArrayList();
        String[] availablePrompterNames = this.mNativeDialogManager.getAvailablePrompterNames();
        if (availablePrompterNames == null || availablePrompterNames.length == 0) {
            Logger.error(TAG, "getAvailablePrompterNames: prompter name list is empty");
            return arrayList;
        }
        arrayList.addAll(Arrays.asList(availablePrompterNames));
        return arrayList;
    }

    @Override // cerence.ark.assistant.framework.Framework
    public IPrompter createPrompter(String str, AudioAttributes audioAttributes) {
        Config config = this.mInitConfig;
        if (config == null) {
            Logger.error(TAG, "createPrompter: init config is null, prompterName = " + str);
            return null;
        }
        PrompterImpl prompterImpl = this.mPrompterMap.get(str);
        if (prompterImpl != null) {
            Logger.warn(TAG, "The prompter already exist: prompterName = " + str);
            return prompterImpl;
        }
        if (!getAvailablePrompterNames().contains(str)) {
            Logger.error(TAG, "createPrompter: prompterName is unavailable, prompterName = " + str);
            return null;
        }
        PrompterImpl prompterImpl2 = new PrompterImpl(str, config.getAudioPlaybackMode(), this.mNativeDialogManager, this.mPrompterVoices, this.mDefaultVoiceId);
        if (!prompterImpl2.initialize(audioAttributes)) {
            return null;
        }
        this.mPrompterMap.put(str, prompterImpl2);
        return prompterImpl2;
    }

    @Override // cerence.ark.assistant.framework.Framework
    public void destroyPrompter(IPrompter iPrompter) {
        if (!(iPrompter instanceof PrompterImpl)) {
            Logger.warn(TAG, "destroyPrompter: prompter is invalid");
            return;
        }
        PrompterImpl prompterImpl = (PrompterImpl) iPrompter;
        this.mPrompterMap.remove(prompterImpl.getPrompterName());
        prompterImpl.destroy();
    }

    @Override // cerence.ark.assistant.framework.Framework
    public void updateDynamicContent(String str, List<String> list) {
        if (list == null || list.isEmpty()) {
            Logger.error(TAG, String.format("updateDynamicContent: contentType = %s, list is null", str));
            return;
        }
        String[] strArr = (String[]) list.toArray(new String[0]);
        Logger.debug(TAG, String.format("updateDynamicContent: %s, contentType = %s, update list num = %s", this.mNativeDialogManager.updateDynamicContent(str, strArr).name(), str, Integer.valueOf(strArr.length)));
    }

    @Override // cerence.ark.assistant.framework.Framework
    public boolean updateShortcutCommand(List<String> list) {
        String[] strArr = new String[0];
        if (list != null) {
            strArr = (String[]) list.toArray(new String[0]);
        }
        DmResultCode dmResultCodeUpdateShortcut = this.mNativeDialogManager.updateShortcut(strArr);
        Logger.debug(TAG, String.format("%s: %s, update list num = %s", "updateShortcutCommand", dmResultCodeUpdateShortcut.name(), Integer.valueOf(strArr.length)));
        return isSuccess(dmResultCodeUpdateShortcut);
    }

    @Override // cerence.ark.assistant.framework.Framework
    public void setWuwConfig(WuwConfig wuwConfig, IResultCallback<Void> iResultCallback) {
        if (wuwConfig == null) {
            Utils.notifyFailure(TAG, "setWuwConfig", "wuwConfig is null", iResultCallback);
            return;
        }
        String jsonString = wuwConfig.toJsonString();
        String str = TAG;
        Logger.debug(str, String.format("%s: %s", "setWuwConfig", jsonString));
        DmResultCode wuw = this.mNativeDialogManager.setWuw(jsonString);
        Logger.debug(str, String.format("%s: %s", "setWuwConfig", wuw.name()));
        if (isSuccess(wuw)) {
            Utils.notifySuccess(iResultCallback);
        } else {
            Utils.notifyFailure(str, "setWuwConfig", wuw.name(), iResultCallback);
        }
    }

    @Override // cerence.ark.assistant.framework.Framework
    public WuwInfo getWuwInfo() {
        String wuwInfo = this.mNativeDialogManager.getWuwInfo();
        if (TextUtils.isEmpty(wuwInfo)) {
            Logger.error(TAG, "getWuwInfo: wuw json string is empty");
            return null;
        }
        Logger.info(TAG, "getWuwInfo: " + wuwInfo);
        return FrameworkUtils.getWuwInfo(wuwInfo);
    }

    @Override // cerence.ark.assistant.framework.Framework
    public boolean updateAssistantContext(AssistantContextType assistantContextType, String str) {
        if (assistantContextType == null) {
            Logger.error(TAG, "updateAssistantContext: context type is empty");
            return false;
        }
        DmResultCode dmResultCodeUpdateSystemContext = this.mNativeDialogManager.updateSystemContext(assistantContextType.getValue(), str);
        Logger.debug(TAG, String.format("updateAssistantContext: %s, contextType = %s (%s)", dmResultCodeUpdateSystemContext.name(), assistantContextType.name(), Integer.valueOf(assistantContextType.getValue())));
        return isSuccess(dmResultCodeUpdateSystemContext);
    }

    @Override // cerence.ark.assistant.framework.Framework
    public void setErrorListener(ErrorListener errorListener) {
        this.mErrorListener = errorListener;
    }

    @Override // cerence.ark.assistant.framework.Framework
    public void activateDevice(String str, IResultCallback<String> iResultCallback) {
        if (TextUtils.isEmpty(str)) {
            Utils.notifyFailure(TAG, "activateDevice", ErrorMessage.DEVICE_ID_NULL, iResultCallback);
            return;
        }
        int[] iArr = new int[1];
        DmResultCode dmResultCodeActivateDevice = this.mNativeDialogManager.activateDevice(str, iArr);
        if (isSuccess(dmResultCodeActivateDevice)) {
            this.mCallbackMap.put(Integer.valueOf(iArr[0]), iResultCallback);
        } else {
            Utils.notifyFailure(TAG, "activateDevice", dmResultCodeActivateDevice.name(), iResultCallback);
        }
    }

    @Override // cerence.ark.assistant.framework.Framework
    public void deactivateDevice(String str, IResultCallback<String> iResultCallback) {
        if (TextUtils.isEmpty(str)) {
            Utils.notifyFailure(TAG, "deactivateDevice", ErrorMessage.DEVICE_ID_NULL, iResultCallback);
            return;
        }
        int[] iArr = new int[1];
        DmResultCode dmResultCodeDeactivateDevice = this.mNativeDialogManager.deactivateDevice(str, iArr);
        if (isSuccess(dmResultCodeDeactivateDevice)) {
            this.mCallbackMap.put(Integer.valueOf(iArr[0]), iResultCallback);
        } else {
            Utils.notifyFailure(TAG, "deactivateDevice", dmResultCodeDeactivateDevice.name(), iResultCallback);
        }
    }

    @Override // cerence.ark.assistant.framework.Framework
    public void queryDeviceStatus(String str, IResultCallback<String> iResultCallback) {
        if (TextUtils.isEmpty(str)) {
            Utils.notifyFailure(TAG, "queryDeviceStatus", ErrorMessage.DEVICE_ID_NULL, iResultCallback);
            return;
        }
        int[] iArr = new int[1];
        DmResultCode dmResultCodeQueryDeviceStatus = this.mNativeDialogManager.queryDeviceStatus(str, iArr);
        if (isSuccess(dmResultCodeQueryDeviceStatus)) {
            this.mCallbackMap.put(Integer.valueOf(iArr[0]), iResultCallback);
        } else {
            Utils.notifyFailure(TAG, "queryDeviceStatus", dmResultCodeQueryDeviceStatus.name(), iResultCallback);
        }
    }

    @Override // cerence.ark.assistant.framework.Framework
    public AssistantState getAssistantState() {
        return this.mCurrentState;
    }

    @Override // cerence.ark.assistant.framework.Framework
    public boolean requestTrimMemory() {
        String str = TAG;
        Logger.info(str, "requestTrimMemory: begin");
        DmResultCode dmResultCodeRequestTrimMemory = this.mNativeDialogManager.requestTrimMemory();
        Logger.info(str, "requestTrimMemory: end, " + dmResultCodeRequestTrimMemory);
        return isSuccess(dmResultCodeRequestTrimMemory);
    }

    @Override // cerence.ark.assistant.framework.Framework
    public boolean setUserDataRetentionEnabled(boolean z) {
        DmResultCode dataRetentionEnabled = this.mNativeDialogManager.setDataRetentionEnabled(z);
        Logger.info(TAG, "setUserDataRetentionEnabled: " + dataRetentionEnabled);
        return isSuccess(dataRetentionEnabled);
    }

    @Override // cerence.ark.assistant.framework.Framework
    public boolean overrideConfiguration(String str, String str2) {
        String str3 = TAG;
        Logger.info(str3, String.format("overrideConfiguration: key = %s", str));
        if (TextUtils.isEmpty(str)) {
            Logger.error(str3, "overrideConfiguration: key is empty");
            return false;
        }
        DmResultCode dmResultCodeOverrideConfiguration = this.mNativeDialogManager.overrideConfiguration(str, str2);
        Logger.info(str3, "overrideConfiguration: " + dmResultCodeOverrideConfiguration);
        return isSuccess(dmResultCodeOverrideConfiguration);
    }

    @Override // cerence.ark.assistant.dm.NativeDialogManager.DMCallback
    public void onRequest(String str) {
        FrameworkMessageListener frameworkMessageListener = this.mMessageListener;
        if (frameworkMessageListener == null) {
            Logger.error(TAG, "onRequest: messageListener is null");
        } else if (TextUtils.isEmpty(str)) {
            Logger.error(TAG, "onRequest: jsonData is null");
        } else {
            frameworkMessageListener.onRequestReceived(str);
        }
    }

    @Override // cerence.ark.assistant.dm.NativeDialogManager.DMCallback
    public boolean onAbortRequest(String str) {
        FrameworkMessageListener frameworkMessageListener = this.mMessageListener;
        if (frameworkMessageListener == null) {
            Logger.error(TAG, "onAbortRequest: messageListener is null");
            return true;
        }
        if (TextUtils.isEmpty(str)) {
            Logger.error(TAG, "onAbortRequest: jsonData is null");
            return true;
        }
        return frameworkMessageListener.onAbortRequest(str);
    }

    @Override // cerence.ark.assistant.dm.NativeDialogManager.DMCallback
    public void onNotification(String str) {
        FrameworkMessageListener frameworkMessageListener = this.mMessageListener;
        if (frameworkMessageListener == null) {
            Logger.error(TAG, "onNotification: messageListener is null");
        } else if (TextUtils.isEmpty(str)) {
            Logger.error(TAG, "onNotification: jsonData is null");
        } else {
            frameworkMessageListener.onNotificationReceived(str);
        }
    }

    @Override // cerence.ark.assistant.dm.NativeDialogManager.DMCallback
    public void onAbortPrompt(String str, int i) {
        if (str.equals(this.mDialogPrompterName)) {
            synchronized (this.mPlayerLock) {
                IAudioPlayer iAudioPlayer = this.mAudioPlayer;
                if (iAudioPlayer != null) {
                    iAudioPlayer.onStop(true);
                }
                this.mAudioLogger.closeDialogPrompterFileStream();
            }
            return;
        }
        this.mAudioLogger.closePrompterFileStream(str);
        PrompterImpl prompterImpl = this.mPrompterMap.get(str);
        if (prompterImpl == null) {
            Logger.error(TAG, String.format("onAbortPrompt: prompter is null, instanceName = %s, promptId = %s", str, Integer.valueOf(i)));
        } else {
            prompterImpl.onAbortPrompt();
        }
    }

    @Override // cerence.ark.assistant.dm.NativeDialogManager.DMCallback
    public void onPromptAudio(String str, byte[] bArr, int i) {
        if (str.equals(this.mDialogPrompterName)) {
            synchronized (this.mPlayerLock) {
                IAudioPlayer iAudioPlayer = this.mAudioPlayer;
                if (iAudioPlayer == null) {
                    return;
                }
                iAudioPlayer.onPlayAudio(bArr);
                this.mAudioLogger.saveDialogPrompterAudio(bArr, this.mDialogPromptAudioFolderName, i);
                return;
            }
        }
        PrompterImpl prompterImpl = this.mPrompterMap.get(str);
        if (prompterImpl == null) {
            Logger.error(TAG, String.format("onPromptAudio: prompter is null, instanceName = %s, promptId = %s", str, Integer.valueOf(i)));
        } else {
            prompterImpl.playAudio(bArr);
            this.mAudioLogger.savePrompterAudio(bArr, str, i);
        }
    }

    @Override // cerence.ark.assistant.dm.NativeDialogManager.DMCallback
    public void onPromptEvent(String str, String str2, int i) {
        if (str.equals(this.mDialogPrompterName)) {
            return;
        }
        PrompterImpl prompterImpl = this.mPrompterMap.get(str);
        if (prompterImpl == null) {
            Logger.error(TAG, String.format("onPromptEvent: prompter is null, instanceName = %s, promptId = %s, jsonEvent = %s", str, Integer.valueOf(i), str2));
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str2);
            String strOptString = jSONObject.optString("type");
            String strOptString2 = jSONObject.optString("event");
            if (PROMPT_EVENT.equals(strOptString)) {
                String str3 = TAG;
                Logger.debug(str3, String.format("onPromptEvent: promptId = %s, promptEvent = %s", Integer.valueOf(i), strOptString2));
                PromptEvent promptEventFromString = PromptEvent.fromString(strOptString2);
                if (promptEventFromString == null) {
                    Logger.warn(str3, "onPromptEvent: event name not mapping PromptEvent enum");
                } else {
                    prompterImpl.onPromptEvent(i, promptEventFromString);
                }
            }
        } catch (JSONException e) {
            Logger.error(TAG, "onPromptEvent: exception", e);
        }
    }

    @Override // cerence.ark.assistant.dm.NativeDialogManager.DMCallback
    public void onPromptError(String str, String str2, int i) {
        String str3 = TAG;
        Logger.error(str3, String.format("onPromptError: instanceName = %s, promptId = %s, jsonError = %s", str, Integer.valueOf(i), str2));
        if (str.equals(this.mDialogPrompterName)) {
            return;
        }
        PrompterImpl prompterImpl = this.mPrompterMap.get(str);
        if (prompterImpl == null) {
            Logger.error(str3, String.format("onPromptError: prompter is null, instanceName = %s, promptId = %s, jsonError = %s", str, Integer.valueOf(i), str2));
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str2);
            String strOptString = jSONObject.optString("type");
            String strOptString2 = jSONObject.optString("error_msg");
            if (PROMPTER_ERROR.equals(strOptString)) {
                prompterImpl.onPrompterError(i, PrompterError.fromString(strOptString2));
            }
        } catch (JSONException e) {
            Logger.error(TAG, "onPromptError: exception, jsonError = " + str2, e);
        }
    }

    @Override // cerence.ark.assistant.dm.NativeDialogManager.DMCallback
    public void onEndOfStream(String str, int i) {
        String str2 = TAG;
        Logger.debug(str2, String.format("onEndOfStream: instanceName = %s, promptId = %s", str, Integer.valueOf(i)));
        if (str.equals(this.mDialogPrompterName)) {
            synchronized (this.mPlayerLock) {
                IAudioPlayer iAudioPlayer = this.mAudioPlayer;
                if (iAudioPlayer != null) {
                    iAudioPlayer.onEndOfStream();
                }
                this.mAudioLogger.closeDialogPrompterFileStream();
            }
            return;
        }
        this.mAudioLogger.closePrompterFileStream(str);
        PrompterImpl prompterImpl = this.mPrompterMap.get(str);
        if (prompterImpl == null) {
            Logger.error(str2, String.format("onEndOfStream: prompter is null, instanceName = %s, promptId = %s", str, Integer.valueOf(i)));
        } else {
            prompterImpl.onEndOfStream();
        }
    }

    @Override // cerence.ark.assistant.dm.NativeDialogManager.DMCallback
    public void onStartRecording() {
        this.mIsAudioRecordingStarted = true;
        synchronized (this.mRecorderLock) {
            initializeAudioRecorder();
            this.mAudioRecorderManager.startAudioRecording();
        }
    }

    @Override // cerence.ark.assistant.dm.NativeDialogManager.DMCallback
    public void onStopRecording(boolean z) {
        Logger.debug(TAG, "stopAudioRecording: transient = " + z);
        this.mIsAudioRecordingStarted = false;
        this.mAudioLogger.closeMicFileStream();
        if (z) {
            return;
        }
        destroyAudioRecorder();
    }

    @Override // cerence.ark.assistant.dm.NativeDialogManager.DMCallback
    public void onSpeechAudio(SpeechAudioType speechAudioType, byte[] bArr) {
        SpeechAudioListener speechAudioListener = this.mSpeechAudioListener;
        if (speechAudioListener == null) {
            return;
        }
        this.mAudioLogger.saveSpeechAudio(speechAudioType, bArr);
        speechAudioListener.onSpeechAudio(speechAudioType, bArr, bArr.length == 0);
    }

    @Override // cerence.ark.assistant.dm.NativeDialogManager.DMCallback
    public void onEnhancedSiriAudio(byte[] bArr, long j) {
        EnhancedSiriAudioListener enhancedSiriAudioListener = this.mEnhancedSiriAudioListener;
        if (enhancedSiriAudioListener == null) {
            return;
        }
        this.mAudioLogger.saveEnhancedSiriAudio(bArr, ENHANCED_SIRI_AUDIO_FILE_NAME);
        enhancedSiriAudioListener.onEnhancedSiriAudio(bArr, j);
    }

    @Override // cerence.ark.assistant.dm.NativeDialogManager.DMCallback
    public void onFatalError(FatalErrorCode fatalErrorCode, String str) {
        String str2 = TAG;
        Logger.error(str2, String.format("%s: %s", "onFatalError", str));
        ErrorListener errorListener = this.mErrorListener;
        if (errorListener != null) {
            ErrorInfo errorInfo = new ErrorInfo();
            errorInfo.setErrorCode(FrameworkUtils.getErrorCode(fatalErrorCode)).setErrorMessage(str);
            errorListener.onError(errorInfo);
            return;
        }
        Logger.error(str2, String.format("%s: error listener is null", "onFatalError"));
    }

    @Override // cerence.ark.assistant.dm.NativeDialogManager.DMCallback
    public void onActivateDeviceSuccess(String str) {
        if (TextUtils.isEmpty(str)) {
            Logger.error(TAG, String.format("%s: device info is empty", "onActivateDeviceSuccess"));
            return;
        }
        try {
            IResultCallback<String> iResultCallbackRemove = this.mCallbackMap.remove(Integer.valueOf(new JSONObject(str).optInt(JsonKey.DEVICE_REQUEST_ID)));
            if (iResultCallbackRemove != null) {
                iResultCallbackRemove.onResult(new Result.Builder().buildSuccessResult(str));
            } else {
                Logger.error(TAG, String.format("%s: callback is null, %s", "onActivateDeviceSuccess", str));
            }
        } catch (JSONException e) {
            Logger.error(TAG, String.format("%s: parse json exception, %s", "onActivateDeviceSuccess", str), e);
        }
    }

    @Override // cerence.ark.assistant.dm.NativeDialogManager.DMCallback
    public void onActivateDeviceFailed(String str) {
        if (TextUtils.isEmpty(str)) {
            Logger.error(TAG, String.format("%s: device info is empty", "onActivateDeviceFailed"));
            return;
        }
        String str2 = TAG;
        Logger.error(str2, String.format("%s: %s", "onActivateDeviceFailed", str));
        try {
            IResultCallback<String> iResultCallbackRemove = this.mCallbackMap.remove(Integer.valueOf(new JSONObject(str).optInt(JsonKey.DEVICE_REQUEST_ID)));
            if (iResultCallbackRemove != null) {
                iResultCallbackRemove.onResult(new Result.Builder().buildErrorResult(str));
            } else {
                Logger.error(str2, String.format("%s: callback is null", "onActivateDeviceFailed"));
            }
        } catch (JSONException e) {
            Logger.error(TAG, String.format("%s: parse json exception", "onActivateDeviceFailed"), e);
        }
    }

    private void updateAssistantState(AssistantState assistantState) {
        Logger.warn(TAG, String.format("updateAssistantState: %s -> %s", this.mCurrentState.name(), assistantState.name()));
        this.mCurrentState = assistantState;
        Utils.notifyAssistantStateChanged(assistantState);
    }

    private boolean isSuccess(DmResultCode dmResultCode) {
        return dmResultCode == DmResultCode.OK;
    }

    private void initializeAudioRecorder() {
        AudioRecorderProvider defaultAudioRecorderProvider;
        AudioRecorderManager audioRecorderManager = this.mAudioRecorderManager;
        if (audioRecorderManager == null || !audioRecorderManager.isInitialized()) {
            Config config = this.mInitConfig;
            if (config == null) {
                Logger.error(TAG, "initializeAudioRecorder: init config is null");
                return;
            }
            this.mAudioRecorderManager = new AudioRecorderManager(new IAudioListener() { // from class: cerence.ark.assistant.framework.impl.ArkFramework$$ExternalSyntheticLambda1
                @Override // cerence.ark.assistant.framework.impl.recorder.IAudioListener
                public final void onAudioAvailable(byte[] bArr) {
                    this.f$0.m44x6588311d(bArr);
                }
            });
            if (config.getAudioRecordingMode() == AudioRecordingMode.APP) {
                defaultAudioRecorderProvider = AudioRecorderProvider.getProvider(Utils.getContext());
                if (defaultAudioRecorderProvider == null) {
                    throw new ArkAssistantException("If recording mode is APP, you should provide the customized audio recorder by audio recorder provider.");
                }
            } else {
                defaultAudioRecorderProvider = new DefaultAudioRecorderProvider();
            }
            IAudioRecorder iAudioRecorderCreateAudioRecorder = defaultAudioRecorderProvider.createAudioRecorder();
            if (iAudioRecorderCreateAudioRecorder == null) {
                throw new ArkAssistantException("Create audio recorder is null.");
            }
            this.mMicAudioFolderName = iAudioRecorderCreateAudioRecorder.getClass().getSimpleName();
            this.mAudioRecorderManager.initialize(iAudioRecorderCreateAudioRecorder, new IResultCallback() { // from class: cerence.ark.assistant.framework.impl.ArkFramework$$ExternalSyntheticLambda2
                @Override // cerence.ark.assistant.common.callback.IResultCallback
                public final void onResult(Result result) {
                    this.f$0.m45xa9134ede(result);
                }
            });
        }
    }

    /* JADX INFO: renamed from: lambda$initializeAudioRecorder$0$cerence-ark-assistant-framework-impl-ArkFramework, reason: not valid java name */
    /* synthetic */ void m44x6588311d(byte[] bArr) {
        if (this.mAssistantConfiguration.isEnableBargeIn() || this.mIsAudioRecordingStarted) {
            this.mNativeDialogManager.feedAudio(bArr, bArr.length);
            this.mAudioLogger.saveMicAudio(bArr, this.mMicAudioFolderName);
        }
    }

    /* JADX INFO: renamed from: lambda$initializeAudioRecorder$1$cerence-ark-assistant-framework-impl-ArkFramework, reason: not valid java name */
    /* synthetic */ void m45xa9134ede(Result result) {
        ErrorListener errorListener;
        if (result.isSuccess() || (errorListener = this.mErrorListener) == null) {
            return;
        }
        ErrorInfo errorInfo = new ErrorInfo();
        errorInfo.setErrorCode(ErrorInfo.ErrorCode.FATAL_INITIALIZE_AUDIO).setErrorMessage("Initialize audio recorder error.");
        errorListener.onError(errorInfo);
    }

    private boolean initializeAudioPlayer(Config config, AssistantConfiguration assistantConfiguration) {
        AudioPlayerProvider defaultAudioPlayerProvider;
        boolean zOnInitialize;
        synchronized (this.mPlayerLock) {
            if (config.getAudioPlaybackMode() == AudioPlaybackMode.APP) {
                defaultAudioPlayerProvider = AudioPlayerProvider.getProvider(Utils.getContext());
                if (defaultAudioPlayerProvider == null) {
                    throw new ArkAssistantException("If playback mode is APP, you should provide the customized audio player by audio player provider.");
                }
            } else {
                defaultAudioPlayerProvider = new DefaultAudioPlayerProvider();
            }
            IAudioPlayer iAudioPlayerCreateAudioPlayer = defaultAudioPlayerProvider.createAudioPlayer();
            if (iAudioPlayerCreateAudioPlayer == null) {
                throw new ArkAssistantException("Create audio player is null.");
            }
            this.mDialogPromptAudioFolderName = iAudioPlayerCreateAudioPlayer.getClass().getSimpleName();
            AudioAttributes audioAttributes = assistantConfiguration.getAudioAttributes();
            if (audioAttributes == null) {
                audioAttributes = new AudioAttributes.Builder().setLegacyStreamType(3).build();
            }
            zOnInitialize = iAudioPlayerCreateAudioPlayer.onInitialize(AudioChannel.CHANNEL_MONO, AudioFormat.PCM_16BIT, SampleRate.SAMPLE_RATE_HZ_22050, audioAttributes);
            if (zOnInitialize) {
                this.mAudioPlayer = iAudioPlayerCreateAudioPlayer;
            }
        }
        return zOnInitialize;
    }

    private void destroyAudioPlayer() {
        synchronized (this.mPlayerLock) {
            IAudioPlayer iAudioPlayer = this.mAudioPlayer;
            if (iAudioPlayer == null) {
                return;
            }
            iAudioPlayer.onRelease();
            this.mAudioPlayer = null;
            this.mAudioLogger.closeDialogPrompterFileStream();
        }
    }

    private void destroyAudioRecorder() {
        synchronized (this.mRecorderLock) {
            AudioRecorderManager audioRecorderManager = this.mAudioRecorderManager;
            if (audioRecorderManager == null) {
                return;
            }
            if (audioRecorderManager.isInitialized()) {
                final CountDownLatch countDownLatch = new CountDownLatch(1);
                this.mAudioRecorderManager.destroy(new IResultCallback() { // from class: cerence.ark.assistant.framework.impl.ArkFramework$$ExternalSyntheticLambda0
                    @Override // cerence.ark.assistant.common.callback.IResultCallback
                    public final void onResult(Result result) {
                        countDownLatch.countDown();
                    }
                });
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    Logger.error(TAG, "destroyAudioRecorder: await exception", e);
                    Thread.currentThread().interrupt();
                }
            }
            this.mAudioRecorderManager = null;
        }
    }

    private void createSpeechAudioListener() {
        SpeechAudioListenerProvider provider = SpeechAudioListenerProvider.getProvider(Utils.getContext());
        if (provider == null) {
            Logger.warn(TAG, "SpeechAudioProvider is null");
        } else {
            this.mSpeechAudioListener = provider.createSpeechAudioListener();
        }
    }

    private void createEnhancedSiriAudioListener() {
        EnhancedSiriAudioListenerProvider provider = EnhancedSiriAudioListenerProvider.getProvider(Utils.getContext());
        if (provider == null) {
            Logger.warn(TAG, "EnhancedSiriAudioListenerProvider is null");
        } else {
            this.mEnhancedSiriAudioListener = provider.createAudioListener();
        }
    }
}
