package defpackage;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import defpackage.qn;

/* JADX INFO: compiled from: IParentProcess.java */
/* JADX INFO: loaded from: classes.dex */
public interface iw0 extends IInterface {
    void f(String str);

    void l();

    void m(int i, int i2, long j, Bundle bundle);

    /* JADX INFO: compiled from: IParentProcess.java */
    public static abstract class a extends Binder implements iw0 {

        /* JADX INFO: renamed from: iw0$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: IParentProcess.java */
        public static class C0059a implements iw0 {
            public final IBinder e;

            public C0059a(IBinder iBinder) {
                this.e = iBinder;
            }

            @Override // android.os.IInterface
            public final IBinder asBinder() {
                return this.e;
            }

            @Override // defpackage.iw0
            public final void f(String str) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("org.chromium.base.process_launcher.IParentProcess");
                    parcelObtain.writeString(str);
                    this.e.transact(2, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // defpackage.iw0
            public final void l() {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("org.chromium.base.process_launcher.IParentProcess");
                    this.e.transact(3, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // defpackage.iw0
            public final void m(int i, int i2, long j, Bundle bundle) {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("org.chromium.base.process_launcher.IParentProcess");
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeLong(j);
                    if (bundle != null) {
                        parcelObtain.writeInt(1);
                        bundle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    this.e.transact(1, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public a() {
            attachInterface(this, "org.chromium.base.process_launcher.IParentProcess");
        }

        @Override // android.os.Binder
        public final boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface("org.chromium.base.process_launcher.IParentProcess");
            }
            if (i == 1598968902) {
                parcel2.writeString("org.chromium.base.process_launcher.IParentProcess");
                return true;
            }
            if (i == 1) {
                ((qn.a) this).m(parcel.readInt(), parcel.readInt(), parcel.readLong(), (Bundle) (parcel.readInt() != 0 ? Bundle.CREATOR.createFromParcel(parcel) : null));
            } else if (i == 2) {
                ((qn.a) this).f(parcel.readString());
                parcel2.writeNoException();
            } else {
                if (i != 3) {
                    return super.onTransact(i, parcel, parcel2, i2);
                }
                ((qn.a) this).l();
                parcel2.writeNoException();
            }
            return true;
        }

        @Override // android.os.IInterface
        public final IBinder asBinder() {
            return this;
        }
    }
}
