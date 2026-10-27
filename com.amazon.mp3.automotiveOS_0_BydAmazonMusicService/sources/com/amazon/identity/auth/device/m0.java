package com.amazon.identity.auth.device;

import android.content.Context;
import android.text.TextUtils;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class m0 extends d3 {
    public final /* synthetic */ String e;
    public final /* synthetic */ n0 f;

    public m0(n0 n0Var, String str) {
        this.f = n0Var;
        this.e = str;
    }

    @Override // com.amazon.identity.auth.device.ac
    public a a() {
        n0 n0Var = this.f;
        String strC = n0Var.d.c(this.e, n0Var.o.b);
        n0 n0Var2 = this.f;
        String strC2 = n0Var2.d.c(this.e, n0Var2.p.b);
        if (TextUtils.isEmpty(strC) || TextUtils.isEmpty(strC2)) {
            n0 n0Var3 = this.f;
            strC = n0Var3.d.c(this.e, n0Var3.o.f443a);
            n0 n0Var4 = this.f;
            strC2 = n0Var4.d.c(this.e, n0Var4.p.f443a);
        }
        return new a(strC, strC2);
    }

    @Override // com.amazon.identity.auth.device.d3
    public boolean a(Context context) {
        return false;
    }
}
