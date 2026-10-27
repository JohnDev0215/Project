package cerence.ark.assistant.service;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public interface IDictationManagerListener extends IInterface {
    public static final String DESCRIPTOR = "cerence.ark.assistant.service.IDictationManagerListener";

    public static class Default implements IDictationManagerListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // cerence.ark.assistant.service.IDictationManagerListener
        public void onBeginningOfSpeech() throws RemoteException {
        }

        @Override // cerence.ark.assistant.service.IDictationManagerListener
        public void onEndOfSpeech() throws RemoteException {
        }

        @Override // cerence.ark.assistant.service.IDictationManagerListener
        public void onError(int i) throws RemoteException {
        }

        @Override // cerence.ark.assistant.service.IDictationManagerListener
        public void onReadyForSpeech() throws RemoteException {
        }

        @Override // cerence.ark.assistant.service.IDictationManagerListener
        public void onRecognizedSpeech(String str, boolean z) throws RemoteException {
        }

        @Override // cerence.ark.assistant.service.IDictationManagerListener
        public void onRmsChanged(int i) throws RemoteException {
        }
    }

    void onBeginningOfSpeech() throws RemoteException;

    void onEndOfSpeech() throws RemoteException;

    void onError(int i) throws RemoteException;

    void onReadyForSpeech() throws RemoteException;

    void onRecognizedSpeech(String str, boolean z) throws RemoteException;

    void onRmsChanged(int i) throws RemoteException;

    public static abstract class Stub extends Binder implements IDictationManagerListener {
        static final int TRANSACTION_onBeginningOfSpeech = 1;
        static final int TRANSACTION_onEndOfSpeech = 2;
        static final int TRANSACTION_onError = 3;
        static final int TRANSACTION_onReadyForSpeech = 4;
        static final int TRANSACTION_onRecognizedSpeech = 5;
        static final int TRANSACTION_onRmsChanged = 6;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, IDictationManagerListener.DESCRIPTOR);
        }

        public static IDictationManagerListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(IDictationManagerListener.DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IDictationManagerListener)) {
                return (IDictationManagerListener) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(IDictationManagerListener.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(IDictationManagerListener.DESCRIPTOR);
                return true;
            }
            switch (i) {
                case 1:
                    onBeginningOfSpeech();
                    parcel2.writeNoException();
                    return true;
                case 2:
                    onEndOfSpeech();
                    parcel2.writeNoException();
                    return true;
                case 3:
                    onError(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 4:
                    onReadyForSpeech();
                    parcel2.writeNoException();
                    return true;
                case 5:
                    onRecognizedSpeech(parcel.readString(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 6:
                    onRmsChanged(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IDictationManagerListener {
            private IBinder mRemote;

            public String getInterfaceDescriptor() {
                return IDictationManagerListener.DESCRIPTOR;
            }

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // cerence.ark.assistant.service.IDictationManagerListener
            public void onBeginningOfSpeech() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IDictationManagerListener.DESCRIPTOR);
                    this.mRemote.transact(1, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // cerence.ark.assistant.service.IDictationManagerListener
            public void onEndOfSpeech() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IDictationManagerListener.DESCRIPTOR);
                    this.mRemote.transact(2, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // cerence.ark.assistant.service.IDictationManagerListener
            public void onError(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IDictationManagerListener.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    this.mRemote.transact(3, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // cerence.ark.assistant.service.IDictationManagerListener
            public void onReadyForSpeech() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IDictationManagerListener.DESCRIPTOR);
                    this.mRemote.transact(4, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // cerence.ark.assistant.service.IDictationManagerListener
            public void onRecognizedSpeech(String str, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IDictationManagerListener.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(z ? 1 : 0);
                    this.mRemote.transact(5, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // cerence.ark.assistant.service.IDictationManagerListener
            public void onRmsChanged(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IDictationManagerListener.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    this.mRemote.transact(6, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }
    }
}
