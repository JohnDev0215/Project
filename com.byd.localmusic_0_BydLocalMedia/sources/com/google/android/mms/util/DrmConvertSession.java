package com.google.android.mms.util;

import android.drm.DrmConvertedStatus;
import android.drm.DrmManagerClient;
import android.media.MediaMetrics;
import android.util.Log;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/* JADX INFO: loaded from: classes5.dex */
public class DrmConvertSession {
    public static final int STATUS_FILE_ERROR = 492;
    public static final int STATUS_NOT_ACCEPTABLE = 406;
    public static final int STATUS_SUCCESS = 200;
    public static final int STATUS_UNKNOWN_ERROR = 491;
    private static final String TAG = "DrmConvertSession";
    private int mConvertSessionId;
    private DrmManagerClient mDrmClient;

    private DrmConvertSession(DrmManagerClient drmManagerClient, int i) {
        this.mDrmClient = drmManagerClient;
        this.mConvertSessionId = i;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:21:0x004e A[ADDED_TO_REGION] */
    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r3v1, types: [android.drm.DrmManagerClient] */
    /* JADX WARN: Type inference failed for: r3v10, types: [android.drm.DrmManagerClient] */
    /* JADX WARN: Type inference failed for: r3v3, types: [boolean] */
    /* JADX WARN: Type inference failed for: r3v4 */
    /* JADX WARN: Type inference failed for: r3v5 */
    /* JADX WARN: Type inference failed for: r3v6 */
    /* JADX WARN: Type inference failed for: r3v7 */
    /* JADX WARN: Type inference failed for: r3v8 */
    /* JADX WARN: Type inference failed for: r3v9 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.google.android.mms.util.DrmConvertSession open(android.content.Context r6, java.lang.String r7) {
        /*
            java.lang.String r0 = "DrmConvertSession"
            r1 = 0
            r2 = -1
            if (r6 == 0) goto L4b
            if (r7 == 0) goto L4b
            java.lang.String r3 = ""
            boolean r3 = r7.equals(r3)
            if (r3 != 0) goto L4b
            android.drm.DrmManagerClient r3 = new android.drm.DrmManagerClient     // Catch: java.lang.IllegalStateException -> L3d java.lang.IllegalArgumentException -> L44
            r3.<init>(r6)     // Catch: java.lang.IllegalStateException -> L3d java.lang.IllegalArgumentException -> L44
            int r6 = r3.openConvertSession(r7)     // Catch: java.lang.IllegalStateException -> L1b java.lang.IllegalArgumentException -> L22
            r2 = r6
            goto L4c
        L1b:
            r6 = move-exception
            java.lang.String r7 = "Could not access Open DrmFramework."
            android.util.Log.w(r0, r7, r6)     // Catch: java.lang.IllegalStateException -> L3e java.lang.IllegalArgumentException -> L45
            goto L4c
        L22:
            r6 = move-exception
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.IllegalStateException -> L3e java.lang.IllegalArgumentException -> L45
            r4.<init>()     // Catch: java.lang.IllegalStateException -> L3e java.lang.IllegalArgumentException -> L45
            java.lang.String r5 = "Conversion of Mimetype: "
            r4.append(r5)     // Catch: java.lang.IllegalStateException -> L3e java.lang.IllegalArgumentException -> L45
            r4.append(r7)     // Catch: java.lang.IllegalStateException -> L3e java.lang.IllegalArgumentException -> L45
            java.lang.String r7 = " is not supported."
            r4.append(r7)     // Catch: java.lang.IllegalStateException -> L3e java.lang.IllegalArgumentException -> L45
            java.lang.String r7 = r4.toString()     // Catch: java.lang.IllegalStateException -> L3e java.lang.IllegalArgumentException -> L45
            android.util.Log.w(r0, r7, r6)     // Catch: java.lang.IllegalStateException -> L3e java.lang.IllegalArgumentException -> L45
            goto L4c
        L3d:
            r3 = r1
        L3e:
            java.lang.String r6 = "DrmManagerClient didn't initialize properly."
            android.util.Log.w(r0, r6)
            goto L4c
        L44:
            r3 = r1
        L45:
            java.lang.String r6 = "DrmManagerClient instance could not be created, context is Illegal."
            android.util.Log.w(r0, r6)
            goto L4c
        L4b:
            r3 = r1
        L4c:
            if (r3 == 0) goto L57
            if (r2 >= 0) goto L51
            goto L57
        L51:
            com.google.android.mms.util.DrmConvertSession r6 = new com.google.android.mms.util.DrmConvertSession
            r6.<init>(r3, r2)
            return r6
        L57:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.mms.util.DrmConvertSession.open(android.content.Context, java.lang.String):com.google.android.mms.util.DrmConvertSession");
    }

    public byte[] convert(byte[] bArr, int i) {
        DrmConvertedStatus drmConvertedStatusConvertData;
        if (bArr != null) {
            try {
                if (i != bArr.length) {
                    byte[] bArr2 = new byte[i];
                    System.arraycopy(bArr, 0, bArr2, 0, i);
                    drmConvertedStatusConvertData = this.mDrmClient.convertData(this.mConvertSessionId, bArr2);
                } else {
                    drmConvertedStatusConvertData = this.mDrmClient.convertData(this.mConvertSessionId, bArr);
                }
                if (drmConvertedStatusConvertData == null || drmConvertedStatusConvertData.statusCode != 1 || drmConvertedStatusConvertData.convertedData == null) {
                    return null;
                }
                return drmConvertedStatusConvertData.convertedData;
            } catch (IllegalArgumentException e) {
                Log.w(TAG, "Buffer with data to convert is illegal. Convertsession: " + this.mConvertSessionId, e);
                return null;
            } catch (IllegalStateException e2) {
                Log.w(TAG, "Could not convert data. Convertsession: " + this.mConvertSessionId, e2);
                return null;
            }
        }
        throw new IllegalArgumentException("Parameter inBuffer is null");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v12 */
    /* JADX WARN: Type inference failed for: r5v13 */
    /* JADX WARN: Type inference failed for: r5v14 */
    /* JADX WARN: Type inference failed for: r5v15 */
    /* JADX WARN: Type inference failed for: r5v21 */
    /* JADX WARN: Type inference failed for: r5v26 */
    /* JADX WARN: Type inference failed for: r5v27 */
    /* JADX WARN: Type inference failed for: r5v28 */
    /* JADX WARN: Type inference failed for: r5v29 */
    /* JADX WARN: Type inference failed for: r5v3 */
    /* JADX WARN: Type inference failed for: r5v30 */
    /* JADX WARN: Type inference failed for: r5v31 */
    /* JADX WARN: Type inference failed for: r5v32 */
    /* JADX WARN: Type inference failed for: r5v33 */
    /* JADX WARN: Type inference failed for: r5v34 */
    /* JADX WARN: Type inference failed for: r5v35 */
    /* JADX WARN: Type inference failed for: r5v36 */
    /* JADX WARN: Type inference failed for: r5v37 */
    /* JADX WARN: Type inference failed for: r5v38 */
    /* JADX WARN: Type inference failed for: r5v39 */
    /* JADX WARN: Type inference failed for: r5v4 */
    /* JADX WARN: Type inference failed for: r5v40 */
    /* JADX WARN: Type inference failed for: r5v5, types: [java.io.RandomAccessFile] */
    /* JADX WARN: Type inference failed for: r5v7 */
    public int close(String str) throws Throwable {
        int i;
        ?? r5;
        RandomAccessFile randomAccessFile;
        int i2;
        DrmManagerClient drmManagerClient = this.mDrmClient;
        int i3 = 491;
        if (drmManagerClient == null || (i = this.mConvertSessionId) < 0) {
            return 491;
        }
        try {
            DrmConvertedStatus drmConvertedStatusCloseConvertSession = drmManagerClient.closeConvertSession(i);
            if (drmConvertedStatusCloseConvertSession == null || drmConvertedStatusCloseConvertSession.statusCode != 1 || drmConvertedStatusCloseConvertSession.convertedData == null) {
                return 406;
            }
            ?? r52 = 0;
            RandomAccessFile randomAccessFile2 = null;
            RandomAccessFile randomAccessFile3 = null;
            RandomAccessFile randomAccessFile4 = null;
            RandomAccessFile randomAccessFile5 = null;
            r52 = 0;
            try {
                try {
                    try {
                        try {
                            randomAccessFile = new RandomAccessFile(str, "rw");
                            try {
                                i2 = drmConvertedStatusCloseConvertSession.offset;
                                randomAccessFile.seek(i2);
                                randomAccessFile.write(drmConvertedStatusCloseConvertSession.convertedData);
                            } catch (FileNotFoundException e) {
                                e = e;
                                randomAccessFile2 = randomAccessFile;
                                Log.w(TAG, "File: " + str + " could not be found.", e);
                                r52 = randomAccessFile2;
                                if (randomAccessFile2 != null) {
                                    try {
                                        randomAccessFile2.close();
                                        r52 = randomAccessFile2;
                                    } catch (IOException e2) {
                                        e = e2;
                                        str = "Failed to close File:" + str + MediaMetrics.SEPARATOR;
                                        r5 = randomAccessFile2;
                                        Log.w(TAG, str, e);
                                        r52 = r5;
                                        return 492;
                                    }
                                }
                                return 492;
                            } catch (IOException e3) {
                                e = e3;
                                randomAccessFile3 = randomAccessFile;
                                Log.w(TAG, "Could not access File: " + str + " .", e);
                                r52 = randomAccessFile3;
                                if (randomAccessFile3 != null) {
                                    try {
                                        randomAccessFile3.close();
                                        r52 = randomAccessFile3;
                                    } catch (IOException e4) {
                                        e = e4;
                                        str = "Failed to close File:" + str + MediaMetrics.SEPARATOR;
                                        r5 = randomAccessFile3;
                                        Log.w(TAG, str, e);
                                        r52 = r5;
                                        return 492;
                                    }
                                }
                                return 492;
                            } catch (IllegalArgumentException e5) {
                                e = e5;
                                randomAccessFile4 = randomAccessFile;
                                Log.w(TAG, "Could not open file in mode: rw", e);
                                r52 = randomAccessFile4;
                                if (randomAccessFile4 != null) {
                                    try {
                                        randomAccessFile4.close();
                                        r52 = randomAccessFile4;
                                    } catch (IOException e6) {
                                        e = e6;
                                        str = "Failed to close File:" + str + MediaMetrics.SEPARATOR;
                                        r5 = randomAccessFile4;
                                        Log.w(TAG, str, e);
                                        r52 = r5;
                                        return 492;
                                    }
                                }
                                return 492;
                            } catch (SecurityException e7) {
                                e = e7;
                                randomAccessFile5 = randomAccessFile;
                                Log.w(TAG, "Access to File: " + str + " was denied denied by SecurityManager.", e);
                                r52 = randomAccessFile5;
                                if (randomAccessFile5 != null) {
                                    try {
                                        randomAccessFile5.close();
                                        r52 = randomAccessFile5;
                                    } catch (IOException e8) {
                                        e = e8;
                                        str = "Failed to close File:" + str + MediaMetrics.SEPARATOR;
                                        r5 = randomAccessFile5;
                                        Log.w(TAG, str, e);
                                        r52 = r5;
                                        return 492;
                                    }
                                }
                            } catch (Throwable th) {
                                th = th;
                                r52 = randomAccessFile;
                                if (r52 != 0) {
                                    try {
                                        r52.close();
                                    } catch (IOException e9) {
                                        Log.w(TAG, "Failed to close File:" + str + MediaMetrics.SEPARATOR, e9);
                                        throw th;
                                    }
                                }
                                throw th;
                            }
                        } catch (FileNotFoundException e10) {
                            e = e10;
                        } catch (IOException e11) {
                            e = e11;
                        } catch (IllegalArgumentException e12) {
                            e = e12;
                        } catch (SecurityException e13) {
                            e = e13;
                        }
                        try {
                            randomAccessFile.close();
                            i3 = 200;
                            r52 = i2;
                            return i3;
                        } catch (IOException e14) {
                            e = e14;
                            str = "Failed to close File:" + str + MediaMetrics.SEPARATOR;
                            r5 = i2;
                            Log.w(TAG, str, e);
                            r52 = r5;
                            return 492;
                        } catch (IllegalStateException e15) {
                            e = e15;
                            i3 = 200;
                            Log.w(TAG, "Could not close convertsession. Convertsession: " + this.mConvertSessionId, e);
                            return i3;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        i3 = 492;
                    }
                } catch (IllegalStateException e16) {
                    e = e16;
                    i3 = 492;
                    Log.w(TAG, "Could not close convertsession. Convertsession: " + this.mConvertSessionId, e);
                    return i3;
                }
            } catch (Throwable th3) {
                th = th3;
            }
        } catch (IllegalStateException e17) {
            e = e17;
        }
    }
}
