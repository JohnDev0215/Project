package com.amazon.music.curate.skyfire.network;

import CoreInterface.v1_0.Method;
import CoreInterface.v1_0.Queue;
import InteractionInterface.v1_0.ImmutableInvokeHttpSkillMethod;
import InteractionInterface.v1_0.ImmutableSetHttpSkillUrlMethod;
import QueuesInterface.v1_0.ImmutableMultiThreadedQueue;
import QueuesInterface.v1_0.ImmutableSingleThreadedQueue;
import androidx.annotation.Nullable;
import com.amazon.music.curate.skyfire.Mappers;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.List;
import java.util.Map;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes4.dex */
public final class InvokeHttpSkillMethod {
    public static Queue stHttpQueue() {
        return ImmutableSingleThreadedQueue.builder().id("ST_HTTP").build();
    }

    public static Queue mtHttpQueue() {
        return ImmutableMultiThreadedQueue.builder().id("MT_HTTP").build();
    }

    public static Method get(final String endpoint, final String skillPath) {
        return get(endpoint, skillPath, null);
    }

    public static Method get(final String endpoint, final String skillPath, @Nullable final Map<String, String> queryParams) {
        return get(endpoint, skillPath, queryParams, null);
    }

    public static Method get(final String endpoint, final String skillPath, @Nullable final Map<String, String> queryParams, @Nullable final List<Method> onError) {
        ImmutableInvokeHttpSkillMethod.Builder builder = ImmutableInvokeHttpSkillMethod.builder();
        Queue queueStHttpQueue = stHttpQueue();
        builder.url(buildUrl(endpoint, skillPath, queryParams)).queue(queueStHttpQueue).addOnError(ImmutableSetHttpSkillUrlMethod.builder().queue(queueStHttpQueue).url(null).build());
        if (onError != null) {
            builder.addAllOnError(onError);
        }
        return builder.build();
    }

    private static String buildUrl(final String endpoint, final String skillPath, @Nullable final Map<String, String> queryParams) {
        HttpUrl.Builder builderNewBuilder = HttpUrl.parse(endpoint + skillPath).newBuilder();
        if (queryParams != null) {
            for (String str : queryParams.keySet()) {
                String str2 = queryParams.get(str);
                if (str2 != null) {
                    builderNewBuilder.addQueryParameter(str, str2);
                }
            }
        }
        return builderNewBuilder.build().getUrl();
    }

    public static String serializeMethods(final List<Method> methods) throws JsonProcessingException {
        return Mappers.mapper().writerFor(Mappers.mapper().getTypeFactory().constructCollectionType(List.class, Method.class)).writeValueAsString(methods);
    }
}
