package com.amazon.mls.config.internal.sushi;

import android.content.Context;
import com.amazon.mls.config.internal.core.exceptions.ClientSideException;
import com.amazon.mls.config.internal.core.exceptions.ServerSideException;
import com.amazon.mls.config.internal.core.logcat.LogLevel;
import com.amazon.mls.config.internal.core.logcat.LogcatProxy;
import com.amazon.mls.config.internal.core.metrics.InternalMetrics;
import com.amazon.mls.config.internal.core.network.FileUploader;
import com.amazon.mls.config.internal.sushi.storage.FileRing;
import com.amazon.mls.config.internal.sushi.storage.FileRingLoader;
import com.amazon.mls.config.internal.sushi.writer.FileWriter;
import com.amazon.mls.config.internal.sushi.writer.FileWriterLoader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public final class SushiRecorder {
    private static final Map<File, SushiRecorder> INSTANCES = new HashMap();
    private final Context context;
    private final FileRing fileRing;
    private final FileUploader fileUploader;
    private final FileWriter fileWriter;
    private final long maxStorageBytes;
    private final File parentDir;
    private final Object sharedLock = new Object();
    private final String sourceGroup;

    public static SushiRecorder getSushiRecorderInstance(File file, String str, long j, Context context) {
        synchronized (SushiRecorder.class) {
            Map<File, SushiRecorder> map = INSTANCES;
            if (map.containsKey(file)) {
                return map.get(file);
            }
            SushiRecorder sushiRecorder = new SushiRecorder(file, new FileUploader(str), str, j, context);
            map.put(file, sushiRecorder);
            return sushiRecorder;
        }
    }

    public SushiRecorder(File file, FileUploader fileUploader, String str, long j, Context context) {
        this.parentDir = file;
        this.sourceGroup = str;
        this.fileUploader = fileUploader;
        this.context = context;
        this.maxStorageBytes = j;
        this.fileWriter = new FileWriterLoader(file).loadSushiWriter();
        this.fileRing = new FileRingLoader(file, j).loadFileRing();
    }

    public synchronized void upload() {
        List<SushiFile> files;
        LogcatProxy.logForAutomation("Starting upload of previously persisted events in path " + this.parentDir);
        synchronized (this.sharedLock) {
            rotateFile();
            files = this.fileRing.getFiles();
        }
        List<SushiFile> listUploadFiles = uploadFiles(files);
        synchronized (this.sharedLock) {
            Iterator<SushiFile> it = listUploadFiles.iterator();
            while (it.hasNext()) {
                this.fileRing.delete(it.next());
            }
        }
        LogcatProxy.logForAutomation("Completed upload of previously persisted events in path " + this.parentDir);
    }

    public boolean hasEventsReadyForUpload() {
        boolean z;
        synchronized (this.sharedLock) {
            z = !this.fileWriter.fileIsEmpty() || this.fileRing.getFiles().size() > 0;
        }
        return z;
    }

    private List<SushiFile> uploadFiles(List<SushiFile> list) throws Throwable {
        boolean zUploadFile;
        ArrayList arrayList = new ArrayList();
        for (SushiFile sushiFile : list) {
            try {
                zUploadFile = this.fileUploader.uploadFile(sushiFile.getSupportFile(), sushiFile.getDestinationEndpoint());
            } catch (ClientSideException unused) {
                zUploadFile = true;
            } catch (ServerSideException unused2) {
                zUploadFile = false;
            }
            if (zUploadFile) {
                arrayList.add(sushiFile);
            }
        }
        return arrayList;
    }

    private void rotateFile() {
        try {
            SushiFile sushiFileRotate = this.fileWriter.rotate();
            if (sushiFileRotate != null) {
                addFileForUploadThenDelete(sushiFileRotate);
            }
        } catch (IOException e) {
            InternalMetrics.logCounter("sushi_recorder_rotate_failed_e", 1L);
            LogcatProxy.log(LogLevel.ERROR, "Failed to rotate fileWriter and add the file to the file ring.", e);
        }
    }

    private void addFileForUploadThenDelete(SushiFile sushiFile) {
        this.fileRing.addFile(sushiFile);
        sushiFile.delete();
    }
}
