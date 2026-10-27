package defpackage;

import J.N;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.view.View;
import android.webkit.ValueCallback;
import java.util.List;
import java.util.Objects;
import org.chromium.weblayer_private.BrowserImpl;
import org.chromium.weblayer_private.ProfileImpl;
import org.chromium.weblayer_private.TabImpl;

/* JADX INFO: renamed from: aR, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractBinderC0495aR extends Binder implements IInterface {
    public AbstractBinderC0495aR() {
        attachInterface(this, "org.chromium.weblayer_private.interfaces.IBrowser");
    }

    @Override // android.os.IInterface
    public IBinder asBinder() {
        return this;
    }

    @Override // android.os.Binder
    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        if (i == 1598968902) {
            parcel2.writeString("org.chromium.weblayer_private.interfaces.IBrowser");
            return true;
        }
        switch (i) {
            case 1:
                parcel.enforceInterface("org.chromium.weblayer_private.interfaces.IBrowser");
                AbstractC1054kS.a();
                ProfileImpl profileImpl = ((BrowserImpl) this).c;
                parcel2.writeNoException();
                parcel2.writeStrongBinder(profileImpl != null ? profileImpl : null);
                return true;
            case 2:
                parcel.enforceInterface("org.chromium.weblayer_private.interfaces.IBrowser");
                QR qrC = PR.c(parcel.readStrongBinder());
                AbstractC1054kS.a();
                ((BrowserImpl) this).x().c.e((View) BinderC0998jS.d(qrC, View.class));
                parcel2.writeNoException();
                return true;
            case 3:
                parcel.enforceInterface("org.chromium.weblayer_private.interfaces.IBrowser");
                i = parcel.readInt() != 0 ? 1 : 0;
                QR qrC2 = PR.c(parcel.readStrongBinder());
                AbstractC1054kS.a();
                ((BrowserImpl) this).x().a.b(i, (ValueCallback) BinderC0998jS.d(qrC2, ValueCallback.class));
                parcel2.writeNoException();
                return true;
            case C0571bs.IS_GZIP_COMPRESSED_FIELD_NUMBER /* 4 */:
                parcel.enforceInterface("org.chromium.weblayer_private.interfaces.IBrowser");
                boolean z = ((BrowserImpl) this).z(XR.c(parcel.readStrongBinder()));
                parcel2.writeNoException();
                parcel2.writeInt(z ? 1 : 0);
                return true;
            case C0571bs.SEED_DATA_FIELD_NUMBER /* 5 */:
                parcel.enforceInterface("org.chromium.weblayer_private.interfaces.IBrowser");
                BrowserImpl browserImpl = (BrowserImpl) this;
                AbstractC1054kS.a();
                if (browserImpl.u() != null) {
                    TabImpl tabImplU = browserImpl.u();
                    Objects.requireNonNull(tabImplU);
                    AbstractC1054kS.a();
                    i = tabImplU.n;
                }
                parcel2.writeNoException();
                parcel2.writeInt(i);
                return true;
            case C0571bs.DATE_FIELD_NUMBER /* 6 */:
                parcel.enforceInterface("org.chromium.weblayer_private.interfaces.IBrowser");
                List listW = ((BrowserImpl) this).w();
                parcel2.writeNoException();
                parcel2.writeList(listW);
                return true;
            case 7:
                parcel.enforceInterface("org.chromium.weblayer_private.interfaces.IBrowser");
                InterfaceC0663dR interfaceC0663dRC = AbstractBinderC0607cR.c(parcel.readStrongBinder());
                BrowserImpl browserImpl2 = (BrowserImpl) this;
                AbstractC1054kS.a();
                browserImpl2.f = interfaceC0663dRC;
                EP ep = browserImpl2.m;
                browserImpl2.m = null;
                N.MF3mfzBs(browserImpl2.b, browserImpl2, ep.a, ep.b, ep.c);
                if (browserImpl2.w().size() > 0) {
                    browserImpl2.B();
                } else {
                    String str = ep.a;
                    if (str == null || str.isEmpty()) {
                        TabImpl tabImpl = new TabImpl(browserImpl2.c, browserImpl2.e);
                        browserImpl2.c(tabImpl);
                        browserImpl2.z(tabImpl);
                    }
                }
                parcel2.writeNoException();
                return true;
            case 8:
                parcel.enforceInterface("org.chromium.weblayer_private.interfaces.IBrowser");
                ((BrowserImpl) this).c(XR.c(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case 9:
                parcel.enforceInterface("org.chromium.weblayer_private.interfaces.IBrowser");
                YR yrC = XR.c(parcel.readStrongBinder());
                BrowserImpl browserImpl3 = (BrowserImpl) this;
                AbstractC1054kS.a();
                TabImpl tabImpl2 = (TabImpl) yrC;
                if (tabImpl2.j == browserImpl3) {
                    browserImpl3.i(tabImpl2);
                }
                parcel2.writeNoException();
                return true;
            case 10:
                parcel.enforceInterface("org.chromium.weblayer_private.interfaces.IBrowser");
                AbstractC1054kS.a();
                QQ qq = ((BrowserImpl) this).i;
                parcel2.writeNoException();
                parcel2.writeStrongBinder(qq != null ? qq : null);
                return true;
            default:
                return super.onTransact(i, parcel, parcel2, i2);
        }
    }
}
