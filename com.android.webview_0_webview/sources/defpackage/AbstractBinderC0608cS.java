package defpackage;

import android.content.Context;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import org.chromium.weblayer_private.BrowserImpl;

/* JADX INFO: renamed from: cS, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractBinderC0608cS extends Binder implements IInterface {
    public AbstractBinderC0608cS() {
        attachInterface(this, "org.chromium.weblayer_private.interfaces.IUrlBarController");
    }

    @Override // android.os.IInterface
    public IBinder asBinder() {
        return this;
    }

    @Override // android.os.Binder
    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        if (i != 1) {
            if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel2.writeString("org.chromium.weblayer_private.interfaces.IUrlBarController");
            return true;
        }
        parcel.enforceInterface("org.chromium.weblayer_private.interfaces.IUrlBarController");
        Bundle bundle = parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null;
        QQ qq = (QQ) this;
        AbstractC1054kS.a();
        BrowserImpl browserImpl = qq.a;
        if (browserImpl == null) {
            throw new IllegalStateException("UrlBarView cannot be created without a valid Browser");
        }
        Context contextV = browserImpl.v();
        if (contextV == null) {
            throw new IllegalStateException("BrowserFragment not attached yet.");
        }
        BinderC0998jS binderC0998jS = new BinderC0998jS(new PQ(qq, contextV, bundle));
        parcel2.writeNoException();
        parcel2.writeStrongBinder(binderC0998jS);
        return true;
    }
}
