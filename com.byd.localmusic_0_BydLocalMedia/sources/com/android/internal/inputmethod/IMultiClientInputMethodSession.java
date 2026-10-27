package com.android.internal.inputmethod;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.os.ResultReceiver;
import android.view.inputmethod.EditorInfo;
import com.android.internal.view.IInputContext;

/* JADX INFO: loaded from: classes4.dex */
public interface IMultiClientInputMethodSession extends IInterface {

    public static class Default implements IMultiClientInputMethodSession {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.android.internal.inputmethod.IMultiClientInputMethodSession
        public void hideSoftInput(int i, ResultReceiver resultReceiver) throws RemoteException {
        }

        @Override // com.android.internal.inputmethod.IMultiClientInputMethodSession
        public void showSoftInput(int i, ResultReceiver resultReceiver) throws RemoteException {
        }

        @Override // com.android.internal.inputmethod.IMultiClientInputMethodSession
        public void startInputOrWindowGainedFocus(IInputContext iInputContext, int i, EditorInfo editorInfo, int i2, int i3, int i4) throws RemoteException {
        }
    }

    void hideSoftInput(int i, ResultReceiver resultReceiver) throws RemoteException;

    void showSoftInput(int i, ResultReceiver resultReceiver) throws RemoteException;

    void startInputOrWindowGainedFocus(IInputContext iInputContext, int i, EditorInfo editorInfo, int i2, int i3, int i4) throws RemoteException;

    public static abstract class Stub extends Binder implements IMultiClientInputMethodSession {
        private static final String DESCRIPTOR = "com.android.internal.inputmethod.IMultiClientInputMethodSession";
        static final int TRANSACTION_hideSoftInput = 3;
        static final int TRANSACTION_showSoftInput = 2;
        static final int TRANSACTION_startInputOrWindowGainedFocus = 1;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "startInputOrWindowGainedFocus";
            }
            if (i == 2) {
                return "showSoftInput";
            }
            if (i != 3) {
                return null;
            }
            return "hideSoftInput";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IMultiClientInputMethodSession asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IMultiClientInputMethodSession)) {
                return (IMultiClientInputMethodSession) iInterfaceQueryLocalInterface;
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
                startInputOrWindowGainedFocus(IInputContext.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt() != 0 ? EditorInfo.CREATOR.createFromParcel(parcel) : null, parcel.readInt(), parcel.readInt(), parcel.readInt());
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                showSoftInput(parcel.readInt(), parcel.readInt() != 0 ? ResultReceiver.CREATOR.createFromParcel(parcel) : null);
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                hideSoftInput(parcel.readInt(), parcel.readInt() != 0 ? ResultReceiver.CREATOR.createFromParcel(parcel) : null);
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IMultiClientInputMethodSession {
            public static IMultiClientInputMethodSession sDefaultImpl;
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

            @Override // com.android.internal.inputmethod.IMultiClientInputMethodSession
            public void startInputOrWindowGainedFocus(IInputContext iInputContext, int i, EditorInfo editorInfo, int i2, int i3, int i4) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iInputContext != null ? iInputContext.asBinder() : null);
                    parcelObtain.writeInt(i);
                    if (editorInfo != null) {
                        parcelObtain.writeInt(1);
                        editorInfo.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    parcelObtain.writeInt(i4);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().startInputOrWindowGainedFocus(iInputContext, i, editorInfo, i2, i3, i4);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.inputmethod.IMultiClientInputMethodSession
            public void showSoftInput(int i, ResultReceiver resultReceiver) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (resultReceiver != null) {
                        parcelObtain.writeInt(1);
                        resultReceiver.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().showSoftInput(i, resultReceiver);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.inputmethod.IMultiClientInputMethodSession
            public void hideSoftInput(int i, ResultReceiver resultReceiver) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (resultReceiver != null) {
                        parcelObtain.writeInt(1);
                        resultReceiver.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().hideSoftInput(i, resultReceiver);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IMultiClientInputMethodSession iMultiClientInputMethodSession) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iMultiClientInputMethodSession == null) {
                return false;
            }
            Proxy.sDefaultImpl = iMultiClientInputMethodSession;
            return true;
        }

        public static IMultiClientInputMethodSession getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
