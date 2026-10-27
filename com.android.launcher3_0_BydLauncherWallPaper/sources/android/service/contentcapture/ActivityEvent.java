package android.service.contentcapture;

import android.annotation.SystemApi;
import android.content.ComponentName;
import android.os.Parcel;
import android.os.Parcelable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes3.dex */
@SystemApi
public final class ActivityEvent implements Parcelable {
    public static final Parcelable.Creator<ActivityEvent> CREATOR = new Parcelable.Creator<ActivityEvent>() { // from class: android.service.contentcapture.ActivityEvent.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ActivityEvent createFromParcel(Parcel parcel) {
            return new ActivityEvent((ComponentName) parcel.readParcelable(null), parcel.readInt());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ActivityEvent[] newArray(int i) {
            return new ActivityEvent[i];
        }
    };
    public static final int TYPE_ACTIVITY_DESTROYED = 24;
    public static final int TYPE_ACTIVITY_PAUSED = 2;
    public static final int TYPE_ACTIVITY_RESUMED = 1;
    public static final int TYPE_ACTIVITY_STOPPED = 23;
    private final ComponentName mComponentName;
    private final int mType;

    @Retention(RetentionPolicy.SOURCE)
    public @interface ActivityEventType {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ActivityEvent(ComponentName componentName, int i) {
        this.mComponentName = componentName;
        this.mType = i;
    }

    public ComponentName getComponentName() {
        return this.mComponentName;
    }

    public int getEventType() {
        return this.mType;
    }

    public static String getTypeAsString(int i) {
        return i != 1 ? i != 2 ? i != 23 ? i != 24 ? "UKNOWN_TYPE: " + i : "ACTIVITY_DESTROYED" : "ACTIVITY_STOPPED" : "ACTIVITY_PAUSED" : "ACTIVITY_RESUMED";
    }

    public String toString() {
        return "ActivityEvent[" + this.mComponentName.toShortString() + "]:" + getTypeAsString(this.mType);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.mComponentName, i);
        parcel.writeInt(this.mType);
    }
}
