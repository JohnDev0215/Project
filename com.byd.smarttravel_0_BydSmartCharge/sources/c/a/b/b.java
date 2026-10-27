package c.a.b;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.byd.smarttravel.PlanBean;

/* JADX INFO: compiled from: IChargeAidlInterface.java */
/* JADX INFO: loaded from: classes.dex */
public interface b extends IInterface {

    /* JADX INFO: compiled from: IChargeAidlInterface.java */
    public static abstract class a extends Binder implements b {

        /* JADX INFO: renamed from: c.a.b.b$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: IChargeAidlInterface.java */
        public static class C0045a implements b {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static b f1588a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public IBinder f1589b;

            public C0045a(IBinder iBinder) {
                this.f1589b = iBinder;
            }

            @Override // c.a.b.b
            public void a(c.a.b.a aVar) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.byd.smarttravel.IChargeAidlInterface");
                    parcelObtain.writeStrongBinder(aVar != null ? aVar.asBinder() : null);
                    if (this.f1589b.transact(4, parcelObtain, parcelObtain2, 0) || a.g() == null) {
                        parcelObtain2.readException();
                    } else {
                        a.g().a(aVar);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f1589b;
            }

            @Override // c.a.b.b
            public void c(c.a.b.a aVar) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.byd.smarttravel.IChargeAidlInterface");
                    parcelObtain.writeStrongBinder(aVar != null ? aVar.asBinder() : null);
                    if (this.f1589b.transact(5, parcelObtain, parcelObtain2, 0) || a.g() == null) {
                        parcelObtain2.readException();
                    } else {
                        a.g().c(aVar);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // c.a.b.b
            public PlanBean d() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.byd.smarttravel.IChargeAidlInterface");
                    if (!this.f1589b.transact(1, parcelObtain, parcelObtain2, 0) && a.g() != null) {
                        return a.g().d();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? PlanBean.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // c.a.b.b
            public void e(PlanBean planBean) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.byd.smarttravel.IChargeAidlInterface");
                    if (planBean != null) {
                        parcelObtain.writeInt(1);
                        planBean.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.f1589b.transact(2, parcelObtain, parcelObtain2, 0) && a.g() != null) {
                        a.g().e(planBean);
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
        }

        public static b f(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.byd.smarttravel.IChargeAidlInterface");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof b)) ? new C0045a(iBinder) : (b) iInterfaceQueryLocalInterface;
        }

        public static b g() {
            return C0045a.f1588a;
        }
    }

    void a(c.a.b.a aVar) throws RemoteException;

    void c(c.a.b.a aVar) throws RemoteException;

    PlanBean d() throws RemoteException;

    void e(PlanBean planBean) throws RemoteException;
}
