package android.telephony.ims.aidl;

import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.telephony.ims.aidl.IImsCapabilityCallback;
import android.telephony.ims.aidl.IImsRegistrationCallback;
import android.telephony.ims.aidl.IRcsUceControllerCallback;
import com.android.internal.telephony.IIntegerConsumer;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public interface IImsRcsController extends IInterface {

    public static class Default implements IImsRcsController {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.telephony.ims.aidl.IImsRcsController
        public void getImsRcsRegistrationState(int i, IIntegerConsumer iIntegerConsumer) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IImsRcsController
        public void getImsRcsRegistrationTransportType(int i, IIntegerConsumer iIntegerConsumer) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IImsRcsController
        public int getUcePublishState(int i) throws RemoteException {
            return 0;
        }

        @Override // android.telephony.ims.aidl.IImsRcsController
        public boolean isAvailable(int i, int i2) throws RemoteException {
            return false;
        }

        @Override // android.telephony.ims.aidl.IImsRcsController
        public boolean isCapable(int i, int i2, int i3) throws RemoteException {
            return false;
        }

        @Override // android.telephony.ims.aidl.IImsRcsController
        public boolean isUceSettingEnabled(int i, String str, String str2) throws RemoteException {
            return false;
        }

        @Override // android.telephony.ims.aidl.IImsRcsController
        public void registerImsRegistrationCallback(int i, IImsRegistrationCallback iImsRegistrationCallback) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IImsRcsController
        public void registerRcsAvailabilityCallback(int i, IImsCapabilityCallback iImsCapabilityCallback) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IImsRcsController
        public void requestCapabilities(int i, String str, String str2, List<Uri> list, IRcsUceControllerCallback iRcsUceControllerCallback) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IImsRcsController
        public void setUceSettingEnabled(int i, boolean z) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IImsRcsController
        public void unregisterImsRegistrationCallback(int i, IImsRegistrationCallback iImsRegistrationCallback) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IImsRcsController
        public void unregisterRcsAvailabilityCallback(int i, IImsCapabilityCallback iImsCapabilityCallback) throws RemoteException {
        }
    }

    void getImsRcsRegistrationState(int i, IIntegerConsumer iIntegerConsumer) throws RemoteException;

    void getImsRcsRegistrationTransportType(int i, IIntegerConsumer iIntegerConsumer) throws RemoteException;

    int getUcePublishState(int i) throws RemoteException;

    boolean isAvailable(int i, int i2) throws RemoteException;

    boolean isCapable(int i, int i2, int i3) throws RemoteException;

    boolean isUceSettingEnabled(int i, String str, String str2) throws RemoteException;

    void registerImsRegistrationCallback(int i, IImsRegistrationCallback iImsRegistrationCallback) throws RemoteException;

    void registerRcsAvailabilityCallback(int i, IImsCapabilityCallback iImsCapabilityCallback) throws RemoteException;

    void requestCapabilities(int i, String str, String str2, List<Uri> list, IRcsUceControllerCallback iRcsUceControllerCallback) throws RemoteException;

    void setUceSettingEnabled(int i, boolean z) throws RemoteException;

    void unregisterImsRegistrationCallback(int i, IImsRegistrationCallback iImsRegistrationCallback) throws RemoteException;

    void unregisterRcsAvailabilityCallback(int i, IImsCapabilityCallback iImsCapabilityCallback) throws RemoteException;

    public static abstract class Stub extends Binder implements IImsRcsController {
        private static final String DESCRIPTOR = "android.telephony.ims.aidl.IImsRcsController";
        static final int TRANSACTION_getImsRcsRegistrationState = 3;
        static final int TRANSACTION_getImsRcsRegistrationTransportType = 4;
        static final int TRANSACTION_getUcePublishState = 10;
        static final int TRANSACTION_isAvailable = 8;
        static final int TRANSACTION_isCapable = 7;
        static final int TRANSACTION_isUceSettingEnabled = 11;
        static final int TRANSACTION_registerImsRegistrationCallback = 1;
        static final int TRANSACTION_registerRcsAvailabilityCallback = 5;
        static final int TRANSACTION_requestCapabilities = 9;
        static final int TRANSACTION_setUceSettingEnabled = 12;
        static final int TRANSACTION_unregisterImsRegistrationCallback = 2;
        static final int TRANSACTION_unregisterRcsAvailabilityCallback = 6;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "registerImsRegistrationCallback";
                case 2:
                    return "unregisterImsRegistrationCallback";
                case 3:
                    return "getImsRcsRegistrationState";
                case 4:
                    return "getImsRcsRegistrationTransportType";
                case 5:
                    return "registerRcsAvailabilityCallback";
                case 6:
                    return "unregisterRcsAvailabilityCallback";
                case 7:
                    return "isCapable";
                case 8:
                    return "isAvailable";
                case 9:
                    return "requestCapabilities";
                case 10:
                    return "getUcePublishState";
                case 11:
                    return "isUceSettingEnabled";
                case 12:
                    return "setUceSettingEnabled";
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

        public static IImsRcsController asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IImsRcsController)) {
                return (IImsRcsController) iInterfaceQueryLocalInterface;
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
                    registerImsRegistrationCallback(parcel.readInt(), IImsRegistrationCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    unregisterImsRegistrationCallback(parcel.readInt(), IImsRegistrationCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    getImsRcsRegistrationState(parcel.readInt(), IIntegerConsumer.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    getImsRcsRegistrationTransportType(parcel.readInt(), IIntegerConsumer.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    registerRcsAvailabilityCallback(parcel.readInt(), IImsCapabilityCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    unregisterRcsAvailabilityCallback(parcel.readInt(), IImsCapabilityCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsCapable = isCapable(parcel.readInt(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsCapable ? 1 : 0);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsAvailable = isAvailable(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsAvailable ? 1 : 0);
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    requestCapabilities(parcel.readInt(), parcel.readString(), parcel.readString(), parcel.createTypedArrayList(Uri.CREATOR), IRcsUceControllerCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    int ucePublishState = getUcePublishState(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(ucePublishState);
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsUceSettingEnabled = isUceSettingEnabled(parcel.readInt(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsUceSettingEnabled ? 1 : 0);
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    setUceSettingEnabled(parcel.readInt(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IImsRcsController {
            public static IImsRcsController sDefaultImpl;
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

            @Override // android.telephony.ims.aidl.IImsRcsController
            public void registerImsRegistrationCallback(int i, IImsRegistrationCallback iImsRegistrationCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iImsRegistrationCallback != null ? iImsRegistrationCallback.asBinder() : null);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerImsRegistrationCallback(i, iImsRegistrationCallback);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsRcsController
            public void unregisterImsRegistrationCallback(int i, IImsRegistrationCallback iImsRegistrationCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iImsRegistrationCallback != null ? iImsRegistrationCallback.asBinder() : null);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterImsRegistrationCallback(i, iImsRegistrationCallback);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsRcsController
            public void getImsRcsRegistrationState(int i, IIntegerConsumer iIntegerConsumer) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iIntegerConsumer != null ? iIntegerConsumer.asBinder() : null);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getImsRcsRegistrationState(i, iIntegerConsumer);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsRcsController
            public void getImsRcsRegistrationTransportType(int i, IIntegerConsumer iIntegerConsumer) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iIntegerConsumer != null ? iIntegerConsumer.asBinder() : null);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getImsRcsRegistrationTransportType(i, iIntegerConsumer);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsRcsController
            public void registerRcsAvailabilityCallback(int i, IImsCapabilityCallback iImsCapabilityCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iImsCapabilityCallback != null ? iImsCapabilityCallback.asBinder() : null);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerRcsAvailabilityCallback(i, iImsCapabilityCallback);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsRcsController
            public void unregisterRcsAvailabilityCallback(int i, IImsCapabilityCallback iImsCapabilityCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iImsCapabilityCallback != null ? iImsCapabilityCallback.asBinder() : null);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterRcsAvailabilityCallback(i, iImsCapabilityCallback);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsRcsController
            public boolean isCapable(int i, int i2, int i3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isCapable(i, i2, i3);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsRcsController
            public boolean isAvailable(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isAvailable(i, i2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsRcsController
            public void requestCapabilities(int i, String str, String str2, List<Uri> list, IRcsUceControllerCallback iRcsUceControllerCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeTypedList(list);
                    parcelObtain.writeStrongBinder(iRcsUceControllerCallback != null ? iRcsUceControllerCallback.asBinder() : null);
                    if (!this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().requestCapabilities(i, str, str2, list, iRcsUceControllerCallback);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsRcsController
            public int getUcePublishState(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(10, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getUcePublishState(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsRcsController
            public boolean isUceSettingEnabled(int i, String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(11, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isUceSettingEnabled(i, str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsRcsController
            public void setUceSettingEnabled(int i, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(12, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setUceSettingEnabled(i, z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IImsRcsController iImsRcsController) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iImsRcsController == null) {
                return false;
            }
            Proxy.sDefaultImpl = iImsRcsController;
            return true;
        }

        public static IImsRcsController getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
