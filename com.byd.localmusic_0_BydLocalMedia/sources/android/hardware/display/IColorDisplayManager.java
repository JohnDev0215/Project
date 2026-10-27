package android.hardware.display;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public interface IColorDisplayManager extends IInterface {

    public static class Default implements IColorDisplayManager {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.hardware.display.IColorDisplayManager
        public int getColorMode() throws RemoteException {
            return 0;
        }

        @Override // android.hardware.display.IColorDisplayManager
        public int getNightDisplayAutoMode() throws RemoteException {
            return 0;
        }

        @Override // android.hardware.display.IColorDisplayManager
        public int getNightDisplayAutoModeRaw() throws RemoteException {
            return 0;
        }

        @Override // android.hardware.display.IColorDisplayManager
        public int getNightDisplayColorTemperature() throws RemoteException {
            return 0;
        }

        @Override // android.hardware.display.IColorDisplayManager
        public Time getNightDisplayCustomEndTime() throws RemoteException {
            return null;
        }

        @Override // android.hardware.display.IColorDisplayManager
        public Time getNightDisplayCustomStartTime() throws RemoteException {
            return null;
        }

        @Override // android.hardware.display.IColorDisplayManager
        public int getTransformCapabilities() throws RemoteException {
            return 0;
        }

        @Override // android.hardware.display.IColorDisplayManager
        public boolean isDeviceColorManaged() throws RemoteException {
            return false;
        }

        @Override // android.hardware.display.IColorDisplayManager
        public boolean isDisplayWhiteBalanceEnabled() throws RemoteException {
            return false;
        }

        @Override // android.hardware.display.IColorDisplayManager
        public boolean isNightDisplayActivated() throws RemoteException {
            return false;
        }

        @Override // android.hardware.display.IColorDisplayManager
        public boolean isSaturationActivated() throws RemoteException {
            return false;
        }

        @Override // android.hardware.display.IColorDisplayManager
        public boolean setAppSaturationLevel(String str, int i) throws RemoteException {
            return false;
        }

        @Override // android.hardware.display.IColorDisplayManager
        public void setColorMode(int i) throws RemoteException {
        }

        @Override // android.hardware.display.IColorDisplayManager
        public boolean setDisplayWhiteBalanceEnabled(boolean z) throws RemoteException {
            return false;
        }

        @Override // android.hardware.display.IColorDisplayManager
        public boolean setNightDisplayActivated(boolean z) throws RemoteException {
            return false;
        }

        @Override // android.hardware.display.IColorDisplayManager
        public boolean setNightDisplayAutoMode(int i) throws RemoteException {
            return false;
        }

        @Override // android.hardware.display.IColorDisplayManager
        public boolean setNightDisplayColorTemperature(int i) throws RemoteException {
            return false;
        }

        @Override // android.hardware.display.IColorDisplayManager
        public boolean setNightDisplayCustomEndTime(Time time) throws RemoteException {
            return false;
        }

        @Override // android.hardware.display.IColorDisplayManager
        public boolean setNightDisplayCustomStartTime(Time time) throws RemoteException {
            return false;
        }

        @Override // android.hardware.display.IColorDisplayManager
        public boolean setSaturationLevel(int i) throws RemoteException {
            return false;
        }
    }

    int getColorMode() throws RemoteException;

    int getNightDisplayAutoMode() throws RemoteException;

    int getNightDisplayAutoModeRaw() throws RemoteException;

    int getNightDisplayColorTemperature() throws RemoteException;

    Time getNightDisplayCustomEndTime() throws RemoteException;

    Time getNightDisplayCustomStartTime() throws RemoteException;

    int getTransformCapabilities() throws RemoteException;

    boolean isDeviceColorManaged() throws RemoteException;

    boolean isDisplayWhiteBalanceEnabled() throws RemoteException;

    boolean isNightDisplayActivated() throws RemoteException;

    boolean isSaturationActivated() throws RemoteException;

    boolean setAppSaturationLevel(String str, int i) throws RemoteException;

    void setColorMode(int i) throws RemoteException;

    boolean setDisplayWhiteBalanceEnabled(boolean z) throws RemoteException;

    boolean setNightDisplayActivated(boolean z) throws RemoteException;

    boolean setNightDisplayAutoMode(int i) throws RemoteException;

    boolean setNightDisplayColorTemperature(int i) throws RemoteException;

    boolean setNightDisplayCustomEndTime(Time time) throws RemoteException;

    boolean setNightDisplayCustomStartTime(Time time) throws RemoteException;

    boolean setSaturationLevel(int i) throws RemoteException;

    public static abstract class Stub extends Binder implements IColorDisplayManager {
        private static final String DESCRIPTOR = "android.hardware.display.IColorDisplayManager";
        static final int TRANSACTION_getColorMode = 17;
        static final int TRANSACTION_getNightDisplayAutoMode = 10;
        static final int TRANSACTION_getNightDisplayAutoModeRaw = 11;
        static final int TRANSACTION_getNightDisplayColorTemperature = 8;
        static final int TRANSACTION_getNightDisplayCustomEndTime = 15;
        static final int TRANSACTION_getNightDisplayCustomStartTime = 13;
        static final int TRANSACTION_getTransformCapabilities = 5;
        static final int TRANSACTION_isDeviceColorManaged = 1;
        static final int TRANSACTION_isDisplayWhiteBalanceEnabled = 19;
        static final int TRANSACTION_isNightDisplayActivated = 6;
        static final int TRANSACTION_isSaturationActivated = 4;
        static final int TRANSACTION_setAppSaturationLevel = 3;
        static final int TRANSACTION_setColorMode = 18;
        static final int TRANSACTION_setDisplayWhiteBalanceEnabled = 20;
        static final int TRANSACTION_setNightDisplayActivated = 7;
        static final int TRANSACTION_setNightDisplayAutoMode = 12;
        static final int TRANSACTION_setNightDisplayColorTemperature = 9;
        static final int TRANSACTION_setNightDisplayCustomEndTime = 16;
        static final int TRANSACTION_setNightDisplayCustomStartTime = 14;
        static final int TRANSACTION_setSaturationLevel = 2;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "isDeviceColorManaged";
                case 2:
                    return "setSaturationLevel";
                case 3:
                    return "setAppSaturationLevel";
                case 4:
                    return "isSaturationActivated";
                case 5:
                    return "getTransformCapabilities";
                case 6:
                    return "isNightDisplayActivated";
                case 7:
                    return "setNightDisplayActivated";
                case 8:
                    return "getNightDisplayColorTemperature";
                case 9:
                    return "setNightDisplayColorTemperature";
                case 10:
                    return "getNightDisplayAutoMode";
                case 11:
                    return "getNightDisplayAutoModeRaw";
                case 12:
                    return "setNightDisplayAutoMode";
                case 13:
                    return "getNightDisplayCustomStartTime";
                case 14:
                    return "setNightDisplayCustomStartTime";
                case 15:
                    return "getNightDisplayCustomEndTime";
                case 16:
                    return "setNightDisplayCustomEndTime";
                case 17:
                    return "getColorMode";
                case 18:
                    return "setColorMode";
                case 19:
                    return "isDisplayWhiteBalanceEnabled";
                case 20:
                    return "setDisplayWhiteBalanceEnabled";
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

        public static IColorDisplayManager asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IColorDisplayManager)) {
                return (IColorDisplayManager) iInterfaceQueryLocalInterface;
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
                    boolean zIsDeviceColorManaged = isDeviceColorManaged();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsDeviceColorManaged ? 1 : 0);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean saturationLevel = setSaturationLevel(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(saturationLevel ? 1 : 0);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean appSaturationLevel = setAppSaturationLevel(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(appSaturationLevel ? 1 : 0);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsSaturationActivated = isSaturationActivated();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsSaturationActivated ? 1 : 0);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    int transformCapabilities = getTransformCapabilities();
                    parcel2.writeNoException();
                    parcel2.writeInt(transformCapabilities);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsNightDisplayActivated = isNightDisplayActivated();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsNightDisplayActivated ? 1 : 0);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean nightDisplayActivated = setNightDisplayActivated(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(nightDisplayActivated ? 1 : 0);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    int nightDisplayColorTemperature = getNightDisplayColorTemperature();
                    parcel2.writeNoException();
                    parcel2.writeInt(nightDisplayColorTemperature);
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean nightDisplayColorTemperature2 = setNightDisplayColorTemperature(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(nightDisplayColorTemperature2 ? 1 : 0);
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    int nightDisplayAutoMode = getNightDisplayAutoMode();
                    parcel2.writeNoException();
                    parcel2.writeInt(nightDisplayAutoMode);
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    int nightDisplayAutoModeRaw = getNightDisplayAutoModeRaw();
                    parcel2.writeNoException();
                    parcel2.writeInt(nightDisplayAutoModeRaw);
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean nightDisplayAutoMode2 = setNightDisplayAutoMode(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(nightDisplayAutoMode2 ? 1 : 0);
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    Time nightDisplayCustomStartTime = getNightDisplayCustomStartTime();
                    parcel2.writeNoException();
                    if (nightDisplayCustomStartTime != null) {
                        parcel2.writeInt(1);
                        nightDisplayCustomStartTime.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean nightDisplayCustomStartTime2 = setNightDisplayCustomStartTime(parcel.readInt() != 0 ? Time.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(nightDisplayCustomStartTime2 ? 1 : 0);
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    Time nightDisplayCustomEndTime = getNightDisplayCustomEndTime();
                    parcel2.writeNoException();
                    if (nightDisplayCustomEndTime != null) {
                        parcel2.writeInt(1);
                        nightDisplayCustomEndTime.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean nightDisplayCustomEndTime2 = setNightDisplayCustomEndTime(parcel.readInt() != 0 ? Time.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(nightDisplayCustomEndTime2 ? 1 : 0);
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    int colorMode = getColorMode();
                    parcel2.writeNoException();
                    parcel2.writeInt(colorMode);
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    setColorMode(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 19:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsDisplayWhiteBalanceEnabled = isDisplayWhiteBalanceEnabled();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsDisplayWhiteBalanceEnabled ? 1 : 0);
                    return true;
                case 20:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean displayWhiteBalanceEnabled = setDisplayWhiteBalanceEnabled(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(displayWhiteBalanceEnabled ? 1 : 0);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IColorDisplayManager {
            public static IColorDisplayManager sDefaultImpl;
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

            @Override // android.hardware.display.IColorDisplayManager
            public boolean isDeviceColorManaged() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isDeviceColorManaged();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.display.IColorDisplayManager
            public boolean setSaturationLevel(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setSaturationLevel(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.display.IColorDisplayManager
            public boolean setAppSaturationLevel(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setAppSaturationLevel(str, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.display.IColorDisplayManager
            public boolean isSaturationActivated() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isSaturationActivated();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.display.IColorDisplayManager
            public int getTransformCapabilities() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getTransformCapabilities();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.display.IColorDisplayManager
            public boolean isNightDisplayActivated() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isNightDisplayActivated();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.display.IColorDisplayManager
            public boolean setNightDisplayActivated(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setNightDisplayActivated(z);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.display.IColorDisplayManager
            public int getNightDisplayColorTemperature() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getNightDisplayColorTemperature();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.display.IColorDisplayManager
            public boolean setNightDisplayColorTemperature(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setNightDisplayColorTemperature(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.display.IColorDisplayManager
            public int getNightDisplayAutoMode() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(10, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getNightDisplayAutoMode();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.display.IColorDisplayManager
            public int getNightDisplayAutoModeRaw() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(11, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getNightDisplayAutoModeRaw();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.display.IColorDisplayManager
            public boolean setNightDisplayAutoMode(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(12, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setNightDisplayAutoMode(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.display.IColorDisplayManager
            public Time getNightDisplayCustomStartTime() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(13, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getNightDisplayCustomStartTime();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? Time.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.display.IColorDisplayManager
            public boolean setNightDisplayCustomStartTime(Time time) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (time != null) {
                        parcelObtain.writeInt(1);
                        time.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(14, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setNightDisplayCustomStartTime(time);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.display.IColorDisplayManager
            public Time getNightDisplayCustomEndTime() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(15, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getNightDisplayCustomEndTime();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? Time.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.display.IColorDisplayManager
            public boolean setNightDisplayCustomEndTime(Time time) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (time != null) {
                        parcelObtain.writeInt(1);
                        time.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(16, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setNightDisplayCustomEndTime(time);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.display.IColorDisplayManager
            public int getColorMode() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(17, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getColorMode();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.display.IColorDisplayManager
            public void setColorMode(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(18, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setColorMode(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.display.IColorDisplayManager
            public boolean isDisplayWhiteBalanceEnabled() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(19, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isDisplayWhiteBalanceEnabled();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.display.IColorDisplayManager
            public boolean setDisplayWhiteBalanceEnabled(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(20, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setDisplayWhiteBalanceEnabled(z);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IColorDisplayManager iColorDisplayManager) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iColorDisplayManager == null) {
                return false;
            }
            Proxy.sDefaultImpl = iColorDisplayManager;
            return true;
        }

        public static IColorDisplayManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
