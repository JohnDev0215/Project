package defpackage;

import J.N;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import org.chromium.weblayer_private.NavigationControllerImpl;
import org.chromium.weblayer_private.interfaces.NavigateParams;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class JR extends Binder implements IInterface {
    public JR() {
        attachInterface(this, "org.chromium.weblayer_private.interfaces.INavigationController");
    }

    @Override // android.os.IInterface
    public IBinder asBinder() {
        return this;
    }

    @Override // android.os.Binder
    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        if (i == 1598968902) {
            parcel2.writeString("org.chromium.weblayer_private.interfaces.INavigationController");
            return true;
        }
        switch (i) {
            case 1:
                parcel.enforceInterface("org.chromium.weblayer_private.interfaces.INavigationController");
                ((NavigationControllerImpl) this).c(parcel.readString(), parcel.readInt() != 0 ? (NavigateParams) NavigateParams.CREATOR.createFromParcel(parcel) : null);
                parcel2.writeNoException();
                return true;
            case 2:
                parcel.enforceInterface("org.chromium.weblayer_private.interfaces.INavigationController");
                NavigationControllerImpl navigationControllerImpl = (NavigationControllerImpl) this;
                AbstractC1054kS.a();
                N.MZrOkH8X(navigationControllerImpl.a, navigationControllerImpl);
                parcel2.writeNoException();
                return true;
            case 3:
                parcel.enforceInterface("org.chromium.weblayer_private.interfaces.INavigationController");
                NavigationControllerImpl navigationControllerImpl2 = (NavigationControllerImpl) this;
                AbstractC1054kS.a();
                N.MDkifRYV(navigationControllerImpl2.a, navigationControllerImpl2);
                parcel2.writeNoException();
                return true;
            case C0571bs.IS_GZIP_COMPRESSED_FIELD_NUMBER /* 4 */:
                parcel.enforceInterface("org.chromium.weblayer_private.interfaces.INavigationController");
                NavigationControllerImpl navigationControllerImpl3 = (NavigationControllerImpl) this;
                AbstractC1054kS.a();
                N.MgCpZ61D(navigationControllerImpl3.a, navigationControllerImpl3);
                parcel2.writeNoException();
                return true;
            case C0571bs.SEED_DATA_FIELD_NUMBER /* 5 */:
                parcel.enforceInterface("org.chromium.weblayer_private.interfaces.INavigationController");
                NavigationControllerImpl navigationControllerImpl4 = (NavigationControllerImpl) this;
                AbstractC1054kS.a();
                N.Mcpd2RLR(navigationControllerImpl4.a, navigationControllerImpl4);
                parcel2.writeNoException();
                return true;
            case C0571bs.DATE_FIELD_NUMBER /* 6 */:
                parcel.enforceInterface("org.chromium.weblayer_private.interfaces.INavigationController");
                NavigationControllerImpl navigationControllerImpl5 = (NavigationControllerImpl) this;
                AbstractC1054kS.a();
                int iMTQXD9PA = N.MTQXD9PA(navigationControllerImpl5.a, navigationControllerImpl5);
                parcel2.writeNoException();
                parcel2.writeInt(iMTQXD9PA);
                return true;
            case 7:
                parcel.enforceInterface("org.chromium.weblayer_private.interfaces.INavigationController");
                NavigationControllerImpl navigationControllerImpl6 = (NavigationControllerImpl) this;
                AbstractC1054kS.a();
                int iMYvsya1k = N.MYvsya1k(navigationControllerImpl6.a, navigationControllerImpl6);
                parcel2.writeNoException();
                parcel2.writeInt(iMYvsya1k);
                return true;
            case 8:
                parcel.enforceInterface("org.chromium.weblayer_private.interfaces.INavigationController");
                int i3 = parcel.readInt();
                NavigationControllerImpl navigationControllerImpl7 = (NavigationControllerImpl) this;
                AbstractC1054kS.a();
                String strMdarrSGz = N.MdarrSGz(navigationControllerImpl7.a, navigationControllerImpl7, i3);
                parcel2.writeNoException();
                parcel2.writeString(strMdarrSGz);
                return true;
            case 9:
                parcel.enforceInterface("org.chromium.weblayer_private.interfaces.INavigationController");
                NavigationControllerImpl navigationControllerImpl8 = (NavigationControllerImpl) this;
                AbstractC1054kS.a();
                boolean zMk5Op77A = N.Mk5Op77A(navigationControllerImpl8.a, navigationControllerImpl8);
                parcel2.writeNoException();
                parcel2.writeInt(zMk5Op77A ? 1 : 0);
                return true;
            case 10:
                parcel.enforceInterface("org.chromium.weblayer_private.interfaces.INavigationController");
                NavigationControllerImpl navigationControllerImpl9 = (NavigationControllerImpl) this;
                AbstractC1054kS.a();
                boolean zMyt080DX = N.Myt080DX(navigationControllerImpl9.a, navigationControllerImpl9);
                parcel2.writeNoException();
                parcel2.writeInt(zMyt080DX ? 1 : 0);
                return true;
            case 11:
                parcel.enforceInterface("org.chromium.weblayer_private.interfaces.INavigationController");
                int i4 = parcel.readInt();
                NavigationControllerImpl navigationControllerImpl10 = (NavigationControllerImpl) this;
                AbstractC1054kS.a();
                N.M0Oi$rdJ(navigationControllerImpl10.a, navigationControllerImpl10, i4);
                parcel2.writeNoException();
                return true;
            case 12:
                parcel.enforceInterface("org.chromium.weblayer_private.interfaces.INavigationController");
                int i5 = parcel.readInt();
                NavigationControllerImpl navigationControllerImpl11 = (NavigationControllerImpl) this;
                AbstractC1054kS.a();
                String strME_QUEJm = N.ME_QUEJm(navigationControllerImpl11.a, navigationControllerImpl11, i5);
                parcel2.writeNoException();
                parcel2.writeString(strME_QUEJm);
                return true;
            default:
                return super.onTransact(i, parcel, parcel2, i2);
        }
    }
}
