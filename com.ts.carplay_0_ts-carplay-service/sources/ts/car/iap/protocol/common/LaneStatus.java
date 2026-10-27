package ts.car.iap.protocol.common;

/* JADX INFO: loaded from: classes.dex */
public enum LaneStatus {
    LANE_SATUS_NOT_GOOD(0),
    LANE_SATUS_GOOD(1),
    LANE_SATUS_PREFERRED(2);

    private int mLaneStatus;

    LaneStatus(int status) {
        this.mLaneStatus = status;
    }

    public static LaneStatus getLaneStatus(int status) {
        if (status > 2 || status < 0) {
            return null;
        }
        if (status == 0) {
            return LANE_SATUS_NOT_GOOD;
        }
        if (status == 1) {
            return LANE_SATUS_GOOD;
        }
        return LANE_SATUS_PREFERRED;
    }

    @Override // java.lang.Enum
    public String toString() {
        if (equals(LANE_SATUS_NOT_GOOD)) {
            return "LANE_SATUS_NOT_GOOD";
        }
        if (equals(LANE_SATUS_GOOD)) {
            return "LANE_SATUS_GOOD";
        }
        return "LANE_SATUS_PREFERRED";
    }
}
