package com.spotify.player.model.command;

import com.google.common.base.Optional;
import com.spotify.player.model.command.SetRepeatingTrackCommand;

/* JADX INFO: loaded from: classes.dex */
final class AutoValue_SetRepeatingTrackCommand extends SetRepeatingTrackCommand {
    public final boolean a;
    public final Optional b;
    public final Optional c;

    public static final class Builder extends SetRepeatingTrackCommand.Builder {
        public Boolean a;
        public final Optional b = Optional.a();
        public final Optional c = Optional.a();
    }

    public AutoValue_SetRepeatingTrackCommand(boolean z, Optional optional, Optional optional2) {
        this.a = z;
        this.b = optional;
        this.c = optional2;
    }

    @Override // com.spotify.player.model.command.SetRepeatingTrackCommand
    public final Optional b() {
        return this.c;
    }

    @Override // com.spotify.player.model.command.SetRepeatingTrackCommand
    public final Optional c() {
        return this.b;
    }

    @Override // com.spotify.player.model.command.SetRepeatingTrackCommand
    public final boolean d() {
        return this.a;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SetRepeatingTrackCommand)) {
            return false;
        }
        SetRepeatingTrackCommand setRepeatingTrackCommand = (SetRepeatingTrackCommand) obj;
        return this.a == setRepeatingTrackCommand.d() && this.b.equals(setRepeatingTrackCommand.c()) && this.c.equals(setRepeatingTrackCommand.b());
    }

    public final int hashCode() {
        return (((((this.a ? 1231 : 1237) ^ 1000003) * 1000003) ^ this.b.hashCode()) * 1000003) ^ this.c.hashCode();
    }

    public final String toString() {
        return "SetRepeatingTrackCommand{value=" + this.a + ", options=" + this.b + ", loggingParams=" + this.c + "}";
    }
}
