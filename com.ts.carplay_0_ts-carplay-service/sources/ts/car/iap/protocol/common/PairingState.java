package ts.car.iap.protocol.common;

/* JADX INFO: loaded from: classes.dex */
public enum PairingState {
    PairingSuccess(0),
    PairingAlreadyConnected(1),
    PairingCancel(2);

    int mPairingState;

    PairingState(int state) {
        this.mPairingState = state;
    }

    public static PairingState getPairingState(int state) {
        if (state == 0) {
            return PairingSuccess;
        }
        if (state == 1) {
            return PairingAlreadyConnected;
        }
        return PairingCancel;
    }
}
