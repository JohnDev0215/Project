package k3;

import F1.C0243b;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Set;
import l3.C1737B;
import l3.C1741F;
import s3.AbstractC2364C;
import s3.AbstractC2367F;
import s3.AbstractC2405y;
import s3.C2371J;
import s3.C2372K;
import s3.C2388h;
import s3.C2393m;

/* JADX INFO: renamed from: k3.i0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1648i0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final List f17992a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f17993b;
    public int c;
    public final Object d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Object f17994e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Object f17995f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public Object f17996g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final Object f17997h;

    public C1648i0() {
        this.d = null;
        this.f17994e = null;
        this.f17995f = null;
        this.f17992a = Collections.emptyList();
        this.f17996g = null;
        this.f17993b = 0;
        this.c = 0;
        this.f17997h = Bundle.EMPTY;
    }

    public void a(s3.U u4, boolean z6) {
        RecyclerView.g(u4);
        RecyclerView recyclerView = (RecyclerView) this.f17997h;
        s3.W w10 = recyclerView.f12154v0;
        View view = u4.f22219a;
        if (w10 != null) {
            s3.V v10 = w10.f22235e;
            F1.H.g(view, v10 != null ? (C0243b) v10.f22234e.remove(view) : null);
        }
        if (z6) {
            ArrayList arrayList = recyclerView.f12159y;
            if (arrayList.size() > 0) {
                arrayList.get(0).getClass();
                throw new ClassCastException();
            }
            if (recyclerView.f12140o0 != null) {
                recyclerView.f12145r.x0(u4);
            }
        }
        u4.f22233s = null;
        u4.f22232r = null;
        C2372K c2372kC = c();
        c2372kC.getClass();
        int i7 = u4.f22222f;
        ArrayList arrayList2 = c2372kC.a(i7).f22192a;
        if (((C2371J) c2372kC.f22194a.get(i7)).f22193b <= arrayList2.size()) {
            C7.a.z(view);
        } else {
            u4.n();
            arrayList2.add(u4);
        }
    }

    public int b(int i7) {
        RecyclerView recyclerView = (RecyclerView) this.f17997h;
        if (i7 >= 0 && i7 < recyclerView.f12140o0.b()) {
            return !recyclerView.f12140o0.f22206g ? i7 : recyclerView.f12141p.z(i7, 0);
        }
        StringBuilder sbJ = M5.b.j("invalid position ", i7, ". State item count is ");
        sbJ.append(recyclerView.f12140o0.b());
        sbJ.append(recyclerView.w());
        throw new IndexOutOfBoundsException(sbJ.toString());
    }

    public C2372K c() {
        if (((C2372K) this.f17996g) == null) {
            C2372K c2372k = new C2372K();
            c2372k.f22194a = new SparseArray();
            c2372k.f22195b = 0;
            c2372k.c = Collections.newSetFromMap(new IdentityHashMap());
            this.f17996g = c2372k;
            d();
        }
        return (C2372K) this.f17996g;
    }

    public void d() {
        RecyclerView recyclerView;
        AbstractC2405y abstractC2405y;
        C2372K c2372k = (C2372K) this.f17996g;
        if (c2372k == null || (abstractC2405y = (recyclerView = (RecyclerView) this.f17997h).f12155w) == null || !recyclerView.f12099C) {
            return;
        }
        c2372k.c.add(abstractC2405y);
    }

    public void e(AbstractC2405y abstractC2405y, boolean z6) {
        C2372K c2372k = (C2372K) this.f17996g;
        if (c2372k == null) {
            return;
        }
        Set set = c2372k.c;
        set.remove(abstractC2405y);
        if (set.size() != 0 || z6) {
            return;
        }
        int i7 = 0;
        while (true) {
            SparseArray sparseArray = c2372k.f22194a;
            if (i7 >= sparseArray.size()) {
                return;
            }
            ArrayList arrayList = ((C2371J) sparseArray.get(sparseArray.keyAt(i7))).f22192a;
            for (int i10 = 0; i10 < arrayList.size(); i10++) {
                C7.a.z(((s3.U) arrayList.get(i10)).f22219a);
            }
            i7++;
        }
    }

    public void f() {
        ArrayList arrayList = (ArrayList) this.f17995f;
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            g(size);
        }
        arrayList.clear();
        if (RecyclerView.f12093K0) {
            C2393m c2393m = ((RecyclerView) this.f17997h).f12139n0;
            int[] iArr = c2393m.f22320a;
            if (iArr != null) {
                Arrays.fill(iArr, -1);
            }
            c2393m.d = 0;
        }
    }

    public void g(int i7) {
        ArrayList arrayList = (ArrayList) this.f17995f;
        a((s3.U) arrayList.get(i7), true);
        arrayList.remove(i7);
    }

    public void h(View view) {
        s3.U uG = RecyclerView.G(view);
        boolean zK = uG.k();
        RecyclerView recyclerView = (RecyclerView) this.f17997h;
        if (zK) {
            recyclerView.removeDetachedView(view, false);
        }
        if (uG.j()) {
            uG.f22229n.l(uG);
        } else if (uG.q()) {
            uG.f22226j &= -33;
        }
        i(uG);
        if (recyclerView.f12121U == null || uG.h()) {
            return;
        }
        recyclerView.f12121U.d(uG);
    }

    /* JADX WARN: Code restructure failed: missing block: B:46:0x0097, code lost:
    
        r6 = r6 - 1;
     */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x007c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void i(s3.U r13) {
        /*
            Method dump skipped, instruction units count: 291
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: k3.C1648i0.i(s3.U):void");
    }

    public void j(View view) {
        AbstractC2364C abstractC2364C;
        s3.U uG = RecyclerView.G(view);
        boolean zD = uG.d(12);
        RecyclerView recyclerView = (RecyclerView) this.f17997h;
        if (!zD && uG.l() && (abstractC2364C = recyclerView.f12121U) != null) {
            C2388h c2388h = (C2388h) abstractC2364C;
            if (uG.c().isEmpty() && c2388h.f22281g && !uG.g()) {
                if (((ArrayList) this.f17994e) == null) {
                    this.f17994e = new ArrayList();
                }
                uG.f22229n = this;
                uG.o = true;
                ((ArrayList) this.f17994e).add(uG);
                return;
            }
        }
        if (uG.g() && !uG.i() && !recyclerView.f12155w.f22364b) {
            throw new IllegalArgumentException("Called scrap view with an invalid view. Invalid views cannot be reused from scrap, they should rebound from recycler pool." + recyclerView.w());
        }
        uG.f22229n = this;
        uG.o = false;
        ((ArrayList) this.d).add(uG);
    }

    /* JADX WARN: Removed duplicated region for block: B:110:0x01d9  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x01db  */
    /* JADX WARN: Removed duplicated region for block: B:176:0x0317  */
    /* JADX WARN: Removed duplicated region for block: B:178:0x031a  */
    /* JADX WARN: Removed duplicated region for block: B:241:0x041b  */
    /* JADX WARN: Removed duplicated region for block: B:303:0x0508  */
    /* JADX WARN: Removed duplicated region for block: B:304:0x0512  */
    /* JADX WARN: Removed duplicated region for block: B:312:0x052c  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0128  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x013c  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x014b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public s3.U k(long r26, int r28) {
        /*
            Method dump skipped, instruction units count: 1364
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: k3.C1648i0.k(long, int):s3.U");
    }

    public void l(s3.U u4) {
        if (u4.o) {
            ((ArrayList) this.f17994e).remove(u4);
        } else {
            ((ArrayList) this.d).remove(u4);
        }
        u4.f22229n = null;
        u4.o = false;
        u4.f22226j &= -33;
    }

    public void m() {
        AbstractC2367F abstractC2367F = ((RecyclerView) this.f17997h).f12157x;
        this.c = this.f17993b + (abstractC2367F != null ? abstractC2367F.f22186j : 0);
        ArrayList arrayList = (ArrayList) this.f17995f;
        for (int size = arrayList.size() - 1; size >= 0 && arrayList.size() > this.c; size--) {
            g(size);
        }
    }

    public C1648i0(C1737B c1737b, l3.X x8, C1741F c1741f, List list, CharSequence charSequence, int i7, int i10, Bundle bundle) {
        this.d = c1737b;
        this.f17994e = x8;
        this.f17995f = c1741f;
        list.getClass();
        this.f17992a = list;
        this.f17996g = charSequence;
        this.f17993b = i7;
        this.c = i10;
        this.f17997h = bundle == null ? Bundle.EMPTY : bundle;
    }

    public C1648i0(C1648i0 c1648i0) {
        this.d = (C1737B) c1648i0.d;
        this.f17994e = (l3.X) c1648i0.f17994e;
        this.f17995f = (C1741F) c1648i0.f17995f;
        this.f17992a = c1648i0.f17992a;
        this.f17996g = (CharSequence) c1648i0.f17996g;
        this.f17993b = c1648i0.f17993b;
        this.c = c1648i0.c;
        this.f17997h = (Bundle) c1648i0.f17997h;
    }

    public C1648i0(RecyclerView recyclerView) {
        this.f17997h = recyclerView;
        ArrayList arrayList = new ArrayList();
        this.d = arrayList;
        this.f17994e = null;
        this.f17995f = new ArrayList();
        this.f17992a = Collections.unmodifiableList(arrayList);
        this.f17993b = 2;
        this.c = 2;
    }
}
