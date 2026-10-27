package android.ext.services.notification;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public final class ChannelImpressions implements Parcelable {
    public static final Parcelable.Creator<ChannelImpressions> CREATOR;
    private float mDismissToViewRatioLimit;
    private int mDismissals;
    private int mStreak;
    private int mStreakLimit;
    private int mViews;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    static {
        Log.isLoggable("ExtAssistant.CI", 3);
        CREATOR = new Parcelable.Creator<ChannelImpressions>() { // from class: android.ext.services.notification.ChannelImpressions.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public ChannelImpressions createFromParcel(Parcel parcel) {
                return new ChannelImpressions(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public ChannelImpressions[] newArray(int i) {
                return new ChannelImpressions[i];
            }
        };
    }

    public ChannelImpressions() {
        this.mDismissals = 0;
        this.mViews = 0;
        this.mStreak = 0;
        this.mDismissToViewRatioLimit = 0.8f;
        this.mStreakLimit = 2;
    }

    protected ChannelImpressions(Parcel parcel) {
        this.mDismissals = 0;
        this.mViews = 0;
        this.mStreak = 0;
        this.mDismissals = parcel.readInt();
        this.mViews = parcel.readInt();
        this.mStreak = parcel.readInt();
        this.mDismissToViewRatioLimit = parcel.readFloat();
        this.mStreakLimit = parcel.readInt();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mDismissals);
        parcel.writeInt(this.mViews);
        parcel.writeInt(this.mStreak);
        parcel.writeFloat(this.mDismissToViewRatioLimit);
        parcel.writeInt(this.mStreakLimit);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || ChannelImpressions.class != obj.getClass()) {
            return false;
        }
        ChannelImpressions channelImpressions = (ChannelImpressions) obj;
        return this.mDismissals == channelImpressions.mDismissals && this.mViews == channelImpressions.mViews && this.mStreak == channelImpressions.mStreak;
    }

    public int hashCode() {
        return (((this.mDismissals * 31) + this.mViews) * 31) + this.mStreak;
    }

    public String toString() {
        return "ChannelImpressions{mDismissals=" + this.mDismissals + ", mViews=" + this.mViews + ", mStreak=" + this.mStreak + ", thresholds=(" + this.mDismissToViewRatioLimit + "," + this.mStreakLimit + ")}";
    }
}
