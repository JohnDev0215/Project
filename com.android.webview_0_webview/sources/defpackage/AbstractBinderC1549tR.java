package defpackage;

import J.N;
import android.content.Context;
import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import org.chromium.support_lib_boundary.WebSettingsBoundaryInterface;
import org.chromium.weblayer_private.DownloadImpl;

/* JADX INFO: renamed from: tR, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractBinderC1549tR extends Binder implements InterfaceC1604uR {
    public AbstractBinderC1549tR() {
        attachInterface(this, "org.chromium.weblayer_private.interfaces.IDownload");
    }

    @Override // android.os.IInterface
    public IBinder asBinder() {
        return this;
    }

    @Override // android.os.Binder
    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        if (i == 1598968902) {
            parcel2.writeString("org.chromium.weblayer_private.interfaces.IDownload");
            return true;
        }
        switch (i) {
            case 1:
                parcel.enforceInterface("org.chromium.weblayer_private.interfaces.IDownload");
                int i3 = ((DownloadImpl) this).i();
                parcel2.writeNoException();
                parcel2.writeInt(i3);
                return true;
            case 2:
                parcel.enforceInterface("org.chromium.weblayer_private.interfaces.IDownload");
                long jU = ((DownloadImpl) this).u();
                parcel2.writeNoException();
                parcel2.writeLong(jU);
                return true;
            case 3:
                parcel.enforceInterface("org.chromium.weblayer_private.interfaces.IDownload");
                DownloadImpl downloadImpl = (DownloadImpl) this;
                AbstractC1054kS.a();
                downloadImpl.w();
                long jMLPJbQwI = N.MLPJbQwI(downloadImpl.d, downloadImpl);
                parcel2.writeNoException();
                parcel2.writeLong(jMLPJbQwI);
                return true;
            case C0571bs.IS_GZIP_COMPRESSED_FIELD_NUMBER /* 4 */:
                parcel.enforceInterface("org.chromium.weblayer_private.interfaces.IDownload");
                DownloadImpl downloadImpl2 = (DownloadImpl) this;
                AbstractC1054kS.a();
                downloadImpl2.w();
                N.Mmh9t8Wp(downloadImpl2.d, downloadImpl2);
                downloadImpl2.x();
                parcel2.writeNoException();
                return true;
            case C0571bs.SEED_DATA_FIELD_NUMBER /* 5 */:
                parcel.enforceInterface("org.chromium.weblayer_private.interfaces.IDownload");
                DownloadImpl downloadImpl3 = (DownloadImpl) this;
                AbstractC1054kS.a();
                downloadImpl3.w();
                N.MBdFHh_o(downloadImpl3.d, downloadImpl3);
                downloadImpl3.x();
                parcel2.writeNoException();
                return true;
            case C0571bs.DATE_FIELD_NUMBER /* 6 */:
                parcel.enforceInterface("org.chromium.weblayer_private.interfaces.IDownload");
                DownloadImpl downloadImpl4 = (DownloadImpl) this;
                AbstractC1054kS.a();
                downloadImpl4.w();
                N.M7ycbCea(downloadImpl4.d, downloadImpl4);
                downloadImpl4.x();
                parcel2.writeNoException();
                return true;
            case 7:
                parcel.enforceInterface("org.chromium.weblayer_private.interfaces.IDownload");
                String strF = ((DownloadImpl) this).f();
                parcel2.writeNoException();
                parcel2.writeString(strF);
                return true;
            case 8:
                parcel.enforceInterface("org.chromium.weblayer_private.interfaces.IDownload");
                DownloadImpl downloadImpl5 = (DownloadImpl) this;
                AbstractC1054kS.a();
                downloadImpl5.w();
                int i4 = 7;
                switch (N.MMo$maOT(downloadImpl5.d, downloadImpl5)) {
                    case WebSettingsBoundaryInterface.ForceDarkBehavior.FORCE_DARK_ONLY /* 0 */:
                        i4 = 0;
                        break;
                    case 1:
                        i4 = 1;
                        break;
                    case 2:
                        i4 = 2;
                        break;
                    case 3:
                        i4 = 3;
                        break;
                    case C0571bs.IS_GZIP_COMPRESSED_FIELD_NUMBER /* 4 */:
                        i4 = 4;
                        break;
                    case C0571bs.SEED_DATA_FIELD_NUMBER /* 5 */:
                        i4 = 5;
                        break;
                    case C0571bs.DATE_FIELD_NUMBER /* 6 */:
                        i4 = 6;
                        break;
                }
                parcel2.writeNoException();
                parcel2.writeInt(i4);
                return true;
            case 9:
                parcel.enforceInterface("org.chromium.weblayer_private.interfaces.IDownload");
                DownloadImpl downloadImpl6 = (DownloadImpl) this;
                AbstractC1054kS.a();
                downloadImpl6.w();
                String strMiYZ62wR = N.MiYZ62wR(downloadImpl6.d, downloadImpl6);
                parcel2.writeNoException();
                parcel2.writeString(strMiYZ62wR);
                return true;
            case 10:
                parcel.enforceInterface("org.chromium.weblayer_private.interfaces.IDownload");
                DownloadImpl downloadImpl7 = (DownloadImpl) this;
                AbstractC1054kS.a();
                downloadImpl7.w();
                downloadImpl7.e = true;
                if (downloadImpl7.g != null) {
                    Context context = Is.a;
                    V1 v1 = context == null ? null : new V1(context);
                    if (v1 != null) {
                        v1.b.cancel(null, downloadImpl7.f);
                    }
                    downloadImpl7.g = null;
                }
                parcel2.writeNoException();
                return true;
            default:
                return super.onTransact(i, parcel, parcel2, i2);
        }
    }
}
