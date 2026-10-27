package defpackage;

import J.N;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import java.util.ArrayList;
import org.chromium.weblayer_private.CookieManagerImpl;
import org.chromium.weblayer_private.DownloadCallbackProxy;
import org.chromium.weblayer_private.ProfileImpl;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class RR extends Binder implements IInterface {
    public RR() {
        attachInterface(this, "org.chromium.weblayer_private.interfaces.IProfile");
    }

    @Override // android.os.IInterface
    public IBinder asBinder() {
        return this;
    }

    @Override // android.os.Binder
    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        if (i == 1598968902) {
            parcel2.writeString("org.chromium.weblayer_private.interfaces.IProfile");
            return true;
        }
        switch (i) {
            case 1:
                parcel.enforceInterface("org.chromium.weblayer_private.interfaces.IProfile");
                ProfileImpl profileImpl = (ProfileImpl) this;
                AbstractC1054kS.a();
                if (!profileImpl.e) {
                    DownloadCallbackProxy downloadCallbackProxy = profileImpl.g;
                    if (downloadCallbackProxy != null) {
                        N.Mq3U77nO(downloadCallbackProxy.a);
                        downloadCallbackProxy.a = 0L;
                        profileImpl.g = null;
                    }
                    CookieManagerImpl cookieManagerImpl = profileImpl.c;
                    if (cookieManagerImpl != null) {
                        cookieManagerImpl.a = 0L;
                        profileImpl.c = null;
                    }
                    N.M_1y2G80(profileImpl.b);
                    profileImpl.b = 0L;
                    Runnable runnable = profileImpl.d;
                    if (runnable != null) {
                        runnable.run();
                        profileImpl.d = null;
                    }
                }
                parcel2.writeNoException();
                return true;
            case 2:
                parcel.enforceInterface("org.chromium.weblayer_private.interfaces.IProfile");
                int[] iArrCreateIntArray = parcel.createIntArray();
                long j = parcel.readLong();
                long j2 = parcel.readLong();
                QR qrC = PR.c(parcel.readStrongBinder());
                ProfileImpl profileImpl2 = (ProfileImpl) this;
                AbstractC1054kS.a();
                profileImpl2.c();
                Runnable runnable2 = (Runnable) BinderC0998jS.d(qrC, Runnable.class);
                long j3 = profileImpl2.b;
                ArrayList arrayList = new ArrayList();
                for (int i3 : iArrCreateIntArray) {
                    if (i3 == 0) {
                        arrayList.add(0);
                    } else if (i3 == 1) {
                        arrayList.add(1);
                    }
                }
                int[] iArr = new int[arrayList.size()];
                for (int i4 = 0; i4 < arrayList.size(); i4++) {
                    iArr[i4] = ((Integer) arrayList.get(i4)).intValue();
                }
                N.MTR7prkl(j3, iArr, j, j2, runnable2);
                parcel2.writeNoException();
                return true;
            case 3:
                parcel.enforceInterface("org.chromium.weblayer_private.interfaces.IProfile");
                ProfileImpl profileImpl3 = (ProfileImpl) this;
                AbstractC1054kS.a();
                profileImpl3.c();
                String str = profileImpl3.a;
                parcel2.writeNoException();
                parcel2.writeString(str);
                return true;
            case C0571bs.IS_GZIP_COMPRESSED_FIELD_NUMBER /* 4 */:
                parcel.enforceInterface("org.chromium.weblayer_private.interfaces.IProfile");
                String string = parcel.readString();
                ProfileImpl profileImpl4 = (ProfileImpl) this;
                AbstractC1054kS.a();
                profileImpl4.c();
                N.Mq7GFTAG(profileImpl4.b, string);
                parcel2.writeNoException();
                return true;
            case C0571bs.SEED_DATA_FIELD_NUMBER /* 5 */:
                parcel.enforceInterface("org.chromium.weblayer_private.interfaces.IProfile");
                QR qrC2 = PR.c(parcel.readStrongBinder());
                final ProfileImpl profileImpl5 = (ProfileImpl) this;
                AbstractC1054kS.a();
                profileImpl5.c();
                final Runnable runnable3 = (Runnable) BinderC0998jS.d(qrC2, Runnable.class);
                boolean zM1s__uyA = N.M1s__uyA(profileImpl5.b, new Runnable(profileImpl5, runnable3) { // from class: AQ
                    public final ProfileImpl s;
                    public final Runnable t;

                    {
                        this.s = profileImpl5;
                        this.t = runnable3;
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        ProfileImpl profileImpl6 = this.s;
                        Runnable runnable4 = this.t;
                        N.M_1y2G80(profileImpl6.b);
                        profileImpl6.b = 0L;
                        if (runnable4 != null) {
                            runnable4.run();
                        }
                    }
                });
                profileImpl5.e = zM1s__uyA;
                if (!zM1s__uyA) {
                    StringBuilder sbF = Z7.f("Profile still in use: ");
                    sbF.append(profileImpl5.a);
                    throw new IllegalStateException(sbF.toString());
                }
                Runnable runnable4 = profileImpl5.d;
                if (runnable4 != null) {
                    runnable4.run();
                    profileImpl5.d = null;
                }
                parcel2.writeNoException();
                return true;
            case C0571bs.DATE_FIELD_NUMBER /* 6 */:
                parcel.enforceInterface("org.chromium.weblayer_private.interfaces.IProfile");
                ((ProfileImpl) this).g.c = AbstractBinderC1714wR.c(parcel.readStrongBinder());
                parcel2.writeNoException();
                return true;
            case 7:
                parcel.enforceInterface("org.chromium.weblayer_private.interfaces.IProfile");
                ProfileImpl profileImpl6 = (ProfileImpl) this;
                AbstractC1054kS.a();
                profileImpl6.c();
                CookieManagerImpl cookieManagerImpl2 = profileImpl6.c;
                parcel2.writeNoException();
                parcel2.writeStrongBinder(cookieManagerImpl2 != null ? cookieManagerImpl2 : null);
                return true;
            default:
                return super.onTransact(i, parcel, parcel2, i2);
        }
    }
}
