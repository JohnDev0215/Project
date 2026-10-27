package c.a.j.f;

import android.util.Log;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/* JADX INFO: compiled from: IOUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class f {
    public static boolean a(String str, String str2) {
        Log.i("copyDir", "source:" + str + " target:" + str2);
        String str3 = File.separator;
        if (!str2.endsWith(str3)) {
            str2 = str2 + str3;
        }
        File file = new File(str);
        if (file.isDirectory()) {
            boolean zG = g(file, str2);
            Log.i("copyDir", "recursionDir result:" + zG);
            return zG;
        }
        boolean zB = b(file, new File(str2 + file.getName()));
        Log.i("copyDir", "copyFileBio result:" + zB);
        return zB;
    }

    public static boolean b(File file, File file2) {
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file2));
            byte[] bArr = new byte[209715200];
            while (true) {
                int i2 = bufferedInputStream.read(bArr);
                if (i2 == -1) {
                    bufferedInputStream.close();
                    bufferedOutputStream.close();
                    return true;
                }
                bufferedOutputStream.write(bArr, 0, i2);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            c.a.k.b.b("copyFileBio fail,e=" + e2.getMessage());
            return false;
        }
    }

    public static void c(File file) {
        for (File file2 : file.listFiles()) {
            if (file2.isDirectory()) {
                c(file2);
            } else {
                file2.delete();
            }
        }
        file.delete();
    }

    public static float d(File file) {
        float length = 0.0f;
        for (File file2 : file.listFiles()) {
            length += file2.isFile() ? file2.length() : d(file2);
        }
        return length;
    }

    public static String e(File file, String str) throws IOException {
        c.a.k.b.c("getSpecialFilePath, special = " + str);
        File[] fileArrListFiles = file.listFiles();
        c.a.k.b.c("getSpecialFilePath, files = " + fileArrListFiles);
        if (fileArrListFiles == null) {
            return null;
        }
        for (int i2 = 0; i2 < fileArrListFiles.length; i2++) {
            c.a.k.b.c("getSpecialFilePath, files[i].isFile() = " + fileArrListFiles[i2].isFile() + ",files[i].getName()=" + fileArrListFiles[i2].getName());
            if (fileArrListFiles[i2].isFile() && fileArrListFiles[i2].getName().endsWith(str)) {
                return fileArrListFiles[i2].getAbsolutePath();
            }
        }
        return null;
    }

    public static String f(File file) throws Throwable {
        FileInputStream fileInputStream;
        Throwable th;
        ByteArrayOutputStream byteArrayOutputStream;
        try {
            fileInputStream = new FileInputStream(file);
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
                try {
                    byte[] bArr = new byte[104857600];
                    while (true) {
                        int i2 = fileInputStream.read(bArr);
                        if (i2 == -1) {
                            break;
                        }
                        byteArrayOutputStream.write(bArr, 0, i2);
                    }
                    byteArrayOutputStream.flush();
                    String str = new String(byteArrayOutputStream.toByteArray());
                    try {
                        fileInputStream.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                    try {
                        byteArrayOutputStream.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                    return str;
                } catch (Throwable th2) {
                    th = th2;
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                    }
                    if (byteArrayOutputStream == null) {
                        throw th;
                    }
                    try {
                        byteArrayOutputStream.close();
                        throw th;
                    } catch (IOException e5) {
                        e5.printStackTrace();
                        throw th;
                    }
                }
            } catch (Throwable th3) {
                th = th3;
                byteArrayOutputStream = null;
            }
        } catch (Throwable th4) {
            fileInputStream = null;
            th = th4;
            byteArrayOutputStream = null;
        }
    }

    public static boolean g(File file, String str) {
        if (!file.isDirectory()) {
            File file2 = new File(str);
            File parentFile = file2.getParentFile();
            if (!parentFile.exists()) {
                parentFile.mkdirs();
            }
            boolean zB = b(file, file2);
            if (zB) {
                return zB;
            }
            c.a.k.b.c("copyDir error result =" + zB);
            return zB;
        }
        File[] fileArrListFiles = file.listFiles();
        if (fileArrListFiles.length > 0) {
            for (File file3 : fileArrListFiles) {
                g(file3, str + File.separator + file3.getName());
            }
        }
        return true;
    }

    public static void h(File file, String str) throws IOException {
        File file2 = new File(str);
        if (!file2.exists()) {
            file2.mkdirs();
        }
        ZipFile zipFile = new ZipFile(file, Charset.forName("GBK"));
        Enumeration<? extends ZipEntry> enumerationEntries = zipFile.entries();
        while (enumerationEntries.hasMoreElements()) {
            ZipEntry zipEntryNextElement = enumerationEntries.nextElement();
            InputStream inputStream = zipFile.getInputStream(zipEntryNextElement);
            String name = zipEntryNextElement.getName();
            int iLastIndexOf = name.lastIndexOf(47);
            String strReplaceAll = (str + name).replaceAll("\\*", "/");
            if (iLastIndexOf != -1) {
                File file3 = new File(strReplaceAll.substring(0, strReplaceAll.lastIndexOf("/")));
                if (!file3.exists()) {
                    file3.mkdirs();
                }
            }
            if (!new File(strReplaceAll).isDirectory()) {
                FileOutputStream fileOutputStream = new FileOutputStream(strReplaceAll);
                byte[] bArr = new byte[20971520];
                while (true) {
                    int i2 = inputStream.read(bArr);
                    if (i2 <= 0) {
                        break;
                    } else {
                        fileOutputStream.write(bArr, 0, i2);
                    }
                }
                inputStream.close();
                fileOutputStream.close();
            }
        }
        zipFile.close();
        Log.i("解压{}成功", file.getName());
    }
}
