package com.byd.logtools.tools;

import android.util.Log;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import okhttp3.HttpUrl;

/* JADX INFO: compiled from: FileZipHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static boolean f3243a = true;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static int f3244b = 1000;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static int f3245c = 1001;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static int f3246d = 1002;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    static Comparator<File> f3247e = new C0061g();

    /* JADX INFO: compiled from: FileZipHelper.java */
    class a implements Comparator<File> {
        a() {
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(File file, File file2) {
            return file.getName().compareTo(file2.getName());
        }
    }

    /* JADX INFO: compiled from: FileZipHelper.java */
    class b implements FileFilter {
        b() {
        }

        @Override // java.io.FileFilter
        public boolean accept(File file) {
            return file.isFile() && file.getPath().endsWith(".gz");
        }
    }

    /* JADX INFO: compiled from: FileZipHelper.java */
    class c implements FileFilter {
        c() {
        }

        @Override // java.io.FileFilter
        public boolean accept(File file) {
            return file.isFile() && file.getName().startsWith("tuner_data") && file.getName().endsWith(".pcm");
        }
    }

    /* JADX INFO: compiled from: FileZipHelper.java */
    class d implements FileFilter {
        d() {
        }

        @Override // java.io.FileFilter
        public boolean accept(File file) {
            return file.isFile() && file.getName().startsWith("netdump") && file.getName().contains(".pcap");
        }
    }

    /* JADX INFO: compiled from: FileZipHelper.java */
    class e implements FileFilter {
        e() {
        }

        @Override // java.io.FileFilter
        public boolean accept(File file) {
            return file.isFile() && file.getName().endsWith(".pcap");
        }
    }

    /* JADX INFO: compiled from: FileZipHelper.java */
    class f implements FileFilter {
        f() {
        }

        @Override // java.io.FileFilter
        public boolean accept(File file) {
            return file.isDirectory();
        }
    }

    /* JADX INFO: renamed from: com.byd.logtools.tools.g$g, reason: collision with other inner class name */
    /* JADX INFO: compiled from: FileZipHelper.java */
    class C0061g implements Comparator<File> {
        C0061g() {
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(File file, File file2) {
            if (g.j(file.getName()) == 0) {
                BydLogHelper.logW("FileZipHelper", "getIndexfromLogFileName(file.getName()), file.getName() = " + file.getName() + " newFile.getName() = " + file2.getName());
                return 1;
            }
            if (g.j(file2.getName()) != 0) {
                if (file.getName().compareTo(file2.getName()) < 0) {
                    return 1;
                }
                return file.getName().compareTo(file2.getName()) == 0 ? 0 : -1;
            }
            BydLogHelper.logW("FileZipHelper", "getIndexfromLogFileName(newFile.getName()), file.getName() = " + file.getName() + " newFile.getName() = " + file2.getName());
            return -1;
        }
    }

    private static void b(List<File> list) {
        if (list == null || list.size() <= 0) {
            return;
        }
        Collections.sort(list, new a());
    }

    public static void c(String str, File file, ArrayList<String> arrayList) throws Exception {
        FileDescriptor fileDescriptor;
        FileOutputStream fileOutputStream;
        ZipOutputStream zipOutputStream;
        if (!file.getParentFile().exists()) {
            Log.e("FileZipHelper", "compressArrayListFile destFile.getParentFile() mkdirs is " + file.getParentFile().mkdirs());
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
            for (String str2 : arrayList) {
                if (!str2.contains("logcatd_test_trace.log") && !str2.contains("lost+found") && !str2.contains("vt_trace.log")) {
                    BydLogHelper.logD("compressArrayListFile compress path = " + str2);
                    if (!str2.equals(HttpUrl.FRAGMENT_ENCODE_SET)) {
                        File file2 = new File(str2);
                        BydLogHelper.logD("compressArrayListFile compress path, file.exists() = " + file2.exists());
                        if (file2.exists()) {
                            if (file2.isDirectory()) {
                                p(zipOutputStream, file2, k(file2), f3245c);
                            } else if (file2.isFile()) {
                                p(zipOutputStream, file2, k(file2), f3245c);
                            }
                        }
                    }
                }
                BydLogHelper.logD("compressArrayListFile, filtration path = " + str2);
            }
            zipOutputStream.finish();
            zipOutputStream.flush();
            fd.sync();
            com.byd.logtools.tools.f.a(zipOutputStream);
            com.byd.logtools.tools.f.a(fileOutputStream);
        } catch (Throwable th3) {
            th = th3;
            fileDescriptor = fd;
            zipOutputStream2 = zipOutputStream;
            if (zipOutputStream2 != null) {
                zipOutputStream2.finish();
                zipOutputStream2.flush();
            }
            if (fileOutputStream != null) {
                fileDescriptor.sync();
            }
            com.byd.logtools.tools.f.a(zipOutputStream2);
            com.byd.logtools.tools.f.a(fileOutputStream);
            throw th;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x0102 A[Catch: all -> 0x0266, TryCatch #0 {all -> 0x0266, blocks: (B:8:0x0040, B:10:0x0048, B:12:0x004e, B:13:0x0066, B:15:0x006e, B:17:0x0076, B:20:0x0080, B:24:0x00a3, B:26:0x00ae, B:29:0x00cf, B:31:0x00d5, B:36:0x00ff, B:35:0x00f2, B:88:0x0251, B:37:0x0102, B:41:0x010e, B:43:0x0119, B:46:0x013a, B:48:0x0140, B:52:0x016a, B:51:0x015d, B:54:0x0170, B:57:0x0179, B:59:0x0184, B:61:0x019b, B:63:0x019e, B:65:0x01a2, B:66:0x01ad, B:68:0x01b3, B:70:0x01be, B:72:0x01d5, B:74:0x01d8, B:76:0x01dc, B:77:0x01e8, B:79:0x0202, B:81:0x0225, B:83:0x022b, B:84:0x0231, B:86:0x0237, B:87:0x023d, B:89:0x0256), top: B:103:0x0040 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void d(int r17, java.io.File r18, java.lang.String... r19) throws java.lang.Exception {
        /*
            Method dump skipped, instruction units count: 647
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.logtools.tools.g.d(int, java.io.File, java.lang.String[]):void");
    }

    public static void e(File file, List<File> list) {
        if (file.exists()) {
            if (file.isDirectory()) {
                File[] fileArrListFiles = file.listFiles();
                int length = fileArrListFiles == null ? 0 : fileArrListFiles.length;
                for (int i = 0; i < length; i++) {
                    e(fileArrListFiles[i], list);
                }
            } else {
                list.add(file);
            }
            b(list);
        }
    }

    public static File[] f(File file) {
        if (file != null && file.exists() && file.isDirectory()) {
            return file.listFiles(new e());
        }
        return null;
    }

    private static ArrayList<File> g(File file) {
        ArrayList<File> arrayList = new ArrayList<>();
        File[] fileArrListFiles = file.listFiles(new f());
        if (fileArrListFiles == null) {
            Log.e("FileZipHelper", "getComparatorFileList: There is no log  ");
            return arrayList;
        }
        for (File file2 : fileArrListFiles) {
            if (file2.getName().contains("cycle_")) {
                Log.e("FileZipHelper", "getComparatorFileList: add file to list =  " + file2.getName());
                arrayList.add(file2);
            }
        }
        if (arrayList.size() > 1) {
            Collections.sort(arrayList, f3247e);
        }
        return arrayList;
    }

    public static File[] h(String str) {
        File file = new File(str);
        if (file.exists() && file.isDirectory()) {
            return file.listFiles(new c());
        }
        return null;
    }

    private static File[] i(String str) {
        File file = new File(str);
        if (file.exists() && file.isDirectory()) {
            return file.listFiles(new b());
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int j(String str) {
        if (str == null) {
            return 0;
        }
        try {
            String[] strArrSplit = str.split("_");
            if (strArrSplit.length < 2 || !m(strArrSplit[1])) {
                return 0;
            }
            int i = Integer.parseInt(strArrSplit[1]);
            BydLogHelper.logD("FileZipHelper", "getIndexfromLogFileName: d[1] = " + strArrSplit[1] + ", index = " + i);
            return i;
        } catch (Exception unused) {
            return 0;
        }
    }

    public static String k(File file) {
        String parent = file.getParent();
        StringBuilder sb = new StringBuilder();
        BydLogHelper.logD("getParentPath file.getParent() = " + parent);
        if (parent == null || !parent.contains("/")) {
            sb.append(parent);
        } else {
            String[] strArrSplit = parent.split("/");
            int length = strArrSplit.length - 1;
            BydLogHelper.logD("getParentPath count = " + length);
            if (length > 2) {
                sb.append("/");
                sb.append(strArrSplit[length - 1]);
                sb.append("/");
                sb.append(strArrSplit[length]);
            } else {
                sb.append(parent);
            }
        }
        sb.append("/");
        BydLogHelper.logD("getParentPath file.getParent() = " + parent + " new path = " + sb.toString());
        return sb.toString();
    }

    public static File[] l(File file) {
        if (file != null && file.exists() && file.isDirectory()) {
            return file.listFiles(new d());
        }
        return null;
    }

    private static boolean m(String str) {
        int length = str.length();
        do {
            length--;
            if (length < 0) {
                return true;
            }
        } while (Character.isDigit(str.charAt(length)));
        return false;
    }

    public static String n(File file, File file2, String str) {
        if (!file2.exists()) {
            Log.e("FileZipHelper", "destBasePath.mkdirs() mkdirs is " + file2.mkdirs());
        }
        return o(new File(file2.getPath() + "/" + str), file);
    }

    /* JADX WARN: Removed duplicated region for block: B:49:0x006a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String o(java.io.File r6, java.io.File r7) {
        /*
            java.io.File r0 = new java.io.File
            java.lang.String r1 = r7.getParent()
            r0.<init>(r1)
            boolean r0 = r0.exists()
            if (r0 != 0) goto L1b
            java.io.File r0 = new java.io.File
            java.lang.String r1 = r7.getParent()
            r0.<init>(r1)
            r0.mkdirs()
        L1b:
            r0 = 0
            java.util.zip.ZipOutputStream r1 = new java.util.zip.ZipOutputStream     // Catch: java.lang.Throwable -> L56 java.io.IOException -> L58
            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L56 java.io.IOException -> L58
            r2.<init>(r6)     // Catch: java.lang.Throwable -> L56 java.io.IOException -> L58
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L56 java.io.IOException -> L58
            boolean r2 = r7.isFile()     // Catch: java.io.IOException -> L54 java.lang.Throwable -> L68
            java.lang.String r3 = ""
            if (r2 == 0) goto L34
            int r2 = com.byd.logtools.tools.g.f3246d     // Catch: java.io.IOException -> L54 java.lang.Throwable -> L68
            p(r1, r7, r3, r2)     // Catch: java.io.IOException -> L54 java.lang.Throwable -> L68
            goto L48
        L34:
            java.io.File[] r7 = r7.listFiles()     // Catch: java.io.IOException -> L54 java.lang.Throwable -> L68
            if (r7 == 0) goto L48
            r2 = 0
        L3b:
            int r4 = r7.length     // Catch: java.io.IOException -> L54 java.lang.Throwable -> L68
            if (r2 >= r4) goto L48
            r4 = r7[r2]     // Catch: java.io.IOException -> L54 java.lang.Throwable -> L68
            int r5 = com.byd.logtools.tools.g.f3246d     // Catch: java.io.IOException -> L54 java.lang.Throwable -> L68
            p(r1, r4, r3, r5)     // Catch: java.io.IOException -> L54 java.lang.Throwable -> L68
            int r2 = r2 + 1
            goto L3b
        L48:
            r6.getName()     // Catch: java.io.IOException -> L54 java.lang.Throwable -> L68
            r1.close()     // Catch: java.io.IOException -> L4f
            goto L53
        L4f:
            r6 = move-exception
            r6.printStackTrace()
        L53:
            return r0
        L54:
            r6 = move-exception
            goto L5a
        L56:
            r1 = r0
            goto L68
        L58:
            r6 = move-exception
            r1 = r0
        L5a:
            r6.printStackTrace()     // Catch: java.lang.Throwable -> L68
            if (r1 == 0) goto L67
            r1.close()     // Catch: java.io.IOException -> L63
            goto L67
        L63:
            r6 = move-exception
            r6.printStackTrace()
        L67:
            return r0
        L68:
            if (r1 == 0) goto L72
            r1.close()     // Catch: java.io.IOException -> L6e
            goto L72
        L6e:
            r6 = move-exception
            r6.printStackTrace()
        L72:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.logtools.tools.g.o(java.io.File, java.io.File):java.lang.String");
    }

    private static void p(ZipOutputStream zipOutputStream, File file, String str, int i) throws Throwable {
        FileInputStream fileInputStream = null;
        try {
            try {
                try {
                    int i2 = 0;
                    if (!file.isDirectory()) {
                        if (!file.getName().endsWith(".db") && !file.getName().endsWith(".db-shm") && !file.getName().endsWith(".db-wal")) {
                            byte[] bArr = new byte[4096];
                            FileInputStream fileInputStream2 = new FileInputStream(file);
                            try {
                                zipOutputStream.putNextEntry(new ZipEntry(str + file.getName()));
                                while (true) {
                                    int i3 = fileInputStream2.read(bArr);
                                    if (i3 != -1) {
                                        if (!f3243a && i == f3244b) {
                                            BydLogHelper.logE("zipFileOrDirectory in.read isUDiskMount = " + f3243a);
                                            break;
                                        }
                                        zipOutputStream.write(bArr, 0, i3);
                                    } else {
                                        break;
                                    }
                                }
                                zipOutputStream.closeEntry();
                                fileInputStream = fileInputStream2;
                            } catch (IOException e2) {
                                e = e2;
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
                                    } catch (IOException e3) {
                                        e3.printStackTrace();
                                    }
                                }
                                throw th;
                            }
                        }
                        Log.i("FileZipHelper", "zipFileOrDirectory fileOrDirectory.getName() = " + file.getName());
                        zipOutputStream.closeEntry();
                        return;
                    }
                    BydLogHelper.logD("compress path, fileOrDirectory.exists() = " + file.exists() + ", fileOrDirectory.getPath()= " + file.getPath());
                    File[] fileArrListFiles = file.listFiles();
                    if (fileArrListFiles != null) {
                        while (true) {
                            if (i2 < fileArrListFiles.length) {
                                if (!f3243a && i == f3244b) {
                                    BydLogHelper.logE("zipFileOrDirectory fileOrDirectory.listFiles() isUDiskMount = " + f3243a);
                                    break;
                                }
                                p(zipOutputStream, fileArrListFiles[i2], str + file.getName() + "/", i);
                                i2++;
                            } else {
                                break;
                            }
                        }
                    }
                } catch (IOException e4) {
                    e = e4;
                }
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            } catch (IOException e5) {
                e5.printStackTrace();
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }
}
