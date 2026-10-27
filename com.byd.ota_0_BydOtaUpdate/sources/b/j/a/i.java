package b.j.a;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

/* JADX INFO: compiled from: FragmentTransaction.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class i {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f1905b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f1906c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f1907d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f1908e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f1909f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f1910g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f1911h;
    public String j;
    public int k;
    public CharSequence l;
    public int m;
    public CharSequence n;
    public ArrayList<String> o;
    public ArrayList<String> p;
    public ArrayList<Runnable> r;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ArrayList<a> f1904a = new ArrayList<>();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f1912i = true;
    public boolean q = false;

    /* JADX INFO: compiled from: FragmentTransaction.java */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f1913a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public Fragment f1914b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f1915c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f1916d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f1917e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f1918f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public Lifecycle.State f1919g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public Lifecycle.State f1920h;

        public a() {
        }

        public a(int i2, Fragment fragment) {
            this.f1913a = i2;
            this.f1914b = fragment;
            Lifecycle.State state = Lifecycle.State.RESUMED;
            this.f1919g = state;
            this.f1920h = state;
        }
    }

    public i b(int i2, Fragment fragment, String str) {
        g(i2, fragment, str, 1);
        return this;
    }

    public void c(a aVar) {
        this.f1904a.add(aVar);
        aVar.f1915c = this.f1905b;
        aVar.f1916d = this.f1906c;
        aVar.f1917e = this.f1907d;
        aVar.f1918f = this.f1908e;
    }

    public i d(Fragment fragment) {
        c(new a(7, fragment));
        return this;
    }

    public abstract int e();

    public i f(Fragment fragment) {
        c(new a(6, fragment));
        return this;
    }

    public void g(int i2, Fragment fragment, String str, int i3) {
        Class<?> cls = fragment.getClass();
        int modifiers = cls.getModifiers();
        if (cls.isAnonymousClass() || !Modifier.isPublic(modifiers) || (cls.isMemberClass() && !Modifier.isStatic(modifiers))) {
            throw new IllegalStateException("Fragment " + cls.getCanonicalName() + " must be a public static class to be  properly recreated from instance state.");
        }
        if (str != null) {
            String str2 = fragment.X3;
            if (str2 != null && !str.equals(str2)) {
                throw new IllegalStateException("Can't change tag of fragment " + fragment + ": was " + fragment.X3 + " now " + str);
            }
            fragment.X3 = str;
        }
        if (i2 != 0) {
            if (i2 == -1) {
                throw new IllegalArgumentException("Can't add fragment " + fragment + " with tag " + str + " to container view with no id");
            }
            int i4 = fragment.V3;
            if (i4 != 0 && i4 != i2) {
                throw new IllegalStateException("Can't change container ID of fragment " + fragment + ": was " + fragment.V3 + " now " + i2);
            }
            fragment.V3 = i2;
            fragment.W3 = i2;
        }
        c(new a(i3, fragment));
    }
}
