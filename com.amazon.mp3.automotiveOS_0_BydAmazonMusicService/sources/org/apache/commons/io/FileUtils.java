package org.apache.commons.io;

import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Objects;
import kotlinx.coroutines.internal.LockFreeTaskQueueCore;
import org.apache.commons.io.file.Counters;
import org.apache.commons.io.file.PathUtils;
import org.apache.commons.io.file.StandardDeleteOption;

/* JADX INFO: loaded from: classes7.dex */
public class FileUtils {
    public static final File[] EMPTY_FILE_ARRAY;
    public static final BigInteger ONE_EB_BI;
    public static final BigInteger ONE_GB_BI;
    public static final BigInteger ONE_KB_BI;
    public static final BigInteger ONE_MB_BI;
    public static final BigInteger ONE_PB_BI;
    public static final BigInteger ONE_TB_BI;
    public static final BigInteger ONE_YB;
    public static final BigInteger ONE_ZB;

    static {
        BigInteger bigIntegerValueOf = BigInteger.valueOf(1024L);
        ONE_KB_BI = bigIntegerValueOf;
        BigInteger bigIntegerMultiply = bigIntegerValueOf.multiply(bigIntegerValueOf);
        ONE_MB_BI = bigIntegerMultiply;
        BigInteger bigIntegerMultiply2 = bigIntegerValueOf.multiply(bigIntegerMultiply);
        ONE_GB_BI = bigIntegerMultiply2;
        BigInteger bigIntegerMultiply3 = bigIntegerValueOf.multiply(bigIntegerMultiply2);
        ONE_TB_BI = bigIntegerMultiply3;
        BigInteger bigIntegerMultiply4 = bigIntegerValueOf.multiply(bigIntegerMultiply3);
        ONE_PB_BI = bigIntegerMultiply4;
        ONE_EB_BI = bigIntegerValueOf.multiply(bigIntegerMultiply4);
        BigInteger bigIntegerMultiply5 = BigInteger.valueOf(1024L).multiply(BigInteger.valueOf(LockFreeTaskQueueCore.FROZEN_MASK));
        ONE_ZB = bigIntegerMultiply5;
        ONE_YB = bigIntegerValueOf.multiply(bigIntegerMultiply5);
        EMPTY_FILE_ARRAY = new File[0];
    }

    public static void cleanDirectory(File file) throws IOException {
        File[] fileArrListFiles = listFiles(file, null);
        ArrayList arrayList = new ArrayList();
        for (File file2 : fileArrListFiles) {
            try {
                forceDelete(file2);
            } catch (IOException e) {
                arrayList.add(e);
            }
        }
        if (!arrayList.isEmpty()) {
            throw new IOExceptionList(file.toString(), arrayList);
        }
    }

    public static File delete(File file) throws IOException {
        Objects.requireNonNull(file, "file");
        Files.delete(file.toPath());
        return file;
    }

    public static void deleteDirectory(File file) throws IOException {
        Objects.requireNonNull(file, "directory");
        if (file.exists()) {
            if (!isSymlink(file)) {
                cleanDirectory(file);
            }
            delete(file);
        }
    }

    public static void forceDelete(File file) throws IOException {
        Objects.requireNonNull(file, "file");
        try {
            Counters.PathCounters pathCountersDelete = PathUtils.delete(file.toPath(), PathUtils.EMPTY_LINK_OPTION_ARRAY, StandardDeleteOption.OVERRIDE_READ_ONLY);
            if (pathCountersDelete.getFileCounter().get() >= 1 || pathCountersDelete.getDirectoryCounter().get() >= 1) {
                return;
            }
            throw new FileNotFoundException("File does not exist: " + file);
        } catch (IOException e) {
            throw new IOException("Cannot delete file: " + file, e);
        }
    }

    public static boolean isSymlink(File file) {
        return file != null && Files.isSymbolicLink(file.toPath());
    }

    private static File[] listFiles(File file, FileFilter fileFilter) throws IOException {
        requireDirectoryExists(file, "directory");
        File[] fileArrListFiles = fileFilter == null ? file.listFiles() : file.listFiles(fileFilter);
        if (fileArrListFiles != null) {
            return fileArrListFiles;
        }
        throw new IOException("Unknown I/O error listing contents of directory: " + file);
    }

    private static File requireDirectory(File file, String str) {
        Objects.requireNonNull(file, str);
        if (file.isDirectory()) {
            return file;
        }
        throw new IllegalArgumentException("Parameter '" + str + "' is not a directory: '" + file + "'");
    }

    private static File requireDirectoryExists(File file, String str) {
        requireExists(file, str);
        requireDirectory(file, str);
        return file;
    }

    private static File requireExists(File file, String str) {
        Objects.requireNonNull(file, str);
        if (file.exists()) {
            return file;
        }
        throw new IllegalArgumentException("File system element for parameter '" + str + "' does not exist: '" + file + "'");
    }

    @Deprecated
    public FileUtils() {
    }
}
