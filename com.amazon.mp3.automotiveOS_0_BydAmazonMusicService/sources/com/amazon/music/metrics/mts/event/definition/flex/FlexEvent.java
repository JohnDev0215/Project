package com.amazon.music.metrics.mts.event.definition.flex;

import com.amazon.music.metrics.mts.MTSEvent;

/* JADX INFO: loaded from: classes4.dex */
public class FlexEvent extends MTSEvent {
    private FlexEvent(Builder builder) {
        super("flexEvent", 1L);
        addAttribute("flexEventName", builder.flexEventName);
        addAttribute("flexStr1", builder.flexStr1);
        addAttribute("flexStr2", builder.flexStr2);
        addAttribute("flexStr3", builder.flexStr3);
        addAttribute("flexStr4", builder.flexStr4);
        addAttribute("hcFlexStr1", builder.hcFlexStr1);
        addAttribute("hcFlexStr2", builder.hcFlexStr2);
        addAttribute("hcFlexStr3", builder.hcFlexStr3);
        addAttribute("hcFlexStr4", builder.hcFlexStr4);
        addAttribute("flexNum1", builder.flexNum1);
        addAttribute("flexNum2", builder.flexNum2);
        addAttribute("flexNum3", builder.flexNum3);
        addAttribute("flexNum4", builder.flexNum4);
        addAttribute("emitterId", builder.emitterId);
    }

    public static Builder builder(String str) {
        return new Builder(str);
    }

    public static class Builder {
        private String emitterId;
        private String flexEventName;
        private Float flexNum1;
        private Float flexNum2;
        private Float flexNum3;
        private Float flexNum4;
        private String flexStr1;
        private String flexStr2;
        private String flexStr3;
        private String flexStr4;
        private String hcFlexStr1;
        private String hcFlexStr2;
        private String hcFlexStr3;
        private String hcFlexStr4;

        private Builder(String str) {
            this.flexEventName = str;
        }

        public Builder withFlexStr1(String str) {
            this.flexStr1 = str;
            return this;
        }

        public Builder withFlexStr2(String str) {
            this.flexStr2 = str;
            return this;
        }

        public Builder withFlexStr3(String str) {
            this.flexStr3 = str;
            return this;
        }

        public Builder withFlexStr4(String str) {
            this.flexStr4 = str;
            return this;
        }

        public Builder withHcFlexStr1(String str) {
            this.hcFlexStr1 = str;
            return this;
        }

        public Builder withHcFlexStr2(String str) {
            this.hcFlexStr2 = str;
            return this;
        }

        public Builder withHcFlexStr3(String str) {
            this.hcFlexStr3 = str;
            return this;
        }

        public Builder withHcFlexStr4(String str) {
            this.hcFlexStr4 = str;
            return this;
        }

        public Builder withFlexNum1(Float f) {
            this.flexNum1 = f;
            return this;
        }

        public Builder withFlexNum2(Float f) {
            this.flexNum2 = f;
            return this;
        }

        public Builder withFlexNum3(Float f) {
            this.flexNum3 = f;
            return this;
        }

        public Builder withFlexNum4(Float f) {
            this.flexNum4 = f;
            return this;
        }

        public Builder withEmitterId(String str) {
            this.emitterId = str;
            return this;
        }

        public FlexEvent build() {
            return new FlexEvent(this);
        }
    }
}
