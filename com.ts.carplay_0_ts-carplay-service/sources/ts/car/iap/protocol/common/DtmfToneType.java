package ts.car.iap.protocol.common;

/* JADX INFO: loaded from: classes.dex */
public enum DtmfToneType {
    DtmfToneUnknown(-1),
    DtmfToneNumber0(0),
    DtmfToneNumber1(1),
    DtmfToneNumber2(2),
    DtmfToneNumber3(3),
    DtmfToneNumber4(4),
    DtmfToneNumber5(5),
    DtmfToneNumber6(6),
    DtmfToneNumber7(7),
    DtmfToneNumber8(8),
    DtmfToneNumber9(9),
    DtmfToneStar(10),
    DtmfTonePound(11);

    private int mTone;

    DtmfToneType(int tone) {
        this.mTone = tone;
    }

    public int getDtmfTone() {
        return this.mTone;
    }

    public static DtmfToneType fromToneValue(int tone) {
        for (DtmfToneType value : values()) {
            if (value.getDtmfTone() == tone) {
                return value;
            }
        }
        return DtmfToneUnknown;
    }
}
