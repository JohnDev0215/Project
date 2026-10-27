package com.amazon.music.casting.session.things;

import com.google.gson.annotations.Expose;

/* JADX INFO: loaded from: classes4.dex */
public class MetadataThingShadowDocument {

    @Expose
    private MetadataThingShadow current;

    @Expose
    private MetadataThingShadow previous;
    private Long timestamp;

    private MetadataThingShadowDocument(MetadataThingShadow metadataThingShadow, MetadataThingShadow metadataThingShadow2) {
        this.previous = metadataThingShadow;
        this.current = metadataThingShadow2;
    }

    public MetadataThingShadow getPrevious() {
        return this.previous;
    }

    public MetadataThingShadow getCurrent() {
        return this.current;
    }

    public static class Builder {

        @Expose
        private MetadataThingShadow current;

        @Expose
        private MetadataThingShadow previous;

        public Builder withPrevious(MetadataThingShadow metadataThingShadow) {
            this.previous = metadataThingShadow;
            return this;
        }

        public Builder withCurrent(MetadataThingShadow metadataThingShadow) {
            this.current = metadataThingShadow;
            return this;
        }

        public MetadataThingShadowDocument build() {
            return new MetadataThingShadowDocument(this.previous, this.current);
        }
    }

    public String toString() {
        return "MetadataThingShadowDocument{previous=" + this.previous + ", current=" + this.current + ", timestamp=" + this.timestamp + '}';
    }
}
