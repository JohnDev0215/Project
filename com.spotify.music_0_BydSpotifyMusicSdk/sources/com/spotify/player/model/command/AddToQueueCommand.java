package com.spotify.player.model.command;

import com.google.common.base.Optional;
import com.spotify.player.model.ContextTrack;
import com.spotify.player.model.command.AutoValue_AddToQueueCommand;

/* JADX INFO: loaded from: classes.dex */
public abstract class AddToQueueCommand implements Command {

    public static abstract class Builder {
    }

    public static AddToQueueCommand a(ContextTrack contextTrack) {
        AutoValue_AddToQueueCommand.Builder builder = new AutoValue_AddToQueueCommand.Builder();
        if (contextTrack != null) {
            return new AutoValue_AddToQueueCommand(contextTrack, builder.a, builder.b);
        }
        throw new NullPointerException("Null track");
    }

    public abstract Optional b();

    public abstract Optional c();

    public abstract ContextTrack d();
}
