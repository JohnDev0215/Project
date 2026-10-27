package p;

/* JADX INFO: loaded from: classes.dex */
public final class by2 extends androidx.room.a {
    public final /* synthetic */ int d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ by2(i52 i52Var, int i) {
        super(i52Var);
        this.d = i;
    }

    @Override // androidx.room.a
    public final String b() {
        switch (this.d) {
            case 0:
                return "DELETE from WorkProgress where work_spec_id=?";
            default:
                return "DELETE FROM WorkProgress";
        }
    }
}
