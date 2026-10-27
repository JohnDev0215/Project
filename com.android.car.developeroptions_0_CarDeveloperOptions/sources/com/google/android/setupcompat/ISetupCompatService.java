package com.google.android.setupcompat;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public interface ISetupCompatService extends IInterface {
    void logMetric(int i, Bundle bundle, Bundle bundle2) throws RemoteException;

    void validateActivity(String str, Bundle bundle) throws RemoteException;

    public static abstract class Stub extends Binder implements ISetupCompatService {
        public static ISetupCompatService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.setupcompat.ISetupCompatService");
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof ISetupCompatService)) {
                return (ISetupCompatService) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        private static class Proxy implements ISetupCompatService {
            public static ISetupCompatService sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.google.android.setupcompat.ISetupCompatService
            public void validateActivity(String str, Bundle bundle) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.setupcompat.ISetupCompatService");
                    parcelObtain.writeString(str);
                    if (bundle != null) {
                        parcelObtain.writeInt(1);
                        bundle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().validateActivity(str, bundle);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.android.setupcompat.ISetupCompatService
            public void logMetric(int i, Bundle bundle, Bundle bundle2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.setupcompat.ISetupCompatService");
                    parcelObtain.writeInt(i);
                    if (bundle != null) {
                        parcelObtain.writeInt(1);
                        bundle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (bundle2 != null) {
                        parcelObtain.writeInt(1);
                        bundle2.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().logMetric(i, bundle, bundle2);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static ISetupCompatService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
