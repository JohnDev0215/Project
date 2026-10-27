package com.android.ims.internal;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.telephony.ims.ImsCallForwardInfo;
import android.telephony.ims.ImsReasonInfo;
import android.telephony.ims.ImsSsData;
import android.telephony.ims.ImsSsInfo;
import com.android.ims.internal.IImsUt;

/* JADX INFO: loaded from: classes4.dex */
public interface IImsUtListener extends IInterface {

    public static class Default implements IImsUtListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.android.ims.internal.IImsUtListener
        public void lineIdentificationSupplementaryServiceResponse(int i, ImsSsInfo imsSsInfo) throws RemoteException {
        }

        @Override // com.android.ims.internal.IImsUtListener
        public void onSupplementaryServiceIndication(ImsSsData imsSsData) throws RemoteException {
        }

        @Override // com.android.ims.internal.IImsUtListener
        public void utConfigurationCallBarringQueried(IImsUt iImsUt, int i, ImsSsInfo[] imsSsInfoArr) throws RemoteException {
        }

        @Override // com.android.ims.internal.IImsUtListener
        public void utConfigurationCallForwardQueried(IImsUt iImsUt, int i, ImsCallForwardInfo[] imsCallForwardInfoArr) throws RemoteException {
        }

        @Override // com.android.ims.internal.IImsUtListener
        public void utConfigurationCallWaitingQueried(IImsUt iImsUt, int i, ImsSsInfo[] imsSsInfoArr) throws RemoteException {
        }

        @Override // com.android.ims.internal.IImsUtListener
        public void utConfigurationQueried(IImsUt iImsUt, int i, Bundle bundle) throws RemoteException {
        }

        @Override // com.android.ims.internal.IImsUtListener
        public void utConfigurationQueryFailed(IImsUt iImsUt, int i, ImsReasonInfo imsReasonInfo) throws RemoteException {
        }

        @Override // com.android.ims.internal.IImsUtListener
        public void utConfigurationUpdateFailed(IImsUt iImsUt, int i, ImsReasonInfo imsReasonInfo) throws RemoteException {
        }

        @Override // com.android.ims.internal.IImsUtListener
        public void utConfigurationUpdated(IImsUt iImsUt, int i) throws RemoteException {
        }
    }

    void lineIdentificationSupplementaryServiceResponse(int i, ImsSsInfo imsSsInfo) throws RemoteException;

    void onSupplementaryServiceIndication(ImsSsData imsSsData) throws RemoteException;

    void utConfigurationCallBarringQueried(IImsUt iImsUt, int i, ImsSsInfo[] imsSsInfoArr) throws RemoteException;

    void utConfigurationCallForwardQueried(IImsUt iImsUt, int i, ImsCallForwardInfo[] imsCallForwardInfoArr) throws RemoteException;

    void utConfigurationCallWaitingQueried(IImsUt iImsUt, int i, ImsSsInfo[] imsSsInfoArr) throws RemoteException;

    void utConfigurationQueried(IImsUt iImsUt, int i, Bundle bundle) throws RemoteException;

    void utConfigurationQueryFailed(IImsUt iImsUt, int i, ImsReasonInfo imsReasonInfo) throws RemoteException;

    void utConfigurationUpdateFailed(IImsUt iImsUt, int i, ImsReasonInfo imsReasonInfo) throws RemoteException;

    void utConfigurationUpdated(IImsUt iImsUt, int i) throws RemoteException;

    public static abstract class Stub extends Binder implements IImsUtListener {
        private static final String DESCRIPTOR = "com.android.ims.internal.IImsUtListener";
        static final int TRANSACTION_lineIdentificationSupplementaryServiceResponse = 5;
        static final int TRANSACTION_onSupplementaryServiceIndication = 9;
        static final int TRANSACTION_utConfigurationCallBarringQueried = 6;
        static final int TRANSACTION_utConfigurationCallForwardQueried = 7;
        static final int TRANSACTION_utConfigurationCallWaitingQueried = 8;
        static final int TRANSACTION_utConfigurationQueried = 3;
        static final int TRANSACTION_utConfigurationQueryFailed = 4;
        static final int TRANSACTION_utConfigurationUpdateFailed = 2;
        static final int TRANSACTION_utConfigurationUpdated = 1;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "utConfigurationUpdated";
                case 2:
                    return "utConfigurationUpdateFailed";
                case 3:
                    return "utConfigurationQueried";
                case 4:
                    return "utConfigurationQueryFailed";
                case 5:
                    return "lineIdentificationSupplementaryServiceResponse";
                case 6:
                    return "utConfigurationCallBarringQueried";
                case 7:
                    return "utConfigurationCallForwardQueried";
                case 8:
                    return "utConfigurationCallWaitingQueried";
                case 9:
                    return "onSupplementaryServiceIndication";
                default:
                    return null;
            }
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IImsUtListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IImsUtListener)) {
                return (IImsUtListener) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public String getTransactionName(int i) {
            return getDefaultTransactionName(i);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface(DESCRIPTOR);
                    utConfigurationUpdated(IImsUt.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    utConfigurationUpdateFailed(IImsUt.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt() != 0 ? ImsReasonInfo.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    utConfigurationQueried(IImsUt.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt() != 0 ? Bundle.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    utConfigurationQueryFailed(IImsUt.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt() != 0 ? ImsReasonInfo.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    lineIdentificationSupplementaryServiceResponse(parcel.readInt(), parcel.readInt() != 0 ? ImsSsInfo.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    utConfigurationCallBarringQueried(IImsUt.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), (ImsSsInfo[]) parcel.createTypedArray(ImsSsInfo.CREATOR));
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    utConfigurationCallForwardQueried(IImsUt.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), (ImsCallForwardInfo[]) parcel.createTypedArray(ImsCallForwardInfo.CREATOR));
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    utConfigurationCallWaitingQueried(IImsUt.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), (ImsSsInfo[]) parcel.createTypedArray(ImsSsInfo.CREATOR));
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    onSupplementaryServiceIndication(parcel.readInt() != 0 ? ImsSsData.CREATOR.createFromParcel(parcel) : null);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IImsUtListener {
            public static IImsUtListener sDefaultImpl;
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

            @Override // com.android.ims.internal.IImsUtListener
            public void utConfigurationUpdated(IImsUt iImsUt, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iImsUt != null ? iImsUt.asBinder() : null);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().utConfigurationUpdated(iImsUt, i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.ims.internal.IImsUtListener
            public void utConfigurationUpdateFailed(IImsUt iImsUt, int i, ImsReasonInfo imsReasonInfo) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iImsUt != null ? iImsUt.asBinder() : null);
                    parcelObtain.writeInt(i);
                    if (imsReasonInfo != null) {
                        parcelObtain.writeInt(1);
                        imsReasonInfo.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().utConfigurationUpdateFailed(iImsUt, i, imsReasonInfo);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.ims.internal.IImsUtListener
            public void utConfigurationQueried(IImsUt iImsUt, int i, Bundle bundle) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iImsUt != null ? iImsUt.asBinder() : null);
                    parcelObtain.writeInt(i);
                    if (bundle != null) {
                        parcelObtain.writeInt(1);
                        bundle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().utConfigurationQueried(iImsUt, i, bundle);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.ims.internal.IImsUtListener
            public void utConfigurationQueryFailed(IImsUt iImsUt, int i, ImsReasonInfo imsReasonInfo) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iImsUt != null ? iImsUt.asBinder() : null);
                    parcelObtain.writeInt(i);
                    if (imsReasonInfo != null) {
                        parcelObtain.writeInt(1);
                        imsReasonInfo.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(4, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().utConfigurationQueryFailed(iImsUt, i, imsReasonInfo);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.ims.internal.IImsUtListener
            public void lineIdentificationSupplementaryServiceResponse(int i, ImsSsInfo imsSsInfo) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (imsSsInfo != null) {
                        parcelObtain.writeInt(1);
                        imsSsInfo.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(5, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().lineIdentificationSupplementaryServiceResponse(i, imsSsInfo);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.ims.internal.IImsUtListener
            public void utConfigurationCallBarringQueried(IImsUt iImsUt, int i, ImsSsInfo[] imsSsInfoArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iImsUt != null ? iImsUt.asBinder() : null);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeTypedArray(imsSsInfoArr, 0);
                    if (this.mRemote.transact(6, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().utConfigurationCallBarringQueried(iImsUt, i, imsSsInfoArr);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.ims.internal.IImsUtListener
            public void utConfigurationCallForwardQueried(IImsUt iImsUt, int i, ImsCallForwardInfo[] imsCallForwardInfoArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iImsUt != null ? iImsUt.asBinder() : null);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeTypedArray(imsCallForwardInfoArr, 0);
                    if (this.mRemote.transact(7, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().utConfigurationCallForwardQueried(iImsUt, i, imsCallForwardInfoArr);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.ims.internal.IImsUtListener
            public void utConfigurationCallWaitingQueried(IImsUt iImsUt, int i, ImsSsInfo[] imsSsInfoArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iImsUt != null ? iImsUt.asBinder() : null);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeTypedArray(imsSsInfoArr, 0);
                    if (this.mRemote.transact(8, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().utConfigurationCallWaitingQueried(iImsUt, i, imsSsInfoArr);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.ims.internal.IImsUtListener
            public void onSupplementaryServiceIndication(ImsSsData imsSsData) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (imsSsData != null) {
                        parcelObtain.writeInt(1);
                        imsSsData.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(9, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onSupplementaryServiceIndication(imsSsData);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IImsUtListener iImsUtListener) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iImsUtListener == null) {
                return false;
            }
            Proxy.sDefaultImpl = iImsUtListener;
            return true;
        }

        public static IImsUtListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
