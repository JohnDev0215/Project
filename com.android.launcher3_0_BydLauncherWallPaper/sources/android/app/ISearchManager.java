package android.app;

import android.content.ComponentName;
import android.content.pm.ResolveInfo;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface ISearchManager extends IInterface {

    public static class Default implements ISearchManager {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.app.ISearchManager
        public List<ResolveInfo> getGlobalSearchActivities() throws RemoteException {
            return null;
        }

        @Override // android.app.ISearchManager
        public ComponentName getGlobalSearchActivity() throws RemoteException {
            return null;
        }

        @Override // android.app.ISearchManager
        public SearchableInfo getSearchableInfo(ComponentName componentName) throws RemoteException {
            return null;
        }

        @Override // android.app.ISearchManager
        public List<SearchableInfo> getSearchablesInGlobalSearch() throws RemoteException {
            return null;
        }

        @Override // android.app.ISearchManager
        public ComponentName getWebSearchActivity() throws RemoteException {
            return null;
        }

        @Override // android.app.ISearchManager
        public void launchAssist(int i, Bundle bundle) throws RemoteException {
        }
    }

    List<ResolveInfo> getGlobalSearchActivities() throws RemoteException;

    ComponentName getGlobalSearchActivity() throws RemoteException;

    SearchableInfo getSearchableInfo(ComponentName componentName) throws RemoteException;

    List<SearchableInfo> getSearchablesInGlobalSearch() throws RemoteException;

    ComponentName getWebSearchActivity() throws RemoteException;

    void launchAssist(int i, Bundle bundle) throws RemoteException;

    public static abstract class Stub extends Binder implements ISearchManager {
        private static final String DESCRIPTOR = "android.app.ISearchManager";
        static final int TRANSACTION_getGlobalSearchActivities = 3;
        static final int TRANSACTION_getGlobalSearchActivity = 4;
        static final int TRANSACTION_getSearchableInfo = 1;
        static final int TRANSACTION_getSearchablesInGlobalSearch = 2;
        static final int TRANSACTION_getWebSearchActivity = 5;
        static final int TRANSACTION_launchAssist = 6;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "getSearchableInfo";
                case 2:
                    return "getSearchablesInGlobalSearch";
                case 3:
                    return "getGlobalSearchActivities";
                case 4:
                    return "getGlobalSearchActivity";
                case 5:
                    return "getWebSearchActivity";
                case 6:
                    return "launchAssist";
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

        public static ISearchManager asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof ISearchManager)) {
                return (ISearchManager) iInterfaceQueryLocalInterface;
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
                    SearchableInfo searchableInfo = getSearchableInfo(parcel.readInt() != 0 ? ComponentName.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    if (searchableInfo != null) {
                        parcel2.writeInt(1);
                        searchableInfo.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<SearchableInfo> searchablesInGlobalSearch = getSearchablesInGlobalSearch();
                    parcel2.writeNoException();
                    parcel2.writeTypedList(searchablesInGlobalSearch);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<ResolveInfo> globalSearchActivities = getGlobalSearchActivities();
                    parcel2.writeNoException();
                    parcel2.writeTypedList(globalSearchActivities);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    ComponentName globalSearchActivity = getGlobalSearchActivity();
                    parcel2.writeNoException();
                    if (globalSearchActivity != null) {
                        parcel2.writeInt(1);
                        globalSearchActivity.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    ComponentName webSearchActivity = getWebSearchActivity();
                    parcel2.writeNoException();
                    if (webSearchActivity != null) {
                        parcel2.writeInt(1);
                        webSearchActivity.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    launchAssist(parcel.readInt(), parcel.readInt() != 0 ? Bundle.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements ISearchManager {
            public static ISearchManager sDefaultImpl;
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

            @Override // android.app.ISearchManager
            public SearchableInfo getSearchableInfo(ComponentName componentName) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (componentName != null) {
                        parcelObtain.writeInt(1);
                        componentName.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSearchableInfo(componentName);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? SearchableInfo.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.ISearchManager
            public List<SearchableInfo> getSearchablesInGlobalSearch() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSearchablesInGlobalSearch();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createTypedArrayList(SearchableInfo.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.ISearchManager
            public List<ResolveInfo> getGlobalSearchActivities() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getGlobalSearchActivities();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createTypedArrayList(ResolveInfo.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.ISearchManager
            public ComponentName getGlobalSearchActivity() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getGlobalSearchActivity();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? ComponentName.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.ISearchManager
            public ComponentName getWebSearchActivity() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getWebSearchActivity();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? ComponentName.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.ISearchManager
            public void launchAssist(int i, Bundle bundle) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (bundle != null) {
                        parcelObtain.writeInt(1);
                        bundle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().launchAssist(i, bundle);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ISearchManager iSearchManager) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iSearchManager == null) {
                return false;
            }
            Proxy.sDefaultImpl = iSearchManager;
            return true;
        }

        public static ISearchManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
