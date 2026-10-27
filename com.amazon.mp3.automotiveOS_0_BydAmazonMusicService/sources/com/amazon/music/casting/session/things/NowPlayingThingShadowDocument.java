package com.amazon.music.casting.session.things;

import com.google.gson.annotations.Expose;

/* JADX INFO: loaded from: classes4.dex */
public class NowPlayingThingShadowDocument {

    @Expose
    private NowPlayingThingShadow current;

    @Expose
    private NowPlayingThingShadow previous;
    private Long timestamp;

    private NowPlayingThingShadowDocument(NowPlayingThingShadow nowPlayingThingShadow, NowPlayingThingShadow nowPlayingThingShadow2) {
        this.previous = nowPlayingThingShadow;
        this.current = nowPlayingThingShadow2;
    }

    public NowPlayingThingShadow getPrevious() {
        return this.previous;
    }

    public NowPlayingThingShadow getCurrent() {
        return this.current;
    }

    public static class Builder {

        @Expose
        private NowPlayingThingShadow current;

        @Expose
        private NowPlayingThingShadow previous;

        public Builder withPrevious(NowPlayingThingShadow nowPlayingThingShadow) {
            this.previous = nowPlayingThingShadow;
            return this;
        }

        public Builder withCurrent(NowPlayingThingShadow nowPlayingThingShadow) {
            this.current = nowPlayingThingShadow;
            return this;
        }

        public NowPlayingThingShadowDocument build() {
            return new NowPlayingThingShadowDocument(this.previous, this.current);
        }
    }

    public String toString() {
        return "NowPlayingThingShadowDocument{previous=" + this.previous + ", current=" + this.current + ", timestamp=" + this.timestamp + '}';
    }
}
