package cerence.ark.assistant.service;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import cerence.ark.assistant.framework.data.AudioPlaybackMode;
import cerence.ark.assistant.framework.prompter.PromptDataType;
import cerence.ark.assistant.framework.prompter.PrompterParameterType;
import cerence.ark.assistant.service.IPrompterCallback;

/* JADX INFO: loaded from: classes2.dex */
public interface IPrompterService extends IInterface {
    public static final String DESCRIPTOR = "cerence.ark.assistant.service.IPrompterService";

    public static class Default implements IPrompterService {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // cerence.ark.assistant.service.IPrompterService
        public void pause() throws RemoteException {
        }

        @Override // cerence.ark.assistant.service.IPrompterService
        public int play(String str, String str2, int i, int i2, PromptDataType promptDataType, boolean z, AudioPlaybackMode audioPlaybackMode, String str3, String str4, IPrompterCallback iPrompterCallback) throws RemoteException {
            return 0;
        }

        @Override // cerence.ark.assistant.service.IPrompterService
        public RemoteResult prepareVoice(String str, String str2) throws RemoteException {
            return null;
        }

        @Override // cerence.ark.assistant.service.IPrompterService
        public void resume() throws RemoteException {
        }

        @Override // cerence.ark.assistant.service.IPrompterService
        public RemoteResult setPrompterParameter(PrompterParameterType prompterParameterType, int i) throws RemoteException {
            return null;
        }

        @Override // cerence.ark.assistant.service.IPrompterService
        public void stop() throws RemoteException {
        }
    }

    void pause() throws RemoteException;

    int play(String str, String str2, int i, int i2, PromptDataType promptDataType, boolean z, AudioPlaybackMode audioPlaybackMode, String str3, String str4, IPrompterCallback iPrompterCallback) throws RemoteException;

    RemoteResult prepareVoice(String str, String str2) throws RemoteException;

    void resume() throws RemoteException;

    RemoteResult setPrompterParameter(PrompterParameterType prompterParameterType, int i) throws RemoteException;

    void stop() throws RemoteException;

    public static abstract class Stub extends Binder implements IPrompterService {
        static final int TRANSACTION_pause = 4;
        static final int TRANSACTION_play = 3;
        static final int TRANSACTION_prepareVoice = 2;
        static final int TRANSACTION_resume = 5;
        static final int TRANSACTION_setPrompterParameter = 1;
        static final int TRANSACTION_stop = 6;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, IPrompterService.DESCRIPTOR);
        }

        public static IPrompterService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(IPrompterService.DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IPrompterService)) {
                return (IPrompterService) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(IPrompterService.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(IPrompterService.DESCRIPTOR);
                return true;
            }
            switch (i) {
                case 1:
                    RemoteResult prompterParameter = setPrompterParameter((PrompterParameterType) _Parcel.readTypedObject(parcel, PrompterParameterType.CREATOR), parcel.readInt());
                    parcel2.writeNoException();
                    _Parcel.writeTypedObject(parcel2, prompterParameter, 1);
                    return true;
                case 2:
                    RemoteResult remoteResultPrepareVoice = prepareVoice(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    _Parcel.writeTypedObject(parcel2, remoteResultPrepareVoice, 1);
                    return true;
                case 3:
                    int iPlay = play(parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readInt(), (PromptDataType) _Parcel.readTypedObject(parcel, PromptDataType.CREATOR), parcel.readInt() != 0, (AudioPlaybackMode) _Parcel.readTypedObject(parcel, AudioPlaybackMode.CREATOR), parcel.readString(), parcel.readString(), IPrompterCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(iPlay);
                    return true;
                case 4:
                    pause();
                    parcel2.writeNoException();
                    return true;
                case 5:
                    resume();
                    parcel2.writeNoException();
                    return true;
                case 6:
                    stop();
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IPrompterService {
            private IBinder mRemote;

            public String getInterfaceDescriptor() {
                return IPrompterService.DESCRIPTOR;
            }

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // cerence.ark.assistant.service.IPrompterService
            public RemoteResult setPrompterParameter(PrompterParameterType prompterParameterType, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IPrompterService.DESCRIPTOR);
                    _Parcel.writeTypedObject(parcelObtain, prompterParameterType, 0);
                    parcelObtain.writeInt(i);
                    this.mRemote.transact(1, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return (RemoteResult) _Parcel.readTypedObject(parcelObtain2, RemoteResult.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // cerence.ark.assistant.service.IPrompterService
            public RemoteResult prepareVoice(String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IPrompterService.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    this.mRemote.transact(2, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return (RemoteResult) _Parcel.readTypedObject(parcelObtain2, RemoteResult.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // cerence.ark.assistant.service.IPrompterService
            public int play(String str, String str2, int i, int i2, PromptDataType promptDataType, boolean z, AudioPlaybackMode audioPlaybackMode, String str3, String str4, IPrompterCallback iPrompterCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IPrompterService.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    _Parcel.writeTypedObject(parcelObtain, promptDataType, 0);
                    parcelObtain.writeInt(z ? 1 : 0);
                    _Parcel.writeTypedObject(parcelObtain, audioPlaybackMode, 0);
                    parcelObtain.writeString(str3);
                    parcelObtain.writeString(str4);
                    parcelObtain.writeStrongInterface(iPrompterCallback);
                    this.mRemote.transact(3, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // cerence.ark.assistant.service.IPrompterService
            public void pause() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IPrompterService.DESCRIPTOR);
                    this.mRemote.transact(4, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // cerence.ark.assistant.service.IPrompterService
            public void resume() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IPrompterService.DESCRIPTOR);
                    this.mRemote.transact(5, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // cerence.ark.assistant.service.IPrompterService
            public void stop() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IPrompterService.DESCRIPTOR);
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
