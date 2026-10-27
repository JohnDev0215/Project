package android.support.v4.media.session;

import a.d.a.a.f.e;
import a.d.a.a.f.f;
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

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f154a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f155b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final long f156c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final float f157d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final long f158e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f159f;
    public final CharSequence g;
    public final long h;
    public List<CustomAction> i;
    public final long j;
    public final Bundle k;
    public Object l;

    public static class a implements Parcelable.Creator<PlaybackStateCompat> {
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public PlaybackStateCompat createFromParcel(Parcel parcel) {
            return new PlaybackStateCompat(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public PlaybackStateCompat[] newArray(int i) {
            return new PlaybackStateCompat[i];
        }
    }

    public PlaybackStateCompat(int i, long j, long j2, float f2, long j3, int i2, CharSequence charSequence, long j4, List<CustomAction> list, long j5, Bundle bundle) {
        this.f154a = i;
        this.f155b = j;
        this.f156c = j2;
        this.f157d = f2;
        this.f158e = j3;
        this.f159f = i2;
        this.g = charSequence;
        this.h = j4;
        this.i = new ArrayList(list);
        this.j = j5;
        this.k = bundle;
    }

    public static PlaybackStateCompat a(Object obj) {
        ArrayList arrayList;
        if (obj == null || Build.VERSION.SDK_INT < 21) {
            return null;
        }
        List<Object> listD = e.d(obj);
        if (listD != null) {
            ArrayList arrayList2 = new ArrayList(listD.size());
            Iterator<Object> it = listD.iterator();
            while (it.hasNext()) {
                arrayList2.add(CustomAction.a(it.next()));
            }
            arrayList = arrayList2;
        } else {
            arrayList = null;
        }
        PlaybackStateCompat playbackStateCompat = new PlaybackStateCompat(e.i(obj), e.h(obj), e.c(obj), e.g(obj), e.a(obj), 0, e.e(obj), e.f(obj), arrayList, e.b(obj), Build.VERSION.SDK_INT >= 22 ? f.a(obj) : null);
        playbackStateCompat.l = obj;
        return playbackStateCompat;
    }

    public static int b(long j) {
        if (j == 4) {
            return 126;
        }
        if (j == 2) {
            return 127;
        }
        if (j == 32) {
            return 87;
        }
        if (j == 16) {
            return 88;
        }
        if (j == 1) {
            return 86;
        }
        if (j == 64) {
            return 90;
        }
        if (j == 8) {
            return 89;
        }
        return j == 512 ? 85 : 0;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String toString() {
        return "PlaybackState {state=" + this.f154a + ", position=" + this.f155b + ", buffered position=" + this.f156c + ", speed=" + this.f157d + ", updated=" + this.h + ", actions=" + this.f158e + ", error code=" + this.f159f + ", error message=" + this.g + ", custom actions=" + this.i + ", active item id=" + this.j + "}";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f154a);
        parcel.writeLong(this.f155b);
        parcel.writeFloat(this.f157d);
        parcel.writeLong(this.h);
        parcel.writeLong(this.f156c);
        parcel.writeLong(this.f158e);
        TextUtils.writeToParcel(this.g, parcel, i);
        parcel.writeTypedList(this.i);
        parcel.writeLong(this.j);
        parcel.writeBundle(this.k);
        parcel.writeInt(this.f159f);
    }

    public static final class CustomAction implements Parcelable {
        public static final Parcelable.Creator<CustomAction> CREATOR = new a();

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final String f160a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final CharSequence f161b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final int f162c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final Bundle f163d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public Object f164e;

        public static class a implements Parcelable.Creator<CustomAction> {
            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public CustomAction createFromParcel(Parcel parcel) {
                return new CustomAction(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public CustomAction[] newArray(int i) {
                return new CustomAction[i];
            }
        }

        public CustomAction(String str, CharSequence charSequence, int i, Bundle bundle) {
            this.f160a = str;
            this.f161b = charSequence;
            this.f162c = i;
            this.f163d = bundle;
        }

        public static CustomAction a(Object obj) {
            if (obj == null || Build.VERSION.SDK_INT < 21) {
                return null;
            }
            CustomAction customAction = new CustomAction(e.a.a(obj), e.a.d(obj), e.a.c(obj), e.a.b(obj));
            customAction.f164e = obj;
            return customAction;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public String toString() {
            return "Action:mName='" + ((Object) this.f161b) + ", mIcon=" + this.f162c + ", mExtras=" + this.f163d;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.f160a);
            TextUtils.writeToParcel(this.f161b, parcel, i);
            parcel.writeInt(this.f162c);
            parcel.writeBundle(this.f163d);
        }

        public CustomAction(Parcel parcel) {
            this.f160a = parcel.readString();
            this.f161b = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            this.f162c = parcel.readInt();
            this.f163d = parcel.readBundle(MediaSessionCompat.class.getClassLoader());
        }
    }

    public PlaybackStateCompat(Parcel parcel) {
        this.f154a = parcel.readInt();
        this.f155b = parcel.readLong();
        this.f157d = parcel.readFloat();
        this.h = parcel.readLong();
        this.f156c = parcel.readLong();
        this.f158e = parcel.readLong();
        this.g = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.i = parcel.createTypedArrayList(CustomAction.CREATOR);
        this.j = parcel.readLong();
        this.k = parcel.readBundle(MediaSessionCompat.class.getClassLoader());
        this.f159f = parcel.readInt();
    }
}
