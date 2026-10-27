package android.telephony.data;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public interface IDataServiceCallback extends IInterface {

    public static class Default implements IDataServiceCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.telephony.data.IDataServiceCallback
        public void onDataCallListChanged(List<DataCallResponse> list) throws RemoteException {
        }

        @Override // android.telephony.data.IDataServiceCallback
        public void onDeactivateDataCallComplete(int i) throws RemoteException {
        }

        @Override // android.telephony.data.IDataServiceCallback
        public void onRequestDataCallListComplete(int i, List<DataCallResponse> list) throws RemoteException {
        }

        @Override // android.telephony.data.IDataServiceCallback
        public void onSetDataProfileComplete(int i) throws RemoteException {
        }

        @Override // android.telephony.data.IDataServiceCallback
        public void onSetInitialAttachApnComplete(int i) throws RemoteException {
        }

        @Override // android.telephony.data.IDataServiceCallback
        public void onSetupDataCallComplete(int i, DataCallResponse dataCallResponse) throws RemoteException {
        }
    }

    void onDataCallListChanged(List<DataCallResponse> list) throws RemoteException;

    void onDeactivateDataCallComplete(int i) throws RemoteException;

    void onRequestDataCallListComplete(int i, List<DataCallResponse> list) throws RemoteException;

    void onSetDataProfileComplete(int i) throws RemoteException;

    void onSetInitialAttachApnComplete(int i) throws RemoteException;

    void onSetupDataCallComplete(int i, DataCallResponse dataCallResponse) throws RemoteException;

    public static abstract class Stub extends Binder implements IDataServiceCallback {
        private static final String DESCRIPTOR = "android.telephony.data.IDataServiceCallback";
        static final int TRANSACTION_onDataCallListChanged = 6;
        static final int TRANSACTION_onDeactivateDataCallComplete = 2;
        static final int TRANSACTION_onRequestDataCallListComplete = 5;
        static final int TRANSACTION_onSetDataProfileComplete = 4;
        static final int TRANSACTION_onSetInitialAttachApnComplete = 3;
        static final int TRANSACTION_onSetupDataCallComplete = 1;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "onSetupDataCallComplete";
                case 2:
                    return "onDeactivateDataCallComplete";
                case 3:
                    return "onSetInitialAttachApnComplete";
                case 4:
                    return "onSetDataProfileComplete";
                case 5:
                    return "onRequestDataCallListComplete";
                case 6:
                    return "onDataCallListChanged";
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

        public static IDataServiceCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IDataServiceCallback)) {
                return (IDataServiceCallback) iInterfaceQueryLocalInterface;
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
                    onSetupDataCallComplete(parcel.readInt(), parcel.readInt() != 0 ? DataCallResponse.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    onDeactivateDataCallComplete(parcel.readInt());
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    onSetInitialAttachApnComplete(parcel.readInt());
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    onSetDataProfileComplete(parcel.readInt());
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    onRequestDataCallListComplete(parcel.readInt(), parcel.createTypedArrayList(DataCallResponse.CREATOR));
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    onDataCallListChanged(parcel.createTypedArrayList(DataCallResponse.CREATOR));
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IDataServiceCallback {
            public static IDataServiceCallback sDefaultImpl;
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

            @Override // android.telephony.data.IDataServiceCallback
            public void onSetupDataCallComplete(int i, DataCallResponse dataCallResponse) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (dataCallResponse != null) {
                        parcelObtain.writeInt(1);
                        dataCallResponse.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onSetupDataCallComplete(i, dataCallResponse);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.telephony.data.IDataServiceCallback
            public void onDeactivateDataCallComplete(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onDeactivateDataCallComplete(i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.telephony.data.IDataServiceCallback
            public void onSetInitialAttachApnComplete(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onSetInitialAttachApnComplete(i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.telephony.data.IDataServiceCallback
            public void onSetDataProfileComplete(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(4, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onSetDataProfileComplete(i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.telephony.data.IDataServiceCallback
            public void onRequestDataCallListComplete(int i, List<DataCallResponse> list) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeTypedList(list);
                    if (this.mRemote.transact(5, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onRequestDataCallListComplete(i, list);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.telephony.data.IDataServiceCallback
            public void onDataCallListChanged(List<DataCallResponse> list) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeTypedList(list);
                    if (this.mRemote.transact(6, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onDataCallListChanged(list);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IDataServiceCallback iDataServiceCallback) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iDataServiceCallback == null) {
                return false;
            }
            Proxy.sDefaultImpl = iDataServiceCallback;
            return true;
        }

        public static IDataServiceCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
