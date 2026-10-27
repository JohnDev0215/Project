package n8;

import A7.m;
import P8.p;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import l8.InterfaceC1788f;
import m8.i;
import m8.j;
import n7.AbstractC1998m;
import n7.AbstractC1999n;
import n7.AbstractC2000o;
import n7.w;
import n7.x;
import n7.z;

/* JADX INFO: loaded from: classes.dex */
public final class g implements InterfaceC1788f {
    public static final List o;
    public final String[] l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Set f20169m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final ArrayList f20170n;

    static {
        String strF0 = AbstractC1998m.f0(AbstractC1999n.D('k', 'o', 't', 'l', 'i', 'n'), "", null, null, null, 62);
        List listD = AbstractC1999n.D(android.hardware.bydauto.ac.tsimpl.a.h(strF0, "/Any"), android.hardware.bydauto.ac.tsimpl.a.h(strF0, "/Nothing"), android.hardware.bydauto.ac.tsimpl.a.h(strF0, "/Unit"), android.hardware.bydauto.ac.tsimpl.a.h(strF0, "/Throwable"), android.hardware.bydauto.ac.tsimpl.a.h(strF0, "/Number"), android.hardware.bydauto.ac.tsimpl.a.h(strF0, "/Byte"), android.hardware.bydauto.ac.tsimpl.a.h(strF0, "/Double"), android.hardware.bydauto.ac.tsimpl.a.h(strF0, "/Float"), android.hardware.bydauto.ac.tsimpl.a.h(strF0, "/Int"), android.hardware.bydauto.ac.tsimpl.a.h(strF0, "/Long"), android.hardware.bydauto.ac.tsimpl.a.h(strF0, "/Short"), android.hardware.bydauto.ac.tsimpl.a.h(strF0, "/Boolean"), android.hardware.bydauto.ac.tsimpl.a.h(strF0, "/Char"), android.hardware.bydauto.ac.tsimpl.a.h(strF0, "/CharSequence"), android.hardware.bydauto.ac.tsimpl.a.h(strF0, "/String"), android.hardware.bydauto.ac.tsimpl.a.h(strF0, "/Comparable"), android.hardware.bydauto.ac.tsimpl.a.h(strF0, "/Enum"), android.hardware.bydauto.ac.tsimpl.a.h(strF0, "/Array"), android.hardware.bydauto.ac.tsimpl.a.h(strF0, "/ByteArray"), android.hardware.bydauto.ac.tsimpl.a.h(strF0, "/DoubleArray"), android.hardware.bydauto.ac.tsimpl.a.h(strF0, "/FloatArray"), android.hardware.bydauto.ac.tsimpl.a.h(strF0, "/IntArray"), android.hardware.bydauto.ac.tsimpl.a.h(strF0, "/LongArray"), android.hardware.bydauto.ac.tsimpl.a.h(strF0, "/ShortArray"), android.hardware.bydauto.ac.tsimpl.a.h(strF0, "/BooleanArray"), android.hardware.bydauto.ac.tsimpl.a.h(strF0, "/CharArray"), android.hardware.bydauto.ac.tsimpl.a.h(strF0, "/Cloneable"), android.hardware.bydauto.ac.tsimpl.a.h(strF0, "/Annotation"), android.hardware.bydauto.ac.tsimpl.a.h(strF0, "/collections/Iterable"), android.hardware.bydauto.ac.tsimpl.a.h(strF0, "/collections/MutableIterable"), android.hardware.bydauto.ac.tsimpl.a.h(strF0, "/collections/Collection"), android.hardware.bydauto.ac.tsimpl.a.h(strF0, "/collections/MutableCollection"), android.hardware.bydauto.ac.tsimpl.a.h(strF0, "/collections/List"), android.hardware.bydauto.ac.tsimpl.a.h(strF0, "/collections/MutableList"), android.hardware.bydauto.ac.tsimpl.a.h(strF0, "/collections/Set"), android.hardware.bydauto.ac.tsimpl.a.h(strF0, "/collections/MutableSet"), android.hardware.bydauto.ac.tsimpl.a.h(strF0, "/collections/Map"), android.hardware.bydauto.ac.tsimpl.a.h(strF0, "/collections/MutableMap"), android.hardware.bydauto.ac.tsimpl.a.h(strF0, "/collections/Map.Entry"), android.hardware.bydauto.ac.tsimpl.a.h(strF0, "/collections/MutableMap.MutableEntry"), android.hardware.bydauto.ac.tsimpl.a.h(strF0, "/collections/Iterator"), android.hardware.bydauto.ac.tsimpl.a.h(strF0, "/collections/MutableIterator"), android.hardware.bydauto.ac.tsimpl.a.h(strF0, "/collections/ListIterator"), android.hardware.bydauto.ac.tsimpl.a.h(strF0, "/collections/MutableListIterator"));
        o = listD;
        p pVarE0 = AbstractC1998m.E0(listD);
        int iQ = z.q(AbstractC2000o.J(pVarE0, 10));
        if (iQ < 16) {
            iQ = 16;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(iQ);
        Iterator it = pVarE0.iterator();
        while (true) {
            P8.b bVar = (P8.b) it;
            if (!bVar.f5459m.hasNext()) {
                return;
            }
            x xVar = (x) bVar.next();
            linkedHashMap.put((String) xVar.f20160b, Integer.valueOf(xVar.f20159a));
        }
    }

    public g(j jVar, String[] strArr) {
        m.f(strArr, "strings");
        List list = jVar.f19324n;
        Set setD0 = list.isEmpty() ? w.l : AbstractC1998m.D0(list);
        List<i> list2 = jVar.f19323m;
        m.e(list2, "types.recordList");
        ArrayList arrayList = new ArrayList();
        arrayList.ensureCapacity(list2.size());
        for (i iVar : list2) {
            int i7 = iVar.f19312n;
            for (int i10 = 0; i10 < i7; i10++) {
                arrayList.add(iVar);
            }
        }
        arrayList.trimToSize();
        this.l = strArr;
        this.f20169m = setD0;
        this.f20170n = arrayList;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x003f  */
    @Override // l8.InterfaceC1788f
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.String n(int r9) {
        /*
            Method dump skipped, instruction units count: 248
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: n8.g.n(int):java.lang.String");
    }

    @Override // l8.InterfaceC1788f
    public final boolean p(int i7) {
        return this.f20169m.contains(Integer.valueOf(i7));
    }

    @Override // l8.InterfaceC1788f
    public final String u(int i7) {
        return n(i7);
    }
}
