package android.os;

import android.content.Context;
import android.os.storage.StorageManager;
import android.system.ErrnoException;
import android.system.Os;
import android.util.Slog;
import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InterruptedIOException;
import libcore.io.IoUtils;

/* JADX INFO: loaded from: classes2.dex */
public class RedactingFileDescriptor {
    private static final boolean DEBUG = true;
    private static final String TAG = "RedactingFileDescriptor";
    private final ProxyFileDescriptorCallback mCallback;
    private volatile long[] mFreeOffsets;
    private FileDescriptor mInner;
    private ParcelFileDescriptor mOuter;
    private volatile long[] mRedactRanges;

    private RedactingFileDescriptor(Context context, File file, int i, long[] jArr, long[] jArr2) throws IOException {
        this.mInner = null;
        this.mOuter = null;
        ProxyFileDescriptorCallback proxyFileDescriptorCallback = new ProxyFileDescriptorCallback() { // from class: android.os.RedactingFileDescriptor.1
            @Override // android.os.ProxyFileDescriptorCallback
            public long onGetSize() throws ErrnoException {
                return Os.fstat(RedactingFileDescriptor.this.mInner).st_size;
            }

            @Override // android.os.ProxyFileDescriptorCallback
            public int onRead(long j, int i2, byte[] bArr) throws ErrnoException {
                int iPread;
                AnonymousClass1 anonymousClass1 = this;
                long j2 = j;
                int i3 = i2;
                byte b = 0;
                int i4 = 0;
                while (i4 < i3) {
                    try {
                        iPread = Os.pread(RedactingFileDescriptor.this.mInner, bArr, i4, i3 - i4, j2 + ((long) i4));
                    } catch (InterruptedIOException e) {
                        iPread = e.bytesTransferred;
                    }
                    if (iPread == 0) {
                        break;
                    }
                    i4 += iPread;
                }
                long[] jArr3 = RedactingFileDescriptor.this.mRedactRanges;
                int i5 = 0;
                while (i5 < jArr3.length) {
                    long jMax = Math.max(j2, jArr3[i5]);
                    long jMin = Math.min(((long) i3) + j2, jArr3[i5 + 1]);
                    long j3 = jMax;
                    while (j3 < jMin) {
                        bArr[(int) (j3 - j2)] = b;
                        j3++;
                        i5 = i5;
                    }
                    int i6 = i5;
                    long[] jArr4 = RedactingFileDescriptor.this.mFreeOffsets;
                    int length = jArr4.length;
                    int i7 = b;
                    while (i7 < length) {
                        int i8 = i4;
                        long j4 = jArr4[i7];
                        long jMax2 = Math.max(j4, jMax);
                        long jMin2 = Math.min(j4 + 4, jMin);
                        while (jMax2 < jMin2) {
                            bArr[(int) (jMax2 - j2)] = (byte) "free".charAt((int) (jMax2 - j4));
                            jMax2++;
                            j2 = j;
                            jArr3 = jArr3;
                        }
                        i7++;
                        j2 = j;
                        i4 = i8;
                        jArr3 = jArr3;
                    }
                    i5 = i6 + 2;
                    anonymousClass1 = this;
                    j2 = j;
                    i3 = i2;
                    b = 0;
                }
                return i4;
            }

            @Override // android.os.ProxyFileDescriptorCallback
            public int onWrite(long j, int i2, byte[] bArr) throws ErrnoException {
                int iPwrite;
                int i3 = 0;
                while (i3 < i2) {
                    try {
                        iPwrite = Os.pwrite(RedactingFileDescriptor.this.mInner, bArr, i3, i2 - i3, j + ((long) i3));
                    } catch (InterruptedIOException e) {
                        iPwrite = e.bytesTransferred;
                    }
                    if (iPwrite == 0) {
                        break;
                    }
                    i3 += iPwrite;
                }
                RedactingFileDescriptor redactingFileDescriptor = RedactingFileDescriptor.this;
                redactingFileDescriptor.mRedactRanges = RedactingFileDescriptor.removeRange(redactingFileDescriptor.mRedactRanges, j, ((long) i3) + j);
                return i3;
            }

            @Override // android.os.ProxyFileDescriptorCallback
            public void onFsync() throws ErrnoException {
                Os.fsync(RedactingFileDescriptor.this.mInner);
            }

            @Override // android.os.ProxyFileDescriptorCallback
            public void onRelease() {
                Slog.v(RedactingFileDescriptor.TAG, "onRelease()");
                IoUtils.closeQuietly(RedactingFileDescriptor.this.mInner);
            }
        };
        this.mCallback = proxyFileDescriptorCallback;
        this.mRedactRanges = checkRangesArgument(jArr);
        this.mFreeOffsets = jArr2;
        try {
            try {
                this.mInner = Os.open(file.getAbsolutePath(), FileUtils.translateModePfdToPosix(i), 0);
                this.mOuter = ((StorageManager) context.getSystemService(StorageManager.class)).openProxyFileDescriptor(i, proxyFileDescriptorCallback);
            } catch (ErrnoException e) {
                throw e.rethrowAsIOException();
            }
        } catch (IOException e2) {
            IoUtils.closeQuietly(this.mInner);
            IoUtils.closeQuietly(this.mOuter);
            throw e2;
        }
    }

    private static long[] checkRangesArgument(long[] jArr) {
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
            long[] r0 = libcore.util.EmptyArray.LONG
            r1 = 0
        La:
            int r2 = r6.length
            if (r1 >= r2) goto L9a
            r2 = r6[r1]
            int r2 = (r7 > r2 ? 1 : (r7 == r2 ? 0 : -1))
            if (r2 > 0) goto L1d
            int r2 = r1 + 1
            r2 = r6[r2]
            int r2 = (r9 > r2 ? 1 : (r9 == r2 ? 0 : -1))
            if (r2 < 0) goto L1d
            goto L96
        L1d:
            r2 = r6[r1]
            int r2 = (r7 > r2 ? 1 : (r7 == r2 ? 0 : -1))
            if (r2 < 0) goto L4b
            int r2 = r1 + 1
            r3 = r6[r2]
            int r3 = (r9 > r3 ? 1 : (r9 == r3 ? 0 : -1))
            if (r3 > 0) goto L4b
            int r3 = r0.length
            int r3 = r3 + 4
            long[] r0 = java.util.Arrays.copyOf(r0, r3)
            int r3 = r0.length
            int r3 = r3 + (-4)
            r4 = r6[r1]
            r0[r3] = r4
            int r3 = r0.length
            int r3 = r3 + (-3)
            r0[r3] = r7
            int r3 = r0.length
            int r3 = r3 + (-2)
            r0[r3] = r9
            int r3 = r0.length
            int r3 = r3 + (-1)
            r4 = r6[r2]
            r0[r3] = r4
            goto L96
        L4b:
            int r2 = r0.length
            int r2 = r2 + 2
            long[] r0 = java.util.Arrays.copyOf(r0, r2)
            r2 = r6[r1]
            int r2 = (r9 > r2 ? 1 : (r9 == r2 ? 0 : -1))
            if (r2 < 0) goto L6c
            int r2 = r1 + 1
            r2 = r6[r2]
            int r2 = (r9 > r2 ? 1 : (r9 == r2 ? 0 : -1))
            if (r2 > 0) goto L6c
            int r2 = r0.length
            int r2 = r2 + (-2)
            r3 = r6[r1]
            long r3 = java.lang.Math.max(r3, r9)
            r0[r2] = r3
            goto L73
        L6c:
            int r2 = r0.length
            int r2 = r2 + (-2)
            r3 = r6[r1]
            r0[r2] = r3
        L73:
            r2 = r6[r1]
            int r2 = (r7 > r2 ? 1 : (r7 == r2 ? 0 : -1))
            if (r2 < 0) goto L8d
            int r2 = r1 + 1
            r3 = r6[r2]
            int r3 = (r7 > r3 ? 1 : (r7 == r3 ? 0 : -1))
            if (r3 > 0) goto L8d
            int r3 = r0.length
            int r3 = r3 + (-1)
            r4 = r6[r2]
            long r4 = java.lang.Math.min(r4, r7)
            r0[r3] = r4
            goto L96
        L8d:
            int r2 = r0.length
            int r2 = r2 + (-1)
            int r3 = r1 + 1
            r3 = r6[r3]
            r0[r2] = r3
        L96:
            int r1 = r1 + 2
            goto La
        L9a:
            return r0
        L9b:
            java.lang.IllegalArgumentException r6 = new java.lang.IllegalArgumentException
            r6.<init>()
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: android.os.RedactingFileDescriptor.removeRange(long[], long, long):long[]");
    }
}
