package com.amazon.music.events;

import com.amazon.music.events.types.EntityIdType;
import com.amazon.music.events.types.EntityType;
import com.amazon.music.events.types.InteractionType;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public class UserInteractionAppEvent extends AppEvent {
    private String blockRef;
    private List<Map<String, String>> contentInfo;
    private final String entityId;
    private final EntityIdType entityIdType;
    private final int entityPos;
    private final EntityType entityType;
    private final String identifier;
    private final InteractionType interactionType;
    private final String pageSubType;
    private final String pageType;
    private final String refMarker;

    private UserInteractionAppEvent(Builder builder) {
        setEventTime(builder.eventTime);
        this.interactionType = builder.interactionType;
        this.identifier = builder.identifier;
        this.refMarker = builder.refMarker;
        this.entityId = builder.entityId;
        this.entityIdType = builder.entityIdType;
        this.pageType = builder.pageType;
        this.pageSubType = builder.pageSubType;
        this.entityPos = builder.entityPos;
        this.contentInfo = builder.contentInfo;
        this.entityType = builder.entityType;
        this.blockRef = builder.blockRef;
    }

    public static Builder builder(String str) {
        return new Builder(str);
    }

    public static class Builder {
        private String blockRef;
        private List<Map<String, String>> contentInfo;
        private String entityId;
        private EntityIdType entityIdType;
        private int entityPos;
        private EntityType entityType;
        private long eventTime;
        private final String identifier;
        private InteractionType interactionType;
        private String pageSubType;
        private String pageType;
        private String refMarker;

        private Builder(String str) {
            this.eventTime = System.currentTimeMillis();
            this.entityPos = -1;
            this.identifier = str;
        }

        public Builder withPageType(String str) {
            this.pageType = str;
            return this;
        }

        public Builder withInteractionType(InteractionType interactionType) {
            this.interactionType = interactionType;
            return this;
        }

        public Builder withEntityIdType(EntityIdType entityIdType) {
            this.entityIdType = entityIdType;
            return this;
        }

        public Builder withEntityId(String str) {
            this.entityId = str;
            return this;
        }

        public Builder withEntityType(EntityType entityType) {
            this.entityType = entityType;
            return this;
        }

        public Builder withEntityPos(int i) {
            this.entityPos = i;
            return this;
        }

        public Builder withContentInfo(List<Map<String, String>> list) {
            this.contentInfo = list;
            return this;
        }

        public Builder withRefMarker(String str) {
            this.refMarker = str;
            return this;
        }

        public Builder withBlockRef(String str) {
            this.blockRef = str;
            return this;
        }

        public void publish() {
            AppEvent.appEventPublisher.publish(new UserInteractionAppEvent(this));
        }
    }

    public InteractionType getInteractionType() {
        return this.interactionType;
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public String getEntityId() {
        return this.entityId;
    }

    public EntityIdType getEntityIdType() {
        return this.entityIdType;
    }

    public EntityType getEntityType() {
        return this.entityType;
    }

    public int getEntityPos() {
        return this.entityPos;
    }

    public List<Map<String, String>> getContentInfo() {
        return this.contentInfo;
    }

    public String getPageType() {
        return this.pageType;
    }

    public String getPageSubType() {
        return this.pageSubType;
    }

    public String getBlockRef() {
        return this.blockRef;
    }
}
