package android.os;

import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public interface IDeviceIdleController extends IInterface {

    public static class Default implements IDeviceIdleController {
        @Override // android.os.IDeviceIdleController
        public void addPowerSaveTempWhitelistApp(String str, long j, int i, String str2) throws RemoteException {
        }

        @Override // android.os.IDeviceIdleController
        public long addPowerSaveTempWhitelistAppForMms(String str, int i, String str2) throws RemoteException {
            return 0L;
        }

        @Override // android.os.IDeviceIdleController
        public long addPowerSaveTempWhitelistAppForSms(String str, int i, String str2) throws RemoteException {
            return 0L;
        }

        @Override // android.os.IDeviceIdleController
        public void addPowerSaveWhitelistApp(String str) throws RemoteException {
        }

        @Override // android.os.IDeviceIdleController
        public int addPowerSaveWhitelistApps(List<String> list) throws RemoteException {
            return 0;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.os.IDeviceIdleController
        public void exitIdle(String str) throws RemoteException {
        }

        @Override // android.os.IDeviceIdleController
        public int[] getAppIdTempWhitelist() throws RemoteException {
            return null;
        }

        @Override // android.os.IDeviceIdleController
        public int[] getAppIdUserWhitelist() throws RemoteException {
            return null;
        }

        @Override // android.os.IDeviceIdleController
        public int[] getAppIdWhitelist() throws RemoteException {
            return null;
        }

        @Override // android.os.IDeviceIdleController
        public int[] getAppIdWhitelistExceptIdle() throws RemoteException {
            return null;
        }

        @Override // android.os.IDeviceIdleController
        public String[] getFullPowerWhitelist() throws RemoteException {
            return null;
        }

        @Override // android.os.IDeviceIdleController
        public String[] getFullPowerWhitelistExceptIdle() throws RemoteException {
            return null;
        }

        @Override // android.os.IDeviceIdleController
        public String[] getRemovedSystemPowerWhitelistApps() throws RemoteException {
            return null;
        }

        @Override // android.os.IDeviceIdleController
        public String[] getSystemPowerWhitelist() throws RemoteException {
            return null;
        }

        @Override // android.os.IDeviceIdleController
        public String[] getSystemPowerWhitelistExceptIdle() throws RemoteException {
            return null;
        }

        @Override // android.os.IDeviceIdleController
        public String[] getUserPowerWhitelist() throws RemoteException {
            return null;
        }

        @Override // android.os.IDeviceIdleController
        public boolean isPowerSaveWhitelistApp(String str) throws RemoteException {
            return false;
        }

        @Override // android.os.IDeviceIdleController
        public boolean isPowerSaveWhitelistExceptIdleApp(String str) throws RemoteException {
            return false;
        }

        @Override // android.os.IDeviceIdleController
        public void removePowerSaveWhitelistApp(String str) throws RemoteException {
        }

        @Override // android.os.IDeviceIdleController
        public void removeSystemPowerWhitelistApp(String str) throws RemoteException {
        }

        @Override // android.os.IDeviceIdleController
        public void resetPreIdleTimeoutMode() throws RemoteException {
        }

        @Override // android.os.IDeviceIdleController
        public void restoreSystemPowerWhitelistApp(String str) throws RemoteException {
        }

        @Override // android.os.IDeviceIdleController
        public int setPreIdleTimeoutMode(int i) throws RemoteException {
            return 0;
        }

        @Override // android.os.IDeviceIdleController
        public long whitelistAppTemporarily(String str, int i, String str2) throws RemoteException {
            return 0L;
        }
    }

    void addPowerSaveTempWhitelistApp(String str, long j, int i, String str2) throws RemoteException;

    long addPowerSaveTempWhitelistAppForMms(String str, int i, String str2) throws RemoteException;

    long addPowerSaveTempWhitelistAppForSms(String str, int i, String str2) throws RemoteException;

    void addPowerSaveWhitelistApp(String str) throws RemoteException;

    int addPowerSaveWhitelistApps(List<String> list) throws RemoteException;

    void exitIdle(String str) throws RemoteException;

    int[] getAppIdTempWhitelist() throws RemoteException;

    int[] getAppIdUserWhitelist() throws RemoteException;

    int[] getAppIdWhitelist() throws RemoteException;

    int[] getAppIdWhitelistExceptIdle() throws RemoteException;

    String[] getFullPowerWhitelist() throws RemoteException;

    String[] getFullPowerWhitelistExceptIdle() throws RemoteException;

    String[] getRemovedSystemPowerWhitelistApps() throws RemoteException;

    String[] getSystemPowerWhitelist() throws RemoteException;

    String[] getSystemPowerWhitelistExceptIdle() throws RemoteException;

    String[] getUserPowerWhitelist() throws RemoteException;

    boolean isPowerSaveWhitelistApp(String str) throws RemoteException;

    boolean isPowerSaveWhitelistExceptIdleApp(String str) throws RemoteException;

    void removePowerSaveWhitelistApp(String str) throws RemoteException;

    void removeSystemPowerWhitelistApp(String str) throws RemoteException;

    void resetPreIdleTimeoutMode() throws RemoteException;

    void restoreSystemPowerWhitelistApp(String str) throws RemoteException;

    int setPreIdleTimeoutMode(int i) throws RemoteException;

    long whitelistAppTemporarily(String str, int i, String str2) throws RemoteException;

    public static abstract class Stub extends Binder implements IDeviceIdleController {
        private static final String DESCRIPTOR = "android.os.IDeviceIdleController";
        static final int TRANSACTION_addPowerSaveTempWhitelistApp = 18;
        static final int TRANSACTION_addPowerSaveTempWhitelistAppForMms = 19;
        static final int TRANSACTION_addPowerSaveTempWhitelistAppForSms = 20;
        static final int TRANSACTION_addPowerSaveWhitelistApp = 1;
        static final int TRANSACTION_addPowerSaveWhitelistApps = 2;
        static final int TRANSACTION_exitIdle = 22;
        static final int TRANSACTION_getAppIdTempWhitelist = 15;
        static final int TRANSACTION_getAppIdUserWhitelist = 14;
        static final int TRANSACTION_getAppIdWhitelist = 13;
        static final int TRANSACTION_getAppIdWhitelistExceptIdle = 12;
        static final int TRANSACTION_getFullPowerWhitelist = 11;
        static final int TRANSACTION_getFullPowerWhitelistExceptIdle = 10;
        static final int TRANSACTION_getRemovedSystemPowerWhitelistApps = 6;
        static final int TRANSACTION_getSystemPowerWhitelist = 8;
        static final int TRANSACTION_getSystemPowerWhitelistExceptIdle = 7;
        static final int TRANSACTION_getUserPowerWhitelist = 9;
        static final int TRANSACTION_isPowerSaveWhitelistApp = 17;
        static final int TRANSACTION_isPowerSaveWhitelistExceptIdleApp = 16;
        static final int TRANSACTION_removePowerSaveWhitelistApp = 3;
        static final int TRANSACTION_removeSystemPowerWhitelistApp = 4;
        static final int TRANSACTION_resetPreIdleTimeoutMode = 24;
        static final int TRANSACTION_restoreSystemPowerWhitelistApp = 5;
        static final int TRANSACTION_setPreIdleTimeoutMode = 23;
        static final int TRANSACTION_whitelistAppTemporarily = 21;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "addPowerSaveWhitelistApp";
                case 2:
                    return "addPowerSaveWhitelistApps";
                case 3:
                    return "removePowerSaveWhitelistApp";
                case 4:
                    return "removeSystemPowerWhitelistApp";
                case 5:
                    return "restoreSystemPowerWhitelistApp";
                case 6:
                    return "getRemovedSystemPowerWhitelistApps";
                case 7:
                    return "getSystemPowerWhitelistExceptIdle";
                case 8:
                    return "getSystemPowerWhitelist";
                case 9:
                    return "getUserPowerWhitelist";
                case 10:
                    return "getFullPowerWhitelistExceptIdle";
                case 11:
                    return "getFullPowerWhitelist";
                case 12:
                    return "getAppIdWhitelistExceptIdle";
                case 13:
                    return "getAppIdWhitelist";
                case 14:
                    return "getAppIdUserWhitelist";
                case 15:
                    return "getAppIdTempWhitelist";
                case 16:
                    return "isPowerSaveWhitelistExceptIdleApp";
                case 17:
                    return "isPowerSaveWhitelistApp";
                case 18:
                    return "addPowerSaveTempWhitelistApp";
                case 19:
                    return "addPowerSaveTempWhitelistAppForMms";
                case 20:
                    return "addPowerSaveTempWhitelistAppForSms";
                case 21:
                    return "whitelistAppTemporarily";
                case 22:
                    return "exitIdle";
                case 23:
                    return "setPreIdleTimeoutMode";
                case 24:
                    return "resetPreIdleTimeoutMode";
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

        public static IDeviceIdleController asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IDeviceIdleController)) {
                return (IDeviceIdleController) iInterfaceQueryLocalInterface;
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
                    addPowerSaveWhitelistApp(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iAddPowerSaveWhitelistApps = addPowerSaveWhitelistApps(parcel.createStringArrayList());
                    parcel2.writeNoException();
                    parcel2.writeInt(iAddPowerSaveWhitelistApps);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    removePowerSaveWhitelistApp(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    removeSystemPowerWhitelistApp(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    restoreSystemPowerWhitelistApp(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    String[] removedSystemPowerWhitelistApps = getRemovedSystemPowerWhitelistApps();
                    parcel2.writeNoException();
                    parcel2.writeStringArray(removedSystemPowerWhitelistApps);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    String[] systemPowerWhitelistExceptIdle = getSystemPowerWhitelistExceptIdle();
                    parcel2.writeNoException();
                    parcel2.writeStringArray(systemPowerWhitelistExceptIdle);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    String[] systemPowerWhitelist = getSystemPowerWhitelist();
                    parcel2.writeNoException();
                    parcel2.writeStringArray(systemPowerWhitelist);
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    String[] userPowerWhitelist = getUserPowerWhitelist();
                    parcel2.writeNoException();
                    parcel2.writeStringArray(userPowerWhitelist);
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    String[] fullPowerWhitelistExceptIdle = getFullPowerWhitelistExceptIdle();
                    parcel2.writeNoException();
                    parcel2.writeStringArray(fullPowerWhitelistExceptIdle);
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    String[] fullPowerWhitelist = getFullPowerWhitelist();
                    parcel2.writeNoException();
                    parcel2.writeStringArray(fullPowerWhitelist);
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    int[] appIdWhitelistExceptIdle = getAppIdWhitelistExceptIdle();
                    parcel2.writeNoException();
                    parcel2.writeIntArray(appIdWhitelistExceptIdle);
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    int[] appIdWhitelist = getAppIdWhitelist();
                    parcel2.writeNoException();
                    parcel2.writeIntArray(appIdWhitelist);
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    int[] appIdUserWhitelist = getAppIdUserWhitelist();
                    parcel2.writeNoException();
                    parcel2.writeIntArray(appIdUserWhitelist);
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    int[] appIdTempWhitelist = getAppIdTempWhitelist();
                    parcel2.writeNoException();
                    parcel2.writeIntArray(appIdTempWhitelist);
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsPowerSaveWhitelistExceptIdleApp = isPowerSaveWhitelistExceptIdleApp(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsPowerSaveWhitelistExceptIdleApp ? 1 : 0);
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsPowerSaveWhitelistApp = isPowerSaveWhitelistApp(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsPowerSaveWhitelistApp ? 1 : 0);
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    addPowerSaveTempWhitelistApp(parcel.readString(), parcel.readLong(), parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 19:
                    parcel.enforceInterface(DESCRIPTOR);
                    long jAddPowerSaveTempWhitelistAppForMms = addPowerSaveTempWhitelistAppForMms(parcel.readString(), parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeLong(jAddPowerSaveTempWhitelistAppForMms);
                    return true;
                case 20:
                    parcel.enforceInterface(DESCRIPTOR);
                    long jAddPowerSaveTempWhitelistAppForSms = addPowerSaveTempWhitelistAppForSms(parcel.readString(), parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeLong(jAddPowerSaveTempWhitelistAppForSms);
                    return true;
                case 21:
                    parcel.enforceInterface(DESCRIPTOR);
                    long jWhitelistAppTemporarily = whitelistAppTemporarily(parcel.readString(), parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeLong(jWhitelistAppTemporarily);
                    return true;
                case 22:
                    parcel.enforceInterface(DESCRIPTOR);
                    exitIdle(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 23:
                    parcel.enforceInterface(DESCRIPTOR);
                    int preIdleTimeoutMode = setPreIdleTimeoutMode(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(preIdleTimeoutMode);
                    return true;
                case 24:
                    parcel.enforceInterface(DESCRIPTOR);
                    resetPreIdleTimeoutMode();
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IDeviceIdleController {
            public static IDeviceIdleController sDefaultImpl;
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

            @Override // android.os.IDeviceIdleController
            public void addPowerSaveWhitelistApp(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().addPowerSaveWhitelistApp(str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IDeviceIdleController
            public int addPowerSaveWhitelistApps(List<String> list) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStringList(list);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().addPowerSaveWhitelistApps(list);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IDeviceIdleController
            public void removePowerSaveWhitelistApp(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().removePowerSaveWhitelistApp(str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IDeviceIdleController
            public void removeSystemPowerWhitelistApp(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().removeSystemPowerWhitelistApp(str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IDeviceIdleController
            public void restoreSystemPowerWhitelistApp(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().restoreSystemPowerWhitelistApp(str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IDeviceIdleController
            public String[] getRemovedSystemPowerWhitelistApps() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getRemovedSystemPowerWhitelistApps();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createStringArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IDeviceIdleController
            public String[] getSystemPowerWhitelistExceptIdle() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSystemPowerWhitelistExceptIdle();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createStringArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IDeviceIdleController
            public String[] getSystemPowerWhitelist() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSystemPowerWhitelist();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createStringArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IDeviceIdleController
            public String[] getUserPowerWhitelist() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getUserPowerWhitelist();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createStringArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IDeviceIdleController
            public String[] getFullPowerWhitelistExceptIdle() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(10, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getFullPowerWhitelistExceptIdle();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createStringArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IDeviceIdleController
            public String[] getFullPowerWhitelist() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(11, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getFullPowerWhitelist();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createStringArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IDeviceIdleController
            public int[] getAppIdWhitelistExceptIdle() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(12, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAppIdWhitelistExceptIdle();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createIntArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IDeviceIdleController
            public int[] getAppIdWhitelist() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(13, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAppIdWhitelist();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createIntArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IDeviceIdleController
            public int[] getAppIdUserWhitelist() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(14, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAppIdUserWhitelist();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createIntArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IDeviceIdleController
            public int[] getAppIdTempWhitelist() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(15, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAppIdTempWhitelist();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createIntArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IDeviceIdleController
            public boolean isPowerSaveWhitelistExceptIdleApp(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(16, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isPowerSaveWhitelistExceptIdleApp(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IDeviceIdleController
            public boolean isPowerSaveWhitelistApp(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(17, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isPowerSaveWhitelistApp(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IDeviceIdleController
            public void addPowerSaveTempWhitelistApp(String str, long j, int i, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeLong(j);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(18, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().addPowerSaveTempWhitelistApp(str, j, i, str2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IDeviceIdleController
            public long addPowerSaveTempWhitelistAppForMms(String str, int i, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(19, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().addPowerSaveTempWhitelistAppForMms(str, i, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readLong();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IDeviceIdleController
            public long addPowerSaveTempWhitelistAppForSms(String str, int i, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(20, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().addPowerSaveTempWhitelistAppForSms(str, i, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readLong();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IDeviceIdleController
            public long whitelistAppTemporarily(String str, int i, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(21, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().whitelistAppTemporarily(str, i, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readLong();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IDeviceIdleController
            public void exitIdle(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(22, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().exitIdle(str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IDeviceIdleController
            public int setPreIdleTimeoutMode(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(23, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setPreIdleTimeoutMode(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IDeviceIdleController
            public void resetPreIdleTimeoutMode() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(24, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().resetPreIdleTimeoutMode();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IDeviceIdleController iDeviceIdleController) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iDeviceIdleController == null) {
                return false;
            }
            Proxy.sDefaultImpl = iDeviceIdleController;
            return true;
        }

        public static IDeviceIdleController getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
