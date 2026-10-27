package com.vivid.spotify;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* JADX INFO: compiled from: IRequestCallBack.java */
/* JADX INFO: loaded from: classes.dex */
public interface e extends IInterface {

    /* JADX INFO: compiled from: IRequestCallBack.java */
    public static abstract class a extends Binder implements e {

        /* JADX INFO: renamed from: com.vivid.spotify.e$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: IRequestCallBack.java */
        private static class C0059a implements e {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public static e f5309b;

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            private IBinder f5310a;

            C0059a(IBinder iBinder) {
                this.f5310a = iBinder;
            }

            @Override // com.vivid.spotify.e
            public void N(int i7) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.vivid.spotify.IRequestCallBack");
                    parcelObtain.writeInt(i7);
                    if (this.f5310a.transact(2, parcelObtain, parcelObtain2, 0) || a.U() == null) {
                        parcelObtain2.readException();
                    } else {
                        a.U().N(i7);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f5310a;
            }

            @Override // com.vivid.spotify.e
            public void u() {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.vivid.spotify.IRequestCallBack");
                    if (this.f5310a.transact(1, parcelObtain, parcelObtain2, 0) || a.U() == null) {
                        parcelObtain2.readException();
                    } else {
                        a.U().u();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static e T(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.vivid.spotify.IRequestCallBack");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof e)) ? new C0059a(iBinder) : (e) iInterfaceQueryLocalInterface;
        }

        public static e U() {
            return C0059a.f5309b;
        }
    }

    void N(int i7);

    void u();
}
