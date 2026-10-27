package com.byd.filemanager;

import android.annotation.SuppressLint;
import android.os.storage.StorageManager;
import android.os.storage.VolumeInfo;
import android.text.TextUtils;
import com.byd.filemanager.bean.FileItem;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

/* JADX INFO: compiled from: DiskManager.java */
/* JADX INFO: loaded from: classes.dex */
public class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static volatile e f3548a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f3549b = "DiskManager";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Map<Integer, com.byd.filemanager.bean.d> f3550c = new ConcurrentHashMap();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private StorageManager f3551d = (StorageManager) FileApplication.a().getSystemService("storage");

    private e() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(com.byd.filemanager.bean.d dVar) {
        int iA = dVar.a();
        com.byd.filemanager.bean.d dVar2 = this.f3550c.get(Integer.valueOf(iA));
        this.f3550c.put(Integer.valueOf(iA), dVar);
        com.byd.filemanager.service.b.c().n(iA, 1);
        if (dVar2 == null || !dVar2.b().equals(dVar.b())) {
            org.greenrobot.eventbus.c.c().k(new com.byd.music.f.b(iA));
        }
    }

    public static String e(String str) {
        return str.startsWith("/mnt/media_rw/") ? str.replace("/mnt/media_rw/", "/storage/") : str;
    }

    public static e f() {
        if (f3548a == null) {
            synchronized (e.class) {
                if (f3548a == null) {
                    f3548a = new e();
                }
            }
        }
        return f3548a;
    }

    public static String[] h(List<FileItem> list) {
        if (list == null || list.size() <= 0) {
            return new String[0];
        }
        String[] strArr = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            strArr[i] = e(list.get(i).E());
        }
        return strArr;
    }

    private byte n(VolumeInfo volumeInfo) {
        if (volumeInfo == null) {
            return (byte) 0;
        }
        String deviceType = volumeInfo.getDeviceType();
        if (TextUtils.isEmpty(volumeInfo.path) || !volumeInfo.isMountedWritable() || volumeInfo.type != 0) {
            com.byd.filemanager.k.b.c("DiskManager", "getVolume failed " + volumeInfo);
            return (byte) 0;
        }
        byte bD = (byte) d(deviceType);
        com.byd.filemanager.k.b.c("DiskManager", ((int) bD) + ",getType add volumeInfo =" + volumeInfo);
        return bD;
    }

    private Stream<com.byd.filemanager.bean.d> o(final int i) {
        try {
            List volumes = this.f3551d.getVolumes();
            int size = volumes == null ? 0 : volumes.size();
            com.byd.filemanager.k.b.c("DiskManager", "volumesSize=" + size);
            if (size == 0) {
                return null;
            }
            return volumes.stream().filter(new Predicate() { // from class: com.byd.filemanager.c
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return this.f3545a.x(i, (VolumeInfo) obj);
                }
            }).map(new Function() { // from class: com.byd.filemanager.a
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return this.f3521a.z((VolumeInfo) obj);
                }
            });
        } catch (Throwable th) {
            com.byd.filemanager.k.b.h("getVolumes failed null", th);
            return null;
        }
    }

    public static boolean v(String str, String str2) {
        if (str == null || str2 == null) {
            return false;
        }
        if (str.startsWith("/mnt/media_rw/")) {
            str = str.replace("/mnt/media_rw/", "/storage/");
        }
        if (str2.startsWith("/mnt/media_rw/")) {
            str2 = str2.replace("/mnt/media_rw/", "/storage/");
        }
        return str.startsWith(str2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: w, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ boolean x(int i, VolumeInfo volumeInfo) {
        return (i & n(volumeInfo)) > 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: y, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ com.byd.filemanager.bean.d z(VolumeInfo volumeInfo) {
        return new com.byd.filemanager.bean.d(n(volumeInfo), volumeInfo.path, volumeInfo.fsUuid);
    }

    public void B(int i, boolean z) {
        com.byd.filemanager.k.b.c("DiskManager", "removeVolume index = " + i);
        if (this.f3550c.containsKey(Integer.valueOf(i))) {
            com.byd.filemanager.bean.d dVar = this.f3550c.get(Integer.valueOf(i));
            com.byd.filemanager.service.b.c().n(i, 0);
            String strC = dVar != null ? dVar.c() : null;
            this.f3550c.remove(Integer.valueOf(i));
            com.byd.music.f.b bVar = new com.byd.music.f.b(i);
            bVar.g(z);
            bVar.f(true);
            if (!TextUtils.isEmpty(strC)) {
                bVar.h(strC);
            }
            org.greenrobot.eventbus.c.c().k(bVar);
        }
    }

    public int C(String str) {
        if (!TextUtils.isEmpty(str)) {
            for (int i = 0; i < 3; i++) {
                com.byd.filemanager.bean.d dVar = this.f3550c.get(Integer.valueOf(i));
                if (dVar != null && !TextUtils.isEmpty(dVar.c()) && str.startsWith(dVar.c())) {
                    return i;
                }
            }
        }
        return 0;
    }

    public void b() {
        Stream<com.byd.filemanager.bean.d> streamO = o(3);
        if (streamO == null) {
            return;
        }
        streamO.forEach(new b(this));
    }

    public void c(int i, String str) {
        if (TextUtils.isEmpty(str)) {
            Stream<com.byd.filemanager.bean.d> streamO = o(i);
            if (streamO == null) {
                return;
            }
            streamO.findFirst().ifPresent(new b(this));
            return;
        }
        String str2 = "/storage/" + str;
        a(new com.byd.filemanager.bean.d(i, str2, str));
        com.byd.filemanager.k.b.c("DiskManager", i + ",addExternalBoard uuid =" + str + ",path=" + str2);
    }

    public int d(String str) {
        if (str == null) {
            return 0;
        }
        str.hashCode();
        if (str.equals("sdcard")) {
            return 1;
        }
        return !str.equals("usbotg") ? 0 : 2;
    }

    public String g() {
        return "/storage/emulated/0";
    }

    @SuppressLint({"NewApi"})
    public int i(String str) {
        return j(Paths.get(str, new String[0]));
    }

    public int j(Path path) {
        if (u(path, 0)) {
            return 0;
        }
        return u(path, 1) ? 1 : 2;
    }

    public String k(int i) {
        return l(i);
    }

    public String l(int i) {
        com.byd.filemanager.bean.d dVar = this.f3550c.get(Integer.valueOf(i));
        if (dVar == null || !dVar.d()) {
            com.byd.filemanager.k.b.c("DiskManager", "getRootPath path is null , index:" + i);
            return null;
        }
        String strC = dVar.c();
        com.byd.filemanager.k.b.c("DiskManager", "getRootPath path=" + strC + " , index:" + i);
        return strC;
    }

    public String[] m() {
        ArrayList arrayList = new ArrayList();
        Iterator<Integer> it = this.f3550c.keySet().iterator();
        while (it.hasNext()) {
            com.byd.filemanager.bean.d dVar = this.f3550c.get(Integer.valueOf(it.next().intValue()));
            if (dVar.d()) {
                arrayList.add(dVar.c());
            }
        }
        return (String[]) arrayList.toArray(new String[0]);
    }

    public boolean p() {
        return s(1);
    }

    public boolean q() {
        return s(2);
    }

    public void r() {
        this.f3550c.put(0, new com.byd.filemanager.bean.d(0, "/storage/emulated/0", null));
    }

    public boolean s(int i) {
        com.byd.filemanager.bean.d dVar = this.f3550c.get(Integer.valueOf(i));
        return dVar != null && dVar.d();
    }

    public boolean t(File file, int i) {
        if (!com.byd.filemanager.k.d.g(file)) {
            return false;
        }
        String path = file.getPath();
        com.byd.filemanager.bean.d dVar = this.f3550c.get(Integer.valueOf(i));
        if (dVar == null || !dVar.d() || TextUtils.isEmpty(dVar.c())) {
            return false;
        }
        return path.startsWith(dVar.c());
    }

    @SuppressLint({"NewApi"})
    public boolean u(Path path, int i) {
        com.byd.filemanager.bean.d dVar;
        if (!Files.exists(path, new LinkOption[0]) || (dVar = this.f3550c.get(Integer.valueOf(i))) == null || !dVar.d() || TextUtils.isEmpty(dVar.c())) {
            return false;
        }
        return path.startsWith(Paths.get(dVar.c(), new String[0]));
    }
}
