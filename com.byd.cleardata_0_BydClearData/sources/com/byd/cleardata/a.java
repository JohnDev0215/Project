package com.byd.cleardata;

import android.annotation.SuppressLint;
import android.os.SystemProperties;
import android.os.storage.StorageManager;
import android.os.storage.VolumeInfo;
import android.text.TextUtils;
import com.byd.cleardata.d.d;
import com.byd.cleardata.d.h;
import com.byd.cleardata.i.e;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static volatile a f2568a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Map<Integer, h> f2569b = new HashMap();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private StorageManager f2570c = (StorageManager) FileApplication.a().getSystemService("storage");

    private a() {
    }

    public static String e(String str) {
        return str.startsWith("/mnt/media_rw/") ? str.replace("/mnt/media_rw/", "/storage/") : str;
    }

    public static a f() {
        if (f2568a == null) {
            synchronized (a.class) {
                if (f2568a == null) {
                    f2568a = new a();
                }
            }
        }
        return f2568a;
    }

    public static String[] g(List<d> list) {
        if (list == null || list.size() <= 0) {
            return new String[0];
        }
        String[] strArr = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            strArr[i] = e(list.get(i).E());
        }
        return strArr;
    }

    public static boolean s(String str, String str2) {
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

    public void a(VolumeInfo volumeInfo) {
        com.byd.cleardata.i.c.b("addDisk");
        h hVarF = h.f(volumeInfo);
        if (hVarF != null) {
            h hVar = this.f2569b.get(Integer.valueOf(hVarF.a()));
            this.f2569b.put(Integer.valueOf(hVarF.a()), hVarF);
            if (hVar == null || !hVar.c().equals(hVarF.c())) {
                org.greenrobot.eventbus.c.c().k(new com.byd.cleardata.e.b(hVarF.a()));
            }
        }
    }

    public void b() {
        com.byd.cleardata.i.c.b("addExternal");
        try {
            List volumes = this.f2570c.getVolumes();
            if (volumes != null) {
                com.byd.cleardata.i.c.b("volumes size = " + volumes.size());
                Iterator it = volumes.iterator();
                while (it.hasNext()) {
                    a((VolumeInfo) it.next());
                }
            }
        } catch (Throwable th) {
            com.byd.cleardata.i.c.h("initVolume failed", th);
        }
    }

    public void c() {
        String str = SystemProperties.get("sys.byd.mSdcardUuid");
        if (!TextUtils.isEmpty(str)) {
            h hVar = new h(1, "/storage/" + str, "/mnt/media_rw/" + str, str);
            this.f2569b.put(1, hVar);
            org.greenrobot.eventbus.c.c().k(new com.byd.cleardata.e.b(hVar.a()));
            return;
        }
        try {
            List<VolumeInfo> volumes = this.f2570c.getVolumes();
            if (volumes != null) {
                for (VolumeInfo volumeInfo : volumes) {
                    String deviceType = volumeInfo.getDeviceType();
                    if ("sdcard".equals(deviceType)) {
                        com.byd.cleardata.i.c.b("addSdcard = " + deviceType);
                        a(volumeInfo);
                    }
                }
            }
        } catch (Throwable th) {
            com.byd.cleardata.i.c.h("addSdcard failed", th);
        }
    }

    public void d() {
        String str = SystemProperties.get("sys.byd.mUsbotgUuid");
        com.byd.cleardata.i.c.b("uuid = " + str);
        if (!TextUtils.isEmpty(str)) {
            h hVar = new h(2, "/storage/" + str, "/mnt/media_rw/" + str, str);
            this.f2569b.put(2, hVar);
            org.greenrobot.eventbus.c.c().k(new com.byd.cleardata.e.b(hVar.a()));
            return;
        }
        try {
            List<VolumeInfo> volumes = this.f2570c.getVolumes();
            if (volumes != null) {
                for (VolumeInfo volumeInfo : volumes) {
                    String deviceType = volumeInfo.getDeviceType();
                    if ("usbotg".equals(deviceType)) {
                        com.byd.cleardata.i.c.b("addUsb = " + deviceType);
                        a(volumeInfo);
                    }
                }
            }
        } catch (Throwable th) {
            com.byd.cleardata.i.c.h("addUsb failed", th);
        }
    }

    @SuppressLint({"NewApi"})
    public int h(String str) {
        return i(Paths.get(str, new String[0]));
    }

    public int i(Path path) {
        if (r(path, 0)) {
            return 0;
        }
        return r(path, 1) ? 1 : 2;
    }

    public String j(int i) {
        h hVar = this.f2569b.get(Integer.valueOf(i));
        if (hVar == null || !hVar.e()) {
            return null;
        }
        return hVar.b();
    }

    public String k(int i) {
        h hVar = this.f2569b.get(Integer.valueOf(i));
        if (hVar == null || !hVar.e()) {
            return null;
        }
        return hVar.d();
    }

    public String[] l() {
        ArrayList arrayList = new ArrayList();
        Iterator<Integer> it = this.f2569b.keySet().iterator();
        while (it.hasNext()) {
            h hVar = this.f2569b.get(Integer.valueOf(it.next().intValue()));
            if (hVar.e()) {
                arrayList.add(hVar.d());
            }
        }
        return (String[]) arrayList.toArray(new String[0]);
    }

    public boolean m() {
        return p(1);
    }

    public boolean n() {
        return p(2);
    }

    public void o() {
        this.f2569b.put(0, new h(0, "/storage/emulated/0", "/storage/emulated/0", null));
        b();
    }

    public boolean p(int i) {
        h hVar = this.f2569b.get(Integer.valueOf(i));
        return hVar != null && hVar.e();
    }

    public boolean q(File file, int i) {
        if (!e.h(file)) {
            return false;
        }
        String path = file.getPath();
        h hVar = this.f2569b.get(Integer.valueOf(i));
        if (hVar == null || !hVar.e()) {
            return false;
        }
        return path.startsWith(hVar.d()) || path.startsWith(hVar.b());
    }

    @SuppressLint({"NewApi"})
    public boolean r(Path path, int i) {
        h hVar;
        if (Files.exists(path, new LinkOption[0]) && (hVar = this.f2569b.get(Integer.valueOf(i))) != null && hVar.e()) {
            return path.startsWith(Paths.get(hVar.d(), new String[0]));
        }
        return false;
    }

    public void t(int i) {
        com.byd.cleardata.i.c.b("removeVolume index = " + i);
        this.f2569b.remove(Integer.valueOf(i));
        org.greenrobot.eventbus.c.c().k(new com.byd.cleardata.e.b(i));
    }

    public int u(String str) {
        if (!TextUtils.isEmpty(str)) {
            for (int i = 0; i < 3; i++) {
                h hVar = this.f2569b.get(Integer.valueOf(i));
                if (hVar != null && str.startsWith(hVar.d())) {
                    return i;
                }
            }
        }
        return 0;
    }
}
