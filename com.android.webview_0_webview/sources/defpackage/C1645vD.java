package defpackage;

import android.os.IBinder;
import android.os.Parcel;
import android.view.Surface;
import org.chromium.base.UnguessableToken;
import org.chromium.content.common.SurfaceWrapper;

/* JADX INFO: renamed from: vD, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1645vD implements InterfaceC1755xD {
    public IBinder a;

    public C1645vD(IBinder iBinder) {
        this.a = iBinder;
    }

    @Override // android.os.IInterface
    public IBinder asBinder() {
        return this.a;
    }

    @Override // defpackage.InterfaceC1755xD
    public void n(UnguessableToken unguessableToken, Surface surface) {
        Parcel parcelObtain = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("org.chromium.content.common.IGpuProcessCallback");
            if (unguessableToken != null) {
                parcelObtain.writeInt(1);
                unguessableToken.writeToParcel(parcelObtain, 0);
            } else {
                parcelObtain.writeInt(0);
            }
            if (surface != null) {
                parcelObtain.writeInt(1);
                surface.writeToParcel(parcelObtain, 0);
            } else {
                parcelObtain.writeInt(0);
            }
            this.a.transact(1, parcelObtain, null, 1);
        } finally {
            parcelObtain.recycle();
        }
    }

    @Override // defpackage.InterfaceC1755xD
    public SurfaceWrapper t(int i) {
        Parcel parcelObtain = Parcel.obtain();
        Parcel parcelObtain2 = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("org.chromium.content.common.IGpuProcessCallback");
            parcelObtain.writeInt(i);
            this.a.transact(2, parcelObtain, parcelObtain2, 0);
            parcelObtain2.readException();
            return parcelObtain2.readInt() != 0 ? (SurfaceWrapper) SurfaceWrapper.CREATOR.createFromParcel(parcelObtain2) : null;
        } finally {
            parcelObtain2.recycle();
            parcelObtain.recycle();
        }
    }
}
