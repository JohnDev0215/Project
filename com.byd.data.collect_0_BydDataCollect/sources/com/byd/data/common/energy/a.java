package com.byd.data.common.energy;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import java.util.List;

/* JADX INFO: compiled from: IEnergyCollect.java */
/* JADX INFO: loaded from: classes.dex */
public interface a extends IInterface {

    /* JADX INFO: renamed from: com.byd.data.common.energy.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: IEnergyCollect.java */
    public static abstract class AbstractBinderC0066a extends Binder implements a {
        public AbstractBinderC0066a() {
            attachInterface(this, "com.byd.data.common.energy.IEnergyCollect");
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i3) {
            if (i2 == 1) {
                parcel.enforceInterface("com.byd.data.common.energy.IEnergyCollect");
                List<TravelBean> listH = h(parcel.readLong(), parcel.readLong(), parcel.readInt(), parcel.readInt());
                parcel2.writeNoException();
                parcel2.writeTypedList(listH);
                return true;
            }
            if (i2 == 2) {
                parcel.enforceInterface("com.byd.data.common.energy.IEnergyCollect");
                List<TravelBean> listP = p(parcel.readLong(), parcel.readLong());
                parcel2.writeNoException();
                parcel2.writeTypedList(listP);
                return true;
            }
            if (i2 == 3) {
                parcel.enforceInterface("com.byd.data.common.energy.IEnergyCollect");
                long[] jArrCreateLongArray = parcel.createLongArray();
                g(jArrCreateLongArray);
                parcel2.writeNoException();
                parcel2.writeLongArray(jArrCreateLongArray);
                return true;
            }
            if (i2 != 4) {
                if (i2 != 1598968902) {
                    return super.onTransact(i2, parcel, parcel2, i3);
                }
                parcel2.writeString("com.byd.data.common.energy.IEnergyCollect");
                return true;
            }
            parcel.enforceInterface("com.byd.data.common.energy.IEnergyCollect");
            d();
            parcel2.writeNoException();
            return true;
        }
    }

    void d();

    void g(long[] jArr);

    List<TravelBean> h(long j, long j2, int i2, int i3);

    List<TravelBean> p(long j, long j2);
}
