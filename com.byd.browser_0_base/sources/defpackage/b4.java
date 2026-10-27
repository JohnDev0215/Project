package defpackage;

import android.app.ProgressDialog;
import android.util.Pair;
import java.util.List;
import org.chromium.base.Callback;
import org.chromium.chrome.R$string;

/* JADX INFO: compiled from: AddressEditor.java */
/* JADX INFO: loaded from: classes2.dex */
public final class b4 implements Callback<Pair<String, Runnable>> {
    public final /* synthetic */ c4 e;

    public b4(c4 c4Var) {
        this.e = c4Var;
    }

    @Override // org.chromium.base.Callback
    public final void onResult(Pair<String, Runnable> pair) {
        Pair<String, Runnable> pair2 = pair;
        c4 c4Var = this.e;
        ((List) c4Var.q.f).clear();
        ProgressDialog progressDialog = new ProgressDialog(c4Var.b);
        c4Var.r = progressDialog;
        progressDialog.setMessage(c4Var.b.getText(R$string.payments_loading_message));
        c4Var.r.show();
        String str = (String) pair2.first;
        c4Var.n = str;
        c4Var.g.f = str;
        c4Var.h.a = str;
        c4Var.o = (Runnable) pair2.second;
        c4Var.d(str);
    }
}
