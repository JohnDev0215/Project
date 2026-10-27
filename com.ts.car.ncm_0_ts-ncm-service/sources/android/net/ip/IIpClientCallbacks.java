package android.net.ip;

import android.net.DhcpResultsParcelable;
import android.net.INetd;
import android.net.Layer2PacketParcelable;
import android.net.LinkProperties;
import android.net.ip.IIpClient;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface IIpClientCallbacks extends IInterface {
    public static final String HASH = "02cd6fd07d5c04eca0c35a350f7b0be576242883";
    public static final int VERSION = 7;

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

    public static class Default implements IIpClientCallbacks {
        @Override // android.net.ip.IIpClientCallbacks
        public void onIpClientCreated(IIpClient ipClient) throws RemoteException {
        }

        @Override // android.net.ip.IIpClientCallbacks
        public void onPreDhcpAction() throws RemoteException {
        }

        @Override // android.net.ip.IIpClientCallbacks
        public void onPostDhcpAction() throws RemoteException {
        }

        @Override // android.net.ip.IIpClientCallbacks
        public void onNewDhcpResults(DhcpResultsParcelable dhcpResults) throws RemoteException {
        }

        @Override // android.net.ip.IIpClientCallbacks
        public void onProvisioningSuccess(LinkProperties newLp) throws RemoteException {
        }

        @Override // android.net.ip.IIpClientCallbacks
        public void onProvisioningFailure(LinkProperties newLp) throws RemoteException {
        }

        @Override // android.net.ip.IIpClientCallbacks
        public void onLinkPropertiesChange(LinkProperties newLp) throws RemoteException {
        }

        @Override // android.net.ip.IIpClientCallbacks
        public void onReachabilityLost(String logMsg) throws RemoteException {
        }

        @Override // android.net.ip.IIpClientCallbacks
        public void onQuit() throws RemoteException {
        }

        @Override // android.net.ip.IIpClientCallbacks
        public void installPacketFilter(byte[] filter) throws RemoteException {
        }

        @Override // android.net.ip.IIpClientCallbacks
        public void startReadPacketFilter() throws RemoteException {
        }

        @Override // android.net.ip.IIpClientCallbacks
        public void setFallbackMulticastFilter(boolean enabled) throws RemoteException {
        }

        @Override // android.net.ip.IIpClientCallbacks
        public void setNeighborDiscoveryOffload(boolean enable) throws RemoteException {
        }

        @Override // android.net.ip.IIpClientCallbacks
        public void onPreconnectionStart(List<Layer2PacketParcelable> packets) throws RemoteException {
        }

        @Override // android.net.ip.IIpClientCallbacks
        public int getInterfaceVersion() {
            return 0;
        }

        @Override // android.net.ip.IIpClientCallbacks
        public String getInterfaceHash() {
            return INetd.NEXTHOP_NONE;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IIpClientCallbacks {
        private static final String DESCRIPTOR = "android$net$ip$IIpClientCallbacks".replace('$', '.');
        static final int TRANSACTION_getInterfaceHash = 16777214;
        static final int TRANSACTION_getInterfaceVersion = 16777215;
        static final int TRANSACTION_installPacketFilter = 10;
        static final int TRANSACTION_onIpClientCreated = 1;
        static final int TRANSACTION_onLinkPropertiesChange = 7;
        static final int TRANSACTION_onNewDhcpResults = 4;
        static final int TRANSACTION_onPostDhcpAction = 3;
        static final int TRANSACTION_onPreDhcpAction = 2;
        static final int TRANSACTION_onPreconnectionStart = 14;
        static final int TRANSACTION_onProvisioningFailure = 6;
        static final int TRANSACTION_onProvisioningSuccess = 5;
        static final int TRANSACTION_onQuit = 9;
        static final int TRANSACTION_onReachabilityLost = 8;
        static final int TRANSACTION_setFallbackMulticastFilter = 12;
        static final int TRANSACTION_setNeighborDiscoveryOffload = 13;
        static final int TRANSACTION_startReadPacketFilter = 11;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IIpClientCallbacks asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof IIpClientCallbacks)) {
                return (IIpClientCallbacks) iin;
            }
            return new Proxy(obj);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            DhcpResultsParcelable _arg0;
            LinkProperties _arg02;
            LinkProperties _arg03;
            LinkProperties _arg04;
            boolean _arg05;
            String descriptor = DESCRIPTOR;
            if (code == 1598968902) {
                reply.writeString(descriptor);
                return true;
            }
            switch (code) {
                case 1:
                    data.enforceInterface(descriptor);
                    onIpClientCreated(IIpClient.Stub.asInterface(data.readStrongBinder()));
                    return true;
                case 2:
                    data.enforceInterface(descriptor);
                    onPreDhcpAction();
                    return true;
                case 3:
                    data.enforceInterface(descriptor);
                    onPostDhcpAction();
                    return true;
                case 4:
                    data.enforceInterface(descriptor);
                    if (data.readInt() != 0) {
                        _arg0 = DhcpResultsParcelable.CREATOR.createFromParcel(data);
                    } else {
                        _arg0 = null;
                    }
                    onNewDhcpResults(_arg0);
                    return true;
                case TRANSACTION_onProvisioningSuccess /* 5 */:
                    data.enforceInterface(descriptor);
                    if (data.readInt() != 0) {
                        _arg02 = (LinkProperties) LinkProperties.CREATOR.createFromParcel(data);
                    } else {
                        _arg02 = null;
                    }
                    onProvisioningSuccess(_arg02);
                    return true;
                case 6:
                    data.enforceInterface(descriptor);
                    if (data.readInt() != 0) {
                        _arg03 = (LinkProperties) LinkProperties.CREATOR.createFromParcel(data);
                    } else {
                        _arg03 = null;
                    }
                    onProvisioningFailure(_arg03);
                    return true;
                case 7:
                    data.enforceInterface(descriptor);
                    if (data.readInt() != 0) {
                        _arg04 = (LinkProperties) LinkProperties.CREATOR.createFromParcel(data);
                    } else {
                        _arg04 = null;
                    }
                    onLinkPropertiesChange(_arg04);
                    return true;
                case 8:
                    data.enforceInterface(descriptor);
                    onReachabilityLost(data.readString());
                    return true;
                case TRANSACTION_onQuit /* 9 */:
                    data.enforceInterface(descriptor);
                    onQuit();
                    return true;
                case 10:
                    data.enforceInterface(descriptor);
                    installPacketFilter(data.createByteArray());
                    return true;
                case TRANSACTION_startReadPacketFilter /* 11 */:
                    data.enforceInterface(descriptor);
                    startReadPacketFilter();
                    return true;
                case 12:
                    data.enforceInterface(descriptor);
                    _arg05 = data.readInt() != 0;
                    setFallbackMulticastFilter(_arg05);
                    return true;
                case TRANSACTION_setNeighborDiscoveryOffload /* 13 */:
                    data.enforceInterface(descriptor);
                    _arg05 = data.readInt() != 0;
                    setNeighborDiscoveryOffload(_arg05);
                    return true;
                case TRANSACTION_onPreconnectionStart /* 14 */:
                    data.enforceInterface(descriptor);
                    onPreconnectionStart(data.createTypedArrayList(Layer2PacketParcelable.CREATOR));
                    return true;
                default:
                    switch (code) {
                        case TRANSACTION_getInterfaceHash /* 16777214 */:
                            data.enforceInterface(descriptor);
                            reply.writeNoException();
                            reply.writeString(getInterfaceHash());
                            return true;
                        case TRANSACTION_getInterfaceVersion /* 16777215 */:
                            data.enforceInterface(descriptor);
                            reply.writeNoException();
                            reply.writeInt(getInterfaceVersion());
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        private static class Proxy implements IIpClientCallbacks {
            public static IIpClientCallbacks sDefaultImpl;
            private IBinder mRemote;
            private int mCachedVersion = -1;
            private String mCachedHash = "-1";

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            @Override // android.net.ip.IIpClientCallbacks
            public void onIpClientCreated(IIpClient ipClient) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(ipClient != null ? ipClient.asBinder() : null);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onIpClientCreated(ipClient);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.net.ip.IIpClientCallbacks
            public void onPreDhcpAction() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onPreDhcpAction();
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.net.ip.IIpClientCallbacks
            public void onPostDhcpAction() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onPostDhcpAction();
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.net.ip.IIpClientCallbacks
            public void onNewDhcpResults(DhcpResultsParcelable dhcpResults) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (dhcpResults != null) {
                        _data.writeInt(1);
                        dhcpResults.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(4, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onNewDhcpResults(dhcpResults);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.net.ip.IIpClientCallbacks
            public void onProvisioningSuccess(LinkProperties newLp) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (newLp != null) {
                        _data.writeInt(1);
                        newLp.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_onProvisioningSuccess, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onProvisioningSuccess(newLp);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.net.ip.IIpClientCallbacks
            public void onProvisioningFailure(LinkProperties newLp) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (newLp != null) {
                        _data.writeInt(1);
                        newLp.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(6, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onProvisioningFailure(newLp);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.net.ip.IIpClientCallbacks
            public void onLinkPropertiesChange(LinkProperties newLp) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (newLp != null) {
                        _data.writeInt(1);
                        newLp.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(7, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onLinkPropertiesChange(newLp);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.net.ip.IIpClientCallbacks
            public void onReachabilityLost(String logMsg) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(logMsg);
                    boolean _status = this.mRemote.transact(8, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onReachabilityLost(logMsg);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.net.ip.IIpClientCallbacks
            public void onQuit() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_onQuit, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onQuit();
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.net.ip.IIpClientCallbacks
            public void installPacketFilter(byte[] filter) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByteArray(filter);
                    boolean _status = this.mRemote.transact(10, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().installPacketFilter(filter);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.net.ip.IIpClientCallbacks
            public void startReadPacketFilter() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_startReadPacketFilter, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().startReadPacketFilter();
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.net.ip.IIpClientCallbacks
            public void setFallbackMulticastFilter(boolean enabled) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(enabled ? 1 : 0);
                    boolean _status = this.mRemote.transact(12, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setFallbackMulticastFilter(enabled);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.net.ip.IIpClientCallbacks
            public void setNeighborDiscoveryOffload(boolean enable) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(enable ? 1 : 0);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_setNeighborDiscoveryOffload, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setNeighborDiscoveryOffload(enable);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.net.ip.IIpClientCallbacks
            public void onPreconnectionStart(List<Layer2PacketParcelable> packets) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeTypedList(packets);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_onPreconnectionStart, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onPreconnectionStart(packets);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.net.ip.IIpClientCallbacks
            public int getInterfaceVersion() throws RemoteException {
                if (this.mCachedVersion == -1) {
                    Parcel data = Parcel.obtain();
                    Parcel reply = Parcel.obtain();
                    try {
                        data.writeInterfaceToken(Stub.DESCRIPTOR);
                        boolean _status = this.mRemote.transact(Stub.TRANSACTION_getInterfaceVersion, data, reply, 0);
                        if (!_status && Stub.getDefaultImpl() != null) {
                            return Stub.getDefaultImpl().getInterfaceVersion();
                        }
                        reply.readException();
                        this.mCachedVersion = reply.readInt();
                    } finally {
                        reply.recycle();
                        data.recycle();
                    }
                }
                return this.mCachedVersion;
            }

            @Override // android.net.ip.IIpClientCallbacks
            public synchronized String getInterfaceHash() throws RemoteException {
                if ("-1".equals(this.mCachedHash)) {
                    Parcel data = Parcel.obtain();
                    Parcel reply = Parcel.obtain();
                    try {
                        data.writeInterfaceToken(Stub.DESCRIPTOR);
                        boolean _status = this.mRemote.transact(Stub.TRANSACTION_getInterfaceHash, data, reply, 0);
                        if (!_status && Stub.getDefaultImpl() != null) {
                            return Stub.getDefaultImpl().getInterfaceHash();
                        }
                        reply.readException();
                        this.mCachedHash = reply.readString();
                        reply.recycle();
                        data.recycle();
                    } finally {
                        reply.recycle();
                        data.recycle();
                    }
                }
                return this.mCachedHash;
            }
        }

        public static boolean setDefaultImpl(IIpClientCallbacks impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static IIpClientCallbacks getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
