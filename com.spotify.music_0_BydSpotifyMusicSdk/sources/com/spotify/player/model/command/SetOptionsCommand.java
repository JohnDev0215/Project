package com.spotify.player.model.command;

import com.google.common.base.Optional;
import com.spotify.player.model.command.AutoValue_SetOptionsCommand;

/* JADX INFO: loaded from: classes.dex */
public abstract class SetOptionsCommand implements Command {

    public static abstract class Builder {
        public abstract SetOptionsCommand a();

        public abstract Builder b(boolean z);

        public abstract Builder c(boolean z);
    }

    public static Builder a() {
        return new AutoValue_SetOptionsCommand.Builder();
    }

    public abstract Optional b();

    public abstract Optional c();

    public abstract Optional d();

    public abstract Optional e();

    public abstract Optional f();

    public abstract Optional g();
}
