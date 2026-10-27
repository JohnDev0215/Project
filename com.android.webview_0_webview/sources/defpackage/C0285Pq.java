package defpackage;

import android.os.IBinder;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import java.util.List;

/* JADX INFO: renamed from: Pq, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0285Pq implements InterfaceC0321Rq {
    public IBinder a;

    public C0285Pq(IBinder iBinder) {
        this.a = iBinder;
    }

    @Override // android.os.IInterface
    public IBinder asBinder() {
        return this.a;
    }

    @Override // defpackage.InterfaceC0321Rq
    public void e(ParcelFileDescriptor[] parcelFileDescriptorArr, List list) {
        Parcel parcelObtain = Parcel.obtain();
        Parcel parcelObtain2 = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("org.chromium.android_webview.common.services.ICrashReceiverService");
            parcelObtain.writeTypedArray(parcelFileDescriptorArr, 0);
            parcelObtain.writeList(list);
            this.a.transact(1, parcelObtain, parcelObtain2, 0);
            parcelObtain2.readException();
        } finally {
            parcelObtain2.recycle();
            parcelObtain.recycle();
        }
    }
}
