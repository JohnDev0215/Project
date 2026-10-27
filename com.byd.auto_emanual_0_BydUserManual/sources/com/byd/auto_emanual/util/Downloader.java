package com.byd.auto_emanual.util;

import com.byd.car.locale.ccTLD;
import java.io.BufferedInputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/* JADX INFO: loaded from: classes.dex */
public class Downloader {
    private static String TAG = "Downloader";
    private static volatile Downloader instance;
    private ExecutorService mExecutors = Executors.newCachedThreadPool();
    private Map<String, DownloadRunnable> taskRecords = new HashMap();

    public interface IDownloadListener {
        void onDownloadComplete(String path, String localPath);

        void onDownloadError(String path, String localPath, int from, int responseCode);

        default void onDownloadStart(String path, String localPath, int from, int to, int fileSize) {
        }

        void onDownloading(String path, String localPath, int from, int downloadedSize, int fileSize);

        void onNetworkUnavailable();
    }

    private Downloader() {
    }

    public static Downloader getInstance() {
        if (instance == null) {
            synchronized (Downloader.class) {
                if (instance == null) {
                    instance = new Downloader();
                }
            }
        }
        return instance;
    }

    public void startDownload(String path, int from, int to, String localPath, IDownloadListener listener) {
        if (!this.taskRecords.containsKey(path)) {
            MLog.d(TAG, "startDownload : " + path + " " + from + ", " + to);
            DownloadRunnable downloadRunnable = new DownloadRunnable(path, from, to, localPath, listener);
            this.taskRecords.put(path, downloadRunnable);
            downloadRunnable.setFuture(this.mExecutors.submit(downloadRunnable));
            return;
        }
        this.taskRecords.get(path).setListener(listener);
    }

    public void cancelDownload(String path) {
        if (this.taskRecords.containsKey(path)) {
            this.taskRecords.get(path).cancel();
        }
    }

    class DownloadRunnable implements Runnable {
        private int from;
        private Future future;
        private IDownloadListener listener;
        private String localPath;
        private String path;
        private int to;

        DownloadRunnable(String path, int from, int to, String localPath, IDownloadListener downloadListener) {
            this.path = path;
            this.from = from;
            this.to = to;
            this.localPath = localPath;
            this.listener = downloadListener;
        }

        public void setFuture(Future future) {
            this.future = future;
        }

        public void cancel() {
            Future future = this.future;
            if (future != null && !future.isDone() && !this.future.isCancelled()) {
                this.future.cancel(true);
            }
            this.future = null;
        }

        @Override // java.lang.Runnable
        public void run() {
            int responseCode = -2;
            try {
                Thread.sleep(0L);
                HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(this.path).openConnection();
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setReadTimeout(5000);
                MLog.d(Downloader.TAG, "run " + this.from + ", " + this.to + ", " + this.path);
                int i = this.from;
                if (i >= 0) {
                    if (this.to > i) {
                        httpURLConnection.setRequestProperty("Range", "bytes=" + this.from + "-" + this.to);
                    } else {
                        httpURLConnection.setRequestProperty("Range", "bytes=" + this.from + "-");
                    }
                }
                responseCode = httpURLConnection.getResponseCode();
                if (responseCode != 200 && responseCode != 206) {
                    Downloader.this.taskRecords.remove(this.path);
                    IDownloadListener iDownloadListener = this.listener;
                    if (iDownloadListener != null) {
                        iDownloadListener.onDownloadError(this.path, this.localPath, this.from, responseCode);
                    }
                } else {
                    int contentLength = httpURLConnection.getContentLength();
                    IDownloadListener iDownloadListener2 = this.listener;
                    if (iDownloadListener2 != null) {
                        iDownloadListener2.onDownloadStart(this.path, this.localPath, this.from, this.to, contentLength);
                    }
                    RandomAccessFile randomAccessFile = new RandomAccessFile(this.localPath, ccTLD.RWANDA);
                    int i2 = this.from;
                    if (i2 > 0) {
                        randomAccessFile.seek(i2);
                    }
                    if (this.from == 0 && this.to == 0) {
                        randomAccessFile.setLength(contentLength);
                    }
                    BufferedInputStream bufferedInputStream = new BufferedInputStream(httpURLConnection.getInputStream());
                    byte[] bArr = new byte[8192];
                    int i3 = 0;
                    while (true) {
                        int i4 = bufferedInputStream.read(bArr);
                        if (i4 == -1) {
                            break;
                        }
                        randomAccessFile.write(bArr, 0, i4);
                        int i5 = i3 + i4;
                        IDownloadListener iDownloadListener3 = this.listener;
                        if (iDownloadListener3 != null) {
                            iDownloadListener3.onDownloading(this.path, this.localPath, this.from, i5, contentLength);
                        }
                        i3 = i5;
                    }
                    randomAccessFile.close();
                    bufferedInputStream.close();
                    Downloader.this.taskRecords.remove(this.path);
                    IDownloadListener iDownloadListener4 = this.listener;
                    if (iDownloadListener4 != null) {
                        iDownloadListener4.onDownloadComplete(this.path, this.localPath);
                    }
                }
                httpURLConnection.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
                MLog.e(Downloader.TAG, "download exception", e);
                Downloader.this.taskRecords.remove(this.path);
                IDownloadListener iDownloadListener5 = this.listener;
                if (iDownloadListener5 != null) {
                    iDownloadListener5.onDownloadError(this.path, this.localPath, this.from, responseCode);
                }
            }
            MLog.i(Downloader.TAG, "DownloadRunnable end : " + this.path);
        }

        public void setListener(IDownloadListener listener) {
            this.listener = listener;
        }
    }

    public void clearListener(String path) {
        if (this.taskRecords.containsKey(path)) {
            this.taskRecords.get(path).setListener(null);
        }
    }

    public void setListener(String path, IDownloadListener listener) {
        if (this.taskRecords.containsKey(path)) {
            this.taskRecords.get(path).setListener(listener);
        }
    }

    public boolean isDownloading(String path) {
        return this.taskRecords.containsKey(path);
    }
}
