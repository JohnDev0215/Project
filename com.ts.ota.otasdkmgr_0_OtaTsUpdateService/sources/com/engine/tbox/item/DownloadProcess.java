package com.engine.tbox.item;

import com.engine.tbox.core.utils.LogUtils;

/* JADX INFO: loaded from: classes.dex */
public class DownloadProcess {
    private static final int HUNDRED = 100;
    private long downloadedSize = 0;
    private FtpProcessCallback mCallback;
    private long totalSize;

    public interface FtpProcessCallback {
        public static final int FTP_ERROR = 2;
        public static final int SUCCESS = 0;
        public static final int UNKNOWN_ERROR = 3;
        public static final int WRITE_ERROR = 1;

        boolean isStopCallback();

        void process(int i);

        void result(int i);

        void stopCallback(boolean z);
    }

    public DownloadProcess(long totalFileSize, FtpProcessCallback callback) {
        this.totalSize = totalFileSize;
        this.mCallback = callback;
    }

    public void downloadSize(long downloadSize) {
        this.downloadedSize += downloadSize;
    }

    public boolean isOver() {
        return this.downloadedSize >= this.totalSize;
    }

    public int getProcess() {
        long j = this.totalSize;
        if (j == 0) {
            return 100;
        }
        int process = (int) ((this.downloadedSize * 100) / j);
        if (process > 100) {
            return 99;
        }
        return process;
    }

    public void notifyProcess(int process) {
        LogUtils.d("DownloadProcess", "notifyProcess:" + process + " mCallback:" + this.mCallback, new Object[0]);
        FtpProcessCallback ftpProcessCallback = this.mCallback;
        if (ftpProcessCallback != null) {
            ftpProcessCallback.process(process);
        }
    }

    public void notifyResult(int result) {
        LogUtils.d("DownloadProcess", "notifyResult:" + result + " mCallback:" + this.mCallback, new Object[0]);
        FtpProcessCallback ftpProcessCallback = this.mCallback;
        if (ftpProcessCallback != null) {
            ftpProcessCallback.result(result);
        }
    }

    public boolean isStopCallback() {
        FtpProcessCallback ftpProcessCallback = this.mCallback;
        if (ftpProcessCallback != null) {
            return ftpProcessCallback.isStopCallback();
        }
        return false;
    }

    public String toString() {
        return "DownloadProcess{totalSize=" + FtpUtils.formatFileSize(this.totalSize) + " originTotalSize:" + this.totalSize + ", downloadedSize=" + FtpUtils.formatFileSize(this.downloadedSize) + " originDownloadSize:" + this.downloadedSize + ", process=" + ((this.downloadedSize * 100) / this.totalSize) + "%}";
    }
}
