package com.amazon.mp3.recentlyplayed;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class RecentlyPlayedRequestInfo implements Parcelable {
    public static final Parcelable.Creator<RecentlyPlayedRequestInfo> CREATOR = new Parcelable.Creator<RecentlyPlayedRequestInfo>() { // from class: com.amazon.mp3.recentlyplayed.RecentlyPlayedRequestInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RecentlyPlayedRequestInfo createFromParcel(final Parcel source) {
            RecentlyPlayedRequestInfo recentlyPlayedRequestInfo = new RecentlyPlayedRequestInfo();
            if (source == null) {
                return recentlyPlayedRequestInfo;
            }
            source.readTypedList(recentlyPlayedRequestInfo.mRecentItems, RecentlyPlayedItem.CREATOR);
            source.readTypedList(recentlyPlayedRequestInfo.mRecentTrackList, RecentTrack.CREATOR);
            return recentlyPlayedRequestInfo;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RecentlyPlayedRequestInfo[] newArray(final int size) {
            return new RecentlyPlayedRequestInfo[size];
        }
    };
    private List<RecentlyPlayedItem> mRecentItems = new ArrayList();
    private List<RecentTrack> mRecentTrackList = new ArrayList();

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public void setRecentlyPlayed(final List<RecentlyPlayedItem> recentlyPlayed) {
        this.mRecentItems = recentlyPlayed;
    }

    public List<RecentlyPlayedItem> getRecentlyPlayed() {
        return this.mRecentItems;
    }

    public void setRecentTrackList(final List<RecentTrack> recentTrackList) {
        this.mRecentTrackList = recentTrackList;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(final Parcel dest, final int flags) {
        dest.writeTypedList(this.mRecentItems);
        dest.writeTypedList(this.mRecentTrackList);
    }
}
