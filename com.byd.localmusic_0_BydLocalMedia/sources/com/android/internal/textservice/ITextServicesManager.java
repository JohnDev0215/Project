package com.android.internal.textservice;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.textservice.SpellCheckerInfo;
import android.view.textservice.SpellCheckerSubtype;
import com.android.internal.textservice.ISpellCheckerSessionListener;
import com.android.internal.textservice.ITextServicesSessionListener;

/* JADX INFO: loaded from: classes4.dex */
public interface ITextServicesManager extends IInterface {

    public static class Default implements ITextServicesManager {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.android.internal.textservice.ITextServicesManager
        public void finishSpellCheckerService(int i, ISpellCheckerSessionListener iSpellCheckerSessionListener) throws RemoteException {
        }

        @Override // com.android.internal.textservice.ITextServicesManager
        public SpellCheckerInfo getCurrentSpellChecker(int i, String str) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.textservice.ITextServicesManager
        public SpellCheckerSubtype getCurrentSpellCheckerSubtype(int i, boolean z) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.textservice.ITextServicesManager
        public SpellCheckerInfo[] getEnabledSpellCheckers(int i) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.textservice.ITextServicesManager
        public void getSpellCheckerService(int i, String str, String str2, ITextServicesSessionListener iTextServicesSessionListener, ISpellCheckerSessionListener iSpellCheckerSessionListener, Bundle bundle) throws RemoteException {
        }

        @Override // com.android.internal.textservice.ITextServicesManager
        public boolean isSpellCheckerEnabled(int i) throws RemoteException {
            return false;
        }
    }

    void finishSpellCheckerService(int i, ISpellCheckerSessionListener iSpellCheckerSessionListener) throws RemoteException;

    SpellCheckerInfo getCurrentSpellChecker(int i, String str) throws RemoteException;

    SpellCheckerSubtype getCurrentSpellCheckerSubtype(int i, boolean z) throws RemoteException;

    SpellCheckerInfo[] getEnabledSpellCheckers(int i) throws RemoteException;

    void getSpellCheckerService(int i, String str, String str2, ITextServicesSessionListener iTextServicesSessionListener, ISpellCheckerSessionListener iSpellCheckerSessionListener, Bundle bundle) throws RemoteException;

    boolean isSpellCheckerEnabled(int i) throws RemoteException;

    public static abstract class Stub extends Binder implements ITextServicesManager {
        private static final String DESCRIPTOR = "com.android.internal.textservice.ITextServicesManager";
        static final int TRANSACTION_finishSpellCheckerService = 4;
        static final int TRANSACTION_getCurrentSpellChecker = 1;
        static final int TRANSACTION_getCurrentSpellCheckerSubtype = 2;
        static final int TRANSACTION_getEnabledSpellCheckers = 6;
        static final int TRANSACTION_getSpellCheckerService = 3;
        static final int TRANSACTION_isSpellCheckerEnabled = 5;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "getCurrentSpellChecker";
                case 2:
                    return "getCurrentSpellCheckerSubtype";
                case 3:
                    return "getSpellCheckerService";
                case 4:
                    return "finishSpellCheckerService";
                case 5:
                    return "isSpellCheckerEnabled";
                case 6:
                    return "getEnabledSpellCheckers";
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

        public static ITextServicesManager asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof ITextServicesManager)) {
                return (ITextServicesManager) iInterfaceQueryLocalInterface;
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
                    SpellCheckerInfo currentSpellChecker = getCurrentSpellChecker(parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    if (currentSpellChecker != null) {
                        parcel2.writeInt(1);
                        currentSpellChecker.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    SpellCheckerSubtype currentSpellCheckerSubtype = getCurrentSpellCheckerSubtype(parcel.readInt(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    if (currentSpellCheckerSubtype != null) {
                        parcel2.writeInt(1);
                        currentSpellCheckerSubtype.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    getSpellCheckerService(parcel.readInt(), parcel.readString(), parcel.readString(), ITextServicesSessionListener.Stub.asInterface(parcel.readStrongBinder()), ISpellCheckerSessionListener.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt() != 0 ? Bundle.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    finishSpellCheckerService(parcel.readInt(), ISpellCheckerSessionListener.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsSpellCheckerEnabled = isSpellCheckerEnabled(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsSpellCheckerEnabled ? 1 : 0);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    SpellCheckerInfo[] enabledSpellCheckers = getEnabledSpellCheckers(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeTypedArray(enabledSpellCheckers, 1);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements ITextServicesManager {
            public static ITextServicesManager sDefaultImpl;
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

            @Override // com.android.internal.textservice.ITextServicesManager
            public SpellCheckerInfo getCurrentSpellChecker(int i, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCurrentSpellChecker(i, str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? SpellCheckerInfo.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.textservice.ITextServicesManager
            public SpellCheckerSubtype getCurrentSpellCheckerSubtype(int i, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCurrentSpellCheckerSubtype(i, z);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? SpellCheckerSubtype.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.textservice.ITextServicesManager
            public void getSpellCheckerService(int i, String str, String str2, ITextServicesSessionListener iTextServicesSessionListener, ISpellCheckerSessionListener iSpellCheckerSessionListener, Bundle bundle) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeStrongBinder(iTextServicesSessionListener != null ? iTextServicesSessionListener.asBinder() : null);
                    parcelObtain.writeStrongBinder(iSpellCheckerSessionListener != null ? iSpellCheckerSessionListener.asBinder() : null);
                    if (bundle != null) {
                        parcelObtain.writeInt(1);
                        bundle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().getSpellCheckerService(i, str, str2, iTextServicesSessionListener, iSpellCheckerSessionListener, bundle);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.textservice.ITextServicesManager
            public void finishSpellCheckerService(int i, ISpellCheckerSessionListener iSpellCheckerSessionListener) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iSpellCheckerSessionListener != null ? iSpellCheckerSessionListener.asBinder() : null);
                    if (this.mRemote.transact(4, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().finishSpellCheckerService(i, iSpellCheckerSessionListener);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.textservice.ITextServicesManager
            public boolean isSpellCheckerEnabled(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isSpellCheckerEnabled(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.textservice.ITextServicesManager
            public SpellCheckerInfo[] getEnabledSpellCheckers(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getEnabledSpellCheckers(i);
                    }
                    parcelObtain2.readException();
                    return (SpellCheckerInfo[]) parcelObtain2.createTypedArray(SpellCheckerInfo.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ITextServicesManager iTextServicesManager) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iTextServicesManager == null) {
                return false;
            }
            Proxy.sDefaultImpl = iTextServicesManager;
            return true;
        }

        public static ITextServicesManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
