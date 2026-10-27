package android.automotive.watchdog;

import android.automotive.watchdog.ICarWatchdogClient;
import android.automotive.watchdog.ICarWatchdogMonitor;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import kotlin.text.Typography;

/* JADX INFO: loaded from: classes.dex */
public interface ICarWatchdog extends IInterface {
    public static final String HASH = "f7adf2ef96b380c7fde3919f565eb764986bdcdd";
    public static final int VERSION = 2;

    String getInterfaceHash() throws RemoteException;

    int getInterfaceVersion() throws RemoteException;

    void notifySystemStateChange(int i, int i2, int i3) throws RemoteException;

    void registerClient(ICarWatchdogClient iCarWatchdogClient, int i) throws RemoteException;

    void registerMediator(ICarWatchdogClient iCarWatchdogClient) throws RemoteException;

    void registerMonitor(ICarWatchdogMonitor iCarWatchdogMonitor) throws RemoteException;

    void tellClientAlive(ICarWatchdogClient iCarWatchdogClient, int i) throws RemoteException;

    void tellDumpFinished(ICarWatchdogMonitor iCarWatchdogMonitor, int i) throws RemoteException;

    void tellMediatorAlive(ICarWatchdogClient iCarWatchdogClient, int[] iArr, int i) throws RemoteException;

    void unregisterClient(ICarWatchdogClient iCarWatchdogClient) throws RemoteException;

    void unregisterMediator(ICarWatchdogClient iCarWatchdogClient) throws RemoteException;

    void unregisterMonitor(ICarWatchdogMonitor iCarWatchdogMonitor) throws RemoteException;

    public static class Default implements ICarWatchdog {
        @Override // android.automotive.watchdog.ICarWatchdog
        public void registerClient(ICarWatchdogClient client, int timeout) throws RemoteException {
        }

        @Override // android.automotive.watchdog.ICarWatchdog
        public void unregisterClient(ICarWatchdogClient client) throws RemoteException {
        }

        @Override // android.automotive.watchdog.ICarWatchdog
        public void registerMediator(ICarWatchdogClient mediator) throws RemoteException {
        }

        @Override // android.automotive.watchdog.ICarWatchdog
        public void unregisterMediator(ICarWatchdogClient mediator) throws RemoteException {
        }

        @Override // android.automotive.watchdog.ICarWatchdog
        public void registerMonitor(ICarWatchdogMonitor monitor) throws RemoteException {
        }

        @Override // android.automotive.watchdog.ICarWatchdog
        public void unregisterMonitor(ICarWatchdogMonitor monitor) throws RemoteException {
        }

        @Override // android.automotive.watchdog.ICarWatchdog
        public void tellClientAlive(ICarWatchdogClient client, int sessionId) throws RemoteException {
        }

        @Override // android.automotive.watchdog.ICarWatchdog
        public void tellMediatorAlive(ICarWatchdogClient mediator, int[] clientsNotResponding, int sessionId) throws RemoteException {
        }

        @Override // android.automotive.watchdog.ICarWatchdog
        public void tellDumpFinished(ICarWatchdogMonitor monitor, int pid) throws RemoteException {
        }

        @Override // android.automotive.watchdog.ICarWatchdog
        public void notifySystemStateChange(int type, int arg1, int arg2) throws RemoteException {
        }

        @Override // android.automotive.watchdog.ICarWatchdog
        public int getInterfaceVersion() {
            return 0;
        }

        @Override // android.automotive.watchdog.ICarWatchdog
        public String getInterfaceHash() {
            return "";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements ICarWatchdog {
        private static final String DESCRIPTOR = "android$automotive$watchdog$ICarWatchdog".replace(Typography.dollar, '.');
        static final int TRANSACTION_getInterfaceHash = 16777214;
        static final int TRANSACTION_getInterfaceVersion = 16777215;
        static final int TRANSACTION_notifySystemStateChange = 10;
        static final int TRANSACTION_registerClient = 1;
        static final int TRANSACTION_registerMediator = 3;
        static final int TRANSACTION_registerMonitor = 5;
        static final int TRANSACTION_tellClientAlive = 7;
        static final int TRANSACTION_tellDumpFinished = 9;
        static final int TRANSACTION_tellMediatorAlive = 8;
        static final int TRANSACTION_unregisterClient = 2;
        static final int TRANSACTION_unregisterMediator = 4;
        static final int TRANSACTION_unregisterMonitor = 6;

        public Stub() {
            markVintfStability();
            attachInterface(this, DESCRIPTOR);
        }

        public static ICarWatchdog asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof ICarWatchdog)) {
                return (ICarWatchdog) iin;
            }
            return new Proxy(obj);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            String descriptor = DESCRIPTOR;
            if (code == 1598968902) {
                reply.writeString(descriptor);
                return true;
            }
            switch (code) {
                case 1:
                    data.enforceInterface(descriptor);
                    ICarWatchdogClient _arg0 = ICarWatchdogClient.Stub.asInterface(data.readStrongBinder());
                    int _arg1 = data.readInt();
                    registerClient(_arg0, _arg1);
                    reply.writeNoException();
                    return true;
                case 2:
                    data.enforceInterface(descriptor);
                    ICarWatchdogClient _arg02 = ICarWatchdogClient.Stub.asInterface(data.readStrongBinder());
                    unregisterClient(_arg02);
                    reply.writeNoException();
                    return true;
                case 3:
                    data.enforceInterface(descriptor);
                    ICarWatchdogClient _arg03 = ICarWatchdogClient.Stub.asInterface(data.readStrongBinder());
                    registerMediator(_arg03);
                    reply.writeNoException();
                    return true;
                case 4:
                    data.enforceInterface(descriptor);
                    ICarWatchdogClient _arg04 = ICarWatchdogClient.Stub.asInterface(data.readStrongBinder());
                    unregisterMediator(_arg04);
                    reply.writeNoException();
                    return true;
                case 5:
                    data.enforceInterface(descriptor);
                    ICarWatchdogMonitor _arg05 = ICarWatchdogMonitor.Stub.asInterface(data.readStrongBinder());
                    registerMonitor(_arg05);
                    reply.writeNoException();
                    return true;
                case 6:
                    data.enforceInterface(descriptor);
                    ICarWatchdogMonitor _arg06 = ICarWatchdogMonitor.Stub.asInterface(data.readStrongBinder());
                    unregisterMonitor(_arg06);
                    reply.writeNoException();
                    return true;
                case 7:
                    data.enforceInterface(descriptor);
                    ICarWatchdogClient _arg07 = ICarWatchdogClient.Stub.asInterface(data.readStrongBinder());
                    int _arg12 = data.readInt();
                    tellClientAlive(_arg07, _arg12);
                    reply.writeNoException();
                    return true;
                case 8:
                    data.enforceInterface(descriptor);
                    ICarWatchdogClient _arg08 = ICarWatchdogClient.Stub.asInterface(data.readStrongBinder());
                    int[] _arg13 = data.createIntArray();
                    int _arg2 = data.readInt();
                    tellMediatorAlive(_arg08, _arg13, _arg2);
                    reply.writeNoException();
                    return true;
                case 9:
                    data.enforceInterface(descriptor);
                    ICarWatchdogMonitor _arg09 = ICarWatchdogMonitor.Stub.asInterface(data.readStrongBinder());
                    int _arg14 = data.readInt();
                    tellDumpFinished(_arg09, _arg14);
                    reply.writeNoException();
                    return true;
                case 10:
                    data.enforceInterface(descriptor);
                    int _arg010 = data.readInt();
                    int _arg15 = data.readInt();
                    int _arg22 = data.readInt();
                    notifySystemStateChange(_arg010, _arg15, _arg22);
                    reply.writeNoException();
                    return true;
                default:
                    switch (code) {
                        case TRANSACTION_getInterfaceHash /* 16777214 */:
                            data.enforceInterface(descriptor);
                            reply.writeNoException();
                            reply.writeString(getInterfaceHash());
                            return true;
                        case 16777215:
                            data.enforceInterface(descriptor);
                            reply.writeNoException();
                            reply.writeInt(getInterfaceVersion());
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        private static class Proxy implements ICarWatchdog {
            public static ICarWatchdog sDefaultImpl;
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

            @Override // android.automotive.watchdog.ICarWatchdog
            public void registerClient(ICarWatchdogClient client, int timeout) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(client != null ? client.asBinder() : null);
                    _data.writeInt(timeout);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerClient(client, timeout);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.automotive.watchdog.ICarWatchdog
            public void unregisterClient(ICarWatchdogClient client) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(client != null ? client.asBinder() : null);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterClient(client);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.automotive.watchdog.ICarWatchdog
            public void registerMediator(ICarWatchdogClient mediator) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(mediator != null ? mediator.asBinder() : null);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerMediator(mediator);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.automotive.watchdog.ICarWatchdog
            public void unregisterMediator(ICarWatchdogClient mediator) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(mediator != null ? mediator.asBinder() : null);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterMediator(mediator);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.automotive.watchdog.ICarWatchdog
            public void registerMonitor(ICarWatchdogMonitor monitor) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(monitor != null ? monitor.asBinder() : null);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerMonitor(monitor);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.automotive.watchdog.ICarWatchdog
            public void unregisterMonitor(ICarWatchdogMonitor monitor) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(monitor != null ? monitor.asBinder() : null);
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterMonitor(monitor);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.automotive.watchdog.ICarWatchdog
            public void tellClientAlive(ICarWatchdogClient client, int sessionId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(client != null ? client.asBinder() : null);
                    _data.writeInt(sessionId);
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().tellClientAlive(client, sessionId);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.automotive.watchdog.ICarWatchdog
            public void tellMediatorAlive(ICarWatchdogClient mediator, int[] clientsNotResponding, int sessionId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(mediator != null ? mediator.asBinder() : null);
                    _data.writeIntArray(clientsNotResponding);
                    _data.writeInt(sessionId);
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().tellMediatorAlive(mediator, clientsNotResponding, sessionId);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.automotive.watchdog.ICarWatchdog
            public void tellDumpFinished(ICarWatchdogMonitor monitor, int pid) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(monitor != null ? monitor.asBinder() : null);
                    _data.writeInt(pid);
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().tellDumpFinished(monitor, pid);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.automotive.watchdog.ICarWatchdog
            public void notifySystemStateChange(int type, int arg1, int arg2) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(type);
                    _data.writeInt(arg1);
                    _data.writeInt(arg2);
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifySystemStateChange(type, arg1, arg2);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.automotive.watchdog.ICarWatchdog
            public int getInterfaceVersion() throws RemoteException {
                if (this.mCachedVersion == -1) {
                    Parcel data = Parcel.obtain();
                    Parcel reply = Parcel.obtain();
                    try {
                        data.writeInterfaceToken(Stub.DESCRIPTOR);
                        boolean _status = this.mRemote.transact(16777215, data, reply, 0);
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

            @Override // android.automotive.watchdog.ICarWatchdog
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

        public static boolean setDefaultImpl(ICarWatchdog impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static ICarWatchdog getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
