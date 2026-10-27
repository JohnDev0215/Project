package cerence.ark.assistant.framework.data.wuw;

import android.os.Parcel;
import android.os.Parcelable;
import cerence.ark.assistant.framework.data.wuw.BaseWakeupPhrase;

/* JADX INFO: loaded from: classes.dex */
public class Wuw extends BaseWakeupPhrase {
    public static final Parcelable.Creator<Wuw> CREATOR = new Parcelable.Creator<Wuw>() { // from class: cerence.ark.assistant.framework.data.wuw.Wuw.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Wuw createFromParcel(Parcel parcel) {
            return new Wuw(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Wuw[] newArray(int i) {
            return new Wuw[i];
        }
    };
    private boolean mActivated;

    protected Wuw(Builder builder) {
        super(builder);
        this.mActivated = builder.mActivated;
    }

    protected Wuw(Parcel parcel) {
        super(parcel);
        this.mActivated = parcel.readByte() != 0;
    }

    @Override // cerence.ark.assistant.framework.data.wuw.BaseWakeupPhrase, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeByte(this.mActivated ? (byte) 1 : (byte) 0);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public boolean isActivated() {
        return this.mActivated;
    }

    @Override // cerence.ark.assistant.framework.data.wuw.BaseWakeupPhrase
    public String toString() {
        return "Wuw{phrase: " + this.mPhrase + ", activated: " + this.mActivated + "}";
    }

    public static class Builder extends BaseWakeupPhrase.Builder {
        private boolean mActivated;

        private Builder() {
        }

        @Override // cerence.ark.assistant.framework.data.wuw.BaseWakeupPhrase.Builder
        public Builder setPhrase(String str) {
            super.setPhrase(str);
            return this;
        }

        public Builder setActivated(boolean z) {
            this.mActivated = z;
            return this;
        }

        @Override // cerence.ark.assistant.framework.data.wuw.BaseWakeupPhrase.Builder
        public Wuw build() {
            return new Wuw(this);
        }
    }
}
