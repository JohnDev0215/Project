package com.amazon.podcast.metrics;

import Podcast.UIMetricsInterface.v1_0.UIMetricWriteElement;
import SOAAppSyncInterface.v1_0.WriteMethod;
import SOACoreInterface.v1_0.Method;
import com.amazon.podcast.Queues;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes5.dex */
public class UiMetricMethods {
    private static final Logger logger = LoggerFactory.getLogger("UiMetricMethods");

    public static List<Method> onClicked(final UiMetricAttributes$PageType pageType, final UiMetricAttributes$ActionType actionType, final UiMetricAttributes$ContentName contentName) {
        return onClicked(UUID.randomUUID().toString(), pageType, actionType, contentName);
    }

    public static List<Method> onClicked(final String uuid, final UiMetricAttributes$PageType pageType, final UiMetricAttributes$ActionType actionType, final UiMetricAttributes$ContentName contentName) {
        return buildMetricsMethod(UIMetricWriteElement.builder().withId(uuid).withSignalType(UiMetricAttributes$SignalType.UI_CLICK.name()).withContentSrc(UiMetricAttributes$ContentSrc.CLIENT.toString()).withPageType(pageType.toString()).withActionType(actionType.toString()).withInteractionType(UiMetricAttributes$InteractionType.TAP.name()).withContentName(contentName.toString()).build());
    }

    public static List<Method> onClicked(final String blockRef) {
        return buildMetricsMethod(UIMetricWriteElement.builder().withId(UUID.randomUUID().toString()).withSignalType(UiMetricAttributes$SignalType.UI_CLICK.name()).withMetricsPreset(blockRef).withInteractionType(UiMetricAttributes$InteractionType.TAP.toString()).build());
    }

    public static List<Method> onClicked(final String blockRef, final UiMetricAttributes$ActionType actionType, final UiMetricAttributes$PageType pageType, final UiMetricAttributes$ContentName contentName) {
        UIMetricWriteElement.Builder builderWithContentName = UIMetricWriteElement.builder().withId(UUID.randomUUID().toString()).withSignalType(UiMetricAttributes$SignalType.UI_CLICK.name()).withMetricsPreset(blockRef).withActionType(actionType.toString()).withInteractionType(UiMetricAttributes$InteractionType.TAP.toString()).withContentName(contentName == null ? null : contentName.toString());
        if (pageType == null) {
            return buildMetricsMethod(builderWithContentName.build());
        }
        builderWithContentName.withPageType(pageType.toString());
        return buildMetricsMethod(builderWithContentName.build());
    }

    public static List<Method> onClicked(final UiMetricAttributes$PageType pageType, final UiMetricAttributes$ActionType actionType, final UiMetricAttributes$EntityType entityType, final UiMetricAttributes$EntityIdType entityIdType, final String entityId, final Integer entityPos, final UiMetricAttributes$ContentName contentName) {
        if (StringUtils.isBlank(entityId)) {
            return Collections.EMPTY_LIST;
        }
        return buildMetricsMethod(UIMetricWriteElement.builder().withId(UUID.randomUUID().toString()).withSignalType(UiMetricAttributes$SignalType.UI_CLICK.name()).withContentSrc(UiMetricAttributes$ContentSrc.CLIENT.toString()).withPageType(pageType.toString()).withActionType(actionType.toString()).withInteractionType(UiMetricAttributes$InteractionType.TAP.name()).withEntityId(entityId).withEntityIdType(entityIdType.name()).withEntityType(entityType.name()).withEntityPos(entityPos).withContentName(contentName.toString()).build());
    }

    public static List<Method> onClicked(final UiMetricAttributes$PageType pageType, final UiMetricAttributes$ActionType actionType, final UiMetricAttributes$EntityType entityType, final UiMetricAttributes$EntityIdType entityIdType, final String entityId, final Integer entityPos, final String contentName) {
        if (StringUtils.isBlank(entityId)) {
            return Collections.EMPTY_LIST;
        }
        return buildMetricsMethod(UIMetricWriteElement.builder().withId(UUID.randomUUID().toString()).withSignalType(UiMetricAttributes$SignalType.UI_CLICK.name()).withContentSrc(UiMetricAttributes$ContentSrc.CLIENT.toString()).withPageType(pageType.toString()).withActionType(actionType.toString()).withInteractionType(UiMetricAttributes$InteractionType.TAP.name()).withEntityId(entityId).withEntityIdType(entityIdType.name()).withEntityType(entityType.name()).withEntityPos(entityPos).withContentName(contentName).build());
    }

    public static List<Method> onPageViewed(final UiMetricAttributes$PageType pageType) {
        return buildMetricsMethod(UIMetricWriteElement.builder().withId(UUID.randomUUID().toString()).withSignalType(UiMetricAttributes$SignalType.UI_PAGE_VIEW.name()).withPageType(pageType.toString()).build());
    }

    public static List<Method> onPageViewed(final UiMetricAttributes$PageType pageType, final UiMetricAttributes$DetailPageItemIdType idType, final String entityId) {
        if (StringUtils.isBlank(entityId)) {
            return Collections.EMPTY_LIST;
        }
        return buildMetricsMethod(UIMetricWriteElement.builder().withId(UUID.randomUUID().toString()).withSignalType(UiMetricAttributes$SignalType.UI_PAGE_VIEW.name()).withPageType(pageType.toString()).withDetailPageItemIdType(idType.name()).withDetailPageItemId(entityId).build());
    }

    public static List<Method> onContentViewed(final UiMetricAttributes$PageType pageType, final UiMetricAttributes$ContainerType containerType, final UiMetricAttributes$ContentName contentName) {
        return buildMetricsMethod(UIMetricWriteElement.builder().withId(UUID.randomUUID().toString()).withSignalType(UiMetricAttributes$SignalType.UI_CONTENT_VIEW.name()).withPageType(pageType.toString()).withContentSrc(UiMetricAttributes$ContentSrc.CLIENT.toString()).withContainerIndex(0).withContainerType(containerType.name()).withContentName(contentName.toString()).build());
    }

    public static String getMetricsPreset(final String blockRef) {
        try {
            return new JSONObject().put("blockRef", blockRef).toString();
        } catch (JSONException e) {
            logger.error("Failed to convert blockRef", (Throwable) e);
            return "";
        }
    }

    private static List<Method> buildMetricsMethod(UIMetricWriteElement element) {
        return Collections.singletonList(WriteMethod.builder().withQueue(Queues.uiMetricsSync()).withElement(Collections.singletonList(element)).build());
    }
}
