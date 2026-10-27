package defpackage;

import J.N;
import java.util.regex.Pattern;

/* JADX INFO: compiled from: AddToHomescreenVerifier.java */
/* JADX INFO: loaded from: classes.dex */
public final class x3 extends ss3 {
    public final js3 e;

    public x3(oj ojVar) {
        this.e = ojVar.M();
    }

    @Override // defpackage.ss3
    public final String e() {
        ft1 ft1VarB = ft1.b(this.e.b);
        if (ft1VarB == null) {
            return null;
        }
        return ft1VarB.toString();
    }

    @Override // defpackage.ss3
    public final boolean f(String str) {
        String str2 = this.e.b;
        Pattern pattern = bk3.a;
        return N.MM5WUf_F(str2, str, true);
    }
}
