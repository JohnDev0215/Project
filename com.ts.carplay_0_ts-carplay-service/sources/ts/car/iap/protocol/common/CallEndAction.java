package ts.car.iap.protocol.common;

/* JADX INFO: loaded from: classes.dex */
public enum CallEndAction {
    ActionUnknown(-1),
    ActionEndOrDecline(0),
    ActionEndAll(1);

    private int mAction;

    CallEndAction(int action) {
        this.mAction = action;
    }

    public int getEndAction() {
        return this.mAction;
    }

    public static CallEndAction fromActionValue(int action) {
        for (CallEndAction value : values()) {
            if (value.getEndAction() == action) {
                return value;
            }
        }
        return ActionUnknown;
    }
}
