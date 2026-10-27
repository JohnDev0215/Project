package com.byd.zoomcar.ipc;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public interface IZoomAidlInterface extends IInterface {

    public static class Default implements IZoomAidlInterface {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.byd.zoomcar.ipc.IZoomAidlInterface
        public int getZoomMeetingStatus() throws RemoteException {
            return 0;
        }
    }

    int getZoomMeetingStatus() throws RemoteException;

    public static abstract class Stub extends Binder implements IZoomAidlInterface {
        private static final String DESCRIPTOR = "com.byd.zoomcar.ipc.IZoomAidlInterface";
        static final int TRANSACTION_getZoomMeetingStatus = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IZoomAidlInterface asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IZoomAidlInterface)) {
                return (IZoomAidlInterface) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1) {
                if (i == 1598968902) {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface(DESCRIPTOR);
            int zoomMeetingStatus = getZoomMeetingStatus();
            parcel2.writeNoException();
            parcel2.writeInt(zoomMeetingStatus);
            return true;
        }

        private static class Proxy implements IZoomAidlInterface {
            public static IZoomAidlInterface sDefaultImpl;
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

            @Override // com.byd.zoomcar.ipc.IZoomAidlInterface
            public int getZoomMeetingStatus() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getZoomMeetingStatus();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IZoomAidlInterface iZoomAidlInterface) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iZoomAidlInterface == null) {
                return false;
            }
            Proxy.sDefaultImpl = iZoomAidlInterface;
            return true;
        }

        public static IZoomAidlInterface getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
