package spi;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.byd.amazon.music.param.PlayState;

/* JADX INFO: compiled from: IPlayStateCallBack.java */
/* JADX INFO: loaded from: classes2.dex */
public interface r4 extends IInterface {

    /* JADX INFO: compiled from: IPlayStateCallBack.java */
    public static abstract class a extends Binder implements r4 {
        public static final /* synthetic */ int a = 0;

        /* JADX INFO: renamed from: spi.r4$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: IPlayStateCallBack.java */
        public static class C0020a implements r4 {
            public IBinder a;

            public C0020a(IBinder iBinder) {
                this.a = iBinder;
            }

            @Override // android.os.IInterface
            public final IBinder asBinder() {
                return this.a;
            }

            @Override // spi.r4
            public final void c(PlayState playState) {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.byd.amazon.music.IPlayStateCallBack");
                    if (playState != null) {
                        parcelObtain.writeInt(1);
                        playState.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.a.transact(1, parcelObtain, null, 1)) {
                        int i = a.a;
                    }
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public a() {
            attachInterface(this, "com.byd.amazon.music.IPlayStateCallBack");
        }

        @Override // android.os.IInterface
        public final IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public final boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            if (i == 1) {
                parcel.enforceInterface("com.byd.amazon.music.IPlayStateCallBack");
                c(parcel.readInt() != 0 ? PlayState.CREATOR.createFromParcel(parcel) : null);
                return true;
            }
            if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel2.writeString("com.byd.amazon.music.IPlayStateCallBack");
            return true;
        }
    }

    void c(PlayState playState);
}
