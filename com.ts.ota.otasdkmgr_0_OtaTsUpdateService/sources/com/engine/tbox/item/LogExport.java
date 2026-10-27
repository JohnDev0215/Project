package com.engine.tbox.item;

import com.engine.tbox.core.utils.LogUtils;
import com.engine.tbox.data.FtpInfo;
import com.engine.tbox.item.DownloadProcess;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: classes.dex */
public class LogExport {
    private static final int STARTED = 1;
    private static final int STOPPED = 2;
    private LogExportCallback mCallback;
    private Condition mCond;
    private TBoxConnection mConn;
    private Lock mLock;
    private Thread mThread;
    private static int mState = 2;
    private static LogExport mInstance = null;
    private boolean mThreadRun = true;
    private String mSavePath = null;
    private DownloadProcess.FtpProcessCallback mFtpCallback = new DownloadProcess.FtpProcessCallback() { // from class: com.engine.tbox.item.LogExport.1
        private boolean stop = false;

        @Override // com.engine.tbox.item.DownloadProcess.FtpProcessCallback
        public void process(int process) {
            LogExport.this.mCallback.exportLogProgress(process);
        }

        @Override // com.engine.tbox.item.DownloadProcess.FtpProcessCallback
        public void result(int result) {
            LogUtils.d(LogUtils.TAG, "Download Process result:" + result, new Object[0]);
            if (result == 0) {
                LogExport.this.mCallback.exportLogResult(0);
                return;
            }
            if (result == 1) {
                LogExport.this.mCallback.exportLogResult(2);
            } else if (result == 2) {
                LogExport.this.mCallback.exportLogResult(1);
            } else if (result == 3) {
                LogExport.this.mCallback.exportLogResult(4);
            }
        }

        @Override // com.engine.tbox.item.DownloadProcess.FtpProcessCallback
        public boolean isStopCallback() {
            return this.stop;
        }

        @Override // com.engine.tbox.item.DownloadProcess.FtpProcessCallback
        public void stopCallback(boolean flag) {
            this.stop = flag;
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public void setCurrentState(int state) {
        this.mLock.lock();
        mState = state;
        this.mLock.unlock();
    }

    private LogExport(TBoxConnection conn) {
        this.mThread = null;
        this.mLock = null;
        this.mCond = null;
        this.mConn = conn;
        ReentrantLock reentrantLock = new ReentrantLock();
        this.mLock = reentrantLock;
        this.mCond = reentrantLock.newCondition();
        Thread thread = new Thread() { // from class: com.engine.tbox.item.LogExport.2
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() throws Throwable {
                setName("LogExportThread");
                while (LogExport.this.mThreadRun) {
                    LogExport.this.setCurrentState(2);
                    LogExport.this.mLock.lock();
                    try {
                        try {
                            LogExport.this.mCond.await();
                            LogUtils.d(LogUtils.TAG, "start export the log", new Object[0]);
                            int unused = LogExport.mState = 1;
                        } catch (InterruptedException exp) {
                            exp.printStackTrace();
                        }
                        LogExport.this.mLock.unlock();
                        FtpInfo ftpInfo = LogExport.this.mConn.sendExportLogReq();
                        if (ftpInfo == null) {
                            LogUtils.e(LogUtils.TAG, "export log get ftp info error", new Object[0]);
                            LogExport.this.notifyResult(3);
                        } else {
                            LogUtils.d(LogUtils.TAG, "export log ftp Info:" + ftpInfo, new Object[0]);
                            LogUtils.d(LogUtils.TAG, "start export log.......", new Object[0]);
                            long startTime = System.nanoTime();
                            FtpUtils.downloadFile(ftpInfo.getUrl(), LogExport.this.mSavePath, ftpInfo.getUsername(), ftpInfo.getPassword(), ftpInfo.getPort(), LogExport.this.mFtpCallback);
                            long endTime = System.nanoTime();
                            LogUtils.d(LogUtils.TAG, "LogExport over:" + (endTime - startTime) + " ns", new Object[0]);
                            LogUtils.d(LogUtils.TAG, "end export log.......", new Object[0]);
                            if (LogExport.this.mFtpCallback.isStopCallback()) {
                                LogExport.this.mFtpCallback.stopCallback(false);
                                LogExport.this.notifyResult(6);
                            }
                        }
                    } catch (Throwable th) {
                        LogExport.this.mLock.unlock();
                        throw th;
                    }
                }
            }
        };
        this.mThread = thread;
        thread.start();
    }

    public static LogExport getInstance(TBoxConnection conn) {
        LogExport logExport = mInstance;
        if (logExport != null) {
            return logExport;
        }
        synchronized (LogExport.class) {
            if (mInstance == null) {
                mInstance = new LogExport(conn);
            }
        }
        return mInstance;
    }

    public void registerLogExportCallback(LogExportCallback callbck) {
        LogUtils.d(LogUtils.TAG, "registerLogExportCallback:" + callbck, new Object[0]);
        this.mCallback = callbck;
    }

    public interface LogExportCallback {
        public static final int EXPORT_LOG_FTP_ERROR = 1;
        public static final int EXPORT_LOG_FTP_EXIT = 6;
        public static final int EXPORT_LOG_FTP_EXITING = 5;
        public static final int EXPORT_LOG_FTP_NO_RESPONSE = 3;
        public static final int EXPORT_LOG_FTP_SPACE_ERROR = 2;
        public static final int EXPORT_LOG_FTP_UNKNOWN_ERROR = 4;
        public static final int EXPORT_LOG_SUCCESS = 0;

        default void exportLogProgress(int progress) {
            LogUtils.d(LogUtils.TAG, "exportLogProgress:" + progress, new Object[0]);
        }

        default void exportLogResult(int result) {
            LogUtils.d(LogUtils.TAG, "exportLogResult:" + result, new Object[0]);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyResult(int result) {
        LogExportCallback logExportCallback = this.mCallback;
        if (logExportCallback != null) {
            logExportCallback.exportLogResult(result);
        }
    }

    private void notifyProcess(int process) {
        LogExportCallback logExportCallback = this.mCallback;
        if (logExportCallback != null) {
            logExportCallback.exportLogProgress(process);
        }
    }

    public boolean init(String path) {
        this.mLock.lock();
        try {
            if (mState != 2) {
                LogUtils.d(LogUtils.TAG, "current is exporting the log", new Object[0]);
                this.mFtpCallback.stopCallback(true);
                notifyResult(5);
                return false;
            }
            this.mSavePath = path;
            this.mFtpCallback.stopCallback(false);
            this.mCond.signal();
            return true;
        } finally {
            this.mLock.unlock();
        }
    }

    public boolean stopExport() {
        this.mLock.lock();
        LogUtils.i(LogUtils.TAG, "stopExport:" + mState, new Object[0]);
        if (mState != 2) {
            this.mFtpCallback.stopCallback(true);
            notifyResult(5);
        }
        this.mLock.unlock();
        return true;
    }

    public boolean exit() {
        return true;
    }
}
