package androidx.fragment.app;

import androidx.lifecycle.d;
import java.util.ArrayList;

/* JADX INFO: compiled from: FragmentTransaction.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class i {
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;
    public boolean g;
    public String i;
    public int j;
    public CharSequence k;
    public int l;
    public CharSequence m;
    public ArrayList<String> n;
    public ArrayList<String> o;
    public ArrayList<Runnable> q;
    public final ArrayList<a> a = new ArrayList<>();
    public boolean h = true;
    public boolean p = false;

    /* JADX INFO: compiled from: FragmentTransaction.java */
    public static final class a {
        public int a;
        public Fragment b;
        public boolean c;
        public int d;
        public int e;
        public int f;
        public int g;
        public d.b h;
        public d.b i;

        public a() {
        }

        public a(int i, Fragment fragment) {
            this.a = i;
            this.b = fragment;
            this.c = false;
            d.b bVar = d.b.RESUMED;
            this.h = bVar;
            this.i = bVar;
        }

        public a(int i, Fragment fragment, int i2) {
            this.a = i;
            this.b = fragment;
            this.c = true;
            d.b bVar = d.b.RESUMED;
            this.h = bVar;
            this.i = bVar;
        }

        public a(Fragment fragment, d.b bVar) {
            this.a = 10;
            this.b = fragment;
            this.c = false;
            this.h = fragment.R;
            this.i = bVar;
        }
    }

    public final void b(a aVar) {
        this.a.add(aVar);
        aVar.d = this.b;
        aVar.e = this.c;
        aVar.f = this.d;
        aVar.g = this.e;
    }

    public final void c() {
        if (this.g) {
            throw new IllegalStateException("This transaction is already being added to the back stack");
        }
        this.h = false;
    }

    public abstract void d(int i, Fragment fragment, String str, int i2);

    public final void e(int i, Fragment fragment, String str) {
        if (i == 0) {
            throw new IllegalArgumentException("Must use non-zero containerViewId");
        }
        d(i, fragment, str, 2);
    }
}
