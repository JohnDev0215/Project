package com.amazon.avod.media.framework.storage;

import com.amazon.avod.acos.StorageHelper;
import com.amazon.avod.fileio.DiskFullIOException;
import com.amazon.avod.fileio.DiskIOException;
import com.amazon.avod.fileio.DiskSpaceAwareFileOutputStream;
import com.amazon.avod.fileio.DiskUtils;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.framework.config.DiskStorageConfig;
import com.amazon.avod.media.framework.memory.ByteBufferOutputStream;
import com.amazon.avod.perf.Profiler;
import com.amazon.avod.perf.SimpleCounterMetric;
import com.amazon.avod.util.DLog;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.io.ByteStreams;
import com.google.common.io.Closeables;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.zip.GZIPInputStream;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class DiskStorage {
    private final int mGzipStreamBufferSizeBytes;
    private final StorageHelper mStorageHelper;

    public DiskStorage() {
        this(StorageHelper.getInstance(), DiskStorageConfig.INSTANCE.getGzipBufferSizeBytes());
    }

    DiskStorage(@Nonnull StorageHelper storageHelper, int i) {
        this.mStorageHelper = storageHelper;
        this.mGzipStreamBufferSizeBytes = i;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v3, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r3v7 */
    /* JADX WARN: Type inference failed for: r3v8 */
    public void putInputStream(String str, InputStream inputStream, int i) throws Throwable {
        ?? r3;
        Throwable th;
        IOException e;
        DiskSpaceAwareFileOutputStream diskSpaceAwareFileOutputStream;
        File file = new File(str);
        File parentFile = file.getParentFile();
        if (parentFile != null) {
            boolean zExists = parentFile.exists();
            r3 = zExists;
            if (!zExists) {
                boolean zMkdirs = parentFile.mkdirs();
                r3 = zMkdirs;
                if (!zMkdirs) {
                    if (!freeSpaceExists(parentFile)) {
                        throw new DiskFullIOException(String.format(Locale.US, "Can not create path to file because Disk is Full: %s", parentFile.getAbsolutePath()));
                    }
                    throw new DiskIOException(String.format(Locale.US, "Can not create path to file: %s", parentFile.getAbsolutePath()));
                }
            }
        }
        if (file.exists()) {
            DiskUtils.deleteFile(file);
        }
        try {
            try {
                diskSpaceAwareFileOutputStream = new DiskSpaceAwareFileOutputStream(new OptimizedFileOutputStream(file), file.getAbsolutePath());
            } catch (IOException e2) {
                e = e2;
            } catch (Throwable th2) {
                r3 = 0;
                th = th2;
                Closeables.close(r3, true);
                throw th;
            }
            try {
                int iCopy = (int) ByteStreams.copy(inputStream, diskSpaceAwareFileOutputStream);
                if (iCopy != i) {
                    throw new IOException(String.format(Locale.US, "Could not copy entire stream to disk, input length: %d bytes copied: %d", Integer.valueOf(i), Integer.valueOf(iCopy)));
                }
                Closeables.close(diskSpaceAwareFileOutputStream, true);
            } catch (IOException e3) {
                e = e3;
                DiskUtils.deleteFile(file);
                throw e;
            }
        } catch (Throwable th3) {
            th = th3;
            Closeables.close(r3, true);
            throw th;
        }
    }

    private boolean freeSpaceExists(File file) {
        for (File parentFile = file; parentFile != null; parentFile = parentFile.getParentFile()) {
            if (parentFile.exists()) {
                return parentFile.getFreeSpace() != 0;
            }
        }
        String str = String.format(Locale.US, "No existing ancestor directories found: %s", file.getAbsolutePath());
        Profiler.reportCounterMetric(new SimpleCounterMetric("DiskFullCheck-NoExistingAncestorDirectory", (ImmutableList<String>) ImmutableList.of("Counter", str)));
        DLog.errorf(str);
        return false;
    }

    public void delete(@Nonnull String str, @Nonnull Optional<DiskUtils.DeletionProgressListener> optional) throws DiskIOException {
        File file = new File(str);
        if (!DiskUtils.deleteFile(file, optional)) {
            throw mapToDiskIOException(String.format(Locale.US, "Delete failed for %s", file.getAbsolutePath()), null);
        }
    }

    public void delete(@Nonnull String str) throws DiskIOException {
        delete(str, Optional.absent());
    }

    public byte[] getByteArray(@Nonnull String str, boolean z) throws DiskIOException {
        InputStream inputStream = getInputStream(str, z);
        try {
            try {
                return ByteStreams.toByteArray(inputStream);
            } catch (IOException e) {
                throw mapToDiskIOException(String.format(Locale.US, "Could not copy to byte array for file %s", str), e);
            }
        } finally {
            Closeables.closeQuietly(inputStream);
        }
    }

    public int loadToBuffer(String str, ByteBuffer byteBuffer) throws DiskIOException {
        InputStream inputStream = getInputStream(str, false);
        try {
            try {
                return (int) ByteStreams.copy(inputStream, new ByteBufferOutputStream(byteBuffer));
            } catch (IOException e) {
                throw mapToDiskIOException(String.format(Locale.US, "Could not copy to byte array for file %s", str), e);
            }
        } finally {
            Closeables.closeQuietly(inputStream);
        }
    }

    public int getSizeOf(String str) throws DiskIOException {
        File file = new File(str);
        if (!file.exists()) {
            throw mapToDiskIOException(String.format(Locale.US, "Requested size for non-existent file %s", str), null);
        }
        long length = file.length();
        if (length <= 2147483647L) {
            return (int) length;
        }
        throw mapToDiskIOException(String.format(Locale.US, "File length larger than Integer.MAX_VALUE %s (size: %d)", str, Long.valueOf(length)), null);
    }

    public boolean contains(String str) {
        return new File(str).exists();
    }

    public List<File> getChildrenOf(String str) {
        File[] fileArrListFiles = new File(str).listFiles();
        if (fileArrListFiles != null) {
            return Lists.newArrayList(fileArrListFiles);
        }
        return Collections.emptyList();
    }

    public void touch(@Nonnull String str) {
        Preconditions.checkNotNull(str);
        File file = new File(str);
        if (!file.exists() || file.setLastModified(TimeSpan.now().getTotalMilliseconds())) {
            return;
        }
        DLog.errorf("Could not set last modification time to file %s", file);
    }

    private InputStream getInputStream(@Nonnull String str, boolean z) throws DiskIOException {
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(str));
            return z ? new GZIPInputStream(fileInputStream, this.mGzipStreamBufferSizeBytes) : new BufferedInputStream(fileInputStream, 131072);
        } catch (IOException e) {
            throw mapToDiskIOException(String.format(Locale.US, "Could not open stream for file %s", str), e);
        }
    }

    private DiskIOException mapToDiskIOException(@Nonnull String str, @Nullable IOException iOException) {
        if (iOException != null) {
            String message = iOException.getMessage();
            if (message == null) {
                message = iOException.getClass().getSimpleName();
            }
            return new DiskIOException(String.format(Locale.US, "%s (%s)", str, message), iOException);
        }
        return new DiskIOException(str, null);
    }
}
