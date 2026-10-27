package android.media.tv;

import android.hardware.hdmi.HdmiDeviceInfo;
import android.media.tv.ITvInputServiceCallback;
import android.media.tv.ITvInputSessionCallback;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.InputChannel;

/* JADX INFO: loaded from: classes2.dex */
public interface ITvInputService extends IInterface {

    public static class Default implements ITvInputService {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.media.tv.ITvInputService
        public void createRecordingSession(ITvInputSessionCallback iTvInputSessionCallback, String str, String str2) throws RemoteException {
        }

        @Override // android.media.tv.ITvInputService
        public void createSession(InputChannel inputChannel, ITvInputSessionCallback iTvInputSessionCallback, String str, String str2) throws RemoteException {
        }

        @Override // android.media.tv.ITvInputService
        public void notifyHardwareAdded(TvInputHardwareInfo tvInputHardwareInfo) throws RemoteException {
        }

        @Override // android.media.tv.ITvInputService
        public void notifyHardwareRemoved(TvInputHardwareInfo tvInputHardwareInfo) throws RemoteException {
        }

        @Override // android.media.tv.ITvInputService
        public void notifyHdmiDeviceAdded(HdmiDeviceInfo hdmiDeviceInfo) throws RemoteException {
        }

        @Override // android.media.tv.ITvInputService
        public void notifyHdmiDeviceRemoved(HdmiDeviceInfo hdmiDeviceInfo) throws RemoteException {
        }

        @Override // android.media.tv.ITvInputService
        public void notifyHdmiDeviceUpdated(HdmiDeviceInfo hdmiDeviceInfo) throws RemoteException {
        }

        @Override // android.media.tv.ITvInputService
        public void registerCallback(ITvInputServiceCallback iTvInputServiceCallback) throws RemoteException {
        }

        @Override // android.media.tv.ITvInputService
        public void unregisterCallback(ITvInputServiceCallback iTvInputServiceCallback) throws RemoteException {
        }
    }

    void createRecordingSession(ITvInputSessionCallback iTvInputSessionCallback, String str, String str2) throws RemoteException;

    void createSession(InputChannel inputChannel, ITvInputSessionCallback iTvInputSessionCallback, String str, String str2) throws RemoteException;

    void notifyHardwareAdded(TvInputHardwareInfo tvInputHardwareInfo) throws RemoteException;

    void notifyHardwareRemoved(TvInputHardwareInfo tvInputHardwareInfo) throws RemoteException;

    void notifyHdmiDeviceAdded(HdmiDeviceInfo hdmiDeviceInfo) throws RemoteException;

    void notifyHdmiDeviceRemoved(HdmiDeviceInfo hdmiDeviceInfo) throws RemoteException;

    void notifyHdmiDeviceUpdated(HdmiDeviceInfo hdmiDeviceInfo) throws RemoteException;

    void registerCallback(ITvInputServiceCallback iTvInputServiceCallback) throws RemoteException;

    void unregisterCallback(ITvInputServiceCallback iTvInputServiceCallback) throws RemoteException;

    public static abstract class Stub extends Binder implements ITvInputService {
        private static final String DESCRIPTOR = "android.media.tv.ITvInputService";
        static final int TRANSACTION_createRecordingSession = 4;
        static final int TRANSACTION_createSession = 3;
        static final int TRANSACTION_notifyHardwareAdded = 5;
        static final int TRANSACTION_notifyHardwareRemoved = 6;
        static final int TRANSACTION_notifyHdmiDeviceAdded = 7;
        static final int TRANSACTION_notifyHdmiDeviceRemoved = 8;
        static final int TRANSACTION_notifyHdmiDeviceUpdated = 9;
        static final int TRANSACTION_registerCallback = 1;
        static final int TRANSACTION_unregisterCallback = 2;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "registerCallback";
                case 2:
                    return "unregisterCallback";
                case 3:
                    return "createSession";
                case 4:
                    return "createRecordingSession";
                case 5:
                    return "notifyHardwareAdded";
                case 6:
                    return "notifyHardwareRemoved";
                case 7:
                    return "notifyHdmiDeviceAdded";
                case 8:
                    return "notifyHdmiDeviceRemoved";
                case 9:
                    return "notifyHdmiDeviceUpdated";
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

        public static ITvInputService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof ITvInputService)) {
                return (ITvInputService) iInterfaceQueryLocalInterface;
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
                    registerCallback(ITvInputServiceCallback.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    unregisterCallback(ITvInputServiceCallback.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    createSession(parcel.readInt() != 0 ? InputChannel.CREATOR.createFromParcel(parcel) : null, ITvInputSessionCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readString(), parcel.readString());
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    createRecordingSession(ITvInputSessionCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readString(), parcel.readString());
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    notifyHardwareAdded(parcel.readInt() != 0 ? TvInputHardwareInfo.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    notifyHardwareRemoved(parcel.readInt() != 0 ? TvInputHardwareInfo.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    notifyHdmiDeviceAdded(parcel.readInt() != 0 ? HdmiDeviceInfo.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    notifyHdmiDeviceRemoved(parcel.readInt() != 0 ? HdmiDeviceInfo.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    notifyHdmiDeviceUpdated(parcel.readInt() != 0 ? HdmiDeviceInfo.CREATOR.createFromParcel(parcel) : null);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements ITvInputService {
            public static ITvInputService sDefaultImpl;
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

            @Override // android.media.tv.ITvInputService
            public void registerCallback(ITvInputServiceCallback iTvInputServiceCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iTvInputServiceCallback != null ? iTvInputServiceCallback.asBinder() : null);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().registerCallback(iTvInputServiceCallback);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.tv.ITvInputService
            public void unregisterCallback(ITvInputServiceCallback iTvInputServiceCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iTvInputServiceCallback != null ? iTvInputServiceCallback.asBinder() : null);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().unregisterCallback(iTvInputServiceCallback);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.tv.ITvInputService
            public void createSession(InputChannel inputChannel, ITvInputSessionCallback iTvInputSessionCallback, String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (inputChannel != null) {
                        parcelObtain.writeInt(1);
                        inputChannel.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeStrongBinder(iTvInputSessionCallback != null ? iTvInputSessionCallback.asBinder() : null);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().createSession(inputChannel, iTvInputSessionCallback, str, str2);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.tv.ITvInputService
            public void createRecordingSession(ITvInputSessionCallback iTvInputSessionCallback, String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iTvInputSessionCallback != null ? iTvInputSessionCallback.asBinder() : null);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (this.mRemote.transact(4, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().createRecordingSession(iTvInputSessionCallback, str, str2);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.tv.ITvInputService
            public void notifyHardwareAdded(TvInputHardwareInfo tvInputHardwareInfo) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (tvInputHardwareInfo != null) {
                        parcelObtain.writeInt(1);
                        tvInputHardwareInfo.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(5, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().notifyHardwareAdded(tvInputHardwareInfo);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.tv.ITvInputService
            public void notifyHardwareRemoved(TvInputHardwareInfo tvInputHardwareInfo) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (tvInputHardwareInfo != null) {
                        parcelObtain.writeInt(1);
                        tvInputHardwareInfo.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(6, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().notifyHardwareRemoved(tvInputHardwareInfo);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.tv.ITvInputService
            public void notifyHdmiDeviceAdded(HdmiDeviceInfo hdmiDeviceInfo) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (hdmiDeviceInfo != null) {
                        parcelObtain.writeInt(1);
                        hdmiDeviceInfo.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(7, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().notifyHdmiDeviceAdded(hdmiDeviceInfo);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.tv.ITvInputService
            public void notifyHdmiDeviceRemoved(HdmiDeviceInfo hdmiDeviceInfo) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (hdmiDeviceInfo != null) {
                        parcelObtain.writeInt(1);
                        hdmiDeviceInfo.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(8, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().notifyHdmiDeviceRemoved(hdmiDeviceInfo);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.tv.ITvInputService
            public void notifyHdmiDeviceUpdated(HdmiDeviceInfo hdmiDeviceInfo) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (hdmiDeviceInfo != null) {
                        parcelObtain.writeInt(1);
                        hdmiDeviceInfo.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(9, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().notifyHdmiDeviceUpdated(hdmiDeviceInfo);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ITvInputService iTvInputService) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iTvInputService == null) {
                return false;
            }
            Proxy.sDefaultImpl = iTvInputService;
            return true;
        }

        public static ITvInputService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
