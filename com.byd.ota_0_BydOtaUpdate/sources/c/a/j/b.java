package c.a.j;

import android.os.storage.VolumeInfo;
import c.a.j.f.f;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: compiled from: FileManager.java */
/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final List<a> f2812a = new CopyOnWriteArrayList();

    /* JADX INFO: compiled from: FileManager.java */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public File f2813a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public VolumeInfo f2814b;
    }

    public static boolean a(File file, String str) throws IOException {
        File file2 = new File(str);
        try {
            if (!file2.exists()) {
                c.a.k.b.c("commonFileDir not exit ! creat it...");
                file2.mkdirs();
                file2.setExecutable(true);
                file2.setReadable(true);
                file2.setWritable(true);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            c.a.k.b.b("doCopyUpdateAllFile fail,e=" + e2.getMessage());
        }
        try {
            c.a.k.b.c("copy start ...");
            boolean zA = f.a(file.getPath(), file2.getPath());
            c.a.k.b.c("copy finish end ...result =" + zA);
            return zA;
        } catch (Exception e3) {
            e3.printStackTrace();
            return false;
        }
    }

    public static void b(String str) {
        File file = new File(str);
        if (file.isFile()) {
            file.delete();
        } else if (file.isDirectory()) {
            f.c(file);
        }
    }

    public static void d(String str, String str2) throws IOException {
        f.h(new File(str), str2);
        c.a.k.b.c("解压{}成功");
    }

    public static /* synthetic */ boolean j(File file, String str) {
        return str.endsWith("dx.update") || str.endsWith("adbFlag.ini") || str.endsWith("USBFlag.ini") || str.endsWith("AuthorizedFlag.ini");
    }

    public boolean c(VolumeInfo volumeInfo) {
        File file = new File(volumeInfo.path);
        c.a.k.b.c("doScanHaveUpdateFile");
        File[] fileArrListFiles = file.listFiles(new FilenameFilter() { // from class: c.a.j.a
            @Override // java.io.FilenameFilter
            public final boolean accept(File file2, String str) {
                return b.j(file2, str);
            }
        });
        boolean z = fileArrListFiles != null && fileArrListFiles.length >= 1;
        c.a.k.b.c("doScanHaveUpdateFile hasUpdateFile =" + z);
        if (z) {
            for (File file2 : fileArrListFiles) {
                a aVar = new a();
                aVar.f2813a = file2;
                aVar.f2814b = volumeInfo;
                this.f2812a.add(aVar);
            }
        }
        return z;
    }

    public List<a> e() {
        return this.f2812a;
    }

    public File f() {
        for (a aVar : this.f2812a) {
            if (aVar.f2813a.getName().equals("adbFlag.ini")) {
                return aVar.f2813a;
            }
        }
        return null;
    }

    public File g() {
        for (a aVar : this.f2812a) {
            if (aVar.f2813a.getName().equals("AuthorizedFlag.ini")) {
                return aVar.f2813a;
            }
        }
        return null;
    }

    public File h() {
        for (a aVar : this.f2812a) {
            if (aVar.f2813a.getName().equals("USBFlag.ini")) {
                return aVar.f2813a;
            }
        }
        return null;
    }

    public boolean i() {
        Iterator<a> it = this.f2812a.iterator();
        while (it.hasNext()) {
            if (it.next().f2813a.getName().contains("dx.update")) {
                return true;
            }
        }
        return false;
    }

    public boolean k(VolumeInfo volumeInfo) {
        boolean z = false;
        for (a aVar : this.f2812a) {
            if (aVar.f2814b.equals(volumeInfo) && this.f2812a.remove(aVar)) {
                z = true;
            }
        }
        return z;
    }
}
