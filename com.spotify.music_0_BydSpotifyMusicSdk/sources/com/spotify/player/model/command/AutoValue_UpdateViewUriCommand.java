package com.spotify.player.model.command;

import com.spotify.player.model.command.UpdateViewUriCommand;

/* JADX INFO: loaded from: classes.dex */
final class AutoValue_UpdateViewUriCommand extends UpdateViewUriCommand {

    public static final class Builder extends UpdateViewUriCommand.Builder {
    }

    @Override // com.spotify.player.model.command.UpdateViewUriCommand
    public final void a() {
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof UpdateViewUriCommand)) {
            return false;
        }
        ((UpdateViewUriCommand) obj).a();
        throw null;
    }

    public final int hashCode() {
        throw null;
    }

    public final String toString() {
        return "UpdateViewUriCommand{viewUri=null}";
    }
}
