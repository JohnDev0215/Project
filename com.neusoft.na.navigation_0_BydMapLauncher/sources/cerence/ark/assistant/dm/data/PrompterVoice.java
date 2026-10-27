package cerence.ark.assistant.dm.data;

import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class PrompterVoice {
    private String[] mAvailableDomains;
    private String mGender;
    private String mLanguage;
    private String mOperatingPoint;
    private String mSpeakerName;
    private boolean mSupportedBySds;
    private String mType;
    private String mVersion;
    private String mVoiceId;
    private String mVoiceName;

    public PrompterVoice() {
    }

    public PrompterVoice(String str, String str2, String str3, String str4, String str5, String str6, String str7, String[] strArr, String str8, boolean z) {
        this.mVoiceId = str;
        this.mLanguage = str2;
        this.mSpeakerName = str3;
        this.mVoiceName = str4;
        this.mGender = str5;
        this.mOperatingPoint = str6;
        this.mVersion = str7;
        this.mAvailableDomains = strArr;
        this.mType = str8;
        this.mSupportedBySds = z;
    }

    public String getVoiceId() {
        return this.mVoiceId;
    }

    public void setVoiceId(String str) {
        this.mVoiceId = str;
    }

    public String getLanguage() {
        return this.mLanguage;
    }

    public void setLanguage(String str) {
        this.mLanguage = str;
    }

    public String getSpeakerName() {
        return this.mSpeakerName;
    }

    public void setSpeakerName(String str) {
        this.mSpeakerName = str;
    }

    public String getVoiceName() {
        return this.mVoiceName;
    }

    public void setVoiceName(String str) {
        this.mVoiceName = str;
    }

    public String getGender() {
        return this.mGender;
    }

    public void setGender(String str) {
        this.mGender = str;
    }

    public String getOperatingPoint() {
        return this.mOperatingPoint;
    }

    public void setOperatingPoint(String str) {
        this.mOperatingPoint = str;
    }

    public String getVersion() {
        return this.mVersion;
    }

    public void setVersion(String str) {
        this.mVersion = str;
    }

    public String[] getAvailableDomains() {
        return this.mAvailableDomains;
    }

    public void setAvailableDomains(String[] strArr) {
        this.mAvailableDomains = strArr;
    }

    public String getType() {
        return this.mType;
    }

    public void setType(String str) {
        this.mType = str;
    }

    public boolean getSupportedBySds() {
        return this.mSupportedBySds;
    }

    public void setSupportedBySds(boolean z) {
        this.mSupportedBySds = z;
    }

    public String toString() {
        return "PrompterVoice{mVoiceId='" + this.mVoiceId + "', mLanguage='" + this.mLanguage + "', mVoiceName='" + this.mVoiceName + "', mGender='" + this.mGender + "', mOperatingPoint='" + this.mOperatingPoint + "', mVersion='" + this.mVersion + "', mAvailableDomains=" + Arrays.toString(this.mAvailableDomains) + '}';
    }
}
