package com.android.vending.licensing;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.android.vending.licensing.ILicenseResultListener;

/* JADX INFO: loaded from: classes5.dex */
public interface ILicensingService extends IInterface {
    void checkLicense(long nonce, String packageName, ILicenseResultListener listener) throws RemoteException;

    public static abstract class Stub extends Binder implements ILicensingService {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, "com.android.vending.licensing.ILicensingService");
        }

        public static ILicensingService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = obj.queryLocalInterface("com.android.vending.licensing.ILicensingService");
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof ILicensingService)) {
                return (ILicensingService) iInterfaceQueryLocalInterface;
            }
            return new Proxy(obj);
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            if (code == 1598968902) {
                reply.writeString("com.android.vending.licensing.ILicensingService");
                return true;
            }
            if (code == 1) {
                data.enforceInterface("com.android.vending.licensing.ILicensingService");
                checkLicense(data.readLong(), data.readString(), ILicenseResultListener.Stub.asInterface(data.readStrongBinder()));
                return true;
            }
            return super.onTransact(code, data, reply, flags);
        }

        private static class Proxy implements ILicensingService {
            public static ILicensingService sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.android.vending.licensing.ILicensingService
            public void checkLicense(long nonce, String packageName, ILicenseResultListener listener) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.android.vending.licensing.ILicensingService");
                    parcelObtain.writeLong(nonce);
                    parcelObtain.writeString(packageName);
                    parcelObtain.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().checkLicense(nonce, packageName, listener);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static ILicensingService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
