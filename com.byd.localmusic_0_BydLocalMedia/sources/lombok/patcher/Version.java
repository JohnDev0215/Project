package lombok.patcher;

/* JADX INFO: loaded from: com.byd.localmusic_0_BydLocalMedia.apk:lombok/patcher/Version.SCL.lombok */
public class Version {
    private static final String VERSION = "0.42";

    private Version() {
    }

    public static void main(String[] args) {
        System.out.println(VERSION);
    }

    public static String getVersion() {
        return VERSION;
    }
}
