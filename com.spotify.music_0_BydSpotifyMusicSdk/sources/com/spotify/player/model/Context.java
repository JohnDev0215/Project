package com.spotify.player.model;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.spotify.player.model.AutoValue_Context;
import com.spotify.player.model.AutoValue_ContextPage;
import java.util.ArrayList;
import java.util.Collections;
import p.y31;

/* JADX INFO: loaded from: classes.dex */
public abstract class Context {

    public static abstract class Builder {
        public abstract Context a();

        public abstract Builder b(ImmutableMap immutableMap);

        public abstract Builder c(ImmutableList immutableList);

        public abstract Builder d(Restrictions restrictions);

        public abstract Builder e(String str);
    }

    public static final class Metadata {
    }

    public static Builder a(String str) {
        AutoValue_Context.Builder builder = new AutoValue_Context.Builder();
        if (str == null) {
            throw new NullPointerException("Null uri");
        }
        builder.a = str;
        builder.e("");
        builder.b(ImmutableMap.b(Collections.emptyMap()));
        return builder;
    }

    public static Context b(String str, ImmutableList<String> immutableList) {
        ArrayList arrayList = new ArrayList();
        y31 y31VarListIterator = immutableList.listIterator(0);
        while (y31VarListIterator.hasNext()) {
            arrayList.add(ContextTrack.a((String) y31VarListIterator.next()).a());
        }
        Builder builderA = a(str);
        AutoValue_ContextPage.Builder builder = (AutoValue_ContextPage.Builder) ContextPage.a();
        builder.c = Optional.e(ImmutableList.o(arrayList));
        return builderA.c(ImmutableList.n(builder.a())).a();
    }

    public static Context c(String str) {
        return a(str).e("context://" + str).a();
    }

    public abstract ImmutableMap<String, String> d();

    public abstract Optional<ImmutableList<ContextPage>> e();

    public abstract Optional<Restrictions> f();

    public abstract Builder g();

    public abstract String h();

    public abstract String i();
}
