package com.byd.logtools;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: compiled from: IAuthResultCallback.java */
/* JADX INFO: loaded from: classes.dex */
public interface b extends IInterface {

    /* JADX INFO: compiled from: IAuthResultCallback.java */
    public static abstract class a extends Binder implements b {

        /* JADX INFO: renamed from: com.byd.logtools.b$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: IAuthResultCallback.java */
        private static class C0058a implements b {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static b f3154a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            private IBinder f3155b;

            C0058a(IBinder iBinder) {
                this.f3155b = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f3155b;
            }

            @Override // com.byd.logtools.b
            public void d(int i, int i2, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.byd.logtools.IAuthResultCallback");
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeString(str);
                    if (this.f3155b.transact(1, parcelObtain, parcelObtain2, 0) || a.i() == null) {
                        parcelObtain2.readException();
                    } else {
                        a.i().d(i, i2, str);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public a() {
            attachInterface(this, "com.byd.logtools.IAuthResultCallback");
        }

        public static b h(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.byd.logtools.IAuthResultCallback");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof b)) ? new C0058a(iBinder) : (b) iInterfaceQueryLocalInterface;
        }

        public static b i() {
            return C0058a.f3154a;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1) {
                if (i != 1598968902) {
                    return super.onTransact(i, parcel, parcel2, i2);
                }
                parcel2.writeString("com.byd.logtools.IAuthResultCallback");
                return true;
            }
            parcel.enforceInterface("com.byd.logtools.IAuthResultCallback");
            d(parcel.readInt(), parcel.readInt(), parcel.readString());
            parcel2.writeNoException();
            return true;
        }
    }

    void d(int i, int i2, String str) throws RemoteException;
}
