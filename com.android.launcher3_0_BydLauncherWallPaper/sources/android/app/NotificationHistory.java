package android.app;

import android.app.NotificationHistory;
import android.graphics.drawable.Icon;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.text.format.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class NotificationHistory implements Parcelable {
    public static final Parcelable.Creator<NotificationHistory> CREATOR = new Parcelable.Creator<NotificationHistory>() { // from class: android.app.NotificationHistory.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NotificationHistory createFromParcel(Parcel parcel) {
            return new NotificationHistory(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NotificationHistory[] newArray(int i) {
            return new NotificationHistory[i];
        }
    };
    private int mHistoryCount;
    private int mIndex;
    private List<HistoricalNotification> mNotificationsToWrite;
    private Parcel mParcel;
    private String[] mStringPool;
    private Set<String> mStringsToWrite;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public static final class HistoricalNotification {
        private String mChannelId;
        private String mChannelName;
        private String mConversationId;
        private Icon mIcon;
        private String mPackage;
        private long mPostedTimeMs;
        private String mText;
        private String mTitle;
        private int mUid;
        private int mUserId;

        private HistoricalNotification() {
        }

        public String getPackage() {
            return this.mPackage;
        }

        public String getChannelName() {
            return this.mChannelName;
        }

        public String getChannelId() {
            return this.mChannelId;
        }

        public int getUid() {
            return this.mUid;
        }

        public int getUserId() {
            return this.mUserId;
        }

        public long getPostedTimeMs() {
            return this.mPostedTimeMs;
        }

        public String getTitle() {
            return this.mTitle;
        }

        public String getText() {
            return this.mText;
        }

        public Icon getIcon() {
            return this.mIcon;
        }

        public String getKey() {
            return this.mPackage + "|" + this.mUid + "|" + this.mPostedTimeMs;
        }

        public String getConversationId() {
            return this.mConversationId;
        }

        public String toString() {
            return "HistoricalNotification{key='" + getKey() + DateFormat.QUOTE + ", mChannelName='" + this.mChannelName + DateFormat.QUOTE + ", mChannelId='" + this.mChannelId + DateFormat.QUOTE + ", mUserId=" + this.mUserId + ", mUid=" + this.mUid + ", mTitle='" + this.mTitle + DateFormat.QUOTE + ", mText='" + this.mText + DateFormat.QUOTE + ", mIcon=" + this.mIcon + ", mPostedTimeMs=" + this.mPostedTimeMs + ", mConversationId=" + this.mConversationId + '}';
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            HistoricalNotification historicalNotification = (HistoricalNotification) obj;
            return getUid() == historicalNotification.getUid() && getUserId() == historicalNotification.getUserId() && getPostedTimeMs() == historicalNotification.getPostedTimeMs() && Objects.equals(getPackage(), historicalNotification.getPackage()) && Objects.equals(getChannelName(), historicalNotification.getChannelName()) && Objects.equals(getChannelId(), historicalNotification.getChannelId()) && Objects.equals(getTitle(), historicalNotification.getTitle()) && Objects.equals(getText(), historicalNotification.getText()) && Objects.equals(getConversationId(), historicalNotification.getConversationId()) && ((getIcon() == null && historicalNotification.getIcon() == null) || (getIcon() != null && historicalNotification.getIcon() != null && getIcon().sameAs(historicalNotification.getIcon())));
        }

        public int hashCode() {
            return Objects.hash(getPackage(), getChannelName(), getChannelId(), Integer.valueOf(getUid()), Integer.valueOf(getUserId()), Long.valueOf(getPostedTimeMs()), getTitle(), getText(), getIcon(), getConversationId());
        }

        public static final class Builder {
            private String mChannelId;
            private String mChannelName;
            private String mConversationId;
            private Icon mIcon;
            private String mPackage;
            private long mPostedTimeMs;
            private String mText;
            private String mTitle;
            private int mUid;
            private int mUserId;

            public Builder setPackage(String str) {
                this.mPackage = str;
                return this;
            }

            public Builder setChannelName(String str) {
                this.mChannelName = str;
                return this;
            }

            public Builder setChannelId(String str) {
                this.mChannelId = str;
                return this;
            }

            public Builder setUid(int i) {
                this.mUid = i;
                return this;
            }

            public Builder setUserId(int i) {
                this.mUserId = i;
                return this;
            }

            public Builder setPostedTimeMs(long j) {
                this.mPostedTimeMs = j;
                return this;
            }

            public Builder setTitle(String str) {
                this.mTitle = str;
                return this;
            }

            public Builder setText(String str) {
                this.mText = str;
                return this;
            }

            public Builder setIcon(Icon icon) {
                this.mIcon = icon;
                return this;
            }

            public Builder setConversationId(String str) {
                this.mConversationId = str;
                return this;
            }

            public HistoricalNotification build() {
                HistoricalNotification historicalNotification = new HistoricalNotification();
                historicalNotification.mPackage = this.mPackage;
                historicalNotification.mChannelName = this.mChannelName;
                historicalNotification.mChannelId = this.mChannelId;
                historicalNotification.mUid = this.mUid;
                historicalNotification.mUserId = this.mUserId;
                historicalNotification.mPostedTimeMs = this.mPostedTimeMs;
                historicalNotification.mTitle = this.mTitle;
                historicalNotification.mText = this.mText;
                historicalNotification.mIcon = this.mIcon;
                historicalNotification.mConversationId = this.mConversationId;
                return historicalNotification;
            }
        }
    }

    private NotificationHistory(Parcel parcel) {
        this.mNotificationsToWrite = new ArrayList();
        this.mStringsToWrite = new HashSet();
        this.mParcel = null;
        this.mIndex = 0;
        byte[] blob = parcel.readBlob();
        Parcel parcelObtain = Parcel.obtain();
        parcelObtain.unmarshall(blob, 0, blob.length);
        parcelObtain.setDataPosition(0);
        this.mHistoryCount = parcelObtain.readInt();
        this.mIndex = parcelObtain.readInt();
        if (this.mHistoryCount > 0) {
            this.mStringPool = parcelObtain.createStringArray();
            int i = parcelObtain.readInt();
            int i2 = parcelObtain.readInt();
            Parcel parcelObtain2 = Parcel.obtain();
            this.mParcel = parcelObtain2;
            parcelObtain2.setDataPosition(0);
            this.mParcel.appendFrom(parcelObtain, parcelObtain.dataPosition(), i);
            Parcel parcel2 = this.mParcel;
            parcel2.setDataSize(parcel2.dataPosition());
            this.mParcel.setDataPosition(i2);
        }
    }

    public NotificationHistory() {
        this.mNotificationsToWrite = new ArrayList();
        this.mStringsToWrite = new HashSet();
        this.mParcel = null;
        this.mIndex = 0;
        this.mHistoryCount = 0;
    }

    public boolean hasNextNotification() {
        return this.mIndex < this.mHistoryCount;
    }

    public HistoricalNotification getNextNotification() {
        if (!hasNextNotification()) {
            return null;
        }
        HistoricalNotification notificationFromParcel = readNotificationFromParcel(this.mParcel);
        this.mIndex++;
        if (!hasNextNotification()) {
            this.mParcel.recycle();
            this.mParcel = null;
        }
        return notificationFromParcel;
    }

    public void addPooledStrings(List<String> list) {
        this.mStringsToWrite.addAll(list);
    }

    public void poolStringsFromNotifications() {
        this.mStringsToWrite.clear();
        for (int i = 0; i < this.mNotificationsToWrite.size(); i++) {
            HistoricalNotification historicalNotification = this.mNotificationsToWrite.get(i);
            this.mStringsToWrite.add(historicalNotification.getPackage());
            this.mStringsToWrite.add(historicalNotification.getChannelName());
            this.mStringsToWrite.add(historicalNotification.getChannelId());
            if (!TextUtils.isEmpty(historicalNotification.getConversationId())) {
                this.mStringsToWrite.add(historicalNotification.getConversationId());
            }
        }
    }

    public void addNotificationToWrite(HistoricalNotification historicalNotification) {
        if (historicalNotification == null) {
            return;
        }
        this.mNotificationsToWrite.add(historicalNotification);
        this.mHistoryCount++;
    }

    public void addNewNotificationToWrite(HistoricalNotification historicalNotification) {
        if (historicalNotification == null) {
            return;
        }
        this.mNotificationsToWrite.add(0, historicalNotification);
        this.mHistoryCount++;
    }

    public void addNotificationsToWrite(NotificationHistory notificationHistory) {
        Iterator<HistoricalNotification> it = notificationHistory.getNotificationsToWrite().iterator();
        while (it.hasNext()) {
            addNotificationToWrite(it.next());
        }
        Collections.sort(this.mNotificationsToWrite, new Comparator() { // from class: android.app.-$$Lambda$NotificationHistory$TAe1JI7XCC0Kg8McnJyA4Ml5KF4
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return NotificationHistory.lambda$addNotificationsToWrite$0((NotificationHistory.HistoricalNotification) obj, (NotificationHistory.HistoricalNotification) obj2);
            }
        });
        poolStringsFromNotifications();
    }

    static /* synthetic */ int lambda$addNotificationsToWrite$0(HistoricalNotification historicalNotification, HistoricalNotification historicalNotification2) {
        return Long.compare(historicalNotification.getPostedTimeMs(), historicalNotification2.getPostedTimeMs()) * (-1);
    }

    public void removeNotificationsFromWrite(String str) {
        for (int size = this.mNotificationsToWrite.size() - 1; size >= 0; size--) {
            if (str.equals(this.mNotificationsToWrite.get(size).getPackage())) {
                this.mNotificationsToWrite.remove(size);
            }
        }
        poolStringsFromNotifications();
    }

    public boolean removeNotificationFromWrite(String str, long j) {
        boolean z = false;
        for (int size = this.mNotificationsToWrite.size() - 1; size >= 0; size--) {
            HistoricalNotification historicalNotification = this.mNotificationsToWrite.get(size);
            if (str.equals(historicalNotification.getPackage()) && j == historicalNotification.getPostedTimeMs()) {
                this.mNotificationsToWrite.remove(size);
                z = true;
            }
        }
        if (z) {
            poolStringsFromNotifications();
        }
        return z;
    }

    public boolean removeConversationFromWrite(String str, String str2) {
        boolean z = false;
        for (int size = this.mNotificationsToWrite.size() - 1; size >= 0; size--) {
            HistoricalNotification historicalNotification = this.mNotificationsToWrite.get(size);
            if (str.equals(historicalNotification.getPackage()) && str2.equals(historicalNotification.getConversationId())) {
                this.mNotificationsToWrite.remove(size);
                z = true;
            }
        }
        if (z) {
            poolStringsFromNotifications();
        }
        return z;
    }

    public String[] getPooledStringsToWrite() {
        String[] strArr = (String[]) this.mStringsToWrite.toArray(new String[0]);
        Arrays.sort(strArr);
        return strArr;
    }

    public List<HistoricalNotification> getNotificationsToWrite() {
        return this.mNotificationsToWrite;
    }

    public int getHistoryCount() {
        return this.mHistoryCount;
    }

    private int findStringIndex(String str) {
        int iBinarySearch = Arrays.binarySearch(this.mStringPool, str);
        if (iBinarySearch >= 0) {
            return iBinarySearch;
        }
        throw new IllegalStateException("String '" + str + "' is not in the string pool");
    }

    private void writeNotificationToParcel(HistoricalNotification historicalNotification, Parcel parcel, int i) {
        int iFindStringIndex = historicalNotification.mPackage != null ? findStringIndex(historicalNotification.mPackage) : -1;
        int iFindStringIndex2 = historicalNotification.getChannelName() != null ? findStringIndex(historicalNotification.getChannelName()) : -1;
        int iFindStringIndex3 = historicalNotification.getChannelId() != null ? findStringIndex(historicalNotification.getChannelId()) : -1;
        int iFindStringIndex4 = TextUtils.isEmpty(historicalNotification.getConversationId()) ? -1 : findStringIndex(historicalNotification.getConversationId());
        parcel.writeInt(iFindStringIndex);
        parcel.writeInt(iFindStringIndex2);
        parcel.writeInt(iFindStringIndex3);
        parcel.writeInt(iFindStringIndex4);
        parcel.writeInt(historicalNotification.getUid());
        parcel.writeInt(historicalNotification.getUserId());
        parcel.writeLong(historicalNotification.getPostedTimeMs());
        parcel.writeString(historicalNotification.getTitle());
        parcel.writeString(historicalNotification.getText());
        historicalNotification.getIcon().writeToParcel(parcel, i);
    }

    private HistoricalNotification readNotificationFromParcel(Parcel parcel) {
        HistoricalNotification.Builder builder = new HistoricalNotification.Builder();
        int i = parcel.readInt();
        if (i >= 0) {
            builder.mPackage = this.mStringPool[i];
        } else {
            builder.mPackage = null;
        }
        int i2 = parcel.readInt();
        if (i2 >= 0) {
            builder.setChannelName(this.mStringPool[i2]);
        } else {
            builder.setChannelName(null);
        }
        int i3 = parcel.readInt();
        if (i3 >= 0) {
            builder.setChannelId(this.mStringPool[i3]);
        } else {
            builder.setChannelId(null);
        }
        int i4 = parcel.readInt();
        if (i4 >= 0) {
            builder.setConversationId(this.mStringPool[i4]);
        } else {
            builder.setConversationId(null);
        }
        builder.setUid(parcel.readInt());
        builder.setUserId(parcel.readInt());
        builder.setPostedTimeMs(parcel.readLong());
        builder.setTitle(parcel.readString());
        builder.setText(parcel.readString());
        builder.setIcon(Icon.CREATOR.createFromParcel(parcel));
        return builder.build();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        Parcel parcelObtain = Parcel.obtain();
        parcelObtain.writeInt(this.mHistoryCount);
        parcelObtain.writeInt(this.mIndex);
        if (this.mHistoryCount > 0) {
            String[] pooledStringsToWrite = getPooledStringsToWrite();
            this.mStringPool = pooledStringsToWrite;
            parcelObtain.writeStringArray(pooledStringsToWrite);
            if (!this.mNotificationsToWrite.isEmpty()) {
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain2.setDataPosition(0);
                    for (int i2 = 0; i2 < this.mHistoryCount; i2++) {
                        writeNotificationToParcel(this.mNotificationsToWrite.get(i2), parcelObtain2, i);
                    }
                    int iDataPosition = parcelObtain2.dataPosition();
                    parcelObtain.writeInt(iDataPosition);
                    parcelObtain.writeInt(0);
                    parcelObtain.appendFrom(parcelObtain2, 0, iDataPosition);
                } finally {
                    parcelObtain2.recycle();
                }
            } else {
                Parcel parcel2 = this.mParcel;
                if (parcel2 != null) {
                    parcelObtain.writeInt(parcel2.dataSize());
                    parcelObtain.writeInt(this.mParcel.dataPosition());
                    Parcel parcel3 = this.mParcel;
                    parcelObtain.appendFrom(parcel3, 0, parcel3.dataSize());
                } else {
                    throw new IllegalStateException("Either mParcel or mNotificationsToWrite must not be null");
                }
            }
        }
        parcel.writeBlob(parcelObtain.marshall());
    }
}
