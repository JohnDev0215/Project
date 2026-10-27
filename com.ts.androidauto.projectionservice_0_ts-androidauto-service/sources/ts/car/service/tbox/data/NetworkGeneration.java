package ts.car.service.tbox.data;

/* JADX INFO: loaded from: classes2.dex */
public final class NetworkGeneration {
    public static final int NETWORK_GENERATION_2G = 1;
    public static final int NETWORK_GENERATION_3G = 2;
    public static final int NETWORK_GENERATION_4G = 3;
    public static final int NETWORK_GENERATION_5G = 4;
    public static final int NETWORK_GENERATION_UNKNOW = 0;

    public static String toString(int networkGeneration) {
        if (networkGeneration == 0) {
            return "NETWORK_GENERATION_UNKNOW";
        }
        if (networkGeneration == 1) {
            return "NETWORK_GENERATION_2G";
        }
        if (networkGeneration == 2) {
            return "NETWORK_GENERATION_3G";
        }
        if (networkGeneration == 3) {
            return "NETWORK_GENERATION_4G";
        }
        if (networkGeneration == 4) {
            return "NETWORK_GENERATION_5G";
        }
        return "0x" + Integer.toHexString(networkGeneration);
    }
}
