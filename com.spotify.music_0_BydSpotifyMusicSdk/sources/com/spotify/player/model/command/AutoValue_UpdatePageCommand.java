package com.spotify.player.model.command;

import com.spotify.player.model.command.UpdatePageCommand;

/* JADX INFO: loaded from: classes.dex */
final class AutoValue_UpdatePageCommand extends UpdatePageCommand {

    public static final class Builder extends UpdatePageCommand.Builder {
    }

    @Override // com.spotify.player.model.command.UpdatePageCommand
    public final void a() {
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof UpdatePageCommand)) {
            return false;
        }
        ((UpdatePageCommand) obj).a();
        throw null;
    }

    public final int hashCode() {
        throw null;
    }

    public final String toString() {
        return "UpdatePageCommand{page=null}";
    }
}
