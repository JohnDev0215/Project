package com.amazon.identity.auth.device;

import android.util.Log;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public final class sa {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f545a = "com.amazon.identity.auth.device.sa";

    public static void a(Closeable closeable) {
        if (closeable == null) {
            return;
        }
        try {
            closeable.close();
        } catch (IOException unused) {
            Log.e(ga.a(f545a), "IOException thrown closing input stream");
        }
    }

    public static byte[] a(InputStream inputStream) throws IOException {
        if (inputStream == null) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        while (true) {
            int i = inputStream.read(bArr);
            if (i < 0) {
                return byteArrayOutputStream.toByteArray();
            }
            byteArrayOutputStream.write(bArr, 0, i);
        }
    }

    public static void a(OutputStream outputStream, byte[] bArr) throws IOException {
        try {
            outputStream.write(bArr);
        } finally {
            a(outputStream);
        }
    }
}
