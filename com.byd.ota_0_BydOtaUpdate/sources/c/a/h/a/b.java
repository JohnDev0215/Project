package c.a.h.a;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import c.a.h.a.a;

/* JADX INFO: compiled from: IOtaSdkCommand.java */
/* JADX INFO: loaded from: classes.dex */
public interface b extends IInterface {

    /* JADX INFO: compiled from: IOtaSdkCommand.java */
    public static abstract class a extends Binder implements b {
        public a() {
            attachInterface(this, "com.byd.sdkupdate.aidl.IOtaSdkCommand");
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i3) throws RemoteException {
            if (i2 == 1598968902) {
                parcel2.writeString("com.byd.sdkupdate.aidl.IOtaSdkCommand");
                return true;
            }
            switch (i2) {
                case 1:
                    parcel.enforceInterface("com.byd.sdkupdate.aidl.IOtaSdkCommand");
                    String strM = m();
                    parcel2.writeNoException();
                    parcel2.writeString(strM);
                    return true;
                case 2:
                    parcel.enforceInterface("com.byd.sdkupdate.aidl.IOtaSdkCommand");
                    String strR = r();
                    parcel2.writeNoException();
                    parcel2.writeString(strR);
                    return true;
                case 3:
                    parcel.enforceInterface("com.byd.sdkupdate.aidl.IOtaSdkCommand");
                    boolean zI = i();
                    parcel2.writeNoException();
                    parcel2.writeInt(zI ? 1 : 0);
                    return true;
                case 4:
                    parcel.enforceInterface("com.byd.sdkupdate.aidl.IOtaSdkCommand");
                    long jL = l();
                    parcel2.writeNoException();
                    parcel2.writeLong(jL);
                    return true;
                case 5:
                    parcel.enforceInterface("com.byd.sdkupdate.aidl.IOtaSdkCommand");
                    p();
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface("com.byd.sdkupdate.aidl.IOtaSdkCommand");
                    A();
                    parcel2.writeNoException();
                    return true;
                case 7:
                    parcel.enforceInterface("com.byd.sdkupdate.aidl.IOtaSdkCommand");
                    f();
                    parcel2.writeNoException();
                    return true;
                case 8:
                    parcel.enforceInterface("com.byd.sdkupdate.aidl.IOtaSdkCommand");
                    j(a.AbstractBinderC0055a.B(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 9:
                    parcel.enforceInterface("com.byd.sdkupdate.aidl.IOtaSdkCommand");
                    y(a.AbstractBinderC0055a.B(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 10:
                    parcel.enforceInterface("com.byd.sdkupdate.aidl.IOtaSdkCommand");
                    a(parcel.readInt(), parcel.readLong(), parcel.readInt() != 0, parcel.readFloat(), parcel.readDouble(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i2, parcel, parcel2, i3);
            }
        }
    }

    void A() throws RemoteException;

    void a(int i2, long j, boolean z, float f2, double d2, String str) throws RemoteException;

    void f() throws RemoteException;

    boolean i() throws RemoteException;

    void j(c.a.h.a.a aVar) throws RemoteException;

    long l() throws RemoteException;

    String m() throws RemoteException;

    void p() throws RemoteException;

    String r() throws RemoteException;

    void y(c.a.h.a.a aVar) throws RemoteException;
}
