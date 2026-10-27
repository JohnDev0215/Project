package retrofit2;

import okhttp3.Call;
import p.c00;
import p.h00;
import p.ip1;
import p.jp;
import p.lp;
import p.lq2;
import p.o21;
import p.p81;
import p.ry0;
import p.s32;
import p.vr2;
import p.zp;

/* JADX INFO: loaded from: classes.dex */
public final class b extends o21 {
    public final lp d;

    public b(s32 s32Var, Call.Factory factory, h00 h00Var, lp lpVar) {
        super(s32Var, factory, h00Var);
        this.d = lpVar;
    }

    @Override // p.o21
    public final Object b(ip1 ip1Var, Object[] objArr) {
        final jp jpVar = (jp) this.d.s(ip1Var);
        c00 c00Var = (c00) objArr[objArr.length - 1];
        try {
            zp zpVar = new zp(1, vr2.k(c00Var));
            zpVar.t(new ry0() { // from class: retrofit2.KotlinExtensions$awaitResponse$$inlined$suspendCancellableCoroutine$lambda$1
                {
                    super(1);
                }

                @Override // p.ry0
                public final Object invoke(Object obj) {
                    jpVar.cancel();
                    return lq2.a;
                }
            });
            jpVar.E(new p81(zpVar, 2));
            return zpVar.q();
        } catch (Exception e) {
            return c.a(e, c00Var);
        }
    }
}
