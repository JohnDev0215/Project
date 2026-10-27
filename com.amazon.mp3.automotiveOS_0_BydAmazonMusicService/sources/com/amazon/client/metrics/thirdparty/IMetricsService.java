package com.amazon.client.metrics.thirdparty;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public interface IMetricsService extends IInterface {
    boolean getRecordMetricsSetting() throws RemoteException;

    void record(int i, int i2, String str, String str2, long j, List<DataPointEnvelope> list) throws RemoteException;

    public static abstract class Stub extends Binder implements IMetricsService {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, "com.amazon.client.metrics.thirdparty.IMetricsService");
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface("com.amazon.client.metrics.thirdparty.IMetricsService");
                record(parcel.readInt(), parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readLong(), parcel.createTypedArrayList(DataPointEnvelope.CREATOR));
                return true;
            }
            if (i != 2) {
                if (i == 1598968902) {
                    parcel2.writeString("com.amazon.client.metrics.thirdparty.IMetricsService");
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface("com.amazon.client.metrics.thirdparty.IMetricsService");
            boolean recordMetricsSetting = getRecordMetricsSetting();
            parcel2.writeNoException();
            parcel2.writeInt(recordMetricsSetting ? 1 : 0);
            return true;
        }
    }
}
