package com.spotify.player.model;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.spotify.player.model.AutoValue_ContextPage;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public abstract class ContextPage {

    public static abstract class Builder {
        public abstract ContextPage a();

        public abstract Builder b(Map map);

        public abstract Builder c(String str);

        public abstract Builder d(String str);

        public final Builder e(List list) {
            AutoValue_ContextPage.Builder builder = (AutoValue_ContextPage.Builder) this;
            builder.c = Optional.e(ImmutableList.o(list));
            return builder;
        }
    }

    static {
        a().a();
    }

    public static Builder a() {
        AutoValue_ContextPage.Builder builder = new AutoValue_ContextPage.Builder();
        builder.d("");
        builder.c("");
        builder.b(Collections.emptyMap());
        return builder;
    }

    public abstract ImmutableMap b();

    public abstract String c();

    public abstract String d();

    public abstract Optional e();
}
