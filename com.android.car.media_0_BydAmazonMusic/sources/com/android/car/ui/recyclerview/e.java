package com.android.car.ui.recyclerview;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class e implements Runnable {
    public final /* synthetic */ int a;
    public final /* synthetic */ DefaultScrollBar b;

    public /* synthetic */ e(DefaultScrollBar defaultScrollBar, int i) {
        this.a = i;
        this.b = defaultScrollBar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.a) {
            case 0:
                this.b.lambda$initialize$2();
                break;
            default:
                this.b.lambda$updatePaginationButtons$4();
                break;
        }
    }
}
