package com.android.internal.os;

import android.os.StrictMode;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

/* JADX INFO: loaded from: classes4.dex */
public final class ProcStatsUtil {
    private static final boolean DEBUG = false;
    private static final int READ_SIZE = 1024;
    private static final String TAG = "ProcStatsUtil";

    private ProcStatsUtil() {
    }

    public static String readNullSeparatedFile(String str) {
        String singleLineProcFile = readSingleLineProcFile(str);
        if (singleLineProcFile == null) {
            return null;
        }
        int iIndexOf = singleLineProcFile.indexOf("\u0000\u0000");
        if (iIndexOf != -1) {
            singleLineProcFile = singleLineProcFile.substring(0, iIndexOf);
        }
        return singleLineProcFile.replace("\u0000", " ");
    }

    public static String readSingleLineProcFile(String str) {
        return readTerminatedProcFile(str, (byte) 10);
    }

    public static String readTerminatedProcFile(String str, byte b) {
        boolean z;
        StrictMode.ThreadPolicy threadPolicyAllowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            FileInputStream fileInputStream = new FileInputStream(str);
            try {
                byte[] bArr = new byte[1024];
                ByteArrayOutputStream byteArrayOutputStream = null;
                do {
                    int i = fileInputStream.read(bArr);
                    if (i <= 0) {
                        break;
                    }
                    int i2 = 0;
                    while (true) {
                        if (i2 >= i) {
                            i2 = -1;
                            break;
                        }
                        if (bArr[i2] == b) {
                            break;
                        }
                        i2++;
                    }
                    z = i2 != -1;
                    if (z && byteArrayOutputStream == null) {
                        String str2 = new String(bArr, 0, i2);
                        fileInputStream.close();
                        return str2;
                    }
                    if (byteArrayOutputStream == null) {
                        byteArrayOutputStream = new ByteArrayOutputStream(1024);
                    }
                    if (z) {
                        i = i2;
                    }
                    byteArrayOutputStream.write(bArr, 0, i);
                } while (!z);
                if (byteArrayOutputStream == null) {
                    fileInputStream.close();
                    return "";
                }
                String string = byteArrayOutputStream.toString();
                fileInputStream.close();
                return string;
            } finally {
            }
        } catch (IOException unused) {
            return null;
        } finally {
            StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskReads);
        }
    }
}
