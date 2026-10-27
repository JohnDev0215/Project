package F0;

import android.view.accessibility.AccessibilityEvent;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class J extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f2042m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ M f2043n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ J(M m10, int i7) {
        super(1);
        this.f2042m = i7;
        this.f2043n = m10;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        switch (this.f2042m) {
            case 0:
                M m10 = this.f2043n;
                return Boolean.valueOf(m10.d.getParent().requestSendAccessibilityEvent(m10.d, (AccessibilityEvent) obj));
            default:
                O0 o02 = (O0) obj;
                M m11 = this.f2043n;
                m11.getClass();
                if (o02.f2099m.contains(o02)) {
                    m11.d.getSnapshotObserver().a(o02, m11.f2064M, new B8.A(o02, 8, m11));
                }
                return m7.q.f19267a;
        }
    }
}
