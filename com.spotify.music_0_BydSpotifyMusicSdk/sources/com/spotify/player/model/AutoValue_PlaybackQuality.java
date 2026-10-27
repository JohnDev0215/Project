package com.spotify.player.model;

import com.spotify.player.model.PlaybackQuality;
import p.u4;

/* JADX INFO: loaded from: classes.dex */
final class AutoValue_PlaybackQuality extends PlaybackQuality {
    public final BitrateLevel a;
    public final BitrateStrategy b;
    public final BitrateLevel c;
    public final boolean d;
    public final HiFiStatus e;

    public static final class Builder extends PlaybackQuality.Builder {
        public BitrateLevel a;
        public BitrateStrategy b;
        public BitrateLevel c;
        public Boolean d;
        public HiFiStatus e;

        @Override // com.spotify.player.model.PlaybackQuality.Builder
        public final PlaybackQuality.Builder a(BitrateLevel bitrateLevel) {
            if (bitrateLevel == null) {
                throw new NullPointerException("Null bitrateLevel");
            }
            this.a = bitrateLevel;
            return this;
        }

        @Override // com.spotify.player.model.PlaybackQuality.Builder
        public final PlaybackQuality b() {
            String strH = this.a == null ? " bitrateLevel" : "";
            if (this.b == null) {
                strH = strH.concat(" strategy");
            }
            if (this.c == null) {
                strH = u4.h(strH, " targetBitrateLevel");
            }
            if (this.d == null) {
                strH = u4.h(strH, " targetBitrateAvailable");
            }
            if (this.e == null) {
                strH = u4.h(strH, " hifiStatus");
            }
            if (strH.isEmpty()) {
                return new AutoValue_PlaybackQuality(this.a, this.b, this.c, this.d.booleanValue(), this.e);
            }
            throw new IllegalStateException("Missing required properties:".concat(strH));
        }

        @Override // com.spotify.player.model.PlaybackQuality.Builder
        public final PlaybackQuality.Builder c(HiFiStatus hiFiStatus) {
            if (hiFiStatus == null) {
                throw new NullPointerException("Null hifiStatus");
            }
            this.e = hiFiStatus;
            return this;
        }

        @Override // com.spotify.player.model.PlaybackQuality.Builder
        public final PlaybackQuality.Builder d(BitrateStrategy bitrateStrategy) {
            if (bitrateStrategy == null) {
                throw new NullPointerException("Null strategy");
            }
            this.b = bitrateStrategy;
            return this;
        }

        @Override // com.spotify.player.model.PlaybackQuality.Builder
        public final PlaybackQuality.Builder e(boolean z) {
            this.d = Boolean.valueOf(z);
            return this;
        }

        @Override // com.spotify.player.model.PlaybackQuality.Builder
        public final PlaybackQuality.Builder f(BitrateLevel bitrateLevel) {
            if (bitrateLevel == null) {
                throw new NullPointerException("Null targetBitrateLevel");
            }
            this.c = bitrateLevel;
            return this;
        }
    }

    public AutoValue_PlaybackQuality(BitrateLevel bitrateLevel, BitrateStrategy bitrateStrategy, BitrateLevel bitrateLevel2, boolean z, HiFiStatus hiFiStatus) {
        this.a = bitrateLevel;
        this.b = bitrateStrategy;
        this.c = bitrateLevel2;
        this.d = z;
        this.e = hiFiStatus;
    }

    @Override // com.spotify.player.model.PlaybackQuality
    public final BitrateLevel a() {
        return this.a;
    }

    @Override // com.spotify.player.model.PlaybackQuality
    public final HiFiStatus c() {
        return this.e;
    }

    @Override // com.spotify.player.model.PlaybackQuality
    public final BitrateStrategy d() {
        return this.b;
    }

    @Override // com.spotify.player.model.PlaybackQuality
    public final boolean e() {
        return this.d;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PlaybackQuality)) {
            return false;
        }
        PlaybackQuality playbackQuality = (PlaybackQuality) obj;
        return this.a.equals(playbackQuality.a()) && this.b.equals(playbackQuality.d()) && this.c.equals(playbackQuality.f()) && this.d == playbackQuality.e() && this.e.equals(playbackQuality.c());
    }

    @Override // com.spotify.player.model.PlaybackQuality
    public final BitrateLevel f() {
        return this.c;
    }

    public final int hashCode() {
        return ((((((((this.a.hashCode() ^ 1000003) * 1000003) ^ this.b.hashCode()) * 1000003) ^ this.c.hashCode()) * 1000003) ^ (this.d ? 1231 : 1237)) * 1000003) ^ this.e.hashCode();
    }

    public final String toString() {
        return "PlaybackQuality{bitrateLevel=" + this.a + ", strategy=" + this.b + ", targetBitrateLevel=" + this.c + ", targetBitrateAvailable=" + this.d + ", hifiStatus=" + this.e + "}";
    }
}
