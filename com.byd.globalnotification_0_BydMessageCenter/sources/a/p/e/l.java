package a.p.e;

import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class l extends c0 {
    public static TimeInterpolator s;
    public ArrayList<RecyclerView.b0> h = new ArrayList<>();
    public ArrayList<RecyclerView.b0> i = new ArrayList<>();
    public ArrayList<b> j = new ArrayList<>();
    public ArrayList<a> k = new ArrayList<>();
    public ArrayList<ArrayList<RecyclerView.b0>> l = new ArrayList<>();
    public ArrayList<ArrayList<b>> m = new ArrayList<>();
    public ArrayList<ArrayList<a>> n = new ArrayList<>();
    public ArrayList<RecyclerView.b0> o = new ArrayList<>();
    public ArrayList<RecyclerView.b0> p = new ArrayList<>();
    public ArrayList<RecyclerView.b0> q = new ArrayList<>();
    public ArrayList<RecyclerView.b0> r = new ArrayList<>();

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public RecyclerView.b0 f1194a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public RecyclerView.b0 f1195b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f1196c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f1197d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f1198e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f1199f;

        public a(RecyclerView.b0 b0Var, RecyclerView.b0 b0Var2, int i, int i2, int i3, int i4) {
            this.f1194a = b0Var;
            this.f1195b = b0Var2;
            this.f1196c = i;
            this.f1197d = i2;
            this.f1198e = i3;
            this.f1199f = i4;
        }

        public String toString() {
            StringBuilder sbD = b.a.a.a.a.d("ChangeInfo{oldHolder=");
            sbD.append(this.f1194a);
            sbD.append(", newHolder=");
            sbD.append(this.f1195b);
            sbD.append(", fromX=");
            sbD.append(this.f1196c);
            sbD.append(", fromY=");
            sbD.append(this.f1197d);
            sbD.append(", toX=");
            sbD.append(this.f1198e);
            sbD.append(", toY=");
            sbD.append(this.f1199f);
            sbD.append('}');
            return sbD.toString();
        }
    }

    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public RecyclerView.b0 f1200a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1201b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f1202c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f1203d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f1204e;

        public b(RecyclerView.b0 b0Var, int i, int i2, int i3, int i4) {
            this.f1200a = b0Var;
            this.f1201b = i;
            this.f1202c = i2;
            this.f1203d = i3;
            this.f1204e = i4;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.j
    public boolean c(RecyclerView.b0 b0Var, List<Object> list) {
        return !list.isEmpty() || super.c(b0Var, list);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.j
    public void f(RecyclerView.b0 b0Var) {
        View view = b0Var.f1629a;
        view.animate().cancel();
        int size = this.j.size();
        while (true) {
            size--;
            if (size < 0) {
                break;
            }
            if (this.j.get(size).f1200a == b0Var) {
                view.setTranslationY(0.0f);
                view.setTranslationX(0.0f);
                d(b0Var);
                this.j.remove(size);
            }
        }
        m(this.k, b0Var);
        if (this.h.remove(b0Var)) {
            view.setAlpha(1.0f);
            d(b0Var);
        }
        if (this.i.remove(b0Var)) {
            view.setAlpha(1.0f);
            d(b0Var);
        }
        int size2 = this.n.size();
        while (true) {
            size2--;
            if (size2 < 0) {
                break;
            }
            ArrayList<a> arrayList = this.n.get(size2);
            m(arrayList, b0Var);
            if (arrayList.isEmpty()) {
                this.n.remove(size2);
            }
        }
        int size3 = this.m.size();
        while (true) {
            size3--;
            if (size3 < 0) {
                break;
            }
            ArrayList<b> arrayList2 = this.m.get(size3);
            int size4 = arrayList2.size();
            while (true) {
                size4--;
                if (size4 < 0) {
                    break;
                }
                if (arrayList2.get(size4).f1200a == b0Var) {
                    view.setTranslationY(0.0f);
                    view.setTranslationX(0.0f);
                    d(b0Var);
                    arrayList2.remove(size4);
                    if (arrayList2.isEmpty()) {
                        this.m.remove(size3);
                    }
                }
            }
        }
        int size5 = this.l.size();
        while (true) {
            size5--;
            if (size5 < 0) {
                this.q.remove(b0Var);
                this.o.remove(b0Var);
                this.r.remove(b0Var);
                this.p.remove(b0Var);
                l();
                return;
            }
            ArrayList<RecyclerView.b0> arrayList3 = this.l.get(size5);
            if (arrayList3.remove(b0Var)) {
                view.setAlpha(1.0f);
                d(b0Var);
                if (arrayList3.isEmpty()) {
                    this.l.remove(size5);
                }
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.j
    public void g() {
        int size = this.j.size();
        while (true) {
            size--;
            if (size < 0) {
                break;
            }
            b bVar = this.j.get(size);
            View view = bVar.f1200a.f1629a;
            view.setTranslationY(0.0f);
            view.setTranslationX(0.0f);
            d(bVar.f1200a);
            this.j.remove(size);
        }
        int size2 = this.h.size();
        while (true) {
            size2--;
            if (size2 < 0) {
                break;
            }
            d(this.h.get(size2));
            this.h.remove(size2);
        }
        int size3 = this.i.size();
        while (true) {
            size3--;
            if (size3 < 0) {
                break;
            }
            RecyclerView.b0 b0Var = this.i.get(size3);
            b0Var.f1629a.setAlpha(1.0f);
            d(b0Var);
            this.i.remove(size3);
        }
        int size4 = this.k.size();
        while (true) {
            size4--;
            if (size4 < 0) {
                break;
            }
            a aVar = this.k.get(size4);
            RecyclerView.b0 b0Var2 = aVar.f1194a;
            if (b0Var2 != null) {
                n(aVar, b0Var2);
            }
            RecyclerView.b0 b0Var3 = aVar.f1195b;
            if (b0Var3 != null) {
                n(aVar, b0Var3);
            }
        }
        this.k.clear();
        if (!h()) {
            return;
        }
        int size5 = this.m.size();
        while (true) {
            size5--;
            if (size5 < 0) {
                break;
            }
            ArrayList<b> arrayList = this.m.get(size5);
            int size6 = arrayList.size();
            while (true) {
                size6--;
                if (size6 >= 0) {
                    b bVar2 = arrayList.get(size6);
                    View view2 = bVar2.f1200a.f1629a;
                    view2.setTranslationY(0.0f);
                    view2.setTranslationX(0.0f);
                    d(bVar2.f1200a);
                    arrayList.remove(size6);
                    if (arrayList.isEmpty()) {
                        this.m.remove(arrayList);
                    }
                }
            }
        }
        int size7 = this.l.size();
        while (true) {
            size7--;
            if (size7 < 0) {
                break;
            }
            ArrayList<RecyclerView.b0> arrayList2 = this.l.get(size7);
            int size8 = arrayList2.size();
            while (true) {
                size8--;
                if (size8 >= 0) {
                    RecyclerView.b0 b0Var4 = arrayList2.get(size8);
                    b0Var4.f1629a.setAlpha(1.0f);
                    d(b0Var4);
                    arrayList2.remove(size8);
                    if (arrayList2.isEmpty()) {
                        this.l.remove(arrayList2);
                    }
                }
            }
        }
        int size9 = this.n.size();
        while (true) {
            size9--;
            if (size9 < 0) {
                k(this.q);
                k(this.p);
                k(this.o);
                k(this.r);
                e();
                return;
            }
            ArrayList<a> arrayList3 = this.n.get(size9);
            int size10 = arrayList3.size();
            while (true) {
                size10--;
                if (size10 >= 0) {
                    a aVar2 = arrayList3.get(size10);
                    RecyclerView.b0 b0Var5 = aVar2.f1194a;
                    if (b0Var5 != null) {
                        n(aVar2, b0Var5);
                    }
                    RecyclerView.b0 b0Var6 = aVar2.f1195b;
                    if (b0Var6 != null) {
                        n(aVar2, b0Var6);
                    }
                    if (arrayList3.isEmpty()) {
                        this.n.remove(arrayList3);
                    }
                }
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.j
    public boolean h() {
        return (this.i.isEmpty() && this.k.isEmpty() && this.j.isEmpty() && this.h.isEmpty() && this.p.isEmpty() && this.q.isEmpty() && this.o.isEmpty() && this.r.isEmpty() && this.m.isEmpty() && this.l.isEmpty() && this.n.isEmpty()) ? false : true;
    }

    @Override // a.p.e.c0
    public boolean j(RecyclerView.b0 b0Var, int i, int i2, int i3, int i4) {
        View view = b0Var.f1629a;
        int translationX = i + ((int) view.getTranslationX());
        int translationY = i2 + ((int) b0Var.f1629a.getTranslationY());
        o(b0Var);
        int i5 = i3 - translationX;
        int i6 = i4 - translationY;
        if (i5 == 0 && i6 == 0) {
            d(b0Var);
            return false;
        }
        if (i5 != 0) {
            view.setTranslationX(-i5);
        }
        if (i6 != 0) {
            view.setTranslationY(-i6);
        }
        this.j.add(new b(b0Var, translationX, translationY, i3, i4));
        return true;
    }

    public void k(List<RecyclerView.b0> list) {
        int size = list.size();
        while (true) {
            size--;
            if (size < 0) {
                return;
            } else {
                list.get(size).f1629a.animate().cancel();
            }
        }
    }

    public void l() {
        if (h()) {
            return;
        }
        e();
    }

    public final void m(List<a> list, RecyclerView.b0 b0Var) {
        int size = list.size();
        while (true) {
            size--;
            if (size < 0) {
                return;
            }
            a aVar = list.get(size);
            if (n(aVar, b0Var) && aVar.f1194a == null && aVar.f1195b == null) {
                list.remove(aVar);
            }
        }
    }

    public final boolean n(a aVar, RecyclerView.b0 b0Var) {
        if (aVar.f1195b == b0Var) {
            aVar.f1195b = null;
        } else {
            if (aVar.f1194a != b0Var) {
                return false;
            }
            aVar.f1194a = null;
        }
        b0Var.f1629a.setAlpha(1.0f);
        b0Var.f1629a.setTranslationX(0.0f);
        b0Var.f1629a.setTranslationY(0.0f);
        d(b0Var);
        return true;
    }

    public final void o(RecyclerView.b0 b0Var) {
        if (s == null) {
            s = new ValueAnimator().getInterpolator();
        }
        b0Var.f1629a.animate().setInterpolator(s);
        f(b0Var);
    }
}
