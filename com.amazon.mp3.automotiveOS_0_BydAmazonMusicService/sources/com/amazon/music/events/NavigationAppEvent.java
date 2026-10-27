package com.amazon.music.events;

import com.amazon.music.events.types.Orientation;

/* JADX INFO: loaded from: classes4.dex */
public class NavigationAppEvent extends AppEvent {
    private final String associateTag;
    private final String blockRef;
    private final String identifier;
    private final String itemIdentifier;
    private final String itemIdentifierSubType;
    private final String itemIdentifierType;
    private final Orientation orientation;
    private final String refMarker;
    private final boolean useIdentifierAsPageType;

    private NavigationAppEvent(Builder builder) {
        setEventTime(builder.eventTime);
        this.identifier = builder.identifier;
        this.itemIdentifier = builder.itemIdentifier;
        this.itemIdentifierType = builder.itemIdentifierType;
        this.itemIdentifierSubType = builder.itemIdentifierSubType;
        this.refMarker = builder.refMarker;
        this.associateTag = builder.associateTag;
        this.orientation = builder.orientation;
        this.useIdentifierAsPageType = builder.useIdentifierAsPageType;
        this.blockRef = builder.blockRef;
    }

    public static Builder builder(String str) {
        return new Builder(str);
    }

    public static class Builder {
        private String associateTag;
        private String blockRef;
        private long eventTime;
        private final String identifier;
        private String itemIdentifier;
        private String itemIdentifierSubType;
        private String itemIdentifierType;
        private Orientation orientation;
        private String refMarker;
        private boolean useIdentifierAsPageType;

        private Builder(String str) {
            this.eventTime = System.currentTimeMillis();
            this.useIdentifierAsPageType = false;
            this.identifier = str;
        }

        public Builder useIdentifierAsPageType() {
            this.useIdentifierAsPageType = true;
            return this;
        }

        public Builder withItemIdentifier(String str) {
            this.itemIdentifier = str;
            return this;
        }

        public Builder withItemIdentifierType(String str) {
            this.itemIdentifierType = str;
            return this;
        }

        public Builder withItemIdentifierSubType(String str) {
            this.itemIdentifierSubType = str;
            return this;
        }

        public Builder withRefMarker(String str) {
            this.refMarker = str;
            return this;
        }

        public Builder withAssociateTag(String str) {
            this.associateTag = str;
            return this;
        }

        public Builder withOrientation(Orientation orientation) {
            this.orientation = orientation;
            return this;
        }

        public Builder withBlockRef(String str) {
            this.blockRef = str;
            return this;
        }

        public void publish() {
            AppEvent.appEventPublisher.publish(new NavigationAppEvent(this));
        }
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public boolean useIdentifierAsPageType() {
        return this.useIdentifierAsPageType;
    }

    public String getItemIdentifier() {
        return this.itemIdentifier;
    }

    public String getItemIdentifierType() {
        return this.itemIdentifierType;
    }

    public String getItemIdentifierSubType() {
        return this.itemIdentifierSubType;
    }

    public String getRefMarker() {
        return this.refMarker;
    }

    public String getAssociateTag() {
        return this.associateTag;
    }

    public Orientation getOrientation() {
        return this.orientation;
    }

    public String getBlockRef() {
        return this.blockRef;
    }
}
