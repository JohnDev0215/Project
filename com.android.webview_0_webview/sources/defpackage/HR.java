package defpackage;

import J.N;
import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import java.util.Arrays;
import java.util.List;
import org.chromium.weblayer_private.NavigationImpl;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class HR extends Binder implements IR {
    public HR() {
        attachInterface(this, "org.chromium.weblayer_private.interfaces.INavigation");
    }

    @Override // android.os.IInterface
    public IBinder asBinder() {
        return this;
    }

    @Override // android.os.Binder
    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        if (i == 1598968902) {
            parcel2.writeString("org.chromium.weblayer_private.interfaces.INavigation");
            return true;
        }
        int i3 = 3;
        switch (i) {
            case 1:
                parcel.enforceInterface("org.chromium.weblayer_private.interfaces.INavigation");
                NavigationImpl navigationImpl = (NavigationImpl) this;
                AbstractC1054kS.a();
                navigationImpl.c();
                int iMOjYwlCQ = N.MOjYwlCQ(navigationImpl.b, navigationImpl);
                if (iMOjYwlCQ == 0) {
                    i3 = 0;
                } else if (iMOjYwlCQ == 1) {
                    i3 = 1;
                } else if (iMOjYwlCQ == 2) {
                    i3 = 2;
                }
                parcel2.writeNoException();
                parcel2.writeInt(i3);
                return true;
            case 2:
                parcel.enforceInterface("org.chromium.weblayer_private.interfaces.INavigation");
                NavigationImpl navigationImpl2 = (NavigationImpl) this;
                AbstractC1054kS.a();
                navigationImpl2.c();
                String strMLe5UH8x = N.MLe5UH8x(navigationImpl2.b, navigationImpl2);
                parcel2.writeNoException();
                parcel2.writeString(strMLe5UH8x);
                return true;
            case 3:
                parcel.enforceInterface("org.chromium.weblayer_private.interfaces.INavigation");
                NavigationImpl navigationImpl3 = (NavigationImpl) this;
                AbstractC1054kS.a();
                navigationImpl3.c();
                List<String> listAsList = Arrays.asList(N.M4$r2b34(navigationImpl3.b, navigationImpl3));
                parcel2.writeNoException();
                parcel2.writeStringList(listAsList);
                return true;
            case C0571bs.IS_GZIP_COMPRESSED_FIELD_NUMBER /* 4 */:
                parcel.enforceInterface("org.chromium.weblayer_private.interfaces.INavigation");
                NavigationImpl navigationImpl4 = (NavigationImpl) this;
                AbstractC1054kS.a();
                navigationImpl4.c();
                int iMavL_hzo = N.MavL_hzo(navigationImpl4.b, navigationImpl4);
                parcel2.writeNoException();
                parcel2.writeInt(iMavL_hzo);
                return true;
            case C0571bs.SEED_DATA_FIELD_NUMBER /* 5 */:
                parcel.enforceInterface("org.chromium.weblayer_private.interfaces.INavigation");
                NavigationImpl navigationImpl5 = (NavigationImpl) this;
                AbstractC1054kS.a();
                navigationImpl5.c();
                boolean zMNvdMHI6 = N.MNvdMHI6(navigationImpl5.b, navigationImpl5);
                parcel2.writeNoException();
                parcel2.writeInt(zMNvdMHI6 ? 1 : 0);
                return true;
            case C0571bs.DATE_FIELD_NUMBER /* 6 */:
                parcel.enforceInterface("org.chromium.weblayer_private.interfaces.INavigation");
                NavigationImpl navigationImpl6 = (NavigationImpl) this;
                AbstractC1054kS.a();
                navigationImpl6.c();
                boolean zMIFgE7DZ = N.MIFgE7DZ(navigationImpl6.b, navigationImpl6);
                parcel2.writeNoException();
                parcel2.writeInt(zMIFgE7DZ ? 1 : 0);
                return true;
            case 7:
                parcel.enforceInterface("org.chromium.weblayer_private.interfaces.INavigation");
                NavigationImpl navigationImpl7 = (NavigationImpl) this;
                AbstractC1054kS.a();
                navigationImpl7.c();
                int iMu3sbx92 = N.Mu3sbx92(navigationImpl7.b, navigationImpl7);
                if (iMu3sbx92 == 0) {
                    i3 = 0;
                } else if (iMu3sbx92 == 1) {
                    i3 = 1;
                } else if (iMu3sbx92 == 2) {
                    i3 = 2;
                } else if (iMu3sbx92 != 3) {
                    i3 = 4;
                    if (iMu3sbx92 != 4) {
                        i3 = 5;
                        if (iMu3sbx92 != 5) {
                            throw new IllegalArgumentException(Z7.a("Unexpected load error ", iMu3sbx92));
                        }
                    }
                }
                parcel2.writeNoException();
                parcel2.writeInt(i3);
                return true;
            case 8:
                parcel.enforceInterface("org.chromium.weblayer_private.interfaces.INavigation");
                String string = parcel.readString();
                String string2 = parcel.readString();
                NavigationImpl navigationImpl8 = (NavigationImpl) this;
                if (!N.MFHuFkAr(string)) {
                    throw new IllegalArgumentException("Invalid header");
                }
                if (!N.MDc77mDW(string2)) {
                    throw new IllegalArgumentException("Invalid value");
                }
                if (!N.MDotswwA(navigationImpl8.b, navigationImpl8, string, string2)) {
                    throw new IllegalStateException();
                }
                parcel2.writeNoException();
                return true;
            default:
                return super.onTransact(i, parcel, parcel2, i2);
        }
    }
}
