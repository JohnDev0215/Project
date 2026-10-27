package android.media.soundtrigger_middleware;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public interface ISoundTriggerCallback extends IInterface {

    public static class Default implements ISoundTriggerCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.media.soundtrigger_middleware.ISoundTriggerCallback
        public void onModuleDied() throws RemoteException {
        }

        @Override // android.media.soundtrigger_middleware.ISoundTriggerCallback
        public void onPhraseRecognition(int i, PhraseRecognitionEvent phraseRecognitionEvent) throws RemoteException {
        }

        @Override // android.media.soundtrigger_middleware.ISoundTriggerCallback
        public void onRecognition(int i, RecognitionEvent recognitionEvent) throws RemoteException {
        }

        @Override // android.media.soundtrigger_middleware.ISoundTriggerCallback
        public void onRecognitionAvailabilityChange(boolean z) throws RemoteException {
        }
    }

    void onModuleDied() throws RemoteException;

    void onPhraseRecognition(int i, PhraseRecognitionEvent phraseRecognitionEvent) throws RemoteException;

    void onRecognition(int i, RecognitionEvent recognitionEvent) throws RemoteException;

    void onRecognitionAvailabilityChange(boolean z) throws RemoteException;

    public static abstract class Stub extends Binder implements ISoundTriggerCallback {
        private static final String DESCRIPTOR = "android.media.soundtrigger_middleware.ISoundTriggerCallback";
        static final int TRANSACTION_onModuleDied = 4;
        static final int TRANSACTION_onPhraseRecognition = 2;
        static final int TRANSACTION_onRecognition = 1;
        static final int TRANSACTION_onRecognitionAvailabilityChange = 3;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "onRecognition";
            }
            if (i == 2) {
                return "onPhraseRecognition";
            }
            if (i == 3) {
                return "onRecognitionAvailabilityChange";
            }
            if (i != 4) {
                return null;
            }
            return "onModuleDied";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ISoundTriggerCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof ISoundTriggerCallback)) {
                return (ISoundTriggerCallback) iInterfaceQueryLocalInterface;
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
                onRecognition(parcel.readInt(), parcel.readInt() != 0 ? RecognitionEvent.CREATOR.createFromParcel(parcel) : null);
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                onPhraseRecognition(parcel.readInt(), parcel.readInt() != 0 ? PhraseRecognitionEvent.CREATOR.createFromParcel(parcel) : null);
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                onRecognitionAvailabilityChange(parcel.readInt() != 0);
                return true;
            }
            if (i == 4) {
                parcel.enforceInterface(DESCRIPTOR);
                onModuleDied();
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements ISoundTriggerCallback {
            public static ISoundTriggerCallback sDefaultImpl;
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

            @Override // android.media.soundtrigger_middleware.ISoundTriggerCallback
            public void onRecognition(int i, RecognitionEvent recognitionEvent) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (recognitionEvent != null) {
                        parcelObtain.writeInt(1);
                        recognitionEvent.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onRecognition(i, recognitionEvent);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.soundtrigger_middleware.ISoundTriggerCallback
            public void onPhraseRecognition(int i, PhraseRecognitionEvent phraseRecognitionEvent) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (phraseRecognitionEvent != null) {
                        parcelObtain.writeInt(1);
                        phraseRecognitionEvent.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onPhraseRecognition(i, phraseRecognitionEvent);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.soundtrigger_middleware.ISoundTriggerCallback
            public void onRecognitionAvailabilityChange(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onRecognitionAvailabilityChange(z);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.soundtrigger_middleware.ISoundTriggerCallback
            public void onModuleDied() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(4, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onModuleDied();
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ISoundTriggerCallback iSoundTriggerCallback) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iSoundTriggerCallback == null) {
                return false;
            }
            Proxy.sDefaultImpl = iSoundTriggerCallback;
            return true;
        }

        public static ISoundTriggerCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
