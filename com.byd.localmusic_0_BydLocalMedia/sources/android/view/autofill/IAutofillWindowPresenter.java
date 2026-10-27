package android.view.autofill;

import android.graphics.Rect;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.ThreadedRenderer;
import android.view.WindowManager;

/* JADX INFO: loaded from: classes3.dex */
public interface IAutofillWindowPresenter extends IInterface {

    public static class Default implements IAutofillWindowPresenter {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.view.autofill.IAutofillWindowPresenter
        public void hide(Rect rect) throws RemoteException {
        }

        @Override // android.view.autofill.IAutofillWindowPresenter
        public void show(WindowManager.LayoutParams layoutParams, Rect rect, boolean z, int i) throws RemoteException {
        }
    }

    void hide(Rect rect) throws RemoteException;

    void show(WindowManager.LayoutParams layoutParams, Rect rect, boolean z, int i) throws RemoteException;

    public static abstract class Stub extends Binder implements IAutofillWindowPresenter {
        private static final String DESCRIPTOR = "android.view.autofill.IAutofillWindowPresenter";
        static final int TRANSACTION_hide = 2;
        static final int TRANSACTION_show = 1;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return ThreadedRenderer.OVERDRAW_PROPERTY_SHOW;
            }
            if (i != 2) {
                return null;
            }
            return "hide";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IAutofillWindowPresenter asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IAutofillWindowPresenter)) {
                return (IAutofillWindowPresenter) iInterfaceQueryLocalInterface;
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
                show(parcel.readInt() != 0 ? WindowManager.LayoutParams.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? Rect.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0, parcel.readInt());
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                hide(parcel.readInt() != 0 ? Rect.CREATOR.createFromParcel(parcel) : null);
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IAutofillWindowPresenter {
            public static IAutofillWindowPresenter sDefaultImpl;
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

            @Override // android.view.autofill.IAutofillWindowPresenter
            public void show(WindowManager.LayoutParams layoutParams, Rect rect, boolean z, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (layoutParams != null) {
                        parcelObtain.writeInt(1);
                        layoutParams.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (rect != null) {
                        parcelObtain.writeInt(1);
                        rect.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().show(layoutParams, rect, z, i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.autofill.IAutofillWindowPresenter
            public void hide(Rect rect) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (rect != null) {
                        parcelObtain.writeInt(1);
                        rect.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().hide(rect);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IAutofillWindowPresenter iAutofillWindowPresenter) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iAutofillWindowPresenter == null) {
                return false;
            }
            Proxy.sDefaultImpl = iAutofillWindowPresenter;
            return true;
        }

        public static IAutofillWindowPresenter getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
