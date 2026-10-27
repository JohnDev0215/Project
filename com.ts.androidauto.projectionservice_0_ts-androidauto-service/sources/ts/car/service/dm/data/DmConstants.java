package ts.car.service.dm.data;

import com.ts.androidauto.fsdk.common.AndroidAutoConst;

/* JADX INFO: loaded from: classes2.dex */
public class DmConstants {

    public static final class ActivateServiceResult {
        public static final int FAILED = -1;
        public static final int SUCCESS = 0;
    }

    public static final class Capability {
        public static final int CAP_A2DP = 6;
        public static final int CAP_ANDROID_AUTO = 2;
        public static final int CAP_CARLIFE = 3;
        public static final int CAP_CARPLAY = 1;
        public static final int CAP_HFP = 5;
        public static final int CAP_HICAR = 4;
        public static final int CAP_NONE = 0;

        public static String getString(int state) {
            switch (state) {
                case 0:
                    return "CAP_NONE";
                case 1:
                    return "CAP_CARPLAY";
                case 2:
                    return "CAP_ANDROID_AUTO";
                case 3:
                    return "CAP_CARLIFE";
                case 4:
                    return "CAP_HICAR";
                case 5:
                    return "CAP_HFP";
                case 6:
                    return "CAP_A2DP";
                default:
                    return AndroidAutoConst.DEFAULT_SCREEN_NAME;
            }
        }
    }

    public static final class ConnectionType {
        public static final int CONNECTION_ALL = 0;
        public static final int CONNECTION_TYPE_USB = 1;
        public static final int CONNECTION_TYPE_WIRELESS = 2;

        public static String getString(int connectType) {
            if (connectType == 1) {
                return "CONNECTION_TYPE_USB";
            }
            if (connectType == 2) {
                return "CONNECTION_TYPE_WIRELESS";
            }
            return AndroidAutoConst.DEFAULT_SCREEN_NAME;
        }
    }

    public static final class CapabilityState {
        public static final int AVALIABLE = 1;
        public static final int CONNECTED = 4;
        public static final int CONNECTING = 3;
        public static final int DISCONNECTING = 5;
        public static final int INVALID = 0;
        public static final int UNAVALIABLE = 2;

        public static String getStateString(int state) {
            if (state == 0) {
                return "INVALID";
            }
            if (state == 1) {
                return "AVALIABLE";
            }
            if (state == 2) {
                return "UNAVALIABLE";
            }
            if (state == 3) {
                return "CONNECTING";
            }
            if (state == 4) {
                return "CONNECTED";
            }
            if (state == 5) {
                return "DISCONNECTING";
            }
            return AndroidAutoConst.DEFAULT_SCREEN_NAME;
        }
    }
}
