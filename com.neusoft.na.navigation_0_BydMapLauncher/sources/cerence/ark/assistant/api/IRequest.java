package cerence.ark.assistant.api;

import cerence.ark.assistant.core.JsonCompliant;

/* JADX INFO: loaded from: classes2.dex */
public interface IRequest extends JsonCompliant {
    default String getDomainName() {
        return null;
    }

    long getId();

    default String getName() {
        return null;
    }

    String getParams();

    @Override // cerence.ark.assistant.core.JsonCompliant
    default String toJsonString() {
        return null;
    }
}
