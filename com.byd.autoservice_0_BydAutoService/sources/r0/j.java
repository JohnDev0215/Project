package r0;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

/* JADX INFO: compiled from: FragmentTransaction.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class j {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f4745b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f4746c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f4747d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f4748e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f4749f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f4750g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f4751h;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public String f4753j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f4754k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public CharSequence f4755l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f4756m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public CharSequence f4757n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public ArrayList<String> f4758o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public ArrayList<String> f4759p;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public ArrayList<Runnable> f4761r;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ArrayList<a> f4744a = new ArrayList<>();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f4752i = true;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f4760q = false;

    /* JADX INFO: compiled from: FragmentTransaction.java */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f4762a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public Fragment f4763b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f4764c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f4765d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f4766e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f4767f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public Lifecycle.State f4768g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public Lifecycle.State f4769h;

        public a() {
        }

        public a(int i3, Fragment fragment) {
            this.f4762a = i3;
            this.f4763b = fragment;
            Lifecycle.State state = Lifecycle.State.RESUMED;
            this.f4768g = state;
            this.f4769h = state;
        }
    }

    public j b(int i3, Fragment fragment, String str) {
        g(i3, fragment, str, 1);
        return this;
    }

    public void c(a aVar) {
        this.f4744a.add(aVar);
        aVar.f4764c = this.f4745b;
        aVar.f4765d = this.f4746c;
        aVar.f4766e = this.f4747d;
        aVar.f4767f = this.f4748e;
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

    public void g(int i3, Fragment fragment, String str, int i4) {
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
        if (i3 != 0) {
            if (i3 == -1) {
                throw new IllegalArgumentException("Can't add fragment " + fragment + " with tag " + str + " to container view with no id");
            }
            int i5 = fragment.mFragmentId;
            if (i5 != 0 && i5 != i3) {
                throw new IllegalStateException("Can't change container ID of fragment " + fragment + ": was " + fragment.mFragmentId + " now " + i3);
            }
            fragment.mFragmentId = i3;
            fragment.mContainerId = i3;
        }
        c(new a(i4, fragment));
    }
}
