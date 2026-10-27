package defpackage;

import java.util.List;
import org.chromium.base.Callback;

/* JADX INFO: compiled from: TabPersistentStore.java */
/* JADX INFO: loaded from: classes2.dex */
public final class a33 implements Callback<List<String>> {
    public final /* synthetic */ z23 e;

    public a33(z23 z23Var) {
        this.e = z23Var;
    }

    @Override // org.chromium.base.Callback
    public final void onResult(List<String> list) {
        List<String> list2 = list;
        if (list2 == null) {
            return;
        }
        for (int i = 0; i < list2.size(); i++) {
            this.e.d(list2.get(i), true);
        }
    }
}
