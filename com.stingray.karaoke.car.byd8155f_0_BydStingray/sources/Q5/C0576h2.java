package Q5;

import q7.C2188b;
import q7.C2190d;
import q7.C2195i;
import q7.InterfaceC2191e;
import q7.InterfaceC2192f;
import q7.InterfaceC2194h;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: Q5.h2, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class C0576h2 implements InterfaceC2785n {
    public final /* synthetic */ int l;

    public /* synthetic */ C0576h2(int i7) {
        this.l = i7;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        C2188b c2188b;
        switch (this.l) {
            case 0:
                ((Integer) obj).intValue();
                N5.f0 f0Var = (N5.f0) obj2;
                A7.m.f(f0Var, "item");
                return f0Var.d;
            case 1:
                Boolean bool = (Boolean) obj;
                bool.booleanValue();
                return bool;
            case 2:
                return ((InterfaceC2194h) obj).v((InterfaceC2192f) obj2);
            case 3:
                return ((InterfaceC2194h) obj).v((InterfaceC2192f) obj2);
            case 4:
                ((Integer) obj).intValue();
                N5.f0 f0Var2 = (N5.f0) obj2;
                A7.m.f(f0Var2, "item");
                return f0Var2.d;
            case 5:
                return Boolean.valueOf(A7.m.a(obj, obj2));
            case 6:
                return Integer.valueOf(((Integer) obj).intValue() + 1);
            case 7:
                InterfaceC2192f interfaceC2192f = (InterfaceC2192f) obj2;
                if (!(interfaceC2192f instanceof S8.m0)) {
                    return obj;
                }
                Integer num = obj instanceof Integer ? (Integer) obj : null;
                int iIntValue = num != null ? num.intValue() : 1;
                return iIntValue == 0 ? interfaceC2192f : Integer.valueOf(iIntValue + 1);
            case 8:
                S8.m0 m0Var = (S8.m0) obj;
                InterfaceC2192f interfaceC2192f2 = (InterfaceC2192f) obj2;
                if (m0Var != null) {
                    return m0Var;
                }
                if (interfaceC2192f2 instanceof S8.m0) {
                    return (S8.m0) interfaceC2192f2;
                }
                return null;
            case 9:
                return (X8.v) obj;
            case 10:
                String str = (String) obj;
                InterfaceC2192f interfaceC2192f3 = (InterfaceC2192f) obj2;
                A7.m.f(str, "acc");
                A7.m.f(interfaceC2192f3, "element");
                if (str.length() == 0) {
                    return interfaceC2192f3.toString();
                }
                return str + ", " + interfaceC2192f3;
            default:
                InterfaceC2194h interfaceC2194h = (InterfaceC2194h) obj;
                InterfaceC2192f interfaceC2192f4 = (InterfaceC2192f) obj2;
                A7.m.f(interfaceC2194h, "acc");
                A7.m.f(interfaceC2192f4, "element");
                InterfaceC2194h interfaceC2194hK = interfaceC2194h.k(interfaceC2192f4.getKey());
                C2195i c2195i = C2195i.l;
                if (interfaceC2194hK == c2195i) {
                    return interfaceC2192f4;
                }
                C2190d c2190d = C2190d.l;
                InterfaceC2191e interfaceC2191e = (InterfaceC2191e) interfaceC2194hK.F(c2190d);
                if (interfaceC2191e == null) {
                    c2188b = new C2188b(interfaceC2192f4, interfaceC2194hK);
                } else {
                    InterfaceC2194h interfaceC2194hK2 = interfaceC2194hK.k(c2190d);
                    if (interfaceC2194hK2 == c2195i) {
                        return new C2188b(interfaceC2191e, interfaceC2192f4);
                    }
                    c2188b = new C2188b(interfaceC2191e, new C2188b(interfaceC2192f4, interfaceC2194hK2));
                }
                return c2188b;
        }
    }
}
