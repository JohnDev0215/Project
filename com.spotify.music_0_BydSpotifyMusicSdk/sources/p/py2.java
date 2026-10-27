package p;

/* JADX INFO: loaded from: classes.dex */
public final class py2 extends androidx.room.a {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ py2(Object obj, i52 i52Var, int i) {
        super(i52Var);
        this.d = i;
        this.e = obj;
    }

    @Override // androidx.room.a
    public final String b() {
        switch (this.d) {
            case 0:
                return "DELETE FROM worktag WHERE work_spec_id=?";
            default:
                return "\n            UPDATE RateLimitedEvents\n            SET count = ?,\n            timestamp = ?\n            WHERE eventName = ?\n        ";
        }
    }
}
