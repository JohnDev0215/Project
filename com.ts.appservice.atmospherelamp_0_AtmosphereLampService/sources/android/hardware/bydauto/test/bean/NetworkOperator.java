package android.hardware.bydauto.test.bean;

/* JADX INFO: loaded from: classes.dex */
public class NetworkOperator {
    private int enable;
    private String mccMnc;
    private int sigLevel;
    public static int SIG_LEVEL_INVALID = 0;
    public static int SIG_LEVEL_2G = 1;
    public static int SIG_LEVEL_3G = 2;
    public static int SIG_LEVEL_4G = 3;

    public String getMccMnc() {
        return this.mccMnc;
    }

    public void setMccMnc(String mccMnc) {
        this.mccMnc = mccMnc;
    }

    public void setEnable(int enable) {
        this.enable = enable;
    }

    public int getEnable() {
        return this.enable;
    }

    public void setSigLevel(int level) {
        this.sigLevel = level;
    }

    public int getSigLevel() {
        return this.sigLevel;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer("NetworkOperator{");
        sb.append("mccMnc='");
        sb.append(this.mccMnc);
        sb.append('\'');
        sb.append(", enable=");
        sb.append(this.enable);
        sb.append(", sigLevel=");
        sb.append(this.sigLevel);
        sb.append('}');
        return sb.toString();
    }
}
