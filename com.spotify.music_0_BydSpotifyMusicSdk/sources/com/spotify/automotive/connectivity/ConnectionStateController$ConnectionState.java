package com.spotify.automotive.connectivity;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class ConnectionStateController$ConnectionState {
    public static final ConnectionStateController$ConnectionState c;
    public static final ConnectionStateController$ConnectionState e;
    public static final ConnectionStateController$ConnectionState k;
    public static final /* synthetic */ ConnectionStateController$ConnectionState[] l;

    static {
        ConnectionStateController$ConnectionState connectionStateController$ConnectionState = new ConnectionStateController$ConnectionState("UNKNOWN", 0);
        c = connectionStateController$ConnectionState;
        ConnectionStateController$ConnectionState connectionStateController$ConnectionState2 = new ConnectionStateController$ConnectionState("DISCONNECTED", 1);
        e = connectionStateController$ConnectionState2;
        ConnectionStateController$ConnectionState connectionStateController$ConnectionState3 = new ConnectionStateController$ConnectionState("CONNECTED", 2);
        k = connectionStateController$ConnectionState3;
        l = new ConnectionStateController$ConnectionState[]{connectionStateController$ConnectionState, connectionStateController$ConnectionState2, connectionStateController$ConnectionState3};
    }

    public static ConnectionStateController$ConnectionState valueOf(String str) {
        return (ConnectionStateController$ConnectionState) Enum.valueOf(ConnectionStateController$ConnectionState.class, str);
    }

    public static ConnectionStateController$ConnectionState[] values() {
        return (ConnectionStateController$ConnectionState[]) l.clone();
    }
}
