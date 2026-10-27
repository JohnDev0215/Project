package android.content.integrity;

import android.content.IntentSender;
import android.content.pm.ParceledListSlice;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface IAppIntegrityManager extends IInterface {

    public static class Default implements IAppIntegrityManager {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.content.integrity.IAppIntegrityManager
        public String getCurrentRuleSetProvider() throws RemoteException {
            return null;
        }

        @Override // android.content.integrity.IAppIntegrityManager
        public String getCurrentRuleSetVersion() throws RemoteException {
            return null;
        }

        @Override // android.content.integrity.IAppIntegrityManager
        public ParceledListSlice<Rule> getCurrentRules() throws RemoteException {
            return null;
        }

        @Override // android.content.integrity.IAppIntegrityManager
        public List<String> getWhitelistedRuleProviders() throws RemoteException {
            return null;
        }

        @Override // android.content.integrity.IAppIntegrityManager
        public void updateRuleSet(String str, ParceledListSlice<Rule> parceledListSlice, IntentSender intentSender) throws RemoteException {
        }
    }

    String getCurrentRuleSetProvider() throws RemoteException;

    String getCurrentRuleSetVersion() throws RemoteException;

    ParceledListSlice<Rule> getCurrentRules() throws RemoteException;

    List<String> getWhitelistedRuleProviders() throws RemoteException;

    void updateRuleSet(String str, ParceledListSlice<Rule> parceledListSlice, IntentSender intentSender) throws RemoteException;

    public static abstract class Stub extends Binder implements IAppIntegrityManager {
        private static final String DESCRIPTOR = "android.content.integrity.IAppIntegrityManager";
        static final int TRANSACTION_getCurrentRuleSetProvider = 3;
        static final int TRANSACTION_getCurrentRuleSetVersion = 2;
        static final int TRANSACTION_getCurrentRules = 4;
        static final int TRANSACTION_getWhitelistedRuleProviders = 5;
        static final int TRANSACTION_updateRuleSet = 1;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "updateRuleSet";
            }
            if (i == 2) {
                return "getCurrentRuleSetVersion";
            }
            if (i == 3) {
                return "getCurrentRuleSetProvider";
            }
            if (i == 4) {
                return "getCurrentRules";
            }
            if (i != 5) {
                return null;
            }
            return "getWhitelistedRuleProviders";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IAppIntegrityManager asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IAppIntegrityManager)) {
                return (IAppIntegrityManager) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public String getTransactionName(int i) {
            return getDefaultTransactionName(i);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                updateRuleSet(parcel.readString(), parcel.readInt() != 0 ? ParceledListSlice.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? IntentSender.CREATOR.createFromParcel(parcel) : null);
                parcel2.writeNoException();
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                String currentRuleSetVersion = getCurrentRuleSetVersion();
                parcel2.writeNoException();
                parcel2.writeString(currentRuleSetVersion);
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                String currentRuleSetProvider = getCurrentRuleSetProvider();
                parcel2.writeNoException();
                parcel2.writeString(currentRuleSetProvider);
                return true;
            }
            if (i == 4) {
                parcel.enforceInterface(DESCRIPTOR);
                ParceledListSlice<Rule> currentRules = getCurrentRules();
                parcel2.writeNoException();
                if (currentRules != null) {
                    parcel2.writeInt(1);
                    currentRules.writeToParcel(parcel2, 1);
                } else {
                    parcel2.writeInt(0);
                }
                return true;
            }
            if (i != 5) {
                if (i == 1598968902) {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface(DESCRIPTOR);
            List<String> whitelistedRuleProviders = getWhitelistedRuleProviders();
            parcel2.writeNoException();
            parcel2.writeStringList(whitelistedRuleProviders);
            return true;
        }

        private static class Proxy implements IAppIntegrityManager {
            public static IAppIntegrityManager sDefaultImpl;
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

            @Override // android.content.integrity.IAppIntegrityManager
            public void updateRuleSet(String str, ParceledListSlice<Rule> parceledListSlice, IntentSender intentSender) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (parceledListSlice != null) {
                        parcelObtain.writeInt(1);
                        parceledListSlice.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (intentSender != null) {
                        parcelObtain.writeInt(1);
                        intentSender.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().updateRuleSet(str, parceledListSlice, intentSender);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.integrity.IAppIntegrityManager
            public String getCurrentRuleSetVersion() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCurrentRuleSetVersion();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.integrity.IAppIntegrityManager
            public String getCurrentRuleSetProvider() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCurrentRuleSetProvider();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.integrity.IAppIntegrityManager
            public ParceledListSlice<Rule> getCurrentRules() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCurrentRules();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? ParceledListSlice.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.integrity.IAppIntegrityManager
            public List<String> getWhitelistedRuleProviders() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getWhitelistedRuleProviders();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createStringArrayList();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IAppIntegrityManager iAppIntegrityManager) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iAppIntegrityManager == null) {
                return false;
            }
            Proxy.sDefaultImpl = iAppIntegrityManager;
            return true;
        }

        public static IAppIntegrityManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
