package cerence.ark.assistant.framework.data;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import cerence.ark.assistant.api.prompter.VoiceType;
import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class VoiceConfig implements Parcelable, JsonCompliant {
    public static final Parcelable.Creator<VoiceConfig> CREATOR = new Parcelable.Creator<VoiceConfig>() { // from class: cerence.ark.assistant.framework.data.VoiceConfig.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public VoiceConfig createFromParcel(Parcel parcel) {
            return new VoiceConfig(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public VoiceConfig[] newArray(int i) {
            return new VoiceConfig[i];
        }
    };
    private final String mHybridVoiceId;
    private final String mLocalVoiceId;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    private VoiceConfig(Builder builder) {
        this.mLocalVoiceId = builder.mLocalVoiceId;
        this.mHybridVoiceId = builder.mHybridVoiceId;
    }

    protected VoiceConfig(Parcel parcel) {
        this.mLocalVoiceId = parcel.readString();
        this.mHybridVoiceId = parcel.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mLocalVoiceId);
        parcel.writeString(this.mHybridVoiceId);
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getLocalVoiceId() {
        return this.mLocalVoiceId;
    }

    public String getHybridVoiceId() {
        return this.mHybridVoiceId;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (!TextUtils.isEmpty(this.mLocalVoiceId)) {
            jSONObject.put(VoiceType.LOCAL.getValue(), this.mLocalVoiceId);
        }
        if (!TextUtils.isEmpty(this.mHybridVoiceId)) {
            jSONObject.put(VoiceType.HYBRID.getValue(), this.mHybridVoiceId);
        }
        return jSONObject.toString();
    }

    public String toString() {
        return "VoiceConfig{mLocalVoiceId='" + this.mLocalVoiceId + "', mHybridVoiceId='" + this.mHybridVoiceId + "'}";
    }

    public static final class Builder {
        private String mHybridVoiceId;
        private String mLocalVoiceId;

        private Builder() {
        }

        public Builder setLocalVoiceId(String str) {
            this.mLocalVoiceId = str;
            return this;
        }

        public Builder setHybridVoiceId(String str) {
            this.mHybridVoiceId = str;
            return this;
        }

        public VoiceConfig build() {
            return new VoiceConfig(this);
        }
    }
}
