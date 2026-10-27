package org.tukaani.xz.index;

/* JADX INFO: loaded from: classes2.dex */
public class BlockInfo {
    IndexDecoder index;
    public int blockNumber = -1;
    public long compressedOffset = -1;
    public long uncompressedOffset = -1;
    public long unpaddedSize = -1;
    public long uncompressedSize = -1;

    public BlockInfo(IndexDecoder indexOfFirstStream) {
        this.index = indexOfFirstStream;
    }

    public int getCheckType() {
        return this.index.getStreamFlags().checkType;
    }

    public boolean hasNext() {
        return this.index.hasRecord(this.blockNumber + 1);
    }

    public void setNext() {
        this.index.setBlockInfo(this, this.blockNumber + 1);
    }
}
