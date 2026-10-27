package c.a.c;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: compiled from: IAuthBinder.java */
/* JADX INFO: loaded from: classes.dex */
public interface a extends IInterface {

    /* JADX INFO: renamed from: c.a.c.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: IAuthBinder.java */
    public static abstract class AbstractBinderC0047a extends Binder implements a {

        /* JADX INFO: renamed from: c.a.c.a$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: IAuthBinder.java */
        public static class C0048a implements a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static a f2356a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public IBinder f2357b;

            public C0048a(IBinder iBinder) {
                this.f2357b = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f2357b;
            }

            @Override // c.a.c.a
            public void e(int i2, String str, b bVar) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.byd.logtools.IAuthBinder");
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeString(str);
                    parcelObtain.writeStrongBinder(bVar != null ? bVar.asBinder() : null);
                    if (this.f2357b.transact(1, parcelObtain, parcelObtain2, 0) || AbstractBinderC0047a.C() == null) {
                        parcelObtain2.readException();
                    } else {
                        AbstractBinderC0047a.C().e(i2, str, bVar);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static a B(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.byd.logtools.IAuthBinder");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof a)) ? new C0048a(iBinder) : (a) iInterfaceQueryLocalInterface;
        }

        public static a C() {
            return C0048a.f2356a;
        }
    }

    void e(int i2, String str, b bVar) throws RemoteException;
}
