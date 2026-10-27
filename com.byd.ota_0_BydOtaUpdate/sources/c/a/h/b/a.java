package c.a.h.b;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: compiled from: IOtaTestCallback.java */
/* JADX INFO: loaded from: classes.dex */
public interface a extends IInterface {

    /* JADX INFO: renamed from: c.a.h.b.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: IOtaTestCallback.java */
    public static abstract class AbstractBinderC0057a extends Binder implements a {

        /* JADX INFO: renamed from: c.a.h.b.a$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: IOtaTestCallback.java */
        public static class C0058a implements a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static a f2778a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public IBinder f2779b;

            public C0058a(IBinder iBinder) {
                this.f2779b = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f2779b;
            }

            @Override // c.a.h.b.a
            public void b(Bundle bundle) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.byd.sdkupdate.inner.IOtaTestCallback");
                    if (!this.f2779b.transact(1, parcelObtain, parcelObtain2, 0) && AbstractBinderC0057a.C() != null) {
                        AbstractBinderC0057a.C().b(bundle);
                        return;
                    }
                    parcelObtain2.readException();
                    if (parcelObtain2.readInt() != 0) {
                        bundle.readFromParcel(parcelObtain2);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public AbstractBinderC0057a() {
            attachInterface(this, "com.byd.sdkupdate.inner.IOtaTestCallback");
        }

        public static a B(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.byd.sdkupdate.inner.IOtaTestCallback");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof a)) ? new C0058a(iBinder) : (a) iInterfaceQueryLocalInterface;
        }

        public static a C() {
            return C0058a.f2778a;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i3) throws RemoteException {
            if (i2 == 1) {
                parcel.enforceInterface("com.byd.sdkupdate.inner.IOtaTestCallback");
                Bundle bundle = new Bundle();
                b(bundle);
                parcel2.writeNoException();
                parcel2.writeInt(1);
                bundle.writeToParcel(parcel2, 1);
                return true;
            }
            if (i2 != 2) {
                if (i2 != 1598968902) {
                    return super.onTransact(i2, parcel, parcel2, i3);
                }
                parcel2.writeString("com.byd.sdkupdate.inner.IOtaTestCallback");
                return true;
            }
            parcel.enforceInterface("com.byd.sdkupdate.inner.IOtaTestCallback");
            a(parcel.readInt(), parcel.readLong(), parcel.readInt() != 0, parcel.readFloat(), parcel.readDouble(), parcel.readString());
            parcel2.writeNoException();
            return true;
        }
    }

    void a(int i2, long j, boolean z, float f2, double d2, String str) throws RemoteException;

    void b(Bundle bundle) throws RemoteException;
}
