package cerence.ark.assistant.framework;

import android.media.AudioAttributes;
import cerence.ark.assistant.common.callback.IResultCallback;
import cerence.ark.assistant.framework.data.AssistantConfiguration;
import cerence.ark.assistant.framework.data.AssistantContextType;
import cerence.ark.assistant.framework.data.Config;
import cerence.ark.assistant.framework.data.wuw.WuwConfig;
import cerence.ark.assistant.framework.data.wuw.WuwInfo;
import cerence.ark.assistant.framework.prompter.IPrompter;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface Framework {
    void activateDevice(String str, IResultCallback<String> iResultCallback);

    IPrompter createPrompter(String str, AudioAttributes audioAttributes);

    void deactivateDevice(String str, IResultCallback<String> iResultCallback);

    void destroy(IResultCallback<Void> iResultCallback);

    void destroyPrompter(IPrompter iPrompter);

    AssistantState getAssistantState();

    List<String> getAvailablePrompterNames();

    WuwInfo getWuwInfo();

    void initialize(Config config, FrameworkMessageListener frameworkMessageListener, IResultCallback<Void> iResultCallback);

    boolean overrideConfiguration(String str, String str2);

    void queryDeviceStatus(String str, IResultCallback<String> iResultCallback);

    boolean requestTrimMemory();

    void sendAppEvent(String str, IResultCallback<Void> iResultCallback);

    void sendResponse(String str, IResultCallback<Void> iResultCallback);

    void setErrorListener(ErrorListener errorListener);

    boolean setTtsVoiceId(String str);

    boolean setUserDataRetentionEnabled(boolean z);

    void setWuwConfig(WuwConfig wuwConfig, IResultCallback<Void> iResultCallback);

    void startVoiceAssistant(AssistantConfiguration assistantConfiguration, IResultCallback<Void> iResultCallback);

    void stopVoiceAssistant(IResultCallback<Void> iResultCallback);

    boolean updateAssistantContext(AssistantContextType assistantContextType, String str);

    void updateDynamicContent(String str, List<String> list);

    boolean updateShortcutCommand(List<String> list);
}
