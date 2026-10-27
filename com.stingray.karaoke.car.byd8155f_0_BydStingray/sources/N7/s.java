package N7;

import F8.AbstractC0287w;
import F8.Y;
import Q7.D;
import Q7.InterfaceC0654g;
import Q7.InterfaceC0657j;
import T7.B;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Set;
import n7.AbstractC1998m;
import n7.z;
import o8.C2057b;
import o8.C2061f;

/* JADX INFO: loaded from: classes.dex */
public abstract class s {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Set f4886a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final HashMap f4887b;
    public static final HashMap c;
    public static final LinkedHashSet d;

    static {
        r[] rVarArrValues = r.values();
        ArrayList arrayList = new ArrayList(rVarArrValues.length);
        for (r rVar : rVarArrValues) {
            arrayList.add(rVar.f4884m);
        }
        f4886a = AbstractC1998m.D0(arrayList);
        q[] qVarArrValues = q.values();
        ArrayList arrayList2 = new ArrayList(qVarArrValues.length);
        for (q qVar : qVarArrValues) {
            arrayList2.add(qVar.l);
        }
        AbstractC1998m.D0(arrayList2);
        f4887b = new HashMap();
        c = new HashMap();
        z.v(new HashMap(z.q(4)), new m7.i[]{new m7.i(q.UBYTEARRAY, C2061f.e("ubyteArrayOf")), new m7.i(q.USHORTARRAY, C2061f.e("ushortArrayOf")), new m7.i(q.UINTARRAY, C2061f.e("uintArrayOf")), new m7.i(q.ULONGARRAY, C2061f.e("ulongArrayOf"))});
        r[] rVarArrValues2 = r.values();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (r rVar2 : rVarArrValues2) {
            linkedHashSet.add(rVar2.f4885n.i());
        }
        d = linkedHashSet;
        for (r rVar3 : r.values()) {
            HashMap map = f4887b;
            C2057b c2057b = rVar3.f4885n;
            C2057b c2057b2 = rVar3.l;
            map.put(c2057b, c2057b2);
            c.put(c2057b2, rVar3.f4885n);
        }
    }

    public static final boolean a(AbstractC0287w abstractC0287w) {
        InterfaceC0654g interfaceC0654gN;
        if (Y.l(abstractC0287w) || (interfaceC0654gN = abstractC0287w.B0().n()) == null) {
            return false;
        }
        InterfaceC0657j interfaceC0657jO = interfaceC0654gN.o();
        return (interfaceC0657jO instanceof D) && A7.m.a(((B) ((D) interfaceC0657jO)).f8767q, o.f4875j) && f4886a.contains(interfaceC0654gN.getName());
    }
}
