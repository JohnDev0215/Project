package defpackage;

import android.os.IBinder;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;

/* JADX INFO: renamed from: Vq, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0393Vq implements InterfaceC0429Xq {
    public IBinder a;

    public C0393Vq(IBinder iBinder) {
        this.a = iBinder;
    }

    @Override // android.os.IInterface
    public IBinder asBinder() {
        return this.a;
    }

    @Override // defpackage.InterfaceC0429Xq
    public void s(ParcelFileDescriptor parcelFileDescriptor, long j, InterfaceC0514ar interfaceC0514ar) {
        Parcel parcelObtain = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("org.chromium.android_webview.common.services.IVariationsSeedServer");
            if (parcelFileDescriptor != null) {
                parcelObtain.writeInt(1);
                parcelFileDescriptor.writeToParcel(parcelObtain, 0);
            } else {
                parcelObtain.writeInt(0);
            }
            parcelObtain.writeLong(j);
            parcelObtain.writeStrongBinder(interfaceC0514ar != null ? interfaceC0514ar.asBinder() : null);
            this.a.transact(1, parcelObtain, null, 1);
        } finally {
            parcelObtain.recycle();
        }
    }
}
