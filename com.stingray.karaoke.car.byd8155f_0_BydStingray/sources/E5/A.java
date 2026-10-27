package E5;

/* JADX INFO: loaded from: classes.dex */
public final class A extends C {

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ int f1749q;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ A(E e10, int i7) {
        super(e10);
        this.f1749q = i7;
    }

    @Override // E5.C, java.util.Iterator
    public Object next() {
        switch (this.f1749q) {
            case 1:
                return a().f1757q;
            default:
                return super.next();
        }
    }
}
