package com.amazon.music.freetier;

import com.amazon.music.skyfire.ui.skyfire.ServiceStage;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public class ExploreEndpointFactory {
    private static Map<ServiceStage, String> endpoints;

    static {
        HashMap map = new HashMap();
        endpoints = map;
        map.put(ServiceStage.LOCAL, "http://10.0.2.2:8080/api");
        endpoints.put(ServiceStage.DEV, "https://dev-na.explore.skill.music.a2z.com/api");
        endpoints.put(ServiceStage.QA, "https://qa-na.explore.skill.music.a2z.com/api");
        endpoints.put(ServiceStage.PROD, "https://na.explore.skill.music.a2z.com/api");
    }

    public static String getExploreEndpoint(ServiceStage serviceStage) {
        return endpoints.get(serviceStage);
    }
}
