package androidx.appcompat.view;

import android.view.View;
import android.view.animation.Interpolator;
import defpackage.in3;
import defpackage.jn3;
import defpackage.kn3;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class ViewPropertyAnimatorCompatSet {
    public Interpolator c;
    public jn3 d;
    public boolean e;
    public long b = -1;
    public final kn3 f = new kn3() { // from class: androidx.appcompat.view.ViewPropertyAnimatorCompatSet.1
        public boolean a = false;
        public int b = 0;

        @Override // defpackage.kn3, defpackage.jn3
        public final void a() {
            int i = this.b + 1;
            this.b = i;
            ViewPropertyAnimatorCompatSet viewPropertyAnimatorCompatSet = ViewPropertyAnimatorCompatSet.this;
            if (i == viewPropertyAnimatorCompatSet.a.size()) {
                jn3 jn3Var = viewPropertyAnimatorCompatSet.d;
                if (jn3Var != null) {
                    jn3Var.a();
                }
                this.b = 0;
                this.a = false;
                viewPropertyAnimatorCompatSet.e = false;
            }
        }

        @Override // defpackage.kn3, defpackage.jn3
        public final void c() {
            if (this.a) {
                return;
            }
            this.a = true;
            jn3 jn3Var = ViewPropertyAnimatorCompatSet.this.d;
            if (jn3Var != null) {
                jn3Var.c();
            }
        }
    };
    public final ArrayList<in3> a = new ArrayList<>();

    public final void a() {
        if (this.e) {
            Iterator<in3> it = this.a.iterator();
            while (it.hasNext()) {
                it.next().b();
            }
            this.e = false;
        }
    }

    public final void b() {
        View view;
        if (this.e) {
            return;
        }
        for (in3 in3Var : this.a) {
            long j = this.b;
            if (j >= 0) {
                in3Var.c(j);
            }
            Interpolator interpolator = this.c;
            if (interpolator != null && (view = in3Var.a.get()) != null) {
                view.animate().setInterpolator(interpolator);
            }
            if (this.d != null) {
                in3Var.d(this.f);
            }
            View view2 = in3Var.a.get();
            if (view2 != null) {
                view2.animate().start();
            }
        }
        this.e = true;
    }
}
