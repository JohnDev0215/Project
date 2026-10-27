package ts.car.iap.protocol.common;

/* JADX INFO: loaded from: classes.dex */
public enum InitiateCallService {
    CallServiceUnknown(-1),
    CallServiceTelephony(1),
    CallServiceFaceTimeAudio(2),
    CallServiceFaceTimeVideo(3);

    private int mService;

    InitiateCallService(int service) {
        this.mService = service;
    }

    public int getCallService() {
        return this.mService;
    }

    public static InitiateCallService fromServiceValue(int service) {
        for (InitiateCallService value : values()) {
            if (value.getCallService() == service) {
                return value;
            }
        }
        return CallServiceUnknown;
    }
}
