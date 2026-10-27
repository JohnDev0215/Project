package org.tukaani.xz.index;

/* JADX INFO: loaded from: classes2.dex */
class IndexRecord {
    final long uncompressed;
    final long unpadded;

    IndexRecord(long unpadded, long uncompressed) {
        this.unpadded = unpadded;
        this.uncompressed = uncompressed;
    }
}
