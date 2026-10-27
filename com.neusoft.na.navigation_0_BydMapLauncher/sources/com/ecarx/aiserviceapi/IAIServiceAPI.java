package com.ecarx.aiserviceapi;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.ecarx.aiserviceapi.IAIServiceObserver;

/* JADX INFO: loaded from: classes2.dex */
public interface IAIServiceAPI extends IInterface {

    public static class Default implements IAIServiceAPI {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.ecarx.aiserviceapi.IAIServiceAPI
        public void awakenAI() throws RemoteException {
        }

        @Override // com.ecarx.aiserviceapi.IAIServiceAPI
        public void playSMSTTS(String words) throws RemoteException {
        }

        @Override // com.ecarx.aiserviceapi.IAIServiceAPI
        public void playTTS(String words) throws RemoteException {
        }

        @Override // com.ecarx.aiserviceapi.IAIServiceAPI
        public void playTTSByType(int instanceType, String words) throws RemoteException {
        }

        @Override // com.ecarx.aiserviceapi.IAIServiceAPI
        public void registerAIServiceObserver(IAIServiceObserver observer) throws RemoteException {
        }

        @Override // com.ecarx.aiserviceapi.IAIServiceAPI
        public void startVoiceToText() throws RemoteException {
        }

        @Override // com.ecarx.aiserviceapi.IAIServiceAPI
        public void stopTTS() throws RemoteException {
        }

        @Override // com.ecarx.aiserviceapi.IAIServiceAPI
        public void stopTTSByType(int instanceType) throws RemoteException {
        }

        @Override // com.ecarx.aiserviceapi.IAIServiceAPI
        public void stopVoiceToText() throws RemoteException {
        }

        @Override // com.ecarx.aiserviceapi.IAIServiceAPI
        public void unregisterAIServiceObserver(IAIServiceObserver observer) throws RemoteException {
        }
    }

    void awakenAI() throws RemoteException;

    void playSMSTTS(String words) throws RemoteException;

    void playTTS(String words) throws RemoteException;

    void playTTSByType(int instanceType, String words) throws RemoteException;

    void registerAIServiceObserver(IAIServiceObserver observer) throws RemoteException;

    void startVoiceToText() throws RemoteException;

    void stopTTS() throws RemoteException;

    void stopTTSByType(int instanceType) throws RemoteException;

    void stopVoiceToText() throws RemoteException;

    void unregisterAIServiceObserver(IAIServiceObserver observer) throws RemoteException;

    public static abstract class Stub extends Binder implements IAIServiceAPI {
        private static final String DESCRIPTOR = "com.ecarx.aiserviceapi.IAIServiceAPI";
        static final int TRANSACTION_awakenAI = 5;
        static final int TRANSACTION_playSMSTTS = 10;
        static final int TRANSACTION_playTTS = 3;
        static final int TRANSACTION_playTTSByType = 8;
        static final int TRANSACTION_registerAIServiceObserver = 1;
        static final int TRANSACTION_startVoiceToText = 6;
        static final int TRANSACTION_stopTTS = 4;
        static final int TRANSACTION_stopTTSByType = 9;
        static final int TRANSACTION_stopVoiceToText = 7;
        static final int TRANSACTION_unregisterAIServiceObserver = 2;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IAIServiceAPI asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = obj.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IAIServiceAPI)) {
                return (IAIServiceAPI) iInterfaceQueryLocalInterface;
            }
            return new Proxy(obj);
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            if (code == 1598968902) {
                reply.writeString(DESCRIPTOR);
                return true;
            }
            switch (code) {
                case 1:
                    data.enforceInterface(DESCRIPTOR);
                    registerAIServiceObserver(IAIServiceObserver.Stub.asInterface(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    unregisterAIServiceObserver(IAIServiceObserver.Stub.asInterface(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case 3:
                    data.enforceInterface(DESCRIPTOR);
                    playTTS(data.readString());
                    reply.writeNoException();
                    return true;
                case 4:
                    data.enforceInterface(DESCRIPTOR);
                    stopTTS();
                    reply.writeNoException();
                    return true;
                case 5:
                    data.enforceInterface(DESCRIPTOR);
                    awakenAI();
                    reply.writeNoException();
                    return true;
                case 6:
                    data.enforceInterface(DESCRIPTOR);
                    startVoiceToText();
                    reply.writeNoException();
                    return true;
                case 7:
                    data.enforceInterface(DESCRIPTOR);
                    stopVoiceToText();
                    reply.writeNoException();
                    return true;
                case 8:
                    data.enforceInterface(DESCRIPTOR);
                    playTTSByType(data.readInt(), data.readString());
                    reply.writeNoException();
                    return true;
                case 9:
                    data.enforceInterface(DESCRIPTOR);
                    stopTTSByType(data.readInt());
                    reply.writeNoException();
                    return true;
                case 10:
                    data.enforceInterface(DESCRIPTOR);
                    playSMSTTS(data.readString());
                    reply.writeNoException();
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        private static class Proxy implements IAIServiceAPI {
            public static IAIServiceAPI sDefaultImpl;
            private IBinder mRemote;

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.ecarx.aiserviceapi.IAIServiceAPI
            public void registerAIServiceObserver(IAIServiceObserver observer) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(observer != null ? observer.asBinder() : null);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerAIServiceObserver(observer);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.aiserviceapi.IAIServiceAPI
            public void unregisterAIServiceObserver(IAIServiceObserver observer) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(observer != null ? observer.asBinder() : null);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterAIServiceObserver(observer);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.aiserviceapi.IAIServiceAPI
            public void playTTS(String words) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(words);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().playTTS(words);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.aiserviceapi.IAIServiceAPI
            public void stopTTS() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().stopTTS();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.aiserviceapi.IAIServiceAPI
            public void awakenAI() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().awakenAI();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.aiserviceapi.IAIServiceAPI
            public void startVoiceToText() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().startVoiceToText();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.aiserviceapi.IAIServiceAPI
            public void stopVoiceToText() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().stopVoiceToText();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.aiserviceapi.IAIServiceAPI
            public void playTTSByType(int instanceType, String words) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(instanceType);
                    parcelObtain.writeString(words);
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().playTTSByType(instanceType, words);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.aiserviceapi.IAIServiceAPI
            public void stopTTSByType(int instanceType) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(instanceType);
                    if (!this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().stopTTSByType(instanceType);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.aiserviceapi.IAIServiceAPI
            public void playSMSTTS(String words) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(words);
                    if (!this.mRemote.transact(10, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().playSMSTTS(words);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IAIServiceAPI impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static IAIServiceAPI getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
