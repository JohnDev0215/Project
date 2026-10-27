package p;

/* JADX INFO: loaded from: classes.dex */
public final class pk1 extends ik1 {
    public static final pk1 c = new pk1(4, 5);

    @Override // p.ik1
    public final void a(androidx.sqlite.db.framework.b bVar) {
        bVar.w("ALTER TABLE workspec ADD COLUMN `trigger_content_update_delay` INTEGER NOT NULL DEFAULT -1");
        bVar.w("ALTER TABLE workspec ADD COLUMN `trigger_max_content_delay` INTEGER NOT NULL DEFAULT -1");
    }
}
