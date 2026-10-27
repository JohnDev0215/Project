package com.spotify.player.model.command;

import com.google.common.base.Optional;
import com.spotify.player.model.command.StopCommand;

/* JADX INFO: loaded from: classes.dex */
final class AutoValue_StopCommand extends StopCommand {
    public final Optional a;

    public static final class Builder extends StopCommand.Builder {
        public final Optional a = Optional.a();
    }

    public AutoValue_StopCommand(Optional optional) {
        this.a = optional;
    }

    @Override // com.spotify.player.model.command.StopCommand
    public final Optional b() {
        return this.a;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof StopCommand) {
            return this.a.equals(((StopCommand) obj).b());
        }
        return false;
    }

    public final int hashCode() {
        return this.a.hashCode() ^ 1000003;
    }

    public final String toString() {
        return "StopCommand{loggingParams=" + this.a + "}";
    }
}
