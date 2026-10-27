package com.spotify.player.model;

import com.spotify.player.model.AutoValue_PlayerError;

/* JADX INFO: loaded from: classes.dex */
public abstract class PlayerError {

    public static abstract class Builder {
        public abstract PlayerError a();

        public abstract Builder b(String str);

        public abstract Builder c(String str);

        public abstract Builder d(String str);
    }

    public static Builder a(ErrorType errorType) {
        AutoValue_PlayerError.Builder builder = new AutoValue_PlayerError.Builder();
        builder.a = errorType;
        builder.b = "";
        builder.c = "";
        builder.d = "";
        return builder;
    }

    public abstract String b();

    public abstract ErrorType c();

    public abstract String d();

    public abstract String e();
}
