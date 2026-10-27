package io.reactivex.rxjava3.internal.observers;

/* JADX INFO: loaded from: classes.dex */
public final class e extends c {
    public final /* synthetic */ int m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public e(int i) {
        super(1);
        this.m = i;
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onError(Throwable th) {
        switch (this.m) {
            case 0:
                if (this.c == null) {
                    this.e = th;
                }
                countDown();
                break;
            default:
                this.c = null;
                this.e = th;
                countDown();
                break;
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onNext(Object obj) {
        switch (this.m) {
            case 0:
                if (this.c == null) {
                    this.c = obj;
                    this.k.dispose();
                    countDown();
                }
                break;
            default:
                this.c = obj;
                break;
        }
    }
}
