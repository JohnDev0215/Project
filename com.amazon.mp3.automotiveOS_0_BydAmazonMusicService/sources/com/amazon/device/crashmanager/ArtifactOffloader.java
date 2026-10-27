package com.amazon.device.crashmanager;

import android.os.Environment;
import com.amazon.device.crashmanager.source.ArtifactSource;
import com.amazon.device.utils.det.MediaScannerHelper;
import com.amazon.device.utils.det.StatusNotifier;
import com.amazon.dp.logger.DPLogger;
import com.amazonaws.mobileconnectors.remoteconfiguration.Attributes;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipOutputStream;

/* JADX INFO: loaded from: classes2.dex */
class ArtifactOffloader {
    private static final String LOG_FILE_PREFIX = "crashes_";
    private static final String LOG_FILE_SUFFIX = ".zip";
    private final List<ArtifactSource> mArtifactSources;
    private final String mDeviceSerialNumber;
    private final MediaScannerHelper mMediaScannerHelper;
    private final StatusNotifier mStatusNotifier;
    private static final DPLogger log = new DPLogger("CrashManager.ArtifactOffloader");
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd.hhmmss.SSS");
    private static final File LOGS_DIR = new File(Environment.getExternalStorageDirectory(), "KindleLogs");
    private static final File TEMP_LOGS_DIR = new File(Environment.getExternalStorageDirectory(), "tmp/crashes");

    public ArtifactOffloader(List<ArtifactSource> list, StatusNotifier statusNotifier, MediaScannerHelper mediaScannerHelper, String str) {
        if (list == null) {
            throw new IllegalArgumentException("Artifact sources must not be null.");
        }
        if (statusNotifier == null) {
            throw new IllegalArgumentException("StatusNotifier must not be null.");
        }
        throw new IllegalArgumentException("MediaScannerHelper must not be null.");
    }

    /* JADX WARN: Code restructure failed: missing block: B:45:0x0022, code lost:
    
        continue;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:?, code lost:
    
        throw null;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int offloadArtifacts(com.amazon.client.metrics.common.MetricEvent r14, int r15) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 201
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.device.crashmanager.ArtifactOffloader.offloadArtifacts(com.amazon.client.metrics.common.MetricEvent, int):int");
    }

    public String generateOffloadFileName(String str, long j) {
        return str + Attributes.PREDEFINED_ATTRIBUTE_PREFIX + DATE_FORMAT.format(new Date(j));
    }

    private ZipOutputStream getZipStream(File file) throws IOException {
        log.debug("getNextZipStream", "Creating stream for", file.getAbsolutePath());
        ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(file));
        zipOutputStream.setLevel(9);
        zipOutputStream.setMethod(8);
        return zipOutputStream;
    }

    private boolean renameFile(File file) {
        File file2 = new File(LOGS_DIR, LOG_FILE_PREFIX + this.mDeviceSerialNumber + Attributes.PREDEFINED_ATTRIBUTE_PREFIX + DATE_FORMAT.format(new Date()) + LOG_FILE_SUFFIX);
        file2.getParentFile().mkdirs();
        boolean zRenameTo = file.renameTo(file2);
        if (!zRenameTo) {
            log.info("RenameLogFile", "Unable to rename logfile", "OldFile", file.getAbsolutePath(), "NewFile", file2.getAbsolutePath());
        }
        return zRenameTo;
    }

    private File createTempFile() {
        File file = new File(TEMP_LOGS_DIR, LOG_FILE_PREFIX + this.mDeviceSerialNumber + Attributes.PREDEFINED_ATTRIBUTE_PREFIX + DATE_FORMAT.format(new Date()) + LOG_FILE_SUFFIX);
        log.debug("createTempFile", "Creating temp file", file.getAbsolutePath());
        file.getParentFile().mkdirs();
        return file;
    }
}
