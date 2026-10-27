package spi;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.byd.amazon.music.bean.MusicItem;
import java.util.List;

/* JADX INFO: compiled from: IMusicItemsCallBack.java */
/* JADX INFO: loaded from: classes2.dex */
public interface p4 extends IInterface {

    /* JADX INFO: compiled from: IMusicItemsCallBack.java */
    public static abstract class a extends Binder implements p4 {
        public static final /* synthetic */ int a = 0;

        /* JADX INFO: renamed from: spi.p4$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: IMusicItemsCallBack.java */
        public static class C0019a implements p4 {
            public IBinder a;

            public C0019a(IBinder iBinder) {
                this.a = iBinder;
            }

            @Override // spi.p4
            public final void a(String str, List<MusicItem> list) {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.byd.amazon.music.IMusicItemsCallBack");
                    parcelObtain.writeString(str);
                    parcelObtain.writeTypedList(list);
                    if (!this.a.transact(1, parcelObtain, null, 1)) {
                        int i = a.a;
                    }
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IInterface
            public final IBinder asBinder() {
                return this.a;
            }

            @Override // spi.p4
            public final void b(String str, String str2) {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.byd.amazon.music.IMusicItemsCallBack");
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.a.transact(2, parcelObtain, null, 1)) {
                        int i = a.a;
                    }
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public a() {
            attachInterface(this, "com.byd.amazon.music.IMusicItemsCallBack");
        }

        @Override // android.os.IInterface
        public final IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public final boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            if (i == 1) {
                parcel.enforceInterface("com.byd.amazon.music.IMusicItemsCallBack");
                a(parcel.readString(), parcel.createTypedArrayList(MusicItem.CREATOR));
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface("com.byd.amazon.music.IMusicItemsCallBack");
                b(parcel.readString(), parcel.readString());
                return true;
            }
            if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel2.writeString("com.byd.amazon.music.IMusicItemsCallBack");
            return true;
        }
    }

    void a(String str, List<MusicItem> list);

    void b(String str, String str2);
}
