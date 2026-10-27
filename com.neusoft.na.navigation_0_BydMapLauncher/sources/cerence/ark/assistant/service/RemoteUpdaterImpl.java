package cerence.ark.assistant.service;

import cerence.ark.assistant.common.Logger;
import cerence.ark.assistant.framework.data.ErrorCode;
import cerence.ark.assistant.framework.impl.updater.IDataUpdater;
import cerence.ark.assistant.framework.updater.DownloadStatus;
import cerence.ark.assistant.library.Utils;
import cerence.ark.assistant.service.RemoteUpdater;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class RemoteUpdaterImpl extends RemoteUpdater.Stub {
    private static final String TAG = "RemoteUpdaterImpl";
    private final IDataUpdater mUpdater;

    public RemoteUpdaterImpl(IDataUpdater iDataUpdater) {
        this.mUpdater = iDataUpdater;
    }

    public IDataUpdater getUpdater() {
        return this.mUpdater;
    }

    @Override // cerence.ark.assistant.service.RemoteUpdater
    public String getCurrentVersion(String str) {
        IDataUpdater iDataUpdater = this.mUpdater;
        if (iDataUpdater == null) {
            Logger.error(TAG, "getCurrentVersion: updater is null");
            return null;
        }
        return iDataUpdater.getCurrentVersion(str);
    }

    @Override // cerence.ark.assistant.service.RemoteUpdater
    public List<DownloadStatus> getDownloadStatus(List<String> list) {
        IDataUpdater iDataUpdater = this.mUpdater;
        if (iDataUpdater == null) {
            Logger.error(TAG, "getDownloadStatus: updater is null");
            return new ArrayList();
        }
        return iDataUpdater.getDownloadStatus(list);
    }

    @Override // cerence.ark.assistant.service.RemoteUpdater
    public RemoteResult deleteUpdate(String str) {
        IDataUpdater iDataUpdater = this.mUpdater;
        if (iDataUpdater == null) {
            Logger.error(TAG, "deleteUpdate: updater is null");
            return Utils.buildErrorRemoteResult(ErrorCode.UNKNOWN);
        }
        return Utils.convertToRemoteResult(iDataUpdater.deleteUpdate(str));
    }

    @Override // cerence.ark.assistant.service.RemoteUpdater
    public RemoteResult checkUpdate(List<String> list, boolean z, boolean z2) {
        IDataUpdater iDataUpdater = this.mUpdater;
        if (iDataUpdater == null) {
            Logger.error(TAG, "checkUpdate: updater is null");
            return Utils.buildErrorRemoteResult(ErrorCode.UNKNOWN);
        }
        return Utils.convertToRemoteResult(iDataUpdater.checkUpdate(list, z, z2));
    }

    @Override // cerence.ark.assistant.service.RemoteUpdater
    public RemoteResult abortUpdate(String str) {
        IDataUpdater iDataUpdater = this.mUpdater;
        if (iDataUpdater == null) {
            Logger.error(TAG, "abortUpdate: updater is null");
            return Utils.buildErrorRemoteResult(ErrorCode.UNKNOWN);
        }
        return Utils.convertToRemoteResult(iDataUpdater.abortUpdate(str));
    }

    @Override // cerence.ark.assistant.service.RemoteUpdater
    public RemoteResult stopUpdate() {
        IDataUpdater iDataUpdater = this.mUpdater;
        if (iDataUpdater == null) {
            Logger.error(TAG, "stopUpdate: updater is null");
            return Utils.buildErrorRemoteResult(ErrorCode.UNKNOWN);
        }
        return Utils.convertToRemoteResult(iDataUpdater.stopUpdate());
    }

    @Override // cerence.ark.assistant.service.RemoteUpdater
    public RemoteResult resumeUpdate(String str) {
        IDataUpdater iDataUpdater = this.mUpdater;
        if (iDataUpdater == null) {
            Logger.error(TAG, "resumeUpdate: updater is null");
            return Utils.buildErrorRemoteResult(ErrorCode.UNKNOWN);
        }
        return Utils.convertToRemoteResult(iDataUpdater.resumeUpdate(str));
    }

    @Override // cerence.ark.assistant.service.RemoteUpdater
    public RemoteResult acceptDownload(String str) {
        IDataUpdater iDataUpdater = this.mUpdater;
        if (iDataUpdater == null) {
            Logger.error(TAG, "acceptDownload: updater is null");
            return Utils.buildErrorRemoteResult(ErrorCode.UNKNOWN);
        }
        return Utils.convertToRemoteResult(iDataUpdater.acceptDownload(str));
    }

    @Override // cerence.ark.assistant.service.RemoteUpdater
    public RemoteResult rejectDownload(String str) {
        IDataUpdater iDataUpdater = this.mUpdater;
        if (iDataUpdater == null) {
            Logger.error(TAG, "rejectDownload: updater is null");
            return Utils.buildErrorRemoteResult(ErrorCode.UNKNOWN);
        }
        return Utils.convertToRemoteResult(iDataUpdater.rejectDownload(str));
    }

    @Override // cerence.ark.assistant.service.RemoteUpdater
    public RemoteResult acceptInstall(String str) {
        IDataUpdater iDataUpdater = this.mUpdater;
        if (iDataUpdater == null) {
            Logger.error(TAG, "acceptInstall: updater is null");
            return Utils.buildErrorRemoteResult(ErrorCode.UNKNOWN);
        }
        return Utils.convertToRemoteResult(iDataUpdater.acceptInstall(str));
    }

    @Override // cerence.ark.assistant.service.RemoteUpdater
    public RemoteResult rejectInstall(String str) {
        IDataUpdater iDataUpdater = this.mUpdater;
        if (iDataUpdater == null) {
            Logger.error(TAG, "rejectInstall: updater is null");
            return Utils.buildErrorRemoteResult(ErrorCode.UNKNOWN);
        }
        return Utils.convertToRemoteResult(iDataUpdater.rejectInstall(str));
    }
}
