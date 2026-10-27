package com.neusoft.na.navigation.widget;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.Surface;
import com.neusoft.na.navigation.widget.IWidgetCallBack;

/* JADX INFO: loaded from: classes2.dex */
public interface IWidgetAidlInterface extends IInterface {

    public static class Default implements IWidgetAidlInterface {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.neusoft.na.navigation.widget.IWidgetAidlInterface
        public void setWidgetCallBack(IWidgetCallBack iWidgetCallBack) throws RemoteException {
        }

        @Override // com.neusoft.na.navigation.widget.IWidgetAidlInterface
        public void setWidgetSuface(Surface surface) throws RemoteException {
        }
    }

    void setWidgetCallBack(IWidgetCallBack iWidgetCallBack) throws RemoteException;

    void setWidgetSuface(Surface surface) throws RemoteException;

    public static abstract class Stub extends Binder implements IWidgetAidlInterface {
        private static final String DESCRIPTOR = "com.neusoft.na.navigation.widget.IWidgetAidlInterface";
        static final int TRANSACTION_setWidgetCallBack = 2;
        static final int TRANSACTION_setWidgetSuface = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IWidgetAidlInterface asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IWidgetAidlInterface)) {
                return (IWidgetAidlInterface) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1) {
                if (i != 2) {
                    if (i == 1598968902) {
                        parcel2.writeString(DESCRIPTOR);
                        return true;
                    }
                    return super.onTransact(i, parcel, parcel2, i2);
                }
                parcel.enforceInterface(DESCRIPTOR);
                setWidgetCallBack(IWidgetCallBack.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            parcel.enforceInterface(DESCRIPTOR);
            Surface surface = parcel.readInt() != 0 ? (Surface) Surface.CREATOR.createFromParcel(parcel) : null;
            setWidgetSuface(surface);
            parcel2.writeNoException();
            if (surface != null) {
                parcel2.writeInt(1);
                surface.writeToParcel(parcel2, 1);
            } else {
                parcel2.writeInt(0);
            }
            return true;
        }

        private static class Proxy implements IWidgetAidlInterface {
            public static IWidgetAidlInterface sDefaultImpl;
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

            @Override // com.neusoft.na.navigation.widget.IWidgetAidlInterface
            public void setWidgetSuface(Surface surface) throws RemoteException {
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
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setWidgetSuface(surface);
                        return;
                    }
                    parcelObtain2.readException();
                    if (parcelObtain2.readInt() != 0) {
                        surface.readFromParcel(parcelObtain2);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.navigation.widget.IWidgetAidlInterface
            public void setWidgetCallBack(IWidgetCallBack iWidgetCallBack) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iWidgetCallBack != null ? iWidgetCallBack.asBinder() : null);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setWidgetCallBack(iWidgetCallBack);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IWidgetAidlInterface iWidgetAidlInterface) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iWidgetAidlInterface == null) {
                return false;
            }
            Proxy.sDefaultImpl = iWidgetAidlInterface;
            return true;
        }

        public static IWidgetAidlInterface getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
