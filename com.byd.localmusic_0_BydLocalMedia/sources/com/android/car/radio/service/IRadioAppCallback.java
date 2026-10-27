package com.android.car.radio.service;

import android.hardware.radio.RadioManager;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public interface IRadioAppCallback extends IInterface {

    public static class Default implements IRadioAppCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.android.car.radio.service.IRadioAppCallback
        public void onCurrentProgramChanged(RadioManager.ProgramInfo programInfo) throws RemoteException {
        }

        @Override // com.android.car.radio.service.IRadioAppCallback
        public void onHardwareError() throws RemoteException {
        }

        @Override // com.android.car.radio.service.IRadioAppCallback
        public void onPlaybackStateChanged(int i) throws RemoteException {
        }

        @Override // com.android.car.radio.service.IRadioAppCallback
        public void onProgramListChanged(List<RadioManager.ProgramInfo> list) throws RemoteException {
        }
    }

    void onCurrentProgramChanged(RadioManager.ProgramInfo programInfo) throws RemoteException;

    void onHardwareError() throws RemoteException;

    void onPlaybackStateChanged(int i) throws RemoteException;

    void onProgramListChanged(List<RadioManager.ProgramInfo> list) throws RemoteException;

    public static abstract class Stub extends Binder implements IRadioAppCallback {
        private static final String DESCRIPTOR = "com.android.car.radio.service.IRadioAppCallback";
        static final int TRANSACTION_onCurrentProgramChanged = 2;
        static final int TRANSACTION_onHardwareError = 1;
        static final int TRANSACTION_onPlaybackStateChanged = 3;
        static final int TRANSACTION_onProgramListChanged = 4;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IRadioAppCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IRadioAppCallback)) {
                return (IRadioAppCallback) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                onHardwareError();
                parcel2.writeNoException();
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                onCurrentProgramChanged(parcel.readInt() != 0 ? RadioManager.ProgramInfo.CREATOR.createFromParcel(parcel) : null);
                parcel2.writeNoException();
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                onPlaybackStateChanged(parcel.readInt());
                parcel2.writeNoException();
                return true;
            }
            if (i != 4) {
                if (i == 1598968902) {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface(DESCRIPTOR);
            onProgramListChanged(parcel.createTypedArrayList(RadioManager.ProgramInfo.CREATOR));
            parcel2.writeNoException();
            return true;
        }

        private static class Proxy implements IRadioAppCallback {
            public static IRadioAppCallback sDefaultImpl;
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

            @Override // com.android.car.radio.service.IRadioAppCallback
            public void onHardwareError() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onHardwareError();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.car.radio.service.IRadioAppCallback
            public void onCurrentProgramChanged(RadioManager.ProgramInfo programInfo) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (programInfo != null) {
                        parcelObtain.writeInt(1);
                        programInfo.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onCurrentProgramChanged(programInfo);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.car.radio.service.IRadioAppCallback
            public void onPlaybackStateChanged(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onPlaybackStateChanged(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.car.radio.service.IRadioAppCallback
            public void onProgramListChanged(List<RadioManager.ProgramInfo> list) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeTypedList(list);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onProgramListChanged(list);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IRadioAppCallback iRadioAppCallback) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iRadioAppCallback == null) {
                return false;
            }
            Proxy.sDefaultImpl = iRadioAppCallback;
            return true;
        }

        public static IRadioAppCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
