package b.l.d;

import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

/* JADX INFO: compiled from: FragmentTransaction.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final f f2842a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ClassLoader f2843b;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f2845d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f2846e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f2847f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f2848g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f2849h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f2850i;
    public String k;
    public int l;
    public CharSequence m;
    public int n;
    public CharSequence o;
    public ArrayList<String> p;
    public ArrayList<String> q;
    public ArrayList<Runnable> s;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public ArrayList<a> f2844c = new ArrayList<>();
    public boolean j = true;
    public boolean r = false;

    /* JADX INFO: compiled from: FragmentTransaction.java */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f2851a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public Fragment f2852b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f2853c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f2854d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f2855e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f2856f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public Lifecycle.State f2857g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public Lifecycle.State f2858h;

        public a() {
        }

        public a(int i2, Fragment fragment) {
            this.f2851a = i2;
            this.f2852b = fragment;
            Lifecycle.State state = Lifecycle.State.RESUMED;
            this.f2857g = state;
            this.f2858h = state;
        }
    }

    public p(f fVar, ClassLoader classLoader) {
        this.f2842a = fVar;
        this.f2843b = classLoader;
    }

    public p b(int i2, Fragment fragment, String str) {
        n(i2, fragment, str, 1);
        return this;
    }

    public p c(ViewGroup viewGroup, Fragment fragment, String str) {
        fragment.mContainer = viewGroup;
        return b(viewGroup.getId(), fragment, str);
    }

    public p d(Fragment fragment, String str) {
        n(0, fragment, str, 1);
        return this;
    }

    public void e(a aVar) {
        this.f2844c.add(aVar);
        aVar.f2853c = this.f2845d;
        aVar.f2854d = this.f2846e;
        aVar.f2855e = this.f2847f;
        aVar.f2856f = this.f2848g;
    }

    public p f(String str) {
        if (!this.j) {
            throw new IllegalStateException("This FragmentTransaction is not allowed to be added to the back stack.");
        }
        this.f2850i = true;
        this.k = str;
        return this;
    }

    public p g(Fragment fragment) {
        e(new a(7, fragment));
        return this;
    }

    public abstract int h();

    public abstract int i();

    public abstract void j();

    public abstract void k();

    public p l(Fragment fragment) {
        e(new a(6, fragment));
        return this;
    }

    public p m() {
        if (this.f2850i) {
            throw new IllegalStateException("This transaction is already being added to the back stack");
        }
        this.j = false;
        return this;
    }

    public void n(int i2, Fragment fragment, String str, int i3) {
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
        if (i2 != 0) {
            if (i2 == -1) {
                throw new IllegalArgumentException("Can't add fragment " + fragment + " with tag " + str + " to container view with no id");
            }
            int i4 = fragment.mFragmentId;
            if (i4 != 0 && i4 != i2) {
                throw new IllegalStateException("Can't change container ID of fragment " + fragment + ": was " + fragment.mFragmentId + " now " + i2);
            }
            fragment.mFragmentId = i2;
            fragment.mContainerId = i2;
        }
        e(new a(i3, fragment));
    }

    public p o(Fragment fragment) {
        e(new a(4, fragment));
        return this;
    }

    public p p(Fragment fragment) {
        e(new a(3, fragment));
        return this;
    }

    public p q(int i2, Fragment fragment) {
        return r(i2, fragment, null);
    }

    public p r(int i2, Fragment fragment, String str) {
        if (i2 == 0) {
            throw new IllegalArgumentException("Must use non-zero containerViewId");
        }
        n(i2, fragment, str, 2);
        return this;
    }

    public p s(boolean z) {
        this.r = z;
        return this;
    }

    public p t(Fragment fragment) {
        e(new a(5, fragment));
        return this;
    }
}
