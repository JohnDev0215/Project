package com.android.vending.licensing;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes5.dex */
public interface ILicenseResultListener extends IInterface {
    void verifyLicense(int responseCode, String signedData, String signature) throws RemoteException;

    public static abstract class Stub extends Binder implements ILicenseResultListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, "com.android.vending.licensing.ILicenseResultListener");
        }

        public static ILicenseResultListener asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = obj.queryLocalInterface("com.android.vending.licensing.ILicenseResultListener");
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof ILicenseResultListener)) {
                return (ILicenseResultListener) iInterfaceQueryLocalInterface;
            }
            return new Proxy(obj);
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            if (code == 1598968902) {
                reply.writeString("com.android.vending.licensing.ILicenseResultListener");
                return true;
            }
            if (code == 1) {
                data.enforceInterface("com.android.vending.licensing.ILicenseResultListener");
                verifyLicense(data.readInt(), data.readString(), data.readString());
                return true;
            }
            return super.onTransact(code, data, reply, flags);
        }

        private static class Proxy implements ILicenseResultListener {
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }
        }
    }
}
