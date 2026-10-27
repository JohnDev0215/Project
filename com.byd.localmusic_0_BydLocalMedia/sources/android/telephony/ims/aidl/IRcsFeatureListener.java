package android.telephony.ims.aidl;

import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.telephony.ims.RcsContactUceCapability;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public interface IRcsFeatureListener extends IInterface {

    public static class Default implements IRcsFeatureListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.telephony.ims.aidl.IRcsFeatureListener
        public void onCapabilityRequestResponseOptions(int i, String str, RcsContactUceCapability rcsContactUceCapability, int i2) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IRcsFeatureListener
        public void onCapabilityRequestResponsePresence(List<RcsContactUceCapability> list, int i) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IRcsFeatureListener
        public void onCommandUpdate(int i, int i2) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IRcsFeatureListener
        public void onNetworkResponse(int i, String str, int i2) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IRcsFeatureListener
        public void onNotifyUpdateCapabilities(int i) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IRcsFeatureListener
        public void onRemoteCapabilityRequest(Uri uri, RcsContactUceCapability rcsContactUceCapability, int i) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IRcsFeatureListener
        public void onUnpublish() throws RemoteException {
        }
    }

    void onCapabilityRequestResponseOptions(int i, String str, RcsContactUceCapability rcsContactUceCapability, int i2) throws RemoteException;

    void onCapabilityRequestResponsePresence(List<RcsContactUceCapability> list, int i) throws RemoteException;

    void onCommandUpdate(int i, int i2) throws RemoteException;

    void onNetworkResponse(int i, String str, int i2) throws RemoteException;

    void onNotifyUpdateCapabilities(int i) throws RemoteException;

    void onRemoteCapabilityRequest(Uri uri, RcsContactUceCapability rcsContactUceCapability, int i) throws RemoteException;

    void onUnpublish() throws RemoteException;

    public static abstract class Stub extends Binder implements IRcsFeatureListener {
        private static final String DESCRIPTOR = "android.telephony.ims.aidl.IRcsFeatureListener";
        static final int TRANSACTION_onCapabilityRequestResponseOptions = 6;
        static final int TRANSACTION_onCapabilityRequestResponsePresence = 3;
        static final int TRANSACTION_onCommandUpdate = 1;
        static final int TRANSACTION_onNetworkResponse = 2;
        static final int TRANSACTION_onNotifyUpdateCapabilities = 4;
        static final int TRANSACTION_onRemoteCapabilityRequest = 7;
        static final int TRANSACTION_onUnpublish = 5;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "onCommandUpdate";
                case 2:
                    return "onNetworkResponse";
                case 3:
                    return "onCapabilityRequestResponsePresence";
                case 4:
                    return "onNotifyUpdateCapabilities";
                case 5:
                    return "onUnpublish";
                case 6:
                    return "onCapabilityRequestResponseOptions";
                case 7:
                    return "onRemoteCapabilityRequest";
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

        public static IRcsFeatureListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IRcsFeatureListener)) {
                return (IRcsFeatureListener) iInterfaceQueryLocalInterface;
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
                    onCommandUpdate(parcel.readInt(), parcel.readInt());
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    onNetworkResponse(parcel.readInt(), parcel.readString(), parcel.readInt());
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    onCapabilityRequestResponsePresence(parcel.createTypedArrayList(RcsContactUceCapability.CREATOR), parcel.readInt());
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    onNotifyUpdateCapabilities(parcel.readInt());
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    onUnpublish();
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    onCapabilityRequestResponseOptions(parcel.readInt(), parcel.readString(), parcel.readInt() != 0 ? RcsContactUceCapability.CREATOR.createFromParcel(parcel) : null, parcel.readInt());
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    onRemoteCapabilityRequest(parcel.readInt() != 0 ? Uri.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? RcsContactUceCapability.CREATOR.createFromParcel(parcel) : null, parcel.readInt());
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IRcsFeatureListener {
            public static IRcsFeatureListener sDefaultImpl;
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

            @Override // android.telephony.ims.aidl.IRcsFeatureListener
            public void onCommandUpdate(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onCommandUpdate(i, i2);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IRcsFeatureListener
            public void onNetworkResponse(int i, String str, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i2);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onNetworkResponse(i, str, i2);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IRcsFeatureListener
            public void onCapabilityRequestResponsePresence(List<RcsContactUceCapability> list, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeTypedList(list);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onCapabilityRequestResponsePresence(list, i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IRcsFeatureListener
            public void onNotifyUpdateCapabilities(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(4, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onNotifyUpdateCapabilities(i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IRcsFeatureListener
            public void onUnpublish() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(5, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onUnpublish();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IRcsFeatureListener
            public void onCapabilityRequestResponseOptions(int i, String str, RcsContactUceCapability rcsContactUceCapability, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    if (rcsContactUceCapability != null) {
                        parcelObtain.writeInt(1);
                        rcsContactUceCapability.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i2);
                    if (this.mRemote.transact(6, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onCapabilityRequestResponseOptions(i, str, rcsContactUceCapability, i2);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IRcsFeatureListener
            public void onRemoteCapabilityRequest(Uri uri, RcsContactUceCapability rcsContactUceCapability, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (uri != null) {
                        parcelObtain.writeInt(1);
                        uri.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (rcsContactUceCapability != null) {
                        parcelObtain.writeInt(1);
                        rcsContactUceCapability.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(7, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onRemoteCapabilityRequest(uri, rcsContactUceCapability, i);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IRcsFeatureListener iRcsFeatureListener) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iRcsFeatureListener == null) {
                return false;
            }
            Proxy.sDefaultImpl = iRcsFeatureListener;
            return true;
        }

        public static IRcsFeatureListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
