package e2;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import d5.C1077F;
import h2.AbstractC1343A;
import java.util.List;

/* JADX INFO: renamed from: e2.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class BinderC1198f extends Binder {
    public static final int d;
    public final d5.I c;

    static {
        d = AbstractC1343A.f15195a >= 30 ? IBinder.getSuggestedMaxIpcSizeBytes() : 65536;
    }

    public BinderC1198f(List list) {
        this.c = d5.I.w(list);
    }

    public static d5.I a(IBinder iBinder) {
        int i7;
        if (iBinder instanceof BinderC1198f) {
            return ((BinderC1198f) iBinder).c;
        }
        C1077F c1077fU = d5.I.u();
        int i10 = 1;
        int i11 = 0;
        while (i10 != 0) {
            Parcel parcelObtain = Parcel.obtain();
            Parcel parcelObtain2 = Parcel.obtain();
            try {
                parcelObtain.writeInt(i11);
                try {
                    iBinder.transact(1, parcelObtain, parcelObtain2, 0);
                    while (true) {
                        i7 = parcelObtain2.readInt();
                        if (i7 == 1) {
                            Bundle bundle = parcelObtain2.readBundle();
                            bundle.getClass();
                            c1077fU.a(bundle);
                            i11++;
                        }
                    }
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    i10 = i7;
                } catch (RemoteException e10) {
                    throw new RuntimeException(e10);
                }
            } catch (Throwable th) {
                parcelObtain2.recycle();
                parcelObtain.recycle();
                throw th;
            }
        }
        return c1077fU.h();
    }

    @Override // android.os.Binder
    public final boolean onTransact(int i7, Parcel parcel, Parcel parcel2, int i10) {
        if (i7 != 1) {
            return super.onTransact(i7, parcel, parcel2, i10);
        }
        if (parcel2 == null) {
            return false;
        }
        d5.I i11 = this.c;
        int size = i11.size();
        int i12 = parcel.readInt();
        while (i12 < size && parcel2.dataSize() < d) {
            parcel2.writeInt(1);
            parcel2.writeBundle((Bundle) i11.get(i12));
            i12++;
        }
        parcel2.writeInt(i12 < size ? 2 : 0);
        return true;
    }
}
