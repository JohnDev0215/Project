package cerence.ark.assistant.api;

import cerence.ark.assistant.api.BaseResult;
import cerence.ark.assistant.core.JsonCompliant;

/* JADX INFO: loaded from: classes.dex */
public interface IResponse<T extends BaseResult> extends JsonCompliant {
    void requestSkipSnoozing();

    void setData(T t);

    void setErrorMessage(String str);

    void setResultCode(String str);
}
