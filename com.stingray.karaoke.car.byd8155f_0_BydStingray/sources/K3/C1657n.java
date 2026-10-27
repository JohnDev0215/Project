package k3;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;

/* JADX INFO: renamed from: k3.n, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1657n implements InterfaceC1659o {
    public IBinder c;

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        return this.c;
    }

    @Override // k3.InterfaceC1659o
    public final void x(InterfaceC1651k interfaceC1651k, Bundle bundle) {
        Parcel parcelObtain = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("androidx.media3.session.IMediaSessionService");
            parcelObtain.writeStrongInterface(interfaceC1651k);
            parcelObtain.writeInt(1);
            bundle.writeToParcel(parcelObtain, 0);
            this.c.transact(3001, parcelObtain, null, 1);
        } finally {
            parcelObtain.recycle();
        }
    }
}
