package d.c.a.a.a;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: compiled from: IRecordListener.java */
/* JADX INFO: loaded from: classes.dex */
public interface a extends IInterface {

    /* JADX INFO: renamed from: d.c.a.a.a.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: IRecordListener.java */
    public static abstract class AbstractBinderC0126a extends Binder implements a {
        private static final String DESCRIPTOR = "com.iflytek.autofly.speechfrontend.aidl.IRecordListener";
        static final int TRANSACTION_getRecordBuffer = 1;

        /* JADX INFO: renamed from: d.c.a.a.a.a$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: IRecordListener.java */
        private static class C0127a implements a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static a f4050a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            private IBinder f4051b;

            C0127a(IBinder iBinder) {
                this.f4051b = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f4051b;
            }
        }

        public AbstractBinderC0126a() {
            attachInterface(this, DESCRIPTOR);
        }

        public static a asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof a)) ? new C0127a(iBinder) : (a) iInterfaceQueryLocalInterface;
        }

        public static a getDefaultImpl() {
            return C0127a.f4050a;
        }

        public static boolean setDefaultImpl(a aVar) {
            if (C0127a.f4050a != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (aVar == null) {
                return false;
            }
            C0127a.f4050a = aVar;
            return true;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1) {
                if (i != 1598968902) {
                    return super.onTransact(i, parcel, parcel2, i2);
                }
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            parcel.enforceInterface(DESCRIPTOR);
            byte[] recordBuffer = getRecordBuffer(parcel.readInt());
            parcel2.writeNoException();
            parcel2.writeByteArray(recordBuffer);
            return true;
        }
    }

    byte[] getRecordBuffer(int i) throws RemoteException;
}
