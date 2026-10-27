package l5;

import E5.C;

/* JADX INFO: loaded from: classes.dex */
public final class k extends C {

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ int f18647q;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ k(n nVar, int i7) {
        super(nVar);
        this.f18647q = i7;
    }

    @Override // E5.C, java.util.Iterator
    public Object next() {
        switch (this.f18647q) {
            case 1:
                return b().f18652q;
            default:
                return super.next();
        }
    }
}
