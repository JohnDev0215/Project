package org.apache.commons.compress.archivers.sevenz;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;

/* JADX INFO: loaded from: classes4.dex */
class Folder {
    static final Folder[] EMPTY_FOLDER_ARRAY = new Folder[0];
    BindPair[] bindPairs;
    Coder[] coders;
    long crc;
    boolean hasCrc;
    int numUnpackSubStreams;
    long[] packedStreams;
    long totalInputStreams;
    long totalOutputStreams;
    long[] unpackSizes;

    Folder() {
    }

    Iterable<Coder> getOrderedCoders() throws IOException {
        Coder[] coderArr;
        int iFindBindPairForOutStream;
        long[] jArr = this.packedStreams;
        if (jArr == null || (coderArr = this.coders) == null || jArr.length == 0 || coderArr.length == 0) {
            return Collections.emptyList();
        }
        LinkedList linkedList = new LinkedList();
        long j = this.packedStreams[0];
        loop0: while (true) {
            for (int i = (int) j; i >= 0; i = -1) {
                Coder[] coderArr2 = this.coders;
                if (i >= coderArr2.length) {
                    break loop0;
                }
                if (linkedList.contains(coderArr2[i])) {
                    throw new IOException("folder uses the same coder more than once in coder chain");
                }
                linkedList.addLast(this.coders[i]);
                iFindBindPairForOutStream = findBindPairForOutStream(i);
                if (iFindBindPairForOutStream != -1) {
                    break;
                }
            }
            j = this.bindPairs[iFindBindPairForOutStream].inIndex;
        }
        return linkedList;
    }

    int findBindPairForInStream(int i) {
        if (this.bindPairs == null) {
            return -1;
        }
        int i2 = 0;
        while (true) {
            BindPair[] bindPairArr = this.bindPairs;
            if (i2 >= bindPairArr.length) {
                return -1;
            }
            if (bindPairArr[i2].inIndex == i) {
                return i2;
            }
            i2++;
        }
    }

    int findBindPairForOutStream(int i) {
        if (this.bindPairs == null) {
            return -1;
        }
        int i2 = 0;
        while (true) {
            BindPair[] bindPairArr = this.bindPairs;
            if (i2 >= bindPairArr.length) {
                return -1;
            }
            if (bindPairArr[i2].outIndex == i) {
                return i2;
            }
            i2++;
        }
    }

    long getUnpackSize() {
        long j = this.totalOutputStreams;
        if (j == 0) {
            return 0L;
        }
        for (int i = ((int) j) - 1; i >= 0; i--) {
            if (findBindPairForOutStream(i) < 0) {
                return this.unpackSizes[i];
            }
        }
        return 0L;
    }

    long getUnpackSizeForCoder(Coder coder) {
        if (this.coders == null) {
            return 0L;
        }
        int i = 0;
        while (true) {
            Coder[] coderArr = this.coders;
            if (i >= coderArr.length) {
                return 0L;
            }
            if (coderArr[i] == coder) {
                return this.unpackSizes[i];
            }
            i++;
        }
    }

    public String toString() {
        return "Folder with " + this.coders.length + " coders, " + this.totalInputStreams + " input streams, " + this.totalOutputStreams + " output streams, " + this.bindPairs.length + " bind pairs, " + this.packedStreams.length + " packed streams, " + this.unpackSizes.length + " unpack sizes, " + (this.hasCrc ? "with CRC " + this.crc : "without CRC") + " and " + this.numUnpackSubStreams + " unpack streams";
    }
}
