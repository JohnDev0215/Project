package cerence.ark.assistant.service;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public interface SpeechSynthesisCallback extends IInterface {
    public static final String DESCRIPTOR = "cerence.ark.assistant.service.SpeechSynthesisCallback";

    public static class Default implements SpeechSynthesisCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // cerence.ark.assistant.service.SpeechSynthesisCallback
        public void onAudio(String str, byte[] bArr, int i) throws RemoteException {
        }

        @Override // cerence.ark.assistant.service.SpeechSynthesisCallback
        public void onError(String str, String str2, int i) throws RemoteException {
        }

        @Override // cerence.ark.assistant.service.SpeechSynthesisCallback
        public void onEvent(String str, String str2, int i) throws RemoteException {
        }
    }

    void onAudio(String str, byte[] bArr, int i) throws RemoteException;

    void onError(String str, String str2, int i) throws RemoteException;

    void onEvent(String str, String str2, int i) throws RemoteException;

    public static abstract class Stub extends Binder implements SpeechSynthesisCallback {
        static final int TRANSACTION_onAudio = 1;
        static final int TRANSACTION_onError = 2;
        static final int TRANSACTION_onEvent = 3;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, SpeechSynthesisCallback.DESCRIPTOR);
        }

        public static SpeechSynthesisCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(SpeechSynthesisCallback.DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof SpeechSynthesisCallback)) {
                return (SpeechSynthesisCallback) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(SpeechSynthesisCallback.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(SpeechSynthesisCallback.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                onAudio(parcel.readString(), parcel.createByteArray(), parcel.readInt());
                parcel2.writeNoException();
            } else if (i == 2) {
                onError(parcel.readString(), parcel.readString(), parcel.readInt());
                parcel2.writeNoException();
            } else if (i == 3) {
                onEvent(parcel.readString(), parcel.readString(), parcel.readInt());
                parcel2.writeNoException();
            } else {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            return true;
        }

        private static class Proxy implements SpeechSynthesisCallback {
            private IBinder mRemote;

            public String getInterfaceDescriptor() {
                return SpeechSynthesisCallback.DESCRIPTOR;
            }

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // cerence.ark.assistant.service.SpeechSynthesisCallback
            public void onAudio(String str, byte[] bArr, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(SpeechSynthesisCallback.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeByteArray(bArr);
                    parcelObtain.writeInt(i);
                    this.mRemote.transact(1, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // cerence.ark.assistant.service.SpeechSynthesisCallback
            public void onError(String str, String str2, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(SpeechSynthesisCallback.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeInt(i);
                    this.mRemote.transact(2, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // cerence.ark.assistant.service.SpeechSynthesisCallback
            public void onEvent(String str, String str2, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(SpeechSynthesisCallback.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeInt(i);
                    this.mRemote.transact(3, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }
    }
}
