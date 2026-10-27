package c.a.f.a;

import android.util.Log;
import c.a.b.d.d;
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
public class a {

    /* JADX INFO: renamed from: c.a.f.a.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: IOUtils.java */
    public interface InterfaceC0050a {
        void a(long j, long j2);
    }

    public static String a(File file) throws Throwable {
        FileInputStream fileInputStream;
        Throwable th;
        ByteArrayOutputStream byteArrayOutputStream;
        try {
            fileInputStream = new FileInputStream(file);
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
                try {
                    byte[] bArr = new byte[1024];
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

    public static void b(File file, String str, InterfaceC0050a interfaceC0050a) throws IOException {
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
                byte[] bArrJ = d.j(file.length());
                long j = 0;
                while (true) {
                    int i2 = inputStream.read(bArrJ);
                    if (i2 <= 0) {
                        break;
                    }
                    fileOutputStream.write(bArrJ, 0, i2);
                    j += (long) i2;
                    if (interfaceC0050a != null) {
                        interfaceC0050a.a(j, file.length());
                    }
                }
                inputStream.close();
                fileOutputStream.close();
            }
        }
        Log.i("解压{}成功", file.getName());
    }
}
