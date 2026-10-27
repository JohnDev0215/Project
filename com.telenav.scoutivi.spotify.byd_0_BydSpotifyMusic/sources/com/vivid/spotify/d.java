package com.vivid.spotify;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* JADX INFO: compiled from: IMediaInfoControllerCallback.java */
/* JADX INFO: loaded from: classes.dex */
public interface d extends IInterface {

    /* JADX INFO: compiled from: IMediaInfoControllerCallback.java */
    public static abstract class a extends Binder implements d {

        /* JADX INFO: renamed from: com.vivid.spotify.d$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: IMediaInfoControllerCallback.java */
        private static class C0057a implements d {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public static d f5251b;

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            private IBinder f5252a;

            C0057a(IBinder iBinder) {
                this.f5252a = iBinder;
            }

            @Override // com.vivid.spotify.d
            public void D(Bundle bundle) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.vivid.spotify.IMediaInfoControllerCallback");
                    if (bundle != null) {
                        parcelObtain.writeInt(1);
                        bundle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.f5252a.transact(1, parcelObtain, parcelObtain2, 0) || a.U() == null) {
                        parcelObtain2.readException();
                    } else {
                        a.U().D(bundle);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.vivid.spotify.d
            public void P(String str, String str2, String str3, long j7) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.vivid.spotify.IMediaInfoControllerCallback");
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeString(str3);
                    parcelObtain.writeLong(j7);
                    if (this.f5252a.transact(2, parcelObtain, parcelObtain2, 0) || a.U() == null) {
                        parcelObtain2.readException();
                    } else {
                        a.U().P(str, str2, str3, j7);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f5252a;
            }

            @Override // com.vivid.spotify.d
            public void f(long j7) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.vivid.spotify.IMediaInfoControllerCallback");
                    parcelObtain.writeLong(j7);
                    if (this.f5252a.transact(3, parcelObtain, parcelObtain2, 0) || a.U() == null) {
                        parcelObtain2.readException();
                    } else {
                        a.U().f(j7);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static d T(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.vivid.spotify.IMediaInfoControllerCallback");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof d)) ? new C0057a(iBinder) : (d) iInterfaceQueryLocalInterface;
        }

        public static d U() {
            return C0057a.f5251b;
        }
    }

    void D(Bundle bundle);

    void P(String str, String str2, String str3, long j7);

    void f(long j7);
}
