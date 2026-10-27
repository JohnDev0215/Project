package com.byd.engineermode.ui.functiontest.logs;

import android.util.Log;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/* JADX INFO: compiled from: FileZipHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static boolean f2474a = true;

    public static void a(String str, File file, String... strArr) throws Exception {
        FileDescriptor fileDescriptor;
        FileOutputStream fileOutputStream;
        ZipOutputStream zipOutputStream;
        String str2;
        f2474a = true;
        if (!file.getParentFile().exists()) {
            Log.e("FileZipHelper", "destFile.getParentFile() mkdirs is " + file.getParentFile().mkdirs());
        }
        ZipOutputStream zipOutputStream2 = null;
        FileDescriptor fd = null;
        zipOutputStream2 = null;
        try {
            fileOutputStream = new FileOutputStream(file);
            try {
                zipOutputStream = new ZipOutputStream(fileOutputStream);
            } catch (Throwable th) {
                th = th;
                fileDescriptor = null;
            }
        } catch (Throwable th2) {
            th = th2;
            fileDescriptor = null;
            fileOutputStream = null;
        }
        try {
            fd = fileOutputStream.getFD();
            int length = strArr.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                str2 = strArr[i];
                if (!f2474a) {
                    com.byd.engineermode.utils.a.c("compressDirectory isUDiskMount = " + f2474a);
                    break;
                }
                if (str2.contains("logcatd_test_trace.log") || str2.contains("lost+found") || str2.contains("vt_trace.log")) {
                    break;
                }
                com.byd.engineermode.utils.a.a("compress path = " + str2);
                if (!str2.equals("")) {
                    File file2 = new File(str2);
                    com.byd.engineermode.utils.a.a("compress path, file.exists() = " + file2.exists());
                    if (file2.exists()) {
                        if (file2.isDirectory()) {
                            b(zipOutputStream, file2, "");
                        } else if (file2.isFile()) {
                            b(zipOutputStream, file2, "");
                        }
                    }
                }
                i++;
            }
            com.byd.engineermode.utils.a.a("compressDirectory, filtration path = " + str2);
            zipOutputStream.finish();
            zipOutputStream.flush();
            fd.sync();
            a.a(zipOutputStream);
            a.a(fileOutputStream);
        } catch (Throwable th3) {
            th = th3;
            FileDescriptor fileDescriptor2 = fd;
            zipOutputStream2 = zipOutputStream;
            fileDescriptor = fileDescriptor2;
            if (zipOutputStream2 != null) {
                zipOutputStream2.finish();
                zipOutputStream2.flush();
            }
            if (fileOutputStream != null) {
                fileDescriptor.sync();
            }
            a.a(zipOutputStream2);
            a.a(fileOutputStream);
            throw th;
        }
    }

    private static void b(ZipOutputStream zipOutputStream, File file, String str) throws Throwable {
        FileInputStream fileInputStream = null;
        try {
            try {
                try {
                    int i = 0;
                    if (!file.isDirectory()) {
                        if (!file.getName().endsWith(".db") && !file.getName().endsWith(".db-shm") && !file.getName().endsWith(".db-wal")) {
                            byte[] bArr = new byte[4096];
                            FileInputStream fileInputStream2 = new FileInputStream(file);
                            try {
                                zipOutputStream.putNextEntry(new ZipEntry(str + file.getName()));
                                while (true) {
                                    int i2 = fileInputStream2.read(bArr);
                                    if (i2 == -1) {
                                        break;
                                    }
                                    if (!f2474a) {
                                        com.byd.engineermode.utils.a.c("zipFileOrDirectory in.read isUDiskMount = " + f2474a);
                                        break;
                                    }
                                    zipOutputStream.write(bArr, 0, i2);
                                }
                                zipOutputStream.closeEntry();
                                fileInputStream = fileInputStream2;
                            } catch (IOException e) {
                                e = e;
                                fileInputStream = fileInputStream2;
                                e.printStackTrace();
                                if (fileInputStream == null) {
                                    return;
                                } else {
                                    fileInputStream.close();
                                }
                            } catch (Throwable th) {
                                th = th;
                                fileInputStream = fileInputStream2;
                                if (fileInputStream != null) {
                                    try {
                                        fileInputStream.close();
                                    } catch (IOException e2) {
                                        e2.printStackTrace();
                                    }
                                }
                                throw th;
                            }
                        }
                        Log.i("FileZipHelper", "zipFileOrDirectory fileOrDirectory.getName() = " + file.getName());
                        zipOutputStream.closeEntry();
                        return;
                    }
                    com.byd.engineermode.utils.a.c("compress path, fileOrDirectory.exists() = " + file.exists() + ", fileOrDirectory listFiles()= " + file.listFiles());
                    File[] fileArrListFiles = file.listFiles();
                    if (fileArrListFiles != null) {
                        while (true) {
                            if (i >= fileArrListFiles.length) {
                                break;
                            }
                            if (!f2474a) {
                                com.byd.engineermode.utils.a.c("zipFileOrDirectory fileOrDirectory.listFiles() isUDiskMount = " + f2474a);
                                break;
                            }
                            b(zipOutputStream, fileArrListFiles[i], str + file.getName() + "/");
                            i++;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (IOException e3) {
                e = e3;
            }
            if (fileInputStream != null) {
                fileInputStream.close();
            }
        } catch (IOException e4) {
            e4.printStackTrace();
        }
    }
}
