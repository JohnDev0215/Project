package androidx.fragment.app;

import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.i;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

/* JADX INFO: compiled from: FragmentTransaction.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final h f2366a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final ClassLoader f2367b;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    int f2369d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    int f2370e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    int f2371f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    int f2372g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    int f2373h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    boolean f2374i;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    String f2376k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    int f2377l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    CharSequence f2378m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    int f2379n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    CharSequence f2380o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    ArrayList<String> f2381p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    ArrayList<String> f2382q;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    ArrayList<Runnable> f2384s;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    ArrayList<a> f2368c = new ArrayList<>();

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    boolean f2375j = true;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    boolean f2383r = false;

    /* JADX INFO: compiled from: FragmentTransaction.java */
    static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f2385a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        Fragment f2386b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f2387c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        int f2388d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        int f2389e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        int f2390f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        i.c f2391g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        i.c f2392h;

        a() {
        }

        a(int i7, Fragment fragment) {
            this.f2385a = i7;
            this.f2386b = fragment;
            i.c cVar = i.c.RESUMED;
            this.f2391g = cVar;
            this.f2392h = cVar;
        }

        a(int i7, Fragment fragment, i.c cVar) {
            this.f2385a = i7;
            this.f2386b = fragment;
            this.f2391g = fragment.W;
            this.f2392h = cVar;
        }
    }

    r(h hVar, ClassLoader classLoader) {
        this.f2366a = hVar;
        this.f2367b = classLoader;
    }

    public r b(int i7, Fragment fragment) {
        n(i7, fragment, null, 1);
        return this;
    }

    public r c(int i7, Fragment fragment, String str) {
        n(i7, fragment, str, 1);
        return this;
    }

    r d(ViewGroup viewGroup, Fragment fragment, String str) {
        fragment.L = viewGroup;
        return c(viewGroup.getId(), fragment, str);
    }

    public r e(Fragment fragment, String str) {
        n(0, fragment, str, 1);
        return this;
    }

    void f(a aVar) {
        this.f2368c.add(aVar);
        aVar.f2387c = this.f2369d;
        aVar.f2388d = this.f2370e;
        aVar.f2389e = this.f2371f;
        aVar.f2390f = this.f2372g;
    }

    public r g(View view, String str) {
        if (s.C()) {
            String strH = i0.u.H(view);
            if (strH == null) {
                throw new IllegalArgumentException("Unique transitionNames are required for all sharedElements");
            }
            if (this.f2381p == null) {
                this.f2381p = new ArrayList<>();
                this.f2382q = new ArrayList<>();
            } else {
                if (this.f2382q.contains(str)) {
                    throw new IllegalArgumentException("A shared element with the target name '" + str + "' has already been added to the transaction.");
                }
                if (this.f2381p.contains(strH)) {
                    throw new IllegalArgumentException("A shared element with the source name '" + strH + "' has already been added to the transaction.");
                }
            }
            this.f2381p.add(strH);
            this.f2382q.add(str);
        }
        return this;
    }

    public r h(String str) {
        if (!this.f2375j) {
            throw new IllegalStateException("This FragmentTransaction is not allowed to be added to the back stack.");
        }
        this.f2374i = true;
        this.f2376k = str;
        return this;
    }

    public abstract int i();

    public abstract int j();

    public abstract void k();

    public abstract void l();

    public r m() {
        if (this.f2374i) {
            throw new IllegalStateException("This transaction is already being added to the back stack");
        }
        this.f2375j = false;
        return this;
    }

    void n(int i7, Fragment fragment, String str, int i8) {
        Class<?> cls = fragment.getClass();
        int modifiers = cls.getModifiers();
        if (cls.isAnonymousClass() || !Modifier.isPublic(modifiers) || (cls.isMemberClass() && !Modifier.isStatic(modifiers))) {
            throw new IllegalStateException("Fragment " + cls.getCanonicalName() + " must be a public static class to be  properly recreated from instance state.");
        }
        if (str != null) {
            String str2 = fragment.D;
            if (str2 != null && !str.equals(str2)) {
                throw new IllegalStateException("Can't change tag of fragment " + fragment + ": was " + fragment.D + " now " + str);
            }
            fragment.D = str;
        }
        if (i7 != 0) {
            if (i7 == -1) {
                throw new IllegalArgumentException("Can't add fragment " + fragment + " with tag " + str + " to container view with no id");
            }
            int i9 = fragment.B;
            if (i9 != 0 && i9 != i7) {
                throw new IllegalStateException("Can't change container ID of fragment " + fragment + ": was " + fragment.B + " now " + i7);
            }
            fragment.B = i7;
            fragment.C = i7;
        }
        f(new a(i8, fragment));
    }

    public abstract boolean o();

    public r p(Fragment fragment) {
        f(new a(3, fragment));
        return this;
    }

    public r q(int i7, Fragment fragment) {
        return r(i7, fragment, null);
    }

    public r r(int i7, Fragment fragment, String str) {
        if (i7 == 0) {
            throw new IllegalArgumentException("Must use non-zero containerViewId");
        }
        n(i7, fragment, str, 2);
        return this;
    }

    public r s(int i7, int i8, int i9, int i10) {
        this.f2369d = i7;
        this.f2370e = i8;
        this.f2371f = i9;
        this.f2372g = i10;
        return this;
    }

    public r t(Fragment fragment, i.c cVar) {
        f(new a(10, fragment, cVar));
        return this;
    }

    public r u(Fragment fragment) {
        f(new a(8, fragment));
        return this;
    }

    public r v(boolean z6) {
        this.f2383r = z6;
        return this;
    }

    public r w(Fragment fragment) {
        f(new a(5, fragment));
        return this;
    }
}
