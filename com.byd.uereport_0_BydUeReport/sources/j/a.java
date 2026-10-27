package j;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: compiled from: IResultReceiver.java */
/* JADX INFO: loaded from: classes.dex */
public interface a extends IInterface {

    /* JADX INFO: renamed from: j.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: IResultReceiver.java */
    public static abstract class AbstractBinderC0063a extends Binder implements a {

        /* JADX INFO: renamed from: j.a$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: IResultReceiver.java */
        public static class C0064a implements a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static a f5535a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public IBinder f5536b;

            public C0064a(IBinder iBinder) {
                this.f5536b = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f5536b;
            }

            @Override // j.a
            public void w0(int i8, Bundle bundle) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("android.support.v4.os.IResultReceiver");
                    parcelObtain.writeInt(i8);
                    if (bundle != null) {
                        parcelObtain.writeInt(1);
                        bundle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.f5536b.transact(1, parcelObtain, null, 1) || AbstractBinderC0063a.B0() == null) {
                        return;
                    }
                    AbstractBinderC0063a.B0().w0(i8, bundle);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public AbstractBinderC0063a() {
            attachInterface(this, "android.support.v4.os.IResultReceiver");
        }

        public static a A0(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("android.support.v4.os.IResultReceiver");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof a)) ? new C0064a(iBinder) : (a) iInterfaceQueryLocalInterface;
        }

        public static a B0() {
            return C0064a.f5535a;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i8, Parcel parcel, Parcel parcel2, int i9) throws RemoteException {
            if (i8 == 1) {
                parcel.enforceInterface("android.support.v4.os.IResultReceiver");
                w0(parcel.readInt(), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                return true;
            }
            if (i8 != 1598968902) {
                return super.onTransact(i8, parcel, parcel2, i9);
            }
            parcel2.writeString("android.support.v4.os.IResultReceiver");
            return true;
        }
    }

    void w0(int i8, Bundle bundle) throws RemoteException;
}
