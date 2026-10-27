package com.android.internal.textservice;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.android.internal.textservice.ISpellCheckerSession;

/* JADX INFO: loaded from: classes4.dex */
public interface ITextServicesSessionListener extends IInterface {

    public static class Default implements ITextServicesSessionListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.android.internal.textservice.ITextServicesSessionListener
        public void onServiceConnected(ISpellCheckerSession iSpellCheckerSession) throws RemoteException {
        }
    }

    void onServiceConnected(ISpellCheckerSession iSpellCheckerSession) throws RemoteException;

    public static abstract class Stub extends Binder implements ITextServicesSessionListener {
        private static final String DESCRIPTOR = "com.android.internal.textservice.ITextServicesSessionListener";
        static final int TRANSACTION_onServiceConnected = 1;

        public static String getDefaultTransactionName(int i) {
            if (i != 1) {
                return null;
            }
            return "onServiceConnected";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ITextServicesSessionListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof ITextServicesSessionListener)) {
                return (ITextServicesSessionListener) iInterfaceQueryLocalInterface;
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
                onServiceConnected(ISpellCheckerSession.Stub.asInterface(parcel.readStrongBinder()));
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements ITextServicesSessionListener {
            public static ITextServicesSessionListener sDefaultImpl;
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

            @Override // com.android.internal.textservice.ITextServicesSessionListener
            public void onServiceConnected(ISpellCheckerSession iSpellCheckerSession) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iSpellCheckerSession != null ? iSpellCheckerSession.asBinder() : null);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onServiceConnected(iSpellCheckerSession);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ITextServicesSessionListener iTextServicesSessionListener) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iTextServicesSessionListener == null) {
                return false;
            }
            Proxy.sDefaultImpl = iTextServicesSessionListener;
            return true;
        }

        public static ITextServicesSessionListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
