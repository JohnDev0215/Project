package com.amazon.mp3.util;

import android.content.Context;
import android.os.Build;
import android.os.StatFs;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.music.destination.parser.ParserUtil;
import com.amazon.music.externalstorage.ExternalStorageNotSupportedException;
import com.amazon.music.externalstorage.StorageLocation;
import com.amazon.music.externalstorage.StorageLocationFileManager;
import com.fasterxml.jackson.core.JsonPointer;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.GZIPOutputStream;
import kotlin.text.Typography;

/* JADX INFO: loaded from: classes4.dex */
public class FileUtil {
    private static final String TAG = "FileUtil";

    public static void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                Log.debug(TAG, "Error closing: ", e);
            }
        }
    }

    public static void createDirectory(String filePath) {
        if (filePath == null) {
            return;
        }
        if (!filePath.endsWith("/")) {
            filePath = filePath.substring(0, filePath.lastIndexOf(47) + 1);
        }
        if (filePath.length() == 0) {
            return;
        }
        File file = new File(filePath);
        if (file.exists()) {
            return;
        }
        file.mkdirs();
    }

    public static boolean createEmptyFile(File directory, String name) {
        try {
            return new File(directory, name).createNewFile();
        } catch (IOException unused) {
            return false;
        }
    }

    public static boolean moveFile(String srcFile, String destFile) {
        return new File(srcFile).renameTo(new File(destFile));
    }

    public static void copyFile(String srcFile, String destFile, boolean preserveFileDate) throws Throwable {
        copyFile(new File(srcFile), new File(destFile), preserveFileDate);
    }

    public static void copyFile(File srcFile, File destFile, boolean preserveFileDate) throws Throwable {
        if (srcFile == null) {
            throw new NullPointerException("Source must not be null");
        }
        if (destFile == null) {
            throw new NullPointerException("Destination must not be null");
        }
        if (!srcFile.exists()) {
            throw new FileNotFoundException("Source '" + srcFile + "' does not exist");
        }
        if (srcFile.isDirectory()) {
            throw new IOException("Source '" + srcFile + "' exists but is a directory");
        }
        if (srcFile.getCanonicalPath().equals(destFile.getCanonicalPath())) {
            throw new IOException("Source '" + srcFile + "' and destination '" + destFile + "' are the same");
        }
        if (destFile.getParentFile() != null && !destFile.getParentFile().exists() && !destFile.getParentFile().mkdirs()) {
            throw new IOException("Destination '" + destFile + "' directory cannot be created");
        }
        if (destFile.exists() && !destFile.canWrite()) {
            throw new IOException("Destination '" + destFile + "' exists but is read-only");
        }
        doCopyFile(srcFile, destFile, preserveFileDate);
    }

    public static int deleteFilesInDir(File dir, boolean descend, @Nullable FileFilter filter) {
        File[] fileArrListFiles;
        if (dir == null || !dir.isDirectory() || (fileArrListFiles = dir.listFiles(filter)) == null) {
            return 0;
        }
        int iDeleteFilesInDir = 0;
        for (File file : fileArrListFiles) {
            if (file != null) {
                if (descend && file.isDirectory()) {
                    iDeleteFilesInDir += deleteFilesInDir(file, descend, filter);
                }
                if (file.delete()) {
                    iDeleteFilesInDir++;
                }
            }
        }
        return iDeleteFilesInDir;
    }

    public static boolean deleteFile(String file) {
        return deleteFile(new File(file));
    }

    public static boolean deleteFile(File file) {
        if (file == null || !file.exists()) {
            return false;
        }
        boolean zDelete = file.delete();
        if (!zDelete) {
            Log.debug(TAG, "Could not deleted file: %s", file.getAbsolutePath());
        }
        return zDelete;
    }

    public static void deleteEmptyDirectoriesUntilRoot(File currentDirectory, final File rootDirectory) {
        if (!isValidDirectory(rootDirectory)) {
            Log.error(TAG, "Called deleteEmptyDirectoriesUntilRoot with invalid root directory: $s", rootDirectory.getAbsolutePath());
            return;
        }
        boolean zDelete = true;
        while (zDelete && isValidDirectory(currentDirectory) && isChildDirectory(currentDirectory, rootDirectory) && currentDirectory.listFiles().length == 0) {
            zDelete = currentDirectory.delete();
            if (zDelete) {
                Log.debug(TAG, "Deleting empty directory: %s", currentDirectory.getAbsolutePath());
            } else {
                Log.error(TAG, "Could not delete empty directory: %s", currentDirectory.getAbsolutePath());
            }
            currentDirectory = currentDirectory.getParentFile();
        }
    }

    public static boolean isValidDirectory(final File directory) {
        return directory != null && directory.exists() && directory.isDirectory();
    }

    public static boolean isChildDirectory(final File childDirectory, final File parentDirectory) {
        if (childDirectory == null || parentDirectory == null) {
            return false;
        }
        String absolutePath = childDirectory.getAbsolutePath();
        String absolutePath2 = parentDirectory.getAbsolutePath();
        return absolutePath.startsWith(absolutePath2) && !absolutePath.equals(absolutePath2);
    }

    @Nullable
    public static File getMusicRootDirectoryForFile(final File file, final StorageLocationFileManager storageLocationFileManager, final StorageLocationFileManager.Visibility visibility, final Context context) {
        if (storageLocationFileManager == null) {
            return null;
        }
        try {
            File storageDirectory = storageLocationFileManager.getStorageDirectory(StorageLocation.DEVICE, visibility);
            if (isChildDirectory(file, storageDirectory)) {
                return storageDirectory;
            }
        } catch (ExternalStorageNotSupportedException | FileNotFoundException unused) {
        }
        try {
            File storageDirectory2 = storageLocationFileManager.getStorageDirectory(StorageLocation.SDCARD, visibility);
            if (isChildDirectory(file, storageDirectory2)) {
                return storageDirectory2;
            }
        } catch (ExternalStorageNotSupportedException | FileNotFoundException unused2) {
        }
        Log.error(TAG, "Music root directory with visibility " + visibility + " not found for file " + file.getAbsolutePath());
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v0 */
    /* JADX WARN: Type inference failed for: r11v1, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r11v2 */
    /* JADX WARN: Type inference failed for: r11v3, types: [java.io.Closeable, java.nio.channels.FileChannel, java.nio.channels.ReadableByteChannel] */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v13 */
    /* JADX WARN: Type inference failed for: r2v14 */
    /* JADX WARN: Type inference failed for: r2v2, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r2v3 */
    /* JADX WARN: Type inference failed for: r2v4, types: [java.io.Closeable, java.nio.channels.FileChannel] */
    private static void doCopyFile(File file, File file2, boolean z) throws Throwable {
        FileInputStream fileInputStream;
        FileOutputStream fileOutputStream;
        ?? channel;
        if (file2.exists() && file2.isDirectory()) {
            throw new IOException("Destination '" + file2 + "' exists but is a directory");
        }
        ?? channel2 = 0;
        channel2 = 0;
        channel2 = 0;
        try {
            fileInputStream = new FileInputStream(file);
        } catch (Throwable th) {
            th = th;
            fileInputStream = null;
            fileOutputStream = null;
        }
        try {
            fileOutputStream = new FileOutputStream(file2);
            try {
                channel = fileInputStream.getChannel();
            } catch (Throwable th2) {
                th = th2;
                channel = 0;
            }
        } catch (Throwable th3) {
            th = th3;
            fileOutputStream = null;
            channel = fileOutputStream;
            close(channel2);
            close(fileOutputStream);
            close(channel);
            close(fileInputStream);
            throw th;
        }
        try {
            channel2 = fileOutputStream.getChannel();
            long size = channel.size();
            long jTransferFrom = 0;
            while (jTransferFrom < size) {
                long j = size - jTransferFrom;
                jTransferFrom += channel2.transferFrom(channel, jTransferFrom, j > 20971520 ? 20971520L : j);
            }
            close(channel2);
            close(fileOutputStream);
            close(channel);
            close(fileInputStream);
            if (file.length() != file2.length()) {
                throw new IOException("Failed to copy full contents from '" + file + "' to '" + file2 + "'");
            }
            if (!z || file2.setLastModified(file.lastModified())) {
                return;
            }
            Log.debug(TAG, "Failed to set last modified date on %s", file2);
        } catch (Throwable th4) {
            th = th4;
            close(channel2);
            close(fileOutputStream);
            close(channel);
            close(fileInputStream);
            throw th;
        }
    }

    public static long getFreeSpace(String path) {
        return getFreeSpace(new File(path));
    }

    public static long getFreeSpace(File file) {
        String path;
        try {
            path = file.getPath();
        } catch (Exception unused) {
            path = null;
        }
        try {
            StatFs statFs = new StatFs(path);
            return ((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize());
        } catch (Exception unused2) {
            Log.debug(TAG, "Error with file: %s", path);
            return 0L;
        }
    }

    public static long getTotalSpace(File file) {
        String path;
        try {
            path = file.getPath();
        } catch (Exception unused) {
            path = null;
        }
        try {
            return new StatFs(path).getTotalBytes();
        } catch (Exception unused2) {
            Log.debug(TAG, "Error with file: %s", path);
            return 0L;
        }
    }

    public static String replaceInvalidPathChars(String pathComponent) {
        char[] cArr = {'.', ' ', '\\', JsonPointer.SEPARATOR, ':', '*', '?', '\"', Typography.less, Typography.greater, '|', '#', '%'};
        for (int i = 0; i < 13; i++) {
            pathComponent = pathComponent.replace(cArr[i], '_');
        }
        return pathComponent;
    }

    public static boolean saveToFile(final String content, final File file) throws Throwable {
        createDirectory(file.getAbsolutePath());
        OutputStreamWriter outputStreamWriter = null;
        try {
            try {
                try {
                    OutputStreamWriter outputStreamWriter2 = new OutputStreamWriter(new FileOutputStream(file), ParserUtil.UTF_8);
                    try {
                        outputStreamWriter2.write(content);
                        outputStreamWriter2.close();
                        return true;
                    } catch (FileNotFoundException e) {
                        e = e;
                        outputStreamWriter = outputStreamWriter2;
                        Log.warning(TAG, "Failed to save file", e);
                        if (outputStreamWriter != null) {
                            outputStreamWriter.close();
                        }
                        return false;
                    } catch (IOException e2) {
                        e = e2;
                        outputStreamWriter = outputStreamWriter2;
                        Log.warning(TAG, "Failed to save file", e);
                        if (outputStreamWriter != null) {
                            outputStreamWriter.close();
                        }
                        return false;
                    } catch (Throwable th) {
                        th = th;
                        outputStreamWriter = outputStreamWriter2;
                        if (outputStreamWriter != null) {
                            outputStreamWriter.close();
                        }
                        throw th;
                    }
                } catch (FileNotFoundException e3) {
                    e = e3;
                } catch (IOException e4) {
                    e = e4;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException e5) {
            Log.warning(TAG, "Failed to close stream while saving", e5);
            return false;
        }
    }

    /* JADX WARN: Not initialized variable reg: 2, insn: 0x0060: MOVE (r1 I:??[OBJECT, ARRAY]) = (r2 I:??[OBJECT, ARRAY]), block:B:35:0x0060 */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0063 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String loadFromFile(final java.io.File r6) throws java.lang.Throwable {
        /*
            java.lang.String r0 = "Failed to close"
            r1 = 0
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L47 java.io.FileNotFoundException -> L49
            r2.<init>(r6)     // Catch: java.lang.Throwable -> L47 java.io.FileNotFoundException -> L49
            java.nio.channels.FileChannel r3 = r2.getChannel()     // Catch: java.io.FileNotFoundException -> L45 java.lang.Throwable -> L5f
            long r4 = r6.length()     // Catch: java.io.FileNotFoundException -> L45 java.lang.Throwable -> L5f
            int r6 = (int) r4     // Catch: java.io.FileNotFoundException -> L45 java.lang.Throwable -> L5f
            byte[] r6 = new byte[r6]     // Catch: java.io.FileNotFoundException -> L45 java.lang.Throwable -> L5f
            java.nio.ByteBuffer r4 = java.nio.ByteBuffer.wrap(r6)     // Catch: java.io.FileNotFoundException -> L45 java.lang.Throwable -> L5f
            r3.read(r4)     // Catch: java.io.IOException -> L32 java.lang.Throwable -> L5f
            java.lang.String r3 = new java.lang.String     // Catch: java.io.UnsupportedEncodingException -> L22 java.io.FileNotFoundException -> L45 java.lang.Throwable -> L5f
            java.lang.String r4 = "UTF-8"
            r3.<init>(r6, r4)     // Catch: java.io.UnsupportedEncodingException -> L22 java.io.FileNotFoundException -> L45 java.lang.Throwable -> L5f
            goto L27
        L22:
            java.lang.String r3 = new java.lang.String     // Catch: java.io.FileNotFoundException -> L45 java.lang.Throwable -> L5f
            r3.<init>(r6)     // Catch: java.io.FileNotFoundException -> L45 java.lang.Throwable -> L5f
        L27:
            r2.close()     // Catch: java.io.IOException -> L2b
            goto L31
        L2b:
            r6 = move-exception
            java.lang.String r1 = com.amazon.mp3.util.FileUtil.TAG
            com.amazon.mp3.util.Log.info(r1, r0, r6)
        L31:
            return r3
        L32:
            r6 = move-exception
            java.lang.String r3 = com.amazon.mp3.util.FileUtil.TAG     // Catch: java.io.FileNotFoundException -> L45 java.lang.Throwable -> L5f
            java.lang.String r4 = "Couldn't read file"
            com.amazon.mp3.util.Log.warning(r3, r4, r6)     // Catch: java.io.FileNotFoundException -> L45 java.lang.Throwable -> L5f
            r2.close()     // Catch: java.io.IOException -> L3e
            goto L44
        L3e:
            r6 = move-exception
            java.lang.String r2 = com.amazon.mp3.util.FileUtil.TAG
            com.amazon.mp3.util.Log.info(r2, r0, r6)
        L44:
            return r1
        L45:
            r6 = move-exception
            goto L4b
        L47:
            r6 = move-exception
            goto L61
        L49:
            r6 = move-exception
            r2 = r1
        L4b:
            java.lang.String r3 = com.amazon.mp3.util.FileUtil.TAG     // Catch: java.lang.Throwable -> L5f
            java.lang.String r4 = "Couldn't load file"
            com.amazon.mp3.util.Log.warning(r3, r4, r6)     // Catch: java.lang.Throwable -> L5f
            if (r2 == 0) goto L5e
            r2.close()     // Catch: java.io.IOException -> L58
            goto L5e
        L58:
            r6 = move-exception
            java.lang.String r2 = com.amazon.mp3.util.FileUtil.TAG
            com.amazon.mp3.util.Log.info(r2, r0, r6)
        L5e:
            return r1
        L5f:
            r6 = move-exception
            r1 = r2
        L61:
            if (r1 == 0) goto L6d
            r1.close()     // Catch: java.io.IOException -> L67
            goto L6d
        L67:
            r1 = move-exception
            java.lang.String r2 = com.amazon.mp3.util.FileUtil.TAG
            com.amazon.mp3.util.Log.info(r2, r0, r1)
        L6d:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.util.FileUtil.loadFromFile(java.io.File):java.lang.String");
    }

    @NonNull
    public static byte[] gzip(@NonNull final byte[] bytes) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(bytes.length);
        try {
            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            try {
                gZIPOutputStream.write(bytes);
                gZIPOutputStream.close();
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                byteArrayOutputStream.close();
                return byteArray;
            } catch (Throwable th) {
                gZIPOutputStream.close();
                throw th;
            }
        } catch (Throwable th2) {
            try {
                byteArrayOutputStream.close();
            } catch (Throwable th3) {
                th2.addSuppressed(th3);
            }
            throw th2;
        }
    }

    @NonNull
    public static byte[] loadBytesFromFile(@NonNull final File file) throws IOException {
        if (!file.canRead()) {
            throw new IOException("Unable to read the file: " + file.getAbsolutePath());
        }
        if (Build.VERSION.SDK_INT > 26) {
            return Files.readAllBytes(file.toPath());
        }
        int length = (int) file.length();
        byte[] bArr = new byte[length];
        byte[] bArr2 = new byte[length];
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
        try {
            int i = bufferedInputStream.read(bArr, 0, length);
            if (i < length) {
                int i2 = length - i;
                while (i2 > 0) {
                    int i3 = bufferedInputStream.read(bArr2, 0, i2);
                    System.arraycopy(bArr2, 0, bArr, length - i2, i3);
                    i2 -= i3;
                }
            }
            bufferedInputStream.close();
            return bArr;
        } catch (Throwable th) {
            try {
                bufferedInputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public static List<String> getValidMusicChildFiles(File parentDir) {
        LinkedList linkedList = new LinkedList();
        File[] fileArrListFiles = parentDir.listFiles();
        if (fileArrListFiles != null) {
            for (File file : fileArrListFiles) {
                if (file.isDirectory()) {
                    linkedList.addAll(getValidMusicChildFiles(file));
                } else {
                    String name = file.getName();
                    if (((name.isEmpty() || name.charAt(0) == '.') ? false : true) && !MimeTypeUtil.isUnsupported(file)) {
                        linkedList.add(file.getAbsolutePath());
                    }
                }
            }
        }
        return linkedList;
    }

    public static void deleteEmptyDirectories(File dir, boolean deleteInputDir) {
        File[] fileArrListFiles;
        if (dir.exists() && dir.isDirectory() && (fileArrListFiles = dir.listFiles()) != null) {
            for (File file : fileArrListFiles) {
                deleteEmptyDirectories(file, true);
            }
            File[] fileArrListFiles2 = dir.listFiles();
            if (deleteInputDir && fileArrListFiles2 != null && fileArrListFiles2.length == 0) {
                Log.debug(TAG, "Deleting directory %s", dir.getPath());
                dir.delete();
            }
        }
    }
}
