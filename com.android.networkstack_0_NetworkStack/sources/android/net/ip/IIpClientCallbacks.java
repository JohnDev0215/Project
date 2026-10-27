package android.net.ip;

import android.net.DhcpResultsParcelable;
import android.net.Layer2PacketParcelable;
import android.net.LinkProperties;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface IIpClientCallbacks extends IInterface {
    String getInterfaceHash() throws RemoteException;

    int getInterfaceVersion() throws RemoteException;

    void installPacketFilter(byte[] bArr) throws RemoteException;

    void onIpClientCreated(IIpClient iIpClient) throws RemoteException;

    void onLinkPropertiesChange(LinkProperties linkProperties) throws RemoteException;

    void onNewDhcpResults(DhcpResultsParcelable dhcpResultsParcelable) throws RemoteException;

    void onPostDhcpAction() throws RemoteException;

    void onPreDhcpAction() throws RemoteException;

    void onPreconnectionStart(List<Layer2PacketParcelable> list) throws RemoteException;

    void onProvisioningFailure(LinkProperties linkProperties) throws RemoteException;

    void onProvisioningSuccess(LinkProperties linkProperties) throws RemoteException;

    void onQuit() throws RemoteException;

    void onReachabilityLost(String str) throws RemoteException;

    void setFallbackMulticastFilter(boolean z) throws RemoteException;

    void setNeighborDiscoveryOffload(boolean z) throws RemoteException;

    void startReadPacketFilter() throws RemoteException;

    public static abstract class Stub extends Binder implements IIpClientCallbacks {
        private static final String DESCRIPTOR = "android$net$ip$IIpClientCallbacks".replace('$', '.');

        public static IIpClientCallbacks asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IIpClientCallbacks)) {
                return (IIpClientCallbacks) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        private static class Proxy implements IIpClientCallbacks {
            public static IIpClientCallbacks sDefaultImpl;
            private IBinder mRemote;
            private int mCachedVersion = -1;
            private String mCachedHash = "-1";

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // android.net.ip.IIpClientCallbacks
            public void onIpClientCreated(IIpClient iIpClient) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iIpClient != null ? iIpClient.asBinder() : null);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onIpClientCreated(iIpClient);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.ip.IIpClientCallbacks
            public void onPreDhcpAction() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onPreDhcpAction();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.ip.IIpClientCallbacks
            public void onPostDhcpAction() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onPostDhcpAction();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.ip.IIpClientCallbacks
            public void onNewDhcpResults(DhcpResultsParcelable dhcpResultsParcelable) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (dhcpResultsParcelable != null) {
                        parcelObtain.writeInt(1);
                        dhcpResultsParcelable.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(4, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onNewDhcpResults(dhcpResultsParcelable);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.ip.IIpClientCallbacks
            public void onProvisioningSuccess(LinkProperties linkProperties) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (linkProperties != null) {
                        parcelObtain.writeInt(1);
                        linkProperties.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(5, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onProvisioningSuccess(linkProperties);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.ip.IIpClientCallbacks
            public void onProvisioningFailure(LinkProperties linkProperties) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (linkProperties != null) {
                        parcelObtain.writeInt(1);
                        linkProperties.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(6, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onProvisioningFailure(linkProperties);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.ip.IIpClientCallbacks
            public void onLinkPropertiesChange(LinkProperties linkProperties) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (linkProperties != null) {
                        parcelObtain.writeInt(1);
                        linkProperties.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(7, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onLinkPropertiesChange(linkProperties);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.ip.IIpClientCallbacks
            public void onReachabilityLost(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (this.mRemote.transact(8, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onReachabilityLost(str);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.ip.IIpClientCallbacks
            public void onQuit() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(9, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onQuit();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.ip.IIpClientCallbacks
            public void installPacketFilter(byte[] bArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeByteArray(bArr);
                    if (this.mRemote.transact(10, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().installPacketFilter(bArr);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.ip.IIpClientCallbacks
            public void startReadPacketFilter() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(11, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().startReadPacketFilter();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.ip.IIpClientCallbacks
            public void setFallbackMulticastFilter(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (this.mRemote.transact(12, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().setFallbackMulticastFilter(z);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.ip.IIpClientCallbacks
            public void setNeighborDiscoveryOffload(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (this.mRemote.transact(13, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().setNeighborDiscoveryOffload(z);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.ip.IIpClientCallbacks
            public void onPreconnectionStart(List<Layer2PacketParcelable> list) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeTypedList(list);
                    if (this.mRemote.transact(14, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onPreconnectionStart(list);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.ip.IIpClientCallbacks
            public int getInterfaceVersion() throws RemoteException {
                if (this.mCachedVersion == -1) {
                    Parcel parcelObtain = Parcel.obtain();
                    Parcel parcelObtain2 = Parcel.obtain();
                    try {
                        parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                        if (!this.mRemote.transact(16777215, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                            return Stub.getDefaultImpl().getInterfaceVersion();
                        }
                        parcelObtain2.readException();
                        this.mCachedVersion = parcelObtain2.readInt();
                    } finally {
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                    }
                }
                return this.mCachedVersion;
            }

            @Override // android.net.ip.IIpClientCallbacks
            public synchronized String getInterfaceHash() throws RemoteException {
                if ("-1".equals(this.mCachedHash)) {
                    Parcel parcelObtain = Parcel.obtain();
                    Parcel parcelObtain2 = Parcel.obtain();
                    try {
                        parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                        if (!this.mRemote.transact(16777214, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                            return Stub.getDefaultImpl().getInterfaceHash();
                        }
                        parcelObtain2.readException();
                        this.mCachedHash = parcelObtain2.readString();
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                    } finally {
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                    }
                }
                return this.mCachedHash;
            }
        }

        public static IIpClientCallbacks getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
