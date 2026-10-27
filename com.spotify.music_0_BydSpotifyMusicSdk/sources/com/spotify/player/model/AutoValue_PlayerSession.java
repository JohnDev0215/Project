package com.spotify.player.model;

import com.spotify.player.model.PlayerSession;

/* JADX INFO: loaded from: classes.dex */
final class AutoValue_PlayerSession extends PlayerSession {

    public static final class Builder extends PlayerSession.Builder {
    }

    @Override // com.spotify.player.model.PlayerSession
    public final void a() {
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PlayerSession)) {
            return false;
        }
        ((PlayerSession) obj).a();
        throw null;
    }

    public final int hashCode() {
        throw null;
    }

    public final String toString() {
        return "PlayerSession{session=null}";
    }
}
