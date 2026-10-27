package android.car.media;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* JADX INFO: loaded from: classes.dex */
public interface ICarAudioControl extends IInterface {

    public static class Default implements ICarAudioControl {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.car.media.ICarAudioControl
        public int getCount() {
            return 0;
        }

        @Override // android.car.media.ICarAudioControl
        public int getFlags(int i) {
            return 0;
        }

        @Override // android.car.media.ICarAudioControl
        public float getFloatValue(int i) {
            return 0.0f;
        }

        @Override // android.car.media.ICarAudioControl
        public float[] getFloatValueRange(int i) {
            return null;
        }

        @Override // android.car.media.ICarAudioControl
        public int getIntegerValue(int i) {
            return 0;
        }

        @Override // android.car.media.ICarAudioControl
        public int[] getIntegerValueRange(int i) {
            return null;
        }

        @Override // android.car.media.ICarAudioControl
        public String getName(int i) {
            return null;
        }

        @Override // android.car.media.ICarAudioControl
        public int getType(int i) {
            return 0;
        }

        @Override // android.car.media.ICarAudioControl
        public void registerAudioControlCallback(IBinder iBinder) {
        }

        @Override // android.car.media.ICarAudioControl
        public void setFloatValue(int i, float f) {
        }

        @Override // android.car.media.ICarAudioControl
        public void setIntegerValue(int i, int i2) {
        }

        @Override // android.car.media.ICarAudioControl
        public void unregisterAudioControlCallback(IBinder iBinder) {
        }

        @Override // android.car.media.ICarAudioControl
        public void updateAudioControlCallbackFilter(IBinder iBinder, CarAudioControlFilter carAudioControlFilter) {
        }
    }

    public static abstract class Stub extends Binder implements ICarAudioControl {
        private static final String DESCRIPTOR = "android.car.media.ICarAudioControl";
        static final int TRANSACTION_getCount = 1;
        static final int TRANSACTION_getFlags = 4;
        static final int TRANSACTION_getFloatValue = 8;
        static final int TRANSACTION_getFloatValueRange = 10;
        static final int TRANSACTION_getIntegerValue = 7;
        static final int TRANSACTION_getIntegerValueRange = 9;
        static final int TRANSACTION_getName = 2;
        static final int TRANSACTION_getType = 3;
        static final int TRANSACTION_registerAudioControlCallback = 11;
        static final int TRANSACTION_setFloatValue = 6;
        static final int TRANSACTION_setIntegerValue = 5;
        static final int TRANSACTION_unregisterAudioControlCallback = 12;
        static final int TRANSACTION_updateAudioControlCallbackFilter = 13;

        private static class Proxy implements ICarAudioControl {
            public static ICarAudioControl sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // android.car.media.ICarAudioControl
            public int getCount() {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCount();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.car.media.ICarAudioControl
            public int getFlags(int i) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getFlags(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.car.media.ICarAudioControl
            public float getFloatValue(int i) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getFloatValue(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readFloat();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.car.media.ICarAudioControl
            public float[] getFloatValueRange(int i) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(10, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getFloatValueRange(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createFloatArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.car.media.ICarAudioControl
            public int getIntegerValue(int i) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getIntegerValue(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.car.media.ICarAudioControl
            public int[] getIntegerValueRange(int i) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getIntegerValueRange(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createIntArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            @Override // android.car.media.ICarAudioControl
            public String getName(int i) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getName(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.car.media.ICarAudioControl
            public int getType(int i) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getType(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.car.media.ICarAudioControl
            public void registerAudioControlCallback(IBinder iBinder) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(11, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                        parcelObtain2.readException();
                    } else {
                        Stub.getDefaultImpl().registerAudioControlCallback(iBinder);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.car.media.ICarAudioControl
            public void setFloatValue(int i, float f) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeFloat(f);
                    if (this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                        parcelObtain2.readException();
                    } else {
                        Stub.getDefaultImpl().setFloatValue(i, f);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.car.media.ICarAudioControl
            public void setIntegerValue(int i, int i2) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                        parcelObtain2.readException();
                    } else {
                        Stub.getDefaultImpl().setIntegerValue(i, i2);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.car.media.ICarAudioControl
            public void unregisterAudioControlCallback(IBinder iBinder) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(12, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                        parcelObtain2.readException();
                    } else {
                        Stub.getDefaultImpl().unregisterAudioControlCallback(iBinder);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.car.media.ICarAudioControl
            public void updateAudioControlCallbackFilter(IBinder iBinder, CarAudioControlFilter carAudioControlFilter) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBinder);
                    if (carAudioControlFilter != null) {
                        parcelObtain.writeInt(1);
                        carAudioControlFilter.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(13, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                        parcelObtain2.readException();
                    } else {
                        Stub.getDefaultImpl().updateAudioControlCallbackFilter(iBinder, carAudioControlFilter);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ICarAudioControl asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof ICarAudioControl)) ? new Proxy(iBinder) : (ICarAudioControl) iInterfaceQueryLocalInterface;
        }

        public static ICarAudioControl getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(ICarAudioControl iCarAudioControl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iCarAudioControl == null) {
                return false;
            }
            Proxy.sDefaultImpl = iCarAudioControl;
            return true;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface(DESCRIPTOR);
                    int count = getCount();
                    parcel2.writeNoException();
                    parcel2.writeInt(count);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    String name = getName(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeString(name);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    int type = getType(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(type);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    int flags = getFlags(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(flags);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    setIntegerValue(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    setFloatValue(parcel.readInt(), parcel.readFloat());
                    parcel2.writeNoException();
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    int integerValue = getIntegerValue(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(integerValue);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    float floatValue = getFloatValue(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeFloat(floatValue);
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    int[] integerValueRange = getIntegerValueRange(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeIntArray(integerValueRange);
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    float[] floatValueRange = getFloatValueRange(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeFloatArray(floatValueRange);
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    registerAudioControlCallback(parcel.readStrongBinder());
                    parcel2.writeNoException();
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    unregisterAudioControlCallback(parcel.readStrongBinder());
                    parcel2.writeNoException();
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    updateAudioControlCallbackFilter(parcel.readStrongBinder(), parcel.readInt() != 0 ? CarAudioControlFilter.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    int getCount();

    int getFlags(int i);

    float getFloatValue(int i);

    float[] getFloatValueRange(int i);

    int getIntegerValue(int i);

    int[] getIntegerValueRange(int i);

    String getName(int i);

    int getType(int i);

    void registerAudioControlCallback(IBinder iBinder);

    void setFloatValue(int i, float f);

    void setIntegerValue(int i, int i2);

    void unregisterAudioControlCallback(IBinder iBinder);

    void updateAudioControlCallbackFilter(IBinder iBinder, CarAudioControlFilter carAudioControlFilter);
}
