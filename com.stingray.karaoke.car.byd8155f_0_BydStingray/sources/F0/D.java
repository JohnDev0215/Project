package F0;

import android.R;

/* JADX INFO: loaded from: classes.dex */
public abstract class D {
    public static final void a(G1.e eVar, L0.o oVar) {
        if (T.i(oVar)) {
            Object obj = oVar.d.l.get(L0.i.f3891g);
            if (obj == null) {
                obj = null;
            }
            L0.a aVar = (L0.a) obj;
            if (aVar != null) {
                eVar.b(new G1.d(R.id.accessibilityActionSetProgress, aVar.f3875a));
            }
        }
    }
}
