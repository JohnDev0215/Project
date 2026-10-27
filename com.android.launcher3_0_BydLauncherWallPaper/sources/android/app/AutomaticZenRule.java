package android.app;

import android.content.ComponentName;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.service.notification.ZenPolicy;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class AutomaticZenRule implements Parcelable {
    public static final Parcelable.Creator<AutomaticZenRule> CREATOR = new Parcelable.Creator<AutomaticZenRule>() { // from class: android.app.AutomaticZenRule.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AutomaticZenRule createFromParcel(Parcel parcel) {
            return new AutomaticZenRule(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AutomaticZenRule[] newArray(int i) {
            return new AutomaticZenRule[i];
        }
    };
    private static final int DISABLED = 0;
    private static final int ENABLED = 1;
    private Uri conditionId;
    private ComponentName configurationActivity;
    private long creationTime;
    private boolean enabled;
    private int interruptionFilter;
    private boolean mModified;
    private ZenPolicy mZenPolicy;
    private String name;
    private ComponentName owner;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Deprecated
    public AutomaticZenRule(String str, ComponentName componentName, Uri uri, int i, boolean z) {
        this(str, componentName, null, uri, null, i, z);
    }

    public AutomaticZenRule(String str, ComponentName componentName, ComponentName componentName2, Uri uri, ZenPolicy zenPolicy, int i, boolean z) {
        this.enabled = false;
        this.mModified = false;
        this.name = str;
        this.owner = componentName;
        this.configurationActivity = componentName2;
        this.conditionId = uri;
        this.interruptionFilter = i;
        this.enabled = z;
        this.mZenPolicy = zenPolicy;
    }

    public AutomaticZenRule(String str, ComponentName componentName, ComponentName componentName2, Uri uri, ZenPolicy zenPolicy, int i, boolean z, long j) {
        this(str, componentName, componentName2, uri, zenPolicy, i, z);
        this.creationTime = j;
    }

    public AutomaticZenRule(Parcel parcel) {
        this.enabled = false;
        this.mModified = false;
        this.enabled = parcel.readInt() == 1;
        if (parcel.readInt() == 1) {
            this.name = parcel.readString();
        }
        this.interruptionFilter = parcel.readInt();
        this.conditionId = (Uri) parcel.readParcelable(null);
        this.owner = (ComponentName) parcel.readParcelable(null);
        this.configurationActivity = (ComponentName) parcel.readParcelable(null);
        this.creationTime = parcel.readLong();
        this.mZenPolicy = (ZenPolicy) parcel.readParcelable(null);
        this.mModified = parcel.readInt() == 1;
    }

    public ComponentName getOwner() {
        return this.owner;
    }

    public ComponentName getConfigurationActivity() {
        return this.configurationActivity;
    }

    public Uri getConditionId() {
        return this.conditionId;
    }

    public int getInterruptionFilter() {
        return this.interruptionFilter;
    }

    public String getName() {
        return this.name;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public boolean isModified() {
        return this.mModified;
    }

    public ZenPolicy getZenPolicy() {
        ZenPolicy zenPolicy = this.mZenPolicy;
        if (zenPolicy == null) {
            return null;
        }
        return zenPolicy.copy();
    }

    public long getCreationTime() {
        return this.creationTime;
    }

    public void setConditionId(Uri uri) {
        this.conditionId = uri;
    }

    public void setInterruptionFilter(int i) {
        this.interruptionFilter = i;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setEnabled(boolean z) {
        this.enabled = z;
    }

    public void setModified(boolean z) {
        this.mModified = z;
    }

    public void setZenPolicy(ZenPolicy zenPolicy) {
        this.mZenPolicy = zenPolicy == null ? null : zenPolicy.copy();
    }

    public void setConfigurationActivity(ComponentName componentName) {
        this.configurationActivity = componentName;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.enabled ? 1 : 0);
        if (this.name != null) {
            parcel.writeInt(1);
            parcel.writeString(this.name);
        } else {
            parcel.writeInt(0);
        }
        parcel.writeInt(this.interruptionFilter);
        parcel.writeParcelable(this.conditionId, 0);
        parcel.writeParcelable(this.owner, 0);
        parcel.writeParcelable(this.configurationActivity, 0);
        parcel.writeLong(this.creationTime);
        parcel.writeParcelable(this.mZenPolicy, 0);
        parcel.writeInt(this.mModified ? 1 : 0);
    }

    public String toString() {
        return AutomaticZenRule.class.getSimpleName() + "[enabled=" + this.enabled + ",name=" + this.name + ",interruptionFilter=" + this.interruptionFilter + ",conditionId=" + this.conditionId + ",owner=" + this.owner + ",configActivity=" + this.configurationActivity + ",creationTime=" + this.creationTime + ",mZenPolicy=" + this.mZenPolicy + ']';
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof AutomaticZenRule)) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        AutomaticZenRule automaticZenRule = (AutomaticZenRule) obj;
        return automaticZenRule.enabled == this.enabled && automaticZenRule.mModified == this.mModified && Objects.equals(automaticZenRule.name, this.name) && automaticZenRule.interruptionFilter == this.interruptionFilter && Objects.equals(automaticZenRule.conditionId, this.conditionId) && Objects.equals(automaticZenRule.owner, this.owner) && Objects.equals(automaticZenRule.mZenPolicy, this.mZenPolicy) && Objects.equals(automaticZenRule.configurationActivity, this.configurationActivity) && automaticZenRule.creationTime == this.creationTime;
    }

    public int hashCode() {
        return Objects.hash(Boolean.valueOf(this.enabled), this.name, Integer.valueOf(this.interruptionFilter), this.conditionId, this.owner, this.configurationActivity, this.mZenPolicy, Boolean.valueOf(this.mModified), Long.valueOf(this.creationTime));
    }
}
