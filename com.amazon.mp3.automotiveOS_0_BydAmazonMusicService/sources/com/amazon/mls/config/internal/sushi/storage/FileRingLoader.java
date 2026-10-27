package com.amazon.mls.config.internal.sushi.storage;

import com.amazon.mls.config.internal.core.configuration.Region;
import com.amazon.mls.config.internal.core.logcat.LogLevel;
import com.amazon.mls.config.internal.core.logcat.LogcatProxy;
import com.amazon.mls.config.internal.core.metrics.InternalMetrics;
import com.amazon.mls.config.internal.sushi.SushiFile;
import com.amazon.mls.config.internal.sushi.util.FileUtils;
import com.amazonaws.mobileconnectors.remoteconfiguration.Attributes;
import java.io.File;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: classes3.dex */
public class FileRingLoader {
    private final FileRingNameFormatter fileNameFormatter = new FileRingNameFormatter();
    private final File fileRingDir;
    private final long maxRingSizeBytes;
    private long ringSizeBytes;

    public FileRingLoader(File file, long j) {
        this.fileRingDir = FileUtils.getChildPath(file, "file_ring");
        this.maxRingSizeBytes = j;
        LogcatProxy.log("Created Sushi FileRingLoader with parentDir '" + file.getAbsolutePath() + "' and maxStorageBytes " + j);
    }

    public FileRing loadFileRing() {
        try {
            FileUtils.setupDir(this.fileRingDir);
            LinkedList<SushiFile> linkedListLoadFiles = loadFiles();
            sortFileList(linkedListLoadFiles);
            return new FileRingImpl(this.fileRingDir, linkedListLoadFiles, this.maxRingSizeBytes, this.ringSizeBytes, getIdForLastFile(linkedListLoadFiles));
        } catch (IllegalArgumentException e) {
            DummyFileRing dummyFileRing = new DummyFileRing();
            LogcatProxy.log(LogLevel.ERROR, "Failed to load the fil File Ring. Using a Dummy implementation.", e);
            InternalMetrics.logCounter("sushi_ring_load_failed_e", 1L);
            return dummyFileRing;
        }
    }

    private LinkedList<SushiFile> loadFiles() {
        this.ringSizeBytes = 0L;
        LinkedList<SushiFile> linkedList = new LinkedList<>();
        for (File file : FileUtils.listFiles(this.fileRingDir)) {
            SushiFile sushiFileTryLoadFile = tryLoadFile(file);
            if (sushiFileTryLoadFile != null) {
                linkedList.add(sushiFileTryLoadFile);
                this.ringSizeBytes += sushiFileTryLoadFile.getFileLength();
            } else {
                LogcatProxy.log(LogLevel.ERROR, String.format(Locale.US, "Unexpected file name pattern found in file ring dir. Deleting it: %s", file.getAbsolutePath()));
                FileUtils.safelyDeleteFromDisk(file);
            }
        }
        return linkedList;
    }

    private boolean isOlderVersion(String str) {
        return !"v2".equals(str);
    }

    private SushiFile tryLoadFile(File file) {
        String[] strArrSplit = file.getName().split(Attributes.PREDEFINED_ATTRIBUTE_PREFIX, 4);
        if (strArrSplit.length != 4) {
            LogcatProxy.log(String.format(Locale.US, "File %s has bad format", file.getAbsolutePath()));
            return null;
        }
        if (isOlderVersion(strArrSplit[0])) {
            LogcatProxy.log(String.format(Locale.US, "File %s has old version", file.getAbsolutePath()));
            return null;
        }
        try {
            long j = Long.parseLong(strArrSplit[1]);
            String str = strArrSplit[2];
            return new SushiFile.Builder().withEventCount(Integer.parseInt(strArrSplit[3])).withDestinationRegion(Region.getRegionFromString(str)).withFileId(j).withFileLength(file.length()).withSupportFile(file).build();
        } catch (IllegalArgumentException unused) {
            LogcatProxy.log(String.format(Locale.US, "File %s has bad format", file.getAbsolutePath()));
            return null;
        }
    }

    private void sortFileList(List<SushiFile> list) {
        Collections.sort(list, new Comparator<SushiFile>() { // from class: com.amazon.mls.config.internal.sushi.storage.FileRingLoader.1
            @Override // java.util.Comparator
            public int compare(SushiFile sushiFile, SushiFile sushiFile2) {
                return Long.valueOf(sushiFile.getFileId()).compareTo(Long.valueOf(sushiFile2.getFileId()));
            }
        });
    }

    private long getIdForLastFile(List<SushiFile> list) {
        if (list.size() == 0) {
            return 0L;
        }
        return list.get(list.size() - 1).getFileId();
    }
}
