package defpackage;

import org.chromium.base.Callback;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class l70 implements Runnable {
    public final /* synthetic */ int e;
    public final /* synthetic */ Callback f;

    public /* synthetic */ l70(Callback callback, int i) {
        this.e = i;
        this.f = callback;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.e;
        Callback callback = this.f;
        switch (i) {
            case 0:
                callback.onResult(null);
                break;
            default:
                int i2 = xl2.t;
                callback.onResult(null);
                break;
        }
    }
}
