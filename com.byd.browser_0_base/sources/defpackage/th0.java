package defpackage;

import java.nio.ByteBuffer;
import java.util.HashMap;
import org.chromium.base.Callback;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class th0 implements Runnable {
    public final /* synthetic */ int e;
    public final /* synthetic */ Callback f;

    public /* synthetic */ th0(Callback callback, int i) {
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
            case 1:
                callback.onResult(ByteBuffer.allocateDirect(0));
                break;
            case 2:
                HashMap map = l02.l;
                callback.onResult(null);
                break;
            default:
                int i2 = xl2.t;
                callback.onResult(null);
                break;
        }
    }
}
