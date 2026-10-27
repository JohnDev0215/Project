package p;

/* JADX INFO: loaded from: classes.dex */
public final class zs {
    public final zb0 a;

    public zs(zb0 zb0Var) {
        this.a = zb0Var;
    }

    public final void a(androidx.sqlite.db.framework.b bVar) {
        bVar.n();
        try {
            StringBuilder sb = new StringBuilder("DELETE FROM workspec WHERE state IN (2, 3, 5) AND (last_enqueue_time + minimum_retention_duration) < ");
            this.a.getClass();
            sb.append(System.currentTimeMillis() - jx2.a);
            sb.append(" AND (SELECT COUNT(*)=0 FROM dependency WHERE     prerequisite_id=id AND     work_spec_id NOT IN         (SELECT id FROM workspec WHERE state IN (2, 3, 5)))");
            bVar.w(sb.toString());
            bVar.j0();
        } finally {
            bVar.m();
        }
    }
}
