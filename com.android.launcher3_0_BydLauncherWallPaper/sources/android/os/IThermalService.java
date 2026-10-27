package android.os;

import android.os.IThermalEventListener;
import android.os.IThermalStatusListener;

/* JADX INFO: loaded from: classes2.dex */
public interface IThermalService extends IInterface {

    public static class Default implements IThermalService {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.os.IThermalService
        public CoolingDevice[] getCurrentCoolingDevices() throws RemoteException {
            return null;
        }

        @Override // android.os.IThermalService
        public CoolingDevice[] getCurrentCoolingDevicesWithType(int i) throws RemoteException {
            return null;
        }

        @Override // android.os.IThermalService
        public Temperature[] getCurrentTemperatures() throws RemoteException {
            return null;
        }

        @Override // android.os.IThermalService
        public Temperature[] getCurrentTemperaturesWithType(int i) throws RemoteException {
            return null;
        }

        @Override // android.os.IThermalService
        public int getCurrentThermalStatus() throws RemoteException {
            return 0;
        }

        @Override // android.os.IThermalService
        public float getThermalHeadroom(int i) throws RemoteException {
            return 0.0f;
        }

        @Override // android.os.IThermalService
        public boolean registerThermalEventListener(IThermalEventListener iThermalEventListener) throws RemoteException {
            return false;
        }

        @Override // android.os.IThermalService
        public boolean registerThermalEventListenerWithType(IThermalEventListener iThermalEventListener, int i) throws RemoteException {
            return false;
        }

        @Override // android.os.IThermalService
        public boolean registerThermalStatusListener(IThermalStatusListener iThermalStatusListener) throws RemoteException {
            return false;
        }

        @Override // android.os.IThermalService
        public boolean unregisterThermalEventListener(IThermalEventListener iThermalEventListener) throws RemoteException {
            return false;
        }

        @Override // android.os.IThermalService
        public boolean unregisterThermalStatusListener(IThermalStatusListener iThermalStatusListener) throws RemoteException {
            return false;
        }
    }

    CoolingDevice[] getCurrentCoolingDevices() throws RemoteException;

    CoolingDevice[] getCurrentCoolingDevicesWithType(int i) throws RemoteException;

    Temperature[] getCurrentTemperatures() throws RemoteException;

    Temperature[] getCurrentTemperaturesWithType(int i) throws RemoteException;

    int getCurrentThermalStatus() throws RemoteException;

    float getThermalHeadroom(int i) throws RemoteException;

    boolean registerThermalEventListener(IThermalEventListener iThermalEventListener) throws RemoteException;

    boolean registerThermalEventListenerWithType(IThermalEventListener iThermalEventListener, int i) throws RemoteException;

    boolean registerThermalStatusListener(IThermalStatusListener iThermalStatusListener) throws RemoteException;

    boolean unregisterThermalEventListener(IThermalEventListener iThermalEventListener) throws RemoteException;

    boolean unregisterThermalStatusListener(IThermalStatusListener iThermalStatusListener) throws RemoteException;

    public static abstract class Stub extends Binder implements IThermalService {
        private static final String DESCRIPTOR = "android.os.IThermalService";
        static final int TRANSACTION_getCurrentCoolingDevices = 9;
        static final int TRANSACTION_getCurrentCoolingDevicesWithType = 10;
        static final int TRANSACTION_getCurrentTemperatures = 4;
        static final int TRANSACTION_getCurrentTemperaturesWithType = 5;
        static final int TRANSACTION_getCurrentThermalStatus = 8;
        static final int TRANSACTION_getThermalHeadroom = 11;
        static final int TRANSACTION_registerThermalEventListener = 1;
        static final int TRANSACTION_registerThermalEventListenerWithType = 2;
        static final int TRANSACTION_registerThermalStatusListener = 6;
        static final int TRANSACTION_unregisterThermalEventListener = 3;
        static final int TRANSACTION_unregisterThermalStatusListener = 7;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "registerThermalEventListener";
                case 2:
                    return "registerThermalEventListenerWithType";
                case 3:
                    return "unregisterThermalEventListener";
                case 4:
                    return "getCurrentTemperatures";
                case 5:
                    return "getCurrentTemperaturesWithType";
                case 6:
                    return "registerThermalStatusListener";
                case 7:
                    return "unregisterThermalStatusListener";
                case 8:
                    return "getCurrentThermalStatus";
                case 9:
                    return "getCurrentCoolingDevices";
                case 10:
                    return "getCurrentCoolingDevicesWithType";
                case 11:
                    return "getThermalHeadroom";
                default:
                    return null;
            }
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IThermalService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IThermalService)) {
                return (IThermalService) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public String getTransactionName(int i) {
            return getDefaultTransactionName(i);
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
                    boolean zRegisterThermalEventListener = registerThermalEventListener(IThermalEventListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(zRegisterThermalEventListener ? 1 : 0);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zRegisterThermalEventListenerWithType = registerThermalEventListenerWithType(IThermalEventListener.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zRegisterThermalEventListenerWithType ? 1 : 0);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zUnregisterThermalEventListener = unregisterThermalEventListener(IThermalEventListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(zUnregisterThermalEventListener ? 1 : 0);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    Temperature[] currentTemperatures = getCurrentTemperatures();
                    parcel2.writeNoException();
                    parcel2.writeTypedArray(currentTemperatures, 1);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    Temperature[] currentTemperaturesWithType = getCurrentTemperaturesWithType(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeTypedArray(currentTemperaturesWithType, 1);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zRegisterThermalStatusListener = registerThermalStatusListener(IThermalStatusListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(zRegisterThermalStatusListener ? 1 : 0);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zUnregisterThermalStatusListener = unregisterThermalStatusListener(IThermalStatusListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(zUnregisterThermalStatusListener ? 1 : 0);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    int currentThermalStatus = getCurrentThermalStatus();
                    parcel2.writeNoException();
                    parcel2.writeInt(currentThermalStatus);
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    CoolingDevice[] currentCoolingDevices = getCurrentCoolingDevices();
                    parcel2.writeNoException();
                    parcel2.writeTypedArray(currentCoolingDevices, 1);
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    CoolingDevice[] currentCoolingDevicesWithType = getCurrentCoolingDevicesWithType(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeTypedArray(currentCoolingDevicesWithType, 1);
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    float thermalHeadroom = getThermalHeadroom(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeFloat(thermalHeadroom);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IThermalService {
            public static IThermalService sDefaultImpl;
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

            @Override // android.os.IThermalService
            public boolean registerThermalEventListener(IThermalEventListener iThermalEventListener) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iThermalEventListener != null ? iThermalEventListener.asBinder() : null);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().registerThermalEventListener(iThermalEventListener);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IThermalService
            public boolean registerThermalEventListenerWithType(IThermalEventListener iThermalEventListener, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iThermalEventListener != null ? iThermalEventListener.asBinder() : null);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().registerThermalEventListenerWithType(iThermalEventListener, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IThermalService
            public boolean unregisterThermalEventListener(IThermalEventListener iThermalEventListener) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iThermalEventListener != null ? iThermalEventListener.asBinder() : null);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().unregisterThermalEventListener(iThermalEventListener);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IThermalService
            public Temperature[] getCurrentTemperatures() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCurrentTemperatures();
                    }
                    parcelObtain2.readException();
                    return (Temperature[]) parcelObtain2.createTypedArray(Temperature.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IThermalService
            public Temperature[] getCurrentTemperaturesWithType(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCurrentTemperaturesWithType(i);
                    }
                    parcelObtain2.readException();
                    return (Temperature[]) parcelObtain2.createTypedArray(Temperature.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IThermalService
            public boolean registerThermalStatusListener(IThermalStatusListener iThermalStatusListener) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iThermalStatusListener != null ? iThermalStatusListener.asBinder() : null);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().registerThermalStatusListener(iThermalStatusListener);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IThermalService
            public boolean unregisterThermalStatusListener(IThermalStatusListener iThermalStatusListener) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iThermalStatusListener != null ? iThermalStatusListener.asBinder() : null);
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().unregisterThermalStatusListener(iThermalStatusListener);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IThermalService
            public int getCurrentThermalStatus() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCurrentThermalStatus();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IThermalService
            public CoolingDevice[] getCurrentCoolingDevices() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCurrentCoolingDevices();
                    }
                    parcelObtain2.readException();
                    return (CoolingDevice[]) parcelObtain2.createTypedArray(CoolingDevice.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IThermalService
            public CoolingDevice[] getCurrentCoolingDevicesWithType(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(10, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCurrentCoolingDevicesWithType(i);
                    }
                    parcelObtain2.readException();
                    return (CoolingDevice[]) parcelObtain2.createTypedArray(CoolingDevice.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IThermalService
            public float getThermalHeadroom(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(11, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getThermalHeadroom(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readFloat();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IThermalService iThermalService) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iThermalService == null) {
                return false;
            }
            Proxy.sDefaultImpl = iThermalService;
            return true;
        }

        public static IThermalService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
