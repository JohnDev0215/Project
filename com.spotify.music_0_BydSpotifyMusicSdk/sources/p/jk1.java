package p;

/* JADX INFO: loaded from: classes.dex */
public final class jk1 extends ik1 {
    public static final jk1 c = new jk1(11, 12);

    @Override // p.ik1
    public final void a(androidx.sqlite.db.framework.b bVar) {
        bVar.w("ALTER TABLE workspec ADD COLUMN `out_of_quota_policy` INTEGER NOT NULL DEFAULT 0");
    }
}
