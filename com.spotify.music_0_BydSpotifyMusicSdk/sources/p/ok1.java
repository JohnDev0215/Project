package p;

/* JADX INFO: loaded from: classes.dex */
public final class ok1 extends ik1 {
    public static final ok1 c = new ok1(3, 4);

    @Override // p.ik1
    public final void a(androidx.sqlite.db.framework.b bVar) {
        bVar.w("\n    UPDATE workspec SET schedule_requested_at = 0\n    WHERE state NOT IN (2, 3, 5)\n        AND schedule_requested_at = -1\n        AND interval_duration <> 0\n    ");
    }
}
