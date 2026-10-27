package defpackage;

import J.N;
import java.util.regex.Pattern;

/* JADX INFO: compiled from: WebApkVerifier.java */
/* JADX INFO: loaded from: classes.dex */
public final class gq3 extends ss3 {
    public final js3 e;

    public gq3(oj ojVar) {
        this.e = ojVar.M();
    }

    @Override // defpackage.ss3
    public final String e() {
        return this.e.c;
    }

    @Override // defpackage.ss3
    public final boolean f(String str) {
        String str2 = this.e.c;
        Pattern pattern = bk3.a;
        return N.MLKUC6KA(str, str2);
    }
}
