package defpackage;

import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import org.chromium.weblayer_private.ChildProcessServiceImpl;

/* JADX INFO: renamed from: fR, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractBinderC0774fR extends Binder implements IInterface {
    public AbstractBinderC0774fR() {
        attachInterface(this, "org.chromium.weblayer_private.interfaces.IChildProcessService");
    }

    @Override // android.os.IInterface
    public IBinder asBinder() {
        return this;
    }

    @Override // android.os.Binder
    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        if (i == 1) {
            parcel.enforceInterface("org.chromium.weblayer_private.interfaces.IChildProcessService");
            AbstractC1054kS.a();
            ((ChildProcessServiceImpl) this).a.b();
            parcel2.writeNoException();
            return true;
        }
        if (i == 2) {
            parcel.enforceInterface("org.chromium.weblayer_private.interfaces.IChildProcessService");
            ChildProcessServiceImpl childProcessServiceImpl = (ChildProcessServiceImpl) this;
            AbstractC1054kS.a();
            childProcessServiceImpl.a.c();
            childProcessServiceImpl.a = null;
            parcel2.writeNoException();
            return true;
        }
        if (i != 3) {
            if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel2.writeString("org.chromium.weblayer_private.interfaces.IChildProcessService");
            return true;
        }
        parcel.enforceInterface("org.chromium.weblayer_private.interfaces.IChildProcessService");
        QR qrC = PR.c(parcel.readStrongBinder());
        AbstractC1054kS.a();
        BinderC0998jS binderC0998jS = new BinderC0998jS(((ChildProcessServiceImpl) this).a.a((Intent) BinderC0998jS.d(qrC, Intent.class)));
        parcel2.writeNoException();
        parcel2.writeStrongBinder(binderC0998jS);
        return true;
    }
}
