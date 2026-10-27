package defpackage;

import defpackage.f83;
import org.chromium.base.Callback;

/* JADX INFO: compiled from: LinkToTextHelper.java */
/* JADX INFO: loaded from: classes2.dex */
public final class u71 implements f83.b {
    public final /* synthetic */ Callback e;

    public u71(td2 td2Var) {
        this.e = td2Var;
    }

    @Override // defpackage.sl
    public final void c(String[] strArr) {
        this.e.onResult(strArr);
    }
}
