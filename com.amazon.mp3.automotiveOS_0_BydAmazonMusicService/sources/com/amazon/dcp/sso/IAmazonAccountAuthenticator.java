package com.amazon.dcp.sso;

import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.amazon.dcp.sso.IWebserviceCallback;
import java.util.Map;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes2.dex */
public interface IAmazonAccountAuthenticator extends IInterface {

    /* JADX INFO: compiled from: DCP */
    public static abstract class Stub extends Binder implements IAmazonAccountAuthenticator {

        /* JADX INFO: compiled from: DCP */
        public static class Proxy implements IAmazonAccountAuthenticator {
            public static IAmazonAccountAuthenticator sDefaultImpl;
            private IBinder mRemote;

            public Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.amazon.dcp.sso.IAmazonAccountAuthenticator
            public void callGetCredentialsWebservice(Uri uri, String str, Map map, byte[] bArr, IWebserviceCallback iWebserviceCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.amazon.dcp.sso.IAmazonAccountAuthenticator");
                    if (uri != null) {
                        parcelObtain.writeInt(1);
                        uri.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str);
                    parcelObtain.writeMap(map);
                    parcelObtain.writeByteArray(bArr);
                    parcelObtain.writeStrongBinder(iWebserviceCallback != null ? iWebserviceCallback.asBinder() : null);
                    if (this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                        parcelObtain2.readException();
                    } else {
                        Stub.getDefaultImpl().callGetCredentialsWebservice(uri, str, map, bArr, iWebserviceCallback);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.amazon.dcp.sso.IAmazonAccountAuthenticator
            public void invalidateAuthToken(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.amazon.dcp.sso.IAmazonAccountAuthenticator");
                    parcelObtain.writeString(str);
                    if (this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                        parcelObtain2.readException();
                    } else {
                        Stub.getDefaultImpl().invalidateAuthToken(str);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.amazon.dcp.sso.IAmazonAccountAuthenticator
            public void invalidateAuthTokenByTokenType(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.amazon.dcp.sso.IAmazonAccountAuthenticator");
                    parcelObtain.writeString(str);
                    if (this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                        parcelObtain2.readException();
                    } else {
                        Stub.getDefaultImpl().invalidateAuthTokenByTokenType(str);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.amazon.dcp.sso.IAmazonAccountAuthenticator
            public String peekAuthToken(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.amazon.dcp.sso.IAmazonAccountAuthenticator");
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().peekAuthToken(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.amazon.dcp.sso.IAmazonAccountAuthenticator
            public void storeToken(String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.amazon.dcp.sso.IAmazonAccountAuthenticator");
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                        parcelObtain2.readException();
                    } else {
                        Stub.getDefaultImpl().storeToken(str, str2);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, "com.amazon.dcp.sso.IAmazonAccountAuthenticator");
        }

        public static IAmazonAccountAuthenticator asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.amazon.dcp.sso.IAmazonAccountAuthenticator");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof IAmazonAccountAuthenticator)) ? new Proxy(iBinder) : (IAmazonAccountAuthenticator) iInterfaceQueryLocalInterface;
        }

        public static IAmazonAccountAuthenticator getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1598968902) {
                parcel2.writeString("com.amazon.dcp.sso.IAmazonAccountAuthenticator");
                return true;
            }
            if (i == 1) {
                parcel.enforceInterface("com.amazon.dcp.sso.IAmazonAccountAuthenticator");
                callGetCredentialsWebservice(parcel.readInt() != 0 ? (Uri) Uri.CREATOR.createFromParcel(parcel) : null, parcel.readString(), parcel.readHashMap(getClass().getClassLoader()), parcel.createByteArray(), IWebserviceCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface("com.amazon.dcp.sso.IAmazonAccountAuthenticator");
                storeToken(parcel.readString(), parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface("com.amazon.dcp.sso.IAmazonAccountAuthenticator");
                String strPeekAuthToken = peekAuthToken(parcel.readString());
                parcel2.writeNoException();
                parcel2.writeString(strPeekAuthToken);
                return true;
            }
            if (i == 4) {
                parcel.enforceInterface("com.amazon.dcp.sso.IAmazonAccountAuthenticator");
                invalidateAuthToken(parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            if (i != 5) {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface("com.amazon.dcp.sso.IAmazonAccountAuthenticator");
            invalidateAuthTokenByTokenType(parcel.readString());
            parcel2.writeNoException();
            return true;
        }
    }

    void callGetCredentialsWebservice(Uri uri, String str, Map map, byte[] bArr, IWebserviceCallback iWebserviceCallback) throws RemoteException;

    void invalidateAuthToken(String str) throws RemoteException;

    void invalidateAuthTokenByTokenType(String str) throws RemoteException;

    String peekAuthToken(String str) throws RemoteException;

    void storeToken(String str, String str2) throws RemoteException;
}
