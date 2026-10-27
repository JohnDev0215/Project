package p;

import android.os.IBinder;
import android.os.Parcel;
import android.support.v4.media.session.PlaybackStateCompat;
import android.view.KeyEvent;

/* JADX INFO: loaded from: classes.dex */
public final class t21 implements android.support.v4.media.session.b {
    public IBinder b;

    @Override // android.support.v4.media.session.b
    public final void Z(s21 s21Var) {
        Parcel parcelObtain = Parcel.obtain();
        Parcel parcelObtain2 = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
            parcelObtain.writeStrongInterface(s21Var);
            this.b.transact(3, parcelObtain, parcelObtain2, 0);
            parcelObtain2.readException();
        } finally {
            parcelObtain2.recycle();
            parcelObtain.recycle();
        }
    }

    @Override // android.support.v4.media.session.b
    public final PlaybackStateCompat a() {
        Parcel parcelObtain = Parcel.obtain();
        Parcel parcelObtain2 = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
            this.b.transact(28, parcelObtain, parcelObtain2, 0);
            parcelObtain2.readException();
            return (PlaybackStateCompat) he1.a(parcelObtain2, PlaybackStateCompat.CREATOR);
        } finally {
            parcelObtain2.recycle();
            parcelObtain.recycle();
        }
    }

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        return this.b;
    }

    @Override // android.support.v4.media.session.b
    public final boolean f0(KeyEvent keyEvent) {
        Parcel parcelObtain = Parcel.obtain();
        Parcel parcelObtain2 = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
            he1.Z(0, parcelObtain, keyEvent);
            this.b.transact(2, parcelObtain, parcelObtain2, 0);
            parcelObtain2.readException();
            return parcelObtain2.readInt() != 0;
        } finally {
            parcelObtain2.recycle();
            parcelObtain.recycle();
        }
    }
}
