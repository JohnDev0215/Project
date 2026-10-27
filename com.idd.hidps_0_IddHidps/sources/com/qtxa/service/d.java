package com.qtxa.service;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* JADX INFO: loaded from: classes.dex */
public interface d extends IInterface {

    public static abstract class a extends Binder implements d {

        /* JADX INFO: renamed from: com.qtxa.service.d$a$a, reason: collision with other inner class name */
        private static class C0022a implements d {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public static d f172b;

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            private IBinder f173a;

            C0022a(IBinder iBinder) {
                this.f173a = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f173a;
            }

            @Override // com.qtxa.service.d
            public String i(String str) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.qtxa.service.IQtxaIdpsCommCallback");
                    parcelObtain.writeString(str);
                    if (!this.f173a.transact(1, parcelObtain, parcelObtain2, 0) && a.w() != null) {
                        return a.w().i(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static d f(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.qtxa.service.IQtxaIdpsCommCallback");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof d)) ? new C0022a(iBinder) : (d) iInterfaceQueryLocalInterface;
        }

        public static d w() {
            return C0022a.f172b;
        }
    }

    String i(String str);
}
