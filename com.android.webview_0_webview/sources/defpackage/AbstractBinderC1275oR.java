package defpackage;

import J.N;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.webkit.ValueCallback;
import java.lang.ref.WeakReference;
import org.chromium.base.Callback;
import org.chromium.weblayer_private.CookieManagerImpl;
import org.chromium.weblayer_private.interfaces.ICookieChangedCallbackClient;

/* JADX INFO: renamed from: oR, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractBinderC1275oR extends Binder implements IInterface {
    public AbstractBinderC1275oR() {
        attachInterface(this, "org.chromium.weblayer_private.interfaces.ICookieManager");
    }

    @Override // android.os.IInterface
    public IBinder asBinder() {
        return this;
    }

    @Override // android.os.Binder
    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        if (i == 1) {
            parcel.enforceInterface("org.chromium.weblayer_private.interfaces.ICookieManager");
            String string = parcel.readString();
            String string2 = parcel.readString();
            QR qrC = PR.c(parcel.readStrongBinder());
            AbstractC1054kS.a();
            final ValueCallback valueCallback = (ValueCallback) BinderC0998jS.d(qrC, ValueCallback.class);
            boolean zMHaZNm_6 = N.MHaZNm_6(((CookieManagerImpl) this).a, string, string2, new Callback(valueCallback) { // from class: cQ
                public final ValueCallback a;

                {
                    this.a = valueCallback;
                }

                @Override // org.chromium.base.Callback
                public void a(Object obj) {
                    this.a.onReceiveValue((Boolean) obj);
                }
            });
            parcel2.writeNoException();
            parcel2.writeInt(zMHaZNm_6 ? 1 : 0);
            return true;
        }
        if (i == 2) {
            parcel.enforceInterface("org.chromium.weblayer_private.interfaces.ICookieManager");
            String string3 = parcel.readString();
            QR qrC2 = PR.c(parcel.readStrongBinder());
            AbstractC1054kS.a();
            final ValueCallback valueCallback2 = (ValueCallback) BinderC0998jS.d(qrC2, ValueCallback.class);
            N.M55lJVED(((CookieManagerImpl) this).a, string3, new Callback(valueCallback2) { // from class: dQ
                public final ValueCallback a;

                {
                    this.a = valueCallback2;
                }

                @Override // org.chromium.base.Callback
                public void a(Object obj) {
                    this.a.onReceiveValue((String) obj);
                }
            });
            parcel2.writeNoException();
            return true;
        }
        if (i != 3) {
            if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel2.writeString("org.chromium.weblayer_private.interfaces.ICookieManager");
            return true;
        }
        parcel.enforceInterface("org.chromium.weblayer_private.interfaces.ICookieManager");
        String string4 = parcel.readString();
        String string5 = parcel.readString();
        ICookieChangedCallbackClient iCookieChangedCallbackClientC = AbstractBinderC1220nR.c(parcel.readStrongBinder());
        CookieManagerImpl cookieManagerImpl = (CookieManagerImpl) this;
        AbstractC1054kS.a();
        final int iM9VhfsIF = N.M9VhfsIF(cookieManagerImpl.a, string4, string5, iCookieChangedCallbackClientC);
        final WeakReference weakReference = new WeakReference(cookieManagerImpl);
        BinderC0998jS binderC0998jS = new BinderC0998jS(new Runnable(weakReference, iM9VhfsIF) { // from class: eQ
            public final WeakReference s;
            public final int t;

            {
                this.s = weakReference;
                this.t = iM9VhfsIF;
            }

            @Override // java.lang.Runnable
            public void run() {
                CookieManagerImpl.c(this.s, this.t);
            }
        });
        parcel2.writeNoException();
        parcel2.writeStrongBinder(binderC0998jS);
        return true;
    }
}
