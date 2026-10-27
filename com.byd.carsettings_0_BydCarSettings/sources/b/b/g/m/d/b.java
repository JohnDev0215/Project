package b.b.g.m.d;

import android.view.inputmethod.InputMethodInfo;

/* JADX INFO: compiled from: BydInputMethod.java */
/* JADX INFO: loaded from: classes2.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f895a;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public InputMethodInfo f897c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public String f898d;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f896b = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f899e = false;

    public InputMethodInfo a() {
        return this.f897c;
    }

    public String b() {
        return this.f895a;
    }

    public String c() {
        return this.f898d;
    }

    public boolean d() {
        return this.f899e;
    }

    public boolean e() {
        return this.f896b;
    }

    public void f(boolean z) {
        this.f899e = z;
    }

    public void g(boolean z) {
        this.f896b = z;
    }

    public void h(InputMethodInfo inputMethodInfo) {
        this.f897c = inputMethodInfo;
    }

    public void i(String str) {
        this.f895a = str;
    }

    public void j(String str) {
        this.f898d = str;
    }

    public String toString() {
        return "BydInputMethod{label='" + this.f895a + "'subLabel='" + this.f898d + "', isDef=" + this.f896b + ", info=" + this.f897c + ", isCheck=" + this.f899e + '}';
    }
}
