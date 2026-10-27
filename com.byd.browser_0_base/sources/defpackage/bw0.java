package defpackage;

import J.N;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.view.Surface;
import org.chromium.base.UnguessableToken;
import org.chromium.content.common.SurfaceWrapper;

/* JADX INFO: compiled from: IGpuProcessCallback.java */
/* JADX INFO: loaded from: classes3.dex */
public interface bw0 extends IInterface {
    void d(UnguessableToken unguessableToken, Surface surface);

    SurfaceWrapper t(int i);

    /* JADX INFO: compiled from: IGpuProcessCallback.java */
    public static abstract class a extends Binder implements bw0 {
        public static final /* synthetic */ int e = 0;

        /* JADX INFO: renamed from: bw0$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: IGpuProcessCallback.java */
        public static class C0032a implements bw0 {
            public final IBinder e;

            public C0032a(IBinder iBinder) {
                this.e = iBinder;
            }

            @Override // android.os.IInterface
            public final IBinder asBinder() {
                return this.e;
            }

            @Override // defpackage.bw0
            public final void d(UnguessableToken unguessableToken, Surface surface) {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("org.chromium.content.common.IGpuProcessCallback");
                    if (unguessableToken != null) {
                        parcelObtain.writeInt(1);
                        unguessableToken.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (surface != null) {
                        parcelObtain.writeInt(1);
                        surface.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    this.e.transact(1, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // defpackage.bw0
            public final SurfaceWrapper t(int i) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("org.chromium.content.common.IGpuProcessCallback");
                    parcelObtain.writeInt(i);
                    this.e.transact(2, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? SurfaceWrapper.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public a() {
            attachInterface(this, "org.chromium.content.common.IGpuProcessCallback");
        }

        @Override // android.os.Binder
        public final boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface("org.chromium.content.common.IGpuProcessCallback");
            }
            if (i == 1598968902) {
                parcel2.writeString("org.chromium.content.common.IGpuProcessCallback");
                return true;
            }
            if (i == 1) {
                N.M$O7xE3y(parcel.readInt() != 0 ? UnguessableToken.CREATOR.createFromParcel(parcel) : null, (Surface) (parcel.readInt() != 0 ? Surface.CREATOR.createFromParcel(parcel) : null));
            } else {
                if (i != 2) {
                    return super.onTransact(i, parcel, parcel2, i2);
                }
                SurfaceWrapper surfaceWrapperT = ((ht0) this).t(parcel.readInt());
                parcel2.writeNoException();
                if (surfaceWrapperT != null) {
                    parcel2.writeInt(1);
                    surfaceWrapperT.writeToParcel(parcel2, 1);
                } else {
                    parcel2.writeInt(0);
                }
            }
            return true;
        }

        @Override // android.os.IInterface
        public final IBinder asBinder() {
            return this;
        }
    }
}
