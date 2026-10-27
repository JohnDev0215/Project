package androidx.fragment.app;

import androidx.lifecycle.Lifecycle;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

/* JADX INFO: compiled from: FragmentTransaction.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class k {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    int f1414b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    int f1415c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    int f1416d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    int f1417e;
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
    ArrayList<a> f1413a = new ArrayList<>();
    boolean i = true;
    boolean q = false;

    /* JADX INFO: compiled from: FragmentTransaction.java */
    static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f1418a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        Fragment f1419b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f1420c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        int f1421d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        int f1422e;
        int f;
        Lifecycle.State g;
        Lifecycle.State h;

        a() {
        }

        a(int i, Fragment fragment) {
            this.f1418a = i;
            this.f1419b = fragment;
            Lifecycle.State state = Lifecycle.State.RESUMED;
            this.g = state;
            this.h = state;
        }

        a(int i, Fragment fragment, Lifecycle.State state) {
            this.f1418a = i;
            this.f1419b = fragment;
            this.g = fragment.mMaxState;
            this.h = state;
        }
    }

    public k b(int i, Fragment fragment, String str) {
        j(i, fragment, str, 1);
        return this;
    }

    void c(a aVar) {
        this.f1413a.add(aVar);
        aVar.f1420c = this.f1414b;
        aVar.f1421d = this.f1415c;
        aVar.f1422e = this.f1416d;
        aVar.f = this.f1417e;
    }

    public k d(Fragment fragment) {
        c(new a(7, fragment));
        return this;
    }

    public abstract int e();

    public abstract int f();

    public abstract void g();

    public k h(Fragment fragment) {
        c(new a(6, fragment));
        return this;
    }

    public k i() {
        if (this.h) {
            throw new IllegalStateException("This transaction is already being added to the back stack");
        }
        this.i = false;
        return this;
    }

    void j(int i, Fragment fragment, String str, int i2) {
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

    public k k(Fragment fragment) {
        c(new a(3, fragment));
        return this;
    }

    public k l(Fragment fragment, Lifecycle.State state) {
        c(new a(10, fragment, state));
        return this;
    }
}
