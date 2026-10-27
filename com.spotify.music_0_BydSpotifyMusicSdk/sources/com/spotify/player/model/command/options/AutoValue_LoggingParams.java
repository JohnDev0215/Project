package com.spotify.player.model.command.options;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableSet;
import com.spotify.player.model.command.options.LoggingParams;

/* JADX INFO: loaded from: classes.dex */
final class AutoValue_LoggingParams extends LoggingParams {
    public final Optional b;
    public final Optional c;
    public final ImmutableSet d;
    public final ImmutableSet e;

    public static final class Builder extends LoggingParams.Builder {
        public Optional a = Optional.a();
        public Optional b = Optional.a();
        public ImmutableSet c;
        public ImmutableSet d;

        @Override // com.spotify.player.model.command.options.LoggingParams.Builder
        public final LoggingParams a() {
            String strConcat = this.c == null ? " pageInstanceIds" : "";
            if (this.d == null) {
                strConcat = strConcat.concat(" interactionIds");
            }
            if (strConcat.isEmpty()) {
                return new AutoValue_LoggingParams(this.a, this.b, this.c, this.d);
            }
            throw new IllegalStateException("Missing required properties:".concat(strConcat));
        }

        @Override // com.spotify.player.model.command.options.LoggingParams.Builder
        public final LoggingParams.Builder b(Long l) {
            this.a = Optional.e(l);
            return this;
        }

        @Override // com.spotify.player.model.command.options.LoggingParams.Builder
        public final LoggingParams.Builder c(ImmutableSet immutableSet) {
            if (immutableSet == null) {
                throw new NullPointerException("Null interactionIds");
            }
            this.d = immutableSet;
            return this;
        }

        @Override // com.spotify.player.model.command.options.LoggingParams.Builder
        public final LoggingParams.Builder d(ImmutableSet immutableSet) {
            if (immutableSet == null) {
                throw new NullPointerException("Null pageInstanceIds");
            }
            this.c = immutableSet;
            return this;
        }
    }

    public AutoValue_LoggingParams(Optional optional, Optional optional2, ImmutableSet immutableSet, ImmutableSet immutableSet2) {
        this.b = optional;
        this.c = optional2;
        this.d = immutableSet;
        this.e = immutableSet2;
    }

    @Override // com.spotify.player.model.command.options.LoggingParams
    public final Optional b() {
        return this.b;
    }

    @Override // com.spotify.player.model.command.options.LoggingParams
    public final Optional c() {
        return this.c;
    }

    @Override // com.spotify.player.model.command.options.LoggingParams
    public final ImmutableSet d() {
        return this.e;
    }

    @Override // com.spotify.player.model.command.options.LoggingParams
    public final ImmutableSet e() {
        return this.d;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LoggingParams)) {
            return false;
        }
        LoggingParams loggingParams = (LoggingParams) obj;
        return this.b.equals(loggingParams.b()) && this.c.equals(loggingParams.c()) && this.d.equals(loggingParams.e()) && this.e.equals(loggingParams.d());
    }

    @Override // com.spotify.player.model.command.options.LoggingParams
    public final LoggingParams.Builder f() {
        Builder builder = new Builder();
        builder.a = Optional.a();
        Optional.a();
        builder.a = this.b;
        builder.b = this.c;
        builder.c = this.d;
        builder.d = this.e;
        return builder;
    }

    public final int hashCode() {
        return ((((((this.b.hashCode() ^ 1000003) * 1000003) ^ this.c.hashCode()) * 1000003) ^ this.d.hashCode()) * 1000003) ^ this.e.hashCode();
    }

    public final String toString() {
        return "LoggingParams{commandInitiatedTime=" + this.b + ", commandReceivedTime=" + this.c + ", pageInstanceIds=" + this.d + ", interactionIds=" + this.e + "}";
    }
}
