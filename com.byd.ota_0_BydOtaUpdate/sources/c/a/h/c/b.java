package c.a.h.c;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import c.a.h.c.a;

/* JADX INFO: compiled from: IOtaToolCommand.java */
/* JADX INFO: loaded from: classes.dex */
public interface b extends IInterface {

    /* JADX INFO: compiled from: IOtaToolCommand.java */
    public static abstract class a extends Binder implements b {

        /* JADX INFO: renamed from: c.a.h.c.b$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: IOtaToolCommand.java */
        public static class C0062a implements b {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static b f2783a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public IBinder f2784b;

            public C0062a(IBinder iBinder) {
                this.f2784b = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f2784b;
            }

            @Override // c.a.h.c.b
            public void q(c.a.h.c.a aVar) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.byd.sdkupdate.tool.IOtaToolCommand");
                    parcelObtain.writeStrongBinder(aVar != null ? aVar.asBinder() : null);
                    if (this.f2784b.transact(2, parcelObtain, parcelObtain2, 0) || a.C() == null) {
                        parcelObtain2.readException();
                    } else {
                        a.C().q(aVar);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // c.a.h.c.b
            public void s(c.a.h.c.a aVar) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.byd.sdkupdate.tool.IOtaToolCommand");
                    parcelObtain.writeStrongBinder(aVar != null ? aVar.asBinder() : null);
                    if (this.f2784b.transact(3, parcelObtain, parcelObtain2, 0) || a.C() == null) {
                        parcelObtain2.readException();
                    } else {
                        a.C().s(aVar);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public a() {
            attachInterface(this, "com.byd.sdkupdate.tool.IOtaToolCommand");
        }

        public static b B(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.byd.sdkupdate.tool.IOtaToolCommand");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof b)) ? new C0062a(iBinder) : (b) iInterfaceQueryLocalInterface;
        }

        public static b C() {
            return C0062a.f2783a;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i3) throws RemoteException {
            if (i2 == 1) {
                parcel.enforceInterface("com.byd.sdkupdate.tool.IOtaToolCommand");
                c(parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                parcel2.writeNoException();
                return true;
            }
            if (i2 == 2) {
                parcel.enforceInterface("com.byd.sdkupdate.tool.IOtaToolCommand");
                q(a.AbstractBinderC0060a.B(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            if (i2 == 3) {
                parcel.enforceInterface("com.byd.sdkupdate.tool.IOtaToolCommand");
                s(a.AbstractBinderC0060a.B(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            if (i2 != 4) {
                if (i2 != 1598968902) {
                    return super.onTransact(i2, parcel, parcel2, i3);
                }
                parcel2.writeString("com.byd.sdkupdate.tool.IOtaToolCommand");
                return true;
            }
            parcel.enforceInterface("com.byd.sdkupdate.tool.IOtaToolCommand");
            a(parcel.readInt(), parcel.readLong(), parcel.readInt() != 0, parcel.readFloat(), parcel.readDouble(), parcel.readString());
            parcel2.writeNoException();
            return true;
        }
    }

    void a(int i2, long j, boolean z, float f2, double d2, String str) throws RemoteException;

    void c(Bundle bundle) throws RemoteException;

    void q(c.a.h.c.a aVar) throws RemoteException;

    void s(c.a.h.c.a aVar) throws RemoteException;
}
