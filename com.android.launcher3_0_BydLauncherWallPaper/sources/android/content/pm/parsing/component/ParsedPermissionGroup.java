package android.content.pm.parsing.component;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class ParsedPermissionGroup extends ParsedComponent {
    public static final Parcelable.Creator<ParsedPermissionGroup> CREATOR = new Parcelable.Creator<ParsedPermissionGroup>() { // from class: android.content.pm.parsing.component.ParsedPermissionGroup.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ParsedPermissionGroup createFromParcel(Parcel parcel) {
            return new ParsedPermissionGroup(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ParsedPermissionGroup[] newArray(int i) {
            return new ParsedPermissionGroup[i];
        }
    };
    int backgroundRequestDetailResourceId;
    int backgroundRequestResourceId;
    int priority;
    int requestDetailResourceId;
    int requestRes;

    @Override // android.content.pm.parsing.component.ParsedComponent, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public void setPriority(int i) {
        this.priority = i;
    }

    public String toString() {
        return "PermissionGroup{" + Integer.toHexString(System.identityHashCode(this)) + " " + getName() + "}";
    }

    @Override // android.content.pm.parsing.component.ParsedComponent, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(this.requestDetailResourceId);
        parcel.writeInt(this.backgroundRequestResourceId);
        parcel.writeInt(this.backgroundRequestDetailResourceId);
        parcel.writeInt(this.requestRes);
        parcel.writeInt(this.priority);
    }

    public ParsedPermissionGroup() {
    }

    protected ParsedPermissionGroup(Parcel parcel) {
        super(parcel);
        this.requestDetailResourceId = parcel.readInt();
        this.backgroundRequestResourceId = parcel.readInt();
        this.backgroundRequestDetailResourceId = parcel.readInt();
        this.requestRes = parcel.readInt();
        this.priority = parcel.readInt();
    }

    public int getRequestDetailResourceId() {
        return this.requestDetailResourceId;
    }

    public int getBackgroundRequestResourceId() {
        return this.backgroundRequestResourceId;
    }

    public int getBackgroundRequestDetailResourceId() {
        return this.backgroundRequestDetailResourceId;
    }

    public int getRequestRes() {
        return this.requestRes;
    }

    public int getPriority() {
        return this.priority;
    }
}
