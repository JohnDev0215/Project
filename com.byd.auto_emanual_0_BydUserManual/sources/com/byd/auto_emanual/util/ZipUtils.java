package com.byd.auto_emanual.util;

import android.os.Build;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/* JADX INFO: loaded from: classes.dex */
public class ZipUtils {
    public static boolean zipFiles(Collection<File> resFiles, String zipFilePath) throws IOException {
        return zipFiles(resFiles, new File(zipFilePath), null);
    }

    public static boolean zipFiles(Collection<File> resFiles, File zipFile) throws IOException {
        return zipFiles(resFiles, zipFile, null);
    }

    public static boolean zipFiles(Collection<File> resFiles, File zipFile, String comment) throws Throwable {
        ZipOutputStream zipOutputStream;
        if (resFiles == null || zipFile == null) {
            return false;
        }
        ZipOutputStream zipOutputStream2 = null;
        try {
            zipOutputStream = new ZipOutputStream(new FileOutputStream(zipFile));
        } catch (Throwable th) {
            th = th;
        }
        try {
            Iterator<File> it = resFiles.iterator();
            while (it.hasNext()) {
                if (!zipFile(it.next(), "", zipOutputStream, comment)) {
                    zipOutputStream.finish();
                    closeIO(zipOutputStream);
                    return false;
                }
            }
            zipOutputStream.finish();
            closeIO(zipOutputStream);
            return true;
        } catch (Throwable th2) {
            th = th2;
            zipOutputStream2 = zipOutputStream;
            if (zipOutputStream2 != null) {
                zipOutputStream2.finish();
                closeIO(zipOutputStream2);
            }
            throw th;
        }
    }

    public static boolean zipFile(File resFile, File zipFile) throws IOException {
        return zipFile(resFile, zipFile, null);
    }

    public static boolean zipFile(File resFile, File zipFile, String comment) throws Throwable {
        ZipOutputStream zipOutputStream;
        if (resFile == null || zipFile == null) {
            return false;
        }
        ZipOutputStream zipOutputStream2 = null;
        try {
            zipOutputStream = new ZipOutputStream(new FileOutputStream(zipFile));
        } catch (Throwable th) {
            th = th;
        }
        try {
            boolean zZipFile = zipFile(resFile, "", zipOutputStream, comment);
            closeIO(zipOutputStream);
            return zZipFile;
        } catch (Throwable th2) {
            th = th2;
            zipOutputStream2 = zipOutputStream;
            if (zipOutputStream2 != null) {
                closeIO(zipOutputStream2);
            }
            throw th;
        }
    }

    private static boolean zipFile(File resFile, String rootPath, ZipOutputStream zos, String comment) throws Throwable {
        BufferedInputStream bufferedInputStream;
        String str = rootPath + (isSpace(rootPath) ? "" : File.separator) + resFile.getName();
        if (resFile.isDirectory()) {
            File[] fileArrListFiles = resFile.listFiles();
            if (fileArrListFiles == null || fileArrListFiles.length <= 0) {
                ZipEntry zipEntry = new ZipEntry(str + '/');
                if (!isEmpty(comment)) {
                    zipEntry.setComment(comment);
                }
                zos.putNextEntry(zipEntry);
                zos.closeEntry();
            } else {
                for (File file : fileArrListFiles) {
                    if (!zipFile(file, str, zos, comment)) {
                        return false;
                    }
                }
            }
        } else {
            BufferedInputStream bufferedInputStream2 = null;
            try {
                bufferedInputStream = new BufferedInputStream(new FileInputStream(resFile));
            } catch (Throwable th) {
                th = th;
            }
            try {
                ZipEntry zipEntry2 = new ZipEntry(str);
                if (!isEmpty(comment)) {
                    zipEntry2.setComment(comment);
                }
                zos.putNextEntry(zipEntry2);
                byte[] bArr = new byte[1024];
                while (true) {
                    int i = bufferedInputStream.read(bArr);
                    if (i == -1) {
                        break;
                    }
                    zos.write(bArr, 0, i);
                }
                zos.closeEntry();
                closeIO(bufferedInputStream);
            } catch (Throwable th2) {
                th = th2;
                bufferedInputStream2 = bufferedInputStream;
                closeIO(bufferedInputStream2);
                throw th;
            }
        }
        return true;
    }

    public static boolean unzipFiles(Collection<File> zipFiles, File destDir) throws Exception {
        if (zipFiles == null || destDir == null) {
            return false;
        }
        Iterator<File> it = zipFiles.iterator();
        while (it.hasNext()) {
            if (!unzipFile(it.next(), destDir)) {
                return false;
            }
        }
        return true;
    }

    public static boolean unzipFile(File zipFile, File destDir) throws Exception {
        return unzipFileByKeyword(zipFile, destDir, null, 1, StandardCharsets.UTF_8) != null;
    }

    public static boolean unzipFile(File zipFile, File destDir, Charset charset) throws Exception {
        return unzipFileByKeyword(zipFile, destDir, null, 1, charset) != null;
    }

    public static List<File> unzipFileByKeyword(File zipFile, File destDir, String keyword, int mode, Charset charset) throws Exception {
        ZipFile zipFile2;
        BufferedOutputStream bufferedOutputStream;
        BufferedInputStream bufferedInputStream = null;
        if (zipFile == null || destDir == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        if (Build.VERSION.SDK_INT >= 24) {
            zipFile2 = new ZipFile(zipFile, mode, charset);
        } else {
            zipFile2 = new ZipFile(zipFile);
        }
        Enumeration<? extends ZipEntry> enumerationEntries = zipFile2.entries();
        while (enumerationEntries.hasMoreElements()) {
            ZipEntry zipEntryNextElement = enumerationEntries.nextElement();
            String name = zipEntryNextElement.getName();
            if (isEmpty(keyword) || getFileName(name).toLowerCase().contains(keyword.toLowerCase())) {
                File file = new File(destDir + File.separator + name);
                arrayList.add(file);
                if (zipEntryNextElement.isDirectory()) {
                    if (!createOrExistsDir(file)) {
                        return null;
                    }
                } else {
                    if (!createOrExistsFile(file)) {
                        return null;
                    }
                    try {
                        BufferedInputStream bufferedInputStream2 = new BufferedInputStream(zipFile2.getInputStream(zipEntryNextElement));
                        try {
                            bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
                        } catch (Throwable th) {
                            th = th;
                            bufferedOutputStream = null;
                        }
                        try {
                            byte[] bArr = new byte[1024];
                            while (true) {
                                int i = bufferedInputStream2.read(bArr);
                                if (i == -1) {
                                    break;
                                }
                                bufferedOutputStream.write(bArr, 0, i);
                            }
                            closeIO(bufferedInputStream2, bufferedOutputStream);
                        } catch (Throwable th2) {
                            th = th2;
                            bufferedInputStream = bufferedInputStream2;
                            closeIO(bufferedInputStream, bufferedOutputStream);
                            throw th;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        bufferedOutputStream = null;
                    }
                }
            }
        }
        return arrayList;
    }

    public static List<String> getFilesPath(String zipFilePath) throws IOException {
        return getFilesPath(getFileByPath(zipFilePath));
    }

    public static List<String> getFilesPath(File zipFile) throws IOException {
        if (zipFile == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Enumeration<?> entries = getEntries(zipFile);
        while (entries.hasMoreElements()) {
            arrayList.add(((ZipEntry) entries.nextElement()).getName());
        }
        return arrayList;
    }

    public static List<String> getComments(String zipFilePath) throws IOException {
        return getComments(getFileByPath(zipFilePath));
    }

    public static List<String> getComments(File zipFile) throws IOException {
        if (zipFile == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Enumeration<?> entries = getEntries(zipFile);
        while (entries.hasMoreElements()) {
            arrayList.add(((ZipEntry) entries.nextElement()).getComment());
        }
        return arrayList;
    }

    public static Enumeration<?> getEntries(String zipFilePath) throws IOException {
        return getEntries(getFileByPath(zipFilePath));
    }

    public static Enumeration<?> getEntries(File zipFile) throws IOException {
        if (zipFile == null) {
            return null;
        }
        return new ZipFile(zipFile).entries();
    }

    private static void closeIO(Closeable... closeables) {
        if (closeables == null) {
            return;
        }
        for (Closeable closeable : closeables) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static File getFileByPath(String filePath) {
        if (isSpace(filePath)) {
            return null;
        }
        return new File(filePath);
    }

    private static boolean createOrExistsDir(String dirPath) {
        return createOrExistsDir(getFileByPath(dirPath));
    }

    private static boolean createOrExistsDir(File file) {
        return file != null && (!file.exists() ? !file.mkdirs() : !file.isDirectory());
    }

    private static boolean createOrExistsFile(File file) {
        if (file == null) {
            return false;
        }
        if (file.exists()) {
            return file.isFile();
        }
        if (!createOrExistsDir(file.getParentFile())) {
            return false;
        }
        try {
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static String getFileName(String filePath) {
        int iLastIndexOf;
        return (isSpace(filePath) || (iLastIndexOf = filePath.lastIndexOf(File.separator)) == -1) ? filePath : filePath.substring(iLastIndexOf + 1);
    }

    private static boolean isEmpty(CharSequence s) {
        return s == null || s.length() == 0;
    }

    private static boolean isSpace(String s) {
        return s == null || s.trim().length() == 0;
    }
}
