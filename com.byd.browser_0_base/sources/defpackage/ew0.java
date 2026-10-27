package defpackage;

import android.os.IBinder;
import android.os.Parcel;

/* JADX INFO: compiled from: IOnFinishInstallCallback.java */
/* JADX INFO: loaded from: classes3.dex */
public final class ew0 implements fw0 {
    public final IBinder e;

    public ew0(IBinder iBinder) {
        this.e = iBinder;
    }

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        return this.e;
    }

    @Override // defpackage.fw0
    public final void c(int i) {
        Parcel parcelObtain = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("org.chromium.components.webapk_install.IOnFinishInstallCallback");
            parcelObtain.writeInt(i);
            this.e.transact(1, parcelObtain, null, 1);
        } finally {
            parcelObtain.recycle();
        }
    }
}
