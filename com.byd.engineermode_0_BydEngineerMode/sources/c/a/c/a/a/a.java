package c.a.c.a.a;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: compiled from: IMediaControlCallback.java */
/* JADX INFO: loaded from: classes.dex */
public interface a extends IInterface {

    /* JADX INFO: renamed from: c.a.c.a.a.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: IMediaControlCallback.java */
    public static abstract class AbstractBinderC0059a extends Binder implements a {
        public AbstractBinderC0059a() {
            attachInterface(this, "com.byd.mediacenter.api.media.IMediaControlCallback");
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1) {
                if (i != 1598968902) {
                    return super.onTransact(i, parcel, parcel2, i2);
                }
                parcel2.writeString("com.byd.mediacenter.api.media.IMediaControlCallback");
                return true;
            }
            parcel.enforceInterface("com.byd.mediacenter.api.media.IMediaControlCallback");
            int i3 = parcel.readInt();
            int i4 = parcel.readInt();
            Bundle bundle = parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null;
            b(i3, i4, bundle);
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

    void b(int i, int i2, Bundle bundle) throws RemoteException;
}
