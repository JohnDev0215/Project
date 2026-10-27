package com.spotify.connectivity.productstateesperanto;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableMap;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class ProductStateValuesResponse {
    private final Map<String, String> mProperties = new HashMap();

    public static Optional<Map<String, String>> asOptionalMap(ProductStateValuesResponse productStateValuesResponse) {
        return Optional.e(productStateValuesResponse.getProperties());
    }

    public Map<String, String> getProperties() {
        return ImmutableMap.b(this.mProperties);
    }

    public void setProperty(String str, String str2) {
        this.mProperties.put(str, str2);
    }
}
