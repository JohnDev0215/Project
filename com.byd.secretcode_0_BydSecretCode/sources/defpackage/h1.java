package defpackage;

import android.view.View;
import android.view.animation.Interpolator;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: compiled from: ViewPropertyAnimatorCompatSet.java */
/* JADX INFO: loaded from: classes.dex */
public class h1 {
    public Interpolator c;
    public y9 d;
    public boolean e;
    public long b = -1;
    public final z9 f = new a();
    public final ArrayList<x9> a = new ArrayList<>();

    /* JADX INFO: compiled from: ViewPropertyAnimatorCompatSet.java */
    public class a extends z9 {
        public boolean a = false;
        public int b = 0;

        public a() {
        }

        @Override // defpackage.y9
        public void a(View view) {
            int i = this.b + 1;
            this.b = i;
            if (i == h1.this.a.size()) {
                y9 y9Var = h1.this.d;
                if (y9Var != null) {
                    y9Var.a(null);
                }
                d();
            }
        }

        @Override // defpackage.z9, defpackage.y9
        public void b(View view) {
            if (this.a) {
                return;
            }
            this.a = true;
            y9 y9Var = h1.this.d;
            if (y9Var != null) {
                y9Var.b(null);
            }
        }

        public void d() {
            this.b = 0;
            this.a = false;
            h1.this.b();
        }
    }

    public void a() {
        if (this.e) {
            Iterator<x9> it = this.a.iterator();
            while (it.hasNext()) {
                it.next().b();
            }
            this.e = false;
        }
    }

    public void b() {
        this.e = false;
    }

    public h1 c(x9 x9Var) {
        if (!this.e) {
            this.a.add(x9Var);
        }
        return this;
    }

    public h1 d(x9 x9Var, x9 x9Var2) {
        this.a.add(x9Var);
        x9Var2.h(x9Var.c());
        this.a.add(x9Var2);
        return this;
    }

    public h1 e(long j) {
        if (!this.e) {
            this.b = j;
        }
        return this;
    }

    public h1 f(Interpolator interpolator) {
        if (!this.e) {
            this.c = interpolator;
        }
        return this;
    }

    public h1 g(y9 y9Var) {
        if (!this.e) {
            this.d = y9Var;
        }
        return this;
    }

    public void h() {
        if (this.e) {
            return;
        }
        for (x9 x9Var : this.a) {
            long j = this.b;
            if (j >= 0) {
                x9Var.d(j);
            }
            Interpolator interpolator = this.c;
            if (interpolator != null) {
                x9Var.e(interpolator);
            }
            if (this.d != null) {
                x9Var.f(this.f);
            }
            x9Var.j();
        }
        this.e = true;
    }
}
