package com.neusoft.na.byd.tts;

import android.content.Context;
import android.content.Intent;
import android.media.AudioAttributes;
import android.text.TextUtils;
import android.util.Log;
import cerence.ark.assistant.api.ArkAssistant;
import cerence.ark.assistant.api.prompter.PrompterInstance;
import cerence.ark.assistant.api.prompter.TtsVoice;
import cerence.ark.assistant.common.callback.IResultCallback;
import cerence.ark.assistant.common.callback.Result;
import cerence.ark.assistant.framework.data.Config;
import cerence.ark.assistant.framework.prompter.IPrompter;
import cerence.ark.assistant.framework.prompter.PlayState;
import cerence.ark.assistant.framework.prompter.PrompterCallback;
import cerence.ark.assistant.framework.prompter.PrompterError;
import com.neusoft.na.byd.tts.utils.DeviceUtils;
import com.neusoft.na.common.BydSystemAdapterCallback;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class TTSPlayer {
    public static final String TAG = "BydTTSPlayer";
    private static TTSPlayer instance;
    private BydSystemAdapterCallback bydSystemAdapterCallback;
    private Context context;
    private TtsVoice currentTtsVoice;
    private IPrompter prompter;
    private String switchTtsLanguageVoiceName;
    private String ttsFolderPath;
    private final String ACTION_NEUSOFT_NAVIGATION_PLAY_STATE = "com.neusoft.navigation.tts.play.state";
    private final String ACTION_KEY_TTS_PLAY_STATE = "ttsPlayState";
    private List<TtsVoice> ttsVoices = new ArrayList();

    public static TTSPlayer getInstance(Context context, String str) {
        Log.i(TAG, "[getInstance]");
        if (instance == null) {
            instance = new TTSPlayer(context, str);
        }
        return instance;
    }

    private TTSPlayer(Context context, String str) {
        Log.i(TAG, "[TTSPlayer] --> ttsFolderPath == " + str);
        this.context = context;
        this.ttsFolderPath = str;
        init();
    }

    private void init() {
        Log.i(TAG, "[init]");
        try {
            if (!new File(this.ttsFolderPath).exists()) {
                Log.e(TAG, "TTS resource folder is not exists.");
                return;
            }
            String str = this.context.getFilesDir().getAbsolutePath() + File.separator + "tts" + File.separator + "writable";
            File file = new File(str);
            if (!file.exists()) {
                file.mkdirs();
            }
            Log.i(TAG, "deviceId == " + DeviceUtils.getUniqueDeviceId(this.context));
            ArkAssistant.get().initializeVoiceAssistant(this.context, new Config.Builder().dataPath(this.ttsFolderPath).writablePath(str).userId(DeviceUtils.getUniqueDeviceId(this.context)).setDeviceId(DeviceUtils.getUniqueDeviceId(this.context)).isSaveInputAudio(false).isSaveOutputAudio(false).build(), new IResultCallback() { // from class: com.neusoft.na.byd.tts.TTSPlayer$$ExternalSyntheticLambda0
                @Override // cerence.ark.assistant.common.callback.IResultCallback
                public final void onResult(Result result) {
                    this.f$0.m147lambda$init$1$comneusoftnabydttsTTSPlayer(result);
                }
            });
        } catch (Exception e) {
            Log.e(TAG, "TTSPlayer init error!");
            e.printStackTrace();
        }
    }

    /* JADX INFO: renamed from: lambda$init$1$com-neusoft-na-byd-tts-TTSPlayer, reason: not valid java name */
    /* synthetic */ void m147lambda$init$1$comneusoftnabydttsTTSPlayer(Result result) {
        if (result.isSuccess()) {
            Log.i(TAG, "init assistant success");
            List<PrompterInstance> availablePrompterInstances = ArkAssistant.get().getAvailablePrompterInstances();
            if (!availablePrompterInstances.isEmpty()) {
                this.prompter = ArkAssistant.get().createPrompter(availablePrompterInstances.get(0), new AudioAttributes.Builder().setUsage(12).build());
            }
            if (this.prompter == null) {
                Log.e(TAG, "create prompter fail");
                return;
            } else {
                Log.i(TAG, "create prompter success");
                ArkAssistant.get().queryAvailableTtsVoices(null, new IResultCallback() { // from class: com.neusoft.na.byd.tts.TTSPlayer$$ExternalSyntheticLambda1
                    @Override // cerence.ark.assistant.common.callback.IResultCallback
                    public final void onResult(Result result2) {
                        this.f$0.m146lambda$init$0$comneusoftnabydttsTTSPlayer(result2);
                    }
                });
                return;
            }
        }
        Log.e(TAG, "init fail, error message = " + result.getErrorMessage() + " ,code = " + result.getErrorCode());
    }

    /* JADX INFO: renamed from: lambda$init$0$com-neusoft-na-byd-tts-TTSPlayer, reason: not valid java name */
    /* synthetic */ void m146lambda$init$0$comneusoftnabydttsTTSPlayer(Result result) {
        Log.i(TAG, "queryAvailableTtsVoices -->");
        List list = (List) result.getData();
        if (!list.isEmpty()) {
            Log.i(TAG, "availableTtsVoices.size == " + list.size());
            this.ttsVoices.clear();
            this.ttsVoices.addAll(list);
            this.currentTtsVoice = this.ttsVoices.get(0);
        } else {
            Log.e(TAG, "TTS voices list is empty!");
        }
        if (!TextUtils.isEmpty(this.switchTtsLanguageVoiceName)) {
            switchTtsLanguage(this.switchTtsLanguageVoiceName);
        }
        Log.i(TAG, "tts init end.");
    }

    public void switchTtsLanguage(String str) {
        if (this.prompter == null) {
            this.switchTtsLanguageVoiceName = str;
            return;
        }
        Log.i(TAG, "[switchTtsLanguage] --> voiceName == " + str);
        if (TextUtils.isEmpty(str)) {
            Log.i(TAG, "voiceName is empty");
            return;
        }
        List<TtsVoice> list = this.ttsVoices;
        if (list != null && !list.isEmpty()) {
            for (TtsVoice ttsVoice : this.ttsVoices) {
                if (ttsVoice.getVoiceId().contains(str.toLowerCase())) {
                    Log.i(TAG, "find set current tts voice");
                    this.currentTtsVoice = ttsVoice;
                }
            }
            TtsVoice ttsVoice2 = this.currentTtsVoice;
            if (ttsVoice2 == null || ttsVoice2.getVoiceId().contains(str.toLowerCase())) {
                return;
            }
            Log.d(TAG, "not found set current tts voice");
            BydSystemAdapterCallback bydSystemAdapterCallback = this.bydSystemAdapterCallback;
            if (bydSystemAdapterCallback != null) {
                bydSystemAdapterCallback.onLanguageSwitchFail();
                return;
            }
            return;
        }
        Log.e(TAG, "TTS ttsVoices is empty");
    }

    public void playTtsWithVoiceId(final String str) {
        Log.i(TAG, "[playTtsWithVoiceId] --> text == " + str);
        if (TextUtils.isEmpty(str)) {
            Log.i(TAG, "text is empty, do not play");
            return;
        }
        IPrompter iPrompter = this.prompter;
        if (iPrompter != null) {
            iPrompter.play(str, false, this.currentTtsVoice.getVoiceId(), (String) null, new PrompterCallback() { // from class: com.neusoft.na.byd.tts.TTSPlayer.1
                @Override // cerence.ark.assistant.framework.prompter.PrompterCallback
                public void onPromptCreated() {
                    Log.i(TTSPlayer.TAG, "[onPromptCreated]");
                }

                @Override // cerence.ark.assistant.framework.prompter.PrompterCallback
                public void onPlayStateChanged(PlayState playState) {
                    Log.i(TTSPlayer.TAG, "playState = " + playState.ordinal() + ", " + playState.name());
                    TTSPlayer.this.sendTtsPlayStateBroadcast(playState);
                    if (TTSPlayer.this.bydSystemAdapterCallback == null || playState.ordinal() != 2) {
                        return;
                    }
                    TTSPlayer.this.bydSystemAdapterCallback.ttsPlayEnd(str);
                }

                @Override // cerence.ark.assistant.framework.prompter.PrompterCallback
                public void onError(PrompterError prompterError) {
                    Log.e(TTSPlayer.TAG, "prompterError = " + prompterError.name());
                    if (TTSPlayer.this.bydSystemAdapterCallback != null) {
                        TTSPlayer.this.bydSystemAdapterCallback.ttsPlayError(str);
                    }
                }

                @Override // cerence.ark.assistant.framework.prompter.PrompterCallback
                public void onPromptDestroyed() {
                    Log.i(TTSPlayer.TAG, "[onPromptDestroyed]");
                }
            });
        }
    }

    public void stopTts() {
        Log.i(TAG, "[stopTts]");
        IPrompter iPrompter = this.prompter;
        if (iPrompter != null) {
            iPrompter.stop();
        }
    }

    public void destroyTts() {
        Log.i(TAG, "[destroyTts]");
        if (this.prompter != null) {
            ArkAssistant.get().destroyPrompter(this.prompter);
            this.prompter = null;
            instance = null;
        }
    }

    public void setTtsCallback(BydSystemAdapterCallback bydSystemAdapterCallback) {
        Log.i(TAG, "[setTtsCallback]");
        this.bydSystemAdapterCallback = bydSystemAdapterCallback;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendTtsPlayStateBroadcast(PlayState playState) {
        Log.i(TAG, "[sendTtsPlayStateBroadcast] --> playState.ordinal() == " + playState.ordinal());
        boolean z = playState.ordinal() == 0;
        Intent intent = new Intent("com.neusoft.navigation.tts.play.state");
        intent.putExtra("ttsPlayState", z);
        this.context.sendBroadcast(intent);
    }
}
