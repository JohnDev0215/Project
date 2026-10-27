package q4;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.byd.smarttravel.PlanBean;

/* JADX INFO: compiled from: ICallbackInterface.java */
/* JADX INFO: loaded from: classes2.dex */
public interface b extends IInterface {

    /* JADX INFO: compiled from: ICallbackInterface.java */
    public static abstract class a extends Binder implements b {

        /* JADX INFO: renamed from: q4.b$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: ICallbackInterface.java */
        public static class C0108a implements b {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static b f7427a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public IBinder f7428b;

            public C0108a(IBinder iBinder) {
                this.f7428b = iBinder;
            }

            @Override // q4.b
            public void a0(int i8, PlanBean planBean) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.byd.smarttravel.ICallbackInterface");
                    parcelObtain.writeInt(i8);
                    if (planBean != null) {
                        parcelObtain.writeInt(1);
                        planBean.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.f7428b.transact(1, parcelObtain, parcelObtain2, 0) && a.B0() != null) {
                        a.B0().a0(i8, planBean);
                        return;
                    }
                    parcelObtain2.readException();
                    if (parcelObtain2.readInt() != 0) {
                        planBean.f(parcelObtain2);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f7428b;
            }
        }

        public static b A0(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.byd.smarttravel.ICallbackInterface");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof b)) ? new C0108a(iBinder) : (b) iInterfaceQueryLocalInterface;
        }

        public static b B0() {
            return C0108a.f7427a;
        }
    }

    void a0(int i8, PlanBean planBean) throws RemoteException;
}
