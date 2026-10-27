package com.amazon.client.metrics.thirdparty.clickstream.internal;

import com.amazon.client.metrics.thirdparty.MetricsException;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public enum ClickStreamData {
    REQUEST_ID("RequestId"),
    SESSION_ID("Session"),
    CUSTOMER_ID("CustomerId"),
    END_TIME("EndTime"),
    START_TIME("StartTime"),
    IP_ADDRESS("REMOTE_ADDR"),
    USER_AGENT("HTTP_USER_AGENT"),
    MARKETPLACE_ID("Marketplaceid"),
    MARKETPLACE("Marketplace"),
    DEVICE_TYPE_ID("deviceTypeID"),
    INFO("Info"),
    COUNTRY_OF_RESIDENCE("cor"),
    NON_ANONYMOUS_SESSION_ID("nonAnonymousSessionId"),
    NON_ANONYMOUS_CUSTOMER_ID("nonAnonymousCustomerId"),
    ANONYMOUS("anonymous"),
    PAGE_TYPE("page-type"),
    HIT_TYPE("hitType"),
    TEAM_NAME("team-name"),
    SITE_VARIANT("site_variant"),
    PAGE_ACTION("page-action"),
    SUB_PAGE_TYPE("sub-page-type"),
    PAGE_TYPE_ID("page-type-id"),
    TAB_ID("tab-id"),
    PAGE_ASSEMBLY_TYPE("pageAssemblyType"),
    IS_CUSTOMER_HIT("is-customer-hit"),
    IS_PRIME_CUSTOMER("is-prime-customer"),
    GROUPING_ASIN("groupingASIN"),
    PRODUCT_GLID("ProductGlId"),
    WEBLAB_CLIENT_ID("wl-client-id"),
    WEBLAB("wl"),
    ECOMMERCE_PAGE_ACTION("ecommerce-page-action"),
    ORDER_DATA("order-data"),
    IS_PRIME_ELIGIBLE_ITEM("is-prime-eligible-item"),
    IS_GLANCE_VIEW("is-glance-view"),
    IMPRESSION_PROGRAM_GROUP("programGroup"),
    IMPRESSION_TYPE("impressionType"),
    IMPRESSION_DATA("impressionData"),
    IMPRESSION_METADATA("impressionMetadata");

    public static final List<ClickStreamData> ALLOWED_OVERRIDES;
    private final String mName;

    static {
        ClickStreamData clickStreamData = USER_AGENT;
        ArrayList arrayList = new ArrayList();
        ALLOWED_OVERRIDES = arrayList;
        arrayList.add(clickStreamData);
    }

    public String getName() {
        return this.mName;
    }

    ClickStreamData(String str) {
        this.mName = str;
    }

    public static ClickStreamData fromString(String str) throws MetricsException {
        for (ClickStreamData clickStreamData : values()) {
            if (clickStreamData.getName().equalsIgnoreCase(str)) {
                return clickStreamData;
            }
        }
        throw new MetricsException(str + " is not a valid ClickStreamData");
    }

    public static boolean isOverrideAllowed(ClickStreamData clickStreamData) {
        return ALLOWED_OVERRIDES.contains(clickStreamData);
    }
}
