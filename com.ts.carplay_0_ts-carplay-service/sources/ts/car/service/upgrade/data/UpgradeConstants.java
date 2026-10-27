package ts.car.service.upgrade.data;

/* JADX INFO: loaded from: classes.dex */
public class UpgradeConstants {

    public static final class ErrCode {
        public static final String[] DESCRIPTIONS = {"Success", "Already updating", "Package not exists", "Package error", "Internal error", "Update timeout"};
        public static final int INTERNAL_ERROR = 4;
        public static final int PACKAGE_ERROR = 3;
        public static final int PACKAGE_NOT_EXIST = 2;
        public static final int SUCCESS = 0;
        public static final int UNKNOWN = -1;
        public static final int UPDATE_STARTED = 1;
        public static final int UPDATE_TIMEOUT = 5;
    }

    public static final class Module {
        public static final String SOC = "SOC";
    }

    public static final class SocStreamingExtras {
        public static final String OFFSET = "extra.payload.offset";
        public static final String PROPS = "extra.payload.props";
        public static final int PROPS_LENGTH = 4;
        public static final String SIZE = "extra.payload.size";
    }

    public static final class Status {
        public static final String[] DESCRIPTIONS = {"Start", "Updated", "Fail", "Success", "Retry", "Resume"};
        public static final int FAIL = 2;
        public static final int RESUME = 5;
        public static final int RETRY = 4;
        public static final int START = 0;
        public static final int SUCC = 3;
        public static final int UNKNOWN = -1;
        public static final int UPGRADED = 1;
    }

    public static final class Trigger {
        public static final int DEBUG_LOCAL = 1;
        public static final int DEBUG_REMOTE = 2;
        public static final String[] DESCRIPTIONS = {"USB", "DEBUG_LOCAL", "DEBUG_REMOTE", "OTHERS"};
        public static final int OTHERS = 3;
        public static final int USB = 0;
    }

    public static final class Type {
        public static final String[] DESCRIPTIONS = {"Local", "Streaming"};
        public static final int LOCAL = 0;
        public static final int STREAMING = 1;
    }
}
