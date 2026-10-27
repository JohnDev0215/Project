package p;

/* JADX INFO: loaded from: classes.dex */
public final class rk1 extends ik1 {
    public static final rk1 c = new rk1(7, 8);

    @Override // p.ik1
    public final void a(androidx.sqlite.db.framework.b bVar) {
        bVar.w("\n    CREATE INDEX IF NOT EXISTS `index_WorkSpec_period_start_time` ON `workspec`(`period_start_time`)\n    ");
    }
}
