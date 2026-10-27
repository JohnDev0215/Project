package p;

import android.view.View;
import android.view.animation.Interpolator;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class vu2 {
    public Interpolator c;
    public wu2 d;
    public boolean e;
    public long b = -1;
    public final uu2 f = new uu2(this);
    public final ArrayList a = new ArrayList();

    public final void a() {
        if (this.e) {
            Iterator it = this.a.iterator();
            while (it.hasNext()) {
                ((tu2) it.next()).b();
            }
            this.e = false;
        }
    }

    public final void b() {
        View view;
        if (this.e) {
            return;
        }
        for (tu2 tu2Var : this.a) {
            long j = this.b;
            if (j >= 0) {
                tu2Var.c(j);
            }
            Interpolator interpolator = this.c;
            if (interpolator != null && (view = (View) tu2Var.a.get()) != null) {
                view.animate().setInterpolator(interpolator);
            }
            if (this.d != null) {
                tu2Var.d(this.f);
            }
            View view2 = (View) tu2Var.a.get();
            if (view2 != null) {
                view2.animate().start();
            }
        }
        this.e = true;
    }
}
