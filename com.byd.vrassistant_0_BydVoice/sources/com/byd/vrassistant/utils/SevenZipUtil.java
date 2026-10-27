package com.byd.vrassistant.utils;

import com.byd.vrassistant.utils.DataUtils;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.commons.compress.archivers.sevenz.SevenZArchiveEntry;
import org.apache.commons.compress.archivers.sevenz.SevenZFile;
import org.apache.commons.compress.archivers.sevenz.SevenZOutputFile;

/* JADX INFO: loaded from: classes2.dex */
public class SevenZipUtil {
    private final String TAG = "NEU_SevenZipUtil";

    public static void unCompress(File file, File file2) throws Throwable {
        if (file == null || !file.exists()) {
            throw new RuntimeException("Invalid outputDir:" + file2);
        }
        if (file2 == null || !file2.exists() || !file2.isDirectory()) {
            file2.mkdirs();
        }
        SevenZFile sevenZFile = new SevenZFile(file);
        while (true) {
            SevenZArchiveEntry nextEntry = sevenZFile.getNextEntry();
            if (nextEntry == null) {
                return;
            }
            String name = nextEntry.getName();
            if (nextEntry.isDirectory()) {
                new File(file2, name).mkdirs();
            } else {
                int iLastIndexOf = name.lastIndexOf(File.separator);
                File file3 = new File(file2, iLastIndexOf == -1 ? "" : name.substring(0, iLastIndexOf));
                if (!file3.exists() || !file3.isDirectory()) {
                    file3.mkdirs();
                }
                File file4 = new File(file2, name);
                FileOutputStream fileOutputStream = null;
                try {
                    try {
                        FileOutputStream fileOutputStream2 = new FileOutputStream(file4);
                        try {
                            byte[] bArr = new byte[2048];
                            while (true) {
                                int i = sevenZFile.read(bArr);
                                if (i == -1) {
                                    break;
                                } else {
                                    fileOutputStream2.write(bArr, 0, i);
                                }
                            }
                            fileOutputStream2.flush();
                            fileOutputStream2.close();
                        } catch (Exception e) {
                            e = e;
                            fileOutputStream = fileOutputStream2;
                            e.printStackTrace();
                            if (fileOutputStream != null) {
                                fileOutputStream.flush();
                                fileOutputStream.close();
                            }
                        } catch (Throwable th) {
                            th = th;
                            fileOutputStream = fileOutputStream2;
                            if (fileOutputStream != null) {
                                fileOutputStream.flush();
                                fileOutputStream.close();
                            }
                            throw th;
                        }
                    } catch (Exception e2) {
                        e = e2;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            }
        }
    }

    public static void unCompressEx(File file, File file2, DataUtils.USBCopyDataProgress uSBCopyDataProgress) throws Throwable {
        if (file == null || !file.exists()) {
            throw new RuntimeException("Invalid outputDir:" + file2);
        }
        if (file2 == null || !file2.exists() || !file2.isDirectory()) {
            file2.mkdirs();
        }
        SevenZFile sevenZFile = new SevenZFile(file);
        while (true) {
            SevenZArchiveEntry nextEntry = sevenZFile.getNextEntry();
            if (nextEntry == null) {
                return;
            }
            String name = nextEntry.getName();
            if (nextEntry.isDirectory()) {
                new File(file2, name).mkdirs();
            } else {
                int iLastIndexOf = name.lastIndexOf(File.separator);
                File file3 = new File(file2, iLastIndexOf == -1 ? "" : name.substring(0, iLastIndexOf));
                if (!file3.exists() || !file3.isDirectory()) {
                    file3.mkdirs();
                }
                FileOutputStream fileOutputStream = null;
                try {
                    try {
                        FileOutputStream fileOutputStream2 = new FileOutputStream(new File(file2, name));
                        try {
                            byte[] bArr = new byte[2048];
                            while (true) {
                                int i = sevenZFile.read(bArr);
                                if (i == -1) {
                                    break;
                                } else {
                                    fileOutputStream2.write(bArr, 0, i);
                                }
                            }
                            if (uSBCopyDataProgress != null) {
                                uSBCopyDataProgress.onFileCopyIncreased(name, "");
                            }
                            fileOutputStream2.flush();
                            fileOutputStream2.close();
                        } catch (Exception e) {
                            e = e;
                            fileOutputStream = fileOutputStream2;
                            e.printStackTrace();
                            if (fileOutputStream != null) {
                                fileOutputStream.flush();
                                fileOutputStream.close();
                            }
                        } catch (Throwable th) {
                            th = th;
                            fileOutputStream = fileOutputStream2;
                            if (fileOutputStream != null) {
                                fileOutputStream.flush();
                                fileOutputStream.close();
                            }
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                    }
                } catch (Exception e2) {
                    e = e2;
                }
            }
        }
    }

    public static long getCompressFileSize(File file) throws IOException {
        if (file == null || !file.exists()) {
            throw new RuntimeException("Invalid inCompressFile:" + file.getName());
        }
        SevenZFile sevenZFile = new SevenZFile(file);
        long size = 0;
        while (true) {
            SevenZArchiveEntry nextEntry = sevenZFile.getNextEntry();
            if (nextEntry == null) {
                return size;
            }
            size += nextEntry.getSize();
            nextEntry.isDirectory();
        }
    }

    public static int getCompressFileCount(File file) throws IOException {
        if (file == null || !file.exists()) {
            throw new RuntimeException("Invalid inCompressFile:" + file.getName());
        }
        SevenZFile sevenZFile = new SevenZFile(file);
        int i = 0;
        while (true) {
            SevenZArchiveEntry nextEntry = sevenZFile.getNextEntry();
            if (nextEntry == null) {
                return i;
            }
            if (!nextEntry.isDirectory()) {
                i++;
            }
        }
    }

    public static void compress(File file, File... fileArr) throws IOException {
        if (fileArr.length == 0) {
            throw new RuntimeException("InputFiles is null.");
        }
        for (File file2 : fileArr) {
            if (!file2.exists()) {
                throw new RuntimeException("InputFile:" + file2.getPath() + "not exists.");
            }
        }
        if (file != null && file.exists()) {
            file.delete();
        }
        SevenZOutputFile sevenZOutputFile = new SevenZOutputFile(file);
        try {
            for (File file3 : fileArr) {
                compress(sevenZOutputFile, file3, (String) null);
            }
        } finally {
            sevenZOutputFile.close();
        }
    }

    private static void compress(SevenZOutputFile sevenZOutputFile, File file, String str) throws IOException {
        if (str == null) {
            str = file.getName();
        }
        if (file.isDirectory()) {
            File[] fileArrListFiles = file.listFiles();
            if (fileArrListFiles.length == 0) {
                genSingleEntry(sevenZOutputFile, file, str);
                sevenZOutputFile.closeArchiveEntry();
                return;
            }
            for (File file2 : fileArrListFiles) {
                compress(sevenZOutputFile, file2, str + File.separator + file2.getName());
            }
            return;
        }
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
        genSingleEntry(sevenZOutputFile, file, str);
        byte[] bArr = new byte[2048];
        while (true) {
            int i = bufferedInputStream.read(bArr);
            if (i != -1) {
                sevenZOutputFile.write(bArr, 0, i);
            } else {
                bufferedInputStream.close();
                sevenZOutputFile.closeArchiveEntry();
                return;
            }
        }
    }

    private static SevenZArchiveEntry genSingleEntry(SevenZOutputFile sevenZOutputFile, File file, String str) throws IOException {
        SevenZArchiveEntry sevenZArchiveEntryCreateArchiveEntry = sevenZOutputFile.createArchiveEntry(file, str);
        sevenZOutputFile.putArchiveEntry(sevenZArchiveEntryCreateArchiveEntry);
        return sevenZArchiveEntryCreateArchiveEntry;
    }

    public static void compress(File file, File file2, String str) throws IOException {
        if (str == null) {
            return;
        }
        byte[] bArr = new byte[2048];
        File fileCreateTempFile = File.createTempFile(file.getName(), null);
        long length = file.length();
        boolean zRenameTo = file.renameTo(fileCreateTempFile);
        SevenZOutputFile sevenZOutputFile = new SevenZOutputFile(file);
        if (length > 0) {
            if (!zRenameTo) {
                throw new RuntimeException("could not rename the file " + file.getAbsolutePath() + " to " + fileCreateTempFile.getAbsolutePath());
            }
            SevenZFile sevenZFile = new SevenZFile(fileCreateTempFile);
            while (true) {
                SevenZArchiveEntry nextEntry = sevenZFile.getNextEntry();
                if (nextEntry == null) {
                    break;
                }
                sevenZOutputFile.putArchiveEntry(nextEntry);
                while (true) {
                    int i = sevenZFile.read(bArr);
                    if (i != -1) {
                        sevenZOutputFile.write(bArr, 0, i);
                    }
                }
                sevenZOutputFile.closeArchiveEntry();
            }
            sevenZFile.close();
        }
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file2));
        sevenZOutputFile.putArchiveEntry(sevenZOutputFile.createArchiveEntry(file2, str));
        while (true) {
            int i2 = bufferedInputStream.read(bArr);
            if (i2 != -1) {
                sevenZOutputFile.write(bArr, 0, i2);
            } else {
                bufferedInputStream.close();
                sevenZOutputFile.closeArchiveEntry();
                sevenZOutputFile.close();
                fileCreateTempFile.delete();
                return;
            }
        }
    }
}
