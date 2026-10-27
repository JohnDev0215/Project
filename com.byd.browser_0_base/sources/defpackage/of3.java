package defpackage;

import android.os.Bundle;
import defpackage.wf3;
import org.chromium.base.task.PostTask;

/* JADX INFO: compiled from: TrustedWebActivityClient.java */
/* JADX INFO: loaded from: classes.dex */
public final class of3 extends vd1 {
    public final /* synthetic */ wf3.a k;
    public final /* synthetic */ pf3 l;

    public of3(pf3 pf3Var, vf3 vf3Var) {
        this.l = pf3Var;
        this.k = vf3Var;
    }

    @Override // defpackage.vd1
    public final void q(String str, Bundle bundle) {
        PostTask.c(rh3.c, new xi(str, bundle, this.l.e, this.k, 1));
    }
}
