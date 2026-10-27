package cerence.ark.assistant.api.hmi;

import cerence.ark.assistant.api.Activated;
import cerence.ark.assistant.api.BaseResult;
import cerence.ark.assistant.api.IRequest;
import cerence.ark.assistant.api.IResponseSender;
import cerence.ark.assistant.api.notification.INotification;

/* JADX INFO: loaded from: classes2.dex */
public interface HmiListener extends Activated {
    @Override // cerence.ark.assistant.api.Activated
    default void activate() {
    }

    @Override // cerence.ark.assistant.api.Activated
    default void deactivate() {
    }

    void onNotification(INotification iNotification);

    void onRequest(IRequest iRequest, IResponseSender<BaseResult> iResponseSender);
}
