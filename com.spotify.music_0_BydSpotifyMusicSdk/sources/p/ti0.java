package p;

import android.database.sqlite.SQLiteReadOnlyDatabaseException;
import com.google.common.base.Optional;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
public final class ti0 {
    public final lb2 a;
    public final mb2 b;
    public final ei0 c;
    public final kk0 d;
    public final g90 e;
    public final wc1 f;
    public final zb0 g;

    public ti0(lb2 lb2Var, mb2 mb2Var, ei0 ei0Var, kk0 kk0Var, g90 g90Var, wc1 wc1Var, zb0 zb0Var) {
        this.a = lb2Var;
        this.b = mb2Var;
        this.c = ei0Var;
        this.d = kk0Var;
        this.e = g90Var;
        this.f = wc1Var;
        this.g = zb0Var;
    }

    public final void a(String str, byte[] bArr, byte[] bArr2, boolean z, String str2, long j, String str3) {
        mb2 mb2Var = this.b;
        ei0 ei0Var = this.c;
        zb0 zb0Var = this.g;
        wc1 wc1Var = this.f;
        String strE = this.e.e();
        kk0 kk0Var = this.d;
        ((i52) kk0Var.k).c();
        try {
            kk0.b(kk0Var, str, bArr, mb2Var, bArr2, ei0Var, zb0Var, wc1Var, z, str2, strE, j, str3);
            ((i52) kk0Var.k).o();
        } finally {
            ((i52) kk0Var.k).k();
        }
    }

    public final void b(long j, String str, String str2, String str3, boolean z, byte[] bArr) {
        String string;
        Optional optionalB = Optional.b(str2);
        lb2 lb2Var = this.a;
        String strE = lb2Var.b.e();
        String strL = lb2Var.c.l();
        kk0 kk0Var = lb2Var.d;
        if (((bk0) kk0Var.e).B() == 0) {
            string = UUID.randomUUID().toString();
            ((wg1) kk0Var.k).f("database_id", string);
            kk0Var.l = string;
        } else {
            string = (String) kk0Var.l;
            if (string == null && (string = ((ww1) ((wg1) kk0Var.k).a).t().getString("database_id", null)) == null) {
                string = UUID.randomUUID().toString();
                ((wg1) kk0Var.k).f("database_id", string);
                kk0Var.l = string;
            }
        }
        byte[] bArrA = lb2Var.a(optionalB, strE, strL, string);
        if (optionalB.d() && !lb2Var.a.containsKey("")) {
            lb2Var.a(Optional.a(), strE, strL, string);
        }
        try {
            a(str, bArr, bArrA, z, str2, j, str3);
        } catch (SQLiteReadOnlyDatabaseException unused) {
            Thread.yield();
            a(str, bArr, bArrA, z, str2, j, str3);
        }
    }
}
