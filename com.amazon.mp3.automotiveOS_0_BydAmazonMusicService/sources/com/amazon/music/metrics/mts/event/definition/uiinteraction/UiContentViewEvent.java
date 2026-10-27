package com.amazon.music.metrics.mts.event.definition.uiinteraction;

import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.find.metrics.SearchMetricsServiceDefault;
import com.amazon.music.find.utils.ContentUtils;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.metrics.mts.MTSEvent;
import com.amazon.music.metrics.mts.event.types.ActivityIdType;
import com.amazon.music.metrics.mts.event.types.ResponseIdType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ContainerType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ContentName;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityType;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.ISODateTimeFormat;

/* JADX INFO: loaded from: classes4.dex */
public class UiContentViewEvent extends MTSEvent {
    private UiContentViewEvent(BaseBuilder baseBuilder, List<Map<String, String>> list) {
        super("uiContentView", 6L);
        setTimestamp(baseBuilder.eventTime);
        addAttribute("emittedOnView", baseBuilder.emittedOnView);
        addAttribute("pageType", baseBuilder.pageType);
        if (baseBuilder.experienceMode != null) {
            addAttribute("experienceMode", baseBuilder.experienceMode);
        }
        if (baseBuilder.activityId != null) {
            addAttribute(ContentUtils.KEY_ACTIVITY_ID, baseBuilder.activityId);
        }
        if (baseBuilder.activityIdType != null) {
            addAttribute(ContentUtils.KEY_ACTIVITY_ID_TYPE, baseBuilder.activityIdType);
        }
        if (baseBuilder.responseId != null) {
            addAttribute(ContentUtils.KEY_RESPONSE_ID, baseBuilder.responseId);
        }
        if (baseBuilder.responseIdType != null) {
            addAttribute(ContentUtils.KEY_RESPONSE_ID_TYPE, baseBuilder.responseIdType);
        }
        if (baseBuilder.searchQuery != null) {
            addAttribute(ContentUtils.KEY_SEARCH_QUERY, baseBuilder.searchQuery);
        }
        if (list != null) {
            addContentInfoAttributes(list);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public static ClientEventBuilder clientEventBuilder() {
        return new ClientEventBuilder();
    }

    public static abstract class BaseBuilder<T extends BaseBuilder<T>> {
        private String activityId;
        private String activityIdType;
        private String experienceMode;
        private String responseId;
        private String responseIdType;
        private String searchQuery;
        private boolean emittedOnView = false;
        private long eventTime = System.currentTimeMillis();
        private String pageType = null;

        public T emittedOnView() {
            this.emittedOnView = true;
            return this;
        }

        public T withEventTime(long j) {
            this.eventTime = j;
            return this;
        }

        public T withPageType(String str) {
            this.pageType = str;
            return this;
        }

        public T withActivityId(String str) {
            if (str != null) {
                this.activityId = str;
            }
            return this;
        }

        public T withActivityIdType(ActivityIdType activityIdType) {
            if (activityIdType != null) {
                this.activityIdType = activityIdType.getMetricsValue();
            }
            return this;
        }

        public T withResponseId(String str) {
            if (str != null) {
                this.responseId = str;
            }
            return this;
        }

        public T withResponseIdType(ResponseIdType responseIdType) {
            if (responseIdType != null) {
                this.responseIdType = responseIdType.getMetricsValue();
            }
            return this;
        }

        public T withSearchQuery(String str) {
            if (str != null) {
                this.searchQuery = str;
            }
            return this;
        }

        public T withExperienceMode(String str) {
            if (str != null) {
                this.experienceMode = str;
            }
            return this;
        }
    }

    public static class Builder extends BaseBuilder<Builder> {
        private List<Map<String, String>> contentInfo;

        public Builder withContentInfo(List<Map<String, String>> list) {
            this.contentInfo = list;
            return this;
        }

        public UiContentViewEvent build() {
            return new UiContentViewEvent(this, this.contentInfo);
        }
    }

    public static class ClientEventBuilder extends BaseBuilder<ClientEventBuilder> {
        private Map<String, String> contentInfo;

        private ClientEventBuilder() {
            HashMap map = new HashMap();
            this.contentInfo = map;
            map.put(ContextMappingConstants.CONTENT_SRC, SearchMetricsServiceDefault.CONTENT_SRC_CLIENT);
        }

        public ClientEventBuilder withContentName(ContentName contentName) {
            if (contentName != null) {
                this.contentInfo.put("contentName", contentName.getMetricsValue());
            }
            return this;
        }

        public ClientEventBuilder withBlockRef(String str) {
            if (str != null) {
                this.contentInfo.put("blockRef", str);
            }
            return this;
        }

        public ClientEventBuilder withContainerIndex(long j) {
            this.contentInfo.put("containerIndex", String.valueOf(j));
            return this;
        }

        public ClientEventBuilder withContainerType(ContainerType containerType) {
            if (containerType != null) {
                this.contentInfo.put("containerType", containerType.getMetricsValue());
            }
            return this;
        }

        public ClientEventBuilder withNumItemsAccessible(long j) {
            this.contentInfo.put("numItemsAccessible", String.valueOf(j));
            return this;
        }

        public ClientEventBuilder withLocalContentId(String str) {
            if (str != null) {
                this.contentInfo.put("localContentId", str);
            }
            return this;
        }

        public ClientEventBuilder withReasonType(String str) {
            if (str != null) {
                this.contentInfo.put("reasonType", str);
            }
            return this;
        }

        public ClientEventBuilder withImpressionTimestamp(long j) {
            if (j > 0) {
                this.contentInfo.put("impressionTimestamp", ISODateTimeFormat.dateTime().print(new DateTime(j, DateTimeZone.UTC)));
            }
            return this;
        }

        public ClientEventBuilder withEntityType(EntityType entityType) {
            if (entityType != null) {
                this.contentInfo.put(PageUriUtils.ENTITY_TYPE_QUERY_PARAM, entityType.toString());
            }
            return this;
        }

        public ClientEventBuilder withEntityId(String str) {
            if (str != null) {
                this.contentInfo.put(ContextMappingConstants.ENTITY_ID, str);
            }
            return this;
        }

        public ClientEventBuilder withEntityIdType(EntityIdType entityIdType) {
            if (entityIdType != null) {
                this.contentInfo.put(ContextMappingConstants.ENTITY_ID_TYPE, entityIdType.toString());
            }
            return this;
        }

        public UiContentViewEvent build() {
            return new UiContentViewEvent(this, Collections.singletonList(this.contentInfo));
        }
    }
}
