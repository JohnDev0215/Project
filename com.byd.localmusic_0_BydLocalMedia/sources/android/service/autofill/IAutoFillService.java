package android.service.autofill;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.service.autofill.IFillCallback;
import android.service.autofill.ISaveCallback;

/* JADX INFO: loaded from: classes3.dex */
public interface IAutoFillService extends IInterface {

    public static class Default implements IAutoFillService {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.service.autofill.IAutoFillService
        public void onConnectedStateChanged(boolean z) throws RemoteException {
        }

        @Override // android.service.autofill.IAutoFillService
        public void onFillRequest(FillRequest fillRequest, IFillCallback iFillCallback) throws RemoteException {
        }

        @Override // android.service.autofill.IAutoFillService
        public void onSaveRequest(SaveRequest saveRequest, ISaveCallback iSaveCallback) throws RemoteException {
        }
    }

    void onConnectedStateChanged(boolean z) throws RemoteException;

    void onFillRequest(FillRequest fillRequest, IFillCallback iFillCallback) throws RemoteException;

    void onSaveRequest(SaveRequest saveRequest, ISaveCallback iSaveCallback) throws RemoteException;

    public static abstract class Stub extends Binder implements IAutoFillService {
        private static final String DESCRIPTOR = "android.service.autofill.IAutoFillService";
        static final int TRANSACTION_onConnectedStateChanged = 1;
        static final int TRANSACTION_onFillRequest = 2;
        static final int TRANSACTION_onSaveRequest = 3;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "onConnectedStateChanged";
            }
            if (i == 2) {
                return "onFillRequest";
            }
            if (i != 3) {
                return null;
            }
            return "onSaveRequest";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IAutoFillService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IAutoFillService)) {
                return (IAutoFillService) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public String getTransactionName(int i) {
            return getDefaultTransactionName(i);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                onConnectedStateChanged(parcel.readInt() != 0);
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                onFillRequest(parcel.readInt() != 0 ? FillRequest.CREATOR.createFromParcel(parcel) : null, IFillCallback.Stub.asInterface(parcel.readStrongBinder()));
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                onSaveRequest(parcel.readInt() != 0 ? SaveRequest.CREATOR.createFromParcel(parcel) : null, ISaveCallback.Stub.asInterface(parcel.readStrongBinder()));
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IAutoFillService {
            public static IAutoFillService sDefaultImpl;
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

            @Override // android.service.autofill.IAutoFillService
            public void onConnectedStateChanged(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onConnectedStateChanged(z);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.autofill.IAutoFillService
            public void onFillRequest(FillRequest fillRequest, IFillCallback iFillCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (fillRequest != null) {
                        parcelObtain.writeInt(1);
                        fillRequest.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeStrongBinder(iFillCallback != null ? iFillCallback.asBinder() : null);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onFillRequest(fillRequest, iFillCallback);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.autofill.IAutoFillService
            public void onSaveRequest(SaveRequest saveRequest, ISaveCallback iSaveCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (saveRequest != null) {
                        parcelObtain.writeInt(1);
                        saveRequest.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeStrongBinder(iSaveCallback != null ? iSaveCallback.asBinder() : null);
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onSaveRequest(saveRequest, iSaveCallback);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IAutoFillService iAutoFillService) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iAutoFillService == null) {
                return false;
            }
            Proxy.sDefaultImpl = iAutoFillService;
            return true;
        }

        public static IAutoFillService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
