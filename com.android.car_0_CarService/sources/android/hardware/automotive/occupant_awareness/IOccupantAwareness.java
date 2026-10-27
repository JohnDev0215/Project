package android.hardware.automotive.occupant_awareness;

import android.hardware.automotive.occupant_awareness.IOccupantAwarenessClientCallback;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import kotlin.text.Typography;

/* JADX INFO: loaded from: classes.dex */
public interface IOccupantAwareness extends IInterface {
    public static final int CAP_DRIVER_MONITORING_DETECTION = 4;
    public static final int CAP_GAZE_DETECTION = 2;
    public static final int CAP_NONE = 0;
    public static final int CAP_PRESENCE_DETECTION = 1;
    public static final String HASH = "3614b1c47ed7be85c1e77554e7f04966cf35b465";
    public static final int VERSION = 1;

    int getCapabilityForRole(int i) throws RemoteException;

    String getInterfaceHash() throws RemoteException;

    int getInterfaceVersion() throws RemoteException;

    void getLatestDetection(OccupantDetections occupantDetections) throws RemoteException;

    byte getState(int i, int i2) throws RemoteException;

    void setCallback(IOccupantAwarenessClientCallback iOccupantAwarenessClientCallback) throws RemoteException;

    byte startDetection() throws RemoteException;

    byte stopDetection() throws RemoteException;

    public static class Default implements IOccupantAwareness {
        @Override // android.hardware.automotive.occupant_awareness.IOccupantAwareness
        public byte startDetection() throws RemoteException {
            return (byte) 0;
        }

        @Override // android.hardware.automotive.occupant_awareness.IOccupantAwareness
        public byte stopDetection() throws RemoteException {
            return (byte) 0;
        }

        @Override // android.hardware.automotive.occupant_awareness.IOccupantAwareness
        public int getCapabilityForRole(int occupantRole) throws RemoteException {
            return 0;
        }

        @Override // android.hardware.automotive.occupant_awareness.IOccupantAwareness
        public byte getState(int occupantRole, int detectionCapability) throws RemoteException {
            return (byte) 0;
        }

        @Override // android.hardware.automotive.occupant_awareness.IOccupantAwareness
        public void setCallback(IOccupantAwarenessClientCallback callback) throws RemoteException {
        }

        @Override // android.hardware.automotive.occupant_awareness.IOccupantAwareness
        public void getLatestDetection(OccupantDetections detections) throws RemoteException {
        }

        @Override // android.hardware.automotive.occupant_awareness.IOccupantAwareness
        public int getInterfaceVersion() {
            return 0;
        }

        @Override // android.hardware.automotive.occupant_awareness.IOccupantAwareness
        public String getInterfaceHash() {
            return "";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IOccupantAwareness {
        private static final String DESCRIPTOR = "android$hardware$automotive$occupant_awareness$IOccupantAwareness".replace(Typography.dollar, '.');
        static final int TRANSACTION_getCapabilityForRole = 3;
        static final int TRANSACTION_getInterfaceHash = 16777214;
        static final int TRANSACTION_getInterfaceVersion = 16777215;
        static final int TRANSACTION_getLatestDetection = 6;
        static final int TRANSACTION_getState = 4;
        static final int TRANSACTION_setCallback = 5;
        static final int TRANSACTION_startDetection = 1;
        static final int TRANSACTION_stopDetection = 2;

        public Stub() {
            markVintfStability();
            attachInterface(this, DESCRIPTOR);
        }

        public static IOccupantAwareness asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof IOccupantAwareness)) {
                return (IOccupantAwareness) iin;
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
                    byte _result = startDetection();
                    reply.writeNoException();
                    reply.writeByte(_result);
                    return true;
                case 2:
                    data.enforceInterface(descriptor);
                    byte _result2 = stopDetection();
                    reply.writeNoException();
                    reply.writeByte(_result2);
                    return true;
                case 3:
                    data.enforceInterface(descriptor);
                    int _arg0 = data.readInt();
                    int _result3 = getCapabilityForRole(_arg0);
                    reply.writeNoException();
                    reply.writeInt(_result3);
                    return true;
                case 4:
                    data.enforceInterface(descriptor);
                    int _arg02 = data.readInt();
                    int _arg1 = data.readInt();
                    byte _result4 = getState(_arg02, _arg1);
                    reply.writeNoException();
                    reply.writeByte(_result4);
                    return true;
                case 5:
                    data.enforceInterface(descriptor);
                    IOccupantAwarenessClientCallback _arg03 = IOccupantAwarenessClientCallback.Stub.asInterface(data.readStrongBinder());
                    setCallback(_arg03);
                    reply.writeNoException();
                    return true;
                case 6:
                    data.enforceInterface(descriptor);
                    OccupantDetections _arg04 = new OccupantDetections();
                    getLatestDetection(_arg04);
                    reply.writeNoException();
                    reply.writeInt(1);
                    _arg04.writeToParcel(reply, 1);
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

        private static class Proxy implements IOccupantAwareness {
            public static IOccupantAwareness sDefaultImpl;
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

            @Override // android.hardware.automotive.occupant_awareness.IOccupantAwareness
            public byte startDetection() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().startDetection();
                    }
                    _reply.readException();
                    byte _result = _reply.readByte();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.automotive.occupant_awareness.IOccupantAwareness
            public byte stopDetection() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().stopDetection();
                    }
                    _reply.readException();
                    byte _result = _reply.readByte();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.automotive.occupant_awareness.IOccupantAwareness
            public int getCapabilityForRole(int occupantRole) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(occupantRole);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCapabilityForRole(occupantRole);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.automotive.occupant_awareness.IOccupantAwareness
            public byte getState(int occupantRole, int detectionCapability) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(occupantRole);
                    _data.writeInt(detectionCapability);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getState(occupantRole, detectionCapability);
                    }
                    _reply.readException();
                    byte _result = _reply.readByte();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.automotive.occupant_awareness.IOccupantAwareness
            public void setCallback(IOccupantAwarenessClientCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setCallback(callback);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.automotive.occupant_awareness.IOccupantAwareness
            public void getLatestDetection(OccupantDetections detections) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getLatestDetection(detections);
                        return;
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        detections.readFromParcel(_reply);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.automotive.occupant_awareness.IOccupantAwareness
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

            @Override // android.hardware.automotive.occupant_awareness.IOccupantAwareness
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

        public static boolean setDefaultImpl(IOccupantAwareness impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static IOccupantAwareness getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
