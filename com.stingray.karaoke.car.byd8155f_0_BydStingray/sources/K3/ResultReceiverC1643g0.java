package k3;

import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.ResultReceiver;
import h5.C1373E;
import java.lang.ref.WeakReference;
import l3.C1736A;
import l3.C1749e;
import l3.InterfaceC1750f;
import x3.AbstractC2673a;
import x3.InterfaceC2676d;

/* JADX INFO: renamed from: k3.g0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class ResultReceiverC1643g0 extends ResultReceiver {
    public final /* synthetic */ int l = 1;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Object f17962m;

    public /* synthetic */ ResultReceiverC1643g0(Handler handler) {
        super(handler);
    }

    @Override // android.os.ResultReceiver
    public final void onReceiveResult(int i7, Bundle bundle) {
        InterfaceC1750f interfaceC1750f;
        switch (this.l) {
            case 0:
                if (bundle == null) {
                    bundle = Bundle.EMPTY;
                }
                ((C1373E) this.f17962m).l(new C1(i7, bundle));
                return;
            default:
                C1736A c1736a = (C1736A) ((WeakReference) this.f17962m).get();
                if (c1736a == null || bundle == null) {
                    return;
                }
                synchronized (c1736a.f18507b) {
                    l3.O o = c1736a.f18508e;
                    IBinder binder = bundle.getBinder("android.support.v4.media.session.EXTRA_BINDER");
                    int i10 = l3.J.d;
                    if (binder == null) {
                        interfaceC1750f = null;
                    } else {
                        IInterface iInterfaceQueryLocalInterface = binder.queryLocalInterface("android.support.v4.media.session.IMediaSession");
                        if (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof InterfaceC1750f)) {
                            C1749e c1749e = new C1749e();
                            c1749e.c = binder;
                            interfaceC1750f = c1749e;
                        } else {
                            interfaceC1750f = (InterfaceC1750f) iInterfaceQueryLocalInterface;
                        }
                    }
                    synchronized (o.l) {
                        o.f18535n = interfaceC1750f;
                        break;
                    }
                    l3.O o4 = c1736a.f18508e;
                    InterfaceC2676d interfaceC2676dL = AbstractC2673a.L(bundle);
                    synchronized (o4.l) {
                        o4.o = interfaceC2676dL;
                        break;
                    }
                    c1736a.a();
                }
                return;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ResultReceiverC1643g0(Handler handler, C1373E c1373e) {
        super(handler);
        this.f17962m = c1373e;
    }
}
