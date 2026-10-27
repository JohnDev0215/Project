package a.a.a;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* JADX INFO: loaded from: classes.dex */
public interface a extends IInterface {

    /* JADX INFO: renamed from: a.a.a.a$a, reason: collision with other inner class name */
    public static abstract class AbstractBinderC0000a extends Binder implements a {

        /* JADX INFO: renamed from: a.a.a.a$a$a, reason: collision with other inner class name */
        private static class C0001a implements a {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public static a f0b;

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            private IBinder f1a;

            C0001a(IBinder iBinder) {
                this.f1a = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f1a;
            }

            @Override // a.a.a.a
            public String e() {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.qtxa.cleanAidl.IQtSystemCleanerService");
                    if (!this.f1a.transact(1, parcelObtain, parcelObtain2, 0) && AbstractBinderC0000a.w() != null) {
                        return AbstractBinderC0000a.w().e();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // a.a.a.a
            public String g(int i) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.qtxa.cleanAidl.IQtSystemCleanerService");
                    parcelObtain.writeInt(i);
                    if (!this.f1a.transact(3, parcelObtain, parcelObtain2, 0) && AbstractBinderC0000a.w() != null) {
                        return AbstractBinderC0000a.w().g(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // a.a.a.a
            public String h(String str, int i) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.qtxa.cleanAidl.IQtSystemCleanerService");
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.f1a.transact(6, parcelObtain, parcelObtain2, 0) && AbstractBinderC0000a.w() != null) {
                        return AbstractBinderC0000a.w().h(str, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // a.a.a.a
            public String l(String str) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.qtxa.cleanAidl.IQtSystemCleanerService");
                    parcelObtain.writeString(str);
                    if (!this.f1a.transact(10, parcelObtain, parcelObtain2, 0) && AbstractBinderC0000a.w() != null) {
                        return AbstractBinderC0000a.w().l(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // a.a.a.a
            public int m(String str) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.qtxa.cleanAidl.IQtSystemCleanerService");
                    parcelObtain.writeString(str);
                    if (!this.f1a.transact(9, parcelObtain, parcelObtain2, 0) && AbstractBinderC0000a.w() != null) {
                        return AbstractBinderC0000a.w().m(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // a.a.a.a
            public int r(String str) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.qtxa.cleanAidl.IQtSystemCleanerService");
                    parcelObtain.writeString(str);
                    if (!this.f1a.transact(8, parcelObtain, parcelObtain2, 0) && AbstractBinderC0000a.w() != null) {
                        return AbstractBinderC0000a.w().r(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // a.a.a.a
            public String u() {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.qtxa.cleanAidl.IQtSystemCleanerService");
                    if (!this.f1a.transact(2, parcelObtain, parcelObtain2, 0) && AbstractBinderC0000a.w() != null) {
                        return AbstractBinderC0000a.w().u();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static a f(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.qtxa.cleanAidl.IQtSystemCleanerService");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof a)) ? new C0001a(iBinder) : (a) iInterfaceQueryLocalInterface;
        }

        public static a w() {
            return C0001a.f0b;
        }
    }

    String e();

    String g(int i);

    String h(String str, int i);

    String l(String str);

    int m(String str);

    int r(String str);

    String u();
}
