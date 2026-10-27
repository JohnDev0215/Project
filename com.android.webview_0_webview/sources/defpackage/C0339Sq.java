package defpackage;

import android.os.IBinder;
import android.os.Parcel;
import java.util.Map;

/* JADX INFO: renamed from: Sq, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0339Sq implements InterfaceC0375Uq {
    public IBinder a;

    public C0339Sq(IBinder iBinder) {
        this.a = iBinder;
    }

    @Override // android.os.IInterface
    public IBinder asBinder() {
        return this.a;
    }

    @Override // defpackage.InterfaceC0375Uq
    public void q(Map map) {
        Parcel parcelObtain = Parcel.obtain();
        Parcel parcelObtain2 = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("org.chromium.android_webview.common.services.IDeveloperUiService");
            parcelObtain.writeMap(map);
            this.a.transact(1, parcelObtain, parcelObtain2, 0);
            parcelObtain2.readException();
        } finally {
            parcelObtain2.recycle();
            parcelObtain.recycle();
        }
    }
}
