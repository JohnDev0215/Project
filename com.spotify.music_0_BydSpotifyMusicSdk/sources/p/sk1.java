package p;

/* JADX INFO: loaded from: classes.dex */
public final class sk1 extends ik1 {
    public static final sk1 c = new sk1(8, 9);

    @Override // p.ik1
    public final void a(androidx.sqlite.db.framework.b bVar) {
        bVar.w("ALTER TABLE workspec ADD COLUMN `run_in_foreground` INTEGER NOT NULL DEFAULT 0");
    }
}
