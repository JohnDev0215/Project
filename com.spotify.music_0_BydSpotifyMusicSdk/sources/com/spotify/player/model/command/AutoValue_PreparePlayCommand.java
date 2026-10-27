package com.spotify.player.model.command;

import com.google.common.base.Optional;
import com.spotify.player.model.Context;
import com.spotify.player.model.command.PreparePlayCommand;

/* JADX INFO: loaded from: classes.dex */
final class AutoValue_PreparePlayCommand extends PreparePlayCommand {

    public static final class Builder extends PreparePlayCommand.Builder {
        public Builder() {
            Optional.a();
        }
    }

    @Override // com.spotify.player.model.command.PreparePlayCommand
    public final Context a() {
        return null;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PreparePlayCommand)) {
            return false;
        }
        ((PreparePlayCommand) obj).a();
        throw null;
    }

    public final int hashCode() {
        throw null;
    }

    public final String toString() {
        return "PreparePlayCommand{context=null, playOrigin=null, options=null}";
    }
}
