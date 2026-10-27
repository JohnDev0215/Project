package android.view.contentcapture;

import android.content.ComponentName;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.contentcapture.IDataShareWriteAdapter;
import com.android.internal.os.IResultReceiver;

/* JADX INFO: loaded from: classes3.dex */
public interface IContentCaptureManager extends IInterface {

    public static class Default implements IContentCaptureManager {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.view.contentcapture.IContentCaptureManager
        public void finishSession(int i) throws RemoteException {
        }

        @Override // android.view.contentcapture.IContentCaptureManager
        public void getContentCaptureConditions(String str, IResultReceiver iResultReceiver) throws RemoteException {
        }

        @Override // android.view.contentcapture.IContentCaptureManager
        public void getServiceComponentName(IResultReceiver iResultReceiver) throws RemoteException {
        }

        @Override // android.view.contentcapture.IContentCaptureManager
        public void getServiceSettingsActivity(IResultReceiver iResultReceiver) throws RemoteException {
        }

        @Override // android.view.contentcapture.IContentCaptureManager
        public void isContentCaptureFeatureEnabled(IResultReceiver iResultReceiver) throws RemoteException {
        }

        @Override // android.view.contentcapture.IContentCaptureManager
        public void removeData(DataRemovalRequest dataRemovalRequest) throws RemoteException {
        }

        @Override // android.view.contentcapture.IContentCaptureManager
        public void shareData(DataShareRequest dataShareRequest, IDataShareWriteAdapter iDataShareWriteAdapter) throws RemoteException {
        }

        @Override // android.view.contentcapture.IContentCaptureManager
        public void startSession(IBinder iBinder, ComponentName componentName, int i, int i2, IResultReceiver iResultReceiver) throws RemoteException {
        }
    }

    void finishSession(int i) throws RemoteException;

    void getContentCaptureConditions(String str, IResultReceiver iResultReceiver) throws RemoteException;

    void getServiceComponentName(IResultReceiver iResultReceiver) throws RemoteException;

    void getServiceSettingsActivity(IResultReceiver iResultReceiver) throws RemoteException;

    void isContentCaptureFeatureEnabled(IResultReceiver iResultReceiver) throws RemoteException;

    void removeData(DataRemovalRequest dataRemovalRequest) throws RemoteException;

    void shareData(DataShareRequest dataShareRequest, IDataShareWriteAdapter iDataShareWriteAdapter) throws RemoteException;

    void startSession(IBinder iBinder, ComponentName componentName, int i, int i2, IResultReceiver iResultReceiver) throws RemoteException;

    public static abstract class Stub extends Binder implements IContentCaptureManager {
        private static final String DESCRIPTOR = "android.view.contentcapture.IContentCaptureManager";
        static final int TRANSACTION_finishSession = 2;
        static final int TRANSACTION_getContentCaptureConditions = 8;
        static final int TRANSACTION_getServiceComponentName = 3;
        static final int TRANSACTION_getServiceSettingsActivity = 7;
        static final int TRANSACTION_isContentCaptureFeatureEnabled = 6;
        static final int TRANSACTION_removeData = 4;
        static final int TRANSACTION_shareData = 5;
        static final int TRANSACTION_startSession = 1;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "startSession";
                case 2:
                    return "finishSession";
                case 3:
                    return "getServiceComponentName";
                case 4:
                    return "removeData";
                case 5:
                    return "shareData";
                case 6:
                    return "isContentCaptureFeatureEnabled";
                case 7:
                    return "getServiceSettingsActivity";
                case 8:
                    return "getContentCaptureConditions";
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

        public static IContentCaptureManager asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IContentCaptureManager)) {
                return (IContentCaptureManager) iInterfaceQueryLocalInterface;
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
                    startSession(parcel.readStrongBinder(), parcel.readInt() != 0 ? ComponentName.CREATOR.createFromParcel(parcel) : null, parcel.readInt(), parcel.readInt(), IResultReceiver.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    finishSession(parcel.readInt());
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    getServiceComponentName(IResultReceiver.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    removeData(parcel.readInt() != 0 ? DataRemovalRequest.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    shareData(parcel.readInt() != 0 ? DataShareRequest.CREATOR.createFromParcel(parcel) : null, IDataShareWriteAdapter.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    isContentCaptureFeatureEnabled(IResultReceiver.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    getServiceSettingsActivity(IResultReceiver.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    getContentCaptureConditions(parcel.readString(), IResultReceiver.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IContentCaptureManager {
            public static IContentCaptureManager sDefaultImpl;
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

            @Override // android.view.contentcapture.IContentCaptureManager
            public void startSession(IBinder iBinder, ComponentName componentName, int i, int i2, IResultReceiver iResultReceiver) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBinder);
                    if (componentName != null) {
                        parcelObtain.writeInt(1);
                        componentName.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeStrongBinder(iResultReceiver != null ? iResultReceiver.asBinder() : null);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().startSession(iBinder, componentName, i, i2, iResultReceiver);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.contentcapture.IContentCaptureManager
            public void finishSession(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().finishSession(i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.contentcapture.IContentCaptureManager
            public void getServiceComponentName(IResultReceiver iResultReceiver) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iResultReceiver != null ? iResultReceiver.asBinder() : null);
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().getServiceComponentName(iResultReceiver);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.contentcapture.IContentCaptureManager
            public void removeData(DataRemovalRequest dataRemovalRequest) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (dataRemovalRequest != null) {
                        parcelObtain.writeInt(1);
                        dataRemovalRequest.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(4, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().removeData(dataRemovalRequest);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.contentcapture.IContentCaptureManager
            public void shareData(DataShareRequest dataShareRequest, IDataShareWriteAdapter iDataShareWriteAdapter) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (dataShareRequest != null) {
                        parcelObtain.writeInt(1);
                        dataShareRequest.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeStrongBinder(iDataShareWriteAdapter != null ? iDataShareWriteAdapter.asBinder() : null);
                    if (this.mRemote.transact(5, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().shareData(dataShareRequest, iDataShareWriteAdapter);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.contentcapture.IContentCaptureManager
            public void isContentCaptureFeatureEnabled(IResultReceiver iResultReceiver) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iResultReceiver != null ? iResultReceiver.asBinder() : null);
                    if (this.mRemote.transact(6, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().isContentCaptureFeatureEnabled(iResultReceiver);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.contentcapture.IContentCaptureManager
            public void getServiceSettingsActivity(IResultReceiver iResultReceiver) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iResultReceiver != null ? iResultReceiver.asBinder() : null);
                    if (this.mRemote.transact(7, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().getServiceSettingsActivity(iResultReceiver);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.contentcapture.IContentCaptureManager
            public void getContentCaptureConditions(String str, IResultReceiver iResultReceiver) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeStrongBinder(iResultReceiver != null ? iResultReceiver.asBinder() : null);
                    if (this.mRemote.transact(8, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().getContentCaptureConditions(str, iResultReceiver);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IContentCaptureManager iContentCaptureManager) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iContentCaptureManager == null) {
                return false;
            }
            Proxy.sDefaultImpl = iContentCaptureManager;
            return true;
        }

        public static IContentCaptureManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
