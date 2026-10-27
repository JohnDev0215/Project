package android.net;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public interface IIpSecService extends IInterface {

    public static class Default implements IIpSecService {
        @Override // android.net.IIpSecService
        public void addAddressToTunnelInterface(int i, LinkAddress linkAddress, String str) throws RemoteException {
        }

        @Override // android.net.IIpSecService
        public IpSecSpiResponse allocateSecurityParameterIndex(String str, int i, IBinder iBinder) throws RemoteException {
            return null;
        }

        @Override // android.net.IIpSecService
        public void applyTransportModeTransform(ParcelFileDescriptor parcelFileDescriptor, int i, int i2) throws RemoteException {
        }

        @Override // android.net.IIpSecService
        public void applyTunnelModeTransform(int i, int i2, int i3, String str) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.net.IIpSecService
        public void closeUdpEncapsulationSocket(int i) throws RemoteException {
        }

        @Override // android.net.IIpSecService
        public IpSecTransformResponse createTransform(IpSecConfig ipSecConfig, IBinder iBinder, String str) throws RemoteException {
            return null;
        }

        @Override // android.net.IIpSecService
        public IpSecTunnelInterfaceResponse createTunnelInterface(String str, String str2, Network network, IBinder iBinder, String str3) throws RemoteException {
            return null;
        }

        @Override // android.net.IIpSecService
        public void deleteTransform(int i) throws RemoteException {
        }

        @Override // android.net.IIpSecService
        public void deleteTunnelInterface(int i, String str) throws RemoteException {
        }

        @Override // android.net.IIpSecService
        public IpSecUdpEncapResponse openUdpEncapsulationSocket(int i, IBinder iBinder) throws RemoteException {
            return null;
        }

        @Override // android.net.IIpSecService
        public void releaseSecurityParameterIndex(int i) throws RemoteException {
        }

        @Override // android.net.IIpSecService
        public void removeAddressFromTunnelInterface(int i, LinkAddress linkAddress, String str) throws RemoteException {
        }

        @Override // android.net.IIpSecService
        public void removeTransportModeTransforms(ParcelFileDescriptor parcelFileDescriptor) throws RemoteException {
        }
    }

    void addAddressToTunnelInterface(int i, LinkAddress linkAddress, String str) throws RemoteException;

    IpSecSpiResponse allocateSecurityParameterIndex(String str, int i, IBinder iBinder) throws RemoteException;

    void applyTransportModeTransform(ParcelFileDescriptor parcelFileDescriptor, int i, int i2) throws RemoteException;

    void applyTunnelModeTransform(int i, int i2, int i3, String str) throws RemoteException;

    void closeUdpEncapsulationSocket(int i) throws RemoteException;

    IpSecTransformResponse createTransform(IpSecConfig ipSecConfig, IBinder iBinder, String str) throws RemoteException;

    IpSecTunnelInterfaceResponse createTunnelInterface(String str, String str2, Network network, IBinder iBinder, String str3) throws RemoteException;

    void deleteTransform(int i) throws RemoteException;

    void deleteTunnelInterface(int i, String str) throws RemoteException;

    IpSecUdpEncapResponse openUdpEncapsulationSocket(int i, IBinder iBinder) throws RemoteException;

    void releaseSecurityParameterIndex(int i) throws RemoteException;

    void removeAddressFromTunnelInterface(int i, LinkAddress linkAddress, String str) throws RemoteException;

    void removeTransportModeTransforms(ParcelFileDescriptor parcelFileDescriptor) throws RemoteException;

    public static abstract class Stub extends Binder implements IIpSecService {
        private static final String DESCRIPTOR = "android.net.IIpSecService";
        static final int TRANSACTION_addAddressToTunnelInterface = 6;
        static final int TRANSACTION_allocateSecurityParameterIndex = 1;
        static final int TRANSACTION_applyTransportModeTransform = 11;
        static final int TRANSACTION_applyTunnelModeTransform = 12;
        static final int TRANSACTION_closeUdpEncapsulationSocket = 4;
        static final int TRANSACTION_createTransform = 9;
        static final int TRANSACTION_createTunnelInterface = 5;
        static final int TRANSACTION_deleteTransform = 10;
        static final int TRANSACTION_deleteTunnelInterface = 8;
        static final int TRANSACTION_openUdpEncapsulationSocket = 3;
        static final int TRANSACTION_releaseSecurityParameterIndex = 2;
        static final int TRANSACTION_removeAddressFromTunnelInterface = 7;
        static final int TRANSACTION_removeTransportModeTransforms = 13;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "allocateSecurityParameterIndex";
                case 2:
                    return "releaseSecurityParameterIndex";
                case 3:
                    return "openUdpEncapsulationSocket";
                case 4:
                    return "closeUdpEncapsulationSocket";
                case 5:
                    return "createTunnelInterface";
                case 6:
                    return "addAddressToTunnelInterface";
                case 7:
                    return "removeAddressFromTunnelInterface";
                case 8:
                    return "deleteTunnelInterface";
                case 9:
                    return "createTransform";
                case 10:
                    return "deleteTransform";
                case 11:
                    return "applyTransportModeTransform";
                case 12:
                    return "applyTunnelModeTransform";
                case 13:
                    return "removeTransportModeTransforms";
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

        public static IIpSecService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IIpSecService)) {
                return (IIpSecService) iInterfaceQueryLocalInterface;
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
                    IpSecSpiResponse ipSecSpiResponseAllocateSecurityParameterIndex = allocateSecurityParameterIndex(parcel.readString(), parcel.readInt(), parcel.readStrongBinder());
                    parcel2.writeNoException();
                    if (ipSecSpiResponseAllocateSecurityParameterIndex != null) {
                        parcel2.writeInt(1);
                        ipSecSpiResponseAllocateSecurityParameterIndex.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    releaseSecurityParameterIndex(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    IpSecUdpEncapResponse ipSecUdpEncapResponseOpenUdpEncapsulationSocket = openUdpEncapsulationSocket(parcel.readInt(), parcel.readStrongBinder());
                    parcel2.writeNoException();
                    if (ipSecUdpEncapResponseOpenUdpEncapsulationSocket != null) {
                        parcel2.writeInt(1);
                        ipSecUdpEncapResponseOpenUdpEncapsulationSocket.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    closeUdpEncapsulationSocket(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    IpSecTunnelInterfaceResponse ipSecTunnelInterfaceResponseCreateTunnelInterface = createTunnelInterface(parcel.readString(), parcel.readString(), parcel.readInt() != 0 ? Network.CREATOR.createFromParcel(parcel) : null, parcel.readStrongBinder(), parcel.readString());
                    parcel2.writeNoException();
                    if (ipSecTunnelInterfaceResponseCreateTunnelInterface != null) {
                        parcel2.writeInt(1);
                        ipSecTunnelInterfaceResponseCreateTunnelInterface.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    addAddressToTunnelInterface(parcel.readInt(), parcel.readInt() != 0 ? LinkAddress.CREATOR.createFromParcel(parcel) : null, parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    removeAddressFromTunnelInterface(parcel.readInt(), parcel.readInt() != 0 ? LinkAddress.CREATOR.createFromParcel(parcel) : null, parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    deleteTunnelInterface(parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    IpSecTransformResponse ipSecTransformResponseCreateTransform = createTransform(parcel.readInt() != 0 ? IpSecConfig.CREATOR.createFromParcel(parcel) : null, parcel.readStrongBinder(), parcel.readString());
                    parcel2.writeNoException();
                    if (ipSecTransformResponseCreateTransform != null) {
                        parcel2.writeInt(1);
                        ipSecTransformResponseCreateTransform.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    deleteTransform(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    applyTransportModeTransform(parcel.readInt() != 0 ? ParcelFileDescriptor.CREATOR.createFromParcel(parcel) : null, parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    applyTunnelModeTransform(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    removeTransportModeTransforms(parcel.readInt() != 0 ? ParcelFileDescriptor.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IIpSecService {
            public static IIpSecService sDefaultImpl;
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

            @Override // android.net.IIpSecService
            public IpSecSpiResponse allocateSecurityParameterIndex(String str, int i, IBinder iBinder) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iBinder);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().allocateSecurityParameterIndex(str, i, iBinder);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? IpSecSpiResponse.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.IIpSecService
            public void releaseSecurityParameterIndex(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().releaseSecurityParameterIndex(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.IIpSecService
            public IpSecUdpEncapResponse openUdpEncapsulationSocket(int i, IBinder iBinder) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iBinder);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().openUdpEncapsulationSocket(i, iBinder);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? IpSecUdpEncapResponse.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.IIpSecService
            public void closeUdpEncapsulationSocket(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().closeUdpEncapsulationSocket(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.IIpSecService
            public IpSecTunnelInterfaceResponse createTunnelInterface(String str, String str2, Network network, IBinder iBinder, String str3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (network != null) {
                        parcelObtain.writeInt(1);
                        network.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeStrongBinder(iBinder);
                    parcelObtain.writeString(str3);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().createTunnelInterface(str, str2, network, iBinder, str3);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? IpSecTunnelInterfaceResponse.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.IIpSecService
            public void addAddressToTunnelInterface(int i, LinkAddress linkAddress, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (linkAddress != null) {
                        parcelObtain.writeInt(1);
                        linkAddress.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().addAddressToTunnelInterface(i, linkAddress, str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.IIpSecService
            public void removeAddressFromTunnelInterface(int i, LinkAddress linkAddress, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (linkAddress != null) {
                        parcelObtain.writeInt(1);
                        linkAddress.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().removeAddressFromTunnelInterface(i, linkAddress, str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.IIpSecService
            public void deleteTunnelInterface(int i, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().deleteTunnelInterface(i, str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.IIpSecService
            public IpSecTransformResponse createTransform(IpSecConfig ipSecConfig, IBinder iBinder, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (ipSecConfig != null) {
                        parcelObtain.writeInt(1);
                        ipSecConfig.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeStrongBinder(iBinder);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().createTransform(ipSecConfig, iBinder, str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? IpSecTransformResponse.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.IIpSecService
            public void deleteTransform(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(10, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().deleteTransform(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.IIpSecService
            public void applyTransportModeTransform(ParcelFileDescriptor parcelFileDescriptor, int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (parcelFileDescriptor != null) {
                        parcelObtain.writeInt(1);
                        parcelFileDescriptor.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(11, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().applyTransportModeTransform(parcelFileDescriptor, i, i2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.IIpSecService
            public void applyTunnelModeTransform(int i, int i2, int i3, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(12, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().applyTunnelModeTransform(i, i2, i3, str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.IIpSecService
            public void removeTransportModeTransforms(ParcelFileDescriptor parcelFileDescriptor) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (parcelFileDescriptor != null) {
                        parcelObtain.writeInt(1);
                        parcelFileDescriptor.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(13, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().removeTransportModeTransforms(parcelFileDescriptor);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IIpSecService iIpSecService) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iIpSecService == null) {
                return false;
            }
            Proxy.sDefaultImpl = iIpSecService;
            return true;
        }

        public static IIpSecService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
