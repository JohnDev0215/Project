package defpackage;

import defpackage.y11;

/* JADX INFO: compiled from: InterfaceControlMessagesHelper.java */
/* JADX INFO: loaded from: classes3.dex */
public final class z11 {
    public static <I extends y11, P extends y11.c> boolean a(k00 k00Var, y11.b<I, P> bVar, wi2 wi2Var, gh1 gh1Var) {
        e70 e70VarC = gq2.c(wi2Var.f());
        try {
            e70VarC.c(fg1.e);
            zc2 zc2VarB = zc2.b(e70VarC);
            e70VarC.a();
            cd2 cd2Var = new cd2();
            bd2 bd2Var = new bd2();
            cd2Var.b = bd2Var;
            if (zc2VarB.a == 0) {
                t82 t82Var = new t82();
                bd2Var.a = 0;
                bd2Var.b = t82Var;
                bVar.g();
                t82Var.b = 0;
            } else {
                cd2Var.b = null;
            }
            return gh1Var.a(cd2Var.c(k00Var, new yg1(-1, 2, wi2Var.d.d)));
        } catch (Throwable th) {
            e70VarC.a();
            throw th;
        }
    }
}
