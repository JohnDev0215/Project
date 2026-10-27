package cerence.ark.assistant.api;

import cerence.ark.assistant.api.BaseResult;

/* JADX INFO: loaded from: classes.dex */
public interface IResponseSender<T extends BaseResult> {
    void send(IResponse<T> iResponse);
}
