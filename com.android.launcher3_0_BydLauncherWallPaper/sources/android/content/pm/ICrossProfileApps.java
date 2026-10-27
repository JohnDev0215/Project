package android.content.pm;

import android.app.IApplicationThread;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.os.UserHandle;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface ICrossProfileApps extends IInterface {

    public static class Default implements ICrossProfileApps {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.content.pm.ICrossProfileApps
        public boolean canConfigureInteractAcrossProfiles(String str) throws RemoteException {
            return false;
        }

        @Override // android.content.pm.ICrossProfileApps
        public boolean canInteractAcrossProfiles(String str) throws RemoteException {
            return false;
        }

        @Override // android.content.pm.ICrossProfileApps
        public boolean canRequestInteractAcrossProfiles(String str) throws RemoteException {
            return false;
        }

        @Override // android.content.pm.ICrossProfileApps
        public boolean canUserAttemptToConfigureInteractAcrossProfiles(String str) throws RemoteException {
            return false;
        }

        @Override // android.content.pm.ICrossProfileApps
        public void clearInteractAcrossProfilesAppOps() throws RemoteException {
        }

        @Override // android.content.pm.ICrossProfileApps
        public List<UserHandle> getTargetUserProfiles(String str) throws RemoteException {
            return null;
        }

        @Override // android.content.pm.ICrossProfileApps
        public void resetInteractAcrossProfilesAppOps(List<String> list) throws RemoteException {
        }

        @Override // android.content.pm.ICrossProfileApps
        public void setInteractAcrossProfilesAppOp(String str, int i) throws RemoteException {
        }

        @Override // android.content.pm.ICrossProfileApps
        public void startActivityAsUser(IApplicationThread iApplicationThread, String str, String str2, ComponentName componentName, int i, boolean z) throws RemoteException {
        }

        @Override // android.content.pm.ICrossProfileApps
        public void startActivityAsUserByIntent(IApplicationThread iApplicationThread, String str, String str2, Intent intent, int i, IBinder iBinder, Bundle bundle) throws RemoteException {
        }
    }

    boolean canConfigureInteractAcrossProfiles(String str) throws RemoteException;

    boolean canInteractAcrossProfiles(String str) throws RemoteException;

    boolean canRequestInteractAcrossProfiles(String str) throws RemoteException;

    boolean canUserAttemptToConfigureInteractAcrossProfiles(String str) throws RemoteException;

    void clearInteractAcrossProfilesAppOps() throws RemoteException;

    List<UserHandle> getTargetUserProfiles(String str) throws RemoteException;

    void resetInteractAcrossProfilesAppOps(List<String> list) throws RemoteException;

    void setInteractAcrossProfilesAppOp(String str, int i) throws RemoteException;

    void startActivityAsUser(IApplicationThread iApplicationThread, String str, String str2, ComponentName componentName, int i, boolean z) throws RemoteException;

    void startActivityAsUserByIntent(IApplicationThread iApplicationThread, String str, String str2, Intent intent, int i, IBinder iBinder, Bundle bundle) throws RemoteException;

    public static abstract class Stub extends Binder implements ICrossProfileApps {
        private static final String DESCRIPTOR = "android.content.pm.ICrossProfileApps";
        static final int TRANSACTION_canConfigureInteractAcrossProfiles = 7;
        static final int TRANSACTION_canInteractAcrossProfiles = 4;
        static final int TRANSACTION_canRequestInteractAcrossProfiles = 5;
        static final int TRANSACTION_canUserAttemptToConfigureInteractAcrossProfiles = 8;
        static final int TRANSACTION_clearInteractAcrossProfilesAppOps = 10;
        static final int TRANSACTION_getTargetUserProfiles = 3;
        static final int TRANSACTION_resetInteractAcrossProfilesAppOps = 9;
        static final int TRANSACTION_setInteractAcrossProfilesAppOp = 6;
        static final int TRANSACTION_startActivityAsUser = 1;
        static final int TRANSACTION_startActivityAsUserByIntent = 2;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "startActivityAsUser";
                case 2:
                    return "startActivityAsUserByIntent";
                case 3:
                    return "getTargetUserProfiles";
                case 4:
                    return "canInteractAcrossProfiles";
                case 5:
                    return "canRequestInteractAcrossProfiles";
                case 6:
                    return "setInteractAcrossProfilesAppOp";
                case 7:
                    return "canConfigureInteractAcrossProfiles";
                case 8:
                    return "canUserAttemptToConfigureInteractAcrossProfiles";
                case 9:
                    return "resetInteractAcrossProfilesAppOps";
                case 10:
                    return "clearInteractAcrossProfilesAppOps";
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

        public static ICrossProfileApps asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof ICrossProfileApps)) {
                return (ICrossProfileApps) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public String getTransactionName(int i) {
            return getDefaultTransactionName(i);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface(DESCRIPTOR);
                    startActivityAsUser(IApplicationThread.Stub.asInterface(parcel.readStrongBinder()), parcel.readString(), parcel.readString(), parcel.readInt() != 0 ? ComponentName.CREATOR.createFromParcel(parcel) : null, parcel.readInt(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    startActivityAsUserByIntent(IApplicationThread.Stub.asInterface(parcel.readStrongBinder()), parcel.readString(), parcel.readString(), parcel.readInt() != 0 ? Intent.CREATOR.createFromParcel(parcel) : null, parcel.readInt(), parcel.readStrongBinder(), parcel.readInt() != 0 ? Bundle.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<UserHandle> targetUserProfiles = getTargetUserProfiles(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeTypedList(targetUserProfiles);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zCanInteractAcrossProfiles = canInteractAcrossProfiles(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zCanInteractAcrossProfiles ? 1 : 0);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zCanRequestInteractAcrossProfiles = canRequestInteractAcrossProfiles(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zCanRequestInteractAcrossProfiles ? 1 : 0);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    setInteractAcrossProfilesAppOp(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zCanConfigureInteractAcrossProfiles = canConfigureInteractAcrossProfiles(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zCanConfigureInteractAcrossProfiles ? 1 : 0);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zCanUserAttemptToConfigureInteractAcrossProfiles = canUserAttemptToConfigureInteractAcrossProfiles(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zCanUserAttemptToConfigureInteractAcrossProfiles ? 1 : 0);
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    resetInteractAcrossProfilesAppOps(parcel.createStringArrayList());
                    parcel2.writeNoException();
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    clearInteractAcrossProfilesAppOps();
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements ICrossProfileApps {
            public static ICrossProfileApps sDefaultImpl;
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

            @Override // android.content.pm.ICrossProfileApps
            public void startActivityAsUser(IApplicationThread iApplicationThread, String str, String str2, ComponentName componentName, int i, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iApplicationThread != null ? iApplicationThread.asBinder() : null);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (componentName != null) {
                        parcelObtain.writeInt(1);
                        componentName.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().startActivityAsUser(iApplicationThread, str, str2, componentName, i, z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.ICrossProfileApps
            public void startActivityAsUserByIntent(IApplicationThread iApplicationThread, String str, String str2, Intent intent, int i, IBinder iBinder, Bundle bundle) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iApplicationThread != null ? iApplicationThread.asBinder() : null);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (intent != null) {
                        parcelObtain.writeInt(1);
                        intent.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iBinder);
                    if (bundle != null) {
                        parcelObtain.writeInt(1);
                        bundle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().startActivityAsUserByIntent(iApplicationThread, str, str2, intent, i, iBinder, bundle);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.ICrossProfileApps
            public List<UserHandle> getTargetUserProfiles(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getTargetUserProfiles(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createTypedArrayList(UserHandle.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.ICrossProfileApps
            public boolean canInteractAcrossProfiles(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().canInteractAcrossProfiles(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.ICrossProfileApps
            public boolean canRequestInteractAcrossProfiles(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().canRequestInteractAcrossProfiles(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.ICrossProfileApps
            public void setInteractAcrossProfilesAppOp(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setInteractAcrossProfilesAppOp(str, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.ICrossProfileApps
            public boolean canConfigureInteractAcrossProfiles(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().canConfigureInteractAcrossProfiles(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.ICrossProfileApps
            public boolean canUserAttemptToConfigureInteractAcrossProfiles(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().canUserAttemptToConfigureInteractAcrossProfiles(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.ICrossProfileApps
            public void resetInteractAcrossProfilesAppOps(List<String> list) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStringList(list);
                    if (!this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().resetInteractAcrossProfilesAppOps(list);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.ICrossProfileApps
            public void clearInteractAcrossProfilesAppOps() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(10, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().clearInteractAcrossProfilesAppOps();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ICrossProfileApps iCrossProfileApps) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iCrossProfileApps == null) {
                return false;
            }
            Proxy.sDefaultImpl = iCrossProfileApps;
            return true;
        }

        public static ICrossProfileApps getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
