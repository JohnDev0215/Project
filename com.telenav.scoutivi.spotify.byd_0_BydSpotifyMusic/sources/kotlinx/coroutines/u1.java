package kotlinx.coroutines;

/* JADX INFO: compiled from: Builders.common.kt */
/* JADX INFO: loaded from: classes.dex */
final class u1 extends f2 {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final k5.d<g5.r> f7841e;

    public u1(k5.g gVar, r5.p<? super h0, ? super k5.d<? super g5.r>, ? extends Object> pVar) {
        super(gVar, false);
        this.f7841e = l5.c.b(pVar, this, this);
    }

    @Override // kotlinx.coroutines.a
    protected void onStart() {
        b6.a.a(this.f7841e, this);
    }
}
