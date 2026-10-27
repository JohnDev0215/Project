package cerence.ark.assistant.framework;

import android.media.AudioAttributes;
import cerence.ark.assistant.api.prompter.PrompterInstance;
import cerence.ark.assistant.api.prompter.TtsVoice;
import cerence.ark.assistant.common.callback.IResultCallback;
import cerence.ark.assistant.common.callback.Result;
import cerence.ark.assistant.dm.DmTokenProvider;
import cerence.ark.assistant.framework.data.AssistantConfiguration;
import cerence.ark.assistant.framework.data.AssistantContextType;
import cerence.ark.assistant.framework.data.Config;
import cerence.ark.assistant.framework.data.ErrorCode;
import cerence.ark.assistant.framework.data.LogConsumer;
import cerence.ark.assistant.framework.data.RppLocationType;
import cerence.ark.assistant.framework.data.VoiceConfig;
import cerence.ark.assistant.framework.data.wuw.WuwConfig;
import cerence.ark.assistant.framework.impl.updater.IDataUpdater;
import cerence.ark.assistant.framework.prompter.IPrompter;
import cerence.ark.assistant.framework.updater.IUpdateListener;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public interface Framework {
    void cancelListening(IResultCallback<Void, ErrorCode> iResultCallback);

    void clearUserData(IResultCallback<Void, ErrorCode> iResultCallback);

    Result<Void, ErrorCode> configureLogConsumer(LogConsumer logConsumer);

    IPrompter createPrompter(PrompterInstance prompterInstance, AudioAttributes audioAttributes);

    IDataUpdater createUpdater(IUpdateListener iUpdateListener);

    void destroy(IResultCallback<Void, ErrorCode> iResultCallback);

    void destroyPrompter(IPrompter iPrompter);

    Result<Void, ErrorCode> destroyUpdater(IDataUpdater iDataUpdater);

    AssistantState getAssistantState();

    List<String> getAvailableAsrLanguages();

    List<PrompterInstance> getAvailablePrompterInstances();

    List<String> getAvailableTtsLanguages();

    List<TtsVoice> getAvailableTtsVoices(String str);

    List<PrompterInstance> getDialogPrompterInstances();

    List<String> getLogConsumerNames();

    PrompterInstance getTtsPrompterInstance();

    String getVersion();

    void initialize(Config config, FrameworkMessageListener frameworkMessageListener, DmTokenProvider dmTokenProvider, IResultCallback<Void, ErrorCode> iResultCallback);

    Result<Void, ErrorCode> overrideConfiguration(String str, String str2);

    Result<Void, ErrorCode> requestTrimMemory();

    void sendAppEvent(String str, IResultCallback<Void, ErrorCode> iResultCallback);

    void sendResponse(String str, IResultCallback<Void, ErrorCode> iResultCallback);

    void setDynamicWords(List<String> list, IResultCallback<Void, ErrorCode> iResultCallback);

    void setErrorListener(ErrorListener errorListener);

    Result<Void, ErrorCode> setVoiceConfig(VoiceConfig voiceConfig);

    void setWuwConfig(WuwConfig wuwConfig, IResultCallback<Void, ErrorCode> iResultCallback);

    void startListening(String str, IResultCallback<Void, ErrorCode> iResultCallback);

    void startVoiceAssistant(AssistantConfiguration assistantConfiguration, IResultCallback<Void, ErrorCode> iResultCallback);

    void stopListening(IResultCallback<Void, ErrorCode> iResultCallback);

    void stopVoiceAssistant(IResultCallback<Void, ErrorCode> iResultCallback);

    void updateAssistantContext(AssistantContextType assistantContextType, String str, IResultCallback<Void, ErrorCode> iResultCallback);

    void updateDynamicContent(String str, List<String> list, IResultCallback<Void, ErrorCode> iResultCallback);

    void updateLocation(String str, String str2, String str3, IResultCallback<Void, ErrorCode> iResultCallback);

    Result<Void, ErrorCode> updateUdeDataConfig(String str, String str2, String str3);

    Result<Void, ErrorCode> updateUdeRppConfig(RppLocationType rppLocationType, String str, String str2, String str3);
}
