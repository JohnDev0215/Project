package com.spotify.player.model.command;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.spotify.player.model.command.SetQueueCommand;

/* JADX INFO: loaded from: classes.dex */
final class AutoValue_SetQueueCommand extends SetQueueCommand {
    public final String a;
    public final ImmutableList b;
    public final ImmutableList c;
    public final Optional d;
    public final Optional e;

    public static final class Builder extends SetQueueCommand.Builder {
        public String a;
        public ImmutableList b;
        public ImmutableList c;
        public final Optional d = Optional.a();
        public final Optional e = Optional.a();
    }

    public AutoValue_SetQueueCommand(String str, ImmutableList immutableList, ImmutableList immutableList2, Optional optional, Optional optional2) {
        this.a = str;
        this.b = immutableList;
        this.c = immutableList2;
        this.d = optional;
        this.e = optional2;
    }

    @Override // com.spotify.player.model.command.SetQueueCommand
    public final Optional b() {
        return this.e;
    }

    @Override // com.spotify.player.model.command.SetQueueCommand
    public final ImmutableList c() {
        return this.c;
    }

    @Override // com.spotify.player.model.command.SetQueueCommand
    public final Optional d() {
        return this.d;
    }

    @Override // com.spotify.player.model.command.SetQueueCommand
    public final ImmutableList e() {
        return this.b;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SetQueueCommand)) {
            return false;
        }
        SetQueueCommand setQueueCommand = (SetQueueCommand) obj;
        return this.a.equals(setQueueCommand.f()) && this.b.equals(setQueueCommand.e()) && this.c.equals(setQueueCommand.c()) && this.d.equals(setQueueCommand.d()) && this.e.equals(setQueueCommand.b());
    }

    @Override // com.spotify.player.model.command.SetQueueCommand
    public final String f() {
        return this.a;
    }

    public final int hashCode() {
        return ((((((((this.a.hashCode() ^ 1000003) * 1000003) ^ this.b.hashCode()) * 1000003) ^ this.c.hashCode()) * 1000003) ^ this.d.hashCode()) * 1000003) ^ this.e.hashCode();
    }

    public final String toString() {
        return "SetQueueCommand{queueRevision=" + this.a + ", prevTracks=" + this.b + ", nextTracks=" + this.c + ", options=" + this.d + ", loggingParams=" + this.e + "}";
    }
}
