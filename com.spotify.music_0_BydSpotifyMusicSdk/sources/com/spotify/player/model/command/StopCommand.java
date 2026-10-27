package com.spotify.player.model.command;

import com.google.common.base.Optional;
import com.spotify.player.model.command.AutoValue_StopCommand;

/* JADX INFO: loaded from: classes.dex */
public abstract class StopCommand implements Command {

    public static abstract class Builder {
    }

    public static StopCommand a() {
        return new AutoValue_StopCommand(new AutoValue_StopCommand.Builder().a);
    }

    public abstract Optional b();
}
