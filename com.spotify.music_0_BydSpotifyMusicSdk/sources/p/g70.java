package p;

import android.view.View;
import android.view.ViewPropertyAnimator;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class g70 implements Runnable {
    public final /* synthetic */ int c;
    public final /* synthetic */ ArrayList e;
    public final /* synthetic */ m70 k;

    public /* synthetic */ g70(m70 m70Var, ArrayList arrayList, int i) {
        this.c = i;
        this.k = m70Var;
        this.e = arrayList;
    }

    @Override // java.lang.Runnable
    public final void run() {
        long j;
        char c;
        int i = this.c;
        m70 m70Var = this.k;
        ArrayList<l70> arrayList = this.e;
        switch (i) {
            case 0:
                for (l70 l70Var : arrayList) {
                    n22 n22Var = l70Var.a;
                    m70Var.getClass();
                    View view = n22Var.a;
                    int i2 = l70Var.d - l70Var.b;
                    int i3 = l70Var.e - l70Var.c;
                    if (i2 != 0) {
                        view.animate().translationX(0.0f);
                    }
                    if (i3 != 0) {
                        view.animate().translationY(0.0f);
                    }
                    ViewPropertyAnimator viewPropertyAnimatorAnimate = view.animate();
                    m70Var.f155p.add(n22Var);
                    viewPropertyAnimatorAnimate.setDuration(m70Var.e).setListener(new i70(m70Var, n22Var, i2, view, i3, viewPropertyAnimatorAnimate)).start();
                }
                arrayList.clear();
                m70Var.m.remove(arrayList);
                break;
            case 1:
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    k70 k70Var = (k70) it.next();
                    m70Var.getClass();
                    n22 n22Var2 = k70Var.a;
                    View view2 = n22Var2 == null ? null : n22Var2.a;
                    n22 n22Var3 = k70Var.b;
                    View view3 = n22Var3 != null ? n22Var3.a : null;
                    ArrayList arrayList2 = m70Var.r;
                    long j2 = m70Var.f;
                    if (view2 != null) {
                        ViewPropertyAnimator duration = view2.animate().setDuration(j2);
                        arrayList2.add(k70Var.a);
                        duration.translationX(k70Var.e - k70Var.c);
                        duration.translationY(k70Var.f - k70Var.d);
                        j = j2;
                        duration.alpha(0.0f).setListener(new j70(m70Var, k70Var, duration, view2, 0)).start();
                    } else {
                        j = j2;
                    }
                    if (view3 != null) {
                        ViewPropertyAnimator viewPropertyAnimatorAnimate2 = view3.animate();
                        arrayList2.add(k70Var.b);
                        c = 0;
                        viewPropertyAnimatorAnimate2.translationX(0.0f).translationY(0.0f).setDuration(j).alpha(1.0f).setListener(new j70(m70Var, k70Var, viewPropertyAnimatorAnimate2, view3, 1)).start();
                    } else {
                        c = 0;
                    }
                }
                arrayList.clear();
                m70Var.n.remove(arrayList);
                break;
            default:
                Iterator it2 = arrayList.iterator();
                while (it2.hasNext()) {
                    n22 n22Var4 = (n22) it2.next();
                    m70Var.getClass();
                    View view4 = n22Var4.a;
                    ViewPropertyAnimator viewPropertyAnimatorAnimate3 = view4.animate();
                    m70Var.o.add(n22Var4);
                    viewPropertyAnimatorAnimate3.alpha(1.0f).setDuration(m70Var.c).setListener(new h70(m70Var, n22Var4, view4, viewPropertyAnimatorAnimate3)).start();
                }
                arrayList.clear();
                m70Var.l.remove(arrayList);
                break;
        }
    }
}
