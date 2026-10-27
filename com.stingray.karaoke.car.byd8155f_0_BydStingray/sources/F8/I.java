package F8;

/* JADX INFO: loaded from: classes.dex */
public final class I extends AbstractC0268c {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final I f2439b = new I(0);
    public static final I c = new I(1);
    public static final I d = new I(2);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f2440a;

    public /* synthetic */ I(int i7) {
        this.f2440a = i7;
    }

    @Override // F8.AbstractC0268c
    public final I8.d x(J j3, I8.c cVar) {
        switch (this.f2440a) {
            case 0:
                A7.m.f(j3, "state");
                A7.m.f(cVar, "type");
                return j3.c.a0(cVar);
            case 1:
                A7.m.f(j3, "state");
                A7.m.f(cVar, "type");
                throw new UnsupportedOperationException("Should not be called");
            default:
                A7.m.f(j3, "state");
                A7.m.f(cVar, "type");
                return j3.c.W(cVar);
        }
    }
}
