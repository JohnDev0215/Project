package com.facebook.common.util;

import com.facebook.common.internal.Preconditions;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes5.dex */
public class StreamUtil {
    public static long skip(final InputStream inputStream, final long bytesCount) throws IOException {
        Preconditions.checkNotNull(inputStream);
        Preconditions.checkArgument(Boolean.valueOf(bytesCount >= 0));
        long j = bytesCount;
        while (j > 0) {
            long jSkip = inputStream.skip(j);
            if (jSkip <= 0) {
                if (inputStream.read() == -1) {
                    return bytesCount - j;
                }
                jSkip = 1;
            }
            j -= jSkip;
        }
        return bytesCount;
    }
}
