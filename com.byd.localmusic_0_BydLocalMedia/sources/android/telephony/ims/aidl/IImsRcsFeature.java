package android.telephony.ims.aidl;

import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.telephony.ims.RcsContactUceCapability;
import android.telephony.ims.aidl.IImsCapabilityCallback;
import android.telephony.ims.aidl.IRcsFeatureListener;
import android.telephony.ims.feature.CapabilityChangeRequest;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public interface IImsRcsFeature extends IInterface {

    public static class Default implements IImsRcsFeature {
        @Override // android.telephony.ims.aidl.IImsRcsFeature
        public void addCapabilityCallback(IImsCapabilityCallback iImsCapabilityCallback) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.telephony.ims.aidl.IImsRcsFeature
        public void changeCapabilitiesConfiguration(CapabilityChangeRequest capabilityChangeRequest, IImsCapabilityCallback iImsCapabilityCallback) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IImsRcsFeature
        public int getFeatureState() throws RemoteException {
            return 0;
        }

        @Override // android.telephony.ims.aidl.IImsRcsFeature
        public void queryCapabilityConfiguration(int i, int i2, IImsCapabilityCallback iImsCapabilityCallback) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IImsRcsFeature
        public int queryCapabilityStatus() throws RemoteException {
            return 0;
        }

        @Override // android.telephony.ims.aidl.IImsRcsFeature
        public void removeCapabilityCallback(IImsCapabilityCallback iImsCapabilityCallback) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IImsRcsFeature
        public void requestCapabilities(List<Uri> list, int i) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IImsRcsFeature
        public void respondToCapabilityRequest(String str, RcsContactUceCapability rcsContactUceCapability, int i) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IImsRcsFeature
        public void respondToCapabilityRequestWithError(Uri uri, int i, String str, int i2) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IImsRcsFeature
        public void sendCapabilityRequest(Uri uri, RcsContactUceCapability rcsContactUceCapability, int i) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IImsRcsFeature
        public void setListener(IRcsFeatureListener iRcsFeatureListener) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IImsRcsFeature
        public void updateCapabilities(RcsContactUceCapability rcsContactUceCapability, int i) throws RemoteException {
        }
    }

    void addCapabilityCallback(IImsCapabilityCallback iImsCapabilityCallback) throws RemoteException;

    void changeCapabilitiesConfiguration(CapabilityChangeRequest capabilityChangeRequest, IImsCapabilityCallback iImsCapabilityCallback) throws RemoteException;

    int getFeatureState() throws RemoteException;

    void queryCapabilityConfiguration(int i, int i2, IImsCapabilityCallback iImsCapabilityCallback) throws RemoteException;

    int queryCapabilityStatus() throws RemoteException;

    void removeCapabilityCallback(IImsCapabilityCallback iImsCapabilityCallback) throws RemoteException;

    void requestCapabilities(List<Uri> list, int i) throws RemoteException;

    void respondToCapabilityRequest(String str, RcsContactUceCapability rcsContactUceCapability, int i) throws RemoteException;

    void respondToCapabilityRequestWithError(Uri uri, int i, String str, int i2) throws RemoteException;

    void sendCapabilityRequest(Uri uri, RcsContactUceCapability rcsContactUceCapability, int i) throws RemoteException;

    void setListener(IRcsFeatureListener iRcsFeatureListener) throws RemoteException;

    void updateCapabilities(RcsContactUceCapability rcsContactUceCapability, int i) throws RemoteException;

    public static abstract class Stub extends Binder implements IImsRcsFeature {
        private static final String DESCRIPTOR = "android.telephony.ims.aidl.IImsRcsFeature";
        static final int TRANSACTION_addCapabilityCallback = 4;
        static final int TRANSACTION_changeCapabilitiesConfiguration = 6;
        static final int TRANSACTION_getFeatureState = 3;
        static final int TRANSACTION_queryCapabilityConfiguration = 7;
        static final int TRANSACTION_queryCapabilityStatus = 2;
        static final int TRANSACTION_removeCapabilityCallback = 5;
        static final int TRANSACTION_requestCapabilities = 8;
        static final int TRANSACTION_respondToCapabilityRequest = 11;
        static final int TRANSACTION_respondToCapabilityRequestWithError = 12;
        static final int TRANSACTION_sendCapabilityRequest = 10;
        static final int TRANSACTION_setListener = 1;
        static final int TRANSACTION_updateCapabilities = 9;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "setListener";
                case 2:
                    return "queryCapabilityStatus";
                case 3:
                    return "getFeatureState";
                case 4:
                    return "addCapabilityCallback";
                case 5:
                    return "removeCapabilityCallback";
                case 6:
                    return "changeCapabilitiesConfiguration";
                case 7:
                    return "queryCapabilityConfiguration";
                case 8:
                    return "requestCapabilities";
                case 9:
                    return "updateCapabilities";
                case 10:
                    return "sendCapabilityRequest";
                case 11:
                    return "respondToCapabilityRequest";
                case 12:
                    return "respondToCapabilityRequestWithError";
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

        public static IImsRcsFeature asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IImsRcsFeature)) {
                return (IImsRcsFeature) iInterfaceQueryLocalInterface;
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
                    setListener(IRcsFeatureListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iQueryCapabilityStatus = queryCapabilityStatus();
                    parcel2.writeNoException();
                    parcel2.writeInt(iQueryCapabilityStatus);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    int featureState = getFeatureState();
                    parcel2.writeNoException();
                    parcel2.writeInt(featureState);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    addCapabilityCallback(IImsCapabilityCallback.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    removeCapabilityCallback(IImsCapabilityCallback.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    changeCapabilitiesConfiguration(parcel.readInt() != 0 ? CapabilityChangeRequest.CREATOR.createFromParcel(parcel) : null, IImsCapabilityCallback.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    queryCapabilityConfiguration(parcel.readInt(), parcel.readInt(), IImsCapabilityCallback.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    requestCapabilities(parcel.createTypedArrayList(Uri.CREATOR), parcel.readInt());
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    updateCapabilities(parcel.readInt() != 0 ? RcsContactUceCapability.CREATOR.createFromParcel(parcel) : null, parcel.readInt());
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    sendCapabilityRequest(parcel.readInt() != 0 ? Uri.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? RcsContactUceCapability.CREATOR.createFromParcel(parcel) : null, parcel.readInt());
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    respondToCapabilityRequest(parcel.readString(), parcel.readInt() != 0 ? RcsContactUceCapability.CREATOR.createFromParcel(parcel) : null, parcel.readInt());
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    respondToCapabilityRequestWithError(parcel.readInt() != 0 ? Uri.CREATOR.createFromParcel(parcel) : null, parcel.readInt(), parcel.readString(), parcel.readInt());
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IImsRcsFeature {
            public static IImsRcsFeature sDefaultImpl;
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

            @Override // android.telephony.ims.aidl.IImsRcsFeature
            public void setListener(IRcsFeatureListener iRcsFeatureListener) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iRcsFeatureListener != null ? iRcsFeatureListener.asBinder() : null);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setListener(iRcsFeatureListener);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsRcsFeature
            public int queryCapabilityStatus() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().queryCapabilityStatus();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsRcsFeature
            public int getFeatureState() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getFeatureState();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsRcsFeature
            public void addCapabilityCallback(IImsCapabilityCallback iImsCapabilityCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iImsCapabilityCallback != null ? iImsCapabilityCallback.asBinder() : null);
                    if (this.mRemote.transact(4, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().addCapabilityCallback(iImsCapabilityCallback);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsRcsFeature
            public void removeCapabilityCallback(IImsCapabilityCallback iImsCapabilityCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iImsCapabilityCallback != null ? iImsCapabilityCallback.asBinder() : null);
                    if (this.mRemote.transact(5, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().removeCapabilityCallback(iImsCapabilityCallback);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsRcsFeature
            public void changeCapabilitiesConfiguration(CapabilityChangeRequest capabilityChangeRequest, IImsCapabilityCallback iImsCapabilityCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (capabilityChangeRequest != null) {
                        parcelObtain.writeInt(1);
                        capabilityChangeRequest.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeStrongBinder(iImsCapabilityCallback != null ? iImsCapabilityCallback.asBinder() : null);
                    if (this.mRemote.transact(6, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().changeCapabilitiesConfiguration(capabilityChangeRequest, iImsCapabilityCallback);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsRcsFeature
            public void queryCapabilityConfiguration(int i, int i2, IImsCapabilityCallback iImsCapabilityCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeStrongBinder(iImsCapabilityCallback != null ? iImsCapabilityCallback.asBinder() : null);
                    if (this.mRemote.transact(7, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().queryCapabilityConfiguration(i, i2, iImsCapabilityCallback);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsRcsFeature
            public void requestCapabilities(List<Uri> list, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeTypedList(list);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(8, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().requestCapabilities(list, i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsRcsFeature
            public void updateCapabilities(RcsContactUceCapability rcsContactUceCapability, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (rcsContactUceCapability != null) {
                        parcelObtain.writeInt(1);
                        rcsContactUceCapability.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(9, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().updateCapabilities(rcsContactUceCapability, i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsRcsFeature
            public void sendCapabilityRequest(Uri uri, RcsContactUceCapability rcsContactUceCapability, int i) throws RemoteException {
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
                    if (this.mRemote.transact(10, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().sendCapabilityRequest(uri, rcsContactUceCapability, i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsRcsFeature
            public void respondToCapabilityRequest(String str, RcsContactUceCapability rcsContactUceCapability, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (rcsContactUceCapability != null) {
                        parcelObtain.writeInt(1);
                        rcsContactUceCapability.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(11, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().respondToCapabilityRequest(str, rcsContactUceCapability, i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsRcsFeature
            public void respondToCapabilityRequestWithError(Uri uri, int i, String str, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (uri != null) {
                        parcelObtain.writeInt(1);
                        uri.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i2);
                    if (this.mRemote.transact(12, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().respondToCapabilityRequestWithError(uri, i, str, i2);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IImsRcsFeature iImsRcsFeature) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iImsRcsFeature == null) {
                return false;
            }
            Proxy.sDefaultImpl = iImsRcsFeature;
            return true;
        }

        public static IImsRcsFeature getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
