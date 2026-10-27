package com.ecarx.aiserviceapi;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public interface IAIServiceObserver extends IInterface {

    public static class Default implements IAIServiceObserver {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.ecarx.aiserviceapi.IAIServiceObserver
        public void onAIStateChangeObserver(int state) throws RemoteException {
        }

        @Override // com.ecarx.aiserviceapi.IAIServiceObserver
        public void onTTSStateChangeByTypeObserver(int instanceType, int state) throws RemoteException {
        }

        @Override // com.ecarx.aiserviceapi.IAIServiceObserver
        public void onTTSStateChangeObserver(int state) throws RemoteException {
        }

        @Override // com.ecarx.aiserviceapi.IAIServiceObserver
        public int readMessage(int type, String contactName, String phoneNumber) throws RemoteException {
            return 0;
        }

        @Override // com.ecarx.aiserviceapi.IAIServiceObserver
        public int sendMessage(int type, String contactName, String phoneNumber, String messageContent) throws RemoteException {
            return 0;
        }

        @Override // com.ecarx.aiserviceapi.IAIServiceObserver
        public int updateMessageContent(String content, boolean isFinal) throws RemoteException {
            return 0;
        }
    }

    void onAIStateChangeObserver(int state) throws RemoteException;

    void onTTSStateChangeByTypeObserver(int instanceType, int state) throws RemoteException;

    void onTTSStateChangeObserver(int state) throws RemoteException;

    int readMessage(int type, String contactName, String phoneNumber) throws RemoteException;

    int sendMessage(int type, String contactName, String phoneNumber, String messageContent) throws RemoteException;

    int updateMessageContent(String content, boolean isFinal) throws RemoteException;

    public static abstract class Stub extends Binder implements IAIServiceObserver {
        private static final String DESCRIPTOR = "com.ecarx.aiserviceapi.IAIServiceObserver";
        static final int TRANSACTION_onAIStateChangeObserver = 1;
        static final int TRANSACTION_onTTSStateChangeByTypeObserver = 3;
        static final int TRANSACTION_onTTSStateChangeObserver = 2;
        static final int TRANSACTION_readMessage = 5;
        static final int TRANSACTION_sendMessage = 4;
        static final int TRANSACTION_updateMessageContent = 6;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IAIServiceObserver asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = obj.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IAIServiceObserver)) {
                return (IAIServiceObserver) iInterfaceQueryLocalInterface;
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
                    onAIStateChangeObserver(data.readInt());
                    reply.writeNoException();
                    return true;
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    onTTSStateChangeObserver(data.readInt());
                    reply.writeNoException();
                    return true;
                case 3:
                    data.enforceInterface(DESCRIPTOR);
                    onTTSStateChangeByTypeObserver(data.readInt(), data.readInt());
                    reply.writeNoException();
                    return true;
                case 4:
                    data.enforceInterface(DESCRIPTOR);
                    int iSendMessage = sendMessage(data.readInt(), data.readString(), data.readString(), data.readString());
                    reply.writeNoException();
                    reply.writeInt(iSendMessage);
                    return true;
                case 5:
                    data.enforceInterface(DESCRIPTOR);
                    int message = readMessage(data.readInt(), data.readString(), data.readString());
                    reply.writeNoException();
                    reply.writeInt(message);
                    return true;
                case 6:
                    data.enforceInterface(DESCRIPTOR);
                    int iUpdateMessageContent = updateMessageContent(data.readString(), data.readInt() != 0);
                    reply.writeNoException();
                    reply.writeInt(iUpdateMessageContent);
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        private static class Proxy implements IAIServiceObserver {
            public static IAIServiceObserver sDefaultImpl;
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

            @Override // com.ecarx.aiserviceapi.IAIServiceObserver
            public void onAIStateChangeObserver(int state) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(state);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onAIStateChangeObserver(state);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.aiserviceapi.IAIServiceObserver
            public void onTTSStateChangeObserver(int state) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(state);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onTTSStateChangeObserver(state);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.aiserviceapi.IAIServiceObserver
            public void onTTSStateChangeByTypeObserver(int instanceType, int state) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(instanceType);
                    parcelObtain.writeInt(state);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onTTSStateChangeByTypeObserver(instanceType, state);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.aiserviceapi.IAIServiceObserver
            public int sendMessage(int type, String contactName, String phoneNumber, String messageContent) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(type);
                    parcelObtain.writeString(contactName);
                    parcelObtain.writeString(phoneNumber);
                    parcelObtain.writeString(messageContent);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().sendMessage(type, contactName, phoneNumber, messageContent);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.aiserviceapi.IAIServiceObserver
            public int readMessage(int type, String contactName, String phoneNumber) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(type);
                    parcelObtain.writeString(contactName);
                    parcelObtain.writeString(phoneNumber);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().readMessage(type, contactName, phoneNumber);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.aiserviceapi.IAIServiceObserver
            public int updateMessageContent(String content, boolean isFinal) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(content);
                    parcelObtain.writeInt(isFinal ? 1 : 0);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().updateMessageContent(content, isFinal);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IAIServiceObserver impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static IAIServiceObserver getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
