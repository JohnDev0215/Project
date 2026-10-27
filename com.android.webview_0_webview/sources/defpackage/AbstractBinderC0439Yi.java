package defpackage;

import android.location.Location;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import java.util.Objects;

/* JADX INFO: renamed from: Yi, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractBinderC0439Yi extends AbstractBinderC0401Wg implements InterfaceC0385Vi {
    public AbstractBinderC0439Yi() {
        super("com.google.android.gms.location.ILocationListener");
    }

    public static InterfaceC0385Vi v(IBinder iBinder) {
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.location.ILocationListener");
        return iInterfaceQueryLocalInterface instanceof InterfaceC0385Vi ? (InterfaceC0385Vi) iInterfaceQueryLocalInterface : new C0421Xi(iBinder);
    }

    @Override // defpackage.AbstractBinderC0401Wg
    public boolean u(int i, Parcel parcel, Parcel parcel2, int i2) {
        if (i != 1) {
            return false;
        }
        Location location = (Location) AbstractC0383Vg.b(parcel, Location.CREATOR);
        BinderC0170Ji binderC0170Ji = (BinderC0170Ji) this;
        synchronized (binderC0170Ji) {
            C0835gd c0835gd = binderC0170Ji.a;
            C0152Ii c0152Ii = new C0152Ii(location);
            Objects.requireNonNull(c0835gd);
            Cif.g(c0152Ii, "Notifier must not be null");
            c0835gd.a.sendMessage(c0835gd.a.obtainMessage(1, c0152Ii));
        }
        return true;
    }
}
