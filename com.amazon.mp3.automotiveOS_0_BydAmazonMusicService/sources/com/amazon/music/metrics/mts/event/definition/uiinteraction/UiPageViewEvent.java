package com.amazon.music.metrics.mts.event.definition.uiinteraction;

import com.amazon.music.find.utils.ContentUtils;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amazon.music.metrics.mts.MTSEvent;
import com.amazon.music.metrics.mts.event.types.ActivityIdType;
import com.amazon.music.metrics.mts.event.types.ResponseIdType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ViewType;
import com.google.android.gms.common.internal.ImagesContract;

/* JADX INFO: loaded from: classes4.dex */
public class UiPageViewEvent extends MTSEvent {
    private UiPageViewEvent(Builder builder) {
        super("uiPageView", 4L);
        setTimestamp(builder.eventTime);
        addAttribute("pageType", builder.pageType);
        if (builder.experienceMode != null) {
            addAttribute("experienceMode", builder.experienceMode);
        }
        if (builder.pageSubType != null) {
            addAttribute("pageSubType", builder.pageSubType);
        }
        if (builder.detailPageItemId != null) {
            addAttribute("detailPageItemId", builder.detailPageItemId);
        }
        if (builder.detailPageItemIdType != null) {
            addAttribute("detailPageItemIdType", builder.detailPageItemIdType);
        }
        if (builder.externalReference != null) {
            addAttribute("refMarker", builder.externalReference);
        }
        if (builder.associateTag != null) {
            addAttribute("associateTag", builder.associateTag);
        }
        if (builder.viewType != null) {
            addAttribute("viewType", builder.viewType);
        }
        if (builder.blockRef != null) {
            addAttribute("blockRef", builder.blockRef);
        }
        if (builder.loadTime > 0) {
            addAttribute("loadTimeMilliseconds", builder.loadTime);
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
        if (builder.locale != null) {
            addAttribute(Splash.PARAMS_LOCALE, builder.locale);
        }
        if (builder.url != null) {
            addAttribute(ImagesContract.URL, builder.url);
        }
        if (builder.userAgent != null) {
            addAttribute("userAgent", builder.userAgent);
        }
        if (builder.csSessionId != null) {
            addAttribute("csSessionId", builder.csSessionId);
        }
    }

    public static Builder builder(String str) {
        return new Builder(str);
    }

    public static class Builder {
        private String activityId;
        private String activityIdType;
        private String associateTag;
        private String blockRef;
        private String csSessionId;
        private String detailPageItemId;
        private String detailPageItemIdType;
        private long eventTime;
        private String experienceMode;
        private String externalReference;
        private long loadTime;
        private String locale;
        private String pageSubType;
        private String pageType;
        private String responseId;
        private String responseIdType;
        private String searchQuery;
        private String url;
        private String userAgent;
        private String viewType;

        private Builder(String str) {
            this.eventTime = System.currentTimeMillis();
            this.pageType = str;
        }

        public Builder withDetailPageItemId(String str) {
            this.detailPageItemId = str;
            return this;
        }

        public Builder withDetailPageItemIdType(String str) {
            this.detailPageItemIdType = str;
            return this;
        }

        public Builder withExternalReference(String str) {
            this.externalReference = str;
            return this;
        }

        public Builder withAssociateTag(String str) {
            this.associateTag = str;
            return this;
        }

        public Builder withViewType(ViewType viewType) {
            if (viewType == null) {
                this.viewType = null;
            } else {
                this.viewType = viewType.getMetricsValue();
            }
            return this;
        }

        public Builder withEventTime(long j) {
            this.eventTime = j;
            return this;
        }

        public Builder withLoadTime(long j) {
            this.loadTime = j;
            return this;
        }

        public Builder withPageSubType(String str) {
            this.pageSubType = str;
            return this;
        }

        public Builder withBlockRef(String str) {
            this.blockRef = str;
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

        public Builder withExperienceMode(String str) {
            if (str != null) {
                this.experienceMode = str;
            }
            return this;
        }

        public Builder withLocale(String str) {
            if (str != null) {
                this.locale = str;
            }
            return this;
        }

        public Builder withUrl(String str) {
            if (str != null) {
                this.url = str;
            }
            return this;
        }

        public Builder withUserAgent(String str) {
            if (str != null) {
                this.userAgent = str;
            }
            return this;
        }

        public Builder withCsSessionId(String str) {
            if (str != null) {
                this.csSessionId = str;
            }
            return this;
        }

        public UiPageViewEvent build() {
            return new UiPageViewEvent(this);
        }
    }
}
