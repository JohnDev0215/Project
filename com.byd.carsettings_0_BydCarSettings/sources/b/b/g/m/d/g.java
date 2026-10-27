package b.b.g.m.d;

import android.content.pm.PackageInfo;
import android.graphics.drawable.Drawable;
import java.util.List;
import java.util.Objects;

/* JADX INFO: compiled from: PermissionApp.java */
/* JADX INFO: loaded from: classes2.dex */
public class g implements Comparable<g> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public PackageInfo f921a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Drawable f922b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public String f923c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f924d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f925e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public List<c> f926f;
    public int g;
    public long h;
    public boolean i;

    public g(PackageInfo packageInfo, Drawable drawable, String str) {
        this.f921a = packageInfo;
        this.f922b = drawable;
        this.f923c = str;
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(g gVar) {
        List<c> list = this.f926f;
        if (list == null || gVar.f926f == null) {
            return 0;
        }
        if (list.size() > gVar.f926f.size()) {
            return -1;
        }
        if (this.f926f.size() < gVar.f926f.size()) {
            return 1;
        }
        int i = this.g;
        int i2 = gVar.g;
        if (i > i2) {
            return -1;
        }
        return i < i2 ? 1 : 0;
    }

    public String b() {
        return this.f923c;
    }

    public int c() {
        return this.g;
    }

    public Drawable d() {
        return this.f922b;
    }

    public PackageInfo e() {
        return this.f921a;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        b.b.m.c.a.a("PermissionApp", "--equals--o: " + obj + ", permissionList = " + this.f926f);
        if (obj instanceof c) {
            List<c> list = this.f926f;
            return list != null && list.contains(obj);
        }
        if (obj == null || g.class != obj.getClass()) {
            return false;
        }
        g gVar = (g) obj;
        return this.f924d == gVar.f924d && this.g == gVar.g && this.h == gVar.h && this.i == gVar.i && Objects.equals(this.f921a, gVar.f921a) && Objects.equals(this.f922b, gVar.f922b) && Objects.equals(this.f923c, gVar.f923c) && Objects.equals(this.f925e, gVar.f925e) && Objects.equals(this.f926f, gVar.f926f);
    }

    public List<c> f() {
        return this.f926f;
    }

    public boolean g() {
        return this.i;
    }

    public void h(boolean z) {
        this.i = z;
    }

    public int hashCode() {
        return Objects.hash(this.f921a, this.f922b, this.f923c, Boolean.valueOf(this.f924d), this.f925e, this.f926f, Integer.valueOf(this.g), Long.valueOf(this.h), Boolean.valueOf(this.i));
    }

    public void i(int i) {
        this.g = i;
    }

    public void j(long j) {
        this.h = j;
    }

    public void k(List<c> list) {
        this.f926f = list;
    }

    public void l(boolean z) {
        this.f924d = z;
    }

    public void m(String str) {
        this.f925e = str;
    }

    public String toString() {
        return "PermissionApp{info=" + this.f921a + ", icon=" + this.f922b + ", appName='" + this.f923c + "', systemApp=" + this.f924d + ", version='" + this.f925e + "', enableCount=" + this.g + ", installTime=" + this.h + ", permissionList=" + this.f926f + '}';
    }
}
