package p;

import android.os.Bundle;

/* JADX INFO: loaded from: classes.dex */
public final class f03 extends b03 {
    public final int d;
    public final String e;
    public final int f;
    public final /* synthetic */ com.google.android.play.core.assetpacks.a g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public f03(com.google.android.play.core.assetpacks.a aVar, j23 j23Var, int i, String str, int i2) {
        super(aVar, j23Var);
        this.g = aVar;
        this.d = i;
        this.e = str;
        this.f = i2;
    }

    @Override // p.b03, p.a33
    public final void V(Bundle bundle) {
        com.google.android.play.core.assetpacks.a aVar = this.g;
        aVar.d.c(this.b);
        com.google.android.play.core.assetpacks.a.g.c("onError(%d), retrying notifyModuleCompleted...", Integer.valueOf(bundle.getInt("error_code")));
        int i = this.f;
        if (i > 0) {
            aVar.j(this.e, this.d, i - 1);
        }
    }
}
