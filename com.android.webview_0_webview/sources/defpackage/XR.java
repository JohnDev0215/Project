package defpackage;

import J.N;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import org.chromium.weblayer_private.NavigationControllerImpl;
import org.chromium.weblayer_private.TabCallbackProxy;
import org.chromium.weblayer_private.TabImpl;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class XR extends Binder implements YR {
    public XR() {
        attachInterface(this, "org.chromium.weblayer_private.interfaces.ITab");
    }

    public static YR c(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("org.chromium.weblayer_private.interfaces.ITab");
        return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof YR)) ? new WR(iBinder) : (YR) iInterfaceQueryLocalInterface;
    }

    @Override // android.os.IInterface
    public IBinder asBinder() {
        return this;
    }

    @Override // android.os.Binder
    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        if (i == 1598968902) {
            parcel2.writeString("org.chromium.weblayer_private.interfaces.ITab");
            return true;
        }
        switch (i) {
            case 1:
                parcel.enforceInterface("org.chromium.weblayer_private.interfaces.ITab");
                InterfaceC0552bS interfaceC0552bSC = AbstractBinderC0496aS.c(parcel.readStrongBinder());
                TabImpl tabImpl = (TabImpl) this;
                AbstractC1054kS.a();
                tabImpl.m = interfaceC0552bSC;
                tabImpl.e = new TabCallbackProxy(tabImpl.a, interfaceC0552bSC);
                parcel2.writeNoException();
                return true;
            case 2:
                parcel.enforceInterface("org.chromium.weblayer_private.interfaces.ITab");
                NR nrC = MR.c(parcel.readStrongBinder());
                TabImpl tabImpl2 = (TabImpl) this;
                AbstractC1054kS.a();
                NavigationControllerImpl navigationControllerImpl = new NavigationControllerImpl(tabImpl2, nrC);
                tabImpl2.f = navigationControllerImpl;
                parcel2.writeNoException();
                parcel2.writeStrongBinder(navigationControllerImpl);
                return true;
            case 3:
                parcel.enforceInterface("org.chromium.weblayer_private.interfaces.ITab");
                InterfaceC1769xR interfaceC1769xRC = AbstractBinderC1714wR.c(parcel.readStrongBinder());
                AbstractC1054kS.a();
                ((TabImpl) this).b.g.c = interfaceC1769xRC;
                parcel2.writeNoException();
                return true;
            case C0571bs.IS_GZIP_COMPRESSED_FIELD_NUMBER /* 4 */:
                parcel.enforceInterface("org.chromium.weblayer_private.interfaces.ITab");
                ((TabImpl) this).B(AbstractBinderC1879zR.c(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case C0571bs.SEED_DATA_FIELD_NUMBER /* 5 */:
                parcel.enforceInterface("org.chromium.weblayer_private.interfaces.ITab");
                ((TabImpl) this).D(FR.c(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case C0571bs.DATE_FIELD_NUMBER /* 6 */:
                parcel.enforceInterface("org.chromium.weblayer_private.interfaces.ITab");
                String string = parcel.readString();
                boolean z = parcel.readInt() != 0;
                QR qrC = PR.c(parcel.readStrongBinder());
                TabImpl tabImpl3 = (TabImpl) this;
                AbstractC1054kS.a();
                N.M9gymlSj(tabImpl3.a, string, z, new HQ(tabImpl3, qrC));
                parcel2.writeNoException();
                return true;
            case 7:
                parcel.enforceInterface("org.chromium.weblayer_private.interfaces.ITab");
                ((TabImpl) this).E(parcel.readInt() != 0);
                parcel2.writeNoException();
                return true;
            case 8:
                parcel.enforceInterface("org.chromium.weblayer_private.interfaces.ITab");
                AbstractC1054kS.a();
                int i3 = ((TabImpl) this).n;
                parcel2.writeNoException();
                parcel2.writeInt(i3);
                return true;
            case 9:
                parcel.enforceInterface("org.chromium.weblayer_private.interfaces.ITab");
                boolean zC = ((TabImpl) this).C(CR.c(parcel.readStrongBinder()));
                parcel2.writeNoException();
                parcel2.writeInt(zC ? 1 : 0);
                return true;
            case 10:
                parcel.enforceInterface("org.chromium.weblayer_private.interfaces.ITab");
                ((TabImpl) this).u(parcel.readString(), parcel.readInt() != 0);
                parcel2.writeNoException();
                return true;
            default:
                switch (i) {
                    case 12:
                        parcel.enforceInterface("org.chromium.weblayer_private.interfaces.ITab");
                        AbstractC1054kS.a();
                        ((TabImpl) this).c.k(false);
                        parcel2.writeNoException();
                        return true;
                    case 13:
                        parcel.enforceInterface("org.chromium.weblayer_private.interfaces.ITab");
                        boolean zI = ((TabImpl) this).i();
                        parcel2.writeNoException();
                        parcel2.writeInt(zI ? 1 : 0);
                        return true;
                    case 14:
                        parcel.enforceInterface("org.chromium.weblayer_private.interfaces.ITab");
                        String strMDUS1J6g = N.MDUS1J6g(((TabImpl) this).a);
                        parcel2.writeNoException();
                        parcel2.writeString(strMDUS1J6g);
                        return true;
                    default:
                        return super.onTransact(i, parcel, parcel2, i2);
                }
        }
    }
}
