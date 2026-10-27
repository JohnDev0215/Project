package com.byd.data.common;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* JADX INFO: compiled from: ICloudCollect.java */
/* JADX INFO: loaded from: classes.dex */
public interface a extends IInterface {

    /* JADX INFO: renamed from: com.byd.data.common.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ICloudCollect.java */
    public static abstract class AbstractBinderC0063a extends Binder implements a {

        /* JADX INFO: renamed from: com.byd.data.common.a$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: ICloudCollect.java */
        private static class C0064a implements a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static a f3288a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            private IBinder f3289b;

            C0064a(IBinder iBinder) {
                this.f3289b = iBinder;
            }

            @Override // com.byd.data.common.a
            public void a(int i2, int i3) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.byd.data.common.ICloudCollect");
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    if (this.f3289b.transact(4, parcelObtain, parcelObtain2, 0) || AbstractBinderC0063a.s() == null) {
                        parcelObtain2.readException();
                    } else {
                        AbstractBinderC0063a.s().a(i2, i3);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f3289b;
            }

            @Override // com.byd.data.common.a
            public void f(byte b2) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.byd.data.common.ICloudCollect");
                    parcelObtain.writeByte(b2);
                    if (this.f3289b.transact(5, parcelObtain, parcelObtain2, 0) || AbstractBinderC0063a.s() == null) {
                        parcelObtain2.readException();
                    } else {
                        AbstractBinderC0063a.s().f(b2);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.data.common.a
            public void j() {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.byd.data.common.ICloudCollect");
                    if (this.f3289b.transact(6, parcelObtain, parcelObtain2, 0) || AbstractBinderC0063a.s() == null) {
                        parcelObtain2.readException();
                    } else {
                        AbstractBinderC0063a.s().j();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.data.common.a
            public void k() {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.byd.data.common.ICloudCollect");
                    if (this.f3289b.transact(2, parcelObtain, parcelObtain2, 0) || AbstractBinderC0063a.s() == null) {
                        parcelObtain2.readException();
                    } else {
                        AbstractBinderC0063a.s().k();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.data.common.a
            public void m(byte b2) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.byd.data.common.ICloudCollect");
                    parcelObtain.writeByte(b2);
                    if (this.f3289b.transact(8, parcelObtain, parcelObtain2, 0) || AbstractBinderC0063a.s() == null) {
                        parcelObtain2.readException();
                    } else {
                        AbstractBinderC0063a.s().m(b2);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.data.common.a
            public void n(boolean z) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.byd.data.common.ICloudCollect");
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (this.f3289b.transact(3, parcelObtain, parcelObtain2, 0) || AbstractBinderC0063a.s() == null) {
                        parcelObtain2.readException();
                    } else {
                        AbstractBinderC0063a.s().n(z);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public AbstractBinderC0063a() {
            attachInterface(this, "com.byd.data.common.ICloudCollect");
        }

        public static a r(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.byd.data.common.ICloudCollect");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof a)) ? new C0064a(iBinder) : (a) iInterfaceQueryLocalInterface;
        }

        public static a s() {
            return C0064a.f3288a;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i3) {
            if (i2 == 1598968902) {
                parcel2.writeString("com.byd.data.common.ICloudCollect");
                return true;
            }
            switch (i2) {
                case 1:
                    parcel.enforceInterface("com.byd.data.common.ICloudCollect");
                    String strB = b();
                    parcel2.writeNoException();
                    parcel2.writeString(strB);
                    return true;
                case 2:
                    parcel.enforceInterface("com.byd.data.common.ICloudCollect");
                    k();
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface("com.byd.data.common.ICloudCollect");
                    n(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface("com.byd.data.common.ICloudCollect");
                    a(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface("com.byd.data.common.ICloudCollect");
                    f(parcel.readByte());
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface("com.byd.data.common.ICloudCollect");
                    j();
                    parcel2.writeNoException();
                    return true;
                case 7:
                    parcel.enforceInterface("com.byd.data.common.ICloudCollect");
                    boolean zI = i(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zI ? 1 : 0);
                    return true;
                case 8:
                    parcel.enforceInterface("com.byd.data.common.ICloudCollect");
                    m(parcel.readByte());
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i2, parcel, parcel2, i3);
            }
        }
    }

    void a(int i2, int i3);

    String b();

    void f(byte b2);

    boolean i(int i2);

    void j();

    void k();

    void m(byte b2);

    void n(boolean z);
}
