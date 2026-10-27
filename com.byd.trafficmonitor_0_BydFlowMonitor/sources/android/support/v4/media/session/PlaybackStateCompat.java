package android.support.v4.media.session;

import a.d.a.b.f.e;
import a.d.a.b.f.f;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class PlaybackStateCompat implements Parcelable {
    public static final Parcelable.Creator<PlaybackStateCompat> CREATOR = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f164b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final long f165c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final long f166d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final float f167e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final long f168f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f169g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final CharSequence f170h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final long f171i;
    public List<CustomAction> j;
    public final long k;
    public final Bundle l;
    public Object m;

    public static class a implements Parcelable.Creator<PlaybackStateCompat> {
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public PlaybackStateCompat createFromParcel(Parcel parcel) {
            return new PlaybackStateCompat(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public PlaybackStateCompat[] newArray(int i2) {
            return new PlaybackStateCompat[i2];
        }
    }

    public PlaybackStateCompat(int i2, long j, long j2, float f2, long j3, int i3, CharSequence charSequence, long j4, List<CustomAction> list, long j5, Bundle bundle) {
        this.f164b = i2;
        this.f165c = j;
        this.f166d = j2;
        this.f167e = f2;
        this.f168f = j3;
        this.f169g = i3;
        this.f170h = charSequence;
        this.f171i = j4;
        this.j = new ArrayList(list);
        this.k = j5;
        this.l = bundle;
    }

    public static PlaybackStateCompat j(Object obj) {
        ArrayList arrayList;
        if (obj == null || Build.VERSION.SDK_INT < 21) {
            return null;
        }
        List<Object> listD = e.d(obj);
        if (listD != null) {
            ArrayList arrayList2 = new ArrayList(listD.size());
            Iterator<Object> it = listD.iterator();
            while (it.hasNext()) {
                arrayList2.add(CustomAction.j(it.next()));
            }
            arrayList = arrayList2;
        } else {
            arrayList = null;
        }
        PlaybackStateCompat playbackStateCompat = new PlaybackStateCompat(e.i(obj), e.h(obj), e.c(obj), e.g(obj), e.a(obj), 0, e.e(obj), e.f(obj), arrayList, e.b(obj), Build.VERSION.SDK_INT >= 22 ? f.a(obj) : null);
        playbackStateCompat.m = obj;
        return playbackStateCompat;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String toString() {
        return "PlaybackState {state=" + this.f164b + ", position=" + this.f165c + ", buffered position=" + this.f166d + ", speed=" + this.f167e + ", updated=" + this.f171i + ", actions=" + this.f168f + ", error code=" + this.f169g + ", error message=" + this.f170h + ", custom actions=" + this.j + ", active item id=" + this.k + "}";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeInt(this.f164b);
        parcel.writeLong(this.f165c);
        parcel.writeFloat(this.f167e);
        parcel.writeLong(this.f171i);
        parcel.writeLong(this.f166d);
        parcel.writeLong(this.f168f);
        TextUtils.writeToParcel(this.f170h, parcel, i2);
        parcel.writeTypedList(this.j);
        parcel.writeLong(this.k);
        parcel.writeBundle(this.l);
        parcel.writeInt(this.f169g);
    }

    public static final class CustomAction implements Parcelable {
        public static final Parcelable.Creator<CustomAction> CREATOR = new a();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final String f172b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final CharSequence f173c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final int f174d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final Bundle f175e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public Object f176f;

        public static class a implements Parcelable.Creator<CustomAction> {
            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public CustomAction createFromParcel(Parcel parcel) {
                return new CustomAction(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public CustomAction[] newArray(int i2) {
                return new CustomAction[i2];
            }
        }

        public CustomAction(String str, CharSequence charSequence, int i2, Bundle bundle) {
            this.f172b = str;
            this.f173c = charSequence;
            this.f174d = i2;
            this.f175e = bundle;
        }

        public static CustomAction j(Object obj) {
            if (obj == null || Build.VERSION.SDK_INT < 21) {
                return null;
            }
            CustomAction customAction = new CustomAction(e.a.a(obj), e.a.d(obj), e.a.c(obj), e.a.b(obj));
            customAction.f176f = obj;
            return customAction;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public String toString() {
            return "Action:mName='" + ((Object) this.f173c) + ", mIcon=" + this.f174d + ", mExtras=" + this.f175e;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            parcel.writeString(this.f172b);
            TextUtils.writeToParcel(this.f173c, parcel, i2);
            parcel.writeInt(this.f174d);
            parcel.writeBundle(this.f175e);
        }

        public CustomAction(Parcel parcel) {
            this.f172b = parcel.readString();
            this.f173c = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            this.f174d = parcel.readInt();
            this.f175e = parcel.readBundle(MediaSessionCompat.class.getClassLoader());
        }
    }

    public PlaybackStateCompat(Parcel parcel) {
        this.f164b = parcel.readInt();
        this.f165c = parcel.readLong();
        this.f167e = parcel.readFloat();
        this.f171i = parcel.readLong();
        this.f166d = parcel.readLong();
        this.f168f = parcel.readLong();
        this.f170h = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.j = parcel.createTypedArrayList(CustomAction.CREATOR);
        this.k = parcel.readLong();
        this.l = parcel.readBundle(MediaSessionCompat.class.getClassLoader());
        this.f169g = parcel.readInt();
    }
}
