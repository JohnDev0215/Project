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

    public String getMccMnc() {
        return this.mccMnc;
    }

    public void setMccMnc(String str) {
        this.mccMnc = str;
    }

    public void setEnable(int i) {
        this.enable = i;
    }

    public int getEnable() {
        return this.enable;
    }

    public void setSigLevel(int i) {
        this.sigLevel = i;
    }

    public int getSigLevel() {
        return this.sigLevel;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("NetworkOperator{mccMnc='");
        stringBuffer.append(this.mccMnc).append("', enable=");
        stringBuffer.append(this.enable);
        stringBuffer.append(", sigLevel=").append(this.sigLevel);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }
}
