package kotlinx.coroutines;

/* JADX INFO: compiled from: Builders.common.kt */
/* JADX INFO: loaded from: classes.dex */
class f2 extends a<g5.r> {
    public f2(k5.g gVar, boolean z6) {
        super(gVar, z6);
    }

    @Override // kotlinx.coroutines.s1
    protected boolean handleJobException(Throwable th) {
        g0.a(getContext(), th);
        return true;
    }
}
