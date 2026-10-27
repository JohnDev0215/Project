package com.byd.ttsserviceapi;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.byd.ttsserviceapi.ITTSStatusCallback;

/* JADX INFO: loaded from: classes.dex */
public interface ITTSServiceApi extends IInterface {
    public static final int PLAYSTATE_PAUSED = 1;
    public static final int PLAYSTATE_STARTED = 2;
    public static final int PLAYSTATE_STOPPED = 3;

    public static class Default implements ITTSServiceApi {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.byd.ttsserviceapi.ITTSServiceApi
        public void pauseTTS() throws RemoteException {
        }

        @Override // com.byd.ttsserviceapi.ITTSServiceApi
        public void resumeTTS() throws RemoteException {
        }

        @Override // com.byd.ttsserviceapi.ITTSServiceApi
        public void startTTS(String str, boolean z, ITTSStatusCallback iTTSStatusCallback) throws RemoteException {
        }

        @Override // com.byd.ttsserviceapi.ITTSServiceApi
        public boolean stopTTS() throws RemoteException {
            return false;
        }
    }

    void pauseTTS() throws RemoteException;

    void resumeTTS() throws RemoteException;

    void startTTS(String str, boolean z, ITTSStatusCallback iTTSStatusCallback) throws RemoteException;

    boolean stopTTS() throws RemoteException;

    public static abstract class Stub extends Binder implements ITTSServiceApi {
        private static final String DESCRIPTOR = "com.byd.ttsserviceapi.ITTSServiceApi";
        static final int TRANSACTION_pauseTTS = 2;
        static final int TRANSACTION_resumeTTS = 3;
        static final int TRANSACTION_startTTS = 1;
        static final int TRANSACTION_stopTTS = 4;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ITTSServiceApi asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof ITTSServiceApi)) {
                return (ITTSServiceApi) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                startTTS(parcel.readString(), parcel.readInt() != 0, ITTSStatusCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                pauseTTS();
                parcel2.writeNoException();
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                resumeTTS();
                parcel2.writeNoException();
                return true;
            }
            if (i != 4) {
                if (i == 1598968902) {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface(DESCRIPTOR);
            boolean zStopTTS = stopTTS();
            parcel2.writeNoException();
            parcel2.writeInt(zStopTTS ? 1 : 0);
            return true;
        }

        private static class Proxy implements ITTSServiceApi {
            public static ITTSServiceApi sDefaultImpl;
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

            @Override // com.byd.ttsserviceapi.ITTSServiceApi
            public void startTTS(String str, boolean z, ITTSStatusCallback iTTSStatusCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeStrongBinder(iTTSStatusCallback != null ? iTTSStatusCallback.asBinder() : null);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().startTTS(str, z, iTTSStatusCallback);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.ttsserviceapi.ITTSServiceApi
            public void pauseTTS() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().pauseTTS();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.ttsserviceapi.ITTSServiceApi
            public void resumeTTS() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().resumeTTS();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.ttsserviceapi.ITTSServiceApi
            public boolean stopTTS() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().stopTTS();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ITTSServiceApi iTTSServiceApi) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iTTSServiceApi == null) {
                return false;
            }
            Proxy.sDefaultImpl = iTTSServiceApi;
            return true;
        }

        public static ITTSServiceApi getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
