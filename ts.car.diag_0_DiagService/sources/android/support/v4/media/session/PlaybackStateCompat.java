package android.support.v4.media.session;

import android.annotation.SuppressLint;
import android.media.session.PlaybackState;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.annotation.RestrictTo;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"BanParcelableUsage"})
public final class PlaybackStateCompat implements Parcelable {
    public static final Parcelable.Creator<PlaybackStateCompat> CREATOR = new a();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final int f64c;
    final long d;
    final long e;
    final float f;
    final long g;
    final int h;
    final CharSequence i;
    final long j;
    List<CustomAction> k;
    final long l;
    final Bundle m;

    /* JADX INFO: compiled from: caradapter-bug */
    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public @interface Actions {
    }

    /* JADX INFO: compiled from: caradapter-bug */
    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public @interface MediaKeyAction {
    }

    /* JADX INFO: compiled from: caradapter-bug */
    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public @interface RepeatMode {
    }

    /* JADX INFO: compiled from: caradapter-bug */
    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public @interface ShuffleMode {
    }

    /* JADX INFO: compiled from: caradapter-bug */
    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public @interface State {
    }

    /* JADX INFO: compiled from: caradapter-bug */
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
        public PlaybackStateCompat[] newArray(int i) {
            return new PlaybackStateCompat[i];
        }
    }

    PlaybackStateCompat(int i, long j, long j2, float f, long j3, int i2, CharSequence charSequence, long j4, List<CustomAction> list, long j5, Bundle bundle) {
        this.f64c = i;
        this.d = j;
        this.e = j2;
        this.f = f;
        this.g = j3;
        this.h = i2;
        this.i = charSequence;
        this.j = j4;
        this.k = new ArrayList(list);
        this.l = j5;
        this.m = bundle;
    }

    public static PlaybackStateCompat a(Object obj) {
        ArrayList arrayList;
        int i = Build.VERSION.SDK_INT;
        Bundle extras = null;
        if (obj == null || i < 21) {
            return null;
        }
        PlaybackState playbackState = (PlaybackState) obj;
        List<PlaybackState.CustomAction> customActions = playbackState.getCustomActions();
        if (customActions != null) {
            ArrayList arrayList2 = new ArrayList(customActions.size());
            Iterator<PlaybackState.CustomAction> it = customActions.iterator();
            while (it.hasNext()) {
                arrayList2.add(CustomAction.a(it.next()));
            }
            arrayList = arrayList2;
        } else {
            arrayList = null;
        }
        if (i >= 22) {
            extras = playbackState.getExtras();
            MediaSessionCompat.a(extras);
        }
        return new PlaybackStateCompat(playbackState.getState(), playbackState.getPosition(), playbackState.getBufferedPosition(), playbackState.getPlaybackSpeed(), playbackState.getActions(), 0, playbackState.getErrorMessage(), playbackState.getLastPositionUpdateTime(), arrayList, playbackState.getActiveQueueItemId(), extras);
    }

    public long b() {
        return this.g;
    }

    public int c() {
        return this.f64c;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String toString() {
        return "PlaybackState {state=" + this.f64c + ", position=" + this.d + ", buffered position=" + this.e + ", speed=" + this.f + ", updated=" + this.j + ", actions=" + this.g + ", error code=" + this.h + ", error message=" + this.i + ", custom actions=" + this.k + ", active item id=" + this.l + "}";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f64c);
        parcel.writeLong(this.d);
        parcel.writeFloat(this.f);
        parcel.writeLong(this.j);
        parcel.writeLong(this.e);
        parcel.writeLong(this.g);
        TextUtils.writeToParcel(this.i, parcel, i);
        parcel.writeTypedList(this.k);
        parcel.writeLong(this.l);
        parcel.writeBundle(this.m);
        parcel.writeInt(this.h);
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public static final class CustomAction implements Parcelable {
        public static final Parcelable.Creator<CustomAction> CREATOR = new a();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final String f65c;
        private final CharSequence d;
        private final int e;
        private final Bundle f;

        /* JADX INFO: compiled from: caradapter-bug */
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
            public CustomAction[] newArray(int i) {
                return new CustomAction[i];
            }
        }

        CustomAction(String str, CharSequence charSequence, int i, Bundle bundle) {
            this.f65c = str;
            this.d = charSequence;
            this.e = i;
            this.f = bundle;
        }

        public static CustomAction a(Object obj) {
            if (obj == null || Build.VERSION.SDK_INT < 21) {
                return null;
            }
            PlaybackState.CustomAction customAction = (PlaybackState.CustomAction) obj;
            Bundle extras = customAction.getExtras();
            MediaSessionCompat.a(extras);
            return new CustomAction(customAction.getAction(), customAction.getName(), customAction.getIcon(), extras);
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public String toString() {
            return "Action:mName='" + ((Object) this.d) + ", mIcon=" + this.e + ", mExtras=" + this.f;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.f65c);
            TextUtils.writeToParcel(this.d, parcel, i);
            parcel.writeInt(this.e);
            parcel.writeBundle(this.f);
        }

        CustomAction(Parcel parcel) {
            this.f65c = parcel.readString();
            this.d = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            this.e = parcel.readInt();
            this.f = parcel.readBundle(MediaSessionCompat.class.getClassLoader());
        }
    }

    PlaybackStateCompat(Parcel parcel) {
        this.f64c = parcel.readInt();
        this.d = parcel.readLong();
        this.f = parcel.readFloat();
        this.j = parcel.readLong();
        this.e = parcel.readLong();
        this.g = parcel.readLong();
        this.i = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.k = parcel.createTypedArrayList(CustomAction.CREATOR);
        this.l = parcel.readLong();
        this.m = parcel.readBundle(MediaSessionCompat.class.getClassLoader());
        this.h = parcel.readInt();
    }
}
