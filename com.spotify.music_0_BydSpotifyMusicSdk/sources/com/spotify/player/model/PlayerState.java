package com.spotify.player.model;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.spotify.player.model.AutoValue_PlayerState;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public abstract class PlayerState {

    public static abstract class Builder {
        public abstract Builder A(long j);

        public abstract Builder B(ContextTrack contextTrack);

        public abstract Builder a(AudioStream audioStream);

        public abstract PlayerState b();

        public abstract Builder c(Map map);

        public abstract Builder d(Restrictions restrictions);

        public abstract Builder e(String str);

        public abstract Builder f(String str);

        public abstract Builder g(Long l);

        public abstract Builder h(ContextIndex contextIndex);

        public abstract Builder i(boolean z);

        public abstract Builder j(boolean z);

        public abstract Builder k(boolean z);

        public abstract Builder l(boolean z);

        public abstract Builder m(List list);

        public abstract Builder n(PlayerOptions playerOptions);

        public abstract Builder o(Map map);

        public abstract Builder p(PlayOrigin playOrigin);

        public abstract Builder q(String str);

        public abstract Builder r(PlaybackQuality playbackQuality);

        public abstract Builder s(Double d);

        public abstract Builder t(Long l);

        public abstract Builder u(List list);

        public abstract Builder v(String str);

        public abstract Builder w(Restrictions restrictions);

        public abstract Builder x(String str);

        public abstract Builder y(List list);

        public abstract Builder z(Suppressions suppressions);
    }

    static {
        b().b();
    }

    public static Builder b() {
        AutoValue_PlayerState.Builder builder = new AutoValue_PlayerState.Builder();
        builder.a = 0L;
        builder.e("");
        builder.f("");
        Restrictions restrictions = Restrictions.a;
        builder.d(restrictions);
        builder.n(PlayerOptions.a);
        builder.w(restrictions);
        builder.p(PlayOrigin.b(""));
        builder.k(false);
        builder.j(false);
        builder.i(false);
        builder.l(false);
        builder.z(Suppressions.a);
        builder.u(Collections.emptyList());
        builder.m(Collections.emptyList());
        builder.c(Collections.emptyMap());
        builder.o(Collections.emptyMap());
        builder.x("");
        builder.v("0");
        builder.y(Collections.emptyList());
        builder.a(AudioStream.c);
        return builder;
    }

    public abstract long A();

    public abstract Optional B();

    public abstract AudioStream a();

    public abstract ImmutableMap c();

    public abstract Restrictions d();

    public abstract String e();

    public abstract String f();

    public abstract Optional g();

    public abstract Optional h();

    public abstract boolean i();

    public abstract boolean j();

    public abstract boolean k();

    public abstract boolean l();

    public abstract ImmutableList m();

    public abstract PlayerOptions n();

    public abstract ImmutableMap o();

    public abstract PlayOrigin p();

    public abstract Optional q();

    public abstract Optional r();

    public abstract Optional s();

    public abstract Optional t();

    public abstract ImmutableList u();

    public abstract String v();

    public abstract Restrictions w();

    public abstract String x();

    public abstract ImmutableList y();

    public abstract Suppressions z();
}
