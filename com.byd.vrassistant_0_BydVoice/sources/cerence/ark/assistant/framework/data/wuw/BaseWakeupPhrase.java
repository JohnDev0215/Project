package cerence.ark.assistant.framework.data.wuw;

import android.os.Parcel;
import android.os.Parcelable;
import cerence.ark.assistant.core.JsonCompliant;
import cerence.ark.assistant.library.JsonKey;
import java.util.Objects;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class BaseWakeupPhrase implements Parcelable, JsonCompliant {
    public static final Parcelable.Creator<BaseWakeupPhrase> CREATOR = new Parcelable.Creator<BaseWakeupPhrase>() { // from class: cerence.ark.assistant.framework.data.wuw.BaseWakeupPhrase.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BaseWakeupPhrase createFromParcel(Parcel parcel) {
            return new BaseWakeupPhrase(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BaseWakeupPhrase[] newArray(int i) {
            return new BaseWakeupPhrase[i];
        }
    };
    protected String mPhrase;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    protected BaseWakeupPhrase(Builder builder) {
        this.mPhrase = builder.mPhrase;
    }

    protected BaseWakeupPhrase(Parcel parcel) {
        this.mPhrase = parcel.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mPhrase);
    }

    public String getPhrase() {
        return this.mPhrase;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        return toJSONObject().toString();
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public JSONObject toJSONObject() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(JsonKey.WUW_ORTHOGRAPHY, this.mPhrase);
        return jSONObject;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof BaseWakeupPhrase)) {
            return false;
        }
        BaseWakeupPhrase baseWakeupPhrase = (BaseWakeupPhrase) obj;
        String str = this.mPhrase;
        return str != null && str.equals(baseWakeupPhrase.getPhrase());
    }

    public int hashCode() {
        return Objects.hashCode(this.mPhrase);
    }

    public String toString() {
        return "BaseWakeupPhrase{phrase: " + this.mPhrase + "}";
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        protected String mPhrase = "";

        public Builder setPhrase(String str) {
            if (str == null) {
                return this;
            }
            this.mPhrase = str;
            return this;
        }

        public BaseWakeupPhrase build() {
            return new BaseWakeupPhrase(this);
        }
    }
}
