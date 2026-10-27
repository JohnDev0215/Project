package com.amazon.mls.config.internal.sushi.storage;

import com.amazon.mls.config.internal.core.logcat.LogLevel;
import com.amazon.mls.config.internal.core.logcat.LogcatProxy;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

/* JADX INFO: loaded from: classes3.dex */
public final class FileArchiver {
    private FileArchiver() {
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x003a  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x005e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void archiveFile(java.io.File r8, java.io.File r9) throws java.lang.Throwable {
        /*
            r0 = 0
            r2 = 0
            long r3 = r8.length()     // Catch: java.lang.Throwable -> L27 java.lang.Exception -> L2a
            java.io.FileInputStream r5 = new java.io.FileInputStream     // Catch: java.lang.Exception -> L25 java.lang.Throwable -> L27
            r5.<init>(r8)     // Catch: java.lang.Exception -> L25 java.lang.Throwable -> L27
            java.util.zip.GZIPOutputStream r8 = new java.util.zip.GZIPOutputStream     // Catch: java.lang.Throwable -> L1d java.lang.Exception -> L20
            java.io.FileOutputStream r6 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L1d java.lang.Exception -> L20
            r6.<init>(r9)     // Catch: java.lang.Throwable -> L1d java.lang.Exception -> L20
            r8.<init>(r6)     // Catch: java.lang.Throwable -> L1d java.lang.Exception -> L20
            long r0 = writeToStream(r5, r8)     // Catch: java.lang.Exception -> L1b java.lang.Throwable -> L66
            goto L32
        L1b:
            r2 = move-exception
            goto L2f
        L1d:
            r9 = move-exception
            r8 = r2
            goto L67
        L20:
            r8 = move-exception
            r7 = r2
            r2 = r8
            r8 = r7
            goto L2f
        L25:
            r8 = move-exception
            goto L2c
        L27:
            r9 = move-exception
            r8 = r2
            goto L68
        L2a:
            r8 = move-exception
            r3 = r0
        L2c:
            r5 = r2
            r2 = r8
            r8 = r5
        L2f:
            safelyDeleteFile(r9)     // Catch: java.lang.Throwable -> L66
        L32:
            com.amazon.mls.config.internal.sushi.util.SafeStreamCloser.close(r5)
            com.amazon.mls.config.internal.sushi.util.SafeStreamCloser.close(r8)
            if (r2 != 0) goto L5e
            int r8 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r8 != 0) goto L3f
            return
        L3f:
            com.amazon.mls.config.internal.core.exceptions.ClientSideException r8 = new com.amazon.mls.config.internal.core.exceptions.ClientSideException
            java.util.Locale r9 = java.util.Locale.US
            r2 = 2
            java.lang.Object[] r2 = new java.lang.Object[r2]
            r5 = 0
            java.lang.Long r3 = java.lang.Long.valueOf(r3)
            r2[r5] = r3
            r3 = 1
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            r2[r3] = r0
            java.lang.String r0 = "Unable to write all the bytes when archiving! Expected %d but received %d"
            java.lang.String r9 = java.lang.String.format(r9, r0, r2)
            r8.<init>(r9)
            throw r8
        L5e:
            com.amazon.mls.config.internal.core.exceptions.ClientSideException r8 = new com.amazon.mls.config.internal.core.exceptions.ClientSideException
            java.lang.String r9 = "File archiving failed!"
            r8.<init>(r9, r2)
            throw r8
        L66:
            r9 = move-exception
        L67:
            r2 = r5
        L68:
            com.amazon.mls.config.internal.sushi.util.SafeStreamCloser.close(r2)
            com.amazon.mls.config.internal.sushi.util.SafeStreamCloser.close(r8)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mls.config.internal.sushi.storage.FileArchiver.archiveFile(java.io.File, java.io.File):void");
    }

    private static void safelyDeleteFile(File file) {
        try {
            boolean zDelete = file.delete();
            LogcatProxy.log(LogLevel.WARN, "Archiving failed and tried to delete destination file with the following result: " + zDelete);
        } catch (Exception e) {
            LogcatProxy.log(LogLevel.WARN, "Failed to delete corrupted archived file. ", e);
        }
    }

    private static long writeToStream(FileInputStream fileInputStream, GZIPOutputStream gZIPOutputStream) throws IOException {
        byte[] bArr = new byte[8192];
        long j = 0;
        while (true) {
            int i = fileInputStream.read(bArr);
            if (i <= 0) {
                return j;
            }
            gZIPOutputStream.write(bArr, 0, i);
            j += (long) i;
        }
    }
}
