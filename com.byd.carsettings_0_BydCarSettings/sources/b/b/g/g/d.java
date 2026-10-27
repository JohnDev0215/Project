package b.b.g.g;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.byd.carsettings.db.model.EnergyBean;
import java.util.List;

/* JADX INFO: compiled from: IEnergyAidlInterface.java */
/* JADX INFO: loaded from: classes2.dex */
public interface d extends IInterface {

    /* JADX INFO: compiled from: IEnergyAidlInterface.java */
    public static abstract class a extends Binder implements d {

        /* JADX INFO: renamed from: b.b.g.g.d$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: IEnergyAidlInterface.java */
        public static class C0043a implements d {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static d f795a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public IBinder f796b;

            public C0043a(IBinder iBinder) {
                this.f796b = iBinder;
            }

            @Override // b.b.g.g.d
            public List<EnergyBean> S() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.byd.carsettings.db.IEnergyAidlInterface");
                    if (!this.f796b.transact(2, parcelObtain, parcelObtain2, 0) && a.e0() != null) {
                        return a.e0().S();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createTypedArrayList(EnergyBean.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f796b;
            }

            @Override // b.b.g.g.d
            public void q() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.byd.carsettings.db.IEnergyAidlInterface");
                    if (this.f796b.transact(3, parcelObtain, parcelObtain2, 0) || a.e0() == null) {
                        parcelObtain2.readException();
                    } else {
                        a.e0().q();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // b.b.g.g.d
            public List<EnergyBean> y() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.byd.carsettings.db.IEnergyAidlInterface");
                    if (!this.f796b.transact(1, parcelObtain, parcelObtain2, 0) && a.e0() != null) {
                        return a.e0().y();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createTypedArrayList(EnergyBean.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static d d0(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.byd.carsettings.db.IEnergyAidlInterface");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof d)) ? new C0043a(iBinder) : (d) iInterfaceQueryLocalInterface;
        }

        public static d e0() {
            return C0043a.f795a;
        }
    }

    List<EnergyBean> S() throws RemoteException;

    void q() throws RemoteException;

    List<EnergyBean> y() throws RemoteException;
}
