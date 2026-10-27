package androidx.fragment.app;

import androidx.lifecycle.d;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public abstract class n {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    int f1332b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    int f1333c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    int f1334d;
    int e;
    int f;
    int g;
    boolean h;
    String j;
    int k;
    CharSequence l;
    int m;
    CharSequence n;
    ArrayList<String> o;
    ArrayList<String> p;
    ArrayList<Runnable> r;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    ArrayList<a> f1331a = new ArrayList<>();
    boolean i = true;
    boolean q = false;

    static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f1335a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        Fragment f1336b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f1337c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        int f1338d;
        int e;
        int f;
        d.b g;
        d.b h;

        a() {
        }

        a(int i, Fragment fragment) {
            this.f1335a = i;
            this.f1336b = fragment;
            d.b bVar = d.b.RESUMED;
            this.g = bVar;
            this.h = bVar;
        }
    }

    public n b(Fragment fragment, String str) {
        f(0, fragment, str, 1);
        return this;
    }

    void c(a aVar) {
        this.f1331a.add(aVar);
        aVar.f1337c = this.f1332b;
        aVar.f1338d = this.f1333c;
        aVar.e = this.f1334d;
        aVar.f = this.e;
    }

    public abstract int d();

    public abstract int e();

    void f(int i, Fragment fragment, String str, int i2) {
        Class<?> cls = fragment.getClass();
        int modifiers = cls.getModifiers();
        if (cls.isAnonymousClass() || !Modifier.isPublic(modifiers) || (cls.isMemberClass() && !Modifier.isStatic(modifiers))) {
            throw new IllegalStateException("Fragment " + cls.getCanonicalName() + " must be a public static class to be  properly recreated from instance state.");
        }
        if (str != null) {
            String str2 = fragment.z;
            if (str2 != null && !str.equals(str2)) {
                throw new IllegalStateException("Can't change tag of fragment " + fragment + ": was " + fragment.z + " now " + str);
            }
            fragment.z = str;
        }
        if (i != 0) {
            if (i == -1) {
                throw new IllegalArgumentException("Can't add fragment " + fragment + " with tag " + str + " to container view with no id");
            }
            int i3 = fragment.x;
            if (i3 != 0 && i3 != i) {
                throw new IllegalStateException("Can't change container ID of fragment " + fragment + ": was " + fragment.x + " now " + i);
            }
            fragment.x = i;
            fragment.y = i;
        }
        c(new a(i2, fragment));
    }

    public n g(Fragment fragment) {
        c(new a(3, fragment));
        return this;
    }

    public n h(int i, Fragment fragment) {
        return i(i, fragment, null);
    }

    public n i(int i, Fragment fragment, String str) {
        if (i == 0) {
            throw new IllegalArgumentException("Must use non-zero containerViewId");
        }
        f(i, fragment, str, 2);
        return this;
    }
}
