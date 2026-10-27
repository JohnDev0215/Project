package defpackage;

import J.N;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.webkit.ValueCallback;
import org.chromium.content.browser.BrowserStartupControllerImpl;
import org.chromium.weblayer_private.DownloadImpl;
import org.chromium.weblayer_private.ProfileImpl;

/* JADX INFO: renamed from: dS, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractBinderC0664dS extends Binder implements IInterface {
    public AbstractBinderC0664dS() {
        attachInterface(this, "org.chromium.weblayer_private.interfaces.IWebLayer");
    }

    @Override // android.os.IInterface
    public IBinder asBinder() {
        return this;
    }

    @Override // android.os.Binder
    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        if (i == 1598968902) {
            parcel2.writeString("org.chromium.weblayer_private.interfaces.IWebLayer");
            return true;
        }
        switch (i) {
            case 2:
                parcel.enforceInterface("org.chromium.weblayer_private.interfaces.IWebLayer");
                ((XQ) this).i(PR.c(parcel.readStrongBinder()), null, PR.c(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case 3:
                parcel.enforceInterface("org.chromium.weblayer_private.interfaces.IWebLayer");
                QR qrC = PR.c(parcel.readStrongBinder());
                XQ xq = (XQ) this;
                AbstractC1054kS.a();
                xq.f(qrC, null);
                ((BrowserStartupControllerImpl) CD.a()).g(5, false);
                xq.v();
                parcel2.writeNoException();
                return true;
            case C0571bs.IS_GZIP_COMPRESSED_FIELD_NUMBER /* 4 */:
                parcel.enforceInterface("org.chromium.weblayer_private.interfaces.IWebLayer");
                VR vrC = UR.c(parcel.readStrongBinder());
                QR qrC2 = PR.c(parcel.readStrongBinder());
                AbstractC1054kS.a();
                BP bp = new BP(new CP(((XQ) this).a, vrC, (Bundle) BinderC0998jS.d(qrC2, Bundle.class)));
                parcel2.writeNoException();
                parcel2.writeStrongBinder(bp);
                return true;
            case C0571bs.SEED_DATA_FIELD_NUMBER /* 5 */:
                parcel.enforceInterface("org.chromium.weblayer_private.interfaces.IWebLayer");
                String string = parcel.readString();
                AbstractC1054kS.a();
                ProfileImpl profileImplA = ((XQ) this).a.a(string);
                parcel2.writeNoException();
                parcel2.writeStrongBinder(profileImplA);
                return true;
            case C0571bs.DATE_FIELD_NUMBER /* 6 */:
                parcel.enforceInterface("org.chromium.weblayer_private.interfaces.IWebLayer");
                boolean z = parcel.readInt() != 0;
                AbstractC1054kS.a();
                N.M1lGn20V(z);
                parcel2.writeNoException();
                return true;
            case 7:
                parcel.enforceInterface("org.chromium.weblayer_private.interfaces.IWebLayer");
                AbstractC1054kS.a();
                boolean zMdEjk1wX = N.MdEjk1wX();
                parcel2.writeNoException();
                parcel2.writeInt(zMdEjk1wX ? 1 : 0);
                return true;
            case 8:
                parcel.enforceInterface("org.chromium.weblayer_private.interfaces.IWebLayer");
                QR qrC3 = PR.c(parcel.readStrongBinder());
                AbstractC1054kS.a();
                AbstractC1054kS.a();
                XQ.u(qrC3, null);
                BinderC1108lQ binderC1108lQD = BinderC1108lQ.d();
                parcel2.writeNoException();
                parcel2.writeStrongBinder(binderC1108lQD != null ? binderC1108lQD : null);
                return true;
            case 9:
                parcel.enforceInterface("org.chromium.weblayer_private.interfaces.IWebLayer");
                ((XQ) this).i(PR.c(parcel.readStrongBinder()), PR.c(parcel.readStrongBinder()), PR.c(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case 10:
                parcel.enforceInterface("org.chromium.weblayer_private.interfaces.IWebLayer");
                QR qrC4 = PR.c(parcel.readStrongBinder());
                QR qrC5 = PR.c(parcel.readStrongBinder());
                XQ xq2 = (XQ) this;
                AbstractC1054kS.a();
                xq2.f(qrC4, qrC5);
                ((BrowserStartupControllerImpl) CD.a()).g(5, false);
                xq2.v();
                parcel2.writeNoException();
                return true;
            case 11:
                parcel.enforceInterface("org.chromium.weblayer_private.interfaces.IWebLayer");
                QR qrC6 = PR.c(parcel.readStrongBinder());
                QR qrC7 = PR.c(parcel.readStrongBinder());
                AbstractC1054kS.a();
                XQ.u(qrC6, qrC7);
                BinderC1108lQ binderC1108lQD2 = BinderC1108lQ.d();
                parcel2.writeNoException();
                parcel2.writeStrongBinder(binderC1108lQD2 != null ? binderC1108lQD2 : null);
                return true;
            case 12:
                parcel.enforceInterface("org.chromium.weblayer_private.interfaces.IWebLayer");
                QR qrC8 = PR.c(parcel.readStrongBinder());
                Intent intent = parcel.readInt() != 0 ? (Intent) Intent.CREATOR.createFromParcel(parcel) : null;
                AbstractC1054kS.a();
                DownloadImpl.d((Context) BinderC0998jS.d(qrC8, Context.class), intent, ((XQ) this).a);
                parcel2.writeNoException();
                return true;
            case 13:
                parcel.enforceInterface("org.chromium.weblayer_private.interfaces.IWebLayer");
                QR qrC9 = PR.c(parcel.readStrongBinder());
                AbstractC1054kS.a();
                ProfileImpl.d((ValueCallback) BinderC0998jS.d(qrC9, ValueCallback.class));
                parcel2.writeNoException();
                return true;
            case 14:
                parcel.enforceInterface("org.chromium.weblayer_private.interfaces.IWebLayer");
                InterfaceC0831gS interfaceC0831gSC = AbstractBinderC0775fS.c(parcel.readStrongBinder());
                AbstractC1054kS.a();
                XQ.d = interfaceC0831gSC;
                parcel2.writeNoException();
                return true;
            default:
                return super.onTransact(i, parcel, parcel2, i2);
        }
    }
}
