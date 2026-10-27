package com.byd.car.appfocus;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.byd.car.appfocus.IAppFocusCallback;

/* JADX INFO: loaded from: classes2.dex */
public interface IAppFocusService extends IInterface {

    public static class Default implements IAppFocusService {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.byd.car.appfocus.IAppFocusService
        public int getCurrentResourceFocusAppCode(int i) {
            return 0;
        }

        @Override // com.byd.car.appfocus.IAppFocusService
        public String getCurrentResourceFocusPackageName(int i) {
            return null;
        }

        @Override // com.byd.car.appfocus.IAppFocusService
        public void registerResourceFocusChanged(int i, int i2, String str, IAppFocusCallback iAppFocusCallback) {
        }

        @Override // com.byd.car.appfocus.IAppFocusService
        public int releaseResourceFocus(int i, int i2, String str) {
            return 0;
        }

        @Override // com.byd.car.appfocus.IAppFocusService
        public int requestResourceFocus(int i, int i2, String str) {
            return 0;
        }

        @Override // com.byd.car.appfocus.IAppFocusService
        public void unregisterResourceFocusChanged(int i, int i2, String str) {
        }
    }

    public static abstract class Stub extends Binder implements IAppFocusService {
        private static final String DESCRIPTOR = "com.byd.car.appfocus.IAppFocusService";
        public static final int TRANSACTION_getCurrentResourceFocusAppCode = 5;
        public static final int TRANSACTION_getCurrentResourceFocusPackageName = 6;
        public static final int TRANSACTION_registerResourceFocusChanged = 1;
        public static final int TRANSACTION_releaseResourceFocus = 3;
        public static final int TRANSACTION_requestResourceFocus = 2;
        public static final int TRANSACTION_unregisterResourceFocusChanged = 4;

        public static class Proxy implements IAppFocusService {
            public static IAppFocusService sDefaultImpl;
            private IBinder mRemote;

            public Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.byd.car.appfocus.IAppFocusService
            public int getCurrentResourceFocusAppCode(int i) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCurrentResourceFocusAppCode(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.car.appfocus.IAppFocusService
            public String getCurrentResourceFocusPackageName(int i) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCurrentResourceFocusPackageName(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            @Override // com.byd.car.appfocus.IAppFocusService
            public void registerResourceFocusChanged(int i, int i2, String str, IAppFocusCallback iAppFocusCallback) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeString(str);
                    parcelObtain.writeStrongBinder(iAppFocusCallback != null ? iAppFocusCallback.asBinder() : null);
                    if (this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                        parcelObtain2.readException();
                    } else {
                        Stub.getDefaultImpl().registerResourceFocusChanged(i, i2, str, iAppFocusCallback);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.car.appfocus.IAppFocusService
            public int releaseResourceFocus(int i, int i2, String str) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().releaseResourceFocus(i, i2, str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.car.appfocus.IAppFocusService
            public int requestResourceFocus(int i, int i2, String str) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().requestResourceFocus(i, i2, str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.car.appfocus.IAppFocusService
            public void unregisterResourceFocusChanged(int i, int i2, String str) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeString(str);
                    if (this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                        parcelObtain2.readException();
                    } else {
                        Stub.getDefaultImpl().unregisterResourceFocusChanged(i, i2, str);
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

        public static IAppFocusService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof IAppFocusService)) ? new Proxy(iBinder) : (IAppFocusService) iInterfaceQueryLocalInterface;
        }

        public static IAppFocusService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(IAppFocusService iAppFocusService) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iAppFocusService == null) {
                return false;
            }
            Proxy.sDefaultImpl = iAppFocusService;
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
                    registerResourceFocusChanged(parcel.readInt(), parcel.readInt(), parcel.readString(), IAppFocusCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iRequestResourceFocus = requestResourceFocus(parcel.readInt(), parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(iRequestResourceFocus);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iReleaseResourceFocus = releaseResourceFocus(parcel.readInt(), parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(iReleaseResourceFocus);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    unregisterResourceFocusChanged(parcel.readInt(), parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    int currentResourceFocusAppCode = getCurrentResourceFocusAppCode(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(currentResourceFocusAppCode);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    String currentResourceFocusPackageName = getCurrentResourceFocusPackageName(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeString(currentResourceFocusPackageName);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    int getCurrentResourceFocusAppCode(int i);

    String getCurrentResourceFocusPackageName(int i);

    void registerResourceFocusChanged(int i, int i2, String str, IAppFocusCallback iAppFocusCallback);

    int releaseResourceFocus(int i, int i2, String str);

    int requestResourceFocus(int i, int i2, String str);

    void unregisterResourceFocusChanged(int i, int i2, String str);
}
