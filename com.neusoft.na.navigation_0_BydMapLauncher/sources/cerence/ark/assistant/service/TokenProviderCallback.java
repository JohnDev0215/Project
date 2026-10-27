package cerence.ark.assistant.service;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import cerence.ark.assistant.framework.data.token.TokenConfig;
import cerence.ark.assistant.framework.data.token.TokenInfo;
import cerence.ark.assistant.framework.data.token.TokenProviderType;

/* JADX INFO: loaded from: classes2.dex */
public interface TokenProviderCallback extends IInterface {
    public static final String DESCRIPTOR = "cerence.ark.assistant.service.TokenProviderCallback";

    public static class Default implements TokenProviderCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // cerence.ark.assistant.service.TokenProviderCallback
        public void onAbort() throws RemoteException {
        }

        @Override // cerence.ark.assistant.service.TokenProviderCallback
        public TokenInfo onAuthenticate() throws RemoteException {
            return null;
        }

        @Override // cerence.ark.assistant.service.TokenProviderCallback
        public boolean onConfigure(TokenConfig tokenConfig) throws RemoteException {
            return false;
        }

        @Override // cerence.ark.assistant.service.TokenProviderCallback
        public void onDestroy() throws RemoteException {
        }

        @Override // cerence.ark.assistant.service.TokenProviderCallback
        public TokenProviderType onGetTokenProviderType() throws RemoteException {
            return null;
        }

        @Override // cerence.ark.assistant.service.TokenProviderCallback
        public TokenInfo onRefresh(String str) throws RemoteException {
            return null;
        }
    }

    void onAbort() throws RemoteException;

    TokenInfo onAuthenticate() throws RemoteException;

    boolean onConfigure(TokenConfig tokenConfig) throws RemoteException;

    void onDestroy() throws RemoteException;

    TokenProviderType onGetTokenProviderType() throws RemoteException;

    TokenInfo onRefresh(String str) throws RemoteException;

    public static abstract class Stub extends Binder implements TokenProviderCallback {
        static final int TRANSACTION_onAbort = 5;
        static final int TRANSACTION_onAuthenticate = 3;
        static final int TRANSACTION_onConfigure = 2;
        static final int TRANSACTION_onDestroy = 6;
        static final int TRANSACTION_onGetTokenProviderType = 1;
        static final int TRANSACTION_onRefresh = 4;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, TokenProviderCallback.DESCRIPTOR);
        }

        public static TokenProviderCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(TokenProviderCallback.DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof TokenProviderCallback)) {
                return (TokenProviderCallback) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(TokenProviderCallback.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(TokenProviderCallback.DESCRIPTOR);
                return true;
            }
            switch (i) {
                case 1:
                    TokenProviderType tokenProviderTypeOnGetTokenProviderType = onGetTokenProviderType();
                    parcel2.writeNoException();
                    _Parcel.writeTypedObject(parcel2, tokenProviderTypeOnGetTokenProviderType, 1);
                    return true;
                case 2:
                    boolean zOnConfigure = onConfigure((TokenConfig) _Parcel.readTypedObject(parcel, TokenConfig.CREATOR));
                    parcel2.writeNoException();
                    parcel2.writeInt(zOnConfigure ? 1 : 0);
                    return true;
                case 3:
                    TokenInfo tokenInfoOnAuthenticate = onAuthenticate();
                    parcel2.writeNoException();
                    _Parcel.writeTypedObject(parcel2, tokenInfoOnAuthenticate, 1);
                    return true;
                case 4:
                    TokenInfo tokenInfoOnRefresh = onRefresh(parcel.readString());
                    parcel2.writeNoException();
                    _Parcel.writeTypedObject(parcel2, tokenInfoOnRefresh, 1);
                    return true;
                case 5:
                    onAbort();
                    parcel2.writeNoException();
                    return true;
                case 6:
                    onDestroy();
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements TokenProviderCallback {
            private IBinder mRemote;

            public String getInterfaceDescriptor() {
                return TokenProviderCallback.DESCRIPTOR;
            }

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // cerence.ark.assistant.service.TokenProviderCallback
            public TokenProviderType onGetTokenProviderType() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(TokenProviderCallback.DESCRIPTOR);
                    this.mRemote.transact(1, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return (TokenProviderType) _Parcel.readTypedObject(parcelObtain2, TokenProviderType.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // cerence.ark.assistant.service.TokenProviderCallback
            public boolean onConfigure(TokenConfig tokenConfig) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(TokenProviderCallback.DESCRIPTOR);
                    _Parcel.writeTypedObject(parcelObtain, tokenConfig, 0);
                    this.mRemote.transact(2, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // cerence.ark.assistant.service.TokenProviderCallback
            public TokenInfo onAuthenticate() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(TokenProviderCallback.DESCRIPTOR);
                    this.mRemote.transact(3, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return (TokenInfo) _Parcel.readTypedObject(parcelObtain2, TokenInfo.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // cerence.ark.assistant.service.TokenProviderCallback
            public TokenInfo onRefresh(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(TokenProviderCallback.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    this.mRemote.transact(4, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return (TokenInfo) _Parcel.readTypedObject(parcelObtain2, TokenInfo.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // cerence.ark.assistant.service.TokenProviderCallback
            public void onAbort() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(TokenProviderCallback.DESCRIPTOR);
                    this.mRemote.transact(5, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // cerence.ark.assistant.service.TokenProviderCallback
            public void onDestroy() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(TokenProviderCallback.DESCRIPTOR);
                    this.mRemote.transact(6, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }
    }

    public static class _Parcel {
        /* JADX INFO: Access modifiers changed from: private */
        public static <T> T readTypedObject(Parcel parcel, Parcelable.Creator<T> creator) {
            if (parcel.readInt() != 0) {
                return creator.createFromParcel(parcel);
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static <T extends Parcelable> void writeTypedObject(Parcel parcel, T t, int i) {
            if (t != null) {
                parcel.writeInt(1);
                t.writeToParcel(parcel, i);
            } else {
                parcel.writeInt(0);
            }
        }
    }
}
