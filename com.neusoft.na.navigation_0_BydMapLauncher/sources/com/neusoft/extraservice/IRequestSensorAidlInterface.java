package com.neusoft.extraservice;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.neusoft.extraservice.INavigationAidlInterface;

/* JADX INFO: loaded from: classes2.dex */
public interface IRequestSensorAidlInterface extends IInterface {
    void requestSensorDataCallBack(INavigationAidlInterface iNavigationAidlInterface) throws RemoteException;

    public static abstract class Stub extends Binder implements IRequestSensorAidlInterface {
        private static final String DESCRIPTOR = "com.neusoft.extraservice.IRequestSensorAidlInterface";
        static final int TRANSACTION_requestSensorDataCallBack = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IRequestSensorAidlInterface asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IRequestSensorAidlInterface)) {
                return (IRequestSensorAidlInterface) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1) {
                if (i == 1598968902) {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface(DESCRIPTOR);
            requestSensorDataCallBack(INavigationAidlInterface.Stub.asInterface(parcel.readStrongBinder()));
            parcel2.writeNoException();
            return true;
        }

        private static class Proxy implements IRequestSensorAidlInterface {
            private IBinder mRemote;

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.neusoft.extraservice.IRequestSensorAidlInterface
            public void requestSensorDataCallBack(INavigationAidlInterface iNavigationAidlInterface) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iNavigationAidlInterface != null ? iNavigationAidlInterface.asBinder() : null);
                    this.mRemote.transact(1, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }
    }
}
