package defpackage;

import android.content.SharedPreferences;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import org.chromium.base.b;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.state.CriticalPersistedTabData;
import org.chromium.chrome.browser.tabmodel.TabModel;

/* JADX INFO: compiled from: TabGroupModelFilter.java */
/* JADX INFO: loaded from: classes2.dex */
public final class g03 extends p13 {
    public static SharedPreferences u;
    public final org.chromium.base.b<a> k;
    public final HashMap l;
    public final HashMap m;
    public int n;
    public int o;
    public Tab p;
    public boolean q;
    public boolean r;
    public boolean s;
    public final boolean t;

    /* JADX INFO: compiled from: TabGroupModelFilter.java */
    public interface a {
        void a(int i, Tab tab);

        void b(int i, Tab tab);

        void c(int i, Tab tab);

        void d(Tab tab);

        void e(List list, ArrayList arrayList, ArrayList arrayList2);

        void f(int i, int i2, Tab tab);

        void g(int i, int i2, Tab tab);
    }

    /* JADX INFO: compiled from: TabGroupModelFilter.java */
    public class b {
        public final LinkedHashSet a = new LinkedHashSet();
        public int b = -1;
        public final int c;

        public b(int i) {
            this.c = i;
        }

        public final void a(int i) {
            this.a.add(Integer.valueOf(i));
            if (this.b == -1) {
                this.b = i;
            }
            if (c() > 1) {
                SharedPreferences sharedPreferences = g03.u;
                g03.this.X(this.c);
            }
        }

        public final List<Integer> b() {
            return Collections.unmodifiableList(new ArrayList(this.a));
        }

        public final int c() {
            return this.a.size();
        }
    }

    public g03(TabModel tabModel, boolean z) {
        super(tabModel);
        this.k = new org.chromium.base.b<>();
        this.l = new HashMap();
        this.m = new HashMap();
        this.n = -1;
        this.q = true;
        this.t = z;
    }

    public static int O(Tab tab) {
        return CriticalPersistedTabData.m(tab).q;
    }

    public static void b0(int i, Tab tab) {
        CriticalPersistedTabData.m(tab).o(i);
    }

    @Override // defpackage.p13
    public final List<Integer> A(int i) {
        Tab tabF = z7.F(this.e, i);
        if (tabF == null) {
            return super.A(i);
        }
        b bVar = (b) this.m.get(Integer.valueOf(CriticalPersistedTabData.m(tabF).q));
        return bVar == null ? super.A(-1) : Collections.unmodifiableList(bVar.b());
    }

    @Override // defpackage.p13
    public final List<Tab> B(int i) {
        Tab tabF = z7.F(this.e, i);
        if (tabF == null) {
            return super.B(i);
        }
        b bVar = (b) this.m.get(Integer.valueOf(CriticalPersistedTabData.m(tabF).q));
        return bVar == null ? super.B(-1) : M(bVar.b());
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0050  */
    @Override // defpackage.p13
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int E(int r5, org.chromium.chrome.browser.tab.Tab r6) {
        /*
            r4 = this;
            int r0 = r4.L(r6)
            r1 = -1
            if (r0 != r1) goto Ld
            org.chromium.chrome.browser.tab.state.CriticalPersistedTabData r6 = org.chromium.chrome.browser.tab.state.CriticalPersistedTabData.m(r6)
            int r0 = r6.q
        Ld:
            r6 = 0
            org.chromium.chrome.browser.tabmodel.TabModel r2 = r4.e
            if (r0 == r1) goto L50
            java.util.HashMap r4 = r4.m
            java.lang.Integer r1 = java.lang.Integer.valueOf(r0)
            boolean r1 = r4.containsKey(r1)
            if (r1 != 0) goto L1f
            goto L50
        L1f:
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            java.lang.Object r4 = r4.get(r0)
            g03$b r4 = (g03.b) r4
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.util.List r4 = r4.b()
            r0.addAll(r4)
            java.lang.Object r4 = r0.get(r6)
            java.lang.Integer r4 = (java.lang.Integer) r4
            int r4 = r4.intValue()
            int r4 = defpackage.z7.G(r2, r4)
            int r0 = r0.size()
            int r0 = r0 + r4
            if (r5 >= r4) goto L4b
            goto L74
        L4b:
            if (r5 >= r0) goto L4e
            goto L73
        L4e:
            r4 = r0
            goto L74
        L50:
            int r4 = r2.getCount()
            if (r5 <= 0) goto L73
            if (r5 < r4) goto L59
            goto L73
        L59:
            r0 = r5
        L5a:
            if (r0 == r4) goto L4e
            int r1 = r0 + (-1)
            org.chromium.chrome.browser.tab.Tab r1 = r2.getTabAt(r1)
            int r1 = O(r1)
            org.chromium.chrome.browser.tab.Tab r3 = r2.getTabAt(r0)
            int r3 = O(r3)
            if (r1 != r3) goto L4e
            int r0 = r0 + 1
            goto L5a
        L73:
            r4 = r5
        L74:
            if (r4 != r5) goto L77
            r6 = 1
        L77:
            java.lang.String r5 = "Tabs.Tasks.TabAddedWithValidProposedPosition"
            defpackage.oj2.O(r5, r6)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.g03.E(int, org.chromium.chrome.browser.tab.Tab):int");
    }

    @Override // defpackage.p13
    public final boolean F(Tab tab) {
        b bVar = (b) this.m.get(Integer.valueOf(CriticalPersistedTabData.m(tab).q));
        return bVar != null && bVar.c() > 1;
    }

    @Override // defpackage.p13
    public final void G() {
        boolean z = true;
        this.h = true;
        HashSet hashSet = new HashSet();
        int i = -1;
        int i2 = 0;
        while (true) {
            TabModel tabModel = this.e;
            if (i2 >= tabModel.getCount()) {
                break;
            }
            int iO = O(tabModel.getTabAt(i2));
            if (iO != i) {
                if (hashSet.contains(Integer.valueOf(iO))) {
                    z = false;
                    break;
                } else {
                    hashSet.add(Integer.valueOf(i));
                    i = iO;
                }
            }
            i2++;
        }
        oj2.O("Tabs.Tasks.OrderValidOnStartup", z);
    }

    @Override // defpackage.p13
    public final void H() {
        X(-1);
        TabModel tabModel = this.e;
        if (tabModel.index() == -1) {
            this.n = -1;
        } else {
            I(tabModel.getTabAt(tabModel.index()));
        }
    }

    @Override // defpackage.p13
    public final void I(Tab tab) {
        int i = CriticalPersistedTabData.m(tab).q;
        HashMap map = this.m;
        if (map.get(Integer.valueOf(i)) == null) {
            this.p = tab;
            return;
        }
        ((b) map.get(Integer.valueOf(i))).b = tab.getId();
        this.n = ((Integer) this.l.get(Integer.valueOf(i))).intValue();
    }

    @Override // defpackage.p13
    public final boolean J() {
        return this.p == null;
    }

    public final void K(a aVar) {
        this.k.a(aVar);
    }

    public final int L(Tab tab) {
        if (!(this.g || this.h) || this.r) {
            return -1;
        }
        if (!this.t && tab.getLaunchType() != 13 && tab.getLaunchType() != 14 && tab.getLaunchType() != 12) {
            return -1;
        }
        Tab tabF = z7.F(this.e, CriticalPersistedTabData.m(tab).p);
        if (tabF != null) {
            return CriticalPersistedTabData.m(tabF).q;
        }
        return -1;
    }

    public final List<Tab> M(List<Integer> list) {
        ArrayList arrayList = new ArrayList();
        Iterator<Integer> it = list.iterator();
        while (it.hasNext()) {
            Tab tabF = z7.F(this.e, it.next().intValue());
            if (tabF != null) {
                arrayList.add(tabF);
            }
        }
        return Collections.unmodifiableList(arrayList);
    }

    public final List<Tab> N(int i) {
        if (i == -1) {
            return super.B(i);
        }
        b bVar = (b) this.m.get(Integer.valueOf(i));
        return bVar == null ? super.B(-1) : M(bVar.b());
    }

    public final int Q(Tab tab) {
        return z7.G(this.e, ((b) this.m.get(Integer.valueOf(CriticalPersistedTabData.m(tab).q))).b().get(r2.size() - 1).intValue()) + 1;
    }

    public final void R(List<Tab> list, Tab tab, boolean z, boolean z2) {
        org.chromium.base.b<a> bVar;
        int i = CriticalPersistedTabData.m(tab).q;
        int iQ = Q(tab);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        int i2 = 0;
        while (true) {
            int size = list.size();
            bVar = this.k;
            if (i2 >= size) {
                break;
            }
            Tab tab2 = list.get(i2);
            if (!z || i2 == list.size() - 1) {
                Iterator<a> it = bVar.iterator();
                while (true) {
                    b.a aVar = (b.a) it;
                    if (!aVar.hasNext()) {
                        break;
                    } else {
                        ((a) aVar.next()).a(i, tab2);
                    }
                }
            }
            int id = tab2.getId();
            TabModel tabModel = this.e;
            int iG = z7.G(tabModel, id);
            arrayList.add(Integer.valueOf(iG));
            arrayList2.add(Integer.valueOf(CriticalPersistedTabData.m(tab2).q));
            if (tab2.getId() != tab.getId()) {
                boolean z3 = iG < iQ;
                b0(i, tab2);
                if (iG == iQ || iG + 1 == iQ) {
                    int i3 = z3 ? iQ : iQ + 1;
                    int iQ2 = a8.q(iQ, 0, tabModel.getCount());
                    if (z3) {
                        iQ2--;
                    }
                    h1(iQ2, iG, tab2);
                    iQ = i3;
                } else {
                    int id2 = tab2.getId();
                    int i4 = z3 ? iQ : iQ + 1;
                    tabModel.s(id2, iQ);
                    iQ = i4;
                }
            }
            i2++;
        }
        if (!z2) {
            return;
        }
        Iterator<a> it2 = bVar.iterator();
        while (true) {
            b.a aVar2 = (b.a) it2;
            if (!aVar2.hasNext()) {
                return;
            } else {
                ((a) aVar2.next()).e(list, arrayList, arrayList2);
            }
        }
    }

    public final void S(int i, int i2, boolean z) {
        TabModel tabModel = this.e;
        z7.F(tabModel, i);
        Tab tabF = z7.F(tabModel, i2);
        int iO = O(tabF);
        List<Tab> listB = B(i);
        int iQ = Q(tabF);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        if (!z) {
            if (z7.G(tabModel, listB.get(0).getId()) != iQ) {
                R(listB, tabF, true, !z);
                return;
            }
        }
        org.chromium.base.b<a> bVar = this.k;
        Iterator<a> it = bVar.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                break;
            } else {
                ((a) aVar.next()).a(iO, listB.get(listB.size() - 1));
            }
        }
        for (int i3 = 0; i3 < listB.size(); i3++) {
            Tab tab = listB.get(i3);
            if (!z) {
                arrayList.add(Integer.valueOf(z7.G(tabModel, tab.getId())));
                arrayList2.add(Integer.valueOf(CriticalPersistedTabData.m(tab).q));
            }
            b0(iO, tab);
        }
        Z();
        b bVar2 = (b) this.m.get(Integer.valueOf(O(listB.get(listB.size() - 1))));
        Iterator<a> it2 = bVar.iterator();
        while (true) {
            b.a aVar2 = (b.a) it2;
            if (!aVar2.hasNext()) {
                return;
            }
            a aVar3 = (a) aVar2.next();
            Tab tab2 = listB.get(listB.size() - 1);
            int i4 = bVar2.b;
            aVar3.d(tab2);
            if (!z) {
                aVar3.e(listB, arrayList, arrayList2);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x009f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void U(int r12) {
        /*
            Method dump skipped, instruction units count: 307
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.g03.U(int):void");
    }

    public final void V(Tab tab) {
        int i = CriticalPersistedTabData.m(tab).q;
        HashMap map = this.m;
        int i2 = 1;
        if (map.get(Integer.valueOf(i)) != null && ((b) map.get(Integer.valueOf(i))).c() > 1) {
            i9.e.execute(new yr(i, i2, this));
        }
    }

    public final void W(a aVar) {
        this.k.c(aVar);
    }

    public final void X(int i) {
        int i2 = 0;
        boolean z = i == -1;
        HashMap map = this.l;
        if (z) {
            map.clear();
        }
        while (true) {
            TabModel tabModel = this.e;
            if (i2 >= tabModel.getCount()) {
                return;
            }
            Tab tabAt = tabModel.getTabAt(i2);
            if (z) {
                i = O(tabAt);
                if (!map.containsKey(Integer.valueOf(i))) {
                    map.put(Integer.valueOf(i), Integer.valueOf(map.size()));
                }
            }
            b bVar = (b) this.m.get(Integer.valueOf(i));
            int id = tabAt.getId();
            LinkedHashSet linkedHashSet = bVar.a;
            if (linkedHashSet.contains(Integer.valueOf(id))) {
                linkedHashSet.remove(Integer.valueOf(id));
                linkedHashSet.add(Integer.valueOf(id));
            }
            i2++;
        }
    }

    public final void Z() {
        TabModel tabModel;
        this.q = false;
        this.r = true;
        HashMap map = new HashMap();
        HashMap map2 = this.m;
        Iterator it = map2.keySet().iterator();
        while (it.hasNext()) {
            int iIntValue = ((Integer) it.next()).intValue();
            map.put(Integer.valueOf(iIntValue), Integer.valueOf(((b) map2.get(Integer.valueOf(iIntValue))).b));
        }
        a0();
        int i = 0;
        while (true) {
            tabModel = this.e;
            if (i >= tabModel.getCount()) {
                break;
            }
            y(tabModel.getTabAt(i));
            i++;
        }
        Iterator it2 = map2.keySet().iterator();
        while (it2.hasNext()) {
            int iIntValue2 = ((Integer) it2.next()).intValue();
            if (map.containsKey(Integer.valueOf(iIntValue2))) {
                int iIntValue3 = ((Integer) map.get(Integer.valueOf(iIntValue2))).intValue();
                if (((b) map2.get(Integer.valueOf(iIntValue2))).a.contains(Integer.valueOf(iIntValue3))) {
                    ((b) map2.get(Integer.valueOf(iIntValue2))).b = iIntValue3;
                }
            }
        }
        if (tabModel.index() == -1) {
            this.n = -1;
        } else {
            I(tabModel.getTabAt(tabModel.index()));
        }
        this.q = true;
        this.r = false;
    }

    public final void a0() {
        this.l.clear();
        this.m.clear();
        this.o = 0;
    }

    @Override // defpackage.w03
    public final int getCount() {
        return this.m.size();
    }

    @Override // defpackage.w03
    public final Tab getTabAt(int i) {
        int iIntValue;
        if (i < 0 || i >= getCount()) {
            return null;
        }
        HashMap map = this.l;
        Iterator it = map.keySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                iIntValue = -1;
                break;
            }
            Integer num = (Integer) it.next();
            if (((Integer) map.get(num)).intValue() == i) {
                iIntValue = num.intValue();
                break;
            }
        }
        if (iIntValue == -1) {
            return null;
        }
        return z7.F(this.e, ((b) this.m.get(Integer.valueOf(iIntValue))).b);
    }

    @Override // defpackage.p13, defpackage.t13
    public final void h1(int i, int i2, Tab tab) {
        int iIntValue;
        TabModel tabModel;
        boolean z;
        boolean z2 = false;
        if (this.g || this.h) {
            HashMap map = this.m;
            boolean z3 = !map.containsKey(Integer.valueOf(CriticalPersistedTabData.m(tab).q)) ? false : !((b) map.get(Integer.valueOf(CriticalPersistedTabData.m(tab).q))).a.contains(Integer.valueOf(tab.getId()));
            boolean z4 = (map.containsKey(Integer.valueOf(CriticalPersistedTabData.m(tab).q)) ^ true) || this.s;
            if (z3 || z4) {
                Iterator it = map.keySet().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        iIntValue = -1;
                        break;
                    }
                    Integer num = (Integer) it.next();
                    if (((b) map.get(num)).a.contains(Integer.valueOf(tab.getId()))) {
                        iIntValue = num.intValue();
                        break;
                    }
                }
            } else {
                iIntValue = CriticalPersistedTabData.m(tab).q;
            }
            b bVar = (b) map.get(Integer.valueOf(iIntValue));
            org.chromium.base.b<a> bVar2 = this.k;
            if (!z4) {
                if (!z3) {
                    H();
                    int iMin = Math.min(i2, i);
                    int iMax = Math.max(i2, i);
                    while (true) {
                        tabModel = this.e;
                        if (iMin > iMax) {
                            z = true;
                            break;
                        } else {
                            if (O(tabModel.getTabAt(iMin)) != CriticalPersistedTabData.m(tab).q) {
                                z = false;
                                break;
                            }
                            iMin++;
                        }
                    }
                    if (!z) {
                        int iC = (i - ((b) map.get(Integer.valueOf(CriticalPersistedTabData.m(tab).q))).c()) + 1;
                        if (iC >= 0) {
                            int i3 = i;
                            while (true) {
                                if (i3 < iC) {
                                    z2 = true;
                                    break;
                                } else if (O(tabModel.getTabAt(i3)) != CriticalPersistedTabData.m(tab).q) {
                                    break;
                                } else {
                                    i3--;
                                }
                            }
                        }
                        if (!z2) {
                            return;
                        }
                        Iterator<a> it2 = bVar2.iterator();
                        while (true) {
                            b.a aVar = (b.a) it2;
                            if (!aVar.hasNext()) {
                                break;
                            } else {
                                ((a) aVar.next()).f(i2, i, tab);
                            }
                        }
                    } else {
                        Iterator<a> it3 = bVar2.iterator();
                        while (true) {
                            b.a aVar2 = (b.a) it3;
                            if (!aVar2.hasNext()) {
                                break;
                            } else {
                                ((a) aVar2.next()).g(i2, i, tab);
                            }
                        }
                    }
                } else {
                    Z();
                    if (bVar != null && bVar.c() != 1) {
                        return;
                    }
                    b bVar3 = (b) map.get(Integer.valueOf(CriticalPersistedTabData.m(tab).q));
                    Iterator<a> it4 = bVar2.iterator();
                    while (true) {
                        b.a aVar3 = (b.a) it4;
                        if (!aVar3.hasNext()) {
                            break;
                        }
                        a aVar4 = (a) aVar3.next();
                        int i4 = bVar3.b;
                        aVar4.d(tab);
                    }
                }
            } else {
                Z();
                int iIntValue2 = ((Integer) this.l.get(Integer.valueOf(iIntValue))).intValue();
                Iterator<a> it5 = bVar2.iterator();
                while (true) {
                    b.a aVar5 = (b.a) it5;
                    if (!aVar5.hasNext()) {
                        break;
                    } else {
                        ((a) aVar5.next()).c(iIntValue2, tab);
                    }
                }
            }
            super.h1(i, i2, tab);
        }
    }

    @Override // defpackage.w03
    public final int index() {
        return this.n;
    }

    @Override // defpackage.w03
    public final boolean isIncognito() {
        return this.e.isIncognito();
    }

    @Override // defpackage.p13
    public final void o(Tab tab) {
        z(tab);
    }

    @Override // defpackage.w03
    public final int r(Tab tab) {
        if (tab == null || tab.isIncognito() != isIncognito() || this.e.r(tab) == -1) {
            return -1;
        }
        int i = CriticalPersistedTabData.m(tab).q;
        HashMap map = this.l;
        if (map.containsKey(Integer.valueOf(i))) {
            return ((Integer) map.get(Integer.valueOf(i))).intValue();
        }
        return -1;
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x00c7  */
    @Override // defpackage.p13
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void y(org.chromium.chrome.browser.tab.Tab r6) {
        /*
            Method dump skipped, instruction units count: 233
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.g03.y(org.chromium.chrome.browser.tab.Tab):void");
    }

    @Override // defpackage.p13
    public final void z(Tab tab) {
        int iIntValue;
        int i = CriticalPersistedTabData.m(tab).q;
        if (tab.isIncognito() == isIncognito()) {
            HashMap map = this.m;
            if (map.get(Integer.valueOf(i)) != null) {
                if (((b) map.get(Integer.valueOf(i))).a.contains(Integer.valueOf(tab.getId()))) {
                    b bVar = (b) map.get(Integer.valueOf(i));
                    int id = tab.getId();
                    int i2 = bVar.b;
                    LinkedHashSet linkedHashSet = bVar.a;
                    if (i2 == id) {
                        if (linkedHashSet.size() == 1 || !linkedHashSet.contains(Integer.valueOf(id))) {
                            iIntValue = -1;
                        } else {
                            List<Integer> listB = bVar.b();
                            int iIndexOf = listB.indexOf(Integer.valueOf(id));
                            iIntValue = iIndexOf == 0 ? listB.get(iIndexOf + 1).intValue() : listB.get(iIndexOf - 1).intValue();
                        }
                        if (iIntValue != -1) {
                            bVar.b = iIntValue;
                        }
                    }
                    linkedHashSet.remove(Integer.valueOf(id));
                    if (bVar.c() == 1) {
                        this.o--;
                    }
                    if (bVar.c() == 0) {
                        HashMap map2 = this.l;
                        int iIntValue2 = ((Integer) map2.get(Integer.valueOf(i))).intValue();
                        for (Integer num : map2.keySet()) {
                            int iIntValue3 = ((Integer) map2.get(num)).intValue();
                            if (iIntValue3 > iIntValue2) {
                                map2.put(num, Integer.valueOf(iIntValue3 - 1));
                            }
                        }
                        map2.remove(Integer.valueOf(i));
                        map.remove(Integer.valueOf(i));
                        i9.e.execute(new uj(i, 2, this));
                        return;
                    }
                    return;
                }
            }
        }
        throw new IllegalStateException("Attempting to close tab in the wrong model");
    }
}
