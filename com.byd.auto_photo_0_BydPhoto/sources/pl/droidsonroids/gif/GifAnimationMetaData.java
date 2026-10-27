package pl.droidsonroids.gif;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import java.io.Serializable;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public class GifAnimationMetaData implements Serializable, Parcelable {
    public static final Parcelable.Creator<GifAnimationMetaData> CREATOR = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f3168a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final int f3169b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final int f3170c;
    private final int d;
    private final int e;
    private final long f;
    private final long g;

    class a implements Parcelable.Creator<GifAnimationMetaData> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public GifAnimationMetaData createFromParcel(Parcel parcel) {
            return new GifAnimationMetaData(parcel, null);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public GifAnimationMetaData[] newArray(int i) {
            return new GifAnimationMetaData[i];
        }
    }

    private GifAnimationMetaData(Parcel parcel) {
        this.f3168a = parcel.readInt();
        this.f3169b = parcel.readInt();
        this.f3170c = parcel.readInt();
        this.d = parcel.readInt();
        this.e = parcel.readInt();
        this.g = parcel.readLong();
        this.f = parcel.readLong();
    }

    /* synthetic */ GifAnimationMetaData(Parcel parcel, a aVar) {
        this(parcel);
    }

    public boolean a() {
        return this.e > 1 && this.f3169b > 0;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @NonNull
    public String toString() {
        int i = this.f3168a;
        String str = String.format(Locale.ENGLISH, "GIF: size: %dx%d, frames: %d, loops: %s, duration: %d", Integer.valueOf(this.d), Integer.valueOf(this.f3170c), Integer.valueOf(this.e), i == 0 ? "Infinity" : Integer.toString(i), Integer.valueOf(this.f3169b));
        if (!a()) {
            return str;
        }
        return "Animated " + str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f3168a);
        parcel.writeInt(this.f3169b);
        parcel.writeInt(this.f3170c);
        parcel.writeInt(this.d);
        parcel.writeInt(this.e);
        parcel.writeLong(this.g);
        parcel.writeLong(this.f);
    }
}
