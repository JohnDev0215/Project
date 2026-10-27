package android.view.autofill;

import android.graphics.Rect;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.autofill.IAutofillWindowPresenter;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public interface IAugmentedAutofillManagerClient extends IInterface {

    public static class Default implements IAugmentedAutofillManagerClient {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.view.autofill.IAugmentedAutofillManagerClient
        public void autofill(int i, List<AutofillId> list, List<AutofillValue> list2, boolean z) throws RemoteException {
        }

        @Override // android.view.autofill.IAugmentedAutofillManagerClient
        public Rect getViewCoordinates(AutofillId autofillId) throws RemoteException {
            return null;
        }

        @Override // android.view.autofill.IAugmentedAutofillManagerClient
        public boolean requestAutofill(int i, AutofillId autofillId) throws RemoteException {
            return false;
        }

        @Override // android.view.autofill.IAugmentedAutofillManagerClient
        public void requestHideFillUi(int i, AutofillId autofillId) throws RemoteException {
        }

        @Override // android.view.autofill.IAugmentedAutofillManagerClient
        public void requestShowFillUi(int i, AutofillId autofillId, int i2, int i3, Rect rect, IAutofillWindowPresenter iAutofillWindowPresenter) throws RemoteException {
        }
    }

    void autofill(int i, List<AutofillId> list, List<AutofillValue> list2, boolean z) throws RemoteException;

    Rect getViewCoordinates(AutofillId autofillId) throws RemoteException;

    boolean requestAutofill(int i, AutofillId autofillId) throws RemoteException;

    void requestHideFillUi(int i, AutofillId autofillId) throws RemoteException;

    void requestShowFillUi(int i, AutofillId autofillId, int i2, int i3, Rect rect, IAutofillWindowPresenter iAutofillWindowPresenter) throws RemoteException;

    public static abstract class Stub extends Binder implements IAugmentedAutofillManagerClient {
        private static final String DESCRIPTOR = "android.view.autofill.IAugmentedAutofillManagerClient";
        static final int TRANSACTION_autofill = 2;
        static final int TRANSACTION_getViewCoordinates = 1;
        static final int TRANSACTION_requestAutofill = 5;
        static final int TRANSACTION_requestHideFillUi = 4;
        static final int TRANSACTION_requestShowFillUi = 3;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "getViewCoordinates";
            }
            if (i == 2) {
                return "autofill";
            }
            if (i == 3) {
                return "requestShowFillUi";
            }
            if (i == 4) {
                return "requestHideFillUi";
            }
            if (i != 5) {
                return null;
            }
            return "requestAutofill";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IAugmentedAutofillManagerClient asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IAugmentedAutofillManagerClient)) {
                return (IAugmentedAutofillManagerClient) iInterfaceQueryLocalInterface;
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
                Rect viewCoordinates = getViewCoordinates(parcel.readInt() != 0 ? AutofillId.CREATOR.createFromParcel(parcel) : null);
                parcel2.writeNoException();
                if (viewCoordinates != null) {
                    parcel2.writeInt(1);
                    viewCoordinates.writeToParcel(parcel2, 1);
                } else {
                    parcel2.writeInt(0);
                }
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                autofill(parcel.readInt(), parcel.createTypedArrayList(AutofillId.CREATOR), parcel.createTypedArrayList(AutofillValue.CREATOR), parcel.readInt() != 0);
                parcel2.writeNoException();
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                requestShowFillUi(parcel.readInt(), parcel.readInt() != 0 ? AutofillId.CREATOR.createFromParcel(parcel) : null, parcel.readInt(), parcel.readInt(), parcel.readInt() != 0 ? Rect.CREATOR.createFromParcel(parcel) : null, IAutofillWindowPresenter.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            if (i == 4) {
                parcel.enforceInterface(DESCRIPTOR);
                requestHideFillUi(parcel.readInt(), parcel.readInt() != 0 ? AutofillId.CREATOR.createFromParcel(parcel) : null);
                parcel2.writeNoException();
                return true;
            }
            if (i != 5) {
                if (i == 1598968902) {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface(DESCRIPTOR);
            boolean zRequestAutofill = requestAutofill(parcel.readInt(), parcel.readInt() != 0 ? AutofillId.CREATOR.createFromParcel(parcel) : null);
            parcel2.writeNoException();
            parcel2.writeInt(zRequestAutofill ? 1 : 0);
            return true;
        }

        private static class Proxy implements IAugmentedAutofillManagerClient {
            public static IAugmentedAutofillManagerClient sDefaultImpl;
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

            @Override // android.view.autofill.IAugmentedAutofillManagerClient
            public Rect getViewCoordinates(AutofillId autofillId) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (autofillId != null) {
                        parcelObtain.writeInt(1);
                        autofillId.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getViewCoordinates(autofillId);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? Rect.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.autofill.IAugmentedAutofillManagerClient
            public void autofill(int i, List<AutofillId> list, List<AutofillValue> list2, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeTypedList(list);
                    parcelObtain.writeTypedList(list2);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().autofill(i, list, list2, z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.autofill.IAugmentedAutofillManagerClient
            public void requestShowFillUi(int i, AutofillId autofillId, int i2, int i3, Rect rect, IAutofillWindowPresenter iAutofillWindowPresenter) throws Throwable {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (autofillId != null) {
                        parcelObtain.writeInt(1);
                        autofillId.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    if (rect != null) {
                        parcelObtain.writeInt(1);
                        rect.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeStrongBinder(iAutofillWindowPresenter != null ? iAutofillWindowPresenter.asBinder() : null);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().requestShowFillUi(i, autofillId, i2, i3, rect, iAutofillWindowPresenter);
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                    } else {
                        parcelObtain2.readException();
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                    }
                } catch (Throwable th2) {
                    th = th2;
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    throw th;
                }
            }

            @Override // android.view.autofill.IAugmentedAutofillManagerClient
            public void requestHideFillUi(int i, AutofillId autofillId) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (autofillId != null) {
                        parcelObtain.writeInt(1);
                        autofillId.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().requestHideFillUi(i, autofillId);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.autofill.IAugmentedAutofillManagerClient
            public boolean requestAutofill(int i, AutofillId autofillId) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (autofillId != null) {
                        parcelObtain.writeInt(1);
                        autofillId.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().requestAutofill(i, autofillId);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IAugmentedAutofillManagerClient iAugmentedAutofillManagerClient) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iAugmentedAutofillManagerClient == null) {
                return false;
            }
            Proxy.sDefaultImpl = iAugmentedAutofillManagerClient;
            return true;
        }

        public static IAugmentedAutofillManagerClient getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
