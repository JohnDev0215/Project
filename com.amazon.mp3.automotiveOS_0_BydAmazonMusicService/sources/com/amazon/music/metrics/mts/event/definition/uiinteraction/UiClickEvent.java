package com.amazon.music.metrics.mts.event.definition.uiinteraction;

import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.find.utils.ContentUtils;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.metrics.mts.MTSEvent;
import com.amazon.music.metrics.mts.event.types.ActivityIdType;
import com.amazon.music.metrics.mts.event.types.ResponseIdType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageEntityIdType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageEntityType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.google.android.gms.common.internal.ImagesContract;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public class UiClickEvent extends MTSEvent {
    private UiClickEvent(Builder builder) {
        super("uiClick", 5L);
        setTimestamp(builder.eventTime);
        if (builder.experienceMode != null) {
            addAttribute("experienceMode", builder.experienceMode);
        }
        if (builder.pageSubType != null) {
            addAttribute("pageSubType", builder.pageSubType);
        }
        if (builder.actionType != null) {
            addAttribute("actionType", builder.actionType);
        }
        if (builder.url != null) {
            addAttribute(ImagesContract.URL, builder.url);
        }
        if (builder.blockRef != null) {
            addAttribute("blockRef", builder.blockRef);
        }
        if (builder.localContentId != null) {
            addAttribute("localContentId", builder.localContentId);
        }
        if (builder.activityId != null) {
            addAttribute(ContentUtils.KEY_ACTIVITY_ID, builder.activityId);
        }
        if (builder.activityIdType != null) {
            addAttribute(ContentUtils.KEY_ACTIVITY_ID_TYPE, builder.activityIdType);
        }
        if (builder.responseId != null) {
            addAttribute(ContentUtils.KEY_RESPONSE_ID, builder.responseId);
        }
        if (builder.responseIdType != null) {
            addAttribute(ContentUtils.KEY_RESPONSE_ID_TYPE, builder.responseIdType);
        }
        if (builder.searchQuery != null) {
            addAttribute(ContentUtils.KEY_SEARCH_QUERY, builder.searchQuery);
        }
        if (builder.csSessionId != null) {
            addAttribute("csSessionId", builder.csSessionId);
        }
        addAttribute("interactionType", builder.interactionType);
        addAttribute("pageType", builder.pageType);
        addAttribute("refMarker", builder.refMarker);
        addAttribute(ContextMappingConstants.ENTITY_ID, builder.entityId);
        addAttribute(ContextMappingConstants.ENTITY_ID_TYPE, builder.entityIdType);
        addAttribute(PageUriUtils.ENTITY_TYPE_QUERY_PARAM, builder.entityType);
        if (builder.entityPos >= 0) {
            addAttribute("entityPos", builder.entityPos);
        }
        if (builder.contentInfo != null) {
            addContentInfoAttributes(builder.contentInfo);
        }
        if (builder.pageEntityId != null) {
            addAttribute("pageEntityId", builder.pageEntityId);
        }
        if (builder.pageEntityIdType != null) {
            addAttribute("pageEntityIdType", builder.pageEntityIdType);
        }
        if (builder.pageEntityType != null) {
            addAttribute("pageEntityType", builder.pageEntityType);
        }
    }

    public static Builder builder(ActionType actionType) {
        return new Builder(actionType);
    }

    public static Builder builder(String str) {
        return new Builder(str);
    }

    public static Builder builder(String str, String str2) {
        return new Builder(str, str2);
    }

    public static class Builder {
        private String actionType;
        private String activityId;
        private String activityIdType;
        private String blockRef;
        private List<Map<String, String>> contentInfo;
        private String csSessionId;
        private String entityId;
        private String entityIdType;
        private int entityPos;
        private String entityType;
        private long eventTime;
        private String experienceMode;
        private String interactionType;
        private String localContentId;
        private String pageEntityId;
        private String pageEntityIdType;
        private String pageEntityType;
        private String pageSubType;
        private String pageType;
        private String refMarker;
        private String responseId;
        private String responseIdType;
        private String searchQuery;
        private String url;

        private Builder(ActionType actionType) {
            this.eventTime = System.currentTimeMillis();
            this.entityPos = -1;
            this.actionType = actionType.getMetricsValue();
        }

        private Builder(String str, String str2) {
            this.eventTime = System.currentTimeMillis();
            this.entityPos = -1;
            this.url = str;
            this.blockRef = str2;
        }

        private Builder(String str) {
            this.eventTime = System.currentTimeMillis();
            this.entityPos = -1;
            this.actionType = str;
        }

        public Builder withInteractionType(InteractionType interactionType) {
            if (interactionType != null) {
                this.interactionType = interactionType.getMetricsValue();
            }
            return this;
        }

        public Builder withPageType(PageType pageType) {
            if (pageType != null) {
                this.pageType = pageType.getMetricsValue();
            }
            return this;
        }

        public Builder withPageSubType(String str) {
            this.pageSubType = str;
            return this;
        }

        public Builder withRefMarker(String str) {
            this.refMarker = str;
            return this;
        }

        public Builder withEntityId(String str) {
            this.entityId = str;
            return this;
        }

        public Builder withEntityIdType(EntityIdType entityIdType) {
            if (entityIdType != null) {
                this.entityIdType = entityIdType.getMetricsValue();
            }
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

        public Builder withEventTime(long j) {
            this.eventTime = j;
            return this;
        }

        public Builder withBlockRef(String str) {
            this.blockRef = str;
            return this;
        }

        public Builder withEntityType(EntityType entityType) {
            if (entityType != null) {
                this.entityType = entityType.getMetricsValue();
            }
            return this;
        }

        public Builder withUrl(String str) {
            this.url = str;
            return this;
        }

        public Builder withLocalContentId(String str) {
            this.localContentId = str;
            return this;
        }

        public Builder withCustomPageType(String str) {
            this.pageType = str;
            return this;
        }

        public Builder withCustomEntityIdType(String str) {
            this.entityIdType = str;
            return this;
        }

        public Builder withCustomEntityType(String str) {
            this.entityType = str;
            return this;
        }

        public Builder withActivityId(String str) {
            if (str != null) {
                this.activityId = str;
            }
            return this;
        }

        public Builder withActivityIdType(ActivityIdType activityIdType) {
            if (activityIdType != null) {
                this.activityIdType = activityIdType.getMetricsValue();
            }
            return this;
        }

        public Builder withResponseId(String str) {
            if (str != null) {
                this.responseId = str;
            }
            return this;
        }

        public Builder withResponseIdType(ResponseIdType responseIdType) {
            if (responseIdType != null) {
                this.responseIdType = responseIdType.getMetricsValue();
            }
            return this;
        }

        public Builder withSearchQuery(String str) {
            if (str != null) {
                this.searchQuery = str;
            }
            return this;
        }

        public Builder withPageEntityId(String str) {
            this.pageEntityId = str;
            return this;
        }

        public Builder withPageEntityIdType(PageEntityIdType pageEntityIdType) {
            if (pageEntityIdType != null) {
                this.pageEntityIdType = pageEntityIdType.getMetricsValue();
            }
            return this;
        }

        public Builder withPageEntityType(PageEntityType pageEntityType) {
            if (pageEntityType != null) {
                this.pageEntityType = pageEntityType.getMetricsValue();
            }
            return this;
        }

        public Builder withCustomPageEntityIdType(String str) {
            this.pageEntityIdType = str;
            return this;
        }

        public Builder withCustomPageEntityType(String str) {
            this.pageEntityType = str;
            return this;
        }

        public Builder withExperienceMode(String str) {
            if (str != null) {
                this.experienceMode = str;
            }
            return this;
        }

        public Builder withCsSessionId(String str) {
            if (str != null) {
                this.csSessionId = str;
            }
            return this;
        }

        public UiClickEvent build() {
            return new UiClickEvent(this);
        }
    }
}
