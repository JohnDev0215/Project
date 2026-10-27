package com.engine.tbox.core.conn;

/* JADX INFO: loaded from: classes.dex */
public enum ConnectionState {
    IDLE,
    CONNECTING,
    CONNECTED,
    DISCONNECTING,
    DISCONNECTED;

    /* JADX INFO: renamed from: com.engine.tbox.core.conn.ConnectionState$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$engine$tbox$core$conn$ConnectionState;

        static {
            int[] iArr = new int[ConnectionState.values().length];
            $SwitchMap$com$engine$tbox$core$conn$ConnectionState = iArr;
            try {
                iArr[ConnectionState.IDLE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$engine$tbox$core$conn$ConnectionState[ConnectionState.CONNECTING.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$engine$tbox$core$conn$ConnectionState[ConnectionState.CONNECTED.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$engine$tbox$core$conn$ConnectionState[ConnectionState.DISCONNECTING.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$engine$tbox$core$conn$ConnectionState[ConnectionState.DISCONNECTED.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
        }
    }

    public static String stateToString(ConnectionState state) {
        int i = AnonymousClass1.$SwitchMap$com$engine$tbox$core$conn$ConnectionState[state.ordinal()];
        if (i == 2) {
            return "CONNECTING";
        }
        if (i == 3) {
            return "CONNECTED";
        }
        if (i == 4) {
            return "DISCONNECTING";
        }
        if (i == 5) {
            return "DISCONNECTED";
        }
        return "IDLE";
    }
}
