package o1;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: compiled from: IMediaControlCallback.java */
/* JADX INFO: loaded from: classes.dex */
public interface a extends IInterface {

    /* JADX INFO: renamed from: o1.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: IMediaControlCallback.java */
    public static abstract class AbstractBinderC0044a extends Binder implements a {
        public AbstractBinderC0044a() {
            attachInterface(this, "com.byd.mediacenter.api.media.IMediaControlCallback");
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i3, Parcel parcel, Parcel parcel2, int i4) throws RemoteException {
            if (i3 != 1) {
                if (i3 != 1598968902) {
                    return super.onTransact(i3, parcel, parcel2, i4);
                }
                parcel2.writeString("com.byd.mediacenter.api.media.IMediaControlCallback");
                return true;
            }
            parcel.enforceInterface("com.byd.mediacenter.api.media.IMediaControlCallback");
            int i5 = parcel.readInt();
            int i6 = parcel.readInt();
            Bundle bundle = parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null;
            k(i5, i6, bundle);
            parcel2.writeNoException();
            if (bundle != null) {
                parcel2.writeInt(1);
                bundle.writeToParcel(parcel2, 1);
            } else {
                parcel2.writeInt(0);
            }
            return true;
        }
    }

    void k(int i3, int i4, Bundle bundle) throws RemoteException;
}
