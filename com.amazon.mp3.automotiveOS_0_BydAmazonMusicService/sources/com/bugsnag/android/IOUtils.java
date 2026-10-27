package com.bugsnag.android;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

/* JADX INFO: loaded from: classes5.dex */
class IOUtils {
    IOUtils() {
    }

    static void closeQuietly(@Nullable Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception unused) {
            }
        }
    }

    static int copy(@NonNull Reader reader, @NonNull Writer writer) throws IOException {
        char[] cArr = new char[4096];
        long j = 0;
        while (true) {
            int i = reader.read(cArr);
            if (-1 == i) {
                break;
            }
            writer.write(cArr, 0, i);
            j += (long) i;
        }
        if (j > 2147483647L) {
            return -1;
        }
        return (int) j;
    }

    static void deleteFile(File file, Logger logger) {
        try {
            if (file.delete()) {
                return;
            }
            file.deleteOnExit();
        } catch (Exception e) {
            logger.w("Failed to delete file", e);
        }
    }
}
