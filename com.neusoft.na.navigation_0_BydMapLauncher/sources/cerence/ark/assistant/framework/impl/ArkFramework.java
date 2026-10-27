package cerence.ark.assistant.framework.impl;

import android.media.AudioAttributes;
import android.os.Handler;
import android.os.HandlerThread;
import android.text.TextUtils;
import cerence.ark.assistant.api.prompter.PrompterInstance;
import cerence.ark.assistant.api.prompter.TtsVoice;
import cerence.ark.assistant.api.prompter.VoiceType;
import cerence.ark.assistant.common.Logger;
import cerence.ark.assistant.common.callback.IResultCallback;
import cerence.ark.assistant.common.callback.Result;
import cerence.ark.assistant.dm.DmTokenProvider;
import cerence.ark.assistant.dm.NativeDialogManager;
import cerence.ark.assistant.dm.data.DmConfiguration;
import cerence.ark.assistant.dm.data.DmInitParams;
import cerence.ark.assistant.dm.data.DmResultCode;
import cerence.ark.assistant.dm.data.FatalErrorCode;
import cerence.ark.assistant.dm.data.LocationType;
import cerence.ark.assistant.dm.data.PrompterVoice;
import cerence.ark.assistant.dm.data.RecognitionErrorCode;
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
import cerence.ark.assistant.framework.data.ErrorCode;
import cerence.ark.assistant.framework.data.ErrorInfo;
import cerence.ark.assistant.framework.data.LogConsumer;
import cerence.ark.assistant.framework.data.RppLocationType;
import cerence.ark.assistant.framework.data.VoiceConfig;
import cerence.ark.assistant.framework.data.wuw.WuwConfig;
import cerence.ark.assistant.framework.impl.AudioLogConfig;
import cerence.ark.assistant.framework.impl.player.DefaultAudioPlayerProvider;
import cerence.ark.assistant.framework.impl.prompter.PrompterImpl;
import cerence.ark.assistant.framework.impl.prompter.TextToSpeechPrompter;
import cerence.ark.assistant.framework.impl.recorder.AudioRecorderManager;
import cerence.ark.assistant.framework.impl.recorder.DefaultAudioRecorderProvider;
import cerence.ark.assistant.framework.impl.recorder.IAudioListener;
import cerence.ark.assistant.framework.impl.updater.IDataUpdater;
import cerence.ark.assistant.framework.impl.updater.UpdateListenerImpl;
import cerence.ark.assistant.framework.impl.updater.UpdaterImpl;
import cerence.ark.assistant.framework.prompter.IPrompter;
import cerence.ark.assistant.framework.prompter.PrompterError;
import cerence.ark.assistant.framework.speech.SpeechAudioListener;
import cerence.ark.assistant.framework.speech.SpeechAudioListenerProvider;
import cerence.ark.assistant.framework.updater.IUpdateListener;
import cerence.ark.assistant.framework.vpa.VpaAudioListener;
import cerence.ark.assistant.framework.vpa.VpaAudioListenerProvider;
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
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class ArkFramework implements Framework, NativeDialogManager.DMCallback, NativeDialogManager.RecognitionCallback {
    private static final int PROMPTER_AUDIO_CHUNK_SIZE = 1024;
    private static final String TAG = "ArkFramework";
    private static final String VPA_AUDIO_FILE_NAME = "vpa";
    private AssistantConfiguration mAssistantConfiguration;
    private IAudioPlayer mAudioPlayer;
    private AudioRecorderManager mAudioRecorderManager;
    private IResultCallback<Void, ErrorCode> mClearUserDataCallback;
    private String mDialogPromptAudioFolderName;
    private ErrorListener mErrorListener;
    private Config mInitConfig;
    private FrameworkMessageListener mMessageListener;
    private String mMicAudioFolderName;
    private PrompterVoice[] mPrompterVoices;
    private Handler mRecoveryHandler;
    private boolean mRecoveryThreadDestroyed;
    private SpeechAudioListener mSpeechAudioListener;
    private PrompterInstance mTtsPrompterInstance;
    private IDataUpdater mUpdater;
    private VpaAudioListener mVpaAudioListener;
    private final AudioLogger mAudioLogger = new AudioLogger();
    private final Object mRecorderLock = new Object();
    private final Object mPlayerLock = new Object();
    private final Object mPrompterNameLock = new Object();
    private final Object mAssistantStateLock = new Object();
    private final Object mRecoveryHandlerLock = new Object();
    private final Map<String, PrompterImpl> mPrompterMap = new ConcurrentHashMap(16);
    private AssistantState mCurrentState = AssistantState.UNINITIALIZED;
    private List<PrompterInstance> mAvailablePrompterInstances = new ArrayList();
    private List<PrompterInstance> mDialogPrompterInstances = new ArrayList();
    private final NativeDialogManager mNativeDialogManager = new NativeDialogManager();
    private final List<String> mDialogPrompterNames = new CopyOnWriteArrayList();

    @Override // cerence.ark.assistant.framework.Framework
    public void initialize(Config config, FrameworkMessageListener frameworkMessageListener, DmTokenProvider dmTokenProvider, IResultCallback<Void, ErrorCode> iResultCallback) {
        if (config == null) {
            Logger.error(TAG, Utils.format("%s: Config is null", "initialize"));
            Utils.notifyError(ErrorCode.PARAMETER_INVALID, iResultCallback);
            return;
        }
        String str = TAG;
        Logger.info(str, Utils.format2Argv("initialize", config.toString()));
        DmInitParams.Builder builder = new DmInitParams.Builder();
        builder.setDataPath(config.getDataPath()).setWritablePath(config.getWritablePath()).setDeviceId(config.getDeviceId()).setUserId(config.getUserId()).setAudioRecordingMode(FrameworkUtils.getDmAudioRecordingMode(config.getAudioRecordingMode())).setAudioPlaybackMode(FrameworkUtils.getDmAudioPlaybackMode(config.getAudioPlaybackMode())).setUpdateStorageDir(config.getUpdateStorageDir()).setDeviceModel(config.getDeviceModel());
        DmResultCode dmResultCodeInitialize = this.mNativeDialogManager.initialize(builder.build(), dmTokenProvider, this);
        Logger.info(str, Utils.format2Argv("initialize", dmResultCodeInitialize.name()));
        if (FrameworkUtils.isSuccess(dmResultCodeInitialize)) {
            this.mDialogPrompterInstances = FrameworkUtils.getPrompterInstances(this.mNativeDialogManager.getDialogPrompterInstances());
            this.mAvailablePrompterInstances = FrameworkUtils.getPrompterInstances(this.mNativeDialogManager.getAvailablePrompterInstances());
            this.mInitConfig = config;
            this.mPrompterVoices = this.mNativeDialogManager.getAvailablePrompterVoices(null);
            this.mMessageListener = frameworkMessageListener;
            this.mAudioLogger.initialize(new AudioLogConfig.Builder().setAudioDir(Utils.getAudioLogDir(config.getWritablePath())).setSaveInputAudio(config.isSaveInputAudio()).setSaveOutputAudio(config.isSaveOutputAudio()).setMaximumSize(config.getMaxAudioLogSize()).build(), 1920, 1024);
            updateAssistantState(AssistantState.INITIALIZED);
            Utils.notifySuccess(iResultCallback);
            return;
        }
        Utils.notifyError(FrameworkUtils.getErrorCode(dmResultCodeInitialize), iResultCallback);
    }

    @Override // cerence.ark.assistant.framework.Framework
    public synchronized void startVoiceAssistant(AssistantConfiguration assistantConfiguration, IResultCallback<Void, ErrorCode> iResultCallback) {
        String jsonString;
        if (assistantConfiguration == null) {
            Logger.error(TAG, Utils.format("%s: AssistantConfiguration is null", "startVoiceAssistant"));
            Utils.notifyError(ErrorCode.PARAMETER_INVALID, iResultCallback);
            return;
        }
        if (getAssistantState() == AssistantState.RUNNING) {
            Logger.error(TAG, Utils.format2Argv("startVoiceAssistant", ErrorMessage.ASSISTANT_ALREADY_STARTED));
            Utils.notifyError(ErrorCode.ASSISTANT_ALREADY_STARTED, iResultCallback);
            return;
        }
        Config config = this.mInitConfig;
        if (config == null) {
            Logger.error(TAG, Utils.format2Argv("startVoiceAssistant", ErrorMessage.CONFIG_NULL));
            Utils.notifyError(ErrorCode.PARAMETER_INVALID, iResultCallback);
            return;
        }
        String str = TAG;
        Logger.info(str, Utils.format2Argv("startVoiceAssistant", assistantConfiguration.toString()));
        VoiceConfig voiceConfig = assistantConfiguration.getVoiceConfig();
        if (voiceConfig == null) {
            voiceConfig = FrameworkUtils.getDefaultVoiceConfig(assistantConfiguration.getAsrLanguage(), this.mPrompterVoices);
            if (voiceConfig == null) {
                Logger.error(str, Utils.format2Argv("startVoiceAssistant", ErrorMessage.VOICE_NOT_FOUND));
                Utils.notifyError(ErrorCode.VOICE_NOT_FOUND, iResultCallback);
                return;
            }
        } else if (!FrameworkUtils.checkTtsVoiceConfigValid(voiceConfig, this.mPrompterVoices)) {
            Logger.error(str, Utils.format2Argv("startVoiceAssistant", ErrorMessage.VOICE_CONFIG_INVALID));
            Utils.notifyError(ErrorCode.VOICE_CONFIG_INVALID, iResultCallback);
            return;
        }
        if (!FrameworkUtils.isPlaybackModePal(config.getAudioPlaybackMode())) {
            List<PrompterInstance> list = this.mDialogPrompterInstances;
            if (list != null && !list.isEmpty()) {
                List list2 = (List) this.mDialogPrompterInstances.stream().map(new Function() { // from class: cerence.ark.assistant.framework.impl.ArkFramework$$ExternalSyntheticLambda0
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return ArkFramework.lambda$startVoiceAssistant$0((PrompterInstance) obj);
                    }
                }).collect(Collectors.toList());
                this.mDialogPrompterNames.clear();
                this.mDialogPrompterNames.addAll(list2);
                if (!initializeAudioPlayer(config, assistantConfiguration)) {
                    Logger.error(str, Utils.format2Argv("startVoiceAssistant", ErrorMessage.AUDIO_PLAYER_INIT_FAIL));
                    Utils.notifyError(ErrorCode.AUDIO_PLAYER_INIT_FAIL, iResultCallback);
                    return;
                }
            }
            Logger.error(str, Utils.format2Argv("startVoiceAssistant", ErrorMessage.DIALOG_PROMPTER_NAME_NULL));
            Utils.notifyError(ErrorCode.UNKNOWN, iResultCallback);
            return;
        }
        if (config.getAudioRecordingMode() == AudioRecordingMode.SDK && !Utils.checkSelfPermission("android.permission.RECORD_AUDIO")) {
            Logger.error(str, Utils.format2Argv("startVoiceAssistant", ErrorMessage.AUDIO_RECORD_PERMISSION_DENIED));
            Utils.notifyError(ErrorCode.AUDIO_RECORD_PERMISSION_DENIED, iResultCallback);
            return;
        }
        createSpeechAudioListener();
        createVpaAudioListener();
        String jsonString2 = null;
        try {
            jsonString = voiceConfig.toJsonString();
        } catch (JSONException e) {
            e = e;
            jsonString = null;
        }
        try {
            Logger.info(str, Utils.format("%s: voiceConfigJson = %s", "startVoiceAssistant", jsonString));
        } catch (JSONException e2) {
            e = e2;
            Logger.error(TAG, Utils.format("%s: voiceConfig convert to JSON string exception", "startVoiceAssistant"), e);
        }
        if (jsonString == null) {
            Logger.error(TAG, Utils.format("%s: voiceConfigJson is null", "startVoiceAssistant"));
            Utils.notifyError(ErrorCode.VOICE_CONFIG_INVALID, iResultCallback);
            return;
        }
        List<String> hybridTtsDomains = assistantConfiguration.getHybridTtsDomains();
        final JSONArray jSONArray = new JSONArray();
        if (hybridTtsDomains != null) {
            Objects.requireNonNull(jSONArray);
            hybridTtsDomains.forEach(new Consumer() { // from class: cerence.ark.assistant.framework.impl.ArkFramework$$ExternalSyntheticLambda1
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    jSONArray.put((String) obj);
                }
            });
        }
        DmConfiguration.Builder builder = new DmConfiguration.Builder();
        DmConfiguration.Builder builderIsReceiveSpeechAudio = builder.enableBargeIn(assistantConfiguration.isEnableBargeIn()).snoozingDuration(assistantConfiguration.getSnoozingDuration()).listeningTimeout(assistantConfiguration.getListeningTimeout()).interactionMode(FrameworkUtils.getDmInteractionMode(assistantConfiguration.getInteractionMode())).setTtsVoice(jsonString).extra(assistantConfiguration.getExtra()).isReceiveSpeechAudio(this.mSpeechAudioListener != null);
        if (assistantConfiguration.getWuwConfig() != null) {
            jsonString2 = assistantConfiguration.getWuwConfig().toJsonString();
        }
        builderIsReceiveSpeechAudio.setWuw(jsonString2).enableDataRetention(assistantConfiguration.isUserDataRetentionEnabled()).disabledHints(FrameworkUtils.convertDisabledHints(assistantConfiguration.getDisabledHints())).setLanguage(assistantConfiguration.getAsrLanguage()).setHybridTTSDomains(jSONArray.toString()).setWakeupRespType(FrameworkUtils.convertWakeupRespType(assistantConfiguration.getWakeupRespType()));
        DmResultCode dmResultCodeStart = this.mNativeDialogManager.start(builder.build());
        Logger.info(TAG, Utils.format2Argv("startVoiceAssistant", dmResultCodeStart.name()));
        if (isSuccess(dmResultCodeStart)) {
            this.mAssistantConfiguration = assistantConfiguration;
            updateAssistantState(AssistantState.RUNNING);
            Utils.notifySuccess(iResultCallback);
        } else {
            destroyAudioPlayer();
            Utils.notifyError(FrameworkUtils.getErrorCode(dmResultCodeStart), iResultCallback);
        }
    }

    static /* synthetic */ String lambda$startVoiceAssistant$0(PrompterInstance prompterInstance) {
        Logger.info(TAG, "dialogPromptInstance: " + prompterInstance);
        return prompterInstance.getName();
    }

    @Override // cerence.ark.assistant.framework.Framework
    public void stopVoiceAssistant(IResultCallback<Void, ErrorCode> iResultCallback) {
        String str = TAG;
        Logger.info(str, Utils.format("%s: ", "stopVoiceAssistant"));
        DmResultCode dmResultCodeStop = this.mNativeDialogManager.stop();
        Logger.info(str, Utils.format2Argv("stopVoiceAssistant", dmResultCodeStop.name()));
        if (FrameworkUtils.isSuccess(dmResultCodeStop)) {
            destroyAudioPlayer();
            destroyAudioRecorder();
            this.mAudioLogger.closeMicFileStream();
            this.mAudioLogger.closeSpeechFileStream();
            this.mAudioLogger.closeVpaFileStream();
            this.mSpeechAudioListener = null;
            this.mVpaAudioListener = null;
            updateAssistantState(AssistantState.INITIALIZED);
            Utils.notifySuccess(iResultCallback);
            return;
        }
        Utils.notifyError(FrameworkUtils.getErrorCode(dmResultCodeStop), iResultCallback);
    }

    @Override // cerence.ark.assistant.framework.Framework
    public void destroy(IResultCallback<Void, ErrorCode> iResultCallback) {
        PrompterImpl prompterImpl;
        synchronized (this.mRecoveryHandlerLock) {
            if (this.mRecoveryHandler != null) {
                Logger.info(TAG, "destroy recovery voice assistant thread");
                this.mRecoveryHandler.getLooper().quit();
            }
            this.mRecoveryThreadDestroyed = true;
        }
        IDataUpdater iDataUpdater = this.mUpdater;
        if (iDataUpdater instanceof UpdaterImpl) {
            ((UpdaterImpl) iDataUpdater).destroyUpdater();
        }
        this.mUpdater = null;
        for (String str : this.mPrompterMap.keySet()) {
            if (!TextUtils.isEmpty(str) && (prompterImpl = this.mPrompterMap.get(str)) != null && !this.mDialogPrompterInstances.contains(prompterImpl.getPrompterInstance())) {
                prompterImpl.destroy();
            }
        }
        this.mPrompterMap.clear();
        String str2 = TAG;
        Utils.logMethodBegin(str2, "destroy");
        DmResultCode dmResultCodeDestroy = this.mNativeDialogManager.destroy();
        Logger.info(str2, Utils.format2Argv("destroy", dmResultCodeDestroy.name()));
        if (FrameworkUtils.isSuccess(dmResultCodeDestroy)) {
            destroyAudioPlayer();
            destroyAudioRecorder();
            this.mAudioLogger.destroy();
            this.mMessageListener = null;
            this.mErrorListener = null;
            this.mSpeechAudioListener = null;
            this.mVpaAudioListener = null;
            this.mInitConfig = null;
            this.mAssistantConfiguration = null;
            this.mClearUserDataCallback = null;
            this.mUpdater = null;
            updateAssistantState(AssistantState.UNINITIALIZED);
            Logger.info(str2, Utils.format("%s: finish", "destroy"));
            Utils.notifySuccess(iResultCallback);
            return;
        }
        Utils.notifyError(FrameworkUtils.getErrorCode(dmResultCodeDestroy), iResultCallback);
    }

    @Override // cerence.ark.assistant.framework.Framework
    public void sendResponse(String str, IResultCallback<Void, ErrorCode> iResultCallback) {
        if (TextUtils.isEmpty(str)) {
            Logger.error(TAG, Utils.format2Argv("sendResponse", ErrorMessage.RESPONSE_JSON_NULL));
            Utils.notifyError(ErrorCode.PARAMETER_INVALID, iResultCallback);
            return;
        }
        String str2 = TAG;
        Logger.debug(str2, Utils.format2Argv("sendResponse", str));
        DmResultCode dmResultCodeSendResponse = this.mNativeDialogManager.sendResponse(str);
        Logger.info(str2, Utils.format2Argv("sendResponse", dmResultCodeSendResponse.name()));
        if (FrameworkUtils.isSuccess(dmResultCodeSendResponse)) {
            Utils.notifySuccess(iResultCallback);
        } else {
            Utils.notifyError(FrameworkUtils.getErrorCode(dmResultCodeSendResponse), iResultCallback);
        }
    }

    @Override // cerence.ark.assistant.framework.Framework
    public void sendAppEvent(String str, IResultCallback<Void, ErrorCode> iResultCallback) {
        if (TextUtils.isEmpty(str)) {
            Logger.error(TAG, Utils.format2Argv("sendAppEvent", ErrorMessage.APPEVENT_JSON_JSON_NULL));
            Utils.notifyError(ErrorCode.PARAMETER_INVALID, iResultCallback);
            return;
        }
        String str2 = TAG;
        Logger.debug(str2, Utils.format2Argv("sendAppEvent", str));
        DmResultCode dmResultCodeSendAppEvent = this.mNativeDialogManager.sendAppEvent(str);
        Logger.info(str2, Utils.format2Argv("sendAppEvent", dmResultCodeSendAppEvent.name()));
        if (FrameworkUtils.isSuccess(dmResultCodeSendAppEvent)) {
            Utils.notifySuccess(iResultCallback);
        } else {
            Utils.notifyError(FrameworkUtils.getErrorCode(dmResultCodeSendAppEvent), iResultCallback);
        }
    }

    @Override // cerence.ark.assistant.framework.Framework
    public Result<Void, ErrorCode> setVoiceConfig(VoiceConfig voiceConfig) {
        if (!FrameworkUtils.checkTtsVoiceConfigValid(voiceConfig, this.mPrompterVoices)) {
            Logger.error(TAG, Utils.format("%s: %s, voiceConfig: %s", "setVoiceConfig", ErrorMessage.VOICE_CONFIG_INVALID, voiceConfig));
            return Utils.buildErrorResult(ErrorCode.VOICE_CONFIG_INVALID);
        }
        try {
            DmResultCode ttsVoice = this.mNativeDialogManager.setTtsVoice(voiceConfig.toJsonString());
            Logger.info(TAG, Utils.format("%s: %s, voiceConfig: %s", "setVoiceConfig", ttsVoice.name(), voiceConfig));
            if (FrameworkUtils.isSuccess(ttsVoice)) {
                return new Result.Builder().buildSuccess();
            }
            return Utils.buildErrorResult(FrameworkUtils.getErrorCode(ttsVoice));
        } catch (JSONException e) {
            Logger.error(TAG, "setVoiceConfig: exception", e);
            return Utils.buildErrorResult(ErrorCode.UNKNOWN);
        }
    }

    @Override // cerence.ark.assistant.framework.Framework
    public List<PrompterInstance> getAvailablePrompterInstances() {
        synchronized (this.mPrompterNameLock) {
            if (Component.DEPENDENCY_TEXT_TO_SPEECH_SERVICE && !this.mAvailablePrompterInstances.isEmpty()) {
                if (this.mTtsPrompterInstance == null) {
                    this.mTtsPrompterInstance = FrameworkUtils.getTtsPrompterInstance(this.mAvailablePrompterInstances);
                }
                List<PrompterInstance> list = (List) this.mAvailablePrompterInstances.stream().filter(new Predicate() { // from class: cerence.ark.assistant.framework.impl.ArkFramework$$ExternalSyntheticLambda2
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        return this.f$0.m72x38c81934((PrompterInstance) obj);
                    }
                }).collect(Collectors.toList());
                if (Logger.hasLogLevel(3)) {
                    Logger.debug(TAG, "getAvailablePrompterInstances: " + TextUtils.join("|", list));
                }
                return list;
            }
            if (Logger.hasLogLevel(3)) {
                Logger.debug(TAG, "getAvailablePrompterInstances: " + TextUtils.join("|", this.mAvailablePrompterInstances));
            }
            return this.mAvailablePrompterInstances;
        }
    }

    /* JADX INFO: renamed from: lambda$getAvailablePrompterInstances$1$cerence-ark-assistant-framework-impl-ArkFramework, reason: not valid java name */
    /* synthetic */ boolean m72x38c81934(PrompterInstance prompterInstance) {
        return !Objects.equals(prompterInstance, this.mTtsPrompterInstance);
    }

    @Override // cerence.ark.assistant.framework.Framework
    public List<PrompterInstance> getDialogPrompterInstances() {
        return this.mDialogPrompterInstances;
    }

    @Override // cerence.ark.assistant.framework.Framework
    public PrompterInstance getTtsPrompterInstance() {
        synchronized (this.mPrompterNameLock) {
            if (!Component.DEPENDENCY_TEXT_TO_SPEECH_SERVICE) {
                return null;
            }
            if (this.mTtsPrompterInstance == null) {
                this.mTtsPrompterInstance = FrameworkUtils.getTtsPrompterInstance(this.mAvailablePrompterInstances);
            }
            return this.mTtsPrompterInstance;
        }
    }

    @Override // cerence.ark.assistant.framework.Framework
    public IPrompter createPrompter(PrompterInstance prompterInstance, AudioAttributes audioAttributes) {
        PrompterImpl prompterImpl;
        Config config = this.mInitConfig;
        if (config == null) {
            Logger.error(TAG, "createPrompter: init config is null, prompterInstance = " + prompterInstance);
            return null;
        }
        if (prompterInstance == null) {
            Logger.error(TAG, "createPrompter: prompterInstance is null");
            return null;
        }
        PrompterImpl prompterImpl2 = this.mPrompterMap.get(prompterInstance.getName());
        if (prompterImpl2 != null) {
            Logger.warn(TAG, "The prompter already exist: prompterInstance = " + prompterInstance);
            return prompterImpl2;
        }
        if (!FrameworkUtils.isPrompterInstanceNameValid(prompterInstance.getName(), this.mAvailablePrompterInstances) && !FrameworkUtils.isPrompterInstanceNameValid(prompterInstance.getName(), this.mDialogPrompterInstances)) {
            Logger.error(TAG, "createPrompter: prompterName is unavailable, prompterInstance = " + prompterInstance);
            return null;
        }
        if (Objects.equals(prompterInstance, this.mTtsPrompterInstance)) {
            prompterImpl = new TextToSpeechPrompter(prompterInstance, audioAttributes, config.getAudioPlaybackMode(), this.mNativeDialogManager);
        } else {
            prompterImpl = new PrompterImpl(prompterInstance, audioAttributes, config.getAudioPlaybackMode(), this.mNativeDialogManager);
        }
        this.mPrompterMap.put(prompterInstance.getName(), prompterImpl);
        return prompterImpl;
    }

    @Override // cerence.ark.assistant.framework.Framework
    public void destroyPrompter(IPrompter iPrompter) {
        if (!(iPrompter instanceof PrompterImpl)) {
            Logger.warn(TAG, "destroyPrompter: prompter is invalid");
            return;
        }
        PrompterImpl prompterImpl = (PrompterImpl) iPrompter;
        this.mPrompterMap.remove(prompterImpl.getPrompterInstance().getName());
        prompterImpl.destroy();
    }

    @Override // cerence.ark.assistant.framework.Framework
    public void updateDynamicContent(String str, List<String> list, IResultCallback<Void, ErrorCode> iResultCallback) {
        String[] strArr = new String[0];
        if (list != null && !list.isEmpty()) {
            strArr = (String[]) list.toArray(new String[0]);
        }
        DmResultCode dmResultCodeUpdateDynamicContent = this.mNativeDialogManager.updateDynamicContent(str, strArr);
        Logger.info(TAG, Utils.format("%s: %s, contentType = %s, update list num = %s", "updateDynamicContent", dmResultCodeUpdateDynamicContent.name(), str, Integer.valueOf(strArr.length)));
        if (FrameworkUtils.isSuccess(dmResultCodeUpdateDynamicContent)) {
            Utils.notifySuccess(iResultCallback);
        } else {
            Utils.notifyError(FrameworkUtils.getErrorCode(dmResultCodeUpdateDynamicContent), iResultCallback);
        }
    }

    @Override // cerence.ark.assistant.framework.Framework
    public void setWuwConfig(WuwConfig wuwConfig, IResultCallback<Void, ErrorCode> iResultCallback) {
        if (wuwConfig == null) {
            Logger.error(TAG, Utils.format("%s: wuwConfig is null", "setWuwConfig"));
            Utils.notifyError(ErrorCode.PARAMETER_INVALID, iResultCallback);
            return;
        }
        String jsonString = wuwConfig.toJsonString();
        String str = TAG;
        Logger.info(str, Utils.format2Argv("setWuwConfig", jsonString));
        DmResultCode wuw = this.mNativeDialogManager.setWuw(jsonString);
        Logger.info(str, Utils.format2Argv("setWuwConfig", wuw.name()));
        if (FrameworkUtils.isSuccess(wuw)) {
            Utils.notifySuccess(iResultCallback);
        } else {
            Utils.notifyError(FrameworkUtils.getErrorCode(wuw), iResultCallback);
        }
    }

    @Override // cerence.ark.assistant.framework.Framework
    public void updateAssistantContext(AssistantContextType assistantContextType, String str, IResultCallback<Void, ErrorCode> iResultCallback) {
        if (assistantContextType == null) {
            Logger.error(TAG, Utils.format("%s: context type is null", "updateAssistantContext"));
            Utils.notifyError(ErrorCode.PARAMETER_INVALID, iResultCallback);
            return;
        }
        String str2 = TAG;
        Logger.debug(str2, Utils.format("%s: contextType = %s, contextData = %s", "updateAssistantContext", assistantContextType.name(), str));
        DmResultCode dmResultCodeUpdateSystemContext = this.mNativeDialogManager.updateSystemContext(FrameworkUtils.getCloudParamType(assistantContextType), str);
        Logger.info(str2, Utils.format2Argv("updateAssistantContext", dmResultCodeUpdateSystemContext.name()));
        if (FrameworkUtils.isSuccess(dmResultCodeUpdateSystemContext)) {
            Utils.notifySuccess(iResultCallback);
        } else {
            Utils.notifyError(FrameworkUtils.getErrorCode(dmResultCodeUpdateSystemContext), iResultCallback);
        }
    }

    @Override // cerence.ark.assistant.framework.Framework
    public void setErrorListener(ErrorListener errorListener) {
        this.mErrorListener = errorListener;
    }

    @Override // cerence.ark.assistant.framework.Framework
    public AssistantState getAssistantState() {
        AssistantState assistantState;
        synchronized (this.mAssistantStateLock) {
            assistantState = this.mCurrentState;
        }
        return assistantState;
    }

    @Override // cerence.ark.assistant.framework.Framework
    public void clearUserData(IResultCallback<Void, ErrorCode> iResultCallback) {
        this.mClearUserDataCallback = iResultCallback;
        DmResultCode dmResultCodeClearUserData = this.mNativeDialogManager.clearUserData();
        Logger.info(TAG, Utils.format2Argv("clearUserData", dmResultCodeClearUserData.name()));
        if (isSuccess(dmResultCodeClearUserData)) {
            return;
        }
        this.mClearUserDataCallback = null;
        Utils.notifyError(FrameworkUtils.getErrorCode(dmResultCodeClearUserData), iResultCallback);
    }

    @Override // cerence.ark.assistant.framework.Framework
    public void setDynamicWords(List<String> list, IResultCallback<Void, ErrorCode> iResultCallback) {
        String[] strArr = list == null ? new String[0] : (String[]) list.toArray(new String[0]);
        String str = TAG;
        Logger.info(str, Utils.format2Argv("setDynamicWords", Arrays.toString(strArr)));
        DmResultCode dynamicWords = this.mNativeDialogManager.setDynamicWords(strArr);
        Logger.info(str, Utils.format2Argv("setDynamicWords", dynamicWords.name()));
        if (FrameworkUtils.isSuccess(dynamicWords)) {
            Utils.notifySuccess(iResultCallback);
        } else {
            Utils.notifyError(FrameworkUtils.getErrorCode(dynamicWords), iResultCallback);
        }
    }

    @Override // cerence.ark.assistant.framework.Framework
    public void updateLocation(String str, String str2, String str3, IResultCallback<Void, ErrorCode> iResultCallback) {
        if (TextUtils.isEmpty(str)) {
            Logger.error(TAG, Utils.format("%s: the country is empty", "updateLocation"));
            Utils.notifyError(ErrorCode.PARAMETER_INVALID, iResultCallback);
            return;
        }
        String str4 = TAG;
        Logger.info(str4, Utils.format("%s: country = %s, state = %s, city = %s", "updateLocation", str, str2, str3));
        DmResultCode dmResultCodeUpdateLocation = this.mNativeDialogManager.updateLocation(str, str2, str3);
        Logger.info(str4, Utils.format2Argv("updateLocation", dmResultCodeUpdateLocation.name()));
        if (FrameworkUtils.isSuccess(dmResultCodeUpdateLocation)) {
            Utils.notifySuccess(iResultCallback);
        } else {
            Utils.notifyError(FrameworkUtils.getErrorCode(dmResultCodeUpdateLocation), iResultCallback);
        }
    }

    @Override // cerence.ark.assistant.framework.Framework
    public IDataUpdater createUpdater(IUpdateListener iUpdateListener) {
        if (this.mUpdater != null) {
            Logger.error(TAG, "createUpdater: updater already exist");
            return null;
        }
        UpdaterImpl updaterImpl = new UpdaterImpl(this.mNativeDialogManager);
        if (!updaterImpl.initUpdater(new UpdateListenerImpl(iUpdateListener)).isSuccess()) {
            return null;
        }
        this.mUpdater = updaterImpl;
        return updaterImpl;
    }

    @Override // cerence.ark.assistant.framework.Framework
    public Result<Void, ErrorCode> destroyUpdater(IDataUpdater iDataUpdater) {
        if (iDataUpdater == null) {
            Logger.error(TAG, Utils.format("%s: the parameter updater is null", "destroyUpdater"));
            return Utils.buildErrorResult(ErrorCode.PARAMETER_INVALID);
        }
        if (!(iDataUpdater instanceof UpdaterImpl)) {
            Logger.error(TAG, Utils.format("%s: updater is not as UpdaterImpl", "destroyUpdater"));
            return Utils.buildErrorResult(ErrorCode.PARAMETER_INVALID);
        }
        this.mUpdater = null;
        return ((UpdaterImpl) iDataUpdater).destroyUpdater();
    }

    @Override // cerence.ark.assistant.framework.Framework
    public String getVersion() {
        return this.mNativeDialogManager.getVersion();
    }

    @Override // cerence.ark.assistant.framework.Framework
    public List<String> getAvailableAsrLanguages() {
        ArrayList arrayList = new ArrayList();
        String[] availableAsrLanguages = this.mNativeDialogManager.getAvailableAsrLanguages();
        if (availableAsrLanguages == null || availableAsrLanguages.length == 0) {
            Logger.error(TAG, "getAvailableAsrLanguages: language list is empty");
            return arrayList;
        }
        arrayList.addAll(Arrays.asList(availableAsrLanguages));
        return arrayList;
    }

    @Override // cerence.ark.assistant.framework.Framework
    public List<String> getAvailableTtsLanguages() {
        ArrayList arrayList = new ArrayList();
        String[] availablePrompterLanguages = this.mNativeDialogManager.getAvailablePrompterLanguages();
        if (availablePrompterLanguages == null || availablePrompterLanguages.length == 0) {
            Logger.error(TAG, Utils.format("%s: language list is empty", "getAvailableTtsLanguages"));
            return arrayList;
        }
        arrayList.addAll(Arrays.asList(availablePrompterLanguages));
        return arrayList;
    }

    @Override // cerence.ark.assistant.framework.Framework
    public List<TtsVoice> getAvailableTtsVoices(String str) {
        ArrayList arrayList = new ArrayList();
        PrompterVoice[] availablePrompterVoices = this.mNativeDialogManager.getAvailablePrompterVoices(str);
        if (availablePrompterVoices == null || availablePrompterVoices.length == 0) {
            Logger.error(TAG, Utils.format("%s: prompter voice list is empty", "getAvailableTtsVoices"));
            return arrayList;
        }
        for (PrompterVoice prompterVoice : availablePrompterVoices) {
            if (prompterVoice == null) {
                Logger.warn(TAG, Utils.format("%s: prompter voice is empty", "getAvailableTtsVoices"));
            } else {
                arrayList.add(new TtsVoice.Builder().setVoiceId(prompterVoice.getVoiceId()).setLanguage(prompterVoice.getLanguage()).setVoiceName(prompterVoice.getVoiceName()).setGender(prompterVoice.getGender()).setOperatingPoint(prompterVoice.getOperatingPoint()).setVersion(prompterVoice.getVersion()).setAvailableDomains(prompterVoice.getAvailableDomains()).setType(VoiceType.fromString(prompterVoice.getType())).setSupportedBySds(prompterVoice.getSupportedBySds()).setSpeakerName(prompterVoice.getSpeakerName()).build());
            }
        }
        return arrayList;
    }

    @Override // cerence.ark.assistant.framework.Framework
    public Result<Void, ErrorCode> overrideConfiguration(String str, String str2) {
        String str3 = TAG;
        Logger.info(str3, Utils.format("overrideConfiguration: key = %s, value = %s", str, str2));
        if (TextUtils.isEmpty(str)) {
            Logger.error(str3, "overrideConfiguration: key is empty");
            return Utils.buildErrorResult(ErrorCode.PARAMETER_INVALID);
        }
        DmResultCode dmResultCodeOverrideConfiguration = this.mNativeDialogManager.overrideConfiguration(str, str2);
        Logger.info(str3, Utils.format("overrideConfiguration: %s", dmResultCodeOverrideConfiguration.name()));
        if (FrameworkUtils.isSuccess(dmResultCodeOverrideConfiguration)) {
            return new Result.Builder().buildSuccess();
        }
        return Utils.buildErrorResult(FrameworkUtils.getErrorCode(dmResultCodeOverrideConfiguration));
    }

    @Override // cerence.ark.assistant.framework.Framework
    public List<String> getLogConsumerNames() {
        String str = TAG;
        Utils.logMethodBegin(str, "getLogConsumerNames");
        String[] logConsumerNames = this.mNativeDialogManager.getLogConsumerNames();
        if (logConsumerNames != null) {
            Logger.info(str, Utils.format("%s: %s", "getLogConsumerNames", TextUtils.join("|", logConsumerNames)));
            return Arrays.asList(logConsumerNames);
        }
        Logger.error(str, Utils.format("%s: log consumer names is empty", "getLogConsumerNames"));
        return new ArrayList();
    }

    @Override // cerence.ark.assistant.framework.Framework
    public Result<Void, ErrorCode> configureLogConsumer(LogConsumer logConsumer) {
        if (logConsumer == null) {
            Logger.error(TAG, Utils.format("%s: log consumer is null", "configureLogConsumer"));
            return Utils.buildErrorResult(ErrorCode.PARAMETER_INVALID);
        }
        String str = TAG;
        Logger.info(str, Utils.format("%s: logConsumer = %s", "configureLogConsumer", logConsumer));
        DmResultCode dmResultCodeConfigureLogConsumer = this.mNativeDialogManager.configureLogConsumer(logConsumer.getName(), logConsumer.getOutput(), logConsumer.getLogModules(), logConsumer.getSuppressModules(), logConsumer.getZones());
        Logger.info(str, Utils.format2Argv("configureLogConsumer", dmResultCodeConfigureLogConsumer.name()));
        if (FrameworkUtils.isSuccess(dmResultCodeConfigureLogConsumer)) {
            return new Result.Builder().buildSuccess();
        }
        return Utils.buildErrorResult(FrameworkUtils.getErrorCode(dmResultCodeConfigureLogConsumer));
    }

    @Override // cerence.ark.assistant.framework.Framework
    public Result<Void, ErrorCode> updateUdeDataConfig(String str, String str2, String str3) {
        DmResultCode dmResultCodeUpdateUdeDataConfig = this.mNativeDialogManager.updateUdeDataConfig(str, str2, str3);
        Logger.info(TAG, Utils.format2Argv("updateUdeDataConfig", dmResultCodeUpdateUdeDataConfig.name()));
        if (FrameworkUtils.isSuccess(dmResultCodeUpdateUdeDataConfig)) {
            return new Result.Builder().buildSuccess();
        }
        return Utils.buildErrorResult(FrameworkUtils.getErrorCode(dmResultCodeUpdateUdeDataConfig));
    }

    @Override // cerence.ark.assistant.framework.Framework
    public Result<Void, ErrorCode> updateUdeRppConfig(RppLocationType rppLocationType, String str, String str2, String str3) {
        LocationType locationTypeConvertLocationType = FrameworkUtils.convertLocationType(rppLocationType);
        if (locationTypeConvertLocationType == null) {
            Logger.error(TAG, Utils.format("updateUdeRppConfig: locationType is null", new Object[0]));
            return Utils.buildErrorResult(ErrorCode.PARAMETER_INVALID);
        }
        DmResultCode dmResultCodeUpdateUdeRppConfig = this.mNativeDialogManager.updateUdeRppConfig(locationTypeConvertLocationType, str, str2, str3);
        Logger.info(TAG, Utils.format2Argv("updateUdeRppConfig", dmResultCodeUpdateUdeRppConfig.name()));
        if (FrameworkUtils.isSuccess(dmResultCodeUpdateUdeRppConfig)) {
            return new Result.Builder().buildSuccess();
        }
        return Utils.buildErrorResult(FrameworkUtils.getErrorCode(dmResultCodeUpdateUdeRppConfig));
    }

    @Override // cerence.ark.assistant.framework.Framework
    public Result<Void, ErrorCode> requestTrimMemory() {
        DmResultCode dmResultCodeRequestTrimMemory = this.mNativeDialogManager.requestTrimMemory();
        Logger.info(TAG, Utils.format2Argv("requestTrimMemory", dmResultCodeRequestTrimMemory.name()));
        if (FrameworkUtils.isSuccess(dmResultCodeRequestTrimMemory)) {
            return new Result.Builder().buildSuccess();
        }
        return Utils.buildErrorResult(FrameworkUtils.getErrorCode(dmResultCodeRequestTrimMemory));
    }

    @Override // cerence.ark.assistant.dm.NativeDialogManager.DMCallback
    public void onRequest(String str, String str2) {
        FrameworkMessageListener frameworkMessageListener = this.mMessageListener;
        if (frameworkMessageListener == null) {
            Logger.error(TAG, "onRequest: messageListener is null");
        } else if (TextUtils.isEmpty(str)) {
            Logger.error(TAG, Utils.format("onRequest: activeZone = %s, jsonData is empty", str2));
        } else {
            frameworkMessageListener.onRequestReceived(str2, str);
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
    public void onPromptAudio(String str, byte[] bArr, int i) {
        if (this.mDialogPrompterNames.contains(str)) {
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
        PrompterInstance prompterInstance = this.mTtsPrompterInstance;
        if (prompterInstance != null && str.equals(prompterInstance.getName())) {
            FrameworkMessageListener frameworkMessageListener = this.mMessageListener;
            if (frameworkMessageListener != null) {
                frameworkMessageListener.onPromptAudio(str, bArr, i);
                return;
            }
            return;
        }
        PrompterImpl prompterImpl = this.mPrompterMap.get(str);
        if (prompterImpl == null) {
            Logger.error(TAG, Utils.format("onPromptAudio: prompter is null, instanceName = %s, promptId = %s", str, Integer.valueOf(i)));
        } else {
            prompterImpl.onPlayAudio(bArr, i);
            this.mAudioLogger.savePrompterAudio(bArr, str, i);
        }
    }

    @Override // cerence.ark.assistant.dm.NativeDialogManager.DMCallback
    public void onAbortPrompt(String str, int i) {
        if (this.mDialogPrompterNames.contains(str)) {
            synchronized (this.mPlayerLock) {
                IAudioPlayer iAudioPlayer = this.mAudioPlayer;
                if (iAudioPlayer != null) {
                    iAudioPlayer.onStop(true);
                }
                this.mAudioLogger.closeDialogPrompterFileStream();
            }
            return;
        }
        PrompterInstance prompterInstance = this.mTtsPrompterInstance;
        if (prompterInstance == null || !str.equals(prompterInstance.getName())) {
            this.mAudioLogger.closePrompterFileStream(str);
            PrompterImpl prompterImpl = this.mPrompterMap.get(str);
            if (prompterImpl == null) {
                Logger.error(TAG, Utils.format("onAbortPrompt: prompter is null, instanceName = %s, promptId = %s", str, Integer.valueOf(i)));
            } else {
                prompterImpl.onAbortPrompt(i);
            }
        }
    }

    @Override // cerence.ark.assistant.dm.NativeDialogManager.DMCallback
    public void onEndOfStream(String str, int i) {
        String str2 = TAG;
        Logger.info(str2, Utils.format("onEndOfStream: instanceName = %s, promptId = %s", str, Integer.valueOf(i)));
        if (this.mDialogPrompterNames.contains(str)) {
            synchronized (this.mPlayerLock) {
                IAudioPlayer iAudioPlayer = this.mAudioPlayer;
                if (iAudioPlayer != null) {
                    iAudioPlayer.onEndOfStream();
                }
                this.mAudioLogger.closeDialogPrompterFileStream();
            }
            return;
        }
        PrompterInstance prompterInstance = this.mTtsPrompterInstance;
        if (prompterInstance == null || !str.equals(prompterInstance.getName())) {
            this.mAudioLogger.closePrompterFileStream(str);
            PrompterImpl prompterImpl = this.mPrompterMap.get(str);
            if (prompterImpl == null) {
                Logger.error(str2, Utils.format("onEndOfStream: prompter is null, instanceName = %s, promptId = %s", str, Integer.valueOf(i)));
            } else {
                prompterImpl.onEndOfStream(i);
            }
        }
    }

    @Override // cerence.ark.assistant.dm.NativeDialogManager.DMCallback
    public void onPromptEvent(String str, String str2, int i) {
        if (this.mDialogPrompterNames.contains(str)) {
            return;
        }
        PrompterInstance prompterInstance = this.mTtsPrompterInstance;
        if (prompterInstance != null && str.equals(prompterInstance.getName())) {
            FrameworkMessageListener frameworkMessageListener = this.mMessageListener;
            if (frameworkMessageListener != null) {
                frameworkMessageListener.onPromptEvent(str, str2, i);
                return;
            }
            return;
        }
        PrompterImpl prompterImpl = this.mPrompterMap.get(str);
        if (prompterImpl == null) {
            Logger.error(TAG, Utils.format("onPromptEvent: prompter is null, instanceName = %s, promptId = %s, jsonEvent = %s", str, Integer.valueOf(i), str2));
            return;
        }
        try {
            PromptEvent promptEvent = FrameworkUtils.parsePromptEvent(str2);
            if (promptEvent == null) {
                return;
            }
            Logger.info(TAG, Utils.format("onPromptEvent: instanceName = %s, promptId = %s, promptEvent = %s", str, Integer.valueOf(i), promptEvent.name()));
            prompterImpl.onPromptEvent(i, promptEvent);
        } catch (JSONException e) {
            String str3 = TAG;
            Logger.error(str3, Utils.format("onPromptEvent: exception, instanceName = %s, promptId = %s, jsonEvent = %s", str, Integer.valueOf(i), str2));
            Logger.error(str3, "onPromptEvent: exception", e);
        }
    }

    @Override // cerence.ark.assistant.dm.NativeDialogManager.DMCallback
    public void onPromptError(String str, String str2, int i) {
        String str3 = TAG;
        Logger.error(str3, Utils.format("onPromptError: instanceName = %s, promptId = %s, jsonError = %s", str, Integer.valueOf(i), str2));
        if (this.mDialogPrompterNames.contains(str)) {
            return;
        }
        PrompterInstance prompterInstance = this.mTtsPrompterInstance;
        if (prompterInstance != null && str.equals(prompterInstance.getName())) {
            FrameworkMessageListener frameworkMessageListener = this.mMessageListener;
            if (frameworkMessageListener != null) {
                frameworkMessageListener.onPromptError(str, str2, i);
                return;
            }
            return;
        }
        PrompterImpl prompterImpl = this.mPrompterMap.get(str);
        if (prompterImpl == null) {
            Logger.error(str3, Utils.format("onPromptError: prompter is null, instanceName = %s, promptId = %s, jsonError = %s", str, Integer.valueOf(i), str2));
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str2);
            String strOptString = jSONObject.optString("type");
            String strOptString2 = jSONObject.optString("error_msg");
            if (JsonKey.PROMPT_ERROR_TYPE_VALUE.equals(strOptString)) {
                prompterImpl.onPrompterError(i, PrompterError.fromString(strOptString2));
            }
        } catch (JSONException e) {
            String str4 = TAG;
            Logger.error(str4, Utils.format("onPromptError: exception, instanceName = %s, promptId = %s, jsonError = %s", str, Integer.valueOf(i), str2));
            Logger.error(str4, "onPromptError: exception", e);
        }
    }

    @Override // cerence.ark.assistant.dm.NativeDialogManager.DMCallback
    public void onStartRecording() {
        Logger.info(TAG, "onStartRecording");
        synchronized (this.mRecorderLock) {
            initializeAudioRecorder();
        }
    }

    @Override // cerence.ark.assistant.dm.NativeDialogManager.DMCallback
    public void onStopRecording(boolean z) {
        Logger.info(TAG, "onStopRecording");
        this.mAudioLogger.closeMicFileStream();
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
    public void onVpaAudio(byte[] bArr, long j) {
        VpaAudioListener vpaAudioListener = this.mVpaAudioListener;
        if (vpaAudioListener == null) {
            return;
        }
        this.mAudioLogger.saveVpaAudio(bArr, VPA_AUDIO_FILE_NAME);
        vpaAudioListener.onVpaAudio(bArr, j);
    }

    @Override // cerence.ark.assistant.dm.NativeDialogManager.DMCallback
    public void onFatalError(FatalErrorCode fatalErrorCode, String str) {
        String str2 = TAG;
        Logger.error(str2, Utils.format2Argv("onFatalError", str));
        ErrorListener errorListener = this.mErrorListener;
        if (errorListener != null) {
            ErrorInfo errorInfo = new ErrorInfo();
            errorInfo.setErrorCode(FrameworkUtils.getErrorCode(fatalErrorCode)).setErrorMessage(str);
            errorListener.onError(errorInfo);
            return;
        }
        Logger.error(str2, Utils.format("%s: error listener is null", "onFatalError"));
    }

    @Override // cerence.ark.assistant.dm.NativeDialogManager.RecognitionCallback
    public void onBeginningOfSpeech() {
        String str = TAG;
        Logger.info(str, "onBeginningOfSpeech");
        FrameworkMessageListener frameworkMessageListener = this.mMessageListener;
        if (frameworkMessageListener != null) {
            frameworkMessageListener.onBeginningOfSpeech();
        } else {
            Logger.error(str, "onBeginningOfSpeech: messageListener is null");
        }
    }

    @Override // cerence.ark.assistant.dm.NativeDialogManager.RecognitionCallback
    public void onEndOfSpeech() {
        String str = TAG;
        Logger.info(str, "onEndOfSpeech");
        FrameworkMessageListener frameworkMessageListener = this.mMessageListener;
        if (frameworkMessageListener != null) {
            frameworkMessageListener.onEndOfSpeech();
        } else {
            Logger.error(str, "onEndOfSpeech: messageListener is null");
        }
    }

    @Override // cerence.ark.assistant.dm.NativeDialogManager.RecognitionCallback
    public void onError(RecognitionErrorCode recognitionErrorCode) {
        String str = TAG;
        Logger.error(str, Utils.format("onError: %s", recognitionErrorCode));
        recoverVoiceAssistant();
        FrameworkMessageListener frameworkMessageListener = this.mMessageListener;
        if (frameworkMessageListener != null) {
            frameworkMessageListener.onError(recognitionErrorCode);
        } else {
            Logger.error(str, "onError: messageListener is null");
        }
    }

    @Override // cerence.ark.assistant.dm.NativeDialogManager.RecognitionCallback
    public void onReadyForSpeech() {
        String str = TAG;
        Logger.info(str, "onReadyForSpeech");
        FrameworkMessageListener frameworkMessageListener = this.mMessageListener;
        if (frameworkMessageListener != null) {
            frameworkMessageListener.onReadyForSpeech();
        } else {
            Logger.error(str, "onReadyForSpeech: messageListener is null");
        }
    }

    @Override // cerence.ark.assistant.dm.NativeDialogManager.RecognitionCallback
    public void onRecognizedSpeech(String str, boolean z) {
        String str2 = TAG;
        Logger.debug(str2, Utils.format("onRecognizedSpeech: text: %s, isFinal: %s", str, Boolean.valueOf(z)));
        if (z) {
            recoverVoiceAssistant();
        }
        FrameworkMessageListener frameworkMessageListener = this.mMessageListener;
        if (frameworkMessageListener != null) {
            frameworkMessageListener.onRecognizedSpeech(str, z);
        } else {
            Logger.error(str2, "onRecognizedSpeech: messageListener is null");
        }
    }

    @Override // cerence.ark.assistant.dm.NativeDialogManager.RecognitionCallback
    public void onRmsChanged(int i) {
        FrameworkMessageListener frameworkMessageListener = this.mMessageListener;
        if (frameworkMessageListener != null) {
            frameworkMessageListener.onRmsChanged(i);
        } else {
            Logger.error(TAG, "onRmsChanged: messageListener is null");
        }
    }

    @Override // cerence.ark.assistant.framework.Framework
    public void startListening(String str, IResultCallback<Void, ErrorCode> iResultCallback) {
        DmResultCode dmResultCodeStartListening;
        String str2 = TAG;
        Logger.info(str2, Utils.format("startListening: language: %s", str));
        if (getAssistantState() == AssistantState.INITIALIZED) {
            if (TextUtils.isEmpty(str)) {
                Logger.error(str2, "startListening: language is empty");
                Utils.notifyError(ErrorCode.LANGUAGE_NULL, iResultCallback);
                return;
            } else {
                dmResultCodeStartListening = this.mNativeDialogManager.startListening(this, str);
                Logger.info(str2, Utils.format("startListening: %s", dmResultCodeStartListening));
            }
        } else if (getAssistantState() == AssistantState.RUNNING) {
            if (TextUtils.isEmpty(str)) {
                AssistantConfiguration assistantConfiguration = this.mAssistantConfiguration;
                str = assistantConfiguration == null ? null : assistantConfiguration.getAsrLanguage();
                if (TextUtils.isEmpty(str)) {
                    Logger.error(str2, "startListening: asrLanguage is empty");
                    Utils.notifyError(ErrorCode.LANGUAGE_NULL, iResultCallback);
                    return;
                }
                Logger.info(str2, Utils.format("startListening: the language of AssistantConfiguration: %s", str));
            }
            stopVoiceAssistant(null);
            dmResultCodeStartListening = this.mNativeDialogManager.startListening(this, str);
            Logger.info(str2, Utils.format("startListening: %s", dmResultCodeStartListening));
        } else {
            dmResultCodeStartListening = DmResultCode.ACE_NOT_INITIALIZED;
            Logger.error(str2, "startListening: assistant is not initialized");
        }
        if (FrameworkUtils.isSuccess(dmResultCodeStartListening)) {
            Utils.notifySuccess(iResultCallback);
        } else {
            Utils.notifyError(FrameworkUtils.getErrorCode(dmResultCodeStartListening), iResultCallback);
        }
    }

    @Override // cerence.ark.assistant.framework.Framework
    public void stopListening(IResultCallback<Void, ErrorCode> iResultCallback) {
        DmResultCode dmResultCodeStopListening;
        if (Utils.isInitialized(getAssistantState())) {
            dmResultCodeStopListening = this.mNativeDialogManager.stopListening();
            Logger.info(TAG, Utils.format("stopListening: %s", dmResultCodeStopListening));
        } else {
            dmResultCodeStopListening = DmResultCode.ACE_NOT_INITIALIZED;
        }
        if (FrameworkUtils.isSuccess(dmResultCodeStopListening)) {
            Utils.notifySuccess(iResultCallback);
        } else {
            Utils.notifyError(FrameworkUtils.getErrorCode(dmResultCodeStopListening), iResultCallback);
        }
    }

    @Override // cerence.ark.assistant.framework.Framework
    public void cancelListening(IResultCallback<Void, ErrorCode> iResultCallback) {
        DmResultCode dmResultCodeCancelListening;
        if (Utils.isInitialized(getAssistantState())) {
            dmResultCodeCancelListening = this.mNativeDialogManager.cancelListening();
            Logger.info(TAG, Utils.format("cancelListening: %s", dmResultCodeCancelListening));
            recoverVoiceAssistant();
        } else {
            dmResultCodeCancelListening = DmResultCode.ACE_NOT_INITIALIZED;
        }
        if (FrameworkUtils.isSuccess(dmResultCodeCancelListening)) {
            Utils.notifySuccess(iResultCallback);
        } else {
            Utils.notifyError(FrameworkUtils.getErrorCode(dmResultCodeCancelListening), iResultCallback);
        }
    }

    @Override // cerence.ark.assistant.dm.NativeDialogManager.DMCallback
    public void onEvent(String str) {
        IResultCallback<Void, ErrorCode> iResultCallback;
        Logger.info(TAG, Utils.format("onEvent: %s", str));
        try {
            String strOptString = new JSONObject(str).optString("event");
            if (JsonKey.CLEAR_USER_DATA_SUCCESS.equals(strOptString)) {
                IResultCallback<Void, ErrorCode> iResultCallback2 = this.mClearUserDataCallback;
                if (iResultCallback2 != null) {
                    Utils.notifySuccess(iResultCallback2);
                    this.mClearUserDataCallback = null;
                }
            } else if (JsonKey.CLEAR_USER_DATA_FAILED.equals(strOptString) && (iResultCallback = this.mClearUserDataCallback) != null) {
                Utils.notifyError(ErrorCode.UNKNOWN, iResultCallback);
                this.mClearUserDataCallback = null;
            }
        } catch (Exception e) {
            Logger.error(TAG, "onEvent: parse json exception", e);
        }
    }

    private void updateAssistantState(AssistantState assistantState) {
        synchronized (this.mAssistantStateLock) {
            Logger.info(TAG, Utils.format("updateAssistantState: %s -> %s", this.mCurrentState.name(), assistantState.name()));
            this.mCurrentState = assistantState;
        }
        Utils.notifyAssistantStateChanged(assistantState);
    }

    private boolean isSuccess(DmResultCode dmResultCode) {
        return dmResultCode == DmResultCode.ACE_OK;
    }

    private void initializeAudioRecorder() {
        AudioRecorderProvider defaultAudioRecorderProvider;
        AudioRecorderManager audioRecorderManager = this.mAudioRecorderManager;
        if (audioRecorderManager != null && audioRecorderManager.isInitialized()) {
            this.mAudioRecorderManager.getAudioData();
            return;
        }
        Config config = this.mInitConfig;
        if (config == null) {
            Logger.error(TAG, "initializeAudioRecorder: init config is null");
            return;
        }
        this.mAudioRecorderManager = new AudioRecorderManager(new IAudioListener() { // from class: cerence.ark.assistant.framework.impl.ArkFramework$$ExternalSyntheticLambda4
            @Override // cerence.ark.assistant.framework.impl.recorder.IAudioListener
            public final void onAudioAvailable(byte[] bArr) {
                this.f$0.m73xec9e6c9f(bArr);
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
        this.mAudioRecorderManager.initialize(iAudioRecorderCreateAudioRecorder, new IResultCallback() { // from class: cerence.ark.assistant.framework.impl.ArkFramework$$ExternalSyntheticLambda5
            @Override // cerence.ark.assistant.common.callback.IResultCallback
            public final void onResult(Result result) {
                this.f$0.m74x30298a60(result);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$initializeAudioRecorder$2$cerence-ark-assistant-framework-impl-ArkFramework, reason: not valid java name */
    /* synthetic */ void m73xec9e6c9f(byte[] bArr) {
        this.mNativeDialogManager.feedAudio(bArr, bArr.length);
        this.mAudioLogger.saveMicAudio(bArr, this.mMicAudioFolderName);
    }

    /* JADX INFO: renamed from: lambda$initializeAudioRecorder$3$cerence-ark-assistant-framework-impl-ArkFramework, reason: not valid java name */
    /* synthetic */ void m74x30298a60(Result result) {
        if (result.isSuccess()) {
            this.mAudioRecorderManager.getAudioData();
            return;
        }
        ErrorListener errorListener = this.mErrorListener;
        if (errorListener != null) {
            ErrorInfo errorInfo = new ErrorInfo();
            errorInfo.setErrorCode(ErrorInfo.ErrorCode.FATAL_INITIALIZE_AUDIO).setErrorMessage(ErrorMessage.AUDIO_RECORD_INIT_FAIL);
            errorListener.onError(errorInfo);
        }
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
                this.mAudioRecorderManager.destroy(new IResultCallback() { // from class: cerence.ark.assistant.framework.impl.ArkFramework$$ExternalSyntheticLambda3
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

    private void createVpaAudioListener() {
        VpaAudioListenerProvider provider = VpaAudioListenerProvider.getProvider(Utils.getContext());
        if (provider == null) {
            Logger.warn(TAG, "VpaAudioListenerProvider is null");
        } else {
            this.mVpaAudioListener = provider.createAudioListener();
        }
    }

    private void recoverVoiceAssistant() {
        synchronized (this.mRecoveryHandlerLock) {
            if (this.mRecoveryThreadDestroyed) {
                Logger.warn(TAG, "recover thread was destroyed");
                return;
            }
            if (this.mRecoveryHandler == null) {
                Logger.info(TAG, "create recovery voice assistant thread");
                this.mRecoveryThreadDestroyed = false;
                HandlerThread handlerThread = new HandlerThread("ca_recovery_voice_assistant");
                handlerThread.start();
                this.mRecoveryHandler = new Handler(handlerThread.getLooper());
            }
            this.mRecoveryHandler.post(new Runnable() { // from class: cerence.ark.assistant.framework.impl.ArkFramework$$ExternalSyntheticLambda6
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.m75xa822c7ee();
                }
            });
        }
    }

    /* JADX INFO: renamed from: lambda$recoverVoiceAssistant$5$cerence-ark-assistant-framework-impl-ArkFramework, reason: not valid java name */
    /* synthetic */ void m75xa822c7ee() {
        AssistantConfiguration assistantConfiguration = this.mAssistantConfiguration;
        if (getAssistantState() != AssistantState.INITIALIZED || assistantConfiguration == null) {
            return;
        }
        Logger.info(TAG, "recover voice assistant");
        startVoiceAssistant(assistantConfiguration, null);
    }
}
