package defpackage;

import J.N;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.ContextThemeWrapper;
import java.util.Iterator;
import org.chromium.weblayer_private.BrowserImpl;
import org.chromium.weblayer_private.TabImpl;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class SR extends Binder implements IInterface {
    public SR() {
        attachInterface(this, "org.chromium.weblayer_private.interfaces.IRemoteFragment");
    }

    @Override // android.os.IInterface
    public IBinder asBinder() {
        return this;
    }

    @Override // android.os.Binder
    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        JN jn;
        if (i == 1598968902) {
            parcel2.writeString("org.chromium.weblayer_private.interfaces.IRemoteFragment");
            return true;
        }
        switch (i) {
            case 1:
                parcel.enforceInterface("org.chromium.weblayer_private.interfaces.IRemoteFragment");
                QR qrC = PR.c(parcel.readStrongBinder());
                AbstractC1054kS.a();
                Bundle bundle = (Bundle) BinderC0998jS.d(qrC, Bundle.class);
                CP cp = (CP) ((DQ) this);
                AbstractC1054kS.a();
                try {
                    ((TR) cp.a).w(new BinderC0998jS(bundle));
                    cp.d = new BrowserImpl(cp.b, cp.c, bundle, new C1438rQ(cp.e, cp));
                    parcel2.writeNoException();
                    return true;
                } catch (RemoteException e) {
                    throw new ZQ(e);
                }
            case 2:
                parcel.enforceInterface("org.chromium.weblayer_private.interfaces.IRemoteFragment");
                QR qrC2 = PR.c(parcel.readStrongBinder());
                AbstractC1054kS.a();
                Context context = (Context) BinderC0998jS.d(qrC2, Context.class);
                CP cp2 = (CP) ((DQ) this);
                AbstractC1054kS.a();
                try {
                    ((TR) cp2.a).v(new BinderC0998jS(context));
                    ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(AbstractC1299ox.a(context), AbstractC0622cn.A7);
                    cp2.e = contextThemeWrapper;
                    BrowserImpl browserImpl = cp2.d;
                    if (browserImpl != null) {
                        browserImpl.d(new C1438rQ(contextThemeWrapper, cp2));
                        browserImpl.B();
                    }
                    parcel2.writeNoException();
                    return true;
                } catch (RemoteException e2) {
                    throw new ZQ(e2);
                }
            case 3:
                parcel.enforceInterface("org.chromium.weblayer_private.interfaces.IRemoteFragment");
                QR qrC3 = PR.c(parcel.readStrongBinder());
                DQ dq = (DQ) this;
                AbstractC1054kS.a();
                try {
                    ((TR) dq.a).u(new BinderC0998jS((Bundle) BinderC0998jS.d(qrC3, Bundle.class)));
                    parcel2.writeNoException();
                    return true;
                } catch (RemoteException e3) {
                    throw new ZQ(e3);
                }
            case C0571bs.IS_GZIP_COMPRESSED_FIELD_NUMBER /* 4 */:
                parcel.enforceInterface("org.chromium.weblayer_private.interfaces.IRemoteFragment");
                AbstractC1054kS.a();
                AbstractC1054kS.a();
                BinderC0998jS binderC0998jS = new BinderC0998jS(((CP) ((DQ) this)).d.x().a);
                parcel2.writeNoException();
                parcel2.writeStrongBinder(binderC0998jS);
                return true;
            case C0571bs.SEED_DATA_FIELD_NUMBER /* 5 */:
                parcel.enforceInterface("org.chromium.weblayer_private.interfaces.IRemoteFragment");
                AbstractC1054kS.a();
                CP cp3 = (CP) ((DQ) this);
                try {
                    ((TR) cp3.a).D();
                    cp3.d.y();
                    parcel2.writeNoException();
                    return true;
                } catch (RemoteException e4) {
                    throw new ZQ(e4);
                }
            case C0571bs.DATE_FIELD_NUMBER /* 6 */:
                parcel.enforceInterface("org.chromium.weblayer_private.interfaces.IRemoteFragment");
                AbstractC1054kS.a();
                CP cp4 = (CP) ((DQ) this);
                try {
                    ((TR) cp4.a).B();
                    cp4.d.k = true;
                    parcel2.writeNoException();
                    return true;
                } catch (RemoteException e5) {
                    throw new ZQ(e5);
                }
            case 7:
                parcel.enforceInterface("org.chromium.weblayer_private.interfaces.IRemoteFragment");
                AbstractC1054kS.a();
                CP cp5 = (CP) ((DQ) this);
                try {
                    ((TR) cp5.a).A();
                    cp5.d.k = false;
                    parcel2.writeNoException();
                    return true;
                } catch (RemoteException e6) {
                    throw new ZQ(e6);
                }
            case 8:
                parcel.enforceInterface("org.chromium.weblayer_private.interfaces.IRemoteFragment");
                AbstractC1054kS.a();
                CP cp6 = (CP) ((DQ) this);
                try {
                    ((TR) cp6.a).E();
                    BrowserImpl browserImpl2 = cp6.d;
                    browserImpl2.j = false;
                    browserImpl2.A();
                    parcel2.writeNoException();
                    return true;
                } catch (RemoteException e7) {
                    throw new ZQ(e7);
                }
            case 9:
                parcel.enforceInterface("org.chromium.weblayer_private.interfaces.IRemoteFragment");
                DQ dq2 = (DQ) this;
                AbstractC1054kS.a();
                try {
                    ((TR) dq2.a).y();
                    parcel2.writeNoException();
                    return true;
                } catch (RemoteException e8) {
                    throw new ZQ(e8);
                }
            case 10:
                parcel.enforceInterface("org.chromium.weblayer_private.interfaces.IRemoteFragment");
                AbstractC1054kS.a();
                CP cp7 = (CP) ((DQ) this);
                AbstractC1054kS.a();
                try {
                    ((TR) cp7.a).z();
                    BrowserImpl browserImpl3 = cp7.d;
                    if (browserImpl3 != null) {
                        browserImpl3.f();
                        browserImpl3.A();
                    }
                    cp7.e = null;
                    parcel2.writeNoException();
                    return true;
                } catch (RemoteException e9) {
                    throw new ZQ(e9);
                }
            case 11:
                parcel.enforceInterface("org.chromium.weblayer_private.interfaces.IRemoteFragment");
                AbstractC1054kS.a();
                CP cp8 = (CP) ((DQ) this);
                AbstractC1054kS.a();
                try {
                    ((TR) cp8.a).x();
                    BrowserImpl browserImpl4 = cp8.d;
                    browserImpl4.h = true;
                    N.MM5Y8Vdi(browserImpl4.b, browserImpl4);
                    browserImpl4.z(null);
                    Iterator it = browserImpl4.w().iterator();
                    while (it.hasNext()) {
                        browserImpl4.i((TabImpl) it.next());
                    }
                    browserImpl4.f();
                    QQ qq = browserImpl4.i;
                    N.MZ0OGKWB(qq.b);
                    qq.b = 0L;
                    qq.a = null;
                    N.MaMrnX9h(browserImpl4.b);
                    cp8.d = null;
                    parcel2.writeNoException();
                    return true;
                } catch (RemoteException e10) {
                    throw new ZQ(e10);
                }
            case 12:
                parcel.enforceInterface("org.chromium.weblayer_private.interfaces.IRemoteFragment");
                QR qrC4 = PR.c(parcel.readStrongBinder());
                AbstractC1054kS.a();
                Bundle bundle2 = (Bundle) BinderC0998jS.d(qrC4, Bundle.class);
                CP cp9 = (CP) ((DQ) this);
                AbstractC1054kS.a();
                BrowserImpl browserImpl5 = cp9.d;
                boolean z = !N.Mtk__Xyp(browserImpl5.b, browserImpl5).isEmpty();
                if (browserImpl5.c.a.isEmpty() && z) {
                    N.MaKl4w3e(browserImpl5.b, browserImpl5);
                    bundle2.putByteArray("SAVED_STATE_SESSION_SERVICE_CRYPTO_KEY", N.MxJsteK0(browserImpl5.b, browserImpl5));
                } else if (!z) {
                    bundle2.putByteArray("SAVED_STATE_MINIMAL_PERSISTENCE_STATE_KEY", N.MtZQkZrN(browserImpl5.b, browserImpl5));
                }
                try {
                    ((TR) cp9.a).C(new BinderC0998jS(bundle2));
                    parcel2.writeNoException();
                    return true;
                } catch (RemoteException e11) {
                    throw new ZQ(e11);
                }
            case 13:
                parcel.enforceInterface("org.chromium.weblayer_private.interfaces.IRemoteFragment");
                int i3 = parcel.readInt();
                int i4 = parcel.readInt();
                QR qrC5 = PR.c(parcel.readStrongBinder());
                AbstractC1054kS.a();
                Intent intent = (Intent) BinderC0998jS.d(qrC5, Intent.class);
                AbstractC1054kS.a();
                C1438rQ c1438rQ = ((CP) ((DQ) this)).d.e;
                if (c1438rQ != null) {
                    InterfaceC0492aO interfaceC0492aO = (InterfaceC0492aO) c1438rQ.I.get(i3);
                    c1438rQ.I.delete(i3);
                    String str = (String) c1438rQ.v.remove(Integer.valueOf(i3));
                    if (interfaceC0492aO != null) {
                        interfaceC0492aO.a(c1438rQ, i4, intent);
                    } else if (str != null) {
                        C1602uP.a(Is.a, str, 0).a.show();
                    }
                }
                parcel2.writeNoException();
                return true;
            case 14:
                parcel.enforceInterface("org.chromium.weblayer_private.interfaces.IRemoteFragment");
                int i5 = parcel.readInt();
                String[] strArrCreateStringArray = parcel.createStringArray();
                int[] iArrCreateIntArray = parcel.createIntArray();
                AbstractC1054kS.a();
                AbstractC1054kS.a();
                C1438rQ c1438rQ2 = ((CP) ((DQ) this)).d.e;
                if (c1438rQ2 != null && (jn = c1438rQ2.C) != null) {
                    jn.B(i5, strArrCreateStringArray, iArrCreateIntArray);
                }
                parcel2.writeNoException();
                return true;
            default:
                return super.onTransact(i, parcel, parcel2, i2);
        }
    }
}
