package cerence.ark.assistant.api;

import android.content.Context;

/* JADX INFO: loaded from: classes2.dex */
public interface Dhi {
    Context getContext();

    void sendHmiRequest(IRequest iRequest, IResponseSender<BaseResult> iResponseSender);
}
