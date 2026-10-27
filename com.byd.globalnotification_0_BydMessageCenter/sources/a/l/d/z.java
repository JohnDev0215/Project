package a.l.d;

import a.n.g;
import androidx.fragment.app.Fragment;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public abstract class z {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f1062b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f1063c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f1064d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f1065e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f1066f;
    public boolean g;
    public String h;
    public int i;
    public CharSequence j;
    public int k;
    public CharSequence l;
    public ArrayList<String> m;
    public ArrayList<String> n;
    public ArrayList<Runnable> p;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ArrayList<a> f1061a = new ArrayList<>();
    public boolean o = false;

    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f1067a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public Fragment f1068b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f1069c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f1070d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f1071e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f1072f;
        public g.b g;
        public g.b h;

        public a() {
        }

        public a(int i, Fragment fragment) {
            this.f1067a = i;
            this.f1068b = fragment;
            g.b bVar = g.b.RESUMED;
            this.g = bVar;
            this.h = bVar;
        }
    }

    public z(n nVar, ClassLoader classLoader) {
    }

    public void b(a aVar) {
        this.f1061a.add(aVar);
        aVar.f1069c = this.f1062b;
        aVar.f1070d = this.f1063c;
        aVar.f1071e = this.f1064d;
        aVar.f1072f = this.f1065e;
    }

    public void c(int i, Fragment fragment, String str, int i2) {
        Class<?> cls = fragment.getClass();
        int modifiers = cls.getModifiers();
        if (cls.isAnonymousClass() || !Modifier.isPublic(modifiers) || (cls.isMemberClass() && !Modifier.isStatic(modifiers))) {
            StringBuilder sbD = b.a.a.a.a.d("Fragment ");
            sbD.append(cls.getCanonicalName());
            sbD.append(" must be a public static class to be  properly recreated from instance state.");
            throw new IllegalStateException(sbD.toString());
        }
        if (str != null) {
            String str2 = fragment.x;
            if (str2 != null && !str.equals(str2)) {
                throw new IllegalStateException("Can't change tag of fragment " + fragment + ": was " + fragment.x + " now " + str);
            }
            fragment.x = str;
        }
        if (i != 0) {
            if (i == -1) {
                throw new IllegalArgumentException("Can't add fragment " + fragment + " with tag " + str + " to container view with no id");
            }
            int i3 = fragment.v;
            if (i3 != 0 && i3 != i) {
                throw new IllegalStateException("Can't change container ID of fragment " + fragment + ": was " + fragment.v + " now " + i);
            }
            fragment.v = i;
            fragment.w = i;
        }
        b(new a(i2, fragment));
    }
}
