package com.amazon.music.explore.skyfire;

import CoreInterface.v1_0.Method;
import CoreInterface.v1_0.Queue;
import InteractionInterface.v1_0.ImmutableInvokeHttpSkillMethod;
import InteractionInterface.v1_0.ImmutableSetHttpSkillUrlMethod;
import QueuesInterface.v1_0.ImmutableMultiThreadedQueue;
import QueuesInterface.v1_0.ImmutableSingleThreadedQueue;
import com.amazon.music.explore.ExploreEndpointFactory;
import com.amazon.music.explore.Mappers;
import com.amazon.music.skyfire.ui.skyfire.ServiceStage;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes4.dex */
public class SkyFireUtils {
    public static Queue stHttpQueue() {
        return ImmutableSingleThreadedQueue.builder().id("ST_HTTP").build();
    }

    public static Queue mtHttpQueue() {
        return ImmutableMultiThreadedQueue.builder().id("MT_HTTP").build();
    }

    public static Method getInvokeHttpSkillMethod(String str) {
        return getInvokeHttpSkillMethod(str, null, null, null, null);
    }

    public static Method getInvokeHttpSkillMethod(String str, List<Method> list) {
        return getInvokeHttpSkillMethod(str, null, null, list, null);
    }

    public static Method getInvokeHttpSkillMethod(String str, @Nullable Map<String, String> map) {
        return getInvokeHttpSkillMethod(str, map, null, null, null);
    }

    public static Method getInvokeHttpSkillMethod(String str, @Nullable Map<String, String> map, @Nullable List<Method> list) {
        return getInvokeHttpSkillMethod(str, map, list, null, null);
    }

    public static Method getInvokeHttpSkillMethod(String str, @Nullable Map<String, String> map, @Nullable List<Method> list, @Nullable List<String> list2) {
        return getInvokeHttpSkillMethod(str, map, list, null, list2);
    }

    public static Method getInvokeHttpSkillMethod(String str, @Nullable Map<String, String> map, @Nullable List<Method> list, @Nullable List<Method> list2, @Nullable List<String> list3) {
        ImmutableInvokeHttpSkillMethod.Builder builder = ImmutableInvokeHttpSkillMethod.builder();
        Queue queueStHttpQueue = stHttpQueue();
        builder.url(buildUrl(str, map)).queue(queueStHttpQueue).addOnError(ImmutableSetHttpSkillUrlMethod.builder().queue(queueStHttpQueue).url(null).build());
        if (list != null) {
            builder.addAllOnError(list);
        }
        if (list2 != null) {
            builder.addAllAfter(list2);
        }
        if (list3 != null) {
            builder.addAllClientInformation(list3);
        }
        return builder.build();
    }

    private static String buildUrl(String str, @Nullable Map<String, String> map) {
        HttpUrl.Builder builderNewBuilder = HttpUrl.parse(ExploreEndpointFactory.getExploreEndpoint(ServiceStage.PROD) + str).newBuilder();
        if (map != null) {
            for (String str2 : map.keySet()) {
                String str3 = map.get(str2);
                if (str3 != null) {
                    builderNewBuilder.addQueryParameter(str2, str3);
                }
            }
        }
        return builderNewBuilder.build().getUrl();
    }

    public static String getSerializedMethods(List<Method> list) throws JsonProcessingException {
        return Mappers.mapper().writerFor(Mappers.mapper().getTypeFactory().constructCollectionType(List.class, Method.class)).writeValueAsString(list);
    }
}
