package b.b.h;

import android.os.Environment;
import b.b.h.f.b;

/* JADX INFO: compiled from: DbConfig.java */
/* JADX INFO: loaded from: classes2.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static String f1852a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public String f1853b;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f1855d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Class f1856e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public b.InterfaceC0077b f1857f;
    public String g;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public String f1854c = f1852a + "/byd/sqlite";
    public boolean h = false;

    static {
        if ("mounted".equals(Environment.getExternalStorageState())) {
            f1852a = Environment.getExternalStorageDirectory().getAbsolutePath();
        }
    }

    public String a() {
        return this.f1854c;
    }

    public Class b() {
        return this.f1856e;
    }

    public String c() {
        return this.f1853b;
    }

    public int d() {
        return this.f1855d;
    }

    public String e() {
        return this.g;
    }

    public b.InterfaceC0077b f() {
        return this.f1857f;
    }

    public boolean g() {
        return this.h;
    }

    public void h(Class cls) {
        this.f1856e = cls;
    }

    public void i(String str) {
        this.f1853b = str;
    }

    public void j(int i) {
        this.f1855d = i;
    }

    public void k(String str) {
        this.g = str;
    }

    public void l(b.InterfaceC0077b interfaceC0077b) {
        this.f1857f = interfaceC0077b;
    }

    public String toString() {
        return "DbConfig{customDir='" + this.f1854c + "', dbName='" + this.f1853b + "', dbVersion=" + this.f1855d + ", dataClazz=" + this.f1856e + ", isUseCustomDir=" + this.h + '}';
    }
}
