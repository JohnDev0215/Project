package com.spotify.connectivity.platformconnectiontype;

import com.spotify.connectivity.connectiontype.ConnectionState;
import com.spotify.connectivity.connectiontype.OfflineReason;
import com.spotify.connectivity.connectiontype.OfflineState;
import io.reactivex.rxjava3.core.Observable;
import p.lj;

/* JADX INFO: loaded from: classes.dex */
public final class ConnectionStateObservable {

    /* JADX INFO: renamed from: com.spotify.connectivity.platformconnectiontype.ConnectionStateObservable$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$spotify$connectivity$connectiontype$OfflineState$State;

        static {
            int[] iArr = new int[OfflineState.State.values().length];
            $SwitchMap$com$spotify$connectivity$connectiontype$OfflineState$State = iArr;
            try {
                iArr[OfflineState.State.OFFLINE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$spotify$connectivity$connectiontype$OfflineState$State[OfflineState.State.ONLINE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$spotify$connectivity$connectiontype$OfflineState$State[OfflineState.State.RECONNECTING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private ConnectionStateObservable() {
    }

    public static Observable<ConnectionState> create(Observable<OfflineState.State> observable, Observable<Boolean> observable2) {
        return Observable.combineLatest(observable, observable2, new lj(3)).distinctUntilChanged();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ConnectionState toConnectionState(OfflineState.State state, boolean z) {
        if (state == OfflineState.State.FORCED_OFFLINE) {
            return new ConnectionState.Offline(OfflineReason.FORCED_OFFLINE);
        }
        if (!z) {
            return new ConnectionState.Offline(OfflineReason.NO_INTERNET);
        }
        int i = AnonymousClass1.$SwitchMap$com$spotify$connectivity$connectiontype$OfflineState$State[state.ordinal()];
        return i != 1 ? i != 2 ? ConnectionState.Connecting.INSTANCE : ConnectionState.Online.INSTANCE : new ConnectionState.Offline(OfflineReason.AP_CONNECTION_PROBLEM);
    }
}
