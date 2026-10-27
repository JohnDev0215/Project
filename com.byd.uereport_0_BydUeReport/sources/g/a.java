package g;

import android.app.Notification;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: compiled from: INotificationSideChannel.java */
/* JADX INFO: loaded from: classes.dex */
public interface a extends IInterface {

    /* JADX INFO: renamed from: g.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: INotificationSideChannel.java */
    public static abstract class AbstractBinderC0044a extends Binder implements a {
        public AbstractBinderC0044a() {
            attachInterface(this, "android.support.v4.app.INotificationSideChannel");
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i8, Parcel parcel, Parcel parcel2, int i9) throws RemoteException {
            if (i8 == 1) {
                parcel.enforceInterface("android.support.v4.app.INotificationSideChannel");
                j0(parcel.readString(), parcel.readInt(), parcel.readString(), parcel.readInt() != 0 ? (Notification) Notification.CREATOR.createFromParcel(parcel) : null);
                return true;
            }
            if (i8 == 2) {
                parcel.enforceInterface("android.support.v4.app.INotificationSideChannel");
                r(parcel.readString(), parcel.readInt(), parcel.readString());
                return true;
            }
            if (i8 == 3) {
                parcel.enforceInterface("android.support.v4.app.INotificationSideChannel");
                I(parcel.readString());
                return true;
            }
            if (i8 != 1598968902) {
                return super.onTransact(i8, parcel, parcel2, i9);
            }
            parcel2.writeString("android.support.v4.app.INotificationSideChannel");
            return true;
        }
    }

    void I(String str) throws RemoteException;

    void j0(String str, int i8, String str2, Notification notification) throws RemoteException;

    void r(String str, int i8, String str2) throws RemoteException;
}
