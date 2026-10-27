package android.media.soundtrigger_middleware;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public interface ISoundTriggerModule extends IInterface {

    public static class Default implements ISoundTriggerModule {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.media.soundtrigger_middleware.ISoundTriggerModule
        public void detach() throws RemoteException {
        }

        @Override // android.media.soundtrigger_middleware.ISoundTriggerModule
        public void forceRecognitionEvent(int i) throws RemoteException {
        }

        @Override // android.media.soundtrigger_middleware.ISoundTriggerModule
        public int getModelParameter(int i, int i2) throws RemoteException {
            return 0;
        }

        @Override // android.media.soundtrigger_middleware.ISoundTriggerModule
        public int loadModel(SoundModel soundModel) throws RemoteException {
            return 0;
        }

        @Override // android.media.soundtrigger_middleware.ISoundTriggerModule
        public int loadPhraseModel(PhraseSoundModel phraseSoundModel) throws RemoteException {
            return 0;
        }

        @Override // android.media.soundtrigger_middleware.ISoundTriggerModule
        public ModelParameterRange queryModelParameterSupport(int i, int i2) throws RemoteException {
            return null;
        }

        @Override // android.media.soundtrigger_middleware.ISoundTriggerModule
        public void setModelParameter(int i, int i2, int i3) throws RemoteException {
        }

        @Override // android.media.soundtrigger_middleware.ISoundTriggerModule
        public void startRecognition(int i, RecognitionConfig recognitionConfig) throws RemoteException {
        }

        @Override // android.media.soundtrigger_middleware.ISoundTriggerModule
        public void stopRecognition(int i) throws RemoteException {
        }

        @Override // android.media.soundtrigger_middleware.ISoundTriggerModule
        public void unloadModel(int i) throws RemoteException {
        }
    }

    void detach() throws RemoteException;

    void forceRecognitionEvent(int i) throws RemoteException;

    int getModelParameter(int i, int i2) throws RemoteException;

    int loadModel(SoundModel soundModel) throws RemoteException;

    int loadPhraseModel(PhraseSoundModel phraseSoundModel) throws RemoteException;

    ModelParameterRange queryModelParameterSupport(int i, int i2) throws RemoteException;

    void setModelParameter(int i, int i2, int i3) throws RemoteException;

    void startRecognition(int i, RecognitionConfig recognitionConfig) throws RemoteException;

    void stopRecognition(int i) throws RemoteException;

    void unloadModel(int i) throws RemoteException;

    public static abstract class Stub extends Binder implements ISoundTriggerModule {
        private static final String DESCRIPTOR = "android.media.soundtrigger_middleware.ISoundTriggerModule";
        static final int TRANSACTION_detach = 10;
        static final int TRANSACTION_forceRecognitionEvent = 6;
        static final int TRANSACTION_getModelParameter = 8;
        static final int TRANSACTION_loadModel = 1;
        static final int TRANSACTION_loadPhraseModel = 2;
        static final int TRANSACTION_queryModelParameterSupport = 9;
        static final int TRANSACTION_setModelParameter = 7;
        static final int TRANSACTION_startRecognition = 4;
        static final int TRANSACTION_stopRecognition = 5;
        static final int TRANSACTION_unloadModel = 3;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "loadModel";
                case 2:
                    return "loadPhraseModel";
                case 3:
                    return "unloadModel";
                case 4:
                    return "startRecognition";
                case 5:
                    return "stopRecognition";
                case 6:
                    return "forceRecognitionEvent";
                case 7:
                    return "setModelParameter";
                case 8:
                    return "getModelParameter";
                case 9:
                    return "queryModelParameterSupport";
                case 10:
                    return "detach";
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

        public static ISoundTriggerModule asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof ISoundTriggerModule)) {
                return (ISoundTriggerModule) iInterfaceQueryLocalInterface;
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
                    int iLoadModel = loadModel(parcel.readInt() != 0 ? SoundModel.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(iLoadModel);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iLoadPhraseModel = loadPhraseModel(parcel.readInt() != 0 ? PhraseSoundModel.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(iLoadPhraseModel);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    unloadModel(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    startRecognition(parcel.readInt(), parcel.readInt() != 0 ? RecognitionConfig.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    stopRecognition(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    forceRecognitionEvent(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    setModelParameter(parcel.readInt(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    int modelParameter = getModelParameter(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(modelParameter);
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    ModelParameterRange modelParameterRangeQueryModelParameterSupport = queryModelParameterSupport(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    if (modelParameterRangeQueryModelParameterSupport != null) {
                        parcel2.writeInt(1);
                        modelParameterRangeQueryModelParameterSupport.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    detach();
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements ISoundTriggerModule {
            public static ISoundTriggerModule sDefaultImpl;
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

            @Override // android.media.soundtrigger_middleware.ISoundTriggerModule
            public int loadModel(SoundModel soundModel) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (soundModel != null) {
                        parcelObtain.writeInt(1);
                        soundModel.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().loadModel(soundModel);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.soundtrigger_middleware.ISoundTriggerModule
            public int loadPhraseModel(PhraseSoundModel phraseSoundModel) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (phraseSoundModel != null) {
                        parcelObtain.writeInt(1);
                        phraseSoundModel.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().loadPhraseModel(phraseSoundModel);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.soundtrigger_middleware.ISoundTriggerModule
            public void unloadModel(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unloadModel(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.soundtrigger_middleware.ISoundTriggerModule
            public void startRecognition(int i, RecognitionConfig recognitionConfig) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (recognitionConfig != null) {
                        parcelObtain.writeInt(1);
                        recognitionConfig.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().startRecognition(i, recognitionConfig);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.soundtrigger_middleware.ISoundTriggerModule
            public void stopRecognition(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().stopRecognition(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.soundtrigger_middleware.ISoundTriggerModule
            public void forceRecognitionEvent(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().forceRecognitionEvent(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.soundtrigger_middleware.ISoundTriggerModule
            public void setModelParameter(int i, int i2, int i3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setModelParameter(i, i2, i3);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.soundtrigger_middleware.ISoundTriggerModule
            public int getModelParameter(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getModelParameter(i, i2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.soundtrigger_middleware.ISoundTriggerModule
            public ModelParameterRange queryModelParameterSupport(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().queryModelParameterSupport(i, i2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? ModelParameterRange.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.soundtrigger_middleware.ISoundTriggerModule
            public void detach() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(10, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().detach();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ISoundTriggerModule iSoundTriggerModule) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iSoundTriggerModule == null) {
                return false;
            }
            Proxy.sDefaultImpl = iSoundTriggerModule;
            return true;
        }

        public static ISoundTriggerModule getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
