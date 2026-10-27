package cerence.ark.assistant.framework.impl.prompter;

import android.media.AudioAttributes;
import android.text.TextUtils;
import cerence.ark.assistant.common.Logger;
import cerence.ark.assistant.common.Scheduler;
import cerence.ark.assistant.dm.NativeDialogManager;
import cerence.ark.assistant.dm.data.DmResultCode;
import cerence.ark.assistant.dm.data.PrompterVoice;
import cerence.ark.assistant.framework.audio.AudioChannel;
import cerence.ark.assistant.framework.audio.AudioFormat;
import cerence.ark.assistant.framework.audio.SampleRate;
import cerence.ark.assistant.framework.audio.player.IAudioPlayer;
import cerence.ark.assistant.framework.data.AudioPlaybackMode;
import cerence.ark.assistant.framework.impl.FrameworkUtils;
import cerence.ark.assistant.framework.impl.PromptEvent;
import cerence.ark.assistant.framework.prompter.IPrompter;
import cerence.ark.assistant.framework.prompter.PlayState;
import cerence.ark.assistant.framework.prompter.PromptPlayerProvider;
import cerence.ark.assistant.framework.prompter.PrompterCallback;
import cerence.ark.assistant.framework.prompter.PrompterError;
import cerence.ark.assistant.library.Utils;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
public class PrompterImpl implements IPrompter {
    private static final String TAG = "PrompterImpl";
    private final Map<Integer, PrompterCallback> mCallbackMap = new ConcurrentHashMap(16);
    private final String mDefaultVoiceId;
    private volatile boolean mIsPause;
    private final NativeDialogManager mNativeDialogManager;
    private final AudioPlaybackMode mPlaybackMode;
    private IAudioPlayer mPromptPlayer;
    private final String mPrompterName;
    private final PrompterVoice[] mPrompterVoices;

    public PrompterImpl(String str, AudioPlaybackMode audioPlaybackMode, NativeDialogManager nativeDialogManager, PrompterVoice[] prompterVoiceArr, String str2) {
        this.mPrompterName = str;
        this.mPlaybackMode = audioPlaybackMode;
        this.mNativeDialogManager = nativeDialogManager;
        this.mPrompterVoices = prompterVoiceArr;
        this.mDefaultVoiceId = str2;
    }

    public String getPrompterName() {
        return this.mPrompterName;
    }

    public synchronized boolean initialize(AudioAttributes audioAttributes) {
        if (FrameworkUtils.isPlaybackModePal(this.mPlaybackMode)) {
            return true;
        }
        PromptPlayerProvider provider = PromptPlayerProvider.getProvider(Utils.getContext());
        if (provider == null) {
            provider = new DefaultPromptPlayerProvider();
        }
        IAudioPlayer iAudioPlayerCreatePromptPlayer = provider.createPromptPlayer();
        String str = TAG;
        Logger.debug(str, "initialize: " + iAudioPlayerCreatePromptPlayer.getClass().getSimpleName());
        boolean zOnInitialize = iAudioPlayerCreatePromptPlayer.onInitialize(AudioChannel.CHANNEL_MONO, AudioFormat.PCM_16BIT, SampleRate.SAMPLE_RATE_HZ_22050, audioAttributes);
        if (zOnInitialize) {
            this.mPromptPlayer = iAudioPlayerCreatePromptPlayer;
        } else {
            Logger.error(str, "initialize: initialize audio prompter failure");
        }
        return zOnInitialize;
    }

    public synchronized void playAudio(byte[] bArr) {
        IAudioPlayer iAudioPlayer = this.mPromptPlayer;
        if (iAudioPlayer == null) {
            return;
        }
        iAudioPlayer.onPlayAudio(bArr);
    }

    public synchronized void onAbortPrompt() {
        IAudioPlayer iAudioPlayer = this.mPromptPlayer;
        if (iAudioPlayer == null) {
            Logger.error(TAG, "onAbortPrompt: audio prompter is null");
        } else {
            iAudioPlayer.onStop(!this.mIsPause);
        }
    }

    public void onPromptEvent(int i, PromptEvent promptEvent) {
        if (PromptEvent.PROMPT_PLAY_STARTED == promptEvent) {
            notifyPlayState(i, PlayState.STARTED);
            return;
        }
        if (PromptEvent.PROMPT_PLAY_ENDED == promptEvent) {
            if (this.mIsPause) {
                notifyPlayState(i, PlayState.PAUSED);
            }
        } else if (PromptEvent.PROMPT_FINISHED == promptEvent) {
            notifyPlayState(i, PlayState.STOPPED);
        }
    }

    public synchronized void onEndOfStream() {
        IAudioPlayer iAudioPlayer = this.mPromptPlayer;
        if (iAudioPlayer == null) {
            Logger.error(TAG, "onEndOfStream: audio prompter is null");
        } else {
            iAudioPlayer.onEndOfStream();
        }
    }

    public void onPrompterError(int i, PrompterError prompterError) {
        notifyPrompterError(i, prompterError);
    }

    @Override // cerence.ark.assistant.framework.prompter.IPrompter
    public void play(String str, boolean z, PrompterCallback prompterCallback) {
        play(str, z, null, null, prompterCallback);
    }

    @Override // cerence.ark.assistant.framework.prompter.IPrompter
    public void play(String str, boolean z, String str2, String str3, final PrompterCallback prompterCallback) {
        if (!FrameworkUtils.isPlaybackModePal(this.mPlaybackMode)) {
            synchronized (this) {
                if (this.mPromptPlayer == null) {
                    Logger.error(TAG, "play: audio prompter is null");
                    Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.framework.impl.prompter.PrompterImpl$$ExternalSyntheticLambda2
                        @Override // java.lang.Runnable
                        public final void run() {
                            PrompterImpl.lambda$play$0(prompterCallback);
                        }
                    });
                    return;
                }
            }
        }
        if (TextUtils.isEmpty(str)) {
            Logger.error(TAG, "play: tts text is empty");
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.framework.impl.prompter.PrompterImpl$$ExternalSyntheticLambda3
                @Override // java.lang.Runnable
                public final void run() {
                    PrompterImpl.lambda$play$1(prompterCallback);
                }
            });
            return;
        }
        if (!TextUtils.isEmpty(str2)) {
            if (!FrameworkUtils.checkTtsVoiceIdValid(str2, this.mPrompterVoices)) {
                Logger.error(TAG, "play: ttsVoiceId is invalid, ttsVoiceId = " + str2);
                Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.framework.impl.prompter.PrompterImpl$$ExternalSyntheticLambda4
                    @Override // java.lang.Runnable
                    public final void run() {
                        PrompterImpl.lambda$play$2(prompterCallback);
                    }
                });
                return;
            }
        } else {
            str2 = this.mDefaultVoiceId;
        }
        this.mIsPause = false;
        int[] iArr = new int[1];
        DmResultCode dmResultCodePlayPrompt = this.mNativeDialogManager.playPrompt(this.mPrompterName, str, str2, str3, z, iArr);
        int i = iArr[0];
        Logger.debug(TAG, String.format("play: %s, promptId = %s", dmResultCodePlayPrompt.name(), Integer.valueOf(i)));
        if (dmResultCodePlayPrompt != DmResultCode.OK) {
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.framework.impl.prompter.PrompterImpl$$ExternalSyntheticLambda5
                @Override // java.lang.Runnable
                public final void run() {
                    PrompterImpl.lambda$play$3(prompterCallback);
                }
            });
        } else if (prompterCallback != null) {
            this.mCallbackMap.put(Integer.valueOf(i), prompterCallback);
        }
    }

    static /* synthetic */ void lambda$play$0(PrompterCallback prompterCallback) {
        if (prompterCallback != null) {
            prompterCallback.onError(PrompterError.PROMPTER_DESTROYED);
        }
    }

    static /* synthetic */ void lambda$play$1(PrompterCallback prompterCallback) {
        if (prompterCallback != null) {
            prompterCallback.onError(PrompterError.INPUT_INVALID);
        }
    }

    static /* synthetic */ void lambda$play$2(PrompterCallback prompterCallback) {
        if (prompterCallback != null) {
            prompterCallback.onError(PrompterError.INVALID_VOICE);
        }
    }

    static /* synthetic */ void lambda$play$3(PrompterCallback prompterCallback) {
        if (prompterCallback != null) {
            prompterCallback.onError(PrompterError.OTHER_ERROR);
        }
    }

    @Override // cerence.ark.assistant.framework.prompter.IPrompter
    public void pause() {
        this.mIsPause = true;
        Logger.debug(TAG, "pause: " + this.mNativeDialogManager.pausePrompt(this.mPrompterName).name());
    }

    @Override // cerence.ark.assistant.framework.prompter.IPrompter
    public void resume() {
        this.mIsPause = false;
        Logger.debug(TAG, "resume: " + this.mNativeDialogManager.resumePrompt(this.mPrompterName).name());
    }

    @Override // cerence.ark.assistant.framework.prompter.IPrompter
    public void stop() {
        this.mIsPause = false;
        Logger.debug(TAG, "stop: " + this.mNativeDialogManager.abortPrompt(this.mPrompterName).name());
    }

    public void destroy() {
        if (!FrameworkUtils.isPlaybackModePal(this.mPlaybackMode)) {
            synchronized (this) {
                IAudioPlayer iAudioPlayer = this.mPromptPlayer;
                if (iAudioPlayer == null) {
                    Logger.error(TAG, "destroy: audio prompter is null");
                    return;
                } else {
                    iAudioPlayer.onRelease();
                    this.mPromptPlayer = null;
                }
            }
        }
        Logger.debug(TAG, "destroy: " + this.mNativeDialogManager.abortPrompt(this.mPrompterName).name());
        this.mCallbackMap.clear();
    }

    private void notifyPlayState(final int i, final PlayState playState) {
        Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.framework.impl.prompter.PrompterImpl$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m56x2b510f20(i, playState);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$notifyPlayState$4$cerence-ark-assistant-framework-impl-prompter-PrompterImpl, reason: not valid java name */
    /* synthetic */ void m56x2b510f20(int i, PlayState playState) {
        Logger.debug(TAG, String.format("promptId = %s, playState = %s", Integer.valueOf(i), playState.name()));
        PrompterCallback prompterCallback = this.mCallbackMap.get(Integer.valueOf(i));
        if (prompterCallback != null) {
            prompterCallback.onPlayStateChanged(playState);
            if (playState == PlayState.STOPPED) {
                this.mCallbackMap.remove(Integer.valueOf(i));
            }
        }
    }

    private void notifyPrompterError(final int i, final PrompterError prompterError) {
        Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.framework.impl.prompter.PrompterImpl$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m57x9e37d405(i, prompterError);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$notifyPrompterError$5$cerence-ark-assistant-framework-impl-prompter-PrompterImpl, reason: not valid java name */
    /* synthetic */ void m57x9e37d405(int i, PrompterError prompterError) {
        Logger.error(TAG, String.format("promptId = %s, prompterError = %s", Integer.valueOf(i), prompterError.name()));
        PrompterCallback prompterCallback = this.mCallbackMap.get(Integer.valueOf(i));
        if (prompterCallback != null) {
            prompterCallback.onError(prompterError);
            this.mCallbackMap.remove(Integer.valueOf(i));
        }
    }
}
