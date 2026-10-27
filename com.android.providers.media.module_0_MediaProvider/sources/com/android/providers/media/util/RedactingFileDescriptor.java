package com.android.providers.media.util;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import android.os.ProxyFileDescriptorCallback;
import android.os.storage.StorageManager;
import android.system.ErrnoException;
import android.system.Os;
import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InterruptedIOException;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class RedactingFileDescriptor {
    private static final boolean DEBUG = true;
    private static final String TAG = "RedactingFileDescriptor";
    private final ProxyFileDescriptorCallback mCallback = new ProxyFileDescriptorCallback() { // from class: com.android.providers.media.util.RedactingFileDescriptor.1
        @Override // android.os.ProxyFileDescriptorCallback
        public long onGetSize() throws ErrnoException {
            return Os.fstat(RedactingFileDescriptor.this.mInnerFd).st_size;
        }

        @Override // android.os.ProxyFileDescriptorCallback
        public int onRead(long j, int i, byte[] bArr) throws ErrnoException {
            int iPread;
            AnonymousClass1 anonymousClass1 = this;
            long j2 = j;
            int i2 = i;
            byte b = 0;
            int i3 = 0;
            while (i3 < i2) {
                try {
                    iPread = Os.pread(RedactingFileDescriptor.this.mInnerFd, bArr, i3, i2 - i3, j2 + ((long) i3));
                } catch (InterruptedIOException e) {
                    iPread = e.bytesTransferred;
                }
                if (iPread == 0) {
                    break;
                }
                i3 += iPread;
            }
            long[] jArr = RedactingFileDescriptor.this.mRedactRanges;
            int i4 = 0;
            while (i4 < jArr.length) {
                long jMax = Math.max(j2, jArr[i4]);
                long jMin = Math.min(((long) i2) + j2, jArr[i4 + 1]);
                long j3 = jMax;
                while (j3 < jMin) {
                    bArr[(int) (j3 - j2)] = b;
                    j3++;
                    i4 = i4;
                }
                int i5 = i4;
                long[] jArr2 = RedactingFileDescriptor.this.mFreeOffsets;
                int length = jArr2.length;
                int i6 = b;
                while (i6 < length) {
                    int i7 = i3;
                    long j4 = jArr2[i6];
                    long jMax2 = Math.max(j4, jMax);
                    long jMin2 = Math.min(j4 + 4, jMin);
                    while (jMax2 < jMin2) {
                        bArr[(int) (jMax2 - j2)] = (byte) "free".charAt((int) (jMax2 - j4));
                        jMax2++;
                        j2 = j;
                        jArr = jArr;
                    }
                    i6++;
                    j2 = j;
                    i3 = i7;
                    jArr = jArr;
                }
                i4 = i5 + 2;
                anonymousClass1 = this;
                j2 = j;
                i2 = i;
                b = 0;
            }
            return i3;
        }

        @Override // android.os.ProxyFileDescriptorCallback
        public int onWrite(long j, int i, byte[] bArr) throws ErrnoException {
            int iPwrite;
            int i2 = 0;
            while (i2 < i) {
                try {
                    iPwrite = Os.pwrite(RedactingFileDescriptor.this.mInnerFd, bArr, i2, i - i2, j + ((long) i2));
                } catch (InterruptedIOException e) {
                    iPwrite = e.bytesTransferred;
                }
                if (iPwrite == 0) {
                    break;
                }
                i2 += iPwrite;
            }
            RedactingFileDescriptor redactingFileDescriptor = RedactingFileDescriptor.this;
            redactingFileDescriptor.mRedactRanges = RedactingFileDescriptor.removeRange(redactingFileDescriptor.mRedactRanges, j, ((long) i2) + j);
            return i2;
        }

        @Override // android.os.ProxyFileDescriptorCallback
        public void onFsync() throws ErrnoException {
            Os.fsync(RedactingFileDescriptor.this.mInnerFd);
        }

        @Override // android.os.ProxyFileDescriptorCallback
        public void onRelease() {
            FileUtils.closeQuietly(RedactingFileDescriptor.this.mInner);
        }
    };
    private volatile long[] mFreeOffsets;
    private ParcelFileDescriptor mInner;
    private FileDescriptor mInnerFd;
    private ParcelFileDescriptor mOuter;
    private volatile long[] mRedactRanges;

    private RedactingFileDescriptor(Context context, File file, int i, long[] jArr, long[] jArr2) throws IOException {
        this.mInner = null;
        this.mOuter = null;
        this.mInnerFd = null;
        this.mRedactRanges = checkRangesArgument(jArr);
        this.mFreeOffsets = jArr2;
        try {
            ParcelFileDescriptor parcelFileDescriptorOpenSafely = FileUtils.openSafely(file, i);
            this.mInner = parcelFileDescriptorOpenSafely;
            this.mInnerFd = parcelFileDescriptorOpenSafely.getFileDescriptor();
            this.mOuter = ((StorageManager) context.getSystemService(StorageManager.class)).openProxyFileDescriptor(i, this.mCallback, new Handler(Looper.getMainLooper()));
        } catch (IOException e) {
            FileUtils.closeQuietly(this.mInner);
            FileUtils.closeQuietly(this.mOuter);
            throw e;
        }
    }

    public static long[] checkRangesArgument(long[] jArr) {
        if (jArr.length % 2 != 0) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < jArr.length - 1; i += 2) {
            if (jArr[i] > jArr[i + 1]) {
                throw new IllegalArgumentException();
            }
        }
        return jArr;
    }

    public static ParcelFileDescriptor open(Context context, File file, int i, long[] jArr, long[] jArr2) throws IOException {
        return new RedactingFileDescriptor(context, file, i, jArr, jArr2).mOuter;
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x004b  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x008d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static long[] removeRange(long[] r6, long r7, long r9) {
        /*
            int r0 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r0 != 0) goto L5
            return r6
        L5:
            if (r0 > 0) goto L9b
            r0 = 0
            long[] r1 = new long[r0]
        La:
            int r2 = r6.length
            if (r0 >= r2) goto L9a
            r2 = r6[r0]
            int r2 = (r7 > r2 ? 1 : (r7 == r2 ? 0 : -1))
            if (r2 > 0) goto L1d
            int r2 = r0 + 1
            r2 = r6[r2]
            int r2 = (r9 > r2 ? 1 : (r9 == r2 ? 0 : -1))
            if (r2 < 0) goto L1d
            goto L96
        L1d:
            r2 = r6[r0]
            int r2 = (r7 > r2 ? 1 : (r7 == r2 ? 0 : -1))
            if (r2 < 0) goto L4b
            int r2 = r0 + 1
            r3 = r6[r2]
            int r3 = (r9 > r3 ? 1 : (r9 == r3 ? 0 : -1))
            if (r3 > 0) goto L4b
            int r3 = r1.length
            int r3 = r3 + 4
            long[] r1 = java.util.Arrays.copyOf(r1, r3)
            int r3 = r1.length
            int r3 = r3 + (-4)
            r4 = r6[r0]
            r1[r3] = r4
            int r3 = r1.length
            int r3 = r3 + (-3)
            r1[r3] = r7
            int r3 = r1.length
            int r3 = r3 + (-2)
            r1[r3] = r9
            int r3 = r1.length
            int r3 = r3 + (-1)
            r4 = r6[r2]
            r1[r3] = r4
            goto L96
        L4b:
            int r2 = r1.length
            int r2 = r2 + 2
            long[] r1 = java.util.Arrays.copyOf(r1, r2)
            r2 = r6[r0]
            int r2 = (r9 > r2 ? 1 : (r9 == r2 ? 0 : -1))
            if (r2 < 0) goto L6c
            int r2 = r0 + 1
            r2 = r6[r2]
            int r2 = (r9 > r2 ? 1 : (r9 == r2 ? 0 : -1))
            if (r2 > 0) goto L6c
            int r2 = r1.length
            int r2 = r2 + (-2)
            r3 = r6[r0]
            long r3 = java.lang.Math.max(r3, r9)
            r1[r2] = r3
            goto L73
        L6c:
            int r2 = r1.length
            int r2 = r2 + (-2)
            r3 = r6[r0]
            r1[r2] = r3
        L73:
            r2 = r6[r0]
            int r2 = (r7 > r2 ? 1 : (r7 == r2 ? 0 : -1))
            if (r2 < 0) goto L8d
            int r2 = r0 + 1
            r3 = r6[r2]
            int r3 = (r7 > r3 ? 1 : (r7 == r3 ? 0 : -1))
            if (r3 > 0) goto L8d
            int r3 = r1.length
            int r3 = r3 + (-1)
            r4 = r6[r2]
            long r4 = java.lang.Math.min(r4, r7)
            r1[r3] = r4
            goto L96
        L8d:
            int r2 = r1.length
            int r2 = r2 + (-1)
            int r3 = r0 + 1
            r3 = r6[r3]
            r1[r2] = r3
        L96:
            int r0 = r0 + 2
            goto La
        L9a:
            return r1
        L9b:
            java.lang.IllegalArgumentException r6 = new java.lang.IllegalArgumentException
            r6.<init>()
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.providers.media.util.RedactingFileDescriptor.removeRange(long[], long, long):long[]");
    }
}
