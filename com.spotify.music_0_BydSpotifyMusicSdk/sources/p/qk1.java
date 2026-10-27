package p;

/* JADX INFO: loaded from: classes.dex */
public final class qk1 extends ik1 {
    public static final qk1 c = new qk1(6, 7);

    @Override // p.ik1
    public final void a(androidx.sqlite.db.framework.b bVar) {
        bVar.w("\n    CREATE TABLE IF NOT EXISTS `WorkProgress` (`work_spec_id` TEXT NOT NULL, `progress`\n    BLOB NOT NULL, PRIMARY KEY(`work_spec_id`), FOREIGN KEY(`work_spec_id`)\n    REFERENCES `WorkSpec`(`id`) ON UPDATE CASCADE ON DELETE CASCADE )\n    ");
    }
}
