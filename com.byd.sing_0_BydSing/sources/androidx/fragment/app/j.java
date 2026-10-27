package androidx.fragment.app;

import androidx.lifecycle.Lifecycle;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

/* JADX INFO: compiled from: FragmentTransaction.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class j {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    int f1563b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    int f1564c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    int f1565d;
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
    ArrayList<a> f1562a = new ArrayList<>();
    boolean i = true;
    boolean q = false;

    /* JADX INFO: compiled from: FragmentTransaction.java */
    static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f1566a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        Fragment f1567b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f1568c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        int f1569d;
        int e;
        int f;
        Lifecycle.State g;
        Lifecycle.State h;

        a() {
        }

        a(int i, Fragment fragment) {
            this.f1566a = i;
            this.f1567b = fragment;
            Lifecycle.State state = Lifecycle.State.RESUMED;
            this.g = state;
            this.h = state;
        }
    }

    public j b(int i, Fragment fragment, String str) {
        i(i, fragment, str, 1);
        return this;
    }

    public j c(Fragment fragment, String str) {
        i(0, fragment, str, 1);
        return this;
    }

    void d(a aVar) {
        this.f1562a.add(aVar);
        aVar.f1568c = this.f1563b;
        aVar.f1569d = this.f1564c;
        aVar.e = this.f1565d;
        aVar.f = this.e;
    }

    public j e(Fragment fragment) {
        d(new a(7, fragment));
        return this;
    }

    public abstract int f();

    public abstract int g();

    public j h(Fragment fragment) {
        d(new a(6, fragment));
        return this;
    }

    void i(int i, Fragment fragment, String str, int i2) {
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
        d(new a(i2, fragment));
    }
}
