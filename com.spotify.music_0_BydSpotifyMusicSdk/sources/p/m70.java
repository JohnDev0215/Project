package p;

import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.view.View;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class m70 extends v12 {
    public static TimeInterpolator s;
    public boolean g;
    public ArrayList h;
    public ArrayList i;
    public ArrayList j;
    public ArrayList k;
    public ArrayList l;
    public ArrayList m;
    public ArrayList n;
    public ArrayList o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public ArrayList f155p;
    public ArrayList q;
    public ArrayList r;

    public static void h(ArrayList arrayList) {
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            ((n22) arrayList.get(size)).a.animate().cancel();
        }
    }

    @Override // p.v12
    public final boolean a(n22 n22Var, n22 n22Var2, u12 u12Var, u12 u12Var2) {
        int i;
        int i2;
        int i3 = u12Var.a;
        int i4 = u12Var.b;
        if (n22Var2.q()) {
            int i5 = u12Var.a;
            i2 = u12Var.b;
            i = i5;
        } else {
            i = u12Var2.a;
            i2 = u12Var2.b;
        }
        if (n22Var == n22Var2) {
            return g(n22Var, i3, i4, i, i2);
        }
        View view = n22Var.a;
        float translationX = view.getTranslationX();
        float translationY = view.getTranslationY();
        float alpha = view.getAlpha();
        l(n22Var);
        view.setTranslationX(translationX);
        view.setTranslationY(translationY);
        view.setAlpha(alpha);
        l(n22Var2);
        float f = -((int) ((i - i3) - translationX));
        View view2 = n22Var2.a;
        view2.setTranslationX(f);
        view2.setTranslationY(-((int) ((i2 - i4) - translationY)));
        view2.setAlpha(0.0f);
        ArrayList arrayList = this.k;
        k70 k70Var = new k70();
        k70Var.a = n22Var;
        k70Var.b = n22Var2;
        k70Var.c = i3;
        k70Var.d = i4;
        k70Var.e = i;
        k70Var.f = i2;
        arrayList.add(k70Var);
        return true;
    }

    @Override // p.v12
    public final void d(n22 n22Var) {
        View view = n22Var.a;
        view.animate().cancel();
        ArrayList arrayList = this.j;
        int size = arrayList.size();
        while (true) {
            size--;
            if (size < 0) {
                break;
            }
            if (((l70) arrayList.get(size)).a == n22Var) {
                view.setTranslationY(0.0f);
                view.setTranslationX(0.0f);
                c(n22Var);
                arrayList.remove(size);
            }
        }
        j(n22Var, this.k);
        if (this.h.remove(n22Var)) {
            view.setAlpha(1.0f);
            c(n22Var);
        }
        if (this.i.remove(n22Var)) {
            view.setAlpha(1.0f);
            c(n22Var);
        }
        ArrayList arrayList2 = this.n;
        for (int size2 = arrayList2.size() - 1; size2 >= 0; size2--) {
            ArrayList arrayList3 = (ArrayList) arrayList2.get(size2);
            j(n22Var, arrayList3);
            if (arrayList3.isEmpty()) {
                arrayList2.remove(size2);
            }
        }
        ArrayList arrayList4 = this.m;
        for (int size3 = arrayList4.size() - 1; size3 >= 0; size3--) {
            ArrayList arrayList5 = (ArrayList) arrayList4.get(size3);
            int size4 = arrayList5.size() - 1;
            while (true) {
                if (size4 < 0) {
                    break;
                }
                if (((l70) arrayList5.get(size4)).a == n22Var) {
                    view.setTranslationY(0.0f);
                    view.setTranslationX(0.0f);
                    c(n22Var);
                    arrayList5.remove(size4);
                    if (arrayList5.isEmpty()) {
                        arrayList4.remove(size3);
                    }
                } else {
                    size4--;
                }
            }
        }
        ArrayList arrayList6 = this.l;
        for (int size5 = arrayList6.size() - 1; size5 >= 0; size5--) {
            ArrayList arrayList7 = (ArrayList) arrayList6.get(size5);
            if (arrayList7.remove(n22Var)) {
                view.setAlpha(1.0f);
                c(n22Var);
                if (arrayList7.isEmpty()) {
                    arrayList6.remove(size5);
                }
            }
        }
        this.q.remove(n22Var);
        this.o.remove(n22Var);
        this.r.remove(n22Var);
        this.f155p.remove(n22Var);
        i();
    }

    @Override // p.v12
    public final void e() {
        ArrayList arrayList = this.j;
        int size = arrayList.size();
        while (true) {
            size--;
            if (size < 0) {
                break;
            }
            l70 l70Var = (l70) arrayList.get(size);
            View view = l70Var.a.a;
            view.setTranslationY(0.0f);
            view.setTranslationX(0.0f);
            c(l70Var.a);
            arrayList.remove(size);
        }
        ArrayList arrayList2 = this.h;
        for (int size2 = arrayList2.size() - 1; size2 >= 0; size2--) {
            c((n22) arrayList2.get(size2));
            arrayList2.remove(size2);
        }
        ArrayList arrayList3 = this.i;
        int size3 = arrayList3.size();
        while (true) {
            size3--;
            if (size3 < 0) {
                break;
            }
            n22 n22Var = (n22) arrayList3.get(size3);
            n22Var.a.setAlpha(1.0f);
            c(n22Var);
            arrayList3.remove(size3);
        }
        ArrayList arrayList4 = this.k;
        for (int size4 = arrayList4.size() - 1; size4 >= 0; size4--) {
            k70 k70Var = (k70) arrayList4.get(size4);
            n22 n22Var2 = k70Var.a;
            if (n22Var2 != null) {
                k(k70Var, n22Var2);
            }
            n22 n22Var3 = k70Var.b;
            if (n22Var3 != null) {
                k(k70Var, n22Var3);
            }
        }
        arrayList4.clear();
        if (f()) {
            ArrayList arrayList5 = this.m;
            for (int size5 = arrayList5.size() - 1; size5 >= 0; size5--) {
                ArrayList arrayList6 = (ArrayList) arrayList5.get(size5);
                for (int size6 = arrayList6.size() - 1; size6 >= 0; size6--) {
                    l70 l70Var2 = (l70) arrayList6.get(size6);
                    View view2 = l70Var2.a.a;
                    view2.setTranslationY(0.0f);
                    view2.setTranslationX(0.0f);
                    c(l70Var2.a);
                    arrayList6.remove(size6);
                    if (arrayList6.isEmpty()) {
                        arrayList5.remove(arrayList6);
                    }
                }
            }
            ArrayList arrayList7 = this.l;
            for (int size7 = arrayList7.size() - 1; size7 >= 0; size7--) {
                ArrayList arrayList8 = (ArrayList) arrayList7.get(size7);
                for (int size8 = arrayList8.size() - 1; size8 >= 0; size8--) {
                    n22 n22Var4 = (n22) arrayList8.get(size8);
                    n22Var4.a.setAlpha(1.0f);
                    c(n22Var4);
                    arrayList8.remove(size8);
                    if (arrayList8.isEmpty()) {
                        arrayList7.remove(arrayList8);
                    }
                }
            }
            ArrayList arrayList9 = this.n;
            for (int size9 = arrayList9.size() - 1; size9 >= 0; size9--) {
                ArrayList arrayList10 = (ArrayList) arrayList9.get(size9);
                for (int size10 = arrayList10.size() - 1; size10 >= 0; size10--) {
                    k70 k70Var2 = (k70) arrayList10.get(size10);
                    n22 n22Var5 = k70Var2.a;
                    if (n22Var5 != null) {
                        k(k70Var2, n22Var5);
                    }
                    n22 n22Var6 = k70Var2.b;
                    if (n22Var6 != null) {
                        k(k70Var2, n22Var6);
                    }
                    if (arrayList10.isEmpty()) {
                        arrayList9.remove(arrayList10);
                    }
                }
            }
            h(this.q);
            h(this.f155p);
            h(this.o);
            h(this.r);
            ArrayList arrayList11 = this.b;
            if (arrayList11.size() > 0) {
                u4.z(arrayList11.get(0));
                throw null;
            }
            arrayList11.clear();
        }
    }

    @Override // p.v12
    public final boolean f() {
        return (this.i.isEmpty() && this.k.isEmpty() && this.j.isEmpty() && this.h.isEmpty() && this.f155p.isEmpty() && this.q.isEmpty() && this.o.isEmpty() && this.r.isEmpty() && this.m.isEmpty() && this.l.isEmpty() && this.n.isEmpty()) ? false : true;
    }

    public final boolean g(n22 n22Var, int i, int i2, int i3, int i4) {
        View view = n22Var.a;
        int translationX = i + ((int) view.getTranslationX());
        int translationY = i2 + ((int) n22Var.a.getTranslationY());
        l(n22Var);
        int i5 = i3 - translationX;
        int i6 = i4 - translationY;
        if (i5 == 0 && i6 == 0) {
            c(n22Var);
            return false;
        }
        if (i5 != 0) {
            view.setTranslationX(-i5);
        }
        if (i6 != 0) {
            view.setTranslationY(-i6);
        }
        ArrayList arrayList = this.j;
        l70 l70Var = new l70();
        l70Var.a = n22Var;
        l70Var.b = translationX;
        l70Var.c = translationY;
        l70Var.d = i3;
        l70Var.e = i4;
        arrayList.add(l70Var);
        return true;
    }

    public final void i() {
        if (f()) {
            return;
        }
        ArrayList arrayList = this.b;
        if (arrayList.size() <= 0) {
            arrayList.clear();
        } else {
            u4.z(arrayList.get(0));
            throw null;
        }
    }

    public final void j(n22 n22Var, ArrayList arrayList) {
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            k70 k70Var = (k70) arrayList.get(size);
            if (k(k70Var, n22Var) && k70Var.a == null && k70Var.b == null) {
                arrayList.remove(k70Var);
            }
        }
    }

    public final boolean k(k70 k70Var, n22 n22Var) {
        if (k70Var.b == n22Var) {
            k70Var.b = null;
        } else {
            if (k70Var.a != n22Var) {
                return false;
            }
            k70Var.a = null;
        }
        n22Var.a.setAlpha(1.0f);
        View view = n22Var.a;
        view.setTranslationX(0.0f);
        view.setTranslationY(0.0f);
        c(n22Var);
        return true;
    }

    public final void l(n22 n22Var) {
        if (s == null) {
            s = new ValueAnimator().getInterpolator();
        }
        n22Var.a.animate().setInterpolator(s);
        d(n22Var);
    }
}
