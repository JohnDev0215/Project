package com.spotify.player.model;

import com.google.common.collect.ImmutableSet;
import java.util.Collections;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public abstract class Suppressions {
    public static final Suppressions a = a(Collections.emptySet());

    public static final class Providers {
    }

    public static Suppressions a(Set set) {
        return new AutoValue_Suppressions(set == null ? ImmutableSet.q() : ImmutableSet.o(set));
    }

    public abstract ImmutableSet b();
}
