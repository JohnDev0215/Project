package org.extra.relinker;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import org.extra.relinker.c;

/* JADX INFO: loaded from: classes3.dex */
public class a implements c.a {

    /* JADX INFO: renamed from: org.extra.relinker.a$a, reason: collision with other inner class name */
    public static class C0106a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public ZipFile f3959a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public ZipEntry f3960b;

        public C0106a(ZipFile zipFile, ZipEntry zipEntry) {
            this.f3959a = zipFile;
            this.f3960b = zipEntry;
        }
    }

    private String[] a(Context context) {
        String[] strArr;
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        if (Build.VERSION.SDK_INT < 21 || (strArr = applicationInfo.splitSourceDirs) == null || strArr.length == 0) {
            return new String[]{applicationInfo.sourceDir};
        }
        String[] strArr2 = new String[strArr.length + 1];
        strArr2[0] = applicationInfo.sourceDir;
        System.arraycopy(strArr, 0, strArr2, 1, strArr.length);
        return strArr2;
    }

    private C0106a a(Context context, String[] strArr, String str, f fVar) {
        int i;
        String[] strArrA = a(context);
        int length = strArrA.length;
        int i2 = 0;
        ZipFile zipFile = null;
        int i3 = 0;
        while (i3 < length) {
            String str2 = strArrA[i3];
            int i4 = i2;
            while (true) {
                int i5 = i4 + 1;
                i = 5;
                if (i4 >= 5) {
                    break;
                }
                try {
                    zipFile = new ZipFile(new File(str2), 1);
                    break;
                } catch (IOException unused) {
                    i4 = i5;
                }
            }
            if (zipFile != null) {
                int i6 = i2;
                while (true) {
                    int i7 = i6 + 1;
                    if (i6 < i) {
                        int length2 = strArr.length;
                        int i8 = i2;
                        while (i8 < length2) {
                            String str3 = "lib" + File.separatorChar + strArr[i8] + File.separatorChar + str;
                            Object[] objArr = new Object[2];
                            objArr[i2] = str3;
                            objArr[1] = str2;
                            fVar.a("Looking for %s in APK %s...", objArr);
                            ZipEntry entry = zipFile.getEntry(str3);
                            if (entry != null) {
                                return new C0106a(zipFile, entry);
                            }
                            i8++;
                            i2 = 0;
                        }
                        i6 = i7;
                        i = 5;
                    } else {
                        try {
                            zipFile.close();
                            break;
                        } catch (IOException unused2) {
                        }
                    }
                }
            }
            i3++;
            i2 = 0;
        }
        return null;
    }

    @Override // org.extra.relinker.c.a
    public void a(Context context, String[] strArr, String str, File file, f fVar) throws Throwable {
        InputStream inputStream;
        FileOutputStream fileOutputStream;
        long jA;
        C0106a c0106a = null;
        closeable = null;
        Closeable closeable = null;
        try {
            C0106a c0106aA = a(context, strArr, str, fVar);
            try {
                if (c0106aA == null) {
                    throw new b(str);
                }
                int i = 0;
                while (true) {
                    int i2 = i + 1;
                    if (i < 5) {
                        fVar.a("Found %s! Extracting...", str);
                        try {
                            if (file.exists() || file.createNewFile()) {
                                try {
                                    inputStream = c0106aA.f3959a.getInputStream(c0106aA.f3960b);
                                    try {
                                        fileOutputStream = new FileOutputStream(file);
                                        try {
                                            jA = a(inputStream, fileOutputStream);
                                            fileOutputStream.getFD().sync();
                                        } catch (FileNotFoundException | IOException unused) {
                                            a(inputStream);
                                        } catch (Throwable th) {
                                            th = th;
                                            closeable = fileOutputStream;
                                            a(inputStream);
                                            a(closeable);
                                            throw th;
                                        }
                                    } catch (FileNotFoundException unused2) {
                                        fileOutputStream = null;
                                    } catch (IOException unused3) {
                                        fileOutputStream = null;
                                    } catch (Throwable th2) {
                                        th = th2;
                                    }
                                } catch (FileNotFoundException unused4) {
                                    inputStream = null;
                                    fileOutputStream = null;
                                } catch (IOException unused5) {
                                    inputStream = null;
                                    fileOutputStream = null;
                                } catch (Throwable th3) {
                                    th = th3;
                                    inputStream = null;
                                }
                                if (jA != file.length()) {
                                    a(inputStream);
                                    a(fileOutputStream);
                                } else {
                                    a(inputStream);
                                    a(fileOutputStream);
                                    file.setReadable(true, false);
                                    file.setExecutable(true, false);
                                    file.setWritable(true);
                                    try {
                                        ZipFile zipFile = c0106aA.f3959a;
                                        if (zipFile != null) {
                                            zipFile.close();
                                            return;
                                        }
                                        return;
                                    } catch (IOException unused6) {
                                        return;
                                    }
                                }
                            }
                        } catch (IOException unused7) {
                        }
                        i = i2;
                    } else {
                        fVar.a("FATAL! Couldn't extract the library from the APK!");
                        try {
                            ZipFile zipFile2 = c0106aA.f3959a;
                            if (zipFile2 != null) {
                                zipFile2.close();
                                return;
                            }
                            return;
                        } catch (IOException unused8) {
                            return;
                        }
                    }
                }
            } catch (Throwable th4) {
                th = th4;
                c0106a = c0106aA;
                if (c0106a != null) {
                    try {
                        ZipFile zipFile3 = c0106a.f3959a;
                        if (zipFile3 != null) {
                            zipFile3.close();
                        }
                    } catch (IOException unused9) {
                    }
                }
                throw th;
            }
        } catch (Throwable th5) {
            th = th5;
        }
    }

    private long a(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[4096];
        long j = 0;
        while (true) {
            int i = inputStream.read(bArr);
            if (i == -1) {
                outputStream.flush();
                return j;
            }
            outputStream.write(bArr, 0, i);
            j += (long) i;
        }
    }

    private void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }
}
