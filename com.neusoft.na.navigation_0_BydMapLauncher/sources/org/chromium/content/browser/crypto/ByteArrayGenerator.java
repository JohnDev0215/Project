package org.chromium.content.browser.crypto;

import java.io.FileInputStream;
import java.security.GeneralSecurityException;

/* JADX INFO: loaded from: classes3.dex */
public class ByteArrayGenerator {
    public byte[] getBytes(int i) throws Throwable {
        FileInputStream fileInputStream;
        FileInputStream fileInputStream2 = null;
        try {
            fileInputStream = new FileInputStream("/dev/urandom");
        } catch (Throwable th) {
            th = th;
        }
        try {
            byte[] bArr = new byte[i];
            if (i != fileInputStream.read(bArr)) {
                throw new GeneralSecurityException("Not enough random data available");
            }
            fileInputStream.close();
            return bArr;
        } catch (Throwable th2) {
            th = th2;
            fileInputStream2 = fileInputStream;
            if (fileInputStream2 != null) {
                fileInputStream2.close();
            }
            throw th;
        }
    }
}
