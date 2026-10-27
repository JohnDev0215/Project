package androidx.fragment.app;

import androidx.lifecycle.Lifecycle;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

/* JADX INFO: compiled from: FragmentTransaction.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class j {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    int f1552b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    int f1553c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    int f1554d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    int f1555e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    int f1556f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    int f1557g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    boolean f1558h;
    String j;
    int k;
    CharSequence l;
    int m;
    CharSequence n;
    ArrayList<String> o;
    ArrayList<String> p;
    ArrayList<Runnable> r;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    ArrayList<a> f1551a = new ArrayList<>();
    boolean i = true;
    boolean q = false;

    /* JADX INFO: compiled from: FragmentTransaction.java */
    static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f1559a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        Fragment f1560b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f1561c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        int f1562d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        int f1563e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        int f1564f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        Lifecycle.State f1565g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        Lifecycle.State f1566h;

        a() {
        }

        a(int i, Fragment fragment) {
            this.f1559a = i;
            this.f1560b = fragment;
            Lifecycle.State state = Lifecycle.State.RESUMED;
            this.f1565g = state;
            this.f1566h = state;
        }
    }

    public j b(int i, Fragment fragment, String str) {
        g(i, fragment, str, 1);
        return this;
    }

    void c(a aVar) {
        this.f1551a.add(aVar);
        aVar.f1561c = this.f1552b;
        aVar.f1562d = this.f1553c;
        aVar.f1563e = this.f1554d;
        aVar.f1564f = this.f1555e;
    }

    public j d(Fragment fragment) {
        c(new a(7, fragment));
        return this;
    }

    public abstract int e();

    public j f(Fragment fragment) {
        c(new a(6, fragment));
        return this;
    }

    void g(int i, Fragment fragment, String str, int i2) {
        Class<?> cls = fragment.getClass();
        int modifiers = cls.getModifiers();
        if (cls.isAnonymousClass() || !Modifier.isPublic(modifiers) || (cls.isMemberClass() && !Modifier.isStatic(modifiers))) {
            throw new IllegalStateException("Fragment " + cls.getCanonicalName() + " must be a public static class to be  properly recreated from instance state.");
        }
        if (str != null) {
            String str2 = fragment.mTag;
            if (str2 != null && !str.equals(str2)) {
                throw new IllegalStateException("Can't change tag of fragment " + fragment + ": was " + fragment.mTag + " now " + str);
            }
            fragment.mTag = str;
        }
        if (i != 0) {
            if (i == -1) {
                throw new IllegalArgumentException("Can't add fragment " + fragment + " with tag " + str + " to container view with no id");
            }
            int i3 = fragment.mFragmentId;
            if (i3 != 0 && i3 != i) {
                throw new IllegalStateException("Can't change container ID of fragment " + fragment + ": was " + fragment.mFragmentId + " now " + i);
            }
            fragment.mFragmentId = i;
            fragment.mContainerId = i;
        }
        c(new a(i2, fragment));
    }
}
