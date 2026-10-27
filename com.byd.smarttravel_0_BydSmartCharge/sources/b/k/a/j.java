package b.k.a;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

/* JADX INFO: compiled from: FragmentTransaction.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class j {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f1253b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f1254c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f1255d;
    public int e;
    public int f;
    public int g;
    public boolean h;
    public String j;
    public int k;
    public CharSequence l;
    public int m;
    public CharSequence n;
    public ArrayList<String> o;
    public ArrayList<String> p;
    public ArrayList<Runnable> r;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ArrayList<a> f1252a = new ArrayList<>();
    public boolean i = true;
    public boolean q = false;

    /* JADX INFO: compiled from: FragmentTransaction.java */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f1256a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public Fragment f1257b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f1258c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f1259d;
        public int e;
        public int f;
        public Lifecycle.State g;
        public Lifecycle.State h;

        public a() {
        }

        public a(int i, Fragment fragment) {
            this.f1256a = i;
            this.f1257b = fragment;
            Lifecycle.State state = Lifecycle.State.RESUMED;
            this.g = state;
            this.h = state;
        }
    }

    public j b(int i, Fragment fragment, String str) {
        g(i, fragment, str, 1);
        return this;
    }

    public void c(a aVar) {
        this.f1252a.add(aVar);
        aVar.f1258c = this.f1253b;
        aVar.f1259d = this.f1254c;
        aVar.e = this.f1255d;
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

    public void g(int i, Fragment fragment, String str, int i2) {
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
