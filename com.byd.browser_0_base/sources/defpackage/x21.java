package defpackage;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import defpackage.w21;
import org.chromium.base.task.PostTask;

/* JADX INFO: compiled from: IsReadyToPayServiceHelper.java */
/* JADX INFO: loaded from: classes3.dex */
public final class x21 extends w21.a implements ServiceConnection {
    public static final /* synthetic */ int k = 0;
    public final Context e;
    public a f;
    public boolean g;
    public boolean h;
    public final Handler i = new Handler();
    public final Intent j;

    /* JADX INFO: compiled from: IsReadyToPayServiceHelper.java */
    public interface a {
    }

    public x21(Context context, Intent intent, org.chromium.components.payments.a aVar) {
        this.e = context;
        this.f = aVar;
        this.j = intent;
    }

    public final void A() {
        a aVar = this.f;
        if (aVar == null) {
            return;
        }
        PostTask.d(rh3.a, new ve3(24, (org.chromium.components.payments.a) aVar));
        this.f = null;
        if (this.g) {
            this.e.unbindService(this);
            this.g = false;
        }
        this.i.removeCallbacksAndMessages(null);
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        v21 u21Var;
        if (this.f == null) {
            return;
        }
        if (iBinder == null) {
            u21Var = null;
        } else {
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("org.chromium.IsReadyToPayService");
            u21Var = (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof v21)) ? new u21(iBinder) : (v21) iInterfaceQueryLocalInterface;
        }
        if (u21Var == null) {
            A();
            return;
        }
        oj2.U(2, 2, "PaymentRequest.PrePurchaseQuery");
        this.h = true;
        try {
            u21Var.g(this);
            this.i.postDelayed(new ve3(25, this), 400L);
        } catch (Throwable unused) {
            A();
        }
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName) {
        A();
    }
}
