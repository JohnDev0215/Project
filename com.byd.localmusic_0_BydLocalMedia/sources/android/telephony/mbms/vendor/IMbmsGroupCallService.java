package android.telephony.mbms.vendor;

import android.content.ContentResolver;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.telephony.mbms.IGroupCallCallback;
import android.telephony.mbms.IMbmsGroupCallSessionCallback;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public interface IMbmsGroupCallService extends IInterface {

    public static class Default implements IMbmsGroupCallService {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.telephony.mbms.vendor.IMbmsGroupCallService
        public void dispose(int i) throws RemoteException {
        }

        @Override // android.telephony.mbms.vendor.IMbmsGroupCallService
        public int initialize(IMbmsGroupCallSessionCallback iMbmsGroupCallSessionCallback, int i) throws RemoteException {
            return 0;
        }

        @Override // android.telephony.mbms.vendor.IMbmsGroupCallService
        public int startGroupCall(int i, long j, List list, List list2, IGroupCallCallback iGroupCallCallback) throws RemoteException {
            return 0;
        }

        @Override // android.telephony.mbms.vendor.IMbmsGroupCallService
        public void stopGroupCall(int i, long j) throws RemoteException {
        }

        @Override // android.telephony.mbms.vendor.IMbmsGroupCallService
        public void updateGroupCall(int i, long j, List list, List list2) throws RemoteException {
        }
    }

    void dispose(int i) throws RemoteException;

    int initialize(IMbmsGroupCallSessionCallback iMbmsGroupCallSessionCallback, int i) throws RemoteException;

    int startGroupCall(int i, long j, List list, List list2, IGroupCallCallback iGroupCallCallback) throws RemoteException;

    void stopGroupCall(int i, long j) throws RemoteException;

    void updateGroupCall(int i, long j, List list, List list2) throws RemoteException;

    public static abstract class Stub extends Binder implements IMbmsGroupCallService {
        private static final String DESCRIPTOR = "android.telephony.mbms.vendor.IMbmsGroupCallService";
        static final int TRANSACTION_dispose = 5;
        static final int TRANSACTION_initialize = 1;
        static final int TRANSACTION_startGroupCall = 4;
        static final int TRANSACTION_stopGroupCall = 2;
        static final int TRANSACTION_updateGroupCall = 3;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return ContentResolver.SYNC_EXTRAS_INITIALIZE;
            }
            if (i == 2) {
                return "stopGroupCall";
            }
            if (i == 3) {
                return "updateGroupCall";
            }
            if (i == 4) {
                return "startGroupCall";
            }
            if (i != 5) {
                return null;
            }
            return "dispose";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IMbmsGroupCallService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IMbmsGroupCallService)) {
                return (IMbmsGroupCallService) iInterfaceQueryLocalInterface;
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
                int iInitialize = initialize(IMbmsGroupCallSessionCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                parcel2.writeNoException();
                parcel2.writeInt(iInitialize);
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                stopGroupCall(parcel.readInt(), parcel.readLong());
                parcel2.writeNoException();
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                int i3 = parcel.readInt();
                long j = parcel.readLong();
                ClassLoader classLoader = getClass().getClassLoader();
                updateGroupCall(i3, j, parcel.readArrayList(classLoader), parcel.readArrayList(classLoader));
                parcel2.writeNoException();
                return true;
            }
            if (i == 4) {
                parcel.enforceInterface(DESCRIPTOR);
                int i4 = parcel.readInt();
                long j2 = parcel.readLong();
                ClassLoader classLoader2 = getClass().getClassLoader();
                int iStartGroupCall = startGroupCall(i4, j2, parcel.readArrayList(classLoader2), parcel.readArrayList(classLoader2), IGroupCallCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                parcel2.writeInt(iStartGroupCall);
                return true;
            }
            if (i != 5) {
                if (i == 1598968902) {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface(DESCRIPTOR);
            dispose(parcel.readInt());
            parcel2.writeNoException();
            return true;
        }

        private static class Proxy implements IMbmsGroupCallService {
            public static IMbmsGroupCallService sDefaultImpl;
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

            @Override // android.telephony.mbms.vendor.IMbmsGroupCallService
            public int initialize(IMbmsGroupCallSessionCallback iMbmsGroupCallSessionCallback, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iMbmsGroupCallSessionCallback != null ? iMbmsGroupCallSessionCallback.asBinder() : null);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().initialize(iMbmsGroupCallSessionCallback, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.telephony.mbms.vendor.IMbmsGroupCallService
            public void stopGroupCall(int i, long j) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeLong(j);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().stopGroupCall(i, j);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.telephony.mbms.vendor.IMbmsGroupCallService
            public void updateGroupCall(int i, long j, List list, List list2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeLong(j);
                    parcelObtain.writeList(list);
                    parcelObtain.writeList(list2);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().updateGroupCall(i, j, list, list2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.telephony.mbms.vendor.IMbmsGroupCallService
            public int startGroupCall(int i, long j, List list, List list2, IGroupCallCallback iGroupCallCallback) throws Throwable {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeLong(j);
                    parcelObtain.writeList(list);
                    parcelObtain.writeList(list2);
                    parcelObtain.writeStrongBinder(iGroupCallCallback != null ? iGroupCallCallback.asBinder() : null);
                    try {
                        if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                            int iStartGroupCall = Stub.getDefaultImpl().startGroupCall(i, j, list, list2, iGroupCallCallback);
                            parcelObtain2.recycle();
                            parcelObtain.recycle();
                            return iStartGroupCall;
                        }
                        parcelObtain2.readException();
                        int i2 = parcelObtain2.readInt();
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                        return i2;
                    } catch (Throwable th) {
                        th = th;
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            }

            @Override // android.telephony.mbms.vendor.IMbmsGroupCallService
            public void dispose(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().dispose(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IMbmsGroupCallService iMbmsGroupCallService) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iMbmsGroupCallService == null) {
                return false;
            }
            Proxy.sDefaultImpl = iMbmsGroupCallService;
            return true;
        }

        public static IMbmsGroupCallService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
