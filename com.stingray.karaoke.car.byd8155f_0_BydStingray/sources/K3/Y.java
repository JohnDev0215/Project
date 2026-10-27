package k3;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;
import h2.AbstractC1346c;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class Y implements ServiceConnection {
    public final Bundle l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ C1628b0 f17871m;

    public Y(C1628b0 c1628b0, Bundle bundle) {
        this.f17871m = c1628b0;
        this.l = bundle;
    }

    @Override // android.content.ServiceConnection
    public final void onBindingDied(ComponentName componentName) {
        C1672v c1672v = this.f17871m.f17904a;
        Objects.requireNonNull(c1672v);
        c1672v.X0(new A2.l(c1672v, 21));
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        C1628b0 c1628b0 = this.f17871m;
        try {
            try {
                boolean zEquals = c1628b0.f17906e.f17758a.getPackageName().equals(componentName.getPackageName());
                C1672v c1672v = c1628b0.f17904a;
                if (!zEquals) {
                    AbstractC1346c.p("MCImplBase", "Expected connection to " + c1628b0.f17906e.f17758a.getPackageName() + " but is connected to " + componentName);
                    Objects.requireNonNull(c1672v);
                    c1672v.X0(new A2.l(c1672v, 21));
                    return;
                }
                InterfaceC1659o interfaceC1659oN1 = R0.n1(iBinder);
                if (interfaceC1659oN1 == null) {
                    AbstractC1346c.p("MCImplBase", "Service interface is missing.");
                    Objects.requireNonNull(c1672v);
                    c1672v.X0(new A2.l(c1672v, 21));
                } else {
                    String packageName = c1628b0.d.getPackageName();
                    int iMyPid = Process.myPid();
                    Bundle bundle = this.l;
                    c1672v.getClass();
                    interfaceC1659oN1.x(c1628b0.c, new C1642g(packageName, iMyPid, bundle).b());
                }
            } catch (RemoteException unused) {
                AbstractC1346c.z("MCImplBase", "Service " + componentName + " has died prematurely");
                C1672v c1672v2 = c1628b0.f17904a;
                Objects.requireNonNull(c1672v2);
                c1672v2.X0(new A2.l(c1672v2, 21));
            }
        } catch (Throwable th) {
            C1672v c1672v3 = c1628b0.f17904a;
            Objects.requireNonNull(c1672v3);
            c1672v3.X0(new A2.l(c1672v3, 21));
            throw th;
        }
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName) {
        C1672v c1672v = this.f17871m.f17904a;
        Objects.requireNonNull(c1672v);
        c1672v.X0(new A2.l(c1672v, 21));
    }
}
