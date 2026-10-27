package cerence.ark.assistant.dm.data;

import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class PrompterVoice {
    private String[] mAvailableDomains;
    private String mGender;
    private String mLanguage;
    private String mOperatingPoint;
    private String mVersion;
    private String mVoiceId;
    private String mVoiceName;

    public PrompterVoice() {
    }

    public PrompterVoice(String str, String str2, String str3, String str4, String str5, String str6, String[] strArr) {
        this.mVoiceId = str;
        this.mLanguage = str2;
        this.mVoiceName = str3;
        this.mGender = str4;
        this.mOperatingPoint = str5;
        this.mVersion = str6;
        this.mAvailableDomains = strArr;
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

    public String toString() {
        return "PrompterVoice{mVoiceId='" + this.mVoiceId + "', mLanguage='" + this.mLanguage + "', mVoiceName='" + this.mVoiceName + "', mGender='" + this.mGender + "', mOperatingPoint='" + this.mOperatingPoint + "', mVersion='" + this.mVersion + "', mAvailableDomains=" + Arrays.toString(this.mAvailableDomains) + '}';
    }
}
