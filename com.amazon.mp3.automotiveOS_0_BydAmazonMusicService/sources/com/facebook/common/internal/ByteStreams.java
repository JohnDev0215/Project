package com.facebook.common.internal;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes5.dex */
public final class ByteStreams {
    private ByteStreams() {
    }

    public static long copy(InputStream from, OutputStream to) throws IOException {
        Preconditions.checkNotNull(from);
        Preconditions.checkNotNull(to);
        byte[] bArr = new byte[4096];
        long j = 0;
        while (true) {
            int i = from.read(bArr);
            if (i == -1) {
                return j;
            }
            to.write(bArr, 0, i);
            j += (long) i;
        }
    }

    public static int read(InputStream in, byte[] b, int off, int len) throws IOException {
        Preconditions.checkNotNull(in);
        Preconditions.checkNotNull(b);
        if (len < 0) {
            throw new IndexOutOfBoundsException("len is negative");
        }
        int i = 0;
        while (i < len) {
            int i2 = in.read(b, off + i, len - i);
            if (i2 == -1) {
                break;
            }
            i += i2;
        }
        return i;
    }
}
