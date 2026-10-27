package cerence.ark.assistant.framework.prompter;

import cerence.ark.assistant.api.prompter.PrompterInstance;
import cerence.ark.assistant.common.callback.Result;
import cerence.ark.assistant.framework.data.ErrorCode;

/* JADX INFO: loaded from: classes2.dex */
public interface BasePrompter {
    PrompterInstance getPrompterInstance();

    Result<Void, ErrorCode> setPrompterParameter(PrompterParameterType prompterParameterType, int i);
}
