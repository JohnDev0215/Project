package com.android.internal.telecom;

import android.content.ComponentName;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes4.dex */
public interface ICallScreeningAdapter extends IInterface {

    public static class Default implements ICallScreeningAdapter {
        @Override // com.android.internal.telecom.ICallScreeningAdapter
        public void allowCall(String str) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.android.internal.telecom.ICallScreeningAdapter
        public void disallowCall(String str, boolean z, boolean z2, boolean z3, ComponentName componentName) throws RemoteException {
        }

        @Override // com.android.internal.telecom.ICallScreeningAdapter
        public void screenCallFurther(String str) throws RemoteException {
        }

        @Override // com.android.internal.telecom.ICallScreeningAdapter
        public void silenceCall(String str) throws RemoteException {
        }
    }

    void allowCall(String str) throws RemoteException;

    void disallowCall(String str, boolean z, boolean z2, boolean z3, ComponentName componentName) throws RemoteException;

    void screenCallFurther(String str) throws RemoteException;

    void silenceCall(String str) throws RemoteException;

    public static abstract class Stub extends Binder implements ICallScreeningAdapter {
        private static final String DESCRIPTOR = "com.android.internal.telecom.ICallScreeningAdapter";
        static final int TRANSACTION_allowCall = 1;
        static final int TRANSACTION_disallowCall = 4;
        static final int TRANSACTION_screenCallFurther = 3;
        static final int TRANSACTION_silenceCall = 2;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "allowCall";
            }
            if (i == 2) {
                return "silenceCall";
            }
            if (i == 3) {
                return "screenCallFurther";
            }
            if (i != 4) {
                return null;
            }
            return "disallowCall";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ICallScreeningAdapter asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof ICallScreeningAdapter)) {
                return (ICallScreeningAdapter) iInterfaceQueryLocalInterface;
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
                allowCall(parcel.readString());
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                silenceCall(parcel.readString());
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                screenCallFurther(parcel.readString());
                return true;
            }
            if (i == 4) {
                parcel.enforceInterface(DESCRIPTOR);
                disallowCall(parcel.readString(), parcel.readInt() != 0, parcel.readInt() != 0, parcel.readInt() != 0, parcel.readInt() != 0 ? ComponentName.CREATOR.createFromParcel(parcel) : null);
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements ICallScreeningAdapter {
            public static ICallScreeningAdapter sDefaultImpl;
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

            @Override // com.android.internal.telecom.ICallScreeningAdapter
            public void allowCall(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().allowCall(str);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telecom.ICallScreeningAdapter
            public void silenceCall(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().silenceCall(str);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telecom.ICallScreeningAdapter
            public void screenCallFurther(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().screenCallFurther(str);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telecom.ICallScreeningAdapter
            public void disallowCall(String str, boolean z, boolean z2, boolean z3, ComponentName componentName) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeInt(z2 ? 1 : 0);
                    parcelObtain.writeInt(z3 ? 1 : 0);
                    if (componentName != null) {
                        parcelObtain.writeInt(1);
                        componentName.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(4, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().disallowCall(str, z, z2, z3, componentName);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ICallScreeningAdapter iCallScreeningAdapter) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iCallScreeningAdapter == null) {
                return false;
            }
            Proxy.sDefaultImpl = iCallScreeningAdapter;
            return true;
        }

        public static ICallScreeningAdapter getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
