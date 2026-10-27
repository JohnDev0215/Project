package cerence.ark.assistant.framework.updater;

import cerence.ark.assistant.common.callback.IResultCallback;
import cerence.ark.assistant.framework.data.ErrorCode;

/* JADX INFO: loaded from: classes2.dex */
public interface IRecoveryUpdater {
    void abortUpdate(IResultCallback<Void, ErrorCode> iResultCallback);

    void resumeUpdate(IResultCallback<Void, ErrorCode> iResultCallback);

    void stopUpdate(IResultCallback<Void, ErrorCode> iResultCallback);
}
