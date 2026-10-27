package N1;

import java.util.LinkedHashSet;
import z7.InterfaceC2772a;

/* JADX INFO: renamed from: N1.n, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0433n extends A7.o implements InterfaceC2772a {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f4548m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Q f4549n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C0433n(Q q4, int i7) {
        super(0);
        this.f4548m = i7;
        this.f4549n = q4;
    }

    @Override // z7.InterfaceC2772a
    public final Object a() {
        switch (this.f4548m) {
            case 0:
                return ((P1.i) this.f4549n.f4494t.getValue()).c;
            default:
                P1.f fVar = this.f4549n.l;
                String strR = ((r9.z) fVar.d.getValue()).l.r();
                synchronized (P1.f.f5276f) {
                    LinkedHashSet linkedHashSet = P1.f.f5275e;
                    if (linkedHashSet.contains(strR)) {
                        throw new IllegalStateException(("There are multiple DataStores active for the same file: " + strR + ". You should either maintain your DataStore as a singleton or confirm that there is no two DataStore's active on the same file (by confirming that the scope is cancelled).").toString());
                    }
                    linkedHashSet.add(strR);
                }
                return new P1.i(fVar.f5277a, (r9.z) fVar.d.getValue(), (b0) fVar.f5278b.invoke((r9.z) fVar.d.getValue(), fVar.f5277a), new P1.e(fVar, 1));
        }
    }
}
