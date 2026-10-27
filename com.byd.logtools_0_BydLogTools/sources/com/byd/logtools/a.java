package com.byd.logtools;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.byd.logtools.b;

/* JADX INFO: compiled from: IAuthBinder.java */
/* JADX INFO: loaded from: classes.dex */
public interface a extends IInterface {

    /* JADX INFO: renamed from: com.byd.logtools.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: IAuthBinder.java */
    public static abstract class AbstractBinderC0053a extends Binder implements a {

        /* JADX INFO: renamed from: com.byd.logtools.a$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: IAuthBinder.java */
        private static class C0054a implements a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static a f2967a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            private IBinder f2968b;

            C0054a(IBinder iBinder) {
                this.f2968b = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f2968b;
            }

            @Override // com.byd.logtools.a
            public void b(int i, String str, b bVar) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.byd.logtools.IAuthBinder");
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeStrongBinder(bVar != null ? bVar.asBinder() : null);
                    if (this.f2968b.transact(1, parcelObtain, parcelObtain2, 0) || AbstractBinderC0053a.i() == null) {
                        parcelObtain2.readException();
                    } else {
                        AbstractBinderC0053a.i().b(i, str, bVar);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.logtools.a
            public void c(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.byd.logtools.IAuthBinder");
                    parcelObtain.writeInt(i);
                    if (this.f2968b.transact(2, parcelObtain, parcelObtain2, 0) || AbstractBinderC0053a.i() == null) {
                        parcelObtain2.readException();
                    } else {
                        AbstractBinderC0053a.i().c(i);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public AbstractBinderC0053a() {
            attachInterface(this, "com.byd.logtools.IAuthBinder");
        }

        public static a h(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.byd.logtools.IAuthBinder");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof a)) ? new C0054a(iBinder) : (a) iInterfaceQueryLocalInterface;
        }

        public static a i() {
            return C0054a.f2967a;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface("com.byd.logtools.IAuthBinder");
                b(parcel.readInt(), parcel.readString(), b.a.h(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            if (i != 2) {
                if (i != 1598968902) {
                    return super.onTransact(i, parcel, parcel2, i2);
                }
                parcel2.writeString("com.byd.logtools.IAuthBinder");
                return true;
            }
            parcel.enforceInterface("com.byd.logtools.IAuthBinder");
            c(parcel.readInt());
            parcel2.writeNoException();
            return true;
        }
    }

    void b(int i, String str, b bVar) throws RemoteException;

    void c(int i) throws RemoteException;
}
