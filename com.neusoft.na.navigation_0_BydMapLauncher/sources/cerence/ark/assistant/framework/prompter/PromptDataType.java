package cerence.ark.assistant.framework.prompter;

import android.os.Parcel;
import android.os.Parcelable;
import cerence.ark.assistant.common.Logger;
import cerence.ark.assistant.library.Utils;
import java.util.HashMap;
import java.util.Map;
import org.bouncycastle.i18n.TextBundle;

/* JADX INFO: loaded from: classes2.dex */
public enum PromptDataType implements Parcelable {
    TEXT(TextBundle.TEXT_ENTRY),
    SSML("ssml"),
    AUDIO_FILE("audio_file");

    public static final Parcelable.Creator<PromptDataType> CREATOR = new Parcelable.Creator<PromptDataType>() { // from class: cerence.ark.assistant.framework.prompter.PromptDataType.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PromptDataType createFromParcel(Parcel parcel) {
            return PromptDataType.fromString(parcel.readString());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PromptDataType[] newArray(int i) {
            return new PromptDataType[i];
        }
    };
    private static final Map<String, PromptDataType> PROMPTER_DATA_TYPE_MAP = new HashMap(16);
    private final String mValue;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    static {
        for (PromptDataType promptDataType : values()) {
            PROMPTER_DATA_TYPE_MAP.put(promptDataType.getValue(), promptDataType);
        }
    }

    PromptDataType(String str) {
        this.mValue = str;
    }

    public String getValue() {
        return this.mValue;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mValue);
    }

    public static PromptDataType fromString(String str) {
        PromptDataType promptDataType = PROMPTER_DATA_TYPE_MAP.get(str);
        if (promptDataType == null) {
            Logger.warn("PromptDataType", Utils.format("fromString: promptDataType not mapping PromptDataType enum, value = %s", str));
        }
        return promptDataType;
    }
}
