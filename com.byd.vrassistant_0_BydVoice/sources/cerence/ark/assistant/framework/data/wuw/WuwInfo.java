package cerence.ark.assistant.framework.data.wuw;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class WuwInfo implements Parcelable {
    public static final Parcelable.Creator<WuwInfo> CREATOR = new Parcelable.Creator<WuwInfo>() { // from class: cerence.ark.assistant.framework.data.wuw.WuwInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WuwInfo createFromParcel(Parcel parcel) {
            return new WuwInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WuwInfo[] newArray(int i) {
            return new WuwInfo[i];
        }
    };
    private final List<Wuw> mCustomizedWuwList;
    private final List<Wuw> mPredefinedWuwList;
    private final List<Wuw> mShortcutList;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    private WuwInfo(Builder builder) {
        this.mPredefinedWuwList = builder.mPredefinedWuwList;
        this.mCustomizedWuwList = builder.mCustomizedWuwList;
        this.mShortcutList = builder.mShortcutList;
    }

    protected WuwInfo(Parcel parcel) {
        this.mPredefinedWuwList = parcel.createTypedArrayList(Wuw.CREATOR);
        this.mCustomizedWuwList = parcel.createTypedArrayList(Wuw.CREATOR);
        this.mShortcutList = parcel.createTypedArrayList(Wuw.CREATOR);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(this.mPredefinedWuwList);
        parcel.writeTypedList(this.mCustomizedWuwList);
        parcel.writeTypedList(this.mShortcutList);
    }

    public List<Wuw> getPredefinedWuwList() {
        return this.mPredefinedWuwList;
    }

    public List<Wuw> getCustomizedWuwList() {
        return this.mCustomizedWuwList;
    }

    public List<Wuw> getShortcutList() {
        return this.mShortcutList;
    }

    public String toString() {
        return "Wuw{predefinedWuwList: " + this.mPredefinedWuwList + ", customizedWuwList: " + this.mCustomizedWuwList + ", shortcutList: " + this.mShortcutList + "}";
    }

    public static final class Builder {
        private List<Wuw> mCustomizedWuwList;
        private List<Wuw> mPredefinedWuwList;
        private List<Wuw> mShortcutList;

        private Builder() {
            this.mPredefinedWuwList = new ArrayList();
            this.mCustomizedWuwList = new ArrayList();
            this.mShortcutList = new ArrayList();
        }

        public Builder setPredefinedWuwList(List<Wuw> list) {
            this.mPredefinedWuwList = list;
            return this;
        }

        public Builder setCustomizedWuwList(List<Wuw> list) {
            this.mCustomizedWuwList = list;
            return this;
        }

        public Builder setShortcutList(List<Wuw> list) {
            this.mShortcutList = list;
            return this;
        }

        public WuwInfo build() {
            return new WuwInfo(this);
        }
    }
}
