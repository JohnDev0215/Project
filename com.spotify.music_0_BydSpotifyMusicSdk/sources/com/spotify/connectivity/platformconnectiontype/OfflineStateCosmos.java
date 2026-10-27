package com.spotify.connectivity.platformconnectiontype;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.spotify.connectivity.connectiontype.OfflineState;
import p.h71;
import p.l71;
import p.q71;
import p.rb2;
import p.v71;

/* JADX INFO: loaded from: classes.dex */
public abstract class OfflineStateCosmos implements OfflineState {

    public static class OfflineStateJsonDeserializer extends h71 {
        @Override // p.h71
        public OfflineState deserialize(q71 q71Var, DeserializationContext deserializationContext) {
            q71Var.E();
            throw new IllegalStateException("No ObjectCodec defined for parser, needed for deserialization");
        }
    }

    public static class OfflineStateJsonSerializer extends v71 {
        public void serialize(OfflineState offlineState, l71 l71Var, rb2 rb2Var) {
            l71Var.q();
            l71Var.i("connection");
            l71Var.q();
            boolean z = offlineState.offlineState() == OfflineState.State.FORCED_OFFLINE;
            l71Var.i("force_offline");
            l71Var.b(z);
            l71Var.c();
            l71Var.c();
        }
    }

    public static OfflineStateCosmos create(OfflineState.State state) {
        return new AutoValue_OfflineStateCosmos(state);
    }

    @Override // com.spotify.connectivity.connectiontype.OfflineState
    public boolean offline() {
        return OfflineState.State.ONLINE != offlineState();
    }

    @Override // com.spotify.connectivity.connectiontype.OfflineState
    public abstract OfflineState.State offlineState();
}
