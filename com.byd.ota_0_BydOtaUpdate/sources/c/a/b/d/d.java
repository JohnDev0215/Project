package c.a.b.d;

import android.text.TextUtils;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/* JADX INFO: compiled from: FileUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f2339a = "d";

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v10 */
    /* JADX WARN: Type inference failed for: r1v11 */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v3, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r1v5 */
    /* JADX WARN: Type inference failed for: r1v6 */
    public static String a(String str) throws Throwable {
        InputStreamReader inputStreamReader;
        String line;
        File file = new File(str);
        StringBuilder sb = new StringBuilder();
        if (file.exists()) {
            ?? r1 = 0;
            InputStreamReader inputStreamReader2 = null;
            try {
                try {
                    inputStreamReader = new InputStreamReader(new FileInputStream(file), "UTF-8");
                } catch (Throwable th) {
                    th = th;
                }
            } catch (Exception e2) {
                e = e2;
            }
            try {
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                while (true) {
                    line = bufferedReader.readLine();
                    if (line == null) {
                        break;
                    }
                    sb.append(line);
                }
                p(inputStreamReader);
                r1 = line;
            } catch (Exception e3) {
                e = e3;
                inputStreamReader2 = inputStreamReader;
                e.printStackTrace();
                p(inputStreamReader2);
                r1 = inputStreamReader2;
            } catch (Throwable th2) {
                th = th2;
                r1 = inputStreamReader;
                p(r1);
                throw th;
            }
        }
        return sb.toString();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v10 */
    /* JADX WARN: Type inference failed for: r4v11 */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r4v5, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r4v6 */
    /* JADX WARN: Type inference failed for: r4v8, types: [java.io.Closeable] */
    public static void b(String str, String str2) throws Throwable {
        Throwable th;
        OutputStreamWriter outputStreamWriter;
        Exception e2;
        File file = new File(str, (String) str2);
        if (file.exists()) {
            try {
                try {
                    outputStreamWriter = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
                    try {
                        outputStreamWriter.write("");
                        outputStreamWriter.flush();
                        str2 = outputStreamWriter;
                    } catch (Exception e3) {
                        e2 = e3;
                        e2.printStackTrace();
                        str2 = outputStreamWriter;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    p(str2);
                    throw th;
                }
            } catch (Exception e4) {
                outputStreamWriter = null;
                e2 = e4;
            } catch (Throwable th3) {
                str2 = 0;
                th = th3;
                p(str2);
                throw th;
            }
            p(str2);
        }
    }

    public static File c(String str) {
        File file = new File(str);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return file;
    }

    public static File d(String str, String str2) {
        File file = new File(str, str2);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return file;
    }

    public static boolean e(File file) {
        return file != null && (file.exists() ? file.isDirectory() : file.mkdirs());
    }

    public static synchronized boolean f(String str, String str2, String str3) {
        FileOutputStream fileOutputStream;
        Exception e2;
        ZipOutputStream zipOutputStream;
        String str4 = f2339a;
        e.c(str4, "createZip->srcFilePath = " + str + ", destFileDir = " + str2 + ", destFileName = " + str3);
        synchronized (d.class) {
            File file = new File(str);
            if (!file.exists()) {
                e.e(str4, "createZip->srcFile is not exists");
                return false;
            }
            File[] fileArrListFiles = file.listFiles();
            if (fileArrListFiles == null || fileArrListFiles.length <= 0) {
                e.e(str4, "createZip->The source files == null || files.length <= 0");
                return false;
            }
            String str5 = File.separator;
            if (!str2.endsWith(str5)) {
                str2 = str2 + str5;
            }
            File fileL = l(str2);
            if (!fileL.exists() || !fileL.isDirectory()) {
                e.e(str4, "createZip->!destDirFile.exists() || !destDirFile.isDirectory()");
                return false;
            }
            if (TextUtils.isEmpty(str3)) {
                str3 = String.format("%1$s.zip", file.getName());
            }
            e.c(str4, "createZip->real destFileName = " + str3);
            try {
                long jCurrentTimeMillis = System.currentTimeMillis();
                fileOutputStream = new FileOutputStream(new File(fileL, str3));
                try {
                    zipOutputStream = new ZipOutputStream(fileOutputStream);
                } catch (Exception e3) {
                    e2 = e3;
                    zipOutputStream = null;
                }
                try {
                    boolean zV = file.isFile() ? v(file, zipOutputStream, file.getName()) : u(file, zipOutputStream, file.getName());
                    e.c(str4, "createZip->压缩完成，耗时：" + (System.currentTimeMillis() - jCurrentTimeMillis) + "ms");
                    StringBuilder sb = new StringBuilder();
                    sb.append("createZip->zipSuccess = ");
                    sb.append(zV);
                    e.c(str4, sb.toString());
                    p(zipOutputStream);
                    p(fileOutputStream);
                    return zV;
                } catch (Exception e4) {
                    e2 = e4;
                    e2.printStackTrace();
                    e.b(f2339a, "createZip->error msg = " + e2.getMessage());
                    p(zipOutputStream);
                    p(fileOutputStream);
                    return false;
                }
            } catch (Exception e5) {
                fileOutputStream = null;
                e2 = e5;
                zipOutputStream = null;
            }
        }
    }

    public static boolean g(String str) {
        File file = new File(str);
        if (file.exists()) {
            return file.isDirectory() ? h(str) : i(str);
        }
        return true;
    }

    public static boolean h(String str) {
        File file = new File(str);
        if (file.exists() && file.isDirectory()) {
            File[] fileArrListFiles = file.listFiles();
            boolean zH = true;
            for (int i2 = 0; i2 < fileArrListFiles.length; i2++) {
                if (!fileArrListFiles[i2].isFile()) {
                    if (fileArrListFiles[i2].isDirectory() && !(zH = h(fileArrListFiles[i2].getAbsolutePath()))) {
                        break;
                    }
                } else {
                    zH = i(fileArrListFiles[i2].getAbsolutePath());
                    if (!zH) {
                        break;
                    }
                }
            }
            if (zH && file.delete()) {
                return true;
            }
        }
        return false;
    }

    public static boolean i(String str) {
        File file = new File(str);
        if (file.exists()) {
            return file.delete();
        }
        return true;
    }

    public static byte[] j(long j) {
        long j2 = j / 1024;
        if (j2 < 1) {
            return new byte[1024];
        }
        long j3 = j2 / 1024;
        if (j3 < 1) {
            return new byte[1048576];
        }
        long j4 = j3 / 1024;
        return j4 < 1 ? new byte[10485760] : j4 / 1024 < 1 ? new byte[20971520] : new byte[41943040];
    }

    public static String k(long j) {
        long j2 = j / 1024;
        if (j2 < 1) {
            return j + "Byte";
        }
        long j3 = j2 / 1024;
        if (j3 < 1) {
            return new BigDecimal(j2).setScale(2, 4).toPlainString() + "kB";
        }
        long j4 = j3 / 1024;
        if (j4 < 1) {
            return new BigDecimal(j3).setScale(2, 4).toPlainString() + "MB";
        }
        long j5 = j4 / 1024;
        if (j5 < 1) {
            return new BigDecimal(j4).setScale(2, 4).toPlainString() + "GB";
        }
        return new BigDecimal(j5).setScale(2, 4).toPlainString() + "TB";
    }

    public static File l(String str) {
        File file = new File(str);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    public static long m(String str, String str2) {
        return new File(str, str2).length();
    }

    public static boolean n(String str) {
        return new File(str).exists();
    }

    public static boolean o(String str, String str2) {
        return new File(str, str2).exists();
    }

    public static void p(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v12 */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v2, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r1v6 */
    public static String q(String str) throws Throwable {
        BufferedReader bufferedReader;
        File file = new File(str);
        ?? r1 = 0;
        StringBuilder sb = null;
        BufferedReader bufferedReader2 = null;
        if (!file.exists()) {
            return null;
        }
        StringBuilder sb2 = new StringBuilder();
        try {
            try {
                bufferedReader = new BufferedReader(new FileReader(file));
            } catch (Throwable th) {
                th = th;
            }
        } catch (Exception e2) {
            e = e2;
        }
        while (true) {
            try {
                String line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                StringBuilder sb3 = new StringBuilder();
                sb3.append(System.lineSeparator());
                sb3.append(line);
                sb2.append(sb3.toString());
                sb = sb3;
            } catch (Exception e3) {
                e = e3;
                bufferedReader2 = bufferedReader;
                e.printStackTrace();
                p(bufferedReader2);
                r1 = bufferedReader2;
            } catch (Throwable th2) {
                th = th2;
                r1 = bufferedReader;
                p(r1);
                throw th;
            }
            return sb2.toString();
        }
        bufferedReader.close();
        p(bufferedReader);
        r1 = sb;
        return sb2.toString();
    }

    public static String r(String str) {
        int iLastIndexOf = str.lastIndexOf(46);
        return iLastIndexOf > -1 ? str.substring(0, iLastIndexOf) : str;
    }

    public static void s(String... strArr) {
        try {
            new ProcessBuilder(strArr).start();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static boolean t(String str, String str2, String str3) throws Throwable {
        RandomAccessFile randomAccessFile;
        RandomAccessFile randomAccessFile2 = null;
        try {
            try {
                l(str);
                s("chmod", "777", str);
                File file = new File(str, str2);
                if (file.exists()) {
                    b(str, str2);
                } else {
                    file.createNewFile();
                }
                s("chmod", "777", str + str2);
                randomAccessFile = new RandomAccessFile(file, "rw");
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Throwable th) {
            th = th;
        }
        try {
            randomAccessFile.write(str3.getBytes(StandardCharsets.UTF_8));
            p(randomAccessFile);
        } catch (Exception e3) {
            randomAccessFile2 = randomAccessFile;
            e = e3;
            e.printStackTrace();
            p(randomAccessFile2);
        } catch (Throwable th2) {
            randomAccessFile2 = randomAccessFile;
            th = th2;
            p(randomAccessFile2);
            throw th;
        }
        return true;
    }

    public static boolean u(File file, ZipOutputStream zipOutputStream, String str) {
        String str2 = f2339a;
        e.c(str2, "zipDirectory->srcFile = " + file.getAbsolutePath());
        if (!file.exists() || !file.isDirectory()) {
            e.e(str2, "zipDirectory->!srcFile.exists() || !srcFile.isDirectory()");
            return false;
        }
        String str3 = File.separator;
        if (!str.endsWith(str3)) {
            str = str + str3;
        }
        File[] fileArrListFiles = file.listFiles();
        boolean zV = true;
        if (fileArrListFiles == null || fileArrListFiles.length <= 0) {
            e.e(str2, "zipDirectory->files == null || files.length <= 0");
            try {
                zipOutputStream.putNextEntry(new ZipEntry(str));
                zipOutputStream.closeEntry();
                return true;
            } catch (Exception e2) {
                e2.printStackTrace();
                e.b(f2339a, "zipDirectory->zip empty dir file error msg: " + e2.getMessage());
                return false;
            }
        }
        for (File file2 : fileArrListFiles) {
            if (file2 != null) {
                if (file2.canRead()) {
                    String str4 = str + file2.getName();
                    zV = file2.isFile() ? v(file2, zipOutputStream, str4) : u(file2, zipOutputStream, str4);
                    if (!zV) {
                        break;
                    }
                } else {
                    e.e(f2339a, "zipDirectory->can't read file path : " + file2.getAbsolutePath());
                }
            }
        }
        return zV;
    }

    public static boolean v(File file, ZipOutputStream zipOutputStream, String str) {
        String str2 = f2339a;
        e.c(str2, "zipFile->srcFile = " + file.getAbsolutePath());
        try {
            try {
            } catch (Exception e2) {
                e2.printStackTrace();
                e.b(f2339a, "zipFile->error msg: " + e2.getMessage());
            }
            if (!file.exists() || !file.isFile()) {
                e.e(str2, "zipFile->!srcFile.exists() || !srcFile.isFile()");
                return false;
            }
            byte[] bArr = new byte[8192];
            zipOutputStream.putNextEntry(new ZipEntry(str));
            FileInputStream fileInputStream = new FileInputStream(file);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            while (true) {
                int i2 = bufferedInputStream.read(bArr);
                if (i2 == -1) {
                    zipOutputStream.closeEntry();
                    p(fileInputStream);
                    p(bufferedInputStream);
                    return true;
                }
                zipOutputStream.write(bArr, 0, i2);
            }
        } finally {
            p(null);
            p(null);
        }
    }
}
