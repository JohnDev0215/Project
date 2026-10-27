package cerence.ark.assistant.framework.updater;

import cerence.ark.assistant.common.callback.IResultCallback;
import cerence.ark.assistant.framework.data.ErrorCode;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public interface IUpdater {
    void abortUpdate(String str, IResultCallback<Void, ErrorCode> iResultCallback);

    void acceptDownload(String str, IResultCallback<Void, ErrorCode> iResultCallback);

    void acceptInstall(String str, IResultCallback<Void, ErrorCode> iResultCallback);

    void checkUpdate(List<String> list, boolean z, boolean z2, IResultCallback<Void, ErrorCode> iResultCallback);

    void deleteUpdate(String str, IResultCallback<Void, ErrorCode> iResultCallback);

    String getCurrentVersion(String str);

    void queryDownloadStatus(List<String> list, IResultCallback<List<DownloadStatus>, ErrorCode> iResultCallback);

    void rejectDownload(String str, IResultCallback<Void, ErrorCode> iResultCallback);

    void rejectInstall(String str, IResultCallback<Void, ErrorCode> iResultCallback);

    void resumeUpdate(String str, IResultCallback<Void, ErrorCode> iResultCallback);

    void stopUpdate(IResultCallback<Void, ErrorCode> iResultCallback);
}
