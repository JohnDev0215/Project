package b.b.j;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: compiled from: INFCInterface.java */
/* JADX INFO: loaded from: classes2.dex */
public interface d extends IInterface {

    /* JADX INFO: compiled from: INFCInterface.java */
    public static abstract class a extends Binder implements d {

        /* JADX INFO: renamed from: b.b.j.d$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: INFCInterface.java */
        public static class C0081a implements d {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static d f1943a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public IBinder f1944b;

            public C0081a(IBinder iBinder) {
                this.f1944b = iBinder;
            }

            @Override // b.b.j.d
            public void F(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.byd.digitalkey.INFCInterface");
                    parcelObtain.writeString(str);
                    if (this.f1944b.transact(1, parcelObtain, parcelObtain2, 0) || a.e0() == null) {
                        parcelObtain2.readException();
                    } else {
                        a.e0().F(str);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f1944b;
            }
        }

        public static d d0(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.byd.digitalkey.INFCInterface");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof d)) ? new C0081a(iBinder) : (d) iInterfaceQueryLocalInterface;
        }

        public static d e0() {
            return C0081a.f1943a;
        }
    }

    void F(String str) throws RemoteException;
}
