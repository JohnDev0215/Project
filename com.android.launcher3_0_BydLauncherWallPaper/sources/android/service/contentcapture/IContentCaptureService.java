package android.service.contentcapture;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.service.contentcapture.IDataShareCallback;
import android.view.contentcapture.ContentCaptureContext;
import android.view.contentcapture.DataRemovalRequest;
import android.view.contentcapture.DataShareRequest;
import com.android.internal.os.IResultReceiver;

/* JADX INFO: loaded from: classes3.dex */
public interface IContentCaptureService extends IInterface {

    public static class Default implements IContentCaptureService {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.service.contentcapture.IContentCaptureService
        public void onActivityEvent(ActivityEvent activityEvent) throws RemoteException {
        }

        @Override // android.service.contentcapture.IContentCaptureService
        public void onActivitySnapshot(int i, SnapshotData snapshotData) throws RemoteException {
        }

        @Override // android.service.contentcapture.IContentCaptureService
        public void onConnected(IBinder iBinder, boolean z, boolean z2) throws RemoteException {
        }

        @Override // android.service.contentcapture.IContentCaptureService
        public void onDataRemovalRequest(DataRemovalRequest dataRemovalRequest) throws RemoteException {
        }

        @Override // android.service.contentcapture.IContentCaptureService
        public void onDataShared(DataShareRequest dataShareRequest, IDataShareCallback iDataShareCallback) throws RemoteException {
        }

        @Override // android.service.contentcapture.IContentCaptureService
        public void onDisconnected() throws RemoteException {
        }

        @Override // android.service.contentcapture.IContentCaptureService
        public void onSessionFinished(int i) throws RemoteException {
        }

        @Override // android.service.contentcapture.IContentCaptureService
        public void onSessionStarted(ContentCaptureContext contentCaptureContext, int i, int i2, IResultReceiver iResultReceiver, int i3) throws RemoteException {
        }
    }

    void onActivityEvent(ActivityEvent activityEvent) throws RemoteException;

    void onActivitySnapshot(int i, SnapshotData snapshotData) throws RemoteException;

    void onConnected(IBinder iBinder, boolean z, boolean z2) throws RemoteException;

    void onDataRemovalRequest(DataRemovalRequest dataRemovalRequest) throws RemoteException;

    void onDataShared(DataShareRequest dataShareRequest, IDataShareCallback iDataShareCallback) throws RemoteException;

    void onDisconnected() throws RemoteException;

    void onSessionFinished(int i) throws RemoteException;

    void onSessionStarted(ContentCaptureContext contentCaptureContext, int i, int i2, IResultReceiver iResultReceiver, int i3) throws RemoteException;

    public static abstract class Stub extends Binder implements IContentCaptureService {
        private static final String DESCRIPTOR = "android.service.contentcapture.IContentCaptureService";
        static final int TRANSACTION_onActivityEvent = 8;
        static final int TRANSACTION_onActivitySnapshot = 5;
        static final int TRANSACTION_onConnected = 1;
        static final int TRANSACTION_onDataRemovalRequest = 6;
        static final int TRANSACTION_onDataShared = 7;
        static final int TRANSACTION_onDisconnected = 2;
        static final int TRANSACTION_onSessionFinished = 4;
        static final int TRANSACTION_onSessionStarted = 3;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "onConnected";
                case 2:
                    return "onDisconnected";
                case 3:
                    return "onSessionStarted";
                case 4:
                    return "onSessionFinished";
                case 5:
                    return "onActivitySnapshot";
                case 6:
                    return "onDataRemovalRequest";
                case 7:
                    return "onDataShared";
                case 8:
                    return "onActivityEvent";
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

        public static IContentCaptureService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IContentCaptureService)) {
                return (IContentCaptureService) iInterfaceQueryLocalInterface;
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
                    onConnected(parcel.readStrongBinder(), parcel.readInt() != 0, parcel.readInt() != 0);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    onDisconnected();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    onSessionStarted(parcel.readInt() != 0 ? ContentCaptureContext.CREATOR.createFromParcel(parcel) : null, parcel.readInt(), parcel.readInt(), IResultReceiver.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    onSessionFinished(parcel.readInt());
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    onActivitySnapshot(parcel.readInt(), parcel.readInt() != 0 ? SnapshotData.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    onDataRemovalRequest(parcel.readInt() != 0 ? DataRemovalRequest.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    onDataShared(parcel.readInt() != 0 ? DataShareRequest.CREATOR.createFromParcel(parcel) : null, IDataShareCallback.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    onActivityEvent(parcel.readInt() != 0 ? ActivityEvent.CREATOR.createFromParcel(parcel) : null);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IContentCaptureService {
            public static IContentCaptureService sDefaultImpl;
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

            @Override // android.service.contentcapture.IContentCaptureService
            public void onConnected(IBinder iBinder, boolean z, boolean z2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBinder);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeInt(z2 ? 1 : 0);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onConnected(iBinder, z, z2);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.contentcapture.IContentCaptureService
            public void onDisconnected() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onDisconnected();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.contentcapture.IContentCaptureService
            public void onSessionStarted(ContentCaptureContext contentCaptureContext, int i, int i2, IResultReceiver iResultReceiver, int i3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (contentCaptureContext != null) {
                        parcelObtain.writeInt(1);
                        contentCaptureContext.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeStrongBinder(iResultReceiver != null ? iResultReceiver.asBinder() : null);
                    parcelObtain.writeInt(i3);
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onSessionStarted(contentCaptureContext, i, i2, iResultReceiver, i3);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.contentcapture.IContentCaptureService
            public void onSessionFinished(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(4, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onSessionFinished(i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.contentcapture.IContentCaptureService
            public void onActivitySnapshot(int i, SnapshotData snapshotData) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (snapshotData != null) {
                        parcelObtain.writeInt(1);
                        snapshotData.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(5, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onActivitySnapshot(i, snapshotData);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.contentcapture.IContentCaptureService
            public void onDataRemovalRequest(DataRemovalRequest dataRemovalRequest) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (dataRemovalRequest != null) {
                        parcelObtain.writeInt(1);
                        dataRemovalRequest.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(6, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onDataRemovalRequest(dataRemovalRequest);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.contentcapture.IContentCaptureService
            public void onDataShared(DataShareRequest dataShareRequest, IDataShareCallback iDataShareCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (dataShareRequest != null) {
                        parcelObtain.writeInt(1);
                        dataShareRequest.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeStrongBinder(iDataShareCallback != null ? iDataShareCallback.asBinder() : null);
                    if (this.mRemote.transact(7, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onDataShared(dataShareRequest, iDataShareCallback);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.contentcapture.IContentCaptureService
            public void onActivityEvent(ActivityEvent activityEvent) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (activityEvent != null) {
                        parcelObtain.writeInt(1);
                        activityEvent.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(8, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onActivityEvent(activityEvent);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IContentCaptureService iContentCaptureService) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iContentCaptureService == null) {
                return false;
            }
            Proxy.sDefaultImpl = iContentCaptureService;
            return true;
        }

        public static IContentCaptureService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
