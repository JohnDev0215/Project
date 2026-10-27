package com.spotify.player.model.command;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.spotify.player.model.PlayerQueue;
import com.spotify.player.model.command.AutoValue_SetQueueCommand;
import p.u4;

/* JADX INFO: loaded from: classes.dex */
public abstract class SetQueueCommand implements Command {

    public static abstract class Builder {
    }

    public static SetQueueCommand a(PlayerQueue playerQueue) {
        AutoValue_SetQueueCommand.Builder builder = new AutoValue_SetQueueCommand.Builder();
        String strD = playerQueue.d();
        if (strD == null) {
            throw new NullPointerException("Null queueRevision");
        }
        builder.a = strD;
        builder.c = ImmutableList.o(playerQueue.b());
        ImmutableList immutableListO = ImmutableList.o(playerQueue.c());
        builder.b = immutableListO;
        String strH = builder.a == null ? " queueRevision" : "";
        if (immutableListO == null) {
            strH = strH.concat(" prevTracks");
        }
        if (builder.c == null) {
            strH = u4.h(strH, " nextTracks");
        }
        if (strH.isEmpty()) {
            return new AutoValue_SetQueueCommand(builder.a, builder.b, builder.c, builder.d, builder.e);
        }
        throw new IllegalStateException("Missing required properties:".concat(strH));
    }

    public abstract Optional b();

    public abstract ImmutableList c();

    public abstract Optional d();

    public abstract ImmutableList e();

    public abstract String f();
}
