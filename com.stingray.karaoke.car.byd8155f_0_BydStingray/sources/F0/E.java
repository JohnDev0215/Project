package F0;

import android.R;
import java.util.LinkedHashMap;

/* JADX INFO: loaded from: classes.dex */
public abstract class E {
    public static final void a(G1.e eVar, L0.o oVar) {
        if (T.i(oVar)) {
            L0.u uVar = L0.i.f3905w;
            LinkedHashMap linkedHashMap = oVar.d.l;
            Object obj = linkedHashMap.get(uVar);
            if (obj == null) {
                obj = null;
            }
            L0.a aVar = (L0.a) obj;
            if (aVar != null) {
                eVar.b(new G1.d(R.id.accessibilityActionPageUp, aVar.f3875a));
            }
            Object obj2 = linkedHashMap.get(L0.i.f3907y);
            if (obj2 == null) {
                obj2 = null;
            }
            L0.a aVar2 = (L0.a) obj2;
            if (aVar2 != null) {
                eVar.b(new G1.d(R.id.accessibilityActionPageDown, aVar2.f3875a));
            }
            Object obj3 = linkedHashMap.get(L0.i.f3906x);
            if (obj3 == null) {
                obj3 = null;
            }
            L0.a aVar3 = (L0.a) obj3;
            if (aVar3 != null) {
                eVar.b(new G1.d(R.id.accessibilityActionPageLeft, aVar3.f3875a));
            }
            Object obj4 = linkedHashMap.get(L0.i.f3908z);
            L0.a aVar4 = (L0.a) (obj4 != null ? obj4 : null);
            if (aVar4 != null) {
                eVar.b(new G1.d(R.id.accessibilityActionPageRight, aVar4.f3875a));
            }
        }
    }
}
