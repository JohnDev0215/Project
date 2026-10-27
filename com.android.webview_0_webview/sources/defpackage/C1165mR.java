package defpackage;

import android.os.IBinder;
import android.os.Parcel;
import org.chromium.weblayer_private.interfaces.ICookieChangedCallbackClient;

/* JADX INFO: renamed from: mR, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1165mR implements ICookieChangedCallbackClient {
    public IBinder a;

    public C1165mR(IBinder iBinder) {
        this.a = iBinder;
    }

    @Override // android.os.IInterface
    public IBinder asBinder() {
        return this.a;
    }

    @Override // org.chromium.weblayer_private.interfaces.ICookieChangedCallbackClient
    public void g(String str, int i) {
        Parcel parcelObtain = Parcel.obtain();
        Parcel parcelObtain2 = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("org.chromium.weblayer_private.interfaces.ICookieChangedCallbackClient");
            parcelObtain.writeString(str);
            parcelObtain.writeInt(i);
            this.a.transact(1, parcelObtain, parcelObtain2, 0);
            parcelObtain2.readException();
        } finally {
            parcelObtain2.recycle();
            parcelObtain.recycle();
        }
    }
}
