package c.a.h.a;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: compiled from: IOtaSdkCallback.java */
/* JADX INFO: loaded from: classes.dex */
public interface a extends IInterface {

    /* JADX INFO: renamed from: c.a.h.a.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: IOtaSdkCallback.java */
    public static abstract class AbstractBinderC0055a extends Binder implements a {

        /* JADX INFO: renamed from: c.a.h.a.a$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: IOtaSdkCallback.java */
        public static class C0056a implements a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static a f2776a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public IBinder f2777b;

            public C0056a(IBinder iBinder) {
                this.f2777b = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f2777b;
            }

            @Override // c.a.h.a.a
            public void k(long j) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.byd.sdkupdate.aidl.IOtaSdkCallback");
                    parcelObtain.writeLong(j);
                    if (this.f2777b.transact(2, parcelObtain, parcelObtain2, 0) || AbstractBinderC0055a.C() == null) {
                        parcelObtain2.readException();
                    } else {
                        AbstractBinderC0055a.C().k(j);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // c.a.h.a.a
            public void v(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.byd.sdkupdate.aidl.IOtaSdkCallback");
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (this.f2777b.transact(1, parcelObtain, parcelObtain2, 0) || AbstractBinderC0055a.C() == null) {
                        parcelObtain2.readException();
                    } else {
                        AbstractBinderC0055a.C().v(z);
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
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.byd.sdkupdate.aidl.IOtaSdkCallback");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof a)) ? new C0056a(iBinder) : (a) iInterfaceQueryLocalInterface;
        }

        public static a C() {
            return C0056a.f2776a;
        }
    }

    void k(long j) throws RemoteException;

    void v(boolean z) throws RemoteException;
}
