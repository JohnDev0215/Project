package a.d.a.b;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: compiled from: IResultReceiver.java */
/* JADX INFO: loaded from: classes.dex */
public interface a extends IInterface {

    /* JADX INFO: renamed from: a.d.a.b.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: IResultReceiver.java */
    public static abstract class AbstractBinderC0007a extends Binder implements a {

        /* JADX INFO: renamed from: a.d.a.b.a$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: IResultReceiver.java */
        public static class C0008a implements a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static a f60a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public IBinder f61b;

            public C0008a(IBinder iBinder) {
                this.f61b = iBinder;
            }

            @Override // a.d.a.b.a
            public void a0(int i, Bundle bundle) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("android.support.v4.os.IResultReceiver");
                    parcelObtain.writeInt(i);
                    if (bundle != null) {
                        parcelObtain.writeInt(1);
                        bundle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.f61b.transact(1, parcelObtain, null, 1) || AbstractBinderC0007a.e0() == null) {
                        return;
                    }
                    AbstractBinderC0007a.e0().a0(i, bundle);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f61b;
            }
        }

        public AbstractBinderC0007a() {
            attachInterface(this, "android.support.v4.os.IResultReceiver");
        }

        public static a d0(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("android.support.v4.os.IResultReceiver");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof a)) ? new C0008a(iBinder) : (a) iInterfaceQueryLocalInterface;
        }

        public static a e0() {
            return C0008a.f60a;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface("android.support.v4.os.IResultReceiver");
                a0(parcel.readInt(), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                return true;
            }
            if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel2.writeString("android.support.v4.os.IResultReceiver");
            return true;
        }
    }

    void a0(int i, Bundle bundle) throws RemoteException;
}
