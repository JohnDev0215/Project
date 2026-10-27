package cerence.ark.assistant.api.data;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class ShortcutCommand implements Parcelable {
    public static final Parcelable.Creator<ShortcutCommand> CREATOR = new Parcelable.Creator<ShortcutCommand>() { // from class: cerence.ark.assistant.api.data.ShortcutCommand.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ShortcutCommand createFromParcel(Parcel parcel) {
            return new ShortcutCommand(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ShortcutCommand[] newArray(int i) {
            return new ShortcutCommand[i];
        }
    };
    private final String mPhrase;
    private final int mTagThreshold;
    private final int mWordThreshold;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    private ShortcutCommand(Builder builder) {
        this.mPhrase = builder.mPhrase;
        this.mTagThreshold = builder.mTagThreshold;
        this.mWordThreshold = builder.mWordThreshold;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder copy(ShortcutCommand shortcutCommand) {
        Builder builder = new Builder();
        builder.mPhrase = shortcutCommand.mPhrase;
        builder.mTagThreshold = shortcutCommand.mTagThreshold;
        builder.mWordThreshold = shortcutCommand.mWordThreshold;
        return builder;
    }

    protected ShortcutCommand(Parcel parcel) {
        this.mPhrase = parcel.readString();
        this.mTagThreshold = parcel.readInt();
        this.mWordThreshold = parcel.readInt();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mPhrase);
        parcel.writeInt(this.mTagThreshold);
        parcel.writeInt(this.mWordThreshold);
    }

    public String getPhrase() {
        return this.mPhrase;
    }

    public int getTagThreshold() {
        return this.mTagThreshold;
    }

    public int getWordThreshold() {
        return this.mWordThreshold;
    }

    public String toString() {
        return "ShortcutCommand{mPhrase='" + this.mPhrase + "', mTagThreshold=" + this.mTagThreshold + ", mWordThreshold=" + this.mWordThreshold + '}';
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ShortcutCommand) {
            return this.mPhrase.equals(((ShortcutCommand) obj).getPhrase());
        }
        return false;
    }

    public int hashCode() {
        return this.mPhrase.hashCode();
    }

    public static class Builder {
        private String mPhrase;
        private int mTagThreshold;
        private int mWordThreshold;

        private Builder() {
            this.mPhrase = "";
        }

        public Builder setPhrase(String str) {
            if (str == null) {
                return this;
            }
            this.mPhrase = str;
            return this;
        }

        public Builder setTagThreshold(int i) {
            this.mTagThreshold = i;
            return this;
        }

        public Builder setWordThreshold(int i) {
            this.mWordThreshold = i;
            return this;
        }

        public ShortcutCommand build() {
            return new ShortcutCommand(this);
        }
    }
}
