package com.spotify.player.model.command;

import com.google.common.base.Optional;
import com.spotify.player.model.command.PlayAsNextInQueueCommand;

/* JADX INFO: loaded from: classes.dex */
final class AutoValue_PlayAsNextInQueueCommand extends PlayAsNextInQueueCommand {

    public static final class Builder extends PlayAsNextInQueueCommand.Builder {
        public Builder() {
            Optional.a();
            Optional.a();
        }
    }

    @Override // com.spotify.player.model.command.PlayAsNextInQueueCommand
    public final void a() {
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PlayAsNextInQueueCommand)) {
            return false;
        }
        ((PlayAsNextInQueueCommand) obj).a();
        throw null;
    }

    public final int hashCode() {
        throw null;
    }

    public final String toString() {
        return "PlayAsNextInQueueCommand{tracks=null, options=null, loggingParams=null}";
    }
}
