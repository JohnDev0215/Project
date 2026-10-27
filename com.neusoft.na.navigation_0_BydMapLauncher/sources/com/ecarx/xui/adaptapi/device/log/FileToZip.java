package com.ecarx.xui.adaptapi.device.log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/* JADX INFO: loaded from: classes2.dex */
public class FileToZip {
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r12v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r12v1 */
    /* JADX WARN: Type inference failed for: r12v10, types: [java.util.zip.ZipOutputStream] */
    /* JADX WARN: Type inference failed for: r12v12, types: [java.util.zip.ZipOutputStream] */
    /* JADX WARN: Type inference failed for: r12v4, types: [java.util.zip.ZipOutputStream] */
    /* JADX WARN: Type inference failed for: r12v9 */
    public static boolean fileToZip(String str, String str2, String str3) throws Throwable {
        boolean zDelete;
        File file = new File(str);
        if (!file.exists()) {
            System.out.println("待压缩的文件目录：" + str + "不存在.");
            return false;
        }
        BufferedInputStream bufferedInputStream = null;
        try {
            try {
                File file2 = new File(((String) str2) + "/" + str3 + ".zip");
                if (file2.exists()) {
                    System.out.println(((String) str2) + "目录下存在名字为:" + str3 + ".zip打包文件.");
                    zDelete = file2.delete();
                } else {
                    zDelete = true;
                }
                File[] fileArrListFiles = file.listFiles();
                if (file.length() < 1 || !zDelete) {
                    System.out.println("待压缩的文件目录：" + str + "里面不存在文件，无需压缩.");
                    str2 = 0;
                } else {
                    str2 = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(file2)));
                    try {
                        byte[] bArr = new byte[10240];
                        int i = 0;
                        while (i < fileArrListFiles.length) {
                            str2.putNextEntry(new ZipEntry(fileArrListFiles[i].getName()));
                            BufferedInputStream bufferedInputStream2 = new BufferedInputStream(new FileInputStream(fileArrListFiles[i]), 10240);
                            while (true) {
                                try {
                                    int i2 = bufferedInputStream2.read(bArr, 0, 10240);
                                    if (i2 != -1) {
                                        str2.write(bArr, 0, i2);
                                    }
                                } catch (FileNotFoundException e) {
                                    e = e;
                                    e.printStackTrace();
                                    throw new RuntimeException(e);
                                } catch (IOException e2) {
                                    e = e2;
                                    e.printStackTrace();
                                    throw new RuntimeException(e);
                                } catch (Throwable th) {
                                    th = th;
                                    bufferedInputStream = bufferedInputStream2;
                                    if (bufferedInputStream != null) {
                                        try {
                                            bufferedInputStream.close();
                                        } catch (IOException e3) {
                                            e3.printStackTrace();
                                            throw new RuntimeException(e3);
                                        }
                                    }
                                    if (str2 != 0) {
                                        str2.close();
                                    }
                                    throw th;
                                }
                            }
                            i++;
                            bufferedInputStream = bufferedInputStream2;
                        }
                    } catch (FileNotFoundException e4) {
                        e = e4;
                    } catch (IOException e5) {
                        e = e5;
                    }
                }
                if (bufferedInputStream != null) {
                    try {
                        bufferedInputStream.close();
                    } catch (IOException e6) {
                        e6.printStackTrace();
                        throw new RuntimeException(e6);
                    }
                }
                if (str2 != 0) {
                    str2.close();
                }
                return true;
            } catch (FileNotFoundException e7) {
                e = e7;
            } catch (IOException e8) {
                e = e8;
            } catch (Throwable th2) {
                th = th2;
                str2 = 0;
            }
        } catch (Throwable th3) {
            th = th3;
        }
    }
}
