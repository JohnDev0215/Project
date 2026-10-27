package defpackage;

import android.view.autofill.AutofillId;
import defpackage.q12;

/* JADX INFO: compiled from: ContentRemovedTask.java */
/* JADX INFO: loaded from: classes2.dex */
public final class sy extends ip1 {
    public final long[] k;

    public sy(jq0 jq0Var, long[] jArr, q12 q12Var) {
        super(jq0Var, q12Var);
        this.k = jArr;
    }

    @Override // defpackage.ip1
    public final void s() {
        ip1.q("ContentRemovedTask.removeContent");
        q12.a aVarO = o();
        if (aVarO == null) {
            return;
        }
        vd1 vd1VarF = vd1.f();
        AutofillId autofillId = this.i.a.b;
        ((m12) vd1VarF).getClass();
        aVarO.a.notifyViewsDisappeared(autofillId, this.k);
    }
}
