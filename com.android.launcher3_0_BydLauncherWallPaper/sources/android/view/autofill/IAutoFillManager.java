package android.view.autofill;

import android.content.ComponentName;
import android.graphics.Rect;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.service.autofill.UserData;
import android.view.autofill.IAutoFillManagerClient;
import com.android.internal.os.IResultReceiver;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public interface IAutoFillManager extends IInterface {

    public static class Default implements IAutoFillManager {
        @Override // android.view.autofill.IAutoFillManager
        public void addClient(IAutoFillManagerClient iAutoFillManagerClient, ComponentName componentName, int i, IResultReceiver iResultReceiver) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.view.autofill.IAutoFillManager
        public void cancelSession(int i, int i2) throws RemoteException {
        }

        @Override // android.view.autofill.IAutoFillManager
        public void disableOwnedAutofillServices(int i) throws RemoteException {
        }

        @Override // android.view.autofill.IAutoFillManager
        public void finishSession(int i, int i2) throws RemoteException {
        }

        @Override // android.view.autofill.IAutoFillManager
        public void getAutofillServiceComponentName(IResultReceiver iResultReceiver) throws RemoteException {
        }

        @Override // android.view.autofill.IAutoFillManager
        public void getAvailableFieldClassificationAlgorithms(IResultReceiver iResultReceiver) throws RemoteException {
        }

        @Override // android.view.autofill.IAutoFillManager
        public void getDefaultFieldClassificationAlgorithm(IResultReceiver iResultReceiver) throws RemoteException {
        }

        @Override // android.view.autofill.IAutoFillManager
        public void getFillEventHistory(IResultReceiver iResultReceiver) throws RemoteException {
        }

        @Override // android.view.autofill.IAutoFillManager
        public void getUserData(IResultReceiver iResultReceiver) throws RemoteException {
        }

        @Override // android.view.autofill.IAutoFillManager
        public void getUserDataId(IResultReceiver iResultReceiver) throws RemoteException {
        }

        @Override // android.view.autofill.IAutoFillManager
        public void isFieldClassificationEnabled(IResultReceiver iResultReceiver) throws RemoteException {
        }

        @Override // android.view.autofill.IAutoFillManager
        public void isServiceEnabled(int i, String str, IResultReceiver iResultReceiver) throws RemoteException {
        }

        @Override // android.view.autofill.IAutoFillManager
        public void isServiceSupported(int i, IResultReceiver iResultReceiver) throws RemoteException {
        }

        @Override // android.view.autofill.IAutoFillManager
        public void onPendingSaveUi(int i, IBinder iBinder) throws RemoteException {
        }

        @Override // android.view.autofill.IAutoFillManager
        public void removeClient(IAutoFillManagerClient iAutoFillManagerClient, int i) throws RemoteException {
        }

        @Override // android.view.autofill.IAutoFillManager
        public void restoreSession(int i, IBinder iBinder, IBinder iBinder2, IResultReceiver iResultReceiver) throws RemoteException {
        }

        @Override // android.view.autofill.IAutoFillManager
        public void setAugmentedAutofillWhitelist(List<String> list, List<ComponentName> list2, IResultReceiver iResultReceiver) throws RemoteException {
        }

        @Override // android.view.autofill.IAutoFillManager
        public void setAuthenticationResult(Bundle bundle, int i, int i2, int i3) throws RemoteException {
        }

        @Override // android.view.autofill.IAutoFillManager
        public void setAutofillFailure(int i, List<AutofillId> list, int i2) throws RemoteException {
        }

        @Override // android.view.autofill.IAutoFillManager
        public void setHasCallback(int i, int i2, boolean z) throws RemoteException {
        }

        @Override // android.view.autofill.IAutoFillManager
        public void setUserData(UserData userData) throws RemoteException {
        }

        @Override // android.view.autofill.IAutoFillManager
        public void startSession(IBinder iBinder, IBinder iBinder2, AutofillId autofillId, Rect rect, AutofillValue autofillValue, int i, boolean z, int i2, ComponentName componentName, boolean z2, IResultReceiver iResultReceiver) throws RemoteException {
        }

        @Override // android.view.autofill.IAutoFillManager
        public void updateSession(int i, AutofillId autofillId, Rect rect, AutofillValue autofillValue, int i2, int i3, int i4) throws RemoteException {
        }
    }

    void addClient(IAutoFillManagerClient iAutoFillManagerClient, ComponentName componentName, int i, IResultReceiver iResultReceiver) throws RemoteException;

    void cancelSession(int i, int i2) throws RemoteException;

    void disableOwnedAutofillServices(int i) throws RemoteException;

    void finishSession(int i, int i2) throws RemoteException;

    void getAutofillServiceComponentName(IResultReceiver iResultReceiver) throws RemoteException;

    void getAvailableFieldClassificationAlgorithms(IResultReceiver iResultReceiver) throws RemoteException;

    void getDefaultFieldClassificationAlgorithm(IResultReceiver iResultReceiver) throws RemoteException;

    void getFillEventHistory(IResultReceiver iResultReceiver) throws RemoteException;

    void getUserData(IResultReceiver iResultReceiver) throws RemoteException;

    void getUserDataId(IResultReceiver iResultReceiver) throws RemoteException;

    void isFieldClassificationEnabled(IResultReceiver iResultReceiver) throws RemoteException;

    void isServiceEnabled(int i, String str, IResultReceiver iResultReceiver) throws RemoteException;

    void isServiceSupported(int i, IResultReceiver iResultReceiver) throws RemoteException;

    void onPendingSaveUi(int i, IBinder iBinder) throws RemoteException;

    void removeClient(IAutoFillManagerClient iAutoFillManagerClient, int i) throws RemoteException;

    void restoreSession(int i, IBinder iBinder, IBinder iBinder2, IResultReceiver iResultReceiver) throws RemoteException;

    void setAugmentedAutofillWhitelist(List<String> list, List<ComponentName> list2, IResultReceiver iResultReceiver) throws RemoteException;

    void setAuthenticationResult(Bundle bundle, int i, int i2, int i3) throws RemoteException;

    void setAutofillFailure(int i, List<AutofillId> list, int i2) throws RemoteException;

    void setHasCallback(int i, int i2, boolean z) throws RemoteException;

    void setUserData(UserData userData) throws RemoteException;

    void startSession(IBinder iBinder, IBinder iBinder2, AutofillId autofillId, Rect rect, AutofillValue autofillValue, int i, boolean z, int i2, ComponentName componentName, boolean z2, IResultReceiver iResultReceiver) throws RemoteException;

    void updateSession(int i, AutofillId autofillId, Rect rect, AutofillValue autofillValue, int i2, int i3, int i4) throws RemoteException;

    public static abstract class Stub extends Binder implements IAutoFillManager {
        private static final String DESCRIPTOR = "android.view.autofill.IAutoFillManager";
        static final int TRANSACTION_addClient = 1;
        static final int TRANSACTION_cancelSession = 9;
        static final int TRANSACTION_disableOwnedAutofillServices = 12;
        static final int TRANSACTION_finishSession = 8;
        static final int TRANSACTION_getAutofillServiceComponentName = 20;
        static final int TRANSACTION_getAvailableFieldClassificationAlgorithms = 21;
        static final int TRANSACTION_getDefaultFieldClassificationAlgorithm = 22;
        static final int TRANSACTION_getFillEventHistory = 4;
        static final int TRANSACTION_getUserData = 16;
        static final int TRANSACTION_getUserDataId = 17;
        static final int TRANSACTION_isFieldClassificationEnabled = 19;
        static final int TRANSACTION_isServiceEnabled = 14;
        static final int TRANSACTION_isServiceSupported = 13;
        static final int TRANSACTION_onPendingSaveUi = 15;
        static final int TRANSACTION_removeClient = 2;
        static final int TRANSACTION_restoreSession = 5;
        static final int TRANSACTION_setAugmentedAutofillWhitelist = 23;
        static final int TRANSACTION_setAuthenticationResult = 10;
        static final int TRANSACTION_setAutofillFailure = 7;
        static final int TRANSACTION_setHasCallback = 11;
        static final int TRANSACTION_setUserData = 18;
        static final int TRANSACTION_startSession = 3;
        static final int TRANSACTION_updateSession = 6;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "addClient";
                case 2:
                    return "removeClient";
                case 3:
                    return "startSession";
                case 4:
                    return "getFillEventHistory";
                case 5:
                    return "restoreSession";
                case 6:
                    return "updateSession";
                case 7:
                    return "setAutofillFailure";
                case 8:
                    return "finishSession";
                case 9:
                    return "cancelSession";
                case 10:
                    return "setAuthenticationResult";
                case 11:
                    return "setHasCallback";
                case 12:
                    return "disableOwnedAutofillServices";
                case 13:
                    return "isServiceSupported";
                case 14:
                    return "isServiceEnabled";
                case 15:
                    return "onPendingSaveUi";
                case 16:
                    return "getUserData";
                case 17:
                    return "getUserDataId";
                case 18:
                    return "setUserData";
                case 19:
                    return "isFieldClassificationEnabled";
                case 20:
                    return "getAutofillServiceComponentName";
                case 21:
                    return "getAvailableFieldClassificationAlgorithms";
                case 22:
                    return "getDefaultFieldClassificationAlgorithm";
                case 23:
                    return "setAugmentedAutofillWhitelist";
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

        public static IAutoFillManager asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IAutoFillManager)) {
                return (IAutoFillManager) iInterfaceQueryLocalInterface;
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
                    addClient(IAutoFillManagerClient.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt() != 0 ? ComponentName.CREATOR.createFromParcel(parcel) : null, parcel.readInt(), IResultReceiver.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    removeClient(IAutoFillManagerClient.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    startSession(parcel.readStrongBinder(), parcel.readStrongBinder(), parcel.readInt() != 0 ? AutofillId.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? Rect.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? AutofillValue.CREATOR.createFromParcel(parcel) : null, parcel.readInt(), parcel.readInt() != 0, parcel.readInt(), parcel.readInt() != 0 ? ComponentName.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0, IResultReceiver.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    getFillEventHistory(IResultReceiver.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    restoreSession(parcel.readInt(), parcel.readStrongBinder(), parcel.readStrongBinder(), IResultReceiver.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    updateSession(parcel.readInt(), parcel.readInt() != 0 ? AutofillId.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? Rect.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? AutofillValue.CREATOR.createFromParcel(parcel) : null, parcel.readInt(), parcel.readInt(), parcel.readInt());
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    setAutofillFailure(parcel.readInt(), parcel.createTypedArrayList(AutofillId.CREATOR), parcel.readInt());
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    finishSession(parcel.readInt(), parcel.readInt());
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    cancelSession(parcel.readInt(), parcel.readInt());
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    setAuthenticationResult(parcel.readInt() != 0 ? Bundle.CREATOR.createFromParcel(parcel) : null, parcel.readInt(), parcel.readInt(), parcel.readInt());
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    setHasCallback(parcel.readInt(), parcel.readInt(), parcel.readInt() != 0);
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    disableOwnedAutofillServices(parcel.readInt());
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    isServiceSupported(parcel.readInt(), IResultReceiver.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    isServiceEnabled(parcel.readInt(), parcel.readString(), IResultReceiver.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    onPendingSaveUi(parcel.readInt(), parcel.readStrongBinder());
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    getUserData(IResultReceiver.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    getUserDataId(IResultReceiver.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    setUserData(parcel.readInt() != 0 ? UserData.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 19:
                    parcel.enforceInterface(DESCRIPTOR);
                    isFieldClassificationEnabled(IResultReceiver.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 20:
                    parcel.enforceInterface(DESCRIPTOR);
                    getAutofillServiceComponentName(IResultReceiver.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 21:
                    parcel.enforceInterface(DESCRIPTOR);
                    getAvailableFieldClassificationAlgorithms(IResultReceiver.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 22:
                    parcel.enforceInterface(DESCRIPTOR);
                    getDefaultFieldClassificationAlgorithm(IResultReceiver.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 23:
                    parcel.enforceInterface(DESCRIPTOR);
                    setAugmentedAutofillWhitelist(parcel.createStringArrayList(), parcel.createTypedArrayList(ComponentName.CREATOR), IResultReceiver.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IAutoFillManager {
            public static IAutoFillManager sDefaultImpl;
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

            @Override // android.view.autofill.IAutoFillManager
            public void addClient(IAutoFillManagerClient iAutoFillManagerClient, ComponentName componentName, int i, IResultReceiver iResultReceiver) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iAutoFillManagerClient != null ? iAutoFillManagerClient.asBinder() : null);
                    if (componentName != null) {
                        parcelObtain.writeInt(1);
                        componentName.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iResultReceiver != null ? iResultReceiver.asBinder() : null);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().addClient(iAutoFillManagerClient, componentName, i, iResultReceiver);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.autofill.IAutoFillManager
            public void removeClient(IAutoFillManagerClient iAutoFillManagerClient, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iAutoFillManagerClient != null ? iAutoFillManagerClient.asBinder() : null);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().removeClient(iAutoFillManagerClient, i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.autofill.IAutoFillManager
            public void startSession(IBinder iBinder, IBinder iBinder2, AutofillId autofillId, Rect rect, AutofillValue autofillValue, int i, boolean z, int i2, ComponentName componentName, boolean z2, IResultReceiver iResultReceiver) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBinder);
                    parcelObtain.writeStrongBinder(iBinder2);
                    if (autofillId != null) {
                        parcelObtain.writeInt(1);
                        autofillId.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (rect != null) {
                        parcelObtain.writeInt(1);
                        rect.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (autofillValue != null) {
                        parcelObtain.writeInt(1);
                        autofillValue.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeInt(i2);
                    if (componentName != null) {
                        parcelObtain.writeInt(1);
                        componentName.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(z2 ? 1 : 0);
                    parcelObtain.writeStrongBinder(iResultReceiver != null ? iResultReceiver.asBinder() : null);
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().startSession(iBinder, iBinder2, autofillId, rect, autofillValue, i, z, i2, componentName, z2, iResultReceiver);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.autofill.IAutoFillManager
            public void getFillEventHistory(IResultReceiver iResultReceiver) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iResultReceiver != null ? iResultReceiver.asBinder() : null);
                    if (this.mRemote.transact(4, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().getFillEventHistory(iResultReceiver);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.autofill.IAutoFillManager
            public void restoreSession(int i, IBinder iBinder, IBinder iBinder2, IResultReceiver iResultReceiver) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iBinder);
                    parcelObtain.writeStrongBinder(iBinder2);
                    parcelObtain.writeStrongBinder(iResultReceiver != null ? iResultReceiver.asBinder() : null);
                    if (this.mRemote.transact(5, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().restoreSession(i, iBinder, iBinder2, iResultReceiver);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.autofill.IAutoFillManager
            public void updateSession(int i, AutofillId autofillId, Rect rect, AutofillValue autofillValue, int i2, int i3, int i4) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (autofillId != null) {
                        parcelObtain.writeInt(1);
                        autofillId.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (rect != null) {
                        parcelObtain.writeInt(1);
                        rect.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (autofillValue != null) {
                        parcelObtain.writeInt(1);
                        autofillValue.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    parcelObtain.writeInt(i4);
                    if (this.mRemote.transact(6, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().updateSession(i, autofillId, rect, autofillValue, i2, i3, i4);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.autofill.IAutoFillManager
            public void setAutofillFailure(int i, List<AutofillId> list, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeTypedList(list);
                    parcelObtain.writeInt(i2);
                    if (this.mRemote.transact(7, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().setAutofillFailure(i, list, i2);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.autofill.IAutoFillManager
            public void finishSession(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (this.mRemote.transact(8, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().finishSession(i, i2);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.autofill.IAutoFillManager
            public void cancelSession(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (this.mRemote.transact(9, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().cancelSession(i, i2);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.autofill.IAutoFillManager
            public void setAuthenticationResult(Bundle bundle, int i, int i2, int i3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bundle != null) {
                        parcelObtain.writeInt(1);
                        bundle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    if (this.mRemote.transact(10, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().setAuthenticationResult(bundle, i, i2, i3);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.autofill.IAutoFillManager
            public void setHasCallback(int i, int i2, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (this.mRemote.transact(11, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().setHasCallback(i, i2, z);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.autofill.IAutoFillManager
            public void disableOwnedAutofillServices(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(12, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().disableOwnedAutofillServices(i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.autofill.IAutoFillManager
            public void isServiceSupported(int i, IResultReceiver iResultReceiver) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iResultReceiver != null ? iResultReceiver.asBinder() : null);
                    if (this.mRemote.transact(13, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().isServiceSupported(i, iResultReceiver);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.autofill.IAutoFillManager
            public void isServiceEnabled(int i, String str, IResultReceiver iResultReceiver) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeStrongBinder(iResultReceiver != null ? iResultReceiver.asBinder() : null);
                    if (this.mRemote.transact(14, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().isServiceEnabled(i, str, iResultReceiver);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.autofill.IAutoFillManager
            public void onPendingSaveUi(int i, IBinder iBinder) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(15, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onPendingSaveUi(i, iBinder);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.autofill.IAutoFillManager
            public void getUserData(IResultReceiver iResultReceiver) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iResultReceiver != null ? iResultReceiver.asBinder() : null);
                    if (this.mRemote.transact(16, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().getUserData(iResultReceiver);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.autofill.IAutoFillManager
            public void getUserDataId(IResultReceiver iResultReceiver) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iResultReceiver != null ? iResultReceiver.asBinder() : null);
                    if (this.mRemote.transact(17, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().getUserDataId(iResultReceiver);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.autofill.IAutoFillManager
            public void setUserData(UserData userData) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (userData != null) {
                        parcelObtain.writeInt(1);
                        userData.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(18, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().setUserData(userData);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.autofill.IAutoFillManager
            public void isFieldClassificationEnabled(IResultReceiver iResultReceiver) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iResultReceiver != null ? iResultReceiver.asBinder() : null);
                    if (this.mRemote.transact(19, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().isFieldClassificationEnabled(iResultReceiver);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.autofill.IAutoFillManager
            public void getAutofillServiceComponentName(IResultReceiver iResultReceiver) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iResultReceiver != null ? iResultReceiver.asBinder() : null);
                    if (this.mRemote.transact(20, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().getAutofillServiceComponentName(iResultReceiver);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.autofill.IAutoFillManager
            public void getAvailableFieldClassificationAlgorithms(IResultReceiver iResultReceiver) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iResultReceiver != null ? iResultReceiver.asBinder() : null);
                    if (this.mRemote.transact(21, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().getAvailableFieldClassificationAlgorithms(iResultReceiver);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.autofill.IAutoFillManager
            public void getDefaultFieldClassificationAlgorithm(IResultReceiver iResultReceiver) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iResultReceiver != null ? iResultReceiver.asBinder() : null);
                    if (this.mRemote.transact(22, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().getDefaultFieldClassificationAlgorithm(iResultReceiver);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.autofill.IAutoFillManager
            public void setAugmentedAutofillWhitelist(List<String> list, List<ComponentName> list2, IResultReceiver iResultReceiver) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStringList(list);
                    parcelObtain.writeTypedList(list2);
                    parcelObtain.writeStrongBinder(iResultReceiver != null ? iResultReceiver.asBinder() : null);
                    if (this.mRemote.transact(23, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().setAugmentedAutofillWhitelist(list, list2, iResultReceiver);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IAutoFillManager iAutoFillManager) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iAutoFillManager == null) {
                return false;
            }
            Proxy.sDefaultImpl = iAutoFillManager;
            return true;
        }

        public static IAutoFillManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
