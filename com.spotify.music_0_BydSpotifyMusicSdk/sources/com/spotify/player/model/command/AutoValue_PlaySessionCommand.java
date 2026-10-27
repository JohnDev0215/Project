package com.spotify.player.model.command;

import com.google.common.base.Optional;
import com.spotify.player.model.command.PlaySessionCommand;

/* JADX INFO: loaded from: classes.dex */
final class AutoValue_PlaySessionCommand extends PlaySessionCommand {

    public static final class Builder extends PlaySessionCommand.Builder {
        public Builder() {
            Optional.a();
            Optional.a();
        }
    }

    @Override // com.spotify.player.model.command.PlaySessionCommand
    public final Optional a() {
        return null;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PlaySessionCommand)) {
            return false;
        }
        ((PlaySessionCommand) obj).a();
        throw null;
    }

    public final int hashCode() {
        throw null;
    }

    public final String toString() {
        return "PlaySessionCommand{playOptions=null, loggingParams=null}";
    }
}
