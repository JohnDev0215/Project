package com.byd.vrdownload;

import android.icu.math.BigDecimal;
import android.util.SparseArray;
import com.byd.vrdownload.bean.BeanTransfer;
import com.byd.vrdownload.exception.SSLCertificateValidationException;
import com.byd.vrdownload.model.AidlResult;
import com.byd.vrdownload.model.DownloadTask;
import com.byd.vrdownload.model.LanguageUpdate;
import com.byd.vrdownload.utils.FileUtils;
import com.byd.vrdownload.utils.LogUtils;
import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadLargeFileListener;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public class TaskDownloadListener extends FileDownloadLargeFileListener {
    private static final String TAG = "TaskDownloadListener";
    CallbackHandler callbackHandler;
    private final SparseArray<LanguageUpdate> downloadRecord;

    public TaskDownloadListener(CallbackHandler callbackHandler, SparseArray<LanguageUpdate> sparseArray) {
        this.callbackHandler = callbackHandler;
        this.downloadRecord = sparseArray;
    }

    @Override // com.liulishuo.filedownloader.FileDownloadLargeFileListener
    protected void pending(BaseDownloadTask baseDownloadTask, long j, long j2) {
        LogUtils.i(TAG, "id=" + baseDownloadTask.getId() + "pending: ");
        LanguageUpdate languageUpdate = this.downloadRecord.get(baseDownloadTask.getId());
        if (languageUpdate == null) {
            LogUtils.e(TAG, "pending: languageUpdate is null, maybe this task has been canceled");
        } else {
            this.callbackHandler.onDownload(BeanTransfer.getDownloadTask(baseDownloadTask, languageUpdate));
        }
    }

    @Override // com.liulishuo.filedownloader.FileDownloadLargeFileListener
    protected void progress(BaseDownloadTask baseDownloadTask, long j, long j2) {
        LogUtils.i(TAG, "id=" + baseDownloadTask.getId() + ",progress: ");
        LanguageUpdate languageUpdate = this.downloadRecord.get(baseDownloadTask.getId());
        if (languageUpdate == null) {
            LogUtils.e(TAG, "progress: languageUpdate is null, maybe this task has been canceled");
            return;
        }
        double dPercentDouble = percentDouble(j, j2);
        DownloadTask downloadTask = BeanTransfer.getDownloadTask(baseDownloadTask, languageUpdate);
        LogUtils.i(TAG, "id=" + baseDownloadTask.getId() + ",progress:language=" + downloadTask.getLanguageUpdate().getLanguage() + ",soFarBytes=" + j + ",totalBytes" + j2 + ",percent:" + dPercentDouble);
        this.callbackHandler.onDownloadProgress(downloadTask, j, j2);
    }

    public static double percentDouble(long j, long j2) {
        if (j2 == 0) {
            return 0.0d;
        }
        return new BigDecimal(j / j2).setScale(6, 4).doubleValue() * 100.0d;
    }

    @Override // com.liulishuo.filedownloader.FileDownloadListener
    protected void blockComplete(BaseDownloadTask baseDownloadTask) throws Throwable {
        super.blockComplete(baseDownloadTask);
        LogUtils.i(TAG, "id=" + baseDownloadTask.getId() + ",download size=" + this.downloadRecord.size());
        LanguageUpdate languageUpdate = this.downloadRecord.get(baseDownloadTask.getId());
        if (languageUpdate == null) {
            LogUtils.e(TAG, "blockComplete: languageUpdate is null, maybe this task has been canceled");
            return;
        }
        DownloadTask downloadTask = BeanTransfer.getDownloadTask(baseDownloadTask, languageUpdate);
        downloadTask.setStatus((byte) 4);
        boolean zCheckMd5 = FileUtils.checkMd5(new File(baseDownloadTask.getTargetFilePath()), this.downloadRecord.get(baseDownloadTask.getId()).getDownloadingPkgMd5());
        LogUtils.i(TAG, "blockComplete: isFileValid=" + zCheckMd5);
        if (!zCheckMd5) {
            downloadTask.setStatus((byte) -5);
        }
        LogUtils.i(TAG, "blockComplete: status=" + ((int) downloadTask.getStatus()));
        this.callbackHandler.onDownload(downloadTask);
    }

    @Override // com.liulishuo.filedownloader.FileDownloadListener
    protected void completed(BaseDownloadTask baseDownloadTask) {
        LogUtils.i(TAG, "id=" + baseDownloadTask.getId() + ",completed: targetFilePath=" + baseDownloadTask.getTargetFilePath());
        LanguageUpdate languageUpdate = this.downloadRecord.get(baseDownloadTask.getId());
        if (languageUpdate == null) {
            LogUtils.e(TAG, "completed: languageUpdate is null, maybe this task has been canceled");
            return;
        }
        DownloadTask downloadTask = BeanTransfer.getDownloadTask(baseDownloadTask, languageUpdate);
        boolean zCheckMd5 = FileUtils.checkMd5(new File(baseDownloadTask.getTargetFilePath()), this.downloadRecord.get(baseDownloadTask.getId()).getDownloadingPkgMd5());
        LogUtils.i(TAG, "completed: isFileValid=" + zCheckMd5);
        if (!zCheckMd5) {
            downloadTask.setStatus((byte) -5);
        }
        LogUtils.i(TAG, "completed: status=" + ((int) downloadTask.getStatus()));
        this.callbackHandler.onDownload(downloadTask);
    }

    @Override // com.liulishuo.filedownloader.FileDownloadLargeFileListener
    protected void paused(BaseDownloadTask baseDownloadTask, long j, long j2) {
        LogUtils.i(TAG, "id=" + baseDownloadTask.getId() + "paused: downloadRecord size=" + this.downloadRecord.size());
        LanguageUpdate languageUpdate = this.downloadRecord.get(baseDownloadTask.getId());
        if (languageUpdate == null) {
            LogUtils.e(TAG, "paused: languageUpdate is null, maybe this task has been canceled");
        } else {
            this.callbackHandler.onDownload(BeanTransfer.getDownloadTask(baseDownloadTask, languageUpdate));
        }
    }

    @Override // com.liulishuo.filedownloader.FileDownloadListener
    protected void error(BaseDownloadTask baseDownloadTask, Throwable th) {
        LogUtils.e(TAG, "id=" + baseDownloadTask.getId() + ",error: ", th);
        LanguageUpdate languageUpdate = this.downloadRecord.get(baseDownloadTask.getId());
        if (languageUpdate == null) {
            LogUtils.e(TAG, "error: languageUpdate is null, maybe this task has been canceled");
            return;
        }
        this.callbackHandler.onDownload(BeanTransfer.getDownloadTask(baseDownloadTask, languageUpdate));
        if (th.getMessage() != null) {
            if (th.getMessage().startsWith("Unacceptable certificate:") || th.getMessage().startsWith("Chain validation failed")) {
                this.callbackHandler.onStartDownloadResult(AidlResult.fail(new SSLCertificateValidationException(th.getMessage())));
            }
        }
    }

    @Override // com.liulishuo.filedownloader.FileDownloadListener
    protected void warn(BaseDownloadTask baseDownloadTask) {
        LogUtils.i(TAG, "id=" + baseDownloadTask.getId() + "warn: downloadRecord size=" + this.downloadRecord.size());
        LanguageUpdate languageUpdate = this.downloadRecord.get(baseDownloadTask.getId());
        if (languageUpdate == null) {
            LogUtils.e(TAG, "warn: languageUpdate is null, maybe this task has been canceled");
        } else {
            this.callbackHandler.onDownload(BeanTransfer.getDownloadTask(baseDownloadTask, languageUpdate));
        }
    }

    @Override // com.liulishuo.filedownloader.FileDownloadListener
    protected boolean isInvalid() {
        return super.isInvalid();
    }

    @Override // com.liulishuo.filedownloader.FileDownloadListener
    protected void started(BaseDownloadTask baseDownloadTask) {
        super.started(baseDownloadTask);
        LogUtils.i(TAG, "id=" + baseDownloadTask.getId() + ",started: downloadRecord size=" + this.downloadRecord.size());
        LanguageUpdate languageUpdate = this.downloadRecord.get(baseDownloadTask.getId());
        if (languageUpdate == null) {
            LogUtils.e(TAG, "started: languageUpdate is null, maybe this task has been canceled");
        } else {
            this.callbackHandler.onDownload(BeanTransfer.getDownloadTask(baseDownloadTask, languageUpdate));
        }
    }

    @Override // com.liulishuo.filedownloader.FileDownloadLargeFileListener
    protected void connected(BaseDownloadTask baseDownloadTask, String str, boolean z, long j, long j2) {
        super.connected(baseDownloadTask, str, z, j, j2);
        LogUtils.i(TAG, "id=" + baseDownloadTask.getId() + ",connected: ");
        LanguageUpdate languageUpdate = this.downloadRecord.get(baseDownloadTask.getId());
        if (languageUpdate == null) {
            LogUtils.e(TAG, "connected: languageUpdate is null, maybe this task has been canceled");
        } else {
            this.callbackHandler.onDownload(BeanTransfer.getDownloadTask(baseDownloadTask, languageUpdate));
        }
    }

    @Override // com.liulishuo.filedownloader.FileDownloadLargeFileListener
    protected void retry(BaseDownloadTask baseDownloadTask, Throwable th, int i, long j) {
        super.retry(baseDownloadTask, th, i, j);
        LogUtils.i(TAG, "id=" + baseDownloadTask.getId() + ",retry: ");
        LanguageUpdate languageUpdate = this.downloadRecord.get(baseDownloadTask.getId());
        if (languageUpdate == null) {
            LogUtils.e(TAG, "retry: languageUpdate is null, maybe this task has been canceled");
        } else {
            this.callbackHandler.onDownload(BeanTransfer.getDownloadTask(baseDownloadTask, languageUpdate));
        }
    }
}
