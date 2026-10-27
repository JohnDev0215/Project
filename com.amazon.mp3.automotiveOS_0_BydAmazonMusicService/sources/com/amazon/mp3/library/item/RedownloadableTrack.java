package com.amazon.mp3.library.item;

import android.os.Parcel;
import android.os.Parcelable;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RedownloadableTrack.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0086\b\u0018\u0000 \u001e2\u00020\u0001:\u0001\u001eB#\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\t\u0012\u0006\u0010\u0015\u001a\u00020\u0014\u0012\b\u0010\u0019\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u001b\u0010\u001cB\u0011\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u001b\u0010\u001dJ\u0018\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0016J\b\u0010\b\u001a\u00020\u0004H\u0016J\t\u0010\n\u001a\u00020\tHÖ\u0001J\t\u0010\u000b\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\u000f\u001a\u00020\u000e2\b\u0010\r\u001a\u0004\u0018\u00010\fHÖ\u0003R\u0019\u0010\u0010\u001a\u0004\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u0017\u0010\u0015\u001a\u00020\u00148\u0006¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\u0019\u0010\u0019\u001a\u0004\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b\u0019\u0010\u0011\u001a\u0004\b\u001a\u0010\u0013¨\u0006\u001f"}, d2 = {"Lcom/amazon/mp3/library/item/RedownloadableTrack;", "Landroid/os/Parcelable;", "Landroid/os/Parcel;", "parcel", "", "flags", "", "writeToParcel", "describeContents", "", "toString", "hashCode", "", "other", "", "equals", "asin", "Ljava/lang/String;", "getAsin", "()Ljava/lang/String;", "Lcom/amazon/mp3/library/item/TrackType;", "trackType", "Lcom/amazon/mp3/library/item/TrackType;", "getTrackType", "()Lcom/amazon/mp3/library/item/TrackType;", "trackName", "getTrackName", "<init>", "(Ljava/lang/String;Lcom/amazon/mp3/library/item/TrackType;Ljava/lang/String;)V", "(Landroid/os/Parcel;)V", "CREATOR", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final /* data */ class RedownloadableTrack implements Parcelable {

    /* JADX INFO: renamed from: CREATOR, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String asin;
    private final String trackName;
    private final TrackType trackType;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof RedownloadableTrack)) {
            return false;
        }
        RedownloadableTrack redownloadableTrack = (RedownloadableTrack) other;
        return Intrinsics.areEqual(this.asin, redownloadableTrack.asin) && this.trackType == redownloadableTrack.trackType && Intrinsics.areEqual(this.trackName, redownloadableTrack.trackName);
    }

    public int hashCode() {
        String str = this.asin;
        int iHashCode = (((str == null ? 0 : str.hashCode()) * 31) + this.trackType.hashCode()) * 31;
        String str2 = this.trackName;
        return iHashCode + (str2 != null ? str2.hashCode() : 0);
    }

    public String toString() {
        return "RedownloadableTrack(asin=" + ((Object) this.asin) + ", trackType=" + this.trackType + ", trackName=" + ((Object) this.trackName) + ')';
    }

    public RedownloadableTrack(String str, TrackType trackType, String str2) {
        Intrinsics.checkNotNullParameter(trackType, "trackType");
        this.asin = str;
        this.trackType = trackType;
        this.trackName = str2;
    }

    public final String getAsin() {
        return this.asin;
    }

    public final String getTrackName() {
        return this.trackName;
    }

    public final TrackType getTrackType() {
        return this.trackType;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public RedownloadableTrack(Parcel parcel) {
        Intrinsics.checkNotNullParameter(parcel, "parcel");
        String string = parcel.readString();
        String string2 = parcel.readString();
        Intrinsics.checkNotNull(string2);
        Intrinsics.checkNotNullExpressionValue(string2, "parcel.readString()!!");
        this(string, TrackType.valueOf(string2), parcel.readString());
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int flags) {
        Intrinsics.checkNotNullParameter(parcel, "parcel");
        parcel.writeString(this.asin);
        parcel.writeString(this.trackType.name());
        parcel.writeString(this.trackName);
    }

    /* JADX INFO: renamed from: com.amazon.mp3.library.item.RedownloadableTrack$CREATOR, reason: from kotlin metadata */
    /* JADX INFO: compiled from: RedownloadableTrack.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u001d\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016¢\u0006\u0002\u0010\u000b¨\u0006\f"}, d2 = {"Lcom/amazon/mp3/library/item/RedownloadableTrack$CREATOR;", "Landroid/os/Parcelable$Creator;", "Lcom/amazon/mp3/library/item/RedownloadableTrack;", "()V", "createFromParcel", "parcel", "Landroid/os/Parcel;", "newArray", "", "size", "", "(I)[Lcom/amazon/mp3/library/item/RedownloadableTrack;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion implements Parcelable.Creator<RedownloadableTrack> {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RedownloadableTrack createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new RedownloadableTrack(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RedownloadableTrack[] newArray(int size) {
            return new RedownloadableTrack[size];
        }
    }
}
