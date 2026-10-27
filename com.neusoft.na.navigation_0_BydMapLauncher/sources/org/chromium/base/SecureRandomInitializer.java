package org.chromium.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.SecureRandom;

/* JADX INFO: loaded from: classes3.dex */
public class SecureRandomInitializer {
    private static final int NUM_RANDOM_BYTES = 16;
    private static byte[] sSeedBytes = new byte[16];

    public static void initialize(SecureRandom secureRandom) throws Throwable {
        FileInputStream fileInputStream;
        FileInputStream fileInputStream2 = null;
        try {
            fileInputStream = new FileInputStream("/dev/urandom");
        } catch (Throwable th) {
            th = th;
        }
        try {
            int i = fileInputStream.read(sSeedBytes);
            byte[] bArr = sSeedBytes;
            if (i != bArr.length) {
                throw new IOException("Failed to get enough random data.");
            }
            secureRandom.setSeed(bArr);
            try {
                fileInputStream.close();
            } catch (IOException unused) {
            }
        } catch (Throwable th2) {
            th = th2;
            fileInputStream2 = fileInputStream;
            if (fileInputStream2 != null) {
                try {
                    fileInputStream2.close();
                } catch (IOException unused2) {
                }
            }
            throw th;
        }
    }
}
