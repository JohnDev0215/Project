package com.amazon.music.downloads.worker;

import android.os.StatFs;
import com.amazon.music.downloads.worker.Configuration;
import com.amazonaws.http.HttpHeader;
import com.google.firebase.perf.network.FirebasePerfUrlConnection;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
class DownloadRunnable implements Runnable {
    public static final String TAG = DownloadRunnable.class.getSimpleName();
    Logger LOG = LoggerFactory.getLogger(TAG);
    private final Configuration mConfiguration;
    private final File mDownloadFile;
    private final DownloadableItem mDownloadableItem;
    private final DownloadEventListener mEventListener;
    private final String mId;
    private final NetworkRetryPolicy mNetworkRetryPolicy;

    public DownloadRunnable(Configuration configuration, String str, DownloadableItem downloadableItem, DownloadEventListener downloadEventListener) {
        Configuration.NetworkRetryPolicyProvider networkRetryPolicyProvider = configuration.getNetworkRetryPolicyProvider();
        if (networkRetryPolicyProvider != null) {
            this.mNetworkRetryPolicy = networkRetryPolicyProvider.createNewInstance();
        } else {
            this.mNetworkRetryPolicy = new LinearBackoffNetworkRetryPolicy();
        }
        this.mConfiguration = configuration;
        this.mId = str;
        this.mDownloadableItem = downloadableItem;
        this.mEventListener = downloadEventListener;
        this.mDownloadFile = new File(downloadableItem.getDestinationURI() + ".part");
    }

    @Override // java.lang.Runnable
    public void run() throws Throwable {
        this.mDownloadableItem.setIsProcessing(true);
        this.mDownloadableItem.setIsQueued(false);
        if (!this.mDownloadableItem.isPaused() && !this.mDownloadableItem.isCanceled()) {
            download();
        } else {
            this.mDownloadableItem.setIsProcessing(false);
        }
    }

    private void download() throws Throwable {
        try {
            long length = this.mDownloadFile.length();
            HttpURLConnection upUrlConnection = setUpUrlConnection(length);
            upUrlConnection.setRequestProperty(HttpHeader.USER_AGENT, "AmazonMusic/" + this.mConfiguration.getAppVersion() + " " + System.getProperty("http.agent"));
            upUrlConnection.connect();
            handleResponse(upUrlConnection, length);
        } catch (MalformedURLException unused) {
            this.mDownloadableItem.setIsProcessing(false);
            this.mEventListener.onError(this.mId, ErrorReason.BAD_URL);
        } catch (IOException unused2) {
            handleReconnection();
        }
    }

    private HttpURLConnection setUpUrlConnection(long j) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) ((URLConnection) FirebasePerfUrlConnection.instrument(new URL(this.mDownloadableItem.getDownloadURL()).openConnection()));
        httpURLConnection.setConnectTimeout(this.mConfiguration.getConnectionTimeout());
        if (j > 0) {
            httpURLConnection.setRequestProperty("Range", "bytes=" + j + "-");
        }
        return httpURLConnection;
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:29:0x00a8 -> B:37:0x00c5). Please report as a decompilation issue!!! */
    private void handleResponse(HttpURLConnection httpURLConnection, long j) throws Throwable {
        try {
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode == 200) {
                saveBytesToFile(httpURLConnection, j);
                return;
            }
            if (responseCode == 206) {
                String etag = this.mDownloadableItem.getEtag();
                String headerField = httpURLConnection.getHeaderField("ETag");
                if (etag == null || !etag.equals(headerField)) {
                    try {
                        if (this.mDownloadFile.delete()) {
                            download();
                        } else {
                            this.LOG.error("could not delete the file before re-downloading");
                            this.mDownloadableItem.setIsProcessing(false);
                            this.mEventListener.onError(this.mId, ErrorReason.CANNOT_WRITE_TO_DISK);
                        }
                    } catch (SecurityException e) {
                        this.LOG.error("Error with partial download: {}", e.getLocalizedMessage(), e);
                        this.mDownloadableItem.setIsProcessing(false);
                        this.mEventListener.onError(this.mId, ErrorReason.CANNOT_WRITE_TO_DISK);
                    }
                    return;
                }
                saveBytesToFile(httpURLConnection, j);
                return;
            }
            if (responseCode == 404) {
                this.mDownloadableItem.setIsProcessing(false);
                this.mEventListener.onError(this.mId, ErrorReason.CONTENT_NOT_FOUND);
                this.mEventListener.onError(this.mId, ErrorReason.SERVER_ERROR, responseCode);
            } else {
                if (responseCode == 416) {
                    this.mEventListener.onProgress(this.mId, j, j);
                    this.mDownloadableItem.setIsProcessing(false);
                    if (removePartialExtensionOnDownloadComplete()) {
                        this.mEventListener.onDownloaded(this.mId);
                        return;
                    } else {
                        this.LOG.error("Could not remove partial extension");
                        this.mEventListener.onError(this.mId, ErrorReason.CANNOT_WRITE_TO_DISK);
                        return;
                    }
                }
                this.mDownloadableItem.setIsProcessing(false);
                this.mEventListener.onError(this.mId, ErrorReason.SERVER_ERROR, responseCode);
            }
        } catch (IOException e2) {
            this.LOG.error("Error with download response", (Throwable) e2);
            httpURLConnection.disconnect();
            handleReconnection();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r12v0, types: [com.amazon.music.downloads.worker.DownloadRunnable] */
    /* JADX WARN: Type inference failed for: r1v10 */
    /* JADX WARN: Type inference failed for: r1v14, types: [boolean] */
    /* JADX WARN: Type inference failed for: r1v15 */
    /* JADX WARN: Type inference failed for: r1v16 */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v21, types: [boolean] */
    /* JADX WARN: Type inference failed for: r1v22, types: [int] */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v31 */
    /* JADX WARN: Type inference failed for: r1v32 */
    /* JADX WARN: Type inference failed for: r1v33 */
    /* JADX WARN: Type inference failed for: r1v34 */
    /* JADX WARN: Type inference failed for: r1v35 */
    /* JADX WARN: Type inference failed for: r1v36 */
    /* JADX WARN: Type inference failed for: r1v37 */
    /* JADX WARN: Type inference failed for: r1v38 */
    /* JADX WARN: Type inference failed for: r1v4, types: [java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r1v5 */
    /* JADX WARN: Type inference failed for: r1v6, types: [java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r1v7 */
    /* JADX WARN: Type inference failed for: r1v8 */
    /* JADX WARN: Type inference failed for: r1v9 */
    /* JADX WARN: Type inference failed for: r2v3, types: [java.io.BufferedOutputStream, java.io.OutputStream] */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    private void saveBytesToFile(HttpURLConnection httpURLConnection, long j) throws Throwable {
        ?? bufferedOutputStream;
        ?? IsPaused;
        long contentLength = httpURLConnection.getContentLength();
        long j2 = j + contentLength;
        this.mDownloadableItem.setFileSizeInBytes(j2);
        this.mDownloadableItem.setBytesDownloaded(j);
        this.mDownloadableItem.setEtag(httpURLConnection.getHeaderField("ETag"));
        synchronized (DownloadRunnable.class) {
            File parentFile = this.mDownloadFile.getParentFile();
            if (!parentFile.exists()) {
                try {
                    FileUtil.mkdirs(parentFile);
                } catch (IOException e) {
                    httpURLConnection.disconnect();
                    this.LOG.error("Could not download to {}", this.mDownloadFile, e);
                    logWritePermission();
                    this.mDownloadableItem.setIsProcessing(false);
                    this.mEventListener.onError(this.mId, ErrorReason.CANNOT_WRITE_TO_DISK);
                    return;
                }
            }
        }
        if (!hasDiskSpace(contentLength)) {
            httpURLConnection.disconnect();
            this.mDownloadableItem.setIsProcessing(false);
            this.mEventListener.onError(this.mId, ErrorReason.DISK_OUT_OF_SPACE);
            return;
        }
        try {
            InputStream inputStream = httpURLConnection.getInputStream();
            byte[] bArr = new byte[4096];
            ProgressUpdatePolicy progressUpdatePolicy = new ProgressUpdatePolicy(j2, 0.1f);
            ?? r1 = 0;
             = 0;
            ?? r12 = 0;
            try {
                try {
                    bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(this.mDownloadFile, true));
                    try {
                        if (!this.mDownloadableItem.isPaused() && !this.mDownloadableItem.isCanceled()) {
                            this.mEventListener.onProgress(this.mId, this.mDownloadableItem.getBytesDownloaded(), this.mDownloadableItem.getFileSizeInBytes());
                        }
                        while (true) {
                            IsPaused = this.mDownloadableItem.isPaused();
                            if (IsPaused != 0 || (IsPaused = this.mDownloadableItem.isCanceled()) != 0 || (IsPaused = fillBuffer(inputStream, bArr, bufferedOutputStream, httpURLConnection)) == -1) {
                                break;
                            }
                            bufferedOutputStream.write(bArr, 0, IsPaused);
                            long bytesDownloaded = this.mDownloadableItem.getBytesDownloaded() + ((long) IsPaused);
                            this.mDownloadableItem.setBytesDownloaded(bytesDownloaded);
                            if (progressUpdatePolicy.shouldUpdate(bytesDownloaded)) {
                                this.mEventListener.onProgress(this.mId, this.mDownloadableItem.getBytesDownloaded(), this.mDownloadableItem.getFileSizeInBytes());
                            }
                        }
                        if (this.mDownloadableItem.isCanceled() || this.mDownloadableItem.isPaused()) {
                            this.mDownloadableItem.setIsProcessing(false);
                        }
                    } catch (RemoteConnectionException unused) {
                        r12 = bufferedOutputStream;
                        closeConnection(inputStream, r12, httpURLConnection);
                        r1 = r12;
                    } catch (IOException e2) {
                        e = e2;
                        r12 = bufferedOutputStream;
                        this.LOG.error("Error writing downloaded data: {}", e.getLocalizedMessage(), e);
                        this.mDownloadableItem.setIsProcessing(false);
                        this.mEventListener.onError(this.mId, ErrorReason.CANNOT_WRITE_TO_DISK);
                        closeConnection(inputStream, r12, httpURLConnection);
                        r1 = r12;
                    } catch (Throwable th) {
                        th = th;
                        r1 = bufferedOutputStream;
                        closeConnection(inputStream, r1, httpURLConnection);
                        throw th;
                    }
                } catch (RemoteConnectionException unused2) {
                } catch (IOException e3) {
                    e = e3;
                }
                if (this.mDownloadableItem.isCanceled() && this.mDownloadFile.delete()) {
                    this.LOG.info("Deleted canceled download request at {}", this.mDownloadFile.getAbsolutePath());
                    closeConnection(inputStream, bufferedOutputStream, httpURLConnection);
                    return;
                }
                long fileSizeInBytes = this.mDownloadableItem.getFileSizeInBytes();
                ?? r13 = IsPaused;
                if (fileSizeInBytes == this.mDownloadableItem.getBytesDownloaded()) {
                    r13 = IsPaused;
                    if (fileSizeInBytes > 0) {
                        this.mDownloadableItem.setIsProcessing(false);
                        if (removePartialExtensionOnDownloadComplete()) {
                            this.mEventListener.onDownloaded(this.mId);
                            r13 = IsPaused;
                        } else {
                            this.LOG.error("Unable to remove partial extension on download");
                            DownloadEventListener downloadEventListener = this.mEventListener;
                            String str = this.mId;
                            ErrorReason errorReason = ErrorReason.CANNOT_WRITE_TO_DISK;
                            downloadEventListener.onError(str, errorReason);
                            r13 = errorReason;
                        }
                    }
                }
                closeConnection(inputStream, bufferedOutputStream, httpURLConnection);
                r1 = r13;
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException e4) {
            this.LOG.error("Failed to open input stream for download", (Throwable) e4);
            httpURLConnection.disconnect();
            handleReconnection();
        }
    }

    private void logWritePermission() {
        File parentFile = this.mDownloadFile.getParentFile();
        while (parentFile != null && !parentFile.canWrite()) {
            parentFile = parentFile.getParentFile();
        }
        this.LOG.info("Last write directory is {}", parentFile);
    }

    private boolean removePartialExtensionOnDownloadComplete() {
        File file = new File(this.mDownloadableItem.getDestinationURI());
        if (file.exists() && !file.delete()) {
            this.LOG.info("Could not delete existing file to complete download for request id {}", this.mId);
            return false;
        }
        if (this.mDownloadFile.renameTo(file)) {
            this.LOG.info("Download for request id {} successful", this.mId);
            return true;
        }
        this.LOG.info("Could not complete download for request id {}", this.mId);
        return false;
    }

    private int fillBuffer(InputStream inputStream, byte[] bArr, OutputStream outputStream, HttpURLConnection httpURLConnection) throws RemoteConnectionException {
        try {
            return inputStream.read(bArr);
        } catch (IOException unused) {
            closeConnection(inputStream, outputStream, httpURLConnection);
            handleReconnection();
            throw new RemoteConnectionException();
        }
    }

    private void handleReconnection() {
        if (this.mNetworkRetryPolicy.sleepBeforeRetry(this.mDownloadFile.length())) {
            if (!this.mDownloadableItem.isPaused() && !this.mDownloadableItem.isCanceled()) {
                download();
                return;
            } else {
                this.mDownloadableItem.setIsProcessing(false);
                return;
            }
        }
        this.mDownloadableItem.setIsProcessing(false);
        this.mEventListener.onError(this.mId, ErrorReason.NETWORK);
    }

    private boolean hasDiskSpace(long j) {
        StatFs statFs = new StatFs(this.mDownloadFile.getParent());
        return statFs.getBlockSizeLong() * (statFs.getAvailableBlocksLong() - 4) >= j;
    }

    private void closeConnection(InputStream inputStream, OutputStream outputStream, HttpURLConnection httpURLConnection) {
        close(inputStream);
        close(outputStream);
        httpURLConnection.disconnect();
    }

    private static void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }
}
