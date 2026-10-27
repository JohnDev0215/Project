package android.support.v4.media.session;

import android.annotation.SuppressLint;
import android.media.session.PlaybackState;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"BanParcelableUsage"})
public final class PlaybackStateCompat implements Parcelable {
    public static final Parcelable.Creator<PlaybackStateCompat> CREATOR = new a();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final int f264e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    final long f265g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    final long f266h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    final float f267i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    final long f268j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    final int f269k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    final CharSequence f270l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    final long f271m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    List<CustomAction> f272n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    final long f273o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    final Bundle f274p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private PlaybackState f275q;

    class a implements Parcelable.Creator<PlaybackStateCompat> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public PlaybackStateCompat createFromParcel(Parcel parcel) {
            return new PlaybackStateCompat(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public PlaybackStateCompat[] newArray(int i7) {
            return new PlaybackStateCompat[i7];
        }
    }

    PlaybackStateCompat(int i7, long j7, long j8, float f7, long j9, int i8, CharSequence charSequence, long j10, List<CustomAction> list, long j11, Bundle bundle) {
        this.f264e = i7;
        this.f265g = j7;
        this.f266h = j8;
        this.f267i = f7;
        this.f268j = j9;
        this.f269k = i8;
        this.f270l = charSequence;
        this.f271m = j10;
        this.f272n = new ArrayList(list);
        this.f273o = j11;
        this.f274p = bundle;
    }

    public static PlaybackStateCompat b(Object obj) {
        ArrayList arrayList = null;
        if (obj == null) {
            return null;
        }
        PlaybackState playbackState = (PlaybackState) obj;
        List<PlaybackState.CustomAction> customActions = playbackState.getCustomActions();
        if (customActions != null) {
            arrayList = new ArrayList(customActions.size());
            Iterator<PlaybackState.CustomAction> it = customActions.iterator();
            while (it.hasNext()) {
                arrayList.add(CustomAction.b(it.next()));
            }
        }
        Bundle extras = playbackState.getExtras();
        MediaSessionCompat.a(extras);
        PlaybackStateCompat playbackStateCompat = new PlaybackStateCompat(playbackState.getState(), playbackState.getPosition(), playbackState.getBufferedPosition(), playbackState.getPlaybackSpeed(), playbackState.getActions(), 0, playbackState.getErrorMessage(), playbackState.getLastPositionUpdateTime(), arrayList, playbackState.getActiveQueueItemId(), extras);
        playbackStateCompat.f275q = playbackState;
        return playbackStateCompat;
    }

    public long c() {
        return this.f268j;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public long h() {
        return this.f273o;
    }

    public List<CustomAction> j() {
        return this.f272n;
    }

    public int k() {
        return this.f269k;
    }

    public CharSequence m() {
        return this.f270l;
    }

    public Bundle p() {
        return this.f274p;
    }

    public long q() {
        return this.f271m;
    }

    public float r() {
        return this.f267i;
    }

    public long s() {
        return this.f265g;
    }

    public int t() {
        return this.f264e;
    }

    public String toString() {
        return "PlaybackState {state=" + this.f264e + ", position=" + this.f265g + ", buffered position=" + this.f266h + ", speed=" + this.f267i + ", updated=" + this.f271m + ", actions=" + this.f268j + ", error code=" + this.f269k + ", error message=" + this.f270l + ", custom actions=" + this.f272n + ", active item id=" + this.f273o + "}";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i7) {
        parcel.writeInt(this.f264e);
        parcel.writeLong(this.f265g);
        parcel.writeFloat(this.f267i);
        parcel.writeLong(this.f271m);
        parcel.writeLong(this.f266h);
        parcel.writeLong(this.f268j);
        TextUtils.writeToParcel(this.f270l, parcel, i7);
        parcel.writeTypedList(this.f272n);
        parcel.writeLong(this.f273o);
        parcel.writeBundle(this.f274p);
        parcel.writeInt(this.f269k);
    }

    public static final class CustomAction implements Parcelable {
        public static final Parcelable.Creator<CustomAction> CREATOR = new a();

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final String f276e;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private final CharSequence f277g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        private final int f278h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        private final Bundle f279i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        private PlaybackState.CustomAction f280j;

        class a implements Parcelable.Creator<CustomAction> {
            a() {
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public CustomAction createFromParcel(Parcel parcel) {
                return new CustomAction(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public CustomAction[] newArray(int i7) {
                return new CustomAction[i7];
            }
        }

        CustomAction(String str, CharSequence charSequence, int i7, Bundle bundle) {
            this.f276e = str;
            this.f277g = charSequence;
            this.f278h = i7;
            this.f279i = bundle;
        }

        public static CustomAction b(Object obj) {
            if (obj == null) {
                return null;
            }
            PlaybackState.CustomAction customAction = (PlaybackState.CustomAction) obj;
            Bundle extras = customAction.getExtras();
            MediaSessionCompat.a(extras);
            CustomAction customAction2 = new CustomAction(customAction.getAction(), customAction.getName(), customAction.getIcon(), extras);
            customAction2.f280j = customAction;
            return customAction2;
        }

        public String c() {
            return this.f276e;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public int h() {
            return this.f278h;
        }

        public CharSequence j() {
            return this.f277g;
        }

        public String toString() {
            return "Action:mName='" + ((Object) this.f277g) + ", mIcon=" + this.f278h + ", mExtras=" + this.f279i;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i7) {
            parcel.writeString(this.f276e);
            TextUtils.writeToParcel(this.f277g, parcel, i7);
            parcel.writeInt(this.f278h);
            parcel.writeBundle(this.f279i);
        }

        CustomAction(Parcel parcel) {
            this.f276e = parcel.readString();
            this.f277g = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            this.f278h = parcel.readInt();
            this.f279i = parcel.readBundle(MediaSessionCompat.class.getClassLoader());
        }
    }

    PlaybackStateCompat(Parcel parcel) {
        this.f264e = parcel.readInt();
        this.f265g = parcel.readLong();
        this.f267i = parcel.readFloat();
        this.f271m = parcel.readLong();
        this.f266h = parcel.readLong();
        this.f268j = parcel.readLong();
        this.f270l = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.f272n = parcel.createTypedArrayList(CustomAction.CREATOR);
        this.f273o = parcel.readLong();
        this.f274p = parcel.readBundle(MediaSessionCompat.class.getClassLoader());
        this.f269k = parcel.readInt();
    }
}
