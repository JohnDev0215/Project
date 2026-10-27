package com.amazon.mls.config.internal.sushi;

import com.amazon.mls.config.internal.core.configuration.Region;
import com.amazon.mls.config.internal.core.logcat.LogLevel;
import com.amazon.mls.config.internal.core.logcat.LogcatProxy;
import com.amazon.mls.config.internal.core.network.SushiEndpoint;
import java.io.File;
import java.util.Locale;

/* JADX INFO: loaded from: classes3.dex */
public class SushiFile {
    private Region destinationRegion;
    private Integer eventCount;
    private long fileId;
    private long fileLength;
    private boolean isOpen;
    private File supportFile;

    private SushiFile() {
        this.isOpen = true;
    }

    private SushiFile(SushiFile sushiFile) {
        this.isOpen = true;
        this.supportFile = sushiFile.supportFile;
        this.eventCount = sushiFile.eventCount;
        this.destinationRegion = sushiFile.destinationRegion;
        this.fileId = sushiFile.fileId;
        this.fileLength = sushiFile.fileLength;
        this.isOpen = sushiFile.isOpen;
    }

    public Integer getEventCount() {
        return this.eventCount;
    }

    public Region getRegion() {
        return this.destinationRegion;
    }

    public SushiEndpoint getDestinationEndpoint() {
        return SushiEndpoint.getEndpointFromRegion(this.destinationRegion);
    }

    public long getFileId() {
        return this.fileId;
    }

    public long getFileLength() {
        return this.fileLength;
    }

    public File getSupportFile() {
        return this.supportFile;
    }

    public boolean isOpen() {
        return this.isOpen;
    }

    public void close() {
        this.isOpen = false;
    }

    public String toString() {
        return String.format(Locale.US, "SupportFile: %s; eventCount: %d; region: %s; fileId: %d; fileLength: %d; isOpen: %b", this.supportFile, this.eventCount, this.destinationRegion, Long.valueOf(this.fileId), Long.valueOf(this.fileLength), Boolean.valueOf(this.isOpen));
    }

    public void addToFileSize(long j) {
        this.fileLength += j;
    }

    public void delete() {
        if (!this.supportFile.delete()) {
            LogcatProxy.log(LogLevel.ERROR, String.format(Locale.US, "Attempted to delete fle but failed. Path %s", this.supportFile.getAbsolutePath()));
        }
    }

    public static class Builder {
        private final SushiFile sushiFile = new SushiFile();

        public SushiFile build() {
            if (this.sushiFile.eventCount.intValue() >= 0) {
                if (this.sushiFile.supportFile != null) {
                    if (this.sushiFile.destinationRegion == null) {
                        throw new IllegalArgumentException("Can't create a SushiFile with a null region");
                    }
                    if (this.sushiFile.fileId < 0) {
                        throw new IllegalArgumentException(String.format("Can't create a SushiFile with a negative file Id: %d", Long.valueOf(this.sushiFile.fileId)));
                    }
                    if (this.sushiFile.fileLength < 0) {
                        throw new IllegalArgumentException(String.format("Can't create a SushiFile with a negative file length: %d", Long.valueOf(this.sushiFile.fileLength)));
                    }
                    return new SushiFile();
                }
                throw new IllegalArgumentException("Can't create a SushiFile with a null supportFile");
            }
            throw new IllegalArgumentException(String.format(Locale.US, "Can't create a SushiFile with a negative event count: %d", this.sushiFile.eventCount));
        }

        public Builder withEventCount(int i) {
            this.sushiFile.eventCount = Integer.valueOf(i);
            return this;
        }

        public Builder withSupportFile(File file) {
            this.sushiFile.supportFile = file;
            return this;
        }

        public Builder withDestinationRegion(Region region) {
            this.sushiFile.destinationRegion = region;
            return this;
        }

        public Builder withFileId(long j) {
            this.sushiFile.fileId = j;
            return this;
        }

        public Builder withFileLength(long j) {
            this.sushiFile.fileLength = j;
            return this;
        }

        public Builder withIsOpen(boolean z) {
            this.sushiFile.isOpen = z;
            return this;
        }
    }
}
