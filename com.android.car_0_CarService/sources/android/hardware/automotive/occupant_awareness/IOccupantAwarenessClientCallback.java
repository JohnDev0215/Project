package android.hardware.automotive.occupant_awareness;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import kotlin.text.Typography;

/* JADX INFO: loaded from: classes.dex */
public interface IOccupantAwarenessClientCallback extends IInterface {
    public static final String HASH = "3614b1c47ed7be85c1e77554e7f04966cf35b465";
    public static final int VERSION = 1;

    String getInterfaceHash() throws RemoteException;

    int getInterfaceVersion() throws RemoteException;

    void onDetectionEvent(OccupantDetections occupantDetections) throws RemoteException;

    void onSystemStatusChanged(int i, byte b) throws RemoteException;

    public static class Default implements IOccupantAwarenessClientCallback {
        @Override // android.hardware.automotive.occupant_awareness.IOccupantAwarenessClientCallback
        public void onSystemStatusChanged(int detectionFlags, byte status) throws RemoteException {
        }

        @Override // android.hardware.automotive.occupant_awareness.IOccupantAwarenessClientCallback
        public void onDetectionEvent(OccupantDetections detections) throws RemoteException {
        }

        @Override // android.hardware.automotive.occupant_awareness.IOccupantAwarenessClientCallback
        public int getInterfaceVersion() {
            return 0;
        }

        @Override // android.hardware.automotive.occupant_awareness.IOccupantAwarenessClientCallback
        public String getInterfaceHash() {
            return "";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IOccupantAwarenessClientCallback {
        private static final String DESCRIPTOR = "android$hardware$automotive$occupant_awareness$IOccupantAwarenessClientCallback".replace(Typography.dollar, '.');
        static final int TRANSACTION_getInterfaceHash = 16777214;
        static final int TRANSACTION_getInterfaceVersion = 16777215;
        static final int TRANSACTION_onDetectionEvent = 2;
        static final int TRANSACTION_onSystemStatusChanged = 1;

        public Stub() {
            markVintfStability();
            attachInterface(this, DESCRIPTOR);
        }

        public static IOccupantAwarenessClientCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof IOccupantAwarenessClientCallback)) {
                return (IOccupantAwarenessClientCallback) iin;
            }
            return new Proxy(obj);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            OccupantDetections _arg0;
            String descriptor = DESCRIPTOR;
            if (code == 1) {
                data.enforceInterface(descriptor);
                int _arg02 = data.readInt();
                byte _arg1 = data.readByte();
                onSystemStatusChanged(_arg02, _arg1);
                return true;
            }
            if (code == 2) {
                data.enforceInterface(descriptor);
                if (data.readInt() != 0) {
                    _arg0 = OccupantDetections.CREATOR.createFromParcel(data);
                } else {
                    _arg0 = null;
                }
                onDetectionEvent(_arg0);
                return true;
            }
            if (code == 1598968902) {
                reply.writeString(descriptor);
                return true;
            }
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

        private static class Proxy implements IOccupantAwarenessClientCallback {
            public static IOccupantAwarenessClientCallback sDefaultImpl;
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

            @Override // android.hardware.automotive.occupant_awareness.IOccupantAwarenessClientCallback
            public void onSystemStatusChanged(int detectionFlags, byte status) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(detectionFlags);
                    _data.writeByte(status);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onSystemStatusChanged(detectionFlags, status);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.hardware.automotive.occupant_awareness.IOccupantAwarenessClientCallback
            public void onDetectionEvent(OccupantDetections detections) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (detections != null) {
                        _data.writeInt(1);
                        detections.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onDetectionEvent(detections);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.hardware.automotive.occupant_awareness.IOccupantAwarenessClientCallback
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

            @Override // android.hardware.automotive.occupant_awareness.IOccupantAwarenessClientCallback
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

        public static boolean setDefaultImpl(IOccupantAwarenessClientCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static IOccupantAwarenessClientCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
