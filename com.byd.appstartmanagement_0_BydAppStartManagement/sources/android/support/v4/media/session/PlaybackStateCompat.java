package android.support.v4.media.session;

import a.b.a.a.f.e;
import a.b.a.a.f.f;
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
    public final int f131b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final long f132c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final long f133d;
    public final float e;
    public final long f;
    public final int g;
    public final CharSequence h;
    public final long i;
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
        public PlaybackStateCompat[] newArray(int i) {
            return new PlaybackStateCompat[i];
        }
    }

    public PlaybackStateCompat(int i, long j, long j2, float f, long j3, int i2, CharSequence charSequence, long j4, List<CustomAction> list, long j5, Bundle bundle) {
        this.f131b = i;
        this.f132c = j;
        this.f133d = j2;
        this.e = f;
        this.f = j3;
        this.g = i2;
        this.h = charSequence;
        this.i = j4;
        this.j = new ArrayList(list);
        this.k = j5;
        this.l = bundle;
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
        playbackStateCompat.m = obj;
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
        return "PlaybackState {state=" + this.f131b + ", position=" + this.f132c + ", buffered position=" + this.f133d + ", speed=" + this.e + ", updated=" + this.i + ", actions=" + this.f + ", error code=" + this.g + ", error message=" + this.h + ", custom actions=" + this.j + ", active item id=" + this.k + "}";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f131b);
        parcel.writeLong(this.f132c);
        parcel.writeFloat(this.e);
        parcel.writeLong(this.i);
        parcel.writeLong(this.f133d);
        parcel.writeLong(this.f);
        TextUtils.writeToParcel(this.h, parcel, i);
        parcel.writeTypedList(this.j);
        parcel.writeLong(this.k);
        parcel.writeBundle(this.l);
        parcel.writeInt(this.g);
    }

    public static final class CustomAction implements Parcelable {
        public static final Parcelable.Creator<CustomAction> CREATOR = new a();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final String f134b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final CharSequence f135c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final int f136d;
        public final Bundle e;
        public Object f;

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
            this.f134b = str;
            this.f135c = charSequence;
            this.f136d = i;
            this.e = bundle;
        }

        public static CustomAction a(Object obj) {
            if (obj == null || Build.VERSION.SDK_INT < 21) {
                return null;
            }
            CustomAction customAction = new CustomAction(e.a.a(obj), e.a.d(obj), e.a.c(obj), e.a.b(obj));
            customAction.f = obj;
            return customAction;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public String toString() {
            return "Action:mName='" + ((Object) this.f135c) + ", mIcon=" + this.f136d + ", mExtras=" + this.e;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.f134b);
            TextUtils.writeToParcel(this.f135c, parcel, i);
            parcel.writeInt(this.f136d);
            parcel.writeBundle(this.e);
        }

        public CustomAction(Parcel parcel) {
            this.f134b = parcel.readString();
            this.f135c = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            this.f136d = parcel.readInt();
            this.e = parcel.readBundle(MediaSessionCompat.class.getClassLoader());
        }
    }

    public PlaybackStateCompat(Parcel parcel) {
        this.f131b = parcel.readInt();
        this.f132c = parcel.readLong();
        this.e = parcel.readFloat();
        this.i = parcel.readLong();
        this.f133d = parcel.readLong();
        this.f = parcel.readLong();
        this.h = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.j = parcel.createTypedArrayList(CustomAction.CREATOR);
        this.k = parcel.readLong();
        this.l = parcel.readBundle(MediaSessionCompat.class.getClassLoader());
        this.g = parcel.readInt();
    }
}
