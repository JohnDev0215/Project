package E;

import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class i extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f1356m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f1357n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ i(Object obj, int i7) {
        super(1);
        this.f1356m = i7;
        this.f1357n = obj;
    }

    /* JADX WARN: Type inference failed for: r3v2, types: [java.lang.Object, java.util.List] */
    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        switch (this.f1356m) {
            case 0:
                ((Number) obj).intValue();
                return this.f1357n;
            case 1:
                ?? r32 = this.f1357n;
                int size = r32.size();
                for (int i7 = 0; i7 < size; i7++) {
                    ((InterfaceC2782k) r32.get(i7)).b(obj);
                }
                return m7.q.f19267a;
            default:
                return Boolean.valueOf(A7.m.a(obj, this.f1357n));
        }
    }
}
