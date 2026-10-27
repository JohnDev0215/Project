package a.b.a.b;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: compiled from: IResultReceiver.java */
/* JADX INFO: loaded from: classes.dex */
public interface a extends IInterface {

    /* JADX INFO: renamed from: a.b.a.b.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: IResultReceiver.java */
    public static abstract class AbstractBinderC0006a extends Binder implements a {

        /* JADX INFO: renamed from: a.b.a.b.a$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: IResultReceiver.java */
        public static class C0007a implements a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static a f55a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public IBinder f56b;

            public C0007a(IBinder iBinder) {
                this.f56b = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f56b;
            }

            @Override // a.b.a.b.a
            public void y(int i, Bundle bundle) throws RemoteException {
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
                    if (this.f56b.transact(1, parcelObtain, null, 1) || AbstractBinderC0006a.C() == null) {
                        return;
                    }
                    AbstractBinderC0006a.C().y(i, bundle);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public AbstractBinderC0006a() {
            attachInterface(this, "android.support.v4.os.IResultReceiver");
        }

        public static a B(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("android.support.v4.os.IResultReceiver");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof a)) ? new C0007a(iBinder) : (a) iInterfaceQueryLocalInterface;
        }

        public static a C() {
            return C0007a.f55a;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface("android.support.v4.os.IResultReceiver");
                y(parcel.readInt(), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                return true;
            }
            if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel2.writeString("android.support.v4.os.IResultReceiver");
            return true;
        }
    }

    void y(int i, Bundle bundle) throws RemoteException;
}
