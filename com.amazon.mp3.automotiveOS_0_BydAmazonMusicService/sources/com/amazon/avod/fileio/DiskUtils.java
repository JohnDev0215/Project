package com.amazon.avod.fileio;

import android.os.StatFs;
import com.amazon.avod.util.DLog;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.io.Files;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Locale;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class DiskUtils {

    public interface DeletionProgressListener {
        void onProgress(@Nonnegative int i);
    }

    public static long getDiskSpace(String str) {
        return getDiskSpace(new StatFs(str));
    }

    public static long getDiskSpace(StatFs statFs) {
        return ((long) statFs.getBlockSize()) * ((long) statFs.getAvailableBlocks());
    }

    private static long getSafetyMarginBytes(StatFs statFs) {
        return ((long) statFs.getBlockSize()) * 2;
    }

    static void parseRethrowFileWriteIOException(IOException iOException, String str, long j) throws DiskIOException {
        try {
            StatFs statFs = new StatFs(str);
            long diskSpace = getDiskSpace(statFs);
            if (diskSpace < getSafetyMarginBytes(statFs) + j) {
                throw new DiskFullIOException(j, diskSpace, iOException.getMessage());
            }
            throw new DiskIOException(iOException.getMessage());
        } catch (Exception e) {
            DLog.exceptionf(e);
            throw new DiskIOException(iOException.getMessage());
        }
    }

    public static boolean deleteFile(@Nonnull File file, @Nonnull Optional<DeletionProgressListener> optional) {
        return delete(file, optional, true);
    }

    public static boolean deleteFile(File file) {
        return deleteFile(file, Optional.absent());
    }

    public static long getSizeRecursiveInBytes(@Nonnull File file) {
        Preconditions.checkNotNull(file, "directory");
        if (!file.exists()) {
            return 0L;
        }
        long blockSize = new StatFs(file.getAbsolutePath()).getBlockSize();
        if (blockSize < 0) {
            blockSize = 4096;
        }
        long j = 0;
        for (File file2 : Files.fileTraverser().breadthFirst(file)) {
            if (file2.isDirectory()) {
                j += blockSize;
            } else {
                long length = file2.length();
                long j2 = length % blockSize;
                if (j2 != 0) {
                    length += blockSize - j2;
                }
                j += length;
            }
        }
        return j;
    }

    private static boolean delete(@Nonnull File file, @Nonnull Optional<DeletionProgressListener> optional, boolean z) {
        boolean zInnerDelete;
        if (!file.exists() || (!z && !file.isDirectory())) {
            return true;
        }
        if (!z) {
            File[] fileArrListFiles = file.listFiles();
            zInnerDelete = fileArrListFiles != null ? innerDelete(Lists.newLinkedList(Arrays.asList(fileArrListFiles)), optional) : false;
        } else {
            LinkedList linkedListNewLinkedList = Lists.newLinkedList();
            linkedListNewLinkedList.add(file);
            zInnerDelete = innerDelete(linkedListNewLinkedList, optional);
        }
        if (!zInnerDelete) {
            DLog.warnf("Unable to delete: %s", DLog.maskString(file.getAbsolutePath()));
            Object[] objArr = new Object[2];
            objArr[0] = getPermissionsForFile(file);
            objArr[1] = file.getParentFile() != null ? getPermissionsForFile(file.getParentFile()) : null;
            DLog.warnf("Permissions: %s, Parent Permissions: %s", objArr);
            DLog.warnf("If permissions are good, there is likely an open stream to the file somewhere...");
        }
        return zInnerDelete;
    }

    private static boolean innerDelete(@Nonnull Deque<File> deque, @Nonnull Optional<DeletionProgressListener> optional) {
        DeletionProgressListener deletionProgressListenerOrNull = optional.orNull();
        int i = 0;
        while (!deque.isEmpty()) {
            File filePop = deque.pop();
            if (filePop.isDirectory()) {
                File[] fileArrListFiles = filePop.listFiles();
                if (fileArrListFiles == null) {
                    return false;
                }
                if (fileArrListFiles.length > 0) {
                    deque.push(filePop);
                    for (File file : fileArrListFiles) {
                        deque.push(file);
                    }
                }
            }
            if (!filePop.delete()) {
                return false;
            }
            i++;
            if (deletionProgressListenerOrNull != null) {
                deletionProgressListenerOrNull.onProgress(i);
            }
        }
        return true;
    }

    private static String getPermissionsForFile(File file) {
        Locale locale = Locale.US;
        Object[] objArr = new Object[3];
        objArr[0] = Character.valueOf(file.canRead() ? 'r' : '-');
        objArr[1] = Character.valueOf(file.canWrite() ? 'w' : '-');
        objArr[2] = Character.valueOf(file.canExecute() ? 'x' : '-');
        return String.format(locale, "%c%c%c", objArr);
    }
}
