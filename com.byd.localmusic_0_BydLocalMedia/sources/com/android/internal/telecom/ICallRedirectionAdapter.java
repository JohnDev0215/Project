package com.android.internal.telecom;

import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.telecom.PhoneAccountHandle;

/* JADX INFO: loaded from: classes4.dex */
public interface ICallRedirectionAdapter extends IInterface {

    public static class Default implements ICallRedirectionAdapter {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.android.internal.telecom.ICallRedirectionAdapter
        public void cancelCall() throws RemoteException {
        }

        @Override // com.android.internal.telecom.ICallRedirectionAdapter
        public void placeCallUnmodified() throws RemoteException {
        }

        @Override // com.android.internal.telecom.ICallRedirectionAdapter
        public void redirectCall(Uri uri, PhoneAccountHandle phoneAccountHandle, boolean z) throws RemoteException {
        }
    }

    void cancelCall() throws RemoteException;

    void placeCallUnmodified() throws RemoteException;

    void redirectCall(Uri uri, PhoneAccountHandle phoneAccountHandle, boolean z) throws RemoteException;

    public static abstract class Stub extends Binder implements ICallRedirectionAdapter {
        private static final String DESCRIPTOR = "com.android.internal.telecom.ICallRedirectionAdapter";
        static final int TRANSACTION_cancelCall = 1;
        static final int TRANSACTION_placeCallUnmodified = 2;
        static final int TRANSACTION_redirectCall = 3;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "cancelCall";
            }
            if (i == 2) {
                return "placeCallUnmodified";
            }
            if (i != 3) {
                return null;
            }
            return "redirectCall";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ICallRedirectionAdapter asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof ICallRedirectionAdapter)) {
                return (ICallRedirectionAdapter) iInterfaceQueryLocalInterface;
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
                cancelCall();
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                placeCallUnmodified();
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                redirectCall(parcel.readInt() != 0 ? Uri.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? PhoneAccountHandle.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0);
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements ICallRedirectionAdapter {
            public static ICallRedirectionAdapter sDefaultImpl;
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

            @Override // com.android.internal.telecom.ICallRedirectionAdapter
            public void cancelCall() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().cancelCall();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telecom.ICallRedirectionAdapter
            public void placeCallUnmodified() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().placeCallUnmodified();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telecom.ICallRedirectionAdapter
            public void redirectCall(Uri uri, PhoneAccountHandle phoneAccountHandle, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (uri != null) {
                        parcelObtain.writeInt(1);
                        uri.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (phoneAccountHandle != null) {
                        parcelObtain.writeInt(1);
                        phoneAccountHandle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().redirectCall(uri, phoneAccountHandle, z);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ICallRedirectionAdapter iCallRedirectionAdapter) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iCallRedirectionAdapter == null) {
                return false;
            }
            Proxy.sDefaultImpl = iCallRedirectionAdapter;
            return true;
        }

        public static ICallRedirectionAdapter getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
