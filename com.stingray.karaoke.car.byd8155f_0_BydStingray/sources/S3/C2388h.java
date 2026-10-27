package s3;

import F1.C0254m;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.view.View;
import java.util.ArrayList;

/* JADX INFO: renamed from: s3.h, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2388h extends AbstractC2364C {

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static TimeInterpolator f22280s;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f22281g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public ArrayList f22282h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public ArrayList f22283i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public ArrayList f22284j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public ArrayList f22285k;
    public ArrayList l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public ArrayList f22286m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public ArrayList f22287n;
    public ArrayList o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public ArrayList f22288p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public ArrayList f22289q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public ArrayList f22290r;

    public static void h(ArrayList arrayList) {
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            ((U) arrayList.get(size)).f22219a.animate().cancel();
        }
    }

    @Override // s3.AbstractC2364C
    public final boolean a(U u4, U u10, C0254m c0254m, C0254m c0254m2) {
        int i7;
        int i10;
        int i11 = c0254m.f2343b;
        int i12 = c0254m.c;
        if (u10.p()) {
            int i13 = c0254m.f2343b;
            i10 = c0254m.c;
            i7 = i13;
        } else {
            i7 = c0254m2.f2343b;
            i10 = c0254m2.c;
        }
        if (u4 == u10) {
            return g(u4, i11, i12, i7, i10);
        }
        View view = u4.f22219a;
        float translationX = view.getTranslationX();
        float translationY = view.getTranslationY();
        float alpha = view.getAlpha();
        l(u4);
        view.setTranslationX(translationX);
        view.setTranslationY(translationY);
        view.setAlpha(alpha);
        l(u10);
        float f10 = -((int) ((i7 - i11) - translationX));
        View view2 = u10.f22219a;
        view2.setTranslationX(f10);
        view2.setTranslationY(-((int) ((i10 - i12) - translationY)));
        view2.setAlpha(0.0f);
        ArrayList arrayList = this.f22285k;
        C2386f c2386f = new C2386f();
        c2386f.f22273a = u4;
        c2386f.f22274b = u10;
        c2386f.c = i11;
        c2386f.d = i12;
        c2386f.f22275e = i7;
        c2386f.f22276f = i10;
        arrayList.add(c2386f);
        return true;
    }

    @Override // s3.AbstractC2364C
    public final void d(U u4) {
        View view = u4.f22219a;
        view.animate().cancel();
        ArrayList arrayList = this.f22284j;
        int size = arrayList.size();
        while (true) {
            size--;
            if (size < 0) {
                break;
            }
            if (((C2387g) arrayList.get(size)).f22277a == u4) {
                view.setTranslationY(0.0f);
                view.setTranslationX(0.0f);
                c(u4);
                arrayList.remove(size);
            }
        }
        j(this.f22285k, u4);
        if (this.f22282h.remove(u4)) {
            view.setAlpha(1.0f);
            c(u4);
        }
        if (this.f22283i.remove(u4)) {
            view.setAlpha(1.0f);
            c(u4);
        }
        ArrayList arrayList2 = this.f22287n;
        for (int size2 = arrayList2.size() - 1; size2 >= 0; size2--) {
            ArrayList arrayList3 = (ArrayList) arrayList2.get(size2);
            j(arrayList3, u4);
            if (arrayList3.isEmpty()) {
                arrayList2.remove(size2);
            }
        }
        ArrayList arrayList4 = this.f22286m;
        for (int size3 = arrayList4.size() - 1; size3 >= 0; size3--) {
            ArrayList arrayList5 = (ArrayList) arrayList4.get(size3);
            int size4 = arrayList5.size() - 1;
            while (true) {
                if (size4 < 0) {
                    break;
                }
                if (((C2387g) arrayList5.get(size4)).f22277a == u4) {
                    view.setTranslationY(0.0f);
                    view.setTranslationX(0.0f);
                    c(u4);
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
            if (arrayList7.remove(u4)) {
                view.setAlpha(1.0f);
                c(u4);
                if (arrayList7.isEmpty()) {
                    arrayList6.remove(size5);
                }
            }
        }
        this.f22289q.remove(u4);
        this.o.remove(u4);
        this.f22290r.remove(u4);
        this.f22288p.remove(u4);
        i();
    }

    @Override // s3.AbstractC2364C
    public final void e() {
        ArrayList arrayList = this.f22284j;
        int size = arrayList.size();
        while (true) {
            size--;
            if (size < 0) {
                break;
            }
            C2387g c2387g = (C2387g) arrayList.get(size);
            View view = c2387g.f22277a.f22219a;
            view.setTranslationY(0.0f);
            view.setTranslationX(0.0f);
            c(c2387g.f22277a);
            arrayList.remove(size);
        }
        ArrayList arrayList2 = this.f22282h;
        for (int size2 = arrayList2.size() - 1; size2 >= 0; size2--) {
            c((U) arrayList2.get(size2));
            arrayList2.remove(size2);
        }
        ArrayList arrayList3 = this.f22283i;
        int size3 = arrayList3.size();
        while (true) {
            size3--;
            if (size3 < 0) {
                break;
            }
            U u4 = (U) arrayList3.get(size3);
            u4.f22219a.setAlpha(1.0f);
            c(u4);
            arrayList3.remove(size3);
        }
        ArrayList arrayList4 = this.f22285k;
        for (int size4 = arrayList4.size() - 1; size4 >= 0; size4--) {
            C2386f c2386f = (C2386f) arrayList4.get(size4);
            U u10 = c2386f.f22273a;
            if (u10 != null) {
                k(c2386f, u10);
            }
            U u11 = c2386f.f22274b;
            if (u11 != null) {
                k(c2386f, u11);
            }
        }
        arrayList4.clear();
        if (f()) {
            ArrayList arrayList5 = this.f22286m;
            for (int size5 = arrayList5.size() - 1; size5 >= 0; size5--) {
                ArrayList arrayList6 = (ArrayList) arrayList5.get(size5);
                for (int size6 = arrayList6.size() - 1; size6 >= 0; size6--) {
                    C2387g c2387g2 = (C2387g) arrayList6.get(size6);
                    View view2 = c2387g2.f22277a.f22219a;
                    view2.setTranslationY(0.0f);
                    view2.setTranslationX(0.0f);
                    c(c2387g2.f22277a);
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
                    U u12 = (U) arrayList8.get(size8);
                    u12.f22219a.setAlpha(1.0f);
                    c(u12);
                    arrayList8.remove(size8);
                    if (arrayList8.isEmpty()) {
                        arrayList7.remove(arrayList8);
                    }
                }
            }
            ArrayList arrayList9 = this.f22287n;
            for (int size9 = arrayList9.size() - 1; size9 >= 0; size9--) {
                ArrayList arrayList10 = (ArrayList) arrayList9.get(size9);
                for (int size10 = arrayList10.size() - 1; size10 >= 0; size10--) {
                    C2386f c2386f2 = (C2386f) arrayList10.get(size10);
                    U u13 = c2386f2.f22273a;
                    if (u13 != null) {
                        k(c2386f2, u13);
                    }
                    U u14 = c2386f2.f22274b;
                    if (u14 != null) {
                        k(c2386f2, u14);
                    }
                    if (arrayList10.isEmpty()) {
                        arrayList9.remove(arrayList10);
                    }
                }
            }
            h(this.f22289q);
            h(this.f22288p);
            h(this.o);
            h(this.f22290r);
            ArrayList arrayList11 = this.f22172b;
            if (arrayList11.size() > 0) {
                arrayList11.get(0).getClass();
                throw new ClassCastException();
            }
            arrayList11.clear();
        }
    }

    @Override // s3.AbstractC2364C
    public final boolean f() {
        return (this.f22283i.isEmpty() && this.f22285k.isEmpty() && this.f22284j.isEmpty() && this.f22282h.isEmpty() && this.f22288p.isEmpty() && this.f22289q.isEmpty() && this.o.isEmpty() && this.f22290r.isEmpty() && this.f22286m.isEmpty() && this.l.isEmpty() && this.f22287n.isEmpty()) ? false : true;
    }

    public final boolean g(U u4, int i7, int i10, int i11, int i12) {
        View view = u4.f22219a;
        int translationX = i7 + ((int) view.getTranslationX());
        int translationY = i10 + ((int) u4.f22219a.getTranslationY());
        l(u4);
        int i13 = i11 - translationX;
        int i14 = i12 - translationY;
        if (i13 == 0 && i14 == 0) {
            c(u4);
            return false;
        }
        if (i13 != 0) {
            view.setTranslationX(-i13);
        }
        if (i14 != 0) {
            view.setTranslationY(-i14);
        }
        ArrayList arrayList = this.f22284j;
        C2387g c2387g = new C2387g();
        c2387g.f22277a = u4;
        c2387g.f22278b = translationX;
        c2387g.c = translationY;
        c2387g.d = i11;
        c2387g.f22279e = i12;
        arrayList.add(c2387g);
        return true;
    }

    public final void i() {
        if (f()) {
            return;
        }
        ArrayList arrayList = this.f22172b;
        if (arrayList.size() <= 0) {
            arrayList.clear();
        } else {
            arrayList.get(0).getClass();
            throw new ClassCastException();
        }
    }

    public final void j(ArrayList arrayList, U u4) {
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            C2386f c2386f = (C2386f) arrayList.get(size);
            if (k(c2386f, u4) && c2386f.f22273a == null && c2386f.f22274b == null) {
                arrayList.remove(c2386f);
            }
        }
    }

    public final boolean k(C2386f c2386f, U u4) {
        if (c2386f.f22274b == u4) {
            c2386f.f22274b = null;
        } else {
            if (c2386f.f22273a != u4) {
                return false;
            }
            c2386f.f22273a = null;
        }
        u4.f22219a.setAlpha(1.0f);
        View view = u4.f22219a;
        view.setTranslationX(0.0f);
        view.setTranslationY(0.0f);
        c(u4);
        return true;
    }

    public final void l(U u4) {
        if (f22280s == null) {
            f22280s = new ValueAnimator().getInterpolator();
        }
        u4.f22219a.animate().setInterpolator(f22280s);
        d(u4);
    }
}
