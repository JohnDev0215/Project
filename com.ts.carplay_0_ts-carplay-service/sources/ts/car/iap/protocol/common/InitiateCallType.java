package ts.car.iap.protocol.common;

/* JADX INFO: loaded from: classes.dex */
public enum InitiateCallType {
    CallTypeUnknown(-1),
    CallTypeDestination(0),
    CallTypeVoicemail(1),
    CallTypeRedial(2);

    private int mType;

    InitiateCallType(int type) {
        this.mType = type;
    }

    public int getCallType() {
        return this.mType;
    }

    public static InitiateCallType fromTypeValue(int type) {
        for (InitiateCallType value : values()) {
            if (value.getCallType() == type) {
                return value;
            }
        }
        return CallTypeUnknown;
    }
}
