package com.neusoft.na.ocservice;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public interface ISearchByKeywordCallBack extends IInterface {

    public static class Default implements ISearchByKeywordCallBack {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.neusoft.na.ocservice.ISearchByKeywordCallBack
        public void searchByKeywordResult(int i, List<SuggestionCity> list, List<PoiInfo> list2) throws RemoteException {
        }
    }

    void searchByKeywordResult(int i, List<SuggestionCity> list, List<PoiInfo> list2) throws RemoteException;

    public static abstract class Stub extends Binder implements ISearchByKeywordCallBack {
        private static final String DESCRIPTOR = "com.neusoft.na.ocservice.ISearchByKeywordCallBack";
        static final int TRANSACTION_searchByKeywordResult = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ISearchByKeywordCallBack asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof ISearchByKeywordCallBack)) {
                return (ISearchByKeywordCallBack) iInterfaceQueryLocalInterface;
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
            searchByKeywordResult(parcel.readInt(), parcel.createTypedArrayList(SuggestionCity.CREATOR), parcel.createTypedArrayList(PoiInfo.CREATOR));
            parcel2.writeNoException();
            return true;
        }

        private static class Proxy implements ISearchByKeywordCallBack {
            public static ISearchByKeywordCallBack sDefaultImpl;
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

            @Override // com.neusoft.na.ocservice.ISearchByKeywordCallBack
            public void searchByKeywordResult(int i, List<SuggestionCity> list, List<PoiInfo> list2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeTypedList(list);
                    parcelObtain.writeTypedList(list2);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().searchByKeywordResult(i, list, list2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ISearchByKeywordCallBack iSearchByKeywordCallBack) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iSearchByKeywordCallBack == null) {
                return false;
            }
            Proxy.sDefaultImpl = iSearchByKeywordCallBack;
            return true;
        }

        public static ISearchByKeywordCallBack getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
