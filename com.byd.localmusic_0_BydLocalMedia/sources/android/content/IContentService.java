package android.content;

import android.accounts.Account;
import android.content.ISyncStatusObserver;
import android.database.IContentObserver;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface IContentService extends IInterface {

    public static class Default implements IContentService {
        @Override // android.content.IContentService
        public void addPeriodicSync(Account account, String str, Bundle bundle, long j) throws RemoteException {
        }

        @Override // android.content.IContentService
        public void addStatusChangeListener(int i, ISyncStatusObserver iSyncStatusObserver) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.content.IContentService
        public void cancelRequest(SyncRequest syncRequest) throws RemoteException {
        }

        @Override // android.content.IContentService
        public void cancelSync(Account account, String str, ComponentName componentName) throws RemoteException {
        }

        @Override // android.content.IContentService
        public void cancelSyncAsUser(Account account, String str, ComponentName componentName, int i) throws RemoteException {
        }

        @Override // android.content.IContentService
        public Bundle getCache(String str, Uri uri, int i) throws RemoteException {
            return null;
        }

        @Override // android.content.IContentService
        public List<SyncInfo> getCurrentSyncs() throws RemoteException {
            return null;
        }

        @Override // android.content.IContentService
        public List<SyncInfo> getCurrentSyncsAsUser(int i) throws RemoteException {
            return null;
        }

        @Override // android.content.IContentService
        public int getIsSyncable(Account account, String str) throws RemoteException {
            return 0;
        }

        @Override // android.content.IContentService
        public int getIsSyncableAsUser(Account account, String str, int i) throws RemoteException {
            return 0;
        }

        @Override // android.content.IContentService
        public boolean getMasterSyncAutomatically() throws RemoteException {
            return false;
        }

        @Override // android.content.IContentService
        public boolean getMasterSyncAutomaticallyAsUser(int i) throws RemoteException {
            return false;
        }

        @Override // android.content.IContentService
        public List<PeriodicSync> getPeriodicSyncs(Account account, String str, ComponentName componentName) throws RemoteException {
            return null;
        }

        @Override // android.content.IContentService
        public String[] getSyncAdapterPackagesForAuthorityAsUser(String str, int i) throws RemoteException {
            return null;
        }

        @Override // android.content.IContentService
        public SyncAdapterType[] getSyncAdapterTypes() throws RemoteException {
            return null;
        }

        @Override // android.content.IContentService
        public SyncAdapterType[] getSyncAdapterTypesAsUser(int i) throws RemoteException {
            return null;
        }

        @Override // android.content.IContentService
        public boolean getSyncAutomatically(Account account, String str) throws RemoteException {
            return false;
        }

        @Override // android.content.IContentService
        public boolean getSyncAutomaticallyAsUser(Account account, String str, int i) throws RemoteException {
            return false;
        }

        @Override // android.content.IContentService
        public SyncStatusInfo getSyncStatus(Account account, String str, ComponentName componentName) throws RemoteException {
            return null;
        }

        @Override // android.content.IContentService
        public SyncStatusInfo getSyncStatusAsUser(Account account, String str, ComponentName componentName, int i) throws RemoteException {
            return null;
        }

        @Override // android.content.IContentService
        public boolean isSyncActive(Account account, String str, ComponentName componentName) throws RemoteException {
            return false;
        }

        @Override // android.content.IContentService
        public boolean isSyncPending(Account account, String str, ComponentName componentName) throws RemoteException {
            return false;
        }

        @Override // android.content.IContentService
        public boolean isSyncPendingAsUser(Account account, String str, ComponentName componentName, int i) throws RemoteException {
            return false;
        }

        @Override // android.content.IContentService
        public void notifyChange(Uri[] uriArr, IContentObserver iContentObserver, boolean z, int i, int i2, int i3, String str) throws RemoteException {
        }

        @Override // android.content.IContentService
        public void notifyChangeAsUser(Uri[] uriArr, IContentObserver iContentObserver, boolean z, int i, int i2, int i3, String str, String str2, int i4) throws RemoteException {
        }

        @Override // android.content.IContentService
        public void onDbCorruption(String str, String str2, String str3) throws RemoteException {
        }

        @Override // android.content.IContentService
        public void putCache(String str, Uri uri, Bundle bundle, int i) throws RemoteException {
        }

        @Override // android.content.IContentService
        public void registerContentObserver(Uri uri, boolean z, IContentObserver iContentObserver, int i, int i2) throws RemoteException {
        }

        @Override // android.content.IContentService
        public void removePeriodicSync(Account account, String str, Bundle bundle) throws RemoteException {
        }

        @Override // android.content.IContentService
        public void removeStatusChangeListener(ISyncStatusObserver iSyncStatusObserver) throws RemoteException {
        }

        @Override // android.content.IContentService
        public void requestSync(Account account, String str, Bundle bundle, String str2) throws RemoteException {
        }

        @Override // android.content.IContentService
        public void resetTodayStats() throws RemoteException {
        }

        @Override // android.content.IContentService
        public void setIsSyncable(Account account, String str, int i) throws RemoteException {
        }

        @Override // android.content.IContentService
        public void setIsSyncableAsUser(Account account, String str, int i, int i2) throws RemoteException {
        }

        @Override // android.content.IContentService
        public void setMasterSyncAutomatically(boolean z) throws RemoteException {
        }

        @Override // android.content.IContentService
        public void setMasterSyncAutomaticallyAsUser(boolean z, int i) throws RemoteException {
        }

        @Override // android.content.IContentService
        public void setSyncAutomatically(Account account, String str, boolean z) throws RemoteException {
        }

        @Override // android.content.IContentService
        public void setSyncAutomaticallyAsUser(Account account, String str, boolean z, int i) throws RemoteException {
        }

        @Override // android.content.IContentService
        public void sync(SyncRequest syncRequest, String str) throws RemoteException {
        }

        @Override // android.content.IContentService
        public void syncAsUser(SyncRequest syncRequest, int i, String str) throws RemoteException {
        }

        @Override // android.content.IContentService
        public void unregisterContentObserver(IContentObserver iContentObserver) throws RemoteException {
        }
    }

    void addPeriodicSync(Account account, String str, Bundle bundle, long j) throws RemoteException;

    void addStatusChangeListener(int i, ISyncStatusObserver iSyncStatusObserver) throws RemoteException;

    void cancelRequest(SyncRequest syncRequest) throws RemoteException;

    void cancelSync(Account account, String str, ComponentName componentName) throws RemoteException;

    void cancelSyncAsUser(Account account, String str, ComponentName componentName, int i) throws RemoteException;

    Bundle getCache(String str, Uri uri, int i) throws RemoteException;

    List<SyncInfo> getCurrentSyncs() throws RemoteException;

    List<SyncInfo> getCurrentSyncsAsUser(int i) throws RemoteException;

    int getIsSyncable(Account account, String str) throws RemoteException;

    int getIsSyncableAsUser(Account account, String str, int i) throws RemoteException;

    boolean getMasterSyncAutomatically() throws RemoteException;

    boolean getMasterSyncAutomaticallyAsUser(int i) throws RemoteException;

    List<PeriodicSync> getPeriodicSyncs(Account account, String str, ComponentName componentName) throws RemoteException;

    String[] getSyncAdapterPackagesForAuthorityAsUser(String str, int i) throws RemoteException;

    SyncAdapterType[] getSyncAdapterTypes() throws RemoteException;

    SyncAdapterType[] getSyncAdapterTypesAsUser(int i) throws RemoteException;

    boolean getSyncAutomatically(Account account, String str) throws RemoteException;

    boolean getSyncAutomaticallyAsUser(Account account, String str, int i) throws RemoteException;

    SyncStatusInfo getSyncStatus(Account account, String str, ComponentName componentName) throws RemoteException;

    SyncStatusInfo getSyncStatusAsUser(Account account, String str, ComponentName componentName, int i) throws RemoteException;

    boolean isSyncActive(Account account, String str, ComponentName componentName) throws RemoteException;

    boolean isSyncPending(Account account, String str, ComponentName componentName) throws RemoteException;

    boolean isSyncPendingAsUser(Account account, String str, ComponentName componentName, int i) throws RemoteException;

    void notifyChange(Uri[] uriArr, IContentObserver iContentObserver, boolean z, int i, int i2, int i3, String str) throws RemoteException;

    void notifyChangeAsUser(Uri[] uriArr, IContentObserver iContentObserver, boolean z, int i, int i2, int i3, String str, String str2, int i4) throws RemoteException;

    void onDbCorruption(String str, String str2, String str3) throws RemoteException;

    void putCache(String str, Uri uri, Bundle bundle, int i) throws RemoteException;

    void registerContentObserver(Uri uri, boolean z, IContentObserver iContentObserver, int i, int i2) throws RemoteException;

    void removePeriodicSync(Account account, String str, Bundle bundle) throws RemoteException;

    void removeStatusChangeListener(ISyncStatusObserver iSyncStatusObserver) throws RemoteException;

    void requestSync(Account account, String str, Bundle bundle, String str2) throws RemoteException;

    void resetTodayStats() throws RemoteException;

    void setIsSyncable(Account account, String str, int i) throws RemoteException;

    void setIsSyncableAsUser(Account account, String str, int i, int i2) throws RemoteException;

    void setMasterSyncAutomatically(boolean z) throws RemoteException;

    void setMasterSyncAutomaticallyAsUser(boolean z, int i) throws RemoteException;

    void setSyncAutomatically(Account account, String str, boolean z) throws RemoteException;

    void setSyncAutomaticallyAsUser(Account account, String str, boolean z, int i) throws RemoteException;

    void sync(SyncRequest syncRequest, String str) throws RemoteException;

    void syncAsUser(SyncRequest syncRequest, int i, String str) throws RemoteException;

    void unregisterContentObserver(IContentObserver iContentObserver) throws RemoteException;

    public static abstract class Stub extends Binder implements IContentService {
        private static final String DESCRIPTOR = "android.content.IContentService";
        static final int TRANSACTION_addPeriodicSync = 16;
        static final int TRANSACTION_addStatusChangeListener = 36;
        static final int TRANSACTION_cancelRequest = 10;
        static final int TRANSACTION_cancelSync = 8;
        static final int TRANSACTION_cancelSyncAsUser = 9;
        static final int TRANSACTION_getCache = 39;
        static final int TRANSACTION_getCurrentSyncs = 26;
        static final int TRANSACTION_getCurrentSyncsAsUser = 27;
        static final int TRANSACTION_getIsSyncable = 18;
        static final int TRANSACTION_getIsSyncableAsUser = 19;
        static final int TRANSACTION_getMasterSyncAutomatically = 24;
        static final int TRANSACTION_getMasterSyncAutomaticallyAsUser = 25;
        static final int TRANSACTION_getPeriodicSyncs = 15;
        static final int TRANSACTION_getSyncAdapterPackagesForAuthorityAsUser = 30;
        static final int TRANSACTION_getSyncAdapterTypes = 28;
        static final int TRANSACTION_getSyncAdapterTypesAsUser = 29;
        static final int TRANSACTION_getSyncAutomatically = 11;
        static final int TRANSACTION_getSyncAutomaticallyAsUser = 12;
        static final int TRANSACTION_getSyncStatus = 32;
        static final int TRANSACTION_getSyncStatusAsUser = 33;
        static final int TRANSACTION_isSyncActive = 31;
        static final int TRANSACTION_isSyncPending = 34;
        static final int TRANSACTION_isSyncPendingAsUser = 35;
        static final int TRANSACTION_notifyChange = 3;
        static final int TRANSACTION_notifyChangeAsUser = 4;
        static final int TRANSACTION_onDbCorruption = 41;
        static final int TRANSACTION_putCache = 38;
        static final int TRANSACTION_registerContentObserver = 2;
        static final int TRANSACTION_removePeriodicSync = 17;
        static final int TRANSACTION_removeStatusChangeListener = 37;
        static final int TRANSACTION_requestSync = 5;
        static final int TRANSACTION_resetTodayStats = 40;
        static final int TRANSACTION_setIsSyncable = 20;
        static final int TRANSACTION_setIsSyncableAsUser = 21;
        static final int TRANSACTION_setMasterSyncAutomatically = 22;
        static final int TRANSACTION_setMasterSyncAutomaticallyAsUser = 23;
        static final int TRANSACTION_setSyncAutomatically = 13;
        static final int TRANSACTION_setSyncAutomaticallyAsUser = 14;
        static final int TRANSACTION_sync = 6;
        static final int TRANSACTION_syncAsUser = 7;
        static final int TRANSACTION_unregisterContentObserver = 1;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "unregisterContentObserver";
                case 2:
                    return "registerContentObserver";
                case 3:
                    return "notifyChange";
                case 4:
                    return "notifyChangeAsUser";
                case 5:
                    return "requestSync";
                case 6:
                    return "sync";
                case 7:
                    return "syncAsUser";
                case 8:
                    return "cancelSync";
                case 9:
                    return "cancelSyncAsUser";
                case 10:
                    return "cancelRequest";
                case 11:
                    return "getSyncAutomatically";
                case 12:
                    return "getSyncAutomaticallyAsUser";
                case 13:
                    return "setSyncAutomatically";
                case 14:
                    return "setSyncAutomaticallyAsUser";
                case 15:
                    return "getPeriodicSyncs";
                case 16:
                    return "addPeriodicSync";
                case 17:
                    return "removePeriodicSync";
                case 18:
                    return "getIsSyncable";
                case 19:
                    return "getIsSyncableAsUser";
                case 20:
                    return "setIsSyncable";
                case 21:
                    return "setIsSyncableAsUser";
                case 22:
                    return "setMasterSyncAutomatically";
                case 23:
                    return "setMasterSyncAutomaticallyAsUser";
                case 24:
                    return "getMasterSyncAutomatically";
                case 25:
                    return "getMasterSyncAutomaticallyAsUser";
                case 26:
                    return "getCurrentSyncs";
                case 27:
                    return "getCurrentSyncsAsUser";
                case 28:
                    return "getSyncAdapterTypes";
                case 29:
                    return "getSyncAdapterTypesAsUser";
                case 30:
                    return "getSyncAdapterPackagesForAuthorityAsUser";
                case 31:
                    return "isSyncActive";
                case 32:
                    return "getSyncStatus";
                case 33:
                    return "getSyncStatusAsUser";
                case 34:
                    return "isSyncPending";
                case 35:
                    return "isSyncPendingAsUser";
                case 36:
                    return "addStatusChangeListener";
                case 37:
                    return "removeStatusChangeListener";
                case 38:
                    return "putCache";
                case 39:
                    return "getCache";
                case 40:
                    return "resetTodayStats";
                case 41:
                    return "onDbCorruption";
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

        public static IContentService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IContentService)) {
                return (IContentService) iInterfaceQueryLocalInterface;
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
                    unregisterContentObserver(IContentObserver.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    registerContentObserver(parcel.readInt() != 0 ? Uri.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0, IContentObserver.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    notifyChange((Uri[]) parcel.createTypedArray(Uri.CREATOR), IContentObserver.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt() != 0, parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    notifyChangeAsUser((Uri[]) parcel.createTypedArray(Uri.CREATOR), IContentObserver.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt() != 0, parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    requestSync(parcel.readInt() != 0 ? Account.CREATOR.createFromParcel(parcel) : null, parcel.readString(), parcel.readInt() != 0 ? Bundle.CREATOR.createFromParcel(parcel) : null, parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    sync(parcel.readInt() != 0 ? SyncRequest.CREATOR.createFromParcel(parcel) : null, parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    syncAsUser(parcel.readInt() != 0 ? SyncRequest.CREATOR.createFromParcel(parcel) : null, parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    cancelSync(parcel.readInt() != 0 ? Account.CREATOR.createFromParcel(parcel) : null, parcel.readString(), parcel.readInt() != 0 ? ComponentName.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    cancelSyncAsUser(parcel.readInt() != 0 ? Account.CREATOR.createFromParcel(parcel) : null, parcel.readString(), parcel.readInt() != 0 ? ComponentName.CREATOR.createFromParcel(parcel) : null, parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    cancelRequest(parcel.readInt() != 0 ? SyncRequest.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean syncAutomatically = getSyncAutomatically(parcel.readInt() != 0 ? Account.CREATOR.createFromParcel(parcel) : null, parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(syncAutomatically ? 1 : 0);
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean syncAutomaticallyAsUser = getSyncAutomaticallyAsUser(parcel.readInt() != 0 ? Account.CREATOR.createFromParcel(parcel) : null, parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(syncAutomaticallyAsUser ? 1 : 0);
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    setSyncAutomatically(parcel.readInt() != 0 ? Account.CREATOR.createFromParcel(parcel) : null, parcel.readString(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    setSyncAutomaticallyAsUser(parcel.readInt() != 0 ? Account.CREATOR.createFromParcel(parcel) : null, parcel.readString(), parcel.readInt() != 0, parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<PeriodicSync> periodicSyncs = getPeriodicSyncs(parcel.readInt() != 0 ? Account.CREATOR.createFromParcel(parcel) : null, parcel.readString(), parcel.readInt() != 0 ? ComponentName.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeTypedList(periodicSyncs);
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    addPeriodicSync(parcel.readInt() != 0 ? Account.CREATOR.createFromParcel(parcel) : null, parcel.readString(), parcel.readInt() != 0 ? Bundle.CREATOR.createFromParcel(parcel) : null, parcel.readLong());
                    parcel2.writeNoException();
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    removePeriodicSync(parcel.readInt() != 0 ? Account.CREATOR.createFromParcel(parcel) : null, parcel.readString(), parcel.readInt() != 0 ? Bundle.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    int isSyncable = getIsSyncable(parcel.readInt() != 0 ? Account.CREATOR.createFromParcel(parcel) : null, parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(isSyncable);
                    return true;
                case 19:
                    parcel.enforceInterface(DESCRIPTOR);
                    int isSyncableAsUser = getIsSyncableAsUser(parcel.readInt() != 0 ? Account.CREATOR.createFromParcel(parcel) : null, parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(isSyncableAsUser);
                    return true;
                case 20:
                    parcel.enforceInterface(DESCRIPTOR);
                    setIsSyncable(parcel.readInt() != 0 ? Account.CREATOR.createFromParcel(parcel) : null, parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 21:
                    parcel.enforceInterface(DESCRIPTOR);
                    setIsSyncableAsUser(parcel.readInt() != 0 ? Account.CREATOR.createFromParcel(parcel) : null, parcel.readString(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 22:
                    parcel.enforceInterface(DESCRIPTOR);
                    setMasterSyncAutomatically(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 23:
                    parcel.enforceInterface(DESCRIPTOR);
                    setMasterSyncAutomaticallyAsUser(parcel.readInt() != 0, parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 24:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean masterSyncAutomatically = getMasterSyncAutomatically();
                    parcel2.writeNoException();
                    parcel2.writeInt(masterSyncAutomatically ? 1 : 0);
                    return true;
                case 25:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean masterSyncAutomaticallyAsUser = getMasterSyncAutomaticallyAsUser(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(masterSyncAutomaticallyAsUser ? 1 : 0);
                    return true;
                case 26:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<SyncInfo> currentSyncs = getCurrentSyncs();
                    parcel2.writeNoException();
                    parcel2.writeTypedList(currentSyncs);
                    return true;
                case 27:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<SyncInfo> currentSyncsAsUser = getCurrentSyncsAsUser(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeTypedList(currentSyncsAsUser);
                    return true;
                case 28:
                    parcel.enforceInterface(DESCRIPTOR);
                    SyncAdapterType[] syncAdapterTypes = getSyncAdapterTypes();
                    parcel2.writeNoException();
                    parcel2.writeTypedArray(syncAdapterTypes, 1);
                    return true;
                case 29:
                    parcel.enforceInterface(DESCRIPTOR);
                    SyncAdapterType[] syncAdapterTypesAsUser = getSyncAdapterTypesAsUser(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeTypedArray(syncAdapterTypesAsUser, 1);
                    return true;
                case 30:
                    parcel.enforceInterface(DESCRIPTOR);
                    String[] syncAdapterPackagesForAuthorityAsUser = getSyncAdapterPackagesForAuthorityAsUser(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeStringArray(syncAdapterPackagesForAuthorityAsUser);
                    return true;
                case 31:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsSyncActive = isSyncActive(parcel.readInt() != 0 ? Account.CREATOR.createFromParcel(parcel) : null, parcel.readString(), parcel.readInt() != 0 ? ComponentName.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsSyncActive ? 1 : 0);
                    return true;
                case 32:
                    parcel.enforceInterface(DESCRIPTOR);
                    SyncStatusInfo syncStatus = getSyncStatus(parcel.readInt() != 0 ? Account.CREATOR.createFromParcel(parcel) : null, parcel.readString(), parcel.readInt() != 0 ? ComponentName.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    if (syncStatus != null) {
                        parcel2.writeInt(1);
                        syncStatus.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 33:
                    parcel.enforceInterface(DESCRIPTOR);
                    SyncStatusInfo syncStatusAsUser = getSyncStatusAsUser(parcel.readInt() != 0 ? Account.CREATOR.createFromParcel(parcel) : null, parcel.readString(), parcel.readInt() != 0 ? ComponentName.CREATOR.createFromParcel(parcel) : null, parcel.readInt());
                    parcel2.writeNoException();
                    if (syncStatusAsUser != null) {
                        parcel2.writeInt(1);
                        syncStatusAsUser.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 34:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsSyncPending = isSyncPending(parcel.readInt() != 0 ? Account.CREATOR.createFromParcel(parcel) : null, parcel.readString(), parcel.readInt() != 0 ? ComponentName.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsSyncPending ? 1 : 0);
                    return true;
                case 35:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsSyncPendingAsUser = isSyncPendingAsUser(parcel.readInt() != 0 ? Account.CREATOR.createFromParcel(parcel) : null, parcel.readString(), parcel.readInt() != 0 ? ComponentName.CREATOR.createFromParcel(parcel) : null, parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsSyncPendingAsUser ? 1 : 0);
                    return true;
                case 36:
                    parcel.enforceInterface(DESCRIPTOR);
                    addStatusChangeListener(parcel.readInt(), ISyncStatusObserver.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 37:
                    parcel.enforceInterface(DESCRIPTOR);
                    removeStatusChangeListener(ISyncStatusObserver.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 38:
                    parcel.enforceInterface(DESCRIPTOR);
                    putCache(parcel.readString(), parcel.readInt() != 0 ? Uri.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? Bundle.CREATOR.createFromParcel(parcel) : null, parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 39:
                    parcel.enforceInterface(DESCRIPTOR);
                    Bundle cache = getCache(parcel.readString(), parcel.readInt() != 0 ? Uri.CREATOR.createFromParcel(parcel) : null, parcel.readInt());
                    parcel2.writeNoException();
                    if (cache != null) {
                        parcel2.writeInt(1);
                        cache.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 40:
                    parcel.enforceInterface(DESCRIPTOR);
                    resetTodayStats();
                    parcel2.writeNoException();
                    return true;
                case 41:
                    parcel.enforceInterface(DESCRIPTOR);
                    onDbCorruption(parcel.readString(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IContentService {
            public static IContentService sDefaultImpl;
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

            @Override // android.content.IContentService
            public void unregisterContentObserver(IContentObserver iContentObserver) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iContentObserver != null ? iContentObserver.asBinder() : null);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterContentObserver(iContentObserver);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.IContentService
            public void registerContentObserver(Uri uri, boolean z, IContentObserver iContentObserver, int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    int i3 = 1;
                    if (uri != null) {
                        parcelObtain.writeInt(1);
                        uri.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!z) {
                        i3 = 0;
                    }
                    parcelObtain.writeInt(i3);
                    parcelObtain.writeStrongBinder(iContentObserver != null ? iContentObserver.asBinder() : null);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerContentObserver(uri, z, iContentObserver, i, i2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.IContentService
            public void notifyChange(Uri[] uriArr, IContentObserver iContentObserver, boolean z, int i, int i2, int i3, String str) throws Throwable {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeTypedArray(uriArr, 0);
                    parcelObtain.writeStrongBinder(iContentObserver != null ? iContentObserver.asBinder() : null);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    parcelObtain.writeString(str);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifyChange(uriArr, iContentObserver, z, i, i2, i3, str);
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                    } else {
                        parcelObtain2.readException();
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                    }
                } catch (Throwable th2) {
                    th = th2;
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    throw th;
                }
            }

            @Override // android.content.IContentService
            public void notifyChangeAsUser(Uri[] uriArr, IContentObserver iContentObserver, boolean z, int i, int i2, int i3, String str, String str2, int i4) throws Throwable {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeTypedArray(uriArr, 0);
                    parcelObtain.writeStrongBinder(iContentObserver != null ? iContentObserver.asBinder() : null);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeInt(i4);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifyChangeAsUser(uriArr, iContentObserver, z, i, i2, i3, str, str2, i4);
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                    } else {
                        parcelObtain2.readException();
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                    }
                } catch (Throwable th2) {
                    th = th2;
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    throw th;
                }
            }

            @Override // android.content.IContentService
            public void requestSync(Account account, String str, Bundle bundle, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (account != null) {
                        parcelObtain.writeInt(1);
                        account.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str);
                    if (bundle != null) {
                        parcelObtain.writeInt(1);
                        bundle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().requestSync(account, str, bundle, str2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.IContentService
            public void sync(SyncRequest syncRequest, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (syncRequest != null) {
                        parcelObtain.writeInt(1);
                        syncRequest.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().sync(syncRequest, str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.IContentService
            public void syncAsUser(SyncRequest syncRequest, int i, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (syncRequest != null) {
                        parcelObtain.writeInt(1);
                        syncRequest.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().syncAsUser(syncRequest, i, str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.IContentService
            public void cancelSync(Account account, String str, ComponentName componentName) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (account != null) {
                        parcelObtain.writeInt(1);
                        account.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str);
                    if (componentName != null) {
                        parcelObtain.writeInt(1);
                        componentName.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().cancelSync(account, str, componentName);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.IContentService
            public void cancelSyncAsUser(Account account, String str, ComponentName componentName, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (account != null) {
                        parcelObtain.writeInt(1);
                        account.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str);
                    if (componentName != null) {
                        parcelObtain.writeInt(1);
                        componentName.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().cancelSyncAsUser(account, str, componentName, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.IContentService
            public void cancelRequest(SyncRequest syncRequest) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (syncRequest != null) {
                        parcelObtain.writeInt(1);
                        syncRequest.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(10, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().cancelRequest(syncRequest);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.IContentService
            public boolean getSyncAutomatically(Account account, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (account != null) {
                        parcelObtain.writeInt(1);
                        account.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(11, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSyncAutomatically(account, str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.IContentService
            public boolean getSyncAutomaticallyAsUser(Account account, String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (account != null) {
                        parcelObtain.writeInt(1);
                        account.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(12, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSyncAutomaticallyAsUser(account, str, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.IContentService
            public void setSyncAutomatically(Account account, String str, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    int i = 1;
                    if (account != null) {
                        parcelObtain.writeInt(1);
                        account.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str);
                    if (!z) {
                        i = 0;
                    }
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(13, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setSyncAutomatically(account, str, z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.IContentService
            public void setSyncAutomaticallyAsUser(Account account, String str, boolean z, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    int i2 = 1;
                    if (account != null) {
                        parcelObtain.writeInt(1);
                        account.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str);
                    if (!z) {
                        i2 = 0;
                    }
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(14, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setSyncAutomaticallyAsUser(account, str, z, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.IContentService
            public List<PeriodicSync> getPeriodicSyncs(Account account, String str, ComponentName componentName) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (account != null) {
                        parcelObtain.writeInt(1);
                        account.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str);
                    if (componentName != null) {
                        parcelObtain.writeInt(1);
                        componentName.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(15, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPeriodicSyncs(account, str, componentName);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createTypedArrayList(PeriodicSync.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.IContentService
            public void addPeriodicSync(Account account, String str, Bundle bundle, long j) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (account != null) {
                        parcelObtain.writeInt(1);
                        account.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str);
                    if (bundle != null) {
                        parcelObtain.writeInt(1);
                        bundle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeLong(j);
                    if (!this.mRemote.transact(16, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().addPeriodicSync(account, str, bundle, j);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.IContentService
            public void removePeriodicSync(Account account, String str, Bundle bundle) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (account != null) {
                        parcelObtain.writeInt(1);
                        account.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str);
                    if (bundle != null) {
                        parcelObtain.writeInt(1);
                        bundle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(17, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().removePeriodicSync(account, str, bundle);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.IContentService
            public int getIsSyncable(Account account, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (account != null) {
                        parcelObtain.writeInt(1);
                        account.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(18, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getIsSyncable(account, str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.IContentService
            public int getIsSyncableAsUser(Account account, String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (account != null) {
                        parcelObtain.writeInt(1);
                        account.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(19, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getIsSyncableAsUser(account, str, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.IContentService
            public void setIsSyncable(Account account, String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (account != null) {
                        parcelObtain.writeInt(1);
                        account.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(20, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setIsSyncable(account, str, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.IContentService
            public void setIsSyncableAsUser(Account account, String str, int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (account != null) {
                        parcelObtain.writeInt(1);
                        account.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(21, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setIsSyncableAsUser(account, str, i, i2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.IContentService
            public void setMasterSyncAutomatically(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(22, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setMasterSyncAutomatically(z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.IContentService
            public void setMasterSyncAutomaticallyAsUser(boolean z, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(23, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setMasterSyncAutomaticallyAsUser(z, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.IContentService
            public boolean getMasterSyncAutomatically() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(24, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMasterSyncAutomatically();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.IContentService
            public boolean getMasterSyncAutomaticallyAsUser(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(25, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMasterSyncAutomaticallyAsUser(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.IContentService
            public List<SyncInfo> getCurrentSyncs() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(26, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCurrentSyncs();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createTypedArrayList(SyncInfo.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.IContentService
            public List<SyncInfo> getCurrentSyncsAsUser(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(27, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCurrentSyncsAsUser(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createTypedArrayList(SyncInfo.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.IContentService
            public SyncAdapterType[] getSyncAdapterTypes() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(28, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSyncAdapterTypes();
                    }
                    parcelObtain2.readException();
                    return (SyncAdapterType[]) parcelObtain2.createTypedArray(SyncAdapterType.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.IContentService
            public SyncAdapterType[] getSyncAdapterTypesAsUser(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(29, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSyncAdapterTypesAsUser(i);
                    }
                    parcelObtain2.readException();
                    return (SyncAdapterType[]) parcelObtain2.createTypedArray(SyncAdapterType.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.IContentService
            public String[] getSyncAdapterPackagesForAuthorityAsUser(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(30, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSyncAdapterPackagesForAuthorityAsUser(str, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createStringArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.IContentService
            public boolean isSyncActive(Account account, String str, ComponentName componentName) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (account != null) {
                        parcelObtain.writeInt(1);
                        account.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str);
                    if (componentName != null) {
                        parcelObtain.writeInt(1);
                        componentName.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(31, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isSyncActive(account, str, componentName);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.IContentService
            public SyncStatusInfo getSyncStatus(Account account, String str, ComponentName componentName) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (account != null) {
                        parcelObtain.writeInt(1);
                        account.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str);
                    if (componentName != null) {
                        parcelObtain.writeInt(1);
                        componentName.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(32, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSyncStatus(account, str, componentName);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? SyncStatusInfo.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.IContentService
            public SyncStatusInfo getSyncStatusAsUser(Account account, String str, ComponentName componentName, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (account != null) {
                        parcelObtain.writeInt(1);
                        account.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str);
                    if (componentName != null) {
                        parcelObtain.writeInt(1);
                        componentName.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(33, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSyncStatusAsUser(account, str, componentName, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? SyncStatusInfo.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.IContentService
            public boolean isSyncPending(Account account, String str, ComponentName componentName) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (account != null) {
                        parcelObtain.writeInt(1);
                        account.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str);
                    if (componentName != null) {
                        parcelObtain.writeInt(1);
                        componentName.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(34, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isSyncPending(account, str, componentName);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.IContentService
            public boolean isSyncPendingAsUser(Account account, String str, ComponentName componentName, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (account != null) {
                        parcelObtain.writeInt(1);
                        account.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str);
                    if (componentName != null) {
                        parcelObtain.writeInt(1);
                        componentName.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(35, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isSyncPendingAsUser(account, str, componentName, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.IContentService
            public void addStatusChangeListener(int i, ISyncStatusObserver iSyncStatusObserver) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iSyncStatusObserver != null ? iSyncStatusObserver.asBinder() : null);
                    if (!this.mRemote.transact(36, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().addStatusChangeListener(i, iSyncStatusObserver);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.IContentService
            public void removeStatusChangeListener(ISyncStatusObserver iSyncStatusObserver) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iSyncStatusObserver != null ? iSyncStatusObserver.asBinder() : null);
                    if (!this.mRemote.transact(37, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().removeStatusChangeListener(iSyncStatusObserver);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.IContentService
            public void putCache(String str, Uri uri, Bundle bundle, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (uri != null) {
                        parcelObtain.writeInt(1);
                        uri.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (bundle != null) {
                        parcelObtain.writeInt(1);
                        bundle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(38, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().putCache(str, uri, bundle, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.IContentService
            public Bundle getCache(String str, Uri uri, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (uri != null) {
                        parcelObtain.writeInt(1);
                        uri.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(39, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCache(str, uri, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? Bundle.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.IContentService
            public void resetTodayStats() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(40, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().resetTodayStats();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.IContentService
            public void onDbCorruption(String str, String str2, String str3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeString(str3);
                    if (!this.mRemote.transact(41, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onDbCorruption(str, str2, str3);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IContentService iContentService) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iContentService == null) {
                return false;
            }
            Proxy.sDefaultImpl = iContentService;
            return true;
        }

        public static IContentService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
