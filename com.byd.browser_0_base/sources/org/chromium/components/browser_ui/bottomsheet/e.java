package org.chromium.components.browser_ui.bottomsheet;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class e implements Runnable {
    public final /* synthetic */ int e;
    public final /* synthetic */ f f;

    public /* synthetic */ e(f fVar, int i) {
        this.e = i;
        this.f = fVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        BottomSheet bottomSheet;
        int i = 3;
        int i2 = this.e;
        f fVar = this.f;
        switch (i2) {
            case 0:
                if (!fVar.o.b() && (bottomSheet = fVar.e) != null) {
                    if (bottomSheet.t != null) {
                        if (fVar.m == fVar.h()) {
                            i = fVar.l;
                        } else {
                            BottomSheet bottomSheet2 = fVar.e;
                            if (bottomSheet2.t == null) {
                                i = 0;
                            } else if (bottomSheet2.n()) {
                                i = 1;
                            } else if (bottomSheet2.m()) {
                                i = 2;
                            }
                        }
                        fVar.e.s(i, 0, true);
                    } else {
                        fVar.u(true);
                    }
                    fVar.m = null;
                    fVar.l = -1;
                }
                fVar.w();
                break;
            default:
                BottomSheet bottomSheet3 = fVar.e;
                if (bottomSheet3.y) {
                    bottomSheet3.s(bottomSheet3.g(), 3, true);
                    break;
                }
                break;
        }
    }
}
