package com.spotify.player.model;

import com.google.common.collect.ImmutableSet;

/* JADX INFO: loaded from: classes.dex */
final class AutoValue_Suppressions extends Suppressions {
    public final ImmutableSet b;

    public AutoValue_Suppressions(ImmutableSet immutableSet) {
        if (immutableSet == null) {
            throw new NullPointerException("Null providers");
        }
        this.b = immutableSet;
    }

    @Override // com.spotify.player.model.Suppressions
    public final ImmutableSet b() {
        return this.b;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Suppressions) {
            return this.b.equals(((Suppressions) obj).b());
        }
        return false;
    }

    public final int hashCode() {
        return this.b.hashCode() ^ 1000003;
    }

    public final String toString() {
        return "Suppressions{providers=" + this.b + "}";
    }
}
