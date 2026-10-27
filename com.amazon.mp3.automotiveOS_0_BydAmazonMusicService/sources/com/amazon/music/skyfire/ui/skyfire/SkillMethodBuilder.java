package com.amazon.music.skyfire.ui.skyfire;

import CoreInterface.v1_0.Method;
import CoreInterface.v1_0.Queue;
import InteractionInterface.v1_0.ImmutableInvokeHttpSkillMethod;
import InteractionInterface.v1_0.ImmutableSetHttpSkillUrlMethod;
import com.amazon.music.skyfire.ui.providers.SkyFireConfigurationProvider;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.HttpUrl;

/* JADX INFO: compiled from: SkillMethodBuilder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u001e\u001a\u00020\u0007R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\tR\u001f\u0010\u000e\u001a\u0010\u0012\u0004\u0012\u00020\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u00100\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\u00020\u0014X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001c\u0010\u0019\u001a\u0004\u0018\u00010\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001d¨\u0006\u001f"}, d2 = {"Lcom/amazon/music/skyfire/ui/skyfire/SkillMethodBuilder;", "", "configProvider", "Lcom/amazon/music/skyfire/ui/providers/SkyFireConfigurationProvider;", "(Lcom/amazon/music/skyfire/ui/providers/SkyFireConfigurationProvider;)V", "after", "", "LCoreInterface/v1_0/Method;", "getAfter", "()Ljava/util/List;", "getConfigProvider", "()Lcom/amazon/music/skyfire/ui/providers/SkyFireConfigurationProvider;", "onError", "getOnError", "queryParams", "", "", "getQueryParams", "()Ljava/util/Map;", "queue", "LCoreInterface/v1_0/Queue;", "getQueue", "()LCoreInterface/v1_0/Queue;", "setQueue", "(LCoreInterface/v1_0/Queue;)V", "skillPath", "getSkillPath", "()Ljava/lang/String;", "setSkillPath", "(Ljava/lang/String;)V", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SkillMethodBuilder {
    private final List<Method> after;
    private final SkyFireConfigurationProvider configProvider;
    private final List<Method> onError;
    private final Map<String, String> queryParams;
    private Queue queue;
    private String skillPath;

    public SkillMethodBuilder(SkyFireConfigurationProvider configProvider) {
        Intrinsics.checkNotNullParameter(configProvider, "configProvider");
        this.configProvider = configProvider;
        this.queue = Queues.INSTANCE.getSingleTheaded();
        this.queryParams = new LinkedHashMap();
        ArrayList arrayList = new ArrayList();
        this.onError = arrayList;
        this.after = new ArrayList();
        arrayList.add(ImmutableSetHttpSkillUrlMethod.builder().queue(this.queue).url(null).build());
    }

    public final SkyFireConfigurationProvider getConfigProvider() {
        return this.configProvider;
    }

    public final String getSkillPath() {
        return this.skillPath;
    }

    public final void setSkillPath(String str) {
        this.skillPath = str;
    }

    public final Queue getQueue() {
        return this.queue;
    }

    public final void setQueue(Queue queue) {
        Intrinsics.checkNotNullParameter(queue, "<set-?>");
        this.queue = queue;
    }

    public final Map<String, String> getQueryParams() {
        return this.queryParams;
    }

    public final List<Method> getOnError() {
        return this.onError;
    }

    public final List<Method> getAfter() {
        return this.after;
    }

    public final Method build() {
        String str = this.skillPath;
        if (str == null) {
            throw new IllegalStateException("Path cannot be null".toString());
        }
        Queue queue = this.queue;
        HttpUrl httpUrl = HttpUrl.INSTANCE.parse(Intrinsics.stringPlus(this.configProvider.getServiceURL(), str));
        HttpUrl.Builder builderNewBuilder = httpUrl == null ? null : httpUrl.newBuilder();
        for (Map.Entry<String, String> entry : this.queryParams.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (value != null && builderNewBuilder != null) {
                builderNewBuilder.addQueryParameter(key, value);
            }
        }
        ImmutableInvokeHttpSkillMethod.Builder builder = ImmutableInvokeHttpSkillMethod.builder();
        builder.url(String.valueOf(builderNewBuilder != null ? builderNewBuilder.build() : null));
        builder.queue(queue);
        builder.addAllOnError(getOnError());
        builder.addAllAfter(getAfter());
        ImmutableInvokeHttpSkillMethod immutableInvokeHttpSkillMethodBuild = builder.build();
        Intrinsics.checkNotNullExpressionValue(immutableInvokeHttpSkillMethodBuild, "builder().apply {\n      …(after)\n        }.build()");
        return immutableInvokeHttpSkillMethodBuild;
    }
}
