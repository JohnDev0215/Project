package A3;

import h2.AbstractC1347d;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f399a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final u f400b;
    public final z3.i c;

    public q(Object obj, u uVar, z3.i iVar) {
        this.f399a = obj;
        this.f400b = uVar;
        this.c = iVar;
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x0016  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean equals(java.lang.Object r8) {
        /*
            Method dump skipped, instruction units count: 207
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: A3.q.equals(java.lang.Object):boolean");
    }

    public final int hashCode() {
        int iHashCode;
        this.f400b.getClass();
        Object obj = this.f399a;
        if (obj instanceof K3.i) {
            K3.i iVar = (K3.i) obj;
            int iHashCode2 = (iVar.d.hashCode() + ((iVar.f3543b.hashCode() + (iVar.f3542a.hashCode() * 31)) * 923521)) * 961;
            iVar.f3545f.getClass();
            iHashCode = iVar.f3561x.l.hashCode() + ((iVar.f3544e.hashCode() + ((iVar.f3560w.hashCode() + ((iVar.f3559v.hashCode() + ((iVar.f3553p.hashCode() + ((iVar.o.hashCode() + ((iVar.f3552n.hashCode() + AbstractC1347d.d(AbstractC1347d.d(AbstractC1347d.d(AbstractC1347d.d((((1 + iHashCode2) * 31) + Arrays.hashCode(iVar.f3547h.l)) * 31, 31, iVar.f3549j), 31, iVar.f3550k), 31, iVar.l), 31, iVar.f3551m)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31);
        } else {
            iHashCode = obj != null ? obj.hashCode() : 0;
        }
        return this.c.hashCode() + (iHashCode * 31);
    }
}
