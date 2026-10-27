package p;

import androidx.work.BackoffPolicy;
import androidx.work.OutOfQuotaPolicy;
import androidx.work.WorkInfo$State;
import java.util.LinkedHashSet;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
public abstract class fy2 {
    public UUID a;
    public ky2 b;
    public final LinkedHashSet c;

    public fy2(Class cls) {
        UUID uuidRandomUUID = UUID.randomUUID();
        v03.i(uuidRandomUUID, "randomUUID()");
        this.a = uuidRandomUUID;
        String string = this.a.toString();
        v03.i(string, "id.toString()");
        this.b = new ky2(string, (WorkInfo$State) null, cls.getName(), (String) null, (w50) null, (w50) null, 0L, 0L, 0L, (wy) null, 0, (BackoffPolicy) null, 0L, 0L, 0L, 0L, false, (OutOfQuotaPolicy) null, 0, 0L, 0, 0, 8388602);
        String[] strArr = {cls.getName()};
        LinkedHashSet linkedHashSet = new LinkedHashSet(he1.B(1));
        kotlin.collections.b.v0(linkedHashSet, strArr);
        this.c = linkedHashSet;
    }

    public final mq1 a() {
        lq1 lq1Var = (lq1) this;
        mq1 mq1Var = new mq1(lq1Var.a, lq1Var.b, lq1Var.c);
        wy wyVar = this.b.j;
        boolean z = (wyVar.h.isEmpty() ^ true) || wyVar.d || wyVar.b || wyVar.c;
        ky2 ky2Var = this.b;
        if (ky2Var.q) {
            if (!(!z)) {
                throw new IllegalArgumentException("Expedited jobs only support network and storage constraints".toString());
            }
            if (ky2Var.g > 0) {
                throw new IllegalArgumentException("Expedited jobs cannot be delayed".toString());
            }
        }
        UUID uuidRandomUUID = UUID.randomUUID();
        v03.i(uuidRandomUUID, "randomUUID()");
        this.a = uuidRandomUUID;
        String string = uuidRandomUUID.toString();
        v03.i(string, "id.toString()");
        ky2 ky2Var2 = this.b;
        v03.j(ky2Var2, "other");
        this.b = new ky2(string, ky2Var2.b, ky2Var2.c, ky2Var2.d, new w50(ky2Var2.e), new w50(ky2Var2.f), ky2Var2.g, ky2Var2.h, ky2Var2.i, new wy(ky2Var2.j), ky2Var2.k, ky2Var2.l, ky2Var2.m, ky2Var2.n, ky2Var2.o, ky2Var2.f145p, ky2Var2.q, ky2Var2.r, ky2Var2.s, ky2Var2.u, ky2Var2.v, ky2Var2.w, 524288);
        return mq1Var;
    }
}
