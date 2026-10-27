package android.telephony;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteCallback;
import android.os.RemoteException;
import android.telephony.cdma.CdmaSmsCbProgramData;
import android.text.TextUtils;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public interface ICellBroadcastService extends IInterface {

    public static class Default implements ICellBroadcastService {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.telephony.ICellBroadcastService
        public CharSequence getCellBroadcastAreaInfo(int i) throws RemoteException {
            return null;
        }

        @Override // android.telephony.ICellBroadcastService
        public void handleCdmaCellBroadcastSms(int i, byte[] bArr, int i2) throws RemoteException {
        }

        @Override // android.telephony.ICellBroadcastService
        public void handleCdmaScpMessage(int i, List<CdmaSmsCbProgramData> list, String str, RemoteCallback remoteCallback) throws RemoteException {
        }

        @Override // android.telephony.ICellBroadcastService
        public void handleGsmCellBroadcastSms(int i, byte[] bArr) throws RemoteException {
        }
    }

    CharSequence getCellBroadcastAreaInfo(int i) throws RemoteException;

    void handleCdmaCellBroadcastSms(int i, byte[] bArr, int i2) throws RemoteException;

    void handleCdmaScpMessage(int i, List<CdmaSmsCbProgramData> list, String str, RemoteCallback remoteCallback) throws RemoteException;

    void handleGsmCellBroadcastSms(int i, byte[] bArr) throws RemoteException;

    public static abstract class Stub extends Binder implements ICellBroadcastService {
        private static final String DESCRIPTOR = "android.telephony.ICellBroadcastService";
        static final int TRANSACTION_getCellBroadcastAreaInfo = 4;
        static final int TRANSACTION_handleCdmaCellBroadcastSms = 2;
        static final int TRANSACTION_handleCdmaScpMessage = 3;
        static final int TRANSACTION_handleGsmCellBroadcastSms = 1;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "handleGsmCellBroadcastSms";
            }
            if (i == 2) {
                return "handleCdmaCellBroadcastSms";
            }
            if (i == 3) {
                return "handleCdmaScpMessage";
            }
            if (i != 4) {
                return null;
            }
            return "getCellBroadcastAreaInfo";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ICellBroadcastService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof ICellBroadcastService)) {
                return (ICellBroadcastService) iInterfaceQueryLocalInterface;
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
                handleGsmCellBroadcastSms(parcel.readInt(), parcel.createByteArray());
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                handleCdmaCellBroadcastSms(parcel.readInt(), parcel.createByteArray(), parcel.readInt());
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                handleCdmaScpMessage(parcel.readInt(), parcel.createTypedArrayList(CdmaSmsCbProgramData.CREATOR), parcel.readString(), parcel.readInt() != 0 ? RemoteCallback.CREATOR.createFromParcel(parcel) : null);
                return true;
            }
            if (i != 4) {
                if (i == 1598968902) {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface(DESCRIPTOR);
            CharSequence cellBroadcastAreaInfo = getCellBroadcastAreaInfo(parcel.readInt());
            parcel2.writeNoException();
            if (cellBroadcastAreaInfo != null) {
                parcel2.writeInt(1);
                TextUtils.writeToParcel(cellBroadcastAreaInfo, parcel2, 1);
            } else {
                parcel2.writeInt(0);
            }
            return true;
        }

        private static class Proxy implements ICellBroadcastService {
            public static ICellBroadcastService sDefaultImpl;
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

            @Override // android.telephony.ICellBroadcastService
            public void handleGsmCellBroadcastSms(int i, byte[] bArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeByteArray(bArr);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().handleGsmCellBroadcastSms(i, bArr);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.telephony.ICellBroadcastService
            public void handleCdmaCellBroadcastSms(int i, byte[] bArr, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeByteArray(bArr);
                    parcelObtain.writeInt(i2);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().handleCdmaCellBroadcastSms(i, bArr, i2);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.telephony.ICellBroadcastService
            public void handleCdmaScpMessage(int i, List<CdmaSmsCbProgramData> list, String str, RemoteCallback remoteCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeTypedList(list);
                    parcelObtain.writeString(str);
                    if (remoteCallback != null) {
                        parcelObtain.writeInt(1);
                        remoteCallback.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().handleCdmaScpMessage(i, list, str, remoteCallback);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.telephony.ICellBroadcastService
            public CharSequence getCellBroadcastAreaInfo(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCellBroadcastAreaInfo(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ICellBroadcastService iCellBroadcastService) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iCellBroadcastService == null) {
                return false;
            }
            Proxy.sDefaultImpl = iCellBroadcastService;
            return true;
        }

        public static ICellBroadcastService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
