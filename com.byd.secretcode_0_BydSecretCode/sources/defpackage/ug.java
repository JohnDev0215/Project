package defpackage;

import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/* JADX INFO: compiled from: ZipBTFilesUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class ug {
    public static final SimpleDateFormat a = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ssZ");
    public static boolean b = false;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0 */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.io.FileInputStream] */
    /* JADX WARN: Type inference failed for: r0v3 */
    /* JADX WARN: Type inference failed for: r0v5 */
    /* JADX WARN: Type inference failed for: r0v6 */
    public static void a(String str, String str2, ZipOutputStream zipOutputStream) throws Exception {
        if (zipOutputStream == null) {
            return;
        }
        ?? r0 = 0;
        r0 = 0;
        try {
            try {
                File file = new File(str + str2);
                if (file.isFile()) {
                    try {
                        ZipEntry zipEntry = new ZipEntry(str2);
                        FileInputStream fileInputStream = new FileInputStream(file);
                        try {
                            zipOutputStream.putNextEntry(zipEntry);
                            byte[] bArr = new byte[4096];
                            while (true) {
                                int i = fileInputStream.read(bArr);
                                if (i == -1) {
                                    break;
                                } else {
                                    zipOutputStream.write(bArr, 0, i);
                                }
                            }
                            zipOutputStream.closeEntry();
                            r0 = fileInputStream;
                        } catch (IOException e) {
                            e.printStackTrace();
                            fileInputStream.close();
                            return;
                        } catch (Throwable th) {
                            try {
                                fileInputStream.close();
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                            throw th;
                        }
                    } catch (IOException e3) {
                        e = e3;
                        r0 = str2;
                        try {
                            e.printStackTrace();
                            return;
                        } catch (IOException unused) {
                        }
                    }
                } else {
                    Log.d("ZipBTFilesUtils", "file exists= " + file.exists() + ", file patch = " + file.getPath());
                    String[] list = file.list();
                    if (list.length <= 0) {
                        zipOutputStream.putNextEntry(new ZipEntry(str2 + File.separator));
                        zipOutputStream.closeEntry();
                    }
                    for (String str3 : list) {
                        a(str + str2 + "/", str3, zipOutputStream);
                    }
                }
                if (r0 != 0) {
                    r0.close();
                }
            } catch (Throwable th2) {
                th2.printStackTrace();
            }
        } catch (IOException e4) {
            e = e4;
        }
    }

    public static void b(String str, String str2) {
        File file;
        ZipOutputStream zipOutputStream = null;
        try {
            file = new File(str);
            try {
                File file2 = new File(str2);
                if (!file2.exists()) {
                    file2.mkdirs();
                }
                zipOutputStream = new ZipOutputStream(new FileOutputStream(new File(file2, a.format(new Date()) + "btlog.zip")));
            } catch (Throwable unused) {
            }
        } catch (Throwable unused2) {
            file = null;
        }
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("ZipFolder file.exists() = ");
            sb.append(file.exists());
            String str3 = File.separator;
            sb.append(str3);
            sb.append(", file.getName() = ");
            sb.append(file.getName());
            Log.d("ZipBTFilesUtils", sb.toString());
            try {
                a(file.getParent() + str3, file.getName(), zipOutputStream);
                b = true;
                zipOutputStream.finish();
                zipOutputStream.close();
            } catch (Exception e) {
                b = false;
                e.printStackTrace();
                if (zipOutputStream != null) {
                    zipOutputStream.finish();
                    zipOutputStream.close();
                }
            } catch (Throwable unused3) {
                if (zipOutputStream != null) {
                    try {
                        zipOutputStream.finish();
                        zipOutputStream.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
            }
        } catch (IOException e3) {
            e3.printStackTrace();
        }
    }

    public static boolean c() {
        Log.d("ZipBTFilesUtils", "ZipBTFilesUtils isSuccess: " + b);
        return b;
    }
}
