package android.apex;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;
import kotlin.text.Typography;

/* JADX INFO: loaded from: classes.dex */
public interface IApexService extends IInterface {

    public static class Default implements IApexService {
        @Override // android.apex.IApexService
        public void abortStagedSession(int i) throws RemoteException {
        }

        @Override // android.apex.IApexService
        public void activatePackage(String str) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.apex.IApexService
        public void deactivatePackage(String str) throws RemoteException {
        }

        @Override // android.apex.IApexService
        public void destroyCeSnapshotsNotSpecified(int i, int[] iArr) throws RemoteException {
        }

        @Override // android.apex.IApexService
        public void destroyDeSnapshots(int i) throws RemoteException {
        }

        @Override // android.apex.IApexService
        public ApexInfo getActivePackage(String str) throws RemoteException {
            return null;
        }

        @Override // android.apex.IApexService
        public ApexInfo[] getActivePackages() throws RemoteException {
            return null;
        }

        @Override // android.apex.IApexService
        public ApexInfo[] getAllPackages() throws RemoteException {
            return null;
        }

        @Override // android.apex.IApexService
        public ApexSessionInfo[] getSessions() throws RemoteException {
            return null;
        }

        @Override // android.apex.IApexService
        public ApexSessionInfo getStagedSessionInfo(int i) throws RemoteException {
            return null;
        }

        @Override // android.apex.IApexService
        public void markStagedSessionReady(int i) throws RemoteException {
        }

        @Override // android.apex.IApexService
        public void markStagedSessionSuccessful(int i) throws RemoteException {
        }

        @Override // android.apex.IApexService
        public void postinstallPackages(List<String> list) throws RemoteException {
        }

        @Override // android.apex.IApexService
        public void preinstallPackages(List<String> list) throws RemoteException {
        }

        @Override // android.apex.IApexService
        public void remountPackages() throws RemoteException {
        }

        @Override // android.apex.IApexService
        public void restoreCeData(int i, int i2, String str) throws RemoteException {
        }

        @Override // android.apex.IApexService
        public void resumeRevertIfNeeded() throws RemoteException {
        }

        @Override // android.apex.IApexService
        public void revertActiveSessions() throws RemoteException {
        }

        @Override // android.apex.IApexService
        public long snapshotCeData(int i, int i2, String str) throws RemoteException {
            return 0L;
        }

        @Override // android.apex.IApexService
        public void stagePackages(List<String> list) throws RemoteException {
        }

        @Override // android.apex.IApexService
        public void submitStagedSession(ApexSessionParams apexSessionParams, ApexInfoList apexInfoList) throws RemoteException {
        }

        @Override // android.apex.IApexService
        public void unstagePackages(List<String> list) throws RemoteException {
        }
    }

    void abortStagedSession(int i) throws RemoteException;

    void activatePackage(String str) throws RemoteException;

    void deactivatePackage(String str) throws RemoteException;

    void destroyCeSnapshotsNotSpecified(int i, int[] iArr) throws RemoteException;

    void destroyDeSnapshots(int i) throws RemoteException;

    ApexInfo getActivePackage(String str) throws RemoteException;

    ApexInfo[] getActivePackages() throws RemoteException;

    ApexInfo[] getAllPackages() throws RemoteException;

    ApexSessionInfo[] getSessions() throws RemoteException;

    ApexSessionInfo getStagedSessionInfo(int i) throws RemoteException;

    void markStagedSessionReady(int i) throws RemoteException;

    void markStagedSessionSuccessful(int i) throws RemoteException;

    void postinstallPackages(List<String> list) throws RemoteException;

    void preinstallPackages(List<String> list) throws RemoteException;

    void remountPackages() throws RemoteException;

    void restoreCeData(int i, int i2, String str) throws RemoteException;

    void resumeRevertIfNeeded() throws RemoteException;

    void revertActiveSessions() throws RemoteException;

    long snapshotCeData(int i, int i2, String str) throws RemoteException;

    void stagePackages(List<String> list) throws RemoteException;

    void submitStagedSession(ApexSessionParams apexSessionParams, ApexInfoList apexInfoList) throws RemoteException;

    void unstagePackages(List<String> list) throws RemoteException;

    public static abstract class Stub extends Binder implements IApexService {
        private static final String DESCRIPTOR = "android$apex$IApexService".replace(Typography.dollar, '.');
        static final int TRANSACTION_abortStagedSession = 8;
        static final int TRANSACTION_activatePackage = 16;
        static final int TRANSACTION_deactivatePackage = 17;
        static final int TRANSACTION_destroyCeSnapshotsNotSpecified = 13;
        static final int TRANSACTION_destroyDeSnapshots = 12;
        static final int TRANSACTION_getActivePackage = 15;
        static final int TRANSACTION_getActivePackages = 6;
        static final int TRANSACTION_getAllPackages = 7;
        static final int TRANSACTION_getSessions = 4;
        static final int TRANSACTION_getStagedSessionInfo = 5;
        static final int TRANSACTION_markStagedSessionReady = 2;
        static final int TRANSACTION_markStagedSessionSuccessful = 3;
        static final int TRANSACTION_postinstallPackages = 19;
        static final int TRANSACTION_preinstallPackages = 18;
        static final int TRANSACTION_remountPackages = 22;
        static final int TRANSACTION_restoreCeData = 11;
        static final int TRANSACTION_resumeRevertIfNeeded = 21;
        static final int TRANSACTION_revertActiveSessions = 9;
        static final int TRANSACTION_snapshotCeData = 10;
        static final int TRANSACTION_stagePackages = 20;
        static final int TRANSACTION_submitStagedSession = 1;
        static final int TRANSACTION_unstagePackages = 14;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IApexService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IApexService)) {
                return (IApexService) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            String str = DESCRIPTOR;
            if (i == 1598968902) {
                parcel2.writeString(str);
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface(str);
                    ApexSessionParams apexSessionParamsCreateFromParcel = parcel.readInt() != 0 ? ApexSessionParams.CREATOR.createFromParcel(parcel) : null;
                    ApexInfoList apexInfoList = new ApexInfoList();
                    submitStagedSession(apexSessionParamsCreateFromParcel, apexInfoList);
                    parcel2.writeNoException();
                    parcel2.writeInt(1);
                    apexInfoList.writeToParcel(parcel2, 1);
                    return true;
                case 2:
                    parcel.enforceInterface(str);
                    markStagedSessionReady(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(str);
                    markStagedSessionSuccessful(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface(str);
                    ApexSessionInfo[] sessions = getSessions();
                    parcel2.writeNoException();
                    parcel2.writeTypedArray(sessions, 1);
                    return true;
                case 5:
                    parcel.enforceInterface(str);
                    ApexSessionInfo stagedSessionInfo = getStagedSessionInfo(parcel.readInt());
                    parcel2.writeNoException();
                    if (stagedSessionInfo != null) {
                        parcel2.writeInt(1);
                        stagedSessionInfo.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 6:
                    parcel.enforceInterface(str);
                    ApexInfo[] activePackages = getActivePackages();
                    parcel2.writeNoException();
                    parcel2.writeTypedArray(activePackages, 1);
                    return true;
                case 7:
                    parcel.enforceInterface(str);
                    ApexInfo[] allPackages = getAllPackages();
                    parcel2.writeNoException();
                    parcel2.writeTypedArray(allPackages, 1);
                    return true;
                case 8:
                    parcel.enforceInterface(str);
                    abortStagedSession(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 9:
                    parcel.enforceInterface(str);
                    revertActiveSessions();
                    parcel2.writeNoException();
                    return true;
                case 10:
                    parcel.enforceInterface(str);
                    long jSnapshotCeData = snapshotCeData(parcel.readInt(), parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeLong(jSnapshotCeData);
                    return true;
                case 11:
                    parcel.enforceInterface(str);
                    restoreCeData(parcel.readInt(), parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 12:
                    parcel.enforceInterface(str);
                    destroyDeSnapshots(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 13:
                    parcel.enforceInterface(str);
                    destroyCeSnapshotsNotSpecified(parcel.readInt(), parcel.createIntArray());
                    parcel2.writeNoException();
                    return true;
                case 14:
                    parcel.enforceInterface(str);
                    unstagePackages(parcel.createStringArrayList());
                    parcel2.writeNoException();
                    return true;
                case 15:
                    parcel.enforceInterface(str);
                    ApexInfo activePackage = getActivePackage(parcel.readString());
                    parcel2.writeNoException();
                    if (activePackage != null) {
                        parcel2.writeInt(1);
                        activePackage.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 16:
                    parcel.enforceInterface(str);
                    activatePackage(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 17:
                    parcel.enforceInterface(str);
                    deactivatePackage(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 18:
                    parcel.enforceInterface(str);
                    preinstallPackages(parcel.createStringArrayList());
                    parcel2.writeNoException();
                    return true;
                case 19:
                    parcel.enforceInterface(str);
                    postinstallPackages(parcel.createStringArrayList());
                    parcel2.writeNoException();
                    return true;
                case 20:
                    parcel.enforceInterface(str);
                    stagePackages(parcel.createStringArrayList());
                    parcel2.writeNoException();
                    return true;
                case 21:
                    parcel.enforceInterface(str);
                    resumeRevertIfNeeded();
                    parcel2.writeNoException();
                    return true;
                case 22:
                    parcel.enforceInterface(str);
                    remountPackages();
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IApexService {
            public static IApexService sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            @Override // android.apex.IApexService
            public void submitStagedSession(ApexSessionParams apexSessionParams, ApexInfoList apexInfoList) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (apexSessionParams != null) {
                        parcelObtain.writeInt(1);
                        apexSessionParams.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().submitStagedSession(apexSessionParams, apexInfoList);
                        return;
                    }
                    parcelObtain2.readException();
                    if (parcelObtain2.readInt() != 0) {
                        apexInfoList.readFromParcel(parcelObtain2);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.apex.IApexService
            public void markStagedSessionReady(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().markStagedSessionReady(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.apex.IApexService
            public void markStagedSessionSuccessful(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().markStagedSessionSuccessful(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.apex.IApexService
            public ApexSessionInfo[] getSessions() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSessions();
                    }
                    parcelObtain2.readException();
                    return (ApexSessionInfo[]) parcelObtain2.createTypedArray(ApexSessionInfo.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.apex.IApexService
            public ApexSessionInfo getStagedSessionInfo(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getStagedSessionInfo(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? ApexSessionInfo.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.apex.IApexService
            public ApexInfo[] getActivePackages() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getActivePackages();
                    }
                    parcelObtain2.readException();
                    return (ApexInfo[]) parcelObtain2.createTypedArray(ApexInfo.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.apex.IApexService
            public ApexInfo[] getAllPackages() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAllPackages();
                    }
                    parcelObtain2.readException();
                    return (ApexInfo[]) parcelObtain2.createTypedArray(ApexInfo.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.apex.IApexService
            public void abortStagedSession(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().abortStagedSession(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.apex.IApexService
            public void revertActiveSessions() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().revertActiveSessions();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.apex.IApexService
            public long snapshotCeData(int i, int i2, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(10, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().snapshotCeData(i, i2, str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readLong();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.apex.IApexService
            public void restoreCeData(int i, int i2, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(11, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().restoreCeData(i, i2, str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.apex.IApexService
            public void destroyDeSnapshots(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(12, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().destroyDeSnapshots(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.apex.IApexService
            public void destroyCeSnapshotsNotSpecified(int i, int[] iArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeIntArray(iArr);
                    if (!this.mRemote.transact(13, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().destroyCeSnapshotsNotSpecified(i, iArr);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.apex.IApexService
            public void unstagePackages(List<String> list) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStringList(list);
                    if (!this.mRemote.transact(14, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unstagePackages(list);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.apex.IApexService
            public ApexInfo getActivePackage(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(15, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getActivePackage(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? ApexInfo.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.apex.IApexService
            public void activatePackage(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(16, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().activatePackage(str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.apex.IApexService
            public void deactivatePackage(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(17, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().deactivatePackage(str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.apex.IApexService
            public void preinstallPackages(List<String> list) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStringList(list);
                    if (!this.mRemote.transact(18, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().preinstallPackages(list);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.apex.IApexService
            public void postinstallPackages(List<String> list) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStringList(list);
                    if (!this.mRemote.transact(19, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().postinstallPackages(list);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.apex.IApexService
            public void stagePackages(List<String> list) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStringList(list);
                    if (!this.mRemote.transact(20, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().stagePackages(list);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.apex.IApexService
            public void resumeRevertIfNeeded() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(21, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().resumeRevertIfNeeded();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.apex.IApexService
            public void remountPackages() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(22, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().remountPackages();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IApexService iApexService) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iApexService == null) {
                return false;
            }
            Proxy.sDefaultImpl = iApexService;
            return true;
        }

        public static IApexService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
