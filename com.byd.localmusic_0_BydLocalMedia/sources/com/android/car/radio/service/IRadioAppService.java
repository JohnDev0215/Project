package com.android.car.radio.service;

import android.hardware.radio.ProgramSelector;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.android.car.radio.bands.ProgramType;
import com.android.car.radio.bands.RegionConfig;
import com.android.car.radio.service.IRadioAppCallback;
import com.android.car.radio.service.ITuneCallback;

/* JADX INFO: loaded from: classes4.dex */
public interface IRadioAppService extends IInterface {

    public static class Default implements IRadioAppService {
        @Override // com.android.car.radio.service.IRadioAppService
        public void addCallback(IRadioAppCallback iRadioAppCallback) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.android.car.radio.service.IRadioAppService
        public RegionConfig getRegionConfig() throws RemoteException {
            return null;
        }

        @Override // com.android.car.radio.service.IRadioAppService
        public boolean isProgramListSupported() throws RemoteException {
            return false;
        }

        @Override // com.android.car.radio.service.IRadioAppService
        public void removeCallback(IRadioAppCallback iRadioAppCallback) throws RemoteException {
        }

        @Override // com.android.car.radio.service.IRadioAppService
        public void seek(boolean z, ITuneCallback iTuneCallback) throws RemoteException {
        }

        @Override // com.android.car.radio.service.IRadioAppService
        public void setMuted(boolean z) throws RemoteException {
        }

        @Override // com.android.car.radio.service.IRadioAppService
        public void setSkipMode(int i) throws RemoteException {
        }

        @Override // com.android.car.radio.service.IRadioAppService
        public void skip(boolean z, ITuneCallback iTuneCallback) throws RemoteException {
        }

        @Override // com.android.car.radio.service.IRadioAppService
        public void step(boolean z, ITuneCallback iTuneCallback) throws RemoteException {
        }

        @Override // com.android.car.radio.service.IRadioAppService
        public void switchBand(ProgramType programType) throws RemoteException {
        }

        @Override // com.android.car.radio.service.IRadioAppService
        public void tune(ProgramSelector programSelector, ITuneCallback iTuneCallback) throws RemoteException {
        }
    }

    void addCallback(IRadioAppCallback iRadioAppCallback) throws RemoteException;

    RegionConfig getRegionConfig() throws RemoteException;

    boolean isProgramListSupported() throws RemoteException;

    void removeCallback(IRadioAppCallback iRadioAppCallback) throws RemoteException;

    void seek(boolean z, ITuneCallback iTuneCallback) throws RemoteException;

    void setMuted(boolean z) throws RemoteException;

    void setSkipMode(int i) throws RemoteException;

    void skip(boolean z, ITuneCallback iTuneCallback) throws RemoteException;

    void step(boolean z, ITuneCallback iTuneCallback) throws RemoteException;

    void switchBand(ProgramType programType) throws RemoteException;

    void tune(ProgramSelector programSelector, ITuneCallback iTuneCallback) throws RemoteException;

    public static abstract class Stub extends Binder implements IRadioAppService {
        private static final String DESCRIPTOR = "com.android.car.radio.service.IRadioAppService";
        static final int TRANSACTION_addCallback = 1;
        static final int TRANSACTION_getRegionConfig = 11;
        static final int TRANSACTION_isProgramListSupported = 10;
        static final int TRANSACTION_removeCallback = 2;
        static final int TRANSACTION_seek = 4;
        static final int TRANSACTION_setMuted = 8;
        static final int TRANSACTION_setSkipMode = 7;
        static final int TRANSACTION_skip = 6;
        static final int TRANSACTION_step = 5;
        static final int TRANSACTION_switchBand = 9;
        static final int TRANSACTION_tune = 3;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IRadioAppService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IRadioAppService)) {
                return (IRadioAppService) iInterfaceQueryLocalInterface;
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
                    addCallback(IRadioAppCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    removeCallback(IRadioAppCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    tune(parcel.readInt() != 0 ? ProgramSelector.CREATOR.createFromParcel(parcel) : null, ITuneCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    seek(parcel.readInt() != 0, ITuneCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    step(parcel.readInt() != 0, ITuneCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    skip(parcel.readInt() != 0, ITuneCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    setSkipMode(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    setMuted(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    switchBand(parcel.readInt() != 0 ? ProgramType.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsProgramListSupported = isProgramListSupported();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsProgramListSupported ? 1 : 0);
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    RegionConfig regionConfig = getRegionConfig();
                    parcel2.writeNoException();
                    if (regionConfig != null) {
                        parcel2.writeInt(1);
                        regionConfig.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IRadioAppService {
            public static IRadioAppService sDefaultImpl;
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

            @Override // com.android.car.radio.service.IRadioAppService
            public void addCallback(IRadioAppCallback iRadioAppCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iRadioAppCallback != null ? iRadioAppCallback.asBinder() : null);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().addCallback(iRadioAppCallback);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.car.radio.service.IRadioAppService
            public void removeCallback(IRadioAppCallback iRadioAppCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iRadioAppCallback != null ? iRadioAppCallback.asBinder() : null);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().removeCallback(iRadioAppCallback);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.car.radio.service.IRadioAppService
            public void tune(ProgramSelector programSelector, ITuneCallback iTuneCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (programSelector != null) {
                        parcelObtain.writeInt(1);
                        programSelector.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeStrongBinder(iTuneCallback != null ? iTuneCallback.asBinder() : null);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().tune(programSelector, iTuneCallback);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.car.radio.service.IRadioAppService
            public void seek(boolean z, ITuneCallback iTuneCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeStrongBinder(iTuneCallback != null ? iTuneCallback.asBinder() : null);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().seek(z, iTuneCallback);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.car.radio.service.IRadioAppService
            public void step(boolean z, ITuneCallback iTuneCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeStrongBinder(iTuneCallback != null ? iTuneCallback.asBinder() : null);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().step(z, iTuneCallback);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.car.radio.service.IRadioAppService
            public void skip(boolean z, ITuneCallback iTuneCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeStrongBinder(iTuneCallback != null ? iTuneCallback.asBinder() : null);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().skip(z, iTuneCallback);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.car.radio.service.IRadioAppService
            public void setSkipMode(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setSkipMode(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.car.radio.service.IRadioAppService
            public void setMuted(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setMuted(z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.car.radio.service.IRadioAppService
            public void switchBand(ProgramType programType) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (programType != null) {
                        parcelObtain.writeInt(1);
                        programType.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().switchBand(programType);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.car.radio.service.IRadioAppService
            public boolean isProgramListSupported() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(10, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isProgramListSupported();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.car.radio.service.IRadioAppService
            public RegionConfig getRegionConfig() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(11, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getRegionConfig();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? RegionConfig.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IRadioAppService iRadioAppService) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iRadioAppService == null) {
                return false;
            }
            Proxy.sDefaultImpl = iRadioAppService;
            return true;
        }

        public static IRadioAppService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
