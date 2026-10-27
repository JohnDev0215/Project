package com.amazon.mls.config.internal.sushi.storage;

import com.amazon.mls.config.internal.core.exceptions.ClientSideException;
import com.amazon.mls.config.internal.core.logcat.LogLevel;
import com.amazon.mls.config.internal.core.logcat.LogcatProxy;
import com.amazon.mls.config.internal.core.metrics.InternalMetrics;
import com.amazon.mls.config.internal.sushi.SushiFile;
import com.amazon.mls.config.internal.sushi.util.FileUtils;
import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: classes3.dex */
class FileRingImpl implements FileRing {
    private final FileRingNameFormatter fileNameFormatter = new FileRingNameFormatter();
    private final File fileRingDir;
    private final LinkedList<SushiFile> files;
    private long greatestFileId;
    private final long maxRingSizeBytes;
    private long ringSizeBytes;

    FileRingImpl(File file, LinkedList<SushiFile> linkedList, long j, long j2, long j3) {
        this.files = linkedList;
        this.ringSizeBytes = j2;
        this.fileRingDir = file;
        this.greatestFileId = j3;
        this.maxRingSizeBytes = j;
    }

    @Override // com.amazon.mls.config.internal.sushi.storage.FileRing
    public void addFile(SushiFile sushiFile) {
        try {
            validateFile(sushiFile);
            addFileToRing(sushiFile);
            enforceRingRestrictions();
        } catch (ClientSideException | IllegalArgumentException e) {
            LogcatProxy.log(LogLevel.ERROR, "Exception encountered while adding file to ring.", e);
            InternalMetrics.logCounter("sushi_ring_add_file_failed_w", 1L);
        }
    }

    @Override // com.amazon.mls.config.internal.sushi.storage.FileRing
    public List<SushiFile> getFiles() {
        return new LinkedList(this.files);
    }

    @Override // com.amazon.mls.config.internal.sushi.storage.FileRing
    public void delete(SushiFile sushiFile) {
        try {
            this.ringSizeBytes -= sushiFile.getFileLength();
            this.files.remove(sushiFile);
            sushiFile.delete();
        } catch (Exception e) {
            LogcatProxy.log(LogLevel.ERROR, "Exception while trying to delete file from the ring!", e);
        }
    }

    private void validateFile(SushiFile sushiFile) throws IllegalArgumentException {
        File supportFile = sushiFile.getSupportFile();
        long length = supportFile.length();
        if ((sushiFile.isOpen() && supportFile.isDirectory()) || length == 0) {
            throw new IllegalArgumentException(String.format(Locale.US, "Can't use a directory, empty or missing file! Path = %s", supportFile.getAbsolutePath()));
        }
    }

    private void addFileToRing(SushiFile sushiFile) throws Throwable {
        this.greatestFileId++;
        SushiFile sushiFileArchiveFile = archiveFile(sushiFile);
        this.ringSizeBytes += sushiFileArchiveFile.getFileLength();
        this.files.addLast(sushiFileArchiveFile);
    }

    private SushiFile archiveFile(SushiFile sushiFile) throws Throwable {
        File childPath = FileUtils.getChildPath(this.fileRingDir, this.fileNameFormatter.getFileName(this.greatestFileId, sushiFile.getRegion(), sushiFile.getEventCount().intValue()));
        FileArchiver.archiveFile(sushiFile.getSupportFile(), childPath);
        return new SushiFile.Builder().withSupportFile(childPath).withFileId(this.greatestFileId).withFileLength(childPath.length()).withEventCount(sushiFile.getEventCount().intValue()).withIsOpen(false).withDestinationRegion(sushiFile.getRegion()).build();
    }

    private void enforceRingRestrictions() {
        deleteEmptyFiles();
        enforceSizeRestriction();
    }

    private void deleteEmptyFiles() {
        for (SushiFile sushiFile : this.files) {
            if (sushiFile.getSupportFile().length() == 0) {
                delete(sushiFile);
            }
        }
    }

    private void enforceSizeRestriction() {
        Iterator<SushiFile> it = this.files.iterator();
        while (this.ringSizeBytes > this.maxRingSizeBytes && it.hasNext()) {
            delete(it.next());
        }
    }
}
