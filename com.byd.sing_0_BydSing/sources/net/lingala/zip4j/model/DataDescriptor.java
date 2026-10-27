package net.lingala.zip4j.model;

/* JADX INFO: loaded from: classes.dex */
public class DataDescriptor extends ZipHeader {
    private long compressedSize;
    private long crc;
    private long uncompressedSize;

    public long getCompressedSize() {
        return this.compressedSize;
    }

    public long getCrc() {
        return this.crc;
    }

    public long getUncompressedSize() {
        return this.uncompressedSize;
    }

    public void setCompressedSize(long j) {
        this.compressedSize = j;
    }

    public void setCrc(long j) {
        this.crc = j;
    }

    public void setUncompressedSize(long j) {
        this.uncompressedSize = j;
    }
}
