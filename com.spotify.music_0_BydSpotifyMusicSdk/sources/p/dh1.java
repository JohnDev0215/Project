package p;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaSessionCompat$QueueItem;
import android.support.v4.media.session.PlaybackStateCompat;
import android.text.TextUtils;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public abstract class dh1 extends Binder implements s21 {
    public final WeakReference b;

    public dh1() {
        attachInterface(this, "android.support.v4.media.session.IMediaControllerCallback");
        this.b = new WeakReference(null);
    }

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        return this;
    }

    @Override // p.s21
    public final void c0(PlaybackStateCompat playbackStateCompat) {
        u4.z(this.b.get());
    }

    @Override // p.s21
    public final void d(int i) {
        u4.z(this.b.get());
    }

    @Override // p.s21
    public final void f(int i) {
        u4.z(this.b.get());
    }

    @Override // android.os.Binder
    /* JADX INFO: renamed from: g0, reason: merged with bridge method [inline-methods] */
    public final boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        if (i >= 1 && i <= 16777215) {
            parcel.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
        }
        if (i == 1598968902) {
            parcel2.writeString("android.support.v4.media.session.IMediaControllerCallback");
            return true;
        }
        WeakReference weakReference = this.b;
        switch (i) {
            case 1:
                parcel.readString();
                u4.z(weakReference.get());
                return true;
            case 2:
                ((fh1) this).l0();
                throw null;
            case 3:
                c0((PlaybackStateCompat) ci1.b(parcel, PlaybackStateCompat.CREATOR));
                return true;
            case 4:
                ((fh1) this).i0((MediaMetadataCompat) ci1.b(parcel, MediaMetadataCompat.CREATOR));
                throw null;
            case 5:
                ((fh1) this).j0(parcel.createTypedArrayList(MediaSessionCompat$QueueItem.CREATOR));
                throw null;
            case 6:
                ((fh1) this).k0((CharSequence) ci1.b(parcel, TextUtils.CHAR_SEQUENCE_CREATOR));
                throw null;
            case 7:
                ((fh1) this).h0((Bundle) ci1.b(parcel, Bundle.CREATOR));
                throw null;
            case 8:
                throw new AssertionError();
            case 9:
                d(parcel.readInt());
                return true;
            case 10:
                parcel.readInt();
                return true;
            case 11:
                parcel.readInt();
                u4.z(weakReference.get());
                return true;
            case 12:
                f(parcel.readInt());
                return true;
            case 13:
                u4.z(weakReference.get());
                return true;
            default:
                return super.onTransact(i, parcel, parcel2, i2);
        }
    }
}
