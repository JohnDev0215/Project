package android.hardware.location;

import android.app.PendingIntent;
import android.hardware.location.IContextHubCallback;
import android.hardware.location.IContextHubClient;
import android.hardware.location.IContextHubClientCallback;
import android.hardware.location.IContextHubTransactionCallback;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public interface IContextHubService extends IInterface {

    public static class Default implements IContextHubService {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.hardware.location.IContextHubService
        public IContextHubClient createClient(int i, IContextHubClientCallback iContextHubClientCallback) throws RemoteException {
            return null;
        }

        @Override // android.hardware.location.IContextHubService
        public IContextHubClient createPendingIntentClient(int i, PendingIntent pendingIntent, long j) throws RemoteException {
            return null;
        }

        @Override // android.hardware.location.IContextHubService
        public void disableNanoApp(int i, IContextHubTransactionCallback iContextHubTransactionCallback, long j) throws RemoteException {
        }

        @Override // android.hardware.location.IContextHubService
        public void enableNanoApp(int i, IContextHubTransactionCallback iContextHubTransactionCallback, long j) throws RemoteException {
        }

        @Override // android.hardware.location.IContextHubService
        public int[] findNanoAppOnHub(int i, NanoAppFilter nanoAppFilter) throws RemoteException {
            return null;
        }

        @Override // android.hardware.location.IContextHubService
        public int[] getContextHubHandles() throws RemoteException {
            return null;
        }

        @Override // android.hardware.location.IContextHubService
        public ContextHubInfo getContextHubInfo(int i) throws RemoteException {
            return null;
        }

        @Override // android.hardware.location.IContextHubService
        public List<ContextHubInfo> getContextHubs() throws RemoteException {
            return null;
        }

        @Override // android.hardware.location.IContextHubService
        public NanoAppInstanceInfo getNanoAppInstanceInfo(int i) throws RemoteException {
            return null;
        }

        @Override // android.hardware.location.IContextHubService
        public int loadNanoApp(int i, NanoApp nanoApp) throws RemoteException {
            return 0;
        }

        @Override // android.hardware.location.IContextHubService
        public void loadNanoAppOnHub(int i, IContextHubTransactionCallback iContextHubTransactionCallback, NanoAppBinary nanoAppBinary) throws RemoteException {
        }

        @Override // android.hardware.location.IContextHubService
        public void queryNanoApps(int i, IContextHubTransactionCallback iContextHubTransactionCallback) throws RemoteException {
        }

        @Override // android.hardware.location.IContextHubService
        public int registerCallback(IContextHubCallback iContextHubCallback) throws RemoteException {
            return 0;
        }

        @Override // android.hardware.location.IContextHubService
        public int sendMessage(int i, int i2, ContextHubMessage contextHubMessage) throws RemoteException {
            return 0;
        }

        @Override // android.hardware.location.IContextHubService
        public int unloadNanoApp(int i) throws RemoteException {
            return 0;
        }

        @Override // android.hardware.location.IContextHubService
        public void unloadNanoAppFromHub(int i, IContextHubTransactionCallback iContextHubTransactionCallback, long j) throws RemoteException {
        }
    }

    IContextHubClient createClient(int i, IContextHubClientCallback iContextHubClientCallback) throws RemoteException;

    IContextHubClient createPendingIntentClient(int i, PendingIntent pendingIntent, long j) throws RemoteException;

    void disableNanoApp(int i, IContextHubTransactionCallback iContextHubTransactionCallback, long j) throws RemoteException;

    void enableNanoApp(int i, IContextHubTransactionCallback iContextHubTransactionCallback, long j) throws RemoteException;

    int[] findNanoAppOnHub(int i, NanoAppFilter nanoAppFilter) throws RemoteException;

    int[] getContextHubHandles() throws RemoteException;

    ContextHubInfo getContextHubInfo(int i) throws RemoteException;

    List<ContextHubInfo> getContextHubs() throws RemoteException;

    NanoAppInstanceInfo getNanoAppInstanceInfo(int i) throws RemoteException;

    int loadNanoApp(int i, NanoApp nanoApp) throws RemoteException;

    void loadNanoAppOnHub(int i, IContextHubTransactionCallback iContextHubTransactionCallback, NanoAppBinary nanoAppBinary) throws RemoteException;

    void queryNanoApps(int i, IContextHubTransactionCallback iContextHubTransactionCallback) throws RemoteException;

    int registerCallback(IContextHubCallback iContextHubCallback) throws RemoteException;

    int sendMessage(int i, int i2, ContextHubMessage contextHubMessage) throws RemoteException;

    int unloadNanoApp(int i) throws RemoteException;

    void unloadNanoAppFromHub(int i, IContextHubTransactionCallback iContextHubTransactionCallback, long j) throws RemoteException;

    public static abstract class Stub extends Binder implements IContextHubService {
        private static final String DESCRIPTOR = "android.hardware.location.IContextHubService";
        static final int TRANSACTION_createClient = 9;
        static final int TRANSACTION_createPendingIntentClient = 10;
        static final int TRANSACTION_disableNanoApp = 15;
        static final int TRANSACTION_enableNanoApp = 14;
        static final int TRANSACTION_findNanoAppOnHub = 7;
        static final int TRANSACTION_getContextHubHandles = 2;
        static final int TRANSACTION_getContextHubInfo = 3;
        static final int TRANSACTION_getContextHubs = 11;
        static final int TRANSACTION_getNanoAppInstanceInfo = 6;
        static final int TRANSACTION_loadNanoApp = 4;
        static final int TRANSACTION_loadNanoAppOnHub = 12;
        static final int TRANSACTION_queryNanoApps = 16;
        static final int TRANSACTION_registerCallback = 1;
        static final int TRANSACTION_sendMessage = 8;
        static final int TRANSACTION_unloadNanoApp = 5;
        static final int TRANSACTION_unloadNanoAppFromHub = 13;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "registerCallback";
                case 2:
                    return "getContextHubHandles";
                case 3:
                    return "getContextHubInfo";
                case 4:
                    return "loadNanoApp";
                case 5:
                    return "unloadNanoApp";
                case 6:
                    return "getNanoAppInstanceInfo";
                case 7:
                    return "findNanoAppOnHub";
                case 8:
                    return "sendMessage";
                case 9:
                    return "createClient";
                case 10:
                    return "createPendingIntentClient";
                case 11:
                    return "getContextHubs";
                case 12:
                    return "loadNanoAppOnHub";
                case 13:
                    return "unloadNanoAppFromHub";
                case 14:
                    return "enableNanoApp";
                case 15:
                    return "disableNanoApp";
                case 16:
                    return "queryNanoApps";
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

        public static IContextHubService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IContextHubService)) {
                return (IContextHubService) iInterfaceQueryLocalInterface;
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
                    int iRegisterCallback = registerCallback(IContextHubCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(iRegisterCallback);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    int[] contextHubHandles = getContextHubHandles();
                    parcel2.writeNoException();
                    parcel2.writeIntArray(contextHubHandles);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    ContextHubInfo contextHubInfo = getContextHubInfo(parcel.readInt());
                    parcel2.writeNoException();
                    if (contextHubInfo != null) {
                        parcel2.writeInt(1);
                        contextHubInfo.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iLoadNanoApp = loadNanoApp(parcel.readInt(), parcel.readInt() != 0 ? NanoApp.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(iLoadNanoApp);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iUnloadNanoApp = unloadNanoApp(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(iUnloadNanoApp);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    NanoAppInstanceInfo nanoAppInstanceInfo = getNanoAppInstanceInfo(parcel.readInt());
                    parcel2.writeNoException();
                    if (nanoAppInstanceInfo != null) {
                        parcel2.writeInt(1);
                        nanoAppInstanceInfo.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    int[] iArrFindNanoAppOnHub = findNanoAppOnHub(parcel.readInt(), parcel.readInt() != 0 ? NanoAppFilter.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeIntArray(iArrFindNanoAppOnHub);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iSendMessage = sendMessage(parcel.readInt(), parcel.readInt(), parcel.readInt() != 0 ? ContextHubMessage.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(iSendMessage);
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    IContextHubClient iContextHubClientCreateClient = createClient(parcel.readInt(), IContextHubClientCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(iContextHubClientCreateClient != null ? iContextHubClientCreateClient.asBinder() : null);
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    IContextHubClient iContextHubClientCreatePendingIntentClient = createPendingIntentClient(parcel.readInt(), parcel.readInt() != 0 ? PendingIntent.CREATOR.createFromParcel(parcel) : null, parcel.readLong());
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(iContextHubClientCreatePendingIntentClient != null ? iContextHubClientCreatePendingIntentClient.asBinder() : null);
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<ContextHubInfo> contextHubs = getContextHubs();
                    parcel2.writeNoException();
                    parcel2.writeTypedList(contextHubs);
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    loadNanoAppOnHub(parcel.readInt(), IContextHubTransactionCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt() != 0 ? NanoAppBinary.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    unloadNanoAppFromHub(parcel.readInt(), IContextHubTransactionCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readLong());
                    parcel2.writeNoException();
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    enableNanoApp(parcel.readInt(), IContextHubTransactionCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readLong());
                    parcel2.writeNoException();
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    disableNanoApp(parcel.readInt(), IContextHubTransactionCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readLong());
                    parcel2.writeNoException();
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    queryNanoApps(parcel.readInt(), IContextHubTransactionCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IContextHubService {
            public static IContextHubService sDefaultImpl;
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

            @Override // android.hardware.location.IContextHubService
            public int registerCallback(IContextHubCallback iContextHubCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iContextHubCallback != null ? iContextHubCallback.asBinder() : null);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().registerCallback(iContextHubCallback);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.location.IContextHubService
            public int[] getContextHubHandles() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getContextHubHandles();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createIntArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.location.IContextHubService
            public ContextHubInfo getContextHubInfo(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getContextHubInfo(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? ContextHubInfo.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.location.IContextHubService
            public int loadNanoApp(int i, NanoApp nanoApp) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (nanoApp != null) {
                        parcelObtain.writeInt(1);
                        nanoApp.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().loadNanoApp(i, nanoApp);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.location.IContextHubService
            public int unloadNanoApp(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().unloadNanoApp(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.location.IContextHubService
            public NanoAppInstanceInfo getNanoAppInstanceInfo(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getNanoAppInstanceInfo(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? NanoAppInstanceInfo.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.location.IContextHubService
            public int[] findNanoAppOnHub(int i, NanoAppFilter nanoAppFilter) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (nanoAppFilter != null) {
                        parcelObtain.writeInt(1);
                        nanoAppFilter.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().findNanoAppOnHub(i, nanoAppFilter);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createIntArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.location.IContextHubService
            public int sendMessage(int i, int i2, ContextHubMessage contextHubMessage) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (contextHubMessage != null) {
                        parcelObtain.writeInt(1);
                        contextHubMessage.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().sendMessage(i, i2, contextHubMessage);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.location.IContextHubService
            public IContextHubClient createClient(int i, IContextHubClientCallback iContextHubClientCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iContextHubClientCallback != null ? iContextHubClientCallback.asBinder() : null);
                    if (!this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().createClient(i, iContextHubClientCallback);
                    }
                    parcelObtain2.readException();
                    return IContextHubClient.Stub.asInterface(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.location.IContextHubService
            public IContextHubClient createPendingIntentClient(int i, PendingIntent pendingIntent, long j) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (pendingIntent != null) {
                        parcelObtain.writeInt(1);
                        pendingIntent.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeLong(j);
                    if (!this.mRemote.transact(10, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().createPendingIntentClient(i, pendingIntent, j);
                    }
                    parcelObtain2.readException();
                    return IContextHubClient.Stub.asInterface(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.location.IContextHubService
            public List<ContextHubInfo> getContextHubs() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(11, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getContextHubs();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createTypedArrayList(ContextHubInfo.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.location.IContextHubService
            public void loadNanoAppOnHub(int i, IContextHubTransactionCallback iContextHubTransactionCallback, NanoAppBinary nanoAppBinary) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iContextHubTransactionCallback != null ? iContextHubTransactionCallback.asBinder() : null);
                    if (nanoAppBinary != null) {
                        parcelObtain.writeInt(1);
                        nanoAppBinary.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(12, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().loadNanoAppOnHub(i, iContextHubTransactionCallback, nanoAppBinary);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.location.IContextHubService
            public void unloadNanoAppFromHub(int i, IContextHubTransactionCallback iContextHubTransactionCallback, long j) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iContextHubTransactionCallback != null ? iContextHubTransactionCallback.asBinder() : null);
                    parcelObtain.writeLong(j);
                    if (!this.mRemote.transact(13, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unloadNanoAppFromHub(i, iContextHubTransactionCallback, j);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.location.IContextHubService
            public void enableNanoApp(int i, IContextHubTransactionCallback iContextHubTransactionCallback, long j) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iContextHubTransactionCallback != null ? iContextHubTransactionCallback.asBinder() : null);
                    parcelObtain.writeLong(j);
                    if (!this.mRemote.transact(14, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().enableNanoApp(i, iContextHubTransactionCallback, j);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.location.IContextHubService
            public void disableNanoApp(int i, IContextHubTransactionCallback iContextHubTransactionCallback, long j) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iContextHubTransactionCallback != null ? iContextHubTransactionCallback.asBinder() : null);
                    parcelObtain.writeLong(j);
                    if (!this.mRemote.transact(15, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().disableNanoApp(i, iContextHubTransactionCallback, j);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.location.IContextHubService
            public void queryNanoApps(int i, IContextHubTransactionCallback iContextHubTransactionCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iContextHubTransactionCallback != null ? iContextHubTransactionCallback.asBinder() : null);
                    if (!this.mRemote.transact(16, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().queryNanoApps(i, iContextHubTransactionCallback);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IContextHubService iContextHubService) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iContextHubService == null) {
                return false;
            }
            Proxy.sDefaultImpl = iContextHubService;
            return true;
        }

        public static IContextHubService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
