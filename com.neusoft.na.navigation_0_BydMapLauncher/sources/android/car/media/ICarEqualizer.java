package android.car.media;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public interface ICarEqualizer extends IInterface {

    public static class Default implements ICarEqualizer {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.car.media.ICarEqualizer
        public int getBand(int i) throws RemoteException {
            return 0;
        }

        @Override // android.car.media.ICarEqualizer
        public int[] getBandFreqRange(int i) throws RemoteException {
            return null;
        }

        @Override // android.car.media.ICarEqualizer
        public int getBandLevel(int i) throws RemoteException {
            return 0;
        }

        @Override // android.car.media.ICarEqualizer
        public int[] getBandLevelRange(int i) throws RemoteException {
            return null;
        }

        @Override // android.car.media.ICarEqualizer
        public int getCenterFreq(int i) throws RemoteException {
            return 0;
        }

        @Override // android.car.media.ICarEqualizer
        public int getCurrentPreset() throws RemoteException {
            return 0;
        }

        @Override // android.car.media.ICarEqualizer
        public int getNumberOfBands() throws RemoteException {
            return 0;
        }

        @Override // android.car.media.ICarEqualizer
        public int getNumberOfPresets() throws RemoteException {
            return 0;
        }

        @Override // android.car.media.ICarEqualizer
        public String getPresetName(int i) throws RemoteException {
            return null;
        }

        @Override // android.car.media.ICarEqualizer
        public boolean isBandSupported() throws RemoteException {
            return false;
        }

        @Override // android.car.media.ICarEqualizer
        public boolean isPresetSupported() throws RemoteException {
            return false;
        }

        @Override // android.car.media.ICarEqualizer
        public void registerEqualizerCallback(IBinder iBinder) throws RemoteException {
        }

        @Override // android.car.media.ICarEqualizer
        public void setBandLevel(int i, int i2) throws RemoteException {
        }

        @Override // android.car.media.ICarEqualizer
        public void unregisterEqualizerCallback(IBinder iBinder) throws RemoteException {
        }

        @Override // android.car.media.ICarEqualizer
        public void usePreset(int i) throws RemoteException {
        }
    }

    int getBand(int i) throws RemoteException;

    int[] getBandFreqRange(int i) throws RemoteException;

    int getBandLevel(int i) throws RemoteException;

    int[] getBandLevelRange(int i) throws RemoteException;

    int getCenterFreq(int i) throws RemoteException;

    int getCurrentPreset() throws RemoteException;

    int getNumberOfBands() throws RemoteException;

    int getNumberOfPresets() throws RemoteException;

    String getPresetName(int i) throws RemoteException;

    boolean isBandSupported() throws RemoteException;

    boolean isPresetSupported() throws RemoteException;

    void registerEqualizerCallback(IBinder iBinder) throws RemoteException;

    void setBandLevel(int i, int i2) throws RemoteException;

    void unregisterEqualizerCallback(IBinder iBinder) throws RemoteException;

    void usePreset(int i) throws RemoteException;

    public static abstract class Stub extends Binder implements ICarEqualizer {
        private static final String DESCRIPTOR = "android.car.media.ICarEqualizer";
        static final int TRANSACTION_getBand = 7;
        static final int TRANSACTION_getBandFreqRange = 6;
        static final int TRANSACTION_getBandLevel = 4;
        static final int TRANSACTION_getBandLevelRange = 2;
        static final int TRANSACTION_getCenterFreq = 5;
        static final int TRANSACTION_getCurrentPreset = 8;
        static final int TRANSACTION_getNumberOfBands = 1;
        static final int TRANSACTION_getNumberOfPresets = 10;
        static final int TRANSACTION_getPresetName = 11;
        static final int TRANSACTION_isBandSupported = 13;
        static final int TRANSACTION_isPresetSupported = 12;
        static final int TRANSACTION_registerEqualizerCallback = 14;
        static final int TRANSACTION_setBandLevel = 3;
        static final int TRANSACTION_unregisterEqualizerCallback = 15;
        static final int TRANSACTION_usePreset = 9;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ICarEqualizer asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof ICarEqualizer)) {
                return (ICarEqualizer) iInterfaceQueryLocalInterface;
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
                    int numberOfBands = getNumberOfBands();
                    parcel2.writeNoException();
                    parcel2.writeInt(numberOfBands);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    int[] bandLevelRange = getBandLevelRange(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeIntArray(bandLevelRange);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    setBandLevel(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    int bandLevel = getBandLevel(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(bandLevel);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    int centerFreq = getCenterFreq(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(centerFreq);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    int[] bandFreqRange = getBandFreqRange(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeIntArray(bandFreqRange);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    int band = getBand(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(band);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    int currentPreset = getCurrentPreset();
                    parcel2.writeNoException();
                    parcel2.writeInt(currentPreset);
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    usePreset(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    int numberOfPresets = getNumberOfPresets();
                    parcel2.writeNoException();
                    parcel2.writeInt(numberOfPresets);
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    String presetName = getPresetName(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeString(presetName);
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsPresetSupported = isPresetSupported();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsPresetSupported ? 1 : 0);
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsBandSupported = isBandSupported();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsBandSupported ? 1 : 0);
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    registerEqualizerCallback(parcel.readStrongBinder());
                    parcel2.writeNoException();
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    unregisterEqualizerCallback(parcel.readStrongBinder());
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements ICarEqualizer {
            public static ICarEqualizer sDefaultImpl;
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

            @Override // android.car.media.ICarEqualizer
            public int getNumberOfBands() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getNumberOfBands();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.car.media.ICarEqualizer
            public int[] getBandLevelRange(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getBandLevelRange(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createIntArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.car.media.ICarEqualizer
            public void setBandLevel(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setBandLevel(i, i2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.car.media.ICarEqualizer
            public int getBandLevel(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getBandLevel(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.car.media.ICarEqualizer
            public int getCenterFreq(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCenterFreq(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.car.media.ICarEqualizer
            public int[] getBandFreqRange(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getBandFreqRange(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createIntArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.car.media.ICarEqualizer
            public int getBand(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getBand(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.car.media.ICarEqualizer
            public int getCurrentPreset() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCurrentPreset();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.car.media.ICarEqualizer
            public void usePreset(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().usePreset(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.car.media.ICarEqualizer
            public int getNumberOfPresets() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(10, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getNumberOfPresets();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.car.media.ICarEqualizer
            public String getPresetName(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(11, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPresetName(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.car.media.ICarEqualizer
            public boolean isPresetSupported() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(12, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isPresetSupported();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.car.media.ICarEqualizer
            public boolean isBandSupported() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(13, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isBandSupported();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.car.media.ICarEqualizer
            public void registerEqualizerCallback(IBinder iBinder) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBinder);
                    if (!this.mRemote.transact(14, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerEqualizerCallback(iBinder);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.car.media.ICarEqualizer
            public void unregisterEqualizerCallback(IBinder iBinder) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBinder);
                    if (!this.mRemote.transact(15, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterEqualizerCallback(iBinder);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ICarEqualizer iCarEqualizer) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iCarEqualizer == null) {
                return false;
            }
            Proxy.sDefaultImpl = iCarEqualizer;
            return true;
        }

        public static ICarEqualizer getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
