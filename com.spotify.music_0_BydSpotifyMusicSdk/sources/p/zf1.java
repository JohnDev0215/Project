package p;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.IInterface;

/* JADX INFO: loaded from: classes.dex */
public final class zf1 implements ServiceConnection {
    public final /* synthetic */ int a = 1;
    public final /* synthetic */ Object b;

    public /* synthetic */ zf1(Object obj) {
        this.b = obj;
    }

    public final boolean a() {
        u4.z(this.b);
        throw null;
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        x21 x21Var;
        int i = this.a;
        Object obj = this.b;
        switch (i) {
            case 1:
                v03.j(componentName, "name");
                v03.j(iBinder, "service");
                ql1 ql1Var = (ql1) obj;
                int i2 = rl1.c;
                IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("androidx.room.IMultiInstanceInvalidationService");
                if (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof x21)) {
                    w21 w21Var = new w21();
                    w21Var.b = iBinder;
                    x21Var = w21Var;
                } else {
                    x21Var = (x21) iInterfaceQueryLocalInterface;
                }
                ql1Var.f = x21Var;
                ql1Var.c.execute(ql1Var.i);
                break;
            default:
                g03 g03Var = (g03) obj;
                g03Var.b.e("ServiceConnectionImpl.onServiceConnected(%s)", componentName);
                g03Var.a().post(new zz2(this, iBinder));
                break;
        }
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName) {
        int i = this.a;
        Object obj = this.b;
        switch (i) {
            case 1:
                v03.j(componentName, "name");
                ql1 ql1Var = (ql1) obj;
                ql1Var.c.execute(ql1Var.j);
                ql1Var.f = null;
                break;
            default:
                g03 g03Var = (g03) obj;
                g03Var.b.e("ServiceConnectionImpl.onServiceDisconnected(%s)", componentName);
                g03Var.a().post(new a03(1, this));
                break;
        }
    }

    public /* synthetic */ zf1(g03 g03Var) {
        this.b = g03Var;
    }
}
