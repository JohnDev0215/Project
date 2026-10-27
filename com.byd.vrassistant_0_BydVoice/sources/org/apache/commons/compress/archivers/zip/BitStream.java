package org.apache.commons.compress.archivers.zip;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteOrder;
import org.apache.commons.compress.utils.BitInputStream;

/* JADX INFO: loaded from: classes4.dex */
class BitStream extends BitInputStream {
    BitStream(InputStream inputStream) {
        super(inputStream, ByteOrder.LITTLE_ENDIAN);
    }

    int nextBit() throws IOException {
        return (int) readBits(1);
    }

    long nextBits(int i) throws IOException {
        if (i < 0 || i > 8) {
            throw new IOException("Trying to read " + i + " bits, at most 8 are allowed");
        }
        return readBits(i);
    }

    int nextByte() throws IOException {
        return (int) readBits(8);
    }
}
