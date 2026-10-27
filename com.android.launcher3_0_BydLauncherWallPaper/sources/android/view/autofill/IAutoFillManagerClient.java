package android.view.autofill;

import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentSender;
import android.graphics.Rect;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.KeyEvent;
import android.view.autofill.IAutofillWindowPresenter;
import com.android.internal.os.IResultReceiver;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public interface IAutoFillManagerClient extends IInterface {

    public static class Default implements IAutoFillManagerClient {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.view.autofill.IAutoFillManagerClient
        public void authenticate(int i, int i2, IntentSender intentSender, Intent intent, boolean z) throws RemoteException {
        }

        @Override // android.view.autofill.IAutoFillManagerClient
        public void autofill(int i, List<AutofillId> list, List<AutofillValue> list2, boolean z) throws RemoteException {
        }

        @Override // android.view.autofill.IAutoFillManagerClient
        public void dispatchUnhandledKey(int i, AutofillId autofillId, KeyEvent keyEvent) throws RemoteException {
        }

        @Override // android.view.autofill.IAutoFillManagerClient
        public void getAugmentedAutofillClient(IResultReceiver iResultReceiver) throws RemoteException {
        }

        @Override // android.view.autofill.IAutoFillManagerClient
        public void notifyDisableAutofill(long j, ComponentName componentName) throws RemoteException {
        }

        @Override // android.view.autofill.IAutoFillManagerClient
        public void notifyFillUiHidden(int i, AutofillId autofillId) throws RemoteException {
        }

        @Override // android.view.autofill.IAutoFillManagerClient
        public void notifyFillUiShown(int i, AutofillId autofillId) throws RemoteException {
        }

        @Override // android.view.autofill.IAutoFillManagerClient
        public void notifyNoFillUi(int i, AutofillId autofillId, int i2) throws RemoteException {
        }

        @Override // android.view.autofill.IAutoFillManagerClient
        public void requestHideFillUi(int i, AutofillId autofillId) throws RemoteException {
        }

        @Override // android.view.autofill.IAutoFillManagerClient
        public void requestShowFillUi(int i, AutofillId autofillId, int i2, int i3, Rect rect, IAutofillWindowPresenter iAutofillWindowPresenter) throws RemoteException {
        }

        @Override // android.view.autofill.IAutoFillManagerClient
        public void requestShowSoftInput(AutofillId autofillId) throws RemoteException {
        }

        @Override // android.view.autofill.IAutoFillManagerClient
        public void setSaveUiState(int i, boolean z) throws RemoteException {
        }

        @Override // android.view.autofill.IAutoFillManagerClient
        public void setSessionFinished(int i, List<AutofillId> list) throws RemoteException {
        }

        @Override // android.view.autofill.IAutoFillManagerClient
        public void setState(int i) throws RemoteException {
        }

        @Override // android.view.autofill.IAutoFillManagerClient
        public void setTrackedViews(int i, AutofillId[] autofillIdArr, boolean z, boolean z2, AutofillId[] autofillIdArr2, AutofillId autofillId) throws RemoteException {
        }

        @Override // android.view.autofill.IAutoFillManagerClient
        public void startIntentSender(IntentSender intentSender, Intent intent) throws RemoteException {
        }
    }

    void authenticate(int i, int i2, IntentSender intentSender, Intent intent, boolean z) throws RemoteException;

    void autofill(int i, List<AutofillId> list, List<AutofillValue> list2, boolean z) throws RemoteException;

    void dispatchUnhandledKey(int i, AutofillId autofillId, KeyEvent keyEvent) throws RemoteException;

    void getAugmentedAutofillClient(IResultReceiver iResultReceiver) throws RemoteException;

    void notifyDisableAutofill(long j, ComponentName componentName) throws RemoteException;

    void notifyFillUiHidden(int i, AutofillId autofillId) throws RemoteException;

    void notifyFillUiShown(int i, AutofillId autofillId) throws RemoteException;

    void notifyNoFillUi(int i, AutofillId autofillId, int i2) throws RemoteException;

    void requestHideFillUi(int i, AutofillId autofillId) throws RemoteException;

    void requestShowFillUi(int i, AutofillId autofillId, int i2, int i3, Rect rect, IAutofillWindowPresenter iAutofillWindowPresenter) throws RemoteException;

    void requestShowSoftInput(AutofillId autofillId) throws RemoteException;

    void setSaveUiState(int i, boolean z) throws RemoteException;

    void setSessionFinished(int i, List<AutofillId> list) throws RemoteException;

    void setState(int i) throws RemoteException;

    void setTrackedViews(int i, AutofillId[] autofillIdArr, boolean z, boolean z2, AutofillId[] autofillIdArr2, AutofillId autofillId) throws RemoteException;

    void startIntentSender(IntentSender intentSender, Intent intent) throws RemoteException;

    public static abstract class Stub extends Binder implements IAutoFillManagerClient {
        private static final String DESCRIPTOR = "android.view.autofill.IAutoFillManagerClient";
        static final int TRANSACTION_authenticate = 3;
        static final int TRANSACTION_autofill = 2;
        static final int TRANSACTION_dispatchUnhandledKey = 10;
        static final int TRANSACTION_getAugmentedAutofillClient = 14;
        static final int TRANSACTION_notifyDisableAutofill = 15;
        static final int TRANSACTION_notifyFillUiHidden = 9;
        static final int TRANSACTION_notifyFillUiShown = 8;
        static final int TRANSACTION_notifyNoFillUi = 7;
        static final int TRANSACTION_requestHideFillUi = 6;
        static final int TRANSACTION_requestShowFillUi = 5;
        static final int TRANSACTION_requestShowSoftInput = 16;
        static final int TRANSACTION_setSaveUiState = 12;
        static final int TRANSACTION_setSessionFinished = 13;
        static final int TRANSACTION_setState = 1;
        static final int TRANSACTION_setTrackedViews = 4;
        static final int TRANSACTION_startIntentSender = 11;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "setState";
                case 2:
                    return "autofill";
                case 3:
                    return "authenticate";
                case 4:
                    return "setTrackedViews";
                case 5:
                    return "requestShowFillUi";
                case 6:
                    return "requestHideFillUi";
                case 7:
                    return "notifyNoFillUi";
                case 8:
                    return "notifyFillUiShown";
                case 9:
                    return "notifyFillUiHidden";
                case 10:
                    return "dispatchUnhandledKey";
                case 11:
                    return "startIntentSender";
                case 12:
                    return "setSaveUiState";
                case 13:
                    return "setSessionFinished";
                case 14:
                    return "getAugmentedAutofillClient";
                case 15:
                    return "notifyDisableAutofill";
                case 16:
                    return "requestShowSoftInput";
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

        public static IAutoFillManagerClient asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IAutoFillManagerClient)) {
                return (IAutoFillManagerClient) iInterfaceQueryLocalInterface;
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
                    setState(parcel.readInt());
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    autofill(parcel.readInt(), parcel.createTypedArrayList(AutofillId.CREATOR), parcel.createTypedArrayList(AutofillValue.CREATOR), parcel.readInt() != 0);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    authenticate(parcel.readInt(), parcel.readInt(), parcel.readInt() != 0 ? IntentSender.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? Intent.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    setTrackedViews(parcel.readInt(), (AutofillId[]) parcel.createTypedArray(AutofillId.CREATOR), parcel.readInt() != 0, parcel.readInt() != 0, (AutofillId[]) parcel.createTypedArray(AutofillId.CREATOR), parcel.readInt() != 0 ? AutofillId.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    requestShowFillUi(parcel.readInt(), parcel.readInt() != 0 ? AutofillId.CREATOR.createFromParcel(parcel) : null, parcel.readInt(), parcel.readInt(), parcel.readInt() != 0 ? Rect.CREATOR.createFromParcel(parcel) : null, IAutofillWindowPresenter.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    requestHideFillUi(parcel.readInt(), parcel.readInt() != 0 ? AutofillId.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    notifyNoFillUi(parcel.readInt(), parcel.readInt() != 0 ? AutofillId.CREATOR.createFromParcel(parcel) : null, parcel.readInt());
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    notifyFillUiShown(parcel.readInt(), parcel.readInt() != 0 ? AutofillId.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    notifyFillUiHidden(parcel.readInt(), parcel.readInt() != 0 ? AutofillId.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    dispatchUnhandledKey(parcel.readInt(), parcel.readInt() != 0 ? AutofillId.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? KeyEvent.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    startIntentSender(parcel.readInt() != 0 ? IntentSender.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? Intent.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    setSaveUiState(parcel.readInt(), parcel.readInt() != 0);
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    setSessionFinished(parcel.readInt(), parcel.createTypedArrayList(AutofillId.CREATOR));
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    getAugmentedAutofillClient(IResultReceiver.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    notifyDisableAutofill(parcel.readLong(), parcel.readInt() != 0 ? ComponentName.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    requestShowSoftInput(parcel.readInt() != 0 ? AutofillId.CREATOR.createFromParcel(parcel) : null);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IAutoFillManagerClient {
            public static IAutoFillManagerClient sDefaultImpl;
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

            @Override // android.view.autofill.IAutoFillManagerClient
            public void setState(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().setState(i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.autofill.IAutoFillManagerClient
            public void autofill(int i, List<AutofillId> list, List<AutofillValue> list2, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeTypedList(list);
                    parcelObtain.writeTypedList(list2);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().autofill(i, list, list2, z);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.autofill.IAutoFillManagerClient
            public void authenticate(int i, int i2, IntentSender intentSender, Intent intent, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (intentSender != null) {
                        parcelObtain.writeInt(1);
                        intentSender.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (intent != null) {
                        parcelObtain.writeInt(1);
                        intent.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().authenticate(i, i2, intentSender, intent, z);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.autofill.IAutoFillManagerClient
            public void setTrackedViews(int i, AutofillId[] autofillIdArr, boolean z, boolean z2, AutofillId[] autofillIdArr2, AutofillId autofillId) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeTypedArray(autofillIdArr, 0);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeInt(z2 ? 1 : 0);
                    parcelObtain.writeTypedArray(autofillIdArr2, 0);
                    if (autofillId != null) {
                        parcelObtain.writeInt(1);
                        autofillId.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(4, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().setTrackedViews(i, autofillIdArr, z, z2, autofillIdArr2, autofillId);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.autofill.IAutoFillManagerClient
            public void requestShowFillUi(int i, AutofillId autofillId, int i2, int i3, Rect rect, IAutofillWindowPresenter iAutofillWindowPresenter) throws RemoteException {
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
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    if (rect != null) {
                        parcelObtain.writeInt(1);
                        rect.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeStrongBinder(iAutofillWindowPresenter != null ? iAutofillWindowPresenter.asBinder() : null);
                    if (this.mRemote.transact(5, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().requestShowFillUi(i, autofillId, i2, i3, rect, iAutofillWindowPresenter);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.autofill.IAutoFillManagerClient
            public void requestHideFillUi(int i, AutofillId autofillId) throws RemoteException {
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
                    if (this.mRemote.transact(6, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().requestHideFillUi(i, autofillId);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.autofill.IAutoFillManagerClient
            public void notifyNoFillUi(int i, AutofillId autofillId, int i2) throws RemoteException {
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
                    parcelObtain.writeInt(i2);
                    if (this.mRemote.transact(7, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().notifyNoFillUi(i, autofillId, i2);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.autofill.IAutoFillManagerClient
            public void notifyFillUiShown(int i, AutofillId autofillId) throws RemoteException {
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
                    if (this.mRemote.transact(8, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().notifyFillUiShown(i, autofillId);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.autofill.IAutoFillManagerClient
            public void notifyFillUiHidden(int i, AutofillId autofillId) throws RemoteException {
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
                    if (this.mRemote.transact(9, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().notifyFillUiHidden(i, autofillId);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.autofill.IAutoFillManagerClient
            public void dispatchUnhandledKey(int i, AutofillId autofillId, KeyEvent keyEvent) throws RemoteException {
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
                    if (keyEvent != null) {
                        parcelObtain.writeInt(1);
                        keyEvent.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(10, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().dispatchUnhandledKey(i, autofillId, keyEvent);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.autofill.IAutoFillManagerClient
            public void startIntentSender(IntentSender intentSender, Intent intent) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (intentSender != null) {
                        parcelObtain.writeInt(1);
                        intentSender.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (intent != null) {
                        parcelObtain.writeInt(1);
                        intent.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(11, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().startIntentSender(intentSender, intent);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.autofill.IAutoFillManagerClient
            public void setSaveUiState(int i, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (this.mRemote.transact(12, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().setSaveUiState(i, z);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.autofill.IAutoFillManagerClient
            public void setSessionFinished(int i, List<AutofillId> list) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeTypedList(list);
                    if (this.mRemote.transact(13, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().setSessionFinished(i, list);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.autofill.IAutoFillManagerClient
            public void getAugmentedAutofillClient(IResultReceiver iResultReceiver) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iResultReceiver != null ? iResultReceiver.asBinder() : null);
                    if (this.mRemote.transact(14, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().getAugmentedAutofillClient(iResultReceiver);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.autofill.IAutoFillManagerClient
            public void notifyDisableAutofill(long j, ComponentName componentName) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeLong(j);
                    if (componentName != null) {
                        parcelObtain.writeInt(1);
                        componentName.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(15, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().notifyDisableAutofill(j, componentName);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.autofill.IAutoFillManagerClient
            public void requestShowSoftInput(AutofillId autofillId) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (autofillId != null) {
                        parcelObtain.writeInt(1);
                        autofillId.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(16, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().requestShowSoftInput(autofillId);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IAutoFillManagerClient iAutoFillManagerClient) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iAutoFillManagerClient == null) {
                return false;
            }
            Proxy.sDefaultImpl = iAutoFillManagerClient;
            return true;
        }

        public static IAutoFillManagerClient getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
