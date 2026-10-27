package android.os;

import android.os.IVibratorStateListener;

/* JADX INFO: loaded from: classes2.dex */
public interface IVibratorService extends IInterface {

    public static class Default implements IVibratorService {
        @Override // android.os.IVibratorService
        public int[] areEffectsSupported(int[] iArr) throws RemoteException {
            return null;
        }

        @Override // android.os.IVibratorService
        public boolean[] arePrimitivesSupported(int[] iArr) throws RemoteException {
            return null;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.os.IVibratorService
        public void cancelVibrate(IBinder iBinder) throws RemoteException {
        }

        @Override // android.os.IVibratorService
        public boolean hasAmplitudeControl() throws RemoteException {
            return false;
        }

        @Override // android.os.IVibratorService
        public boolean hasVibrator() throws RemoteException {
            return false;
        }

        @Override // android.os.IVibratorService
        public boolean isVibrating() throws RemoteException {
            return false;
        }

        @Override // android.os.IVibratorService
        public boolean registerVibratorStateListener(IVibratorStateListener iVibratorStateListener) throws RemoteException {
            return false;
        }

        @Override // android.os.IVibratorService
        public boolean setAlwaysOnEffect(int i, String str, int i2, VibrationEffect vibrationEffect, VibrationAttributes vibrationAttributes) throws RemoteException {
            return false;
        }

        @Override // android.os.IVibratorService
        public boolean unregisterVibratorStateListener(IVibratorStateListener iVibratorStateListener) throws RemoteException {
            return false;
        }

        @Override // android.os.IVibratorService
        public void vibrate(int i, String str, VibrationEffect vibrationEffect, VibrationAttributes vibrationAttributes, String str2, IBinder iBinder) throws RemoteException {
        }
    }

    int[] areEffectsSupported(int[] iArr) throws RemoteException;

    boolean[] arePrimitivesSupported(int[] iArr) throws RemoteException;

    void cancelVibrate(IBinder iBinder) throws RemoteException;

    boolean hasAmplitudeControl() throws RemoteException;

    boolean hasVibrator() throws RemoteException;

    boolean isVibrating() throws RemoteException;

    boolean registerVibratorStateListener(IVibratorStateListener iVibratorStateListener) throws RemoteException;

    boolean setAlwaysOnEffect(int i, String str, int i2, VibrationEffect vibrationEffect, VibrationAttributes vibrationAttributes) throws RemoteException;

    boolean unregisterVibratorStateListener(IVibratorStateListener iVibratorStateListener) throws RemoteException;

    void vibrate(int i, String str, VibrationEffect vibrationEffect, VibrationAttributes vibrationAttributes, String str2, IBinder iBinder) throws RemoteException;

    public static abstract class Stub extends Binder implements IVibratorService {
        private static final String DESCRIPTOR = "android.os.IVibratorService";
        static final int TRANSACTION_areEffectsSupported = 6;
        static final int TRANSACTION_arePrimitivesSupported = 7;
        static final int TRANSACTION_cancelVibrate = 10;
        static final int TRANSACTION_hasAmplitudeControl = 5;
        static final int TRANSACTION_hasVibrator = 1;
        static final int TRANSACTION_isVibrating = 2;
        static final int TRANSACTION_registerVibratorStateListener = 3;
        static final int TRANSACTION_setAlwaysOnEffect = 8;
        static final int TRANSACTION_unregisterVibratorStateListener = 4;
        static final int TRANSACTION_vibrate = 9;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "hasVibrator";
                case 2:
                    return "isVibrating";
                case 3:
                    return "registerVibratorStateListener";
                case 4:
                    return "unregisterVibratorStateListener";
                case 5:
                    return "hasAmplitudeControl";
                case 6:
                    return "areEffectsSupported";
                case 7:
                    return "arePrimitivesSupported";
                case 8:
                    return "setAlwaysOnEffect";
                case 9:
                    return "vibrate";
                case 10:
                    return "cancelVibrate";
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

        public static IVibratorService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IVibratorService)) {
                return (IVibratorService) iInterfaceQueryLocalInterface;
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
                    boolean zHasVibrator = hasVibrator();
                    parcel2.writeNoException();
                    parcel2.writeInt(zHasVibrator ? 1 : 0);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsVibrating = isVibrating();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsVibrating ? 1 : 0);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zRegisterVibratorStateListener = registerVibratorStateListener(IVibratorStateListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(zRegisterVibratorStateListener ? 1 : 0);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zUnregisterVibratorStateListener = unregisterVibratorStateListener(IVibratorStateListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(zUnregisterVibratorStateListener ? 1 : 0);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zHasAmplitudeControl = hasAmplitudeControl();
                    parcel2.writeNoException();
                    parcel2.writeInt(zHasAmplitudeControl ? 1 : 0);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    int[] iArrAreEffectsSupported = areEffectsSupported(parcel.createIntArray());
                    parcel2.writeNoException();
                    parcel2.writeIntArray(iArrAreEffectsSupported);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean[] zArrArePrimitivesSupported = arePrimitivesSupported(parcel.createIntArray());
                    parcel2.writeNoException();
                    parcel2.writeBooleanArray(zArrArePrimitivesSupported);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean alwaysOnEffect = setAlwaysOnEffect(parcel.readInt(), parcel.readString(), parcel.readInt(), parcel.readInt() != 0 ? VibrationEffect.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? VibrationAttributes.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(alwaysOnEffect ? 1 : 0);
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    vibrate(parcel.readInt(), parcel.readString(), parcel.readInt() != 0 ? VibrationEffect.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? VibrationAttributes.CREATOR.createFromParcel(parcel) : null, parcel.readString(), parcel.readStrongBinder());
                    parcel2.writeNoException();
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    cancelVibrate(parcel.readStrongBinder());
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IVibratorService {
            public static IVibratorService sDefaultImpl;
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

            @Override // android.os.IVibratorService
            public boolean hasVibrator() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().hasVibrator();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IVibratorService
            public boolean isVibrating() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isVibrating();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IVibratorService
            public boolean registerVibratorStateListener(IVibratorStateListener iVibratorStateListener) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iVibratorStateListener != null ? iVibratorStateListener.asBinder() : null);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().registerVibratorStateListener(iVibratorStateListener);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IVibratorService
            public boolean unregisterVibratorStateListener(IVibratorStateListener iVibratorStateListener) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iVibratorStateListener != null ? iVibratorStateListener.asBinder() : null);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().unregisterVibratorStateListener(iVibratorStateListener);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IVibratorService
            public boolean hasAmplitudeControl() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().hasAmplitudeControl();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IVibratorService
            public int[] areEffectsSupported(int[] iArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeIntArray(iArr);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().areEffectsSupported(iArr);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createIntArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IVibratorService
            public boolean[] arePrimitivesSupported(int[] iArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeIntArray(iArr);
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().arePrimitivesSupported(iArr);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createBooleanArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IVibratorService
            public boolean setAlwaysOnEffect(int i, String str, int i2, VibrationEffect vibrationEffect, VibrationAttributes vibrationAttributes) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i2);
                    if (vibrationEffect != null) {
                        parcelObtain.writeInt(1);
                        vibrationEffect.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (vibrationAttributes != null) {
                        parcelObtain.writeInt(1);
                        vibrationAttributes.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setAlwaysOnEffect(i, str, i2, vibrationEffect, vibrationAttributes);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IVibratorService
            public void vibrate(int i, String str, VibrationEffect vibrationEffect, VibrationAttributes vibrationAttributes, String str2, IBinder iBinder) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    if (vibrationEffect != null) {
                        parcelObtain.writeInt(1);
                        vibrationEffect.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (vibrationAttributes != null) {
                        parcelObtain.writeInt(1);
                        vibrationAttributes.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str2);
                    parcelObtain.writeStrongBinder(iBinder);
                    if (!this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().vibrate(i, str, vibrationEffect, vibrationAttributes, str2, iBinder);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IVibratorService
            public void cancelVibrate(IBinder iBinder) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBinder);
                    if (!this.mRemote.transact(10, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().cancelVibrate(iBinder);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IVibratorService iVibratorService) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iVibratorService == null) {
                return false;
            }
            Proxy.sDefaultImpl = iVibratorService;
            return true;
        }

        public static IVibratorService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
