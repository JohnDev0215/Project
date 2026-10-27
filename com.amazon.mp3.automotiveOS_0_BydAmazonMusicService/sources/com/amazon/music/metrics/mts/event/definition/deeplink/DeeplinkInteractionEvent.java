package com.amazon.music.metrics.mts.event.definition.deeplink;

import com.amazon.music.metrics.mts.MTSEvent;

/* JADX INFO: loaded from: classes4.dex */
public class DeeplinkInteractionEvent extends MTSEvent {
    private DeeplinkInteractionEvent(Builder builder) {
        super("deeplinkInteraction", 1L);
        addAttribute("refTag", builder.refTag);
        addAttribute("csSessionId", builder.csSessionId);
        addAttribute("referer", builder.referer);
        addAttribute("deeplinkPath", builder.deeplinkPath);
        addAttribute("deeplinkPathIntended", builder.deeplinkPathIntended);
        addAttribute("deeplinkStage", builder.deeplinkStage);
        addAttribute("isFallBack", builder.isFallBack);
        addAttribute("isBranchLink", builder.isBranchLink);
        addAttribute("branchUrl", builder.branchUrl);
        addAttribute("associateTag", builder.associateTag);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String associateTag;
        private String branchUrl;
        private String csSessionId;
        private String deeplinkPath;
        private String deeplinkPathIntended;
        private String deeplinkStage;
        private boolean isBranchLink;
        private boolean isFallBack;
        private String refTag;
        private String referer;

        public Builder withRefTag(String str) {
            this.refTag = str;
            return this;
        }

        public Builder withCsSessionId(String str) {
            this.csSessionId = str;
            return this;
        }

        public Builder withReferer(String str) {
            this.referer = str;
            return this;
        }

        public Builder withDeeplinkPath(String str) {
            this.deeplinkPath = str;
            return this;
        }

        public Builder withDeeplinkPathIntended(String str) {
            this.deeplinkPathIntended = str;
            return this;
        }

        public Builder withDeeplinkStage(String str) {
            this.deeplinkStage = str;
            return this;
        }

        public Builder withIsFallBack(boolean z) {
            this.isFallBack = z;
            return this;
        }

        public Builder withIsBranchLink(boolean z) {
            this.isBranchLink = z;
            return this;
        }

        public Builder withBranchUrl(String str) {
            this.branchUrl = str;
            return this;
        }

        public Builder withAssociateTag(String str) {
            this.associateTag = str;
            return this;
        }

        public DeeplinkInteractionEvent build() {
            return new DeeplinkInteractionEvent(this);
        }
    }
}
