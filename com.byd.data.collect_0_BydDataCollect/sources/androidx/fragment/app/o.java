package androidx.fragment.app;

import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.d;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

/* JADX INFO: compiled from: FragmentTransaction.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class o {

    /* JADX INFO: renamed from: a */
    private final f f1482a;

    /* JADX INFO: renamed from: b */
    private final ClassLoader f1483b;

    /* JADX INFO: renamed from: d */
    int f1485d;

    /* JADX INFO: renamed from: e */
    int f1486e;

    /* JADX INFO: renamed from: f */
    int f1487f;

    /* JADX INFO: renamed from: g */
    int f1488g;

    /* JADX INFO: renamed from: h */
    int f1489h;

    /* JADX INFO: renamed from: i */
    boolean f1490i;
    String k;
    int l;
    CharSequence m;
    int n;
    CharSequence o;
    ArrayList<String> p;
    ArrayList<String> q;
    ArrayList<Runnable> s;

    /* JADX INFO: renamed from: c */
    ArrayList<a> f1484c = new ArrayList<>();
    boolean j = true;
    boolean r = false;

    /* JADX INFO: compiled from: FragmentTransaction.java */
    static final class a {

        /* JADX INFO: renamed from: a */
        int f1491a;

        /* JADX INFO: renamed from: b */
        Fragment f1492b;

        /* JADX INFO: renamed from: c */
        int f1493c;

        /* JADX INFO: renamed from: d */
        int f1494d;

        /* JADX INFO: renamed from: e */
        int f1495e;

        /* JADX INFO: renamed from: f */
        int f1496f;

        /* JADX INFO: renamed from: g */
        d.b f1497g;

        /* JADX INFO: renamed from: h */
        d.b f1498h;

        a() {
        }

        a(int i2, Fragment fragment) {
            this.f1491a = i2;
            this.f1492b = fragment;
            d.b bVar = d.b.RESUMED;
            this.f1497g = bVar;
            this.f1498h = bVar;
        }
    }

    o(f fVar, ClassLoader classLoader) {
        this.f1482a = fVar;
        this.f1483b = classLoader;
    }

    public o b(int i2, Fragment fragment, String str) {
        o(i2, fragment, str, 1);
        return this;
    }

    o c(ViewGroup viewGroup, Fragment fragment, String str) {
        fragment.H = viewGroup;
        return b(viewGroup.getId(), fragment, str);
    }

    public o d(Fragment fragment, String str) {
        o(0, fragment, str, 1);
        return this;
    }

    void e(a aVar) {
        this.f1484c.add(aVar);
        aVar.f1493c = this.f1485d;
        aVar.f1494d = this.f1486e;
        aVar.f1495e = this.f1487f;
        aVar.f1496f = this.f1488g;
    }

    public o f(View view, String str) {
        if (p.C()) {
            String strH = b.g.k.t.H(view);
            if (strH == null) {
                throw new IllegalArgumentException("Unique transitionNames are required for all sharedElements");
            }
            if (this.p == null) {
                this.p = new ArrayList<>();
                this.q = new ArrayList<>();
            } else {
                if (this.q.contains(str)) {
                    throw new IllegalArgumentException("A shared element with the target name '" + str + "' has already been added to the transaction.");
                }
                if (this.p.contains(strH)) {
                    throw new IllegalArgumentException("A shared element with the source name '" + strH + "' has already been added to the transaction.");
                }
            }
            this.p.add(strH);
            this.q.add(str);
        }
        return this;
    }

    public o g(String str) {
        if (!this.j) {
            throw new IllegalStateException("This FragmentTransaction is not allowed to be added to the back stack.");
        }
        this.f1490i = true;
        this.k = str;
        return this;
    }

    public o h(Fragment fragment) {
        e(new a(7, fragment));
        return this;
    }

    public abstract int i();

    public abstract int j();

    public abstract void k();

    public abstract void l();

    public o m(Fragment fragment) {
        e(new a(6, fragment));
        return this;
    }

    public o n() {
        if (this.f1490i) {
            throw new IllegalStateException("This transaction is already being added to the back stack");
        }
        this.j = false;
        return this;
    }

    void o(int i2, Fragment fragment, String str, int i3) {
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
        if (i2 != 0) {
            if (i2 == -1) {
                throw new IllegalArgumentException("Can't add fragment " + fragment + " with tag " + str + " to container view with no id");
            }
            int i4 = fragment.x;
            if (i4 != 0 && i4 != i2) {
                throw new IllegalStateException("Can't change container ID of fragment " + fragment + ": was " + fragment.x + " now " + i2);
            }
            fragment.x = i2;
            fragment.y = i2;
        }
        e(new a(i3, fragment));
    }

    public o p(Fragment fragment) {
        e(new a(3, fragment));
        return this;
    }

    public o q(int i2, Fragment fragment) {
        return r(i2, fragment, null);
    }

    public o r(int i2, Fragment fragment, String str) {
        if (i2 == 0) {
            throw new IllegalArgumentException("Must use non-zero containerViewId");
        }
        o(i2, fragment, str, 2);
        return this;
    }

    public o s(int i2, int i3, int i4, int i5) {
        this.f1485d = i2;
        this.f1486e = i3;
        this.f1487f = i4;
        this.f1488g = i5;
        return this;
    }

    public o t(Fragment fragment) {
        e(new a(8, fragment));
        return this;
    }

    public o u(boolean z) {
        this.r = z;
        return this;
    }
}
