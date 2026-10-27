package defpackage;

import J.N;
import android.util.SparseArray;
import defpackage.tp3;
import java.util.Map;
import org.chromium.base.Callback;
import org.chromium.components.translate.TranslateMessage;
import org.chromium.content.browser.input.ImeAdapterImpl;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class ur3 implements Callback {
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;

    public /* synthetic */ ur3(int i, Object obj) {
        this.e = i;
        this.f = obj;
    }

    @Override // org.chromium.base.Callback
    public final void onResult(Object obj) {
        pr3 pr3Var;
        int i = this.e;
        or3 or3Var = null;
        Object obj2 = this.f;
        switch (i) {
            case 0:
                Map map = (Map) obj2;
                Map.Entry entry = (Map.Entry) obj;
                String str = (String) entry.getKey();
                ty1 ty1Var = (ty1) entry.getValue();
                if (ty1Var != null) {
                    kz1 kz1Var = ty1Var.b;
                    if (kz1Var == null) {
                        pr3Var = null;
                    } else {
                        ry1 ry1Var = kz1Var.c;
                        pr3Var = new pr3(ry1Var == null ? null : new nr3(ry1Var.b, ry1Var.c));
                    }
                    lz1 lz1Var = ty1Var.d;
                    or3Var = new or3(pr3Var, lz1Var != null ? new qr3(lz1Var.b, lz1Var.c) : null);
                }
                map.put(str, or3Var);
                break;
            case 1:
                ((Runnable) obj2).run();
                break;
            case 2:
                TranslateMessage translateMessage = (TranslateMessage) obj2;
                int iIntValue = ((Integer) obj).intValue();
                translateMessage.f = null;
                long j = translateMessage.d;
                if (j != 0) {
                    N.MARFJEb_(j, iIntValue);
                    break;
                }
                break;
            case 3:
                ImeAdapterImpl imeAdapterImpl = (ImeAdapterImpl) obj2;
                ss1 ss1Var = (ss1) obj;
                imeAdapterImpl.getClass();
                if (ss1Var.b != null) {
                    SparseArray<ss1> sparseArray = imeAdapterImpl.C;
                    int i2 = ss1Var.a;
                    sparseArray.put(i2, ss1Var);
                    imeAdapterImpl.B.b(i2, ss1Var.b);
                } else {
                    ss1Var.a(2);
                }
                break;
            case 4:
                ((x7) obj2).s();
                break;
            case 5:
                tp3.a aVar = (tp3.a) obj2;
                if (!((Boolean) obj).booleanValue()) {
                    aVar.onServiceConnected(null, null);
                }
                break;
            default:
                tp3 tp3Var = (tp3) obj2;
                if (tp3Var.f.isEmpty() && tp3Var.e == 0 && tp3Var.d != null) {
                    tp3Var.d = null;
                    break;
                }
                break;
        }
    }
}
