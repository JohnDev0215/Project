package android.util.apk;

import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import java.io.FileDescriptor;
import java.io.IOException;
import java.nio.DirectByteBuffer;

/* JADX INFO: loaded from: classes3.dex */
class MemoryMappedFileDataSource implements DataSource {
    private static final long MEMORY_PAGE_SIZE_BYTES = Os.sysconf(OsConstants._SC_PAGESIZE);
    private final FileDescriptor mFd;
    private final long mFilePosition;
    private final long mSize;

    MemoryMappedFileDataSource(FileDescriptor fileDescriptor, long j, long j2) {
        this.mFd = fileDescriptor;
        this.mFilePosition = j;
        this.mSize = j2;
    }

    @Override // android.util.apk.DataSource
    public long size() {
        return this.mSize;
    }

    @Override // android.util.apk.DataSource
    public void feedIntoDataDigester(DataDigester dataDigester, long j, int i) throws Throwable {
        long j2;
        long jMmap;
        long j3;
        long j4 = this.mFilePosition + j;
        long j5 = MEMORY_PAGE_SIZE_BYTES;
        long j6 = (j4 / j5) * j5;
        int i2 = (int) (j4 - j6);
        long j7 = i + i2;
        try {
            try {
                jMmap = Os.mmap(0L, j7, OsConstants.PROT_READ, OsConstants.MAP_SHARED | OsConstants.MAP_POPULATE, this.mFd, j6);
                try {
                    j3 = j7;
                } catch (ErrnoException e) {
                    e = e;
                    j3 = j7;
                } catch (Throwable th) {
                    th = th;
                    j2 = j7;
                    if (jMmap != 0) {
                        try {
                            Os.munmap(jMmap, j2);
                        } catch (ErrnoException unused) {
                        }
                    }
                    throw th;
                }
            } catch (ErrnoException e2) {
                e = e2;
                j3 = j7;
            } catch (Throwable th2) {
                th = th2;
                j2 = j7;
                jMmap = 0;
            }
            try {
                dataDigester.consume(new DirectByteBuffer(i, jMmap + ((long) i2), this.mFd, (Runnable) null, true));
                if (jMmap != 0) {
                    try {
                        Os.munmap(jMmap, j3);
                    } catch (ErrnoException unused2) {
                    }
                }
            } catch (ErrnoException e3) {
                e = e3;
                throw new IOException("Failed to mmap " + j3 + " bytes", e);
            }
        } catch (Throwable th3) {
            th = th3;
        }
    }
}
