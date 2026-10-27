package a.c.a.a;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public interface a extends IInterface {

    /* JADX INFO: renamed from: a.c.a.a.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: caradapter-bug */
    public static abstract class AbstractBinderC0001a extends Binder implements a {

        /* JADX INFO: renamed from: a.c.a.a.a$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: caradapter-bug */
        public static class C0002a implements a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public IBinder f53a;

            public C0002a(IBinder iBinder) {
                this.f53a = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f53a;
            }
        }

        public AbstractBinderC0001a() {
            attachInterface(this, "android.support.v4.os.IResultReceiver");
        }

        public static a b(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("android.support.v4.os.IResultReceiver");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof a)) ? new C0002a(iBinder) : (a) iInterfaceQueryLocalInterface;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface("android.support.v4.os.IResultReceiver");
                a(parcel.readInt(), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                return true;
            }
            if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel2.writeString("android.support.v4.os.IResultReceiver");
            return true;
        }
    }

    void a(int i, Bundle bundle) throws RemoteException;
}
