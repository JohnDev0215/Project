package b.b.g.p.k;

import android.content.ComponentName;
import android.graphics.drawable.Drawable;
import java.util.Objects;

/* JADX INFO: compiled from: AppInfo.java */
/* JADX INFO: loaded from: classes2.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Drawable f1409a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public String f1410b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f1411c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public String f1412d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f1413e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public ComponentName f1414f;

    public a() {
    }

    public String a() {
        return this.f1410b;
    }

    public ComponentName b() {
        return this.f1414f;
    }

    public Drawable c() {
        return this.f1409a;
    }

    public String d() {
        return this.f1412d;
    }

    public int e() {
        return this.f1413e;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || a.class != obj.getClass()) {
            return false;
        }
        a aVar = (a) obj;
        return this.f1410b.equals(aVar.f1410b) && this.f1412d.equals(aVar.f1412d);
    }

    public boolean f() {
        return this.f1411c;
    }

    public void g(String str) {
        this.f1410b = str;
    }

    public void h(ComponentName componentName) {
        this.f1414f = componentName;
    }

    public int hashCode() {
        return Objects.hash(this.f1410b, this.f1412d);
    }

    public void i(boolean z) {
        this.f1411c = z;
    }

    public void j(Drawable drawable) {
        this.f1409a = drawable;
    }

    public void k(String str) {
        this.f1412d = str;
    }

    public void l(int i) {
        this.f1413e = i;
    }

    public String toString() {
        return "BootAppInfo{icon=" + this.f1409a + ", appName=" + this.f1410b + ", enable=" + this.f1411c + ", pkgName=" + this.f1412d + ", uid=" + this.f1413e + '}';
    }

    public a(Drawable drawable, String str, boolean z, String str2, int i) {
        this.f1409a = drawable;
        this.f1410b = str;
        this.f1411c = z;
        this.f1412d = str2;
        this.f1413e = i;
    }
}
