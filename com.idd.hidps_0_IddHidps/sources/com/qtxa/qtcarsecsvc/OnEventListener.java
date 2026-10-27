package com.qtxa.qtcarsecsvc;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* JADX INFO: loaded from: classes.dex */
public interface OnEventListener extends IInterface {

    public static abstract class a extends Binder implements OnEventListener {

        /* JADX INFO: renamed from: com.qtxa.qtcarsecsvc.OnEventListener$a$a, reason: collision with other inner class name */
        private static class C0018a implements OnEventListener {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public static OnEventListener f100b;

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            private IBinder f101a;

            C0018a(IBinder iBinder) {
                this.f101a = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f101a;
            }

            @Override // com.qtxa.qtcarsecsvc.OnEventListener
            public String onEvent(String str) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.qtxa.qtcarsecsvc.OnEventListener");
                    parcelObtain.writeString(str);
                    if (!this.f101a.transact(1, parcelObtain, parcelObtain2, 0) && a.w() != null) {
                        return a.w().onEvent(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static OnEventListener f(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.qtxa.qtcarsecsvc.OnEventListener");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof OnEventListener)) ? new C0018a(iBinder) : (OnEventListener) iInterfaceQueryLocalInterface;
        }

        public static OnEventListener w() {
            return C0018a.f100b;
        }
    }

    String onEvent(String str);
}
