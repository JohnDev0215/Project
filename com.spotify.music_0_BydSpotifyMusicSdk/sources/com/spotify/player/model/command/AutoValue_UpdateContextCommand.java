package com.spotify.player.model.command;

import com.spotify.player.model.Context;
import com.spotify.player.model.command.UpdateContextCommand;

/* JADX INFO: loaded from: classes.dex */
final class AutoValue_UpdateContextCommand extends UpdateContextCommand {

    public static final class Builder extends UpdateContextCommand.Builder {
    }

    @Override // com.spotify.player.model.command.UpdateContextCommand
    public final Context a() {
        return null;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof UpdateContextCommand)) {
            return false;
        }
        ((UpdateContextCommand) obj).a();
        throw null;
    }

    public final int hashCode() {
        throw null;
    }

    public final String toString() {
        return "UpdateContextCommand{context=null}";
    }
}
