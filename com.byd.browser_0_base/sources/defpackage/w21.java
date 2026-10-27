package defpackage;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import org.chromium.base.task.PostTask;

/* JADX INFO: compiled from: IsReadyToPayServiceCallback.java */
/* JADX INFO: loaded from: classes.dex */
public interface w21 extends IInterface {

    /* JADX INFO: compiled from: IsReadyToPayServiceCallback.java */
    public static abstract class a extends Binder implements w21 {
        public a() {
            attachInterface(this, "org.chromium.IsReadyToPayServiceCallback");
        }

        @Override // android.os.Binder
        public final boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface("org.chromium.IsReadyToPayServiceCallback");
            }
            if (i == 1598968902) {
                parcel2.writeString("org.chromium.IsReadyToPayServiceCallback");
                return true;
            }
            if (i != 1) {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            int i3 = parcel.readInt();
            int i4 = 0;
            boolean z = i3 != 0;
            x21 x21Var = (x21) this;
            if (x21Var.f != null) {
                oj2.O("PaymentRequest.EventResponse.IsReadyToPay", z);
                org.chromium.components.payments.a aVar = (org.chromium.components.payments.a) x21Var.f;
                aVar.getClass();
                PostTask.d(rh3.a, new d5(aVar, z, i4));
                x21Var.f = null;
                if (x21Var.g) {
                    x21Var.e.unbindService(x21Var);
                    x21Var.g = false;
                }
                x21Var.i.removeCallbacksAndMessages(null);
            }
            return true;
        }

        @Override // android.os.IInterface
        public final IBinder asBinder() {
            return this;
        }
    }
}
