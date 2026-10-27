package p;

import java.io.File;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class eg2 implements cg2 {
    public final bc2 a;
    public final mv0 b;
    public final y6 c;
    public final y6 d;
    public final ey1 e;

    /* JADX WARN: Removed duplicated region for block: B:33:0x00c0  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00c2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public eg2(android.content.Context r13, p.bc2 r14) {
        /*
            Method dump skipped, instruction units count: 305
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p.eg2.<init>(android.content.Context, p.bc2):void");
    }

    public final void a() {
        y6 y6Var = (y6) this.b.a;
        y6Var.getClass();
        qk2.a().getClass();
        hk0.t(new Object[0]);
        File file = y6Var.a;
        if (file.exists()) {
            if (!file.isDirectory()) {
                y6.a(file);
                return;
            }
            qk2.a().getClass();
            hk0.t(new Object[0]);
            File[] fileArrListFiles = file.listFiles();
            if (fileArrListFiles != null) {
                for (File file2 : fileArrListFiles) {
                    v03.i(file2, "it");
                    y6.a(file2);
                }
            }
        }
    }

    public final void b(m02 m02Var) {
        v03.j(m02Var, "configuration");
        y6 y6Var = this.c;
        synchronized (y6Var) {
            hk0 hk0VarA = qk2.a();
            Objects.toString(y6Var.a);
            hk0VarA.getClass();
            hk0.t(new Object[0]);
            y6Var.e(m02Var.a.b());
        }
    }
}
