package com.neusoft.optimus.wheeljack.popview.AIDL;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.neusoft.optimus.wheeljack.popview.AIDL.IAudioBluetoothInterface;
import com.neusoft.optimus.wheeljack.popview.AIDL.IAudioInterface;
import com.neusoft.optimus.wheeljack.popview.AIDL.IBluetoothInterface;
import com.neusoft.optimus.wheeljack.popview.AIDL.ICarLifeInterface;
import com.neusoft.optimus.wheeljack.popview.AIDL.IDvrInterface;
import com.neusoft.optimus.wheeljack.popview.AIDL.IEcarxkeyInterface;
import com.neusoft.optimus.wheeljack.popview.AIDL.INotficationAppleInterface;
import com.neusoft.optimus.wheeljack.popview.AIDL.IRadioAidlInterface;
import com.neusoft.optimus.wheeljack.popview.AIDL.IVRAidlInterface;
import com.neusoft.optimus.wheeljack.popview.AIDL.IVideoInterface;

/* JADX INFO: loaded from: classes2.dex */
public interface IRequestInterface extends IInterface {
    void changeSIRIVRStatus(int i) throws RemoteException;

    void requestAudioBluetoothCallBack(IAudioBluetoothInterface iAudioBluetoothInterface) throws RemoteException;

    void requestAudioCallBack(IAudioInterface iAudioInterface) throws RemoteException;

    void requestBluetoothCallBack(IBluetoothInterface iBluetoothInterface) throws RemoteException;

    void requestCarLifeCallBack(ICarLifeInterface iCarLifeInterface) throws RemoteException;

    void requestDvrCallBack(IDvrInterface iDvrInterface) throws RemoteException;

    void requestEcarxkeyCallBack(IEcarxkeyInterface iEcarxkeyInterface) throws RemoteException;

    void requestNotificationApple(INotficationAppleInterface iNotficationAppleInterface) throws RemoteException;

    void requestRadioCallBack(IRadioAidlInterface iRadioAidlInterface) throws RemoteException;

    void requestVRCallBack(IVRAidlInterface iVRAidlInterface) throws RemoteException;

    void requestVideoCallBack(IVideoInterface iVideoInterface) throws RemoteException;

    void unrequestEcarxkeyCallBack(IEcarxkeyInterface iEcarxkeyInterface) throws RemoteException;

    public static abstract class Stub extends Binder implements IRequestInterface {
        private static final String DESCRIPTOR = "com.neusoft.optimus.wheeljack.popview.AIDL.IRequestInterface";
        static final int TRANSACTION_changeSIRIVRStatus = 12;
        static final int TRANSACTION_requestAudioBluetoothCallBack = 5;
        static final int TRANSACTION_requestAudioCallBack = 1;
        static final int TRANSACTION_requestBluetoothCallBack = 4;
        static final int TRANSACTION_requestCarLifeCallBack = 9;
        static final int TRANSACTION_requestDvrCallBack = 8;
        static final int TRANSACTION_requestEcarxkeyCallBack = 10;
        static final int TRANSACTION_requestNotificationApple = 7;
        static final int TRANSACTION_requestRadioCallBack = 3;
        static final int TRANSACTION_requestVRCallBack = 6;
        static final int TRANSACTION_requestVideoCallBack = 2;
        static final int TRANSACTION_unrequestEcarxkeyCallBack = 11;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IRequestInterface asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IRequestInterface)) {
                return (IRequestInterface) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface(DESCRIPTOR);
                    requestAudioCallBack(IAudioInterface.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    requestVideoCallBack(IVideoInterface.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    requestRadioCallBack(IRadioAidlInterface.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    requestBluetoothCallBack(IBluetoothInterface.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    requestAudioBluetoothCallBack(IAudioBluetoothInterface.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    requestVRCallBack(IVRAidlInterface.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    requestNotificationApple(INotficationAppleInterface.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    requestDvrCallBack(IDvrInterface.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    requestCarLifeCallBack(ICarLifeInterface.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    requestEcarxkeyCallBack(IEcarxkeyInterface.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    unrequestEcarxkeyCallBack(IEcarxkeyInterface.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    changeSIRIVRStatus(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IRequestInterface {
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

            @Override // com.neusoft.optimus.wheeljack.popview.AIDL.IRequestInterface
            public void requestAudioCallBack(IAudioInterface iAudioInterface) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iAudioInterface != null ? iAudioInterface.asBinder() : null);
                    this.mRemote.transact(1, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.optimus.wheeljack.popview.AIDL.IRequestInterface
            public void requestVideoCallBack(IVideoInterface iVideoInterface) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iVideoInterface != null ? iVideoInterface.asBinder() : null);
                    this.mRemote.transact(2, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.optimus.wheeljack.popview.AIDL.IRequestInterface
            public void requestRadioCallBack(IRadioAidlInterface iRadioAidlInterface) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iRadioAidlInterface != null ? iRadioAidlInterface.asBinder() : null);
                    this.mRemote.transact(3, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.optimus.wheeljack.popview.AIDL.IRequestInterface
            public void requestBluetoothCallBack(IBluetoothInterface iBluetoothInterface) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBluetoothInterface != null ? iBluetoothInterface.asBinder() : null);
                    this.mRemote.transact(4, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.optimus.wheeljack.popview.AIDL.IRequestInterface
            public void requestAudioBluetoothCallBack(IAudioBluetoothInterface iAudioBluetoothInterface) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iAudioBluetoothInterface != null ? iAudioBluetoothInterface.asBinder() : null);
                    this.mRemote.transact(5, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.optimus.wheeljack.popview.AIDL.IRequestInterface
            public void requestVRCallBack(IVRAidlInterface iVRAidlInterface) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iVRAidlInterface != null ? iVRAidlInterface.asBinder() : null);
                    this.mRemote.transact(6, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.optimus.wheeljack.popview.AIDL.IRequestInterface
            public void requestNotificationApple(INotficationAppleInterface iNotficationAppleInterface) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iNotficationAppleInterface != null ? iNotficationAppleInterface.asBinder() : null);
                    this.mRemote.transact(7, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.optimus.wheeljack.popview.AIDL.IRequestInterface
            public void requestDvrCallBack(IDvrInterface iDvrInterface) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iDvrInterface != null ? iDvrInterface.asBinder() : null);
                    this.mRemote.transact(8, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.optimus.wheeljack.popview.AIDL.IRequestInterface
            public void requestCarLifeCallBack(ICarLifeInterface iCarLifeInterface) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iCarLifeInterface != null ? iCarLifeInterface.asBinder() : null);
                    this.mRemote.transact(9, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.optimus.wheeljack.popview.AIDL.IRequestInterface
            public void requestEcarxkeyCallBack(IEcarxkeyInterface iEcarxkeyInterface) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iEcarxkeyInterface != null ? iEcarxkeyInterface.asBinder() : null);
                    this.mRemote.transact(10, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.optimus.wheeljack.popview.AIDL.IRequestInterface
            public void unrequestEcarxkeyCallBack(IEcarxkeyInterface iEcarxkeyInterface) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iEcarxkeyInterface != null ? iEcarxkeyInterface.asBinder() : null);
                    this.mRemote.transact(11, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.optimus.wheeljack.popview.AIDL.IRequestInterface
            public void changeSIRIVRStatus(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    this.mRemote.transact(12, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }
    }
}
