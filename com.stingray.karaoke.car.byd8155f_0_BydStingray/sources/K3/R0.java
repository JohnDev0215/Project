package k3;

import android.content.Context;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.stingray.karaoke.car.player.PlayerService;
import h2.AbstractC1346c;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class R0 extends Binder implements InterfaceC1659o {
    public final WeakReference c;
    public final Handler d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final l3.U f17852e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Set f17853f;

    public R0(PlayerService playerService) {
        attachInterface(this, "androidx.media3.session.IMediaSessionService");
        this.c = new WeakReference(playerService);
        Context applicationContext = playerService.getApplicationContext();
        this.d = new Handler(applicationContext.getMainLooper());
        this.f17852e = l3.U.a(applicationContext);
        this.f17853f = Collections.synchronizedSet(new HashSet());
    }

    public static InterfaceC1659o n1(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("androidx.media3.session.IMediaSessionService");
        if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof InterfaceC1659o)) {
            return (InterfaceC1659o) iInterfaceQueryLocalInterface;
        }
        C1657n c1657n = new C1657n();
        c1657n.c = iBinder;
        return c1657n;
    }

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        return this;
    }

    @Override // android.os.Binder
    public final boolean onTransact(int i7, Parcel parcel, Parcel parcel2, int i10) {
        if (i7 >= 1 && i7 <= 16777215) {
            parcel.enforceInterface("androidx.media3.session.IMediaSessionService");
        }
        if (i7 == 1598968902) {
            parcel2.writeString("androidx.media3.session.IMediaSessionService");
            return true;
        }
        if (i7 != 3001) {
            return super.onTransact(i7, parcel, parcel2, i10);
        }
        x(BinderC1658n0.n1(parcel.readStrongBinder()), (Bundle) (parcel.readInt() != 0 ? Bundle.CREATOR.createFromParcel(parcel) : null));
        return true;
    }

    @Override // k3.InterfaceC1659o
    public final void x(InterfaceC1651k interfaceC1651k, Bundle bundle) {
        if (interfaceC1651k == null || bundle == null) {
            return;
        }
        try {
            C1642g c1642gA = C1642g.a(bundle);
            if (this.c.get() == null) {
                try {
                    interfaceC1651k.a();
                    return;
                } catch (RemoteException unused) {
                    return;
                }
            }
            int callingPid = Binder.getCallingPid();
            int callingUid = Binder.getCallingUid();
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            if (callingPid == 0) {
                callingPid = c1642gA.d;
            }
            l3.T t10 = new l3.T(c1642gA.c, callingPid, callingUid);
            boolean zB = this.f17852e.b(t10);
            this.f17853f.add(interfaceC1651k);
            try {
                this.d.post(new RunnableC1664q0(this, interfaceC1651k, t10, c1642gA, zB, 1));
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        } catch (RuntimeException e10) {
            AbstractC1346c.A("MSessionService", "Ignoring malformed Bundle for ConnectionRequest", e10);
        }
    }
}
