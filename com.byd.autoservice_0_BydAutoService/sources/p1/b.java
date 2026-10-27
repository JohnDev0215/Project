package p1;

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

        /* JADX INFO: renamed from: p1.b$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: ISystemSettings.java */
        public static class C0051a implements b {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static b f4526a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public IBinder f4527b;

            public C0051a(IBinder iBinder) {
                this.f4527b = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f4527b;
            }

            @Override // p1.b
            public void t() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.byd.systemsettings.ISystemSettings");
                    if (this.f4527b.transact(12, parcelObtain, parcelObtain2, 0) || a.G() == null) {
                        parcelObtain2.readException();
                    } else {
                        a.G().t();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static b F(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.byd.systemsettings.ISystemSettings");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof b)) ? new C0051a(iBinder) : (b) iInterfaceQueryLocalInterface;
        }

        public static b G() {
            return C0051a.f4526a;
        }
    }

    void t() throws RemoteException;
}
