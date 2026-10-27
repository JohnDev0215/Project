package c.a.h.b;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import c.a.h.b.a;

/* JADX INFO: compiled from: IOtaTestCommand.java */
/* JADX INFO: loaded from: classes.dex */
public interface b extends IInterface {

    /* JADX INFO: compiled from: IOtaTestCommand.java */
    public static abstract class a extends Binder implements b {

        /* JADX INFO: renamed from: c.a.h.b.b$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: IOtaTestCommand.java */
        public static class C0059a implements b {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static b f2780a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public IBinder f2781b;

            public C0059a(IBinder iBinder) {
                this.f2781b = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f2781b;
            }

            @Override // c.a.h.b.b
            public void c(Bundle bundle) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.byd.sdkupdate.inner.IOtaTestCommand");
                    if (bundle != null) {
                        parcelObtain.writeInt(1);
                        bundle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.f2781b.transact(4, parcelObtain, parcelObtain2, 0) || a.C() == null) {
                        parcelObtain2.readException();
                    } else {
                        a.C().c(bundle);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // c.a.h.b.b
            public void n(c.a.h.b.a aVar) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.byd.sdkupdate.inner.IOtaTestCommand");
                    parcelObtain.writeStrongBinder(aVar != null ? aVar.asBinder() : null);
                    if (this.f2781b.transact(5, parcelObtain, parcelObtain2, 0) || a.C() == null) {
                        parcelObtain2.readException();
                    } else {
                        a.C().n(aVar);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // c.a.h.b.b
            public String o(int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.byd.sdkupdate.inner.IOtaTestCommand");
                    parcelObtain.writeInt(i2);
                    if (!this.f2781b.transact(1, parcelObtain, parcelObtain2, 0) && a.C() != null) {
                        return a.C().o(i2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // c.a.h.b.b
            public String t(int i2, int i3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.byd.sdkupdate.inner.IOtaTestCommand");
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    if (!this.f2781b.transact(3, parcelObtain, parcelObtain2, 0) && a.C() != null) {
                        return a.C().t(i2, i3);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // c.a.h.b.b
            public void u(c.a.h.b.a aVar) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.byd.sdkupdate.inner.IOtaTestCommand");
                    parcelObtain.writeStrongBinder(aVar != null ? aVar.asBinder() : null);
                    if (this.f2781b.transact(6, parcelObtain, parcelObtain2, 0) || a.C() == null) {
                        parcelObtain2.readException();
                    } else {
                        a.C().u(aVar);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // c.a.h.b.b
            public String x(int i2, int i3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.byd.sdkupdate.inner.IOtaTestCommand");
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    if (!this.f2781b.transact(2, parcelObtain, parcelObtain2, 0) && a.C() != null) {
                        return a.C().x(i2, i3);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public a() {
            attachInterface(this, "com.byd.sdkupdate.inner.IOtaTestCommand");
        }

        public static b B(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.byd.sdkupdate.inner.IOtaTestCommand");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof b)) ? new C0059a(iBinder) : (b) iInterfaceQueryLocalInterface;
        }

        public static b C() {
            return C0059a.f2780a;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i3) throws RemoteException {
            if (i2 == 1598968902) {
                parcel2.writeString("com.byd.sdkupdate.inner.IOtaTestCommand");
                return true;
            }
            switch (i2) {
                case 1:
                    parcel.enforceInterface("com.byd.sdkupdate.inner.IOtaTestCommand");
                    String strO = o(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeString(strO);
                    return true;
                case 2:
                    parcel.enforceInterface("com.byd.sdkupdate.inner.IOtaTestCommand");
                    String strX = x(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeString(strX);
                    return true;
                case 3:
                    parcel.enforceInterface("com.byd.sdkupdate.inner.IOtaTestCommand");
                    String strT = t(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeString(strT);
                    return true;
                case 4:
                    parcel.enforceInterface("com.byd.sdkupdate.inner.IOtaTestCommand");
                    c(parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface("com.byd.sdkupdate.inner.IOtaTestCommand");
                    n(a.AbstractBinderC0057a.B(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface("com.byd.sdkupdate.inner.IOtaTestCommand");
                    u(a.AbstractBinderC0057a.B(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 7:
                    parcel.enforceInterface("com.byd.sdkupdate.inner.IOtaTestCommand");
                    a(parcel.readInt(), parcel.readLong(), parcel.readInt() != 0, parcel.readFloat(), parcel.readDouble(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i2, parcel, parcel2, i3);
            }
        }
    }

    void a(int i2, long j, boolean z, float f2, double d2, String str) throws RemoteException;

    void c(Bundle bundle) throws RemoteException;

    void n(c.a.h.b.a aVar) throws RemoteException;

    String o(int i2) throws RemoteException;

    String t(int i2, int i3) throws RemoteException;

    void u(c.a.h.b.a aVar) throws RemoteException;

    String x(int i2, int i3) throws RemoteException;
}
