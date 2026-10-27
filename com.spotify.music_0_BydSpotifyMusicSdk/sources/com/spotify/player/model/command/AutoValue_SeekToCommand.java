package com.spotify.player.model.command;

import com.google.common.base.Optional;
import com.spotify.player.model.command.SeekToCommand;

/* JADX INFO: loaded from: classes.dex */
final class AutoValue_SeekToCommand extends SeekToCommand {
    public final long a;
    public final Optional b;
    public final Optional c;
    public final Optional d;

    public static final class Builder extends SeekToCommand.Builder {
        public Long a;
        public final Optional b = Optional.a();
        public final Optional c = Optional.a();
        public final Optional d = Optional.a();
    }

    public AutoValue_SeekToCommand(long j, Optional optional, Optional optional2, Optional optional3) {
        this.a = j;
        this.b = optional;
        this.c = optional2;
        this.d = optional3;
    }

    @Override // com.spotify.player.model.command.SeekToCommand
    public final Optional b() {
        return this.d;
    }

    @Override // com.spotify.player.model.command.SeekToCommand
    public final Optional c() {
        return this.c;
    }

    @Override // com.spotify.player.model.command.SeekToCommand
    public final Optional d() {
        return this.b;
    }

    @Override // com.spotify.player.model.command.SeekToCommand
    public final long e() {
        return this.a;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SeekToCommand)) {
            return false;
        }
        SeekToCommand seekToCommand = (SeekToCommand) obj;
        return this.a == seekToCommand.e() && this.b.equals(seekToCommand.d()) && this.c.equals(seekToCommand.c()) && this.d.equals(seekToCommand.b());
    }

    public final int hashCode() {
        long j = this.a;
        return ((((((((int) ((j >>> 32) ^ j)) ^ 1000003) * 1000003) ^ this.b.hashCode()) * 1000003) ^ this.c.hashCode()) * 1000003) ^ this.d.hashCode();
    }

    public final String toString() {
        return "SeekToCommand{value=" + this.a + ", relative=" + this.b + ", options=" + this.c + ", loggingParams=" + this.d + "}";
    }
}
