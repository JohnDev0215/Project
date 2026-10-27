package com.amazon.music.inappmessaging.external;

import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
public final class IAMRequest {
    private static final String CAMPAIGN_ID_KEY = "campaignId";
    private static final String CATEGORY_KEY = "category";
    private static final String THROTTLE_KEY = "throttle";
    private static final String TRIGGER_KEY = "trigger";
    private static final Gson gson = new Gson();
    private final String campaignId;
    private final String category;
    private final Map<String, String> filters = constructFiltersMap();
    private final boolean throttle;
    private final String trigger;

    public IAMRequest(final boolean throttle, final String trigger, final String campaignId, final String category) {
        this.throttle = throttle;
        this.trigger = trigger;
        this.campaignId = campaignId;
        this.category = category;
    }

    public boolean shouldThrottle() {
        return this.throttle;
    }

    public String getTrigger() {
        return this.trigger;
    }

    public String getCampaignId() {
        return this.campaignId;
    }

    public String getCategory() {
        return this.category;
    }

    public Map<String, String> filters() {
        return this.filters;
    }

    public String toJson() {
        return gson.toJson(this);
    }

    public static IAMRequest fromJson(String requestJson) {
        return (IAMRequest) gson.fromJson(requestJson, IAMRequest.class);
    }

    private Map<String, String> constructFiltersMap() {
        HashMap map = new HashMap();
        map.put(THROTTLE_KEY, String.valueOf(this.throttle));
        String str = this.trigger;
        if (str != null) {
            map.put(TRIGGER_KEY, str);
        }
        String str2 = this.campaignId;
        if (str2 != null) {
            map.put("campaignId", str2);
        }
        String str3 = this.category;
        if (str3 != null) {
            map.put(CATEGORY_KEY, str3);
        }
        return map;
    }

    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || IAMRequest.class != o.getClass()) {
            return false;
        }
        IAMRequest iAMRequest = (IAMRequest) o;
        return Objects.equals(Boolean.valueOf(this.throttle), Boolean.valueOf(iAMRequest.throttle)) && Objects.equals(this.trigger, iAMRequest.trigger) && Objects.equals(this.campaignId, iAMRequest.campaignId) && Objects.equals(this.category, iAMRequest.category);
    }

    public int hashCode() {
        return Objects.hash(Boolean.valueOf(this.throttle), this.trigger, this.campaignId, this.category);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(THROTTLE_KEY);
        sb.append("=");
        sb.append(this.throttle);
        sb.append(",");
        sb.append(TRIGGER_KEY);
        sb.append("=");
        String str = this.trigger;
        if (str == null) {
            str = "null";
        }
        sb.append(str);
        sb.append(",");
        sb.append("campaignId");
        sb.append("=");
        String str2 = this.campaignId;
        if (str2 == null) {
            str2 = "null";
        }
        sb.append(str2);
        sb.append(",");
        sb.append(CATEGORY_KEY);
        sb.append("=");
        String str3 = this.category;
        sb.append(str3 != null ? str3 : "null");
        return sb.toString();
    }
}
