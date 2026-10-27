package c.a.d;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.byd.trafficmonitor.model.TrafficInfo;
import java.util.List;

/* JADX INFO: compiled from: ITrafficAidlInterface.java */
/* JADX INFO: loaded from: classes.dex */
public interface k extends IInterface {

    /* JADX INFO: compiled from: ITrafficAidlInterface.java */
    public static abstract class a extends Binder implements k {

        /* JADX INFO: renamed from: c.a.d.k$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: ITrafficAidlInterface.java */
        public static class C0074a implements k {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static k f3570a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public IBinder f3571b;

            public C0074a(IBinder iBinder) {
                this.f3571b = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f3571b;
            }

            @Override // c.a.d.k
            public void m(int i2, int i3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.byd.trafficmonitor.ITrafficAidlInterface");
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    if (this.f3571b.transact(2, parcelObtain, parcelObtain2, 0) || a.L() == null) {
                        parcelObtain2.readException();
                    } else {
                        a.L().m(i2, i3);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // c.a.d.k
            public List<TrafficInfo> n() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.byd.trafficmonitor.ITrafficAidlInterface");
                    if (!this.f3571b.transact(1, parcelObtain, parcelObtain2, 0) && a.L() != null) {
                        return a.L().n();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createTypedArrayList(TrafficInfo.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static k K(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.byd.trafficmonitor.ITrafficAidlInterface");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof k)) ? new C0074a(iBinder) : (k) iInterfaceQueryLocalInterface;
        }

        public static k L() {
            return C0074a.f3570a;
        }
    }

    void m(int i2, int i3) throws RemoteException;

    List<TrafficInfo> n() throws RemoteException;
}
