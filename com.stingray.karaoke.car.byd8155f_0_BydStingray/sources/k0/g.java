package K0;

import K.C0331b0;
import O.K;
import S8.k0;
import android.os.CancellationSignal;
import java.lang.reflect.InvocationTargetException;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class g implements CancellationSignal.OnCancelListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f3496a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f3497b;

    public /* synthetic */ g(Object obj, int i7) {
        this.f3496a = i7;
        this.f3497b = obj;
    }

    @Override // android.os.CancellationSignal.OnCancelListener
    public final void onCancel() throws IllegalAccessException, InvocationTargetException {
        switch (this.f3496a) {
            case 0:
                ((k0) this.f3497b).g(null);
                break;
            default:
                K k10 = (K) this.f3497b;
                if (k10 != null) {
                    C0331b0 c0331b0 = k10.d;
                    if (c0331b0 != null) {
                        c0331b0.e(N0.K.f4356b);
                    }
                    C0331b0 c0331b02 = k10.d;
                    if (c0331b02 != null) {
                        c0331b02.f(N0.K.f4356b);
                        break;
                    }
                }
                break;
        }
    }
}
