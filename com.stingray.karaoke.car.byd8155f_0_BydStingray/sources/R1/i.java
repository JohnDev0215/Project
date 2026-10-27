package R1;

import h8.AbstractC1386c;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import m7.q;
import n7.AbstractC2000o;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class i extends s7.i implements InterfaceC2785n {
    public /* synthetic */ Object l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Set f7355m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public i(Set set, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f7355m = set;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        i iVar = new i(this.f7355m, interfaceC2189c);
        iVar.l = obj;
        return iVar;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((i) create((S1.b) obj, (InterfaceC2189c) obj2)).invokeSuspend(q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        AbstractC1386c.u(obj);
        Set setKeySet = ((S1.b) this.l).a().keySet();
        ArrayList arrayList = new ArrayList(AbstractC2000o.J(setKeySet, 10));
        Iterator it = setKeySet.iterator();
        while (it.hasNext()) {
            arrayList.add(((S1.d) it.next()).f7868a);
        }
        LinkedHashSet linkedHashSet = j.f7356a;
        boolean z6 = true;
        Set set = this.f7355m;
        if (set != linkedHashSet) {
            Set set2 = set;
            if ((set2 instanceof Collection) && set2.isEmpty()) {
                z6 = false;
            } else {
                Iterator it2 = set2.iterator();
                while (it2.hasNext()) {
                    if (!arrayList.contains((String) it2.next())) {
                        break;
                    }
                }
                z6 = false;
            }
        }
        return Boolean.valueOf(z6);
    }
}
