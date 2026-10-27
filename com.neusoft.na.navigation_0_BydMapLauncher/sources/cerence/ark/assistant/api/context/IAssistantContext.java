package cerence.ark.assistant.api.context;

import cerence.ark.assistant.core.JsonCompliant;
import cerence.ark.assistant.framework.data.AssistantContextType;

/* JADX INFO: loaded from: classes2.dex */
public interface IAssistantContext extends JsonCompliant {
    AssistantContextType getContextType();
}
