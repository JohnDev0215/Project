package b.s;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: compiled from: IMultiInstanceInvalidationCallback.java */
/* JADX INFO: loaded from: classes.dex */
public interface a extends IInterface {

    /* JADX INFO: renamed from: b.s.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: IMultiInstanceInvalidationCallback.java */
    public static abstract class AbstractBinderC0060a extends Binder implements a {

        /* JADX INFO: renamed from: b.s.a$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: IMultiInstanceInvalidationCallback.java */
        public static class C0061a implements a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public IBinder f3143a;

            public C0061a(IBinder iBinder) {
                this.f3143a = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f3143a;
            }

            @Override // b.s.a
            public void y(String[] strArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("androidx.room.IMultiInstanceInvalidationCallback");
                    parcelObtain.writeStringArray(strArr);
                    this.f3143a.transact(1, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static a K(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("androidx.room.IMultiInstanceInvalidationCallback");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof a)) ? new C0061a(iBinder) : (a) iInterfaceQueryLocalInterface;
        }
    }

    void y(String[] strArr) throws RemoteException;
}
