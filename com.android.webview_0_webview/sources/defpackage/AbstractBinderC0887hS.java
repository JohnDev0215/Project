package defpackage;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import org.chromium.weblayer_private.WebLayerFactoryImpl;

/* JADX INFO: renamed from: hS, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractBinderC0887hS extends Binder implements IInterface {
    public AbstractBinderC0887hS() {
        attachInterface(this, "org.chromium.weblayer_private.interfaces.IWebLayerFactory");
    }

    @Override // android.os.IInterface
    public IBinder asBinder() {
        return this;
    }

    @Override // android.os.Binder
    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        if (i == 1) {
            parcel.enforceInterface("org.chromium.weblayer_private.interfaces.IWebLayerFactory");
            boolean zD = ((WebLayerFactoryImpl) this).d();
            parcel2.writeNoException();
            parcel2.writeInt(zD ? 1 : 0);
            return true;
        }
        if (i == 2) {
            parcel.enforceInterface("org.chromium.weblayer_private.interfaces.IWebLayerFactory");
            AbstractC1054kS.a();
            XQ xq = new XQ();
            parcel2.writeNoException();
            parcel2.writeStrongBinder(xq);
            return true;
        }
        if (i == 3) {
            parcel.enforceInterface("org.chromium.weblayer_private.interfaces.IWebLayerFactory");
            AbstractC1054kS.a();
            parcel2.writeNoException();
            parcel2.writeString("83.0.4103.120");
            return true;
        }
        if (i != 4) {
            if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel2.writeString("org.chromium.weblayer_private.interfaces.IWebLayerFactory");
            return true;
        }
        parcel.enforceInterface("org.chromium.weblayer_private.interfaces.IWebLayerFactory");
        AbstractC1054kS.a();
        parcel2.writeNoException();
        parcel2.writeInt(83);
        return true;
    }
}
