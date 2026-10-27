package com.android.internal.widget;

import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.widget.RemoteViews;

/* JADX INFO: loaded from: classes4.dex */
public interface IRemoteViewsFactory extends IInterface {

    public static class Default implements IRemoteViewsFactory {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.android.internal.widget.IRemoteViewsFactory
        public int getCount() throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.widget.IRemoteViewsFactory
        public long getItemId(int i) throws RemoteException {
            return 0L;
        }

        @Override // com.android.internal.widget.IRemoteViewsFactory
        public RemoteViews getLoadingView() throws RemoteException {
            return null;
        }

        @Override // com.android.internal.widget.IRemoteViewsFactory
        public RemoteViews getViewAt(int i) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.widget.IRemoteViewsFactory
        public int getViewTypeCount() throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.widget.IRemoteViewsFactory
        public boolean hasStableIds() throws RemoteException {
            return false;
        }

        @Override // com.android.internal.widget.IRemoteViewsFactory
        public boolean isCreated() throws RemoteException {
            return false;
        }

        @Override // com.android.internal.widget.IRemoteViewsFactory
        public void onDataSetChanged() throws RemoteException {
        }

        @Override // com.android.internal.widget.IRemoteViewsFactory
        public void onDataSetChangedAsync() throws RemoteException {
        }

        @Override // com.android.internal.widget.IRemoteViewsFactory
        public void onDestroy(Intent intent) throws RemoteException {
        }
    }

    int getCount() throws RemoteException;

    long getItemId(int i) throws RemoteException;

    RemoteViews getLoadingView() throws RemoteException;

    RemoteViews getViewAt(int i) throws RemoteException;

    int getViewTypeCount() throws RemoteException;

    boolean hasStableIds() throws RemoteException;

    boolean isCreated() throws RemoteException;

    void onDataSetChanged() throws RemoteException;

    void onDataSetChangedAsync() throws RemoteException;

    void onDestroy(Intent intent) throws RemoteException;

    public static abstract class Stub extends Binder implements IRemoteViewsFactory {
        private static final String DESCRIPTOR = "com.android.internal.widget.IRemoteViewsFactory";
        static final int TRANSACTION_getCount = 4;
        static final int TRANSACTION_getItemId = 8;
        static final int TRANSACTION_getLoadingView = 6;
        static final int TRANSACTION_getViewAt = 5;
        static final int TRANSACTION_getViewTypeCount = 7;
        static final int TRANSACTION_hasStableIds = 9;
        static final int TRANSACTION_isCreated = 10;
        static final int TRANSACTION_onDataSetChanged = 1;
        static final int TRANSACTION_onDataSetChangedAsync = 2;
        static final int TRANSACTION_onDestroy = 3;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "onDataSetChanged";
                case 2:
                    return "onDataSetChangedAsync";
                case 3:
                    return "onDestroy";
                case 4:
                    return "getCount";
                case 5:
                    return "getViewAt";
                case 6:
                    return "getLoadingView";
                case 7:
                    return "getViewTypeCount";
                case 8:
                    return "getItemId";
                case 9:
                    return "hasStableIds";
                case 10:
                    return "isCreated";
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

        public static IRemoteViewsFactory asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IRemoteViewsFactory)) {
                return (IRemoteViewsFactory) iInterfaceQueryLocalInterface;
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
                    onDataSetChanged();
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    onDataSetChangedAsync();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    onDestroy(parcel.readInt() != 0 ? Intent.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    int count = getCount();
                    parcel2.writeNoException();
                    parcel2.writeInt(count);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    RemoteViews viewAt = getViewAt(parcel.readInt());
                    parcel2.writeNoException();
                    if (viewAt != null) {
                        parcel2.writeInt(1);
                        viewAt.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    RemoteViews loadingView = getLoadingView();
                    parcel2.writeNoException();
                    if (loadingView != null) {
                        parcel2.writeInt(1);
                        loadingView.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    int viewTypeCount = getViewTypeCount();
                    parcel2.writeNoException();
                    parcel2.writeInt(viewTypeCount);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    long itemId = getItemId(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeLong(itemId);
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zHasStableIds = hasStableIds();
                    parcel2.writeNoException();
                    parcel2.writeInt(zHasStableIds ? 1 : 0);
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsCreated = isCreated();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsCreated ? 1 : 0);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IRemoteViewsFactory {
            public static IRemoteViewsFactory sDefaultImpl;
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

            @Override // com.android.internal.widget.IRemoteViewsFactory
            public void onDataSetChanged() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onDataSetChanged();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.widget.IRemoteViewsFactory
            public void onDataSetChangedAsync() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onDataSetChangedAsync();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.widget.IRemoteViewsFactory
            public void onDestroy(Intent intent) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (intent != null) {
                        parcelObtain.writeInt(1);
                        intent.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onDestroy(intent);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.widget.IRemoteViewsFactory
            public int getCount() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCount();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.widget.IRemoteViewsFactory
            public RemoteViews getViewAt(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getViewAt(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? RemoteViews.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.widget.IRemoteViewsFactory
            public RemoteViews getLoadingView() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLoadingView();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? RemoteViews.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.widget.IRemoteViewsFactory
            public int getViewTypeCount() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getViewTypeCount();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.widget.IRemoteViewsFactory
            public long getItemId(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getItemId(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readLong();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.widget.IRemoteViewsFactory
            public boolean hasStableIds() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().hasStableIds();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.widget.IRemoteViewsFactory
            public boolean isCreated() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(10, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isCreated();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IRemoteViewsFactory iRemoteViewsFactory) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iRemoteViewsFactory == null) {
                return false;
            }
            Proxy.sDefaultImpl = iRemoteViewsFactory;
            return true;
        }

        public static IRemoteViewsFactory getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
