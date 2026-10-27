package android.service.notification;

import android.os.Parcel;
import android.os.Parcelable;
import android.service.notification.NotificationListenerService;

/* JADX INFO: loaded from: classes3.dex */
public class NotificationRankingUpdate implements Parcelable {
    public static final Parcelable.Creator<NotificationRankingUpdate> CREATOR = new Parcelable.Creator<NotificationRankingUpdate>() { // from class: android.service.notification.NotificationRankingUpdate.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NotificationRankingUpdate createFromParcel(Parcel parcel) {
            return new NotificationRankingUpdate(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NotificationRankingUpdate[] newArray(int i) {
            return new NotificationRankingUpdate[i];
        }
    };
    private final NotificationListenerService.RankingMap mRankingMap;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public NotificationRankingUpdate(NotificationListenerService.Ranking[] rankingArr) {
        this.mRankingMap = new NotificationListenerService.RankingMap(rankingArr);
    }

    public NotificationRankingUpdate(Parcel parcel) {
        this.mRankingMap = (NotificationListenerService.RankingMap) parcel.readParcelable(getClass().getClassLoader());
    }

    public NotificationListenerService.RankingMap getRankingMap() {
        return this.mRankingMap;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.mRankingMap.equals(((NotificationRankingUpdate) obj).mRankingMap);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.mRankingMap, i);
    }
}
