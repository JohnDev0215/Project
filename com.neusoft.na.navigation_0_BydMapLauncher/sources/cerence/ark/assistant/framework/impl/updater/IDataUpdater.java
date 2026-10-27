package cerence.ark.assistant.framework.impl.updater;

import cerence.ark.assistant.common.callback.Result;
import cerence.ark.assistant.framework.data.ErrorCode;
import cerence.ark.assistant.framework.updater.DownloadStatus;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public interface IDataUpdater {
    Result<Void, ErrorCode> abortUpdate(String str);

    Result<Void, ErrorCode> acceptDownload(String str);

    Result<Void, ErrorCode> acceptInstall(String str);

    Result<Void, ErrorCode> checkUpdate(List<String> list, boolean z, boolean z2);

    Result<Void, ErrorCode> deleteUpdate(String str);

    String getCurrentVersion(String str);

    List<DownloadStatus> getDownloadStatus(List<String> list);

    Result<Void, ErrorCode> rejectDownload(String str);

    Result<Void, ErrorCode> rejectInstall(String str);

    Result<Void, ErrorCode> resumeUpdate(String str);

    Result<Void, ErrorCode> stopUpdate();
}
