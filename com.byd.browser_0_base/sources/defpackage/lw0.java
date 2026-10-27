package defpackage;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* JADX INFO: compiled from: ISystemSettings.java */
/* JADX INFO: loaded from: classes.dex */
public interface lw0 extends IInterface {

    /* JADX INFO: compiled from: ISystemSettings.java */
    public static abstract class a extends Binder implements lw0 {
        public static final /* synthetic */ int e = 0;

        /* JADX INFO: renamed from: lw0$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: ISystemSettings.java */
        public static class C0065a implements lw0 {
            public final IBinder e;

            public C0065a(IBinder iBinder) {
                this.e = iBinder;
            }

            @Override // android.os.IInterface
            public final IBinder asBinder() {
                return this.e;
            }

            @Override // defpackage.lw0
            public final void h() {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.byd.systemsettings.ISystemSettings");
                    if (!this.e.transact(4, parcelObtain, parcelObtain2, 0)) {
                        int i = a.e;
                    }
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }
    }

    void h();
}
