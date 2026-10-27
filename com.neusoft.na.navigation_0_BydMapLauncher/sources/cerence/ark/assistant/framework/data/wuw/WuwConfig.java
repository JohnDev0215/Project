package cerence.ark.assistant.framework.data.wuw;

import android.os.Parcel;
import android.os.Parcelable;
import cerence.ark.assistant.common.Logger;
import cerence.ark.assistant.core.JsonCompliant;
import cerence.ark.assistant.library.JsonKey;
import cerence.ark.assistant.library.Utils;
import cerence.ark.assistant.library.exception.ArkAssistantException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class WuwConfig implements Parcelable, JsonCompliant {
    public static final Parcelable.Creator<WuwConfig> CREATOR = new Parcelable.Creator<WuwConfig>() { // from class: cerence.ark.assistant.framework.data.wuw.WuwConfig.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WuwConfig createFromParcel(Parcel parcel) {
            return new WuwConfig(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WuwConfig[] newArray(int i) {
            return new WuwConfig[i];
        }
    };
    private static final String TAG = "WuwConfig";
    private final List<BaseWakeupPhrase> mAntiWuwList;
    private final List<WakeupPhrase> mCustomizedWuwList;
    private final List<WakeupPhrase> mPredefinedWuwList;
    private final List<WakeupPhrase> mShortcutList;
    private final List<WakeupPhrase> mVpaWuwList;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    private WuwConfig(Builder builder) {
        this.mPredefinedWuwList = builder.mPredefinedWuwList;
        this.mCustomizedWuwList = builder.mCustomizedWuwList;
        this.mShortcutList = builder.mShortcutList;
        this.mVpaWuwList = builder.mVpaWuwList;
        this.mAntiWuwList = builder.mAntiWuwList;
    }

    protected WuwConfig(Parcel parcel) {
        this.mPredefinedWuwList = parcel.createTypedArrayList(WakeupPhrase.CREATOR);
        this.mCustomizedWuwList = parcel.createTypedArrayList(WakeupPhrase.CREATOR);
        this.mShortcutList = parcel.createTypedArrayList(WakeupPhrase.CREATOR);
        this.mVpaWuwList = parcel.createTypedArrayList(WakeupPhrase.CREATOR);
        this.mAntiWuwList = parcel.createTypedArrayList(BaseWakeupPhrase.CREATOR);
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(this.mPredefinedWuwList);
        parcel.writeTypedList(this.mCustomizedWuwList);
        parcel.writeTypedList(this.mShortcutList);
        parcel.writeTypedList(this.mVpaWuwList);
        parcel.writeTypedList(this.mAntiWuwList);
    }

    public List<WakeupPhrase> getPredefinedWuwList() {
        return this.mPredefinedWuwList;
    }

    public List<WakeupPhrase> getCustomizedWuwList() {
        return this.mCustomizedWuwList;
    }

    public List<WakeupPhrase> getShortcutList() {
        return this.mShortcutList;
    }

    public List<WakeupPhrase> getVpaWuwList() {
        return this.mVpaWuwList;
    }

    public List<BaseWakeupPhrase> getAntiWuwList() {
        return this.mAntiWuwList;
    }

    public String toString() {
        return "WuwConfig{predefinedWuwList: " + this.mPredefinedWuwList + ", customizedWuwList: " + this.mCustomizedWuwList + ", shortcutList: " + this.mShortcutList + ", vpaWuwList: " + this.mVpaWuwList + ", antiWuwList: " + this.mAntiWuwList + "}";
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() {
        JSONObject jSONObject = new JSONObject();
        try {
            List<WakeupPhrase> list = this.mPredefinedWuwList;
            if (list != null) {
                jSONObject.put(JsonKey.WUW_TYPE_SDS, getJSONArray(list));
            }
            List<WakeupPhrase> list2 = this.mCustomizedWuwList;
            if (list2 != null) {
                jSONObject.put(JsonKey.WUW_TYP_CUSTOMIZED, getJSONArray(list2));
            }
            List<WakeupPhrase> list3 = this.mShortcutList;
            if (list3 != null) {
                jSONObject.put(JsonKey.WUW_TYP_SHORTCUT, getJSONArray(list3));
            }
            List<WakeupPhrase> list4 = this.mVpaWuwList;
            if (list4 != null) {
                jSONObject.put(JsonKey.WUW_TYP_VPA, getJSONArray(list4));
            }
            List<BaseWakeupPhrase> list5 = this.mAntiWuwList;
            if (list5 != null) {
                jSONObject.put(JsonKey.WUW_TYP_ANTI, getJSONArray(list5));
            }
        } catch (JSONException e) {
            Logger.error(TAG, "json exception", e);
        }
        return jSONObject.toString();
    }

    private static <T extends BaseWakeupPhrase> JSONArray getJSONArray(List<T> list) throws JSONException {
        JSONArray jSONArray = new JSONArray();
        int i = 0;
        int i2 = 1;
        while (i < list.size()) {
            T t = list.get(i);
            if (t == null) {
                Logger.warn(TAG, Utils.format("wuw is null, index = %s", Integer.valueOf(i)));
            } else {
                JSONObject jSONObject = t.toJSONObject();
                jSONObject.put("id", i2);
                jSONArray.put(jSONObject);
            }
            i++;
            i2++;
        }
        return jSONArray;
    }

    public static final class Builder {
        private List<BaseWakeupPhrase> mAntiWuwList;
        private List<WakeupPhrase> mCustomizedWuwList;
        private List<WakeupPhrase> mPredefinedWuwList;
        private List<WakeupPhrase> mShortcutList;
        private List<WakeupPhrase> mVpaWuwList;

        private Builder() {
        }

        public Builder setPredefinedWuwList(List<WakeupPhrase> list) {
            if (list == null) {
                throw new ArkAssistantException("The parameter predefinedWuwList cannot be null");
            }
            this.mPredefinedWuwList = new ArrayList(list);
            return this;
        }

        public Builder setCustomizedWuwList(List<WakeupPhrase> list) {
            if (list == null) {
                throw new ArkAssistantException("The parameter customizedWuwList cannot be null");
            }
            this.mCustomizedWuwList = new ArrayList(list);
            return this;
        }

        public Builder setShortcutList(List<WakeupPhrase> list) {
            if (list == null) {
                throw new ArkAssistantException("The parameter shortcutList cannot be null");
            }
            this.mShortcutList = new ArrayList(list);
            return this;
        }

        public Builder setVpaWuwList(List<WakeupPhrase> list) {
            if (list == null) {
                throw new ArkAssistantException("The parameter vpaWuwList cannot be null");
            }
            this.mVpaWuwList = new ArrayList(list);
            return this;
        }

        public Builder setAntiWuwList(List<BaseWakeupPhrase> list) {
            if (list == null) {
                throw new ArkAssistantException("The parameter antiWuwList cannot be null");
            }
            this.mAntiWuwList = new ArrayList(list);
            return this;
        }

        public WuwConfig build() {
            return new WuwConfig(this);
        }
    }
}
