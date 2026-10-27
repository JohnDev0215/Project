package c.a.h.c;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: compiled from: IOtaToolCallback.java */
/* JADX INFO: loaded from: classes.dex */
public interface a extends IInterface {

    /* JADX INFO: renamed from: c.a.h.c.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: IOtaToolCallback.java */
    public static abstract class AbstractBinderC0060a extends Binder implements a {

        /* JADX INFO: renamed from: c.a.h.c.a$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: IOtaToolCallback.java */
        public static class C0061a implements a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public IBinder f2782a;

            public C0061a(IBinder iBinder) {
                this.f2782a = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f2782a;
            }
        }

        public AbstractBinderC0060a() {
            attachInterface(this, "com.byd.sdkupdate.tool.IOtaToolCallback");
        }

        public static a B(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.byd.sdkupdate.tool.IOtaToolCallback");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof a)) ? new C0061a(iBinder) : (a) iInterfaceQueryLocalInterface;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i3) throws RemoteException {
            if (i2 == 1) {
                parcel.enforceInterface("com.byd.sdkupdate.tool.IOtaToolCallback");
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
                parcel2.writeString("com.byd.sdkupdate.tool.IOtaToolCallback");
                return true;
            }
            parcel.enforceInterface("com.byd.sdkupdate.tool.IOtaToolCallback");
            a(parcel.readInt(), parcel.readLong(), parcel.readInt() != 0, parcel.readFloat(), parcel.readDouble(), parcel.readString());
            parcel2.writeNoException();
            return true;
        }
    }

    void a(int i2, long j, boolean z, float f2, double d2, String str) throws RemoteException;

    void b(Bundle bundle) throws RemoteException;
}
