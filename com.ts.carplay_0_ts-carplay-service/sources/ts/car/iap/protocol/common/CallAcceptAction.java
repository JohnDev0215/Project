package ts.car.iap.protocol.common;

/* JADX INFO: loaded from: classes.dex */
public enum CallAcceptAction {
    ActionUnknown(-1),
    ActionAcceptOrHoldAndAccept(0),
    ActionEndAndAccept(1);

    private int mAction;

    CallAcceptAction(int action) {
        this.mAction = action;
    }

    public int getAcceptAction() {
        return this.mAction;
    }

    public static CallAcceptAction fromActionValue(int action) {
        for (CallAcceptAction value : values()) {
            if (value.getAcceptAction() == action) {
                return value;
            }
        }
        return ActionUnknown;
    }
}
