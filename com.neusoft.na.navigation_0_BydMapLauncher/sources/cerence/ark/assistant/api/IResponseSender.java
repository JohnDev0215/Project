package cerence.ark.assistant.api;

import cerence.ark.assistant.api.BaseResult;

/* JADX INFO: loaded from: classes2.dex */
public interface IResponseSender<T extends BaseResult> {
    long getId();

    void send(IResponse<T> iResponse);
}
