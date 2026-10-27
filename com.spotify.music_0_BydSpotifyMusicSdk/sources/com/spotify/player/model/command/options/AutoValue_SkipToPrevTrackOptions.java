package com.spotify.player.model.command.options;

import com.google.common.base.Optional;
import com.spotify.player.model.command.options.SkipToPrevTrackOptions;

/* JADX INFO: loaded from: classes.dex */
final class AutoValue_SkipToPrevTrackOptions extends SkipToPrevTrackOptions {
    public final Optional a;
    public final CommandOptions b;

    public static final class Builder extends SkipToPrevTrackOptions.Builder {
        public Optional a = Optional.a();
        public CommandOptions b;

        @Override // com.spotify.player.model.command.options.SkipToPrevTrackOptions.Builder
        public final SkipToPrevTrackOptions.Builder a(Boolean bool) {
            this.a = Optional.e(bool);
            return this;
        }

        @Override // com.spotify.player.model.command.options.SkipToPrevTrackOptions.Builder
        public final SkipToPrevTrackOptions b() {
            String str = this.b == null ? " commandOptions" : "";
            if (str.isEmpty()) {
                return new AutoValue_SkipToPrevTrackOptions(this.a, this.b);
            }
            throw new IllegalStateException("Missing required properties:".concat(str));
        }
    }

    public AutoValue_SkipToPrevTrackOptions(Optional optional, CommandOptions commandOptions) {
        this.a = optional;
        this.b = commandOptions;
    }

    @Override // com.spotify.player.model.command.options.SkipToPrevTrackOptions
    public final Optional a() {
        return this.a;
    }

    @Override // com.spotify.player.model.command.options.SkipToPrevTrackOptions
    public final CommandOptions c() {
        return this.b;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SkipToPrevTrackOptions)) {
            return false;
        }
        SkipToPrevTrackOptions skipToPrevTrackOptions = (SkipToPrevTrackOptions) obj;
        return this.a.equals(skipToPrevTrackOptions.a()) && this.b.equals(skipToPrevTrackOptions.c());
    }

    public final int hashCode() {
        return ((this.a.hashCode() ^ 1000003) * 1000003) ^ this.b.hashCode();
    }

    public final String toString() {
        return "SkipToPrevTrackOptions{allowSeeking=" + this.a + ", commandOptions=" + this.b + "}";
    }
}
