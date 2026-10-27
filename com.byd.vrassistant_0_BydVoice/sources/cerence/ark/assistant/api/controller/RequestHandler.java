package cerence.ark.assistant.api.controller;

import cerence.ark.assistant.api.BaseResult;
import cerence.ark.assistant.api.IRequest;
import cerence.ark.assistant.api.IResponseSender;

/* JADX INFO: loaded from: classes.dex */
@FunctionalInterface
public interface RequestHandler<T extends BaseResult> {
    default boolean onAbort(IRequest iRequest) {
        return true;
    }

    void onHandle(IRequest iRequest, IResponseSender<T> iResponseSender);
}
