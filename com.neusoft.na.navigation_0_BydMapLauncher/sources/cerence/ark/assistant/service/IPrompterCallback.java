package cerence.ark.assistant.service;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import cerence.ark.assistant.framework.prompter.PlayState;
import cerence.ark.assistant.framework.prompter.PrompterError;

/* JADX INFO: loaded from: classes2.dex */
public interface IPrompterCallback extends IInterface {
    public static final String DESCRIPTOR = "cerence.ark.assistant.service.IPrompterCallback";

    public static class Default implements IPrompterCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // cerence.ark.assistant.service.IPrompterCallback
        public void onError(PrompterError prompterError) throws RemoteException {
        }

        @Override // cerence.ark.assistant.service.IPrompterCallback
        public void onPlayStateChanged(PlayState playState) throws RemoteException {
        }

        @Override // cerence.ark.assistant.service.IPrompterCallback
        public void onPromptCreated() throws RemoteException {
        }

        @Override // cerence.ark.assistant.service.IPrompterCallback
        public void onPromptDestroyed() throws RemoteException {
        }
    }

    void onError(PrompterError prompterError) throws RemoteException;

    void onPlayStateChanged(PlayState playState) throws RemoteException;

    void onPromptCreated() throws RemoteException;

    void onPromptDestroyed() throws RemoteException;

    public static abstract class Stub extends Binder implements IPrompterCallback {
        static final int TRANSACTION_onError = 3;
        static final int TRANSACTION_onPlayStateChanged = 2;
        static final int TRANSACTION_onPromptCreated = 1;
        static final int TRANSACTION_onPromptDestroyed = 4;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, IPrompterCallback.DESCRIPTOR);
        }

        public static IPrompterCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(IPrompterCallback.DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IPrompterCallback)) {
                return (IPrompterCallback) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(IPrompterCallback.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(IPrompterCallback.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                onPromptCreated();
                parcel2.writeNoException();
            } else if (i == 2) {
                onPlayStateChanged((PlayState) _Parcel.readTypedObject(parcel, PlayState.CREATOR));
                parcel2.writeNoException();
            } else if (i == 3) {
                onError((PrompterError) _Parcel.readTypedObject(parcel, PrompterError.CREATOR));
                parcel2.writeNoException();
            } else if (i == 4) {
                onPromptDestroyed();
                parcel2.writeNoException();
            } else {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            return true;
        }

        private static class Proxy implements IPrompterCallback {
            private IBinder mRemote;

            public String getInterfaceDescriptor() {
                return IPrompterCallback.DESCRIPTOR;
            }

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // cerence.ark.assistant.service.IPrompterCallback
            public void onPromptCreated() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IPrompterCallback.DESCRIPTOR);
                    this.mRemote.transact(1, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // cerence.ark.assistant.service.IPrompterCallback
            public void onPlayStateChanged(PlayState playState) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IPrompterCallback.DESCRIPTOR);
                    _Parcel.writeTypedObject(parcelObtain, playState, 0);
                    this.mRemote.transact(2, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // cerence.ark.assistant.service.IPrompterCallback
            public void onError(PrompterError prompterError) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IPrompterCallback.DESCRIPTOR);
                    _Parcel.writeTypedObject(parcelObtain, prompterError, 0);
                    this.mRemote.transact(3, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // cerence.ark.assistant.service.IPrompterCallback
            public void onPromptDestroyed() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IPrompterCallback.DESCRIPTOR);
                    this.mRemote.transact(4, parcelObtain, parcelObtain2, 0);
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
