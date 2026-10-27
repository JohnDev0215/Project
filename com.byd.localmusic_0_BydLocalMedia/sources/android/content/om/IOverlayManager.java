package android.content.om;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public interface IOverlayManager extends IInterface {

    public static class Default implements IOverlayManager {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.content.om.IOverlayManager
        public Map getAllOverlays(int i) throws RemoteException {
            return null;
        }

        @Override // android.content.om.IOverlayManager
        public String[] getDefaultOverlayPackages() throws RemoteException {
            return null;
        }

        @Override // android.content.om.IOverlayManager
        public OverlayInfo getOverlayInfo(String str, int i) throws RemoteException {
            return null;
        }

        @Override // android.content.om.IOverlayManager
        public List getOverlayInfosForTarget(String str, int i) throws RemoteException {
            return null;
        }

        @Override // android.content.om.IOverlayManager
        public void invalidateCachesForOverlay(String str, int i) throws RemoteException {
        }

        @Override // android.content.om.IOverlayManager
        public boolean setEnabled(String str, boolean z, int i) throws RemoteException {
            return false;
        }

        @Override // android.content.om.IOverlayManager
        public boolean setEnabledExclusive(String str, boolean z, int i) throws RemoteException {
            return false;
        }

        @Override // android.content.om.IOverlayManager
        public boolean setEnabledExclusiveInCategory(String str, int i) throws RemoteException {
            return false;
        }

        @Override // android.content.om.IOverlayManager
        public boolean setHighestPriority(String str, int i) throws RemoteException {
            return false;
        }

        @Override // android.content.om.IOverlayManager
        public boolean setLowestPriority(String str, int i) throws RemoteException {
            return false;
        }

        @Override // android.content.om.IOverlayManager
        public boolean setPriority(String str, String str2, int i) throws RemoteException {
            return false;
        }
    }

    Map getAllOverlays(int i) throws RemoteException;

    String[] getDefaultOverlayPackages() throws RemoteException;

    OverlayInfo getOverlayInfo(String str, int i) throws RemoteException;

    List getOverlayInfosForTarget(String str, int i) throws RemoteException;

    void invalidateCachesForOverlay(String str, int i) throws RemoteException;

    boolean setEnabled(String str, boolean z, int i) throws RemoteException;

    boolean setEnabledExclusive(String str, boolean z, int i) throws RemoteException;

    boolean setEnabledExclusiveInCategory(String str, int i) throws RemoteException;

    boolean setHighestPriority(String str, int i) throws RemoteException;

    boolean setLowestPriority(String str, int i) throws RemoteException;

    boolean setPriority(String str, String str2, int i) throws RemoteException;

    public static abstract class Stub extends Binder implements IOverlayManager {
        private static final String DESCRIPTOR = "android.content.om.IOverlayManager";
        static final int TRANSACTION_getAllOverlays = 1;
        static final int TRANSACTION_getDefaultOverlayPackages = 10;
        static final int TRANSACTION_getOverlayInfo = 3;
        static final int TRANSACTION_getOverlayInfosForTarget = 2;
        static final int TRANSACTION_invalidateCachesForOverlay = 11;
        static final int TRANSACTION_setEnabled = 4;
        static final int TRANSACTION_setEnabledExclusive = 5;
        static final int TRANSACTION_setEnabledExclusiveInCategory = 6;
        static final int TRANSACTION_setHighestPriority = 8;
        static final int TRANSACTION_setLowestPriority = 9;
        static final int TRANSACTION_setPriority = 7;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "getAllOverlays";
                case 2:
                    return "getOverlayInfosForTarget";
                case 3:
                    return "getOverlayInfo";
                case 4:
                    return "setEnabled";
                case 5:
                    return "setEnabledExclusive";
                case 6:
                    return "setEnabledExclusiveInCategory";
                case 7:
                    return "setPriority";
                case 8:
                    return "setHighestPriority";
                case 9:
                    return "setLowestPriority";
                case 10:
                    return "getDefaultOverlayPackages";
                case 11:
                    return "invalidateCachesForOverlay";
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

        public static IOverlayManager asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IOverlayManager)) {
                return (IOverlayManager) iInterfaceQueryLocalInterface;
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
                    Map allOverlays = getAllOverlays(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeMap(allOverlays);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    List overlayInfosForTarget = getOverlayInfosForTarget(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeList(overlayInfosForTarget);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    OverlayInfo overlayInfo = getOverlayInfo(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    if (overlayInfo != null) {
                        parcel2.writeInt(1);
                        overlayInfo.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean enabled = setEnabled(parcel.readString(), parcel.readInt() != 0, parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(enabled ? 1 : 0);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean enabledExclusive = setEnabledExclusive(parcel.readString(), parcel.readInt() != 0, parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(enabledExclusive ? 1 : 0);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean enabledExclusiveInCategory = setEnabledExclusiveInCategory(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(enabledExclusiveInCategory ? 1 : 0);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean priority = setPriority(parcel.readString(), parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(priority ? 1 : 0);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean highestPriority = setHighestPriority(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(highestPriority ? 1 : 0);
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean lowestPriority = setLowestPriority(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(lowestPriority ? 1 : 0);
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    String[] defaultOverlayPackages = getDefaultOverlayPackages();
                    parcel2.writeNoException();
                    parcel2.writeStringArray(defaultOverlayPackages);
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    invalidateCachesForOverlay(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IOverlayManager {
            public static IOverlayManager sDefaultImpl;
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

            @Override // android.content.om.IOverlayManager
            public Map getAllOverlays(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAllOverlays(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readHashMap(getClass().getClassLoader());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.om.IOverlayManager
            public List getOverlayInfosForTarget(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getOverlayInfosForTarget(str, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readArrayList(getClass().getClassLoader());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.om.IOverlayManager
            public OverlayInfo getOverlayInfo(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getOverlayInfo(str, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? OverlayInfo.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.om.IOverlayManager
            public boolean setEnabled(String str, boolean z, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setEnabled(str, z, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.om.IOverlayManager
            public boolean setEnabledExclusive(String str, boolean z, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setEnabledExclusive(str, z, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.om.IOverlayManager
            public boolean setEnabledExclusiveInCategory(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setEnabledExclusiveInCategory(str, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.om.IOverlayManager
            public boolean setPriority(String str, String str2, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setPriority(str, str2, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.om.IOverlayManager
            public boolean setHighestPriority(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setHighestPriority(str, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.om.IOverlayManager
            public boolean setLowestPriority(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setLowestPriority(str, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.om.IOverlayManager
            public String[] getDefaultOverlayPackages() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(10, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDefaultOverlayPackages();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createStringArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.om.IOverlayManager
            public void invalidateCachesForOverlay(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(11, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().invalidateCachesForOverlay(str, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IOverlayManager iOverlayManager) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iOverlayManager == null) {
                return false;
            }
            Proxy.sDefaultImpl = iOverlayManager;
            return true;
        }

        public static IOverlayManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
