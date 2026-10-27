package v0;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

/* JADX INFO: compiled from: FragmentTransaction.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class j {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f8423b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f8424c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f8425d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f8426e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f8427f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f8428g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f8429h;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public String f8431j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f8432k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public CharSequence f8433l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f8434m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public CharSequence f8435n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public ArrayList<String> f8436o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public ArrayList<String> f8437p;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public ArrayList<Runnable> f8439r;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ArrayList<a> f8422a = new ArrayList<>();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f8430i = true;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f8438q = false;

    /* JADX INFO: compiled from: FragmentTransaction.java */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f8440a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public Fragment f8441b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f8442c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f8443d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f8444e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f8445f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public Lifecycle.State f8446g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public Lifecycle.State f8447h;

        public a() {
        }

        public a(int i8, Fragment fragment) {
            this.f8440a = i8;
            this.f8441b = fragment;
            Lifecycle.State state = Lifecycle.State.RESUMED;
            this.f8446g = state;
            this.f8447h = state;
        }

        public a(int i8, Fragment fragment, Lifecycle.State state) {
            this.f8440a = i8;
            this.f8441b = fragment;
            this.f8446g = fragment.mMaxState;
            this.f8447h = state;
        }
    }

    public j b(int i8, Fragment fragment, String str) {
        j(i8, fragment, str, 1);
        return this;
    }

    public j c(Fragment fragment, String str) {
        j(0, fragment, str, 1);
        return this;
    }

    public void d(a aVar) {
        this.f8422a.add(aVar);
        aVar.f8442c = this.f8423b;
        aVar.f8443d = this.f8424c;
        aVar.f8444e = this.f8425d;
        aVar.f8445f = this.f8426e;
    }

    public j e(Fragment fragment) {
        d(new a(7, fragment));
        return this;
    }

    public abstract int f();

    public abstract void g();

    public j h(Fragment fragment) {
        d(new a(6, fragment));
        return this;
    }

    public j i() {
        if (this.f8429h) {
            throw new IllegalStateException("This transaction is already being added to the back stack");
        }
        this.f8430i = false;
        return this;
    }

    public void j(int i8, Fragment fragment, String str, int i9) {
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
        if (i8 != 0) {
            if (i8 == -1) {
                throw new IllegalArgumentException("Can't add fragment " + fragment + " with tag " + str + " to container view with no id");
            }
            int i10 = fragment.mFragmentId;
            if (i10 != 0 && i10 != i8) {
                throw new IllegalStateException("Can't change container ID of fragment " + fragment + ": was " + fragment.mFragmentId + " now " + i8);
            }
            fragment.mFragmentId = i8;
            fragment.mContainerId = i8;
        }
        d(new a(i9, fragment));
    }

    public abstract boolean k();

    public j l(Fragment fragment) {
        d(new a(3, fragment));
        return this;
    }

    public j m(Fragment fragment, Lifecycle.State state) {
        d(new a(10, fragment, state));
        return this;
    }
}
