package com.spotify.player.model;

import com.google.common.collect.ImmutableSet;
import com.spotify.player.model.AutoValue_PlayOrigin;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public abstract class PlayOrigin {

    public static abstract class Builder {
        public abstract PlayOrigin a();

        public abstract Builder b(String str);

        public abstract Builder c(String str);

        public abstract Builder d(Set set);

        public abstract Builder e(String str);

        public abstract Builder f(String str);

        public abstract Builder g(String str);

        public abstract Builder h(String str);

        public abstract Builder i(String str);
    }

    public static Builder a(String str) {
        AutoValue_PlayOrigin.Builder builder = new AutoValue_PlayOrigin.Builder();
        builder.e(str);
        builder.f("");
        builder.i("");
        builder.c("");
        builder.g("");
        builder.b("");
        builder.d(ImmutableSet.q());
        builder.h("");
        return builder;
    }

    public static PlayOrigin b(String str) {
        return a(str).a();
    }

    public abstract String c();

    public abstract String d();

    public abstract ImmutableSet<String> e();

    public abstract String f();

    public abstract String g();

    public abstract String h();

    public abstract String i();

    public abstract Builder j();

    public abstract String k();
}
