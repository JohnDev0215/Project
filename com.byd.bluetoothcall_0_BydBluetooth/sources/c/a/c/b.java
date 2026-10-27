package c.a.c;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: compiled from: ISystemSettings.java */
/* JADX INFO: loaded from: classes.dex */
public interface b extends IInterface {

    /* JADX INFO: compiled from: ISystemSettings.java */
    public static abstract class a extends Binder implements b {

        /* JADX INFO: renamed from: c.a.c.b$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: ISystemSettings.java */
        private static class C0069a implements b {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static b f2621a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            private IBinder f2622b;

            C0069a(IBinder iBinder) {
                this.f2622b = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f2622b;
            }

            @Override // c.a.c.b
            public void l() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.byd.systemsettings.ISystemSettings");
                    if (this.f2622b.transact(3, parcelObtain, parcelObtain2, 0) || a.d0() == null) {
                        parcelObtain2.readException();
                    } else {
                        a.d0().l();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // c.a.c.b
            public void v() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.byd.systemsettings.ISystemSettings");
                    if (this.f2622b.transact(12, parcelObtain, parcelObtain2, 0) || a.d0() == null) {
                        parcelObtain2.readException();
                    } else {
                        a.d0().v();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static b c0(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.byd.systemsettings.ISystemSettings");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof b)) ? new C0069a(iBinder) : (b) iInterfaceQueryLocalInterface;
        }

        public static b d0() {
            return C0069a.f2621a;
        }
    }

    void l() throws RemoteException;

    void v() throws RemoteException;
}
