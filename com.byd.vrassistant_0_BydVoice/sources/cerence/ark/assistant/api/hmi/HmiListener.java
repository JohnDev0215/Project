package cerence.ark.assistant.api.hmi;

import cerence.ark.assistant.api.BaseResult;
import cerence.ark.assistant.api.IRequest;
import cerence.ark.assistant.api.IResponseSender;
import cerence.ark.assistant.api.notification.INotification;

/* JADX INFO: loaded from: classes.dex */
public interface HmiListener {
    void onNotification(INotification iNotification);

    void onRequest(IRequest iRequest, IResponseSender<BaseResult> iResponseSender);
}
