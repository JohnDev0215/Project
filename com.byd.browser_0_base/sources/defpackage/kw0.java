package defpackage;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* JADX INFO: compiled from: IPaymentDetailsUpdateServiceCallback.java */
/* JADX INFO: loaded from: classes3.dex */
public interface kw0 extends IInterface {

    /* JADX INFO: compiled from: IPaymentDetailsUpdateServiceCallback.java */
    public static abstract class a extends Binder implements kw0 {

        /* JADX INFO: renamed from: kw0$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: IPaymentDetailsUpdateServiceCallback.java */
        public static class C0062a implements kw0 {
            public final IBinder e;

            public C0062a(IBinder iBinder) {
                this.e = iBinder;
            }

            @Override // android.os.IInterface
            public final IBinder asBinder() {
                return this.e;
            }

            @Override // defpackage.kw0
            public final void s(Bundle bundle) {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("org.chromium.components.payments.IPaymentDetailsUpdateServiceCallback");
                    parcelObtain.writeInt(1);
                    bundle.writeToParcel(parcelObtain, 0);
                    this.e.transact(1, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // defpackage.kw0
            public final void v() {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("org.chromium.components.payments.IPaymentDetailsUpdateServiceCallback");
                    this.e.transact(2, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static kw0 A(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("org.chromium.components.payments.IPaymentDetailsUpdateServiceCallback");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof kw0)) ? new C0062a(iBinder) : (kw0) iInterfaceQueryLocalInterface;
        }
    }

    void s(Bundle bundle);

    void v();
}
