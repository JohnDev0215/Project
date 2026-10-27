package defpackage;

import J.N;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.chromium.base.CollectionUtil;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.state.CriticalPersistedTabData;
import org.chromium.chrome.browser.tabmodel.TabModel;

/* JADX INFO: compiled from: HistoricalTabModelObserver.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ru0 implements t13 {
    public final TabModel e;
    public final su0 f;

    public ru0(TabModel tabModel) {
        tu0 tu0Var = new tu0(tabModel);
        this.e = tabModel;
        this.f = tu0Var;
        tabModel.j(this);
    }

    @Override // defpackage.t13
    public final void Y(List<Tab> list) {
        if (list.isEmpty()) {
            return;
        }
        int size = list.size();
        su0 su0Var = this.f;
        if (size == 1) {
            Tab tab = list.get(0);
            ((tu0) su0Var).getClass();
            if (tu0.b(tab)) {
                oj2.U(0, 3, "Tabs.RecentlyClosed.HistoricalSaverCloseType");
                N.MusxVN12(tab, (ByteBuffer) tu0.a(tab).b, tu0.a(tab).a);
                return;
            }
            return;
        }
        HashMap map = new HashMap();
        ArrayList<qu0> arrayList = new ArrayList();
        for (Tab tab2 : list) {
            int i = CriticalPersistedTabData.m(tab2).q;
            if (map.containsKey(Integer.valueOf(i))) {
                ((qu0) map.get(Integer.valueOf(i))).c.add(tab2);
            } else {
                String strA = i03.a(i);
                ArrayList arrayList2 = new ArrayList();
                arrayList2.add(tab2);
                qu0 qu0Var = new qu0(i, strA, arrayList2);
                arrayList.add(qu0Var);
                map.put(Integer.valueOf(i), qu0Var);
            }
        }
        tu0 tu0Var = (tu0) su0Var;
        tu0Var.getClass();
        ArrayList<qu0> arrayList3 = new ArrayList();
        for (qu0 qu0Var2 : arrayList) {
            List<Tab> list2 = qu0Var2.c;
            ArrayList arrayList4 = new ArrayList();
            for (Tab tab3 : list2) {
                if (tu0.b(tab3)) {
                    arrayList4.add(tab3);
                }
            }
            if (!arrayList4.isEmpty()) {
                if (arrayList4.size() == 1) {
                    arrayList3.add(new qu0((Tab) arrayList4.get(0)));
                } else {
                    arrayList3.add(new qu0(qu0Var2.a, qu0Var2.b, arrayList4));
                }
            }
        }
        if (arrayList3.isEmpty()) {
            return;
        }
        ArrayList arrayList5 = new ArrayList();
        ArrayList arrayList6 = new ArrayList();
        ArrayList arrayList7 = new ArrayList();
        ArrayList arrayList8 = new ArrayList();
        ArrayList arrayList9 = new ArrayList();
        ArrayList arrayList10 = new ArrayList();
        for (qu0 qu0Var3 : arrayList3) {
            boolean zA = qu0Var3.a();
            List<Tab> list3 = qu0Var3.c;
            if (zA) {
                on2 on2VarA = tu0.a(list3.get(0));
                arrayList5.add(list3.get(0));
                arrayList6.add(-1);
                arrayList9.add((ByteBuffer) on2VarA.b);
                arrayList10.add(Integer.valueOf(on2VarA.a));
            } else {
                int i2 = qu0Var3.a;
                arrayList7.add(Integer.valueOf(i2));
                String str = qu0Var3.b;
                if (str == null) {
                    str = "";
                }
                arrayList8.add(str);
                for (Tab tab4 : list3) {
                    on2 on2VarA2 = tu0.a(tab4);
                    arrayList5.add(tab4);
                    arrayList6.add(Integer.valueOf(i2));
                    arrayList9.add((ByteBuffer) on2VarA2.b);
                    arrayList10.add(Integer.valueOf(on2VarA2.a));
                }
            }
        }
        if (arrayList3.size() == 1 && ((qu0) arrayList3.get(0)).a()) {
            Tab tab5 = (Tab) arrayList5.get(0);
            oj2.U(0, 3, "Tabs.RecentlyClosed.HistoricalSaverCloseType");
            N.MusxVN12(tab5, (ByteBuffer) tu0.a(tab5).b, tu0.a(tab5).a);
        } else {
            if (arrayList3.size() != 1 || ((qu0) arrayList3.get(0)).a()) {
                oj2.U(2, 3, "Tabs.RecentlyClosed.HistoricalSaverCloseType");
                N.MeHKJ1cw(tu0Var.a, CollectionUtil.c(arrayList7), (String[]) arrayList8.toArray(new String[0]), CollectionUtil.c(arrayList6), (Tab[]) arrayList5.toArray(new Tab[0]), (ByteBuffer[]) arrayList9.toArray(new ByteBuffer[0]), CollectionUtil.c(arrayList10));
                return;
            }
            oj2.U(1, 3, "Tabs.RecentlyClosed.HistoricalSaverCloseType");
            N.MTL0tV25(tu0Var.a, (String) arrayList8.get(0), (Tab[]) arrayList5.toArray(new Tab[0]), (ByteBuffer[]) arrayList9.toArray(new ByteBuffer[0]), CollectionUtil.c(arrayList10));
        }
    }
}
