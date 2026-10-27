package com.spotify.player.model;

import com.spotify.player.model.PauseResumeOrigin;
import p.u4;

/* JADX INFO: loaded from: classes.dex */
final class AutoValue_PauseResumeOrigin extends PauseResumeOrigin {
    public final String a;

    public static final class Builder extends PauseResumeOrigin.Builder {
        public String a;

        @Override // com.spotify.player.model.PauseResumeOrigin.Builder
        public final PauseResumeOrigin a() {
            String str = this.a == null ? " featureIdentifier" : "";
            if (str.isEmpty()) {
                return new AutoValue_PauseResumeOrigin(this.a);
            }
            throw new IllegalStateException("Missing required properties:".concat(str));
        }
    }

    public AutoValue_PauseResumeOrigin(String str) {
        this.a = str;
    }

    @Override // com.spotify.player.model.PauseResumeOrigin
    public final String b() {
        return this.a;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof PauseResumeOrigin) {
            return this.a.equals(((PauseResumeOrigin) obj).b());
        }
        return false;
    }

    public final int hashCode() {
        return this.a.hashCode() ^ 1000003;
    }

    public final String toString() {
        return u4.m(new StringBuilder("PauseResumeOrigin{featureIdentifier="), this.a, "}");
    }
}
