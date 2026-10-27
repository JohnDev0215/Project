package com.amazon.device.crashmanager;

import android.os.Build;
import android.system.ErrnoException;
import android.system.OsConstants;
import com.amazon.client.metrics.common.MetricEvent;
import com.amazon.device.crashmanager.metrics.MetricsConstants;
import com.amazon.device.crashmanager.processor.ArtifactProcessor;
import com.amazon.device.crashmanager.source.ArtifactSource;
import com.amazon.device.crashmanager.source.DropBoxArtifactSource;
import com.amazon.device.crashmanager.utils.CrashDescriptorDedupeUtil;
import com.amazon.device.utils.det.NetworkManager;
import com.amazon.device.utils.det.StatusNotifier;
import com.amazon.dp.logger.DPLogger;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.mobileconnectors.remoteconfiguration.clientcontextdecorator.RemoteConfigurationAndroidClientContextDecorator;
import com.google.firebase.perf.FirebasePerformance;
import com.google.firebase.perf.network.FirebasePerfUrlConnection;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeoutException;

/* JADX INFO: loaded from: classes2.dex */
class ArtifactUploader {
    private static final int BACK_OFF_MULTIPLIER = 2;
    private static final int HTTP_CONNECT_TIMEOUT = 10000;
    private static final int HTTP_READ_TIMEOUT = 60000;
    private static final long INITIAL_BACK_OFF_MS = 500;
    private static final int MAX_RETRIES = 5;
    private static final DPLogger log = new DPLogger("ArtifactUploader");
    private final String TAG = getClass().getSimpleName();
    private final boolean mAllowWANUpload;
    private final List<ArtifactProcessor> mArtifactProcessors;
    private final List<ArtifactSource> mArtifactSources;
    private final String mDeviceSerialNumber;
    private final String mDeviceType;
    private final Domain mDomain;
    private final CrashDescriptorDedupeUtil mDropBoxCrashDedupeUtil;
    private final NetworkManager mNetworkManager;
    private final StatusNotifier mStatusNotifier;

    private enum ArtifactUploadStatus {
        SUCCESS,
        DE_DUPED,
        SKIP,
        FAILED,
        SERVER_ERROR
    }

    public enum CrashUploadStatus {
        SUCCESS,
        FAILURE,
        SERVER_ERROR
    }

    public class CrashUploadStatistics {
        private final CrashUploadStatus mCrashUploadStatus;
        private final int mNumArtifactsFailed;
        private final int mNumArtifactsUploaded;

        public CrashUploadStatistics(ArtifactUploader artifactUploader, CrashUploadStatus crashUploadStatus) {
            this(crashUploadStatus, 0, 0);
        }

        public CrashUploadStatistics(CrashUploadStatus crashUploadStatus, int i, int i2) {
            this.mCrashUploadStatus = crashUploadStatus;
            this.mNumArtifactsUploaded = i;
            this.mNumArtifactsFailed = i2;
        }

        public CrashUploadStatus getCrashUploadStatus() {
            return this.mCrashUploadStatus;
        }

        public int getNumArtifactsFailed() {
            return this.mNumArtifactsFailed;
        }

        public int getNumArtifactsUploaded() {
            return this.mNumArtifactsUploaded;
        }
    }

    private class ArtifactUploaderResult {
        private final ArtifactUploadStatus mArtifactUploadStatus;
        private final String mCrashDescriptor;
        private final String mUploadErrorMessage;
        private final String mUploadErrorTag;

        public ArtifactUploaderResult(ArtifactUploader artifactUploader, ArtifactUploadStatus artifactUploadStatus, String str) {
            this(artifactUploadStatus, str, null, null);
        }

        public ArtifactUploaderResult(ArtifactUploadStatus artifactUploadStatus, String str, String str2, String str3) {
            if (artifactUploadStatus == null) {
                throw new IllegalArgumentException("artifact upload status cannot be null");
            }
            this.mArtifactUploadStatus = artifactUploadStatus;
            this.mCrashDescriptor = str;
            this.mUploadErrorTag = str2;
            this.mUploadErrorMessage = str3;
        }

        public ArtifactUploadStatus getArtifactUploadStatus() {
            return this.mArtifactUploadStatus;
        }

        public String getCrashDescriptor() {
            return this.mCrashDescriptor;
        }

        public String getUploadErrorTag() {
            return this.mUploadErrorTag;
        }

        public String getUploadErrorMessage() {
            return this.mUploadErrorMessage;
        }
    }

    public ArtifactUploader(Domain domain, String str, String str2, List<ArtifactSource> list, List<ArtifactProcessor> list2, CrashDescriptorDedupeUtil crashDescriptorDedupeUtil, StatusNotifier statusNotifier, NetworkManager networkManager, boolean z) throws IllegalArgumentException {
        if (domain == null) {
            throw new IllegalArgumentException("Domain must not be null.");
        }
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("Device type: " + str + ", is invalid");
        }
        if (str2 == null || str2.length() == 0) {
            throw new IllegalArgumentException("Device type: " + str2 + ", is invalid");
        }
        if (list == null) {
            throw new IllegalArgumentException("Artifact sources must not be null.");
        }
        if (list2 == null) {
            throw new IllegalArgumentException("Artifact processors must not be null.");
        }
        if (crashDescriptorDedupeUtil == null) {
            throw new IllegalArgumentException("CrashDescriptorStorageUtil must not be null.");
        }
        if (statusNotifier == null) {
            throw new IllegalArgumentException("StatusNotifier must not be null.");
        }
        if (networkManager == null) {
            throw new IllegalArgumentException("NetworkManager must not be null.");
        }
        this.mDomain = domain;
        this.mDeviceType = str;
        this.mDeviceSerialNumber = str2;
        this.mArtifactSources = list;
        this.mArtifactProcessors = list2;
        this.mDropBoxCrashDedupeUtil = crashDescriptorDedupeUtil;
        this.mStatusNotifier = statusNotifier;
        this.mNetworkManager = networkManager;
        this.mAllowWANUpload = z;
    }

    public CrashUploadStatistics uploadArtifacts(MetricEvent metricEvent) {
        return uploadArtifacts(metricEvent, null);
    }

    public CrashUploadStatistics uploadArtifacts(MetricEvent metricEvent, String str) {
        int i;
        int i2;
        Iterator<ArtifactSource> it;
        boolean z;
        double d = 1.0d;
        if (this.mAllowWANUpload) {
            if (!this.mNetworkManager.isConnected()) {
                log.info(this.TAG, "Cannot upload crashes since device not connected to a network", new Object[0]);
                this.mStatusNotifier.broadcastUploadStatus("Cannot upload crashes due to no connectivity");
                metricEvent.addCounter(MetricsConstants.COUNTER_NO_NETWORK, 1.0d);
                return new CrashUploadStatistics(CrashUploadStatus.FAILURE, 0, 0);
            }
        } else if (!this.mNetworkManager.isWifiConnected() && !this.mNetworkManager.isEthernetConnected()) {
            log.info(this.TAG, "Cannot upload crashes since device is not connected to wifi or ethernet.", new Object[0]);
            this.mStatusNotifier.broadcastUploadStatus("Cannot upload crashes due to no usable connection");
            metricEvent.addCounter(MetricsConstants.COUNTER_NO_NETWORK, 1.0d);
            return new CrashUploadStatistics(CrashUploadStatus.FAILURE, 0, 0);
        }
        this.mStatusNotifier.broadcastUploadStatus("Uploading artifacts.");
        log.debug(this.TAG, "Uploading artifacts.", new Object[0]);
        HashSet hashSet = new HashSet();
        HashSet hashSet2 = new HashSet();
        try {
            try {
                Iterator<ArtifactSource> it2 = this.mArtifactSources.iterator();
                i = 0;
                i2 = 0;
                while (it2.hasNext()) {
                    try {
                        ArtifactSource next = it2.next();
                        boolean z2 = next instanceof DropBoxArtifactSource;
                        if (z2) {
                            buildDedupeMapFromDropbox((DropBoxArtifactSource) next, metricEvent);
                        }
                        while (true) {
                            Artifact nextArtifact = next.getNextArtifact(metricEvent, CrashManagerActions.ARTIFACT_UPLOAD);
                            if (nextArtifact != null) {
                                metricEvent.incrementCounter(MetricsConstants.COUNTER_ARTIFACTS_READ, d);
                                ArtifactUploaderResult artifactUploaderResultUploadArtifact = uploadArtifact(metricEvent, nextArtifact, str, z2);
                                try {
                                    nextArtifact.close();
                                    it = it2;
                                    z = z2;
                                } catch (IOException e) {
                                    it = it2;
                                    z = z2;
                                    log.warn(this.TAG, "Failed to close artifact.", e);
                                }
                                String crashDescriptor = artifactUploaderResultUploadArtifact.getCrashDescriptor();
                                if (crashDescriptor != null) {
                                    hashSet.add(crashDescriptor);
                                }
                                String str2 = artifactUploaderResultUploadArtifact.mUploadErrorMessage;
                                String uploadErrorTag = artifactUploaderResultUploadArtifact.getUploadErrorTag();
                                if (str2 != null && uploadErrorTag != null) {
                                    hashSet2.add(uploadErrorTag + "." + str2);
                                }
                                int i3 = AnonymousClass1.$SwitchMap$com$amazon$device$crashmanager$ArtifactUploader$ArtifactUploadStatus[artifactUploaderResultUploadArtifact.getArtifactUploadStatus().ordinal()];
                                if (i3 != 1) {
                                    if (i3 == 2) {
                                        i2++;
                                        log.info(this.TAG, "Skipped an artifact due to client error", new Object[0]);
                                        metricEvent.incrementCounter(MetricsConstants.COUNTER_ARTIFACTS_FAILED, 1.0d);
                                        next.saveCurrentIndex();
                                    } else if (i3 == 3) {
                                        log.info(this.TAG, "Skipped an artifact since this is a dupe and original artifact already uploaded", new Object[0]);
                                        metricEvent.addCounter(MetricsConstants.COUNTER_ARTIFACTS_SKIPPED, 1.0d);
                                        next.saveCurrentIndex();
                                    } else {
                                        if (i3 == 4) {
                                            log.info(this.TAG, "Failed to upload artifact after local retries. CrashUpload will halt now and will be retried later", new Object[0]);
                                            CrashUploadStatistics crashUploadStatistics = new CrashUploadStatistics(CrashUploadStatus.FAILURE, i, i2);
                                            Iterator it3 = hashSet.iterator();
                                            while (it3.hasNext()) {
                                                metricEvent.addString(MetricsConstants.STRING_UPLOAD_CRASH_DESCRIPTOR, (String) it3.next());
                                            }
                                            Iterator it4 = hashSet2.iterator();
                                            while (it4.hasNext()) {
                                                metricEvent.addString(MetricsConstants.STRING_UPLOAD_EXCEPTIONS, (String) it4.next());
                                            }
                                            return crashUploadStatistics;
                                        }
                                        if (i3 == 5) {
                                            log.info(this.TAG, "Receiving consistent 500's from Server. CrashUpload will halt now and will be retried later", new Object[0]);
                                            return new CrashUploadStatistics(CrashUploadStatus.SERVER_ERROR, i, i2);
                                        }
                                    }
                                    d = 1.0d;
                                } else {
                                    i++;
                                    log.info(this.TAG, "Uploaded an artifact.", new Object[0]);
                                    metricEvent.addCounter(MetricsConstants.COUNTER_ARTIFACTS_UPLOADED, 1.0d);
                                    d = 1.0d;
                                    metricEvent.addCounter(nextArtifact.getTag() + MetricsConstants.COUNTER_ARTIFACT_UPLOADED_SUCCESS, 1.0d);
                                    next.saveCurrentIndex();
                                }
                                it2 = it;
                                z2 = z;
                            }
                        }
                    } catch (Exception e2) {
                        e = e2;
                        log.error(this.TAG, "Exception thrown while uploading crash entries", e);
                        this.mStatusNotifier.broadcastUploadStatus("Exception while uploading artifacts:\n " + e.getMessage());
                        CrashUploadStatistics crashUploadStatistics2 = new CrashUploadStatistics(CrashUploadStatus.FAILURE, i, i2);
                        Iterator it5 = hashSet.iterator();
                        while (it5.hasNext()) {
                            metricEvent.addString(MetricsConstants.STRING_UPLOAD_CRASH_DESCRIPTOR, (String) it5.next());
                        }
                        Iterator it6 = hashSet2.iterator();
                        while (it6.hasNext()) {
                            metricEvent.addString(MetricsConstants.STRING_UPLOAD_EXCEPTIONS, (String) it6.next());
                        }
                        return crashUploadStatistics2;
                    }
                }
                Iterator it7 = hashSet.iterator();
                while (it7.hasNext()) {
                    metricEvent.addString(MetricsConstants.STRING_UPLOAD_CRASH_DESCRIPTOR, (String) it7.next());
                }
                Iterator it8 = hashSet2.iterator();
                while (it8.hasNext()) {
                    metricEvent.addString(MetricsConstants.STRING_UPLOAD_EXCEPTIONS, (String) it8.next());
                }
                log.info(this.TAG, "Finished uploading artifacts. Uploaded " + i + " artifacts.", new Object[0]);
                this.mStatusNotifier.broadcastUploadStatus("Uploaded " + i + " artifacts.");
                this.mDropBoxCrashDedupeUtil.clearAll();
                return new CrashUploadStatistics(CrashUploadStatus.SUCCESS, i, i2);
            } catch (Exception e3) {
                e = e3;
                i = 0;
                i2 = 0;
            }
        } finally {
            Iterator it9 = hashSet.iterator();
            while (it9.hasNext()) {
                metricEvent.addString(MetricsConstants.STRING_UPLOAD_CRASH_DESCRIPTOR, (String) it9.next());
            }
            Iterator it10 = hashSet2.iterator();
            while (it10.hasNext()) {
                metricEvent.addString(MetricsConstants.STRING_UPLOAD_EXCEPTIONS, (String) it10.next());
            }
        }
    }

    /* JADX INFO: renamed from: com.amazon.device.crashmanager.ArtifactUploader$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$device$crashmanager$ArtifactUploader$ArtifactUploadStatus;

        static {
            int[] iArr = new int[ArtifactUploadStatus.values().length];
            $SwitchMap$com$amazon$device$crashmanager$ArtifactUploader$ArtifactUploadStatus = iArr;
            try {
                iArr[ArtifactUploadStatus.SUCCESS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$device$crashmanager$ArtifactUploader$ArtifactUploadStatus[ArtifactUploadStatus.SKIP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$device$crashmanager$ArtifactUploader$ArtifactUploadStatus[ArtifactUploadStatus.DE_DUPED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$device$crashmanager$ArtifactUploader$ArtifactUploadStatus[ArtifactUploadStatus.FAILED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$device$crashmanager$ArtifactUploader$ArtifactUploadStatus[ArtifactUploadStatus.SERVER_ERROR.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    private void buildDedupeMapFromDropbox(DropBoxArtifactSource dropBoxArtifactSource, MetricEvent metricEvent) {
        log.info(this.TAG, "Starting to build descriptor dedupe map", new Object[0]);
        metricEvent.startTimer(MetricsConstants.TIMER_BUILD_COUNTER_TIME);
        while (true) {
            Artifact nextArtifact = dropBoxArtifactSource.getNextArtifact(metricEvent, CrashManagerActions.BUILD_MAP);
            if (nextArtifact != null) {
                try {
                    processArtifact(nextArtifact, CrashManagerActions.BUILD_MAP);
                    nextArtifact.close();
                } catch (IOException e) {
                    log.warn(this.TAG, "Failed to close artifact.", e);
                } catch (Exception e2) {
                    log.warn(this.TAG, "Exception thrown when process artifact.", e2);
                }
                this.mDropBoxCrashDedupeUtil.increaseCounter(nextArtifact.getTag(), nextArtifact.getCrashDescriptor());
            } else {
                dropBoxArtifactSource.saveBuildMapIndex();
                this.mDropBoxCrashDedupeUtil.persistCrashDescriptors();
                metricEvent.stopTimer(MetricsConstants.TIMER_BUILD_COUNTER_TIME);
                log.info(this.TAG, "Finish building descriptor dedupe map", new Object[0]);
                return;
            }
        }
    }

    protected ArtifactUploaderResult uploadArtifact(MetricEvent metricEvent, Artifact artifact, String str, boolean z) {
        try {
            log.debug(this.TAG, "About to upload artifact", "Tag", artifact.getTag(), "Creation time UTC", Long.valueOf(artifact.getCreationTimeUTCMillis()), "DeviceType", this.mDeviceType, RemoteConfigurationAndroidClientContextDecorator.DEVICE_SERIAL_NUMBER, this.mDeviceSerialNumber);
            metricEvent.incrementCounter(MetricsConstants.COUNTER_UPLOAD_ATTEMPT, 1.0d);
            metricEvent.startTimer(MetricsConstants.TIMER_UPLOAD_TIME);
            return uploadArtifactWithRetries(metricEvent, artifact, str, z);
        } catch (Exception e) {
            log.error(this.TAG, "Exception while uploading crashes", e);
            metricEvent.incrementCounter(MetricsConstants.COUNTER_UPLOAD_FAILED, 1.0d);
            metricEvent.addCounter(MetricsConstants.COUNTER_UPLOAD_UNKNOWN_EXCEPTION, 1.0d);
            metricEvent.addCounter("uploadUnknowException." + e.getClass().getName(), 1.0d);
            return new ArtifactUploaderResult(ArtifactUploadStatus.SKIP, artifact.getCrashDescriptor(), MetricsConstants.STRING_UPLOAD_UNKNOWN_EXCEPTION, getExceptionMessage(e));
        } finally {
            metricEvent.stopTimer(MetricsConstants.TIMER_UPLOAD_TIME);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:125:0x01a5 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:129:0x01f4 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00c7  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x01da A[Catch: all -> 0x02cd, TryCatch #6 {all -> 0x02cd, blocks: (B:3:0x000f, B:5:0x0018, B:14:0x006f, B:31:0x00b5, B:39:0x00d4, B:45:0x0117, B:68:0x01c5, B:70:0x01da, B:71:0x01f4, B:47:0x013e, B:49:0x0166, B:54:0x0178, B:59:0x01a7, B:79:0x023a, B:85:0x025c, B:89:0x027d, B:28:0x0095), top: B:114:0x000f }] */
    /* JADX WARN: Type inference failed for: r1v25 */
    /* JADX WARN: Type inference failed for: r1v26 */
    /* JADX WARN: Type inference failed for: r1v28 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private com.amazon.device.crashmanager.ArtifactUploader.ArtifactUploaderResult uploadArtifactWithRetries(com.amazon.client.metrics.common.MetricEvent r26, com.amazon.device.crashmanager.Artifact r27, java.lang.String r28, boolean r29) throws java.lang.Exception {
        /*
            Method dump skipped, instruction units count: 734
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.device.crashmanager.ArtifactUploader.uploadArtifactWithRetries(com.amazon.client.metrics.common.MetricEvent, com.amazon.device.crashmanager.Artifact, java.lang.String, boolean):com.amazon.device.crashmanager.ArtifactUploader$ArtifactUploaderResult");
    }

    private ArtifactUploadStatus attemptUpload(MetricEvent metricEvent, Artifact artifact, String str, boolean z, InputStream inputStream) throws Exception {
        String str2 = String.format("%s-%d", artifact.getTag(), Long.valueOf(artifact.getCreationTimeUTCMillis()));
        HttpURLConnection httpURLConnection = null;
        try {
            String crashDescriptor = artifact.getCrashDescriptor();
            if (crashDescriptor != null && z && !this.mDropBoxCrashDedupeUtil.contains(artifact.getTag(), crashDescriptor)) {
                log.debug(this.TAG, "CrashDescriptor: " + crashDescriptor + " not found in SharedPreferences, skipping.", new Object[0]);
                return ArtifactUploadStatus.DE_DUPED;
            }
            HttpURLConnection httpUrlConnection = getHttpUrlConnection(str2);
            addRequestHeaders(httpUrlConnection, str2, str);
            httpUrlConnection.setConnectTimeout(HTTP_CONNECT_TIMEOUT);
            httpUrlConnection.setReadTimeout(60000);
            httpUrlConnection.setFixedLengthStreamingMode(inputStream.available());
            httpUrlConnection.setDoOutput(true);
            httpUrlConnection.setRequestMethod(FirebasePerformance.HttpMethod.POST);
            log.debug(this.TAG, "Upload Size: " + inputStream.available(), new Object[0]);
            metricEvent.incrementCounter(MetricsConstants.COUNTER_CRASH_SIZE, (double) inputStream.available());
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(httpUrlConnection.getOutputStream());
            copyBytesToOutputStream(inputStream, bufferedOutputStream);
            bufferedOutputStream.flush();
            ArtifactUploadStatus artifactUploadStatusHandleResponse = handleResponse(httpUrlConnection);
            httpUrlConnection.disconnect();
            return artifactUploadStatusHandleResponse;
        } catch (Throwable th) {
            if (0 != 0) {
                httpURLConnection.disconnect();
            }
            throw th;
        }
    }

    private void addRequestHeaders(HttpURLConnection httpURLConnection, String str, String str2) {
        httpURLConnection.addRequestProperty("Accept-Encoding", "utf-8");
        httpURLConnection.addRequestProperty(HttpHeader.CONTENT_TYPE, "Application/Raw");
        httpURLConnection.addRequestProperty("X-Anonymous-Tag", this.mDeviceSerialNumber);
        httpURLConnection.addRequestProperty("X-DeviceType", this.mDeviceType);
        httpURLConnection.addRequestProperty("X-DeviceFirmwareVersion", Build.DISPLAY);
        httpURLConnection.setRequestProperty("X-Content-Type", "CrashReport");
        log.debug(this.TAG, "Key for the file sent to DET", str);
        if (str2 == null || str2.length() <= 0) {
            return;
        }
        httpURLConnection.setRequestProperty("X-Upload-Tag", str2);
    }

    private HttpURLConnection getHttpUrlConnection(String str) throws IOException {
        return (HttpURLConnection) ((URLConnection) FirebasePerfUrlConnection.instrument(new URL(DetEndpointConfig.getServiceEndpoint(this.mDomain) + "/DeviceEventProxy/DETLogServlet?key=" + str).openConnection()));
    }

    private ArtifactUploadStatus handleResponse(HttpURLConnection httpURLConnection) throws TimeoutException, IOException {
        int responseCode = httpURLConnection.getResponseCode();
        String responseMessage = httpURLConnection.getResponseMessage();
        if (responseCode >= 200 && responseCode < 300) {
            log.info("handleResponse", String.format("Successfully uploaded crash; code: %s, message: %s", Integer.valueOf(responseCode), responseMessage), new Object[0]);
            return ArtifactUploadStatus.SUCCESS;
        }
        if (responseCode == 408) {
            log.error("handleResponse", String.format("Http client timeout while uploading crash; code: %s, message: %s", Integer.valueOf(responseCode), responseMessage), new Object[0]);
            throw new TimeoutException("Http client timeout while uploading crash; message: " + responseMessage);
        }
        if (responseCode >= 400 && responseCode < 500) {
            log.error("handleResponse", String.format("Client error while uploading crash; code: %s, message: %s", Integer.valueOf(responseCode), responseMessage), new Object[0]);
            return ArtifactUploadStatus.FAILED;
        }
        if (responseCode >= 500 && responseCode < 600) {
            return ArtifactUploadStatus.SERVER_ERROR;
        }
        log.error("handleResponse", String.format("Unexpected response code while uploading crash; code: %s, message: %s", Integer.valueOf(responseCode), responseMessage), new Object[0]);
        return ArtifactUploadStatus.FAILED;
    }

    private String getExceptionMessage(Exception exc) {
        if (exc.getCause() == null) {
            return exc.getMessage();
        }
        return exc.getMessage() + " " + exc.getCause().getMessage();
    }

    private InputStream processArtifact(Artifact artifact, String str) throws Exception {
        return chooseArtifactProcessor(artifact).processArtifact(artifact, str);
    }

    private ArtifactProcessor chooseArtifactProcessor(Artifact artifact) {
        for (ArtifactProcessor artifactProcessor : this.mArtifactProcessors) {
            if (artifactProcessor != null && artifactProcessor.canProcessTag(artifact.getTag())) {
                return artifactProcessor;
            }
        }
        throw new IllegalStateException("No artifact processor available for artifact. Tag:" + artifact.getTag());
    }

    protected boolean isConnectionRefusedError(Exception exc) {
        if (!(exc instanceof ConnectException)) {
            return false;
        }
        Throwable cause = exc.getCause();
        if ((cause instanceof ErrnoException) && OsConstants.ECONNREFUSED == ((ErrnoException) cause).errno) {
            return true;
        }
        String message = exc.getMessage();
        return message != null && message.contains("ECONNREFUSED");
    }

    private void copyBytesToOutputStream(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[1024];
        while (true) {
            int i = inputStream.read(bArr);
            if (i == -1) {
                return;
            } else {
                outputStream.write(bArr, 0, i);
            }
        }
    }
}
