package io.sentry.android.core;

import android.content.Context;
import java.io.File;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
public abstract class K {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static String f16061a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Charset f16062b = Charset.forName("UTF-8");

    public static synchronized String a(Context context) {
        try {
            if (f16061a == null) {
                File file = new File(context.getFilesDir(), "INSTALLATION");
                try {
                    if (!file.exists()) {
                        FileOutputStream fileOutputStream = new FileOutputStream(file);
                        try {
                            String string = UUID.randomUUID().toString();
                            fileOutputStream.write(string.getBytes(f16062b));
                            fileOutputStream.flush();
                            fileOutputStream.close();
                            f16061a = string;
                            return string;
                        } finally {
                        }
                    }
                    RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
                    try {
                        byte[] bArr = new byte[(int) randomAccessFile.length()];
                        randomAccessFile.readFully(bArr);
                        String str = new String(bArr, f16062b);
                        randomAccessFile.close();
                        f16061a = str;
                    } finally {
                    }
                } catch (Throwable th) {
                    throw new RuntimeException(th);
                }
            }
            return f16061a;
        } catch (Throwable th2) {
            throw th2;
        }
    }
}
