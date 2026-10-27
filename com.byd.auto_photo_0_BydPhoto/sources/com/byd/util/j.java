package com.byd.util;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.os.storage.StorageVolume;
import android.text.TextUtils;
import com.byd.auto_photo.R;
import java.io.File;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static volatile j f2962a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Context f2963b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final StorageManager f2964c;
    private List<a> d;

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f2965a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        String f2966b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        String f2967c;
        String d;
        String e;
        String f;
        boolean g;
        boolean h;
        int i;

        public a(int i, String str, String str2, String str3, boolean z, String str4) {
            this.f2965a = i;
            this.f2966b = str;
            this.d = str2;
            this.f = str3;
            this.g = z;
            this.e = str4;
            int iLastIndexOf = str2.lastIndexOf(File.separator);
            if (iLastIndexOf > 0 && iLastIndexOf < str2.length()) {
                this.f2967c = str2.substring(iLastIndexOf + 1).toLowerCase();
            }
            if (str3 == null) {
                this.f = this.f2967c;
            }
        }

        public int a() {
            return this.f2965a;
        }

        public String b() {
            return this.d;
        }

        public String c() {
            if (this.i <= 0) {
                return this.e;
            }
            return this.e + " " + this.i;
        }

        public String d() {
            return this.f2967c;
        }

        public boolean e() {
            return this.g;
        }

        public String toString() {
            return "VolumeInfo{id=" + this.f2965a + ", desc='" + this.f2966b + "', name='" + this.f2967c + "', path='" + this.d + "', uuid='" + this.f + "', isSD=" + this.g + '}';
        }
    }

    private j(Context context) {
        Context applicationContext = context.getApplicationContext();
        this.f2963b = applicationContext;
        this.f2964c = (StorageManager) applicationContext.getSystemService("storage");
    }

    private int b(boolean z) {
        return z ? 1835401217 : -129368063;
    }

    public static j e(Context context) {
        synchronized (j.class) {
            if (f2962a == null) {
                f2962a = new j(context);
            }
        }
        return f2962a;
    }

    private int f(boolean z) {
        int i = 0;
        if (this.d.size() == 0) {
            return 0;
        }
        Iterator<a> it = this.d.iterator();
        while (it.hasNext()) {
            if (it.next().g == z) {
                i++;
            }
        }
        return i;
    }

    private int g(StorageVolume storageVolume, int i) {
        if (Build.VERSION.SDK_INT < 24) {
            return i;
        }
        try {
            Class<?> cls = storageVolume.getClass();
            Method method = cls.getMethod("dump", new Class[0]);
            method.setAccessible(true);
            Method method2 = cls.getMethod("getStorageId", new Class[0]);
            method2.setAccessible(true);
            i = ((Integer) method2.invoke(storageVolume, new Object[0])).intValue();
            w.c("-->storageId:" + i + " dump:" + ((String) method.invoke(storageVolume, new Object[0])));
            return i;
        } catch (Exception e) {
            e.printStackTrace();
            return i;
        }
    }

    private int h(String str, int i) {
        if (Build.VERSION.SDK_INT < 24) {
            return i;
        }
        List<StorageVolume> storageVolumes = this.f2964c.getStorageVolumes();
        if (storageVolumes.size() <= 0) {
            return i;
        }
        try {
            Class<?> cls = Class.forName("android.os.storage.StorageVolume");
            Method method = cls.getMethod("getPath", new Class[0]);
            method.setAccessible(true);
            for (int i2 = 0; i2 < storageVolumes.size(); i2++) {
                StorageVolume storageVolume = storageVolumes.get(i2);
                if (((String) method.invoke(storageVolume, new Object[0])).equalsIgnoreCase(str)) {
                    Method method2 = cls.getMethod("dump", new Class[0]);
                    method2.setAccessible(true);
                    Method method3 = cls.getMethod("getStorageId", new Class[0]);
                    method3.setAccessible(true);
                    i = ((Integer) method3.invoke(storageVolume, new Object[0])).intValue();
                    w.c("-->storageId:" + i + " dump:" + ((String) method2.invoke(storageVolume, new Object[0])));
                    return i;
                }
            }
            return i;
        } catch (Exception e) {
            e.printStackTrace();
            return i;
        }
    }

    private boolean k(String str) {
        StringBuilder sb;
        String str2;
        if (Environment.getExternalStorageDirectory().getPath().equals(str)) {
            sb = new StringBuilder();
            sb.append(str);
            str2 = " => is not external sd or udisk path";
        } else {
            if (!str.toLowerCase().startsWith("/storage/ffff-fff")) {
                return true;
            }
            sb = new StringBuilder();
            sb.append(str);
            str2 = " => is not external storage path";
        }
        sb.append(str2);
        w.c(sb.toString());
        return false;
    }

    private String m(boolean z) {
        return this.f2963b.getResources().getString(z ? R.string.sdcard : R.string.udisk);
    }

    public void a(String str, boolean z) {
        if (this.d == null) {
            this.d = new LinkedList();
        }
        a aVar = new a(Build.VERSION.SDK_INT < 28 ? h(str, b(!z)) : 0, null, str, null, !z, m(!z));
        int iF = f(!z);
        if (iF > 0) {
            aVar.i = iF;
        }
        this.d.add(aVar);
    }

    public List<String> c() {
        List<StorageVolume> storageVolumes = this.f2964c.getStorageVolumes();
        LinkedList linkedList = new LinkedList();
        for (StorageVolume storageVolume : storageVolumes) {
            String uuid = storageVolume.getUuid();
            if (uuid != null) {
                String string = null;
                if (Build.VERSION.SDK_INT >= 30 && storageVolume.getDirectory() != null) {
                    string = storageVolume.getDirectory().toString();
                }
                if (TextUtils.isEmpty(string)) {
                    string = "/storage/" + uuid;
                }
                if (k(string)) {
                    linkedList.add(string);
                    w.c("getExternalPathIgMountedState : " + string);
                }
            }
        }
        return linkedList;
    }

    public int d(String str) {
        if (q() == 0) {
            return 0;
        }
        for (a aVar : this.d) {
            if (str.startsWith(aVar.d)) {
                return aVar.g ? 1 : 2;
            }
        }
        return 0;
    }

    public a i(String str) {
        List<a> list = this.d;
        if (list != null && list.size() != 0) {
            for (a aVar : this.d) {
                if (str.equals(aVar.d)) {
                    return aVar;
                }
            }
        }
        return null;
    }

    public List<a> j() {
        return this.d;
    }

    public boolean l() {
        if (q() == 0) {
            return false;
        }
        Iterator<a> it = this.d.iterator();
        while (it.hasNext()) {
            if (it.next().h) {
                return true;
            }
        }
        return false;
    }

    public void n(String str) {
        List<a> list = this.d;
        if (list == null || list.size() == 0) {
            return;
        }
        for (int i = 0; i < this.d.size(); i++) {
            a aVar = this.d.get(i);
            if (str.startsWith(aVar.d)) {
                this.d.remove(aVar);
                return;
            }
        }
    }

    public String o(String str) {
        if (q() == 0) {
            return str;
        }
        for (a aVar : this.d) {
            if (str.startsWith(aVar.d)) {
                return str.replace(aVar.d, aVar.c());
            }
        }
        return str;
    }

    public void p(String str, boolean z) {
        if (q() == 0) {
            return;
        }
        for (a aVar : this.d) {
            if (str.equals(aVar.d)) {
                aVar.h = z;
                return;
            }
        }
    }

    public int q() {
        List<a> list = this.d;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public void r() {
        String str;
        StringBuilder sb;
        Object obj;
        List<StorageVolume> storageVolumes = this.f2964c.getStorageVolumes();
        w.c("storage volume size : " + storageVolumes.size());
        String strH = g.c(false) ? g.h(false) : null;
        List<a> list = this.d;
        if (list == null) {
            this.d = new LinkedList();
        } else {
            list.clear();
        }
        for (int i = 0; i < storageVolumes.size(); i++) {
            StorageVolume storageVolume = storageVolumes.get(i);
            String uuid = storageVolume.getUuid();
            if (uuid == null) {
                str = "uuid is null => ";
                obj = storageVolume;
                sb = new StringBuilder();
            } else if ("mounted".equals(storageVolume.getState())) {
                int i2 = Build.VERSION.SDK_INT;
                String string = (i2 < 30 || storageVolume.getDirectory() == null) ? null : storageVolume.getDirectory().toString();
                if (TextUtils.isEmpty(string)) {
                    string = "/storage/" + uuid;
                }
                String str2 = string;
                if (k(str2)) {
                    boolean zEquals = uuid.equals(strH);
                    a aVar = new a(i2 < 28 ? g(storageVolume, b(zEquals)) : 0, storageVolume.getDescription(this.f2963b), str2, uuid, zEquals, m(zEquals));
                    int iF = f(zEquals);
                    if (iF > 0) {
                        aVar.i = iF;
                    }
                    this.d.add(aVar);
                    str = "storage volume ==>";
                    obj = aVar;
                    sb = new StringBuilder();
                }
            } else {
                str = "not in mounted state => ";
                obj = storageVolume;
                sb = new StringBuilder();
            }
            sb.append(str);
            sb.append(obj);
            w.c(sb.toString());
        }
    }
}
