package cerence.ark.assistant.framework.data.wuw;

import android.os.Parcel;
import android.os.Parcelable;
import cerence.ark.assistant.framework.data.wuw.BaseWakeupPhrase;
import cerence.ark.assistant.library.JsonKey;
import cerence.ark.assistant.library.Utils;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class WakeupPhrase extends BaseWakeupPhrase {
    public static final Parcelable.Creator<WakeupPhrase> CREATOR = new Parcelable.Creator<WakeupPhrase>() { // from class: cerence.ark.assistant.framework.data.wuw.WakeupPhrase.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WakeupPhrase createFromParcel(Parcel parcel) {
            return new WakeupPhrase(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WakeupPhrase[] newArray(int i) {
            return new WakeupPhrase[i];
        }
    };
    private final int mTagThreshold;
    private final int mWordThreshold;

    private WakeupPhrase(Builder builder) {
        super(builder);
        this.mTagThreshold = builder.mTagThreshold;
        this.mWordThreshold = builder.mWordThreshold;
    }

    protected WakeupPhrase(Parcel parcel) {
        super(parcel);
        this.mTagThreshold = parcel.readInt();
        this.mWordThreshold = parcel.readInt();
    }

    @Override // cerence.ark.assistant.framework.data.wuw.BaseWakeupPhrase, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(this.mTagThreshold);
        parcel.writeInt(this.mWordThreshold);
    }

    public int getTagThreshold() {
        return this.mTagThreshold;
    }

    public int getWordThreshold() {
        return this.mWordThreshold;
    }

    @Override // cerence.ark.assistant.framework.data.wuw.BaseWakeupPhrase
    public String toString() {
        return "WakeupPhrase{phrase: " + this.mPhrase + ", tagThreshold: " + this.mTagThreshold + ", wordThreshold: " + this.mWordThreshold + "}";
    }

    @Override // cerence.ark.assistant.framework.data.wuw.BaseWakeupPhrase, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        return toJSONObject().toString();
    }

    @Override // cerence.ark.assistant.framework.data.wuw.BaseWakeupPhrase, cerence.ark.assistant.core.JsonCompliant
    public JSONObject toJSONObject() throws JSONException {
        JSONObject jSONObject = super.toJSONObject();
        if (Utils.isValidThreshold(this.mTagThreshold)) {
            jSONObject.put(JsonKey.WUW_TAG_THRESHOLD, this.mTagThreshold);
        }
        if (Utils.isValidThreshold(this.mWordThreshold)) {
            jSONObject.put(JsonKey.WUW_WORD_THRESHOLD, this.mWordThreshold);
        }
        return jSONObject;
    }

    @Override // cerence.ark.assistant.framework.data.wuw.BaseWakeupPhrase
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override // cerence.ark.assistant.framework.data.wuw.BaseWakeupPhrase
    public int hashCode() {
        return super.hashCode();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder copy(WakeupPhrase wakeupPhrase) {
        Builder builder = new Builder();
        builder.mPhrase = wakeupPhrase.mPhrase;
        builder.mTagThreshold = wakeupPhrase.mTagThreshold;
        builder.mWordThreshold = wakeupPhrase.mWordThreshold;
        return builder;
    }

    public static class Builder extends BaseWakeupPhrase.Builder {
        private int mTagThreshold;
        private int mWordThreshold;

        private Builder() {
        }

        @Override // cerence.ark.assistant.framework.data.wuw.BaseWakeupPhrase.Builder
        public Builder setPhrase(String str) {
            super.setPhrase(str);
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

        @Override // cerence.ark.assistant.framework.data.wuw.BaseWakeupPhrase.Builder
        public WakeupPhrase build() {
            return new WakeupPhrase(this);
        }
    }
}
