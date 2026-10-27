package com.amazon.music.platform.providers;

import com.amazon.music.platform.Outcome;
import com.amazon.music.platform.data.mediagateway.RecommendForAssistant$MediaContent;
import com.amazon.music.platform.graphql.mediagateway.generated.RecommendForGoogleAssistantQuery;
import java.util.List;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;

/* JADX INFO: compiled from: MediaGatewayServiceProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J'\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u00042\u0006\u0010\u0003\u001a\u00020\u0002H¦@ø\u0001\u0000¢\u0006\u0004\b\u0007\u0010\b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\t"}, d2 = {"Lcom/amazon/music/platform/providers/MediaGatewayServiceProvider;", "Lcom/amazon/music/platform/providers/GraphQLServiceProvider;", "Lcom/amazon/music/platform/graphql/mediagateway/generated/RecommendForGoogleAssistantQuery;", "queryData", "Lcom/amazon/music/platform/Outcome;", "", "Lcom/amazon/music/platform/data/mediagateway/RecommendForAssistant$MediaContent;", "recommend", "(Lcom/amazon/music/platform/graphql/mediagateway/generated/RecommendForGoogleAssistantQuery;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public interface MediaGatewayServiceProvider extends GraphQLServiceProvider {
    Object recommend(RecommendForGoogleAssistantQuery recommendForGoogleAssistantQuery, Continuation<? super Outcome<? extends List<? extends RecommendForAssistant$MediaContent>>> continuation);
}
