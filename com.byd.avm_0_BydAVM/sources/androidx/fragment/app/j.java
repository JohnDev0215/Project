package androidx.fragment.app;

import androidx.lifecycle.Lifecycle;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

/* JADX INFO: compiled from: FragmentTransaction.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class j {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    int f1037b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    int f1038c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    int f1039d;
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
    ArrayList<a> f1036a = new ArrayList<>();
    boolean i = true;
    boolean q = false;

    /* JADX INFO: compiled from: FragmentTransaction.java */
    static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f1040a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        Fragment f1041b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f1042c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        int f1043d;
        int e;
        int f;
        Lifecycle.State g;
        Lifecycle.State h;

        a() {
        }

        a(int i, Fragment fragment) {
            this.f1040a = i;
            this.f1041b = fragment;
            Lifecycle.State state = Lifecycle.State.RESUMED;
            this.g = state;
            this.h = state;
        }
    }

    public j b(int i, Fragment fragment, String str) {
        g(i, fragment, str, 1);
        return this;
    }

    void c(a aVar) {
        this.f1036a.add(aVar);
        aVar.f1042c = this.f1037b;
        aVar.f1043d = this.f1038c;
        aVar.e = this.f1039d;
        aVar.f = this.e;
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
