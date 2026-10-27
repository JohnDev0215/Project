package com.amazon.mp3.det;

import androidx.annotation.NonNull;
import com.amazon.mp3.util.FileUtil;
import com.amazon.mp3.util.Log;
import com.amazon.net.okhttp3.UnifiedOkHttpClient;
import com.amazonaws.http.HttpHeader;
import com.google.firebase.perf.network.FirebasePerfOkHttpClient;
import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.concurrent.Executor;
import kotlin.text.Charsets;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/* JADX INFO: loaded from: classes3.dex */
class BinaryFileDetUploader {
    private static final String TAG = "BinaryFileDetUploader";
    private final DetUploadListener mDetUploadListener;
    private final String mDeviceFirmwareVersion;
    private final String mDeviceId;
    private final String mDeviceType;
    private final Executor mExecutor;
    private final String mUploadTag;

    public interface DetUploadListener {
        void onError(final File file, final Exception e);

        void onError(final File file, final Response response);

        void onSuccess(final File file);
    }

    public BinaryFileDetUploader(final String deviceId, final String deviceType, final String deviceFirmwareVersion, final String uploadTag, final Executor executor, final DetUploadListener detUploadListener) {
        this.mDeviceId = deviceId;
        this.mDeviceType = deviceType;
        this.mExecutor = executor;
        this.mDeviceFirmwareVersion = deviceFirmwareVersion;
        this.mDetUploadListener = detUploadListener;
        this.mUploadTag = uploadTag;
    }

    public void submitReport(@NonNull final String reportKey, @NonNull final File file) {
        this.mExecutor.execute(new UploadTask(reportKey, file));
    }

    private class UploadTask implements Runnable {
        private final File mFile;
        private final String mReportKey;

        UploadTask(@NonNull final String reportKey, @NonNull final File file) {
            this.mReportKey = reportKey;
            this.mFile = file;
        }

        @Override // java.lang.Runnable
        public void run() {
            Log.debug(BinaryFileDetUploader.TAG, "Starting upload:" + this.mFile.getAbsolutePath());
            HttpUrl httpUrl = HttpUrl.parse("https://det-ta-g7g.amazon.com/DeviceEventProxy/DETLogServlet");
            if (httpUrl == null) {
                Log.error(BinaryFileDetUploader.TAG, "Unable to parse: https://det-ta-g7g.amazon.com/DeviceEventProxy/DETLogServlet");
                return;
            }
            Request.Builder builderUrl = new Request.Builder().url(httpUrl.newBuilder().addQueryParameter("key", this.mReportKey).build());
            builderUrl.header(HttpHeader.CONTENT_TYPE, "Application/Raw").header("X-DSN", BinaryFileDetUploader.this.mDeviceId).header("X-DeviceType", BinaryFileDetUploader.this.mDeviceType).header("X-DeviceFirmwareVersion", BinaryFileDetUploader.this.mDeviceFirmwareVersion).header("X-Upload-Tag", BinaryFileDetUploader.this.mUploadTag).header("X-Content-Type", "BinaryFile");
            try {
                builderUrl.post(RequestBody.create(MediaType.parse("Application/Raw"), buildBody()));
                Response responseUploadReport = uploadReport(builderUrl.build());
                if (!responseUploadReport.isSuccessful()) {
                    BinaryFileDetUploader.this.mDetUploadListener.onError(this.mFile, responseUploadReport);
                } else {
                    BinaryFileDetUploader.this.mDetUploadListener.onSuccess(this.mFile);
                }
            } catch (IOException e) {
                BinaryFileDetUploader.this.mDetUploadListener.onError(this.mFile, e);
            }
        }

        @NonNull
        private byte[] buildBody() throws IOException {
            byte[] bArrGzip = FileUtil.gzip(FileUtil.loadBytesFromFile(this.mFile));
            String name = this.mFile.getName();
            StringBuilder sb = new StringBuilder();
            Locale locale = Locale.US;
            sb.append(String.format(locale, "Files: %s.0%n", name));
            sb.append(String.format(locale, "MFBS/1.0 1%n", new Object[0]));
            sb.append(String.format(locale, "Content-Name: %s%n", name));
            sb.append(String.format(locale, "Content-Encoding: %s%n", "gzip"));
            sb.append(String.format(locale, "Content-Length: %d%n%n", Integer.valueOf(bArrGzip.length)));
            byte[] bytes = sb.toString().getBytes(Charsets.UTF_8);
            byte[] bArr = new byte[bytes.length + bArrGzip.length];
            System.arraycopy(bytes, 0, bArr, 0, bytes.length);
            System.arraycopy(bArrGzip, 0, bArr, bytes.length, bArrGzip.length);
            return bArr;
        }

        private Response uploadReport(Request request) throws IOException {
            return FirebasePerfOkHttpClient.execute(UnifiedOkHttpClient.newClient().newCall(request));
        }
    }
}
