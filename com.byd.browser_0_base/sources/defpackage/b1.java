package defpackage;

import androidx.recyclerview.widget.RecyclerView;
import org.chromium.chrome.R$layout;
import org.chromium.ui.modelutil.m;

/* JADX WARN: Classes with same name are omitted, all sources:
  assets/webapk8.dex
  classes2.dex
 */
/* JADX INFO: compiled from: AccountPickerCoordinator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class b1 {
    public final f1 a;

    /* JADX INFO: compiled from: AccountPickerCoordinator.java */
    public interface a {
        void l(String str);

        void u();
    }

    public b1(RecyclerView recyclerView, a aVar) {
        ya1 ya1Var = new ya1();
        m mVar = new m(ya1Var);
        mVar.H(2, new o61(R$layout.account_picker_new_account_row), new as1());
        mVar.H(1, new o61(R$layout.account_picker_row), new ki0());
        recyclerView.setAdapter(mVar);
        this.a = new f1(recyclerView.getContext(), ya1Var, aVar);
    }
}
