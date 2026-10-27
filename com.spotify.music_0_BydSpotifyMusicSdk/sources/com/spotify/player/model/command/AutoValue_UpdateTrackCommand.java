package com.spotify.player.model.command;

import com.spotify.player.model.ContextTrack;
import com.spotify.player.model.command.UpdateTrackCommand;

/* JADX INFO: loaded from: classes.dex */
final class AutoValue_UpdateTrackCommand extends UpdateTrackCommand {

    public static final class Builder extends UpdateTrackCommand.Builder {
    }

    @Override // com.spotify.player.model.command.UpdateTrackCommand
    public final ContextTrack a() {
        return null;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof UpdateTrackCommand)) {
            return false;
        }
        ((UpdateTrackCommand) obj).a();
        throw null;
    }

    public final int hashCode() {
        throw null;
    }

    public final String toString() {
        return "UpdateTrackCommand{track=null}";
    }
}
