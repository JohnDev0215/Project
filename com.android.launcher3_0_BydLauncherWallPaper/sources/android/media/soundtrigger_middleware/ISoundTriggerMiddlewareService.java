package android.media.soundtrigger_middleware;

import android.media.soundtrigger_middleware.ISoundTriggerCallback;
import android.media.soundtrigger_middleware.ISoundTriggerModule;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public interface ISoundTriggerMiddlewareService extends IInterface {

    public static class Default implements ISoundTriggerMiddlewareService {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.media.soundtrigger_middleware.ISoundTriggerMiddlewareService
        public ISoundTriggerModule attach(int i, ISoundTriggerCallback iSoundTriggerCallback) throws RemoteException {
            return null;
        }

        @Override // android.media.soundtrigger_middleware.ISoundTriggerMiddlewareService
        public SoundTriggerModuleDescriptor[] listModules() throws RemoteException {
            return null;
        }
    }

    ISoundTriggerModule attach(int i, ISoundTriggerCallback iSoundTriggerCallback) throws RemoteException;

    SoundTriggerModuleDescriptor[] listModules() throws RemoteException;

    public static abstract class Stub extends Binder implements ISoundTriggerMiddlewareService {
        private static final String DESCRIPTOR = "android.media.soundtrigger_middleware.ISoundTriggerMiddlewareService";
        static final int TRANSACTION_attach = 2;
        static final int TRANSACTION_listModules = 1;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "listModules";
            }
            if (i != 2) {
                return null;
            }
            return "attach";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ISoundTriggerMiddlewareService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof ISoundTriggerMiddlewareService)) {
                return (ISoundTriggerMiddlewareService) iInterfaceQueryLocalInterface;
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
                SoundTriggerModuleDescriptor[] soundTriggerModuleDescriptorArrListModules = listModules();
                parcel2.writeNoException();
                parcel2.writeTypedArray(soundTriggerModuleDescriptorArrListModules, 1);
                return true;
            }
            if (i != 2) {
                if (i == 1598968902) {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface(DESCRIPTOR);
            ISoundTriggerModule iSoundTriggerModuleAttach = attach(parcel.readInt(), ISoundTriggerCallback.Stub.asInterface(parcel.readStrongBinder()));
            parcel2.writeNoException();
            parcel2.writeStrongBinder(iSoundTriggerModuleAttach != null ? iSoundTriggerModuleAttach.asBinder() : null);
            return true;
        }

        private static class Proxy implements ISoundTriggerMiddlewareService {
            public static ISoundTriggerMiddlewareService sDefaultImpl;
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

            @Override // android.media.soundtrigger_middleware.ISoundTriggerMiddlewareService
            public SoundTriggerModuleDescriptor[] listModules() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().listModules();
                    }
                    parcelObtain2.readException();
                    return (SoundTriggerModuleDescriptor[]) parcelObtain2.createTypedArray(SoundTriggerModuleDescriptor.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.soundtrigger_middleware.ISoundTriggerMiddlewareService
            public ISoundTriggerModule attach(int i, ISoundTriggerCallback iSoundTriggerCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iSoundTriggerCallback != null ? iSoundTriggerCallback.asBinder() : null);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().attach(i, iSoundTriggerCallback);
                    }
                    parcelObtain2.readException();
                    return ISoundTriggerModule.Stub.asInterface(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ISoundTriggerMiddlewareService iSoundTriggerMiddlewareService) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iSoundTriggerMiddlewareService == null) {
                return false;
            }
            Proxy.sDefaultImpl = iSoundTriggerMiddlewareService;
            return true;
        }

        public static ISoundTriggerMiddlewareService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
