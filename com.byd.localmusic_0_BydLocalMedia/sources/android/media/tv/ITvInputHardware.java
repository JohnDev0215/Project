package android.media.tv;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.Surface;

/* JADX INFO: loaded from: classes2.dex */
public interface ITvInputHardware extends IInterface {

    public static class Default implements ITvInputHardware {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.media.tv.ITvInputHardware
        public void overrideAudioSink(int i, String str, int i2, int i3, int i4) throws RemoteException {
        }

        @Override // android.media.tv.ITvInputHardware
        public void setStreamVolume(float f) throws RemoteException {
        }

        @Override // android.media.tv.ITvInputHardware
        public boolean setSurface(Surface surface, TvStreamConfig tvStreamConfig) throws RemoteException {
            return false;
        }
    }

    void overrideAudioSink(int i, String str, int i2, int i3, int i4) throws RemoteException;

    void setStreamVolume(float f) throws RemoteException;

    boolean setSurface(Surface surface, TvStreamConfig tvStreamConfig) throws RemoteException;

    public static abstract class Stub extends Binder implements ITvInputHardware {
        private static final String DESCRIPTOR = "android.media.tv.ITvInputHardware";
        static final int TRANSACTION_overrideAudioSink = 3;
        static final int TRANSACTION_setStreamVolume = 2;
        static final int TRANSACTION_setSurface = 1;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "setSurface";
            }
            if (i == 2) {
                return "setStreamVolume";
            }
            if (i != 3) {
                return null;
            }
            return "overrideAudioSink";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ITvInputHardware asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof ITvInputHardware)) {
                return (ITvInputHardware) iInterfaceQueryLocalInterface;
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
                boolean surface = setSurface(parcel.readInt() != 0 ? Surface.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? TvStreamConfig.CREATOR.createFromParcel(parcel) : null);
                parcel2.writeNoException();
                parcel2.writeInt(surface ? 1 : 0);
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                setStreamVolume(parcel.readFloat());
                parcel2.writeNoException();
                return true;
            }
            if (i != 3) {
                if (i == 1598968902) {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface(DESCRIPTOR);
            overrideAudioSink(parcel.readInt(), parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt());
            parcel2.writeNoException();
            return true;
        }

        private static class Proxy implements ITvInputHardware {
            public static ITvInputHardware sDefaultImpl;
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

            @Override // android.media.tv.ITvInputHardware
            public boolean setSurface(Surface surface, TvStreamConfig tvStreamConfig) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (surface != null) {
                        parcelObtain.writeInt(1);
                        surface.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (tvStreamConfig != null) {
                        parcelObtain.writeInt(1);
                        tvStreamConfig.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setSurface(surface, tvStreamConfig);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.tv.ITvInputHardware
            public void setStreamVolume(float f) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeFloat(f);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setStreamVolume(f);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.tv.ITvInputHardware
            public void overrideAudioSink(int i, String str, int i2, int i3, int i4) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    parcelObtain.writeInt(i4);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().overrideAudioSink(i, str, i2, i3, i4);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ITvInputHardware iTvInputHardware) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iTvInputHardware == null) {
                return false;
            }
            Proxy.sDefaultImpl = iTvInputHardware;
            return true;
        }

        public static ITvInputHardware getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
