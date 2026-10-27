package defpackage;

import java.util.List;
import org.chromium.base.Callback;

/* JADX INFO: compiled from: TabPersistentStore.java */
/* JADX INFO: loaded from: classes2.dex */
public final class b33 implements Callback<List<String>> {
    public final /* synthetic */ int e;
    public final /* synthetic */ z23 f;

    public b33(z23 z23Var, int i) {
        this.f = z23Var;
        this.e = i;
    }

    @Override // org.chromium.base.Callback
    public final void onResult(List<String> list) {
        List<String> list2 = list;
        String strQ = v53.q(this.e);
        z23 z23Var = this.f;
        z23Var.d(strQ, true);
        if (list2 == null) {
            return;
        }
        for (int i = 0; i < list2.size(); i++) {
            z23Var.d(list2.get(i), true);
        }
    }
}
