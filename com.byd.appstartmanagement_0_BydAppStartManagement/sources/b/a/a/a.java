package b.a.a;

import android.content.ComponentName;
import android.graphics.drawable.Drawable;
import java.util.Objects;

/* JADX INFO: compiled from: AppInfo.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Drawable f145a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public String f146b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f147c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public String f148d;
    public int e;
    public ComponentName f;

    public String a() {
        return this.f146b;
    }

    public ComponentName b() {
        return this.f;
    }

    public Drawable c() {
        return this.f145a;
    }

    public boolean d() {
        return this.f147c;
    }

    public void e(String str) {
        this.f146b = str;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || a.class != obj.getClass()) {
            return false;
        }
        a aVar = (a) obj;
        return this.f146b.equals(aVar.f146b) && this.f148d.equals(aVar.f148d);
    }

    public void f(ComponentName componentName) {
        this.f = componentName;
    }

    public void g(boolean z) {
        this.f147c = z;
    }

    public void h(Drawable drawable) {
        this.f145a = drawable;
    }

    public int hashCode() {
        return Objects.hash(this.f146b, this.f148d);
    }

    public void i(String str) {
        this.f148d = str;
    }

    public void j(int i) {
        this.e = i;
    }

    public String toString() {
        return "BootAppInfo{icon=" + this.f145a + ", appName=" + this.f146b + ", enable=" + this.f147c + ", pkgName=" + this.f148d + ", uid=" + this.e + '}';
    }
}
