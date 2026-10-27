package android.support.v4.media.session;

import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import i.e;
import i.f;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class PlaybackStateCompat implements Parcelable {
    public static final Parcelable.Creator<PlaybackStateCompat> CREATOR = new a();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f199d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final long f200e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final long f201f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final float f202g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final long f203h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f204i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final CharSequence f205j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final long f206k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public List<CustomAction> f207l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final long f208m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final Bundle f209n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public Object f210o;

    public static class a implements Parcelable.Creator<PlaybackStateCompat> {
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public PlaybackStateCompat createFromParcel(Parcel parcel) {
            return new PlaybackStateCompat(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public PlaybackStateCompat[] newArray(int i8) {
            return new PlaybackStateCompat[i8];
        }
    }

    public PlaybackStateCompat(int i8, long j8, long j9, float f8, long j10, int i9, CharSequence charSequence, long j11, List<CustomAction> list, long j12, Bundle bundle) {
        this.f199d = i8;
        this.f200e = j8;
        this.f201f = j9;
        this.f202g = f8;
        this.f203h = j10;
        this.f204i = i9;
        this.f205j = charSequence;
        this.f206k = j11;
        this.f207l = new ArrayList(list);
        this.f208m = j12;
        this.f209n = bundle;
    }

    public static PlaybackStateCompat f(Object obj) {
        ArrayList arrayList;
        if (obj == null || Build.VERSION.SDK_INT < 21) {
            return null;
        }
        List<Object> listD = e.d(obj);
        if (listD != null) {
            ArrayList arrayList2 = new ArrayList(listD.size());
            Iterator<Object> it = listD.iterator();
            while (it.hasNext()) {
                arrayList2.add(CustomAction.f(it.next()));
            }
            arrayList = arrayList2;
        } else {
            arrayList = null;
        }
        PlaybackStateCompat playbackStateCompat = new PlaybackStateCompat(e.i(obj), e.h(obj), e.c(obj), e.g(obj), e.a(obj), 0, e.e(obj), e.f(obj), arrayList, e.b(obj), Build.VERSION.SDK_INT >= 22 ? f.a(obj) : null);
        playbackStateCompat.f210o = obj;
        return playbackStateCompat;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String toString() {
        return "PlaybackState {state=" + this.f199d + ", position=" + this.f200e + ", buffered position=" + this.f201f + ", speed=" + this.f202g + ", updated=" + this.f206k + ", actions=" + this.f203h + ", error code=" + this.f204i + ", error message=" + this.f205j + ", custom actions=" + this.f207l + ", active item id=" + this.f208m + "}";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i8) {
        parcel.writeInt(this.f199d);
        parcel.writeLong(this.f200e);
        parcel.writeFloat(this.f202g);
        parcel.writeLong(this.f206k);
        parcel.writeLong(this.f201f);
        parcel.writeLong(this.f203h);
        TextUtils.writeToParcel(this.f205j, parcel, i8);
        parcel.writeTypedList(this.f207l);
        parcel.writeLong(this.f208m);
        parcel.writeBundle(this.f209n);
        parcel.writeInt(this.f204i);
    }

    public static final class CustomAction implements Parcelable {
        public static final Parcelable.Creator<CustomAction> CREATOR = new a();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final String f211d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final CharSequence f212e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final int f213f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public final Bundle f214g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public Object f215h;

        public static class a implements Parcelable.Creator<CustomAction> {
            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public CustomAction createFromParcel(Parcel parcel) {
                return new CustomAction(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public CustomAction[] newArray(int i8) {
                return new CustomAction[i8];
            }
        }

        public CustomAction(String str, CharSequence charSequence, int i8, Bundle bundle) {
            this.f211d = str;
            this.f212e = charSequence;
            this.f213f = i8;
            this.f214g = bundle;
        }

        public static CustomAction f(Object obj) {
            if (obj == null || Build.VERSION.SDK_INT < 21) {
                return null;
            }
            CustomAction customAction = new CustomAction(e.a.a(obj), e.a.d(obj), e.a.c(obj), e.a.b(obj));
            customAction.f215h = obj;
            return customAction;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public String toString() {
            return "Action:mName='" + ((Object) this.f212e) + ", mIcon=" + this.f213f + ", mExtras=" + this.f214g;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i8) {
            parcel.writeString(this.f211d);
            TextUtils.writeToParcel(this.f212e, parcel, i8);
            parcel.writeInt(this.f213f);
            parcel.writeBundle(this.f214g);
        }

        public CustomAction(Parcel parcel) {
            this.f211d = parcel.readString();
            this.f212e = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            this.f213f = parcel.readInt();
            this.f214g = parcel.readBundle(MediaSessionCompat.class.getClassLoader());
        }
    }

    public PlaybackStateCompat(Parcel parcel) {
        this.f199d = parcel.readInt();
        this.f200e = parcel.readLong();
        this.f202g = parcel.readFloat();
        this.f206k = parcel.readLong();
        this.f201f = parcel.readLong();
        this.f203h = parcel.readLong();
        this.f205j = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.f207l = parcel.createTypedArrayList(CustomAction.CREATOR);
        this.f208m = parcel.readLong();
        this.f209n = parcel.readBundle(MediaSessionCompat.class.getClassLoader());
        this.f204i = parcel.readInt();
    }
}
