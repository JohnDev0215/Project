package android.hardware.bydauto.test.bean;

/* JADX INFO: loaded from: classes.dex */
public class NetworkOperator {
    public static int SIG_LEVEL_2G = 1;
    public static int SIG_LEVEL_3G = 2;
    public static int SIG_LEVEL_4G = 3;
    public static int SIG_LEVEL_INVALID;
    private int enable;
    private String mccMnc;
    private int sigLevel;

    public int getEnable() {
        return this.enable;
    }

    public String getMccMnc() {
        return this.mccMnc;
    }

    public int getSigLevel() {
        return this.sigLevel;
    }

    public void setEnable(int i3) {
        this.enable = i3;
    }

    public void setMccMnc(String str) {
        this.mccMnc = str;
    }

    public void setSigLevel(int i3) {
        this.sigLevel = i3;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("NetworkOperator{");
        stringBuffer.append("mccMnc='");
        stringBuffer.append(this.mccMnc);
        stringBuffer.append('\'');
        stringBuffer.append(", enable=");
        stringBuffer.append(this.enable);
        stringBuffer.append(", sigLevel=");
        stringBuffer.append(this.sigLevel);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }
}
