package com.amazon.music.platform.data.mediagateway;

import com.amazon.music.platform.graphql.mediagateway.generated.RecommendForGoogleAssistantQuery;
import com.amazon.music.platform.graphql.mediagateway.generated.type.MediaIdentifierType;
import com.amazon.music.platform.graphql.mediagateway.generated.type.MediaType;
import com.amazon.music.platform.providers.LoggingProvider;
import com.amazon.music.platform.providers.LoggingProviderKt;
import com.apollographql.apollo3.api.ApolloResponse;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RecommendForGoogleAssistant.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/amazon/music/platform/data/mediagateway/RecommendForGoogleAssistantInternalQuery;", "", "()V", "Companion", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class RecommendForGoogleAssistantInternalQuery {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* JADX INFO: compiled from: RecommendForGoogleAssistant.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J$\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\b\u0010\t\u001a\u0004\u0018\u00010\nJ\u001c\u0010\u000b\u001a\u0004\u0018\u00010\u00052\u0006\u0010\f\u001a\u00020\r2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0002¨\u0006\u000e"}, d2 = {"Lcom/amazon/music/platform/data/mediagateway/RecommendForGoogleAssistantInternalQuery$Companion;", "", "()V", "response", "", "Lcom/amazon/music/platform/data/mediagateway/RecommendForAssistant$MediaContent;", "apolloResponse", "Lcom/apollographql/apollo3/api/ApolloResponse;", "Lcom/amazon/music/platform/graphql/mediagateway/generated/RecommendForGoogleAssistantQuery$Data;", "loggingProvider", "Lcom/amazon/music/platform/providers/LoggingProvider;", "transformGoogleAssistantContent", "item", "Lcom/amazon/music/platform/graphql/mediagateway/generated/RecommendForGoogleAssistantQuery$RecommendForGoogleAssistant;", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        private final RecommendForAssistant$MediaContent transformGoogleAssistantContent(RecommendForGoogleAssistantQuery.RecommendForGoogleAssistant item, LoggingProvider loggingProvider) {
            if (loggingProvider != null) {
                loggingProvider.logInfo(LoggingProviderKt.getTag(this), "Google Assistant requestId=" + item.getRequestId());
            }
            RecommendForGoogleAssistantQuery.MediaContent mediaContent = item.getMediaContent();
            ArrayList arrayList = null;
            if (mediaContent != null) {
                RecommendForGoogleAssistantQuery.OnMediaContainer onMediaContainer = mediaContent.getOnMediaContainer();
                RecommendForGoogleAssistantQuery.OnMediaItem onMediaItem = mediaContent.getOnMediaItem();
                if (onMediaContainer != null) {
                    if (loggingProvider != null) {
                        loggingProvider.logDebug(LoggingProviderKt.getTag(this), "container: " + onMediaContainer);
                    }
                    String id = onMediaContainer.getId();
                    String identifier = onMediaContainer.getIdentifier();
                    MediaIdentifierType mediaIdentifierType = onMediaContainer.getMediaIdentifierType();
                    MediaType mediaType = onMediaContainer.getMediaType();
                    Boolean isOwned = onMediaContainer.getIsOwned();
                    List<RecommendForGoogleAssistantQuery.Item> items = onMediaContainer.getItems();
                    if (items != null) {
                        arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(items, 10));
                        Iterator<T> it = items.iterator();
                        while (it.hasNext()) {
                            arrayList.add(new Item(((RecommendForGoogleAssistantQuery.Item) it.next()).getId()));
                        }
                    }
                    return new MediaContainer(id, identifier, mediaIdentifierType, mediaType, isOwned, arrayList, item.getRequestId());
                }
                if (onMediaItem != null) {
                    if (loggingProvider != null) {
                        loggingProvider.logDebug(LoggingProviderKt.getTag(this), "mediaItem: " + onMediaItem);
                    }
                    return new MediaItem(onMediaItem.getId(), onMediaItem.getIdentifier(), onMediaItem.getMediaIdentifierType(), onMediaItem.getMediaType(), item.getRequestId());
                }
            }
            if (loggingProvider != null) {
                loggingProvider.logInfo(LoggingProviderKt.getTag(this), "empty experience");
            }
            return null;
        }

        public final List<RecommendForAssistant$MediaContent> response(ApolloResponse<RecommendForGoogleAssistantQuery.Data> apolloResponse, LoggingProvider loggingProvider) {
            RecommendForGoogleAssistantQuery.Data data;
            Intrinsics.checkNotNullParameter(apolloResponse, "apolloResponse");
            List<RecommendForGoogleAssistantQuery.RecommendForGoogleAssistant> recommendForGoogleAssistant = (apolloResponse == null || (data = (RecommendForGoogleAssistantQuery.Data) apolloResponse.data) == null) ? null : data.getRecommendForGoogleAssistant();
            if (loggingProvider != null) {
                loggingProvider.logInfo(LoggingProviderKt.getTag(this), "recommendExperiences: " + recommendForGoogleAssistant);
            }
            if (recommendForGoogleAssistant != null) {
                ArrayList arrayList = new ArrayList();
                Iterator<T> it = recommendForGoogleAssistant.iterator();
                while (it.hasNext()) {
                    RecommendForAssistant$MediaContent recommendForAssistant$MediaContentTransformGoogleAssistantContent = RecommendForGoogleAssistantInternalQuery.INSTANCE.transformGoogleAssistantContent((RecommendForGoogleAssistantQuery.RecommendForGoogleAssistant) it.next(), loggingProvider);
                    if (recommendForAssistant$MediaContentTransformGoogleAssistantContent != null) {
                        arrayList.add(recommendForAssistant$MediaContentTransformGoogleAssistantContent);
                    }
                }
                return CollectionsKt___CollectionsKt.toList(arrayList);
            }
            return CollectionsKt__CollectionsKt.emptyList();
        }
    }
}
