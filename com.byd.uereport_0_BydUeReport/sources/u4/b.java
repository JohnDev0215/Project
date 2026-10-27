package u4;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.byd.trafficmonitor.model.TrafficInfo;
import java.util.List;

/* JADX INFO: compiled from: ITrafficAidlInterface.java */
/* JADX INFO: loaded from: classes2.dex */
public interface b extends IInterface {

    /* JADX INFO: compiled from: ITrafficAidlInterface.java */
    public static abstract class a extends Binder implements b {
        public a() {
            attachInterface(this, "com.byd.trafficmonitor.ITrafficAidlInterface");
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i8, Parcel parcel, Parcel parcel2, int i9) throws RemoteException {
            if (i8 == 1) {
                parcel.enforceInterface("com.byd.trafficmonitor.ITrafficAidlInterface");
                List<TrafficInfo> listT = T();
                parcel2.writeNoException();
                parcel2.writeTypedList(listT);
                return true;
            }
            if (i8 != 2) {
                if (i8 != 1598968902) {
                    return super.onTransact(i8, parcel, parcel2, i9);
                }
                parcel2.writeString("com.byd.trafficmonitor.ITrafficAidlInterface");
                return true;
            }
            parcel.enforceInterface("com.byd.trafficmonitor.ITrafficAidlInterface");
            S(parcel.readInt(), parcel.readInt());
            parcel2.writeNoException();
            return true;
        }
    }

    void S(int i8, int i9) throws RemoteException;

    List<TrafficInfo> T() throws RemoteException;
}
