package cerence.ark.assistant.framework.prompter;

import cerence.ark.assistant.common.callback.Result;
import cerence.ark.assistant.framework.data.AudioPlaybackMode;
import cerence.ark.assistant.framework.data.ErrorCode;

/* JADX INFO: loaded from: classes2.dex */
public interface IPrompter extends BasePrompter {
    void pause();

    int play(String str, PromptDataType promptDataType, boolean z, AudioPlaybackMode audioPlaybackMode, String str2, String str3, PrompterCallback prompterCallback);

    int play(String str, PromptDataType promptDataType, boolean z, String str2, PrompterCallback prompterCallback);

    int play(String str, boolean z, String str2, String str3, PrompterCallback prompterCallback);

    Result<Void, ErrorCode> prepareVoice(String str, String str2);

    void resume();

    void stop();
}
