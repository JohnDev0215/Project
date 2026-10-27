package com.spotify.player.model.command;

import com.google.common.base.Optional;
import com.spotify.player.model.ContextTrack;
import com.spotify.player.model.command.AddToQueueCommand;

/* JADX INFO: loaded from: classes.dex */
final class AutoValue_AddToQueueCommand extends AddToQueueCommand {
    public final ContextTrack a;
    public final Optional b;
    public final Optional c;

    public static final class Builder extends AddToQueueCommand.Builder {
        public final Optional a = Optional.a();
        public final Optional b = Optional.a();
    }

    public AutoValue_AddToQueueCommand(ContextTrack contextTrack, Optional optional, Optional optional2) {
        this.a = contextTrack;
        this.b = optional;
        this.c = optional2;
    }

    @Override // com.spotify.player.model.command.AddToQueueCommand
    public final Optional b() {
        return this.c;
    }

    @Override // com.spotify.player.model.command.AddToQueueCommand
    public final Optional c() {
        return this.b;
    }

    @Override // com.spotify.player.model.command.AddToQueueCommand
    public final ContextTrack d() {
        return this.a;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AddToQueueCommand)) {
            return false;
        }
        AddToQueueCommand addToQueueCommand = (AddToQueueCommand) obj;
        return this.a.equals(addToQueueCommand.d()) && this.b.equals(addToQueueCommand.c()) && this.c.equals(addToQueueCommand.b());
    }

    public final int hashCode() {
        return ((((this.a.hashCode() ^ 1000003) * 1000003) ^ this.b.hashCode()) * 1000003) ^ this.c.hashCode();
    }

    public final String toString() {
        return "AddToQueueCommand{track=" + this.a + ", options=" + this.b + ", loggingParams=" + this.c + "}";
    }
}
