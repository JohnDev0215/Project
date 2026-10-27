package android.service.autofill.augmented;

import android.content.ComponentName;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.service.autofill.augmented.IFillCallback;
import android.view.autofill.AutofillId;
import android.view.autofill.AutofillValue;
import android.view.inputmethod.InlineSuggestionsRequest;

/* JADX INFO: loaded from: classes3.dex */
public interface IAugmentedAutofillService extends IInterface {

    public static class Default implements IAugmentedAutofillService {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.service.autofill.augmented.IAugmentedAutofillService
        public void onConnected(boolean z, boolean z2) throws RemoteException {
        }

        @Override // android.service.autofill.augmented.IAugmentedAutofillService
        public void onDestroyAllFillWindowsRequest() throws RemoteException {
        }

        @Override // android.service.autofill.augmented.IAugmentedAutofillService
        public void onDisconnected() throws RemoteException {
        }

        @Override // android.service.autofill.augmented.IAugmentedAutofillService
        public void onFillRequest(int i, IBinder iBinder, int i2, ComponentName componentName, AutofillId autofillId, AutofillValue autofillValue, long j, InlineSuggestionsRequest inlineSuggestionsRequest, IFillCallback iFillCallback) throws RemoteException {
        }
    }

    void onConnected(boolean z, boolean z2) throws RemoteException;

    void onDestroyAllFillWindowsRequest() throws RemoteException;

    void onDisconnected() throws RemoteException;

    void onFillRequest(int i, IBinder iBinder, int i2, ComponentName componentName, AutofillId autofillId, AutofillValue autofillValue, long j, InlineSuggestionsRequest inlineSuggestionsRequest, IFillCallback iFillCallback) throws RemoteException;

    public static abstract class Stub extends Binder implements IAugmentedAutofillService {
        private static final String DESCRIPTOR = "android.service.autofill.augmented.IAugmentedAutofillService";
        static final int TRANSACTION_onConnected = 1;
        static final int TRANSACTION_onDestroyAllFillWindowsRequest = 4;
        static final int TRANSACTION_onDisconnected = 2;
        static final int TRANSACTION_onFillRequest = 3;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "onConnected";
            }
            if (i == 2) {
                return "onDisconnected";
            }
            if (i == 3) {
                return "onFillRequest";
            }
            if (i != 4) {
                return null;
            }
            return "onDestroyAllFillWindowsRequest";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IAugmentedAutofillService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IAugmentedAutofillService)) {
                return (IAugmentedAutofillService) iInterfaceQueryLocalInterface;
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
                boolean z = false;
                boolean z2 = parcel.readInt() != 0;
                if (parcel.readInt() != 0) {
                    z = true;
                }
                onConnected(z2, z);
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                onDisconnected();
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                onFillRequest(parcel.readInt(), parcel.readStrongBinder(), parcel.readInt(), parcel.readInt() != 0 ? ComponentName.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? AutofillId.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? AutofillValue.CREATOR.createFromParcel(parcel) : null, parcel.readLong(), parcel.readInt() != 0 ? InlineSuggestionsRequest.CREATOR.createFromParcel(parcel) : null, IFillCallback.Stub.asInterface(parcel.readStrongBinder()));
                return true;
            }
            if (i == 4) {
                parcel.enforceInterface(DESCRIPTOR);
                onDestroyAllFillWindowsRequest();
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IAugmentedAutofillService {
            public static IAugmentedAutofillService sDefaultImpl;
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

            @Override // android.service.autofill.augmented.IAugmentedAutofillService
            public void onConnected(boolean z, boolean z2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeInt(z2 ? 1 : 0);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onConnected(z, z2);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.autofill.augmented.IAugmentedAutofillService
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

            @Override // android.service.autofill.augmented.IAugmentedAutofillService
            public void onFillRequest(int i, IBinder iBinder, int i2, ComponentName componentName, AutofillId autofillId, AutofillValue autofillValue, long j, InlineSuggestionsRequest inlineSuggestionsRequest, IFillCallback iFillCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iBinder);
                    parcelObtain.writeInt(i2);
                    if (componentName != null) {
                        parcelObtain.writeInt(1);
                        componentName.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (autofillId != null) {
                        parcelObtain.writeInt(1);
                        autofillId.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (autofillValue != null) {
                        parcelObtain.writeInt(1);
                        autofillValue.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeLong(j);
                    if (inlineSuggestionsRequest != null) {
                        parcelObtain.writeInt(1);
                        inlineSuggestionsRequest.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeStrongBinder(iFillCallback != null ? iFillCallback.asBinder() : null);
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onFillRequest(i, iBinder, i2, componentName, autofillId, autofillValue, j, inlineSuggestionsRequest, iFillCallback);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.autofill.augmented.IAugmentedAutofillService
            public void onDestroyAllFillWindowsRequest() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(4, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onDestroyAllFillWindowsRequest();
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IAugmentedAutofillService iAugmentedAutofillService) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iAugmentedAutofillService == null) {
                return false;
            }
            Proxy.sDefaultImpl = iAugmentedAutofillService;
            return true;
        }

        public static IAugmentedAutofillService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
