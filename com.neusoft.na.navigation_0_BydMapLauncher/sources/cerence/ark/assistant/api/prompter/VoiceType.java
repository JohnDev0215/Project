package cerence.ark.assistant.api.prompter;

import android.os.Parcel;
import android.os.Parcelable;
import cerence.ark.assistant.common.Logger;
import cerence.ark.assistant.library.Utils;
import com.google.android.gms.common.internal.ImagesContract;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public enum VoiceType implements Parcelable {
    LOCAL(ImagesContract.LOCAL),
    CLOUD("cloud"),
    HYBRID("hybrid");

    private final String mValue;
    public static final Parcelable.Creator<VoiceType> CREATOR = new Parcelable.Creator<VoiceType>() { // from class: cerence.ark.assistant.api.prompter.VoiceType.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public VoiceType createFromParcel(Parcel parcel) {
            return VoiceType.fromString(parcel.readString());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public VoiceType[] newArray(int i) {
            return new VoiceType[i];
        }
    };
    private static final Map<String, VoiceType> VOICE_TYPE_MAP = new HashMap(16);

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    static {
        for (VoiceType voiceType : values()) {
            VOICE_TYPE_MAP.put(voiceType.getValue(), voiceType);
        }
    }

    VoiceType(String str) {
        this.mValue = str;
    }

    public String getValue() {
        return this.mValue;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mValue);
    }

    public static VoiceType fromString(String str) {
        VoiceType voiceType = VOICE_TYPE_MAP.get(str);
        if (voiceType == null) {
            Logger.warn("VoiceType", Utils.format("fromString: voiceType not mapping VoiceType enum, value = %s", str));
        }
        return voiceType;
    }
}
