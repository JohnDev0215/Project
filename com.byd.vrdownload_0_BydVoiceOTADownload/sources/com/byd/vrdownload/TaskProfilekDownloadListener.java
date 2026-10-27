package com.byd.vrdownload;

import com.byd.vrdownload.bean.BeanTransfer;
import com.byd.vrdownload.model.IProfile;
import com.byd.vrdownload.model.ProfileDownloadTask;
import com.byd.vrdownload.utils.FileUtils;
import com.byd.vrdownload.utils.LogUtils;
import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadLargeFileListener;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public class TaskProfilekDownloadListener extends FileDownloadLargeFileListener {
    private static final String TAG = "TasProfilekDownloadListener";
    IProfile callback;
    private String md5;
    private String profileKey;

    public TaskProfilekDownloadListener(IProfile iProfile, String str, String str2) {
        this.callback = iProfile;
        this.profileKey = str;
        this.md5 = str2;
    }

    @Override // com.liulishuo.filedownloader.FileDownloadLargeFileListener
    protected void pending(BaseDownloadTask baseDownloadTask, long j, long j2) {
        LogUtils.i(TAG, "id=" + baseDownloadTask.getId() + "pending: ");
    }

    @Override // com.liulishuo.filedownloader.FileDownloadLargeFileListener
    protected void progress(BaseDownloadTask baseDownloadTask, long j, long j2) {
        LogUtils.i(TAG, "id=" + baseDownloadTask.getId() + ",progress: ");
        ProfileDownloadTask profileTask = BeanTransfer.getProfileTask(baseDownloadTask);
        LogUtils.i(TAG, "id=" + baseDownloadTask.getId() + ",progress:profileKey=" + this.profileKey + ",soFarBytes=" + j + ",totalBytes" + j2);
        this.callback.onProgress(profileTask, j, j2);
    }

    @Override // com.liulishuo.filedownloader.FileDownloadListener
    protected void blockComplete(BaseDownloadTask baseDownloadTask) throws Throwable {
        super.blockComplete(baseDownloadTask);
        LogUtils.i(TAG, "profile blockComplete id=" + baseDownloadTask.getId());
        ProfileDownloadTask profileTask = BeanTransfer.getProfileTask(baseDownloadTask);
        profileTask.setStatus((byte) 4);
        boolean zCheckMd5 = FileUtils.checkMd5(new File(baseDownloadTask.getTargetFilePath()), this.md5);
        LogUtils.i(TAG, "blockComplete: isFileValid=" + zCheckMd5);
        if (!zCheckMd5) {
            profileTask.setStatus((byte) -5);
        }
        LogUtils.i(TAG, "blockComplete: status=" + ((int) profileTask.getStatus()));
        this.callback.onDownload(profileTask);
    }

    @Override // com.liulishuo.filedownloader.FileDownloadListener
    protected void completed(BaseDownloadTask baseDownloadTask) {
        LogUtils.i(TAG, "id=" + baseDownloadTask.getId() + ",completed: targetFilePath=" + baseDownloadTask.getTargetFilePath());
        ProfileDownloadTask profileTask = BeanTransfer.getProfileTask(baseDownloadTask);
        boolean zCheckMd5 = FileUtils.checkMd5(new File(baseDownloadTask.getTargetFilePath()), this.md5);
        LogUtils.i(TAG, "completed: isFileValid=" + zCheckMd5);
        if (zCheckMd5) {
            this.callback.onDownloadComplete(profileTask);
        }
        LogUtils.i(TAG, "completed: status=" + ((int) profileTask.getStatus()));
    }

    @Override // com.liulishuo.filedownloader.FileDownloadLargeFileListener
    protected void paused(BaseDownloadTask baseDownloadTask, long j, long j2) {
        LogUtils.i(TAG, "profile pause id=" + baseDownloadTask.getId());
    }

    @Override // com.liulishuo.filedownloader.FileDownloadListener
    protected void error(BaseDownloadTask baseDownloadTask, Throwable th) {
        LogUtils.i(TAG, "profile error=" + baseDownloadTask.getId() + ",error: " + th.getMessage());
    }

    @Override // com.liulishuo.filedownloader.FileDownloadListener
    protected void warn(BaseDownloadTask baseDownloadTask) {
        LogUtils.i(TAG, "profile warn=" + baseDownloadTask.getId());
    }

    @Override // com.liulishuo.filedownloader.FileDownloadListener
    protected boolean isInvalid() {
        return super.isInvalid();
    }

    @Override // com.liulishuo.filedownloader.FileDownloadListener
    protected void started(BaseDownloadTask baseDownloadTask) {
        super.started(baseDownloadTask);
        LogUtils.i(TAG, "profile started id=" + baseDownloadTask.getId() + ",started");
    }

    @Override // com.liulishuo.filedownloader.FileDownloadLargeFileListener
    protected void connected(BaseDownloadTask baseDownloadTask, String str, boolean z, long j, long j2) {
        super.connected(baseDownloadTask, str, z, j, j2);
        LogUtils.i(TAG, "profile connected id=" + baseDownloadTask.getId() + ",connected: ");
    }

    @Override // com.liulishuo.filedownloader.FileDownloadLargeFileListener
    protected void retry(BaseDownloadTask baseDownloadTask, Throwable th, int i, long j) {
        super.retry(baseDownloadTask, th, i, j);
        LogUtils.i(TAG, "profile retry id=" + baseDownloadTask.getId() + ",retry: ");
    }
}
