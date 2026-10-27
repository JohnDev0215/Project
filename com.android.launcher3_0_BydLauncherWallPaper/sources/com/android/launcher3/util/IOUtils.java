package com.android.launcher3.util;

import android.content.Context;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes4.dex */
public class IOUtils {
    private static final int BUF_SIZE = 4096;

    public static byte[] toByteArray(File file) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            byte[] byteArray = toByteArray(fileInputStream);
            fileInputStream.close();
            return byteArray;
        } catch (Throwable th) {
            try {
                fileInputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public static byte[] toByteArray(InputStream in) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        copy(in, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public static long copy(InputStream from, OutputStream to) throws IOException {
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

    public static String createTempFile(Context context, byte[] data) {
        throw new IllegalStateException("Method only allowed in development mode");
    }
}
