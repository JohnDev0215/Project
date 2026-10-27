package f;

import android.app.Notification;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: compiled from: INotificationSideChannel.java */
/* JADX INFO: loaded from: classes.dex */
public interface a extends IInterface {

    /* JADX INFO: renamed from: f.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: INotificationSideChannel.java */
    public static abstract class AbstractBinderC0010a extends Binder implements a {
        public AbstractBinderC0010a() {
            attachInterface(this, "android.support.v4.app.INotificationSideChannel");
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i3, Parcel parcel, Parcel parcel2, int i4) throws RemoteException {
            if (i3 == 1) {
                parcel.enforceInterface("android.support.v4.app.INotificationSideChannel");
                y(parcel.readString(), parcel.readInt(), parcel.readString(), parcel.readInt() != 0 ? (Notification) Notification.CREATOR.createFromParcel(parcel) : null);
                return true;
            }
            if (i3 == 2) {
                parcel.enforceInterface("android.support.v4.app.INotificationSideChannel");
                p(parcel.readString(), parcel.readInt(), parcel.readString());
                return true;
            }
            if (i3 == 3) {
                parcel.enforceInterface("android.support.v4.app.INotificationSideChannel");
                j(parcel.readString());
                return true;
            }
            if (i3 != 1598968902) {
                return super.onTransact(i3, parcel, parcel2, i4);
            }
            parcel2.writeString("android.support.v4.app.INotificationSideChannel");
            return true;
        }
    }

    void j(String str) throws RemoteException;

    void p(String str, int i3, String str2) throws RemoteException;

    void y(String str, int i3, String str2, Notification notification) throws RemoteException;
}
