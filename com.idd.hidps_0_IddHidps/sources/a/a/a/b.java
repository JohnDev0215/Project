package a.a.a;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* JADX INFO: loaded from: classes.dex */
public interface b extends IInterface {

    public static abstract class a extends Binder implements b {

        /* JADX INFO: renamed from: a.a.a.b$a$a, reason: collision with other inner class name */
        private static class C0002a implements b {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public static b f2b;

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            private IBinder f3a;

            C0002a(IBinder iBinder) {
                this.f3a = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f3a;
            }

            @Override // a.a.a.b
            public void j(int i) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.qtxa.cleanAidl.ISysCleanerCallback");
                    parcelObtain.writeInt(i);
                    if (this.f3a.transact(1, parcelObtain, parcelObtain2, 0) || a.w() == null) {
                        parcelObtain2.readException();
                    } else {
                        a.w().j(i);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // a.a.a.b
            public void p(int i) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.qtxa.cleanAidl.ISysCleanerCallback");
                    parcelObtain.writeInt(i);
                    if (this.f3a.transact(2, parcelObtain, parcelObtain2, 0) || a.w() == null) {
                        parcelObtain2.readException();
                    } else {
                        a.w().p(i);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // a.a.a.b
            public void q(int i) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.qtxa.cleanAidl.ISysCleanerCallback");
                    parcelObtain.writeInt(i);
                    if (this.f3a.transact(3, parcelObtain, parcelObtain2, 0) || a.w() == null) {
                        parcelObtain2.readException();
                    } else {
                        a.w().q(i);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static b f(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.qtxa.cleanAidl.ISysCleanerCallback");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof b)) ? new C0002a(iBinder) : (b) iInterfaceQueryLocalInterface;
        }

        public static b w() {
            return C0002a.f2b;
        }
    }

    void j(int i);

    void p(int i);

    void q(int i);
}
