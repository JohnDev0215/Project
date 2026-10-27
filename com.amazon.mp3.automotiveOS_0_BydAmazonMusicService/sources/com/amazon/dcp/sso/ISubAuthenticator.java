package com.amazon.dcp.sso;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.amazon.dcp.sso.IAmazonAccountAuthenticator;
import com.amazon.dcp.sso.ISubAuthenticatorResponse;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes2.dex */
public interface ISubAuthenticator extends IInterface {

    /* JADX INFO: compiled from: DCP */
    public static abstract class Stub extends Binder implements ISubAuthenticator {
        private static final String DESCRIPTOR = "com.amazon.dcp.sso.ISubAuthenticator";
        public static final int TRANSACTION_getAccountRemovalAllowed = 2;
        public static final int TRANSACTION_getAuthToken = 1;
        public static final int TRANSACTION_updateAuthToken = 3;

        /* JADX INFO: compiled from: DCP */
        public static class Proxy implements ISubAuthenticator {
            public static ISubAuthenticator sDefaultImpl;
            private IBinder mRemote;

            public Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.amazon.dcp.sso.ISubAuthenticator
            public void getAccountRemovalAllowed(ISubAuthenticatorResponse iSubAuthenticatorResponse, String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iSubAuthenticatorResponse != null ? iSubAuthenticatorResponse.asBinder() : null);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                        parcelObtain2.readException();
                    } else {
                        Stub.getDefaultImpl().getAccountRemovalAllowed(iSubAuthenticatorResponse, str, str2);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.amazon.dcp.sso.ISubAuthenticator
            public void getAuthToken(ISubAuthenticatorResponse iSubAuthenticatorResponse, String str, String str2, String str3, Bundle bundle, IAmazonAccountAuthenticator iAmazonAccountAuthenticator) throws Throwable {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iSubAuthenticatorResponse != null ? iSubAuthenticatorResponse.asBinder() : null);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeString(str3);
                    if (bundle != null) {
                        parcelObtain.writeInt(1);
                        bundle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeStrongBinder(iAmazonAccountAuthenticator != null ? iAmazonAccountAuthenticator.asBinder() : null);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    if (this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                        parcelObtain2.readException();
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                    } else {
                        Stub.getDefaultImpl().getAuthToken(iSubAuthenticatorResponse, str, str2, str3, bundle, iAmazonAccountAuthenticator);
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

            @Override // com.amazon.dcp.sso.ISubAuthenticator
            public void updateAuthToken(ISubAuthenticatorResponse iSubAuthenticatorResponse, String str, String str2, String str3, Bundle bundle, IAmazonAccountAuthenticator iAmazonAccountAuthenticator) throws Throwable {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iSubAuthenticatorResponse != null ? iSubAuthenticatorResponse.asBinder() : null);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeString(str3);
                    if (bundle != null) {
                        parcelObtain.writeInt(1);
                        bundle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeStrongBinder(iAmazonAccountAuthenticator != null ? iAmazonAccountAuthenticator.asBinder() : null);
                    try {
                        if (this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                            parcelObtain2.readException();
                            parcelObtain2.recycle();
                            parcelObtain.recycle();
                        } else {
                            Stub.getDefaultImpl().updateAuthToken(iSubAuthenticatorResponse, str, str2, str3, bundle, iAmazonAccountAuthenticator);
                            parcelObtain2.recycle();
                            parcelObtain.recycle();
                        }
                    } catch (Throwable th) {
                        th = th;
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ISubAuthenticator asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof ISubAuthenticator)) ? new Proxy(iBinder) : (ISubAuthenticator) iInterfaceQueryLocalInterface;
        }

        public static ISubAuthenticator getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(ISubAuthenticator iSubAuthenticator) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iSubAuthenticator == null) {
                return false;
            }
            Proxy.sDefaultImpl = iSubAuthenticator;
            return true;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                getAuthToken(ISubAuthenticatorResponse.Stub.asInterface(parcel.readStrongBinder()), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null, IAmazonAccountAuthenticator.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                getAccountRemovalAllowed(ISubAuthenticatorResponse.Stub.asInterface(parcel.readStrongBinder()), parcel.readString(), parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            if (i != 3) {
                if (i != 1598968902) {
                    return super.onTransact(i, parcel, parcel2, i2);
                }
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            parcel.enforceInterface(DESCRIPTOR);
            updateAuthToken(ISubAuthenticatorResponse.Stub.asInterface(parcel.readStrongBinder()), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null, IAmazonAccountAuthenticator.Stub.asInterface(parcel.readStrongBinder()));
            parcel2.writeNoException();
            return true;
        }
    }

    void getAccountRemovalAllowed(ISubAuthenticatorResponse iSubAuthenticatorResponse, String str, String str2) throws RemoteException;

    void getAuthToken(ISubAuthenticatorResponse iSubAuthenticatorResponse, String str, String str2, String str3, Bundle bundle, IAmazonAccountAuthenticator iAmazonAccountAuthenticator) throws RemoteException;

    void updateAuthToken(ISubAuthenticatorResponse iSubAuthenticatorResponse, String str, String str2, String str3, Bundle bundle, IAmazonAccountAuthenticator iAmazonAccountAuthenticator) throws RemoteException;
}
