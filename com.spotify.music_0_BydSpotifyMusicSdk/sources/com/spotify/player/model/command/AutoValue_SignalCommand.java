package com.spotify.player.model.command;

import com.google.common.base.Optional;
import com.spotify.player.model.command.SignalCommand;

/* JADX INFO: loaded from: classes.dex */
final class AutoValue_SignalCommand extends SignalCommand {

    public static final class Builder extends SignalCommand.Builder {
        public Builder() {
            Optional.a();
            Optional.a();
            Optional.a();
        }
    }

    @Override // com.spotify.player.model.command.SignalCommand
    public final void a() {
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SignalCommand)) {
            return false;
        }
        ((SignalCommand) obj).a();
        throw null;
    }

    public final int hashCode() {
        throw null;
    }

    public final String toString() {
        return "SignalCommand{signalId=null, parameters=null, options=null, loggingParams=null}";
    }
}
