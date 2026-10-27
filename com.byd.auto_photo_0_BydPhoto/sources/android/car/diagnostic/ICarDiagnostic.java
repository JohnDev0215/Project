package android.car.diagnostic;

import android.car.diagnostic.ICarDiagnosticEventListener;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* JADX INFO: loaded from: classes.dex */
public interface ICarDiagnostic extends IInterface {

    public static class Default implements ICarDiagnostic {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.car.diagnostic.ICarDiagnostic
        public boolean clearFreezeFrames(long[] jArr) {
            return false;
        }

        @Override // android.car.diagnostic.ICarDiagnostic
        public CarDiagnosticEvent getFreezeFrame(long j) {
            return null;
        }

        @Override // android.car.diagnostic.ICarDiagnostic
        public long[] getFreezeFrameTimestamps() {
            return null;
        }

        @Override // android.car.diagnostic.ICarDiagnostic
        public CarDiagnosticEvent getLatestLiveFrame() {
            return null;
        }

        @Override // android.car.diagnostic.ICarDiagnostic
        public boolean isClearFreezeFramesSupported() {
            return false;
        }

        @Override // android.car.diagnostic.ICarDiagnostic
        public boolean isFreezeFrameNotificationSupported() {
            return false;
        }

        @Override // android.car.diagnostic.ICarDiagnostic
        public boolean isGetFreezeFrameSupported() {
            return false;
        }

        @Override // android.car.diagnostic.ICarDiagnostic
        public boolean isLiveFrameSupported() {
            return false;
        }

        @Override // android.car.diagnostic.ICarDiagnostic
        public boolean isSelectiveClearFreezeFramesSupported() {
            return false;
        }

        @Override // android.car.diagnostic.ICarDiagnostic
        public boolean registerOrUpdateDiagnosticListener(int i, int i2, ICarDiagnosticEventListener iCarDiagnosticEventListener) {
            return false;
        }

        @Override // android.car.diagnostic.ICarDiagnostic
        public void unregisterDiagnosticListener(int i, ICarDiagnosticEventListener iCarDiagnosticEventListener) {
        }
    }

    public static abstract class Stub extends Binder implements ICarDiagnostic {
        private static final String DESCRIPTOR = "android.car.diagnostic.ICarDiagnostic";
        static final int TRANSACTION_clearFreezeFrames = 6;
        static final int TRANSACTION_getFreezeFrame = 5;
        static final int TRANSACTION_getFreezeFrameTimestamps = 4;
        static final int TRANSACTION_getLatestLiveFrame = 3;
        static final int TRANSACTION_isClearFreezeFramesSupported = 11;
        static final int TRANSACTION_isFreezeFrameNotificationSupported = 9;
        static final int TRANSACTION_isGetFreezeFrameSupported = 10;
        static final int TRANSACTION_isLiveFrameSupported = 8;
        static final int TRANSACTION_isSelectiveClearFreezeFramesSupported = 12;
        static final int TRANSACTION_registerOrUpdateDiagnosticListener = 2;
        static final int TRANSACTION_unregisterDiagnosticListener = 7;

        private static class Proxy implements ICarDiagnostic {
            public static ICarDiagnostic sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // android.car.diagnostic.ICarDiagnostic
            public boolean clearFreezeFrames(long[] jArr) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeLongArray(jArr);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().clearFreezeFrames(jArr);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.car.diagnostic.ICarDiagnostic
            public CarDiagnosticEvent getFreezeFrame(long j) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeLong(j);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getFreezeFrame(j);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? CarDiagnosticEvent.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.car.diagnostic.ICarDiagnostic
            public long[] getFreezeFrameTimestamps() {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getFreezeFrameTimestamps();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createLongArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            @Override // android.car.diagnostic.ICarDiagnostic
            public CarDiagnosticEvent getLatestLiveFrame() {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLatestLiveFrame();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? CarDiagnosticEvent.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.car.diagnostic.ICarDiagnostic
            public boolean isClearFreezeFramesSupported() {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(11, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isClearFreezeFramesSupported();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.car.diagnostic.ICarDiagnostic
            public boolean isFreezeFrameNotificationSupported() {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isFreezeFrameNotificationSupported();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.car.diagnostic.ICarDiagnostic
            public boolean isGetFreezeFrameSupported() {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(10, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isGetFreezeFrameSupported();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.car.diagnostic.ICarDiagnostic
            public boolean isLiveFrameSupported() {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isLiveFrameSupported();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.car.diagnostic.ICarDiagnostic
            public boolean isSelectiveClearFreezeFramesSupported() {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(12, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isSelectiveClearFreezeFramesSupported();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.car.diagnostic.ICarDiagnostic
            public boolean registerOrUpdateDiagnosticListener(int i, int i2, ICarDiagnosticEventListener iCarDiagnosticEventListener) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeStrongBinder(iCarDiagnosticEventListener != null ? iCarDiagnosticEventListener.asBinder() : null);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().registerOrUpdateDiagnosticListener(i, i2, iCarDiagnosticEventListener);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.car.diagnostic.ICarDiagnostic
            public void unregisterDiagnosticListener(int i, ICarDiagnosticEventListener iCarDiagnosticEventListener) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iCarDiagnosticEventListener != null ? iCarDiagnosticEventListener.asBinder() : null);
                    if (this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                        parcelObtain2.readException();
                    } else {
                        Stub.getDefaultImpl().unregisterDiagnosticListener(i, iCarDiagnosticEventListener);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ICarDiagnostic asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof ICarDiagnostic)) ? new Proxy(iBinder) : (ICarDiagnostic) iInterfaceQueryLocalInterface;
        }

        public static ICarDiagnostic getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(ICarDiagnostic iCarDiagnostic) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iCarDiagnostic == null) {
                return false;
            }
            Proxy.sDefaultImpl = iCarDiagnostic;
            return true;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            switch (i) {
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zRegisterOrUpdateDiagnosticListener = registerOrUpdateDiagnosticListener(parcel.readInt(), parcel.readInt(), ICarDiagnosticEventListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(zRegisterOrUpdateDiagnosticListener ? 1 : 0);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    CarDiagnosticEvent latestLiveFrame = getLatestLiveFrame();
                    parcel2.writeNoException();
                    if (latestLiveFrame != null) {
                        parcel2.writeInt(1);
                        latestLiveFrame.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    long[] freezeFrameTimestamps = getFreezeFrameTimestamps();
                    parcel2.writeNoException();
                    parcel2.writeLongArray(freezeFrameTimestamps);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    CarDiagnosticEvent freezeFrame = getFreezeFrame(parcel.readLong());
                    parcel2.writeNoException();
                    if (freezeFrame != null) {
                        parcel2.writeInt(1);
                        freezeFrame.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zClearFreezeFrames = clearFreezeFrames(parcel.createLongArray());
                    parcel2.writeNoException();
                    parcel2.writeInt(zClearFreezeFrames ? 1 : 0);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    unregisterDiagnosticListener(parcel.readInt(), ICarDiagnosticEventListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsLiveFrameSupported = isLiveFrameSupported();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsLiveFrameSupported ? 1 : 0);
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsFreezeFrameNotificationSupported = isFreezeFrameNotificationSupported();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsFreezeFrameNotificationSupported ? 1 : 0);
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsGetFreezeFrameSupported = isGetFreezeFrameSupported();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsGetFreezeFrameSupported ? 1 : 0);
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsClearFreezeFramesSupported = isClearFreezeFramesSupported();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsClearFreezeFramesSupported ? 1 : 0);
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsSelectiveClearFreezeFramesSupported = isSelectiveClearFreezeFramesSupported();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsSelectiveClearFreezeFramesSupported ? 1 : 0);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    boolean clearFreezeFrames(long[] jArr);

    CarDiagnosticEvent getFreezeFrame(long j);

    long[] getFreezeFrameTimestamps();

    CarDiagnosticEvent getLatestLiveFrame();

    boolean isClearFreezeFramesSupported();

    boolean isFreezeFrameNotificationSupported();

    boolean isGetFreezeFrameSupported();

    boolean isLiveFrameSupported();

    boolean isSelectiveClearFreezeFramesSupported();

    boolean registerOrUpdateDiagnosticListener(int i, int i2, ICarDiagnosticEventListener iCarDiagnosticEventListener);

    void unregisterDiagnosticListener(int i, ICarDiagnosticEventListener iCarDiagnosticEventListener);
}
