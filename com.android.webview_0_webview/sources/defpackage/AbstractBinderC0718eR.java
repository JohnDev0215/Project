package defpackage;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import org.chromium.weblayer_private.BrowserImpl;

/* JADX INFO: renamed from: eR, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractBinderC0718eR extends Binder implements IInterface {
    public AbstractBinderC0718eR() {
        attachInterface(this, "org.chromium.weblayer_private.interfaces.IBrowserFragment");
    }

    @Override // android.os.IInterface
    public IBinder asBinder() {
        return this;
    }

    @Override // android.os.Binder
    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        if (i == 1) {
            parcel.enforceInterface("org.chromium.weblayer_private.interfaces.IBrowserFragment");
            AbstractC1054kS.a();
            CP cp = ((BP) this).a;
            parcel2.writeNoException();
            if (cp == null) {
                cp = null;
            }
            parcel2.writeStrongBinder(cp);
            return true;
        }
        if (i != 2) {
            if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel2.writeString("org.chromium.weblayer_private.interfaces.IBrowserFragment");
            return true;
        }
        parcel.enforceInterface("org.chromium.weblayer_private.interfaces.IBrowserFragment");
        AbstractC1054kS.a();
        BrowserImpl browserImpl = ((BP) this).a.d;
        if (browserImpl == null) {
            throw new RuntimeException("Browser is available only between BrowserFragment's onCreate() and onDestroy().");
        }
        parcel2.writeNoException();
        parcel2.writeStrongBinder(browserImpl.asBinder());
        return true;
    }
}
