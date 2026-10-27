package com.amazon.mp3.casting;

import android.os.Parcel;
import android.os.Parcelable;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.voice.AlexaTrack;
import com.amazon.music.station.Rating;

/* JADX INFO: loaded from: classes3.dex */
public class CastingTrack extends AlexaTrack {
    public static final Parcelable.Creator<MusicTrack> CREATOR = new Parcelable.Creator<MusicTrack>() { // from class: com.amazon.mp3.casting.CastingTrack.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MusicTrack createFromParcel(Parcel source) {
            CastingTrack castingTrack = new CastingTrack(MusicTrack.CREATOR.createFromParcel(source));
            castingTrack.mQueueId = source.readString();
            castingTrack.setMpqsTrackId(source.readString());
            castingTrack.setCanRateTrack(Boolean.getBoolean(source.readString()));
            String string = source.readString();
            castingTrack.setRating(string == null ? null : Rating.valueOf(string));
            castingTrack.setIsFirstTrackInQueue(Boolean.valueOf(source.readString()).booleanValue());
            return castingTrack;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MusicTrack[] newArray(int size) {
            return new CastingTrack[size];
        }
    };
    private String mQueueId;

    @Override // com.amazon.mp3.library.item.MusicTrack, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.amazon.mp3.library.item.AbstractItem
    public boolean isCatalogAccessible() {
        return true;
    }

    public CastingTrack(final MusicTrack track) {
        super(track);
    }

    public void setQueueId(final String queueId) {
        this.mQueueId = queueId;
    }

    @Override // com.amazon.mp3.voice.AlexaTrack, com.amazon.mp3.library.item.MusicTrack
    public String toString() {
        return "CastingTrack{mQueueId='" + this.mQueueId + ", mMpqsTrackId='" + getMpqsTrackId() + ", mCanRateTrack=" + canRateTrack() + ", mRating=" + getRating() + ", mIsFirstTrackInQueue=" + isFirstTrackInQueue() + ", mMetricsContext=" + getMetricsContext() + '}';
    }

    @Override // com.amazon.mp3.library.item.MusicTrack, android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.mQueueId);
        dest.writeString(getMpqsTrackId());
        dest.writeString(String.valueOf(canRateTrack()));
        dest.writeString(getRating() == null ? null : getRating().name());
        dest.writeString(String.valueOf(isFirstTrackInQueue()));
    }
}
