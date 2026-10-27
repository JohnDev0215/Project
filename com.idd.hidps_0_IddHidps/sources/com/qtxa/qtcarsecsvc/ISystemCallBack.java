package com.qtxa.qtcarsecsvc;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* JADX INFO: loaded from: classes.dex */
public interface ISystemCallBack extends IInterface {

    public static abstract class a extends Binder implements ISystemCallBack {

        /* JADX INFO: renamed from: com.qtxa.qtcarsecsvc.ISystemCallBack$a$a, reason: collision with other inner class name */
        private static class C0017a implements ISystemCallBack {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public static ISystemCallBack f98b;

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            private IBinder f99a;

            C0017a(IBinder iBinder) {
                this.f99a = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f99a;
            }

            @Override // com.qtxa.qtcarsecsvc.ISystemCallBack
            public String call(String str) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.qtxa.qtcarsecsvc.ISystemCallBack");
                    parcelObtain.writeString(str);
                    if (!this.f99a.transact(1, parcelObtain, parcelObtain2, 0) && a.w() != null) {
                        return a.w().call(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static ISystemCallBack f(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.qtxa.qtcarsecsvc.ISystemCallBack");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof ISystemCallBack)) ? new C0017a(iBinder) : (ISystemCallBack) iInterfaceQueryLocalInterface;
        }

        public static ISystemCallBack w() {
            return C0017a.f98b;
        }
    }

    String call(String str);
}
