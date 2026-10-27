package android.media.tv.tunerresourcemanager;

import android.media.tv.tunerresourcemanager.IResourcesReclaimListener;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public interface ITunerResourceManager extends IInterface {

    public static class Default implements ITunerResourceManager {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.media.tv.tunerresourcemanager.ITunerResourceManager
        public boolean isHigherPriority(ResourceClientProfile resourceClientProfile, ResourceClientProfile resourceClientProfile2) throws RemoteException {
            return false;
        }

        @Override // android.media.tv.tunerresourcemanager.ITunerResourceManager
        public void registerClientProfile(ResourceClientProfile resourceClientProfile, IResourcesReclaimListener iResourcesReclaimListener, int[] iArr) throws RemoteException {
        }

        @Override // android.media.tv.tunerresourcemanager.ITunerResourceManager
        public void releaseCasSession(int i, int i2) throws RemoteException {
        }

        @Override // android.media.tv.tunerresourcemanager.ITunerResourceManager
        public void releaseDemux(int i, int i2) throws RemoteException {
        }

        @Override // android.media.tv.tunerresourcemanager.ITunerResourceManager
        public void releaseDescrambler(int i, int i2) throws RemoteException {
        }

        @Override // android.media.tv.tunerresourcemanager.ITunerResourceManager
        public void releaseFrontend(int i, int i2) throws RemoteException {
        }

        @Override // android.media.tv.tunerresourcemanager.ITunerResourceManager
        public void releaseLnb(int i, int i2) throws RemoteException {
        }

        @Override // android.media.tv.tunerresourcemanager.ITunerResourceManager
        public boolean requestCasSession(CasSessionRequest casSessionRequest, int[] iArr) throws RemoteException {
            return false;
        }

        @Override // android.media.tv.tunerresourcemanager.ITunerResourceManager
        public boolean requestDemux(TunerDemuxRequest tunerDemuxRequest, int[] iArr) throws RemoteException {
            return false;
        }

        @Override // android.media.tv.tunerresourcemanager.ITunerResourceManager
        public boolean requestDescrambler(TunerDescramblerRequest tunerDescramblerRequest, int[] iArr) throws RemoteException {
            return false;
        }

        @Override // android.media.tv.tunerresourcemanager.ITunerResourceManager
        public boolean requestFrontend(TunerFrontendRequest tunerFrontendRequest, int[] iArr) throws RemoteException {
            return false;
        }

        @Override // android.media.tv.tunerresourcemanager.ITunerResourceManager
        public boolean requestLnb(TunerLnbRequest tunerLnbRequest, int[] iArr) throws RemoteException {
            return false;
        }

        @Override // android.media.tv.tunerresourcemanager.ITunerResourceManager
        public void setFrontendInfoList(TunerFrontendInfo[] tunerFrontendInfoArr) throws RemoteException {
        }

        @Override // android.media.tv.tunerresourcemanager.ITunerResourceManager
        public void setLnbInfoList(int[] iArr) throws RemoteException {
        }

        @Override // android.media.tv.tunerresourcemanager.ITunerResourceManager
        public void shareFrontend(int i, int i2) throws RemoteException {
        }

        @Override // android.media.tv.tunerresourcemanager.ITunerResourceManager
        public void unregisterClientProfile(int i) throws RemoteException {
        }

        @Override // android.media.tv.tunerresourcemanager.ITunerResourceManager
        public void updateCasInfo(int i, int i2) throws RemoteException {
        }

        @Override // android.media.tv.tunerresourcemanager.ITunerResourceManager
        public boolean updateClientPriority(int i, int i2, int i3) throws RemoteException {
            return false;
        }
    }

    boolean isHigherPriority(ResourceClientProfile resourceClientProfile, ResourceClientProfile resourceClientProfile2) throws RemoteException;

    void registerClientProfile(ResourceClientProfile resourceClientProfile, IResourcesReclaimListener iResourcesReclaimListener, int[] iArr) throws RemoteException;

    void releaseCasSession(int i, int i2) throws RemoteException;

    void releaseDemux(int i, int i2) throws RemoteException;

    void releaseDescrambler(int i, int i2) throws RemoteException;

    void releaseFrontend(int i, int i2) throws RemoteException;

    void releaseLnb(int i, int i2) throws RemoteException;

    boolean requestCasSession(CasSessionRequest casSessionRequest, int[] iArr) throws RemoteException;

    boolean requestDemux(TunerDemuxRequest tunerDemuxRequest, int[] iArr) throws RemoteException;

    boolean requestDescrambler(TunerDescramblerRequest tunerDescramblerRequest, int[] iArr) throws RemoteException;

    boolean requestFrontend(TunerFrontendRequest tunerFrontendRequest, int[] iArr) throws RemoteException;

    boolean requestLnb(TunerLnbRequest tunerLnbRequest, int[] iArr) throws RemoteException;

    void setFrontendInfoList(TunerFrontendInfo[] tunerFrontendInfoArr) throws RemoteException;

    void setLnbInfoList(int[] iArr) throws RemoteException;

    void shareFrontend(int i, int i2) throws RemoteException;

    void unregisterClientProfile(int i) throws RemoteException;

    void updateCasInfo(int i, int i2) throws RemoteException;

    boolean updateClientPriority(int i, int i2, int i3) throws RemoteException;

    public static abstract class Stub extends Binder implements ITunerResourceManager {
        private static final String DESCRIPTOR = "android.media.tv.tunerresourcemanager.ITunerResourceManager";
        static final int TRANSACTION_isHigherPriority = 18;
        static final int TRANSACTION_registerClientProfile = 1;
        static final int TRANSACTION_releaseCasSession = 16;
        static final int TRANSACTION_releaseDemux = 14;
        static final int TRANSACTION_releaseDescrambler = 15;
        static final int TRANSACTION_releaseFrontend = 13;
        static final int TRANSACTION_releaseLnb = 17;
        static final int TRANSACTION_requestCasSession = 11;
        static final int TRANSACTION_requestDemux = 9;
        static final int TRANSACTION_requestDescrambler = 10;
        static final int TRANSACTION_requestFrontend = 7;
        static final int TRANSACTION_requestLnb = 12;
        static final int TRANSACTION_setFrontendInfoList = 4;
        static final int TRANSACTION_setLnbInfoList = 6;
        static final int TRANSACTION_shareFrontend = 8;
        static final int TRANSACTION_unregisterClientProfile = 2;
        static final int TRANSACTION_updateCasInfo = 5;
        static final int TRANSACTION_updateClientPriority = 3;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "registerClientProfile";
                case 2:
                    return "unregisterClientProfile";
                case 3:
                    return "updateClientPriority";
                case 4:
                    return "setFrontendInfoList";
                case 5:
                    return "updateCasInfo";
                case 6:
                    return "setLnbInfoList";
                case 7:
                    return "requestFrontend";
                case 8:
                    return "shareFrontend";
                case 9:
                    return "requestDemux";
                case 10:
                    return "requestDescrambler";
                case 11:
                    return "requestCasSession";
                case 12:
                    return "requestLnb";
                case 13:
                    return "releaseFrontend";
                case 14:
                    return "releaseDemux";
                case 15:
                    return "releaseDescrambler";
                case 16:
                    return "releaseCasSession";
                case 17:
                    return "releaseLnb";
                case 18:
                    return "isHigherPriority";
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

        public static ITunerResourceManager asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof ITunerResourceManager)) {
                return (ITunerResourceManager) iInterfaceQueryLocalInterface;
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
                    ResourceClientProfile resourceClientProfileCreateFromParcel = parcel.readInt() != 0 ? ResourceClientProfile.CREATOR.createFromParcel(parcel) : null;
                    IResourcesReclaimListener iResourcesReclaimListenerAsInterface = IResourcesReclaimListener.Stub.asInterface(parcel.readStrongBinder());
                    int i3 = parcel.readInt();
                    int[] iArr = i3 >= 0 ? new int[i3] : null;
                    registerClientProfile(resourceClientProfileCreateFromParcel, iResourcesReclaimListenerAsInterface, iArr);
                    parcel2.writeNoException();
                    parcel2.writeIntArray(iArr);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    unregisterClientProfile(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zUpdateClientPriority = updateClientPriority(parcel.readInt(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zUpdateClientPriority ? 1 : 0);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    setFrontendInfoList((TunerFrontendInfo[]) parcel.createTypedArray(TunerFrontendInfo.CREATOR));
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    updateCasInfo(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    setLnbInfoList(parcel.createIntArray());
                    parcel2.writeNoException();
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    TunerFrontendRequest tunerFrontendRequestCreateFromParcel = parcel.readInt() != 0 ? TunerFrontendRequest.CREATOR.createFromParcel(parcel) : null;
                    int i4 = parcel.readInt();
                    int[] iArr2 = i4 >= 0 ? new int[i4] : null;
                    boolean zRequestFrontend = requestFrontend(tunerFrontendRequestCreateFromParcel, iArr2);
                    parcel2.writeNoException();
                    parcel2.writeInt(zRequestFrontend ? 1 : 0);
                    parcel2.writeIntArray(iArr2);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    shareFrontend(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    TunerDemuxRequest tunerDemuxRequestCreateFromParcel = parcel.readInt() != 0 ? TunerDemuxRequest.CREATOR.createFromParcel(parcel) : null;
                    int i5 = parcel.readInt();
                    int[] iArr3 = i5 >= 0 ? new int[i5] : null;
                    boolean zRequestDemux = requestDemux(tunerDemuxRequestCreateFromParcel, iArr3);
                    parcel2.writeNoException();
                    parcel2.writeInt(zRequestDemux ? 1 : 0);
                    parcel2.writeIntArray(iArr3);
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    TunerDescramblerRequest tunerDescramblerRequestCreateFromParcel = parcel.readInt() != 0 ? TunerDescramblerRequest.CREATOR.createFromParcel(parcel) : null;
                    int i6 = parcel.readInt();
                    int[] iArr4 = i6 >= 0 ? new int[i6] : null;
                    boolean zRequestDescrambler = requestDescrambler(tunerDescramblerRequestCreateFromParcel, iArr4);
                    parcel2.writeNoException();
                    parcel2.writeInt(zRequestDescrambler ? 1 : 0);
                    parcel2.writeIntArray(iArr4);
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    CasSessionRequest casSessionRequestCreateFromParcel = parcel.readInt() != 0 ? CasSessionRequest.CREATOR.createFromParcel(parcel) : null;
                    int i7 = parcel.readInt();
                    int[] iArr5 = i7 >= 0 ? new int[i7] : null;
                    boolean zRequestCasSession = requestCasSession(casSessionRequestCreateFromParcel, iArr5);
                    parcel2.writeNoException();
                    parcel2.writeInt(zRequestCasSession ? 1 : 0);
                    parcel2.writeIntArray(iArr5);
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    TunerLnbRequest tunerLnbRequestCreateFromParcel = parcel.readInt() != 0 ? TunerLnbRequest.CREATOR.createFromParcel(parcel) : null;
                    int i8 = parcel.readInt();
                    int[] iArr6 = i8 >= 0 ? new int[i8] : null;
                    boolean zRequestLnb = requestLnb(tunerLnbRequestCreateFromParcel, iArr6);
                    parcel2.writeNoException();
                    parcel2.writeInt(zRequestLnb ? 1 : 0);
                    parcel2.writeIntArray(iArr6);
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    releaseFrontend(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    releaseDemux(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    releaseDescrambler(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    releaseCasSession(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    releaseLnb(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsHigherPriority = isHigherPriority(parcel.readInt() != 0 ? ResourceClientProfile.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? ResourceClientProfile.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsHigherPriority ? 1 : 0);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements ITunerResourceManager {
            public static ITunerResourceManager sDefaultImpl;
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

            @Override // android.media.tv.tunerresourcemanager.ITunerResourceManager
            public void registerClientProfile(ResourceClientProfile resourceClientProfile, IResourcesReclaimListener iResourcesReclaimListener, int[] iArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (resourceClientProfile != null) {
                        parcelObtain.writeInt(1);
                        resourceClientProfile.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeStrongBinder(iResourcesReclaimListener != null ? iResourcesReclaimListener.asBinder() : null);
                    if (iArr == null) {
                        parcelObtain.writeInt(-1);
                    } else {
                        parcelObtain.writeInt(iArr.length);
                    }
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerClientProfile(resourceClientProfile, iResourcesReclaimListener, iArr);
                    } else {
                        parcelObtain2.readException();
                        parcelObtain2.readIntArray(iArr);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.tv.tunerresourcemanager.ITunerResourceManager
            public void unregisterClientProfile(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterClientProfile(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.tv.tunerresourcemanager.ITunerResourceManager
            public boolean updateClientPriority(int i, int i2, int i3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().updateClientPriority(i, i2, i3);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.tv.tunerresourcemanager.ITunerResourceManager
            public void setFrontendInfoList(TunerFrontendInfo[] tunerFrontendInfoArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeTypedArray(tunerFrontendInfoArr, 0);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setFrontendInfoList(tunerFrontendInfoArr);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.tv.tunerresourcemanager.ITunerResourceManager
            public void updateCasInfo(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().updateCasInfo(i, i2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.tv.tunerresourcemanager.ITunerResourceManager
            public void setLnbInfoList(int[] iArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeIntArray(iArr);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setLnbInfoList(iArr);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.tv.tunerresourcemanager.ITunerResourceManager
            public boolean requestFrontend(TunerFrontendRequest tunerFrontendRequest, int[] iArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean z = true;
                    if (tunerFrontendRequest != null) {
                        parcelObtain.writeInt(1);
                        tunerFrontendRequest.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (iArr == null) {
                        parcelObtain.writeInt(-1);
                    } else {
                        parcelObtain.writeInt(iArr.length);
                    }
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().requestFrontend(tunerFrontendRequest, iArr);
                    }
                    parcelObtain2.readException();
                    if (parcelObtain2.readInt() == 0) {
                        z = false;
                    }
                    parcelObtain2.readIntArray(iArr);
                    return z;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.tv.tunerresourcemanager.ITunerResourceManager
            public void shareFrontend(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().shareFrontend(i, i2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.tv.tunerresourcemanager.ITunerResourceManager
            public boolean requestDemux(TunerDemuxRequest tunerDemuxRequest, int[] iArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean z = true;
                    if (tunerDemuxRequest != null) {
                        parcelObtain.writeInt(1);
                        tunerDemuxRequest.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (iArr == null) {
                        parcelObtain.writeInt(-1);
                    } else {
                        parcelObtain.writeInt(iArr.length);
                    }
                    if (!this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().requestDemux(tunerDemuxRequest, iArr);
                    }
                    parcelObtain2.readException();
                    if (parcelObtain2.readInt() == 0) {
                        z = false;
                    }
                    parcelObtain2.readIntArray(iArr);
                    return z;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.tv.tunerresourcemanager.ITunerResourceManager
            public boolean requestDescrambler(TunerDescramblerRequest tunerDescramblerRequest, int[] iArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean z = true;
                    if (tunerDescramblerRequest != null) {
                        parcelObtain.writeInt(1);
                        tunerDescramblerRequest.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (iArr == null) {
                        parcelObtain.writeInt(-1);
                    } else {
                        parcelObtain.writeInt(iArr.length);
                    }
                    if (!this.mRemote.transact(10, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().requestDescrambler(tunerDescramblerRequest, iArr);
                    }
                    parcelObtain2.readException();
                    if (parcelObtain2.readInt() == 0) {
                        z = false;
                    }
                    parcelObtain2.readIntArray(iArr);
                    return z;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.tv.tunerresourcemanager.ITunerResourceManager
            public boolean requestCasSession(CasSessionRequest casSessionRequest, int[] iArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean z = true;
                    if (casSessionRequest != null) {
                        parcelObtain.writeInt(1);
                        casSessionRequest.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (iArr == null) {
                        parcelObtain.writeInt(-1);
                    } else {
                        parcelObtain.writeInt(iArr.length);
                    }
                    if (!this.mRemote.transact(11, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().requestCasSession(casSessionRequest, iArr);
                    }
                    parcelObtain2.readException();
                    if (parcelObtain2.readInt() == 0) {
                        z = false;
                    }
                    parcelObtain2.readIntArray(iArr);
                    return z;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.tv.tunerresourcemanager.ITunerResourceManager
            public boolean requestLnb(TunerLnbRequest tunerLnbRequest, int[] iArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean z = true;
                    if (tunerLnbRequest != null) {
                        parcelObtain.writeInt(1);
                        tunerLnbRequest.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (iArr == null) {
                        parcelObtain.writeInt(-1);
                    } else {
                        parcelObtain.writeInt(iArr.length);
                    }
                    if (!this.mRemote.transact(12, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().requestLnb(tunerLnbRequest, iArr);
                    }
                    parcelObtain2.readException();
                    if (parcelObtain2.readInt() == 0) {
                        z = false;
                    }
                    parcelObtain2.readIntArray(iArr);
                    return z;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.tv.tunerresourcemanager.ITunerResourceManager
            public void releaseFrontend(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(13, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().releaseFrontend(i, i2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.tv.tunerresourcemanager.ITunerResourceManager
            public void releaseDemux(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(14, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().releaseDemux(i, i2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.tv.tunerresourcemanager.ITunerResourceManager
            public void releaseDescrambler(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(15, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().releaseDescrambler(i, i2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.tv.tunerresourcemanager.ITunerResourceManager
            public void releaseCasSession(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(16, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().releaseCasSession(i, i2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.tv.tunerresourcemanager.ITunerResourceManager
            public void releaseLnb(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(17, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().releaseLnb(i, i2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.tv.tunerresourcemanager.ITunerResourceManager
            public boolean isHigherPriority(ResourceClientProfile resourceClientProfile, ResourceClientProfile resourceClientProfile2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (resourceClientProfile != null) {
                        parcelObtain.writeInt(1);
                        resourceClientProfile.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (resourceClientProfile2 != null) {
                        parcelObtain.writeInt(1);
                        resourceClientProfile2.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(18, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isHigherPriority(resourceClientProfile, resourceClientProfile2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ITunerResourceManager iTunerResourceManager) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iTunerResourceManager == null) {
                return false;
            }
            Proxy.sDefaultImpl = iTunerResourceManager;
            return true;
        }

        public static ITunerResourceManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
