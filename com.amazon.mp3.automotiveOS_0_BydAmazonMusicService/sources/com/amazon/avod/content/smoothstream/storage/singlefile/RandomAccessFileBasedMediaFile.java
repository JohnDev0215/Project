package com.amazon.avod.content.smoothstream.storage.singlefile;

import com.amazon.avod.acos.StorageHelper;
import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.backgrounddownload.SingleFileDownloadConfig;
import com.amazon.avod.media.framework.resources.SurgingResourcePool;
import com.amazon.avod.util.DataUnit;
import com.google.common.base.Preconditions;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.util.Locale;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.ThreadSafe;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes2.dex */
@ThreadSafe
public class RandomAccessFileBasedMediaFile implements MediaFile {
    private static final int BUFFER_SIZE_IN_BYTES = (int) DataUnit.KILOBYTES.toBytes(256.0f);
    private final SurgingResourcePool<byte[]> mBufferPool = new ReadBufferPool(2);
    private final RandomAccessFile mRandomAccessFile;

    public static RandomAccessFileBasedMediaFile newInstance(@Nonnull String str, @Nonnegative long j, StorageHelper storageHelper) throws IOException {
        Preconditions.checkNotNull(str, "filePath");
        Preconditions.checkArgument(j >= 0, "file size should not be negative");
        File file = new File(str);
        File parentFile = file.getParentFile();
        if (!parentFile.exists() && !parentFile.mkdirs()) {
            throw new IOException("Cannot create parent folder(s)");
        }
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
        boolean z = storageHelper.getExternalDownloadDir().isPresent() && new File(str).getCanonicalPath().startsWith(storageHelper.getExternalDownloadDir().get().getCanonicalPath());
        if (SingleFileDownloadConfig.getInstance().getFasterSdCardDownload() && !z && j != randomAccessFile.length()) {
            randomAccessFile.setLength(j);
        }
        return new RandomAccessFileBasedMediaFile(randomAccessFile);
    }

    RandomAccessFileBasedMediaFile(@Nonnull RandomAccessFile randomAccessFile) {
        Preconditions.checkNotNull(randomAccessFile, "randomAccessFile");
        this.mRandomAccessFile = randomAccessFile;
    }

    @Override // com.amazon.avod.content.smoothstream.storage.singlefile.MediaFile
    public void put(InputStream inputStream, ByteRange byteRange) throws IOException, ContentException {
        long start = byteRange.getStart();
        int sizeBytes = (int) byteRange.getSizeBytes();
        byte[] bArrRequestResource = this.mBufferPool.requestResource();
        while (true) {
            try {
                int i = inputStream.read(bArrRequestResource);
                if (i == -1) {
                    break;
                }
                synchronized (this.mRandomAccessFile) {
                    this.mRandomAccessFile.seek(start);
                    try {
                        this.mRandomAccessFile.write(bArrRequestResource, 0, i);
                    } catch (IOException e) {
                        throw new ContentException(ContentException.ContentError.DISK_FULL, e);
                    }
                }
                sizeBytes -= i;
                start += (long) i;
            } finally {
                this.mBufferPool.releaseResource(bArrRequestResource);
            }
        }
        if (sizeBytes != 0) {
            throw new IOException(String.format(Locale.US, "Could not write the whole fragment into file.", new Object[0]));
        }
    }

    @Override // com.amazon.avod.content.smoothstream.storage.singlefile.MediaFile
    public ByteBuffer get(ByteRange byteRange) throws IOException {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate((int) byteRange.getSizeBytes());
        byte[] bArrArray = byteBufferAllocate.array();
        synchronized (this.mRandomAccessFile) {
            this.mRandomAccessFile.seek(byteRange.getStart());
            this.mRandomAccessFile.readFully(bArrArray);
        }
        return byteBufferAllocate;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        synchronized (this.mRandomAccessFile) {
            this.mRandomAccessFile.close();
        }
    }

    private static class ReadBufferPool extends SurgingResourcePool<byte[]> {
        public ReadBufferPool(int i) {
            super(i, new Provider<byte[]>() { // from class: com.amazon.avod.content.smoothstream.storage.singlefile.RandomAccessFileBasedMediaFile.ReadBufferPool.1
                @Override // javax.inject.Provider
                public byte[] get() {
                    return new byte[RandomAccessFileBasedMediaFile.BUFFER_SIZE_IN_BYTES];
                }
            });
        }
    }
}
