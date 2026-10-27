package android.telephony.ims.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes3.dex */
public interface IImsCapabilityCallback extends IInterface {

    public static class Default implements IImsCapabilityCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.telephony.ims.aidl.IImsCapabilityCallback
        public void onCapabilitiesStatusChanged(int i) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IImsCapabilityCallback
        public void onChangeCapabilityConfigurationError(int i, int i2, int i3) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IImsCapabilityCallback
        public void onQueryCapabilityConfiguration(int i, int i2, boolean z) throws RemoteException {
        }
    }

    void onCapabilitiesStatusChanged(int i) throws RemoteException;

    void onChangeCapabilityConfigurationError(int i, int i2, int i3) throws RemoteException;

    void onQueryCapabilityConfiguration(int i, int i2, boolean z) throws RemoteException;

    public static abstract class Stub extends Binder implements IImsCapabilityCallback {
        private static final String DESCRIPTOR = "android.telephony.ims.aidl.IImsCapabilityCallback";
        static final int TRANSACTION_onCapabilitiesStatusChanged = 3;
        static final int TRANSACTION_onChangeCapabilityConfigurationError = 2;
        static final int TRANSACTION_onQueryCapabilityConfiguration = 1;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "onQueryCapabilityConfiguration";
            }
            if (i == 2) {
                return "onChangeCapabilityConfigurationError";
            }
            if (i != 3) {
                return null;
            }
            return "onCapabilitiesStatusChanged";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IImsCapabilityCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IImsCapabilityCallback)) {
                return (IImsCapabilityCallback) iInterfaceQueryLocalInterface;
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
                onQueryCapabilityConfiguration(parcel.readInt(), parcel.readInt(), parcel.readInt() != 0);
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                onChangeCapabilityConfigurationError(parcel.readInt(), parcel.readInt(), parcel.readInt());
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                onCapabilitiesStatusChanged(parcel.readInt());
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IImsCapabilityCallback {
            public static IImsCapabilityCallback sDefaultImpl;
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

            @Override // android.telephony.ims.aidl.IImsCapabilityCallback
            public void onQueryCapabilityConfiguration(int i, int i2, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onQueryCapabilityConfiguration(i, i2, z);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsCapabilityCallback
            public void onChangeCapabilityConfigurationError(int i, int i2, int i3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onChangeCapabilityConfigurationError(i, i2, i3);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsCapabilityCallback
            public void onCapabilitiesStatusChanged(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onCapabilitiesStatusChanged(i);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IImsCapabilityCallback iImsCapabilityCallback) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iImsCapabilityCallback == null) {
                return false;
            }
            Proxy.sDefaultImpl = iImsCapabilityCallback;
            return true;
        }

        public static IImsCapabilityCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
