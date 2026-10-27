package cerence.ark.assistant.framework.impl.prompter;

import android.media.AudioAttributes;
import android.text.TextUtils;
import cerence.ark.assistant.api.prompter.PrompterInstance;
import cerence.ark.assistant.api.prompter.VoiceType;
import cerence.ark.assistant.common.Logger;
import cerence.ark.assistant.common.Scheduler;
import cerence.ark.assistant.common.callback.Result;
import cerence.ark.assistant.dm.NativeDialogManager;
import cerence.ark.assistant.dm.data.DmInitParams;
import cerence.ark.assistant.dm.data.DmPromptDataType;
import cerence.ark.assistant.dm.data.DmResultCode;
import cerence.ark.assistant.framework.audio.AudioChannel;
import cerence.ark.assistant.framework.audio.AudioFormat;
import cerence.ark.assistant.framework.audio.SampleRate;
import cerence.ark.assistant.framework.audio.player.IAudioPlayer;
import cerence.ark.assistant.framework.data.AudioPlaybackMode;
import cerence.ark.assistant.framework.data.ErrorCode;
import cerence.ark.assistant.framework.impl.FrameworkUtils;
import cerence.ark.assistant.framework.impl.PromptEvent;
import cerence.ark.assistant.framework.prompter.IPrompter;
import cerence.ark.assistant.framework.prompter.PlayState;
import cerence.ark.assistant.framework.prompter.PromptDataType;
import cerence.ark.assistant.framework.prompter.PromptPlayerProvider;
import cerence.ark.assistant.framework.prompter.PrompterCallback;
import cerence.ark.assistant.framework.prompter.PrompterError;
import cerence.ark.assistant.framework.prompter.PrompterParameterType;
import cerence.ark.assistant.library.Utils;
import cerence.ark.assistant.library.exception.ArkAssistantException;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes2.dex */
public class PrompterImpl implements IPrompter {
    private static final String TAG = "PrompterImpl";
    private IAudioPlayer mAppPlayer;
    private final AudioAttributes mAudioAttributes;
    private final AudioPlaybackMode mDefaultPlaybackMode;
    private volatile boolean mIsPause;
    private final NativeDialogManager mNativeDialogManager;
    private final PrompterInstance mPrompterInstance;
    private IAudioPlayer mSdkPlayer;
    private final Map<Integer, PrompterCallback> mCallbackMap = new ConcurrentHashMap(16);
    private final Map<Integer, IAudioPlayer> mPlayerMap = new ConcurrentHashMap(16);

    public PrompterImpl(PrompterInstance prompterInstance, AudioAttributes audioAttributes, AudioPlaybackMode audioPlaybackMode, NativeDialogManager nativeDialogManager) {
        this.mPrompterInstance = prompterInstance;
        this.mAudioAttributes = audioAttributes;
        this.mDefaultPlaybackMode = audioPlaybackMode;
        this.mNativeDialogManager = nativeDialogManager;
    }

    private synchronized IAudioPlayer initializePlayer(AudioPlaybackMode audioPlaybackMode) {
        if (audioPlaybackMode == AudioPlaybackMode.APP) {
            IAudioPlayer iAudioPlayer = this.mAppPlayer;
            if (iAudioPlayer != null) {
                return iAudioPlayer;
            }
            PromptPlayerProvider provider = PromptPlayerProvider.getProvider(Utils.getContext());
            if (provider == null) {
                throw new ArkAssistantException("If playback mode is APP, you should provide the customized audio player by audio player provider.");
            }
            IAudioPlayer iAudioPlayerCreatePromptPlayer = provider.createPromptPlayer(this.mPrompterInstance.getName());
            if (iAudioPlayerCreatePromptPlayer == null) {
                throw new ArkAssistantException("Create audio player is null.");
            }
            String str = TAG;
            Logger.info(str, "initializePlayer: " + iAudioPlayerCreatePromptPlayer.getClass().getSimpleName());
            if (iAudioPlayerCreatePromptPlayer.onInitialize(AudioChannel.CHANNEL_MONO, AudioFormat.PCM_16BIT, SampleRate.SAMPLE_RATE_HZ_22050, this.mAudioAttributes)) {
                this.mAppPlayer = iAudioPlayerCreatePromptPlayer;
                return iAudioPlayerCreatePromptPlayer;
            }
            Logger.error(str, "initializePlayer: initialize audio prompter failure");
        } else {
            IAudioPlayer iAudioPlayer2 = this.mSdkPlayer;
            if (iAudioPlayer2 != null) {
                return iAudioPlayer2;
            }
            IAudioPlayer iAudioPlayerCreatePromptPlayer2 = new DefaultPromptPlayerProvider().createPromptPlayer(this.mPrompterInstance.getName());
            String str2 = TAG;
            Logger.info(str2, "initializePlayer: " + iAudioPlayerCreatePromptPlayer2.getClass().getSimpleName());
            if (iAudioPlayerCreatePromptPlayer2.onInitialize(AudioChannel.CHANNEL_MONO, AudioFormat.PCM_16BIT, SampleRate.SAMPLE_RATE_HZ_22050, this.mAudioAttributes)) {
                this.mSdkPlayer = iAudioPlayerCreatePromptPlayer2;
                return iAudioPlayerCreatePromptPlayer2;
            }
            Logger.error(str2, "initializePlayer: initialize audio prompter failure");
        }
        return null;
    }

    public final synchronized void onPlayAudio(byte[] bArr, int i) {
        IAudioPlayer iAudioPlayer = this.mPlayerMap.get(Integer.valueOf(i));
        if (iAudioPlayer == null) {
            Logger.error(TAG, "playAudio: audio player is null, promptId = " + i);
        } else {
            iAudioPlayer.onPlayAudio(bArr);
        }
    }

    public final synchronized void onAbortPrompt(int i) {
        IAudioPlayer iAudioPlayer = this.mPlayerMap.get(Integer.valueOf(i));
        if (iAudioPlayer == null) {
            Logger.error(TAG, "onAbortPrompt: audio player is null, promptId = " + i);
        } else {
            iAudioPlayer.onStop(!this.mIsPause);
        }
    }

    public final synchronized void onEndOfStream(int i) {
        IAudioPlayer iAudioPlayer = this.mPlayerMap.get(Integer.valueOf(i));
        if (iAudioPlayer == null) {
            Logger.error(TAG, "onEndOfStream: audio player is null, promptId = " + i);
        } else {
            iAudioPlayer.onEndOfStream();
        }
    }

    public final void onPromptEvent(final int i, final PromptEvent promptEvent) {
        if (PromptEvent.PROMPT_PLAY_STARTED == promptEvent) {
            notifyPlayState(i, PlayState.STARTED);
            return;
        }
        if (PromptEvent.PROMPT_PLAY_ENDED == promptEvent) {
            if (this.mIsPause) {
                notifyPlayState(i, PlayState.PAUSED);
            }
        } else {
            if (PromptEvent.PROMPT_FINISHED == promptEvent) {
                notifyPlayState(i, PlayState.STOPPED);
                return;
            }
            if (PromptEvent.PROMPT_CREATED == promptEvent) {
                final PrompterCallback prompterCallback = this.mCallbackMap.get(Integer.valueOf(i));
                Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.framework.impl.prompter.PrompterImpl$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        PrompterImpl.lambda$onPromptEvent$0(i, promptEvent, prompterCallback);
                    }
                });
            } else if (PromptEvent.PROMPT_DESTROYED == promptEvent) {
                this.mPlayerMap.remove(Integer.valueOf(i));
                final PrompterCallback prompterCallbackRemove = this.mCallbackMap.remove(Integer.valueOf(i));
                Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.framework.impl.prompter.PrompterImpl$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        PrompterImpl.lambda$onPromptEvent$1(i, promptEvent, prompterCallbackRemove);
                    }
                });
            }
        }
    }

    static /* synthetic */ void lambda$onPromptEvent$0(int i, PromptEvent promptEvent, PrompterCallback prompterCallback) {
        Logger.info(TAG, Utils.format("promptId = %s, promptEvent = %s", Integer.valueOf(i), promptEvent.name()));
        if (prompterCallback != null) {
            prompterCallback.onPromptCreated();
        }
    }

    static /* synthetic */ void lambda$onPromptEvent$1(int i, PromptEvent promptEvent, PrompterCallback prompterCallback) {
        Logger.info(TAG, Utils.format("promptId = %s, promptEvent = %s", Integer.valueOf(i), promptEvent.name()));
        if (prompterCallback != null) {
            prompterCallback.onPromptDestroyed();
        }
    }

    public final void onPrompterError(int i, PrompterError prompterError) {
        notifyPrompterError(i, prompterError);
    }

    @Override // cerence.ark.assistant.framework.prompter.BasePrompter
    public PrompterInstance getPrompterInstance() {
        return this.mPrompterInstance;
    }

    @Override // cerence.ark.assistant.framework.prompter.BasePrompter
    public Result<Void, ErrorCode> setPrompterParameter(PrompterParameterType prompterParameterType, int i) {
        if (this.mPrompterInstance.getType() != VoiceType.LOCAL) {
            Logger.error(TAG, "setPrompterParameter: only support local type instance");
            return Utils.buildErrorResult(ErrorCode.INVALID_OPERATION);
        }
        DmResultCode prompterParameter = this.mNativeDialogManager.setPrompterParameter(this.mPrompterInstance.getName(), prompterParameterType.ordinal(), i);
        Logger.info(TAG, "setPrompterParameter: " + prompterParameter);
        if (FrameworkUtils.isSuccess(prompterParameter)) {
            return new Result.Builder().buildSuccess();
        }
        return Utils.buildErrorResult(FrameworkUtils.getErrorCode(prompterParameter));
    }

    @Override // cerence.ark.assistant.framework.prompter.IPrompter
    public Result<Void, ErrorCode> prepareVoice(String str, String str2) {
        String str3 = TAG;
        Logger.info(str3, Utils.format("prepareVoice: instance: %s, type: %s, value: %s", this.mPrompterInstance, str, str2));
        DmResultCode dmResultCodePrepareVoice = this.mNativeDialogManager.prepareVoice(this.mPrompterInstance.getName(), str, str2);
        Logger.info(str3, "prepareVoice: " + dmResultCodePrepareVoice);
        if (FrameworkUtils.isSuccess(dmResultCodePrepareVoice)) {
            return new Result.Builder().buildSuccess();
        }
        return Utils.buildErrorResult(FrameworkUtils.getErrorCode(dmResultCodePrepareVoice));
    }

    @Override // cerence.ark.assistant.framework.prompter.IPrompter
    public final int play(String str, boolean z, String str2, String str3, PrompterCallback prompterCallback) {
        return play(str, PromptDataType.TEXT, z, null, str2, str3, prompterCallback);
    }

    @Override // cerence.ark.assistant.framework.prompter.IPrompter
    public int play(String str, PromptDataType promptDataType, boolean z, String str2, PrompterCallback prompterCallback) {
        return play(str, promptDataType, z, null, str2, null, prompterCallback);
    }

    @Override // cerence.ark.assistant.framework.prompter.IPrompter
    public final int play(String str, PromptDataType promptDataType, boolean z, AudioPlaybackMode audioPlaybackMode, String str2, String str3, PrompterCallback prompterCallback) {
        IAudioPlayer iAudioPlayerInitializePlayer;
        if (TextUtils.isEmpty(str)) {
            Logger.error(TAG, "play: prompt data is empty");
            return -1;
        }
        if (Objects.isNull(promptDataType)) {
            Logger.error(TAG, "play: promptDataType is null");
            return -1;
        }
        AudioPlaybackMode audioPlaybackMode2 = Objects.isNull(audioPlaybackMode) ? this.mDefaultPlaybackMode : audioPlaybackMode;
        if (needInitializeAudioPlayer(audioPlaybackMode2)) {
            iAudioPlayerInitializePlayer = initializePlayer(audioPlaybackMode2);
            if (iAudioPlayerInitializePlayer == null) {
                return -1;
            }
        } else {
            iAudioPlayerInitializePlayer = null;
        }
        this.mIsPause = false;
        int[] iArr = new int[1];
        DmInitParams.AudioPlaybackMode dmPlaybackMode = getDmPlaybackMode(audioPlaybackMode2);
        DmPromptDataType dmPromptDataTypeConvertDmPromptDataType = convertDmPromptDataType(promptDataType);
        String str4 = TAG;
        Logger.info(str4, Utils.format("play: %s, %s", this.mPrompterInstance, str2));
        DmResultCode dmResultCodePlayPrompt = this.mNativeDialogManager.playPrompt(this.mPrompterInstance.getName(), dmPromptDataTypeConvertDmPromptDataType, str, str2, str3, dmPlaybackMode, z, iArr);
        int i = iArr[0];
        Logger.info(str4, Utils.format("play: %s, promptId = %s", dmResultCodePlayPrompt.name(), Integer.valueOf(i)));
        if (!FrameworkUtils.isSuccess(dmResultCodePlayPrompt)) {
            return -1;
        }
        if (iAudioPlayerInitializePlayer != null) {
            this.mPlayerMap.put(Integer.valueOf(i), iAudioPlayerInitializePlayer);
        }
        if (prompterCallback != null) {
            this.mCallbackMap.put(Integer.valueOf(i), prompterCallback);
        }
        return i;
    }

    @Override // cerence.ark.assistant.framework.prompter.IPrompter
    public final void pause() {
        this.mIsPause = true;
        Logger.info(TAG, "pause: " + this.mNativeDialogManager.pausePrompt(this.mPrompterInstance.getName()).name());
    }

    @Override // cerence.ark.assistant.framework.prompter.IPrompter
    public final void resume() {
        this.mIsPause = false;
        Logger.info(TAG, "resume: " + this.mNativeDialogManager.resumePrompt(this.mPrompterInstance.getName()).name());
    }

    @Override // cerence.ark.assistant.framework.prompter.IPrompter
    public final void stop() {
        String str = TAG;
        Logger.info(str, "stop: begin");
        this.mIsPause = false;
        Logger.info(str, "stop: " + this.mNativeDialogManager.abortPrompt(this.mPrompterInstance.getName()).name());
    }

    public final void destroy() {
        String str = TAG;
        Logger.info(str, "destroy: begin");
        Logger.info(str, "destroy: " + this.mNativeDialogManager.abortPrompt(this.mPrompterInstance.getName()).name());
        synchronized (this) {
            IAudioPlayer iAudioPlayer = this.mSdkPlayer;
            if (iAudioPlayer != null) {
                iAudioPlayer.onRelease();
                this.mSdkPlayer = null;
            }
            IAudioPlayer iAudioPlayer2 = this.mAppPlayer;
            if (iAudioPlayer2 != null) {
                iAudioPlayer2.onRelease();
                this.mAppPlayer = null;
            }
            this.mPlayerMap.clear();
        }
        this.mCallbackMap.clear();
    }

    private void notifyPlayState(final int i, final PlayState playState) {
        final PrompterCallback prompterCallback = this.mCallbackMap.get(Integer.valueOf(i));
        Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.framework.impl.prompter.PrompterImpl$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                PrompterImpl.lambda$notifyPlayState$2(i, playState, prompterCallback);
            }
        });
    }

    static /* synthetic */ void lambda$notifyPlayState$2(int i, PlayState playState, PrompterCallback prompterCallback) {
        Logger.info(TAG, Utils.format("promptId = %s, playState = %s", Integer.valueOf(i), playState.name()));
        if (prompterCallback != null) {
            prompterCallback.onPlayStateChanged(playState);
        }
    }

    private void notifyPrompterError(final int i, final PrompterError prompterError) {
        final PrompterCallback prompterCallback = this.mCallbackMap.get(Integer.valueOf(i));
        Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.framework.impl.prompter.PrompterImpl$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                PrompterImpl.lambda$notifyPrompterError$3(i, prompterError, prompterCallback);
            }
        });
    }

    static /* synthetic */ void lambda$notifyPrompterError$3(int i, PrompterError prompterError, PrompterCallback prompterCallback) {
        Logger.error(TAG, Utils.format("promptId = %s, prompterError = %s", Integer.valueOf(i), prompterError.name()));
        if (prompterCallback != null) {
            prompterCallback.onError(prompterError);
        }
    }

    protected boolean needInitializeAudioPlayer(AudioPlaybackMode audioPlaybackMode) {
        return !FrameworkUtils.isPlaybackModePal(audioPlaybackMode);
    }

    protected DmInitParams.AudioPlaybackMode getDmPlaybackMode(AudioPlaybackMode audioPlaybackMode) {
        return FrameworkUtils.getDmAudioPlaybackMode(audioPlaybackMode);
    }

    /* JADX INFO: renamed from: cerence.ark.assistant.framework.impl.prompter.PrompterImpl$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$cerence$ark$assistant$framework$prompter$PromptDataType;

        static {
            int[] iArr = new int[PromptDataType.values().length];
            $SwitchMap$cerence$ark$assistant$framework$prompter$PromptDataType = iArr;
            try {
                iArr[PromptDataType.TEXT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$cerence$ark$assistant$framework$prompter$PromptDataType[PromptDataType.SSML.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private static DmPromptDataType convertDmPromptDataType(PromptDataType promptDataType) {
        if (promptDataType == null) {
            return null;
        }
        int i = AnonymousClass1.$SwitchMap$cerence$ark$assistant$framework$prompter$PromptDataType[promptDataType.ordinal()];
        if (i == 1) {
            return DmPromptDataType.DM_PROMPT_DATA_TYPE_TEXT;
        }
        if (i == 2) {
            return DmPromptDataType.DM_PROMPT_DATA_TYPE_SSML;
        }
        return DmPromptDataType.DM_PROMPT_DATA_TYPE_AUDIO_FILE;
    }
}
