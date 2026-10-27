package android.service.contentcapture;

import android.content.ComponentName;
import android.content.ContentCaptureOptions;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.contentcapture.ContentCaptureCondition;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public interface IContentCaptureServiceCallback extends IInterface {

    public static class Default implements IContentCaptureServiceCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.service.contentcapture.IContentCaptureServiceCallback
        public void disableSelf() throws RemoteException {
        }

        @Override // android.service.contentcapture.IContentCaptureServiceCallback
        public void setContentCaptureConditions(String str, List<ContentCaptureCondition> list) throws RemoteException {
        }

        @Override // android.service.contentcapture.IContentCaptureServiceCallback
        public void setContentCaptureWhitelist(List<String> list, List<ComponentName> list2) throws RemoteException {
        }

        @Override // android.service.contentcapture.IContentCaptureServiceCallback
        public void writeSessionFlush(int i, ComponentName componentName, FlushMetrics flushMetrics, ContentCaptureOptions contentCaptureOptions, int i2) throws RemoteException {
        }
    }

    void disableSelf() throws RemoteException;

    void setContentCaptureConditions(String str, List<ContentCaptureCondition> list) throws RemoteException;

    void setContentCaptureWhitelist(List<String> list, List<ComponentName> list2) throws RemoteException;

    void writeSessionFlush(int i, ComponentName componentName, FlushMetrics flushMetrics, ContentCaptureOptions contentCaptureOptions, int i2) throws RemoteException;

    public static abstract class Stub extends Binder implements IContentCaptureServiceCallback {
        private static final String DESCRIPTOR = "android.service.contentcapture.IContentCaptureServiceCallback";
        static final int TRANSACTION_disableSelf = 3;
        static final int TRANSACTION_setContentCaptureConditions = 2;
        static final int TRANSACTION_setContentCaptureWhitelist = 1;
        static final int TRANSACTION_writeSessionFlush = 4;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "setContentCaptureWhitelist";
            }
            if (i == 2) {
                return "setContentCaptureConditions";
            }
            if (i == 3) {
                return "disableSelf";
            }
            if (i != 4) {
                return null;
            }
            return "writeSessionFlush";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IContentCaptureServiceCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IContentCaptureServiceCallback)) {
                return (IContentCaptureServiceCallback) iInterfaceQueryLocalInterface;
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
                setContentCaptureWhitelist(parcel.createStringArrayList(), parcel.createTypedArrayList(ComponentName.CREATOR));
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                setContentCaptureConditions(parcel.readString(), parcel.createTypedArrayList(ContentCaptureCondition.CREATOR));
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                disableSelf();
                return true;
            }
            if (i == 4) {
                parcel.enforceInterface(DESCRIPTOR);
                writeSessionFlush(parcel.readInt(), parcel.readInt() != 0 ? ComponentName.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? FlushMetrics.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? ContentCaptureOptions.CREATOR.createFromParcel(parcel) : null, parcel.readInt());
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IContentCaptureServiceCallback {
            public static IContentCaptureServiceCallback sDefaultImpl;
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

            @Override // android.service.contentcapture.IContentCaptureServiceCallback
            public void setContentCaptureWhitelist(List<String> list, List<ComponentName> list2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStringList(list);
                    parcelObtain.writeTypedList(list2);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().setContentCaptureWhitelist(list, list2);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.contentcapture.IContentCaptureServiceCallback
            public void setContentCaptureConditions(String str, List<ContentCaptureCondition> list) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeTypedList(list);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().setContentCaptureConditions(str, list);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.contentcapture.IContentCaptureServiceCallback
            public void disableSelf() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().disableSelf();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.contentcapture.IContentCaptureServiceCallback
            public void writeSessionFlush(int i, ComponentName componentName, FlushMetrics flushMetrics, ContentCaptureOptions contentCaptureOptions, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (componentName != null) {
                        parcelObtain.writeInt(1);
                        componentName.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (flushMetrics != null) {
                        parcelObtain.writeInt(1);
                        flushMetrics.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (contentCaptureOptions != null) {
                        parcelObtain.writeInt(1);
                        contentCaptureOptions.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i2);
                    if (this.mRemote.transact(4, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().writeSessionFlush(i, componentName, flushMetrics, contentCaptureOptions, i2);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IContentCaptureServiceCallback iContentCaptureServiceCallback) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iContentCaptureServiceCallback == null) {
                return false;
            }
            Proxy.sDefaultImpl = iContentCaptureServiceCallback;
            return true;
        }

        public static IContentCaptureServiceCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
